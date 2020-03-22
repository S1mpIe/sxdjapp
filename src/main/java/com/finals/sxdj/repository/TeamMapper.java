package com.finals.sxdj.repository;

import com.finals.sxdj.model.Team;
import org.springframework.stereotype.Component;

@Component
public interface TeamMapper {
    void insertNewTeam(String name);
    void insertNewTeammates(int teamId,String mateId,String status);
    Team queryMateTeam(String mateId);
    Team queryTeamById(int teamId);
    Team queryTeamByName(String name);
}
