package com.finals.sxdj.repository;

import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.model.sqlmodel.Comment;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author S1mpIe
 */
@Component
public interface GoodsMapper {
    /**
     * 根据每日推荐Id获取对应商品
     * @param recommendId
     * @return
     */
    GoodsData[] queryGoodsByRecommend(long recommendId);
    GoodsData[] queryGoodsByCategory(String category);
    GoodsData queryGoodsById(long id);
    void updateGoodsNumber(int id,int number);
    void updateGoodsSaleNumber(int id,int saleNumber);
    void increaseSaleNumber(long id,int saleNumber);

    void deleteGoods(long goodsId);

    void offGoods(long goodsId);

    Comment[] queryComments(long goodsId);

    void insertComment(long key, String value,int stars, String author, Date time);
}
