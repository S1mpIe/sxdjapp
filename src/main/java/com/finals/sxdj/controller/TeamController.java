package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.TeamOrders;
import com.finals.sxdj.services.TeamService;
import com.finals.sxdj.services.UserService;
import com.finals.sxdj.utils.JwtUtil;
import org.apache.ibatis.annotations.Delete;
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
    public JSONObject applyNewTeam(@RequestParam("name")String name,@RequestParam("nickName")String nickname,HttpServletRequest request){
        return  teamService.applyNewTeam(name,JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),nickname);
    }
    @PutMapping("/teammate")
    @ResponseBody
    public JSONObject applyNewTeammate(@RequestParam("teamCode")String teamCode,@RequestParam("nickName")String nickname,HttpServletRequest request){
        return teamService.applyNewTeammate(teamCode,JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),nickname);
    }
    @GetMapping("/teammate")
    @ResponseBody
    public JSONObject getPerson(HttpServletRequest request,@RequestParam("teamId")long teamId){
        return teamService.getPerson(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),teamId);
    }
    @DeleteMapping("/teammate")
    @ResponseBody
    public JSONObject deleteTeammate(HttpServletRequest request,@RequestParam("teamId")long teamId,@RequestParam("mateId")long mateId){
        return teamService.deleteTeammate(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),teamId,mateId);
    }
    @GetMapping("/team/orders")
    @ResponseBody
    public JSONObject getMemberRecords(@RequestParam("teamId")long id,HttpServletRequest request){
        return teamService.getTeamRecords(id);
    }
    @DeleteMapping("/team/orders")
    @ResponseBody
    public JSONObject refuseMember(@RequestParam("cartId")long cartId,HttpServletRequest request){
        return teamService.refuseOrder(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),cartId);
    }
    @PutMapping("/team/orders")
    @ResponseBody
    public JSONObject putOrders(@RequestBody TeamOrders orders,HttpServletRequest request){
        return teamService.putNewOrder(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),orders);
    }
    @PostMapping("/team/orders")
    @ResponseBody
    public JSONObject receiveOrders(@RequestParam("orderId")long id,HttpServletRequest request,@RequestParam("teamId")long teamId) {
        return teamService.receiveOrders(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),id,teamId);
    }
    @PutMapping("/team/cart")
    @ResponseBody
    public JSONObject addGoodsToTeamCart(HttpServletRequest request,@RequestParam("teamId")long teamId,@RequestParam("goodsId") int goodsId,@RequestParam("number")int number){
        return teamService.putNewGoods(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),teamId,goodsId,number);
    }
    @DeleteMapping("/team/cart")
    @ResponseBody
    public JSONObject refuseGoods(HttpServletRequest request,@RequestParam("cartId")long cartId){
        return teamService.deleteTeamCart(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),cartId);
    }
    @GetMapping("/team/cart")
    @ResponseBody
    public JSONObject getPersonCart(HttpServletRequest request,@RequestParam("teamId")long teamId){
        return teamService.getPersonCart(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),teamId);
    }
    @GetMapping("/team/allCart")
    @ResponseBody
    public JSONObject getAllCart(HttpServletRequest request,@RequestParam("teamId")long teamId){
        return teamService.getTeamAllCart(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),teamId);
    }
    @GetMapping("/team/code")
    @ResponseBody
    public JSONObject getTeamCode(@RequestParam("teamId")long id,HttpServletRequest request){
        return teamService.getTeamCode(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),id);
    }
    @GetMapping("/team/discount")
    @ResponseBody
    public JSONObject getTeamDisCount(HttpServletRequest request){
        return teamService.getTeamDisCount(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }
}
