package com.shurrik.codegen.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author lip 创建于 2012-4-12 下午7:05:07
 */
public class FileHelper {

	public void createFile(String path,String fileName, String content, String suffix)
			throws IOException {
		String filePath = path+ fileName + "." + suffix;
		System.out.println(filePath);
		
		File dirFile  = null ;
		
		try {
            dirFile  =   new  File(path);
             if ( ! (dirFile.exists())  &&   ! (dirFile.isDirectory())) {
                 boolean  creadok  =  dirFile.mkdirs();
                 if (creadok) {
                    System.out.println( " ok:创建文件夹成功！ " );
                } else {
                    System.out.println( " err:创建文件夹失败！ " );                    
                } 
            } 
         } catch (Exception e) {
            e.printStackTrace();
        } 
		
		
		
		
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		try {

			OutputStream os = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(content);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 	创建文件
	 * @param fileName	文件名
	 * @param content 	文件内容
	 * @param suffix	文件后缀
	 * @throws IOException
	 */
	public void createFile(String fileName, String content, String suffix)
			throws IOException {
		String path = "./src/" + fileName + "." + suffix;
		System.out.println(path);
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		try {

			OutputStream os = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(content);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** 以行为单位读取文件
	 * @param fileName
	 * @return
	 */
	public static String readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer content = new StringBuffer();
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));

			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				content.append(tempString);
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} 
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return content.toString();
	}
}
