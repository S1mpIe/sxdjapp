package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.ExtractPoint;

public interface ExtractService {
    JSONObject insertNewAuditedPoint(String openId,ExtractPoint point);
    JSONObject getNearbyPoint(double latitude,double longitude);
    JSONObject getPersonPoint(String openId);
    JSONObject getAllAuditedPoint();
    JSONObject receiveGoods(String openId,int orderId);
    JSONObject updatePointStatus(String openId, String status);
}
