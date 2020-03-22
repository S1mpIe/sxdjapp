package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface OrderService {
    JSONObject applyNewOrder(Map orderMap,String openId);
    JSONObject getPersonOrders(String openId);
    JSONObject getOrderPay(Map orderMap);
    JSONObject getTeamOrders(String openId);
}
