package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public interface ArticleService {
    JSONObject addNewArticle(String title, String mdUrl, String author, Date birthTime);
    JSONObject getAllArticle();
    JSONObject getArticle(int articleId);
}
