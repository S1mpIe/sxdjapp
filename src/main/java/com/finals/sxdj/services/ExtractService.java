package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.ExtractPoint;

public interface ExtractService {
    JSONObject insertNewAuditedPoint(ExtractPoint point);
    JSONObject getAllAuditedPoint();
    JSONObject updatePointStatus(int pointId,String status);
}
