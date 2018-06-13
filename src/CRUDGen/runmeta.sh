PATTERN=$1
java -cp CRUDGen-0.1.jar:mm.mysql-2.0.7.jar mx.com.itweb.CRUDGen.MetaGenerator org.gjt.mm.mysql.Driver jdbc:mysql://localhost:3306/controlpub it it $PATTERN

