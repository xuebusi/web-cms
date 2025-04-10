package com.xuebusi.cms.api.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("image_grid")
public class ImageGrid {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer blockId;
    private String title;
    private String image;
    private String url;
    private String description;
    private Integer sort;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}