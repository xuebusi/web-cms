package com.xuebusi.cms.api.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuebusi.cms.api.mapper.PermissionMapper;
import com.xuebusi.cms.api.mapper.RolePermissionMapper;
import com.xuebusi.cms.api.model.Permission;
import com.xuebusi.cms.api.model.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionService extends ServiceImpl<RolePermissionMapper, RolePermission> {
    @Autowired
    private PermissionMapper permissionMapper;

    public boolean assignPermission(Integer roleId, Integer permissionId) {
        if (lambdaQuery().eq(RolePermission::getRoleId, roleId)
                .eq(RolePermission::getPermissionId, permissionId)
                .exists()) {
            return true; // 已存在关系
        }

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        return save(rolePermission);
    }

    public List<Permission> getRolePermissions(Integer roleId) {
        return permissionMapper.selectByRoleId(roleId);
    }
}
