package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.model.sqlmodel.Farmers;
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
        System.out.println(name + "-" + introduce + "-" + openId);
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

    @Override
    public JSONObject queryFarmer(String openId) {
        Farmers farmers = farmerMapper.queryFarmer(openId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","failed");
        if(farmers != null){
            if (farmers.getStatus().equals("待审核")){
                jsonObject.put("msg","未审核");
            }else {
                jsonObject.put("status", "success");
                jsonObject.put("farmer",farmers);
            }
        }else {
            jsonObject.put("msg","尚未注册");
        }
        return jsonObject;
    }

    @Override
    public JSONObject queryFarmerById(int farmerId) {
        Farmers farmers = farmerMapper.queryFarmerById(farmerId);
        farmers.setOpenId(null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("farmer",farmers);
        return jsonObject;
    }
}
