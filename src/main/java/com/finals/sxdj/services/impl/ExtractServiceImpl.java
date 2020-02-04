package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.ExtractPoint;
import com.finals.sxdj.repository.ExtractMapper;
import com.finals.sxdj.services.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtractServiceImpl implements ExtractService {
    @Autowired
    public ExtractMapper extractMapper;

    @Override
    public JSONObject insertNewAuditedPoint(ExtractPoint point) {
        int number = extractMapper.insertNewPoint(point);
        JSONObject jsonObject = new JSONObject();
        if(number == 1){
            jsonObject.put("status","success");
        }else {
            jsonObject.put("status","failed");
        }
        return jsonObject;
    }

    @Override
    public JSONObject getAllAuditedPoint() {
        ExtractPoint[] extractPoints = extractMapper.queryAllPoint("已通过");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("extractPoints",extractPoints);
        return jsonObject;
    }

    @Override
    public JSONObject updatePointStatus(int pointId, String status) {
        extractMapper.updatePointStatus(pointId,status);
        return null;
    }
}
