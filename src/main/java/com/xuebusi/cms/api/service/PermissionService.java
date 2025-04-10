package com.xuebusi.cms.api.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuebusi.cms.api.mapper.PermissionMapper;
import com.xuebusi.cms.api.model.Permission;
import org.springframework.stereotype.Service;

@Service
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> {
    public boolean checkPermissionCodeExists(String code, Integer excludeId) {
        return lambdaQuery()
                .eq(Permission::getCode, code)
                .ne(excludeId != null, Permission::getId, excludeId)
                .exists();
    }
}
