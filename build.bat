SET JAVA_HOME=C:\Users\soporte\Documents\GDA\j2sdk1.4.2_19
SET PATH=C:\Users\soporte\Documents\GDA\j2sdk1.4.2_19\bin;%PATH%
java -version

SET MAVEN_HOME=C:\Users\soporte\Documents\GDA\maven
SET PATH=C:\Users\soporte\Documents\GDA\maven\bin;%PATH%

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
CD back-end && maven ejb:install 
GOTO END

:FRONT-WAR
CD front-end && maven war:install
GOTO END

:FRONT-BACK
CD back-end && maven ejb:install && CD ..\front-end && maven war:install
GOTO END

:BACK-CLEAN 
CD back-end && maven clean
GOTO END

:FRONT-CLEAN
CD front-end && maven clean
GOTO END

:ALL-CLEAN
CD back-end && maven clean && CD ..\front-end && maven clean && CD ..\back-end\construccion_ear && maven clean 
GOTO END

:BUILD-EAR
CD back-end\construccion_ear && maven ear
GOTO END

:EAR-CLEAN
CD back-end\construccion_ear && maven clean
GOTO END

:BUILD-ALL
CD back-end && maven ejb:install && CD ..\front-end && maven war:install && CD ..\back-end\construccion_ear && maven ear
GOTO END

:END