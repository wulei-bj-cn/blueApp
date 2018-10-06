package com.blueHouse.mapper;

import com.blueHouse.pojo.Article;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface ArticleMapper {

    Article findArticleById(String id);
    List<Article> findAllArticles();
    void insertArticle(Article article);
    void deleteArticle(Article article);

}
