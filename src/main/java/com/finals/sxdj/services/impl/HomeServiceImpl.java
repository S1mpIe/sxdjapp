package com.finals.sxdj.services.impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.Navigation;
import com.finals.sxdj.model.sqlmodel.Farmers;
import com.finals.sxdj.repository.FarmerMapper;
import com.finals.sxdj.repository.GoodsMapper;
import com.finals.sxdj.repository.RecommendMapper;
import com.finals.sxdj.services.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class HomeServiceImpl implements HomeService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RecommendMapper recommendMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private FarmerMapper farmerMapper;
    @Override
    public String getNavigation() {
        ListOperations listOperations = redisTemplate.opsForList();
        Long len = listOperations.size("navigation");
        if(len == 0){
            throw new RuntimeException("导航栏为空");
        }
        List navigations = listOperations.range("navigation", 0, len);
        ArrayList<Navigation> navigationArrayList = new ArrayList<>();
        for(int i = 0;i < len;i++){
            HashMap o = (HashMap) navigations.get(i);
            navigationArrayList.add(new Navigation((String) o.get("articleId"),(String)o.get("articleImageUrl")));
        }
        return JSONObject.toJSONString(navigationArrayList);
    }

    @Override
    public String getRecommend() {
        return JSONObject.toJSONString(recommendMapper.queryAllOnRecommend());
    }

    @Override
    public String getRecommendGoods(int recommendId) {
        return JSONObject.toJSONString(goodsMapper.queryGoodsByRecommend(recommendId));
    }

    @Override
    public String getFarmers() {
        Farmers[] farmers = farmerMapper.queryAllFarmers();
        int len = farmers.length;
        for(int i = 0;i < len;i++){
            farmers[i].setOpenId(null);
        }
        return JSONObject.toJSONString(farmers);
    }

    @Override
    public String getCategoryGoods(String category) {
        return JSONObject.toJSONString(goodsMapper.queryGoodsByCategory(category));
    }
}