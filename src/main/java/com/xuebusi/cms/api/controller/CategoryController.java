package com.xuebusi.cms.api.controller;

import com.xuebusi.cms.api.common.ResponseResult;
import com.xuebusi.cms.api.model.Category;
import com.xuebusi.cms.api.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/tree")
    public ResponseResult<List<Category>> getCategoryTree(@RequestParam Integer userId) {
        List<Category> categoryTree = categoryService.getCategoryTree(userId);
        return ResponseResult.success(categoryTree);
    }

    @PostMapping
    public ResponseResult<Void> create(@RequestBody Category category) {
        categoryService.save(category);
        return ResponseResult.success("创建成功");
    }

    @PutMapping("/sort")
    public ResponseResult<Void> updateSort(@RequestBody List<Category> categories) {
        categories.forEach(categoryService::updateById);
        return ResponseResult.success("排序更新成功");
    }
}