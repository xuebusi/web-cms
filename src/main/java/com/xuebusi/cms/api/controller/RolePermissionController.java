package com.xuebusi.cms.api.controller;

import com.xuebusi.cms.api.common.ResponseResult;
import com.xuebusi.cms.api.model.Permission;
import com.xuebusi.cms.api.service.RolePermissionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rolepermission")
public class RolePermissionController {

    @Resource
    private RolePermissionService rolePermissionService;

    @PostMapping("/assign")
    public ResponseResult<Void> assignPermission(@RequestParam Integer roleId,
                                                 @RequestParam Integer permissionId) {
        if (rolePermissionService.assignPermission(roleId, permissionId)) {
            return ResponseResult.success("分配成功");
        }
        return ResponseResult.error("分配失败");
    }

    @GetMapping("/role/{roleId}")
    public ResponseResult<List<Permission>> getRolePermissions(@PathVariable Integer roleId) {
        List<Permission> permissions = rolePermissionService.getRolePermissions(roleId);
        return ResponseResult.success(permissions);
    }
}
