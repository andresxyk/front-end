package mx.com.web2lab.actions.seguridad;

//java
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.com.web2lab.actions.SecureAction;
import mx.com.web2lab.tools.seguridad.CSeguridadTool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.Permission;
import org.apache.turbine.om.security.Role;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineRole;
import org.apache.turbine.services.security.torque.om.TurbineRolePeer;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.PermissionSet;
import org.apache.turbine.util.security.UnknownEntityException;
import org.apache.velocity.context.Context;


/**
 * Esta clase esta encargada de administrar los roles
 * en el esquema de seguridad de Turbine
 */
public class BRoleAction extends SecureAction implements Serializable{
	
	/**Log de la aplicacion*/
	private static Log iObjLog = LogFactory.getLog(BRoleAction.class);
	
    /**
     * Metodo utilizado para insertar un nuevo role
     * @param txtNombre
     * @throws Exception 
     */
    public void doInserta(RunData objDatos, Context objContexto)throws Exception{
    	iObjLog.debug(">>>>>>>>>>>>>doInserta -> Insertando Rol");
        TurbineRole objRole = new TurbineRole();
        String strNombreRol = objDatos.getParameters().getString("txtNombre");
        Criteria objCriteria = new Criteria();
        objCriteria.add(TurbineRolePeer.ROLE_NAME, objDatos.getParameters().getString("txtNombre"));
    	List objLista = TurbineRolePeer.doSelect(objCriteria);
    	iObjLog.debug(">>>>>>>>>>>>>entrando size de la lista"+objLista.size());
    	if ( objLista.size() == 0 ){
    		iObjLog.debug(">>>>>>>>>>>>Else:");
	   		objRole.setNew(true);
	   		iObjLog.debug(">>>>>>>>>>>setNew(true):");
	   		objRole.setName(objDatos.getParameters().getString("txtNombre"));
	   		iObjLog.debug(">>>>>>>>>>>setName(...txtNombre...):");
	       	objRole.save();
	       	iObjLog.debug(">>>>>>>>>>>>>doInserta -> Insertando Rol nuevo:"+strNombreRol);
	    	String strContextoMensaje = objDatos.getParameters().getString("txtNombre");
	    	objContexto.put("strMensaje", ""+strContextoMensaje+"" );
	    	objContexto.put("bolCreado", "true");
    	}
    	else{
	    	for ( int inti = 0; inti<objLista.size(); inti++ ){
	    		iObjLog.debug(">>>>>>>>>>>>>entrando al for");
	    		objRole = (TurbineRole)objLista.get(inti);
	    		iObjLog.debug(">>>>>>>>>>>>>entrando al for y asignando objRole = (TurbineRole)objLista.get("+inti+")");
	    		String strDeListaRol = objRole.getName();
	    		iObjLog.debug(">>>>>>>>>>>>>DeLista el nombre de rol es:"+objRole.getName()+" String :"+strDeListaRol);
	    		iObjLog.debug(">>>>>>>>>>>>>DeVM NombreDelRol:"+strNombreRol);
	    		if ( strDeListaRol != "null" || strDeListaRol != ""){
			        if ( strDeListaRol.equals(strNombreRol) ){                        
			            String strContextoMensaje = objDatos.getParameters().getString("txtNombre");
			            iObjLog.debug(">>>>>>>>>>>>>Ya existe el mismo Rol:");
			            objContexto.put("strMensaje", ""+strContextoMensaje+"" );
				    	objContexto.put("bolCreado", "false");
			        }
	    		}
	
	    	
		        else{ 
		        	
	    		}
		        iObjLog.debug(">>>>>>>>>>>>>doInserta -> Saliendo del action Rol:");
	    	}
    	}
    }

    /**
     * Metodo que modifica el nombre del role anterior con el nuevo role name
     * @param txtNombre
     * @param hndNombreAnterior
     * @throws Exception cuando hndNombreanterior no existe en la tabla
     */
    public void doActualiza(RunData objDatos, Context objContexto)throws Exception{
        Criteria objCriteria = new Criteria();
    	objCriteria.add(TurbineRolePeer.ROLE_NAME, objDatos.getParameters().getString("hdnNombreAnterior"));
    	List objLista = TurbineRolePeer.doSelect(objCriteria);
    	TurbineRole objRole = new TurbineRole();
    	
   		for ( int inti = 0; inti<objLista.size(); inti++ ){
   			objRole = (TurbineRole)objLista.get(inti);
   		}
   		
    	Criteria obj2Criteria = new Criteria();
    	obj2Criteria.add(TurbineRolePeer.ROLE_NAME, objDatos.getParameters().getString("txtNombre"));
       	List obj2Lista = TurbineRolePeer.doSelect(obj2Criteria);
       	iObjLog.debug(">>>>>>>>>>>>>entrando size de la lista"+objLista.size());
       	if ( obj2Lista.size() == 0 ){
       		
    	objRole.setName(objDatos.getParameters().getString("txtNombre"));
    	objRole.setNew(false);
    	objRole.save();
    	String strMenModif = objDatos.getParameters().getString("txtNombre");
    	objContexto.put("strMensajeModif", ""+strMenModif+"" );
    	objContexto.put("bolModif", "true");
    	}
    	else{
            iObjLog.debug(">>>>>>>>>>>>>Ya existe el mismo Rol:");
            String strMenModif = objDatos.getParameters().getString("txtNombre");
        	objContexto.put("strMensajeModif", ""+strMenModif+"" );
        	objContexto.put("bolModif", "false");
    	}
    	
    }

    /**
     * acci&oacute;n responsable de remover un role de la tabla
     * @param hdnNombre
     * @throws Exception cuando no encuentra valor en la tabla igual a txtNombre
     */
    public void doBorra(RunData objDatos, Context objContexto)throws Exception{
        Role objRole = TurbineSecurity.getRoleByName(
                objDatos.getParameters().getString("hdnNombre"));

        try{
            TurbineSecurity.removeRole(objRole);
        }
        catch (UnknownEntityException uee){
        }
        String strMenBorrado = objDatos.getParameters().getString("hdnNombre");
    	objContexto.put("strMensajeBorrado", ""+strMenBorrado+"" );
    	objContexto.put("bolBorrado", "true");
    }

    /**
     * Update the roles that are to assigned to a user
     * for a project.
     * @param txtNombre
     * @throws Exception comment
     */
    public void doPermisos(RunData objDatos, Context objContexto)
    throws Exception{
    	CSeguridadTool objTool = (CSeguridadTool)objContexto.get("CSeguridadTool");
        String strNombre = objDatos.getParameters().getString("hdnNombre");
        String strRolPermiso[] = objDatos.getParameters().getStrings("cboPermisosAsig");
        List objLstRolPermisos = new ArrayList();
        //Se pasa a una lista para tener un mejor manejo
        if(strRolPermiso != null){
	        for(int i=0; i< strRolPermiso.length; i++){
	        	iObjLog.debug("ELOBJETOROLPERMISOENCONTRADO:|"+strRolPermiso[i]+"|");
	        	objLstRolPermisos.add(strRolPermiso[i]);
	        }
        }
        iObjLog.debug("LOSPERMISOSAASIGNARSESON:|"+objLstRolPermisos.size()+"|"); 
        iObjLog.debug("ELROLACAMBIARES:|"+strNombre+"|");
        //Obtiene el rol
        Role objRole = TurbineSecurity.getRoleByName(strNombre);
        //Obtiene los permisos del rol
        PermissionSet objRolePermisos = objRole.getPermissions();
        //Obtiene todos los permisos
        Permission[] objPermisos = TurbineSecurity.getAllPermissions().getPermissionsArray();
        String strRolenombre = objRole.getName();
        for (int inti = 0; inti < objPermisos.length; inti++){
            String strPermisonombre = objPermisos[inti].getName();
            String strRolepermiso = strRolenombre + strPermisonombre;
            Permission objPermiso = 
            	TurbineSecurity.getPermissionByName(strPermisonombre);
            //Si contiene el permiso y no lo tenia asignado lo ASIGNA
            if(objLstRolPermisos.contains(strRolepermiso) &&
            		!objRolePermisos.contains(objPermiso)){
            	objRole.grant(objPermiso);                
            //Si no contiene el permiso y lo tenia asigando lo QUITA
            }else if(!objLstRolPermisos.contains(strRolepermiso) &&
            		objRolePermisos.contains(objPermiso)){
            	objRole.revoke(objPermiso);            	
            }            
            //Se actualiza el grupo del tool
            objTool.setRole(objRole);            
            objContexto.put("bolActualizado", "true");
            objContexto.put("strActualizado",strNombre);
        }
    }
     

    /**
     * Implement this to add information to the context.
     *
     * @param data Turbine information.
     * @param context Context for web pages.
     * @throws Exception a generic exception.
     */
    public void doPerform(RunData objDatos, Context objContexto)throws Exception{
        iObjLog.debug("Running do perform!");
        //objDatos.setMessage("Can't find the requested action!");
        String strBuscaTodos = objDatos.getParameters().getString("chkSeleccionaTodo");
        String strRoleNombre = objDatos.getParameters().getString("txtRoleNombre");
        if ( strBuscaTodos != null && strBuscaTodos.length() > 0){
            objContexto.put("strRoleVuel", "");
        }
        else{
            objContexto.put("strRoleVuel", ""+strRoleNombre+"");
        }
        
        if(strRoleNombre==null){
        	
        	 objContexto.put("strRoleVuel","");
        }
    }
}
