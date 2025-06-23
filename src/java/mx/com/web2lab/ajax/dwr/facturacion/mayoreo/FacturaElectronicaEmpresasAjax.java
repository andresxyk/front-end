package mx.com.web2lab.ajax.dwr.facturacion.mayoreo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;

import mx.com.web2lab.ajax.dwr.facturacion.formatos.FormatoFacturaEmpresa;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaEmpresaDesgloceImpl;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaEmpresaGlobalImpl;
import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionElectronicaMayoreoDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionPrevioDao;
import mx.com.web2lab.backend.hbm.ConfiguracionProperties;
//import mx.com.web2lab.backend.facturacion33.mb.FacturacionV33;
import mx.com.web2lab.backend.hbm.om.ap.TFactura;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;
import mx.com.web2lab.domain.facturacion.FacturacionElectronicaDomain;

public class FacturaElectronicaEmpresasAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(FacturaElectronicaEmpresasAjax.class);

	private String hostServiceOrchestratorFacturacion = null;
	
	public FacturaElectronicaEmpresasAjax() {
		iObjLog.debug("new: Generando nueva clase FacturarElectronicaMayoreoAjax");
		hostServiceOrchestratorFacturacion = ConfiguracionProperties.getPropiedad("host.service.orchestrator.facturacion");
	}

	public String generarFacturacionPrevio(String cConvenio, String uUserId, String strBloque, String nTipoPrevio,
			String nTipoFacturacion, String monto, String razon) throws Exception {
		iObjLog.debug("Entrando a FacturaElectronicaEmpresasAjax.generarFacturacionPrevio:Entrando... cConvenio:"
				+ cConvenio + ", uUserId:" + uUserId + " strBloque:" + strBloque + "  nTipoPrevio:" + nTipoPrevio
				+ "   nTipoFacturacion:" + nTipoFacturacion + "    monto:" + monto + "      razon:" + razon);
		String strReturn = "";
		try {
			String urlParam = hostServiceOrchestratorFacturacion+"/gda/service-orchestrator/facturacion-previo?cConvenio="+cConvenio+"&strBloque="+strBloque+"&nTipoPrevio="+nTipoPrevio+"&uUserId="+uUserId+"&monto="+monto+"&nTipoFacturacion="+nTipoFacturacion+"&razon="+razon+"&typeResponse=2";
			String decodeURL = URLDecoder.decode(urlParam, "UTF-8");
			URL url;
			url = new URL(decodeURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();		
			conn.setRequestMethod("GET");
			
	        int responseCode = conn.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				conn.disconnect();
				System.out.println(response.toString());
				return response.toString();
			} else {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getErrorStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				conn.disconnect();
				iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Saliendo...  ");
				throw new Exception(response.toString());
			}
	        
			
		} catch (Exception aObjException) {
			aObjException = aObjException;
			iObjLog.error("FacturarElectronicaSucursalAjax.crearOrdenFacturar:ErrorException....", aObjException);
			throw aObjException;
		} finally {
		}
	}
	
	public String generarFacturacionDefinitivo(String cConvenio, String uUserId, String strBloque, String nTipoPrevio,
			String nTipoFacturacion, String monto, String razon, 
			int tipofactura, String smetodopago, String nocuenta, String uuidSustitucion,
			boolean bSustitucion, boolean bDescuento, String descuentos, String notaDescuentos,
			boolean bRetencion, String descripcionfactura) throws Exception {
		iObjLog.debug("Entrando a FacturaElectronicaEmpresasAjax.generarFacturacionDefinitivo:Entrando... cConvenio:"
				+ cConvenio + ", uUserId:" + uUserId + " strBloque:" + strBloque + "  nTipoPrevio:" + nTipoPrevio
				+ "   nTipoFacturacion:" + nTipoFacturacion + "    monto:" + monto + "      razon:" + razon);
		String strReturn = "";
		try {
			if(nocuenta!=null && nocuenta.length()>0){
				nocuenta = URLEncoder.encode(nocuenta, "UTF-8");
			}else{
				nocuenta = "";
			}
			if(descuentos!=null && descuentos.length()>0){
				descuentos = URLEncoder.encode(descuentos, "UTF-8");
			}else{
				descuentos = "";
			}
			if(notaDescuentos!=null && notaDescuentos.length()>0){
				notaDescuentos = URLEncoder.encode(notaDescuentos, "UTF-8");
			}else{
				notaDescuentos = "";
			}
			if(descripcionfactura!=null && descripcionfactura.length()>0){
				descripcionfactura = URLEncoder.encode(descripcionfactura, "UTF-8");
			}else{
				descripcionfactura = "";
			}
						
			String urlParam = hostServiceOrchestratorFacturacion+"/gda/service-orchestrator/facturacion-definitivo?cConvenio="+cConvenio+
					"&strBloque="+strBloque+"&nTipoPrevio="+nTipoPrevio+"&uUserId="+uUserId+"&monto="+monto+
					"&nTipoFacturacion="+nTipoFacturacion+"&razon="+razon+"&typeResponse=2"+"&tipofactura="+tipofactura+
					"&smetodopago="+smetodopago.trim()+"&nocuenta="+nocuenta+"&uuidSustitucion="+uuidSustitucion+"&bSustitucion="+bSustitucion+
					"&bDescuento="+bDescuento+"&descuentos="+descuentos+"&notaDescuentos="+notaDescuentos+"&bRetencion="+bRetencion+
					"&descripcionfactura="+descripcionfactura;
			
//			String decodeURL = URLEncoder.encode(urlParam, "UTF-8");
			URL url;
			url = new URL(urlParam);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();		
			conn.setRequestMethod("GET");
			
	        int responseCode = conn.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				conn.disconnect();
				System.out.println(response.toString());
				return response.toString();
			} else {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getErrorStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				conn.disconnect();
				iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.generarFacturacionDefinitivo:Saliendo...  ");
				throw new Exception(response.toString());
			}
	        
			
		} catch (Exception aObjException) {
			aObjException = aObjException;
			iObjLog.error("FacturarElectronicaSucursalAjax.generarFacturacionDefinitivo:ErrorException....", aObjException);
			throw aObjException;
		} finally {
		}
	}
	

	public void eliminaPreciosActual(int cconvenio, String tabla) throws Exception {
		iObjLog.debug(
				"Entrando a FacturaElectronicaEmpresasAjax.eliminaPreciosActual:Entrando... cConvenio" + cconvenio);
		FacturacionPrevioDao facturacionPrevioDao = new FacturacionPrevioDao();
		facturacionPrevioDao.eliminarListaPreciosActualSistema(cconvenio, tabla);
	}

	public String obtenerRfc(String rfc) throws Exception {
		iObjLog.debug("Entrando a FacturaElectronicaEmpresasAjax.obtenerRfc:Entrando... rfc" + rfc);
		FacturacionPrevioDao facturacionPrevioDao = new FacturacionPrevioDao();
		String strReturn = facturacionPrevioDao.obtenerRfc(rfc);
		return strReturn;
	}

	public void pruebaLog(String cadena) throws Exception {
		iObjLog.debug("Entrando a FacturaElectronicaEmpresasAjax.obtenerRfc:Entrando... cadena" + cadena);
		// FacturacionV33 facturacionV33= new FacturacionV33();
		// facturacionV33.procesaCadena(cadena);
	}

	public String crearFacturaEmpresa1(FacturaElectronicaBean objFacturaBean, String ufoliofactura, int itipofactura)
			throws Exception {
		TFactura objTFactura = new TFactura();
		FacturacionElectronicaMayoreoDao objFacturaElectronicaMayoreoDAO = new FacturacionElectronicaMayoreoDao();
		String strReturn = "";
		FormatoFacturaEmpresa objFormato = null;
		iObjLog.debug("Entrando a FacturaElectronicaEmpresasAjax.crearFacturaEmpresa:Entrando... " + ufoliofactura
				+ "Tipo Factura" + itipofactura);
		try {
			objTFactura = objFacturaElectronicaMayoreoDAO.buscarFacturaFolio(ufoliofactura, objFacturaBean.getCmarca(),
					objFacturaBean.getSserie());
			if (objTFactura != null) {
				if (!isSesionValida())
					throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
				objFacturaBean.setBolredondear(true);
				switch (itipofactura) {
				case 1:
					objFormato = new FormatoFacturaEmpresaGlobalImpl();
					break;
				case 2:
					objFormato = new FormatoFacturaEmpresaDesgloceImpl();
				}

				objFacturaBean.setKfactura(objTFactura.getKfactura().intValue());
				objFacturaBean.setCconvenio(objTFactura.getCconvenio());
				objFacturaBean = objFormato.crearFacturaFormatoEmpresa(objFacturaBean, objFacturaBean.getCmarca(),
						objFacturaBean.getSserie());
				FacturacionElectronicaDomain objFEDomain = new FacturacionElectronicaDomain();
				objFEDomain.crearFacturaElectronicaEmpresa(objFacturaBean);
				strReturn = objFacturaBean.getsURL();
			} else {
				strReturn = "NO EXISTE";
			}
			iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Saliendo...  ");
		} catch (TorqueException aObjException) {
			aObjException = aObjException;

			iObjLog.error("FacturarElectronicaSucursalAjax.crearOrdenFacturar:ErrorException....", aObjException);
			throw aObjException;
		} finally {
		}
		return strReturn;
	}

	public String crearFacturaEmpresa(FacturaElectronicaBean objFacturaBean, String ufoliofactura, int itipofactura,
			double msubtotal, double miva, double mtotal, String strnocuenta, String strmetodopago,
			String uuidSustitucion, boolean sustitucion, boolean descuento, String descuentos, String notaDescuento,
			boolean retencion) throws Exception {

		TFactura objTFactura = new TFactura();
		FacturacionElectronicaMayoreoDao objFacturaElectronicaMayoreoDAO = new FacturacionElectronicaMayoreoDao();
		String strReturn = "";
		FormatoFacturaEmpresa objFormato = null;
		boolean bandSustitucion = true;

		iObjLog.debug("Entrando a FacturaElectronicaEmpresasAjax.crearFacturaEmpresa:Entrando... " + ufoliofactura
				+ "Tipo Factura" + itipofactura + " cmarca:" + objFacturaBean.getCmarca() + "  serie:"
				+ objFacturaBean.getSserie() + "   descuento:" + descuento + "    descuentos:" + descuentos
				+ "      retencion:" + retencion);
		try {
			objTFactura = objFacturaElectronicaMayoreoDAO.buscarFacturaFolio(ufoliofactura, objFacturaBean.getCmarca(),
					objFacturaBean.getSserie());
			objFacturaElectronicaMayoreoDAO.persistirAjusteFactura(objTFactura.getKfactura(), objTFactura.getUserId(),
					msubtotal, miva, mtotal);
			objFacturaElectronicaMayoreoDAO.persistirMetodoPago(strnocuenta.trim(), strmetodopago.trim(),
					objTFactura.getCconvenio());
			if (!isSesionValida())
				throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
			objFacturaBean.setBolredondear(true);
			switch (itipofactura) {
			case 1:
				objFormato = new FormatoFacturaEmpresaGlobalImpl();
				break;
			case 2:
				objFormato = new FormatoFacturaEmpresaDesgloceImpl();
				break;
			case 3:
				objFormato = new FormatoFacturaEmpresaGlobalImpl();
				break;
			case 4:
				objFormato = new FormatoFacturaEmpresaDesgloceImpl();
			}
			// FacturaSustitucionBean facturaSustitucionBean = new
			// FacturaSustitucionBean();

			objFacturaBean.setBandRetencion(retencion);
			if (sustitucion) {
				objFacturaBean.setUuid(uuidSustitucion.trim());
			}
			if (descuento) {
				objFacturaBean.setBandDescuento(descuento);
				objFacturaBean.setNotaDescuento(notaDescuento);
				objFacturaBean.setDescuentos(descuentos);
				String[] splitdesc = descuentos.split("\\|");
				for (int i = 0; i < splitdesc.length; i++) {
					System.out.println(splitdesc[i]);
				}

			}

			if (bandSustitucion) {

				if ((itipofactura == 1) || (itipofactura == 2)) {
					objFacturaBean.setKfactura(objTFactura.getKfactura().intValue());
					objFacturaBean.setCconvenio(objTFactura.getCconvenio());
					objFacturaBean = objFormato.crearFacturaFormatoEmpresa(objFacturaBean, objFacturaBean.getCmarca(),
							objFacturaBean.getSserie());
					FacturacionElectronicaDomain objFEDomain = new FacturacionElectronicaDomain();
					objFacturaBean = objFEDomain.crearFacturaElectronicaEmpresa(objFacturaBean);
					strReturn = objFacturaBean.getsURL();
					iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Saliendo...1 o 2...."
							+ objFacturaBean.getsURL());
				}
				if (itipofactura == 3) {
					objFacturaBean.setKfactura(objTFactura.getKfactura().intValue());
					objFacturaBean.setCconvenio(objTFactura.getCconvenio());
					objFacturaBean = objFormato.crearFacturaFormatoEmpresa(objFacturaBean, objFacturaBean.getCmarca(),
							objFacturaBean.getSserie());
					FacturacionElectronicaDomain objFEDomain = new FacturacionElectronicaDomain();
					objFacturaBean = objFEDomain.crearFacturaEmpresaUnidad(objFacturaBean);
					strReturn = objFacturaBean.getsURL();
					iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Saliendo...3...."
							+ objFacturaBean.getsURL());
				}
				if (itipofactura == 4) {
					objFacturaBean.setKfactura(objTFactura.getKfactura().intValue());
					objFacturaBean.setCconvenio(objTFactura.getCconvenio());
					objFacturaBean = objFormato.crearFacturaFormatoEmpresa(objFacturaBean, objFacturaBean.getCmarca(),
							objFacturaBean.getSserie());
					FacturacionElectronicaDomain objFEDomain = new FacturacionElectronicaDomain();
					objFacturaBean = objFEDomain.crearFacturaXmlAdenda(objFacturaBean);
					strReturn = objFacturaBean.getsURL();
					iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Saliendo...4...."
							+ objFacturaBean.getsURL());
				}
			}
			iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Saliendo...  ");
		} catch (TorqueException aObjException) {
			aObjException = aObjException;

			iObjLog.error("FacturarElectronicaSucursalAjax.crearOrdenFacturar:ErrorException....", aObjException);
			throw aObjException;
		} finally {
		}
		return strReturn;
	}

	public static String llenaIdFactura(String strNemonico, String intFactura, int MaxLength) {
		String strReturn = "";
		int intTotal = strNemonico.length() + intFactura.length();
		for (int i = intTotal; i <= MaxLength; i++) {
			strReturn = strReturn + "0";
		}
		return strNemonico + strReturn + intFactura;
	}

	public boolean isSesionValida() {
		boolean valida = false;
		try {
			valida = super.isSesionValida(true);
		} catch (Exception e) {
			iObjLog.error("isSesionValida:No existe una sesion valida para el usuario");
			return valida;
		}
		return valida;
	}
}