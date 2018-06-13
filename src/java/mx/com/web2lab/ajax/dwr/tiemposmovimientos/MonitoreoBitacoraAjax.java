package mx.com.web2lab.ajax.dwr.tiemposmovimientos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.dao.tiemposmovimientos.MonitoreoBitacoraDao;
import mx.com.web2lab.backend.util.formatos.Formatos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MonitoreoBitacoraAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(MonitoreoBitacoraAjax.class);
	private Formatos objFormatos = new Formatos();
				
	public String mostrarBitacora(int cSucursal) throws Exception {	
		MonitoreoBitacoraDao objBitacoraDao = new MonitoreoBitacoraDao();
		String strReturn;
		iObjLog.debug("Entrando MonitoreoBitacoraAjax.mostrarBitacora:Entrando...cSucursal.." + cSucursal);		
		try {
			strReturn = objBitacoraDao.mostrarBitacora(cSucursal);
			iObjLog.debug("Saliendo MonitoreoBitacoraAjax.mostrarBitacora:Saliendo...  " + strReturn);
		} catch (Exception aObjException) {
    	    iObjLog.error("Error MonitoreoBitacoraAjax.mostrarBitacora:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			Object MonitoreoBitacoraDao = null;
		}
		return strReturn;
	}
	
	
	
    private double redodedo(double nD) {
		return Math.round(nD*Math.pow(10,0))/Math.pow(10,0);      	
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
