package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.CartGoods;
import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.repository.GoodsMapper;
import com.finals.sxdj.repository.OrderMapper;
import com.finals.sxdj.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
