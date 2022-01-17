package com.example.epidemicprevention.module.hospital.entity;

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
 * Hospital实体类
 *
 * @author zzy
 * @date 2022-01-17
 */
@Data
@TableName("hospital")
public class Hospital {

public interface insert {
}

public interface update {
}

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "医院名称")
    private String name;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区(县)")
    private String area;


}
