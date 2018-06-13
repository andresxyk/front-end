package mx.com.web2lab.ajax.dwr.capturaorden;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.dao.ap.DatosReportesDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DatosReportesAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(DatosReportesAjax.class);
	    
    public String createFileFOPOrden(int kOrdenSucursal) throws Exception	
	{
    	DatosReportesDao objDatosReportesDao = new DatosReportesDao();
        try {
	    	iObjLog.debug("Entrando a DatosReportesAjax.createFileFOPOrden:Entrando...Parametros... " + kOrdenSucursal);	    	
			return objDatosReportesDao.createFOPOrden(kOrdenSucursal);
		} catch (Exception aObjException){
    	    iObjLog.error("DatosReportesAjax.createFileFOPOrden:Exception....", aObjException);
    	    throw aObjException;
		} 
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
