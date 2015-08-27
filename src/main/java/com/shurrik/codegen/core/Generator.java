package com.shurrik.codegen.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shurrik.codegen.model.ClassObject;
import com.shurrik.codegen.util.FileHelper;
import com.shurrik.codegen.util.FreeMarkerHelper;
import com.shurrik.codegen.util.GlobalVariables;
import com.shurrik.codegen.util.ProjectConfig;
import com.shurrik.codegen.util.helper.ClassObjecHelper;
import com.shurrik.codegen.util.helper.JsonClassObjectHelper;

import freemarker.template.TemplateException;

/**
 * @author lip 创建于	2012-4-13	上午10:46:16 
 */
public class Generator {

	public static void generate() throws IOException, TemplateException
	{
		List<ClassObject> classObjectList = getClassObjectList();
		ProjectConfig conf = ProjectConfig.getInstance();
		
		System.out.println(conf.getCorpName());
		System.out.println(conf.getProjectName());
		System.out.println(conf.getModuleName());
		
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
		}
		
		Map<String, Object> model = new HashMap();
		model.put("classObjects", classObjectList);		
		generateCreateSql(model);
	}
	
	public static void generateEntity(Map<String, Object> model) throws IOException, TemplateException
	{
		ClassObject co = (ClassObject) model.get("classObject");
		FileHelper fileHelper = new FileHelper();
		FreeMarkerHelper fmHelper = new FreeMarkerHelper();
		String content = fmHelper.render(model, GlobalVariables.ENTITY_TEMPLATE_PATH);
		String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_ENTITY_PATH;
		fileHelper.createFile(outputPath, co.getClassName(), content, "java");
	}
	
	public static void generateDao(Map<String, Object> model) throws IOException, TemplateException
	{
		ClassObject co = (ClassObject) model.get("classObject");
		FileHelper fileHelper = new FileHelper();
		FreeMarkerHelper fmHelper = new FreeMarkerHelper();
		String content = fmHelper.render(model, GlobalVariables.DAO_TEMPLATE_PATH);
		String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_DAO_PATH;
		fileHelper.createFile(outputPath, "I"+co.getClassName()+"DAO", content, "java");
	}
	public static void generateDaoXML(Map<String, Object> model) throws IOException, TemplateException
	{
		ClassObject co = (ClassObject) model.get("classObject");
		FileHelper fileHelper = new FileHelper();
		FreeMarkerHelper fmHelper = new FreeMarkerHelper();
		String content = fmHelper.render(model, GlobalVariables.DAOXML_TEMPLATE_PATH);
		String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_DAOXML_PATH;
		fileHelper.createFile(outputPath, "I"+co.getClassName()+"DAO", content, "xml");
	}
	public static void generateService(Map<String, Object> model) throws IOException, TemplateException
	{
		ClassObject co = (ClassObject) model.get("classObject");
		FileHelper fileHelper = new FileHelper();
		FreeMarkerHelper fmHelper = new FreeMarkerHelper();
		String content = fmHelper.render(model, GlobalVariables.SERVICEINTER_TEMPLATE_PATH);
		String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_SERVICEINTER_PATH;
		fileHelper.createFile(outputPath, "I"+co.getClassName()+"Service", content, "java");
	}
	public static void generateImpl(Map<String, Object> model) throws IOException, TemplateException
	{
		ClassObject co = (ClassObject) model.get("classObject");
		FileHelper fileHelper = new FileHelper();
		FreeMarkerHelper fmHelper = new FreeMarkerHelper();
		String content = fmHelper.render(model, GlobalVariables.SERVICEIMPL_TEMPLATE_PATH);
		String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_SERVICEIMPL_PATH;
		fileHelper.createFile(outputPath, co.getClassName()+"ServiceImpl", content, "java");
	}
	
	
	
	
	
	
	public static void generateManager(Map<String, Object> model) throws IOException, TemplateException
	{
		ClassObject co = (ClassObject) model.get("classObject");
		FileHelper fileHelper = new FileHelper();
		FreeMarkerHelper fmHelper = new FreeMarkerHelper();
		String content = fmHelper.render(model, GlobalVariables.MANAGER_TEMPLATE_PATH);
		String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_MANAGER_PATH;
		fileHelper.createFile(outputPath, co.getClassName()+"Mng", content, "java");
	}
	
	public static void generateAction(Map<String, Object> model) throws IOException, TemplateException
	{
		ClassObject co = (ClassObject) model.get("classObject");
		FileHelper fileHelper = new FileHelper();
		FreeMarkerHelper fmHelper = new FreeMarkerHelper();
		String content = fmHelper.render(model, GlobalVariables.ACTION_TEMPLATE_PATH);
		String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_ACTION_PATH;
		fileHelper.createFile(outputPath, co.getClassName()+"Action", content, "java");
	}
	
	
	public static void generateViewList(Map<String, Object> model) throws IOException, TemplateException
	{
		ClassObject co = (ClassObject) model.get("classObject");
		FileHelper fileHelper = new FileHelper();
		FreeMarkerHelper fmHelper = new FreeMarkerHelper();
		String content = fmHelper.render(model, GlobalVariables.VIEW_LIST_TEMPLATE_PATH);
		String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_VIEW_PATH.toLowerCase();
		fileHelper.createFile(outputPath+co.getClassName().toLowerCase()+"/", "list", content, "ftl");
	}
	
	public static void generateViewEdit(Map<String, Object> model) throws IOException, TemplateException
	{
		ClassObject co = (ClassObject) model.get("classObject");
		FileHelper fileHelper = new FileHelper();
		FreeMarkerHelper fmHelper = new FreeMarkerHelper();
		String content = fmHelper.render(model, GlobalVariables.VIEW_EDIT_TEMPLATE_PATH);
		String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_VIEW_PATH.toLowerCase();
		fileHelper.createFile(outputPath+co.getClassName().toLowerCase()+"/", "edit", content, "ftl");
	}
	
	public static void generateCreateSql(Map<String, Object> model) throws IOException, TemplateException
	{
//		ClassObject co = (ClassObject) model.get("classObject");
		FileHelper fileHelper = new FileHelper();
		FreeMarkerHelper fmHelper = new FreeMarkerHelper();
		String content = fmHelper.render(model, GlobalVariables.SQL_CREATE_TEMPLATE_PATH);
		String outputPath = GlobalVariables.OUTPUT_ROOTPATH+GlobalVariables.OUTPUT_RELATIVE_SQL_PATH.toLowerCase();
		fileHelper.createFile(outputPath, "create", content, "sql");
	}	
	
	/**	从json文件获取类对象队列
	 * @return
	 */
	public static List<ClassObject> getClassObjectList()
	{
		ClassObjecHelper helper = new JsonClassObjectHelper();
		return  helper.getClassObjectList();
	}
}
