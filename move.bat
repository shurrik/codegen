set dir=d:\workspace\crvci
xcopy .\output\model	%dir%\crvci-domain\src\main\java\com\crv\ci\model /e
xcopy .\output\dao	    %dir%\crvci-biz\src\main\java\com\crv\ci\dao /e
xcopy .\output\service	%dir%\crvci-biz\src\main\java\com\crv\ci\service /e
xcopy .\output\mybatis	%dir%\crvci-biz\src\main\resources\mybatis /e
xcopy .\output\action	%dir%\crvci-web\src\main\java\com\crv\ci\action /e
xcopy .\output\view	    %dir%\crvci-web\src\main\webapp\WEB-INF\freemarker\views\module /e
PAUSE