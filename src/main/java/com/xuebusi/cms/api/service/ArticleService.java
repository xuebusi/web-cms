package com.xuebusi.cms.api.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuebusi.cms.api.mapper.ArticleMapper;
import com.xuebusi.cms.api.model.Article;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ArticleService extends ServiceImpl<ArticleMapper, Article> {
    public boolean publishArticle(Integer id) {
        Article article = new Article();
        article.setId(id);
        article.setStatus(1);
        article.setPublishTime(new Date());
        return updateById(article);
    }

    public boolean unpublishArticle(Integer id) {
        Article article = new Article();
        article.setId(id);
        article.setStatus(0);
        return updateById(article);
    }
}
