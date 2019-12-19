package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;

public interface HomeService {
    /**
     * 获取导航栏轮播数据
     * @return
     */
    String getNavigation();

    /**
     * 获取首页展示的每日推荐
     * @return
     */
    String getRecommend();

    /**
     * 获取每日推荐的商品列表
     * @param recommendId
     * @return
     */
    String getRecommendGoods(int recommendId);

    /**
     * 获取所有农户信息
     * @return
     */
    String getFarmers();

    String getCategoryGoods(String category);
}
