/*字段说明：1属性类型;2表字段长度，为0表示不需要长度;3是否主键;4允许为空;5注释*/
/*
支持以下类型，非字符串长度都设为0
String
Integer
Float
Double
BigDecimal
Boolean
Date
*/
[
    <#list classObjects as classObject>
    {
        "${classObject.className!}#${classObject.classRemark!}":
        {
        <#list classObject.properties as property>
            "${property.name!}":"${property.type!}#${property.size!}#0#<#if property.notNull>0<#else>1</#if>#${property.comment!}"<#if property_has_next>,</#if>
        </#list>
        }
    },
    </#list>
]



