<#list classObjects as classObject>
delete from `t_sys_resource` where url = 'admin/${(classObject.className?lower_case)!}/list';
insert into `t_sys_resource` (`id`, `name`, `url`, `pid`, `summary`, `res_order`, `create_date`, `update_date`) values
('${(classObject.id)!}','${(classObject.classRemark)!}','admin/${(classObject.className?lower_case)!}/list',NULL,NULL,NULL,sysdate(),sysdate());
</#list>