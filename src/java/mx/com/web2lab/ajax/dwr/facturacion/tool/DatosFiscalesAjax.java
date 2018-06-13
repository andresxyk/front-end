package mx.com.web2lab.ajax.dwr.facturacion.tool;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.facturacion.DatosFiscalesBean;
import mx.com.web2lab.backend.dao.facturacion.tool.DatosFiscalesDao;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;

public class DatosFiscalesAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(DatosFiscalesAjax.class);
	
	public DatosFiscalesAjax(){
		iObjLog.debug("new: Generando nueva clase DatosFiscalesAjax");
	}

    /* Create 21/03/2013 Author OMRR */
	public DatosFiscalesBean crearDatosFiscalesFacturacionElectronicaInternet(DatosFiscalesBean objDatosFiscalesBean, int intUser) throws Exception
	{
		DatosFiscalesBean objDatosFiscalesBeanBuscar = new DatosFiscalesBean(); 
		iObjLog.debug("Entrando a DatosFiscalesAjax.crearDatosFiscalesFacturacionElectronicaInternet:Entrando...  ");
		try {
			objDatosFiscalesBeanBuscar.setBlike(true);
			objDatosFiscalesBeanBuscar.setStrRFC(objDatosFiscalesBean.getStrRFC());
			objDatosFiscalesBeanBuscar = this.getOneDatoFiscal(objDatosFiscalesBeanBuscar);
			if (objDatosFiscalesBeanBuscar != null) {
				if (objDatosFiscalesBeanBuscar.getStrRFC() 			== objDatosFiscalesBean.getStrRFC() 		&&
					objDatosFiscalesBeanBuscar.getStrRazonSocial() 	== objDatosFiscalesBean.getStrRazonSocial() &&
					objDatosFiscalesBeanBuscar.getStrDireccion() 	== objDatosFiscalesBean.getStrDireccion()) 	{					
					objDatosFiscalesBean = objDatosFiscalesBeanBuscar;
				} else {
					objDatosFiscalesBeanBuscar = null;
				}
			}			
			if (objDatosFiscalesBeanBuscar == null) {
				objDatosFiscalesBean = this.crearDatosFiscales(objDatosFiscalesBean, intUser);
				objDatosFiscalesBean.setUnuevodatofiscal(1);
			}
			iObjLog.debug("Saliendo a DatosFiscalesAjax.crearDatosFiscalesFacturacionElectronicaInternet:Saliendo...  ");
		}catch (Exception aObjException){
    	    iObjLog.error("DatosFiscalesAjax.crearDatosFiscalesFacturacionElectronicaInternet:ErrorException....", aObjException);
    	    throw aObjException;
    	}
    	return objDatosFiscalesBean;
	}	
	
	public DatosFiscalesBean crearDatosFiscales(DatosFiscalesBean objDatosFiscalesBean, int intUser) throws Exception
	{
		DatosFiscalesDao objDatosFiscalesDAO = new DatosFiscalesDao();
		iObjLog.debug("Entrando a DatosFiscalesAjax.crearDatosFiscales:Entrando...  ");
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
			if (objDatosFiscalesBean.getcCodigoPostal() == 0) {
				objDatosFiscalesBean.setcCodigoPostal(objDatosFiscalesDAO.newDatosSepomex(objDatosFiscalesBean));				
			}
			objDatosFiscalesBean = objDatosFiscalesDAO.newDatosFiscales(objDatosFiscalesBean, intUser);						
			objDatosFiscalesDAO = null;
			iObjLog.debug("Saliendo a DatosFiscalesAjax.crearDatosFiscales:Saliendo...  ");
		}catch (TorqueException aObjException){
    	    iObjLog.error("DatosFiscalesAjax.crearDatosFiscales:ErrorException....", aObjException);
    	    throw aObjException;
    	}
    	return objDatosFiscalesBean;
	}	

	public DatosFiscalesBean getOneDatoFiscal(DatosFiscalesBean objDatosFiscalesBean) throws Exception
	{
		DatosFiscalesDao objDatosFiscalesDAO = new DatosFiscalesDao();
		iObjLog.debug("Entrando a DatosFiscalesAjax.getOneDatoFiscal:Entrando...  ");
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
			iObjLog.debug("Saliendo a DatosFiscalesAjax.getOneDatoFiscal:Saliendo...  ");
			return objDatosFiscalesDAO.getOneDatoFiscal(objDatosFiscalesBean);						
		}catch (TorqueException aObjException){
    	    iObjLog.error("DatosFiscalesAjax.getOneDatoFiscal:ErrorException....", aObjException);
    	    throw aObjException;
    	} finally {
    		objDatosFiscalesDAO = null;
    	}
	}	
		
	public String showDatosFiscales(DatosFiscalesBean objDatosFiscalesBean) throws Exception
	{
		DatosFiscalesDao objDatosFiscalesDAO = new DatosFiscalesDao();
		String strReturn = "";
		iObjLog.debug("Entrando a DatosFiscalesAjax.showDatosFiscales:Entrando...  ");
		try {
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
			strReturn = objDatosFiscalesDAO.showGridDatoFiscal(objDatosFiscalesBean);						
			objDatosFiscalesDAO = null;
			iObjLog.debug("Saliendo a DatosFiscalesAjax.showDatosFiscales:Saliendo...  ");
		}catch (TorqueException aObjException){
    	    iObjLog.error("DatosFiscalesAjax.showDatosFiscales:ErrorException....", aObjException);
    	    throw aObjException;
    	}
    	return strReturn;
	}	
		
    /**
     * Metodo que verifica que exista una sesion valida 
     * @return
     * @throws Exception
     */
    public boolean isSesionValida() {
    	boolean valida = false;
    	try{
    		valida = super.isSesionValida(false);
    	}catch(Exception e ){
    		iObjLog.error("isSesionValida:No existe una sesion valida para el usuario");
    		return valida;
    	}
    	return valida;
    }
    
}


