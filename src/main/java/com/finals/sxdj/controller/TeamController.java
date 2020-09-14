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

import javax.annotation.Resource;
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
    @DeleteMapping("/team")
    @ResponseBody
    public JSONObject quitTeam(@RequestParam("teamId")long teamId,HttpServletRequest request){
        return teamService.quitTeam(teamId,JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"));
    }
    @PutMapping("/teammate")
    @ResponseBody
    public JSONObject applyNewTeammate(@RequestParam("teamCode")String teamCode,@RequestParam("nickName")String nickname,HttpServletRequest request){
        return teamService.applyNewTeammate(teamCode,JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),nickname);
    }
    @PostMapping("/teammate")
    @ResponseBody
    public JSONObject changeNickName(@RequestParam("teamId")long teamId,@RequestParam("mateId")long mateId,@RequestParam("nickName")String nickName){
        return teamService.changeNickName(teamId,mateId,nickName);
    }
    @PostMapping("/teammate/leader")
    @ResponseBody
    public JSONObject transferLeader(@RequestParam("leaderId")long leaderId,@RequestParam("mateId")long mateId,@RequestParam("teamId")long teamId){
        return teamService.transferLeader(leaderId,mateId,teamId);
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
    @GetMapping("/teammember")
    @ResponseBody
    public JSONObject getTeammember(HttpServletRequest request,@RequestParam("teamId")long teamId){
        return teamService.getTeammember(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),teamId);
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
    public JSONObject addGoodsToTeamCart(HttpServletRequest request,@RequestParam("teamId")long teamId,@RequestParam("goodsId") long goodsId,@RequestParam("number")int number) {
        return teamService.putNewGoods(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"), teamId, goodsId, number);
    }
    @PostMapping("/team/cart")
    @ResponseBody
    public JSONObject changeGoods(@RequestParam("teamId")long teamId,HttpServletRequest request,@RequestParam("cartId")long id,@RequestParam("number")int number){
        return teamService.changeGoods(teamId,JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),id,number);
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
    @GetMapping("/team/account")
    @ResponseBody
    public JSONObject getTeamAccount(@RequestParam("teamId")long teamId){
        return teamService.getTeamAccount(teamId);
    }
    @PostMapping("/team/account")
    @ResponseBody
    public JSONObject updateTeamAccount(@RequestParam("teamId")long teamId,@RequestParam("number")double number){
        return teamService.updateTeamAccount(teamId,number);
    }
}
