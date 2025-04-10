package com.xuebusi.cms.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuebusi.cms.api.common.ResponseResult;
import com.xuebusi.cms.api.model.Article;
import com.xuebusi.cms.api.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/published")
    public ResponseResult<Page<Article>> getPublishedArticles(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        articleService.lambdaQuery()
                .eq(Article::getStatus, 1)
                .orderByDesc(Article::getPublishTime)
                .page(page);
        return ResponseResult.success(page);
    }

    @PostMapping("/{id}/publish")
    public ResponseResult<Void> publish(@PathVariable Integer id) {
        if (articleService.publishArticle(id)) {
            return ResponseResult.success("发布成功");
        }
        return ResponseResult.error("发布失败");
    }

    @PostMapping("/{id}/unpublish")
    public ResponseResult<Void> unpublish(@PathVariable Integer id) {
        if (articleService.unpublishArticle(id)) {
            return ResponseResult.success("取消发布成功");
        }
        return ResponseResult.error("取消发布失败");
    }
}