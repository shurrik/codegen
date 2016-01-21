package com.shurrik.codegen.util.helper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shurrik.codegen.model.ClassObject;
import com.shurrik.codegen.util.GlobalVariables;

public abstract class  ClassObjecHelper {
	public abstract List<ClassObject>  getClassObjectList() throws SQLException;

	public final static Map MYSQL_MAP = getMysqlMap();
	public final static Map ORACLE_MAP = getOracleMap();
	public final static Map MSSQL_MAP = getMssqlMap();

	public  String getColumnType(String propertyType)
	{
		String columnType ="";
		switch (propertyType) {
			case "String":
				columnType = "varchar";
				break;
			case "Integer":
				columnType = "int";
				break;
			case "Float":
				columnType = "float";
				break;
			case "Double":
				columnType = "double";
				break;
			case "Long":
				columnType = "bigint";
				break;
			case "BigDecimal":
				columnType = "decimal";
				break;
			case "Boolean":
				columnType = "tinyint";
				break;
			case "Date":
				columnType = "datetime";
				break;
			default:
				columnType = "varchar";
				break;
		}

		return columnType;
	}
	public String getPropertyType(String columnType,String dbType)
	{
		Map map = getDbTypeMap(dbType);
		String propertyType = (String) map.get(columnType);
		return propertyType;
	}


	//数据库类型转JAVA类型
	private Map getDbTypeMap(String dbType)
	{
		if(dbType.equals(GlobalVariables.ORACLE))
		{
			return getOracleMap();
		}
		else if(dbType.equals(GlobalVariables.MSSQL))
		{
			return getMssqlMap();
		}
		else
		{
			return getMysqlMap();
		}
	}

	private static Map getOracleMap()
	{
		Map map = new HashMap();
		map.put("varchar2","String");
		map.put("varchar","String");
		map.put("integer","Integer");
		map.put("number","Integer");
		map.put("long","long");
		map.put("long raw","long");
		map.put("date","Date");
		return map;
	}

	private static Map getMysqlMap()
	{
		Map map = new HashMap();
		map.put("varchar","String");
		map.put("int","Integer");
		map.put("float","Float");
		map.put("bigint","Long");
		map.put("double","Double");
		map.put("decimal","BigDecimal");
		map.put("bit","Boolean");
		map.put("tinyint","Boolean");
		map.put("datetime","Date");
		return map;
	}

	private static Map getMssqlMap()
	{
		Map map = new HashMap();
		return map;
	}

}
