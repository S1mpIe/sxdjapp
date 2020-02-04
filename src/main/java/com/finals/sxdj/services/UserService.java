package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.User;

public interface UserService {
    JSONObject updateUser(User user, String openId);
}
