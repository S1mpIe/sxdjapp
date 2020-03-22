package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.Team;
import com.finals.sxdj.model.sqlmodel.Order;
import com.finals.sxdj.repository.OrderMapper;
import com.finals.sxdj.repository.TeamMapper;
import com.finals.sxdj.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public JSONObject applyNewTeam(String name, String captainId) {
        teamMapper.insertNewTeam(name);
        Team team = teamMapper.queryTeamByName(name);
        System.out.println(team);
        teamMapper.insertNewTeammates(team.getId(),captainId,"队长");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        return jsonObject;
    }

    @Override
    public JSONObject applyNewTeammate(int teamId, String mateId) {
        teamMapper.insertNewTeammates(teamId,mateId,"成员");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        return jsonObject;
    }

    @Override
    public JSONObject getTeamRecords(int teamId) {
        Order[] orders = orderMapper.queryOrdersByConsumer("team-" + teamId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orders",orders);
        return jsonObject;
    }

    @Override
    public JSONObject getTeam(String mateId) {
        Team team = teamMapper.queryMateTeam(mateId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("team",team);
        return jsonObject;
    }

    @Override
    public JSONObject getTeamDisCount(String mateId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("discount",0.98);
        return jsonObject;
    }
}
