package com.xuebusi.cms.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuebusi.cms.api.common.ResponseResult;
import com.xuebusi.cms.api.model.Role;
import com.xuebusi.cms.api.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/{id}")
    public ResponseResult<Role> getById(@PathVariable Integer id) {
        Role role = roleService.getById(id);
        return ResponseResult.success(role);
    }

    @GetMapping("/list")
    public ResponseResult<Page<Role>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Role> page = new Page<>(pageNum, pageSize);
        roleService.page(page);
        return ResponseResult.success(page);
    }

    @PostMapping
    public ResponseResult<Void> create(@RequestBody Role role) {
        if (roleService.checkRoleNameExists(role.getName(), null)) {
            return ResponseResult.error("角色名称已存在");
        }
        roleService.save(role);
        return ResponseResult.success("创建成功");
    }

    @PutMapping
    public ResponseResult<Void> update(@RequestBody Role role) {
        if (roleService.checkRoleNameExists(role.getName(), role.getId())) {
            return ResponseResult.error("角色名称已存在");
        }
        roleService.updateById(role);
        return ResponseResult.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Void> delete(@PathVariable Integer id) {
        roleService.removeById(id);
        return ResponseResult.success("删除成功");
    }
}