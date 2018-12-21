package mx.com.web2lab.ajax.dwr.facturacion.mayoreo;

import mx.com.web2lab.ajax.dwr.facturacion.formatos.FormatoFacturaEmpresa;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaEmpresaDesgloceImpl;
import mx.com.web2lab.ajax.dwr.facturacion.formatos.impl.FormatoFacturaEmpresaGlobalImpl;
import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaSustitucionBean;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionElectronicaMayoreoDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionPrevioDao;
//import mx.com.web2lab.backend.facturacion33.mb.FacturacionV33;
import mx.com.web2lab.backend.hbm.om.ap.TFactura;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;
import mx.com.web2lab.domain.facturacion.FacturacionElectronicaDomain;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;

public class FacturaElectronicaEmpresasAjax extends AjaxAction
{
  private static Log iObjLog = LogFactory.getLog(FacturaElectronicaEmpresasAjax.class);

  public FacturaElectronicaEmpresasAjax() {
    iObjLog.debug("new: Generando nueva clase FacturarElectronicaMayoreoAjax");
  }

  public void eliminaPreciosActual(int cconvenio, String tabla) throws Exception{
	  iObjLog.debug("Entrando a FacturaElectronicaEmpresasAjax.eliminaPreciosActual:Entrando... cConvenio" + cconvenio);
	  FacturacionPrevioDao facturacionPrevioDao= new FacturacionPrevioDao();
	  facturacionPrevioDao.eliminarListaPreciosActualSistema(cconvenio, tabla);
  }
  
  public String obtenerRfc(String rfc) throws Exception{
	  iObjLog.debug("Entrando a FacturaElectronicaEmpresasAjax.obtenerRfc:Entrando... rfc" + rfc);
	  FacturacionPrevioDao facturacionPrevioDao= new FacturacionPrevioDao();
	  String strReturn = facturacionPrevioDao.obtenerRfc(rfc);
	  return strReturn;
  }
  
  public void pruebaLog(String cadena) throws Exception{
	  iObjLog.debug("Entrando a FacturaElectronicaEmpresasAjax.obtenerRfc:Entrando... cadena" + cadena);
	//  FacturacionV33 facturacionV33= new FacturacionV33();
	 // facturacionV33.procesaCadena(cadena);
  }
    
  
  public String crearFacturaEmpresa1(FacturaElectronicaBean objFacturaBean, String ufoliofactura, int itipofactura)
    throws Exception
  {
    TFactura objTFactura = new TFactura();
    FacturacionElectronicaMayoreoDao objFacturaElectronicaMayoreoDAO = new FacturacionElectronicaMayoreoDao();
    String strReturn = "";
    FormatoFacturaEmpresa objFormato = null;
    iObjLog.debug("Entrando a FacturaElectronicaEmpresasAjax.crearFacturaEmpresa:Entrando... " + ufoliofactura + "Tipo Factura" + itipofactura);
    try {
      objTFactura = objFacturaElectronicaMayoreoDAO.buscarFacturaFolio(ufoliofactura,objFacturaBean.getCmarca(),objFacturaBean.getSserie());
      if (objTFactura != null) {
        if (!isSesionValida()) throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
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
        objFacturaBean = objFormato.crearFacturaFormatoEmpresa(objFacturaBean,objFacturaBean.getCmarca(),objFacturaBean.getSserie());
        FacturacionElectronicaDomain objFEDomain = new FacturacionElectronicaDomain();
        objFEDomain.crearFacturaElectronicaEmpresa(objFacturaBean);
        strReturn = objFacturaBean.getsURL();
      } else {
        strReturn = "NO EXISTE";
      }
      iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Saliendo...  "); } catch (TorqueException aObjException) {
      aObjException = 
        aObjException;

      iObjLog.error("FacturarElectronicaSucursalAjax.crearOrdenFacturar:ErrorException....", aObjException);
      throw aObjException;
    }
    finally {
    }
    return strReturn;
  }

  public String crearFacturaEmpresa(FacturaElectronicaBean objFacturaBean, String ufoliofactura, int itipofactura, double msubtotal, double miva, double mtotal, 
		  String strnocuenta, String strmetodopago, String uuidSustitucion, boolean sustitucion) throws Exception {
	  
    TFactura objTFactura = new TFactura();
    FacturacionElectronicaMayoreoDao objFacturaElectronicaMayoreoDAO = new FacturacionElectronicaMayoreoDao();
    String strReturn = "";
    FormatoFacturaEmpresa objFormato = null;
    boolean bandSustitucion = true;
    
    iObjLog.debug("Entrando a FacturaElectronicaEmpresasAjax.crearFacturaEmpresa:Entrando... " + ufoliofactura + "Tipo Factura" + itipofactura + " cmarca:" + objFacturaBean.getCmarca()+"  serie:"+objFacturaBean.getSserie());
    try {
      objTFactura = objFacturaElectronicaMayoreoDAO.buscarFacturaFolio(ufoliofactura,objFacturaBean.getCmarca(),objFacturaBean.getSserie());
      objFacturaElectronicaMayoreoDAO.persistirAjusteFactura(objTFactura.getKfactura(), objTFactura.getUserId(), msubtotal, miva, mtotal);
      objFacturaElectronicaMayoreoDAO.persistirMetodoPago(strnocuenta.trim(), strmetodopago.trim(), objTFactura.getCconvenio());
      if (!isSesionValida()) throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
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
      
      //FacturaSustitucionBean facturaSustitucionBean = new FacturaSustitucionBean();
      if(sustitucion){    	  
    	  objFacturaBean.setUuid(uuidSustitucion.trim());    	  
      }
      if(bandSustitucion){    	
	      
	      if ((itipofactura == 1) || (itipofactura == 2)) {
	        objFacturaBean.setKfactura(objTFactura.getKfactura().intValue());
	        objFacturaBean.setCconvenio(objTFactura.getCconvenio());
	        objFacturaBean = objFormato.crearFacturaFormatoEmpresa(objFacturaBean,objFacturaBean.getCmarca(),objFacturaBean.getSserie());
	        FacturacionElectronicaDomain objFEDomain = new FacturacionElectronicaDomain();
	        objFacturaBean = objFEDomain.crearFacturaElectronicaEmpresa(objFacturaBean);
	        strReturn = objFacturaBean.getsURL();
	        iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Saliendo...1 o 2...." + objFacturaBean.getsURL());
	      }if (itipofactura == 3) {
	        objFacturaBean.setKfactura(objTFactura.getKfactura().intValue());
	        objFacturaBean.setCconvenio(objTFactura.getCconvenio());
	        objFacturaBean = objFormato.crearFacturaFormatoEmpresa(objFacturaBean,objFacturaBean.getCmarca(),objFacturaBean.getSserie());
	        FacturacionElectronicaDomain objFEDomain = new FacturacionElectronicaDomain();
	        objFacturaBean = objFEDomain.crearFacturaEmpresaUnidad(objFacturaBean);
	        strReturn = objFacturaBean.getsURL();
	        iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Saliendo...3...." + objFacturaBean.getsURL());
	      }if (itipofactura == 4) {
	        objFacturaBean.setKfactura(objTFactura.getKfactura().intValue());
	        objFacturaBean.setCconvenio(objTFactura.getCconvenio());
	        objFacturaBean = objFormato.crearFacturaFormatoEmpresa(objFacturaBean,objFacturaBean.getCmarca(),objFacturaBean.getSserie());
	        FacturacionElectronicaDomain objFEDomain = new FacturacionElectronicaDomain();
	        objFacturaBean = objFEDomain.crearFacturaXmlAdenda(objFacturaBean);
	        strReturn = objFacturaBean.getsURL();
	        iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Saliendo...4...." + objFacturaBean.getsURL());
	      }
      }
      iObjLog.debug("Saliendo a FacturarElectronicaSucursalAjax.crearOrdenFacturar:Saliendo...  "); 
      } catch (TorqueException aObjException) {
      aObjException = 
        aObjException;

      iObjLog.error("FacturarElectronicaSucursalAjax.crearOrdenFacturar:ErrorException....", aObjException);
      throw aObjException;
    }
    finally {
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

  public boolean isSesionValida()
  {
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