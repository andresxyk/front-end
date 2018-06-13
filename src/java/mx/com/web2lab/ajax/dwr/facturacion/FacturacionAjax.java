package mx.com.web2lab.ajax.dwr.facturacion;

import java.util.ArrayList;
import java.util.List;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.cotizaciones.CotizacionBean;
import mx.com.web2lab.backend.beans.facturacion.DatosAdicionalesBean;
import mx.com.web2lab.backend.dao.facturacion.empresas.viaje.ViajeFacturacionDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionPrevioDao;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FacturacionAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(FacturacionAjax.class);
	
	public FacturacionAjax(){
		iObjLog.debug("new: Generando nueva clase FacturacionDatosAjax");
	}	
		

public String setDatoAdicional(String strDatosAdicionales,int cconvenio) throws NumberFormatException, Exception{
	String strMensaje="";
	FacturacionPrevioDao objFacturacionPrevioDAODao = new FacturacionPrevioDao();
	iObjLog.debug("FacturacionDatosAjax.setDatoAdicional:Entrando..." + strDatosAdicionales);
	strMensaje=objFacturacionPrevioDAODao.persistirDatoAdicional(strDatosAdicionales,cconvenio);
	iObjLog.debug("DatosAdicionales"+strDatosAdicionales);
	
	return strMensaje;
}

public String actualizaDatoAdicional(String strDatosAdicionales,int cconvenio) throws NumberFormatException, Exception{
	String strMensaje="";
	FacturacionPrevioDao objFacturacionPrevioDAODao = new FacturacionPrevioDao();
	iObjLog.debug("FacturacionDatosAjax.setDatoAdicional:Entrando..." + strDatosAdicionales);
	strMensaje=objFacturacionPrevioDAODao.actualizaDatoAdicional(strDatosAdicionales,cconvenio);
	iObjLog.debug("DatosAdicionales"+strDatosAdicionales);
	
	return strMensaje;
}


public String setRecalculo(int cconvenio,String strbloques) throws NumberFormatException, Exception{
	String strMensaje="";
	iObjLog.debug("FacturacionAjax.setRecalculo:Entrando..." + cconvenio+" bloques"+strbloques);
	FacturacionPrevioDao objFacturacionPrevioDAODao = new FacturacionPrevioDao();
	strMensaje=objFacturacionPrevioDAODao.setRecalculo(cconvenio,this.getBloque(strbloques));
	iObjLog.debug("setRecalculo"+strMensaje);
	
	return strMensaje;
}

public String getBloque(String strcadenabloques){
	String strbloque="";
	strbloque=strcadenabloques.substring(1,(strcadenabloques.length())-1);
	iObjLog.debug("FacturacionAjax.getBloque:Entrando..." + strbloque);
	return strbloque;
	
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
