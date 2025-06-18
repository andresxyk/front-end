package mx.com.web2lab.ajax.dwr.laboratorio;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.configuracion.ExamenConfiguracionBean;
import mx.com.web2lab.backend.dao.laboratorio.ConfiguracionExamenDao;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;

public class ConfiguracionExamenAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(ConfiguracionExamenAjax.class);
	
	public ConfiguracionExamenAjax(){
		iObjLog.debug("new: Generando nueva clase DatosFiscalesAjax");
	}
	       
	public String showGrid(ExamenConfiguracionBean objExamenConfiguracionBean) throws Exception
	{
		ConfiguracionExamenDao objConfiguracionExamenDao = new ConfiguracionExamenDao();
		String strReturn = "";
		iObjLog.debug("Entrando a ConfiguracionExamenAjax.showGrid:Entrando...  ");
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");
			strReturn = objConfiguracionExamenDao.showGrid(objExamenConfiguracionBean);
			iObjLog.debug("Saliendo a ConfiguracionExamenAjax.showGrid:Saliendo...  ");
		}catch (TorqueException aObjException){
    	    iObjLog.error("ConfiguracionExamenAjax.showGrid:ErrorException....", aObjException);
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


