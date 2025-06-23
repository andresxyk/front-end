package mx.com.web2lab.ajax.dwr.facturacion;

import mx.com.web2lab.actions.seguridad.SeguridadUtil;
import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import mx.com.web2lab.backend.beans.facturacion.BuscarOrdenesViajeBean;
import mx.com.web2lab.backend.dao.facturacion.empresas.viaje.IncidenciasFacturacionDao;
import mx.com.web2lab.backend.dao.tools.ConsultaOrdenesDao;
import mx.com.web2lab.backend.hbm.om.ap.AIncidenciaFacturacion;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineUser;

public class ViajeFacturacionAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(ViajeFacturacionAjax.class);	

	public BuscarOrdenesViajeBean buscarViajeOrdenes(BuscarOrdenesViajeBean objBuscarOrdenesViajeBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarViajeOrdenes:Entrando... Viaje " + objBuscarOrdenesViajeBean.getKviaje() + " cEstadoRegistro " + objBuscarOrdenesViajeBean.getIntcestadoregistro() + " Consecutivo " + objBuscarOrdenesViajeBean.getIntbloque());		
		try {			
			if(!isSesionValida(true))throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");						
			ConsultaOrdenesDao objDAOCliente = new ConsultaOrdenesDao();
			objBuscarOrdenesViajeBean = objDAOCliente.buscarOrdenesViaje(objBuscarOrdenesViajeBean);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarViajeOrdenes:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarViajeOrdenes:Exception....", aObjException);
			throw aObjException;
		}
		return objBuscarOrdenesViajeBean;
	}
	
	public BuscarOrdenesViajeBean buscarViajeOrdenesNewIndividual(BuscarOrdenesViajeBean objBuscarOrdenesViajeBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarViajeOrdenes:Entrando... Viaje " + objBuscarOrdenesViajeBean.getKviaje() + " cEstadoRegistro " + objBuscarOrdenesViajeBean.getIntcestadoregistro() + " Consecutivo " + objBuscarOrdenesViajeBean.getIntbloque());		
		try {			
			if(!isSesionValida(true))throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");						
			ConsultaOrdenesDao objDAOCliente = new ConsultaOrdenesDao();
			objBuscarOrdenesViajeBean = objDAOCliente.buscarOrdenesViajeNewIndividual(objBuscarOrdenesViajeBean);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarViajeOrdenes:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarViajeOrdenes:Exception....", aObjException);
			throw aObjException;
		}
		return objBuscarOrdenesViajeBean;
	}
	
	public BuscarOrdenesViajeBean buscarViajeOrdenesNewPaciente(BuscarOrdenesViajeBean objBuscarOrdenesViajeBean,int kpaciente) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarViajeOrdenes:Entrando... Viaje " + objBuscarOrdenesViajeBean.getKviaje() + " cEstadoRegistro " + objBuscarOrdenesViajeBean.getIntcestadoregistro() + " Consecutivo " + objBuscarOrdenesViajeBean.getIntbloque());		
		try {			
			if(!isSesionValida(true))throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");						
			ConsultaOrdenesDao objDAOCliente = new ConsultaOrdenesDao();
			objBuscarOrdenesViajeBean = objDAOCliente.buscarOrdenesViajeNewPaciente(objBuscarOrdenesViajeBean,kpaciente);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarViajeOrdenes:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarViajeOrdenes:Exception....", aObjException);
			throw aObjException;
		}
		return objBuscarOrdenesViajeBean;
	}	


	public String actualizarOrdenesBatch(String strAcciones,String strBloques) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.actualizarOrdenesBatch:Entrando... " + strAcciones);		
		String strReturn = "";
		int kOrdenSucursal = 0;
		int cValor = 0;
		String[] strCadaAcciones = null;
		String[] strElementos = null;
		try {			
			if(!isSesionValida(true))throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");						
			ConsultaOrdenesDao objDAOCliente = new ConsultaOrdenesDao();

			/**************************************Estados*********************************/
			strCadaAcciones = strAcciones.split(",");
			iObjLog.debug("Entrando DatosClienteAjax.actualizarOrdenesBatch:ConsultaElementos... " + strCadaAcciones.length);		
			for(int inti=0;inti<strCadaAcciones.length;inti++) {
				strElementos = strCadaAcciones[inti].split(":");
				iObjLog.debug("Entrando DatosClienteAjax.actualizarOrdenesBatch:ConsultaCompleto... " + strCadaAcciones[inti]);		
				iObjLog.debug("Entrando DatosClienteAjax.actualizarOrdenesBatch:ConsultaElemento0... " + strElementos[0]);		
				iObjLog.debug("Entrando DatosClienteAjax.actualizarOrdenesBatch:ConsultaElemento1... " + strElementos[1]);						
				if (Integer.parseInt(strElementos[0]) > 0) {
					kOrdenSucursal = Integer.parseInt(strElementos[0]);
					cValor = Integer.parseInt(strElementos[1]);
					strReturn = objDAOCliente.actualizarOrdenEstadoRegistro(kOrdenSucursal,cValor,1);
					if (strReturn.length() == 0) {
						strReturn = "Error en la actualizacion";
						break;
					}				
				}
			}
			/**************************************Bloques*********************************/
			strCadaAcciones = strBloques.split(",");
			iObjLog.debug("Entrando DatosClienteAjax.actualizarOrdenesBatch:ConsultaElementos... " + strCadaAcciones.length);		
			for(int inti=0;inti<strCadaAcciones.length;inti++) {
				strElementos = strCadaAcciones[inti].split(":");
				iObjLog.debug("Entrando DatosClienteAjax.actualizarOrdenesBatch:ConsultaCompleto... " + strCadaAcciones[inti]);		
				iObjLog.debug("Entrando DatosClienteAjax.actualizarOrdenesBatch:ConsultaElemento0... " + strElementos[0]);		
				iObjLog.debug("Entrando DatosClienteAjax.actualizarOrdenesBatch:ConsultaElemento1... " + strElementos[1]);						
				if (Integer.parseInt(strElementos[0]) > 0) {
					kOrdenSucursal = Integer.parseInt(strElementos[0]);
					cValor = Integer.parseInt(strElementos[1]);
					strReturn = objDAOCliente.actualizarOrdenEstadoRegistro(kOrdenSucursal,cValor,2);
					if (strReturn.length() == 0) {
						strReturn = "Error en la actualizacion";
						break;
					}				
				}
			}			
			iObjLog.debug("Saliendo DatosClienteAjax.actualizarOrdenesBatch:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.actualizarOrdenesBatch:Exception....", aObjException);
			throw aObjException;
		}
		return strReturn;
	}	
	
	
	public int levantarIncidenciaOrden(int kadmision,int cincidencia,int UserId) throws Exception
	{
		iObjLog.debug("Entrando ViajeFacturacionAjax.levantarIncidenciaOrden:Entrando... ");		
		AIncidenciaFacturacion objIncidenciaReturn = new AIncidenciaFacturacion();
		IncidenciasFacturacionDao objDAOIncidencia = new IncidenciasFacturacionDao();
		try {			
			if(!isSesionValida(true))throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");						
			objIncidenciaReturn = objDAOIncidencia.guardarIncidencia(cincidencia, kadmision, UserId, "T_Orden_Sucursal_Fac", "kOrdenSucursal", "");
			iObjLog.debug("Saliendo ViajeFacturacionAjax.levantarIncidenciaOrden:Saliendo...  ");
			return objIncidenciaReturn.getKincidenciafacturacion().intValue();
		} catch (Exception aObjException){
			iObjLog.error("Error ViajeFacturacionAjax.levantarIncidenciaOrden:Exception....", aObjException);
			throw aObjException;
		} finally {
			objDAOIncidencia = null;			
		}
	}	

	public int levantarIncidenciaViaje(int kviaje,int cincidencia,int UserId) throws Exception
	{
		iObjLog.debug("Entrando ViajeFacturacionAjax.levantarIncidenciaViaje:Entrando... ");		
		AIncidenciaFacturacion objIncidenciaReturn = new AIncidenciaFacturacion();
		IncidenciasFacturacionDao objDAOIncidencia = new IncidenciasFacturacionDao();
		try {			
			if(!isSesionValida(true))throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");						
			objIncidenciaReturn = objDAOIncidencia.guardarIncidencia(cincidencia, kviaje, UserId, "T_Orden_Sucursal_Fac", "kViaje", "");
			iObjLog.debug("Saliendo ViajeFacturacionAjax.levantarIncidenciaViaje:Saliendo...  ");
			return objIncidenciaReturn.getKincidenciafacturacion().intValue();
		} catch (Exception aObjException){
			iObjLog.error("Error ViajeFacturacionAjax.levantarIncidenciaViaje:Exception....", aObjException);
			throw aObjException;
		} finally {
			objDAOIncidencia = null;			
		}
	}
	
		
	public int validaAutetificacion(String strUsuario,String strPassword) throws Exception
	{
		int intReturn = 0;
		iObjLog.debug("Entrando DatosMedicoAjax.validaAutetificacion:Entrando... ");		
		try {			
    		User objUsuario = TurbineSecurity.getAuthenticatedUser(strUsuario.trim(), strPassword.trim());
    		if(objUsuario!=null) {
                TurbineUser objTurbineUser = getUserByName(strUsuario.trim());
                intReturn = objTurbineUser.getUserId();    						
    		} else {
    			intReturn = 0;    			
    		}
			iObjLog.debug("Saliendo DatosMedicoAjax.validaAutetificacion:Saliendo...  " + intReturn);
		} catch (Exception aObjException){
			iObjLog.error("Error DatosMedicoAjax.validaAutetificacion:Exception....", aObjException);
			throw aObjException;
		}
		return intReturn;
	}	
	
    public TurbineUser getUserByName(String strName) throws Exception {
        return SeguridadUtil.getUserByName(strName);
    }	
}
