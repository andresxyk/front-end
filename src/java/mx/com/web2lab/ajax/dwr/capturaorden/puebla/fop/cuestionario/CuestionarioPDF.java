package mx.com.web2lab.ajax.dwr.capturaorden.puebla.fop.cuestionario;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mx.com.web2lab.ajax.dwr.capturaorden.puebla.fop.Convertidor_FOP_PDF;
import mx.com.web2lab.backend.beans.puebla.CuestionarioPantallaBean;

public class CuestionarioPDF {

	private static Log iObjLog = LogFactory.getLog(CuestionarioPDF.class);	
	
	public String imprimirCuestionario(CuestionarioPantallaBean objCuestionarioPantallaBean) throws Exception {		
		Convertidor_FOP_PDF objManager = new Convertidor_FOP_PDF();		
		FileFOP objFileFOP = new FileFOP();
		String strfilefop = "";
		try {
			strfilefop = objFileFOP.createReporte(objCuestionarioPantallaBean);
			strfilefop =  objManager.createDocument(strfilefop, objCuestionarioPantallaBean.getkPaciente()  +  "-HistoriaClinica");		
		} catch (Exception aObjExcepcion) {
			iObjLog.error("ERROR CuestionarioPDF.imprimirCuestionario: ", aObjExcepcion);
			throw aObjExcepcion;
		} finally {
			objManager = null;
			objFileFOP = null;
		}
		return strfilefop;
	}
}
