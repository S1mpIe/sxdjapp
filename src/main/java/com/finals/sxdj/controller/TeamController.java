package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.services.UserService;
import com.finals.sxdj.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private UserService userService;

    @GetMapping("/members")
    @ResponseBody
    public JSONObject getAllTeammates(HttpServletRequest request){
        JSONObject accessToken = JwtUtil.getPayLoad(request.getHeader("accessToken"));
        return userService.getTeammates(accessToken.getString("openId"));
    }
}
