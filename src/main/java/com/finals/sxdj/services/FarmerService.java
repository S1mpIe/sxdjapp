package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.model.sqlmodel.Farmers;
import org.springframework.web.multipart.MultipartFile;

public interface FarmerService {
    JSONObject applyFarmer(String openId, Farmers farmers);
    JSONObject applyGoods(GoodsData goods,int farmerId);
    JSONObject queryAllSold(int farmerId);
    JSONObject queryFarmer(String openId);
    JSONObject queryFarmerById(int farmerId);
    JSONObject updateGoods(int farmerId,GoodsData goodsData);
    JSONObject deleteGoods(int goodsId,String cate);
    JSONObject uploadImage(MultipartFile files);
    JSONObject deleteImage(long pathId);
    JSONObject applyResources(String body);
    JSONObject queryResources(String cate, long id);
    JSONObject getFarmerOrders(int farmerId);
}
