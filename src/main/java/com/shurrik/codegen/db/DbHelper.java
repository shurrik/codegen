package com.shurrik.codegen.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {
	
/*	private String url = "jdbc:mysql://localhost:3306/oa?characterEncoding=UTF-8&amp;autoReconnect=true";
	private String username = "root";
	private String pw="root";
	private Connection conn = null;*/
	
	private String url;
	private String username;
	private String pw;
	private Connection conn = null;	
	
	public DbHelper(String url,String username,String pw)
	{
		this.url = url;
		this.username = username;
		this.pw = pw;
		this.getConn();
	}
	
	public void getTableNameByCon(){
		try {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet rs = meta.getTables(null, null, null,
				new String[] { "TABLE" });
			while (rs.next()) {
				System.out.println("表名：" + rs.getString(3));
				System.out.println("表所属用户名：" + rs.getString(2));
				System.out.println("------------------------------");
			}
			this.close();			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public List<String> getTableNames()
	{
		List<String> list = new ArrayList();
		try {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet rs = meta.getTables(null, null, null,
				new String[] { "TABLE" });
			while (rs.next()) {
				list.add(rs.getString(3));
			}
			this.close();			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	
	public Connection getConn()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn=DriverManager.getConnection(url,username,pw);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;		
	}
	
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args) {
		//mysql
		String url = "jdbc:mysql://localhost:3306/ci?characterEncoding=UTF-8&amp;autoReconnect=true";
		String username = "root";
		String pw="root";	
		
		DbHelper dh = new DbHelper(url,username,pw);
		List<String> tableNames = dh.getTableNames();
		for(String tableName:tableNames)
		{
			System.out.println(tableName);
		}
	}
}
