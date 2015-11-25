<#list classObjects as classObject>
<li ui-sref-active="active">
    <a ui-sref="app.admin.${classObject.className?lower_case}list">
        <span class="title" translate="${classObject.classRemark?lower_case}">${classObject.classRemark?lower_case}</span>
    </a>
</li>
</#list>