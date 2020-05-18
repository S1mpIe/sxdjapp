package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.services.GoodsService;
import com.finals.sxdj.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods")
    @ResponseBody
    public JSONObject getSingleGoods(HttpServletRequest request, @RequestParam("goodsId")int goodsId){
        return goodsService.getGoodsData(goodsId, JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }
}
