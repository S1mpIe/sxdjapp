package com.finals.sxdj.repository;

import com.finals.sxdj.model.FarmerOrder;
import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.model.Resources;
import com.finals.sxdj.model.sqlmodel.Farmers;
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
    GoodsData[] queryAllSold(int farmerId);
    Resources[] getResource(String cate, long id);
    FarmerOrder[] queryFarmerOrder(int farmerId);
    int insertVerifiedFarmer(String openId,Farmers farmers);
    int insertNewGoods(GoodsData goods,int farmerId);
    int insertPathData(long id, String url);
    void updateFarmer(String openId,String key,String value);
    void updateGoods(GoodsData goodsData);
    void deleteImage(long id);

    void insertResource(Long owner, String cate, long ids);
}
