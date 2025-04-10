package com.xuebusi.cms.api.controller;

import com.xuebusi.cms.api.common.ResponseResult;
import com.xuebusi.cms.api.model.Permission;
import com.xuebusi.cms.api.service.PermissionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @GetMapping("/tree")
    public ResponseResult<List<Permission>> getPermissionTree() {
        List<Permission> allPermissions = permissionService.list();
        // 构建权限树逻辑
        return ResponseResult.success(allPermissions);
    }

    @PostMapping
    public ResponseResult<Void> create(@RequestBody Permission permission) {
        if (permissionService.checkPermissionCodeExists(permission.getCode(), null)) {
            return ResponseResult.error("权限代码已存在");
        }
        permissionService.save(permission);
        return ResponseResult.success("创建成功");
    }

    @PutMapping
    public ResponseResult<Void> update(@RequestBody Permission permission) {
        if (permissionService.checkPermissionCodeExists(permission.getCode(), permission.getId())) {
            return ResponseResult.error("权限代码已存在");
        }
        permissionService.updateById(permission);
        return ResponseResult.success("更新成功");
    }
}