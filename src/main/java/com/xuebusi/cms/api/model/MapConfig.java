package com.xuebusi.cms.api.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("map_config")
public class MapConfig {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer blockId;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer zoom;
    private String markerTitle;
    private String apiKey;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}