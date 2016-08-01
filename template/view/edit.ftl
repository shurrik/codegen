<#macro wwwroot>${r"${wwwroot}"}</#macro>
<#assign moduleName = conf.moduleName>
<div class="bjui-pageContent">
    <form action="<@wwwroot/>/${moduleName!}/${classObject.className?lower_case!}/save" id="editForm" data-toggle="validate" data-alertmsg="false" reloadNavtab="true">
        <input type="hidden" name="id" value="${r"${("}${classObject.className?uncap_first!}${r".id)!}"}" />
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                    <tr>
            <#list classObject.properties as property>
                <#if property.name!='createDate'&&property.name!='updateDate'&&property.name!='createrId'&&property.name!='createrName'&&property.name!='updaterId'&&property.name!='updaterName'>
                    <#if property_index gt 0 && property_index%3==0>
                    </tr>
                    <tr>
                    </#if>
                        <td>
                            <label for="${(property.name)!}" class="control-label x85">${(property.comment)!}：</label>
                            <input type="text" name="${(property.name)!}" id="${(property.name)!}" value="${r"${("}${classObject.className?uncap_first!}${r"."}${property.name}${r")!}"}" <#if property.type=='String'>maxlength="${(property.size)!}"</#if> <#if property.notNull><#if property.type=='Integer'>data-rule="digits"<#else>data-rule="required"</#if></#if> >
                        </td>
                </#if>
            </#list>
                    </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>
<div class="bjui-pageFooter" >
    <ul>
        <li><button type="button" class="btn btn-close" data-icon="close">取消</button></li>
        <li><button type="submit" class="btn btn-success" data-icon="save">保存</button></li>
    </ul>
</div>
