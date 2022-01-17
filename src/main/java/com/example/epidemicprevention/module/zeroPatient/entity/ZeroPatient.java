package com.example.epidemicprevention.module.zeroPatient.entity;

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
 * ZeroPatient实体类
 *
 * @author zzy
 * @date 2022-01-17
 */
@Data
@TableName("zero_patient")
public class ZeroPatient {

public interface insert {
}

public interface update {
}

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "病例id")
    private String patientId;

    @ApiModelProperty(value = "7天内路线(待定)")
    private String path;

    @ApiModelProperty(value = "交通工具信息(待定)")
    private String vehicle;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;


}
