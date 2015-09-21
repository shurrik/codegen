<#assign author = conf.author>
<#assign corpName = conf.corpName>
<#assign projectName = conf.projectName>
<#assign moduleName = conf.moduleName>
<#assign className = classObject.className>
<#assign className_lower_case = classObject.className?lower_case>
<#assign className_uncap_first = classObject.className?uncap_first>

package com.${corpName}.${projectName}.rest.api;

import com.${corpName}.common.domain.view.BizData4Page;
import com.${corpName}.${projectName}.model.${classObject.className!};
import com.${corpName}.${projectName}.service.I${classObject.className!}Service;
import com.${corpName}.${projectName}.util.PageParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${author} on ${.now?string('yyyy/M/dd')}.
 */
@Path("/${className_lower_case}")
public class ${classObject.className!}RestAPI extends BaseRestAPI<I${classObject.className!}Service>{

    @Autowired
    private I${classObject.className!}Service ${className_uncap_first}Service;

    @POST
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BizData4Page<${classObject.className!}> list(Map<String,Object> map){

        Map<String, Object> conditions = getQueryMap(map);
        PageParam pageParam = getPageParam(map);
        BizData4Page<${classObject.className!}> pageCtx = doPage(conditions, pageParam);
        return  pageCtx;
    }

    @POST
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ${classObject.className!} save(Map<String,Object> map){

        String editId = (String) map.get("editId");
        ${classObject.className!} ${className_uncap_first} = ${className_uncap_first}Service.fetch(editId);
        return ${className_uncap_first};
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ${classObject.className!} save(${classObject.className!} ${className_uncap_first}){

        if(StringUtils.isBlank(${className_uncap_first}.getId()))
        {
            ${className_uncap_first}Service.add(${className_uncap_first});
        }
        else
        {
            ${className_uncap_first}Service.update(${className_uncap_first});
        }
        return ${className_uncap_first};
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(Map<String,Object> map){

        String editId = (String) map.get("editId");
        ${className_uncap_first}Service.deleteById(editId);
    }

    protected Map getQueryMap(Map<String,Object> map) {
        Map<String, Object> conditions = new HashMap();
    <#list classObject.properties as property>
		conditions.put("${property.name?uncap_first!}", (String)map.get("${property.name?uncap_first!}"));	
	</#list> 
        
        return conditions;
    }

    @Override
    protected I${classObject.className!}Service getMainService() {
        return ${className_uncap_first}Service;
    }
}
