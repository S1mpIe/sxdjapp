package com.finals.sxdj.repository;

import com.finals.sxdj.model.GoodsData;
import org.springframework.stereotype.Component;

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
    GoodsData[] queryGoodsByRecommend(int recommendId);
    GoodsData[] queryGoodsByCategory(String category);
    GoodsData queryGoodsById(int id);
    void updateGoodsNumber(int id,int number);
    void updateGoodsSaleNumber(int id,int saleNumber);
    void increaseSaleNumber(int id,int saleNumber);
}
