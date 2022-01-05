package com.example.epidemicprevention.velocity;

import lombok.Data;

/**
 * 数据库字段信息
 */
@Data
public class DBFiled {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 是否为空
     */
    private String isNullable;

    /**
     * 字段类型
     */
    private String dataType;

    /**
     * 索引
     */
    private String columnKey;

    /**
     * 备注
     */
    private String columnComment;

    /**
     * 默认值
     */
    private String columnDefault;

    /**
     * 驼峰属性名
     */
    private String humpName;

}
