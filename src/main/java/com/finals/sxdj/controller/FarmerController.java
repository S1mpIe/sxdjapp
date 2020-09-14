package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.model.sqlmodel.Farmers;
import com.finals.sxdj.services.FarmerService;
import com.finals.sxdj.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FarmerController {
    @Autowired
    private FarmerService farmerService;
    @PutMapping("/farmer/self")
    @ResponseBody
    public JSONObject applyFarmer(HttpServletRequest request, @RequestBody Farmers farmers){
        JSONObject accessToken = JwtUtil.getPayLoad(request.getHeader("accessToken"));
        return farmerService.applyFarmer(accessToken.getString("openId"),farmers);
    }
    @GetMapping("/farmer/self")
    @ResponseBody
    public JSONObject getPersonFarmer(HttpServletRequest request){
        return farmerService.queryFarmer(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }
    @PostMapping("/farmer/self")
    @ResponseBody
    public JSONObject changePersonFarmer(HttpServletRequest request, @RequestBody Farmers farmers){
        return farmerService.changeFarmer(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),farmers);
    }
    @GetMapping("/farmer/goods")
    @ResponseBody
    public JSONObject getAllGoods(@RequestParam("farmerId") long farmerId){
        return farmerService.queryAllSold(farmerId);
    }
    @PutMapping("/farmer/self/goods")
    @ResponseBody
    public JSONObject insertNewGoods(@RequestBody String body){
        JSONObject jsonObject = JSONObject.parseObject(body);
        Long farmerId = jsonObject.getLong("farmerId");
        GoodsData goods = jsonObject.getObject("goods",GoodsData.class);
        return farmerService.applyGoods(goods,farmerId);
    }
    @PostMapping("/farmer/self/goods")
    @ResponseBody
    public JSONObject changeGoods(@RequestBody String body){
        JSONObject jsonObject = JSONObject.parseObject(body);
        Long farmerId = jsonObject.getLong("farmerId");
        GoodsData goods = jsonObject.getObject("goods",GoodsData.class);
        return farmerService.updateGoods(farmerId,goods);
    }
    @DeleteMapping("/farmer/self/goods")
    @ResponseBody
    public JSONObject deleteGoods(@RequestParam("cate")String cate,long goodsId){
        return farmerService.deleteGoods(goodsId, cate);
    }
    @GetMapping("/farmer")
    @ResponseBody
    public JSONObject getFarmerById(@RequestParam("farmerId")long id){
        return farmerService.queryFarmerById(id);
    }
    @PostMapping("/farmer/upload")
    @ResponseBody
    public JSONObject uploadImage(HttpServletRequest request,@RequestParam("files") MultipartFile files){
        return farmerService.uploadImage(files);
    }
    @DeleteMapping("/farmer/upload")
    @ResponseBody
    public JSONObject deleteImage(HttpServletRequest request,@RequestParam("id")long id){
        return farmerService.deleteImage(id);
    }
    @PutMapping("/resources")
    @ResponseBody
    public JSONObject putResources(@RequestBody String body){
        return farmerService.applyResources(body);
    }
    @PostMapping("/resources")
    @ResponseBody
    public JSONObject changeResources(@RequestBody String body){
        return farmerService.changeResources(body);
    }
    @GetMapping("/resources")
    @ResponseBody
    public JSONObject getResources(@RequestParam("cate")String cate,@RequestParam("id")long id){
        return farmerService.queryResources(cate, id);
    }
    @GetMapping("/farmer/orders")
    @ResponseBody
    public JSONObject getOrders(@RequestParam("farmerId")long farmerId){
        return farmerService.getFarmerOrders(farmerId);
    }
}
