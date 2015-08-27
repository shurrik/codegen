<#assign corpName = conf.corpName>
<#assign projectName = conf.projectName>
<#assign moduleName = conf.moduleName>
<#assign className = classObject.className>
package com.${corpName}.${projectName}.model;
import com.${corpName}.common.domain.BaseDomain;
import java.math.BigDecimal;
import java.util.Date;

public class ${className} extends BaseDomain{
<#list classObject.properties as property>
	private ${property.type!} ${property.name!}; //${property.comment!}
</#list>

<#list classObject.properties as property>
	public ${property.type!} get${property.name?cap_first!}() {
		return ${property.name!};
	}
	public void set${property.name?cap_first!}(${property.type!} ${property.name!}) {
		this.${property.name!} = ${property.name!};
	}
</#list>
	
}
