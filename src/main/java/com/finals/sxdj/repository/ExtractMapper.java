package com.finals.sxdj.repository;

import com.finals.sxdj.model.sqlmodel.ExtractPoint;
import org.springframework.stereotype.Component;

@Component
public interface ExtractMapper {
    int insertNewPoint(String openId,ExtractPoint point);
    int updatePointStatus(String openId,String status);
    ExtractPoint queryPersonPoint(String openId);
    ExtractPoint[] queryAllPoint(String status);
    ExtractPoint[] queryAllActivePoint(double latitude, double longitude, double disparity);
    int updatePoint(ExtractPoint point);
}
