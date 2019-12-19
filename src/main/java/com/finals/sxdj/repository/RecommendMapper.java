package com.finals.sxdj.repository;

import com.finals.sxdj.model.sqlmodel.Recommend;
import org.springframework.stereotype.Component;

@Component
public interface RecommendMapper {
    /**
     * 获取所有展示的每日推荐
     * @return
     */
    Recommend[] queryAllOnRecommend();
}
