package com.finals.sxdj.repository;

import com.finals.sxdj.model.sqlmodel.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    User queryUser(String openId);
    int insertNewUser(User user);
    int updateUser(User user,String openId);
}
