package com.xuebusi.cms.api.controller;

import com.xuebusi.cms.api.common.ResponseResult;
import com.xuebusi.cms.api.model.Role;
import com.xuebusi.cms.api.service.UserRoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userrole")
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    @PostMapping("/assign")
    public ResponseResult<Void> assignRole(@RequestParam Integer userId,
                                           @RequestParam Integer roleId) {
        if (userRoleService.assignRole(userId, roleId)) {
            return ResponseResult.success("分配成功");
        }
        return ResponseResult.error("分配失败");
    }

    @GetMapping("/user/{userId}")
    public ResponseResult<List<Role>> getUserRoles(@PathVariable Integer userId) {
        List<Role> roles = userRoleService.getUserRoles(userId);
        return ResponseResult.success(roles);
    }
}
