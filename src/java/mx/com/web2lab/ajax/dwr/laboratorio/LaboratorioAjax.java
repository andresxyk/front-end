package mx.com.web2lab.ajax.dwr.laboratorio;

import java.util.List;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.tools.ConvertBeanvsHB;
import mx.com.web2lab.backend.dao.ap.DatosOrdenDao;
import mx.com.web2lab.backend.dao.ap.ExamenesDao;
import mx.com.web2lab.backend.dao.facturacion.empresas.viaje.ViajeFacturacionDao;
import mx.com.web2lab.backend.hbm.om.ap.TOrdenSucursal;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LaboratorioAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(LaboratorioAjax.class);
	
	public LaboratorioAjax(){
		iObjLog.debug("new: Generando nueva clase FacturacionDatosAjax");
	}	
		
	public String validaOrden(String strOrden,int cUnidad,String strLiberar,String idUsuario) throws Exception
	{
		String strRespuesta = "";
		ViajeFacturacionDao objViajeFacturacionDAO =  new ViajeFacturacionDao();
		iObjLog.debug("FacturacionDatosAjax.validaOrden:Entrando..." + strOrden);
		try
		{
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
			iObjLog.debug("FacturacionDatosAjax.validaOrden:Validacion...." 
					+ " -- Orden : " + strOrden 
					+ " -- Unidad: " + cUnidad 
					+ " -- Liberar: " + strLiberar
					+ " -- Usuario: " + idUsuario);
			DatosOrdenDao objOrdenDAO = new DatosOrdenDao();
			List lstOrdenes = objOrdenDAO.buscarOrden(Integer.parseInt(strOrden));
			if (lstOrdenes == null) {
				iObjLog.debug("Me regreso null " + strOrden);
				strRespuesta = "ORDENNOVALIDA";				
			} else if (lstOrdenes.size() == 0) {
				iObjLog.debug("No existe la Orden " + strOrden);
				strRespuesta = "ORDENNOVALIDA";
			} else {
				iObjLog.debug("Si existe la Orden " + strOrden + " existen " + lstOrdenes.size());
				TOrdenSucursal objOrden = (TOrdenSucursal)lstOrdenes.get(0);
				if (objOrden.getMpagopaciente() == null && objOrden.getMfacturaempresa() == null) {
					strRespuesta = "ORDENNOVALIDA";				
				} else if (objOrden.getMpagopaciente() == null && objOrden.getMfacturaempresa() != null && objOrden.getMfacturaempresa().doubleValue() > 0.0) {
					if (objViajeFacturacionDAO.SearchOrdenNuevaViaje(objOrden) == false) {
						objViajeFacturacionDAO.OrdenNuevaViaje(objOrden,Integer.parseInt(idUsuario));
						strRespuesta = 	ConvertBeanvsHB.llenaIdFactura(objOrden.getSsucursal().trim(),String.valueOf(objOrden.getUorden()),7);
					} else {
						strRespuesta = "EXISTEVIAJE";				
					}
				} else if (objOrden.getMfacturaempresa() == null) {
					strRespuesta = "ORDENNOVALIDA";				
				} else if (objOrden.getMpagopaciente().doubleValue() > 0.0 && objOrden.getMfacturaempresa().doubleValue() == 0.0) {
					strRespuesta = "ORDENNOCREDITO";
				} else if (objOrden.getCsucursalbycsucursal().getCsucursal().intValue() != cUnidad) {
					iObjLog.debug("Sucursal Orden " + objOrden.getCsucursalbycsucursal().getCsucursal().intValue() + " sucursal origen " + cUnidad);
					strRespuesta = " ,Orden de la sucursal " + objOrden.getCsucursalbycsucursal().getSnombresucursal();					
				} else {				
					if (objViajeFacturacionDAO.SearchOrdenNuevaViaje(objOrden) == false) {
						objViajeFacturacionDAO.OrdenNuevaViaje(objOrden,Integer.parseInt(idUsuario));
						strRespuesta = 	ConvertBeanvsHB.llenaIdFactura(objOrden.getSsucursal().trim(),String.valueOf(objOrden.getUorden()),7);
					} else {
						strRespuesta = "EXISTEVIAJE";				
					}
				}
			}
		}
		catch (Exception e){
			iObjLog.error("FacturacionDatosAjax.validaOrden:ERROR",e);
			if(e instanceof AjaxDwrException){
				throw new AjaxDwrException(e);
			}else{
				throw new AjaxDwrException(0,"FacturacionDatosAjax.validaOrden:Ocurri&oacute; un error al validar la orden ..." + strOrden + " con la sucursal " + cUnidad);
			}
		}		
		return strRespuesta;
	}

	public String sinViajeOrden(int cUnidad,String idUsuario) throws Exception
	{
		String strRespuesta = "";
		iObjLog.debug("FacturacionDatosAjax.sinViajeOrden:Entrando..." + cUnidad);
		try
		{
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
    		ViajeFacturacionDao objOrdenSinViajeDAO = new ViajeFacturacionDao();
    		strRespuesta = objOrdenSinViajeDAO.getOrdenesSinViaje(cUnidad,1);
		} catch (Exception e){
			iObjLog.error("FacturacionDatosAjax.sinViajeOrden:ERROR",e);
			if(e instanceof AjaxDwrException){
				throw new AjaxDwrException(e);
			}else{
				throw new AjaxDwrException(0,"FacturacionDatosAjax.sinViajeOrden:Ocurri&oacute; un error al validar la orden ..." + cUnidad + " con la sucursal " + cUnidad);
			}
		}		
		return strRespuesta;
	}

	public String buscarViaje(int cUnidad,String idUsuario,int kViaje) throws Exception
	{
		String strRespuesta = "";
		iObjLog.debug("FacturacionDatosAjax.buscarViaje:Entrando..." + cUnidad);
		try
		{
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
    		ViajeFacturacionDao objOrdenSinViajeDAO = new ViajeFacturacionDao();
    		strRespuesta = objOrdenSinViajeDAO.buscarViaje(cUnidad, kViaje);
		} catch (Exception e){
			iObjLog.error("FacturacionDatosAjax.buscarViaje:ERROR",e);
			if(e instanceof AjaxDwrException){
				throw new AjaxDwrException(e);
			}else{
				throw new AjaxDwrException(0,"FacturacionDatosAjax.buscarViaje:Ocurri&oacute; un error al validar la orden ..." + cUnidad + " con la sucursal " + cUnidad);
			}
		}		
		return strRespuesta;
	}
	
	public String getOrdenesNewViaje(int cUnidad,String idUsuario) throws Exception
	{
		String strRespuesta = "";
		iObjLog.debug("FacturacionDatosAjax.getOrdenesNewViaje:Entrando..." + cUnidad);
		try
		{
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
    		ViajeFacturacionDao objOrdenSinViajeDAO = new ViajeFacturacionDao();
    		strRespuesta = objOrdenSinViajeDAO.getOrdenesNewViaje(cUnidad);
		} catch (Exception e){
			iObjLog.error("FacturacionDatosAjax.getOrdenesNewViaje:ERROR",e);
			if(e instanceof AjaxDwrException){
				throw new AjaxDwrException(e);
			}else{
				throw new AjaxDwrException(0,"FacturacionDatosAjax.getOrdenesNewViaje:Ocurri&oacute; un error al validar la orden ..." + cUnidad + " con la sucursal " + cUnidad);
			}
		}		
		return strRespuesta;
	}

	public String cerrarViaje(int cUnidad,int idUsuario) throws Exception
	{
		String strRespuesta = "";
		iObjLog.debug("FacturacionDatosAjax.cerrarViaje:Entrando..." + cUnidad);
		try
		{
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
    		ViajeFacturacionDao objOrdenSinViajeDAO = new ViajeFacturacionDao();
    		strRespuesta = objOrdenSinViajeDAO.cerrarViaje(cUnidad,idUsuario);
		} catch (Exception e){
			iObjLog.error("FacturacionDatosAjax.cerrarViaje:ERROR",e);
			if(e instanceof AjaxDwrException){
				throw new AjaxDwrException(e);
			}else{
				throw new AjaxDwrException(0,"FacturacionDatosAjax.cerrarViaje:Ocurri&oacute; un error al validar la orden ..." + cUnidad + " con la sucursal " + cUnidad);
			}
		}		
		return strRespuesta;
	}
		
	public String[] imprimeEtiquetasExamenesLaboratorio(int kAdmision) throws Exception	
	{
		iObjLog.debug("Entrando a DatosExamenAjax.imprimeEtiquetasExamenesLaboratorio:Entrando... ");
		ExamenesDao objDaoExamenes = new ExamenesDao();
		return objDaoExamenes.imprimeEtiquetasZPL(String.valueOf(kAdmision),0," ce.ctipocomercial <> 2 AND ");
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
