package com.finals.sxdj.repository;

import com.finals.sxdj.model.sqlmodel.Order;
import com.finals.sxdj.model.sqlmodel.OrderData;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface OrderMapper {
    void insertNewOrder(String consumerId, Date putTime,double pay);
    void insertNewOrderData(int orderId,int goodsId,int number,double price,double totalPrice);
    void updateOrder(int id,String cate,String value);
    void updateOrderData(String cate,String value);
    Order queryOrderById(String orderId);
    Order[] queryOrdersByConsumer(String consumerId);
    Order queryLastOrder();
    OrderData[] queryById(int orderId);
}
