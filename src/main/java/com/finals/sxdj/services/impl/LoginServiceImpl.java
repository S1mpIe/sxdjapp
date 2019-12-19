package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.services.LoginService;
import com.finals.sxdj.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author S1mpIe
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Value("${appId}")
    private String appId;
    @Value("${secret}")
    private String secret;
    @Value("${url.login}")
    private String loginUrl;
    @Autowired
    private HttpUtil httpUtil;
    @Override
    public JSONObject login(String code) {
        HashMap<String,String> heads = new HashMap<>();
        HashMap<String,String> attributes = new HashMap<>();
        heads.put("Content-Type", "application/json;charset=utf8");
        attributes.put("js_code",code);
        attributes.put("appid",appId);
        attributes.put("secret",secret);
        attributes.put("grant_type","authorization_code");
        System.out.println(httpUtil.getLastInterfaceToken());
        return HttpUtil.sendGetRequest(heads,attributes,loginUrl);
    }
}
