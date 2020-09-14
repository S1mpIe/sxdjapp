package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.services.GoodsService;
import com.finals.sxdj.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods")
    @ResponseBody
    public JSONObject getSingleGoods(HttpServletRequest request, @RequestParam("goodsId")long goodsId){
        return goodsService.getGoodsData(goodsId, JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }
    @GetMapping("/goods/comment")
    @ResponseBody
    public JSONObject getGoodsComment(@RequestParam("goodsId")long goodsId) {
        return goodsService.getGoodsComment(goodsId);
    }
    @PutMapping("/goods/comment")
    @ResponseBody
    public JSONObject putGoodsComment(@RequestBody String body){
        return goodsService.putGoodsComment(body);
    }
    @GetMapping("/goods/orders")
    @ResponseBody
    public JSONObject getGoodsOrders(@RequestParam("goodsId")long goodsId){
        return goodsService.getGoodsOrders(goodsId);
    }
}
