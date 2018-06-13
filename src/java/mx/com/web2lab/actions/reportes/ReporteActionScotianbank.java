package mx.com.web2lab.actions.reportes;

import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import mx.com.web2lab.actions.SecureAction;
import mx.com.web2lab.reportes.GeneraReporte;
import mx.com.web2lab.util.Formatos;
import mx.com.web2lab.util.GenericDAO;
import mx.com.web2lab.backend.dao.ap.PagosDao;
import mx.com.web2lab.backend.hbm.om.ap.TCorteCaja;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

public class ReporteActionScotianbank extends SecureAction {

    private static Log iObjLog = LogFactory.getLog(ReporteActionScotianbank.class);

    public ReporteActionScotianbank() {
    }

    public void doDatos(RunData aObjDatos, Context aObjContexto)
    throws Exception {
    	Connection objCon = null;
		try {
			iObjLog.debug("Entrando a do Datos ");
			GenericDAO objConn = new GenericDAO();
			objCon = objConn.getConnection();                			        									
			String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");
			String kordensucursal= aObjDatos.getParameters().getString("kordensucursal");
		    String strNomArchivo = "DatosOrden" + Calendar.getInstance().getTimeInMillis() + ".pdf";
		    Map params = new HashMap();
            iObjLog.debug("Procesando la sucursal " +kordensucursal);
		    params.put("kordensucursal",kordensucursal);    
            Map subreportes = new HashMap();            
		    new GeneraReporte().generaReportePdf("ReporteScotianbank.jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);
		    iObjLog.debug("Saliendo doDatos" +kordensucursal);
		}catch (Exception aError) {
		    iObjLog.error("Error en ReporteAction.doDatosScontian",aError);
		    aObjContexto.put("resultado", aError);
		    throw aError;
		} finally {
			if(objCon!=null)objCon.close();        	
		}
    }              
}