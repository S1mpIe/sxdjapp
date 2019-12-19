package com.finals.sxdj.controller;
import com.finals.sxdj.services.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author S1mpIe
 */
@Slf4j
@Controller
@RequestMapping(value = "/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "/navigation",method = RequestMethod.GET)
    @ResponseBody
    public String getNavigation(){
        return homeService.getNavigation();
    }

    @RequestMapping(value = "/recommend",method = RequestMethod.GET)
    @ResponseBody
    public String getRecommend(){
            return homeService.getRecommend();
    }

    @RequestMapping(value = "/recommendGoods",method = RequestMethod.GET)
    @ResponseBody
    public String getRecommendGoods(@RequestParam(value = "recommendId")int id){
        return homeService.getRecommendGoods(id);
    }

    @RequestMapping(value = "/farmer",method = RequestMethod.GET)
    @ResponseBody
    public String getFarmers(){
        return homeService.getFarmers();
    }

    @RequestMapping(value = "/categoryGoods",method = RequestMethod.GET)
    @ResponseBody
    public String getGoodsByCate(@RequestParam(value = "category")String cate){
        String categoryGoods = homeService.getCategoryGoods(cate);
        log.info(categoryGoods + "-category-" + cate);
        return categoryGoods;
    }
}
