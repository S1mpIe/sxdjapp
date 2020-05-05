package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.User;
import com.finals.sxdj.services.InitService;
import com.finals.sxdj.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author S1mpIe
 */
@Controller
@Slf4j
public class InitController {
    @Autowired
    private InitService initService;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.PUT)
    public JSONObject getLogin(@RequestBody String body){
        JSONObject jsonObject = JSONObject.parseObject(body);
        return initService.login(jsonObject.getString("code"));
    }
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.PUT)
    public JSONObject registerUser(HttpServletRequest request, @RequestBody User user){
        return initService.register(JwtUtil.getPayLoad(request.getHeader("accessToken")).getString("openId"),user);
    }
}
