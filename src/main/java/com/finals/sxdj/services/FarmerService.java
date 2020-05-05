package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.GoodsData;

public interface FarmerService {
    JSONObject applyFarmer(String openId,String name,String introduce);
    JSONObject applyGoods(GoodsData[] goods,int farmerId);
    JSONObject queryAllSold(int farmerId);
    JSONObject queryFarmer(String openId);
    JSONObject queryFarmerById(int farmerId);
}
