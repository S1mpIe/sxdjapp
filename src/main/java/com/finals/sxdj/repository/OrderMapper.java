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
    void insertNewOrder(long orderId, String consumerId, Date putTime, double pay, long addressId);
    void insertToShoppingCart(String openId,long goodsId,int number);
    void insertNewOrderData(long orderId,long goodsId,int number,double price,double totalPrice);
    void insertNewShoppingAddress(String name,long pointId,String number, String openId);
    void updateOrder(long id,String cate,String value);
    void updateShoppingCart(String openId,long goodsId,int number);
    void updateOrderData(String cate,String value);
    Order queryOrderById(String orderId);
    Order[] queryOrdersByConsumer(String consumerId, String status);
    Order[] queryOrdersByPoint(long pointId);
    Order queryLastOrder();
    OrderData[] queryById(long orderId);
    GoodsData[] queryShoppingCart(String openId);
    CartGoods queryShoppingCartGoods(String openId, long goodsId);
    ShoppingAddress[] queryAllShoppingAddress(String consumerId);
    void deleteShoppingCartGoods(String openId, long goodsId);

    Order[] queryGoodsOrders(long goodsId);

    ShoppingAddress queryShoppingAddress(long addressId);
}
