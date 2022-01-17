package com.example.epidemicprevention.module.resourceType.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * ResourceType实体类
 *
 * @author zzy
 * @date 2022-01-17
 */
@Data
@TableName("resource_type")
public class ResourceType {

public interface insert {
}

public interface update {
}

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "物资类型名称")
    private String name;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;

    @ApiModelProperty(value = "1删除0未删除")
    private Integer isDelete;


}
