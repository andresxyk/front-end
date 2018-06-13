package mx.com.web2lab.ajax.dwr.http;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

public class AjaxAction implements Serializable {
	/** atributo privado que contiene el WebContext */
	private WebContext ctx = null;
	/** atributo privado que contiene el HttpServletRequest */
	private HttpServletRequest req = null;
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(AjaxAction.class);
	/**
	 * Metodo encargado de recuperar el request 
	 * (HttpServletRequest) a partir del 
	 * WebContextFactory.  
	 * @return
	 */
	public HttpServletRequest getWebContext() {
		iObjLog.debug("getWebContext:WebContext actual="+ctx);
		if(ctx==null){
			ctx = WebContextFactory.get();
			iObjLog.debug("getWebContext:Se obtiene WebContext = " + ctx);
		}
		if(req==null && ctx!=null){
			req = ctx.getHttpServletRequest();
			iObjLog.debug("getWebContext:Se obtiene HttpServletRequest = " + req);
		}
		iObjLog.debug("getWebContext:Regresa HttpServletRequest = " + req);
		return req;
	}
	
	/**
	 * Metodo encargado de verificar si el usuario 
	 * tiene una sesion valida 
	 * 
	 * @return
	 */
	public HttpSession getSesionValida() {
		if(req==null) getWebContext();
		HttpSession ses = req.getSession();
		iObjLog.debug("getValidSession:Sesion Obtenida = " + ses);
		if(ses==null) { 
			return null;
		}
		iObjLog.debug("getValidSession:Regresa Sesion = " + ses);
		return ses;
	}
	
	/**
	 * Metodo que verifica si existe una sesion valida
	 * para el usuario que esta firmado
	 * @return
	 */
	public boolean isSesionValida(boolean bolValidar){
		boolean valida = false;
		if (bolValidar) {
			HttpSession ses = getSesionValida(); 
			if(ses!=null){
				if(!ses.isNew() && ses.getAttribute("username")!=null){
					iObjLog.debug("isSesionValida:sesion valida para el usuario = " + ses.getAttribute("username"));
					valida = true;
				}
			}
			iObjLog.debug("isSesionValida:regresando = " + valida + " (" + ses.getAttribute("username")+")");
			return valida;
		} else {
			return true;
		}
	}
	
	public Object getAttribute(String sAttributeName){
		iObjLog.debug("getAttribute:BUSCANDO = " + sAttributeName);
		Object ret = null;
		HttpSession ses = getSesionValida(); 
		if(ses!=null){
			ret = ses.getAttribute(sAttributeName);
		}
		iObjLog.debug("getAttribute: = " + sAttributeName + ", valor = " + ret);
		return ret;
	}
}
