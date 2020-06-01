package dao;

import models.Goods;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class Sql2oGoodsDao implements GoodsDao {

    private final Sql2o sql2o;

    public Sql2oGoodsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Goods getGood(String code) {
        try (Connection con=sql2o.open()){
            String sql="SELECT * FROM goods WHERE code=:code";
            return con.createQuery(sql)
                    .addParameter("code",code)
                    .executeAndFetchFirst(Goods.class);

        }
    }

    @Override
    public void addItem(Goods good) {
        try (Connection con=sql2o.open()){
            String sql ="INSERT INTO goods (code,name,price) VALUES (:code,:name,:price) ";

            int id=(int) con.createQuery(sql,true)
                    .bind(good)
                    .executeUpdate()
                    .getKey();
            good.setId(id);


        }catch (Sql2oException e){
            System.out.println(e);
        }


    }
}
