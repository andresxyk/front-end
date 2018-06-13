package mx.com.web2lab.ajax.dwr.facturacion.formatos;

import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;

public interface FormatoFacturaEmpresa {

	public FacturaElectronicaBean crearFacturaFormatoEmpresa(FacturaElectronicaBean objFacturaBean) throws Exception;
		
}
