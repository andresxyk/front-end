package mx.com.web2lab.ajax.dwr.seguridad;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;


/**
 *
 * Administra las autorizaciones de un usuario
 */
public class SeguridadAjax extends AjaxAction {
    
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(SeguridadAjax.class);
	
	public SeguridadAjax(){
		//Constructor redefinido para generar entradas a bitacora
		iObjLog.debug("new: Generando nueva clase SeguridadAjax...");
	}
	
	public String validaSesion() throws AjaxDwrException{
    	String regresa = "";
    	try{
    		HttpSession ses = this.getSesionValida();
    		iObjLog.debug("validaSesion:se estan leyendo los atributos de sesion = " + ses);
    		if(ses!=null){
    			iObjLog.debug("validaSesion:es nueva: " + ses.isNew());
    			Enumeration enumera = ses.getAttributeNames();
    			while(enumera.hasMoreElements()){
    				Object obj = enumera.nextElement();
    				String sNombre = (String)obj; 
    				regresa += "Atributo=" + sNombre + ", valor=" + ses.getAttribute(sNombre) + "<br>";
    				iObjLog.debug("validaSesion:Atributo=" + obj+ ", valor= " + ses.getAttribute(sNombre));
    			}
    		}
    	}catch(Exception e){
    		iObjLog.error("Ocurrio un error al validar la sesion...", e);
			if(e instanceof AjaxDwrException){
				throw new AjaxDwrException(e);
			}else{
				throw new AjaxDwrException(0,"Ocurrio un error al validar la sesion: " + e.toString());
			}
    	}
    	return regresa;
    }
    
	public User getUser(String username) throws AjaxDwrException{
		User objUser = null;
    	try{
    		if(!isSesionValida(true))throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");    		
    		objUser = TurbineSecurity.getUser(username);
    	} catch(Exception e) {
			iObjLog.error("SeguridadAjax.getUser:ERROR",e);
			if(e instanceof AjaxDwrException){
				throw new AjaxDwrException(e);
			}else{
				throw new AjaxDwrException(0,"SeguridadAjax.getUser:Ocurri&oacute; un error al validar el usuario ..." + username);
			}
    	}
    	return objUser;
    }
}

