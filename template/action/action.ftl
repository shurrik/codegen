<#assign corpName = conf.corpName>
<#assign projectName = conf.projectName>
<#assign moduleName = conf.moduleName>
<#assign className = classObject.className>
<#assign className_lower_case = classObject.className?lower_case>
<#assign className_uncap_first = classObject.className?uncap_first>

package com.${corpName}.${projectName}.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.${corpName}.common.domain.view.BizData4Page;
import com.${corpName}.${projectName}.model.${className};
import com.${corpName}.${projectName}.service.I${className}Service;
import com.${corpName}.common.utils.IdGenerator;
import com.${corpName}.common.utils.JsonResult;
import com.${corpName}.common.utils.PageParam;
import com.${corpName}.common.utils.Constants;

@Controller
public class ${className}Action extends AbstractAdminController<I${className}Service>{

    @Autowired
    private I${className}Service ${className_uncap_first}Service;
    
    @RequestMapping(value="/${className_lower_case}/list")
    public String list(${className} ${className_uncap_first},ModelMap model,PageParam pageParam,HttpServletRequest request,HttpServletResponse response){

        //获取参数
    	Map<String, Object> conditions = getQueryMap(${className_uncap_first}); 	
    	BizData4Page<${className}> pageCtx = doPage(model, conditions, pageParam);
    	model.addAttribute("${className_uncap_first}", ${className_uncap_first});
    	return "/module/${className_lower_case}/list";
    }    
    
    @RequestMapping(value="/${className_lower_case}/add")
    public String add(ModelMap model,HttpServletRequest request,HttpServletResponse response){

    	model.addAttribute("${className_uncap_first}", new ${className}());
        return "module/${className_lower_case}/edit";
    }    
    
    @RequestMapping(value="/${className_lower_case}/edit")
    public String edit(String id,ModelMap model,HttpServletRequest request,HttpServletResponse response){
    	
    	${className} ${className_uncap_first} = ${className_uncap_first}Service.fetch(id);
    	model.addAttribute("${className_uncap_first}", ${className_uncap_first});
        return "module/${className_lower_case}/edit";
    }    
    
    @RequestMapping(value="/${className_lower_case}/save")
    @ResponseBody
    public String save(HttpServletRequest request,${className} ${className_uncap_first}){
		boolean isAddNew = StringUtils.isBlank(${className_uncap_first}.getId())?true:false;
		if(isAddNew)
		{
			${className_uncap_first}.setId(IdGenerator.createNewId());
			${className_uncap_first}Service.add(${className_uncap_first});
		}
		else
		{
			${className_uncap_first}Service.update(${className_uncap_first});
		}
        return JsonResult.saveSuccessClose(Constants.MAIN_TAB).toString();
    }    
    
    @RequestMapping(value="/${className_lower_case}/delete")
    @ResponseBody
    public String delete(String ids,HttpServletRequest request){
    	
    	${className_uncap_first}Service.deleteByIds(ids);
    	return JsonResult.DELETE_SUCCESS.toString();
    }       
	
    protected Map getQueryMap(${className} ${className_uncap_first})
    {
    	Map<String, Object> conditions = new HashMap();
    	
    <#list classObject.properties as property>
		conditions.put("${property.name?uncap_first!}", ${className_uncap_first}.get${property.name?cap_first!}());		
	</#list>    
    	return conditions;
    }

    @Override
    protected I${className}Service getMainService() {
        return ${className_uncap_first}Service;
    }

    @Override
    protected String getMainObjName() {
        return "${className_lower_case}";
    }

    @Override
    protected String getViewTitle() {
        return "${className_lower_case}";
    }
}
