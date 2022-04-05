package mx.com.web2lab.ajax.dwr.facturacion.mayoreo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public String findCfdiPdf(String path) throws Exception {
		String strReturn = "";
		iObjLog.debug("Entrando a FacturarElectronicaSucursalAjax.findCfdiPdf:Entrando... ");
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
			String remplacePath = path.replaceAll("http://10.3.0.8:9085", "/mnt/gda/apache-tomcat/webapps/ROOT");
			URL url; 
			url = new URL("http://10.3.0.8:8192/facturas/ordenes/find-cfdi-server?path="+remplacePath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
			}
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
			
			iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.findCfdiPdf:Saliendo...  ");
		}catch (Exception aObjException){
    	    iObjLog.error("FacturarElectronicaSucursalAjax.crearOrdenFacturarElectronica:ErrorException....", aObjException);
    	}
    	return path;
	}
	
	public PagoFacturaBean pagoFactura(int kFactura,double dblAnticipo, double dblMontoPago,double dblSaldo,
			int cTipoPago,int UserID,String strFechaPago, int intGrupo, boolean crearComplemento,String formaPago, int marca,
			String rfcBanco, String nomBanco, String cuentaClabe, String numOperacion, boolean sustitucion, int folioSustitucion,
			String uuidSustitucion) throws Exception
	{
		iObjLog.debug("Entrando CuentasxCobrarMayoreoAjax.pagoFactura:Entrando... ");		
		PagoFacturaDao objPagoFacturaDao = new PagoFacturaDao();
		PagoFacturaBean objPagoFacturaBean = new PagoFacturaBean();
		int keyPago=0;
		String resSustitucion = "";
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
			
			
				//if(crearComplemento){
				keyPago=objPagoFacturaDao.pago(objPagoFacturaBean,dblMontoPago,formaPago ,marca, rfcBanco, nomBanco, cuentaClabe,numOperacion,
						sustitucion,uuidSustitucion);
				//}			
				objPagoFacturaBean = objPagoFacturaDao.pagoFactura(objPagoFacturaBean,keyPago);
			
			
			iObjLog.debug("Saliendo CuentasxCobrarMayoreoAjax.pagoFactura:Saliendo...  ");
		} catch (Exception aObjException){
			iObjLog.error("Error CuentasxCobrarMayoreoAjax.pagoFactura:Exception....", aObjException);
			throw aObjException;
		} finally {
			objPagoFacturaDao = null;
		}
		return objPagoFacturaBean;
	}
	
	public int getMarcaKfactura(int Kfactura) throws Exception{
		int marca=0;
		iObjLog.debug("Entrando CuentasxCobrarMayoreoAjax.getMarcaKfactura:Entrando... ");
		PagoFacturaDao objPagoFacturaDao = new PagoFacturaDao();
		try {
			marca = objPagoFacturaDao.getMarcaKfactura(Kfactura);			
			iObjLog.debug("Saliendo CuentasxCobrarMayoreoAjax.getMarcaKfactura:Saliendo... "+Kfactura);
		}catch (Exception aObjException){
			iObjLog.error("Error CuentasxCobrarMayoreoAjax.getMarcaKfactura:Exception....", aObjException);
			throw aObjException;
		}
		return marca;
	}
	
	public int getKeyPago(String fechaPago,double monto, String formaPago, int convenio, String kfacturas, 
			String rfcBanco, String nomBanco, String cuentaClabe, String numOperacion) throws Exception{
		int key=0;
		PagoFacturaDao objPagoFacturaDao = new PagoFacturaDao();
		iObjLog.debug("Entrando CuentasxCobrarMayoreoAjax.getKeyPago:Entrando... ");
		try {
			String facturas=kfacturas.substring(0, kfacturas.length()-1);
			key=objPagoFacturaDao.pagoMulti(new Formatos().getFecha(fechaPago),monto,formaPago ,convenio, facturas,rfcBanco,nomBanco,
					cuentaClabe,numOperacion);
			
			iObjLog.debug("Saliendo CuentasxCobrarMayoreoAjax.getKeyPago:Saliendo... "+key);
		}catch (Exception aObjException){
			iObjLog.error("Error CuentasxCobrarMayoreoAjax.getKeyPago:Exception....", aObjException);
			throw aObjException;
		}
		return key;
	} 
	
	public String getConsumows() throws Exception{
		String res="";
		iObjLog.debug("Entrando CuentasxCobrarMayoreoAjax.getConsumows:Entrando... ");
		try {
			
			
			iObjLog.debug("Saliendo CuentasxCobrarMayoreoAjax.getConsumows:Saliendo... ");
		}catch (Exception aObjException){
			iObjLog.error("Error CuentasxCobrarMayoreoAjax.getConsumows:Exception....", aObjException);
			throw aObjException;
		}
		return res;
	} 
	
	public boolean reglaNegocio(String fechaPago) throws Exception{
		//boolean res=false;
		iObjLog.debug("Entrando CuentasxCobrarMayoreoAjax.reglaNegocio:Entrando... ");
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			DateFormat hourFormat = new SimpleDateFormat("dd-MM-yyyy");
			String fechaActual= hourFormat.format(date);
			Date dfechaemision = formatter.parse(fechaActual);
			Date dfechaLimite = new Formatos().getFechaLimite(fechaPago);
			
			if (dfechaLimite.before(dfechaemision)) {
				iObjLog.debug("Saliendo CuentasxCobrarMayoreoAjax.reglaNegocio:Saliendo... ");
				return false;
			} else {
				iObjLog.debug("Saliendo CuentasxCobrarMayoreoAjax.reglaNegocio:Saliendo... ");
				return true;
			}
		}catch (Exception aObjException){
			iObjLog.error("Error CuentasxCobrarMayoreoAjax.reglaNegocio:Exception....", aObjException);
			throw aObjException;
		}
	} 
	
	public boolean pagosFacturasArray(ArrayList pagos){
		boolean resp=false;
		iObjLog.debug("Entrando CuentasxCobrarMayoreoAjax.pagosFacturasArray:Entrando... "+pagos.size()+"     "+ pagos);
		PagoFacturaBean objPagoFacturaBean = new PagoFacturaBean();
		int contExito=0;
		try {
			String cadenaKfacturas="";
		if(pagos.size()>0){
//			for(int i=0;i<pagos.size();i++){
//				String[] pago=pagos.get(i).toString().split(",");
//				if((pagos.size()-1)==i){
//					cadenaKfacturas+=pago[0];
//				}else{
//					cadenaKfacturas+=pago[0]+",";
//				}
//			}
			for(int i=0;i<pagos.size();i++){
				iObjLog.debug("Entrando CuentasxCobrarMayoreoAjax.pagosFacturasArray:Array   "+pagos.get(i).toString());
				String[] pago=pagos.get(i).toString().split(",");
				objPagoFacturaBean = this.pagoFacturaMulti(Integer.parseInt(pago[0]), Double.parseDouble(pago[1]), 
						Double.parseDouble(pago[2]), Double.parseDouble(pago[3]), Integer.parseInt(pago[4]), Integer.parseInt(pago[5]),
						pago[6], Integer.parseInt(pago[7]), Integer.parseInt(pago[8]));
				if(objPagoFacturaBean.getSmensaje().equals("Exito en el registro del Pago")){
					contExito++;
				}
			}
			if(contExito==pagos.size()){
				resp=true;
			}else{
				resp=false;
			}
		}else{
			resp=false;
		}
		iObjLog.debug("Saliendo CuentasxCobrarMayoreoAjax.pagosFacturasArray:Saliendo...  ");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}
	
	public PagoFacturaBean pagoFacturaMulti(int kFactura,double dblAnticipo, double dblMontoPago,double dblSaldo,int cTipoPago,
			int UserID,String strFechaPago, int intGrupo, int keyPago) throws Exception
	{
		iObjLog.debug("Entrando CuentasxCobrarMayoreoAjax.pagoFacturaMulti:Entrando... "+ keyPago);		
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
			objPagoFacturaBean = objPagoFacturaDao.pagoFactura(objPagoFacturaBean,keyPago);
			iObjLog.debug("Saliendo CuentasxCobrarMayoreoAjax.pagoFacturaMulti:Saliendo...  ");
		} catch (Exception aObjException){
			iObjLog.error("Error CuentasxCobrarMayoreoAjax.pagoFacturaMulti:Exception....", aObjException);
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
			objPagoFacturaBean = objPagoFacturaDao.getDatosPagoFactura(kFactura,true,true);
			iObjLog.debug("Saliendo CuentasxCobrarMayoreoAjax.getDatosPagoFactura:Saliendo... mTotalFactura=" + objPagoFacturaBean.getMtotalfactura().doubleValue() + " kFactura=" + objPagoFacturaBean.getKfactura() + " FormatoFactura="+ objPagoFacturaBean.getSformatofactura());
		} catch (Exception aObjException){
			iObjLog.error("Error CuentasxCobrarMayoreoAjax.getPagoFactura:Exception....", aObjException);
			throw aObjException;
		} finally {
			objPagoFacturaDao = null;
		}
		return objPagoFacturaBean;
	}	

	/*Incidencia Cambio 25/07/2013  BY*/
	public PagoFacturaBean buscaFacturaFolio(int cFolio, int marca, boolean pagos) throws Exception
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
			if((marca==7) || (marca==8)){
				if(marca==7){
					objTFactura = objOrdenDatosFacturacionDao.buscarFacturaHB(objFacturaElectronicaBean,7,"AJP");					
				}else if (marca==8){
					objTFactura = objOrdenDatosFacturacionDao.buscarFacturaHB(objFacturaElectronicaBean,7,"AJL");
				}
			}else{
				objTFactura = objOrdenDatosFacturacionDao.buscarFacturaHB(objFacturaElectronicaBean,marca,"");
			}
			
				if (objTFactura != null) {						
					if(objTFactura.getCestadoregistro()==34) {					
						objPagoFacturaBean = objPagoFacturaDao.getDatosFacturaCancelada(objTFactura.getKfactura().intValue(),true);				
					}else {					
						objPagoFacturaBean = objPagoFacturaDao.getDatosPagoFactura(objTFactura.getKfactura().intValue(),true,pagos);				
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
