package com.shurrik.codegen.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author lip 创建于	2012-4-12	下午7:06:52 
 */
public class JsonHelper {
	
	/**	从json文件读取类对象队列
	 * @return
	 */
	public List<JSONObject> getJsonClassList()
	{
		FileHelper fh = new FileHelper();
		String data= fh.readFileByLines(GlobalVariables.JSON_PATH);
		JSONArray list = JSONArray.fromObject(data);
		/*Iterator itor = jsonObj.keySet().iterator();
        while (itor.hasNext()) {  
        	JSONArray entry = (JSONArray) jsonObj.get(itor.next());  
            list.add(entry);
  
        }  */
        return list;
	}
}
