package mx.com.web2lab.ajax.dwr.capturaorden.mayoreo;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import mx.com.web2lab.backend.beans.ap.PacienteBean;
import mx.com.web2lab.backend.dao.ap.PacientesDao;
import mx.com.web2lab.backend.dao.ap.mayoreo.PacientesMayoreoDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PacienteMayoreoAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(PacienteMayoreoAjax.class);
	
	public PacienteBean buscarPacienteMetro(String strExpediente) throws Exception
	{
		PacienteBean objPacienteBean = null;
		iObjLog.debug("Entrando PacienteMayoreoAjax.buscarPacienteMetro:Entrando... " + strExpediente);		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
			PacientesMayoreoDao objDAOPacienteMayoreo = new PacientesMayoreoDao();
			objPacienteBean = new PacienteBean(strExpediente.trim());
			objPacienteBean = objDAOPacienteMayoreo.buscarPacienteMetro(objPacienteBean);
			objDAOPacienteMayoreo = null;
			if (objPacienteBean.getKpacientefundacion().intValue() > 0) {
				PacientesDao objDAOPaciente = new PacientesDao();
				objPacienteBean = objDAOPaciente.buscarPaciente(objPacienteBean);
				objDAOPaciente = null;
			}
			iObjLog.debug("Saliendo PacienteMayoreoAjax.buscarPacienteMetro:Saliendo...  " + objPacienteBean.getKpacientefundacion());
    	}catch (Exception aObjException){
    	    iObjLog.error("Error PacienteMayoreoAjax.buscarPacienteMetro:Exception....", aObjException);
    	    throw aObjException;
    	}
		return objPacienteBean;
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
