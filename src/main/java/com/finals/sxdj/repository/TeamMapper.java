package com.finals.sxdj.repository;

import com.finals.sxdj.model.Team;
import com.finals.sxdj.model.TeamCarts;
import com.finals.sxdj.model.sqlmodel.TeamCart;
import com.finals.sxdj.model.sqlmodel.Teammate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface TeamMapper {
    void insertNewTeam(long id,String name);
    void insertNewTeammates(long teamId,String openId,String status,String nickName);
    void insertCart(String openId, long teamId, Date time,int sourceId,int goodsId,int number);
    Team[] queryMateTeam(String mateId);
    Teammate queryMate(String openId,long teamId);
    Teammate queryMateById(long id);
    Teammate[] queryTeamMembers(long teamId);
    Team queryTeamById(long teamId);
    TeamCarts[] queryCart(long teamId);
    TeamCart[] queryMateCart(long teamId,String openId);
    TeamCart[] queryMateCartById(long teamId,long sourceId);
    TeamCart queryCartById(long cartId);
    TeamCart queryMateSingleCart(String openId,long teamId,int goodsId);
    void updateCart(String openId,long teamId,int goodsId,int number);
    void bookCart(long cartId);
    void updateTeammate(long teamId,int mateId,String key,String value);
    int deleteCart(long cartId);
    int quitTeam(long teamId,int mateId);
    void deleteTeammate(long mateId);
}
