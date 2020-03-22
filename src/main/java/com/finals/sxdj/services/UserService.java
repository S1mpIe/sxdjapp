package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.User;

public interface UserService {
    JSONObject updateUser(String cate,String value, String openId);
    JSONObject getPersonData(String openId);
    JSONObject getTeammates(String openId);
}
