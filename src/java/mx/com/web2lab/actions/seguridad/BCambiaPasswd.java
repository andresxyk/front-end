package mx.com.web2lab.actions.seguridad;

import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import mx.com.web2lab.actions.SecureAction;
import mx.com.web2lab.util.SCambiaPass;
import mx.com.web2lab.backend.util.beans.sistema.TurbineGroup;
import mx.com.web2lab.backend.util.catalogos.ValoresCatalogo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;


public class BCambiaPasswd extends SecureAction implements Serializable{
	
	private static Log iObjLog = LogFactory.getLog(BCambiaPasswd.class);
	
	public void doPerform(RunData aObjData, Context aObjContext) throws Exception{
		LoginAction objLogAction = new LoginAction();
	    HttpSession objSesion = aObjData.getSession();
		iObjLog.debug("Entro al doPerform");
		SCambiaPass objCambiaPass = new SCambiaPass();
		String strTipSistema = null;
		String rutaSistema = null;
		TurbineGroup objTurbineGroup = null;
		String strPath = aObjData.getContextPath().trim()+"/servlet/template/";   
	    String strLoginName = aObjData.getParameters().getString("hdnLoginName");
		String strPasswd = aObjData.getParameters().getString("txtConfContras");
		iObjLog.debug(" - + - + - + - + - + - + - +datos recuperados para cambiar el password ");
		iObjLog.debug(" datos password "+ strPasswd +"  usuario  "+strLoginName);
		boolean bolCambPass = false;
		if(strLoginName != null && !strLoginName.equals("")){
			bolCambPass = objCambiaPass.setActuPasswd(strLoginName, strPasswd);
			iObjLog.debug(" valor del cambio de passwd "+ bolCambPass); 
		}
		if(bolCambPass){
			LoginAction login = new LoginAction();
			User objUsuario = TurbineSecurity.getAuthenticatedUser(strLoginName, strPasswd);
			List objRoles = login.getRoleByUser(objUsuario.getName());
			List objTurbineGroups = objLogAction.getGroupByUser(objUsuario.getName());
			if(objTurbineGroups.size() > 0  ){
				objTurbineGroup = (TurbineGroup)objTurbineGroups.get(0);
				strTipSistema = login.getSistema( objTurbineGroup );
			}
			if(objTurbineGroups != null && !objTurbineGroups.isEmpty() && objTurbineGroups.size() > 1){
				iObjLog.debug("Entro al if de mas de un grupo");
				if(strTipSistema.equals("LABORATORIOS")){
					aObjContext.put("existeLab", "true");
				}
				else if(strTipSistema.equals("UNIDADES")){
					aObjContext.put("existeUnidad", "true");
				}
				objSesion.setAttribute("objTurbineGroup", objTurbineGroups);
				aObjData.setScreenTemplate("/web2lab,seguridad,OpcionDepartamento.vm");
			}else if(objTurbineGroups != null && objTurbineGroups.size() == 1){
				String objMenu = login.generaMenu(strPath, objRoles)+"";
            	objSesion.setAttribute("menu",objMenu);
				if(strTipSistema.equals("LABORATORIOS")){
					aObjContext.put("existeLab", "true");
					objSesion.setAttribute("strIdDepartamentoActual", objTurbineGroup.getGroupId()+"");
					StringTokenizer st = new StringTokenizer(login.getCLabByGroup(objTurbineGroup.getGroupId()+""), "|");
            		objSesion.setAttribute("strLaboratorioDepto", st.nextToken());
            		objSesion.setAttribute("strClaboratorio",st.nextToken());
            		objSesion.setAttribute("strCDepartamento",st.nextToken());
//		        	objSesion.setAttribute("strIdCLabDefault", login.getLaboratorioByDesc(ValoresCatalogo.LABORATORIO_DEFAULT));
            	}
				else if(strTipSistema.equals("UNIDADES")){
					aObjContext.put("existeUnidad", "true");
					rutaSistema = "web2lab,ap,PantallaInicio.vm";
					String st = login.getCLabDepByGroup(objTurbineGroup.getGroupId()+"");
					objSesion.setAttribute("strIdUnidadActual", st);
					objSesion.setAttribute("strIdCLab", login.getLabPorUnidad(st));
//		        	objSesion.setAttribute("strIdCLabDefault", login.getLaboratorioByDesc(ValoresCatalogo.LABORATORIO_DEFAULT));
				}
				objSesion.setAttribute("grupo", objTurbineGroup.getGroupName());
				objSesion.setAttribute("idgrupo", objTurbineGroup.getGroupId());
				aObjData.setScreenTemplate(rutaSistema);
				
			}
			
		}else{
			String strUsuario = aObjData.getParameters().getString("hdnUsuario");
			int intNumEmple = aObjData.getParameters().getInt("hdnNumEmple");
			String strEMail = aObjData.getParameters().getString("hdnEMail");
			
			aObjContext.put("strUsuaVuel", ""+strUsuario+"");
			aObjContext.put("intNumEmpVuel", ""+intNumEmple+"");
			aObjContext.put("strEMailVuel", ""+strEMail+"");
			aObjContext.put("strLoginNameVuel", ""+strLoginName+"");
			
			aObjContext.put("strMenContras", "El password utilizado no es valido");
			aObjData.setScreenTemplate("web2lab,seguridad,CamContras.vm");
		}
	}
}