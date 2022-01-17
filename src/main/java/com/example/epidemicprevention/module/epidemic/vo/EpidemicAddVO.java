package com.example.epidemicprevention.module.epidemic.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author ZZY
 * @Date 2022/1/17
 */
@Data
public class EpidemicAddVO {

    @ApiModelProperty(value = "疫情种类名称")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "严重程度,越大越严重")
    @NotNull
    private Integer severityGrade;

    @ApiModelProperty(value = "0已结束1进行中2有可能发生")
    @NotNull
    @Max(2)
    @Min(0)
    private Integer status;
}
