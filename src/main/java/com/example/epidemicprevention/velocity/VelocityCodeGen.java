package com.example.epidemicprevention.velocity;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;

import com.example.epidemicprevention.util.DateUtils;
import com.example.epidemicprevention.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class VelocityCodeGen {

    @Autowired
    private DBMapper dbMapper;
    private static final String ROOT_PATH = "D:\\soft\\java\\项目\\epidemic-prevention/epidemic-prevention/src/main/";

    private static final String AUTHOR = "zzy";

    private static final String DB_NAME = "prevention";

    private static final String reference="com.example.epidemicprevention";

    private static final String PACKAGE = "com.example.epidemicprevention.module";

    private static final String PACKAGE_PATH = "com/example/epidemicprevention/module/";

    public void generateCodeModule(String type) throws IOException {
        String moduleName = "caseRecord";
        String tableName = "case_record";
        String ClassName = "CaseRecord";
        List<DBFiled> dbFiledList = dbMapper.getTable(DB_NAME, tableName);
        if (dbFiledList != null && dbFiledList.size() > 0) {
            for (DBFiled dbFiled : dbFiledList) {
                dbFiled.setHumpName(StringUtil.camelName(dbFiled.getColumnName()));
            }
        }
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        // 获取模板文件
        Template t = null;        // 设置变量
        VelocityContext ctx = new VelocityContext();
        ctx.put("dbFiledList", dbFiledList);
        ctx.put("tableName", tableName);
        ctx.put("reference",reference);
        ctx.put("package", PACKAGE + "." + moduleName);
        ctx.put("ClassName", ClassName);
        ctx.put("classname", moduleName);
        ctx.put("date", DateUtils.dateToString(new Date(), "yyyy-MM-dd"));
        FileWriter sw = null;
        if (type.equals("Xml")) {
            t = ve.getTemplate("velocity/mapper/xml/mapper.xml.vm", "utf-8");
            File file = new File(ROOT_PATH + "resources/mybatis/mapper/" + moduleName);
            if (!file.exists()) {
                file.mkdir();
            }
            file = new File(ROOT_PATH + "resources/mybatis/mapper/" + moduleName);
            file.mkdir();
            sw = new FileWriter(ROOT_PATH + "resources/mybatis/mapper/" + moduleName + "/" + ClassName + "Mapper.xml");
        } else {
            File file = new File(ROOT_PATH + "java/" + PACKAGE_PATH + moduleName);
            if (!file.exists()) {
                file.mkdir();
            }
            if (type.equals("Impl")) {
                t = ve.getTemplate("velocity/service/impl/serviceImpl.java.vm", "utf-8");
                file = new File(ROOT_PATH + "java/" + PACKAGE_PATH + moduleName + "/service/impl");
                file.mkdir();
                sw = new FileWriter(ROOT_PATH + "java/" + PACKAGE_PATH + moduleName + "/service/impl/" + ClassName + "ServiceImpl.java");
            } else if (type.equals("Entity")) {
                t = ve.getTemplate("velocity/" + type.toLowerCase() + "/" + type.toLowerCase() + ".java.vm", "utf-8");
                file = new File(ROOT_PATH + "java/" + PACKAGE_PATH + moduleName + "/" + type.toLowerCase());
                file.mkdir();
                sw = new FileWriter(ROOT_PATH + "java/" + PACKAGE_PATH + moduleName + "/" + type.toLowerCase() + "/" + ClassName + ".java");

            } else {
                t = ve.getTemplate("velocity/" + type.toLowerCase() + "/" + type.toLowerCase() + ".java.vm", "utf-8");
                file = new File(ROOT_PATH + "java/" + PACKAGE_PATH + moduleName + "/" + type.toLowerCase());
                file.mkdir();
                sw = new FileWriter(ROOT_PATH + "java/" + PACKAGE_PATH + moduleName + "/" + type.toLowerCase() + "/" + ClassName + type + ".java");
            }
        }
        t.merge(ctx, sw);
        sw.flush();
        sw.close();
    }

    public void generateCode() throws IOException {
        generateCodeModule("Entity");
        generateCodeModule("Controller");
        generateCodeModule("Service");
        generateCodeModule("Mapper");
        generateCodeModule("Impl");
        generateCodeModule("Xml");
    }

}
