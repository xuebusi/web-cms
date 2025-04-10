package com.xuebusi.cms.api.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("form")
public class Form {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer blockId;
    private String name;
    private String fields;
    private String submitText;
    private String successMessage;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}