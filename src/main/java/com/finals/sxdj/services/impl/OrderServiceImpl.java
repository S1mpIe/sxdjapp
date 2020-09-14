package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.Account;
import com.finals.sxdj.model.CartGoods;
import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.model.sqlmodel.Order;
import com.finals.sxdj.model.sqlmodel.ShoppingAddress;
import com.finals.sxdj.repository.GoodsMapper;
import com.finals.sxdj.repository.OrderMapper;
import com.finals.sxdj.repository.UserMapper;
import com.finals.sxdj.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserMapper userMapper;
    @Transactional
    @Override
    public JSONObject applyNewOrder(String body, String openId) {
        JSONObject orderJson = JSONObject.parseObject(body);
        Map orderMap =  JSONObject.toJavaObject((JSONObject) orderJson.get("orderMap"),Map.class);
        Set set = orderMap.entrySet();
        Iterator iterator = set.iterator();
        JSONObject jsonObject = new JSONObject();
        Order lastOrder = orderMapper.queryLastOrder();
        long orderId = System.currentTimeMillis()/100;
        double totalPay = 0;
        log.error("start iterator");
        while (iterator.hasNext()){
            log.error("iteratoring...");
            Map.Entry next = (Map.Entry) iterator.next();
            Long goodsId = Long.valueOf((String) next.getKey());
            int number = (int)next.getValue();
            GoodsData goodsData = goodsMapper.queryGoodsById(goodsId);
            if(goodsData.getNumber() >= number){
                goodsMapper.increaseSaleNumber(goodsData.getId(),number);
                double totalPrice = number * goodsData.getPrice();
                totalPay += totalPrice;
                userMapper.updateConsumerBalance("farmer-" + goodsData.getOriginId(),Double.valueOf(String.format("%.2f", (userMapper.queryCount("farmer-" + goodsData.getOriginId()).getBalance() + totalPrice*0.92) )));
                orderMapper.insertNewOrderData(orderId,goodsData.getId(),number,goodsData.getPrice(),(double) Math.round(totalPay * 100) /100);
                orderMapper.deleteShoppingCartGoods(openId,goodsData.getId());
            }else {
                jsonObject.put("status","failed");
                jsonObject.put("msg",goodsData.getId() + "库存不足");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return jsonObject;
            }
        }
        log.error("iterator over");
        Account account = userMapper.queryCount(openId);
        if(account.getBalance() < totalPay){
            jsonObject.put("status","failed");
            jsonObject.put("msg","balance not enough");
        }else {
            orderMapper.insertNewOrder(orderId,openId,new Date(System.currentTimeMillis()),Double.valueOf(String.format("%.2f",totalPay)),orderJson.getIntValue("addressId"));
            userMapper.updateConsumerBalance(openId,Double.valueOf(String.format("%.2f", account.getBalance()-(double) Math.round(totalPay * 100) / 100 )));
            userMapper.insertNewAccountDetail(openId,new Date(System.currentTimeMillis()),"消费", (long) orderId,-1 * totalPay);
            ShoppingAddress addressId = orderMapper.queryShoppingAddress(orderJson.getLongValue("addressId"));
            userMapper.updateConsumerBalance("extract-" + orderJson.getIntValue("addressId"),Double.valueOf(String.format("%.2f", userMapper.queryCount("extract-" + addressId.getPointId()).getBalance() + totalPay * 0.08 )));
            jsonObject.put("status","success");
        }
        return jsonObject;
    }

    @Override
    public JSONObject addShoppingAddress(String name, long pointId, long number, String openId) {
//        System.out.println(body);
//        JSONObject requestJson = JSONObject.parseObject(body);
//        ShoppingAddress address = (ShoppingAddress) requestJson.get("address");
        orderMapper.insertNewShoppingAddress(name, pointId, String.valueOf(number), openId);
        JSONObject ansJson = new JSONObject();
        ansJson.put("status","success");
        return ansJson;
    }

    @Override
    public JSONObject putGoods(String openId, long goodsId, int number) {
        JSONObject jsonObject = new JSONObject();
        if (number < 0){
            jsonObject.put("status","failed");
            jsonObject.put("errmsg","number < 0");
        }else {
            if(number == 0){
                orderMapper.deleteShoppingCartGoods(openId, goodsId);
            }else {
                CartGoods goodsData = orderMapper.queryShoppingCartGoods(openId, goodsId);
                if (goodsData != null){
                    orderMapper.updateShoppingCart(openId, goodsId, number);
                }else {
                    orderMapper.insertToShoppingCart(openId, goodsId, number);
                }
            }
            jsonObject.put("status","success");
        }
        return jsonObject;
    }

    @Override
    public JSONObject receiveGoods(String openId, long orderId) {
        JSONObject jsonObject = new JSONObject();
        orderMapper.updateOrder(orderId,"status","已完成");
        jsonObject.put("status","success");
        return jsonObject;
    }

    @Override
    public JSONObject getPersonOrders(String openId,String status) {
        JSONObject jsonObject = new JSONObject();
        Order[] orders = orderMapper.queryOrdersByConsumer(openId,status);
        jsonObject.put("orders",orders);
        return jsonObject;
    }

    @Override
    public JSONObject getPersonShoppingCart(String openId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodsList",orderMapper.queryShoppingCart(openId));
        return jsonObject;
    }

    @Override
    public JSONObject getShoppingAddress(String openId) {
        JSONObject ansJson = new JSONObject();
        ansJson.put("addresses", orderMapper.queryAllShoppingAddress(openId));
        return ansJson;
    }

    @Override
    public JSONObject getTeamOrders(String openId) {
        return null;
    }

    @Override
    public JSONObject deleteShoppingCart(String openId, long goodsId) {
        JSONObject jsonObject = new JSONObject();
        orderMapper.deleteShoppingCartGoods(openId,goodsId);
        jsonObject.put("status","success");
        return jsonObject;
    }
}
