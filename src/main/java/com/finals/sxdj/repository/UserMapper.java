package com.finals.sxdj.repository;

import com.finals.sxdj.model.Account;
import com.finals.sxdj.model.sqlmodel.AccountDetail;
import com.finals.sxdj.model.sqlmodel.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface UserMapper {
    User queryUser(String openId);
    User[] queryTeammates(String openId);
    Account queryCount(String consumerId);
    AccountDetail[] queryAccountDetail(String consumerId);
    int insertNewUser(String openId);
    int insertNewAccount(String consumerId,int number);
    int insertNewAccountDetail(String consumerId, Date time, String cause, Long source, double change);
    int updateUser(String cate,String value,String openId);
    int updateConsumerBalance(String consumerId, double balance);
}
