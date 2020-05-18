package com.finals.sxdj.repository;

import com.finals.sxdj.model.sqlmodel.Article;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface ArticleMapper {
    void insertNewArticle(String title, String mdUrl, String author, Date birthTime);
    Article[] queryAllArticles();
    Article queryArticle(int articleId);
}
