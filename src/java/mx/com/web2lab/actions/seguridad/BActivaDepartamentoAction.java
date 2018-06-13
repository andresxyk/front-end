package mx.com.web2lab.actions.seguridad;

import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import mx.com.web2lab.actions.SecureAction;
import mx.com.web2lab.backend.util.catalogos.ValoresCatalogo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

public class BActivaDepartamentoAction extends SecureAction implements Serializable{
	
	/* Variable utilizada para enviar mensajes al log */
	private static Log iObjLog = LogFactory.getLog(LoginAction.class);
	
	/**
	 * Metodo que genera dos variables que estaran en sesion
	 * que daran la informacion de cual es el departameno actual
	 * las variables generadas son "strIdDepartamentoActual" que 
	 * define el id del departamento que elijio el usuario y
	 * "strNombreDepartamentoActual" que define el nombre del 
	 * departamento 
	 * @param aobjRunData Objeto que recupera los datos de la forma
	 * @param aobjCOntext Objeto que tiene detos del contexto
	 */
	public void doDepartamento(RunData aobjRunData, Context aobjCOntext){
		HttpSession objSesion = aobjRunData.getSession();
		String strIdDepartamento = aobjRunData.getParameters().getString("departamento");
		String sDepartamento = aobjRunData.getParameters().getString("sdepartamento"+strIdDepartamento);
		String strExisteUnidad = aobjRunData.getParameters().getString("existeUnidad");
		String strExisteLab = aobjRunData.getParameters().getString("existeLab");
		//String strNomDepto = aobjRunData.getParameters().getString(strIdDepartamento);
		iObjLog.debug("doDepartamento Datos recobidos: departamento:" + strIdDepartamento + 
				" ExisteUnidad: " + strExisteUnidad + " ExisteLab: " + strExisteLab + 
				" NomDepto:" + sDepartamento);
		objSesion.setAttribute("strIdDepartamentoActual", strIdDepartamento);
		objSesion.setAttribute("grupo", sDepartamento);
		objSesion.setAttribute("idgrupo", strIdDepartamento);
		LoginAction objLogin = new LoginAction();
		String strNomUser = objSesion.getAttribute("username")+"";
		String strPath = aobjRunData.getContextPath().trim()+"/servlet/template/";
		String objMenu = "";
		try{
			StringTokenizer st = new StringTokenizer(objLogin.getCLabByGroup(strIdDepartamento), "|");
        	//validacion para unidades
          if(strExisteUnidad != null && !strExisteUnidad.equals("")){
          	String ideUnidad = objLogin.getCLabDepByGroup(strIdDepartamento);
        	objSesion.setAttribute("strIdUnidadActual", ideUnidad);
//        	objSesion.setAttribute("strIdCLabDefault", objLogin.getLaboratorioByDesc(ValoresCatalogo.LABORATORIO_DEFAULT));
    		objSesion.setAttribute("strIdCLab", objLogin.getLabPorUnidad(ideUnidad));
           }else if(strExisteLab != null && !strExisteLab.equals("")){
//        	objSesion.setAttribute("strLaboratorioDepto", objLogin.getCLabByGroup(strIdDepartamento));
//        	objSesion.setAttribute("strIdCLabDefault", objLogin.getLaboratorioByDesc(ValoresCatalogo.LABORATORIO_DEFAULT));
        	objSesion.setAttribute("strLaboratorioDepto", st.nextToken());
        	objSesion.setAttribute("strClaboratorio",st.nextToken());
        	objSesion.setAttribute("strCDepartamento",st.nextToken());
           }
			//objSesion.setAttribute("strLaboratorioDepto", objLogin.getCLabByGroup(strIdDepartamento));
			//System.out.println("   departamento seleccionado "+strIdDepartamento+"  nombre "+strNomUser);
			List objRoles = objLogin.getRoleByUser(strNomUser, strIdDepartamento);
			//Iterator objIter = objRoles.iterator();
			/*while(objIter.hasNext()){
				TurbineGroup objTurbineGroup =(TurbineGroup)objIter.next();
				if((objTurbineGroup.getGroupId()+"").equals(strIdDepartamento)){
					objRol.add(objTurbineGroup);
				}
			}*/
			//System.out.println("    objRol  "+objRoles);
			objMenu= objLogin.generaMenu(strPath, objRoles)+"";
			objSesion.setAttribute("menu",objMenu);
		}catch(Exception aObjException){
			iObjLog.debug("Entro a la excepcion al generar roles ");
		}
	}
	
	public void doPerform(RunData aobjRunData, Context aobjCOntext){
		iObjLog.debug("Entro al doPerform");
	}

}