package mx.com.web2lab.ajax.dwr.capturaorden;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.dao.ap.DiagnosticoDao;
import mx.com.web2lab.backend.util.beans.fundacion.BOrdenDiagFundacionBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DatosDiagnosticoAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(DatosDiagnosticoAjax.class);
	private DiagnosticoDao objDiagnosticoDAO = new DiagnosticoDao();
	
	public DatosDiagnosticoAjax(){
		iObjLog.debug("new: Generando nueva clase DatosDiagnosticoAjax");
	}

	public BOrdenDiagFundacionBean consultaDiagnostico(BOrdenDiagFundacionBean objDiagnosticoRequest) throws Exception	
	{
		iObjLog.debug("Entrando a DatosDiagnosticoAjax.consultaDiagnostico:Entrando... " + objDiagnosticoRequest.toString());
		try {
			objDiagnosticoRequest =  objDiagnosticoDAO.buscarDiagnostico(objDiagnosticoRequest);
			iObjLog.debug("Entrando a DatosDiagnosticoAjax.consultaDiagnostico:Salida... " + objDiagnosticoRequest.getObject());
			return objDiagnosticoRequest; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosDiagnosticoAjax.consultaDiagnostico:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	
		
	public BOrdenDiagFundacionBean guardaDiagnostico(BOrdenDiagFundacionBean objDiagnosticoRequest) throws Exception	
	{
		iObjLog.debug("Entrando a DatosDiagnosticoAjax.guardaDiagnostico:Entrando... " + objDiagnosticoRequest.getObject());
		try {
			objDiagnosticoRequest = objDiagnosticoDAO.actualizarDiagnostico(objDiagnosticoRequest);
			iObjLog.debug("Entrando a DatosDiagnosticoAjax.guardaDiagnostico:Salida... " + objDiagnosticoRequest.getObject());
			return objDiagnosticoRequest; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosDiagnosticoAjax.guardaDiagnostico:Exception....", aObjException);
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
