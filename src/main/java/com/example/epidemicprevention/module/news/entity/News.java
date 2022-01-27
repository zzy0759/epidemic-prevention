package com.example.epidemicprevention.module.news.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.epidemicprevention.module.epidemic.entity.Epidemic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * News实体类
 *
 * @author zzy
 * @date 2022-01-26
 */
@Data
@TableName("news")
public class News {

public interface insert {
}

public interface update {
}

    @ApiModelProperty(value = "主键id，递增")
    private Integer id;

    @ApiModelProperty(value = "title")
    private String title;

    @ApiModelProperty(value = "create_time")
    private String createTime;

    @ApiModelProperty(value = "media")
    private String media;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "second_title")
    private String secondTitle;

    @ApiModelProperty(value = "content")
    private String content;

    @ApiModelProperty(value = "是否可信1可信,0不可信")
    private Integer credible;

    @ApiModelProperty(value = "疫情id")
    private String epidemicId;

    @TableField(exist = false)
    private Epidemic epidemic;

    @ApiModelProperty(value = "关键段落")
    private String keywords;
}
