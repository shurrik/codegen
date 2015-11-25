<#list classObjects as classObject>
}).state('app.admin.${classObject.className?lower_case}list', {
    url: "/${classObject.className?lower_case}list",
    templateUrl: "assets/views/biz/admin/${classObject.className?lower_case}/list.html",
    resolve: loadSequence('ngTable', '${classObject.className?lower_case}Ctrl'),
    title: '${classObject.classRemark}',
    ncyBreadcrumb: {
        label: '${classObject.classRemark}'
    }
</#list>
