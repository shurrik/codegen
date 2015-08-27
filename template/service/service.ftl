<#assign corpName = conf.corpName>
<#assign projectName = conf.projectName>
<#assign moduleName = conf.moduleName>
<#assign className = classObject.className>
<#assign className_lower_case = classObject.className?lower_case>
<#assign className_uncap_first = classObject.className?uncap_first>

package com.${corpName}.${projectName}.service;
import com.${corpName}.common.service.IBaseService;
import com.${corpName}.common.service.IPageService;
import com.${corpName}.${projectName}.dao.I${className}DAO;
import com.${corpName}.${projectName}.model.${className};
public interface I${className}Service extends IBaseService<I${className}DAO,${className}>,IPageService<I${className}DAO,${className}>{

}