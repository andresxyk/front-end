package mx.com.web2lab.ajax.dwr.capturaorden.medicos.puntos;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.dao.comer.medicos.puntos.MedicosPuntosDao;
import mx.com.web2lab.backend.hbm.om.ap.medico.puntos.CRegalo;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MedicosPuntosAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(MedicosPuntosAjax.class);
	
	public MedicosPuntosAjax(){
		iObjLog.debug("new: Generando nueva clase FacturacionDatosAjax");
	}
	
	public CRegalo altRegaloMedicos(CRegalo objRegalo) throws Exception
	{
		MedicosPuntosDao objMedicosPuntosDao = new MedicosPuntosDao();
		iObjLog.debug("Entrando DatosMedicoAjaxMedicosPuntosAjax.altRegaloMedicos:Entrando... " + objRegalo.getSregalo());		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
			objRegalo = objMedicosPuntosDao.altaRegalosMedicos(objRegalo);
			iObjLog.debug("Saliendo MedicosPuntosAjaxDatosMedicoAjax.altRegaloMedicos:Saliendo...  " +objRegalo.getCregalo().intValue());
    	}catch (Exception aObjException){
    	    iObjLog.error("Error MedicosPuntosAjax.altRegaloMedicos:Exception....", aObjException);
    	    throw aObjException;
    	}
		return objRegalo;
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
