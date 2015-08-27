package com.shurrik.codegen.util;

import java.io.IOException;
import java.util.Map;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author lip 创建于	2012-4-13	上午10:00:33 
 */
public class FreeMarkerHelper {

	/**	渲染
	 * @param model
	 * @param templatePath
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public String render(Map<String, Object> model,String templatePath) throws IOException, TemplateException
	{
		Configuration configuration = new Configuration();
		Template tpl = configuration.getTemplate(templatePath);
		return FreeMarkerTemplateUtils.processTemplateIntoString(tpl,
				model);
	}
}
