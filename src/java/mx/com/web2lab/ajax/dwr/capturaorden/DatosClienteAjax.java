package mx.com.web2lab.ajax.dwr.capturaorden;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineUser;

import mx.com.web2lab.actions.seguridad.SeguridadUtil;
import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.comer.ClienteBean;
import mx.com.web2lab.backend.beans.comer.ConvenioBean;
import mx.com.web2lab.backend.beans.comer.MetricasClieConBean;
import mx.com.web2lab.backend.beans.facturacion.BuscarOrdenesViajeBean;
import mx.com.web2lab.backend.dao.comer.ClientesNewDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionMayoreoDao;
import mx.com.web2lab.backend.dao.tools.AdministracionFOP_PDF;
import mx.com.web2lab.backend.dao.tools.ConsultaOrdenesDao;
import mx.com.web2lab.backend.dao.tools.ReporteEstadoCuentaCxC;
import mx.com.web2lab.backend.hbm.ConfiguracionProperties;
import mx.com.web2lab.backend.util.Formatos;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

 
public class DatosClienteAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(DatosClienteAjax.class);	
	
	public String getMarcasUser(int userid) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.getMarcasUser:Entrando... userid:"+userid);
		String marcas = null;
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			marcas = ConfiguracionProperties.getPropiedad("alta.convenios.users."+userid);
			iObjLog.debug("marcas:"+marcas);
			iObjLog.debug("Saliendo DatosClienteAjax.getMarcasUser:Saliendo...  ");
			return marcas;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.getMarcasUser:Exception....", aObjException);
			marcas = null;
			throw aObjException;
		} 
		
	}	

	public MetricasClieConBean estatusAltas(int userid) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.estatusAltas:Entrando... ");		
		ClientesNewDao objDAOCliente = new ClientesNewDao();
		MetricasClieConBean objMetricas = new MetricasClieConBean();
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			objMetricas = objDAOCliente.estatusClientesConvenios();
			String marcas = ConfiguracionProperties.getPropiedad("alta.convenios.users."+userid);
			iObjLog.info("marcasProperties:"+marcas);
			if(marcas!=null){
				List lstCmarca = new ArrayList();
				List lstSmarca = new ArrayList();
				String [] marcasSplit = marcas.split(",");
				for (int i = 0; i < marcasSplit.length; i++) { 
					lstCmarca.add(marcasSplit[i]);
					lstSmarca.add(this.nameMarca(Integer.parseInt(marcasSplit[i])));
				} 
				objMetricas.setLstCmarca(lstCmarca);
				objMetricas.setLstSmarca(lstSmarca);
				objMetricas.setMarcasUser(marcas);
			}
			iObjLog.debug("Saliendo DatosClienteAjax.estatusAltas:Saliendo...  ");
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.estatusAltas:Exception....", aObjException);
			objMetricas = null;
			throw aObjException;
		} finally {
			objDAOCliente = null;
		}
		return objMetricas;
	}	
	
	private String nameMarca(int cmarca){
		String name="";
		switch (cmarca) {
		case 1:
			name="OLAB";
			break;
		case 4:
			name="AZTECA";
			break;
		case 5:
			name="SWISSLAB";
			break;
		case 7:
			name="JENNER";
			break;
		case 15:
			name="LIACSA";
			break;
		case 17:
			name="DIAGNOSTIX";
			break;
		case 19:
			name="FAMILY LABS NORTE";
			break;
		case 20:
			name="EXAKTA";
			break;
		case 21:
			name="ASESORES DEL SUR";
			break;
		case 16:
			name="MOREIRA";
			break;
		case 22:
			name="POLAB";
			break;
		case 25:
			name="BIOMEDICA DE REFERENCIA";
			break;
		case 26:
			name="PROMEDIC";
			break;
		case 9:
			name="SWISS HOSPITAL";
			break;
		default:
			break;
		}
		return name;
	}
	
	public String showEstadistica(int uTipoControl) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.showEstadistica:Entrando... ");		
		String strReturn = "";
		ClientesNewDao objDAOCliente = new ClientesNewDao();
		MetricasClieConBean objMetricas = new MetricasClieConBean();
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			strReturn = objDAOCliente.showEstadistica(uTipoControl);
			iObjLog.debug("Saliendo DatosClienteAjax.showEstadistica:Saliendo...  ");
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.showEstadistica:Exception....", aObjException);
			objMetricas = null;
			throw aObjException;
		} finally {
			objDAOCliente = null;
		}
		return strReturn;
	}	
	
	
	public ClienteBean buscarCliente(ClienteBean objClienteBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarCliente:Entrando... ");		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			objClienteBean = (ClienteBean)objDAOCliente.buscarCliente(objClienteBean).get(0);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarCliente:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarCliente:Exception....", aObjException);
			objClienteBean = null;
			throw aObjException;
		}
		return objClienteBean;
	}	

	public ClienteBean buscarClienteCxC(ClienteBean objClienteBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarClienteCxC:Entrando... ");		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			objClienteBean = (ClienteBean)objDAOCliente.buscarClienteCxC(objClienteBean,true).get(0);						
			iObjLog.debug("Saliendo DatosClienteAjax.buscarClienteCxC:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarClienteCxC:Exception....", aObjException);
			objClienteBean = null;
			throw aObjException;
		}
		return objClienteBean;
	}	
	
	/**
     * Versi�n 25 de Marzo 2013 
     BY
     */
	
	public ClienteBean buscarClienteVentas(ClienteBean objClienteBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarClienteCxC:Entrando... ");		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			objClienteBean = (ClienteBean)objDAOCliente.buscarClienteVentas(objClienteBean,true).get(0);						
			iObjLog.debug("Saliendo DatosClienteAjax.buscarClienteCxC:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarClienteCxC:Exception....", aObjException);
			objClienteBean = null;
			throw aObjException;
		}
		return objClienteBean;
	}	
	
	public String antiguedadSaldosCxC(String strNoSe) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.antiguedadSaldosCxC:Entrando... ");		
		ClienteBean objClienteBean = new ClienteBean();	
		AdministracionFOP_PDF objAdministracionFOP_PDF = new AdministracionFOP_PDF();
		ClientesNewDao objDAOCliente = new ClientesNewDao();
		FacturacionMayoreoDao objFacturacionMayoreoDao = new FacturacionMayoreoDao();
		String strFileAntiguedadSaldos = "";
		String strPathFOP = "";
		int cMarca = 1;
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			/*************************Clientes que tienen Facturas Anteriores ***********************/
			String strClientes = objFacturacionMayoreoDao.getClientesAntiguedadCxC();
			objClienteBean.setSobservaciones(strClientes);
			objClienteBean.setCcliente(0);
			objClienteBean.setSrazonsocial("");
			objClienteBean.setSrfc(""); 
			objClienteBean.setSmnemonico("");
			iObjLog.debug("Buscar DatosClienteAjax.antiguedadSaldosCxC:Consulta...  " + strClientes);
			/*************************Clientes que tienen Facturas Anteriores ***********************/
			/*************************Trae Clientes ya procesados ***********************/
			List lstClientes = objDAOCliente.buscarClienteCxC(objClienteBean,false);						
			for(int inti=0;inti<lstClientes.size();inti++) {
				objClienteBean = (ClienteBean)lstClientes.get(inti);
				strFileAntiguedadSaldos += objClienteBean.getSreport_pages_cxc();
				cMarca = objClienteBean.getCmarca();
			}
			/******************** No visualizo que meter OMRR 29012016 ***********************/
			strPathFOP = objAdministracionFOP_PDF.createDocument((ReporteEstadoCuentaCxC.startFileStatic() + strFileAntiguedadSaldos + ReporteEstadoCuentaCxC.endFileStatic()), "ReporteCxC_Antiguedad_Olab",cMarca);
			
			iObjLog.debug("Saliendo DatosClienteAjax.buscarClienteCxC:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarClienteCxC:Exception....", aObjException);
			objClienteBean = null;
			throw aObjException;
		} finally {
			objClienteBean = null;	
			objAdministracionFOP_PDF = null;
			objDAOCliente = null;
			objFacturacionMayoreoDao = null;
		}
		return strPathFOP;
	}	
	
	public String buscarClientes(ClienteBean objClienteBean) throws Exception
	{ 
		iObjLog.debug("Entrando DatosClienteAjax.buscarClientes:Entrando... ");		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			strReturn = this.showClientes(objDAOCliente.buscarCliente(objClienteBean));
		
			iObjLog.debug("Saliendo DatosClienteAjax.buscarClientes:Saliendo...  ");
			objDAOCliente = null; 
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarClientes:Exception....", aObjException);
			objClienteBean = null;
			throw aObjException;
		}
		return strReturn;
	}	 

	public ConvenioBean buscarConvenio(ConvenioBean objConvenioBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarConvenio:Entrando... ");		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			objConvenioBean = (ConvenioBean)objDAOCliente.buscarConvenio(objConvenioBean,"").get(0);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarConvenio:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarConvenio:Exception....", aObjException);
			objConvenioBean = null;
			throw aObjException;
		}
		return objConvenioBean;
	}	

	public ConvenioBean buscarConvenioExamen(ConvenioBean objConvenioBean,String strExamenes, int intTypeBusqueda) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarConvenio:Entrando... ");		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			if (intTypeBusqueda == 1) {
				objConvenioBean = (ConvenioBean)objDAOCliente.buscarConvenioAdministrador(objConvenioBean,strExamenes).get(0);
			} else {
				objConvenioBean = (ConvenioBean)objDAOCliente.buscarConvenioNombreExamen(objConvenioBean,strExamenes).get(0);
			}
			iObjLog.debug("Saliendo DatosClienteAjax.buscarConvenio:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarConvenio:Exception....", aObjException);
			objConvenioBean = null;
			throw aObjException;
		}
		return objConvenioBean;
	}	

	public ConvenioBean buscarConvenioExamenAdministrador(ConvenioBean objConvenioBean,String strExamenes) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarConvenio:Entrando... ");		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			objConvenioBean = (ConvenioBean)objDAOCliente.buscarConvenioAdministrador(objConvenioBean,strExamenes).get(0);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarConvenio:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarConvenio:Exception....", aObjException);
			objConvenioBean = null;
			throw aObjException;
		}
		return objConvenioBean;
	}	
	
	
	public String[] buscarExamenesConvenio(int intConvenio) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarConvenio:Entrando... ");		
		String strReturn[] = new String[4];
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			strReturn = objDAOCliente.buscarExamenesPerfilesConvenio(intConvenio);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarConvenio:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarConvenio:Exception....", aObjException);
			throw aObjException;
		}
		return strReturn;
	}	
	
	
	public String buscarConvenios(ConvenioBean objConvenioBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarConvenios:Entrando... ");		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			strReturn = objDAOCliente.buscarConvenios(objConvenioBean);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarConvenios:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarConvenios:Exception....", aObjException);
			objConvenioBean = null;
			throw aObjException;
		}
		return strReturn;
	}	

	public String buscarConveniosOrdenes(ConvenioBean objConvenioBean,String strFechaInicio, String strFechaTermino) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarConveniosOrdenes:Entrando... ");		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ConsultaOrdenesDao objDAOCliente = new ConsultaOrdenesDao();
			strReturn = objDAOCliente.buscarOrdenesConvenios(objConvenioBean,strFechaInicio,strFechaTermino);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarConveniosOrdenes:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarConveniosOrdenes:Exception....", aObjException);
			objConvenioBean = null;
			throw aObjException;
		}
		return strReturn;
	}	

	public String buscarViajeOrdenes(int kviajesucursal) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarViajeOrdenes:Entrando... ");		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ConsultaOrdenesDao objDAOCliente = new ConsultaOrdenesDao();
			BuscarOrdenesViajeBean objBuscarOrdenesViajeBean = new BuscarOrdenesViajeBean();
			objBuscarOrdenesViajeBean.setKviaje(kviajesucursal);
			objBuscarOrdenesViajeBean = objDAOCliente.buscarOrdenesViaje(objBuscarOrdenesViajeBean);
			strReturn = objBuscarOrdenesViajeBean.getSordeneshtml();
			iObjLog.debug("Saliendo DatosClienteAjax.buscarViajeOrdenes:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarViajeOrdenes:Exception....", aObjException);
			throw aObjException;
		}
		return strReturn;
	}	

	public String buscarFacturacionOrden(int kadmision) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarFacturacionOrden:Entrando... ");		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ConsultaOrdenesDao objDAOCliente = new ConsultaOrdenesDao();
			BuscarOrdenesViajeBean objBuscarOrdenesViajeBean = new BuscarOrdenesViajeBean();
			objBuscarOrdenesViajeBean.setKadmision(kadmision);
			objBuscarOrdenesViajeBean = objDAOCliente.buscarOrdenesViaje(objBuscarOrdenesViajeBean);
			strReturn = objBuscarOrdenesViajeBean.getSordeneshtml();
			iObjLog.debug("Saliendo DatosClienteAjax.buscarFacturacionOrden:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarFacturacionOrden:Exception....", aObjException);
			throw aObjException;
		}
		return strReturn;
	}	
	
	public String buscarOrdenesConvenioPaciente(ConvenioBean objConvenioBean,String strFechaInicio, String strFechaTermino, int kPaciente) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarOrdenesConvenioPaciente:Entrando... ");		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ConsultaOrdenesDao objDAOCliente = new ConsultaOrdenesDao();
			strReturn = objDAOCliente.buscarOrdenesConvenioPaciente(objConvenioBean,strFechaInicio,strFechaTermino,kPaciente);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarOrdenesConvenioPaciente:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarOrdenesConvenioPaciente:Exception....", aObjException);
			objConvenioBean = null;
			throw aObjException;
		}
		return strReturn;
	}	

	public String buscarOrdenesConvenioECE(ConvenioBean objConvenioBean,String strFechaInicio, String strFechaTermino, int kPaciente) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarOrdenesConvenioPaciente:Entrando... ");		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ConsultaOrdenesDao objDAOCliente = new ConsultaOrdenesDao();
			strReturn = objDAOCliente.buscarOrdenesConvenioECE(objConvenioBean,strFechaInicio,strFechaTermino,kPaciente);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarOrdenesConvenioPaciente:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarOrdenesConvenioPaciente:Exception....", aObjException);
			objConvenioBean = null;
			throw aObjException;
		}
		return strReturn;
	}	
	

	public String buscarOrdenesSucursalECE(int cSucursal,String strFechaInicio, String strFechaTermino, int kPaciente, int uFechaRegistro) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarOrdenesSucursalECE:Entrando... ");		
		String strReturn = "";
		boolean bFechaRegistro = false;
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ConsultaOrdenesDao objDAOCliente = new ConsultaOrdenesDao();
			if (uFechaRegistro == 1) {
				bFechaRegistro = true;
			} else {
				bFechaRegistro = false;
			}
			strReturn = objDAOCliente.buscarOrdenesSucursalECE(cSucursal,strFechaInicio,strFechaTermino,kPaciente,bFechaRegistro);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarOrdenesSucursalECE:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarOrdenesSucursalECE:Exception....", aObjException);
			throw aObjException;
		}
		return strReturn;
	}	
	
	public ClienteBean actualizaCliente(ClienteBean objClienteBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.actualizaCliente:Entrando... ");		
		ClientesNewDao objDAOCliente = new ClientesNewDao();
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			iObjLog.debug("getCregimenfiscal:"+objClienteBean.getCregimenfiscal());
			iObjLog.debug("getCusocfdi:"+objClienteBean.getCusocfdi());
			objClienteBean = objDAOCliente.setClienteActualizacion(objClienteBean);
			iObjLog.debug("Saliendo DatosClienteAjax.actualizaCliente:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.actualizaCliente:Exception....", aObjException);
			objClienteBean = null;
			throw aObjException;
		} finally {
			objDAOCliente = null;
		}
		return objClienteBean;
	}	

	public String altaPorcentajeClasificacionConvenio(int uConvenio,int uClasificacion, double pDescuento) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.altaPorcentajeClasificacionConvenio:Entrando... Convenio " + uConvenio + " Clasificacion " + uClasificacion + " Descuento " + pDescuento);		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			if (uConvenio > 0) {
				ClientesNewDao objDAOCliente = new ClientesNewDao();
				strReturn = objDAOCliente.altaPorcentajeClasificacionConvenio(uConvenio,uClasificacion,pDescuento);
				iObjLog.debug("Saliendo DatosClienteAjax.altaPorcentajeClasificacionConvenio:Saliendo...  ");
				objDAOCliente = null;
			} else {
				strReturn = "El Convenio no puede ser el 0, verificalo con TI por favor";
			}
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.altaPorcentajeClasificacionConvenio:Exception....", aObjException);
			throw aObjException;
		}
		return strReturn;
	}	

	public String altaPorcentajeExamenConvenio(int uConvenio,int uExamen, double mFacturar) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.altaPorcentajeExamenConvenio:Entrando... Convenio " + uConvenio + " Examen " + uExamen + " Monto Facturar " + mFacturar);		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			strReturn = objDAOCliente.altaPorcentajeExamenConvenio(uConvenio,uExamen,mFacturar);
			iObjLog.debug("Saliendo DatosClienteAjax.altaPorcentajeExamenConvenio:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.altaPorcentajeExamenConvenio:Exception....", aObjException);
			throw aObjException;
		}
		return strReturn;
	}	

	public String actualizarPorcentajeExamenConvenio(int kConvenioDetalle, int uConvenio,int uExamen, double mFacturar) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.actualizarPorcentajeExamenConvenio:Entrando... Convenio " + uConvenio + " Examen " + uExamen + " Monto Facturar " + mFacturar);		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			strReturn = objDAOCliente.actualizarPorcentajeExamenConvenio(kConvenioDetalle,uConvenio,uExamen,mFacturar);
			iObjLog.debug("Saliendo DatosClienteAjax.actualizarPorcentajeExamenConvenio:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.actualizarPorcentajeExamenConvenio:Exception....", aObjException);
			throw aObjException;
		}
		return strReturn;
	}		

	public String eliminarExamenConvenio(int kConvenioDetalle, int uConvenio,int uExamen, double mFacturar) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.eliminarExamenConvenio:Entrando... Convenio " + uConvenio + " Examen " + uExamen);		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			strReturn = objDAOCliente.eliminarExamenConvenio(kConvenioDetalle,uConvenio,uExamen,mFacturar);
			iObjLog.debug("Saliendo DatosClienteAjax.eliminarExamenConvenio:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.eliminarExamenConvenio:Exception....", aObjException);
			throw aObjException;
		}
		return strReturn;
	}		
	
	
	public String actualizarPorcentajeClasificacionConvenio(int kClasificacion, int uConvenio,int uClasificacion, double pDescuento) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.actualizarPorcentajeClasificacionConvenio:Entrando...kClasificacion " + kClasificacion + " Convenio " + uConvenio + " Clasificacion " + uClasificacion + " Descuento " + pDescuento);		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			strReturn = objDAOCliente.actualizarPorcentajeClasificacionConvenio(kClasificacion,uConvenio,uClasificacion,pDescuento);
			iObjLog.debug("Saliendo DatosClienteAjax.actualizarPorcentajeClasificacionConvenio:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.actualizarPorcentajeClasificacionConvenio:Exception....", aObjException);
			throw aObjException;
		}
		return strReturn;
	}		
	
	public ConvenioBean actualizaConvenio(ConvenioBean objConvenioBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.actualizaConvenio:Entrando... ");		
		ClientesNewDao objDAOCliente = new ClientesNewDao();
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			iObjLog.debug("Entrando DatosClienteAjax.actualizaConvenio:Entrando...Inicio " + objConvenioBean.getSiniciovigencia());		
			objConvenioBean.setDinicio(new Formatos().getFecha(objConvenioBean.getSiniciovigencia()));
			iObjLog.debug("Entrando DatosClienteAjax.actualizaConvenio:Entrando...Termino " + objConvenioBean.getSterminovigencia());		
			objConvenioBean.setDtermino(new Formatos().getFecha(objConvenioBean.getSterminovigencia()));			
			iObjLog.debug("Entrando DatosClienteAjax.actualizaConvenio:Entrando...Inicio Objeto " + objConvenioBean.getDinicio());		
			iObjLog.debug("Entrando DatosClienteAjax.actualizaConvenio:Entrando...Termino Objeto " + objConvenioBean.getDtermino());
			objConvenioBean = objDAOCliente.setConvenioActualizacion(objConvenioBean);
			iObjLog.debug("Saliendo DatosClienteAjax.actualizaConvenio:Saliendo...  ");
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.actualizaConvenio:Exception....", aObjException);
			objConvenioBean = null;
			throw aObjException;
		} finally {
			objDAOCliente = null;
		}
		return objConvenioBean;
	}	

	public String actualizaConvenioPasswordECE(ConvenioBean objConvenioBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.actualizaConvenioPasswordECE:Entrando... ");	
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			strReturn = objDAOCliente.setConvenioActualizacionECEEmpresa(objConvenioBean);
			iObjLog.debug("Saliendo DatosClienteAjax.actualizaConvenioPasswordECE:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.actualizaConvenioPasswordECE:Exception....", aObjException);
			objConvenioBean = null;
			throw aObjException;
		}
		return strReturn;
	}	
	
	
	private String showClientes(List lstClientes) throws Exception
	{		
		ClienteBean objClientes = new ClienteBean();
		String strReturn = "";		
		int intClientes = 0;
		iObjLog.debug("Entrando a DatosPacienteAjax.consultaMedicosGrid:Entrando... ");
		try {
			 if (lstClientes != null) {
				 intClientes = lstClientes.size();
			 }
			 strReturn = ("<table border='0' align='center' style='width: 100%' class='tabla'>" + 
					 		"<tr>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Total de Clientes " + intClientes +
								"	</font></b>" +
								"</th>"  + 
							"</tr>" +
						 "</table>" +	
						 "<table border='0' align='center' style='width: 100%' class='tabla'>" +
							"<tr>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>#" + 
								"	</font></b>" +
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Clave" + 
								"	</font></b>" +
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Razon Social" + 
								"	</font></b>" +
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>RFC" + 
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Marca" + 
								"</th>" + 
							"</tr>");			    
			 if (lstClientes != null) {
				int y; 
				for (int i = 0; i < lstClientes.size() ; i++)
				{
					objClientes = (ClienteBean)lstClientes.get(i);						
					y = i + 1;
					
					strReturn += ("<tr>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:clienteAceptado(" + objClientes.getCcliente() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											y + 
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:clienteAceptado(" + objClientes.getCcliente() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objClientes.getCcliente() + 
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:clienteAceptado(" + objClientes.getCcliente() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objClientes.getSrazonsocial() +
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:clienteAceptado(" + objClientes.getCcliente() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objClientes.getSrfc()+
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:clienteAceptado(" + objClientes.getCcliente() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objClientes.getSmarca() +
										"</a></td>" + 
									 "</tr>");
				}
			}
			strReturn += ("</table>");
			return strReturn; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosMedicoAjax.consultaMedicosGrid:Exception....", aObjException);
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
