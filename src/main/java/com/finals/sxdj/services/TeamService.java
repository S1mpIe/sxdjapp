package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;

public interface TeamService {
    JSONObject applyNewTeam(String name,String captainId);
    JSONObject applyNewTeammate(int teamId,String mateId);
    JSONObject getTeamRecords(int teamId);
    JSONObject getTeam(String mateId);
    JSONObject getTeamDisCount(String mateId);
}
