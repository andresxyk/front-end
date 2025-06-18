package mx.com.web2lab.ajax.dwr.capturaorden;

import mx.com.web2lab.actions.seguridad.SeguridadUtil;
import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import mx.com.web2lab.backend.beans.ap.PacienteBean;
import mx.com.web2lab.backend.dao.ap.PacientesDao;
import mx.com.web2lab.backend.dao.ap.mayoreo.PacientesMayoreoDao;

import mx.com.web2lab.backend.util.formatos.Formatos;
import mx.com.web2lab.backend.util.xmltool.ToolXML;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineUser;

import mx.com.sct.www.DetalleEstudios;
import mx.com.sct.www.PasesLG;
import mx.com.sct.www.WSDataSTCProxy;
import mx.com.vitamedica.www.WSRecepcionExtSoapProxy;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DatosPacienteAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(DatosPacienteAjax.class);
	
	public PacienteBean actualizaPaciente(PacienteBean objPacienteBean) throws Exception
	{
		iObjLog.debug("Entrando DatosPacienteAjax.actualizaPaciente:Entrando... " + objPacienteBean.getKpacientefundacion() + " expediente del metro " + objPacienteBean.getSvalorexpediente());		
		try {			
				if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");
				objPacienteBean.setBregistroactivo(true);
				if (objPacienteBean.getSnacimiento().trim().length() > 2) {
					objPacienteBean.setDnacimiento(new Formatos().getFecha(objPacienteBean.getSnacimiento()));
				} else {
					objPacienteBean.setSnacimiento("01-ENE-1900");
					objPacienteBean.setDnacimiento(new Formatos().getFecha("01-ENE-1900"));
				}
				if (objPacienteBean.getCcodigopostal() == 0) {
					objPacienteBean.setCcodigopostal(156250);
					objPacienteBean.setSdireccion("SIN DIRECCION");
				}
				if (objPacienteBean.getStelefono().trim().length() == 0) {
					objPacienteBean.setStelefono("SIN TELEFONO");
				}
				if (objPacienteBean.getScelular().trim().length() == 0) {
					objPacienteBean.setScelular("SIN CELULAR");
				}				
				PacientesDao objDAOPaciente = new PacientesDao();
				objPacienteBean = objDAOPaciente.setPacienteActualizacion(objPacienteBean);
			iObjLog.debug("Saliendo DatosPacienteAjax.actualizaPaciente:Saliendo...  " + objPacienteBean.toString());
    	}catch (Exception aObjException){
    	    iObjLog.error("Error DatosPacienteAjax.actualizaPaciente:Exception....", aObjException);
    	    objPacienteBean = null;
    	    throw aObjException;
    	}
		return objPacienteBean;
	}	

	public String ressetPasswordECE(PacienteBean objPacienteBean) throws Exception
	{
		iObjLog.debug("Entrando DatosPacienteAjax.ressetPasswordECE:Entrando... " + objPacienteBean.getKpacientefundacion());	
		String strReturn = "Error en el reseteo del Password";
		PacientesDao objDAOPaciente = new PacientesDao();
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");
			strReturn = objDAOPaciente.ressetPasswordECE(objPacienteBean);
			iObjLog.debug("Saliendo DatosPacienteAjax.ressetPasswordECE:Saliendo...  " + objPacienteBean.toString());
    	}catch (Exception aObjException){
    	    iObjLog.error("Error DatosPacienteAjax.ressetPasswordECE:Exception....", aObjException);
    	    strReturn = "Error DatosPacienteAjax.ressetPasswordECE";
    	    throw aObjException;
    	} finally {
    		objDAOPaciente = null;
    	}
		return strReturn;
	}	
		  
	public PacienteBean buscarPaciente(PacienteBean objPacienteBean) throws Exception
	{
		iObjLog.debug("Entrando DatosPacienteAjax.buscarPaciente:Entrando... ");		
		PacientesDao objDAOPaciente = new PacientesDao();
		try {			
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");			
			objPacienteBean.setSvalorexpediente("");
			objPacienteBean = objDAOPaciente.buscarPaciente(objPacienteBean);
			iObjLog.debug("Saliendo DatosPacienteAjax.buscarPaciente:Consulta...Nombre..." + objPacienteBean.getSappaterno() + " " + objPacienteBean.getSapmaterno());
			iObjLog.debug("Saliendo DatosPacienteAjax.buscarPaciente:Saliendo...  " + objPacienteBean.toString());
		} catch (Exception aObjException){
			iObjLog.error("Error DatosPacienteAjax.buscarPaciente:Exception....", aObjException);
			objPacienteBean = null;
			throw aObjException;
		} finally {
			objDAOPaciente = null;
		}
		return objPacienteBean;
	}	
	
	public PacienteBean buscarPacienteVitaMedica(PacienteBean objPacienteBean) throws Exception
	{
		iObjLog.debug("Entrando DatosPacienteAjax.buscarPaciente:Entrando... ");		
		WSRecepcionExtSoapProxy sampleWSRecepcionExtSoapProxyid = new WSRecepcionExtSoapProxy();
		PacientesDao objDAOPaciente = new PacientesDao();			
		List lstPacientes = new ArrayList();
		String strNumeroElegibilidad = objPacienteBean.getSvalorexpediente();
		try {			
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");			
	        java.lang.String buscaOrdenLaboratorio13mtemp = sampleWSRecepcionExtSoapProxyid.buscaOrdenLaboratorio("1",strNumeroElegibilidad);		
			iObjLog.debug("Saliendo DatosPacienteAjax.buscarPaciente:Saliendo...WEbServices 1..." + buscaOrdenLaboratorio13mtemp);	    			
		    NodeList nodes = ToolXML.parserString(buscaOrdenLaboratorio13mtemp).getElementsByTagName("Consulta");
		    if (nodes.getLength() > 0) {
				    objPacienteBean.setCconvenio(0);
				    objPacienteBean.setCsexo(0);
					for (int i = 0; i < 1; i++) {
						Element element = (Element) nodes.item(i);
						objPacienteBean.setSnombre(ToolXML.getValueNode(element, "Nombre_Usuario").trim().toUpperCase().toString());
						objPacienteBean.setSappaterno(ToolXML.getValueNode(element, "ApellidoPaterno_Usuario").trim().toUpperCase().toString());
						objPacienteBean.setSapmaterno(ToolXML.getValueNode(element, "ApellidoMaterno_Usuario").trim().toUpperCase().toString());	
						if ((ToolXML.getValueNode(element, "Genero_Usuario").toString().toUpperCase().equals("MUJER")) || ToolXML.getValueNode(element, "Genero_Usuario").toString().toUpperCase() == "MUJER") {
							objPacienteBean.setCsexo(0);					
						} else {
							objPacienteBean.setCsexo(1);
						}
						objPacienteBean.setSnacimiento(ToolXML.getValueNode(element, "FechaNacimiento_Usuario").replace('/', '-'));					
					}					
					lstPacientes = objDAOPaciente.buscarPacientes(objPacienteBean);
					if (lstPacientes  != null) {
						if (lstPacientes.size() > 0) {
							objPacienteBean = objDAOPaciente.buscarPaciente((PacienteBean)lstPacientes.get(0));
						}
					}					
					for (int i = 0; i < 1; i++) {
						Element element = (Element) nodes.item(i);
//						objPacienteBean.setScelular(objPacienteBean.getScelular() + "");
//						objPacienteBean.setSciudad(objPacienteBean.getSciudad() + "");
//						objPacienteBean.setSdelegmuni(objPacienteBean.getSdelegmuni() + "");
//						objPacienteBean.setScodigopostal(objPacienteBean.getScodigopostal() + "");
//						objPacienteBean.setScolonia(objPacienteBean.getScolonia() + "");
//						objPacienteBean.setScorreoelectronico(objPacienteBean.getScorreoelectronico() + "");
//						objPacienteBean.setSdireccion(objPacienteBean.getSdireccion() + "");
//						objPacienteBean.setStelefono(objPacienteBean.getStelefono() + "");
						objPacienteBean.setSgrupoid(ToolXML.getValueNode(element, "grupoID").trim());
						objPacienteBean.setSnumcredencial(ToolXML.getValueNode(element, "NumCredencial"));
						objPacienteBean.setSnumnomina(ToolXML.getValueNode(element, "NumNomina"));
						objPacienteBean.setSnumbeneficiario(ToolXML.getValueNode(element, "NumNomina"));
						objPacienteBean.setSproveedorreferencia(ToolXML.getValueNode(element, "Proveedor_referencia"));
						objPacienteBean.setSnombremedico(ToolXML.getValueNode(element, "Medico_nombre").trim().toUpperCase().toString());
						objPacienteBean.setSapellidopaternomedico(ToolXML.getValueNode(element, "Medico_apellidoPaterno").trim().toUpperCase().toString());
						objPacienteBean.setSapellidomaternomedico(ToolXML.getValueNode(element, "Medico_apellidoMaterno").trim().toUpperCase().toString());
						objPacienteBean.setSfechaconsulta(ToolXML.getValueNode(element, "fechaConsulta").trim().toString());
						objPacienteBean.setMsumadisponible(Double.parseDouble(ToolXML.getValueNode(element, "Suma_disponible")));
					}
			        java.lang.String strbuscaServiciosPorRealizar = sampleWSRecepcionExtSoapProxyid.buscaServiciosPorRealizar("1",strNumeroElegibilidad);		
					iObjLog.debug("Saliendo DatosPacienteAjax.buscarPaciente:Saliendo...WEbServices 10.2..." + strbuscaServiciosPorRealizar);	   
					if (objPacienteBean.getSgrupoid().trim() == "BNX" || objPacienteBean.getSgrupoid().trim().equals("BNX")) {
						objPacienteBean.setCconvenio(350);
					} else {
						objPacienteBean.setCconvenio(350);
					}
					objPacienteBean.setBvitamedica(1);
				    NodeList lstExamenes = ToolXML.parserString(strbuscaServiciosPorRealizar).getElementsByTagName("Consulta");
				    String strExamenes = "";
				    String strExamenesVitaMedica = "";
				    String strPermisos = "";
				    String strICD = "";
					for (int i = 0; i < lstExamenes.getLength(); i++) {
						Element element = (Element) lstExamenes.item(i);
						if (strExamenes.length() > 0) {
							strExamenes = strExamenes + "," + ToolXML.getValueNode(element, "CPT").trim().toString();
							strExamenesVitaMedica = strExamenesVitaMedica + "','" + ToolXML.getValueNode(element, "CPT").trim().toString();
							strPermisos = strPermisos + "," + ToolXML.getValueNode(element, "preatorizacion").trim().toString();
						} else {
							strExamenes = ToolXML.getValueNode(element, "CPT").trim().toString();
							strExamenesVitaMedica = "S." + ToolXML.getValueNode(element, "CPT").trim().toString();
						}
						strICD =  ToolXML.getValueNode(element, "ICD").trim().toString();
					}
			        java.lang.String strvalidaServicioPorRealizar = sampleWSRecepcionExtSoapProxyid.validaServicioPorRealizar("1", 
			        																										  objPacienteBean.getSproveedorreferencia(), 
			        																										  objPacienteBean.getSgrupoid(), 
			        																										  "05242363", 
			        																										  strNumeroElegibilidad, 
			        																										  strICD, 
			        																										  strExamenes, 
			        																										  objPacienteBean.getSfechaconsulta(), 
			        																										  strPermisos, 
			        																										  1);		
					iObjLog.debug("Saliendo DatosPacienteAjax.buscarPaciente:Saliendo...WEbServices 10.3.4..." + strvalidaServicioPorRealizar);	 
				    NodeList lstMensajeVitaMedica = ToolXML.parserString(strvalidaServicioPorRealizar).getElementsByTagName("Consulta");
					for (int i = 0; i < lstMensajeVitaMedica.getLength(); i++) {
						Element element = (Element) lstMensajeVitaMedica.item(i);
						objPacienteBean.setSmensagevitamedica(ToolXML.getValueNode(element, "descripcionError").trim().toString());
					}
					objPacienteBean.setsCPT(strExamenesVitaMedica);
		    } else {
				objPacienteBean.setBvitamedica(2);
			    NodeList nodesMensaje = ToolXML.parserString(buscaOrdenLaboratorio13mtemp).getElementsByTagName("Mensaje");
				for (int i = 0; i < 1; i++) {
					Element element = (Element) nodesMensaje.item(i);
					objPacienteBean.setSmensagevitamedica(ToolXML.getValueNode(element, "DescripcionMsg").trim().toString());
				}				
		    }
			objPacienteBean.setBvitamedica(2);
			iObjLog.debug("Saliendo DatosPacienteAjax.buscarPaciente:Consulta...Nombre..." + objPacienteBean.getSappaterno() + " " + objPacienteBean.getSapmaterno());
			iObjLog.debug("Saliendo DatosPacienteAjax.buscarPaciente:Saliendo...  " + objPacienteBean.toString());
		} catch (Exception aObjException){
			iObjLog.error("Error DatosPacienteAjax.buscarPaciente:Exception....", aObjException);
			objPacienteBean = null;
			throw aObjException;
		} finally {
			objDAOPaciente = null;
			sampleWSRecepcionExtSoapProxyid = null;
		}
		return objPacienteBean;
	}	

	
	public PacienteBean buscarPacienteMetroWebService(String strPase) throws Exception {
		iObjLog.debug("Entrando DatosPacienteAjax.buscarPacienteMetroWebService:Entrando... ");		
		WSDataSTCProxy sampleWSRecepcionExtSoapProxyid = new WSDataSTCProxy();
		PacienteBean objPacienteBean = null;
		try {			
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");			
	        PasesLG[] lstPase = sampleWSRecepcionExtSoapProxyid.getPase(strPase, "97fbfde1edcd2001cc1841c604967681");
	        if (lstPase == null) {
				objPacienteBean = new PacienteBean();	        	
	        } else {
				iObjLog.debug("Consulta DatosPacienteAjax.buscarPacienteMetroWebService:Size..Array... "+ lstPase.length);		
		        for (int inti=0;inti<lstPase.length;inti++) {
		        	PasesLG objPasesLG = lstPase[inti];
					PacientesMayoreoDao objDAOPacienteMayoreo = new PacientesMayoreoDao();
					objPacienteBean = new PacienteBean(objPasesLG.getExpediente().trim() + "-" + objPasesLG.getParentesco().trim().substring(0,1));
					objPacienteBean = objDAOPacienteMayoreo.buscarPacienteMetro(objPacienteBean);
					objDAOPacienteMayoreo = null;
					if (objPacienteBean.getKpacientefundacion().intValue() > 0) {
						PacientesDao objDAOPaciente = new PacientesDao();
						objPacienteBean = objDAOPaciente.buscarPaciente(objPacienteBean);
						objPacienteBean.setBvitamedica(2);
						if (objPasesLG.getClinicareferencia().trim().toString() == "TAXQUEÑA" || objPasesLG.getClinicareferencia().trim().toString().equals("TAXQUEÑA")) {
							objPacienteBean.setCconvenio(311);
						} else if (objPasesLG.getClinicareferencia().trim().toString() == "TICOMAN" || objPasesLG.getClinicareferencia().trim().toString().equals("TICOMAN")) {
							objPacienteBean.setCconvenio(312);
						} else if (objPasesLG.getClinicareferencia().trim().toString() == "ZARAGOZA" || objPasesLG.getClinicareferencia().trim().toString().equals("ZARAGOZA")) {
							objPacienteBean.setCconvenio(310);
						} else if (objPasesLG.getClinicareferencia().trim().toString() == "CUAUHTEMOC" || objPasesLG.getClinicareferencia().trim().toString().equals("CUAUHTEMOC") ) {
							objPacienteBean.setCconvenio(309);
						} else {
							objPacienteBean.setCconvenio(311);
						}							
						objPacienteBean.setSapellidomaternomedico(objPasesLG.getMedicomaterno());
						objPacienteBean.setSapellidopaternomedico(objPasesLG.getMedicopaterno());
						objPacienteBean.setSnombremedico(objPasesLG.getMediconombre());
						for(int inty=0;inty<objPasesLG.getListaEstudios().length;inty++) {
							DetalleEstudios objDetalleEstudios = objPasesLG.getListaEstudios(inty);
							if (objPacienteBean.getsCPT().toString() == "") {
								objPacienteBean.setsCPT(objDetalleEstudios.getIdestudio());
							} else {
								objPacienteBean.setsCPT(objPacienteBean.getsCPT() + "," + objDetalleEstudios.getIdestudio());
							}
						}
						objDAOPaciente = null;
					} else {
						objPacienteBean = new PacienteBean();	        	
					}
					iObjLog.debug("Saliendo DatosPacienteAjax.buscarPacienteMetroWebService:Saliendo...WebServices 1..." + objPasesLG.getNombre() + " " + objPasesLG.getPaterno() + " " +objPasesLG.getMaterno());	    
		        }
		        if (objPacienteBean == null) {
					objPacienteBean = new PacienteBean();	        	
		        }
	        }
		} catch (Exception aObjException){
			iObjLog.error("Error DatosPacienteAjax.buscarPacienteMetroWebService:Exception....", aObjException);
			throw aObjException;
		} finally {
			sampleWSRecepcionExtSoapProxyid = null;
		}
		return objPacienteBean;
	}	
	
	
	
	public int validaAutetificacion(String strUsuario,String strPassword) throws Exception
	{
		int intReturn = 0;
		iObjLog.debug("Entrando DatosPacienteAjax.validaAutetificacion:Entrando... ");		
		try {			
    		User objUsuario = TurbineSecurity.getAuthenticatedUser(strUsuario.trim(), strPassword.trim());
    		if(objUsuario!=null) {
                TurbineUser objTurbineUser = getUserByName(strUsuario.trim());
                intReturn = objTurbineUser.getUserId();    						
    		} else {
    			intReturn = 0;    			
    		}
			iObjLog.debug("Saliendo DatosPacienteAjax.validaAutetificacion:Saliendo...  " + intReturn);
		} catch (Exception aObjException){
			iObjLog.error("Error DatosPacienteAjax.validaAutetificacion:Exception....", aObjException);
			throw aObjException;
		}
		return intReturn;
	}	
	
    public TurbineUser getUserByName(String strName) throws Exception {
        return SeguridadUtil.getUserByName(strName);
    }	

	public String consultaPacienteGrid(PacienteBean objPacienteBean) throws Exception	
	{
		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");		
		iObjLog.debug("Entrando a DatosPacienteAjax.consultaPacienteGrid:Entrando...Parametros... " + objPacienteBean.getSappaterno().trim() + " " + objPacienteBean.getSapmaterno().trim() + " " + objPacienteBean.getSnombre().trim());
		objPacienteBean.setSnombre(objPacienteBean.getSnombre().trim() + "");
		objPacienteBean.setSappaterno(objPacienteBean.getSappaterno().trim() + "");
		objPacienteBean.setSapmaterno(objPacienteBean.getSapmaterno().trim() + "");
		objPacienteBean.setCsexo(0);
		objPacienteBean.setCconvenio(0);
		objPacienteBean.setScorreoelectronico(null);
		PacientesDao objDAOPaciente = new PacientesDao();
		List lstPacientes = objDAOPaciente.buscarPacientes(objPacienteBean);
		objDAOPaciente = null;
		return this.createGridPaciente(lstPacientes);
	}

	public String consultaPacienteConvenioGrid(PacienteBean objPacienteBean) throws Exception	
	{
		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");		
		iObjLog.debug("Entrando a DatosPacienteAjax.consultaPacienteConvenioGrid:Entrando...Parametros... " + objPacienteBean.getSappaterno().trim() + " " + objPacienteBean.getSapmaterno().trim() + " " + objPacienteBean.getSnombre().trim() + " Convenio " + objPacienteBean.getCconvenio());
		objPacienteBean.setSnombre(objPacienteBean.getSnombre().trim() + "");
		objPacienteBean.setSappaterno(objPacienteBean.getSappaterno().trim() + "");
		objPacienteBean.setSapmaterno(objPacienteBean.getSapmaterno().trim() + "");
		objPacienteBean.setCsexo(0);
		objPacienteBean.setCconvenio(objPacienteBean.getCconvenio());
		objPacienteBean.setScorreoelectronico(null);
		PacientesDao objDAOPaciente = new PacientesDao();
		List lstPacientes = objDAOPaciente.buscarPacientes(objPacienteBean);
		objDAOPaciente = null;
		return this.createGridPaciente(lstPacientes);
	}
	
	private String createGridPaciente(List lstPacientes) throws Exception	
	{
		String strContadorHelp = "";
		int intPacientes = 0;
		int intHelpContador = 0;
		String strReturn = "";		
		PacienteBean objPacienteBean;
		iObjLog.debug("Entrando a DatosPacienteAjax.consultaPacienteGrid:Entrando... Se encontraron " + lstPacientes + " pacientes");
		try {
			 if (lstPacientes != null) {
				 intPacientes = lstPacientes.size();
			 }
			 if (intPacientes > 50 ) {
				 strContadorHelp = ", PERO SOLO SE PRESENTAN 50 (PON MAS DATOS PARA FILTRAR) ";
			 }			
		strReturn = ("<table border='0' align='center' style='width: 883px' class='tabla'>" + 
				 		"<tr>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>Total de Pacientes " + intPacientes + strContadorHelp +

							"	</font></b>" +
							"</th>"  + 
						"</tr>" +
					 "</table>" +	
					 "<table border='0' align='center' style='width: 883px' class='tabla'>" + 
							"<tr>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>C&oacute;digo" + 
							"	</font></b>" +
							"</th>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>Apellido Paterno" + 
							"</th>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>Apellido Materno" + 
							"	</font></b>" +
							"</th>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>Nombre(s)" + 
							"	</font></b>" +
							"</th>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>Fecha Nacimiento" + 
							"	</font></b>" +
							"</th>" + 
						"</tr>");			    
			 if (lstPacientes != null) {
				for (int i = 0; i < lstPacientes.size() ; i++)
				{
					if (intHelpContador >= 50) {
						break;
					}
					intHelpContador = intHelpContador +1;
					objPacienteBean = (PacienteBean)lstPacientes.get(i);						
					strReturn += ("<tr>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:registroAceptado(" + objPacienteBean.getKpacientefundacion() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objPacienteBean.getKpacientefundacion() + 
										"</a></td>" + 
										"<td align='center'>"+
											objPacienteBean.getSappaterno()+
										"</td>" +
										"<td align='center'>"+
											objPacienteBean.getSapmaterno() +
										"</td>" +
										"<td align='center'>"+
											objPacienteBean.getSnombre() +
										"</td>" +
										"<td align='center'>"+
											objPacienteBean.getSnacimiento() +
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
	
	public String consultaPacienteGridNewFacturacion(PacienteBean objPacienteBean) throws Exception	
	{
		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");		
		iObjLog.debug("Entrando a DatosPacienteAjax.consultaPacienteGrid:Entrando...Parametros... " + objPacienteBean.getSappaterno().trim() + " " + objPacienteBean.getSapmaterno().trim() + " " + objPacienteBean.getSnombre().trim());
		objPacienteBean.setSnombre(objPacienteBean.getSnombre().trim() + "");
		objPacienteBean.setSappaterno(objPacienteBean.getSappaterno().trim() + "");
		objPacienteBean.setSapmaterno(objPacienteBean.getSapmaterno().trim() + "");
		objPacienteBean.setCsexo(0);
		objPacienteBean.setCconvenio(0);
		objPacienteBean.setScorreoelectronico(null);
		PacientesDao objDAOPaciente = new PacientesDao();
		List lstPacientes = objDAOPaciente.buscarPacientes(objPacienteBean);
		objDAOPaciente = null;
		return this.createGridPacienteNewFacturacion(lstPacientes);
	}
    
    private String createGridPacienteNewFacturacion(List lstPacientes) throws Exception	
	{
		String strContadorHelp = "";
		int intPacientes = 0;
		int intHelpContador = 0;
		String strReturn = "";		
		PacienteBean objPacienteBean;
		iObjLog.debug("Entrando a DatosPacienteAjax.consultaPacienteGrid:Entrando... Se encontraron " + lstPacientes + " pacientes");
		try {
			 if (lstPacientes != null) {
				 intPacientes = lstPacientes.size();
			 }
			 if (intPacientes > 50 ) {
				 strContadorHelp = ", PERO SOLO SE PRESENTAN 50 (PON MAS DATOS PARA FILTRAR) ";
			 }			
		strReturn = ("<table border='0' align='center' style='width: 1000px' class='tabla'>" + 
				 		"<tr>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>Total de Pacientes " + intPacientes + strContadorHelp +

							"	</font></b>" +
							"</th>"  + 
						"</tr>" +
					 "</table>" +	
					 "<table border='0' align='center' style='width: 1000px' class='tabla'>" + 
							"<tr>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>C&oacute;digo" + 
							"	</font></b>" +
							"</th>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>Apellido Paterno" + 
							"</th>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>Apellido Materno" + 
							"	</font></b>" +
							"</th>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>Nombre(s)" + 
							"	</font></b>" +
							"</th>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>Fecha Nacimiento" + 
							"	</font></b>" +
							"</th>" + 
						"</tr>");			    
			 if (lstPacientes != null) {
				for (int i = 0; i < lstPacientes.size() ; i++)
				{
					if (intHelpContador >= 50) {
						break;
					}
					intHelpContador = intHelpContador +1;
					objPacienteBean = (PacienteBean)lstPacientes.get(i);						
					strReturn += ("<tr>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:buscarOrdenesPaciente(" + objPacienteBean.getKpacientefundacion() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objPacienteBean.getKpacientefundacion() + 
										"</a></td>" + 
										"<td align='center'>"+
											objPacienteBean.getSappaterno()+
										"</td>" +
										"<td align='center'>"+
											objPacienteBean.getSapmaterno() +
										"</td>" +
										"<td align='center'>"+
											objPacienteBean.getSnombre() +
										"</td>" +
										"<td align='center'>"+
											objPacienteBean.getSnacimiento() +
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
