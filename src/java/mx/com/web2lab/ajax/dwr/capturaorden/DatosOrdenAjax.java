package mx.com.web2lab.ajax.dwr.capturaorden;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;

import mx.com.web2lab.backend.beans.ap.OrdenBean;
import mx.com.web2lab.backend.beans.ap.PacienteBean;
import mx.com.web2lab.backend.beans.ap.PagoPacienteBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
import mx.com.web2lab.backend.dao.ap.DatosOrdenDao;
import mx.com.web2lab.backend.dao.ap.PagosDao;
import mx.com.web2lab.backend.dao.facturacion.electronica.orden.OrdenDatosFacturacionDao;
import mx.com.web2lab.backend.dao.facturacion.tool.DatosFiscalesDao;

import mx.com.web2lab.backend.util.ToolArchivosSO;
import mx.com.web2lab.backend.util.formatos.Formatos;

import java.security.MessageDigest;
import java.util.List;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DatosOrdenAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(DatosOrdenAjax.class);
 	private Date objDate = new Date();
	
	public DatosOrdenAjax(){
		//Constructor redefinido para generar entradas a bitacora
		iObjLog.debug("new: Generando nueva clase FacturacionDatosAjax");
	 	objDate.setHours(0);
	 	objDate.setMinutes(0);
	 	objDate.setSeconds(0);
	}
		
	static String llenaIdFactura(String strNemonico,String intFactura,int MaxLength) {
		String strReturn = "";
		int intTotal = (strNemonico.length() + intFactura.length());
		for(int i = intTotal;i <= MaxLength;i++) {
			strReturn += "0";
		}		
		return strNemonico + strReturn + intFactura;
	}
	
    public OrdenBean getOrden(int kOrden) throws Exception	
	{
    	OrdenBean objOrdenBean = null;
		OrdenDatosFacturacionDao objDatosOrdenDAO = new OrdenDatosFacturacionDao();
        try {
	    	iObjLog.debug("Entrando a DatosOrdenAjax.getOrden:Entrando...Parametros... " + kOrden);
			DatosOrdenDao objDAOOrden = new DatosOrdenDao();
			objOrdenBean = objDAOOrden.buscarOrdenOnly(kOrden);
//			objOrdenBean.setSdentregaresultado(new Formatos().getFechaNumeros(objOrdenBean.getDpromesa()));
//			objOrdenBean.setSobservacion(new Formatos().getFechaNumeros(objOrdenBean.getDpromesa()));
			objOrdenBean.setSordenfundacion(DatosOrdenAjax.llenaIdFactura(objOrdenBean.getSordenfundacion(),String.valueOf(objOrdenBean.getCordenfundacion()) , 8));			
			FacturaElectronicaBean objFacturaBean = new FacturaElectronicaBean();
			objOrdenBean.setStrFactura(objDatosOrdenDAO.searchOrdenFacturacion(objFacturaBean,kOrden + "",false).getSFacturaOld());			
			return objOrdenBean;
		} catch (Exception aObjException){
    	    iObjLog.error("DatosOrdenAjax.getOrden:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	

    /* Create 21/03/2013 Author OMRR */
    public OrdenBean getOrdenFacturacionElectronicaInternet(int kOrden,String strPassword,int kDatoFiscal) throws Exception	
	{
    	OrdenBean objOrdenBean = null;
		OrdenDatosFacturacionDao objDatosOrdenDAO = new OrdenDatosFacturacionDao();
		FacturaElectronicaBean objFacturaBean = new FacturaElectronicaBean();
		DatosOrdenDao objDAOOrden = new DatosOrdenDao();
		DatosFiscalesDao objDatosFiscalesDao = new DatosFiscalesDao();
        try {
	    	iObjLog.debug("Entrando a DatosOrdenAjax.getOrden:Entrando...Parametros... " + kOrden);
			objOrdenBean = objDAOOrden.buscarOrdenFacturacionElectronicaInternet(kOrden,strPassword);
			if (objOrdenBean.getSmensajeerror().trim().length() == 0) {
				objOrdenBean.setSordenfundacion(DatosOrdenAjax.llenaIdFactura(objOrdenBean.getSordenfundacion(),String.valueOf(objOrdenBean.getCordenfundacion()) , 8));			
				objOrdenBean.setStrFactura(objDatosOrdenDAO.searchOrdenFacturacion(objFacturaBean,kOrden + "",false).getSFacturaOld());			
				objOrdenBean.setObjdatosfiscalesbean(objDatosFiscalesDao.buscarDatosFiscalesOrden(kOrden,kDatoFiscal));
				objOrdenBean.setNofacturas(objDatosOrdenDAO.numeroFacturas(kOrden));
			} else {
				objOrdenBean.setNofacturas(objDatosOrdenDAO.numeroFacturas(kOrden));
				if (objOrdenBean.getNofacturas() > 1) {
					objOrdenBean.setSmensajeerror("2015");
				}
			}
			return objOrdenBean;
		} catch (Exception aObjException){
    	    iObjLog.error("DatosOrdenAjax.getOrden:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objDatosOrdenDAO = null;
			objFacturaBean = null;
			objDAOOrden = null;
			objDatosFiscalesDao = null;
		}
	}	
    
    
    public OrdenBean getOrdenFac(int kOrden) throws Exception	
	{
    	OrdenBean objOrdenBean = null;
		OrdenDatosFacturacionDao objDatosOrdenDAO = new OrdenDatosFacturacionDao();
        try {
	    	iObjLog.debug("Entrando a DatosOrdenAjax.getOrdenFac:Entrando...Parametros... " + kOrden);
			DatosOrdenDao objDAOOrden = new DatosOrdenDao();
			objOrdenBean = objDAOOrden.buscarOrdenFacOnly(kOrden);
//			objOrdenBean.setSdentregaresultado(new Formatos().getFechaNumeros(objOrdenBean.getDpromesa()));
//			objOrdenBean.setSobservacion(new Formatos().getFechaNumeros(objOrdenBean.getDpromesa()));
			objOrdenBean.setSordenfundacion(DatosOrdenAjax.llenaIdFactura(objOrdenBean.getSordenfundacion(),String.valueOf(objOrdenBean.getCordenfundacion()) , 8));			
			FacturaElectronicaBean objFacturaBean = new FacturaElectronicaBean();
			objOrdenBean.setStrFactura(objDatosOrdenDAO.searchOrdenFacturacion(objFacturaBean,kOrden + "",false).getSFacturaOld());			
			return objOrdenBean;
		} catch (Exception aObjException){
    	    iObjLog.error("DatosOrdenAjax.getOrdenFac:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	
    
    public OrdenBean getOrdenTipo(String sTipoOrden,int cOrden) throws Exception	
	{
    	OrdenBean objBOrden = null;
        try {
	    	iObjLog.debug("Entrando a DatosOrdenAjax.getOrden:Entrando...Parametros... " + cOrden);
			DatosOrdenDao objDAOOrden = new DatosOrdenDao();
			objBOrden = objDAOOrden.buscarOrdenOnly(sTipoOrden,cOrden);
		 	objBOrden.setSobservacion(new Formatos().getFechaNumeros(objBOrden.getDpromesa()));
		 	objBOrden.setSordenfundacion(DatosOrdenAjax.llenaIdFactura(objBOrden.getSordenfundacion(),String.valueOf(objBOrden.getCordenfundacion()) , 8));			
			return objBOrden;
		} catch (Exception aObjException){
    	    iObjLog.error("DatosOrdenAjax.getOrden:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	

    public PacienteBean getkPaciente(String sTipoOrden,int cOrden) throws Exception	
	{
    	OrdenBean objBOrden = null;
        try {
	    	iObjLog.debug("Entrando a DatosOrdenAjax.getOrden:Entrando...Parametros... " + cOrden);
			DatosOrdenDao objDAOOrden = new DatosOrdenDao();
			objBOrden = objDAOOrden.buscarOrdenOnly(sTipoOrden,cOrden);
			return objBOrden.getBpacientebean();
		} catch (Exception aObjException){
    	    iObjLog.error("DatosOrdenAjax.getOrden:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	

    public PacienteBean getOrdenbyAdmision(int kAdmision) throws Exception	
	{
    	OrdenBean objBOrden = null;
        try {
	    	iObjLog.debug("Entrando a DatosOrdenAjax.getOrdenbyAdmision:Entrando...Parametros... " + kAdmision);
			DatosOrdenDao objDAOOrden = new DatosOrdenDao();
			objBOrden = objDAOOrden.buscarOrdenOnly(kAdmision);
			return objBOrden.getBpacientebean();
		} catch (Exception aObjException){
    	    iObjLog.error("DatosOrdenAjax.getOrdenbyAdmision:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	

    public String actualizarFechaCompromiso(int kAdmision,int uUsuarioChange, String strFechaCompromiso) throws Exception	
	{
        try {
	    	iObjLog.debug("Entrando a DatosOrdenAjax.actualizarFechaCompromiso:Entrando...Parametros... " + kAdmision + " Fecha Entrega " +strFechaCompromiso);
	    	Date objdNuevaFechaEntrega = new Formatos().getFecha(strFechaCompromiso); 
			DatosOrdenDao objDAOOrden = new DatosOrdenDao();
			return objDAOOrden.actualizarFechaCompromiso(kAdmision,uUsuarioChange, objdNuevaFechaEntrega);
		} catch (Exception aObjException){
    	    iObjLog.error("DatosOrdenAjax.actualizarFechaCompromiso:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	
    
	public String[] consultaOrdenesGrid(int kPaciente,int cConvenio) throws Exception	
	{
		iObjLog.debug("Entrando a DatosPacienteAjax.consultaOrdenesGrid:Entrando...Parametros... " + kPaciente);
		DatosOrdenDao objDAOOrden = new DatosOrdenDao();
		OrdenBean objOrden = null;
		List lstOrdenes = objDAOOrden.buscarOrdenesVSPaciente(kPaciente,cConvenio,true);
		String strReturn = "";		
		String strReturnArray[] = new String[2];		
		String strJavaScript = "";
		int intSizeOrdenes = 0;
		if (lstOrdenes != null) {
			if (lstOrdenes.size() > 6) {
				strJavaScript = "style='visibility:hidden;display:none;'";
			}
			intSizeOrdenes = lstOrdenes.size();
		}		
		try {//883px
			 strReturn = ("<table border='0' align='center' style='width: 883px' class='tabla'>" + 
							"<tr>" + 
								"<td align='center' onClick='javascript:ocultarOrdenes();' style='font-weight: bold; font-size: medium; color: black; font-style: normal; font-variant: normal;'> " + 
									"<a href='javascript:ocultarOrdenes();' onClick='javascript:ocultarOrdenes();' align='bottom' style='font-weight: bold; color: black; font-size: medium; font-style: normal; font-variant: normal;'>" + intSizeOrdenes + " ORDENES ANTERIORES DEL PACIENTE (CLICK, VER U OCULTAR)"  + 
									"</a>" + 
								"</td>" + 
							"</tr>"	+
						  "</table>" +	
						  "<div id='gridGridOrdenes' " + strJavaScript + ">" +                
			 			  "<table border='0' align='center' style='width: 883px' class='tabla'>" + 
							"<tr>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>C&oacute;digo de Orden" + 
								"	</font></b>" +
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Fecha Orden" + 
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Resultados Laboratorio" + 
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Factura" + 
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Estatus" + 
								"</th>" + 
							"</tr>" +
							"<tr>");			 
			 if (lstOrdenes != null) {
				String strColor = ""; 
				String strColorDiagnostico = ""; 
				String strResultadoConsultar = "";
				String strFactura = "";
				String strEstatusMuestra = "";
				ToolArchivosSO objArchivoResultadoExtra = new ToolArchivosSO();
				for (int i = 0; i < lstOrdenes.size() ; i++) {
					objOrden = (OrdenBean)lstOrdenes.get(i);						
					strResultadoConsultar = "";
					if (objOrden.getCestado() == 17) {
						strColor = "red";
						strColorDiagnostico = "red";
						strResultadoConsultar = "CANCELADA";
					} else {
						strColor = "black";						
						strColorDiagnostico = "green";													

					}
					if (objOrden.isBmuestraspendientes()) {
						strEstatusMuestra = "<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " 
										+ "<font color='red'>MUESTRAS PENDIENTES</font> " + 
									"</td>";
					} else {
						strEstatusMuestra = "<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " 
										+ "<font color='green'>MUESTRAS TOMADAS</font> " + 
									"</td>";
					}
					
					if (objOrden.getStrFactura().trim().toString().length() > 3) {
						strFactura = "<td align='center'  style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " + "" +
									 "	<a href=\"javascript:visualizarFactura('http://192.237.150.66:9085/FacturasElectronicas_Olab/PDF/FacturacionElectronica_" + objOrden.getStrFactura().trim() + ".pdf');\"  align='bottom' style='font-weight: normal; font-size: x-small; color: " + strColorDiagnostico + "; font-style: normal; font-variant: normal;'>"  +  
				    		         "		<img alt='Factura - PDF' id=\"imgPDF\" width=\"22\" height=\"22\" border='0' src='/web2labportal/images/icoPdf.png' />" +
									 "	</a> - " + 							
									 "	<a href=\"javascript:visualizarFactura('http://192.237.150.66:9085/FacturasElectronicas_Olab/XML/FacturacionElectronica_" + objOrden.getStrFactura().trim() + ".xml');\"  align='bottom' style='font-weight: normal; font-size: x-small; color: " + strColorDiagnostico + "; font-style: normal; font-variant: normal;'>"  +  
				    		         "		<img alt='Factura - XML' id=\"imgXML\" width=\"22\" height=\"22\" border='0' src='/web2labportal/images/icoXml.png' />" +
									 "	</a>" + 
									 "</td>"; 
					} else if (objOrden.getCestado() != 17){
						if (objOrden.getMadeuda() > 0.0) {
							if (!(strResultadoConsultar.length() > 5)) {
								strFactura = "<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " +
//												+ " NO ESTA FACTURADA " + 
											"</td>";
							}
						} else  {
							if (!(strResultadoConsultar.length() > 5) && (objOrden.getMpagapaciente() > 0.0)) {
								strFactura = "<td align='center' style='font-weight: normal; font-size: x-small; color: green; font-style: normal; font-variant: normal;'> " +
//												" NO ESTA FACTURADA " + 							
//												"<a href='javascript:doNothing()' onClick='javascript:crearFactura("+ objOrden.getKadmision() +");' align='bottom' style='font-weight: normal; font-size: x-small; color: green; font-style: normal; font-variant: normal;'>" + 
//												"	QUIERES FACTURARLA?" +
//												"</a>" +									
											"</td>";					
							} else {
								strFactura = "<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " +
											 "</td>";								
							}
						}												
					} else {
						strFactura = "<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " +
								 	 "</td>";														
					}
					if (objOrden.getMadeuda() > 0.0) {
						if (!(strResultadoConsultar.length() > 5)) {
//							strResultadoConsultar = "NO ESTA PAGADA";
						}
						strReturn += ("<tr>" + 
								"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:mostrarFactura(" + objOrden.getKadmision() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'>" + 
									this.llenaIdFactura(objOrden.getSordenfundacion().trim(),String.valueOf(objOrden.getCordenfundacion()),8) + 
								"</a></td>" + 
								"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'>"+
									new Formatos().getFechaNumeros(objOrden.getDregistro()) +
								"</td>" +
								"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " 
									+ strResultadoConsultar + 
								"</td>" + strFactura + strEstatusMuestra +
							 "</tr>");
					} else if (objOrden.isBolcotizacionexameneslaboratorio()) {
						if (!(strResultadoConsultar.length() > 5)) {
							strResultadoConsultar = "<td align='center'  style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " + "" +
													"		<a href=\"javascript:visualizarResultado(" + objOrden.getKadmision() + ",'" + objOrden.getBpacientebean().getSpasswordexpedienteenvio() + "');\"  align='bottom' style='font-weight: normal; font-size: x-small; color: " + strColorDiagnostico + "; font-style: normal; font-variant: normal;'><img alt='Resultados Laboratorio' id='idResultadoLaboratorio' width='30' height='30' border='0' src='/web2labportal/images/icoResultadoLaboratorio.png' /> "  +  
													"		</a>" + 
													"		<a href=\"javascript:visualizarResultado(" + objOrden.getKadmision() + ",'" + objOrden.getBpacientebean().getSpasswordexpedienteenvio() + "');\"  align='bottom' style='font-weight: normal; font-size: x-small; color: " + strColorDiagnostico + "; font-style: normal; font-variant: normal;'><img alt='Envio de Resultados' id='idEnvioResultado' width='30' height='30' border='0' src='/web2labportal/images/icoCorreo.png' /> "  +  
													"		</a>" + objArchivoResultadoExtra.getPathRetultadoInBody(objOrden.getKadmision() + "", strColorDiagnostico) + " " + 
													                objArchivoResultadoExtra.getPathRetultadoConclusiones(objOrden.getKadmision() + "", strColorDiagnostico) + " " + 
													                objArchivoResultadoExtra.getPathRetultadoToxi(objOrden.getKadmision() + "", strColorDiagnostico) +  " " +
																    objArchivoResultadoExtra.getPathRetultadoElectro(objOrden.getKadmision() + "", strColorDiagnostico) + " " + 
																    objArchivoResultadoExtra.getPathRetultadoLumbar(objOrden.getKadmision() + "", strColorDiagnostico) + " " + 
																    objArchivoResultadoExtra.getPathRetultadoTorax(objOrden.getKadmision() + "", strColorDiagnostico) +
													"</td>";
						} else {
							strResultadoConsultar = "<td align='center'  style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " + "" 
														+ strResultadoConsultar +
													"</td>";							
						}
						strReturn += ("<tr>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:mostrarFactura(" + objOrden.getKadmision() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'>" + 
											this.llenaIdFactura(objOrden.getSordenfundacion().trim(),String.valueOf(objOrden.getCordenfundacion()),8) + 
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'>"+
											new Formatos().getFechaNumeros(objOrden.getDregistro()) +
										"</td>" + strResultadoConsultar + strFactura + strEstatusMuestra +
									 "</tr>");						
					} else {
						if (objOrden.getCestado() == 17) {
							strColor = "red";
							strColorDiagnostico = "red";
							strResultadoConsultar = "CANCELADA";
						} else {
							strResultadoConsultar = (objArchivoResultadoExtra.getPathRetultadoInBody(objOrden.getKadmision() + "", strColorDiagnostico) + " " + 
													objArchivoResultadoExtra.getPathRetultadoConclusiones(objOrden.getKadmision() + "", strColorDiagnostico) + " " + 
													objArchivoResultadoExtra.getPathRetultadoToxi(objOrden.getKadmision() + "", strColorDiagnostico) + " " +
													objArchivoResultadoExtra.getPathRetultadoElectro(objOrden.getKadmision() + "", strColorDiagnostico) + " " + 
													objArchivoResultadoExtra.getPathRetultadoLumbar(objOrden.getKadmision() + "", strColorDiagnostico) + " " + 
													objArchivoResultadoExtra.getPathRetultadoTorax(objOrden.getKadmision() + "", strColorDiagnostico));
							if (strResultadoConsultar.trim() == "" || strResultadoConsultar.trim().equals("") || strResultadoConsultar.trim().length() < 5) {
								strResultadoConsultar = "SOLO TIENE EXAMENES DE GABINETES ";
							}
							strColor = "blue";						
						}
						strReturn += ("<tr>" + 
								"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:mostrarFactura(" + objOrden.getKadmision() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'>" + 
									this.llenaIdFactura(objOrden.getSordenfundacion().trim(),String.valueOf(objOrden.getCordenfundacion()),8) + 
								"</a></td>" + 
								"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'>"+
									new Formatos().getFechaNumeros(objOrden.getDregistro()) +
								"</td>" +
								"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " 
									+ strResultadoConsultar + 
								"</td>" + strFactura + strEstatusMuestra +
							 "</tr>");
					}
				}
			}
			strReturn += ("</table></div>");
			strReturnArray[0] = strReturn;
			strReturnArray[1] = "" + intSizeOrdenes;
			return strReturnArray; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosPacienteAjax.consultaPacienteGrid:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	

	public String consultaOrdenesCuestionarioGrid(int kPaciente) throws Exception	
	{
		iObjLog.debug("Entrando a DatosPacienteAjax.consultaOrdenesGrid:Entrando...Parametros... " + kPaciente);
		DatosOrdenDao objDAOOrden = new DatosOrdenDao();
		OrdenBean objOrden = null;
		List lstOrdenes = objDAOOrden.buscarOrdenesVSPaciente(kPaciente,0,false);
		String strReturn = "";		
		try {//883px
			 strReturn = ("<table border='0' align='center' style='width: 980px' class='tabla'>" + 
							"<tr>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>C&oacute;digo de Factura" + 
							"	</font></b>" +
							"</th>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>Fecha Factura" + 
							"</th>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>Interpretacion" + 
							"</th>" + 
						"</tr>");			    
			 if (lstOrdenes != null) {
				String strColor = ""; 
				String strColorDiagnostico = ""; 
				for (int i = 0; i < lstOrdenes.size() ; i++)
				{
					objOrden = (OrdenBean)lstOrdenes.get(i);						
					if (objOrden.getCestado() == 2) {
						strColor = "red";
						strColorDiagnostico = "red";
					} else {
						strColor = "black";						
//						if((objOrden.getBordendiagfundacions().equals(null))) {
//							strColorDiagnostico = "red";							
//						} else{
//							strColorDiagnostico = "green";													
//						}
						strColorDiagnostico = "green";													

					}
					strReturn += ("<tr>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:mostrarFactura(" + objOrden.getKadmision() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'>" + 
											this.llenaIdFactura(objOrden.getSordenfundacion().trim(),String.valueOf(objOrden.getCordenfundacion()),8) + 
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'>"+
											new Formatos().getFechaNumeros(objOrden.getDregistro()) +
										"</td>" +
										"<td align='center' onClick='javascript:visualizarResultado(" + objOrden.getKadmision() + ");' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " + "" +
											"<a href='javascript:visualizarResultado(" + objOrden.getKadmision() + ");' onClick='javascript:visualizarResultado(" + objOrden.getKadmision() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: " + strColorDiagnostico + "; font-style: normal; font-variant: normal;'>Diagnostico"  + 
											"</a>" + 
										"</td>" + 
									 "</tr>");
				}
			}
			strReturn += ("</table>");
			return strReturn; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosPacienteAjax.consultaPacienteGrid:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	
	
	
	public PagoPacienteBean setPago(PagoPacienteBean objPago) throws Exception	
	{
		PagosDao objDaoPagos = new PagosDao();
		iObjLog.debug("Entrando a DatosOrdenAjax.setPago:Entrando... ");
		try {
		    objPago = objDaoPagos.setPago(objPago);
			return objPago; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosOrdenAjax.setPago:Exception....", aObjException);
    	    throw aObjException;
		} 
	}		
		
	public void registraResultados(int kAdmision,int intUsuario) throws Exception	
	{
		DatosOrdenDao objDaoOrdenes = new DatosOrdenDao();
		iObjLog.debug("Entrando a DatosOrdenAjax.registraResultados:Entrando... ");
		try {
			objDaoOrdenes.actualizaEntregaResultados(kAdmision, intUsuario);
		}catch (Exception aObjException){
    	    iObjLog.error("DatosOrdenAjax.registraResultados:Exception....", aObjException);
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
    
}
