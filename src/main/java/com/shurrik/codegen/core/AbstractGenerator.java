package com.shurrik.codegen.core;

import com.shurrik.codegen.model.ClassObject;
import com.shurrik.codegen.util.FileHelper;
import com.shurrik.codegen.util.FreeMarkerHelper;
import com.shurrik.codegen.util.GlobalVariables;
import com.shurrik.codegen.util.ProjectConfig;
import com.shurrik.codegen.util.helper.ClassObjecHelper;
import com.shurrik.codegen.util.helper.JsonClassObjectHelper;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lip on 2015-12-24.
 */
public abstract class AbstractGenerator {

    public void run() throws Exception {
        List<ClassObject> classObjectList = getClassObjectList();
        generate(classObjectList);
    }

    public abstract List<ClassObject>  getClassObjectList() throws IOException, TemplateException, SQLException, Exception;

    public  void generate( List<ClassObject> classObjectList) throws IOException, TemplateException
    {
        ProjectConfig conf = ProjectConfig.getInstance();

        System.out.println(conf.getCorpName());
        System.out.println(conf.getProjectName());
        System.out.println(conf.getModuleName());
        System.out.println(conf.getAuthor());

        for(ClassObject co:classObjectList)
        {
            Map<String, Object> model = new HashMap();
            model.put("classObject", co);
            model.put("conf", conf);
            generateEntity(model);
            generateDao(model);
            generateDaoXML(model);
            generateService(model);
            generateImpl(model);
//			generateManager(model);
            generateAction(model);
            generateViewList(model);
            generateViewEdit(model);

            //ng
            generateNgRest(model);
            generateNgCtrl(model);
            generateNgHtml(model);
        }

        Map<String, Object> model = new HashMap();
        model.put("classObjects", classObjectList);
        generateCreateSql(model);
        generateResourceSql(model);
        generateNgConstant(model);
        generateNgRouter(model);
        generateNgNav(model);
        generateEntityJson(model);
        model.put("conf", conf);
        generateBat(model);
    }

    public  void generateEntity(Map<String, Object> model) throws IOException, TemplateException
    {
        ClassObject co = (ClassObject) model.get("classObject");
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.ENTITY_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_ENTITY_PATH;
        fileHelper.createFile(outputPath, co.getClassName(), content, "java");
    }

    public  void generateDao(Map<String, Object> model) throws IOException, TemplateException
    {
        ClassObject co = (ClassObject) model.get("classObject");
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.DAO_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_DAO_PATH;
        fileHelper.createFile(outputPath, "I"+co.getClassName()+"DAO", content, "java");
    }
    public  void generateDaoXML(Map<String, Object> model) throws IOException, TemplateException
    {
        ClassObject co = (ClassObject) model.get("classObject");
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.DAOXML_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_DAOXML_PATH;
        fileHelper.createFile(outputPath, "I"+co.getClassName()+"DAO", content, "xml");
    }
    public  void generateService(Map<String, Object> model) throws IOException, TemplateException
    {
        ClassObject co = (ClassObject) model.get("classObject");
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.SERVICEINTER_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_SERVICEINTER_PATH;
        fileHelper.createFile(outputPath, "I"+co.getClassName()+"Service", content, "java");
    }
    public  void generateImpl(Map<String, Object> model) throws IOException, TemplateException
    {
        ClassObject co = (ClassObject) model.get("classObject");
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.SERVICEIMPL_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_SERVICEIMPL_PATH;
        fileHelper.createFile(outputPath, co.getClassName()+"ServiceImpl", content, "java");
    }

    public  void generateManager(Map<String, Object> model) throws IOException, TemplateException
    {
        ClassObject co = (ClassObject) model.get("classObject");
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.MANAGER_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_MANAGER_PATH;
        fileHelper.createFile(outputPath, co.getClassName()+"Mng", content, "java");
    }

    public  void generateAction(Map<String, Object> model) throws IOException, TemplateException
    {
        ClassObject co = (ClassObject) model.get("classObject");
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.ACTION_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_ACTION_PATH;
        fileHelper.createFile(outputPath, co.getClassName()+"Action", content, "java");
    }


    public  void generateViewList(Map<String, Object> model) throws IOException, TemplateException
    {
        ClassObject co = (ClassObject) model.get("classObject");
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.VIEW_LIST_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_VIEW_PATH.toLowerCase();
        fileHelper.createFile(outputPath+co.getClassName().toLowerCase()+"/", "list", content, "ftl");
    }

    public  void generateViewEdit(Map<String, Object> model) throws IOException, TemplateException
    {
        ClassObject co = (ClassObject) model.get("classObject");
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.VIEW_EDIT_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_VIEW_PATH.toLowerCase();
        fileHelper.createFile(outputPath+co.getClassName().toLowerCase()+"/", "edit", content, "ftl");
    }

    public  void generateNgRest(Map<String, Object> model) throws IOException, TemplateException
    {
        ClassObject co = (ClassObject) model.get("classObject");
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.NG_REST_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_NG_REST_PATH.toLowerCase();
//		fileHelper.createFile(outputPath+co.getClassName().toLowerCase()+"/", "edit", content, "ftl");
        fileHelper.createFile(outputPath, co.getClassName()+"RestAPI", content, "java");
    }

    public  void generateNgCtrl(Map<String, Object> model) throws IOException, TemplateException
    {
        ClassObject co = (ClassObject) model.get("classObject");
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.NG_CTRL_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_NG_CTRL_PATH.toLowerCase();
        fileHelper.createFile(outputPath, co.getClassName().toLowerCase()+"Ctrl", content, "js");
    }

    public  void generateNgHtml(Map<String, Object> model) throws IOException, TemplateException
    {
        ClassObject co = (ClassObject) model.get("classObject");
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.NG_HTML_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_NG_HTML_PATH.toLowerCase();
        fileHelper.createFile(outputPath+co.getClassName().toLowerCase()+"/", "list", content, "html");
    }


    public  void generateCreateSql(Map<String, Object> model) throws IOException, TemplateException
    {
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.SQL_CREATE_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_SQL_PATH.toLowerCase();
        fileHelper.createFile(outputPath, "create", content, "sql");
    }

    public  void generateResourceSql(Map<String, Object> model) throws IOException, TemplateException
    {
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.SQL_RESOURCE_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_SQL_PATH.toLowerCase();
        fileHelper.createFile(outputPath, "resource", content, "sql");
    }

    public  void generateNgConstant(Map<String, Object> model) throws IOException, TemplateException
    {
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.NG_CONSTANT_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_NG_CONSTANT_PATH.toLowerCase();
        fileHelper.createFile(outputPath, "config.constant", content, "js");
    }

    public  void generateNgRouter(Map<String, Object> model) throws IOException, TemplateException
    {
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.NG_ROUTER_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_NG_ROUTER_PATH.toLowerCase();
        fileHelper.createFile(outputPath, "config.router", content, "js");
    }

    public  void generateNgNav(Map<String, Object> model) throws IOException, TemplateException
    {
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.NG_NAV_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_NG_NAV_PATH.toLowerCase();
        fileHelper.createFile(outputPath, "nav", content, "html");
    }

    public  void generateEntityJson(Map<String, Object> model) throws IOException, TemplateException
    {
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.ENTITY_JSON_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_ENTITY_JSON_PATH.toLowerCase();
        fileHelper.createFile(outputPath, "entity", content, "json");
    }

    public  void generateBat(Map<String, Object> model) throws IOException, TemplateException
    {
        FileHelper fileHelper = new FileHelper();
        FreeMarkerHelper fmHelper = new FreeMarkerHelper();
        String content = fmHelper.render(model, GlobalVariables.BAT_TEMPLATE_PATH);
        String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_BAT_PATH.toLowerCase();
        fileHelper.createFile(outputPath, "move", content, "bat");
    }
}
