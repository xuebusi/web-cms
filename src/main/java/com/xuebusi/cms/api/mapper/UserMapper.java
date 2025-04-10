package com.xuebusi.cms.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuebusi.cms.api.model.User;

public interface UserMapper extends BaseMapper<User> {
    User selectByUsername(String username);
}