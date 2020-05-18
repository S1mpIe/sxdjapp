package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.sqlmodel.Article;
import com.finals.sxdj.repository.ArticleMapper;
import com.finals.sxdj.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public JSONObject addNewArticle(String title, String mdUrl, String author, Date birthTime) {
        articleMapper.insertNewArticle(title, mdUrl, author, birthTime);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        return jsonObject;
    }

    @Override
    public JSONObject getAllArticle() {
        Article[] articles = articleMapper.queryAllArticles();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("articles",articles);
        return jsonObject;
    }

    @Override
    public JSONObject getArticle(int articleId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("article",articleMapper.queryArticle(articleId));
        return jsonObject;
    }
}
