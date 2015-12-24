package com.shurrik.codegen;

import java.io.IOException;

import com.shurrik.codegen.core.JsonGenerator;
import freemarker.template.TemplateException;

/**
 * @author lip 创建于	2012-4-12	下午7:33:15 
 */
public class JsonCommendLine {

	public static void main(String args[]) throws Exception
	{
		JsonGenerator generator = new JsonGenerator();
		generator.run();
	}
}
