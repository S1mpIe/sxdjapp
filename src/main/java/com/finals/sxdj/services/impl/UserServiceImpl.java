package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.Account;
import com.finals.sxdj.model.sqlmodel.AccountDetail;
import com.finals.sxdj.model.sqlmodel.User;
import com.finals.sxdj.repository.UserMapper;
import com.finals.sxdj.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public JSONObject getAccount(String consumerId) {
        Account account = userMapper.queryCount(consumerId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account",account.getBalance());
        jsonObject.put("details",getAccountDetail(consumerId).get("details"));
        return jsonObject;
    }

    @Override
    public JSONObject addNewAccount(String openId) {
        userMapper.insertNewAccount(openId,10000);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        return jsonObject;
    }

    @Override
    public JSONObject getTeammates(String openId) {
        User[] users = userMapper.queryTeammates(openId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teammates",users);
        return jsonObject;
    }

    @Override
    public JSONObject getAccountDetail(String consumerId) {
        AccountDetail[] accountDetails = userMapper.queryAccountDetail(consumerId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("details",accountDetails);
        return jsonObject;
    }

    @Override
    public JSONObject updateAccount(String openId, double number, String cate, long id) {
        String userId = openId;
        if(!"person".equals(cate)){
            userId = cate + "-" + id;
        }
        Double overBalance = Double.valueOf(String.format("%.2f", userMapper.queryCount(userId).getBalance() + number ));
        userMapper.updateConsumerBalance(userId,overBalance);
        userMapper.insertNewAccountDetail(userId,new Date(System.currentTimeMillis()),"充值",null,number);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("balance",overBalance);
        jsonObject.put("details",userMapper.queryAccountDetail(userId));
        return jsonObject;
    }
}
