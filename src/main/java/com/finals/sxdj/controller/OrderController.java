package com.finals.sxdj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * @author S1mpIe
 */
@Controller
public class OrderController {

    @PutMapping(value = "/order")
    @ResponseBody
    public String putNewOrder(@RequestParam Map orderMap){
        System.out.println(orderMap.size());
        return "Hy";
    }
}
