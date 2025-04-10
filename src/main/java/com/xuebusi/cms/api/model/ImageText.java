package com.xuebusi.cms.api.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("image_text")
public class ImageText {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer blockId;
    private String title;
    private String image;
    private String content;
    private String imagePosition;
    private Integer sort;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}