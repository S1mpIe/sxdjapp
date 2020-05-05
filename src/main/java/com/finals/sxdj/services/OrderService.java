package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface OrderService {
    JSONObject applyNewOrder(Map orderMap,String openId);
    JSONObject putGoods(String openId,int goodsId,int number);
    JSONObject getPersonOrders(String openId);
    JSONObject getPersonShoppingCart(String openId);
    JSONObject getOrderPay(Map orderMap);
    JSONObject getTeamOrders(String openId);
    JSONObject deleteShoppingCart(String openId,int goodsId);
}
