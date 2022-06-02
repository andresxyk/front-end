SET JAVA_HOME=C:\Java\j2sdk1.4.2_19
SET PATH=C:\Java\j2sdk1.4.2_19\bin;%PATH%
java -version

SET MAVEN_HOME=C:\ToolDeveloperJava\LibreriasJava\maven
SET PATH=C:\ToolDeveloperJava\LibreriasJava\maven\bin;%PATH%

CD .. 
IF "%1" == "back" GOTO BACK-INSTALL
IF "%1" == "front" GOTO FRONT-WAR
IF "%1" == "both" GOTO FRONT-BACK
IF "%1" == "bcl" GOTO BACK-CLEAN
IF "%1" == "fcl" GOTO FRONT-CLEAN
IF "%1" == "ecl" GOTO EAR-CLEAN
IF "%1" == "acl" GOTO ALL-CLEAN
IF "%1" == "ear" GOTO BUILD-EAR
IF "%1" == "all" GOTO BUILD-ALL

:BACK-INSTALL
CD facturacion-empresa-backend && maven ejb:install 
GOTO END

:FRONT-WAR
CD facturacion-empresa-front && maven war:install
GOTO END

:FRONT-BACK
CD facturacion-empresa-backend && maven ejb:install && CD ..\facturacion-empresa-front && maven war:install
GOTO END

:BACK-CLEAN 
CD facturacion-empresa-backend && maven clean
GOTO END

:FRONT-CLEAN
CD facturacion-empresa-front && maven clean
GOTO END

:ALL-CLEAN
CD facturacion-empresa-backend && maven clean && CD ..\facturacion-empresa-front && maven clean && CD ..\facturacion-empresa-backend\construccion_ear && maven clean 
GOTO END

:BUILD-EAR
CD facturacion-empresa-backend\construccion_ear && maven ear
GOTO END

:EAR-CLEAN
CD facturacion-empresa-backend\construccion_ear && maven clean
GOTO END

:BUILD-ALL
CD facturacion-empresa-backend && maven ejb:install && CD ..\facturacion-empresa-front && maven war:install && CD ..\facturacion-empresa-backend\construccion_ear && maven ear
GOTO END

:END