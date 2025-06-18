package mx.com.web2lab.ajax.dwr.facturacion.sucursales;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.dao.facturacion.electronica.sucursales.ToolFacturacionSucursalesDao;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;

public class ToolFacturarcionSucursalesAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(ToolFacturarcionSucursalesAjax.class);
	
	public ToolFacturarcionSucursalesAjax(){
		iObjLog.debug("new: Generando nueva clase DatosOrdenFacturarAjax");
	}
	       
	public void generarFacturaGlobal(int cSucursal,int intUser) throws Exception
	{
		iObjLog.debug("Entrando a ToolFacturarcionSucursalesAjax.generarFacturaGlobal:Entrando... " + cSucursal);
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");
			ToolFacturacionSucursalesDao objToolFacturacionSucursalDAO = new ToolFacturacionSucursalesDao();
			objToolFacturacionSucursalDAO.generarFacturaGlobalSucursal(cSucursal, intUser);
			objToolFacturacionSucursalDAO = null;
			iObjLog.debug("Saliendo a ToolFacturarcionSucursalesAjax.generarFacturaGlobal:Saliendo...  ");
		}catch (TorqueException aObjException){
    	    iObjLog.error("ToolFacturarcionSucursalesAjax.generarFacturaGlobal:ErrorException....", aObjException);
    	    throw aObjException;
    	}
	}	

	public String listaFacturarOrden(int kAdmision,int intGrupo) throws Exception
	{
		iObjLog.debug("Entrando a ToolFacturarcionSucursalesAjax.listaFacturarOrden:Entrando... " + kAdmision);
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");
			ToolFacturacionSucursalesDao objToolFacturacionSucursalDAO = new ToolFacturacionSucursalesDao();
			objToolFacturacionSucursalDAO.changeEstatusOrdenes(kAdmision, 37,intGrupo);
			objToolFacturacionSucursalDAO = null;
			iObjLog.debug("Saliendo a ToolFacturarcionSucursalesAjax.listaFacturarOrden:Saliendo...  ");
			return ("LISTAFACTURAR");
		}catch (TorqueException aObjException){
    	    iObjLog.error("ToolFacturarcionSucursalesAjax.listaFacturarOrden:ErrorException....", aObjException);
    	    throw aObjException;
    	}
	}	
	
	public String retenerFacturarOrden(int kAdmision) throws Exception
	{
		iObjLog.debug("Entrando a ToolFacturarcionSucursalesAjax.retenerFacturarOrden:Entrando... " + kAdmision);
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");
			ToolFacturacionSucursalesDao objToolFacturacionSucursalDAO = new ToolFacturacionSucursalesDao();
			objToolFacturacionSucursalDAO.changeEstatusOrdenes(kAdmision, 38,0);
			objToolFacturacionSucursalDAO = null;
			iObjLog.debug("Saliendo a ToolFacturarcionSucursalesAjax.retenerFacturarOrden:Saliendo...  ");
			return ("RETENIDA");
		}catch (TorqueException aObjException){
    	    iObjLog.error("ToolFacturarcionSucursalesAjax.retenerFacturarOrden:ErrorException....", aObjException);
    	    throw aObjException;
    	}
	}	
	
	public String noFacturarOrden(int kAdmision) throws Exception
	{
		iObjLog.debug("Entrando a ToolFacturarcionSucursalesAjax.retenerFacturarOrden:Entrando... " + kAdmision);
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");
			ToolFacturacionSucursalesDao objToolFacturacionSucursalDAO = new ToolFacturacionSucursalesDao();
			objToolFacturacionSucursalDAO.changeEstatusOrdenes(kAdmision, 48,0);
			objToolFacturacionSucursalDAO = null;
			iObjLog.debug("Saliendo a ToolFacturarcionSucursalesAjax.retenerFacturarOrden:Saliendo...  ");
			return ("NoFacturar");
		}catch (TorqueException aObjException){
    	    iObjLog.error("ToolFacturarcionSucursalesAjax.retenerFacturarOrden:ErrorException....", aObjException);
    	    throw aObjException;
    	}
	}	
	
	public String getEstadoFacturarOrden(int kAdmision) throws Exception
	{
		iObjLog.debug("Entrando a ToolFacturarcionSucursalesAjax.getEstadoFacturarOrden:Entrando... " + kAdmision);
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");
			ToolFacturacionSucursalesDao objToolFacturacionSucursalDAO = new ToolFacturacionSucursalesDao();
			String strResult = objToolFacturacionSucursalDAO.getEstatusOrdenes(kAdmision);
			objToolFacturacionSucursalDAO = null;
			iObjLog.debug("Saliendo a ToolFacturarcionSucursalesAjax.getEstadoFacturarOrden:Saliendo...  " + strResult);
			if (strResult == "") {strResult = "EN SUCURSAL";}
			return strResult;
		}catch (TorqueException aObjException){
    	    iObjLog.error("ToolFacturarcionSucursalesAjax.getEstadoFacturarOrden:ErrorException....", aObjException);
    	    throw aObjException;
    	}
	}	
	
	public String changeConvenioOrdenesFac(int kAdmision,int cConvenio) throws Exception
	{
		iObjLog.debug("Entrando a ToolFacturarcionSucursalesAjax.changeConvenioOrdenesFac:Entrando... " + kAdmision + " Convenio " + cConvenio);
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");
			ToolFacturacionSucursalesDao objToolFacturacionSucursalDAO = new ToolFacturacionSucursalesDao();
			objToolFacturacionSucursalDAO.changeConvenioOrdenesFac(kAdmision, cConvenio);
			objToolFacturacionSucursalDAO = null;
			iObjLog.debug("Saliendo a ToolFacturarcionSucursalesAjax.changeConvenioOrdenesFac:Saliendo...  ");
		}catch (TorqueException aObjException){
    	    iObjLog.error("ToolFacturarcionSucursalesAjax.changeConvenioOrdenesFac:ErrorException....", aObjException);
    	    throw aObjException;
    	}
		return "Actualizacion Existosa!!!";
	}	

	public String changeExamenOrdenesFac(int kAdmision,int cExamen,int cPerfil) throws Exception
	{
		iObjLog.debug("Entrando a ToolFacturarcionSucursalesAjax.changeExamenOrdenesFac:Entrando... " + kAdmision + " Examen " + cExamen);
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");
			ToolFacturacionSucursalesDao objToolFacturacionSucursalDAO = new ToolFacturacionSucursalesDao();
			objToolFacturacionSucursalDAO.changeEstatusExamenOrdenesFac(kAdmision, cExamen, cPerfil);
			objToolFacturacionSucursalDAO = null;
			iObjLog.debug("Saliendo a ToolFacturarcionSucursalesAjax.changeExamenOrdenesFac:Saliendo...  ");
		}catch (TorqueException aObjException){
    	    iObjLog.error("ToolFacturarcionSucursalesAjax.changeExamenOrdenesFac:ErrorException....", aObjException);
    	    throw aObjException;
    	}
		return "Actualizacion Existosa!!!";
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
