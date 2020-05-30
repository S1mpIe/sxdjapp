package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.ExtractPoint;
import com.finals.sxdj.repository.ExtractMapper;
import com.finals.sxdj.repository.OrderMapper;
import com.finals.sxdj.services.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtractServiceImpl implements ExtractService {
    @Autowired
    public ExtractMapper extractMapper;
    @Autowired
    public OrderMapper orderMapper;
    @Override
    public JSONObject insertNewAuditedPoint(String openId,ExtractPoint point) {
        int number = extractMapper.insertNewPoint(openId,point);
        JSONObject jsonObject = new JSONObject();
        if(number == 1){
            jsonObject.put("status","success");
        }else {
            jsonObject.put("status","failed");
        }
        return jsonObject;
    }

    @Override
    public JSONObject getNearbyPoint(double latitude, double longitude) {
        for (int i = 0;i < 5;i++){
            ExtractPoint point = new ExtractPoint();
            point.setAddress("中国湖南长沙");
            point.setArea(140);
            point.setLatitude(latitude+i+0.1);
            point.setLongitude(longitude+i+0.1);
            point.setOwner("Yakult");
            point.setIdNumber("430XXXXXXXXXX");
            point.setPhoneNumber("14707348710");
            point.setName("样例自提点-" + i);
            extractMapper.insertNewPoint("example",point);
        }
        ExtractPoint[] extractPoints = extractMapper.queryAllActivePoint(latitude, longitude, 8);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("points",extractPoints);
        return jsonObject;
    }

    @Override
    public JSONObject getPersonPoint(String openId) {
        JSONObject jsonObject = new JSONObject();
        ExtractPoint point = extractMapper.queryPersonPoint(openId);
        jsonObject.put("point",point);
        if(point != null){
            jsonObject.put("orders",orderMapper.queryOrdersByPoint(point.getId()));
        }
        return jsonObject;
    }

    @Override
    public JSONObject getAllAuditedPoint() {
        ExtractPoint[] extractPoints = extractMapper.queryAllPoint("%");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("extractPoints",extractPoints);
        return jsonObject;
    }

    @Override
    public JSONObject receiveGoods(String openId, int orderId) {
        orderMapper.updateOrder(orderId,"status","待提货");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        return jsonObject;
    }

    @Override
    public JSONObject updatePointStatus(String openId, String status) {
        extractMapper.updatePointStatus(openId,status);
        return null;
    }
}
