        //biz
<#list classObjects as classObject>
        '${classObject.className?lower_case}Ctrl': 'assets/js/controllers/biz/admin/${classObject.className?lower_case}Ctrl.js',
</#list>