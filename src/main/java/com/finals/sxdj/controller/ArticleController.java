package com.finals.sxdj.controller;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    @ResponseBody
    public JSONObject getAllArticles(){
        return articleService.getAllArticle();
    }

    @GetMapping("/article")
    @ResponseBody
    public JSONObject getArticle(@RequestParam("articleId")int id){
        return articleService.getArticle(id);
    }
}
