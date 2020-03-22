package com.finals.sxdj.repository;

import com.finals.sxdj.model.sqlmodel.ExtractPoint;
import org.springframework.stereotype.Component;

@Component
public interface ExtractMapper {
    int insertNewPoint(ExtractPoint point);
    int updatePointStatus(int pointId,String status);
    ExtractPoint[] queryAllPoint(String status);
    ExtractPoint[] queryAllActivePoint(double latitude, double longitude, double disparity);
    int updatePoint(ExtractPoint point);
}
