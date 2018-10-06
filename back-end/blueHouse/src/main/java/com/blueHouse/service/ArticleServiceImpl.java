package com.blueHouse.service;

import com.blueHouse.mapper.ArticleMapper;
import com.blueHouse.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class ArticleServiceImpl implements ArticleService{
    @Resource
    @Autowired
    private ArticleMapper articleMapper;

    public Article findArticleById(String id) { return articleMapper.findArticleById(id); }

    public List<Article> findAllArticles() { return articleMapper.findAllArticles(); }

    public void insertArticle(Article article) { articleMapper.insertArticle(article); }

    public void deleteArticle(Article article) { articleMapper.deleteArticle(article); }

}
