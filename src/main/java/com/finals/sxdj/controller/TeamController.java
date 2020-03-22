package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.services.TeamService;
import com.finals.sxdj.services.UserService;
import com.finals.sxdj.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/team")
    @ResponseBody
    public JSONObject getTeam(HttpServletRequest request){
        String userId = JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId");
        return teamService.getTeam(userId);
    }

    @PutMapping("/team")
    @ResponseBody
    public JSONObject applyNewTeam(@RequestParam("name")String name,HttpServletRequest request){
        return  teamService.applyNewTeam(name,JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }

    @PutMapping("/teammate")
    @ResponseBody
    public JSONObject applyNewTeammate(@RequestParam("teamId")int id,HttpServletRequest request){
        return teamService.applyNewTeammate(id,JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }

    @GetMapping("/team/records")
    @ResponseBody
    public JSONObject getMemberRecords(@RequestParam("teamId")int id){
        return teamService.getTeamRecords(id);
    }

    @GetMapping("/team/discount")
    @ResponseBody
    public JSONObject getTeamDisCount(HttpServletRequest request){
        return teamService.getTeamDisCount(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }
}
