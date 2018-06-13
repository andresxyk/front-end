package mx.com.web2lab.ajax.dwr.capturaorden;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.dao.ap.CuestionarioDao;
import mx.com.web2lab.backend.util.beans.fundacion.BPacienteCuestionarioBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DatosCuestionarioAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(DatosCuestionarioAjax.class);
	private CuestionarioDao objCuestionarioDAO = new CuestionarioDao();
	
	public DatosCuestionarioAjax(){
		iObjLog.debug("new: Generando nueva clase DatosCuestionarioAjax");
	}

	public BPacienteCuestionarioBean consultaCuestionario(BPacienteCuestionarioBean objCuestionarioRequest) throws Exception	
	{
		iObjLog.debug("Entrando a DatosCuestionarioAjax.consultaCuestionario:Entrando... " + objCuestionarioRequest.toString());
		try {
			objCuestionarioRequest =  objCuestionarioDAO.buscarPaciente(objCuestionarioRequest);
			iObjLog.debug("Entrando a DatosCuestionarioAjax.consultaCuestionario:Salida... " + objCuestionarioRequest.getObject());
			return objCuestionarioRequest; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosCuestionarioAjax.consultaCuestionario:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	
	
	
	public BPacienteCuestionarioBean guardaCuestionario(BPacienteCuestionarioBean objCuestionarioRequest) throws Exception	
	{
		iObjLog.debug("Entrando a DatosCuestionarioAjax.guardaCuestionario:Entrando... " + objCuestionarioRequest.getObject());
		try {
			objCuestionarioRequest = objCuestionarioDAO.setCuestionarioActualizacion(objCuestionarioRequest);
			iObjLog.debug("Entrando a DatosCuestionarioAjax.guardaCuestionario:Salida... " + objCuestionarioRequest.getObject());
			return objCuestionarioRequest; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosCuestionarioAjax.guardaCuestionario:Exception....", aObjException);
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
