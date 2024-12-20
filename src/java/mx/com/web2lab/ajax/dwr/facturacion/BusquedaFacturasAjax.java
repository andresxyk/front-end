package mx.com.web2lab.ajax.dwr.facturacion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.cotizaciones.CotizacionBean;
import mx.com.web2lab.backend.beans.facturacion.DatosAdicionalesBean;
import mx.com.web2lab.backend.dao.facturacion.empresas.viaje.ViajeFacturacionDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.BusquedaFacturaDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.DatosAdicionalesDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionPrevioDao;
import mx.com.web2lab.backend.hbm.ConfiguracionProperties;
import mx.com.web2lab.backend.hbm.HibernateUtil;
import mx.com.web2lab.backend.hbm.om.ap.TOrdenSucursalFac;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BusquedaFacturasAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(BusquedaFacturasAjax.class);
	
	private String hostServerApache = null;
	private String hostServerWebApp = null;
	
	public BusquedaFacturasAjax(){
		iObjLog.debug("new: Generando nueva clase BusquedaFacturasAjax");
		hostServerApache = ConfiguracionProperties.getPropiedad("host.server.apache");
		hostServerWebApp = ConfiguracionProperties.getPropiedad("host.server.webapp");
	}	
	
	public String findCfdiPdf(String path) throws Exception {
		String strReturn = "";
		iObjLog.debug("Entrando a FacturarElectronicaSucursalAjax.findCfdiPdf:Entrando... ");
		try {
			String remplacePath = path.replaceAll("http://"+hostServerApache, "/mnt/gda/apache-tomcat/webapps/ROOT");
			URL url; 
			url = new URL("http://"+hostServerWebApp+"/facturas/ordenes/find-cfdi-server?path="+remplacePath);
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
		
public String buscarFacturas(String strFacturas){
	String strReturn = null;
	
	BusquedaFacturaDao objBusquedaFactura = new BusquedaFacturaDao();
	try {
			iObjLog.debug("Entrando a BusquedaFacturasAjax.buscarFactura");
			strReturn=objBusquedaFactura.getBusquedaFactura(strFacturas);
			iObjLog.debug("Saliendo de BusquedaFacturasAjax.buscarFactura"+strReturn);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return strReturn;
}

public String buscarComplemento(String strComplementos){
	String strReturn = null;
	
	BusquedaFacturaDao objBusquedaFactura = new BusquedaFacturaDao();
	try {
			iObjLog.debug("Entrando a BusquedaFacturasAjax.buscarComplemento");
			strReturn=objBusquedaFactura.getBusquedaComplemento(strComplementos);
			iObjLog.debug("Saliendo de BusquedaFacturasAjax.buscarComplemento"+strReturn);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return strReturn;
}

public String buscarFacturaAjuste(String strFacturas, Integer idMarca){
	String strReturn = null;
	
	BusquedaFacturaDao objBusquedaFactura = new BusquedaFacturaDao();
	try {
			iObjLog.debug("Entrando de BusquedaFacturasAjax.buscarFacturaAjuste("+strFacturas+"," + idMarca+")");
			strReturn = objBusquedaFactura.getBusquedaFacturaAjuste(strFacturas, idMarca);
			iObjLog.debug("Saliendo de BusquedaGacturasAjax.buscarFacturaAjuste("+strReturn+"," + idMarca+")");
	} catch (Exception e) {
		// TODO Auto-generated catch block 
		e.printStackTrace();
	}
	return strReturn;
}

public String buscarFacturaSustitucion(String factura, int idMarca, int tipofactura, int cconvenio){
	String strReturn=null;
	BusquedaFacturaDao objBusquedaFactura = new BusquedaFacturaDao();
	try {
		iObjLog.debug("Entrando de BusquedaFacturasAjax.buscarFacturaSustitucion("+factura+"," + idMarca+")");
		strReturn = objBusquedaFactura.getBusquedaFacturasSustitucion(factura, idMarca,tipofactura,cconvenio);
		iObjLog.debug("Saliendo de BusquedaGacturasAjax.buscarFacturaSustitucion("+strReturn+"," + idMarca+")");		
	} catch (Exception e) {
		e.printStackTrace(); 
	}	
	return strReturn;	
}

public int buscarMarca(int cconvenio){
	int marca=0;
	BusquedaFacturaDao objBusquedaFactura = new BusquedaFacturaDao();
	try {
			iObjLog.debug("Entrando de BusquedaFacturasAjax.buscarMarca("+cconvenio+")");
			marca = objBusquedaFactura.getMarca(cconvenio);
			iObjLog.debug("Saliendo de BusquedaGacturasAjax.buscarMarca("+cconvenio+")");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return marca;
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
