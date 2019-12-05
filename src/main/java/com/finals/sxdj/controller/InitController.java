package com.finals.sxdj.controller;

import com.finals.sxdj.services.LoginService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author S1mpIe
 */
@Controller
public class InitController {
    @Autowired
    private LoginService loginService;
    @ResponseBody
    @RequestMapping("/login")
    public String getOpenId(@Param("code") String code){
        return loginService.login(code).toJSONString();
    }
}
