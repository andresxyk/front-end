package mx.com.web2lab.ajax.dwr.laboratorio;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.dao.ap.ExamenesDao;
import mx.com.web2lab.backend.dao.laboratorio.TomaMuestrasDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TomaMuestraAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(TomaMuestraAjax.class);
	
	public TomaMuestraAjax(){
		iObjLog.debug("new: Generando nueva clase FacturacionDatosAjax");
	}	
			
	public String showOrdenesTomaMuestra(int cSucursal, int uSalaToma, int kOrdenSucursalORuConsecutivo) throws Exception	
	{
		TomaMuestrasDao objDaoTomaMuestras = new TomaMuestrasDao();
		String strReturn = "";
		iObjLog.debug("Entrando a LaboratorioAjax.showOrdenesTomaMuestra:Entrando... " + cSucursal);
		try {						
			strReturn = objDaoTomaMuestras.getOrdenesTomaMuestra(cSucursal,uSalaToma,kOrdenSucursalORuConsecutivo);
			iObjLog.debug("Saliendo a LaboratorioAjax.showOrdenesTomaMuestra:Numero de Ordenes... ");
			return strReturn; 
		}catch (Exception aObjException){
    	    iObjLog.error("LaboratorioAjax.showOrdenesTomaMuestra:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objDaoTomaMuestras = null;
		}
	}		

	public String[] inicioTomaMuestra(String kOrdenSucursal, int cExamen, String sUsuario, int uSalaToma) throws Exception	
	{
		TomaMuestrasDao objDaoTomaMuestras = new TomaMuestrasDao();
		ExamenesDao objDaoExamenes = new ExamenesDao();
		String[] strReturn = {"",""};
		String strHelp = "";
		iObjLog.debug("Entrando a LaboratorioAjax.inicioTomaMuestra:Entrando... " + kOrdenSucursal);
		try {
            if (cExamen == 0) {			
				strHelp = objDaoTomaMuestras.inicioTomaMuestra(kOrdenSucursal,sUsuario,"",uSalaToma);
				if (strHelp.trim().length() < 1) {			
//					if (bolLaboratorio) {
						strReturn = objDaoExamenes.imprimeEtiquetasZPL(kOrdenSucursal + "",0," ce.uestaciontomatlalpan = " + uSalaToma + " AND ");
//					} else {
//						strReturn = objDaoExamenes.imprimeEtiquetasZPL(kOrdenSucursal + "",0," ce.ctipocomercial = 2 AND ");
//					}
				} else {
					strReturn[0] = "";
					strReturn[1] = strHelp.trim();
				}
            } else {
				strHelp = objDaoTomaMuestras.inicioTomaMuestra(kOrdenSucursal,sUsuario," and cexamen = " + cExamen, uSalaToma);
				if (strHelp.trim().length() < 1) {			
					strReturn = objDaoExamenes.imprimeEtiquetasZPL(kOrdenSucursal + "",0," ce.cexamen = " + cExamen + " AND ");
				} else {
					strReturn[0] = "";
					strReturn[1] = strHelp.trim();
				}
            }
			iObjLog.debug("Saliendo a LaboratorioAjax.inicioTomaMuestra:Numero de Ordenes...1 " + strReturn[0]);
			iObjLog.debug("Saliendo a LaboratorioAjax.inicioTomaMuestra:Numero de Ordenes...2 " + strReturn[1]);
		}catch (Exception aObjException){
    	    iObjLog.error("LaboratorioAjax.inicioTomaMuestra:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objDaoTomaMuestras = null;
			objDaoExamenes = null;
		}
		return strReturn;
	}		

	public void cambioFechaTomaMuestra(int kOrdenSucursal, int cExamen, String sUsuario, String strNuevaFecha) throws Exception	
	{
		TomaMuestrasDao objDaoTomaMuestras = new TomaMuestrasDao();
		iObjLog.debug("Entrando a LaboratorioAjax.cambioFechaTomaMuestra:Entrando... " + kOrdenSucursal);
		try {
			objDaoTomaMuestras.cambioFechaTomaMuestra(kOrdenSucursal, sUsuario, cExamen + "", strNuevaFecha);
			iObjLog.debug("Saliendo a LaboratorioAjax.cambioFechaTomaMuestra:... ");
		}catch (Exception aObjException){
    	    iObjLog.error("LaboratorioAjax.cambioFechaTomaMuestra:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objDaoTomaMuestras = null;
		}
	}		

	public String tomaMuestraPendiente(int kOrdenExamenSucursal, String sUsuario) throws Exception	
	{
		TomaMuestrasDao objDaoTomaMuestras = new TomaMuestrasDao();
		iObjLog.debug("Entrando a LaboratorioAjax.tomaMuestraPendiente:Entrando... " + kOrdenExamenSucursal);
		try {
			return objDaoTomaMuestras.tomaMuestraPendiente(kOrdenExamenSucursal,sUsuario,true);
		}catch (Exception aObjException){
    	    iObjLog.error("LaboratorioAjax.tomaMuestraPendiente:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objDaoTomaMuestras = null;
		}
	}		

	public String tomarHoyMuestraPendiente(int kOrdenExamenSucursal, String sUsuario) throws Exception	
	{
		TomaMuestrasDao objDaoTomaMuestras = new TomaMuestrasDao();
		iObjLog.debug("Entrando a LaboratorioAjax.tomarHoyMuestraPendiente:Entrando... " + kOrdenExamenSucursal);
		try {
			return objDaoTomaMuestras.tomaMuestraPendiente(kOrdenExamenSucursal,sUsuario,false);
		}catch (Exception aObjException){
    	    iObjLog.error("LaboratorioAjax.tomarHoyMuestraPendiente:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objDaoTomaMuestras = null;
		}
	}		
	
	
	public String terminoTomaMuestra(int kOrdenSucursal, String sUsuario, int uSalaToma) throws Exception	
	{
		TomaMuestrasDao objDaoTomaMuestras = new TomaMuestrasDao();
		iObjLog.debug("Entrando a LaboratorioAjax.terminoTomaMuestra:Entrando... " + kOrdenSucursal);
		try {
			objDaoTomaMuestras.terminoTomaMuestra(kOrdenSucursal,sUsuario, uSalaToma);
			iObjLog.debug("Saliendo a LaboratorioAjax.terminoTomaMuestra:Numero de Ordenes... ");
		}catch (Exception aObjException){
    	    iObjLog.error("LaboratorioAjax.terminoTomaMuestra:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objDaoTomaMuestras = null;
		}
		return "";
	}		
	
	public String[] imprimeEtiquetasExamenesLaboratorio(int kAdmision) throws Exception	
	{
		iObjLog.debug("Entrando a DatosExamenAjax.imprimeEtiquetasExamenesLaboratorio:Entrando... ");
		ExamenesDao objDaoExamenes = new ExamenesDao();
		return objDaoExamenes.imprimeEtiquetasZPL(String.valueOf(kAdmision),0," ce.ctipocomercial <> 2 AND ");
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
