package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.ExtractPoint;
import com.finals.sxdj.services.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author S1mpIe
 */
@Controller
public class ExtractController {
    @Autowired
    private ExtractService extractService;
    @PutMapping("/extract")
    @ResponseBody
    public String applyPoint(@RequestBody ExtractPoint point){
        JSONObject jsonObject = extractService.insertNewAuditedPoint(point);
        return jsonObject.toJSONString();
    }
    @GetMapping("/extract/near")
    @ResponseBody
    public JSONObject getNearPoint(@RequestBody String body){
        JSONObject jsonObject = JSONObject.parseObject(body);
        double latitude = jsonObject.getDouble("latitude");
        double longitude = jsonObject.getDouble("longitude");
        return extractService.getNearbyPoint(latitude, longitude);
    }
}
