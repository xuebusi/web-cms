package com.xuebusi.cms.api.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("form_data")
public class FormData {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer formId;
    private String data;
    private String ipAddress;
    private String userAgent;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}