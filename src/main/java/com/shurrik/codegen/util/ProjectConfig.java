package com.shurrik.codegen.util;

import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ProjectConfig {

	private static ProjectConfig instance = null;
	public static ProjectConfig getInstance(){
		if(instance==null)
		{
			synchronized (ProjectConfig.class) {
				if(instance ==null)
				{
					instance = new ProjectConfig();
					instance.loadProperties();
				}
			}
		}
		return instance;
	}
	private ProjectConfig(){}
	
	private String corpName;
	private String projectName;
	private String moduleName;
	private String tablePrefix;
	private String author;
	private String dbPrefix;
	private String projectPath;

	public void loadProperties(){
		try {
			Resource res = new ClassPathResource("project.properties");
			Properties p = new Properties();   
			p.load(res.getInputStream());   
			if(p.getProperty("corpname")!=null){
				this.setCorpName(p.getProperty("corpname"));
			}
			if(p.getProperty("projectname")!=null){
				this.setProjectName(p.getProperty("projectname"));
			}
			if(p.getProperty("modulename")!=null){
				this.setModuleName(p.getProperty("modulename"));
			}
			if(p.getProperty("tableprefix")!=null){
				this.setTablePrefix(p.getProperty("tableprefix"));
			}	
			if(p.getProperty("author")!=null){
				this.setAuthor(p.getProperty("author"));
			}
			if(p.getProperty("dbprefix")!=null){
				this.setDbPrefix(p.getProperty("dbprefix"));
			}
			if(p.getProperty("projectpath")!=null){
				this.setProjectPath(p.getProperty("projectpath"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
	
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getTablePrefix() {
		return tablePrefix;
	}
	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDbPrefix() {
		return dbPrefix;
	}

	public void setDbPrefix(String dbPrefix) {
		this.dbPrefix = dbPrefix;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}
}
