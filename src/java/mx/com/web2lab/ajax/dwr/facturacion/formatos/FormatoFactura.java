package mx.com.web2lab.ajax.dwr.facturacion.formatos;

import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;

public interface FormatoFactura {

	public FacturaElectronicaBean crearFacturaFormato(FacturaElectronicaBean objFacturaBean) throws Exception;
		
}
