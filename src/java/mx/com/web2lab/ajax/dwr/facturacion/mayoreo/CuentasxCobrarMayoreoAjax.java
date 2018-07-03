package mx.com.web2lab.ajax.dwr.facturacion.mayoreo;

import java.math.BigDecimal;
import java.util.Date;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.comer.ConvenioBean;
import mx.com.web2lab.backend.beans.comer.PagoFacturaBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
import mx.com.web2lab.backend.dao.comer.PagoFacturaDao;
import mx.com.web2lab.backend.dao.facturacion.electronica.orden.OrdenDatosFacturacionDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionMayoreoDao;
import mx.com.web2lab.backend.hbm.om.ap.TFactura;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;
import mx.com.web2lab.backend.util.formatos.Formatos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class CuentasxCobrarMayoreoAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(CuentasxCobrarMayoreoAjax.class);
	
	public CuentasxCobrarMayoreoAjax(){
		iObjLog.debug("new: Generando nueva clase FacturarElectronicaMayoreoAjax");
	}
	
	public PagoFacturaBean pagoFactura(int kFactura,double dblAnticipo, double dblMontoPago,double dblSaldo,int cTipoPago,int UserID,String strFechaPago, int intGrupo) throws Exception
	{
		iObjLog.debug("Entrando CuentasxCobrarMayoreoAjax.pagoFactura:Entrando... ");		
		PagoFacturaDao objPagoFacturaDao = new PagoFacturaDao();
		PagoFacturaBean objPagoFacturaBean = new PagoFacturaBean();
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			objPagoFacturaBean.setCestadoregistro(52);
			objPagoFacturaBean.setCtipopago(cTipoPago);
			objPagoFacturaBean.setDfechapago(new Formatos().getFecha(strFechaPago));
			objPagoFacturaBean.setDregistro(new Date());
			objPagoFacturaBean.setKfactura(kFactura);
			objPagoFacturaBean.setManticipo(new BigDecimal(dblAnticipo));
			objPagoFacturaBean.setMpago(new BigDecimal(dblMontoPago));
			objPagoFacturaBean.setMsaldo(new BigDecimal(dblSaldo));
			objPagoFacturaBean.setUserId(UserID);
			objPagoFacturaBean.setUgrupopago(intGrupo);
			objPagoFacturaBean = objPagoFacturaDao.pagoFactura(objPagoFacturaBean);
			iObjLog.debug("Saliendo CuentasxCobrarMayoreoAjax.pagoFactura:Saliendo...  ");
		} catch (Exception aObjException){
			iObjLog.error("Error CuentasxCobrarMayoreoAjax.buscarCliente:Exception....", aObjException);
			throw aObjException;
		} finally {
			objPagoFacturaDao = null;
		}
		return objPagoFacturaBean;
	}	

	public PagoFacturaBean getPagoFactura(int kFactura) throws Exception
	{
		iObjLog.debug("Entrando CuentasxCobrarMayoreoAjax.getDatosPagoFactura:Entrando... " + kFactura);		
		PagoFacturaDao objPagoFacturaDao = new PagoFacturaDao();
		PagoFacturaBean objPagoFacturaBean = new PagoFacturaBean();
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			objPagoFacturaBean = objPagoFacturaDao.getDatosPagoFactura(kFactura,true);
			iObjLog.debug("Saliendo CuentasxCobrarMayoreoAjax.getDatosPagoFactura:Saliendo... mTotalFactura=" + objPagoFacturaBean.getMtotalfactura().doubleValue() + " kFactura=" + objPagoFacturaBean.getKfactura() + " FormatoFactura="+ objPagoFacturaBean.getSformatofactura());
		} catch (Exception aObjException){
			iObjLog.error("Error CuentasxCobrarMayoreoAjax.buscarCliente:Exception....", aObjException);
			throw aObjException;
		} finally {
			objPagoFacturaDao = null;
		}
		return objPagoFacturaBean;
	}	

	/*Incidencia Cambio 25/07/2013  BY*/
	public PagoFacturaBean buscaFacturaFolio(int cFolio) throws Exception
	{
		iObjLog.debug("Entrando CuentasxCobrarMayoreoAjax.buscaFacturaFolio:Entrando... " + cFolio);								
		PagoFacturaDao objPagoFacturaDao = new PagoFacturaDao();								
		PagoFacturaBean objPagoFacturaBean = new PagoFacturaBean();								
		OrdenDatosFacturacionDao objOrdenDatosFacturacionDao = new OrdenDatosFacturacionDao();								
		FacturaElectronicaBean objFacturaElectronicaBean = new FacturaElectronicaBean();								
		TFactura objTFactura = null;								
		try {								
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");							
			objFacturaElectronicaBean.setSfolio(cFolio + "");							
			objTFactura = objOrdenDatosFacturacionDao.buscarFacturaHB(objFacturaElectronicaBean,1);							
				if (objTFactura != null) {						
					if(objTFactura.getCestadoregistro()==34) {					
						objPagoFacturaBean = objPagoFacturaDao.getDatosFacturaCancelada(objTFactura.getKfactura().intValue(),true);				
					}else {					
						objPagoFacturaBean = objPagoFacturaDao.getDatosPagoFactura(objTFactura.getKfactura().intValue(),true);				
					}					
				} else {						
					objPagoFacturaBean.setKfactura(0);					
					objPagoFacturaBean.setSmensaje("NO EXISTE EL FOLIO INDICADO");					
				}						
			iObjLog.debug("Saliendo CuentasxCobrarMayoreoAjax.getDatosPagoFactura:Saliendo... mTotalFactura=" + objPagoFacturaBean.getMtotalfactura().doubleValue() + " kFactura=" + objPagoFacturaBean.getKfactura() + " FormatoFactura="+ objPagoFacturaBean.getSformatofactura());							
		} catch (Exception aObjException){								
			iObjLog.error("Error CuentasxCobrarMayoreoAjax.buscaFacturaFolio:Exception....", aObjException);							
			throw aObjException;							
		} finally {								
			objTFactura = null;							
			objPagoFacturaDao = null;							
			objOrdenDatosFacturacionDao = null;							
			objFacturaElectronicaBean = null;							
			objTFactura = null;							
		}								
		return objPagoFacturaBean;								

	}	
	
	public ConvenioBean getFacturasConvenio(ConvenioBean objConvenioBean) throws Exception {
		FacturacionMayoreoDao objFacturacionMayoreoDao = new FacturacionMayoreoDao();
		try {
			objConvenioBean = objFacturacionMayoreoDao.getFacturasConvenio(objConvenioBean);									
			objFacturacionMayoreoDao = null;
		} catch (Exception aObjException) {
    	    iObjLog.error("CuentasxCobrarMayoreoAjax.getFacturasConvenio:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objFacturacionMayoreoDao = null;			
		}
		return objConvenioBean;
	}
	
	/**
     * Versión 25 de Marzo 2013 
     BY
     */
	
	public String reversarPago(int kFactura,int idUsuario) throws Exception {
		PagoFacturaDao objPagoFacturaDao = new PagoFacturaDao();
		String strReturn = null;
		try {
			strReturn=objPagoFacturaDao.reversarPago(kFactura, idUsuario);								
			
		} catch (Exception aObjException) {
    	    iObjLog.error("CuentasxCobrarMayoreoAjax.showPagoaReversar:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objPagoFacturaDao = null;			
		}
		return strReturn;
	}
	
	/**
     * Versión 25 de Marzo 2013 
     BY
     */
	
	public String actualizarFactura(int kFactura,int idUsuario,int opcion) throws Exception {
		PagoFacturaDao objPagoFacturaDao = new PagoFacturaDao();
		String strReturn = null;
		try {
			strReturn=objPagoFacturaDao.actualizarFactura(kFactura,idUsuario,opcion);								
			
		} catch (Exception aObjException) {
    	    iObjLog.error("CuentasxCobrarMayoreoAjax.actualizarEstadoFactura:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objPagoFacturaDao = null;			
		}
		return strReturn;
	}
	
	/**
     * Versión 25 de Marzo 2013 
     BY
     */
	public int getCcliente(int cconvenio){
		int iCcliente = 0;
		PagoFacturaDao objPagoFacturaDao = new PagoFacturaDao();
		try {
			iCcliente = objPagoFacturaDao.getCcliente(cconvenio);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			objPagoFacturaDao = null;			
		}
		
		return iCcliente;
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
