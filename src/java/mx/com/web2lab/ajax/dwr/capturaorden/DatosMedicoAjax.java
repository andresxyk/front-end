package mx.com.web2lab.ajax.dwr.capturaorden;

import mx.com.web2lab.actions.seguridad.SeguridadUtil;
import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import mx.com.web2lab.backend.beans.comer.MedicoBean;
import mx.com.web2lab.backend.dao.comer.MedicosDao;
import mx.com.web2lab.backend.dao.tools.ConsultaOrdenesDao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineUser;



public class DatosMedicoAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(DatosMedicoAjax.class);	
	
	public MedicoBean buscarMedico(int intCodigoMedico) throws Exception 
	{
		iObjLog.debug("Entrando DatosMedicoAjax.buscarMedico:Entrando... ");		
		MedicoBean objMedico = new MedicoBean();
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");						
			objMedico.setKmedico(intCodigoMedico);
			objMedico.setCmedico(new Long(0));
			objMedico.setSnombre("");
			objMedico.setSappaterno("");
			objMedico.setSapmaterno("");			
			objMedico.setUtipooperacion(1);
			MedicosDao objDAOMedico = new MedicosDao();
			objMedico = (MedicoBean)objDAOMedico.buscarMedicos(objMedico).get(0);
			iObjLog.debug("Saliendo DatosMedicoAjax.buscarMedico:Saliendo...  ");
		} catch (Exception aObjException){
			iObjLog.error("Error DatosMedicoAjax.buscarMedico:Exception....", aObjException);
			objMedico = null;
			throw aObjException;
		}
		return objMedico;
	}	

	public MedicoBean buscarMedicoClave(int uMedico) throws Exception
	{
		iObjLog.debug("Entrando DatosMedicoAjax.buscarMedicoClave:Entrando... ");		
		MedicoBean objMedico = new MedicoBean();
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");						
			objMedico.setKmedico(0);
			objMedico.setCmedico(new Long(uMedico));
			objMedico.setSnombre("");
			objMedico.setSappaterno("");
			objMedico.setSapmaterno("");			
			objMedico.setUtipooperacion(1);
			MedicosDao objDAOMedico = new MedicosDao();
			objMedico = (MedicoBean)objDAOMedico.buscarMedicos(objMedico).get(0);
			iObjLog.debug("Saliendo DatosMedicoAjax.buscarMedicoClave:Saliendo...  ");
		} catch (Exception aObjException){
			iObjLog.error("Error DatosMedicoAjax.buscarMedicoClave:Exception....", aObjException);
			objMedico = null;
			throw aObjException;
		}
		return objMedico;
	}	
	
	public String buscarMedicoEspecialidad(int intEspecialidada) throws Exception
	{
		iObjLog.debug("Entrando DatosMedicoAjax.buscarMedicoEspecialidad:Entrando... ");		
		MedicoBean objMedico = new MedicoBean();
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");						
			objMedico.setCespecialidad(intEspecialidada);
			objMedico.setUtipooperacion(0);
			objMedico.setSorderby("bPF.cestadoregistro.cestadoregistro,sapellidopaterno,sapellidomaterno,snombre");
			MedicosDao objDAOMedico = new MedicosDao();
			iObjLog.debug("Saliendo DatosMedicoAjax.buscarMedicoEspecialidad:Saliendo...  ");			
			return this.showMedico(objDAOMedico.buscarMedicos(objMedico),2000); 						
		} catch (Exception aObjException){
			iObjLog.error("Error DatosMedicoAjax.buscarMedicoEspecialidad:Exception....", aObjException);
			objMedico = null;
			throw aObjException;
		}
	}			
	
	public String buscarMedicoZona(int intZona) throws Exception
	{
		iObjLog.debug("Entrando DatosMedicoAjax.buscarMedicoZona:Entrando... ");		
		MedicoBean objMedico = new MedicoBean();
		MedicosDao objDAOMedico = new MedicosDao();
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");						
			objMedico.setCzona(intZona);
			objMedico.setUtipooperacion(0);
			iObjLog.debug("Saliendo DatosMedicoAjax.buscarMedicoZona:Saliendo...  ");			
			objMedico.setSorderby("bPF.cestadoregistro.cestadoregistro,sapellidopaterno,sapellidomaterno,snombre");
			return this.showMedico(objDAOMedico.buscarMedicos(objMedico),2000); 						
		} catch (Exception aObjException){
			iObjLog.error("Error DatosMedicoAjax.buscarMedicoZona:Exception....", aObjException);
			objMedico = null;
			throw aObjException;
		} finally {
			objMedico = null;
			objDAOMedico = null;
    	}
	}		
	
	public String consultaMedicosGrid(int intCodigoMedico,String strAP,String strAM,String strNombres) throws Exception	
	{
		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");		
		iObjLog.debug("Entrando a DatosMedicoAjax.consultaMedicosGrid:Entrando...Parametros... " + strAP + " " + strAM + " " + strNombres);
		if (strNombres.trim().length() >= 4 || strAP.trim().length() >= 4 || strAM.trim().length() >= 4 || intCodigoMedico > 0) {
			MedicoBean objMedico = new MedicoBean();
			objMedico.setCmedico(new Long(intCodigoMedico));
			objMedico.setSnombre(strNombres.trim() + "");
			objMedico.setSappaterno(strAP.trim() + "");
			objMedico.setSapmaterno(strAM.trim() + "");
			objMedico.setUtipooperacion(1);
			MedicosDao objDAOMedico = new MedicosDao();
			iObjLog.debug("Entrando a DatosMedicoAjax.consultaMedicosGrid:Entrando... " + objMedico.getSappaterno() + " " + objMedico.getSapmaterno() + " " + objMedico.getSnombre());
			objMedico.setSorderby("bregistrado desc,sapellidopaterno,sapellidomaterno,snombre");
			return this.showMedico(objDAOMedico.buscarMedicos(objMedico),50); 
		} else {
			return "";
		}
	}
	
	public MedicoBean actualizaMedico(MedicoBean objMedicoBean) throws Exception
	{
		MedicosDao objDAOMedico = new MedicosDao();
		iObjLog.debug("Entrando DatosMedicoAjax.actualizaMedico:Entrando... " + objMedicoBean.getCmedico().intValue());		
		try {			
				if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
				objMedicoBean = objDAOMedico.setMedicoActualizacion(objMedicoBean);
			iObjLog.debug("Saliendo DatosMedicoAjax.actualizaMedico:Saliendo...  " + objMedicoBean.toString());
    	}catch (Exception aObjException){
    	    iObjLog.error("Error DatosMedicoAjax.actualizaMedico:Exception....", aObjException);
    	    objMedicoBean = null;
    	    throw aObjException;
		} finally {
			objDAOMedico = null;
    	}
		return objMedicoBean;
	}	
	
	public MedicoBean altaMedico(MedicoBean objMedicoBean) throws Exception
	{
		MedicosDao objDAOMedico = new MedicosDao();
		iObjLog.debug("Entrando DatosMedicoAjax.altaMedico:Entrando... " + objMedicoBean.getCmedico().intValue());		
		try {			
				if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
				objMedicoBean = objDAOMedico.setMedicoAlta(objMedicoBean);
			iObjLog.debug("Saliendo DatosMedicoAjax.altaMedico:Saliendo...  " + objMedicoBean.toString());
    	}catch (Exception aObjException){
    	    iObjLog.error("Error DatosMedicoAjax.altaMedico:Exception....", aObjException);
    	    objMedicoBean = null;
    	    throw aObjException;
		} finally {
			objDAOMedico = null;
    	}
		return objMedicoBean;
	}

	public String actualizaCorreoElectronicoMedico(int cMedico, String strCorreoElectronico) throws Exception
	{
		iObjLog.debug("Entrando DatosMedicoAjax.actualizaCorreoElectronicoMedico:Entrando... cMedico " + cMedico + " CorreoElectronico " + strCorreoElectronico);		
		MedicosDao objDAOMedico = new MedicosDao();
		String strReturn = "Error en la actualizacion del Correo Electronico";
		try {			
				if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
				objDAOMedico.setMedicoActualizacionCorreoElectronico(cMedico,strCorreoElectronico);
				iObjLog.debug("Saliendo DatosMedicoAjax.actualizaCorreoElectronicoMedico:Saliendo... cMedico " + cMedico + " CorreoElectronico " + strCorreoElectronico);
				strReturn = "Exito en la actualizacion del Correo Electronico";
    	}catch (Exception aObjException){
    	    iObjLog.error("Error DatosMedicoAjax.actualizaCorreoElectronicoMedico:Exception....", aObjException);
    	    objDAOMedico = null;
    	    throw aObjException;
		} finally {
			objDAOMedico = null;
    	}    	
		return strReturn;
	}	
	
	public MedicoBean cambiarMedicoOrden(int kOrdenSucursal,int cClaveMedicoOld, int cClaveMedicoNew, int cUser) throws Exception
	{
		MedicoBean objMedico = new MedicoBean();
		String strReturn = "";
		iObjLog.debug("Entrando DatosMedicoAjax.cambiarMedicoOrden:Entrando...KOrdenSucursal " + kOrdenSucursal + " ClaveMedicoAnterior " + cClaveMedicoOld + " ClaveMedicoActual " + cClaveMedicoNew + " Usuario " +cUser);		
		try {			
				if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
				MedicosDao objDAOMedico = new MedicosDao();
				objDAOMedico.ChangeMedicoOrden(kOrdenSucursal,cClaveMedicoNew,cUser);
				objMedico.setKmedico(0);
				objMedico.setCmedico(new Long(cClaveMedicoNew));
				objMedico.setSnombre("");
				objMedico.setSappaterno("");
				objMedico.setSapmaterno("");			
				objMedico.setUtipooperacion(1);
				objMedico = (MedicoBean)objDAOMedico.buscarMedicos(objMedico).get(0);
			iObjLog.debug("Saliendo DatosMedicoAjax.cambiarMedicoOrden:Saliendo...  " + strReturn.toString());
    	}catch (Exception aObjException){
    	    iObjLog.error("Error DatosMedicoAjax.cambiarMedicoOrden:Exception....", aObjException);
    	    throw aObjException;
    	}
		return objMedico;
	}	
	
	public MedicoBean altaMedicoBasico(String strNombre, String strApellidoPaterno, String strApellidoMaterno, String strCorreoElectronico) throws Exception
	{
		MedicoBean objMedicoBean = new MedicoBean();
		iObjLog.debug("Entrando DatosMedicoAjax.actualizaMedico:Entrando... " + objMedicoBean.getCmedico().intValue());		
		try {			
				if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");
				objMedicoBean.setCmedico(new Long(0));
				objMedicoBean.setSnombre(strNombre.trim() + "");
				objMedicoBean.setSappaterno(strApellidoPaterno.trim() + "");
				objMedicoBean.setSapmaterno(strApellidoMaterno.trim() + "");
				objMedicoBean.setScorreoelectro(strCorreoElectronico.trim() + "");
				objMedicoBean.setUestadomedico(24);
				MedicosDao objDAOMedico = new MedicosDao();
				objMedicoBean = objDAOMedico.setMedicoAltaBasico(objMedicoBean);
			iObjLog.debug("Saliendo DatosMedicoAjax.actualizaMedico:Saliendo...  " + objMedicoBean.toString());
    	}catch (Exception aObjException){
			objMedicoBean.setSnombre("");
			objMedicoBean.setSappaterno("El medico ya existe, por favor buscalo ...");
			objMedicoBean.setSapmaterno("");
			objMedicoBean.setCmedico(new Long(-1));
    	    iObjLog.error("Error DatosMedicoAjax.actualizaMedico:Exception....", aObjException);
    	    throw aObjException;
    	}
		return objMedicoBean;
	}	
	
	public String buscarMedicosOrdenes(MedicoBean objMedicoBean,String strFechaInicio, String strFechaTermino) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarMedicosOrdenes:Entrando... ");		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");						
			ConsultaOrdenesDao objDAOCliente = new ConsultaOrdenesDao();
			strReturn = objDAOCliente.buscarOrdenesMedicos(objMedicoBean,strFechaInicio,strFechaTermino);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarMedicosOrdenes:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarMedicosOrdenes:Exception....", aObjException);
			objMedicoBean = null;
			throw aObjException;
		}
		return strReturn;
	}	
	
	private String showMedico(List lstMedicos,int intShowRows) throws Exception
	{		
		MedicoBean objMedico = new MedicoBean();
		String strReturn = "";	
		String strContadorHelp = "";
		int intMedicos = 0;
		String strHeaderShowRows = ""; 
		String strHeaderZona = ""; 
		iObjLog.debug("Entrando a DatosPacienteAjax.consultaMedicosGrid:Entrando... ");
		try {
			 if (lstMedicos != null) {
				 intMedicos = lstMedicos.size();
			 }
			 if (intMedicos > 50 ) {
				 strContadorHelp = ", PERO SOLO SE PRESENTAN " + intShowRows + " (PON MAS DATOS PARA FILTRAR) ";
			 }
			 if (50 == intShowRows ) {
				 strHeaderShowRows = "<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
									 "	<b><font color='black'>Direccion " + 
									 "	</font></b>" +
									 "</th>";
				 strHeaderZona = "";
			 } else {
				 strHeaderShowRows = "<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
									 "	<b><font color='black'>Estado " + 
									 "	</font></b>" +
									 "</th>";
				 strHeaderZona = "<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
				 				 "	<b><font color='black'>Zona" + 
								 "	</font></b>" +
								 "</th>";
			 }
			 
			 strReturn = ("<table border='0' align='center' style='width: 100%'>" + 
					 		"<tr>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Total de M&eacute;dicos " + intMedicos + strContadorHelp +
								"	</font></b>" +
								"</th>"  + 
							"</tr>" +
						 "</table>" +	
						 "<table border='0' align='center' style='width: 883px' class='tabla'>" +
							"<tr>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>#" + 
								"	</font></b>" +
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Clave" + 
								"	</font></b>" +
								"</th>" +  strHeaderZona +
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Apellido Paterno" + 
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Apellido Materno" + 
								"	</font></b>" +
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Nombre(s)" + 
								"	</font></b>" +
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Especialidad" + 
								"	</font></b>" +
								"</th>" + strHeaderShowRows +
							"</tr>");			    
			 if (lstMedicos != null) {
				int y = 0; 
				for (int i = 0; i < lstMedicos.size() ; i++)
				{
					objMedico = (MedicoBean)lstMedicos.get(i);						
					if (y >= intShowRows) {
						break;
					}					
					if (50 == intShowRows ) {
						strHeaderShowRows = objMedico.getSdireccion();
						strHeaderZona = "";
					} else {
						strHeaderShowRows = objMedico.getSestadomedico();
						strHeaderZona = "<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:medicoAceptado(" + objMedico.getKmedico() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objMedico.getCzona() +
										"</a></td>";
					}					
					y = i + 1;
					strReturn += ("<tr>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:medicoAceptado(" + objMedico.getKmedico() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											y + 
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:medicoAceptado(" + objMedico.getKmedico() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objMedico.getCmedico() + 
										"</a></td>" + strHeaderZona +
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:medicoAceptado(" + objMedico.getKmedico() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objMedico.getSappaterno()+
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:medicoAceptado(" + objMedico.getKmedico() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objMedico.getSapmaterno() +
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:medicoAceptado(" + objMedico.getKmedico() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objMedico.getSnombre() +
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:medicoAceptado(" + objMedico.getKmedico() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objMedico.getSespecialidad() +
										"</a></td>" + 
										"<td align='left' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:medicoAceptado(" + objMedico.getKmedico() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											strHeaderShowRows +
										"</a></td>" + 
									 "</tr>");
				}
			}
			strReturn += ("</table>");
			return strReturn; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosMedicoAjax.consultaMedicosGrid:Exception....", aObjException);
    	    throw aObjException;
		} 
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
    	
	public int validaAutetificacion(String strUsuario,String strPassword) throws Exception
	{
		int intReturn = 0;
		iObjLog.debug("Entrando DatosMedicoAjax.validaAutetificacion:Entrando... ");		
		try {			
    		User objUsuario = TurbineSecurity.getAuthenticatedUser(strUsuario.trim(), strPassword.trim());
    		if(objUsuario!=null) {
                TurbineUser objTurbineUser = getUserByName(strUsuario.trim());
                intReturn = objTurbineUser.getUserId();    						
    		} else {
    			intReturn = 0;    			
    		}
			iObjLog.debug("Saliendo DatosMedicoAjax.validaAutetificacion:Saliendo...  " + intReturn);
		} catch (Exception aObjException){
			iObjLog.error("Error DatosMedicoAjax.validaAutetificacion:Exception....", aObjException);
			throw aObjException;
		}
		return intReturn;
	}	
	
    public TurbineUser getUserByName(String strName) throws Exception {
        return SeguridadUtil.getUserByName(strName);
    }	
}
