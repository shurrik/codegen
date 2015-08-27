package com.shurrik.codegen.model;

import java.util.List;

/**
 * @author lip 创建于 2012-4-12 下午7:00:33
 */
public class ClassObject {

	private String className;//类名
	private List<ClassProperty> properties;//类属性
	private String tableName;//数据库表名
	private List<Column> columns;//数据表子段
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<ClassProperty> getProperties() {
		return properties;
	}
	public void setProperties(List<ClassProperty> properties) {
		this.properties = properties;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

}
