package com.xuebusi.cms.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuebusi.cms.api.common.ResponseResult;
import com.xuebusi.cms.api.model.User;
import com.xuebusi.cms.api.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseResult<User> getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return ResponseResult.success(user);
    }

    @GetMapping("/list")
    public ResponseResult<Page<User>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        userService.page(page);
        return ResponseResult.success(page);
    }

    @PostMapping
    public ResponseResult<Void> create(@RequestBody User user) {
        userService.save(user);
        return ResponseResult.success("创建成功");
    }

    @PutMapping
    public ResponseResult<Void> update(@RequestBody User user) {
        userService.updateById(user);
        return ResponseResult.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Void> delete(@PathVariable Integer id) {
        userService.removeById(id);
        return ResponseResult.success("删除成功");
    }

    @GetMapping("/search")
    public ResponseResult<Page<User>> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", keyword)
                   .or().like("email", keyword);
        
        Page<User> page = new Page<>(pageNum, pageSize);
        userService.page(page, queryWrapper);
        return ResponseResult.success(page);
    }
}