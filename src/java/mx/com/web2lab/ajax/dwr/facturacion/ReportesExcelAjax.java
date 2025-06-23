package mx.com.web2lab.ajax.dwr.facturacion;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.ReportesExcelDao;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReportesExcelAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(ReportesExcelAjax.class);
	
	public ReportesExcelAjax(){
		iObjLog.debug("new: Generando nueva clase ReportesExcelAjax");
	}	
		
	public String generarReporte(int cConvenio,int cReporte) throws Exception
	{
		String strRespuesta = "";
		iObjLog.debug("ReportesExcelAjax.generarReporte:Entrando..." + cConvenio + " " + cReporte);
		try
		{
			ReportesExcelDao objReportesExcelDao = new ReportesExcelDao();		
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
    		strRespuesta = objReportesExcelDao.generarReporte(cConvenio, cReporte);
		}
		catch (Exception e){
			iObjLog.error("ReportesExcelAjax.generarReporte:ERROR",e);
			if(e instanceof AjaxDwrException){
				throw new AjaxDwrException(e);
			}else{
				throw new AjaxDwrException(0,"ReportesExcelAjax.generarReporte:Ocurri&oacute; un error al validar la ..." + cConvenio + " " + cReporte);
			}
		}		
		return strRespuesta;
	}
	
    /**
     * Metodo que verifica que exista una sesion valida 
     * @return
     * @throws Exception
     */
    public boolean isSesionValida() {
    	boolean valida = false;
    	try{
    		valida = super.isSesionValida(true);
    	}catch(Exception e ){
    		iObjLog.error("isSesionValida:No existe una sesion valida para el usuario");
    		return valida;
    	}
    	return valida;
    }    
}
