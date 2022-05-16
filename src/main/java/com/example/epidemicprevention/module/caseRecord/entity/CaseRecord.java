package com.example.epidemicprevention.module.caseRecord.entity;

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
 * CaseRecord实体类
 *
 * @author zzy
 * @date 2022-01-26
 */
@Data
@TableName("case_record")
public class CaseRecord {

public interface insert {
}

public interface update {
}

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "新增人数")
    private int newNumber;

    @ApiModelProperty(value = "死亡人数")
    private int dieNumber;

    @ApiModelProperty(value = "总人数")
    private int totalNumber;

    @ApiModelProperty(value = "治愈人数")
    private int cureNumber;

    @ApiModelProperty(value = "现存感染人数")
    private int nowNumber;

    @NotNull(message = "date不能为空",groups = {Integer.class})
    @ApiModelProperty(value = "日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp date;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "疫情ID")
    private String epidemicId;


        }
