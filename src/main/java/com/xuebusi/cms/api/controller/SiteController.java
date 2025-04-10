package com.xuebusi.cms.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuebusi.cms.api.common.ResponseResult;
import com.xuebusi.cms.api.model.Site;
import com.xuebusi.cms.api.service.SiteService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/site")
public class SiteController {

    @Resource
    private SiteService siteService;

    @GetMapping("/user/{userId}")
    public ResponseResult<Site> getByUserId(@PathVariable Integer userId) {
        Site site = siteService.getOne(new QueryWrapper<Site>().eq("user_id", userId));
        return ResponseResult.success(site);
    }

    @PostMapping
    public ResponseResult<Void> create(@RequestBody Site site) {
        siteService.save(site);
        return ResponseResult.success("创建成功");
    }

    @PutMapping
    public ResponseResult<Void> update(@RequestBody Site site) {
        siteService.updateById(site);
        return ResponseResult.success("更新成功");
    }

    @GetMapping("/{id}")
    public ResponseResult<Site> getById(@PathVariable Integer id) {
        Site site = siteService.getById(id);
        return ResponseResult.success(site);
    }
}