package mx.com.web2lab.ajax.dwr.facturacion.sucursales;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;

import mx.com.web2lab.ajax.dwr.facturacion.formatos.FormatoFactura;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaConDescuentoSinonimosImpl;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaPublicoImpl;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaSinDescuentoImpl;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaSinDescuentoSinonimosImpl;
import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.facturacion.DatosFiscalesBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
import mx.com.web2lab.backend.dao.facturacion.electronica.orden.OrdenDatosFacturacionDao;
import mx.com.web2lab.backend.dao.facturacion.tool.DatosFiscalesDao;
import mx.com.web2lab.backend.dao.mail.MailDao;
import mx.com.web2lab.backend.hbm.ConfiguracionProperties;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;
import mx.com.web2lab.domain.facturacion.FacturacionElectronicaDomain;

public class FacturarElectronicaSucursalAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(FacturarElectronicaSucursalAjax.class);
	
	public FacturarElectronicaSucursalAjax(){
		iObjLog.debug("new: Generando nueva clase FacturarElectronicaSucursalAjax");
	}

	
	public String crearOrdenFacturarElectronica(FacturaElectronicaBean objFacturaBean,String strkAdmision,int cSucursal,int cUsuario,boolean bolRefacturacion,String strCorreoElectronico) throws Exception {
		String strReturn = "";
		DatosFiscalesDao objDatosFiscalesDao = new DatosFiscalesDao();
		DatosFiscalesBean objDatosFiscalesBean = null;
		iObjLog.debug("Entrando a FacturarElectronicaSucursalAjax.crearOrdenFacturarElectronica:Entrando... " + objFacturaBean.getSrazonsocialreceptor() + " kOrdenSucursal " + strkAdmision + " Sucursal " + cSucursal + " Usuario " + cUsuario);
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");
			objDatosFiscalesBean = objDatosFiscalesDao.buscarDatosFiscalesOrden(-1, objFacturaBean.gethDatosFiscal());
			if (objDatosFiscalesBean != null) {
				objFacturaBean.sethDatosFiscal(objDatosFiscalesBean.getkDatosFiscales());
				objFacturaBean.setSrazonsocialreceptor(objDatosFiscalesBean.getStrRazonSocial());
				objFacturaBean.setSrfcreceptor(objDatosFiscalesBean.getStrRFC());
				objFacturaBean.setScallereceptor(objDatosFiscalesBean.getStrDireccion());
				objFacturaBean.setSrfcreceptor(objFacturaBean.getSrfcreceptor().trim().replaceAll(" ",""));
				objFacturaBean.setSrfcreceptor(objFacturaBean.getSrfcreceptor().trim().replaceAll("-",""));
	//			objFacturaBean.snexteriorreceptor="";
	//			objFacturaBean.sninteriorreceptor="";
				objFacturaBean.setScoloniareceptor(objDatosFiscalesBean.getStrColonia());
				objFacturaBean.setSciudadreceptor(objDatosFiscalesBean.getStrCiudad());
				objFacturaBean.setSmunicipioreceptor(objDatosFiscalesBean.getStrDelegacionMunicipio());
				objFacturaBean.setSestadoreceptor(objDatosFiscalesBean.getStrEstado());
				objFacturaBean.setScodigopostalreceptor(objDatosFiscalesBean.getcPostal());
				objFacturaBean.setSpaisreceptor("MEXICO");			
				strReturn = this.crearOrdenFacturar(objFacturaBean, strkAdmision, cSucursal, cUsuario, bolRefacturacion, strCorreoElectronico);
			}
			iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.crearOrdenFacturarElectronica:Saliendo...  ");
		}catch (TorqueException aObjException){
    	    iObjLog.error("FacturarElectronicaSucursalAjax.crearOrdenFacturarElectronica:ErrorException....", aObjException);
    	    throw aObjException;
    	} finally {
    		objDatosFiscalesDao = null;
    		objDatosFiscalesBean = null;
    	}
    	return strReturn;
	}	
	
	
	public String crearOrdenFacturar(FacturaElectronicaBean objFacturaBean,String strkAdmision,int cSucursal,int cUsuario,boolean bolRefacturacion,String strCorreoElectronico) throws Exception
	{
		OrdenDatosFacturacionDao objDatosOrdenDAO = new OrdenDatosFacturacionDao();
		String strReturn = "";
		FormatoFactura objFormato = null;						
		FacturacionElectronicaDomain objFEDomain = new FacturacionElectronicaDomain();
		iObjLog.debug("Entrando a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Entrando... " + objFacturaBean.getSrazonsocialreceptor() + " kOrdenSucursal " + strkAdmision + " Sucursal " + cSucursal + " Usuario " + cUsuario + " Marca " + objFacturaBean.getCmarca());
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");
			objFacturaBean.setBolredondear(true);
			objFacturaBean.setkOrdenSucursal(strkAdmision);
			objFacturaBean.setTurbine_User(cUsuario);
			objFacturaBean.setcSucursal(cSucursal);			
			objFacturaBean = objDatosOrdenDAO.searchOrdenFacturacion(objFacturaBean,strkAdmision,bolRefacturacion);
			if (objFacturaBean.getSFacturaOld() == "") {
				switch (objFacturaBean.getcFormato()) {
					case 0: {
//						objFormato = new FormatoFacturaGlobalTextoConDescuentoImpl();												
						objFormato = new FormatoFacturaPublicoImpl(0);												
						break;
					}
					case 1: {
//						objFormato = new FormatoFacturaGlobalTextoConDescuentoImpl();												
						objFormato = new FormatoFacturaSinDescuentoImpl(0);						
						break;
					}
					case 2: {
						objFormato = new FormatoFacturaSinDescuentoSinonimosImpl(0);						
						break;
					}
					case 3: {
						objFormato = new FormatoFacturaConDescuentoSinonimosImpl(0);						
						break;
					}
					default: {
						objFormato = new FormatoFacturaPublicoImpl(0);						
						break;
					}
				}
				objFacturaBean = objFormato.crearFacturaFormato(objFacturaBean);
				objFEDomain.crearFacturaElectronica(objFacturaBean);
				strReturn = objFacturaBean.getSseriofoliocompleto();
				if (strCorreoElectronico.trim().toString().length() > 2) {
					MailDao objMailDAO = new MailDao();
					String strPDFRead = "";
					String strXMLRead = "";
					if (objFacturaBean.getCmarca() == 1) {
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_Olab");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Olab");
					} else if (objFacturaBean.getCmarca() == 5){
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_SwissLab");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_SwissLab");
					} else if (objFacturaBean.getCmarca() == 19){
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_FamilyLabs");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_FamilyLabs");
					} else if (objFacturaBean.getCmarca() == 20){
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_Exakta");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Exakta");
					} else if (objFacturaBean.getCmarca() == 21){
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_AsesoresSur");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_AsesoresSur");
					} else if (objFacturaBean.getCmarca() == 16){
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_Moreira");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Moreira");
					} else if (objFacturaBean.getCmarca() == 22){
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_Polab");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Polab");
					} else if (objFacturaBean.getCmarca() == 25){
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_BiomedicaReferencia");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_BiomedicaReferencia");
					} else if (objFacturaBean.getCmarca() == 26){
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_Promedic");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Promedic");
					} else if (objFacturaBean.getCmarca() == 9){
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_SwissHospital");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_SwissHospital");
					}else{
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_Azteca");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Azteca");
					}
					String strCorreoCopy = "";
					if (objFacturaBean.getcSucursal() == 1) { strCorreoCopy = "sucursal.morazan@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 2) {strCorreoCopy = "sucursal.roma@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 3) {strCorreoCopy = "sucursal.consulado@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 4) {strCorreoCopy = "sucursal.neza@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 5) {strCorreoCopy = "sucursal.lindavista@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 7) {strCorreoCopy = "sucursal.ecatepec@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 8) {strCorreoCopy = "sucursal.centerplaza@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 9) {strCorreoCopy = "sucursal.tacubaya@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 10) {strCorreoCopy = "sucursal.tlalpan@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 11) {strCorreoCopy = "sucursal.coacalco@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 12) {strCorreoCopy = "sucursal.satelite@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 15) {strCorreoCopy = "sucursal.santafe@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 35) {strCorreoCopy = "sucursal.riodelaloza@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 36) {strCorreoCopy = "sucursal.mixcoac@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 37) {strCorreoCopy = "sucursal.miramontes@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 40) {strCorreoCopy = "sucursal.condesa@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 43) {strCorreoCopy = "sucursal.anzures@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 44) {strCorreoCopy = "sucursal.montevideo@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 45) {strCorreoCopy = "sucursal.tezontle@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 49) {strCorreoCopy = "sucursal.metepec@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 53) {strCorreoCopy = "sucursal.delvalle@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 54) {strCorreoCopy = "sucursal.tlalnepantla@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 55) {strCorreoCopy = "sucursal.coyoacan@olab.com.mx"; }
					else if (objFacturaBean.getcSucursal() == 61) {strCorreoCopy = "caja.roma@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 62) {strCorreoCopy = "resultados.reyes@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 63) {strCorreoCopy = "azteca_labchalco@outlook.com"; }
					else if (objFacturaBean.getcSucursal() == 64) {strCorreoCopy = "azteca_chimalhuacan@hotmail.com"; }
					else if (objFacturaBean.getcSucursal() == 65) {strCorreoCopy = "labixtapaluca@hotmail.com"; }
					else if (objFacturaBean.getcSucursal() == 66) {strCorreoCopy = "cristina.velazquez@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 68) {strCorreoCopy = "monica.cervantes@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 69) {strCorreoCopy = "azteca_chimalhuacan2@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 70) {strCorreoCopy = "laboratorio_ameca@hotmail.com"; }
					else if (objFacturaBean.getcSucursal() == 71) {strCorreoCopy = "brissa.padron@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 72) {strCorreoCopy = "guadalupe.medina@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 74) {strCorreoCopy = "lab_aztecavchalco@hotmail.com"; }
					else if (objFacturaBean.getcSucursal() == 75) {strCorreoCopy = "recepcion.universidad@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 76) {strCorreoCopy = "unidad.sanjeronimo@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 77) {strCorreoCopy = "culhuacan.facturacion@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 78) {strCorreoCopy = "unidad.tlalpan@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 79) {strCorreoCopy = "yazmin.herrera@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 80) {strCorreoCopy = "caja.ermita@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 81) {strCorreoCopy = "elizabeth.torres@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 82) {strCorreoCopy = "claudia.flores@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 83) {strCorreoCopy = "yadira.perez@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 85) {strCorreoCopy = "cristina.santos@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 86) {strCorreoCopy = "unidad.metropuebla@aztecalab.mx"; }
					else if (objFacturaBean.getcSucursal() == 87) {strCorreoCopy = "vallecentrolqc@hotmail.com"; }
					else if (objFacturaBean.getcSucursal() == 88) {strCorreoCopy = "chalco.centro@outlook.com"; }
					else if (objFacturaBean.getcSucursal() == 96) {strCorreoCopy = "sucursal.coyoacan@olab.com.mx"; }
					if (objFacturaBean.getcSucursal() == 100){
						strCorreoCopy = "oliver_rubio@hotmail.com";
					}
					if (objFacturaBean.getCmarca() == 1) {
						objMailDAO.sendEmail("OLAB Diagn&oacute;sticos m&eacute;dicos, Facturacion electr&oacute;nica, " + objFacturaBean.getSrazonsocialreceptor(), strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml",objFacturaBean.getSrazonsocialreceptor(),strCorreoCopy,1);
					} else if(objFacturaBean.getCmarca() == 5){
						objMailDAO.sendEmail("SWISSLAB S.A. de C.V., Facturacion electr&oacute;nica, " + objFacturaBean.getSrazonsocialreceptor(), strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml",objFacturaBean.getSrazonsocialreceptor(),strCorreoCopy,5);
					} else if(objFacturaBean.getCmarca() == 19){
						objMailDAO.sendEmail("FamilyLabs Norte, Facturacion electr&oacute;nica, " + objFacturaBean.getSrazonsocialreceptor(), strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml",objFacturaBean.getSrazonsocialreceptor(),strCorreoCopy,5);
					} else if(objFacturaBean.getCmarca() == 20){
						objMailDAO.sendEmail("Exakta, Facturacion electr&oacute;nica, " + objFacturaBean.getSrazonsocialreceptor(), strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml",objFacturaBean.getSrazonsocialreceptor(),strCorreoCopy,5);
					} else if(objFacturaBean.getCmarca() == 21){
						objMailDAO.sendEmail("Asesores del Sur, Facturacion electr&oacute;nica, " + objFacturaBean.getSrazonsocialreceptor(), strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml",objFacturaBean.getSrazonsocialreceptor(),strCorreoCopy,5);
					
					} else if(objFacturaBean.getCmarca() == 16){
						objMailDAO.sendEmail("Moreira, Facturacion electr&oacute;nica, " + objFacturaBean.getSrazonsocialreceptor(), strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml",objFacturaBean.getSrazonsocialreceptor(),strCorreoCopy,5);
					
					} else if(objFacturaBean.getCmarca() == 22){
						objMailDAO.sendEmail("Polab, Facturacion electr&oacute;nica, " + objFacturaBean.getSrazonsocialreceptor(), strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml",objFacturaBean.getSrazonsocialreceptor(),strCorreoCopy,5);
					
					} else if(objFacturaBean.getCmarca() == 25){
						objMailDAO.sendEmail("Biomedica de Referencia, Facturacion electr&oacute;nica, " + objFacturaBean.getSrazonsocialreceptor(), strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml",objFacturaBean.getSrazonsocialreceptor(),strCorreoCopy,5);
					
					} else if(objFacturaBean.getCmarca() == 26){
						objMailDAO.sendEmail("Promedic, Facturacion electr&oacute;nica, " + objFacturaBean.getSrazonsocialreceptor(), strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml",objFacturaBean.getSrazonsocialreceptor(),strCorreoCopy,5);
					
					} else if(objFacturaBean.getCmarca() == 9){
						objMailDAO.sendEmail("SWISS HOSPITAL, Facturacion electr&oacute;nica, " + objFacturaBean.getSrazonsocialreceptor(), strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml",objFacturaBean.getSrazonsocialreceptor(),strCorreoCopy,5);
					
					}else{
						objMailDAO.sendEmail("Azteca, Laboratorio Quimico Clinico Azteca, Facturacion electr&oacute;nica, " + objFacturaBean.getSrazonsocialreceptor(), strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml",objFacturaBean.getSrazonsocialreceptor(),strCorreoCopy,4);
					}
				}
			} else {
				strReturn = objFacturaBean.getSFacturaOld();
			}
			iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Saliendo...  ");
		}catch (TorqueException aObjException){
    	    iObjLog.error("FacturarElectronicaSucursalAjax.crearOrdenFacturar:ErrorException....", aObjException);
    	    throw aObjException;
    	} finally {
			objDatosOrdenDAO = null;   
			objFEDomain = null;
    	}
    	return strReturn;
	}	

	
	public static String llenaIdFactura(String strNemonico,String intFactura,int MaxLength) {
		String strReturn = "";
		int intTotal = (strNemonico.length() + intFactura.length());
		for(int i = intTotal;i <= MaxLength;i++) {
			strReturn += "0";
		}		
		return strNemonico + strReturn + intFactura;
	}
	
    /**
     * Metodo que verifica que exista una sesion valida 
     * @return
     * @throws Exception
     */
    public boolean isSesionValida() {
    	boolean valida = false;
    	try{
    		valida = super.isSesionValida(false);
    	}catch(Exception e ){
    		iObjLog.error("isSesionValida:No existe una sesion valida para el usuario");
    		return valida;
    	}
    	return valida;
    }
    
}
