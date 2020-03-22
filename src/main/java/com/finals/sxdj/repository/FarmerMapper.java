package com.finals.sxdj.repository;

import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.model.sqlmodel.Farmers;
import org.springframework.stereotype.Component;

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
    GoodsData[] queryAllSold(int farmerId);
    int insertVerifiedFarmer(String openId,String name,String introduce);
    int insertNewGoods(GoodsData[] goods,int farmerId);
}
