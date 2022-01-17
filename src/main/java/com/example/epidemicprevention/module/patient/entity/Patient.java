package com.example.epidemicprevention.module.patient.entity;

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
 * Patient实体类
 *
 * @author zzy
 * @date 2022-01-17
 */
@Data
@TableName("patient")
public class Patient {

    public interface insert {
    }

    public interface update {
    }

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "病例姓名")
    private String name;

    @ApiModelProperty(value = "疫情种类")
    private String epidemicId;

    @ApiModelProperty(value = "发现时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp discoverTime;

    @ApiModelProperty(value = "治愈时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp cureTime;

    @ApiModelProperty(value = "预计感染时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp predictBeginTime;

    @ApiModelProperty(value = "是否死亡,默认0否")
    private Integer isDead;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区(县)")
    private String area;

    @ApiModelProperty(value = "1男0女")
    private Integer gender;

    @ApiModelProperty(value = "年龄")
    private Integer age;


}
