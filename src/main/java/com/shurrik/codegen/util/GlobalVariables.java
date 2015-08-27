package com.shurrik.codegen.util;

/**
 * @author lip 创建于 2012-4-12 下午5:36:34
 */
public class GlobalVariables {
	public static final String OUTPUT_ENCODEING="utf-8";
	public static final String OUTPUT_ROOTPATH="./output";
	public static final String JSON_PATH="./entity/entity.json";
	public static final String JAVA_FILE_SUFFIX=".java";
	

	public static final String ENTITY_TEMPLATE_PATH = "./template/model/model.ftl";
	public static final String DAO_TEMPLATE_PATH = "./template/dao/dao.ftl";
	public static final String DAOXML_TEMPLATE_PATH = "./template/mybatis/xml.ftl";
	public static final String SERVICEINTER_TEMPLATE_PATH = "./template/service/service.ftl";
	public static final String SERVICEIMPL_TEMPLATE_PATH = "./template/service/impl/impl.ftl";
	
	public static final String MANAGER_TEMPLATE_PATH = "./template/manager/manager.ftl";
	public static final String ACTION_TEMPLATE_PATH = "./template/action/action.ftl";
	public static final String VIEW_LIST_TEMPLATE_PATH = "./template/view/list.ftl";
	public static final String VIEW_EDIT_TEMPLATE_PATH = "./template/view/edit.ftl";
	public static final String SQL_CREATE_TEMPLATE_PATH = "./template/sql/create.ftl";	
	
	
	public static final String OUTPUT_RELATIVE_ENTITY_PATH="/model/";
	public static final String OUTPUT_RELATIVE_DAO_PATH="/dao/"; 
	public static final String OUTPUT_RELATIVE_DAOXML_PATH="/mybatis/"; 
	public static final String OUTPUT_RELATIVE_SERVICEINTER_PATH="/service/";
	public static final String OUTPUT_RELATIVE_SERVICEIMPL_PATH="/service/impl/";
	public static final String OUTPUT_RELATIVE_MANAGER_PATH="/manager/";
	public static final String OUTPUT_RELATIVE_ACTION_PATH="/action/";
	public static final String OUTPUT_RELATIVE_VIEW_PATH="/view/";
	public static final String OUTPUT_RELATIVE_SQL_PATH="/sql/";	
}