package mx.com.web2lab.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class GenericDAO 
{
	/** Variable que envia mensajes al log */
	private static Log iObjLog = LogFactory.getLog(GenericDAO.class);

	private Connection con;
	private DataSource dataSource;
	
    public Connection getConnection() throws Exception
    {
        try {
        	this.dataSource = ServiceLocatorFrontConexion.getInstance().getDataSource("java:jdbc/Web2LabProduccion");
        	con = dataSource.getConnection();
        } catch (Exception exp) {
	 		iObjLog.error("GenericDAO::getConnection: ", exp);
	 		throw exp;
        }
        return con;
    }
}