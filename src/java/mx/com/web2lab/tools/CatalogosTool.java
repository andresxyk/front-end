package mx.com.web2lab.tools;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import mx.com.web2lab.util.Formatos;
import mx.com.web2lab.util.GenericDAO;
import mx.com.web2lab.backend.util.beans.comercializacion.CatalogoDistinctBean;
import mx.com.web2lab.backend.beans.facturacion.empresas.viaje.ViajeFacturacion;
import mx.com.web2lab.backend.interfaz.MCatalogosTool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.pull.ApplicationTool;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.pool.Recyclable;

public class CatalogosTool implements Recyclable, ApplicationTool, Serializable
{
	/**log de la aplicaci&oacute;n */
	private static Log iObjLog = LogFactory.getLog(CatalogosTool.class);	
	/** The object containing request specific data */
	private RunData iObjDatos;
	/** For Dispose managing */
	private boolean iBolDisposed;
	
	
	/** Conctructor Default*/
	public CatalogosTool()
	{
		
	}
	
	/**
	* Comment
	* @param data comment
	*/
	public void init(Object aObjDatos)
	{
		this.iObjDatos = (RunData)aObjDatos;
	}
	/**
	* nulls out the issue and user objects
	*/
	public void refresh()
	{
	   // do not need since it is a request tool
	}
	/**
	* Recycles the object for a new client. Recycle methods with
	* parameters must be added to implementing object and they will be
	* automatically called by pool implementations when the object is
	* taken from the pool for a new client. The parameters must
	* correspond to the parameters of the constructors of the object.
	* For new objects, constructors can call their corresponding recycle
	* methods whenever applicable.
	* The recycle methods must call their super.
	*/
	public void recycle(){
	   iBolDisposed = false;
	}
	/**
	* Disposes the object after use. The method is called
	* when the object is returned to its pool.
	* The dispose method must call its super.
	*/
	public void dispose(){
		iObjDatos = null;
		iBolDisposed = true;
	}
	/**
	* Checks whether the recyclable has been disposed.
	* @return true, if the recyclable is disposed.
	*/
	public boolean isDisposed()
	{
		   return iBolDisposed;
	}
	
	
	public List obtenAll(String strSucursal,String cMarca) throws Exception
	{
        iObjLog.debug("Entrando a obtenAll -" + strSucursal);
		MCatalogosTool objCatalogo = new MCatalogosTool();
		return objCatalogo.obtenAll(strSucursal,Integer.parseInt(cMarca));
	}

	public List obtenAllArtificial(String strCatalogo,String cMarca) throws Exception
	{
        iObjLog.debug("Entrando a obtenAll " + strCatalogo);
		MCatalogosTool objCatalogo = new MCatalogosTool();
		return objCatalogo.obtenAllArtificial(strCatalogo,Integer.parseInt(cMarca));
	}

	
	public List obtieneConvenio(String kClienteComercial) throws Exception
	{
        iObjLog.debug("Entrando a obtieneConvenioIII " + kClienteComercial);		
		List lstReturn = new ArrayList();
		Connection objConexion = null;
		Statement objStatement = null;
		ResultSet rstConvenios = null;
		String strSQL = "";
		try {
			GenericDAO objConn = new GenericDAO();
			objConexion = objConn.getConnection();
			objStatement = objConexion.createStatement();
			strSQL = "SELECT DISTINCT dcc.kClienteComercial,						\n" +
					 "						  dcc.cConvenio,						\n" +
					 "						  dcc.sConvenio,						\n" +
					 "							 se.sestado 						\n" +
					 "FROM dconveniocomercial dcc INNER JOIN sestado se 			\n" +
					 "						  on dcc.cestado= se.cestado 			\n" +
					 "				          and se.centidad IN (38) 				\n" +
					 "WHERE dcc.kClienteComercial in (" + kClienteComercial + ")    \n" +
					 "ORDER BY dcc.cConvenio 										\n";
	        iObjLog.debug("Consultando a obtieneConvenio " + strSQL);
			rstConvenios = objStatement.executeQuery(strSQL);
			CatalogoDistinctBean objConvenio = null;
			while(rstConvenios.next()) {
				objConvenio = new CatalogoDistinctBean();
				objConvenio.setKdistinct(rstConvenios.getInt("kClienteComercial"));
				iObjLog.debug("Consultando kClienteComercial " + objConvenio.getKdistinct());
				objConvenio.setCdistinct(rstConvenios.getInt("cConvenio"));
				iObjLog.debug("Consultando cConvenio " + objConvenio.getCdistinct());
				objConvenio.setSdistinct(rstConvenios.getString("sConvenio") + ' ' + rstConvenios.getString("sestado"));
				iObjLog.debug("Consultando sConvenio " + objConvenio.getSdistinct());
				lstReturn.add(objConvenio);
			}
	        iObjLog.debug("Saliendo a obtieneConvenio " + lstReturn.size());
		} catch (Exception exp) {
			throw exp;
		} finally{
	 		if(rstConvenios!=null)rstConvenios.close();
	 		if(objStatement!=null)objStatement.close();			
	 		if(objConexion!=null)objConexion.close();
	 	}
		return lstReturn;
	}

	public List viajesFacturacion() throws Exception
	{
		List lstReturn = new ArrayList();
		Connection objConexion = null;
		Statement objStatement = null;
		ResultSet rst = null;
		ViajeFacturacion objViajeFac = null;
		String strSQL = "";
		try {
			int cSucursal = Integer.parseInt((String)iObjDatos.getSession().getAttribute("idgrupo"));			
	        iObjLog.debug("Entrando a obtieneViajesFacturacion " + cSucursal);		
			GenericDAO objConn = new GenericDAO();
			objConexion = objConn.getConnection();
			objStatement = objConexion.createStatement();
			strSQL = "SELECT DISTINCT KVIAJEFAC viaje									\n" +
					 "FROM T_ORDEN_SUCURSAL_FAC 										\n" +
					 "WHERE CSUCURSAL IN (" + cSucursal + ") AND CESTADOREGISTRO = 28	\n" +
					 "ORDER BY KVIAJEFAC";
	        iObjLog.debug("Consultando a obtieneViajesFacturacion " + strSQL);
	        rst = objStatement.executeQuery(strSQL);
			while(rst.next()) {
				objViajeFac = new ViajeFacturacion();
				objViajeFac.setCsucursal(cSucursal);
				objViajeFac.setKviajefacturacion(rst.getInt("viaje"));
				lstReturn.add(objViajeFac);
			}
	        iObjLog.debug("Saliendo a obtieneViajesFacturacion " + lstReturn.size());
		} catch (Exception exp) {
			throw exp;
		} finally{
	 		if(rst!=null)rst.close();
	 		if(objStatement!=null)objStatement.close();			
	 		if(objConexion!=null)objConexion.close();
	 	}
		return lstReturn;
	}
	
	/**
	 * Este metodo regresa la fecha de hoy en la forma dd-MMM-yyyy
	 * algo como 12-JUN-2010
	 * @return strFechaValida
	 */
	public String getFecha() throws Exception{
		return new Formatos().getFecha(new Date());
	}	
	
	public List obtenAllField(String strCatalogo,String strField) throws Exception
	{
        iObjLog.debug("Entrando a obtenAllField " + strCatalogo);
		MCatalogosTool objCatalogo = new MCatalogosTool();
		List lstReturn = objCatalogo.obtenAllField(strCatalogo,strField);
        iObjLog.debug("Saliendo a obtenAllField " + strCatalogo + " " + lstReturn.size());
		return lstReturn;
	}	
}
