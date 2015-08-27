package com.shurrik.codegen;

import java.io.IOException;

import com.shurrik.codegen.core.Generator;

import freemarker.template.TemplateException;

/**
 * @author lip 创建于	2012-4-12	下午7:33:15 
 */
public class CommendLine {

	public static void main(String args[]) throws IOException, TemplateException
	{
		Generator.generate();
	}
}
