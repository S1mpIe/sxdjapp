package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.ExtractPoint;
import com.finals.sxdj.services.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author S1mpIe
 */
@Controller
public class ExtractController {
    @Autowired
    private ExtractService extractService;
    @PutMapping("/extract")
    @ResponseBody
    public String applyPoint(ExtractPoint point){
        JSONObject jsonObject = extractService.insertNewAuditedPoint(point);
        return jsonObject.toJSONString();
    }
    @GetMapping("/extract/near")
    @ResponseBody
    public JSONObject getNearPoint(@RequestParam("latitude")double latitude,@RequestParam("longitude")double longitude){
        return extractService.getNearbyPoint(latitude, longitude);
    }
}
