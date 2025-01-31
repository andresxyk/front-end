package mx.com.web2lab.ajax.dwr.facturacion.formatos.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;


import mx.com.web2lab.ajax.dwr.facturacion.formatos.FormatoFacturaEmpresa;
import mx.com.web2lab.backend.beans.ap.OrdenBean;
import mx.com.web2lab.backend.beans.ap.OrdenExamenBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.BodyFacturaElectronicaBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.DesgloceFacturaExamenBean;
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

public class FormatoFacturaEmpresaDesgloceImpl implements FormatoFacturaEmpresa {

	private static Log iObjLog = LogFactory.getLog(FormatoFacturaEmpresaDesgloceImpl.class);

	public FacturaElectronicaBean crearFacturaFormatoEmpresa(FacturaElectronicaBean objFacturaBean, int marca, String serie) throws Exception {
		DatosOrdenDao objBuscarOrdenDAO = new DatosOrdenDao();
		SucursalDao objSucursalDAO = new SucursalDao();
		Formatos objFormatos = new Formatos();
		BodyFacturaElectronicaBean objBody = null;
		TFactura objTfactura= new TFactura();
		TDatoFiscal objTDatoFiscal = new TDatoFiscal();
		CEntidadLegal objCentidadLegal = new CEntidadLegal();
		CCodigoPostal objCcodigoPostalReceptor= new CCodigoPostal();
		CCodigoPostal objCcodigoPostalEmisor= new CCodigoPostal();
		CControlFolio objCcontrolFolio = new CControlFolio();
		DesgloceFacturaExamenBean objdesgloceExamen = new DesgloceFacturaExamenBean();
		List lstExamen = new ArrayList();
		try {					
			iObjLog.debug("Entrando a FormatoFacturaEmpresaDesgloceImpl.crearFacturaFormatoEmpresa:Entrando...  ");
			/** Busca los datos de la Orden **/
			//OrdenBean objOrdenBean = objBuscarOrdenDAO.buscarOrdenExamenFac(Integer.parseInt(objFacturaBean.getkOrdenSucursal()));
			/** Busca los datos de la Sucursal Folios, serie **/
			//SucursalBean objSucursalTempBean = objSucursalDAO.getSucursal(1003);
			SucursalBean objSucursalTempBean = null;
			if(marca==1){
				 objSucursalTempBean = objSucursalDAO.getSucursal(1003);				
			}else if(marca==4){
				 objSucursalTempBean = objSucursalDAO.getSucursal(1012);
			}else if(marca==5){
				 objSucursalTempBean = objSucursalDAO.getSucursal(1013);
			}else if(marca==15){
				 objSucursalTempBean = objSucursalDAO.getSucursal(1017);
			}else if(marca==7){
				if(serie.equals("AJP")){
					objSucursalTempBean = objSucursalDAO.getSucursal(1014);
				}else if(serie.equals("AJL")){
					objSucursalTempBean = objSucursalDAO.getSucursal(1015);
				}
			}else if(marca==19){
				 objSucursalTempBean = objSucursalDAO.getSucursal(1020);
			}else if(marca==20){
				 objSucursalTempBean = objSucursalDAO.getSucursal(1021);
			}else if(marca==21){
				 objSucursalTempBean = objSucursalDAO.getSucursal(1022);
			}else if(marca==16){
				 objSucursalTempBean = objSucursalDAO.getSucursal(1026);
			}else if(marca==22){
				 objSucursalTempBean = objSucursalDAO.getSucursal(1023);
			}else if(marca==25){
				 objSucursalTempBean = objSucursalDAO.getSucursal(1024);
			}else if(marca==26){
				 objSucursalTempBean = objSucursalDAO.getSucursal(1025);
			}else if(marca==9){
				 objSucursalTempBean = objSucursalDAO.getSucursal(9999);
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
			objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1003);
			
			if(marca==1){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1003);				
			}else if(marca==4){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1012);
			}else if(marca==5){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1013);
			}else if(marca==15){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1017);
			}else if(marca==7){
				if(serie.equals("AJP")){
					objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1014);
				}else if(serie.equals("AJL")){
					objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1015);
				}
			}else if(marca==19){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1020);
			}else if(marca==20){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1021);
			}else if(marca==21){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1022);
			}else if(marca==16){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1026);
			}else if(marca==22){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1023);
			}else if(marca==25){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1024);
			}else if(marca==26){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(1025);
			}else if(marca==9){
				objCcontrolFolio=objFacturacionElectronicaMayoreo.buscaControlFoliol(9999);
			}
			
			objFacturaBean.setNnumeroaprobacion(objCcontrolFolio.getNaprobacion().toString());
			objFacturaBean.setSanoaprobacion(objCcontrolFolio.getNanoprobacion().toString());
			objFacturaBean.setSserie(objTfactura.getSserie());
			//objFacturaBean.setSfolio(String.valueOf(objCcontrolFolio.getUfolioactual()));
			objFacturaBean.setSseriofoliocompleto(this.llenaIdFactura(objFacturaBean.getSserie(),objFacturaBean.getSfolio(), 8));
			lstExamen = objFacturacionElectronicaMayoreo.getDesgloceExamenesFactura(objTfactura.getKfactura());
			objFacturaBean.setSclientecompleto(Integer.toString(objTfactura.getCcliente()));
			//Cuerpo de la factura
			for (int inti = 0; inti < lstExamen.size(); inti++ ) {
				objdesgloceExamen = (DesgloceFacturaExamenBean) lstExamen.get(inti);
				double dlbvalorunitario = Double.valueOf(objdesgloceExamen.getPreciounitario()).doubleValue();
				double dblImporte = Double.valueOf(objdesgloceExamen.getImporte()).doubleValue();
				objBody = new BodyFacturaElectronicaBean();
				objBody.setDblImporte(dblImporte);	
				objBody.setDblValorUnitario(dlbvalorunitario);
				objBody.setIntCantidad(new Integer(objdesgloceExamen.getCantidad()).intValue());
				objBody.setStrCodigo(objdesgloceExamen.getCodigo());
				objBody.setStrDescripcion(objdesgloceExamen.getConcepto());
				objBody.setStrUnidad("unidad");
				objBody.setBolredondear(true);
				objFacturaBean.setBody(objBody);
				objFacturaBean.addMsubtotalsuma(dblImporte);
				iObjLog.debug("Monto----------"+objFacturaBean.getStrMsubtotalsuma());
			}
			//Parte final de totales factura
			double dbSubTotalFactura = Double.valueOf(objFacturaBean.getStrMsubtotalsuma()).doubleValue();
			iObjLog.debug("Monto----------dbSubTotalFactura "+dbSubTotalFactura);
			objFacturaBean.setMdescuento(0);
			objFacturaBean.setMsubtotalsuma(dbSubTotalFactura);
			objFacturaBean.setMsubtotal(dbSubTotalFactura);
			objFacturaBean.setMiva(objTfactura.getMiva().doubleValue());		
			objFacturaBean.setMtotal(objTfactura.getMtotal().doubleValue());
			/* Crear FOP, PDF y XML de la factura */	
			objFacturaBean.setSxml("");	
			objFacturaBean.setSfop("");				
			objFacturaBean.setSsellodigital("");
			objFacturaBean.setScadenaoriginal("");
			iObjLog.debug("Saliendo a FormatoFacturaEmpresaOrdenImpl.crearFacturaFormatoEmpresa:Saliendo...  ");
				
		}catch (TorqueException aObjException){
    	    iObjLog.error("FacturarElectronicaSucursalAjax.crearOrdenFacturar:ErrorException....", aObjException);
    	    throw aObjException;
    	} finally {
			objBuscarOrdenDAO = null;
			objBody = null;
			objSucursalDAO = null;
			objBuscarOrdenDAO=null;
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