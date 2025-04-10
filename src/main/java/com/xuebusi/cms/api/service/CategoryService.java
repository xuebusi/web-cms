package com.xuebusi.cms.api.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuebusi.cms.api.mapper.CategoryMapper;
import com.xuebusi.cms.api.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService extends ServiceImpl<CategoryMapper, Category> {
    public List<Category> getCategoryTree(Integer userId) {
        List<Category> allCategories = lambdaQuery()
                .eq(Category::getUserId, userId)
                .orderByAsc(Category::getSort)
                .list();

        // 构建树形结构
//        return allCategories.stream()
//                .filter(c -> c.getParentId() == 0)
//                .peek(c -> c.setChildren(getChildren(c, allCategories)))
//                .collect(Collectors.toList());
        return new ArrayList<>();
    }

    private List<Category> getChildren(Category parent, List<Category> allCategories) {
//        return allCategories.stream()
//                .filter(c -> c.getParentId().equals(parent.getId()))
//                .peek(c -> c.setChildren(getChildren(c, allCategories)))
//                .collect(Collectors.toList());
        return new ArrayList<>();
    }
}
