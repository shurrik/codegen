<#assign corpName = conf.corpName>
<#assign projectName = conf.projectName>
<#assign moduleName = conf.moduleName>
<#assign className = classObject.className>
<#assign className_lower_case = classObject.className?lower_case>
<#assign className_uncap_first = classObject.className?uncap_first>

package com.${corpName}.${projectName}.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.${corpName}.common.service.impl.AbstractPageService;
import com.${corpName}.${projectName}.dao.I${classObject.className!}DAO;
import com.${corpName}.${projectName}.model.${classObject.className!};
import com.${corpName}.${projectName}.service.I${classObject.className!}Service;

@SuppressWarnings("unchecked")
@Service("${classObject.className!}ServiceImpl")

public class ${classObject.className!}ServiceImpl extends AbstractPageService<I${classObject.className!}DAO,${classObject.className!}> implements I${classObject.className!}Service {

	@Autowired
	private I${classObject.className!}DAO ${classObject.className?uncap_first!}DAO;
	@Override
	public boolean getEnableDataPerm() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public I${classObject.className!}DAO getDao() {
		return ${classObject.className?uncap_first!}DAO;
	}
}
