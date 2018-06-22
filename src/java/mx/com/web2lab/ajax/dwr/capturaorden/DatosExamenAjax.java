package mx.com.web2lab.ajax.dwr.capturaorden;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;

import mx.com.web2lab.backend.beans.ap.OrdenBean;
import mx.com.web2lab.backend.beans.ap.OrdenExamenBean;
import mx.com.web2lab.backend.beans.ap.PagoPacienteBean;
import mx.com.web2lab.backend.beans.comer.ConvenioBean;
import mx.com.web2lab.backend.beans.tools.ConvertBeanvsHB;
import mx.com.web2lab.backend.beans.tools.SucursalBean;

import mx.com.web2lab.backend.dao.ap.DatosOrdenDao;
import mx.com.web2lab.backend.dao.ap.ExamenesDao;
import mx.com.web2lab.backend.dao.ap.GeneracionPasswordDao;
import mx.com.web2lab.backend.dao.ap.PagosDao;
import mx.com.web2lab.backend.dao.ap.ToolsDao;
import mx.com.web2lab.backend.dao.comer.ClientesNewDao;
import mx.com.web2lab.backend.dao.cotizaciones.CotizacionOrdenesDao;
import mx.com.web2lab.backend.dao.facturacion.electronica.sucursales.ToolFacturacionSucursalesDao;

import mx.com.web2lab.backend.hbm.om.ap.CEstadoRegistro;
import mx.com.web2lab.backend.hbm.om.ap.CMarca;
import mx.com.web2lab.backend.hbm.om.ap.CSucursal;
import mx.com.web2lab.backend.hbm.om.ap.TOrdenExamenSucursal;
import mx.com.web2lab.backend.hbm.om.ap.TOrdenSucursal;
import mx.com.web2lab.backend.hbm.om.ap.TOrdenSucursalCotizacion;
import mx.com.web2lab.backend.hbm.om.ap.TOrdenSucursalFac;
import mx.com.web2lab.backend.hbm.om.ap.TPaciente;
import mx.com.web2lab.backend.hbm.om.ap.TPagoPaciente;
import mx.com.web2lab.backend.hbm.om.ap.medico.CMedico;
import mx.com.web2lab.backend.hbm.om.fundacion.BOrdenExamenFundacion;
import mx.com.web2lab.backend.hbm.om.fundacion.BOrdenFundacion;
import mx.com.web2lab.backend.hbm.om.lis.CLugarProcesamiento;

import mx.com.web2lab.backend.util.catalogo.ListaExamenUtil;
import mx.com.web2lab.backend.util.catalogo.SucursalesUtil;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;
import mx.com.web2lab.backend.util.formatos.Formatos;
import mx.com.web2lab.util.GenericDAO;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DatosExamenAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(DatosExamenAjax.class);
 	private Date objDate = new Date();
	
	public DatosExamenAjax(){
		//Constructor redefinido para generar entradas a bitacora
		iObjLog.debug("new: Generando nueva clase FacturacionDatosAjax");
	 	objDate.setHours(0);
	 	objDate.setMinutes(0);
	 	objDate.setSeconds(0);
	}
	
	public CLugarProcesamiento getLugarProcesamiento(OrdenExamenBean objExamenBean, SucursalBean objSucursal) {
		/*
		 * 1;"LABORATORIO NEZA"
		 * 2;"LABORATORIO ECATEPEC"
		 * 3;"SUCURSAL ROMA"
         * 4;"GABINETES"
		 */
		CLugarProcesamiento objLugarProceamiento = new CLugarProcesamiento();
		if (objExamenBean.getStipocomercial().toUpperCase() == "RUTINA") {
			objLugarProceamiento.setClugarprocesamiento(new Integer(1));			
		} else if (objExamenBean.getStipocomercial().toUpperCase() == "ESPECIAL") {
			objLugarProceamiento.setClugarprocesamiento(new Integer(2));			
		} else if (objExamenBean.getStipocomercial().toUpperCase() == "MAQUILA") {
			objLugarProceamiento.setClugarprocesamiento(new Integer(3));
		} else if (objExamenBean.getStipocomercial().toUpperCase() == "GABINETE") {
			objLugarProceamiento.setClugarprocesamiento(new Integer(4));
		} else {
			objLugarProceamiento.setClugarprocesamiento(new Integer(1));						
		}
		if ((objLugarProceamiento.getClugarprocesamiento().intValue() < 4) && (objSucursal.getCsucursal().intValue() == 7 || objSucursal.getCsucursal().intValue() == 11 || objSucursal.getCsucursal().intValue() == 12)){
			objLugarProceamiento.setClugarprocesamiento(new Integer(2));			
		}
		return objLugarProceamiento;
	}

	public void guardarDatosAdicionales(int kOrdenSucursal,String svalor) throws Exception {
		DatosOrdenDao objDatosOrdenDao = new DatosOrdenDao();		
		try {
			objDatosOrdenDao.guardarDatosAdicionales(kOrdenSucursal, 309, svalor);
		} catch (Exception aObjException){
    	    iObjLog.error("DatosExamenAjax.guardarDatosAdicionales:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	
	
	/* Este metodo se encarga de dar de alta una nueva orden
	 * con sus examenes cotizados.
	 */
	
	public OrdenBean altaOrdenExamenes(String strExamenes,String strVolumenesExamenes, 
									   int intSucursal, int kPaciente,
									   int intUsuario,  int intMedico,
									   String strMedico, int intConvenio, 
									   String strFechaEntrega, String strObservaciones, 
									   int intOrdenSolicitada, int intAutorizacionVerResultadosMedico, int kCotizacion,String txtEntregaResultadoA, int cSucursalEntregaResultados) throws Exception	
	{
		TOrdenSucursal objBOrden = new TOrdenSucursal();
		TOrdenExamenSucursal objBOrdenExamen = null;
		TPaciente objPaciente = new TPaciente(); 
		OrdenExamenBean objDExamen = null;
		List lstExameneshbm = new ArrayList();
		List lstReturn = new ArrayList();
		List lstExamenes = new ArrayList();
		ListaExamenUtil objExamenCatalogo = new ListaExamenUtil();
		GeneracionPasswordDao objGeneracionPasswordDao = new GeneracionPasswordDao();
		ToolsDao objToolDao = new ToolsDao();
		DatosOrdenDao objDatosOrdenDao = new DatosOrdenDao();		
		OrdenBean objOrdenBean = new OrdenBean();
		Connection objConexion = null;
		iObjLog.debug("Entrando a DatosExamenAjax.actualizaExamenes:Entrando... " + strExamenes);
		try {
			GenericDAO objConn = new GenericDAO();
			objConexion = objConn.getConnection();			
		 	lstExamenes = objExamenCatalogo.cotizarProductosConvenio(strExamenes , intSucursal , intConvenio);		 	    	
		 	Date newDate = null;
		 	Date oldDate = new Date();
			CSucursal objSucursal = new CSucursal();
			CSucursal objSucursalEntrega = new CSucursal();
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
				objPaciente.setKpaciente(new Integer(kPaciente));
				iObjLog.debug("Entrando a DatosExamenAjax.actualizaExamenes:Entrando... Catalogo Examenes" + lstExamenes.size());
				CEstadoRegistro objCERExamen = new CEstadoRegistro();					
				objCERExamen.setCestadoregistro(new Integer(13));
				String strExamen = "";
				for (int i = 0; i < lstExamenes.size() ; i++)
				{
					objDExamen = (OrdenExamenBean)lstExamenes.get(i);											
					strExamen  = "";
					if ((strVolumenesExamenes.trim().length() == 0) && (objDExamen.getCperfil() == -1)) {
						shrVolumenExamen = 1;
					} else {
						iObjLog.debug("Entrando a DatosExamenAjax.actualizaExamenes:Entrando... Catalogo Volumen " + strVolumenesExamenes.trim() + " Examen " + objDExamen.getCexamen());
						if (strVolumenesExamenes.trim().indexOf(objDExamen.getCexamen() + "") == -1) {
							shrVolumenExamen = 1;
						} else {
							strExamen = strVolumenesExamenes.trim().substring(strVolumenesExamenes.trim().indexOf(objDExamen.getCexamen() + ""));
							shrVolumenExamen = (short) Integer.parseInt(strExamen.substring(strExamen.indexOf(":")+1,strExamen.indexOf(":")+3));
						}
					}
					objDExamen.setUvolumenexamen(shrVolumenExamen);
					newDate = this.calculafechapromesa(1);
					dblSubTotal += (objDExamen.getMsubtotal()*objDExamen.getUvolumenexamen());
					dblDescuentoEmpresa += (objDExamen.getMdescuentoempresa()*objDExamen.getUvolumenexamen());
					dblDescuentoMedico += (objDExamen.getMdescuentomedico()*objDExamen.getUvolumenexamen());
					dblDescuentoPromocion += (objDExamen.getMdescuentopromocion()*objDExamen.getUvolumenexamen());
					dblFacturaEmpresa += (objDExamen.getMfacturaempresa()*objDExamen.getUvolumenexamen());
					dblIVA += (objDExamen.getMiva()*objDExamen.getUvolumenexamen());
					dblPagoPaciente += (objDExamen.getMpagopaciente()*objDExamen.getUvolumenexamen());
					dblTotal += (objDExamen.getMtotal()*objDExamen.getUvolumenexamen());										
					objBOrdenExamen = new TOrdenExamenSucursal();		
					objBOrdenExamen = objConvert.convertOrdenExamenBeanHB(objDExamen, objSucursalBean, "DatosExamenAjax.actualizaExamenes");
					iObjLog.debug("Entrando a DatosExamenAjax.actualizaExamenes:Entrando... ExamenesHB" + objBOrdenExamen.getCexamen().getCexamen().intValue() + " Convenio " + objBOrdenExamen.getCconvenio().getCconvenio().intValue() + " " + objBOrdenExamen.getMfacturaempresa().doubleValue());
					objBOrdenExamen.setDresultadoentrega(newDate);
					objBOrdenExamen.setUmuestra(objToolDao.getSequenceNextId("tmuestra_sequence",objConexion).intValue());
					objBOrdenExamen.setUserid(new BigDecimal(intUsuario));					
					iObjLog.debug("Entrando a DatosExamenAjax.actualizaExamenes:Fechas... " + i + " " + newDate.toString() +  " " + newDate.compareTo(oldDate));
					if (newDate.compareTo(oldDate) > 0) { 
						oldDate = newDate;
					}
					lstExameneshbm.add(objBOrdenExamen);
				}				
				objSucursal.setCsucursal(objSucursalBean.getCsucursal());
				objSucursalEntrega.setCsucursal(new Integer(cSucursalEntregaResultados));
				objBOrden.setBregistroactivo(true);
					CEstadoRegistro objCEROrden = new CEstadoRegistro();					
					objCEROrden.setCestadoregistro(new Integer(13));
					objCEROrden.setCestadoregistro(new Integer(14));
				objBOrden.setCestadoregistro(objCEROrden);
					CMarca objMarca = new CMarca();
					objMarca.setCmarca(new Integer(objSucursalBean.getCmarca()));
				objBOrden.setCmarca(objMarca);
					CMedico objMedico = new CMedico();
					objMedico.setCmedico(new Integer(intMedico));
					objBOrden.setCmedico(objMedico);
				objBOrden.setCsucursalbycsucursal(objSucursal);
				objBOrden.setCsucursalbycsucursalentrega(objSucursalEntrega);
				objBOrden.setDcierre(new Date());
				objBOrden.setDregistro(new Date());
				objBOrden.setDresultadoentrega(new Formatos().getFecha(strFechaEntrega));
				objBOrden.setMsubtotal(new BigDecimal(this.redodedoDouble(dblSubTotal)));
				objBOrden.setMdescuentoempresa(new BigDecimal(this.redodedoDouble(dblDescuentoEmpresa)));
				objBOrden.setMdescuentomedico(new BigDecimal(this.redodedoDouble(dblDescuentoMedico)));
				objBOrden.setMdescuentopromocion(new BigDecimal(this.redodedoDouble(dblDescuentoPromocion)));
				objBOrden.setMfacturaempresa(new BigDecimal(this.redodedoDouble(dblFacturaEmpresa)));
				objBOrden.setMiva(new BigDecimal(this.redodedoDouble(dblIVA)));
				objBOrden.setMpagopaciente(new BigDecimal(this.redodedoDouble(dblPagoPaciente)));
				objBOrden.setMtotal(new BigDecimal(this.redodedoDouble(dblTotal)));
				objBOrden.setCordensolicitada(intOrdenSolicitada);
				objBOrden.setBautorizacionverresultadosmedico(intAutorizacionVerResultadosMedico);
				short piva = 16;
				objBOrden.setPiva(piva);
				objBOrden.setSmedico(strMedico);
				objBOrden.setSobservacion("" + strObservaciones);
				objBOrden.setSentregaresultadosa("" + txtEntregaResultadoA);
				objBOrden.setTpaciente(objPaciente);
				objBOrden.setSsucursal(objSucursalBean.getSsucursal());
				objBOrden.setUorden(objToolDao.getSequenceNextId("tordensucursal" + intSucursal + "_sequence" ,objConexion).intValue());
				objBOrden.setUserid(new BigDecimal(intUsuario));
				objBOrden.setSpassword(objGeneracionPasswordDao.getPassword());
				objBOrden.setUseridchange(new BigDecimal(intUsuario));				
				objBOrden.setCconvenio(intConvenio);
				lstReturn.add(objBOrden);
				iObjLog.debug("Entrando a DatosExamenAjax.actualizaExamenes:Entrando... Persistir Examenes" + lstExameneshbm.size());				
				lstReturn.add(lstExameneshbm);
				lstReturn = objDatosOrdenDao.setActualizaOrdenExamenes(lstReturn);
			}
		 	objOrdenBean = (OrdenBean)lstReturn.get(0);	
		 	if (kCotizacion > 0) {
		 		CotizacionOrdenesDao objCotizacionOrdenesDao = new CotizacionOrdenesDao();
		 		TOrdenSucursalCotizacion objOrdenSucursal = objCotizacionOrdenesDao.buscarCotizaciones(kCotizacion).getObjtordensucursalcotizacion();
		 		objCotizacionOrdenesDao = null;
		 		CotizacionOrdenesDao objCotizacionOrdenesSecondDao = new CotizacionOrdenesDao();
		 		objOrdenSucursal.setKordensucursal((int) objOrdenBean.getKadmision());
		 		objCotizacionOrdenesSecondDao.actualizarCotizacion(objOrdenSucursal);
		 	}
		} catch (Exception aObjException){
    	    iObjLog.error("DatosExamenAjax.actualizaExamenes:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objExamenCatalogo = null;
			objToolDao = null;
			objDatosOrdenDao = null;	
			objGeneracionPasswordDao = null;
		}
		return objOrdenBean; 	
	}	
	
	/* Este metodo se encarga de adicionar un examen a la cotizacion actual
	 * 
	 */
	public String[] newExamen(String strExamen,String strVolumenesExamenes,String strExamenes,int intSucursal,int intConvenio) throws Exception	
	{
		ListaExamenUtil objCatalogoExamenes =  new ListaExamenUtil();
		OrdenExamenBean objExamenBean = null;
		List lstExamenes = new ArrayList();
		String[] strReturn = new String[3];		
		String strCosto = "";
		short shrVolumenExamen = 0;
		String strExamenVolumen =  "";
		BigDecimal objdblPrecioOrden = new BigDecimal(0);		
		iObjLog.debug("Entrando a DatosExamenAjax.newExamen:Entrando...Examen Nuevo " + strExamen + " Examenes Anteriores " + strExamenes + " Sucursal " +  " Convenio " + intConvenio) ;
		try {
				strReturn[2] = "";
		 	    strReturn[0] = this.getEncabezadoCotizacionORCotizadoExamenes(1,1,true);	    
	 			iObjLog.debug("Entrando a DatosExamenAjax.newExamen:Entrando...Entrando 0 > ") ;
	 			if (strExamenes.trim().toString().length() > 2) {
				 	lstExamenes = objCatalogoExamenes.cotizarProductosConvenio(strExamenes + "," + strExamen , intSucursal , intConvenio);		 	    	
	 			} else {
				 	lstExamenes = objCatalogoExamenes.cotizarProductosConvenio(strExamen , intSucursal , intConvenio);		 	    	
	 			}
			 	Date newDate = null;
			 	if (lstExamenes != null) {
					for (int i = 0; i < lstExamenes.size() ; i++)
					{
						objExamenBean = (OrdenExamenBean)lstExamenes.get(i);	
						if (objExamenBean.getMfacturaempresa() >= 1500.0 && Integer.parseInt(strExamen) == objExamenBean.getCexamen() && intConvenio >= 309 && intConvenio <= 312) {
							strReturn[2] = "!!! El examen " + objExamenBean.getCexamen() + " " + objExamenBean.getSexamen() +" tiene un costo mayor a $1,500, por favor verificar la firma autorizada para el metro por favor !!!";
						}						
						if ((strVolumenesExamenes.trim().length() == 0) && (objExamenBean.getCperfil() == -1)) {
							shrVolumenExamen = 1;
						} else {
							iObjLog.debug("Entrando a DatosExamenAjax.actualizaExamenes:Entrando... Catalogo Volumen " + strVolumenesExamenes.trim() + " Examen " + objExamenBean.getCexamen());
							if (strVolumenesExamenes.trim().indexOf(objExamenBean.getCexamen() + "") == -1) {
								shrVolumenExamen = 1;
							} else {
								strExamenVolumen = strVolumenesExamenes.trim().substring(strVolumenesExamenes.trim().indexOf(objExamenBean.getCexamen() + ""));
								shrVolumenExamen = (short) Integer.parseInt(strExamenVolumen.substring(strExamenVolumen.indexOf(":")+1,strExamenVolumen.indexOf(":")+3));
							}
						}
						newDate = this.calculafechapromesa(1);
						strReturn[0] += this.getBodyExamen(objExamenBean,shrVolumenExamen, newDate); 
//						strExamenVolumenReturn += ("," + objExamenBean.getCexamen() +":" + shrVolumenExamen);
						objdblPrecioOrden = objdblPrecioOrden.add(new BigDecimal(shrVolumenExamen * objExamenBean.getMpagopaciente()));
						objExamenBean = null;
					}
					objdblPrecioOrden = objdblPrecioOrden.setScale(0, BigDecimal.ROUND_HALF_UP);								
					strCosto = ("<table border='0' align='center' style='width: 883px' class='tabla'>" + 
								"<tr>" + 
								"	<th colspan='6'>" + 
								"	<center>" + 
								"	<b style='font-weight: bold; font-size: medium; color: black; font-style: normal; font-variant: normal'>Precio Total $" + /*this.redodedoDouble(dblTotal)*/objdblPrecioOrden + "</b>" + 
								"	<input type='button' id='idIndicacionesOrden' value='Indicaciones Orden' onClick='informacionExamenes();' class='boton'>" +
								"	</center>" + 
								"	</th>" +      
								"</tr>" + 
							    "</table>");				
			}
			strReturn[0] += ("</table>" + strCosto);
			strReturn[1] = (strExamen + "," + strExamenes);
//			strReturn[2] = (strExamenVolumenReturn);
			return strReturn; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosExamenAjax.newExamen:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			lstExamenes.clear();
			lstExamenes = null;
		}
	}	
			
    private double redodedoDouble(double nD) {
		return Math.round(nD*Math.pow(10,2))/Math.pow(10,2);      	
    }
	
	public String[] persistentesExamenes(int intAdmision,String strExamenes, boolean bolGerente) throws Exception	
	{
		ExamenesDao objDAOExamenes = new ExamenesDao();
		DatosOrdenDao objDAOOrden = new DatosOrdenDao();
		OrdenBean objBOrden = new OrdenBean();
		OrdenExamenBean objExamenFundacion = null;
		boolean bolOrdenCancelada = true;
		List lstExamenes = null;
		String strBodyExamenes = "";
 		String[] strReturn = new String[2];		
		iObjLog.debug("Entrando a DatosExamenAjax.persistentesExamenes:Entrando... " + intAdmision + " " + strExamenes + " RolGerente " + bolGerente);
		try {
			objBOrden = objDAOOrden.buscarOrdenOnly(intAdmision);
			lstExamenes = objDAOExamenes.buscarpersistemExamenes(intAdmision);
			if (lstExamenes != null) {
				Iterator itrExamenes = lstExamenes.iterator();
				while(itrExamenes.hasNext()) {
					objExamenFundacion = (OrdenExamenBean)itrExamenes.next();					
					iObjLog.debug("Consulta a DatosExamenAjax.persistentesExamenes:Estado...Examen " + objExamenFundacion.getCexamen() + " " + objExamenFundacion.getCestadoregistro());					
					if( objExamenFundacion.getCestadoregistro() == 13) {
						iObjLog.debug("Consulta a DatosExamenAjax.persistentesExamenes:Estado...Orden " + objBOrden.getKadmision() + " " + objBOrden.getCestado());					
						if (objBOrden.getCestado() == 14) {
							strBodyExamenes += this.createBodyExamenCotizados(objExamenFundacion,"black",true,bolGerente); 
						} else {
							objExamenFundacion.setSmotivocancelacion("");
							strBodyExamenes += this.createBodyExamenCotizados(objExamenFundacion,"black",false,bolGerente); 							
						}
						bolOrdenCancelada = false;
					} else {
						strBodyExamenes += this.createBodyExamenCotizados(objExamenFundacion,"red",false,bolGerente); 
					}
				}
			}
			if (bolOrdenCancelada) {
				strReturn[0] = this.getEncabezadoCotizacionORCotizadoExamenes(2,2,bolGerente) + strBodyExamenes;	    
			} else {
				strReturn[0] = this.getEncabezadoCotizacionORCotizadoExamenes(2,objBOrden.getCestado(),bolGerente) + strBodyExamenes;	    				
			}
			strReturn[0] += ("</table>");
			strReturn[1] = (strExamenes);
			iObjLog.debug("Entrando a DatosExamenAjax.persistentesExamenes:Salida... " + strReturn[0]);
			return strReturn; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosExamenAjax.persistentesExamenes:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	

	
	public String[] persistentesExamenesFac(int intAdmision,int intCsucursal,String strExamenes) throws Exception	
	{
		ExamenesDao objDAOExamenes = new ExamenesDao();
		DatosOrdenDao objDAOOrden = new DatosOrdenDao();
		OrdenBean objBOrden = new OrdenBean();
		OrdenExamenBean objExamenFundacion = null;
		boolean bolOrdenCancelada = true;
		List lstExamenes = null;
		String strBodyExamenes = "";
 		String[] strReturn = new String[2];		
		iObjLog.debug("Entrando a DatosExamenAjax.persistentesExamenesFac:Entrando... " + intAdmision + " " + strExamenes);
		try {
			objBOrden = objDAOOrden.buscarOrdenOnly(intAdmision);
			lstExamenes = objDAOExamenes.buscarpersistemExamenesFac(intAdmision);
			if (lstExamenes != null) {
				Iterator itrExamenes = lstExamenes.iterator();
				while(itrExamenes.hasNext()) {
					objExamenFundacion = (OrdenExamenBean)itrExamenes.next();					
					iObjLog.debug("Consulta a DatosExamenAjax.persistentesExamenesFac:Estado...Examen " + objExamenFundacion.getCexamen() + " " + objExamenFundacion.getCestadoregistro());					
					if( objExamenFundacion.getCestadoregistro() == 13) {
						iObjLog.debug("Consulta a DatosExamenAjax.persistentesExamenesFac:Estado...Orden " + objBOrden.getKadmision() + " " + objBOrden.getCestado());					
						if (objBOrden.getCestado() == 14) {
							if(intCsucursal != 1003 || intCsucursal != 1012 ||intCsucursal != 1013){
								strBodyExamenes += this.createBodyExamenCotizados(objExamenFundacion,"black",true,true); 
							}else{
								strBodyExamenes += this.showBodyExamen(objExamenFundacion,"black",true,true); 
							}
						} else {
							objExamenFundacion.setSmotivocancelacion("");
							if(intCsucursal != 1003 || intCsucursal != 1012 ||intCsucursal != 1013){
								strBodyExamenes += this.createBodyExamenCotizados(objExamenFundacion,"black",false,true);
							}else{
								strBodyExamenes += this.showBodyExamen(objExamenFundacion,"black",false,true); 							
							}
						}
						bolOrdenCancelada = false;
					} else {
						if(intCsucursal != 1003 || intCsucursal != 1012 ||intCsucursal != 1013){
							strBodyExamenes += this.createBodyExamenCotizados(objExamenFundacion,"red",false,true);
						} else{
							strBodyExamenes += this.showBodyExamen(objExamenFundacion,"red",false,true); 
						}
					}
				}
			}
			if (bolOrdenCancelada) {
				strReturn[0] = this.getEncabezadoCotizacionORCotizadoExamenes(2,2,false) + strBodyExamenes;	    
			} else {
				strReturn[0] = this.getEncabezadoCotizacionORCotizadoExamenes(2,objBOrden.getCestado(),false) + strBodyExamenes;	    				
			}
			strReturn[0] += ("</table>");
			strReturn[1] = (strExamenes);
			iObjLog.debug("Entrando a DatosExamenAjax.persistentesExamenesFac:Salida... " + strReturn[0]);
			return strReturn; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosExamenAjax.persistentesExamenesFac:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	
	
	
	public OrdenBean newExamenOrdenDELTE(String strExamen,String strExamenes,int intSucursal,int kAdmision,int intUsuario) throws Exception	
	{
		/** Utilerias ***/
		ListaExamenUtil objExamenCatalogo = new ListaExamenUtil();
		ConvertBeanvsHB objConvert = new ConvertBeanvsHB();
		/** DAOS ***/
		DatosOrdenDao objDAOOrden = new DatosOrdenDao();		
		ExamenesDao objDAOExamenes = new ExamenesDao();		
		PagosDao objPagoDao = new PagosDao();  
		ToolsDao objToolDao = new ToolsDao();
		Connection objConexion = null;
		/** Variables ***/
		List lstExamenes = new ArrayList();
		OrdenBean objOrdenBean = null;
		OrdenExamenBean objOrdenExamenBean = null;
		TOrdenSucursal objTOrdenSucursal = null;
		TOrdenExamenSucursal objOrdenExamenSucursal = null;
		TPagoPaciente objLastPago = null;
		PagoPacienteBean objNewPago = new PagoPacienteBean();
		/****************/
		iObjLog.debug("Entrando a DatosExamenAjax.newExamenOrden:Entrando... " + kAdmision + " " + strExamen );
		try {			  			
			GenericDAO objConn = new GenericDAO();
			objConexion = objConn.getConnection();		
			SucursalBean objSucursalBean = (new SucursalesUtil()).getSucursal(intSucursal);
			lstExamenes = objDAOExamenes.buscarpersistemExamenes(kAdmision);			
			objLastPago = objPagoDao.getPagoLast(kAdmision);
			objTOrdenSucursal  = (TOrdenSucursal)objDAOOrden.buscarOrden(kAdmision).get(0);
			objOrdenBean = objConvert.convertOrdenHBBean((objTOrdenSucursal), objLastPago, "DatosExamenAjax.newExamenOrden",false,false); 	
			iObjLog.debug("Consulta a DatosExamenAjax.newExamenOrden:Consulta...Estado Orden " + objOrdenBean.getCestado() );
			if (objOrdenBean.getCestado() == 14) {			
//				objOrdenExamenBean = (OrdenExamenBean)objExamenCatalogo.getProdctos(strExamen, intSucursal).get(0);
				objOrdenExamenBean.calculaFechaPromesa(1);
				objOrdenExamenBean.setUmuestra(objToolDao.getSequenceNextId("tmuestra_sequence",objConexion).intValue());
				objOrdenExamenBean.setUserid(intUsuario);
				objOrdenExamenBean.setBolnewExamen(true);
				objOrdenBean.getLstExamenes().clear();
				objOrdenBean.loadExamen(objOrdenExamenBean);
				objOrdenExamenSucursal = objConvert.convertOrdenExamenBeanHB(objOrdenExamenBean, objSucursalBean, "DatosExamenAjax.newExamenOrden");
				objOrdenExamenSucursal.setTordensucursal(objTOrdenSucursal);
				objDAOExamenes.setGuardaExamenes(objOrdenExamenSucursal);
				iObjLog.debug("Consulta a DatosExamenAjax.newExamenOrden:Consulta...Examenes Anteriores " + lstExamenes.size() );
				for (int inti=0;inti<lstExamenes.size();inti++) {
					objOrdenBean.loadExamen((OrdenExamenBean)lstExamenes.get(inti));				
				}
				if (objLastPago != null ) {
					objNewPago.setCestadoregistro(objLastPago.getCestadoregistro().getCestadoregistro().intValue());
					objNewPago.setCtipopago(objLastPago.getCtipopago().getCtipopago().intValue());
					objNewPago.setDregistro(new Date());
					objNewPago.setKpagopaciente(0);
					objNewPago.setManticipo(objLastPago.getManticipo().doubleValue() + objLastPago.getMpagopacienteparcial().doubleValue());
					objNewPago.setMdevolucionpaciente(0.0);
					objNewPago.setMpagopacienteparcial(0.0);
					objNewPago.setMpagopacientetotal(objOrdenBean.getMpagapaciente());
					objNewPago.setMsaldo(objOrdenBean.getMpagapaciente() - objNewPago.getManticipo());
					objNewPago.setTordensucursal(objTOrdenSucursal.getKordensucursal().intValue());
					objNewPago.setUserid(intUsuario);
					objPagoDao.setPago(objNewPago);								
//					objLastPago = objPagoDao.getPagoLast(kAdmision);
					objOrdenBean.setMacuenta((objNewPago.getManticipo()));
					objOrdenBean.setMadeuda((objNewPago.getMsaldo()));
				} else {
					objOrdenBean.setMacuenta(0.0);
					objOrdenBean.setMadeuda(objOrdenBean.getMpagapaciente());
				}
				objTOrdenSucursal.setMsubtotal(new BigDecimal(objOrdenBean.getMsubtotal()));
				objTOrdenSucursal.setMdescuentoempresa(new BigDecimal(objOrdenBean.getMdescuentoempresa()));
				objTOrdenSucursal.setMdescuentomedico(new BigDecimal(objOrdenBean.getMdescuento()));
				objTOrdenSucursal.setMdescuentopromocion(new BigDecimal(objOrdenBean.getMdescuentopaciente()));
				objTOrdenSucursal.setMfacturaempresa(new BigDecimal(objOrdenBean.getMfacturaempresa()));
				objTOrdenSucursal.setMiva(new BigDecimal(objOrdenBean.getMiva()));
				objTOrdenSucursal.setMpagopaciente(new BigDecimal(objOrdenBean.getMpagapaciente()));
				objTOrdenSucursal.setMtotal(new BigDecimal(objOrdenBean.getMtotal()));
				objTOrdenSucursal = objDAOOrden.setActualizaOrden(objTOrdenSucursal);
			} 
			iObjLog.debug("Saliendo a DatosExamenAjax.newExamenOrden:Saliendo... " + objOrdenBean.getKadmision() + " " + objOrdenBean.getLstExamenes().size());
		 	return objOrdenBean;  	
		}catch (Exception aObjException){
    	    iObjLog.error("DatosExamenAjax.newExamenOrden:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	

	
	public String[] eliminarExamen(int intExameneliminar,String strVolumenesExamenes,String strExamenes,int intSucursal,int intConvenio) throws Exception	
	{
		ListaExamenUtil objCatalogoExamenes =  new ListaExamenUtil();
		OrdenExamenBean objDExamen = null;
		List lstExamenes = new ArrayList();
		String[] strReturn = new String[2];		
		String strCosto = "";
		String strExamenesReturn = "0";
		short shrVolumenExamen = 0;
		String strExamenVolumen =  "";
		BigDecimal objdblPrecioOrden = new BigDecimal(0);		
		iObjLog.debug("Entrando a DatosExamenAjax.newExamen:Entrando... examen a eliminar " + intExameneliminar);
		try {
				String[] strResultantes = strExamenes.split(",");
				int intValor = 0;
				for (int inti=0;inti<strResultantes.length;inti++) {
					intValor = Integer.parseInt(strResultantes[inti]);
					if (!(intExameneliminar == intValor)) {
						strExamenesReturn += "," + intValor;
					}
				}
			 	strReturn[0] = this.getEncabezadoCotizacionORCotizadoExamenes(1,1,true);	    
			 	lstExamenes = objCatalogoExamenes.cotizarProductosConvenio(strExamenesReturn, intSucursal , intConvenio);		 	    				 	
			 	Date newDate = null;
			 	if (lstExamenes != null) {
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
						newDate = this.calculafechapromesa(1);
						strReturn[0] += this.getBodyExamen(objDExamen,shrVolumenExamen, newDate); 
						objdblPrecioOrden = objdblPrecioOrden.add(new BigDecimal(objDExamen.getMpagopaciente() * shrVolumenExamen));							
					}
					objdblPrecioOrden = objdblPrecioOrden.setScale(0, BigDecimal.ROUND_HALF_UP);								
					strCosto = ("<table border='0' align='center' style='width: 883px' class='tabla'>" + 
							"<tr>" + 
							"	<th colspan='6'>" + 
							"	<center>" + 
							"	<b style='font-weight: bold; font-size: medium; color: black; font-style: normal; font-variant: normal'>Precio Total $" + objdblPrecioOrden + "</b>" + 
							"	<input type='button' id='idIndicacionesOrden' value='Indicaciones Orden' onClick='informacionExamenes();' class='boton'>" +
							"	</center>" + 
							"	</th>" +      
							"</tr>" + 
						    "</table>");
					
			 	}
			strReturn[0] += ("</table>" + strCosto);
			strReturn[1] = (strExamenesReturn);
			return strReturn; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosExamenAjax.newExamen:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	

	public String informacionExamenes(String strExamenes) throws Exception	
	{
		ExamenesDao objDAOExamenes = new ExamenesDao();		
		String strReturn = "";
		OrdenExamenBean objExamen = null;
		iObjLog.debug("Entrando a DatosExamenAjax.informacionExamenes:Entrando...Examenes " + strExamenes) ;
		try {
			List lstExamenes = objDAOExamenes.informacionExamen(strExamenes);
			if (lstExamenes != null) {
				if (lstExamenes.size() > 0) {
					for(int inti=0 ;inti<lstExamenes.size();inti++) {
						objExamen = (OrdenExamenBean)lstExamenes.get(inti);
						strReturn = strReturn +("<table border='0' align='center' style='width: 883px' class='tabla'>" + 
												"<tr>" + 
												"	<th colspan='6'>" + 
												"	<center>" + 
												"	<b style='font-weight: bold; font-size: medium; color: black; font-style: normal; font-variant: normal'>Datos Adicionales Examen " + objExamen.getCexamen() + "</b>" + 
												"	</center>" + 
												"	</th>" +      
												"</tr>" + 
												"<tr>" + 
												"	<td style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal'>" +
												"	    <b style='font-weight: normal; font-size: x-small; color: red; font-style: normal; font-variant: normal'>Codigo:</b>" + objExamen.getCexamen() +
												"	</td>" +
												"</tr>" +
												"<tr>" + 
												"	<td style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal'>" +
												"	    <b style='font-weight: normal; font-size: x-small; color: red; font-style: normal; font-variant: normal'>Nombre:</b> " + objExamen.getSexamen() +
												"	</td>" +
												"</tr>" +
												"<tr>" + 
												"	<td style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal'>" +
												"	    <b style='font-weight: normal; font-size: x-small; color: red; font-style: normal; font-variant: normal'>Indic. Paciente:</b> " + objExamen.getSindicacionpaciente() +
												"	</td>" +
												"</tr>" +
												"<tr>" + 
												"	<td style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal'>" +
												"	    <b style='font-weight: normal; font-size: x-small; color: red; font-style: normal; font-variant: normal'>Indic. Tomador:</b> " + objExamen.getSindicaciontomador() +
												"	</td>" +
												"</tr>" +
												"<tr>" + 
												"	<td style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal'>" +
												"	    <b style='font-weight: normal; font-size: x-small; color: red; font-style: normal; font-variant: normal'>Insumo:</b> " + objExamen.getSinsumo() +
												"	</td>" +
												"</tr>" +
												"<tr>" + 
												"	<td style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal'>" +
												"	    <b style='font-weight: normal; font-size: x-small; color: red; font-style: normal; font-variant: normal'>Rechazo:</b> " + objExamen.getSmotivorechazo() +
												"	</td>" +
												"</tr>" +
												"<tr>" + 
												"	<td style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal'>" +
												"	    <b style='font-weight: normal; font-size: x-small; color: red; font-style: normal; font-variant: normal'>Temp:</b> " + objExamen.getStemperaturamuestra() +
					 							"	</td>" +
												"</tr>" +
												"<tr>" + 
												"	<td style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal'>" +
												"	    <b style='font-weight: normal; font-size: x-small; color: red; font-style: normal; font-variant: normal'>Tipo Muestra:</b> " + objExamen.getStipomuestra() +
												"	</td>" +
												"</tr></table>");						
					}
				}
			}
		}catch (Exception aObjException){
    	    iObjLog.error("DatosExamenAjax.informacionExamenes:Exception....", aObjException);
    	    throw aObjException;
		}
		return strReturn; 
	}			

	
	
	private String getEncabezadoCotizacionORCotizadoExamenes(int intType, int intEstadoOrden,boolean bolGerente) {
		String strReturn = "";
		strReturn = ("<table border='0' align='center' style='width: 883px' class='tabla'>" + 
					"<tr>" + 
					"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
					"	<b><font color='black'>C&oacute;digo" + 
					"	</font></b>" +
					"</th>" + 
					"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
					"	<b><font color='black'>Examen" + 
					"</th>" + 
					"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
					"	<b><font color='black'>SubTotal" + 
					"	</font></b>" +
					"</th>" + 
					"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
					"	<b><font color='black'>Descuento" + 
					"	</font></b>" +
					"</th>" + 
					"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
					"	<b><font color='black'>Total" + 
					"	</font></b>" +
					"</th>" + 
					"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
					"	<b><font color='black'>Fecha Promesa" + 
					"	</font></b>" +
					"</th>" + 
					"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
					"	<b><font color='black'>Id Muestra" + 
					"	</font></b>" +
					"</th>" + 
					"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
					"	<b><font color='black'>Perfil" + 
					"	</font></b>" +
					"</th>" + 
					"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
					"	<b><font color='black'>No Exám" + 
					"	</font></b>" +
					"</th>" + 
					"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>");
			if (intType == 1) {
				strReturn += ("	<b><font color='black'>Eliminar" + 
							  "	</font></b>" +
							  "</th>" + 
							  "</tr>");				
			} else {
				if (intEstadoOrden == 14) {
					if (bolGerente) {
						strReturn += ("<input type='checkbox' id='chkCancelarFactura' onClick='cancelarFactura();'><b><font color='black'>Cancelar Factura o Examen" + 
									  "</font></b>" +
									  "</th>");								
					} else {
						strReturn += ("<b><font color='black'>Cancelar" + 
									  "</font></b>" +
									  "</th>");														
					}
					strReturn += ("<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								  "	<b><font color='black'>Muestra Pendiente?" + 
								  "	</font></b>" +
								  "</th></tr>");								
				} else {
					strReturn += ("<b><font color='black'>Motivo Cancelacion" + 
								  "</font></b>" +
								  "</th>" + 
							  	  "</tr>");								
				}
			}
		return strReturn;			    
	}

	private String getBodyExamen(OrdenExamenBean objDExamen,short shrVolumen,Date newDate) {
		String strPerfil = "";
		String strCodigoEliminar = "";
		String strVolumen = "";
		if (objDExamen.getCperfil() == -1) {
			strPerfil = "";		
			strCodigoEliminar = "" + objDExamen.getCexamen();
		} else {
			strPerfil = objDExamen.getCperfil() + " " + objDExamen.getSperfil();
			strCodigoEliminar = "" + objDExamen.getCperfil();
		}
		if (objDExamen.getUvolumenexamen() == 1) {
			strVolumen = "<select id='selVolumen' onchange='javascript:changeVolumen(" + objDExamen.getCexamen() + ",this);'>" +
						 "		<option value='1' selected>1</option>" +
				         "</select>";
		} else if (objDExamen.getUvolumenexamen() > 1) {
			strVolumen = "<select id='selVolumen' onchange='javascript:changeVolumen(" + objDExamen.getCexamen() + ",this);'>";
						for (int inti=1;inti<(objDExamen.getUvolumenexamen()+1);inti++) {
							if (shrVolumen == inti) {
								strVolumen = strVolumen + "<option value='" + Formatos.formateaNumero2Digitos(inti+"") + "' selected>" + Formatos.formateaNumero2Digitos(inti+"") + "</option>";
							} else {
								strVolumen = strVolumen + "<option value='" + Formatos.formateaNumero2Digitos(inti+"") + "'>" + Formatos.formateaNumero2Digitos(inti+"") + "</option>";
							}
						}
			strVolumen = strVolumen + "</select>";
		} else {
			strVolumen = "Examen MAL Configurado";			
		}
		return ("<tr>" + 
				"	<td align='center'>" + 
						objDExamen.getCexamen() + 
				"	</td>" + 
				"	<td align='center'>"+
						objDExamen.getSexamen()+
				"	</td>" +
				"	<td align='center'>"+
						"$ " + (objDExamen.getMsubtotal() * shrVolumen)+
				"	</td>" +
				"	<td align='center'>"+
						"$ " + ((objDExamen.getMdescuentoempresa() + objDExamen.getMdescuentomedico() + objDExamen.getMdescuentopromocion() + objDExamen.getMfacturaempresa()) * shrVolumen) +
				"	</td>" +
				"	<td align='center'>"+
						"$ " + (objDExamen.getMpagopaciente() * shrVolumen) +
				"	</td>" +
				"	<td align='center'>"+
						new Formatos().getFechaNumeros(newDate) +
				"	</td>" +
				"	<td align='center'>"+
						"" +
				"	</td>" +
				"	<td align='center'>"+
						strPerfil + 
				"	</td>" +
				"	<td align='center'>"+
						strVolumen + 
				"	</td>" +
				"	<td align='center'>"+
						"<input type='checkbox' id='chkEliminarExamen" + objDExamen.getCexamen() + "'  value='" + strCodigoEliminar + "' onClick='eliminarExamen(this);'> " +
				"	</td>" +
			 	"</tr>");
	}
	
	private String createBodyExamenCotizados(OrdenExamenBean objDExamen,String strColor, boolean bolLive, boolean bolGerente) {
		String strPerfil = "";
		String strLive = "";
		String strTomaMuestra = "";
		if (objDExamen.getCperfil() == -1) {
			strPerfil = "";			
		} else {
			strPerfil = objDExamen.getCperfil() + " " + objDExamen.getSperfil();
		}
		if ((bolLive) && (bolGerente)) {
			if (objDExamen.getCperfil() == -1) {
				strLive = "<input type='checkbox' id='chkEliminarExamen" + objDExamen.getCexamen() + "'  value='" + objDExamen.getCexamen() + "' onClick='cancelarExamen(this);'> ";
			} else {
				strLive = "<input type='checkbox' id='chkEliminarPerfil" + objDExamen.getCexamen()+objDExamen.getCperfil() + "'  value='" + objDExamen.getCperfil() + "' onClick='cancelarPerfil(this);'> ";
			}
		} else {
			strLive = objDExamen.getSmotivocancelacion();
		}
		Date objAyer = new java.sql.Date(new java.util.Date().getTime() - (1000*60*60*24));  				
		if (objDExamen.getSlogin_name().trim().length() > 0) {
			strTomaMuestra = "<font color='green'>MUESTRA TOMADA</font>";
		} else if (objDExamen.getDtomamuestrainicio().before(objAyer)) {
			strTomaMuestra = "<input type='checkbox' id='chkTomaMuestra" + objDExamen.getKordenexamensucursal() + "'  value='" + objDExamen.getKordenexamensucursal() + "' onClick='tomaMuestraPendiente(" + objDExamen.getKordenexamensucursal() + ",this);' checked> ";
		} else {
			strTomaMuestra = "<input type='checkbox' id='chkTomaMuestra" + objDExamen.getKordenexamensucursal() + "'  value='" + objDExamen.getKordenexamensucursal() + "' onClick='tomaMuestraPendiente(" + objDExamen.getKordenexamensucursal() + ",this);'> ";
		}
		objAyer = null;
		return ("<tr>" + 
				"	<td align='center' style='color: " + strColor + ";'>" 						+ 
						objDExamen.getCexamen() 							+ 
				"	</td>" + 
				"	<td align='center' style='color: " + strColor + ";'>"+
						objDExamen.getSexamen()+
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
						"$ " + (objDExamen.getMsubtotal() * objDExamen.getUvolumenexamen()) +
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
						"$ " + this.redodedoDouble2(((objDExamen.getMdescuentoempresa() + objDExamen.getMdescuentomedico() + objDExamen.getMdescuentopromocion() + objDExamen.getMfacturaempresa()) * objDExamen.getUvolumenexamen())) +
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
						"$ " + (objDExamen.getMpagopaciente() * objDExamen.getUvolumenexamen()) +
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
						new Formatos().getFechaNumeros(objDExamen.getDresultadoentrega()) +
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
		        "		<a href='javascript:imprimirEtiquetaMuestra(" + objDExamen.getUmuestra() +")' onclick='javascript:'imprimirEtiquetaMuestra(" + objDExamen.getUmuestra() +")' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal'> " +
							objDExamen.getUmuestra() +
	            "		</a>" +
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
						strPerfil + 
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
						objDExamen.getUvolumenexamen() +
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
						strLive +
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+ strTomaMuestra +
				"	</td>" +
			 	"</tr>");
	}
		
	public String[] imprimeEtiquetasExamenes(int kAdmision) throws Exception	
	{
		iObjLog.debug("Entrando a DatosExamenAjax.imprimeEtiquetasExamenes:Entrando... ");
		ExamenesDao objDaoExamenes = new ExamenesDao();
		return objDaoExamenes.imprimeEtiquetasZPL(String.valueOf(kAdmision),0,"");
	}	

	public String[] imprimeEtiquetasExamenesLaboratorio(int kAdmision) throws Exception	
	{
		iObjLog.debug("Entrando a DatosExamenAjax.imprimeEtiquetasExamenesLaboratorio:Entrando... ");
		ExamenesDao objDaoExamenes = new ExamenesDao();
		return objDaoExamenes.imprimeEtiquetasZPL(String.valueOf(kAdmision),0," ce.ctipocomercial <> 2 AND ");
	}	

	public String[] imprimeEtiquetasExamenesGabinetes(int kAdmision) throws Exception	
	{
		iObjLog.debug("Entrando a DatosExamenAjax.imprimeEtiquetasExamenesGabinetes:Entrando... ");
		ExamenesDao objDaoExamenes = new ExamenesDao();
		return objDaoExamenes.imprimeEtiquetasZPL(String.valueOf(kAdmision),0," ce.ctipocomercial = 2 AND ");
	}	
	
	public String[] imprimeEtiquetaMuestra(int kAdmision, int uMuestra) throws Exception	
	{
		iObjLog.debug("Entrando a DatosExamenAjax.imprimeEtiquetaMuestra:Entrando... ");
		ExamenesDao objDaoExamenes = new ExamenesDao();
		return objDaoExamenes.imprimeEtiquetasZPL(String.valueOf(kAdmision),uMuestra,"");
	}	
	
	public OrdenBean cancelarFactura(int intAdmision,String strObservaciones, int intUsuario) throws Exception	
	{
		/** Utilerias ***/
		ConvertBeanvsHB objConvert = new ConvertBeanvsHB();
		/** DAOS ***/
		ToolFacturacionSucursalesDao objFacturacionElectronicaDAO = new ToolFacturacionSucursalesDao();
		DatosOrdenDao objDAOOrden = new DatosOrdenDao();		
		ExamenesDao objDAOExamenes = new ExamenesDao();		
		DatosOrdenDao objDatosOrdenDao = new DatosOrdenDao();
		PagosDao objPagoDao = new PagosDao();  
		/** Variables ***/
		List lstExamenes = new ArrayList();
		OrdenBean objOrdenBean = null;
		TOrdenSucursal objTOrdenSucursal = null;
		TOrdenExamenSucursal  objOrdenExamenSucursal = null;
		TPagoPaciente objLastPago = null;
		PagoPacienteBean objNewPago = new PagoPacienteBean();
		OrdenExamenBean objExamenFundacion = null;
		String strMetodo = "DatosExamenAjax.cancelarFactura";
		iObjLog.debug("Entrando a DatosExamenAjax.cancelarFactura:Entrando... " + intAdmision);
		try {
			TOrdenSucursalFac objTOrdenSucursalFac = objDatosOrdenDao.getOrdenSucursalFac(intAdmision,false);
			lstExamenes = objDAOExamenes.buscarpersistemExamenes(intAdmision);
			objTOrdenSucursal  = (TOrdenSucursal)objDAOOrden.buscarOrden(intAdmision).get(0);			
			SucursalBean objSucursalBean = (new SucursalesUtil()).getSucursal(objTOrdenSucursal.getCsucursalbycsucursal().getCsucursal().intValue());
			objLastPago = objPagoDao.getPagoLast(intAdmision);
			objOrdenBean = objConvert.convertOrdenHBBean((objTOrdenSucursal), objLastPago, strMetodo,false,false); 										
			objOrdenBean.setSobservacion("NO CANCELADO");							
			if (objTOrdenSucursalFac != null) {
				if (objTOrdenSucursalFac.getTfactura().getKfactura().intValue() > 0 && objTOrdenSucursalFac.getCconvenio().getCtipoconvenio().getCtipoconvenio().intValue() < 23) {						
					
				} else {
					if (lstExamenes != null && objOrdenBean != null) {
						Iterator itrExamenes = lstExamenes.iterator();
						while(itrExamenes.hasNext()) {
							objExamenFundacion = (OrdenExamenBean)itrExamenes.next();						
							objExamenFundacion.setCestadoregistro(16);					
							objExamenFundacion.setUserid(intUsuario);
							objExamenFundacion.setSobservaciones(strObservaciones);
							objOrdenExamenSucursal = objConvert.convertOrdenExamenBeanHB(objExamenFundacion, objSucursalBean, strMetodo);
							objOrdenExamenSucursal.setSmotivocancelacion(strObservaciones);
							objOrdenExamenSucursal.setTordensucursal(objTOrdenSucursal);
							iObjLog.debug("Entrando a DatosExamenAjax.cancelarFactura:Actualizo Examen... " + objExamenFundacion.getKordenexamensucursal() );
							objDAOExamenes.setActualizaExamenes(objOrdenExamenSucursal);
						}
						iObjLog.debug("Entrando a DatosExamenAjax.cancelarFactura:Encontro Orden......" + objOrdenBean.getKadmision() );
						if (objLastPago != null) {
							if (objLastPago.getManticipo().doubleValue() > 0.0 || objLastPago.getMpagopacienteparcial().doubleValue() > 0.0) {				
								/**************************************** PAGO NEGATIVO ******************************************/
								objNewPago.setCestadoregistro(objLastPago.getCestadoregistro().getCestadoregistro().intValue());
								objNewPago.setCtipopago(objLastPago.getCtipopago().getCtipopago().intValue());
								objNewPago.setDregistro(new Date());
								objNewPago.setManticipo(0.0);
								objNewPago.setMdevolucionpaciente(objLastPago.getManticipo().doubleValue() + objLastPago.getMpagopacienteparcial().doubleValue());
								objNewPago.setMpagopacienteparcial(0.0);
								objNewPago.setMpagopacientetotal(0.0);
								objNewPago.setMsaldo(0.0);
								objNewPago.setTordensucursal(objTOrdenSucursal.getKordensucursal().intValue());
								objNewPago.setUserid(intUsuario);
								objPagoDao.setPago(objNewPago);								
								/**************************************** PAGO NEGATIVO ******************************************/
							}
						}
						CEstadoRegistro objEstadoResgitro = new CEstadoRegistro();
						objEstadoResgitro.setCestadoregistro(new Integer(17));
						objTOrdenSucursal.setCestadoregistro(objEstadoResgitro);
						objTOrdenSucursal.setSobservacion(strObservaciones);
						objTOrdenSucursal.setUseridchange(new BigDecimal(intUsuario));
						objTOrdenSucursal = objDAOOrden.setActualizaOrden(objTOrdenSucursal);
						objOrdenBean.setCestado(17);
						objOrdenBean.setSobservacion(strObservaciones);				
						objFacturacionElectronicaDAO.cancelacionFactura(objTOrdenSucursal.getKordensucursal().intValue());
						iObjLog.debug("Entrando a DatosExamenAjax.cancelarFactura:Actualizo Orden......" + objTOrdenSucursal.getKordensucursal() );
					}
				}	
			}
			iObjLog.debug("Entrando a DatosExamenAjax.cancelarFactura:Salida... ");
			return objOrdenBean; 	
		} catch (Exception aObjException){
    	    iObjLog.error("DatosExamenAjax.cancelarFactura:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objConvert = null;
			objFacturacionElectronicaDAO = null;
			objDAOOrden = null;		
			objDAOExamenes = null;		
			objPagoDao = null;  
			lstExamenes.clear();
			lstExamenes  = null;
			objOrdenBean = null;
			objTOrdenSucursal = null;
			objOrdenExamenSucursal = null;
			objLastPago = null;
			objNewPago = null;
			objExamenFundacion = null;			
		}
	}		

	
	
	public OrdenBean cancelarExamen(int intAdmision,int intExamen,String strExamenes,String strObservaciones, int intUsuario) throws Exception	
	{
		TOrdenExamenSucursal objExamenFundacion = null;
		boolean bolExamenVivos = false;

		
		/** Utilerias ***/
		ListaExamenUtil objExamenCatalogo = new ListaExamenUtil();
		ConvertBeanvsHB objConvert = new ConvertBeanvsHB();
		/** DAOS ***/
		DatosOrdenDao objDAOOrden = new DatosOrdenDao();		
		ExamenesDao objDAOExamenes = new ExamenesDao();		
		PagosDao objPagoDao = new PagosDao();  
		ToolsDao objToolDao = new ToolsDao();
		Connection objConexion = null;
		/** Variables ***/
		List lstExamenes = new ArrayList();
		OrdenBean objOrdenBean = null;
		OrdenExamenBean objOrdenExamenBean = null;
		TOrdenSucursal objTOrdenSucursal = null;
		TOrdenExamenSucursal objOrdenExamenSucursal = null;
		TPagoPaciente objLastPago = null;
		PagoPacienteBean objNewPago = new PagoPacienteBean();
		
		
		iObjLog.debug("Entrando a DatosExamenAjax.cancelarExamen:Entrando... " + intAdmision + " " + strExamenes);
		try {
			lstExamenes = objDAOExamenes.buscarpersistemExamenes(intAdmision);
			
			GenericDAO objConn = new GenericDAO();
			objConexion = objConn.getConnection();		
			objLastPago = objPagoDao.getPagoLast(intAdmision);
			objTOrdenSucursal  = (TOrdenSucursal)objDAOOrden.buscarOrden(intAdmision).get(0);
			objOrdenBean = objConvert.convertOrdenHBBean((objTOrdenSucursal), objLastPago, "DatosExamenAjax.newExamenOrden",false,false); 	
			SucursalBean objSucursalBean = (new SucursalesUtil()).getSucursal(objTOrdenSucursal.getCsucursalbycsucursal().getCsucursal().intValue());
			
			
//			if (lstExamenes != null) {
//								
//				Iterator itrExamenes = lstExamenes.iterator();
//				double dblSubTotal = 0.0;
//				double dblDescuento = 0.0;
//				double dblTotal = 0.0;
//				Date newDate = null;
//			 	Date oldDate = new Date();
//				while(itrExamenes.hasNext()) {
//					objExamenFundacion = (TOrdenExamenSucursal)itrExamenes.next();						
//					if (intExamen == objExamenFundacion.getCexamenfundacion().getCexamenfundacion().intValue()) {
//						objExamenFundacion.setCestado(2);
//						objExamenFundacion.setCusuario(intUsuario);
//						objExamenFundacion.setSobservacion(strObservaciones);
//						iObjLog.debug("Entrando a DatosExamenAjax.cancelarExamen:Actualizo Examen... " + intExamen );
//						objDAOExamenes.setActualizaExamenes(objExamenFundacion);
//					} else if (objExamenFundacion.getCestado() == 1) {
//						bolExamenVivos = true;
//						dblSubTotal += objExamenFundacion.getMsubtotal();
//						dblDescuento += objExamenFundacion.getMdescuento();
//						dblTotal += objExamenFundacion.getMtotal();					
//						newDate = objExamenFundacion.getDpromesa();						
//						if (newDate.compareTo(oldDate) > 0) { 
//							oldDate = newDate;
//						}						
//					}
//				}
//				objBOrden = (TOrdenSucursal)objDAOOrden.buscarOrden(intAdmision).get(0);
//
//				
//				
//				if (objBOrden != null) {
//					iObjLog.debug("Entrando a DatosExamenAjax.cancelarExamen:Encontro Orden......" + objBOrden.getKordenfundacion() );
//					if (bolExamenVivos == true) {
//						objBOrden.setCestado(1);
//					} else {
//						objBOrden.setCestado(2);						
//					}
//					objBOrden.setDpromesa(oldDate);
//					objBOrden.setMsubtotal(new BigDecimal(dblSubTotal));
//					objBOrden.setMdescuento(new BigDecimal(dblDescuento));
//					objBOrden.setMiva(new BigDecimal(0));
//					objBOrden.setMtotal(new BigDecimal(dblTotal));
//					objBOrden.setMadeuda(new BigDecimal(objBOrden.getMtotal() - objBOrden.getMacuenta()));
//					if ((objBOrden.getMtotal() - objBOrden.getMacuenta())  < 0 ){
//						PagosDao objDaoPagos = new PagosDao();
//						PagoPacienteBean objPago = new PagoPacienteBean();
//					 	Date objDate = new Date();							
//						/**************************************** PAGO NEGATIVO ******************************************/
////					    objPago.setBregistroactivo(true);
////					    objPago.setCestado(1);
////					    objPago.setCusuario(intUsuario);
////					    objPago.setDfechapago(objDate);
////					    objPago.setDregistro(objDate);
////					    objPago.setManticipo(new BigDecimal(objBOrden.getMtotal() - objBOrden.getMacuenta()));
////					    objPago.setMsaldo(new BigDecimal(0));
////					    objPago.setMpago(new BigDecimal(objBOrden.getMtotal() - objBOrden.getMacuenta()));
////CANCELACION					    objPago = objDaoPagos.setPago(objPago,objBOrden.getKordenfundacion().intValue());				
//						/**************************************** PAGO NEGATIVO ******************************************/						
//					}
////					objBOrden = objDAOOrden.setActualizaOrden(objBOrden);
//					iObjLog.debug("Entrando a DatosExamenAjax.cancelarExamen:Actualizo Orden......" + objBOrden.getKordenfundacion() );
//				}				
//			}
//			iObjLog.debug("Entrando a DatosExamenAjax.cancelarExamen:Salida... ");
//		 	objBOrden.setSobservacion(new Formatos().getFechaNumeros(objBOrden.getDpromesa()));
//		 	objBOrden.setSordenfundacion(DatosOrdenAjax.llenaIdFactura(objBOrden.getSordenfundacion(),String.valueOf(objBOrden.getCordenfundacion()) , 8));
//		 	return objBOrden; 	
			return null;
		}catch (Exception aObjException){
    	    iObjLog.error("DatosExamenAjax.cancelarExamen:Exception....", aObjException);
    	    throw aObjException;
		} 
	}		
	

	public BOrdenFundacion actualizaOrdenExamenes(int intkAdmision) throws Exception	
	{
		List lstExamenes = null;
		List lstOrden = null;

		BOrdenExamenFundacion objExamenFundacion = null;
		BOrdenFundacion objBOrden = null;
	 	
		Date newDate = null;
	 	Date oldDate = new Date();
		
		DatosOrdenDao objDAOOrden = new DatosOrdenDao();		
		ExamenesDao objDAOExamenes = new ExamenesDao();
		
		iObjLog.debug("Entrando a DatosExamenAjax.actualizaExamenes:Entrando... ");
		try {
			lstExamenes = objDAOExamenes.buscarpersistemExamenes(intkAdmision);
			if (lstExamenes != null) {
				double dblSubTotal = 0.0;
				double dblDescuento = 0.0;
				double dblTotal = 0.0;
				Iterator itrExamenes = lstExamenes.iterator();
				while(itrExamenes.hasNext()) {
					objExamenFundacion = (BOrdenExamenFundacion)itrExamenes.next();					
					if( objExamenFundacion.getCestado() == 1) {
						dblSubTotal += objExamenFundacion.getMsubtotal().doubleValue();
						dblDescuento += objExamenFundacion.getMdescuento().doubleValue();
						dblTotal += objExamenFundacion.getMtotal().doubleValue();					
						newDate = objExamenFundacion.getDpromesa();						
						if (newDate.compareTo(oldDate) > 0) { 
							oldDate = newDate;
						}
					} 
				}
				lstOrden = objDAOOrden.buscarOrden(intkAdmision);
				objBOrden = (BOrdenFundacion)lstOrden.get(0);
				if (objBOrden != null) {
					objBOrden.setDpromesa(oldDate);
					objBOrden.setMsubtotal(new BigDecimal(dblSubTotal));
					objBOrden.setMdescuento(new BigDecimal(dblDescuento));
					objBOrden.setMiva(new BigDecimal(0));
					objBOrden.setMtotal(new BigDecimal(dblTotal));
					objBOrden.setMadeuda(new BigDecimal(objBOrden.getMtotal().doubleValue() - objBOrden.getMacuenta().doubleValue()));
//					objBOrden = objDAOOrden.setActualizaOrden(objBOrden);
				}
			}
		 	objBOrden.setSobservacion(new Formatos().getFechaNumeros(objBOrden.getDpromesa()));
		 	objBOrden.setSordenfundacion(DatosOrdenAjax.llenaIdFactura(objBOrden.getSordenfundacion(),String.valueOf(objBOrden.getCordenfundacion()) , 8));
		 	return objBOrden; 	
		}catch (Exception aObjException){
    	    iObjLog.error("DatosExamenAjax.actualizaExamenes:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	

	public String consultaExamenesGrid(String strExamen,int intUnidad,int intConvenio) throws Exception	
	{
		ClientesNewDao objConvenioDao = new ClientesNewDao();
		ConvenioBean objConvenioBean = new ConvenioBean();
		boolean bolExamen = true;
		List lstExamenes = new ArrayList();
		String strReturn = "";		
		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");	
		try {
			if (strExamen.trim().length() >= 2) {
				iObjLog.debug("Entrando a DatosExamenAjax.consultaExamenesGrid:Entrando...Parametros... " + strExamen.trim());
				objConvenioBean.setCconvenio(new Integer(intConvenio));
				int intcListaPrecio = objConvenioDao.ListaPreciosConvenio(objConvenioBean);
				ListaExamenUtil objCatalogoExamenes =  new ListaExamenUtil();
				OrdenExamenBean objDExamenFundacion = new OrdenExamenBean();
				lstExamenes = objCatalogoExamenes.getExamenes(new OrdenExamenBean(strExamen),intUnidad,intcListaPrecio,intConvenio);
				lstExamenes = objConvenioDao.cotizarConvenio(objConvenioBean,lstExamenes,true);			
				iObjLog.debug("Entrando a DatosExamenAjax.consultaExamenesGrid:Entrando... Se encontraron " + lstExamenes.size() + " examenes");
					 if (lstExamenes != null) {
						String strNamePerfil = "";
						String strProductoCotizar = "";
						int intPerfil = 0;
						String strVolumen = "";
						double dblPrecio = 0.0;
						for (int i = 0; i < lstExamenes.size() ; i++)
						{
							objDExamenFundacion = (OrdenExamenBean)lstExamenes.get(i);						
							if (objDExamenFundacion.getCperfil() == -1) {
								intPerfil=0;
								dblPrecio=0.0;
								strNamePerfil =	""; 
								strProductoCotizar = "" + objDExamenFundacion.getCexamen();
								dblPrecio = objDExamenFundacion.getMtotal();
								if (objDExamenFundacion.getUvolumenexamen() == 1) {
//									strVolumen = "<select id='selVolumen' onchange='javascript:changeVolumen(" + objDExamenFundacion.getCexamen() + ",this);'>" +
//												 "		<option value='1'>1</option>" +
//										         "</select>";
								} else if (objDExamenFundacion.getUvolumenexamen() > 1) {
//									strVolumen = "<select id='selVolumen' onchange='javascript:changeVolumen(" + objDExamenFundacion.getCexamen() + ",this);'>";
//												for (int inti=1;inti<(objDExamenFundacion.getUvolumenexamen()+1);inti++) {
//													strVolumen = strVolumen + "<option value='" + Formatos.formateaNumero2Digitos(inti+"") + "'>" + Formatos.formateaNumero2Digitos(inti+"") + "</option>";
//												}
//									strVolumen = strVolumen + "</select>";
								} else {
//									strVolumen = "Examen MAL Configurado";			
								}
								strVolumen = "";
								strReturn += ("<tr>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:newProductoGrid(" + strProductoCotizar + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objDExamenFundacion.getStipocomercial() + 
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:newProductoGrid(" + strProductoCotizar + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objDExamenFundacion.getCexamen() + 
										"</a></td>" + 
										"<td align='left' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:newProductoGrid(" + strProductoCotizar + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objDExamenFundacion.getSexamen() +
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:newProductoGrid(" + strProductoCotizar + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											dblPrecio +
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:informacionExamen(" + strProductoCotizar + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>Informacion " + 
											objDExamenFundacion.getCexamen() +
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:informacionExamen23(" + strProductoCotizar + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											strVolumen +
										"</a></td>" + 
									 "</tr>");	
							} else {
								bolExamen = false;
								intPerfil=0;
								BigDecimal objdblPrecioPerfil = new BigDecimal(0);
								intPerfil = objDExamenFundacion.getCperfil();
								while (true) {
									if (intPerfil == objDExamenFundacion.getCperfil()) {
										iObjLog.debug("Entrando a DatosExamenAjax.consultaExamenesGrid:Entrando... Perfil " + objDExamenFundacion.getCperfil() + " Examen " + objDExamenFundacion.getCexamen() + " Total " + objDExamenFundacion.getMtotal());
										objdblPrecioPerfil = objdblPrecioPerfil.add(new BigDecimal(objDExamenFundacion.getMtotal()));
										i++;
										if (i < lstExamenes.size()) {
											objDExamenFundacion = (OrdenExamenBean)lstExamenes.get(i);						
										} else {
											i--;
											objDExamenFundacion = (OrdenExamenBean)lstExamenes.get(i);						
											break;											
										}
									} else {
										i--;
										objDExamenFundacion = (OrdenExamenBean)lstExamenes.get(i);						
										break;
									}
								}
								objdblPrecioPerfil = objdblPrecioPerfil.setScale(2, BigDecimal.ROUND_HALF_UP);								
								strNamePerfil =	objDExamenFundacion.getSperfil(); 
								strProductoCotizar = "" + objDExamenFundacion.getCperfil();
								strReturn += ("<tr>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:newProductoGrid(" + strProductoCotizar + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											intPerfil + 
										"</a></td>" + 
										"<td align='left' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:newProductoGrid(" + strProductoCotizar + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											strNamePerfil +
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:newProductoGrid(" + strProductoCotizar + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objdblPrecioPerfil +
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:informacionExamen(" + strProductoCotizar + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
										"</a></td>" + 
									 "</tr>");						
							}							
						}
					 }
					 if (bolExamen) {
						 strReturn = ("<table border='0' align='center' style='width: 883px' class='tabla'>" + 
									"<tr>" + 
									"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
									"	<b><font color='black'>Tipo Examen" + 
									"</th>" + 
									"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
									"	<b><font color='black'>C&oacute;digo Examen" + 
									"	</font></b>" +
									"</th>" + 
									"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
									"	<b><font color='black'>Nombre Examen" + 
									"</th>" + 
									"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
									"	<b><font color='black'>Precio" + 
									"</th>" + 
									"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
									"	<b><font color='black'>Datos Adicionales" + 
									"	</font></b>" +
									"</th>" + 
									"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
									"	<b><font color='black'>" + // Volumen Examen" + 
									"	</font></b>" +
									"</th>" + 
						 			"</tr>") + strReturn;			    
						 
					 } else {
						 strReturn = ("<table border='0' align='center' style='width: 883px' class='tabla'>" + 
									"<tr>" + 
									"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
									"	<b><font color='black'>C&oacute;digo Perfil" + 
									"	</font></b>" +
									"</th>" + 
									"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
									"	<b><font color='black'>Nombre Perfil" + 
									"</th>" + 
									"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
									"	<b><font color='black'>Precio" + 
									"</th>" + 
									"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
									"	<b><font color='black'>Examenes dentro Perfil" + 
									"	</font></b>" +
									"</th>" + 
									"</tr>") + strReturn;			    
						 
					 }
					 strReturn += ("</table>");			
			}
		}catch (Exception aObjException){
			iObjLog.error("DatosExamenAjax.consultaExamenesGrid:Exception....", aObjException);
			throw aObjException;
		} finally {
			lstExamenes.clear();
			lstExamenes = null;
		}
	    return strReturn; 
	}	
	
	private Date calculafechapromesa(int intTipoPaciente) {
		Calendar now = Calendar.getInstance(); 
		int DaySum = 0;
		iObjLog.debug("Entrando a DatosExamenAjax.calculafechapromesa:Entrando... " + intTipoPaciente);	
		iObjLog.debug("Fecha Actual : " + (now.get(Calendar.MONTH) + 1)                        
										 + "-"                        
										 + now.get(Calendar.DATE)                        
										 + "-"                        
										 + now.get(Calendar.YEAR));     
		String[] strDays = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thusday","Friday","Saturday"};
		String[] strSub = new String[]{"12","11","10","14","13","12","11"};
		String[] strPri = new String[]{"13","12","11","10","14","13","12"};
		if (intTipoPaciente == 1) {
			DaySum = Integer.parseInt(strPri[now.get(Calendar.DAY_OF_WEEK) - 1]);
			iObjLog.debug("Sumar dias strPri : " + DaySum);			
		} else if (intTipoPaciente == 2) {
			DaySum = Integer.parseInt(strSub[now.get(Calendar.DAY_OF_WEEK) - 1]);
			iObjLog.debug("Sumar dias SUB : " + DaySum);			
		}
		iObjLog.debug("El dia de la semana es : " + strDays[now.get(Calendar.DAY_OF_WEEK) - 1]);
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int date = now.get(Calendar.DATE) + DaySum;		
		now.clear();		
		now.set(Calendar.YEAR, year);
		now.set(Calendar.MONTH, month);
		now.set(Calendar.DATE, date);		
		iObjLog.debug("Fecha de Entrega : " + (now.get(Calendar.MONTH) + 1)                        
											 + "-"                        
											 + now.get(Calendar.DATE)                        
											 + "-"                        
											 + now.get(Calendar.YEAR));     
		return now.getTime();		
	}
	
	
	/**
	 *  Versión 28/03/2013
	 * BY 
	 * incidencia de Facturación
	 * */
	private String showBodyExamen(OrdenExamenBean objDExamen,String strColor, boolean bolLive, boolean bolGerente) {
		String strPerfil = "";
		String strLive = "";
		if (objDExamen.getCperfil() == -1) {
			strPerfil = "";			
		} else {
			strPerfil = objDExamen.getCperfil() + " " + objDExamen.getSperfil();
		}
		if ((bolLive) && (bolGerente)) {
			if (objDExamen.getCperfil() == -1) {
				strLive = "<input type='checkbox' id='chkEliminarExamen" + objDExamen.getCexamen() + "'  value='" + objDExamen.getCexamen() + "' onClick='cancelarExamen(this);'> ";
			} else {
				strLive = "<input type='checkbox' id='chkEliminarPerfil" + objDExamen.getCexamen()+objDExamen.getCperfil() + "'  value='" + objDExamen.getCperfil() + "' onClick='cancelarPerfil(this);'> ";
			}
		} else {
			strLive = objDExamen.getSmotivocancelacion();
		}
		return ("<tr>" + 
				"	<td align='center' style='color: " + strColor + ";'>" 						+ 
						objDExamen.getCexamen() 							+ 
				"	</td>" + 
				"	<td align='center' style='color: " + strColor + ";'>"+
						objDExamen.getSexamen()+
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
						"$ " + (objDExamen.getMsubtotal() * objDExamen.getUvolumenexamen()) +
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
						"$ " + this.redodedoDouble2(((objDExamen.getMdescuentoempresa() + objDExamen.getMdescuentomedico() + objDExamen.getMdescuentopromocion() + objDExamen.getMfacturaempresa()) * objDExamen.getUvolumenexamen())) +
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
						"$ " + (objDExamen.getMpagopaciente() * objDExamen.getUvolumenexamen()) +
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
						new Formatos().getFechaNumeros(objDExamen.getDresultadoentrega()) +
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
		        "		<a href='javascript:imprimirEtiquetaMuestra(" + objDExamen.getUmuestra() +")' onclick='javascript:'imprimirEtiquetaMuestra(" + objDExamen.getUmuestra() +")' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal'> " +
							objDExamen.getUmuestra() +
	            "		</a>" +
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
						strPerfil + 
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
						objDExamen.getUvolumenexamen() +
				"	</td>" +
				"	<td align='center' style='color: " + strColor + ";'>"+
						strLive +
				"	</td>" +
			 	"</tr>");
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
    
    private double redodedoDouble2(double nD) {
		return Math.round(nD*Math.pow(10,2))/Math.pow(10,2);      	
    }

}
