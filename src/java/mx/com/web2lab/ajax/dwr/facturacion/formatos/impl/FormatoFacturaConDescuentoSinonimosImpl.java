package mx.com.web2lab.ajax.dwr.facturacion.formatos.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;

import mx.com.web2lab.ajax.dwr.facturacion.formatos.FormatoFactura;
import mx.com.web2lab.backend.beans.ap.OrdenBean;
import mx.com.web2lab.backend.beans.ap.OrdenExamenBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.BodyFacturaElectronicaBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
import mx.com.web2lab.backend.beans.tools.SucursalBean;
import mx.com.web2lab.backend.dao.ap.DatosOrdenDao;
import mx.com.web2lab.backend.dao.ap.ExamenesDao;
import mx.com.web2lab.backend.dao.tools.SucursalDao;
import mx.com.web2lab.backend.util.Formatos;

public class FormatoFacturaConDescuentoSinonimosImpl implements FormatoFactura {

	private static Log iObjLog = LogFactory.getLog(FormatoFacturaConDescuentoSinonimosImpl.class);

	private int cMarca = 1;
	
	public FormatoFacturaConDescuentoSinonimosImpl(int cMarca) {
		this.cMarca = cMarca;
	}
	
	public FacturaElectronicaBean crearFacturaFormato(FacturaElectronicaBean objFacturaBean) throws Exception {
		DatosOrdenDao objBuscarOrdenDAO = new DatosOrdenDao();
		SucursalDao objSucursalDAO = new SucursalDao();
		SucursalDao objSucursalAdionalesDAO = new SucursalDao();
		Formatos objFormatos = new Formatos();
		BodyFacturaElectronicaBean objBody = null;
		OrdenExamenBean objOrdenExamenBean = null;		
		try {					
			/** Busca los datos de la Orden **/
			OrdenBean objOrdenBean = objBuscarOrdenDAO.buscarOrdenExamenFac(Integer.parseInt(objFacturaBean.getkOrdenSucursal()));
			SucursalBean objSucursalTempBean = objSucursalDAO.getSucursal(objFacturaBean.getcSucursal());
			objSucursalAdionalesDAO.getDatosFacturarSucursal(objSucursalTempBean,this.cMarca);					
			objFacturaBean.setObjSucursalBean(objSucursalTempBean);

			/*** Setea los valores de la orden para ser facturados */
			if (objOrdenBean.getMpagapaciente() > 0.0) {
				objFacturaBean.setMtotal(objOrdenBean.getMpagapaciente());
				objFacturaBean.setSclientecompleto(objOrdenBean.getCconvenio() + "");
			} else {
				objFacturaBean.setMtotal(objOrdenBean.getMfacturaempresa());
				objFacturaBean.setSclientecompleto(objOrdenBean.getCconvenio() + " " + objOrdenBean.getSconvenio().replaceAll("CREDITO", " "));
			}
			objFacturaBean.setMiva(objOrdenBean.getMtotal() - (objOrdenBean.getMtotal() / 1.16));
			objFacturaBean.setMdescuento((objOrdenBean.getMdescuentoempresa() + objOrdenBean.getMdescuentopaciente()) / 1.16);
			objFacturaBean.setMsubtotal(objFacturaBean.getMtotal() - objFacturaBean.getMiva());
			objFacturaBean.setMsubtotaluniemp(objFacturaBean.getMtotal() - objFacturaBean.getMiva());				
			objBody = new BodyFacturaElectronicaBean();
			objBody.setDblImporte(0);
			objBody.setDblValorUnitario(0);
			objBody.setIntCantidad(0);
			objBody.setStrCodigo(objOrdenBean.getSordencompleta());
			DatosOrdenDao objDatoOrdenDAO = new DatosOrdenDao();
			objBody.setStrDescripcion(objOrdenBean.getBpacientebean().getSappaterno() + " " + objOrdenBean.getBpacientebean().getSapmaterno() + " " + objOrdenBean.getBpacientebean().getSnombre() + "    " + objDatoOrdenDAO.getDatosAdicionales(Integer.parseInt(objFacturaBean.getkOrdenSucursal())));
			objDatoOrdenDAO = null;
			objBody.setStrUnidad("unidad");
			objBody.setBolredondear(false);			
			objFacturaBean.setBody(objBody);
			objFacturaBean.setMsubtotalsuma(0.0);
			ExamenesDao objExamenDAO = new ExamenesDao();
			for (int inti = 0; inti < objOrdenBean.sizeExamenes(); inti++ ) {
				objOrdenExamenBean = objOrdenBean.getOrdenExamen(inti);
				objBody = new BodyFacturaElectronicaBean();
				if (objOrdenBean.getMpagapaciente() > 0.0) {
					objBody.setDblImporte((objOrdenExamenBean.getMpagopaciente() + (objOrdenExamenBean.getMdescuentoempresa() + objOrdenExamenBean.getMdescuentomedico() + objOrdenExamenBean.getMdescuentopromocion())) / 1.16);
				} else {
					objBody.setDblImporte((objOrdenExamenBean.getMfacturaempresa() + (objOrdenExamenBean.getMdescuentoempresa() + objOrdenExamenBean.getMdescuentomedico() + objOrdenExamenBean.getMdescuentopromocion())) / 1.16);
				}					
				objBody.setDblValorUnitario(objBody.getDblImporte());
				objBody.setIntCantidad(1);
				objBody.setStrCodigo(objExamenDAO.getSinonimoExamenConvenio(objOrdenBean.getCconvenio(), objOrdenExamenBean.getCexamen()));				
				objBody.setStrDescripcion(objOrdenExamenBean.getSexamen());
				objBody.setStrUnidad("unidad");
				objBody.setBolredondear(true);
				objFacturaBean.setBody(objBody);
				objFacturaBean.addMsubtotalsuma(objBody.getDblImporte());
			}
			objExamenDAO = null;			
			/* Ingresa valores a la factura */
				objFacturaBean.setScallesuc(objFacturaBean.getObjSucursalBean().getSdireccion());
				objFacturaBean.setScoloniasuc(objFacturaBean.getObjSucursalBean().getScolonia());
				objFacturaBean.setScodigopostalsuc(objFacturaBean.getObjSucursalBean().getScodigopostal());
				objFacturaBean.setSciudadsuc(objFacturaBean.getObjSucursalBean().getSciudad());
				objFacturaBean.setSmunicipiosuc(objFacturaBean.getObjSucursalBean().getScolonia());
				objFacturaBean.setFecha(objFormatos.getFechaHoraYearMothnDay());
				objFacturaBean.setFechaxml(objFormatos.getFechaHoraFEXML());
				objFacturaBean.setNnumeroaprobacion(String.valueOf(objFacturaBean.getObjSucursalBean().getNaprobacion()));
				objFacturaBean.setSanoaprobacion(String.valueOf(objFacturaBean.getObjSucursalBean().getNanoprobacion()));
				objFacturaBean.setSserie(objFacturaBean.getObjSucursalBean().getSserie());
				objFacturaBean.setSfolio(String.valueOf(objFacturaBean.getObjSucursalBean().getUfolioactual()));
				objFacturaBean.setSseriofoliocompleto(this.llenaIdFactura(objFacturaBean.getSserie(),objFacturaBean.getSfolio(), 8));
			/* Crear FOP, PDF y XML de la factura */	
				objFacturaBean.setSxml("");	
				objFacturaBean.setSfop("");				
				objFacturaBean.setSsellodigital("");
				objFacturaBean.setScadenaoriginal("");	
		}catch (TorqueException aObjException){
    	    iObjLog.error("FacturarElectronicaSucursalAjax.crearOrdenFacturar:ErrorException....", aObjException);
    	    throw aObjException;
    	} finally {
			objBuscarOrdenDAO = null;
			objBody = null;
			objSucursalDAO = null;
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