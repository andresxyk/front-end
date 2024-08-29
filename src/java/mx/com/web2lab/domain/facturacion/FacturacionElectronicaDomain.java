package mx.com.web2lab.domain.facturacion;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;

import mx.com.web2lab.ajax.dwr.facturacion.tool.FacturaElectronicaPDF;
import mx.com.web2lab.ajax.dwr.facturacion.tool.FacturaElectronicaXML;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
import mx.com.web2lab.backend.dao.facturacion.electronica.fop.FacturaElectronicaEmpresaFOPDao;
import mx.com.web2lab.backend.dao.facturacion.electronica.fop.FacturaElectronicaFOPDao;
import mx.com.web2lab.backend.dao.facturacion.electronica.orden.OrdenDatosFacturacionDao;
import mx.com.web2lab.backend.dao.facturacion.electronica.security.SelloDigitalDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionElectronicaMayoreoDao;

public class FacturacionElectronicaDomain {
	private static Log iObjLog = LogFactory.getLog(FacturacionElectronicaDomain.class);

	public FacturaElectronicaBean crearFacturaElectronica(FacturaElectronicaBean objFacturaBean) throws Exception {
		iObjLog.debug("Entrando a FacturacionElectronicaDomain.crearFacturaElectronica:... Marca " + objFacturaBean.getCmarca());
		int cmarca=objFacturaBean.getCmarca();
		OrdenDatosFacturacionDao objDatosOrdenDAO = new OrdenDatosFacturacionDao();
		FacturaElectronicaXML objFacturaElectronicaXML = new FacturaElectronicaXML();
		FacturaElectronicaFOPDao objFacturaElectronicaFOPDAO = new FacturaElectronicaFOPDao();
		SelloDigitalDao objSelloDigitalDao = new SelloDigitalDao();
		FacturaElectronicaPDF objFacturaElectronicaPDF = new FacturaElectronicaPDF();
		try {
			objFacturaBean.setSrfcreceptor(objFacturaBean.getSrfcreceptor().trim().replaceAll(" ",""));
			objFacturaBean.setSrfcreceptor(objFacturaBean.getSrfcreceptor().trim().replaceAll("-",""));
			objFacturaBean = objFacturaElectronicaXML.createCFDXML(objFacturaBean);					
			objFacturaBean = objDatosOrdenDAO.persistirFactura(objFacturaBean);
			objFacturaBean = objDatosOrdenDAO.generaUpdateOrdenFac(objFacturaBean);
			objFacturaBean = objDatosOrdenDAO.generaSelloCadenaDigital(objFacturaBean);
			objFacturaBean = objSelloDigitalDao.sellaFacturaDigitalmente(objFacturaBean);			
			datosEmisor(objFacturaBean);						
			objFacturaBean.setSfop(objFacturaElectronicaFOPDAO.createFactura(objFacturaBean,false));					
			objFacturaBean.setsURL(objFacturaElectronicaPDF.createDocument(objFacturaBean));    				
			iObjLog.debug("Saliendo a FacturacionElectronicaDomain.crearFacturaElectronica:Saliendo... URL " + objFacturaBean.getsURL());
//			objFacturaBean = objFacturaElectronicaXML.createCFDXML(objFacturaBean);					
			objDatosOrdenDAO.actualizarFacturaXML(objFacturaBean,cmarca, objFacturaBean.getSserie());			
			iObjLog.debug("Saliendo a FacturacionElectronicaDomain.crearFacturaElectronica:Saliendo...  ");
		}catch (TorqueException aObjException){
    	    iObjLog.error("FacturacionElectronicaDomain.crearFacturaElectronica:ErrorException....", aObjException);
    	    throw aObjException;
    	} finally {
			objDatosOrdenDAO = null;   
			objFacturaElectronicaXML = null;
			objSelloDigitalDao = null;
			objFacturaElectronicaFOPDAO = null;
			objFacturaElectronicaPDF = null;
    	}
		return objFacturaBean;
	}	
	
	public FacturaElectronicaBean crearFacturaElectronicaMayoreo(FacturaElectronicaBean objFacturaBean) throws Exception {
		iObjLog.debug("Entrando a FacturacionElectronicaDomain.crearFacturaElectronicaMayoreo:...  Marca " + objFacturaBean.getCmarca());
		int cmarca=objFacturaBean.getCmarca();
		OrdenDatosFacturacionDao objDatosOrdenDAO = new OrdenDatosFacturacionDao();
		FacturaElectronicaXML objFacturaElectronicaXML = new FacturaElectronicaXML();
		FacturaElectronicaFOPDao objFacturaElectronicaFOPDAO = new FacturaElectronicaFOPDao();
		SelloDigitalDao objSelloDigitalDao = new SelloDigitalDao();
		FacturaElectronicaPDF objFacturaElectronicaPDF = new FacturaElectronicaPDF();
		try {
			objFacturaBean = objFacturaElectronicaXML.createCFDXML(objFacturaBean);					
			objFacturaBean = objDatosOrdenDAO.persistirFactura(objFacturaBean);
			objFacturaBean = objDatosOrdenDAO.generaSelloCadenaDigital(objFacturaBean);
			objFacturaBean = objSelloDigitalDao.sellaFacturaDigitalmente(objFacturaBean);						
			datosEmisor(objFacturaBean);									
			objFacturaBean.setSfop(objFacturaElectronicaFOPDAO.createFactura(objFacturaBean,false));					
			objFacturaBean.setsURL(objFacturaElectronicaPDF.createDocument(objFacturaBean));    				
//			objFacturaBean = objFacturaElectronicaXML.createCFDXML(objFacturaBean);					
			objDatosOrdenDAO.actualizarFacturaXML(objFacturaBean,cmarca, objFacturaBean.getSserie());
			iObjLog.debug("Saliendo a FacturacionElectronicaDomain.crearFacturaElectronicaMayoreo:Saliendo... URL " + objFacturaBean.getsURL());
		}catch (TorqueException aObjException){
    	    iObjLog.error("FacturacionElectronicaDomain.crearFacturaElectronica:ErrorException....", aObjException);
    	    throw aObjException;
    	} finally {
			objDatosOrdenDAO = null;   
			objFacturaElectronicaXML = null;
			objSelloDigitalDao = null;
			objFacturaElectronicaFOPDAO = null;
			objFacturaElectronicaPDF = null;
    	}
		return objFacturaBean;
	}
	
	public FacturaElectronicaBean crearFacturaElectronicaEmpresa(FacturaElectronicaBean objFacturaBean) throws Exception {
		iObjLog.debug("Entrando a FacturacionElectronicaDomain.crearFacturaElectronicaEmpresa:... Marca " + objFacturaBean.getCmarca());
		int cmarca=objFacturaBean.getCmarca();
		OrdenDatosFacturacionDao objDatosOrdenDAO = new OrdenDatosFacturacionDao();
		FacturacionElectronicaMayoreoDao objFacturacionElectronicaMayoreoDAO = new FacturacionElectronicaMayoreoDao();
		FacturaElectronicaXML objFacturaElectronicaXML = new FacturaElectronicaXML();
		FacturaElectronicaEmpresaFOPDao objFacturaElectronicaEmpresaFOPDAO = new FacturaElectronicaEmpresaFOPDao();
		SelloDigitalDao objSelloDigitalDao = new SelloDigitalDao();
		FacturaElectronicaPDF objFacturaElectronicaPDF = new FacturaElectronicaPDF();
		try {
			objFacturaBean = objFacturaElectronicaXML.createCFDXML(objFacturaBean);					
			objFacturaBean = objFacturacionElectronicaMayoreoDAO.persistirFactura(objFacturaBean);
			//objFacturaBean = objDatosOrdenDAO.generaUpdateOrdenFac(objFacturaBean);
			objFacturaBean = objDatosOrdenDAO.generaSelloCadenaDigital(objFacturaBean);
			objFacturaBean = objSelloDigitalDao.sellaFacturaDigitalmente(objFacturaBean);	
			datosEmisor(objFacturaBean);						
			objFacturaBean.setSfop(objFacturaElectronicaEmpresaFOPDAO.createFactura(objFacturaBean));					
			objFacturaBean.setsURL(objFacturaElectronicaPDF.createDocument(objFacturaBean));    				
//			objFacturaBean = objFacturaElectronicaXML.createCFDXML(objFacturaBean);					
			objDatosOrdenDAO.actualizarFacturaXML(objFacturaBean,cmarca,objFacturaBean.getSserie());			
			iObjLog.debug("Saliendo a FacturacionElectronicaDomain.crearFacturaElectronicaEmpresa:Saliendo... URL " + objFacturaBean.getsURL());
		}catch (TorqueException aObjException){
    	    iObjLog.error("FacturacionElectronicaDomain.crearFacturaElectronica:ErrorException....", aObjException);
    	    throw aObjException;
    	} finally {
			objDatosOrdenDAO = null;   
			objFacturaElectronicaXML = null;
			objSelloDigitalDao = null;
			objFacturaElectronicaEmpresaFOPDAO = null;
			objFacturacionElectronicaMayoreoDAO = null;
			objFacturaElectronicaPDF = null;
    	}
		return objFacturaBean;
	}
	
	public FacturaElectronicaBean crearFacturaEmpresaUnidad(FacturaElectronicaBean objFacturaBean) throws Exception {
		iObjLog.debug("Entrando a FacturacionElectronicaDomain.crearFacturaEmpresaUnidad:...  BY Marca " + objFacturaBean.getCmarca());
		int cmarca=objFacturaBean.getCmarca();
		OrdenDatosFacturacionDao objDatosOrdenDAO = new OrdenDatosFacturacionDao();
		FacturacionElectronicaMayoreoDao objFacturacionElectronicaMayoreoDAO = new FacturacionElectronicaMayoreoDao();
		FacturaElectronicaXML objFacturaElectronicaXML = new FacturaElectronicaXML();
		FacturaElectronicaFOPDao objFacturaElectronicaFOPDAO = new FacturaElectronicaFOPDao();
		SelloDigitalDao objSelloDigitalDao = new SelloDigitalDao();
		FacturaElectronicaPDF objFacturaElectronicaPDF = new FacturaElectronicaPDF();
		try {
			objFacturaBean = objFacturaElectronicaXML.createCFDXML(objFacturaBean);					
			objFacturaBean = objFacturacionElectronicaMayoreoDAO.persistirFactura(objFacturaBean);
			//objFacturaBean = objDatosOrdenDAO.generaUpdateOrdenFacReimpresion(objFacturaBean);
			objFacturaBean = objDatosOrdenDAO.generaSelloCadenaDigital(objFacturaBean);
			objFacturaBean = objSelloDigitalDao.sellaFacturaDigitalmente(objFacturaBean);	
			datosEmisor(objFacturaBean);						
			objFacturaBean.setSfop(objFacturaElectronicaFOPDAO.createFactura(objFacturaBean,true));					
			objFacturaBean.setsURL(objFacturaElectronicaPDF.createDocument(objFacturaBean));    				
//			objFacturaBean = objFacturaElectronicaXML.createCFDXML(objFacturaBean);					
			iObjLog.debug("Saliendo a FacturacionElectronicaDomain.crearFacturaEmpresaUnidad:Saliendo...  BY II " + objFacturaBean.getsURL());
			objDatosOrdenDAO.actualizarFacturaXML(objFacturaBean,cmarca,objFacturaBean.getSserie());			
			iObjLog.debug("Saliendo a FacturacionElectronicaDomain.crearFacturaEmpresaUnidad:Saliendo...  BY " + objFacturaBean.getsURL());
		}catch (TorqueException aObjException){
    	    iObjLog.error("FacturacionElectronicaDomain.crearFacturaElectronica:ErrorException....", aObjException);
    	    throw aObjException;
    	} finally {
			objDatosOrdenDAO = null;   
			objFacturaElectronicaXML = null;
			objSelloDigitalDao = null;
			objFacturaElectronicaFOPDAO = null;
			objFacturaElectronicaPDF = null;
    	}
		return objFacturaBean;
	}	


	/******************Verificar este caso de PEMEX **************************/
	public FacturaElectronicaBean crearFacturaXmlAdenda(FacturaElectronicaBean objFacturaBean) throws Exception {
		iObjLog.debug("Entrando a FacturacionElectronicaDomain.crearFacturaXmlAdenda:...  ");
		int cmarca=objFacturaBean.getCmarca();
		OrdenDatosFacturacionDao objDatosOrdenDAO = new OrdenDatosFacturacionDao();
		FacturacionElectronicaMayoreoDao objFacturacionElectronicaMayoreoDAO = new FacturacionElectronicaMayoreoDao();
		FacturaElectronicaXML objFacturaElectronicaXML = new FacturaElectronicaXML();
		FacturaElectronicaEmpresaFOPDao objFacturaElectronicaEmpresaFOPDAO = new FacturaElectronicaEmpresaFOPDao();
		SelloDigitalDao objSelloDigitalDao = new SelloDigitalDao();
		FacturaElectronicaPDF objFacturaElectronicaPDF = new FacturaElectronicaPDF();
		try {
			objFacturaBean = objFacturaElectronicaXML.createCFDXML(objFacturaBean);					
//			objFacturaBean = objFacturaElectronicaXML.createXmlAdendaEmpresa(objFacturaBean);					
			objFacturaBean = objFacturacionElectronicaMayoreoDAO.persistirFactura(objFacturaBean);
			//objFacturaBean = objDatosOrdenDAO.generaUpdateOrdenFac(objFacturaBean);
			objFacturaBean = objDatosOrdenDAO.generaSelloCadenaDigital(objFacturaBean);
			objFacturaBean = objSelloDigitalDao.sellaFacturaDigitalmente(objFacturaBean);		
			datosEmisor(objFacturaBean);						
			objFacturaBean.setSfop(objFacturaElectronicaEmpresaFOPDAO.createFactura(objFacturaBean));					
			objFacturaBean.setsURL(objFacturaElectronicaPDF.createDocument(objFacturaBean));    				
			objDatosOrdenDAO.actualizarFacturaXML(objFacturaBean, cmarca, objFacturaBean.getSserie());			
			iObjLog.debug("Saliendo a FacturacionElectronicaDomain.crearFacturaElectronica:Saliendo...  ");
		}catch (TorqueException aObjException){
    	    iObjLog.error("FacturacionElectronicaDomain.crearFacturaElectronica:ErrorException....", aObjException);
    	    throw aObjException;
    	} finally {
			objDatosOrdenDAO = null;   
			objFacturaElectronicaXML = null;
			objSelloDigitalDao = null;
			objFacturaElectronicaEmpresaFOPDAO = null;
			objFacturacionElectronicaMayoreoDAO = null;
			objFacturaElectronicaPDF = null;
    	}
		return objFacturaBean;
	}	
	
	
	private FacturaElectronicaBean datosEmisor(FacturaElectronicaBean facturaelectronicaBean) {
		if (facturaelectronicaBean.getCmarca() == 1) {
			facturaelectronicaBean.setSrazonsocialemisor("ESTUDIOS CLINICOS DR TJ ORIARD SA DE CV");
			facturaelectronicaBean.setSrfcemisor("ECD741021QA5");
			facturaelectronicaBean.setScalleemisor("AV. REVOLUCION No.56");
			facturaelectronicaBean.setSnexterioremisor("");
			facturaelectronicaBean.setSninterioremisor("");
			facturaelectronicaBean.setScoloniaemisor("ESCANDON");
			facturaelectronicaBean.setSciudademisor("MEXICO D.F.");
			facturaelectronicaBean.setSmunicipioemisor("MIGUEL HIDALGO");
			facturaelectronicaBean.setSestadoemisor("MEXICO D.F.");
			facturaelectronicaBean.setScodigopostalemisor("11800");
			facturaelectronicaBean.setSpaisemisor("MEXICO");
		} else if (facturaelectronicaBean.getCmarca() == 4) {
			facturaelectronicaBean.setSrazonsocialemisor("LABORATORIO QUIMICO CLINICO AZTECA S.A.P.I. DE C.V.");
			facturaelectronicaBean.setSrfcemisor("LQC920131M20");
			facturaelectronicaBean.setScalleemisor("SIMON BOLIVAR 15");
			facturaelectronicaBean.setSnexterioremisor("");
			facturaelectronicaBean.setSninterioremisor("");
			facturaelectronicaBean.setScoloniaemisor("LOS REYES ACAQUILPAN CENTRO");
			facturaelectronicaBean.setSciudademisor("ESTADO DE MEXICO");
			facturaelectronicaBean.setSmunicipioemisor("LA PAZ");
			facturaelectronicaBean.setSestadoemisor("MEXICO D.F.");
			facturaelectronicaBean.setScodigopostalemisor("56400");
			facturaelectronicaBean.setSpaisemisor("MEXICO");
		} else if (facturaelectronicaBean.getCmarca() == 5) {
			facturaelectronicaBean.setSrazonsocialemisor("SWISSLAB S.A. de C.V.");
			facturaelectronicaBean.setSrfcemisor("SWI1201268J8");
			facturaelectronicaBean.setScalleemisor("AV. MIGUEL HIDALGO 1729 PTE.");
			facturaelectronicaBean.setSnexterioremisor("");
			facturaelectronicaBean.setSninterioremisor("");
			facturaelectronicaBean.setScoloniaemisor("OBISPADO");
			facturaelectronicaBean.setSciudademisor("MONTERREY");
			facturaelectronicaBean.setSmunicipioemisor("MONTERREY");
			facturaelectronicaBean.setSestadoemisor("NUEVO LEON");
			facturaelectronicaBean.setScodigopostalemisor("64060");
			facturaelectronicaBean.setSpaisemisor("MEXICO");
		} else if (facturaelectronicaBean.getCmarca() == 7) {
			if(facturaelectronicaBean.getSserie().equals("AJP")){
				facturaelectronicaBean.setSrazonsocialemisor("LABORATORIO CLINICO DEL PRADO S.A. DE C.V.");
				facturaelectronicaBean.setSrfcemisor("LCP061017PA9");
				facturaelectronicaBean.setScalleemisor("MEDELLIN 153");
				facturaelectronicaBean.setSnexterioremisor("");
				facturaelectronicaBean.setSninterioremisor("");
				facturaelectronicaBean.setScoloniaemisor("ROMA NORTE");
				facturaelectronicaBean.setSciudademisor("CIUDAD DE MEXICO");
				facturaelectronicaBean.setSmunicipioemisor("CUAUHTEMOC");
				facturaelectronicaBean.setSestadoemisor("CIUDAD DE MEXICO");
				facturaelectronicaBean.setScodigopostalemisor("06700");
				facturaelectronicaBean.setSpaisemisor("MEXICO");	
			}else if (facturaelectronicaBean.getSserie().equals("AJL")){
				facturaelectronicaBean.setSrazonsocialemisor("LABORATORIO CLINICO LEAN S.A. DE C.V.");
				facturaelectronicaBean.setSrfcemisor("LCL050622DD9");
				facturaelectronicaBean.setScalleemisor("MEDELLIN 153");
				facturaelectronicaBean.setSnexterioremisor("");
				facturaelectronicaBean.setSninterioremisor("");
				facturaelectronicaBean.setScoloniaemisor("ROMA NORTE");
				facturaelectronicaBean.setSciudademisor("CIUDAD DE MEXICO");
				facturaelectronicaBean.setSmunicipioemisor("CUAUHTEMOC");
				facturaelectronicaBean.setSestadoemisor("CIUDAD DE MEXICO");
				facturaelectronicaBean.setScodigopostalemisor("06700");
				facturaelectronicaBean.setSpaisemisor("MEXICO");
			}
		} else if (facturaelectronicaBean.getCmarca() == 19) {
			facturaelectronicaBean.setSrazonsocialemisor("LABORATEX SA DE CV");
			facturaelectronicaBean.setSrfcemisor("LAB020416Q67");
			facturaelectronicaBean.setScalleemisor("CALLE AVENIDA 21 PONIENTE");
			facturaelectronicaBean.setSnexterioremisor("");
			facturaelectronicaBean.setSninterioremisor("");
			facturaelectronicaBean.setScoloniaemisor("ROMA NORTE");
			facturaelectronicaBean.setSciudademisor("CIUDAD DE MEXICO");
			facturaelectronicaBean.setSmunicipioemisor("CUAUHTEMOC");
			facturaelectronicaBean.setSestadoemisor("CIUDAD DE MEXICO");
			facturaelectronicaBean.setScodigopostalemisor("06700");
			facturaelectronicaBean.setSpaisemisor("MEXICO");
		} else if (facturaelectronicaBean.getCmarca() == 20) {
			facturaelectronicaBean.setSrazonsocialemisor("LABORATORIOS BIO ANALISIS SC");
			facturaelectronicaBean.setSrfcemisor("");
			facturaelectronicaBean.setScalleemisor("CALLE AVENIDA 21 PONIENTE");
			facturaelectronicaBean.setSnexterioremisor("");
			facturaelectronicaBean.setSninterioremisor("");
			facturaelectronicaBean.setScoloniaemisor("ROMA NORTE");
			facturaelectronicaBean.setSciudademisor("CIUDAD DE MEXICO");
			facturaelectronicaBean.setSmunicipioemisor("CUAUHTEMOC");
			facturaelectronicaBean.setSestadoemisor("CIUDAD DE MEXICO");
			facturaelectronicaBean.setScodigopostalemisor("06700");
			facturaelectronicaBean.setSpaisemisor("MEXICO");
		} else if (facturaelectronicaBean.getCmarca() == 21) {
			facturaelectronicaBean.setSrazonsocialemisor("ASESORES ESPECIALIZADOS EN LABORATORIOS");
			facturaelectronicaBean.setSrfcemisor("AEL9703115B0");
			facturaelectronicaBean.setScalleemisor("CALLE AVENIDA 21 PONIENTE");
			facturaelectronicaBean.setSnexterioremisor("");
			facturaelectronicaBean.setSninterioremisor("");
			facturaelectronicaBean.setScoloniaemisor("ROMA NORTE");
			facturaelectronicaBean.setSciudademisor("CIUDAD DE MEXICO");
			facturaelectronicaBean.setSmunicipioemisor("CUAUHTEMOC");
			facturaelectronicaBean.setSestadoemisor("CIUDAD DE MEXICO");
			facturaelectronicaBean.setScodigopostalemisor("06700");
			facturaelectronicaBean.setSpaisemisor("MEXICO");
		} else if (facturaelectronicaBean.getCmarca() == 16) {
			facturaelectronicaBean.setSrazonsocialemisor("MOREIRA");
			facturaelectronicaBean.setSrfcemisor("");
			facturaelectronicaBean.setScalleemisor("CALLE AVENIDA 21 PONIENTE");
			facturaelectronicaBean.setSnexterioremisor("");
			facturaelectronicaBean.setSninterioremisor("");
			facturaelectronicaBean.setScoloniaemisor("ROMA NORTE");
			facturaelectronicaBean.setSciudademisor("CIUDAD DE MEXICO");
			facturaelectronicaBean.setSmunicipioemisor("CUAUHTEMOC");
			facturaelectronicaBean.setSestadoemisor("CIUDAD DE MEXICO");
			facturaelectronicaBean.setScodigopostalemisor("06700");
			facturaelectronicaBean.setSpaisemisor("MEXICO");
		} else if (facturaelectronicaBean.getCmarca() == 22) {
			facturaelectronicaBean.setSrazonsocialemisor("POLAB");
			facturaelectronicaBean.setSrfcemisor("");
			facturaelectronicaBean.setScalleemisor("CALLE AVENIDA 21 PONIENTE");
			facturaelectronicaBean.setSnexterioremisor("");
			facturaelectronicaBean.setSninterioremisor("");
			facturaelectronicaBean.setScoloniaemisor("ROMA NORTE");
			facturaelectronicaBean.setSciudademisor("CIUDAD DE MEXICO");
			facturaelectronicaBean.setSmunicipioemisor("CUAUHTEMOC");
			facturaelectronicaBean.setSestadoemisor("CIUDAD DE MEXICO");
			facturaelectronicaBean.setScodigopostalemisor("06700");
			facturaelectronicaBean.setSpaisemisor("MEXICO");
		} else if (facturaelectronicaBean.getCmarca() == 25) {
			facturaelectronicaBean.setSrazonsocialemisor("BIOMEDICA DE REFERENCIA");
			facturaelectronicaBean.setSrfcemisor("");
			facturaelectronicaBean.setScalleemisor("CALLE AVENIDA 21 PONIENTE");
			facturaelectronicaBean.setSnexterioremisor("");
			facturaelectronicaBean.setSninterioremisor("");
			facturaelectronicaBean.setScoloniaemisor("ROMA NORTE");
			facturaelectronicaBean.setSciudademisor("CIUDAD DE MEXICO");
			facturaelectronicaBean.setSmunicipioemisor("CUAUHTEMOC");
			facturaelectronicaBean.setSestadoemisor("CIUDAD DE MEXICO");
			facturaelectronicaBean.setScodigopostalemisor("06700");
			facturaelectronicaBean.setSpaisemisor("MEXICO");
		} else if (facturaelectronicaBean.getCmarca() == 26) {
			facturaelectronicaBean.setSrazonsocialemisor("PROMEDIC");
			facturaelectronicaBean.setSrfcemisor("");
			facturaelectronicaBean.setScalleemisor("CALLE AVENIDA 21 PONIENTE");
			facturaelectronicaBean.setSnexterioremisor("");
			facturaelectronicaBean.setSninterioremisor("");
			facturaelectronicaBean.setScoloniaemisor("ROMA NORTE");
			facturaelectronicaBean.setSciudademisor("CIUDAD DE MEXICO");
			facturaelectronicaBean.setSmunicipioemisor("CUAUHTEMOC");
			facturaelectronicaBean.setSestadoemisor("CIUDAD DE MEXICO");
			facturaelectronicaBean.setScodigopostalemisor("06700");
			facturaelectronicaBean.setSpaisemisor("MEXICO");
		}
		facturaelectronicaBean.setScallesuc("AV. RÍO CONSULADO ESQ. LIRAS, NO. 2727");
		facturaelectronicaBean.setScoloniasuc("AQUILES SERDÁN");
		facturaelectronicaBean.setScodigopostalsuc("15430");
		facturaelectronicaBean.setSciudadsuc("MÉXICO D.F.");		
		return facturaelectronicaBean;		
	}
}
