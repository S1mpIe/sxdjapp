package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.User;
import com.finals.sxdj.services.InitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public JSONObject getLogin(@Param("code") String code){
        return initService.login(code);
    }
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.PUT)
    public JSONObject registerUser(User user){
        return initService.register(user);
    }
}
