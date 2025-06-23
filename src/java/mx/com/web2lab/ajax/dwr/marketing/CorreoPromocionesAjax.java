package mx.com.web2lab.ajax.dwr.marketing;

import java.util.Date;
import java.util.List;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.ap.PacienteBean;
import mx.com.web2lab.backend.dao.ap.PacientesDao;
import mx.com.web2lab.backend.dao.mail.MailDao;
import mx.com.web2lab.backend.dao.marketing.MarketingDao;
import mx.com.web2lab.backend.hbm.om.ap.CPromocionMarketing;
import mx.com.web2lab.backend.hbm.om.ap.TPaciente;
import mx.com.web2lab.backend.hbm.om.ap.TPacienteMarketing;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CorreoPromocionesAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(CorreoPromocionesAjax.class);
	
	public CorreoPromocionesAjax(){
		iObjLog.debug("new: Generando nueva clase CorreoPromocionesAjax");
	}	
		
	public String envioPromocionEmail(int cPromocion,int cSucursal) throws Exception
	{
		String strRespuesta = "";
		List lstPacientesconEmail = null;
		PacienteBean objPaciente = null;
		PacientesDao objPacienteDAO = new PacientesDao();
		PacienteBean objPacienteBean = new PacienteBean();
		MailDao objMailDAO = new MailDao();
		CPromocionMarketing objPromocionMarketing = null;
		MarketingDao objMarketingDao = new MarketingDao();
		TPacienteMarketing objPacienteMarketing = null;
		TPaciente objPacienteHB = new TPaciente();
		iObjLog.debug("CorreoPromocionesAjax.envioPromocionEmail:Entrando..." + cPromocion);
		try
		{
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
			iObjLog.debug("CorreoPromocionesAjax.envioPromocionEmail:Validacion....");
			objPacienteBean.setSapmaterno("");
			objPacienteBean.setSappaterno("");
			objPacienteBean.setSnombre("");
			objPacienteBean.setCconvenio(0);
			objPacienteBean.setScorreoelectronico("@");
			objPacienteBean.setCsexo(cSucursal);
			lstPacientesconEmail = objPacienteDAO.buscarPacientes2(objPacienteBean,"");
			CPromocionMarketing objPromocionMarketingBuscar = new CPromocionMarketing();
			objPromocionMarketingBuscar.setCpromocionenviomarketing(new Integer(cPromocion));
			objPromocionMarketing = objMarketingDao.buscarPromocionMarketing(objPromocionMarketingBuscar);
			objPromocionMarketingBuscar = null;
			String strHTML = "";
			for(int inti=0;inti<lstPacientesconEmail.size();inti++) {
				objPaciente = (PacienteBean)lstPacientesconEmail.get(inti);
				if (objPaciente.getScorreoelectronico().trim().toString().length() > 5) {
					objPacienteMarketing = new TPacienteMarketing();
					objPacienteMarketing.setCpromocionmarketing(objPromocionMarketing);
					objPacienteMarketing.setDregistro(new Date());
						objPacienteHB.setKpaciente(objPaciente.getKpacientefundacion());
					objPacienteMarketing.setTpaciente(objPacienteHB);
					objPacienteMarketing.setUserId(1);
					objPacienteMarketing = objMarketingDao.guardarPacienteMarketing(objPacienteMarketing);
					strHTML = "<html><head><title>Olab</title></head><body><b>Buenas tardes " + objPaciente.getSnombre().toUpperCase() + " " + objPaciente.getSappaterno().toUpperCase() + " " + objPaciente.getSapmaterno().toUpperCase() + "</b>" + objPromocionMarketing.getShtmlpromocion() + "<center>Si deseas cancelar tu suscripci&oacute;n a esta lista, s&oacute;lo tienes que hacer clic en la siguiente liga:<a href='http://201.148.87.156/unsuscribe.asp?kcliente=" + objPacienteMarketing.getKpacientemarketing().intValue() + "'> Unsubscribe</a></center></body></html>";
					if (objMailDAO.sendEmailMarketing(objPromocionMarketing.getSsubject(), objPaciente.getScorreoelectronico(), strHTML, "") == -1) {
						objPacienteMarketing.setUserId(-1);
						objMarketingDao.actualizarPacienteMarketing(objPacienteMarketing);
					}
					objPacienteMarketing = null;
				}
			}
		}
		catch (Exception e){
			iObjLog.error("CorreoPromocionesAjax.envioPromocionEmail:ERROR",e);
			if(e instanceof AjaxDwrException){
				throw new AjaxDwrException(e);
			}else{
				throw new AjaxDwrException(0,"CorreoPromocionesAjax.envioPromocionEmail:Entrando:Ocurri&oacute; un error al enviar email para la promocion..." + cPromocion);
			}
		} finally {
			lstPacientesconEmail = null;
			objPaciente = null;
			objPacienteDAO = null;
			objPacienteBean = null;
			objMailDAO = null;
			objPromocionMarketing = null;
			objMarketingDao = null;
			objPacienteHB = null;
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
