package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.CartGoods;
import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.model.sqlmodel.Comment;
import com.finals.sxdj.repository.GoodsMapper;
import com.finals.sxdj.repository.OrderMapper;
import com.finals.sxdj.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public JSONObject getGoodsData(int goodsId,String openId) {
        JSONObject jsonObject = new JSONObject();
        GoodsData goodsData = goodsMapper.queryGoodsById(goodsId);
        CartGoods cartGoods = orderMapper.queryShoppingCartGoods(openId, goodsId);
        goodsData.setSelectNumber(cartGoods == null ? 0 : cartGoods.getNumber());
        jsonObject.put("goods",goodsData);
        return jsonObject;
    }

    @Override
    public JSONObject getGoodsComment(long goodsId) {
        Comment[] comments = goodsMapper.queryComments(goodsId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("comments",comments);
        return jsonObject;
    }

    @Override
    public JSONObject putGoodsComment(String body) {
        JSONObject bodyJson = JSONObject.parseObject(body);
        String name = bodyJson.getString("name");
        String author = name.substring(0,1) + "**" + name.substring(name.length()-2);
        Map commentMap =  JSONObject.toJavaObject((JSONObject) bodyJson.get("commentMap"),Map.class);
        List stars = (List) bodyJson.get("stars");
        int index = 0;
        Set set = commentMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Map.Entry next = (Map.Entry) iterator.next();
            goodsMapper.insertComment(Long.valueOf((String) next.getKey()),(String)next.getValue(), (Integer) stars.get(index++),author,new Date(System.currentTimeMillis()));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        return jsonObject;
    }
}
