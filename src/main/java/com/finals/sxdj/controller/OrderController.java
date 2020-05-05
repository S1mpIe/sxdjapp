package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.repository.OrderMapper;
import com.finals.sxdj.services.OrderService;
import com.finals.sxdj.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author S1mpIe
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PutMapping(value = "/order")
    @ResponseBody
    public JSONObject putNewOrder(@RequestBody Map orderMap, HttpServletRequest request){
        return orderService.applyNewOrder(orderMap, JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }

    @GetMapping("/order")
    @ResponseBody
    public JSONObject getPersonOrder(HttpServletRequest request){
        return orderService.getPersonOrders(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }

    @GetMapping("/orderPay")
    @ResponseBody
    public JSONObject getOrderPay(@RequestBody Map orderMap){
        return orderService.getOrderPay(orderMap);
    }

    @PostMapping("/shoppingCart")
    @ResponseBody
    public JSONObject putNewGoods(HttpServletRequest request,@RequestParam("id")int id,@RequestParam("number")int number){
        return orderService.putGoods(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),id,number);
    }
    @GetMapping("/shoppingCart")
    @ResponseBody
    public JSONObject getShoppingCart(HttpServletRequest request){
        return orderService.getPersonShoppingCart(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }
    @DeleteMapping("/shoppingCart")
    @ResponseBody
    public JSONObject deleteShoppingCart(HttpServletRequest request,@RequestParam("id")int id){
        return orderService.deleteShoppingCart(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),id);
    }
}
