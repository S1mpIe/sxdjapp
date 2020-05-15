package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.ShoppingAddress;
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
    public JSONObject putNewOrder(@RequestBody String body, HttpServletRequest request){
        return orderService.applyNewOrder(body, JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }

    @PostMapping("/order")
    @ResponseBody
    public JSONObject receiveGoods(@RequestParam("orderId")int orderId,HttpServletRequest request){
        return orderService.receiveGoods(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),orderId);
    }
    @GetMapping("/order")
    @ResponseBody
    public JSONObject getPersonOrder(HttpServletRequest request,@RequestParam("status")String status){
        return orderService.getPersonOrders(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),status);
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

    @GetMapping("/shoppingAddress")
    @ResponseBody
    public JSONObject getAllShoppingAddress(HttpServletRequest request){
        return orderService.getShoppingAddress(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }

    @PutMapping("/shoppingAddress")
    @ResponseBody
    public JSONObject addNewShoppingAddress(HttpServletRequest request, @RequestParam("pointId")int pointId,@RequestParam("name")String name,@RequestParam("phoneNumber")long number){
        return orderService.addShoppingAddress(name,pointId,number,JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }
}
