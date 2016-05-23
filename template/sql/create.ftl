<#list classObjects as classObject>
DROP TABLE IF EXISTS `${(classObject.tableName)!}`;
CREATE TABLE `${(classObject.tableName)!}` (
  `id` varchar(64) COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  <#list classObject.columns as column>
  `${column.name}` ${column.type}<#if (column.length != '0')>(${column.length})</#if> COLLATE utf8_general_ci <#if column.notNull>NOT<#else>DEFAULT</#if> NULL COMMENT '${column.comment}',
  </#list>
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='${(classObject.classRemark)!}';
</#list>
              