JAR=CRUDGen-0.1.jar
ENTITY=$1

javac -classpath $JAR:. Gen${ENTITY}.java

java -classpath $JAR:. Gen${ENTITY}
