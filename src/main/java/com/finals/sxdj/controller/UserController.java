package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.User;
import com.finals.sxdj.repository.UserMapper;
import com.finals.sxdj.services.UserService;
import com.finals.sxdj.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author S1mpIe
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @ResponseBody
    @PostMapping("/user")
    public String updatePerson(User user, HttpServletRequest request){
        JSONObject accessToken = JwtUtil.getPayLoad(request.getHeader("accessToken"));
        System.out.println(accessToken.toJSONString());
        return userService.updateUser(user,accessToken.getString("openId")).toJSONString();
    }
}
