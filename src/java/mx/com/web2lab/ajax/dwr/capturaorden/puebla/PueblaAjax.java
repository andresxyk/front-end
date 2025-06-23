package mx.com.web2lab.ajax.dwr.capturaorden.puebla;

import mx.com.web2lab.ajax.dwr.capturaorden.puebla.fop.cuestionario.CuestionarioPDF;
import mx.com.web2lab.ajax.dwr.capturaorden.puebla.fop.formatopuebla.FormatoPDF;
import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.puebla.CuestionarioPantallaBean;
import mx.com.web2lab.backend.beans.puebla.CuestionarioPantallaInterpretacionBean;
import mx.com.web2lab.backend.dao.puebla.PueblaDao;
import mx.com.web2lab.backend.dao.puebla.PueblaInterpretacionDao;
import mx.com.web2lab.backend.util.Formatos;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PueblaAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(PueblaAjax.class);
	
	public PueblaAjax(){
		iObjLog.debug("new: Generando nueva clase FacturacionDatosAjax");
	}
	
	public CuestionarioPantallaBean persistirCuestionario(CuestionarioPantallaBean objCuestionarioPantallaBean) throws Exception {
		PueblaDao objPueblaDao = new PueblaDao();
		iObjLog.debug("Entrando PueblaAjax.persistirCuestionario:Entrando... " + objCuestionarioPantallaBean.getkPaciente());		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
			objCuestionarioPantallaBean = objPueblaDao.persistirCuestionario(objCuestionarioPantallaBean);
			iObjLog.debug("Saliendo PueblaAjax.persistirCuestionario:Saliendo...  " +objCuestionarioPantallaBean.getKcuestionariopacientepuebla());
    	} catch (Exception aObjException) {
    	    iObjLog.error("Error PueblaAjax.persistirCuestionario:Exception....", aObjException);
    	    throw aObjException;
    	}
		return objCuestionarioPantallaBean;
	}	

	public CuestionarioPantallaBean buscarCuestionario(CuestionarioPantallaBean objCuestionarioPantallaBean) throws Exception {
		PueblaDao objPueblaDao = new PueblaDao();
		iObjLog.debug("Entrando PueblaAjax.buscarCuestionario:Entrando... " + objCuestionarioPantallaBean.getkPaciente());		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
			objCuestionarioPantallaBean = objPueblaDao.buscarCuestionario(objCuestionarioPantallaBean);
			iObjLog.debug("Saliendo PueblaAjax.buscarCuestionario:Saliendo...  " +objCuestionarioPantallaBean.getKcuestionariopacientepuebla());
    	} catch (Exception aObjException) {
    	    iObjLog.error("Error PueblaAjax.buscarCuestionario:Exception....", aObjException);
    	    throw aObjException;
    	}
		return objCuestionarioPantallaBean;
	}	

	public CuestionarioPantallaBean imprimirCuestionario(CuestionarioPantallaBean objCuestionarioPantallaBean) throws Exception {
		PueblaDao objPueblaDao = new PueblaDao();
		CuestionarioPDF objPrincipalCuestionario = new CuestionarioPDF();
		iObjLog.debug("Entrando PueblaAjax.imprimirCuestionario:Entrando... " + objCuestionarioPantallaBean.getkPaciente());		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
			objCuestionarioPantallaBean = objPueblaDao.imprimirCuestionario(objCuestionarioPantallaBean);
			objCuestionarioPantallaBean.setSmensajeoperacion(objPrincipalCuestionario.imprimirCuestionario(objCuestionarioPantallaBean));
			iObjLog.debug("Saliendo PueblaAjax.imprimirCuestionario:Saliendo...  " +objCuestionarioPantallaBean.getKcuestionariopacientepuebla());
    	} catch (Exception aObjException) {
    	    iObjLog.error("Error PueblaAjax.imprimirCuestionario:Exception....", aObjException);
    	    throw aObjException;
    	}
		return objCuestionarioPantallaBean;
	}
	
	public CuestionarioPantallaInterpretacionBean persistirFormato(CuestionarioPantallaInterpretacionBean objCuestionarioPantallaInterpretacionBean) throws Exception {
		PueblaInterpretacionDao objPueblaInterpretacionDao = new PueblaInterpretacionDao();
		iObjLog.debug("Entrando PueblaAjax.persistirFormato:Entrando... " + objCuestionarioPantallaInterpretacionBean.getKordensucursal());		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
			 iObjLog.debug("Entrando PueblaAjax.persistirFormato:Entrando... "+objCuestionarioPantallaInterpretacionBean.getUmastografiaadecuada());
			 
			 if(objCuestionarioPantallaInterpretacionBean.getSfechaultimamastografia().equals(""))
				{   iObjLog.debug("En blanco getSfechaultimamastografia");
					objCuestionarioPantallaInterpretacionBean.setSfechaultimamastografia("16-SEP-1981");
				} 
			if(objCuestionarioPantallaInterpretacionBean.getSfechatomamastografia().equals(""))
			{   iObjLog.debug("En blanco getSfechatomamastografia");
				objCuestionarioPantallaInterpretacionBean.setSfechatomamastografia("16-SEP-1981");
			}
			if(objCuestionarioPantallaInterpretacionBean.getSfechainterpretacionmastografia().equals("")){
				 iObjLog.debug("En blanco getSfechainterpretacionmastografia");
				objCuestionarioPantallaInterpretacionBean.setSfechainterpretacionmastografia("16-SEP-1981");
			}
			if(objCuestionarioPantallaInterpretacionBean.getSfechainformeresultado().equals("")){
				 iObjLog.debug("En blanco getSfechainformeresultado");
				objCuestionarioPantallaInterpretacionBean.setSfechainformeresultado("16-SEP-1981");
			}
			if(objCuestionarioPantallaInterpretacionBean.getSfechareferencia().equals("")){
				 iObjLog.debug("En blanco getSfechareferencia");
				objCuestionarioPantallaInterpretacionBean.setSfechareferencia("16-SEP-1981");
			}
			objCuestionarioPantallaInterpretacionBean.setDfechaultimamastografia(new Formatos().getFecha(objCuestionarioPantallaInterpretacionBean.getSfechaultimamastografia()));
			objCuestionarioPantallaInterpretacionBean.setDfechatomamastografia(new Formatos().getFecha(objCuestionarioPantallaInterpretacionBean.getSfechatomamastografia()));
			objCuestionarioPantallaInterpretacionBean.setDfechainterpretacionmastografia(new Formatos().getFecha(objCuestionarioPantallaInterpretacionBean.getSfechainterpretacionmastografia()));
			objCuestionarioPantallaInterpretacionBean.setDfechainformeresultado(new Formatos().getFecha(objCuestionarioPantallaInterpretacionBean.getSfechainformeresultado()));
			objCuestionarioPantallaInterpretacionBean.setDfechareferencia(new Formatos().getFecha(objCuestionarioPantallaInterpretacionBean.getSfechareferencia()));
			objCuestionarioPantallaInterpretacionBean = objPueblaInterpretacionDao.persistirInterpretacion(objCuestionarioPantallaInterpretacionBean);
			
			if(objCuestionarioPantallaInterpretacionBean.getDfechaultimamastografia().getYear()==81)
				objCuestionarioPantallaInterpretacionBean.setSfechaultimamastografia("");
			if(objCuestionarioPantallaInterpretacionBean.getDfechatomamastografia().getYear()==81)
				objCuestionarioPantallaInterpretacionBean.setSfechatomamastografia("");
			if(objCuestionarioPantallaInterpretacionBean.getDfechainterpretacionmastografia().getYear()==81)
				objCuestionarioPantallaInterpretacionBean.setSfechainterpretacionmastografia("");
			if(objCuestionarioPantallaInterpretacionBean.getDfechainformeresultado().getYear()==81)
				objCuestionarioPantallaInterpretacionBean.setSfechainformeresultado("");
			if(objCuestionarioPantallaInterpretacionBean.getDfechareferencia().getYear()==81)
				objCuestionarioPantallaInterpretacionBean.setSfechareferencia("");
			
				iObjLog.debug("Saliendo PueblaAjax.persistirFormato:Saliendo...  " +objCuestionarioPantallaInterpretacionBean.getKcuestionariopacienteinterpretacionpuebla());
    	} catch (Exception aObjException) {
    	    iObjLog.error("Error PueblaAjax.persistirFormato:Exception....", aObjException);
    	    throw aObjException;
    	}
		return objCuestionarioPantallaInterpretacionBean;
	}
	
	public CuestionarioPantallaInterpretacionBean buscarFormato(CuestionarioPantallaInterpretacionBean objCuestionarioPantallaInterpretacionBean) throws Exception {
		PueblaInterpretacionDao objPueblaInterpretacionDao = new PueblaInterpretacionDao();
		iObjLog.debug("Entrando PueblaAjax.buscarFormato:Entrando... " + objCuestionarioPantallaInterpretacionBean.getKordensucursal());		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
			objCuestionarioPantallaInterpretacionBean = objPueblaInterpretacionDao.buscarInterpretacion(objCuestionarioPantallaInterpretacionBean);
			iObjLog.debug("Saliendo PueblaAjax.buscarFormato:Saliendo...  " +objCuestionarioPantallaInterpretacionBean.getKcuestionariopacienteinterpretacionpuebla());
    	} catch (Exception aObjException) {
    	    iObjLog.error("Error PueblaAjax.buscarCuestionario:Exception....", aObjException);
    	    throw aObjException;
    	}
		return objCuestionarioPantallaInterpretacionBean;
	}	
	
	public CuestionarioPantallaInterpretacionBean imprimirFormato(CuestionarioPantallaInterpretacionBean objCuestionarioPantallaInterpretacionBean) throws Exception {
		PueblaInterpretacionDao objPueblaInterpretacionDao = new PueblaInterpretacionDao();
		FormatoPDF objPrincipalInterpretacion = new FormatoPDF();
		iObjLog.debug("Entrando PueblaAjax.imprimirFormato:Entrando... " + objCuestionarioPantallaInterpretacionBean.getKordensucursal());		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
			objCuestionarioPantallaInterpretacionBean = objPueblaInterpretacionDao.imprimirInterpretacion(objCuestionarioPantallaInterpretacionBean);
			objCuestionarioPantallaInterpretacionBean.setSmensajeoperacion(objPrincipalInterpretacion.imprimirFormato(objCuestionarioPantallaInterpretacionBean));
			iObjLog.debug("Saliendo PueblaAjax.imprimirFormato:Saliendo...  " +objCuestionarioPantallaInterpretacionBean.getKcuestionariopacienteinterpretacionpuebla());
    	} catch (Exception aObjException) {
    	    iObjLog.error("Error PueblaAjax.imprimirFormato:Exception....", aObjException);
    	    throw aObjException;
    	}
		return objCuestionarioPantallaInterpretacionBean;
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
