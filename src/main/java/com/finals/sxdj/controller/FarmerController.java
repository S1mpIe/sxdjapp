package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.services.FarmerService;
import com.finals.sxdj.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FarmerController {
    @Autowired
    private FarmerService farmerService;
    @PutMapping("/farmer")
    @ResponseBody
    public String applyFamer(HttpServletRequest request,@RequestParam("name") String name, @RequestParam("introduce")String introduce){
        JSONObject accessToken = JwtUtil.getPayLoad(request.getHeader("accessToken"));
        return farmerService.applyFarmer(accessToken.getString("openId"),name,introduce).toJSONString();
    }

    @GetMapping("/farmer/goods")
    @ResponseBody
    public String getAllGoods(int farmerId){
        return farmerService.queryAllSold(farmerId).toJSONString();
    }

    @PutMapping("/farmer/goods")
    @ResponseBody
    public String insertNewGoods(int farmerId,GoodsData[] goods){
        return farmerService.applyGoods(goods,farmerId).toJSONString();
    }
}
