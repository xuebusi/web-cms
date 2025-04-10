package com.xuebusi.cms.api.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("article")
public class Article {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer categoryId;
    private String title;
    private String subtitle;
    private String author;
    private String summary;
    private String content;
    private String coverImage;
    private Integer status;
    private Date publishTime;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}