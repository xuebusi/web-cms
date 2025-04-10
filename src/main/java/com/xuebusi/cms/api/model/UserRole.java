package com.xuebusi.cms.api.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_role")
public class UserRole {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer roleId;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}