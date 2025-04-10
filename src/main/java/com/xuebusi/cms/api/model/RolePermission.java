package com.xuebusi.cms.api.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("role_permission")
public class RolePermission {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}