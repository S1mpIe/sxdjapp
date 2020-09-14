package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.ExtractPoint;
import com.finals.sxdj.services.ExtractService;
import com.finals.sxdj.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author S1mpIe
 */
@Controller
public class ExtractController {
    @Autowired
    private ExtractService extractService;
    @PutMapping("/extract")
    @ResponseBody
    public String applyPoint(HttpServletRequest request,@RequestBody ExtractPoint point){
        JSONObject jsonObject = extractService.insertNewAuditedPoint(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),point);
        return jsonObject.toJSONString();
    }
    @GetMapping("/extract")
    @ResponseBody
    public JSONObject getExtract(HttpServletRequest request){
        return extractService.getPersonPoint(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }
    @PostMapping("/extract")
    @ResponseBody
    public JSONObject changeExtract(HttpServletRequest request,@RequestParam("status")String status){
        return extractService.updatePointStatus(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),status);
    }
    @PostMapping("/extract/orders")
    @ResponseBody
    public JSONObject receiveGoods(HttpServletRequest request,@RequestParam("orderId")long orderId){
        return extractService.receiveGoods(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),orderId);
    }
    @GetMapping("/extract/near")
    @ResponseBody
    public JSONObject getNearPoint(@RequestParam("latitude")double latitude,@RequestParam("longitude")double longitude){
        return extractService.getNearbyPoint(latitude, longitude);
    }
    @GetMapping("/extract/account")
    @ResponseBody
    public JSONObject getExtractAccount(@RequestParam("id")long id){
        return extractService.getAccount(id);
    }
}
