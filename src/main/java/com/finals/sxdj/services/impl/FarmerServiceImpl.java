package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.repository.FarmerMapper;
import com.finals.sxdj.services.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmerServiceImpl implements FarmerService {
    @Autowired
    private FarmerMapper farmerMapper;

    @Override
    public JSONObject applyFarmer(String openId, String name, String introduce) {
        int i = farmerMapper.insertVerifiedFarmer(openId, name, introduce);
        JSONObject jsonObject = new JSONObject();
        if(i == 1){
            jsonObject.put("status","success");
        }else {
            jsonObject.put("status","failed");
        }
        return jsonObject;
    }

    @Override
    public JSONObject applyGoods(GoodsData[] goods, int farmerId) {
        int length = goods.length;
        int number = farmerMapper.insertNewGoods(goods, farmerId);
        JSONObject jsonObject = new JSONObject();
        if(number == length){
            jsonObject.put("status","success");
        }else {
            jsonObject.put("status","failed");
        }
        return jsonObject;
    }

    @Override
    public JSONObject queryAllSold(int farmerId) {
        GoodsData[] goodsData = farmerMapper.queryAllSold(farmerId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goods",goodsData);
        return jsonObject;
    }
}
