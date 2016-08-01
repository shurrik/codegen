<#assign corpName = conf.corpName/>
<#assign projectName = conf.projectName/>
<#assign projectPath = conf.projectPath/>
<#--set dir=d:\workspace\${projectName}-->
set dir=${projectPath}
xcopy ..\..\output\model	%dir%\${projectName}-domain\src\main\java\com\${corpName}\${projectName}\model /e
xcopy ..\..\output\dao	    %dir%\${projectName}-biz\src\main\java\com\${corpName}\${projectName}\dao /e
xcopy ..\..\output\service	%dir%\${projectName}-biz\src\main\java\com\${corpName}\${projectName}\service /e
xcopy ..\..\output\mybatis	%dir%\${projectName}-biz\src\main\resources\mybatis /e
xcopy ..\..\output\action	%dir%\${projectName}-web\src\main\java\com\${corpName}\${projectName}\action /e
xcopy ..\..\output\view	    %dir%\${projectName}-web\src\main\webapp\WEB-INF\freemarker\views\module /e
PAUSE