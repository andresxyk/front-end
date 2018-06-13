package mx.com.web2lab.ajax.dwr.capturaorden.puebla.fop.formatopuebla;

import mx.com.web2lab.ajax.dwr.capturaorden.puebla.fop.Convertidor_FOP_PDF;
import mx.com.web2lab.ajax.dwr.capturaorden.puebla.fop.cuestionario.FileFOP;
import mx.com.web2lab.ajax.dwr.capturaorden.puebla.fop.formatodf.FormatoFOP_DF;
import mx.com.web2lab.backend.beans.puebla.CuestionarioPantallaBean;
import mx.com.web2lab.backend.beans.puebla.CuestionarioPantallaInterpretacionBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class FormatoPDF {
	
	private static Log iObjLog = LogFactory.getLog(FormatoPDF.class);
	
	public String imprimirFormato(CuestionarioPantallaInterpretacionBean objCuestionarioPantallaInterpretacionBean) throws Exception {		
		Convertidor_FOP_PDF objManager = new Convertidor_FOP_PDF();		
		FormatoFOP objFileFOP = new FormatoFOP();
		String strfilefop = "";
	try {
			if(objCuestionarioPantallaInterpretacionBean.getSentidad().equals("PUEBLA")) {
				strfilefop = objFileFOP.crearFormato(objCuestionarioPantallaInterpretacionBean);
				strfilefop =  objManager.createDocument(strfilefop, objCuestionarioPantallaInterpretacionBean.getKordensucursal()  +  "-FormatoPuebla");
			} else {
				FormatoFOP_DF objFileFOPdf = new FormatoFOP_DF();
				strfilefop = objFileFOPdf.crearFormato(objCuestionarioPantallaInterpretacionBean);
				strfilefop =  objManager.createDocument(strfilefop, objCuestionarioPantallaInterpretacionBean.getKordensucursal()  +  "-FormatoDF");
			}		
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
