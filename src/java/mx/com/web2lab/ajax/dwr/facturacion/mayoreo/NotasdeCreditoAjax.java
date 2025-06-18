package mx.com.web2lab.ajax.dwr.facturacion.mayoreo;

import java.math.BigDecimal;
import java.util.Date;

import mx.com.web2lab.ajax.dwr.facturacion.formatos.FormatoFactura;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaConDescuentoSinonimosImpl;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaPublicoImpl;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaSinDescuentoImpl;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaSinDescuentoSinonimosImpl;
import mx.com.web2lab.ajax.dwr.facturacion.tool.FacturaElectronicaPDF;
import mx.com.web2lab.ajax.dwr.facturacion.tool.FacturaElectronicaXML;
import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.comer.ConvenioBean;
import mx.com.web2lab.backend.beans.comer.PagoFacturaBean;
import mx.com.web2lab.backend.beans.facturacion.NotaCreditoBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaEmpresaBean;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.NotaDeCreditoDao;
import mx.com.web2lab.backend.dao.comer.PagoFacturaDao;
import mx.com.web2lab.backend.dao.facturacion.electronica.fop.FacturaElectronicaFOPDao;
import mx.com.web2lab.backend.dao.facturacion.electronica.orden.OrdenDatosFacturacionDao;
import mx.com.web2lab.backend.dao.facturacion.electronica.security.SelloDigitalDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionMayoreoDao;
import mx.com.web2lab.backend.dao.mail.MailDao;
import mx.com.web2lab.backend.hbm.ConfiguracionProperties;
import mx.com.web2lab.backend.hbm.om.ap.TFactura;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;
import mx.com.web2lab.backend.util.formatos.Formatos;
import mx.com.web2lab.domain.facturacion.FacturacionElectronicaDomain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;


public class NotasdeCreditoAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(NotasdeCreditoAjax.class);
	
	public NotasdeCreditoAjax(){
		iObjLog.debug("new: Generando nueva clase FacturarElectronicaMayoreoAjax");
	}
	
	public String getFacturasConvenio(int cconvenio) throws Exception
	{
		iObjLog.debug("Entrando NotasdeCreditoAjax.getFacturasConvenio:Entrando... ");
		String strReturn="";
		NotaDeCreditoDao objNotadeCreditoDAO = new NotaDeCreditoDao();
		
		try {			
			   if(!isSesionValida(true))throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");						
			   strReturn = objNotadeCreditoDAO.getFacturasConvenio(cconvenio);
			   iObjLog.debug("Saliendo NotasdeCreditoAjax.getFacturasConvenio:Saliendo...  ");
		} catch (Exception aObjException){
			iObjLog.error("Error NotasdeCreditoAjax.getFacturasConvenio:Exception....", aObjException);
			throw aObjException;
		} finally {
			objNotadeCreditoDAO = null;
		}
		return strReturn;
	}
	
	public String getFacturasConvenioAsignacion(int cconvenio,String abloques) throws Exception
	{
		iObjLog.debug("Entrando NotasdeCreditoAjax.getFacturasConvenioAsignacion:Entrando... "+cconvenio+"--"+ abloques);
		String strReturn="";
		NotaDeCreditoDao objNotadeCreditoDAO = new NotaDeCreditoDao();
		
		try {			
			   if(!isSesionValida(true))throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");						
			   strReturn = objNotadeCreditoDAO.getFacturasConvenioAsignacion(cconvenio,abloques);
			   iObjLog.debug("Saliendo NotasdeCreditoAjax.getFacturasConvenio:Saliendo...  ");
		} catch (Exception aObjException){
			iObjLog.error("Error NotasdeCreditoAjax.getFacturasConvenio:Exception....", aObjException);
			throw aObjException;
		} finally {
			objNotadeCreditoDAO = null;
		}
		return strReturn;
	}
	
	public String getFacturasConvenioElegidas(String strfacturasElegidas) throws Exception{
		iObjLog.debug("Entrando NotasdeCreditoAjax.getFacturasConvenioElegidas:Entrando... "+strfacturasElegidas);
		String strReturn="";
		NotaDeCreditoDao objNotadeCreditoDAO = new NotaDeCreditoDao();
		
		try {			
			   if(!isSesionValida(true))throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");						
			   strReturn = objNotadeCreditoDAO.getFacturasConvenioElegidas(strfacturasElegidas);
			   iObjLog.debug("Saliendo NotasdeCreditoAjax.getFacturasConvenioElegidas:Saliendo...  "+strReturn);
		} catch (Exception aObjException){
			iObjLog.error("Error NotasdeCreditoAjax.getFacturasConvenioElegidas:Exception....", aObjException);
			throw aObjException;
		} finally {
			objNotadeCreditoDAO = null;
		}
		return strReturn;
	}
	
	
	public String getFacturasConvenioElegidasAsignacionBloque(String strfacturasElegidas,String sbloque) throws Exception{
		iObjLog.debug("Entrando NotasdeCreditoAjax.getFacturasConvenioElegidasAsignacionBloque:Entrando... "+strfacturasElegidas+sbloque);
		String strReturn="";
		NotaDeCreditoDao objNotadeCreditoDAO = new NotaDeCreditoDao();
		
		try {			
			   if(!isSesionValida(true))throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");						
			   strReturn = objNotadeCreditoDAO.getFacturasConvenioElegidasAsignacionBloque(strfacturasElegidas,sbloque);
			   iObjLog.debug("Saliendo NotasdeCreditoAjax.getFacturasConvenioElegidasAsignacionBloque:Saliendo...  "+strReturn);
		} catch (Exception aObjException){
			iObjLog.error("Error NotasdeCreditoAjax.getFacturasConvenioElegidasAsignacionBloque:Exception....", aObjException);
			throw aObjException;
		} finally {
			objNotadeCreditoDAO = null;
		}
		return strReturn;
	}
	
 public String creaNota(String strfacturasElegidas,int cconvenio,String strdescripcionnota,double dmontonotacredito,Integer user_id) throws Exception{
		String strreturn="";
		NotaDeCreditoDao objNotaDAO = new NotaDeCreditoDao();
	 try{
		 	
		 iObjLog.debug("Entrando a NotasdeCreditoAjax.crearNota:...  "+strfacturasElegidas+" cconvenio"+cconvenio+" strdescripcionnota "+strdescripcionnota+" dmontonotacredito "+dmontonotacredito+" user_id"+user_id );
		 	strreturn=objNotaDAO.creaNota(strfacturasElegidas,cconvenio,strdescripcionnota,dmontonotacredito,user_id);
		 	
		
	 }catch (Exception aObjException){
	    	    iObjLog.error("FacturarElectronicaSucursalAjax.crearOrdenFacturar:ErrorException....", aObjException);
	    	    throw aObjException;   
	 } finally {
	    		objNotaDAO = null;   
	 }
	    	return strreturn;
 }
 
 
 public String asignarBloqueaFactura(String strfacturasElegidas,int cconvenio,String strdescripcionnota,double dmontonotacredito,Integer user_id,String srtbloque) throws Exception{
		String strreturn="";
		NotaDeCreditoDao objNotaDAO = new NotaDeCreditoDao();
	 try{
		 	
		 iObjLog.debug("Entrando a NotasdeCreditoAjax.crearNota:...  "+strfacturasElegidas+" cconvenio"+cconvenio+" strdescripcionnota "+strdescripcionnota+" dmontonotacredito "+dmontonotacredito+" user_id"+user_id +"--srtBloque--"+srtbloque);
		 	strreturn=objNotaDAO.asignarBloqueaFactura(strfacturasElegidas,cconvenio,strdescripcionnota,dmontonotacredito,user_id,srtbloque);
		 	
		
	 }catch (Exception aObjException){
	    	    iObjLog.error("FacturarElectronicaSucursalAjax.crearOrdenFacturar:ErrorException....", aObjException);
	    	    throw aObjException;   
	 } finally {
	    		objNotaDAO = null;   
	 }
	    	return strreturn;
}
 
 
 public String getCambioDeBloque(int cConvenio,String srtbloque, int nuevoBloque) throws Exception{
		String strreturn="";
		NotaDeCreditoDao objNotaDAO = new NotaDeCreditoDao();
	 try{
		 	
		 iObjLog.debug("Entrando a getCambioDeBloque:...  "+" cconvenio"+cConvenio+" srtBloque "+srtbloque+" nuevoBloque "+nuevoBloque);
		 	strreturn=objNotaDAO.getCambioBloque(cConvenio,srtbloque,nuevoBloque);
		 	
		
	 }catch (Exception aObjException){
	    	    iObjLog.error("FacturarElectronicaSucursalAjax.getCambioDeBloque:ErrorException....", aObjException);
	    	    throw aObjException;   
	 } finally {
	    		objNotaDAO = null;   
	 }
	    	return strreturn;
}
 
/**	
	public String creaNota(String strfacturasElegidas,int cconvenio,String strdescripcionnota,Double dmontonotacredito,Integer user_id) throws Exception{
		String strreturn="";
		
			String strReturn = "";
			FormatoFactura objFormato = null;
			FacturaElectronicaEmpresaBean objFacturaEmpresaBean=null;
			NotaDeCreditoDao objNotaDAO = new NotaDeCreditoDao();
			iObjLog.debug("Entrando a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Entrando... " );
			try {
				
				objFacturaEmpresaBean.setCconvenio(cconvenio);
				objFacturaEmpresaBean.setcSucursal(1004);
				objFacturaEmpresaBean.setTurbine_User(user_id);
				
				objFacturaEmpresaBean = objNotaDAO.generarNota(objFacturaEmpresaBean,strfacturasElegidas,strdescripcionnota, dmontonotacredito);
				
					switch (objFacturaEmpresaBean.getcFormato()) {
						case 0: {
//							objFormato = new FormatoFacturaGlobalTextoConDescuentoImpl();												
							objFormato = new FormatoFacturaPublicoImpl();												
							break;
						}
						case 1: {
//							objFormato = new FormatoFacturaGlobalTextoConDescuentoImpl();												
							objFormato = new FormatoFacturaSinDescuentoImpl();						
							break;
						}
						case 2: {
							objFormato = new FormatoFacturaSinDescuentoSinonimosImpl();						
							break;
						}
						case 3: {
							objFormato = new FormatoFacturaConDescuentoSinonimosImpl();						
							break;
						}
						default: {
							objFormato = new FormatoFacturaPublicoImpl();						
							break;
						}
					}
					//objFacturaEmpresaBean = objFormato.crearFacturaFormato(objFacturaEmpresaBean);
					//FacturacionElectronicaDomain objFEDomain = new FacturacionElectronicaDomain();
					//objFEDomain.crearFacturaElectronica(objFacturaEmpresaBean);
					strReturn = objFacturaEmpresaBean.getsURL();
					/**
					if (strCorreoElectronico.trim().toString().length() > 2) {
						MailDao objMailDAO = new MailDao();
						String strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread");
						String strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread");
						objMailDAO.sendEmail("Factura electr&oacute;nica, OLAB Diagn&oacute;sticos m&eacute;dicos, gracias por su preferencia", strCorreoElectronico, strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml");
					}
				
				iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Saliendo...  ");
			}catch (TorqueException aObjException){
	    	    iObjLog.error("FacturarElectronicaSucursalAjax.crearOrdenFacturar:ErrorException....", aObjException);
	    	    throw aObjException;
	    	} finally {
	    		objNotaDAO = null;   
	    	}
	    	return strReturn;
		}**/
		

/**	
	public String crearNota(String strfacturasElegidas,int cconvenio,String strdescripcionnota,Double dmontonotacredito,Integer user_id) throws Exception{
		iObjLog.debug("Entrando NotasdeCreditoAjax.crearNota:Entrando... "+strfacturasElegidas+" "+cconvenio+" "+strdescripcionnota+" "+dmontonotacredito+" "+user_id);
		String strReturn="";
		NotaDeCreditoDao objNotadeCreditoDAO = new NotaDeCreditoDao();
		FacturaElectronicaBean objFacturaBean = new FacturaElectronicaBean();
		try {			
			   if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesión válida ...");						
			   
			   objFacturaBean=this.crearFacturaElectronica(objNotadeCreditoDAO.generarNota(strfacturasElegidas,cconvenio,strdescripcionnota,dmontonotacredito));
			   																			
				strReturn = objFacturaBean.getsURL();
				if ("bibiana.yanez@olab.com.m".trim().toString().length() > 2) {
					MailDao objMailDAO = new MailDao();
					String strPDFRead = ConfiguracionProperties.getPropiedad("reporte.ruta.pdflocalread");
					String strXMLRead = ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread");
					objMailDAO.sendEmail("Factura electr&oacute;nica, OLAB Diagn&oacute;sticos m&eacute;dicos, gracias por su preferencia", "bibiana.yanez@olab.com.m", strPDFRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".pdf", strXMLRead + "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto() + ".xml");
				}
			   iObjLog.debug("Saliendo NotasdeCreditoAjax.crearNota:Saliendo...  ");
		} catch (Exception aObjException){
			iObjLog.error("Error NotasdeCreditoAjax.crearNota:Exception....", aObjException);
			throw aObjException;
		} finally {
			objNotadeCreditoDAO = null;
		}
	 return strReturn;
	}

	public FacturaElectronicaBean crearFacturaElectronica(FacturaElectronicaBean objFacturaBean) throws Exception {
		iObjLog.debug("Entrando a FacturacionElectronicaDomain.crearFacturaElectronica:...  ");
		NotaDeCreditoDao objNotadeCredito = new NotaDeCreditoDao();
		FacturaElectronicaXML objFacturaElectronicaXML = new FacturaElectronicaXML();
		FacturaElectronicaFOPDao objFacturaElectronicaFOPDAO = new FacturaElectronicaFOPDao();
		SelloDigitalDao objSelloDigitalDao = new SelloDigitalDao();
		FacturaElectronicaPDF objFacturaElectronicaPDF = new FacturaElectronicaPDF();
		try {
			objFacturaBean = objFacturaElectronicaXML.createXML(objFacturaBean);					
			objFacturaBean = objNotadeCredito.persistirFactura(objFacturaBean);
			//objFacturaBean = objDatosOrdenDAO.generaUpdateOrdenFac(objFacturaBean);
			objFacturaBean = objNotadeCredito.generaSelloCadenaDigital(objFacturaBean);
			objFacturaBean = objSelloDigitalDao.sellaFacturaDigitalmente(objFacturaBean);								
			objFacturaBean.setSfop(objFacturaElectronicaFOPDAO.createFactura(objFacturaBean));					
			objFacturaBean.setsURL(objFacturaElectronicaPDF.createDocument(objFacturaBean));    				
			objFacturaBean = objFacturaElectronicaXML.createXML(objFacturaBean);					
			objNotadeCredito.actualizarFacturaXML(objFacturaBean);			
			iObjLog.debug("Saliendo a FacturacionElectronicaDomain.crearFacturaElectronica:Saliendo...  ");
		}catch (TorqueException aObjException){
    	    iObjLog.error("FacturacionElectronicaDomain.crearFacturaElectronica:ErrorException....", aObjException);
    	    throw aObjException;
    	} finally {
    		objNotadeCredito = null;   
			objFacturaElectronicaXML = null;
			objSelloDigitalDao = null;
			objFacturaElectronicaFOPDAO = null;
			objFacturaElectronicaPDF = null;
    	}
		return objFacturaBean;
	}
	
	**/	
	

}
