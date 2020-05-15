package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.ShoppingAddress;

import java.util.Map;

public interface OrderService {
    JSONObject applyNewOrder(String body,String openId);
    JSONObject addShoppingAddress(String name,int pointId,long number, String openId);
    JSONObject putGoods(String openId,int goodsId,int number);
    JSONObject receiveGoods(String openId,int orderId);
    JSONObject getPersonOrders(String openId,String status);
    JSONObject getPersonShoppingCart(String openId);
    JSONObject getShoppingAddress(String openId);
    JSONObject getTeamOrders(String openId);
    JSONObject deleteShoppingCart(String openId,int goodsId);
}
