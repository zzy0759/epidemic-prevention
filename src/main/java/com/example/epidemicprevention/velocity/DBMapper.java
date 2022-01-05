package com.example.epidemicprevention.velocity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DBMapper extends BaseMapper<DBFiled> {

    @Select("select * from information_schema.columns where table_schema = #{dbName} and table_name = #{tableName}")
    List<DBFiled> getTable(String dbName,String tableName);

}
