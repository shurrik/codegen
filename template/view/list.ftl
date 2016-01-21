<#assign corpName = conf.corpName>
<#assign projectName = conf.projectName>
<#assign moduleName = conf.moduleName>
<#assign className = classObject.className>
<#assign className_lower_case = classObject.className?lower_case>
<#assign className_uncap_first = classObject.className?uncap_first>
<#macro wwwroot>${r"${wwwroot}"}</#macro>
<#macro rowfield field>${r"${(row."}${field!}${r")!}"}</#macro>
<#macro rowfieldDate field>${r"${(row."}${field!}${r"?string('yyyy-MM-dd HH:mm:ss'))!}"}</#macro>
<#macro rowfieldBoolean field>${r"<#if row."}${field!}${r">是<#else>否</#if>"}</#macro>
<#macro objfield obj field>${r"${("}${obj!}${r"."}${field}${r")!}"}</#macro>
<div class="bjui-pageHeader">
    <div class="toolBar">
        <div class="btn-group" role="group" aria-label="操作栏">
            <a type="button" class="btn btn-success" href="<@wwwroot/>/${moduleName!}/${classObject.className?lower_case!}/add" data-toggle="navtab" data-id="form"><i class="fa fa-plus"></i>新增</a>
            <a type="button" class="btn btn-danger" href="<@wwwroot/>/${moduleName!}/${classObject.className?lower_case!}/delete?ids={#bjui-selected}" data-toggle="doajax" data-confirm-msg="确定要删除选中项吗？" data-id="form" data-mask="true"><i class="fa fa-trash-o"></i>删除选中行</a>
        </div>
    </div>
    <form id="pagerForm" data-toggle="ajaxsearch" action="<@wwwroot/>/${moduleName!}/${classObject.className?lower_case!}/list" method="post">
        <input type="hidden" name="pageSize" value="${(pageParam.pageSize)!}">
        <input type="hidden" name="pageCurrent" value="${(pageParam.pageCurrent)!}">
        <input type="hidden" name="orderField" value="${(pageParam.orderField)!}">
        <input type="hidden" name="orderDirection" value="${(pageParam.orderDirection)!}">
        <div class="bjui-searchBar">
		<#list classObject.properties as property>
			<label>${(property.comment)!}：</label><input type="text" id="${(property.name)!}" name="${(property.name)!}" value="<@objfield obj=className_uncap_first field=property.name/>"  class="form-control" size="10">&nbsp;				    			
        </#list>        
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true);" data-icon="undo">清空查询</a>
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
            <tr>
				<th width="50">No.</th>                
    		<#list classObject.properties as property>
    			<th data-order-direction="" data-order-field="${(property.name)!}">${(property.comment)!}</th>               
            </#list>
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
        ${r"<#list pageCtx.rows as row>"}
        	<tr data-id="${r'${(row.id)!}'}">
				<td>${r"${(row_index+1)!}"}</td>	        	
			<#list classObject.properties as property>
             <#if property.type='Date'>
                 <td title="<@rowfieldDate field=property.name/>"><@rowfieldDate field=property.name/></td>
             <#elseif property.type='Boolean'>
                 <td><@rowfieldBoolean field=property.name/></td>
             <#else>
                 <td title="<@rowfield field=property.name/>"><@rowfield field=property.name/></td>
             </#if>
            </#list>	                
                <td>
                    <a href="<@wwwroot/>/${moduleName!}/${(className_lower_case)!}/edit?id=${r'${(row.id)!}'}" class="btn btn-blue" data-toggle="navtab" data-id="form" data-reload-warn="本页已有打开的内容，确定将刷新本页内容，是否继续？" data-title="编辑">编辑</a>
                    <a href="<@wwwroot/>/${moduleName!}/${(className_lower_case)!}/delete?ids=${r'${(row.id)!}'}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除该行信息吗？">删</a>
                </td>	        	
        	</tr>
		${r"</#list>"}	        	
        </tbody>
    </table>  
</div>
${r"<@p.pagination pageCtx=pageCtx pageParam=pageParam/>"}