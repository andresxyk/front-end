package mx.com.web2lab.ajax.dwr.facturacion;

import java.util.List;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.tools.ConvertBeanvsHB;
import mx.com.web2lab.backend.dao.ap.DatosOrdenDao;
import mx.com.web2lab.backend.dao.facturacion.empresas.viaje.ViajeFacturacionDao;
import mx.com.web2lab.backend.hbm.om.ap.TOrdenSucursal;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FacturacionDatosAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(FacturacionDatosAjax.class);
	
	public FacturacionDatosAjax(){
		iObjLog.debug("new: Generando nueva clase FacturacionDatosAjax");
	}	
		
	public String validaOrden(String strOrden,int cUnidad,String strLiberar,String idUsuario) throws Exception
	{
		String strRespuesta = "";
		ViajeFacturacionDao objViajeFacturacionDAO =  new ViajeFacturacionDao();
		iObjLog.debug("FacturacionDatosAjax.validaOrden:Entrando..." + strOrden);
		try
		{
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
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

	public String[] validaOrdenViaje(String strOrden,int cUnidad,String strLiberar,String idUsuario) throws Exception
	{
		String[] strRespuesta = new String[2];
		ViajeFacturacionDao objViajeFacturacionDAO =  new ViajeFacturacionDao();
		iObjLog.debug("FacturacionDatosAjax.validaOrden:Entrando..." + strOrden);
		try
		{
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
			iObjLog.debug("FacturacionDatosAjax.validaOrden:Validacion...." 
					+ " -- Orden : " + strOrden 
					+ " -- Unidad: " + cUnidad 
					+ " -- Liberar: " + strLiberar
					+ " -- Usuario: " + idUsuario);
			DatosOrdenDao objOrdenDAO = new DatosOrdenDao();
			List lstOrdenes = objOrdenDAO.buscarOrden(Integer.parseInt(strOrden));
			if (lstOrdenes == null) {
				iObjLog.debug("Me regreso null " + strOrden);
				strRespuesta[0] = "0";
				strRespuesta[1] = "El n&uacute;mero de la orden no es valido " + strOrden;				
			} else if (lstOrdenes.size() == 0) {
				iObjLog.debug("No existe la Orden " + strOrden);
				strRespuesta[0] = "0";
				strRespuesta[1] = "El n&uacute;mero de la orden no es valido " + strOrden;				
			} else {
				iObjLog.debug("Si existe la Orden " + strOrden + " existen " + lstOrdenes.size());
				TOrdenSucursal objOrden = (TOrdenSucursal)lstOrdenes.get(0);
				int kViaje = objViajeFacturacionDAO.SearchOrdenViaje(objOrden);
				if (objOrden.getMpagopaciente() == null && objOrden.getMfacturaempresa() == null) {
					strRespuesta[0] = "0";
					strRespuesta[1] = "El n&uacute;mero de la orden no es valido " + strOrden;				
				} else if (objOrden.getMpagopaciente() == null && objOrden.getMfacturaempresa() != null && objOrden.getMfacturaempresa().doubleValue() > 0.0) {
					if (kViaje == -1) {
						objViajeFacturacionDAO.OrdenNuevaViaje(objOrden,Integer.parseInt(idUsuario));
						strRespuesta[0] = "2";
						strRespuesta[1] = ConvertBeanvsHB.llenaIdFactura(objOrden.getSsucursal().trim(),String.valueOf(objOrden.getUorden()),7);
					} else if (kViaje == 0){
						ViajeFacturacionDao objViajeFacturacionCleanDAO =  new ViajeFacturacionDao();
						objViajeFacturacionCleanDAO.LimpiarErroViaje(objOrden.getKordensucursal());
						objViajeFacturacionCleanDAO = null;
						strRespuesta[0] = "5";
						strRespuesta[1] = "Existen un error con orden, hable inmediatamente a Sistemas por favor!, " + strOrden + " - " + ConvertBeanvsHB.llenaIdFactura(objOrden.getSsucursal().trim(),String.valueOf(objOrden.getUorden()),7) + " Viaje "+ kViaje;				
						objViajeFacturacionDAO.OrdenNuevaViaje(objOrden,Integer.parseInt(idUsuario));
						kViaje = -1;
						strRespuesta[0] = "2";
						strRespuesta[1] = ConvertBeanvsHB.llenaIdFactura(objOrden.getSsucursal().trim(),String.valueOf(objOrden.getUorden()),7);						
					} else if (kViaje > 0){
						strRespuesta[0] = "1";
						strRespuesta[1] = "La orden ya fue ingresada en un viaje, el viajes es : " + strOrden + " - " + ConvertBeanvsHB.llenaIdFactura(objOrden.getSsucursal().trim(),String.valueOf(objOrden.getUorden()),7) + " Viaje "+ kViaje;;				
					}
				} else if (objOrden.getMfacturaempresa() == null) {
					strRespuesta[0] = "0";
					strRespuesta[1] = "El n&uacute;mero de la orden no es valido " + strOrden;				
				} else if (objOrden.getMpagopaciente().doubleValue() > 0.0 && objOrden.getMfacturaempresa().doubleValue() == 0.0) {
					strRespuesta[0] = "3";
					strRespuesta[1] = "El n&uacute;mero de la orden no es de cr&eacute;dito, " + strOrden + " - " + ConvertBeanvsHB.llenaIdFactura(objOrden.getSsucursal().trim(),String.valueOf(objOrden.getUorden()),7);				
				} else if (objOrden.getCsucursalbycsucursal().getCsucursal().intValue() != cUnidad) {
					iObjLog.debug("Sucursal Orden " + objOrden.getCsucursalbycsucursal().getCsucursal().intValue() + " sucursal origen " + cUnidad);
					strRespuesta[0] = "4";
					strRespuesta[1] = "La orden no es de la sucursal del usuario, Orden de la sucursal " + objOrden.getCsucursalbycsucursal().getSnombresucursal();				
				} else {				
					if (kViaje == -1) {
						objViajeFacturacionDAO.OrdenNuevaViaje(objOrden,Integer.parseInt(idUsuario));
						strRespuesta[0] = "2";
						strRespuesta[1] = ConvertBeanvsHB.llenaIdFactura(objOrden.getSsucursal().trim(),String.valueOf(objOrden.getUorden()),7);
					} else if (kViaje == 0){
						ViajeFacturacionDao objViajeFacturacionCleanDAO =  new ViajeFacturacionDao();
						objViajeFacturacionCleanDAO.LimpiarErroViaje(objOrden.getKordensucursal());
						objViajeFacturacionCleanDAO = null;
						strRespuesta[0] = "5";
						strRespuesta[1] = "Existen un error con orden, hable inmediatamente a Sistemas por favor!, " + strOrden + " - " + ConvertBeanvsHB.llenaIdFactura(objOrden.getSsucursal().trim(),String.valueOf(objOrden.getUorden()),7) + " Viaje "+ kViaje;				
						objViajeFacturacionDAO.OrdenNuevaViaje(objOrden,Integer.parseInt(idUsuario));
						kViaje = -1;
						strRespuesta[0] = "2";
						strRespuesta[1] = ConvertBeanvsHB.llenaIdFactura(objOrden.getSsucursal().trim(),String.valueOf(objOrden.getUorden()),7);						
					} else if (kViaje > 0){
						strRespuesta[0] = "1";
						strRespuesta[1] = "La orden ya fue ingresada en un viaje, el viajes es : " + strOrden + " - " + ConvertBeanvsHB.llenaIdFactura(objOrden.getSsucursal().trim(),String.valueOf(objOrden.getUorden()),7) + " Viaje "+ kViaje;;				
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
	
	public String sinViajeOrden(int cUnidad,String idUsuario,int intOrdenar) throws Exception
	{
		String strRespuesta = "";
		iObjLog.debug("FacturacionDatosAjax.sinViajeOrden:Entrando..." + cUnidad);
		try
		{
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
    		ViajeFacturacionDao objOrdenSinViajeDAO = new ViajeFacturacionDao();
    		strRespuesta = objOrdenSinViajeDAO.getOrdenesSinViaje(cUnidad,intOrdenar);
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

	public String enViajeOrdenNoFac(int cUnidad,String idUsuario,int intOrdenar) throws Exception
	{
		String strRespuesta = "";
		iObjLog.debug("FacturacionDatosAjax.sinViajeOrden:Entrando..." + cUnidad);
		try
		{
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
    		ViajeFacturacionDao objOrdenSinViajeDAO = new ViajeFacturacionDao();
    		strRespuesta = objOrdenSinViajeDAO.enViajeOrdenNoFac(cUnidad,intOrdenar);
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
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
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
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
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
		ViajeFacturacionDao objOrdenSinViajeDAO = new ViajeFacturacionDao();
		try
		{
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
    		strRespuesta = objOrdenSinViajeDAO.cerrarViaje(cUnidad,idUsuario);
		} catch (Exception e){
			iObjLog.error("FacturacionDatosAjax.cerrarViaje:ERROR",e);
			if(e instanceof AjaxDwrException){
				throw new AjaxDwrException(e);
			}else{
				throw new AjaxDwrException(0,"FacturacionDatosAjax.cerrarViaje:Ocurri&oacute; un error al validar la orden ..." + cUnidad + " con la sucursal " + cUnidad);
			}
		} finally {
			objOrdenSinViajeDAO = null;
		}
		return strRespuesta;
	}

	public String reimprimirEtiquetasViaje(int cUnidad,int kViajeFacturacion) throws Exception
	{
		String strRespuesta = "";
		iObjLog.debug("FacturacionDatosAjax.reimprimirEtiquetasViaje:Entrando..." + cUnidad + " kViajeFacturacion " + kViajeFacturacion);
		ViajeFacturacionDao objOrdenSinViajeDAO = new ViajeFacturacionDao();
		try
		{
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
    		strRespuesta = objOrdenSinViajeDAO.reimprimirEtiquetasViaje(cUnidad, kViajeFacturacion);
		} catch (Exception e){
			iObjLog.error("FacturacionDatosAjax.reimprimirEtiquetasViaje:ERROR",e);
			if(e instanceof AjaxDwrException){
				throw new AjaxDwrException(e);
			}else{
				throw new AjaxDwrException(0,"FacturacionDatosAjax.reimprimirEtiquetasViaje:Ocurri&oacute; un error al validar la orden ..." + cUnidad + " con la sucursal " + cUnidad);
			}
		} finally {
			objOrdenSinViajeDAO = null;
		}
		return strRespuesta;
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
