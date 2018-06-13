package mx.com.web2lab.actions.seguridad;

//negocio Framework
import java.io.Serializable;

import mx.com.web2lab.actions.SecureAction;
import mx.com.web2lab.util.seguridad.BPermissionMenuUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

public class BAdmonMenuAction extends SecureAction implements Serializable{

	private static Log iObjlog = LogFactory.getLog(BAdmonMenuAction.class);

	/**
	 * Este metodo esta encargado de insertar un nuevo grupo menu
	 * en el esquema de seguridad
	 * @param data aObjDatos
     * @param context aObjContexto
     * @throws Exception comment
	 */
	public void doInserta(RunData aObjDatos, Context aObjContexto)throws Exception{
		BPermissionMenuUtil objPermissionMenuUtil = new BPermissionMenuUtil();
		int intIdPerm = aObjDatos.getParameters().getInt("cboPermisosmenu");
		int intIdGpo = aObjDatos.getParameters().getInt("cboGruposmenu");
		String strLiga = aObjDatos.getParameters().getString("txtLiga");
		if(objPermissionMenuUtil.setInserta(intIdPerm,intIdGpo,strLiga)){
			iObjlog.debug(">>>>: Query ok insert");
			String strContextoMensaje = aObjDatos.getParameters().getString("txtLiga");
	    	aObjContexto.put("strMensaje", ""+strContextoMensaje+"" );			
	    	aObjContexto.put("bolCreado", "true");
	    }else
			iObjlog.debug(">>>: Query fail insert:"+intIdPerm+","+intIdGpo+","+strLiga);
	}

	/**
	 * Este metodo esta encargado de actualizar un grupo menu
	 * en el esquema de seguridad
	 * @param data RunData
     * @param context aObjContexto
     * @throws Exception comment
	 */
	public void doActualiza(RunData aObjDatos, Context aObjContexto)throws Exception{
		BPermissionMenuUtil objPermissionMenuUtil = new BPermissionMenuUtil();
		int intIdPerm = aObjDatos.getParameters().getInt("cboPermisosmenu");							   
		int intIdGpo = aObjDatos.getParameters().getInt("cboGruposmenu");
		String strLiga = aObjDatos.getParameters().getString("txtLiga");
		if(objPermissionMenuUtil.setActualiza(intIdPerm,intIdGpo,strLiga)){
			iObjlog.debug(">>>>: Query ok update");										
			aObjContexto.put("bolModif", "true");
			//aObjDatos.setScreenTemplate("/web2lab,seguridad,Ligas_Menu.vm");
		}else{
			iObjlog.debug(">>>: Query fail update:"+intIdPerm+","+intIdGpo+","+strLiga);
		}		
	}

	/**
     * Metodo que se ejecuta cuando no se encuentra
     * alguna accion especificada
     * @param RunData aObjDatos
     * @param Context aobjContexto
     * @throws Exception
     */
    public void doPerform(RunData aObjDatos, Context aObjContexto)throws Exception{
        iObjlog.debug("Ejecutando do perform!");
        aObjDatos.setMessage("No se puede encontrar la accion solicitada!");
    }
}