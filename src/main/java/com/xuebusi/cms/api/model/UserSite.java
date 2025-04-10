package com.xuebusi.cms.api.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_site")
public class UserSite {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String domain;
    private String subdomain;
    private Integer status;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}