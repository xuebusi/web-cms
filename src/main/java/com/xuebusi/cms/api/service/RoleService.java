package com.xuebusi.cms.api.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuebusi.cms.api.mapper.RoleMapper;
import com.xuebusi.cms.api.model.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {
    // 可以添加角色特有的业务逻辑
    public boolean checkRoleNameExists(String name, Integer excludeId) {
        return lambdaQuery()
                .eq(Role::getName, name)
                .ne(excludeId != null, Role::getId, excludeId)
                .exists();
    }
}
