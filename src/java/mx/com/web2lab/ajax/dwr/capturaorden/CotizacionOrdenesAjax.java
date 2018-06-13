package mx.com.web2lab.ajax.dwr.capturaorden;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;

import mx.com.web2lab.backend.beans.ap.OrdenBean;
import mx.com.web2lab.backend.beans.ap.OrdenExamenBean;
import mx.com.web2lab.backend.beans.cotizaciones.CotizacionBean;
import mx.com.web2lab.backend.beans.tools.ConvertBeanvsHB;
import mx.com.web2lab.backend.beans.tools.SucursalBean;

import mx.com.web2lab.backend.dao.cotizaciones.CotizacionOrdenesDao;

import mx.com.web2lab.backend.hbm.om.ap.CEstadoRegistro;
import mx.com.web2lab.backend.hbm.om.ap.CMarca;
import mx.com.web2lab.backend.hbm.om.ap.CSucursal;
import mx.com.web2lab.backend.hbm.om.ap.TOrdenExamenSucursalCotizacion;
import mx.com.web2lab.backend.hbm.om.ap.TOrdenSucursalCotizacion;
import mx.com.web2lab.backend.hbm.om.ap.TPaciente;
import mx.com.web2lab.backend.hbm.om.ap.medico.CMedico;

import mx.com.web2lab.backend.util.catalogo.ListaExamenUtil;
import mx.com.web2lab.backend.util.catalogo.SucursalesUtil;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;
import mx.com.web2lab.util.Formatos;
import mx.com.web2lab.util.GenericDAO;

import java.sql.Connection;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CotizacionOrdenesAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(CotizacionOrdenesAjax.class);
		
	/* 
	 * Este metodo se encarga de guardar Cotizaciones de Pacientes
	 * con sus examenes cotizados.
	 */
	public OrdenBean guardarCotizacion(String strExamenes,String strVolumenesExamenes,int intSucursal, int kPaciente,int intUsuario,int intMedico,String strMedico, int intConvenio, String strObservaciones) throws Exception	
	{
		TOrdenSucursalCotizacion objBOrden = new TOrdenSucursalCotizacion();
		TOrdenExamenSucursalCotizacion objBOrdenExamen = null;
		TPaciente objPaciente = new TPaciente(); 
		CotizacionBean objCotizacionBean  = new CotizacionBean();
		
		OrdenExamenBean objDExamen = null;
		
		List lstExameneshbm = new ArrayList();
		List lstExamenes = new ArrayList();
		ListaExamenUtil objExamenCatalogo = new ListaExamenUtil();
		
		CotizacionOrdenesDao objCotizacionOrdenesDao = new CotizacionOrdenesDao();		
		
		Connection objConexion = null;

		iObjLog.debug("Entrando a CotizacionOrdenesAjax.guardarCotizacion:Entrando... " + strExamenes);
		try {
			GenericDAO objConn = new GenericDAO();
			objConexion = objConn.getConnection();			
		 	lstExamenes = objExamenCatalogo.cotizarProductosConvenio(strExamenes , intSucursal , intConvenio);		 	    	
//		 	Date newDate = null;
//		 	Date oldDate = new Date();
			CSucursal objSucursal = new CSucursal();
			ConvertBeanvsHB objConvert = new ConvertBeanvsHB();
			SucursalBean objSucursalBean = (new SucursalesUtil()).getSucursal(intSucursal);
		 	if (lstExamenes != null) {
				double dblSubTotal = 0.0;
				double dblDescuentoEmpresa = 0.0;
				double dblDescuentoMedico = 0.0;
				double dblDescuentoPromocion = 0.0;
				double dblFacturaEmpresa = 0.0;
				double dblIVA = 0.0;
				double dblPagoPaciente = 0.0;				
				double dblTotal = 0.0;
				short shrVolumenExamen = 0;
				String strExamenVolumen =  "";
				objPaciente.setKpaciente(new Integer(kPaciente));
				iObjLog.debug("Entrando a CotizacionOrdenesAjax.guardarCotizacion:Entrando... Catalogo Examenes" + lstExamenes.size());
				CEstadoRegistro objCERExamen = new CEstadoRegistro();					
				objCERExamen.setCestadoregistro(new Integer(13));
				for (int i = 0; i < lstExamenes.size() ; i++)
				{
					objDExamen = (OrdenExamenBean)lstExamenes.get(i);						

					if ((strVolumenesExamenes.trim().length() == 0) && (objDExamen.getCperfil() == -1)) {
						shrVolumenExamen = 1;
					} else {
						iObjLog.debug("Entrando a DatosExamenAjax.actualizaExamenes:Entrando... Catalogo Volumen " + strVolumenesExamenes.trim() + " Examen " + objDExamen.getCexamen());
						if (strVolumenesExamenes.trim().indexOf(objDExamen.getCexamen() + "") == -1) {
							shrVolumenExamen = 1;
						} else {
							strExamenVolumen = strVolumenesExamenes.trim().substring(strVolumenesExamenes.trim().indexOf(objDExamen.getCexamen() + ""));
							shrVolumenExamen = (short) Integer.parseInt(strExamenVolumen.substring(strExamenVolumen.indexOf(":")+1,strExamenVolumen.indexOf(":")+3));
						}
					}
//					shrVolumenExamen = 1;
					objDExamen.setUvolumenexamen(shrVolumenExamen);
					dblSubTotal += (objDExamen.getMsubtotal() * objDExamen.getUvolumenexamen());
					dblDescuentoEmpresa += (objDExamen.getMdescuentoempresa() * objDExamen.getUvolumenexamen());
					dblDescuentoMedico += (objDExamen.getMdescuentomedico() * objDExamen.getUvolumenexamen());
					dblDescuentoPromocion += (objDExamen.getMdescuentopromocion() * objDExamen.getUvolumenexamen());
					dblFacturaEmpresa += (objDExamen.getMfacturaempresa() * objDExamen.getUvolumenexamen());
					dblIVA += (objDExamen.getMiva() * objDExamen.getUvolumenexamen());
					dblPagoPaciente += (objDExamen.getMpagopaciente() * objDExamen.getUvolumenexamen());
					dblTotal += (objDExamen.getMtotal() * objDExamen.getUvolumenexamen());										
					objBOrdenExamen = new TOrdenExamenSucursalCotizacion();		
					objBOrdenExamen = objConvert.convertOrdenExamenBeanHBOrdenExamenCotizacion(objDExamen, objSucursalBean, "CotizacionOrdenesAjax.guardarCotizacion");
					iObjLog.debug("Entrando a CotizacionOrdenesAjax.guardarCotizacion:Entrando... ExamenesHB" + objBOrdenExamen.getCexamen() + " Convenio " + objBOrdenExamen.getCconvenio() + " " + objBOrdenExamen.getMfacturaempresa().doubleValue());
//					objBOrdenExamen.setDresultadoentrega(newDate);
//					objBOrdenExamen.setUmuestra(objToolDao.getSequenceNextId("tmuestra_sequence",objConexion).intValue());
//					objBOrdenExamen.setUserid(new BigDecimal(intUsuario));					
//					iObjLog.debug("Entrando a CotizacionOrdenesAjax.guardarCotizacion:Fechas... " + i + " " + newDate.toString() +  " " + newDate.compareTo(oldDate));
//					if (newDate.compareTo(oldDate) > 0) { 
//						oldDate = newDate;
//					}
					lstExameneshbm.add(objBOrdenExamen);
				}				
				objSucursal.setCsucursal(objSucursalBean.getCsucursal());
//				objBOrden.setBregistroactivo(true);
					CEstadoRegistro objCEROrden = new CEstadoRegistro();					
					objCEROrden.setCestadoregistro(new Integer(13));
				objBOrden.setCestadoregistro(objCEROrden);
					CMarca objMarca = new CMarca();
					objMarca.setCmarca(new Integer(objSucursalBean.getCmarca()));
				objBOrden.setCmarca(objMarca);
					CMedico objMedico = new CMedico();
					objMedico.setCmedico(new Integer(intMedico));
				objBOrden.setCmedico(objMedico);
				objBOrden.setCsucursal(objSucursal);
//				objBOrden.setCsucursalbycsucursalentrega(objSucursal);
//				objBOrden.setDcierre(new Date());
				objBOrden.setDregistro(new Date());
//				objBOrden.setDresultadoentrega(new Formatos().getFecha(strFechaEntrega));
				objBOrden.setMsubtotal(new BigDecimal(Formatos.redondeo2decimales(dblSubTotal)));
				objBOrden.setMdescuentoempresa(new BigDecimal(Formatos.redondeo2decimales(dblDescuentoEmpresa)));
				objBOrden.setMdescuentomedico(new BigDecimal(Formatos.redondeo2decimales(dblDescuentoMedico)));
				objBOrden.setMdescuentopromocion(new BigDecimal(Formatos.redondeo2decimales(dblDescuentoPromocion)));
				objBOrden.setMfacturaempresa(new BigDecimal(Formatos.redondeo2decimales(dblFacturaEmpresa)));
				objBOrden.setMiva(new BigDecimal(Formatos.redondeo2decimales(dblIVA)));
				objBOrden.setMpagopaciente(new BigDecimal(Formatos.redondeo2decimales(dblPagoPaciente)));
				objBOrden.setMtotal(new BigDecimal(Formatos.redondeo2decimales(dblTotal)));
				short piva = 16;
				objBOrden.setPiva(piva);
				objBOrden.setSmedico(strMedico);
				objBOrden.setSobservacion("" + strObservaciones);
				objBOrden.setTpaciente(objPaciente);
				objBOrden.setSsucursal(objSucursalBean.getSsucursal());
//				objBOrden.setUorden(objToolDao.getSequenceNextId("tordensucursal" + intSucursal + "_sequence" ,objConexion).intValue());
				objBOrden.setUserId(new BigDecimal(intUsuario));
				objBOrden.setUserIdChange(new BigDecimal(intUsuario));				
				objBOrden.setCconvenio(intConvenio);
				objCotizacionBean.setObjtordensucursalcotizacion(objBOrden);
				objCotizacionBean.setLstexamenesOrdenexamensucursalcotizacion(lstExameneshbm);
				iObjLog.debug("Entrando a CotizacionOrdenesAjax.guardarCotizacion:Entrando... Persistir Examenes" + objCotizacionBean.getLstexamenesOrdenexamensucursalcotizacion().size());				
				objCotizacionBean = objCotizacionOrdenesDao.guardarNuevaCotizacion(objCotizacionBean);
			}
		} catch (Exception aObjException){
    	    iObjLog.error("CotizacionOrdenesAjax.guardarCotizacion:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objExamenCatalogo = null;
			objCotizacionOrdenesDao = null;		
		}
		return objCotizacionBean.getObjordenbean(); 	
	}	
	
	public String[] consultaCotizacionesGrid(int kPaciente) throws Exception	
	{
		iObjLog.debug("Entrando a CotizacionOrdenesAjax.consultaCotizacionesGrid:Entrando...Parametros... " + kPaciente);
		CotizacionOrdenesDao objCotizacionOrdenesDao = new CotizacionOrdenesDao();
		int intSizeOrdenes = 0;
		
		List lstCotizaciones = objCotizacionOrdenesDao.buscarCotizacionesVSPaciente(kPaciente,true);
		intSizeOrdenes = lstCotizaciones.size();
		String strReturn = "";		
		String strReturnArray[] = new String[2];		
		String strJavaScript = "";
		if (lstCotizaciones != null) {
			if (lstCotizaciones.size() > 6) {
				strJavaScript = "style='visibility:hidden;display:none;'";
			}
		}
		try {//883px
			 strReturn = ("<table border='0' align='center' style='width: 883px' class='tabla'>" + 
							"<tr>" + 
								"<td align='center' onClick='javascript:ocultarCotizaciones();' style='font-weight: bold; font-size: medium; color: black; font-style: normal; font-variant: normal;'> " + 
									"<a href='javascript:ocultarCotizaciones();' onClick='javascript:ocultarCotizaciones();' align='bottom' style='font-weight: bold; color: black; font-size: medium; font-style: normal; font-variant: normal;'>" + intSizeOrdenes + " COTIZACIONES DEL PACIENTE (CLICK, VER U OCULTAR)"  + 
									"</a>" + 
								"</td>" + 
							"</tr>"	+
						  "</table>" +	
						  "<div id='gridGridCotizaciones' " + strJavaScript + ">" +                
			 			  "<table border='1' align='center' style='width: 883px' class='tabla' bordercolor='#000000'>" + 
							"<tr>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Cotización" + 
								"	</font></b>" +
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Fecha" + 
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Total" + 
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Examenes y Convenio" + 
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Orden" + 
								"</th>" + 
							"</tr>" +
							"<tr>");			 
			 if (lstCotizaciones != null) {
				String strColor = "black"; 
				String strExamenes = "";
				String strExamenesCotizar = "0";
				String strLinkConvenio = "";
				for (int i = 0; i < lstCotizaciones.size() ; i++)
				{
					CotizacionBean objCotizacionBean  = (CotizacionBean)lstCotizaciones.get(i);						
					iObjLog.debug("Cada Cotizacion a CotizacionOrdenesAjax.consultaCotizacionesGrid:Entrando... " + objCotizacionBean.getObjtordensucursalcotizacion().getKordensucursalcotizacion());				
					strExamenes = "";	
					strExamenesCotizar = "0";
					for (int inti = 0; inti < objCotizacionBean.getLstexamenesOrdenexamensucursalcotizacion().size() ; inti++) {						
						TOrdenExamenSucursalCotizacion objCotizacionExamenHB = (TOrdenExamenSucursalCotizacion)objCotizacionBean.getLstexamenesOrdenexamensucursalcotizacion().get(inti);
						if (objCotizacionExamenHB.getCperfil() > 0) {
							String subcadena = objCotizacionExamenHB.getCperfil() + "";
							if (!(strExamenesCotizar.indexOf (subcadena) != -1)) {
								strExamenes += "<b>" + objCotizacionExamenHB.getCperfil() + "</b> - Perfil" + "<br>";							
								strExamenesCotizar = (strExamenesCotizar + "," + objCotizacionExamenHB.getCperfil());
							}						
						} else {
							strExamenes += "<b>" + objCotizacionExamenHB.getCexamen() + "</b>" + "-" + objCotizacionExamenHB.getSexamen() + "<br>";							
							strExamenesCotizar = (strExamenesCotizar + "," + objCotizacionExamenHB.getCexamen());
						}
					}
					strExamenes += "<br><b>CONVENIO: " + objCotizacionBean.getObjtordensucursalcotizacion().getCconvenio() + "</b>" + "<br>";							

					long lngDiferencia = this.diferenciaDates(objCotizacionBean.getObjtordensucursalcotizacion().getDregistro());					
					if ((objCotizacionBean.getObjtordensucursalcotizacion().getKordensucursal() > 0)) {
						strColor = "green";
						strLinkConvenio =  objCotizacionBean.getObjtordensucursalcotizacion().getKordensucursalcotizacion() + "";
					} else if ((objCotizacionBean.getObjtordensucursalcotizacion().getKordensucursal() == 0) && (lngDiferencia > 15)) {
						strColor = "red";
						strLinkConvenio =  objCotizacionBean.getObjtordensucursalcotizacion().getKordensucursalcotizacion() + "";
					} else {
						strColor = "black";
						strLinkConvenio = "<a href='javascript:doNothing()' onClick=\"javascript:aplicarCotizar(" + objCotizacionBean.getObjtordensucursalcotizacion().getKordensucursalcotizacion() + ",'" + strExamenesCotizar + "'," + objCotizacionBean.getObjtordensucursalcotizacion().getCconvenio() + ");\" align='bottom' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'>" + 
												objCotizacionBean.getObjtordensucursalcotizacion().getKordensucursalcotizacion() + 
										  "</a>";
					}
					strReturn +=   ("<tr>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " + 
											strLinkConvenio +
										"</td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'>"+
											new Formatos().getFechaNumeros(objCotizacionBean.getObjtordensucursalcotizacion().getDregistro()) +
										"</td>" +
										"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'>"+
											"$" + new Formatos().formateaNumero(objCotizacionBean.getObjtordensucursalcotizacion().getMtotal().doubleValue() + "") +
										"</td>" +
										"<td align='left' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " 
											+ strExamenes + 
										"</td>" + 
										"<td align='center'  style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " + "" +
											+ objCotizacionBean.getObjtordensucursalcotizacion().getKordensucursal() + 
										"</td>" + 
									 "</tr>");
				}
			}
			strReturn += ("</table></div>");
			strReturnArray[0] = strReturn;
			strReturnArray[1] = "" + intSizeOrdenes;
			return strReturnArray; 
		}catch (Exception aObjException){
    	    iObjLog.error("CotizacionOrdenesAjax.consultaCotizacionesGrid:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	

	public int expedientePacienteCotizacion(int kCotizacion) throws Exception	
	{
		int kPaciente = 0;
		CotizacionBean objCotizacionBean = null;
		CotizacionOrdenesDao objCotizacionOrdenesDao = new CotizacionOrdenesDao();
		iObjLog.debug("Entrando a CotizacionOrdenesAjax.expedientePacienteCotizacion:Entrando...Parametros... " + kCotizacion);
		try {
			objCotizacionBean = objCotizacionOrdenesDao.buscarCotizaciones(kCotizacion);
			if (objCotizacionBean != null) {
				kPaciente = objCotizacionOrdenesDao.buscarCotizaciones(kCotizacion).getObjtordensucursalcotizacion().getTpaciente().getKpaciente().intValue();
			} else {
				kPaciente = 0;
			}
		}catch (Exception aObjException){
    	    iObjLog.error("CotizacionOrdenesAjax.expedientePacienteCotizacion:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objCotizacionOrdenesDao = null;
		}
		return kPaciente; 
	}	
	
	
    private long diferenciaDates(Date dblFechaComparar) {
    	java.util.Date hoy = new Date();  
        GregorianCalendar date1 = new GregorianCalendar();
        date1.setTime(dblFechaComparar); 
        GregorianCalendar date2 = new GregorianCalendar();
        date2.setTime(hoy); 
        long dias = 0;       
        if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) {
            dias =  date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR);
        } else {
            int diasAnyo = date1.isLeapYear(date1.get(Calendar.YEAR)) ? 366 : 365;
            int rangoAnyos = date2.get(Calendar.YEAR) - date1.get(Calendar.YEAR);
            dias = (rangoAnyos * diasAnyo) + (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));
        }
    	return dias;
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
