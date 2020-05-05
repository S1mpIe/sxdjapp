package com.finals.sxdj.repository;

import com.finals.sxdj.model.Account;
import com.finals.sxdj.model.sqlmodel.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    User queryUser(String openId);
    User[] queryTeammates(String openId);
    Account queryCount(String openId);
    int insertNewUser(User user);
    int insertNewAccount(String openId);
    int updateUser(String cate,String value,String openId);
}
