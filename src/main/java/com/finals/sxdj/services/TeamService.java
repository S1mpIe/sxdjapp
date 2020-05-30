package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.TeamOrders;

public interface TeamService {
    JSONObject applyNewTeam(String name,String captainId,String nickName);
    JSONObject applyNewTeammate(String teamCode,String mateId,String nickName);
    JSONObject deleteTeamCart(String openId,long cartId);
    JSONObject putNewOrder(String openId, TeamOrders orders);
    JSONObject receiveOrders(String openId,long orderId,long teamId);
    JSONObject putNewGoods(String openId,long teamId,int goodsId,int number);
    JSONObject getTeamRecords(long teamId);
    JSONObject getTeam(String mateId);
    JSONObject getTeamDisCount(String mateId);
    JSONObject getPerson(String openId,long teamId);
    JSONObject getPersonCart(String openId,long teamId);
    JSONObject refuseOrder(String openId,long cartId);
    JSONObject getTeamCode(String openId,long teamId);
    JSONObject getTeamAllCart(String openId,long teamId);
    JSONObject deleteTeammate(String openId, long teamId, long mateId);

    JSONObject getTeammember(String openId, long teamId);

    JSONObject updateTeamAccount(long teamId, double number);
    JSONObject getTeamAccount(long teamId);

    JSONObject quitTeam(long teamId, String openId);

    JSONObject changeNickName(long teamId, int mateId, String nickName);

    JSONObject transferLeader(int leaderId, int mateId, long teamId);

    JSONObject changeGoods(long teamId, String openId, long id, int number);
}
