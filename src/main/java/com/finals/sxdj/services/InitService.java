package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.User;

/**
 * @author S1mpIe
 */
public interface InitService {
    JSONObject login(String code);
    JSONObject register(String openId,User user);
}
