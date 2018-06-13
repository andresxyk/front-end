
# Ejecucion del MetaGenerador CRUDGen utilizando el jar de CRUDGen-0.1.jar y el
# drive de misqlcon lo cual se generan los archivos .java y .class para
# cada una de las tablas que se pasan como argumentos al momento de ejecutar la aplicacion

java -cp CRUDGen-0.1.jar:mm.mysql-2.0.7.jar mx.com.itweb.CRUDGen.MetaGenerator org.gjt.mm.mysql.Driver jdbc:mysql://localhost:3306/test it it %


# Comando java para compilar la clase GenProgramer.java la cual se genero con
# la ejecucion del programa de CRUDGen para con estos generar los archivos java
# y vn correspondientes a la tabla.

javac -classpath CRUDGen-0.1.jar:. GenProgrammer.java

# Comando java para correr la aplicacion de GenProgramer

java -classpath CRUDGen-0.1.jar:. GenProgrammer
