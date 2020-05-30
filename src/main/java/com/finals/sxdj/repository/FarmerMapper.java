package com.finals.sxdj.repository;

import com.finals.sxdj.model.FarmerOrder;
import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.model.Resources;
import com.finals.sxdj.model.sqlmodel.Farmers;
import com.finals.sxdj.model.sqlmodel.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author S1mpIe
 */
@Component
public interface FarmerMapper {
    /**
     * 获取所有农户信息
     * @return
     */
    Farmers[] queryAllFarmers();
    Farmers queryFarmer(String openId);
    Farmers queryFarmerById(int farmerId);
    GoodsData[] queryAllSold(long farmerId);
    Resources[] getResource(String cate, long owner);
    Order[] queryFarmerOrder(int farmerId);
    int insertVerifiedFarmer(Farmers farmers);
    int insertNewGoods(long id, GoodsData goods, long farmerId);
    int insertPathData(long id, String url);
    void updateFarmer(Farmers farmers);
    void updateGoods(GoodsData goodsData);
    void deleteImage(long id);
    void insertResource(Long owner, String cate, long ids);
    void deleteAllResource(long owner, String cate);
}
