<#assign corpName = conf.corpName>
<#assign projectName = conf.projectName>
<#assign moduleName = conf.moduleName>
<#assign className = classObject.className>
<#assign classRemark = classObject.classRemark>
<#assign className_lower_case = classObject.className?lower_case>
<#assign className_uncap_first = classObject.className?uncap_first>
<#macro wwwroot>${r"${wwwroot}"}</#macro>
<#macro rowfield field>${r"${(row."}${field!}${r")!}"}</#macro>
<#macro objfield obj field>${r"${("}${obj!}${r"."}${field}${r")!}"}</#macro>
<div ng-controller="${className_lower_case}Ctrl">
<!-- start: PAGE TITLE -->
<section id="page-title">
    <div class="row">
        <div class="col-sm-8">
            <h1 class="mainTitle" translate="${classRemark}">{{ mainTitle }}</h1>
            <span class="mainDescription">${classRemark}</span>
        </div>
        <div ncy-breadcrumb></div>
    </div>
</section>
<!-- end: PAGE TITLE -->

<!-- start: INLINE EDIT EXAMPLE -->
<div class="container-fluid container-fullw">
    <div class="row">
        <div class="col-md-12">
            <form role="form" class="form-inline">
		<#list classObject.properties as property>
                <div class="form-group">${(property.comment)!}：<input type="text"  class="form-control" ng-model="queryMap.${(property.name)!}"></div>						    			
        </#list>                
                <button class="btn btn-primary" type="button" ng-click="query()">查询</button>
                <button class="btn btn-green" type="button" ng-click="add()">新增</button>
            </form>
        </div>
        <div class="col-md-12">
            <div>
                 <div class="table-responsive">
                    <table ng-table="tableParams" class="table">
                        <tbody ng-repeat="p in $data">
                        <tr id="tr{{p.id}}" ng-class-odd="'odd'" ng-class-even="'even'">
					<#list classObject.properties as property>
			                <td class="rowTd" data-title="'${(property.comment)!}'"  sortable="'${(property.name)!}'">{{p.${(property.name)!}}}</td>						    			
			        </#list>                        
                            <td class="rowTd" >
                                <div class="pull-right margin-right-10">
                                <input type=button class="btn btn-primary  btn-sm" id="editRowBtn{{p.id}}" value="编辑"
                                       ng-click="setEditId(p.id)">
                                <input type=button class="btn btn-red  btn-sm"  value="删除"
                                       ng-click="delEditId(p.id)">
                                 </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="pull-right">
                    <pagination total-items="records" ng-model="queryMap.pageCurrent" max-size="10" class="pagination-sm" boundary-links="true" rotate="false"
                                items-per-page="queryMap.pageSize" ng-change="pageChanged()" previous-text="上一页" next-text="下一页" first-text="首页" last-text="末页"></pagination>
                </div>
            </div>


        </div>
    </div>
</div>

<script type="text/ng-template" id="edit.html">
    <div class="modal-header">
        <h3 class="modal-title">编辑</h3>
    </div>
    <div class="modal-body">
        <div class="panel ">
            <div class="panel-body">
                <form role="form">
                    <input type="hidden"  ng-model="${className_uncap_first}.id" value="{{${className_uncap_first}.id}}">
					<#list classObject.properties as property>
                    <div class="form-group">
                        <label for="${(property.name)!}">${(property.comment)!}</label>
                        <input type="text" class="form-control" id="${(property.name)!}" ng-model="${className_uncap_first}.${(property.name)!}" value="{{${className_uncap_first}.${(property.name)!}}}">
                    </div>			                						    			
			        </#list>                    
                </form>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" ng-click="ok($event)">确定</button>
        <button class="btn btn-red" ng-click="cancel($event)">取消</button>
    </div>
</script>
</div>
<!-- end: INLINE EDIT EXAMPLE -->
