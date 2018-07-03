package mx.com.web2lab.ajax.dwr.facturacion.mayoreo;

import java.util.ArrayList;
import java.util.List;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
import mx.com.web2lab.backend.dao.facturacion.electronica.orden.OrdenDatosFacturacionDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionMayoreoDao;
import mx.com.web2lab.backend.util.catalogo.ListaExamenUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MantenimientoOrdenFacturacionAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(MantenimientoOrdenFacturacionAjax.class);
	
	public MantenimientoOrdenFacturacionAjax(){
		iObjLog.debug("new: Generando nueva clase FacturacionDatosAjax");
	}	
		
	public String agregarExamen_Perfil(int intExamen,int kAdmision,int intUsuario, int cConvenio) throws Exception	
	{
		String strReturn = "";
		ListaExamenUtil objCatalogoExamenes =  new ListaExamenUtil();
		List lstExamenes = new ArrayList();
		iObjLog.debug("Entrando a MantenimientoOrdenFacturacionAjax.agregarExamen:Entrando... " + kAdmision );
		try {			  			
		 	lstExamenes = objCatalogoExamenes.cotizarProductosConvenio(String.valueOf(intExamen) , 1 , cConvenio);		 	    	
			objCatalogoExamenes = null;
			iObjLog.debug("Consulta a MantenimientoOrdenFacturacionAjax.agregarExamen:Entrando... Producto " + intExamen + " Elementos " +  lstExamenes.size() );
			FacturacionMayoreoDao objFacturacionMayoreoDao = new FacturacionMayoreoDao();
			strReturn = objFacturacionMayoreoDao.agregarExamenOrdenFacturacion(kAdmision, lstExamenes, intUsuario, cConvenio);
			objFacturacionMayoreoDao = null;
			iObjLog.debug("Saliendo a MantenimientoOrdenFacturacionAjax.agregarExamen:Saliendo... ");
		}catch (Exception aObjException){
    	    iObjLog.error("ERROR.......MantenimientoOrdenFacturacionAjax.agregarExamen:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objCatalogoExamenes = null;
			lstExamenes = null;
		}
		return strReturn;
	}	

	public String cancelarFactura(int uFolioFactura,int intUsuario,int intCopiarInformacion, int cmarca) throws Exception	
	{
		OrdenDatosFacturacionDao objOrdenDatosFacturacionDao = new OrdenDatosFacturacionDao();
		FacturaElectronicaBean objfilexmlbean = new FacturaElectronicaBean();		
		String strReturn = "";
		iObjLog.debug("Entrando a MantenimientoOrdenFacturacionAjax.cancelarFactura:Entrando... " + uFolioFactura + " usuario " + intUsuario);
		try {			  			
			objfilexmlbean.setSfolio(String.valueOf(uFolioFactura));
			objfilexmlbean.setTurbine_User(intUsuario);
			strReturn = objOrdenDatosFacturacionDao.cancelarOrdenesFactura(objfilexmlbean,intCopiarInformacion,cmarca);
			iObjLog.debug("Saliendo a MantenimientoOrdenFacturacionAjax.cancelarFactura:Saliendo... ");
		}catch (Exception aObjException){
    	    iObjLog.error("ERROR.......MantenimientoOrdenFacturacionAjax.cancelarFactura:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objOrdenDatosFacturacionDao = null;
			objfilexmlbean = null;
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
