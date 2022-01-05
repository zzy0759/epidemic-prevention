package com.example.epidemicprevention.util;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WrapperUtils {

    /**
     * 获取只有一个eq条件的QueryWrapper
     *
     * @param key：字段名
     * @param value：值
     * @return QueryWrapper<T>
     */
    public static <T> QueryWrapper<T> getQueryWrapper(String key, Object value) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(key, value);
        return queryWrapper;
    }

    public static <T>UpdateWrapper<T> getUpdateWrapper(String key,Object value){
        UpdateWrapper<T> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq(key,value);
        return updateWrapper;
    }

    public static <T> QueryWrapper<T> queryWrapper(T object) {
        QueryWrapper<T> queryWrapper=new QueryWrapper<>();
        if(object!=null){
            Class<?> clz = object.getClass();
            Field[] fields = clz.getDeclaredFields();
            for (Field field:fields){
                //判断该属性是否在数据表字段中
                TableField tableField=field.getAnnotation(TableField.class);
                if(tableField!=null){
                    if(!tableField.exist()){
                        continue;
                    }
                }
                if(field.getGenericType().toString().equals("class java.lang.String")){
                    Method m = null;
                    try {
                        m = (Method) object.getClass().getMethod(
                                "get" + getMethodName(field.getName()));
                        Object value=m.invoke(object);
                        if(value!=null&&!value.toString().equals("null")){
                            queryWrapper.like(StringUtil.underscoreName(field.getName()),value);
                        }
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }else {
                    Method m = null;
                    try {
                        m = (Method) object.getClass().getMethod(
                                "get" + getMethodName(field.getName()));
                        Object value=m.invoke(object);
                        if(value!=null&&!value.toString().equals("null")){
                            queryWrapper.eq(StringUtil.underscoreName(field.getName()),value);
                        }
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return queryWrapper;
    }


    /**
     * 通过VO类提取查询条件
     * @param object
     * @param <T>
     * @param <G>
     * @return
     */
    public static <T,G> QueryWrapper<T> queryWrapperByQueryVO(G object) {
        QueryWrapper<T> queryWrapper=new QueryWrapper<>();
        if(object!=null){
            Class<?> clz = object.getClass();
            Field[] fields = clz.getDeclaredFields();
            for (Field field:fields){
                //判断该属性是否在数据表字段中
                TableField tableField=field.getAnnotation(TableField.class);
                if(tableField!=null){
                    if(!tableField.exist()){
                        continue;
                    }
                }
                if(field.getGenericType().toString().equals("class java.lang.String")){
                    Method m = null;
                    try {
                        m = (Method) object.getClass().getMethod(
                                "get" + getMethodName(field.getName()));
                        Object value=m.invoke(object);
                        if(value!=null&&!value.toString().equals("null")){
                            queryWrapper.like(StringUtil.underscoreName(field.getName()),value);
                        }
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }else {
                    Method m = null;
                    try {
                        m = (Method) object.getClass().getMethod(
                                "get" + getMethodName(field.getName()));
                        Object value=m.invoke(object);
                        if(value!=null&&!value.toString().equals("null")){
                            queryWrapper.eq(StringUtil.underscoreName(field.getName()),value);
                        }
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return queryWrapper;
    }

    /**
     * 通过VO类提取查询条件,忽略时间条件
     * @param object
     * @param <T>
     * @param <G>
     * @return
     */
    public static <T,G> QueryWrapper<T> queryWrapperByQueryVONoTime(G object) {
        QueryWrapper<T> queryWrapper=new QueryWrapper<>();
        if(object!=null){
            Class<?> clz = object.getClass();
            Field[] fields = clz.getDeclaredFields();
            for (Field field:fields){
                //判断该属性是否在数据表字段中
                TableField tableField=field.getAnnotation(TableField.class);
                if(tableField!=null){
                    if(!tableField.exist()){
                        continue;
                    }
                }
                if(field.getGenericType().toString().equals("class java.lang.String")){
                    Method m = null;
                    try {
                        m = (Method) object.getClass().getMethod(
                                "get" + getMethodName(field.getName()));
                        Object value=m.invoke(object);
                        if(value!=null&&!value.toString().equals("null")){
                            queryWrapper.like(StringUtil.underscoreName(field.getName()),value);
                        }
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }if(field.getGenericType().toString().equals("class java.sql.Timestamp")||field.getGenericType().toString().equals("class java.util.Date")){
//                    Method m = null;
//                    try {
//                        m = (Method) object.getClass().getMethod(
//                                "get" + getMethodName(field.getName()));
//                        Object value=m.invoke(object);
//                        if(value!=null&&!value.toString().equals("null")){
//                            queryWrapper.like(StringUtil.underscoreName(field.getName()),value);
//                        }
//                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
                }else {
                    Method m = null;
                    try {
                        m = (Method) object.getClass().getMethod(
                                "get" + getMethodName(field.getName()));
                        Object value=m.invoke(object);
                        if(value!=null&&!value.toString().equals("null")){
                            queryWrapper.eq(StringUtil.underscoreName(field.getName()),value);
                        }
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return queryWrapper;
    }

    // 把一个字符串的第一个字母大写、效率是最高的、
    private static String getMethodName(String fieldName){
        byte[] items = fieldName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}
