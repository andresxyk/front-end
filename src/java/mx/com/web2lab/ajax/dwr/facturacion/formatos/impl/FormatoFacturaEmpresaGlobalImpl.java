package mx.com.web2lab.ajax.dwr.facturacion.formatos.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;


import mx.com.web2lab.ajax.dwr.facturacion.formatos.FormatoFacturaEmpresa;
import mx.com.web2lab.backend.beans.ap.OrdenBean;
import mx.com.web2lab.backend.beans.ap.OrdenExamenBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.BodyFacturaElectronicaBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
import mx.com.web2lab.backend.beans.tools.SucursalBean;
import mx.com.web2lab.backend.dao.ap.DatosOrdenDao;
import mx.com.web2lab.backend.dao.ap.ExamenesDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionElectronicaMayoreoDao;
import mx.com.web2lab.backend.dao.tools.SucursalDao;
import mx.com.web2lab.backend.hbm.om.ap.CCodigoPostal;
import mx.com.web2lab.backend.hbm.om.ap.CControlFolio;
import mx.com.web2lab.backend.hbm.om.ap.CEntidadLegal;
import mx.com.web2lab.backend.hbm.om.ap.TDatoFiscal;
import mx.com.web2lab.backend.hbm.om.ap.TFactura;
import mx.com.web2lab.backend.util.Formatos;

public class FormatoFacturaEmpresaGlobalImpl implements FormatoFacturaEmpresa {

	private static Log iObjLog = LogFactory.getLog(FormatoFacturaEmpresaGlobalImpl.class);

	public FacturaElectronicaBean crearFacturaFormatoEmpresa(FacturaElectronicaBean objFacturaBean, int marca) throws Exception {
		DatosOrdenDao objBuscarOrdenDAO = new DatosOrdenDao();
		SucursalDao objSucursalDAO = new SucursalDao();
		SucursalDao objSucursalAdionalesDAO = new SucursalDao();
		Formatos objFormatos = new Formatos();
		BodyFacturaElectronicaBean objBody = null;
		OrdenExamenBean objOrdenExamenBean = new OrdenExamenBean();
		TFactura objTfactura= new TFactura();
		TDatoFiscal objTDatoFiscal = new TDatoFiscal();
		CEntidadLegal objCentidadLegal = new CEntidadLegal();
		CCodigoPostal objCcodigoPostalReceptor= new CCodigoPostal();
		CCodigoPostal objCcodigoPostalEmisor= new CCodigoPostal();
		CControlFolio objCcontrolFolio = new CControlFolio();
		try {					
			iObjLog.debug("Entrando a FormatoFacturaEmpresaGlobalImpl.crearFacturaFormatoEmpresa:Entrando...  ");
			/** Busca los datos de la Orden **/
			//OrdenBean objOrdenBean = objBuscarOrdenDAO.buscarOrdenExamenFac(Integer.parseInt(objFacturaBean.getkOrdenSucursal()));
			/** Busca los datos de la Sucursal Folios, serie **/
			SucursalBean objSucursalTempBean = null;
			if (marca == 1){
				objSucursalTempBean = objSucursalDAO.getSucursal(1003);
			}else if (marca==4){
				objSucursalTempBean = objSucursalDAO.getSucursal(1012);
			}else if (marca==5){
				objSucursalTempBean = objSucursalDAO.getSucursal(1013);
			}
			FacturacionElectronicaMayoreoDao objFacturacionElectronicaMayoreo = new FacturacionElectronicaMayoreoDao();
			//objFacturacionElectronicaMayoreo.getDatosFacturarSucursal(objSucursalTempBean);					
			objFacturaBean.setObjSucursalBean(objSucursalTempBean);
			/** Busca la factura **/
			objTfactura=objFacturacionElectronicaMayoreo.buscarFactura(objFacturaBean.getKfactura());
			objFacturaBean.setSserie(objTfactura.getSserie());
			objFacturaBean.setSfolio(Integer.toString(objTfactura.getUfoliofactura()));
			/** Busca los datos del receptor **/
			objTDatoFiscal=objFacturacionElectronicaMayoreo.buscaDatoFiscal(objTfactura.getKdatofiscal());
			objCcodigoPostalReceptor =objFacturacionElectronicaMayoreo.buscaCodigoPostal(objTDatoFiscal.getCcodigopostal());
			objFacturaBean.sethDatosFiscal(objTfactura.getKdatofiscal());
			objFacturaBean.setSrazonsocialreceptor(objTDatoFiscal.getSrazonsocial());
			objFacturaBean.setSrfcreceptor(objTDatoFiscal.getSrfc());
			objFacturaBean.setScallereceptor(objTDatoFiscal.getSdireccion());
			objFacturaBean.setSnexteriorreceptor("");
			objFacturaBean.setSninteriorreceptor("");
			objFacturaBean.setScoloniareceptor(objCcodigoPostalReceptor.getScolonia());
			objFacturaBean.setSciudadreceptor(objCcodigoPostalReceptor.getSciudad());
			objFacturaBean.setSmunicipioreceptor(objCcodigoPostalReceptor.getSdelegacionmunicipio());
			objFacturaBean.setSestadoreceptor("MEXICO D.F.");
			objFacturaBean.setScodigopostalreceptor(objCcodigoPostalReceptor.getCpostal());
			objFacturaBean.setSpaisreceptor(objTDatoFiscal.getSpais());
			/** Ingresa datos de emisor Olab **/
			objCentidadLegal =objFacturacionElectronicaMayoreo.buscaEntidadLegal(objTfactura.getCentidadlegal());
			objCcodigoPostalEmisor =objFacturacionElectronicaMayoreo.buscaCodigoPostal(objCentidadLegal.getCcodigopostal());
			objFacturaBean.setSrazonsocialemisor(objCentidadLegal.getSrazonsocial());
			objFacturaBean.setSrfcemisor(objCentidadLegal.getSrfc());
			objFacturaBean.setScalleemisor(objCentidadLegal.getSdireccionfiscal()+" "+objCentidadLegal.getNexterior());
			objFacturaBean.setSnexterioremisor(objCentidadLegal.getNexterior());
			objFacturaBean.setSninterioremisor("");
			objFacturaBean.setScoloniaemisor(objCcodigoPostalEmisor.getScolonia());
			objFacturaBean.setSciudademisor("MEXICO D.F.");
			objFacturaBean.setSmunicipioemisor(objCcodigoPostalEmisor.getSdelegacionmunicipio());
			objFacturaBean.setScodigopostalemisor(objCcodigoPostalEmisor.getCpostal());
			objFacturaBean.setSpaisemisor(objCentidadLegal.getSpais());
			/** Ingresa datos de emisor Sucursal **/
			objFacturaBean.setScallesuc(objFacturaBean.getObjSucursalBean().getSdireccion());
			objFacturaBean.setScoloniasuc(objFacturaBean.getObjSucursalBean().getScolonia());
			objFacturaBean.setScodigopostalsuc(objFacturaBean.getObjSucursalBean().getScodigopostal());
			objFacturaBean.setSciudadsuc(objFacturaBean.getObjSucursalBean().getSciudad());
			objFacturaBean.setSmunicipiosuc(objFacturaBean.getObjSucursalBean().getSmunicipio());
			objFacturaBean.setSestadosuc(objFacturaBean.getObjSucursalBean().getSestado());
			objFacturaBean.setSpaissuc(objFacturaBean.getObjSucursalBean().getSpais());
			iObjLog.debug("Datos del emisor seteados");
			iObjLog.debug("Comienza Cabecero");
			/** Ingresa Cabecero izquierdo*/
			objFacturaBean.setFecha((objFormatos.getFechaNumerosHoraMinYearMothnDay(objTfactura.getDregistro()).toString()));
			objFacturaBean.setFechaxml(objFormatos.getFechaHoraFEXMLNew(objTfactura.getDregistro()));
			
			if (marca == 1){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1003);
			}else if (marca==4){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1012);
			}else if (marca==5){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1013);
			}
			
			objFacturaBean.setNnumeroaprobacion(objCcontrolFolio.getNaprobacion().toString());
			objFacturaBean.setSanoaprobacion(objCcontrolFolio.getNanoprobacion().toString());
			objFacturaBean.setSserie(objTfactura.getSserie());
			//objFacturaBean.setSfolio(String.valueOf(objCcontrolFolio.getUfolioactual()));
			objFacturaBean.setSseriofoliocompleto(this.llenaIdFactura(objFacturaBean.getSserie(),objFacturaBean.getSfolio(), 8));
			/*** Setea los valores de la orden para ser facturados */
			objFacturaBean.setSclientecompleto(Integer.toString(objTfactura.getCcliente()));
			objBody = new BodyFacturaElectronicaBean();
			//Se crea el cabecero de la factura
			objBody.setDblImporte(0);
			objBody.setDblValorUnitario(0);
			objBody.setIntCantidad(1);
			objBody.setStrCodigo("0");
			//DatosOrdenDao objDatoOrdenDAO = new DatosOrdenDao();
			objBody.setStrDescripcion(objFacturaBean.getSdescripcion().trim());
			//objBody.setStrUnidad("NO APLICA");
			objBody.setBolredondear(false);	
			objBody.setDblValorUnitario(objTfactura.getMsubtotal().doubleValue());
			objBody.setDblImporte(objTfactura.getMsubtotal().doubleValue());
			objFacturaBean.setBody(objBody);
			objFacturaBean.setMsubtotalsuma(objTfactura.getMsubtotal().doubleValue());
			objFacturaBean.setMdescuento(0);
			objFacturaBean.setMsubtotal(objTfactura.getMsubtotal().doubleValue());
			objFacturaBean.setMiva(objTfactura.getMiva().doubleValue());		
			objFacturaBean.setMtotal(objTfactura.getMtotal().doubleValue());
			//ExamenesDao objExamenDAO = new ExamenesDao();
			/*for (int inti = 0; inti < 2; inti++ ) {
				objBody = new BodyFacturaElectronicaBean();
				objBody.setDblImporte(1.18);	
				objBody.setDblValorUnitario(0.9);
				objBody.setIntCantidad(1);
				objBody.setStrCodigo("codigo");
				objBody.setStrDescripcion("DESCRIPCION DEL SUBBODY");
				objBody.setStrUnidad("unidad");
				objBody.setBolredondear(true);
				objFacturaBean.setBody(objBody);
				objFacturaBean.addMsubtotalsuma(objBody.getDblImporte());
				iObjLog.debug("Desgloce----------");
			}*/
			/* Crear FOP, PDF y XML de la factura */	
				objFacturaBean.setSxml("");	
				objFacturaBean.setSfop("");				
				objFacturaBean.setSsellodigital("");
				objFacturaBean.setScadenaoriginal("");
				iObjLog.debug("Saliendo a FormatoFacturaEmpresaGlobalImpl.crearFacturaFormatoEmpresa:Saliendo...  ");
				
		}catch (TorqueException aObjException){
    	    iObjLog.error("FormatoFacturaEmpresaGlobalImpl.crearOrdenFacturar:ErrorException....", aObjException);
    	    throw aObjException;
    	} finally {
			objBuscarOrdenDAO = null;
			objBody = null;
			objSucursalDAO = null;
			objBuscarOrdenDAO=null;
			objSucursalAdionalesDAO = null;
			objFormatos = null;
			
    	}
		return objFacturaBean;
	}

	public static String llenaIdFactura(String strNemonico,String intFactura,int MaxLength) {
		String strReturn = "";
		int intTotal = (strNemonico.length() + intFactura.length());
		for(int i = intTotal;i <= MaxLength;i++) {
			strReturn += "0";
		}		
		return strNemonico + strReturn + intFactura;
	}
	

}