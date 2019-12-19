package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.User;
import com.finals.sxdj.repository.UserMapper;
import com.finals.sxdj.services.InitService;
import com.finals.sxdj.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author S1mpIe
 */
@Service
public class InitServiceImpl implements InitService {
    @Value("${appId}")
    private String appId;
    @Value("${secret}")
    private String secret;
    @Value("${url.login}")
    private String loginUrl;
    @Autowired
    private UserMapper userMapper;
    @Override
    public JSONObject login(String code) {
        HashMap<String,String> heads = new HashMap<>();
        HashMap<String,String> attributes = new HashMap<>();
        heads.put("Content-Type", "application/json;charset=utf8");
        attributes.put("js_code",code);
        attributes.put("appid",appId);
        attributes.put("secret",secret);
        attributes.put("grant_type","authorization_code");
        JSONObject httpJsonObject = HttpUtil.sendGetRequest(heads, attributes, loginUrl);
        String openId = (String) httpJsonObject.get("openId");
        JSONObject jsonObject = new JSONObject();
        if(openId == null){
            jsonObject.put("errId",0);
            jsonObject.put("errMsg","invalid code");
        }else {
            User user = userMapper.queryUser(openId);
            if(user == null){
                jsonObject.put("errId",1);
                jsonObject.put("errMsg","user not registered");
            }else {
                jsonObject.put("user",user);
                jsonObject.put("accessToken","is null now");
            }
        }
        return jsonObject;
    }

    @Override
    public JSONObject register(User user) {
        int rows = userMapper.insertNewUser(user);
        JSONObject jsonObject = new JSONObject();
        if (rows == 1) {
            jsonObject.put("isLogin",true);
        } else {
            jsonObject.put("isLogin",false);
            jsonObject.put("errMsg","information invalid");
        }
        return jsonObject;
    }
}
