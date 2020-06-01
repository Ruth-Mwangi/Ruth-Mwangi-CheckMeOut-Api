package dao;

import models.Goods;

public interface GoodsDao {
    Goods getGood(String code);
    void addItem(Goods good);

}
