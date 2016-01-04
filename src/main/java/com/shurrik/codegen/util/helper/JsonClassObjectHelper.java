package com.shurrik.codegen.util.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import net.sf.json.JSONObject;

import com.shurrik.codegen.model.ClassObject;
import com.shurrik.codegen.model.ClassProperty;
import com.shurrik.codegen.model.Column;
import com.shurrik.codegen.util.CharacterCaseUtils;
import com.shurrik.codegen.util.JsonHelper;
import com.shurrik.codegen.util.ProjectConfig;
import org.apache.commons.lang.StringUtils;

/**
 * @author lip 创建于 2012-4-12 下午5:36:34
 */
public class JsonClassObjectHelper extends ClassObjecHelper{

	/**	从json文件读取数据，转换成ClassObject队列
	 * @return
	 */
	@Override
	public List<ClassObject>  getClassObjectList()
	{
		List<JSONObject> jsonClasses = getJsonClassList();
		List<ClassObject> classes = convertJsonClassList2ClassObjectList(jsonClasses);
		return classes;
	}	
	
	public ClassObject jsonObject2ClassObject(String className,JSONObject jsonProperties)
	{
		ClassObject co = ClassObject.instance();
		//setClassName
//		co.setClassName(className);

		String[] arr = className.split("#");
		className = arr[0];
		co.setClassName(arr[0]);
		co.setClassRemark(arr[1]);
		
		//setProperties
		//setColumns
		List<ClassProperty> properties = new ArrayList();
		List<Column> columns = new ArrayList();
		Iterator itor = jsonProperties.keySet().iterator();
		while (itor.hasNext()) {
			ClassProperty cp = new ClassProperty();
			Column column = new Column();
			
			String pName = (String) itor.next();
			String pValue = jsonProperties.getString(pName);
			String[] pArr = pValue.split("#");
			String pType = pArr[0];
			String comment = pArr[4];
			
			String cName = CharacterCaseUtils.toUnderlineCase(pName);
//			String cType = "varchar";
			/*Integer cLength = Integer.parseInt(pArr[1]);*/
			String cLength = pArr[1];
			Boolean isPk = Integer.parseInt(pArr[2])>0?true:false;
			Boolean notNull = Integer.parseInt(pArr[3])<=0?true:false;
			
			
			cp.setName(pName);
			cp.setType(pType);
			cp.setComment(comment);
			cp.setSize(cLength);
			cp.setNotNull(notNull);
			
			column.setName(cName);
//			column.setType(cType);
			column.setType(getColumnType(pType));
			
			column.setLength(cLength);
			column.setIsPk(isPk);
			column.setNotNull(notNull);
			column.setComment(comment);
			column.setProName(pName);
			
			properties.add(cp);
			columns.add(column);

        }
		co.setProperties(properties);
		co.setColumns(columns);
		
		//setTableName
		ProjectConfig conf = ProjectConfig.getInstance();
		co.setTableName(StringUtils.isNotBlank(conf.getTablePrefix())?"t_"+conf.getTablePrefix()+"_"+className.toLowerCase():"t_"+className.toLowerCase());
		
		return co;
	}
	

	
//	private void setColumns(ClassObject classObject,JSONObject jsonProperties)
//	{
//		List<Column> columns = new ArrayList();
//		Iterator itor = jsonProperties.keySet().iterator();
//		while (itor.hasNext()) {
//			Column column = new Column();
//			String propertyName = (String) itor.next();
//			String propertyType = jsonProperties.getString(propertyName);
//			column.setName(CharacterCaseUtils.toUnderlineCase(propertyName));
//			column.setType(propertyType);
//			columns.add(column);
//        }
//		classObject.setColumns(columns);
//	}
	
	/** json类对象队列转换成ClassObject队列
	 * @param jsonClasses
	 * @return
	 */
	public List<ClassObject> convertJsonClassList2ClassObjectList(List<JSONObject> jsonClasses)
	{
		List<ClassObject> classes = new ArrayList();
		JsonClassObjectHelper coHelper = new JsonClassObjectHelper();
		for(JSONObject jsonClass:jsonClasses)
		{
			Iterator itor = jsonClass.keySet().iterator();
			
			String className = null;
			JSONObject classProperties = null;
			while (itor.hasNext()) {  
				className = (String) itor.next();
				classProperties = jsonClass.getJSONObject(className);
				break;
	        }
			ClassObject co = coHelper.jsonObject2ClassObject(className, classProperties);
			classes.add(co);
		}
		return classes;
	}
	
	/**	从json文件读取类对象队列
	 * @return
	 */
	public List<JSONObject> getJsonClassList()
	{
		JsonHelper jsonHelper = new JsonHelper();
		List<JSONObject> jsonClasses = jsonHelper.getJsonClassList();
		return jsonClasses;
	}
}
