package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.model.sqlmodel.Order;
import com.finals.sxdj.model.sqlmodel.OrderData;
import com.finals.sxdj.repository.GoodsMapper;
import com.finals.sxdj.repository.OrderMapper;
import com.finals.sxdj.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;
//    @Transactional
    @Override
    public JSONObject applyNewOrder(Map orderMap, String openId) {
        Set set = orderMap.entrySet();
        Iterator iterator = set.iterator();
        JSONObject jsonObject = new JSONObject();
        Order lastOrder = orderMapper.queryLastOrder();
        int orderId = 0;
        if (lastOrder != null){
            orderId = lastOrder.getId() + 1;
        }
        double totalPay = 0;
        while (iterator.hasNext()){
            Map.Entry next = (Map.Entry) iterator.next();
            int goodsId = Integer.valueOf((String) next.getKey());
            int number = (int)next.getValue();
            GoodsData goodsData = goodsMapper.queryGoodsById(goodsId);
            if(goodsData.getNumber() >= number){
                goodsMapper.increaseSaleNumber(goodsData.getId(),number);
                double totalPrice = number * goodsData.getPrice();
                totalPay += totalPrice;
                orderMapper.insertNewOrderData(orderId,goodsData.getId(),number,goodsData.getPrice(),totalPrice);
            }else {
                jsonObject.put("status","failed");
                System.out.println(goodsData.toString());
                jsonObject.put("msg",goodsData.getId() + "库存不足");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return jsonObject;
            }
        }
        orderMapper.insertNewOrder(openId,new Date(System.currentTimeMillis()),totalPay);
        jsonObject.put("status","success");
        return jsonObject;
    }

    @Override
    public JSONObject getPersonOrders(String openId) {
        JSONObject jsonObject = new JSONObject();
        Order[] orders = orderMapper.queryOrdersByConsumer(openId);
        jsonObject.put("orders",orders);
        return jsonObject;
    }

    @Override
    public JSONObject getOrderPay(Map orderMap) {
        Set set = orderMap.entrySet();
        Iterator iterator = set.iterator();
        double totalPay = 0;
        while (iterator.hasNext()){
            Map.Entry next = (Map.Entry) iterator.next();
            totalPay += goodsMapper.queryGoodsById((Integer) next.getKey()).getPrice() * (int)next.getValue();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pay",totalPay);
        return jsonObject;
    }

    @Override
    public JSONObject getTeamOrders(String openId) {
        return null;
    }
}
