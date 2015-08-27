<#assign corpName = conf.corpName>
<#assign projectName = conf.projectName>
<#assign moduleName = conf.moduleName>
<#assign className = classObject.className>
<#assign className_lower_case = classObject.className?lower_case>
<#assign className_uncap_first = classObject.className?uncap_first>

package com.${corpName}.${projectName}.dao;

import com.${corpName}.common.dao.IBaseDAO;
import com.${corpName}.${projectName}.model.${className};

public interface I${className}DAO extends IBaseDAO<${className}>{

}
