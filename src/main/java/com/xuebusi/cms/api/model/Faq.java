package com.xuebusi.cms.api.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("faq")
public class Faq {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer blockId;
    private String question;
    private String answer;
    private Integer sort;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}