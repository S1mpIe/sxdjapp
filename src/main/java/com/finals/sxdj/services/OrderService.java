package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;

public interface OrderService {
    JSONObject applyNewOrder(String body,String openId);
    JSONObject addShoppingAddress(String name, long pointId, long number, String openId);
    JSONObject putGoods(String openId, long goodsId, int number);
    JSONObject receiveGoods(String openId, long orderId);
    JSONObject getPersonOrders(String openId,String status);
    JSONObject getPersonShoppingCart(String openId);
    JSONObject getShoppingAddress(String openId);
    JSONObject getTeamOrders(String openId);
    JSONObject deleteShoppingCart(String openId, long goodsId);
}
