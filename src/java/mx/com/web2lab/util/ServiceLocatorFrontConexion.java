package mx.com.web2lab.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;

public class ServiceLocatorFrontConexion {

	/** Variable que envia mensajes al log */
	private static Log iObjLog = LogFactory.getLog(ServiceLocatorFrontConexion.class);
	
	private static Map cache;
	private static ServiceLocatorFrontConexion ourInstance = new ServiceLocatorFrontConexion();

	public static ServiceLocatorFrontConexion getInstance() 
	{ 
		return ourInstance; 
	}

	private ServiceLocatorFrontConexion()  
	{	
		try { 
			this.cache = Collections.synchronizedMap(new HashMap());	
		} catch (Exception exp) {
	 		iObjLog.error("ServiceLocatorFrontConexion::ServiceLocatorFrontConexion:Exception: ", exp);
		}
	}


	public DataSource getDataSource(String dataSourceName) throws Exception,NamingException
	{
		DataSource datasource = null;  
		try {	
			if (this.cache.containsKey(dataSourceName)) 
			{
				datasource = (DataSource) this.cache.get(dataSourceName);
			} else {  
			    InitialContext initialContext = new InitialContext();
				datasource = (DataSource) initialContext.lookup(dataSourceName);
				this.cache.put(dataSourceName, datasource);  
			}	
			
		} catch (NamingException ex) {  
	 		iObjLog.error("ServiceLocatorFrontConexion::getDataSource:NamingException: ", ex);
	 		throw ex;
		} catch (Exception exp) {
	 		iObjLog.error("ServiceLocatorFrontConexion::getDataSource:Exception: ", exp);
	 		throw exp;
		}
		return datasource;
	}
}

