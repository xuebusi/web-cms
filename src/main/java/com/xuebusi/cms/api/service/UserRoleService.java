package com.xuebusi.cms.api.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuebusi.cms.api.mapper.RoleMapper;
import com.xuebusi.cms.api.mapper.UserRoleMapper;
import com.xuebusi.cms.api.model.Role;
import com.xuebusi.cms.api.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {
    @Autowired
    private RoleMapper roleMapper;

    public boolean assignRole(Integer userId, Integer roleId) {
        if (lambdaQuery().eq(UserRole::getUserId, userId)
                .eq(UserRole::getRoleId, roleId)
                .exists()) {
            return true; // 已存在关系
        }

        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        return save(userRole);
    }

    public List<Role> getUserRoles(Integer userId) {
        //return roleMapper.selectByUserId(userId);
        return new ArrayList<>();
    }
}
