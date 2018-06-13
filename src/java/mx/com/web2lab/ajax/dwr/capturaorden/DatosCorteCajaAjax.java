package mx.com.web2lab.ajax.dwr.capturaorden;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;

import mx.com.web2lab.backend.dao.ap.ToolsDao;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DatosCorteCajaAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(DatosCorteCajaAjax.class);
 	private Date objDate = new Date();
	
	public DatosCorteCajaAjax(){
	}

	
	public String guardarGasto(double dblMontoGasto,String strDescripcionGasto,int intUsuario) throws Exception
	{
		iObjLog.debug("Entrando DatosCorteCajaAjax.guardarGasto:Entrando... ");		
		String strReturn = "";
		try {			
			ToolsDao objDAOGasto = new ToolsDao();
			strReturn = objDAOGasto.guardarGasto(dblMontoGasto, strDescripcionGasto,intUsuario) ;
			iObjLog.debug("Saliendo DatosCorteCajaAjax.guardarGasto:Saliendo...  ");
		} catch (Exception aObjException){
			iObjLog.error("Error DatosCorteCajaAjax.guardarGasto:Exception....", aObjException);
			throw aObjException;
		}
		return strReturn;
	}	
	
	/**
     * Metodo que verifica que exista una sesion valida 
     * @return
     * @throws Exception
     */
    public boolean isSesionValida() {
    	boolean valida = false;
    	try{
    		valida = super.isSesionValida(true);
    	}catch(Exception e ){
    		iObjLog.error("isSesionValida:No existe una sesion valida para el usuario");
    		return valida;
    	}
    	return valida;
    }
    
}
