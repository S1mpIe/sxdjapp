package com.finals.sxdj.repository;

import com.finals.sxdj.model.CartGoods;
import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.model.sqlmodel.Order;
import com.finals.sxdj.model.sqlmodel.OrderData;
import com.finals.sxdj.model.sqlmodel.ShoppingAddress;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface OrderMapper {
    void insertNewOrder(String consumerId, Date putTime,double pay,int addressId);
    void insertToShoppingCart(String openId,int goodsId,int number);
    void insertNewOrderData(int orderId,int goodsId,int number,double price,double totalPrice);
    void insertNewShoppingAddress(String name,int pointId,String number, String openId);
    void updateOrder(int id,String cate,String value);
    void updateShoppingCart(String openId,int goodsId,int number);
    void updateOrderData(String cate,String value);
    Order queryOrderById(String orderId);
    Order[] queryOrdersByConsumer(String consumerId, String status);
    Order[] queryOrdersByPoint(int pointId);
    Order queryLastOrder();
    OrderData[] queryById(int orderId);
    GoodsData[] queryShoppingCart(String openId);
    CartGoods queryShoppingCartGoods(String openId, int goodsId);
    ShoppingAddress[] queryAllShoppingAddress(String openId);
    void deleteShoppingCartGoods(String openId, int goodsId);
}
