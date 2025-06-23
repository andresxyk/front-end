package mx.com.web2lab.ajax.dwr.tools;

import mx.com.web2lab.actions.seguridad.SeguridadUtil;
import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.beans.tools.CodigoPostalBean;
import mx.com.web2lab.backend.util.catalogo.CodigoPostalUtil;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineUser;

public class ToolsAjax extends AjaxAction {

	private static Log iObjLog = LogFactory.getLog(ToolsAjax.class);
	
	public ToolsAjax(){
	}
				

	public String consultaDireccionGrid(String txtColonia,String txtDelegacionMunicipio,String txtCodigoPostal,String txtEstado,String txtNameForm) throws Exception	
	{
		iObjLog.debug("Entrando a DatosPacienteAjax.consultaDireccionGrid:Entrando...Parametros... " + txtColonia + " " + txtDelegacionMunicipio + " " + txtCodigoPostal + " " + txtNameForm);		
		CodigoPostalBean objCodigoPostal = new CodigoPostalBean();
		String strReturn = "";;
		List lstCodigosPostales = new ArrayList();
		CodigoPostalUtil objCodigoPostalUtil = new CodigoPostalUtil();
		try {
    		if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesion valida ...");			
			if (txtColonia.trim().length() > 3 || txtDelegacionMunicipio.trim().length() > 3 || txtCodigoPostal.trim().length() > 3) {		
				objCodigoPostal.setKCodigo(0);
				objCodigoPostal.setScolonia(txtColonia.trim() + "");
				objCodigoPostal.setSdelegacionmunicipio(txtDelegacionMunicipio.trim() + "");
				objCodigoPostal.setSestado(txtEstado.trim() + "");
				objCodigoPostal.setScodigopostal(txtCodigoPostal.trim() + "");
				objCodigoPostal.setSciudad("");
				lstCodigosPostales = objCodigoPostalUtil.getCodigosPostales(objCodigoPostal);
				int intRegistros = 0;
				if (lstCodigosPostales != null) {
					intRegistros = lstCodigosPostales.size();
				}
				strReturn = ("<table border='0' align='center' style='width: 883px' class='tabla'>" + 
								"<tr>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Total de Registros " + intRegistros +
								"	</font></b>" +
								"</th>"  + 
								"</tr>" +
							 "</table>" +	
							 "<table border='0' align='center' style='width: 883px' class='tabla'>" +						
								"<tr>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Colonia" + 
								"	</font></b>" +
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Delegacion o Municipio" + 
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>C&oacute;digo Postal" + 
								"	</font></b>" +
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Estado" + 
								"	</font></b>" +
								"</th>" + 
								"</tr>");			    
				
				 if (lstCodigosPostales != null) {
					String strDireccion = "";
					int strEstado = 0;
					if (lstCodigosPostales.size() != 0) {
						for(int inti=0;inti<lstCodigosPostales.size();inti++){	
							objCodigoPostal = (CodigoPostalBean)lstCodigosPostales.get(inti);
							strEstado = (objCodigoPostal.getCasentamiento() - 1);					
							strDireccion = objCodigoPostal.getScolonia() + "-" + objCodigoPostal.getSdelegacionmunicipio() + "-" + objCodigoPostal.getScodigopostal() + "-" + objCodigoPostal.getSestado() + "-" + objCodigoPostal.getKCodigo();
							strReturn += ("<tr>" + 								
										 		"<input type='hidden' id='hdnGridDireccion" + inti + "' value='" +  strDireccion + "'>" +								 		
							 					"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:registroDireccion(window.document." + txtNameForm + ".hdnGridDireccion" + inti + ",window.document." + txtNameForm + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
							 						objCodigoPostal.getScolonia() + 
										  		"</a></td>" + 
							 					"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:registroDireccion(window.document." + txtNameForm + ".hdnGridDireccion" + inti + ",window.document." + txtNameForm + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
							 						objCodigoPostal.getSdelegacionmunicipio() + 
											  	"</a></td>" + 
							 					"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:registroDireccion(window.document." + txtNameForm + ".hdnGridDireccion" + inti + ",window.document." + txtNameForm + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
							 						objCodigoPostal.getScodigopostal()+ 
											  	"</a></td>" + 
							 					"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:registroDireccion(window.document." + txtNameForm + ".hdnGridDireccion" + inti + ",window.document." + txtNameForm + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
							 					    objCodigoPostal.getSestado() + 
											  	"</a></td>" + 
								          "</tr>");					
						}				 
					}
				}
				strReturn += ("</table>");
			}
			return strReturn; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosPacienteAjax.consultaDireccionGrid:Exception....", aObjException);
    	    throw aObjException;
    	}
	}	
	
	public int validaAutetificacion(String strUsuario,String strPassword) throws Exception
	{
		int intReturn = 0;
		iObjLog.debug("Entrando DatosPacienteAjax.validaAutetificacion:Entrando... ");		
		try {			
    		User objUsuario = TurbineSecurity.getAuthenticatedUser(strUsuario.trim(), strPassword.trim());
    		if(objUsuario!=null) {
                TurbineUser objTurbineUser = getUserByName(strUsuario.trim());
                intReturn = objTurbineUser.getUserId();    						
    		} else {
    			intReturn = 0;    			
    		}
			iObjLog.debug("Saliendo DatosPacienteAjax.validaAutetificacion:Saliendo...  " + intReturn);
		} catch (Exception aObjException){
			iObjLog.error("Error DatosPacienteAjax.validaAutetificacion:Exception....", aObjException);
			throw aObjException;
		}
		return intReturn;
	}	
	
    public TurbineUser getUserByName(String strName) throws Exception {
        return SeguridadUtil.getUserByName(strName);
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
