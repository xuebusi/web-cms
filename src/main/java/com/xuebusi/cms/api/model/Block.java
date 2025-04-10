package com.xuebusi.cms.api.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("block")
public class Block {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer pageId;
    private String type;
    private String title;
    private Integer sort;
    private String config;
    private Integer status;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}