package mx.com.web2lab.ajax.dwr.facturacion.mayoreo;

import java.util.List;

import mx.com.web2lab.ajax.dwr.facturacion.formatos.FormatoFactura;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaConDescuentoSinonimosImpl;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaGlobalTextoConDescuentoImpl;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaPublicoImpl;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaSinDescuentoImpl;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaSinDescuentoSinonimosImpl;
import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
import mx.com.web2lab.backend.dao.facturacion.electronica.orden.OrdenDatosFacturacionDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionMayoreoDao;
import mx.com.web2lab.backend.dao.mail.MailDao;
import mx.com.web2lab.backend.hbm.ConfiguracionProperties;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;
import mx.com.web2lab.domain.facturacion.FacturacionElectronicaDomain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;


public class FacturarElectronicaMayoreoAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(FacturarElectronicaMayoreoAjax.class);
	
	public FacturarElectronicaMayoreoAjax(){
		iObjLog.debug("new: Generando nueva clase FacturarElectronicaMayoreoAjax");
	}
	
	public String crearFacturaConvenio(FacturaElectronicaBean objFacturaBean,int cSucursal,int cUsuario,boolean bolRefacturacion,int CConvenio) throws Exception
	{
		OrdenDatosFacturacionDao objDatosOrdenDAO = new OrdenDatosFacturacionDao();
		FacturacionMayoreoDao ObjFacturacionMayoreoDao = new FacturacionMayoreoDao();
		String strReturn = "";
		FormatoFactura objFormato = null;						
		
		List lstOrdenesListaFacturar = ObjFacturacionMayoreoDao.getOrdenListaFacturarConvenio(CConvenio);
		String strCorreoElectronico = "";
		String strkOrdenSucursales = "";

		
		iObjLog.debug("Entrando a FacturarElectronicaMayoreoAjax.crearOrdenFacturar:Entrando... " + objFacturaBean.getSrazonsocialreceptor() + " Sucursal " + cSucursal + " Usuario " + cUsuario + " CMArca " + objFacturaBean.getCmarca());
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
			if (objFacturaBean.getSFacturaOld() == "") {
				if ((objFacturaBean.getcFormato() > -1) && (objFacturaBean.getcFormato() < 4)) {
					for (int inti=0;inti<lstOrdenesListaFacturar.size();inti++) {
						objFacturaBean.setBolredondear(true);
						objFacturaBean.setkOrdenSucursal(((Integer)lstOrdenesListaFacturar.get(inti)).toString());
						objFacturaBean.setTurbine_User(cUsuario);
						objFacturaBean.setcSucursal(cSucursal);
						objFacturaBean = objDatosOrdenDAO.searchOrdenFacturacion(objFacturaBean,((Integer)lstOrdenesListaFacturar.get(inti)).toString(),bolRefacturacion);
						iObjLog.debug("Entrando a FacturarElectronicaMayoreoAjax.crearOrdenFacturar:Entrando... III " + objFacturaBean.getSrazonsocialreceptor() + " Sucursal " + cSucursal + " Usuario " + cUsuario + " CMArca " + objFacturaBean.getCmarca());
						this.FacturacionXOrden(objFacturaBean,((Integer)lstOrdenesListaFacturar.get(inti)).toString(), cSucursal,objFacturaBean.getCmarca(), cUsuario, bolRefacturacion, CConvenio, strCorreoElectronico);
					}
				} else {
					for (int inti=0;inti<lstOrdenesListaFacturar.size();inti++) {
						strkOrdenSucursales =  (strkOrdenSucursales + ((Integer)lstOrdenesListaFacturar.get(inti)).toString() + ",");
					}
					strkOrdenSucursales = (strkOrdenSucursales + "0");
					objFacturaBean.setBolredondear(true);
					objFacturaBean.setkOrdenSucursal(strkOrdenSucursales);
					objFacturaBean.setTurbine_User(cUsuario);
					objFacturaBean.setcSucursal(cSucursal);
					objFacturaBean = objDatosOrdenDAO.searchOrdenFacturacion(objFacturaBean,strkOrdenSucursales,bolRefacturacion);
					iObjLog.debug("Entrando a FacturarElectronicaMayoreoAjax.crearOrdenFacturar:Entrando... II " + objFacturaBean.getSrazonsocialreceptor() + " Sucursal " + cSucursal + " Usuario " + cUsuario + " CMArca " + objFacturaBean.getCmarca());
					
					this.FacturacionXOrden(objFacturaBean,strkOrdenSucursales, cSucursal,objFacturaBean.getCmarca(), cUsuario, bolRefacturacion, CConvenio, strCorreoElectronico);
				}
			}
			iObjLog.debug("Saliendo a FacturarElectronicaMayoreoAjax.crearOrdenFacturar:Saliendo...  ");
		} catch (TorqueException aObjException){
    	    iObjLog.error("Error .......FacturarElectronicaMayoreoAjax.crearOrdenFacturar:ErrorException....", aObjException);
    	    throw aObjException;
    	} finally {
			objDatosOrdenDAO = null;   
    	}
    	return strReturn;
	}	

	private String FacturacionXOrden(FacturaElectronicaBean objFacturaBean,String strkAdmision,int cSucursal,int cMarca,int cUsuario,boolean bolRefacturacion,int CConvenio,String strCorreoElectronico) throws Exception {
		String strReturn = "";
		FormatoFactura objFormato = null;						
		
		iObjLog.debug("Entrando a FacturarElectronicaMayoreoAjax.crearOrdenFacturar:Entrando... " + objFacturaBean.getSrazonsocialreceptor() + " kOrdenSucursal " + strkAdmision + " Sucursal " + cSucursal + " Usuario " + cUsuario + " cMarca " + cMarca);
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
			if (objFacturaBean.getSFacturaOld() == "") {
				switch (objFacturaBean.getcFormato()) {
					case 0: {
						objFormato = new FormatoFacturaPublicoImpl(cMarca);												
						break;
					}
					case 1: {
						objFormato = new FormatoFacturaSinDescuentoImpl(cMarca);						
						break;
					}
					case 2: {
						objFormato = new FormatoFacturaSinDescuentoSinonimosImpl(cMarca);						
						break;
					}
					case 3: {
						objFormato = new FormatoFacturaConDescuentoSinonimosImpl(cMarca);						
						break;
					}
					default: {
						objFormato = new FormatoFacturaPublicoImpl(cMarca);						
						break;
					}
				}
				objFacturaBean = objFormato.crearFacturaFormato(objFacturaBean);
				FacturacionElectronicaDomain objFEDomain = new FacturacionElectronicaDomain();
				if (objFacturaBean.getcTipoPago() == 0) {
					objFacturaBean.setsTipoPago("99 - Otros");					
					objFacturaBean.setsUltimosDigitos("");
				}
				objFEDomain.crearFacturaElectronicaMayoreo(objFacturaBean);
				strReturn = objFacturaBean.getsURL();
				if (strCorreoElectronico.trim().toString().length() > 2) {
					MailDao objMailDAO = new MailDao();
					String strPDFRead = "";
					String strXMLRead = "";
					if (cMarca == 1) {
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_Olab");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Olab");
					} else if (cMarca == 4){
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_Azteca");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Azteca");
					} else if (cMarca == 5){
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_SwissLab");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_SwissLab");
					} else if (cMarca == 19){
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_FamilyLabs");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_FamilyLabs");
					} else if (cMarca == 20){
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_Exakta");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Exakta");
					} else if (cMarca == 21){
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_AsesoresSur");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_AsesoresSur");
					}
					if (cMarca == 1) {
						objMailDAO.sendEmail("OLAB Diagnósticos Médicos, gracias por su preferencia", strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml","","",1);
					} else if (cMarca == 4) {
						objMailDAO.sendEmail("Azteca, Laboratorio Quimico Clinico Azteca, gracias por su preferencia", strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml","","",4);
					} else if (cMarca == 5) {
						objMailDAO.sendEmail("SWISSLAB S.A. de C.V., gracias por su preferencia", strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml","","",5);
					} else if (cMarca == 19) {
						objMailDAO.sendEmail("FamilyLabs Norte, gracias por su preferencia", strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml","","",5);
					} else if (cMarca == 20) {
						objMailDAO.sendEmail("Exakta, gracias por su preferencia", strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml","","",5);
					} else if (cMarca == 21) {
						objMailDAO.sendEmail("Asesores del Sur, gracias por su preferencia", strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml","","",5);
					}
				}
			} else {
				strReturn = objFacturaBean.getSFacturaOld();
			}
			iObjLog.debug("Saliendo a FacturarElectronicaMayoreoAjax.crearOrdenFacturar:Saliendo...  ");
		}catch (TorqueException aObjException){
    	    iObjLog.error("Error .......FacturarElectronicaMayoreoAjax.crearOrdenFacturar:ErrorException....", aObjException);
    	    throw aObjException;
		}
    	return strReturn;
	}	

	private String FacturacionXGrupo(FacturaElectronicaBean objFacturaBean,String strkAdmision,int cSucursal,int cMarca,int cUsuario,boolean bolRefacturacion,int CConvenio,String strCorreoElectronico,String Leyenda) throws Exception
	{
		OrdenDatosFacturacionDao objDatosOrdenDAO = new OrdenDatosFacturacionDao();
		FacturacionMayoreoDao ObjFacturacionMayoreoDao = new FacturacionMayoreoDao();
		String strReturn = "";
		FormatoFactura objFormato = null;						
		
		iObjLog.debug("Entrando a FacturarElectronicaMayoreoAjax.crearOrdenFacturar:Entrando... " + objFacturaBean.getSrazonsocialreceptor() + " kOrdenSucursal " + strkAdmision + " Sucursal " + cSucursal + " Usuario " + cUsuario + " cMarca " + cMarca);
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
			objFacturaBean.setBolredondear(true);
			objFacturaBean.setkOrdenSucursal(strkAdmision);
			objFacturaBean.setTurbine_User(cUsuario);
			objFacturaBean.setcSucursal(cSucursal);
			objFacturaBean = objDatosOrdenDAO.searchOrdenFacturacion(objFacturaBean,strkAdmision,bolRefacturacion);
			if (objFacturaBean.getSFacturaOld() == "") {
				switch (objFacturaBean.getcFormato()) {
					case 0: {
						objFormato = new FormatoFacturaPublicoImpl(cMarca);												
						break;
					}
					case 1: {
						objFormato = new FormatoFacturaSinDescuentoImpl(cMarca);						
						break;
					}
					case 2: {
						objFormato = new FormatoFacturaSinDescuentoSinonimosImpl(cMarca);						
						break;
					}
					case 3: {
						objFormato = new FormatoFacturaConDescuentoSinonimosImpl(cMarca);						
						break;
					}
					default: {
						objFormato = new FormatoFacturaPublicoImpl(cMarca);						
						break;
					}
				}
				objFacturaBean = objFormato.crearFacturaFormato(objFacturaBean);
				FacturacionElectronicaDomain objFEDomain = new FacturacionElectronicaDomain();
				objFEDomain.crearFacturaElectronicaMayoreo(objFacturaBean);
				strReturn = objFacturaBean.getsURL();
				if (strCorreoElectronico.trim().toString().length() > 2) {
					MailDao objMailDAO = new MailDao();
					String strPDFRead = "";
					String strXMLRead = "";
					if (cMarca == 1) {
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_Olab");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Olab");						
					} else if (cMarca == 4) {
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_Azteca");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Azteca");
					} else if (cMarca == 5) {
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_SwissLab");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_SwissLab");
					} else if (cMarca == 19) {
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_FamilyLabs");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_FamilyLabs");
					} else if (cMarca == 20) {
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_Exakta");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Exakta");
					} else if (cMarca == 21) {
						strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread_AsesoresSur");
						strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_AsesoresSur");
					}
					if (cMarca == 1) {
						objMailDAO.sendEmail("OLAB Diagnósticos Médicos, Facturacion Electrónica, gracias por su preferencia", strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml","","",1);
					} else if (cMarca == 4) {
						objMailDAO.sendEmail("Azteca, Laboratorio Quimico Clinico Azteca, Facturacion Electrónica, gracias por su preferencia, gracias por su preferencia", strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml","","",4);
					} else if (cMarca == 5) {
						objMailDAO.sendEmail("SWISSLAB S.A. de C.V., Facturacion Electrónica, gracias por su preferencia, gracias por su preferencia", strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml","","",5);
					} else if (cMarca == 19) {
						objMailDAO.sendEmail("FamilyLabs Norte, Facturacion Electrónica, gracias por su preferencia, gracias por su preferencia", strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml","","",5);
					} else if (cMarca == 20) {
						objMailDAO.sendEmail("Exakta, Facturacion Electrónica, gracias por su preferencia, gracias por su preferencia", strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml","","",5);
					} else if (cMarca == 21) {
						objMailDAO.sendEmail("Asesores del Sur, Facturacion Electrónica, gracias por su preferencia, gracias por su preferencia", strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml","","",5);
					}
				}
			} else {
				strReturn = objFacturaBean.getSFacturaOld();
			}
			iObjLog.debug("Saliendo a FacturarElectronicaMayoreoAjax.crearOrdenFacturar:Saliendo...  ");
		}catch (TorqueException aObjException){
    	    iObjLog.error("Error .......FacturarElectronicaMayoreoAjax.crearOrdenFacturar:ErrorException....", aObjException);
    	    throw aObjException;
    	} finally {
			objDatosOrdenDAO = null;   
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
    		valida = super.isSesionValida(true);
    	}catch(Exception e ){
    		iObjLog.error("isSesionValida:No existe una sesion valida para el usuario");
    		return valida;
    	}
    	return valida;
    }
    
}
