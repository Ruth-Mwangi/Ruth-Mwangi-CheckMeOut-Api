package dao;

import models.Goods;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oGoodsDaoTest {

    private static Sql2oGoodsDao sql2oGoodsDao;
    private static Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/checkmeout_test";
        Sql2o sql2o = new Sql2o(connectionString, "wangui", "33234159");

        sql2oGoodsDao=new Sql2oGoodsDao(sql2o);
        conn=sql2o.open();
    }
    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void correctItemIsRetrieved() throws Exception {
        Goods good=sql2oGoodsDao.getGood("5034624023492");
        assertEquals(good.getName(),"notebook");
    }

//    @Test
//    public void itemIsAdded() {
//        Goods good=new Goods("6006323598471","lotion",500);
//        sql2oGoodsDao.addItem(good);
//        assertEquals(good.getId(),7);
//
//    }
}