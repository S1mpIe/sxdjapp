package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;

public interface GoodsService {
    JSONObject getGoodsData(int goodsId,String openId);
}
