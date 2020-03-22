package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.User;
import com.finals.sxdj.repository.UserMapper;
import com.finals.sxdj.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public JSONObject updateUser(String cate, String value, String openId) {
        int i = userMapper.updateUser(cate, value, openId);
        JSONObject jsonObject = new JSONObject();
        if(i == 1){
            jsonObject.put("status","success");
        }else {
            jsonObject.put("status","failed");
        }
        return jsonObject;
    }

    @Override
    public JSONObject getPersonData(String openId) {
        System.out.println(openId);
        User user = userMapper.queryUser(openId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user",user);
        return jsonObject;
    }

    @Override
    public JSONObject getTeammates(String openId) {
        User[] users = userMapper.queryTeammates(openId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teammates",users);
        return jsonObject;
    }
}
