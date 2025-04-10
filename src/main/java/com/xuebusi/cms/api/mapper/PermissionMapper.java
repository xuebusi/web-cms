package com.xuebusi.cms.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuebusi.cms.api.model.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> selectByRoleId(Integer roleId);
}