package mx.com.web2lab.actions.seguridad;

//Java
import java.io.Serializable;
import java.util.List;

import mx.com.web2lab.actions.SecureAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.Permission;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbinePermission;
import org.apache.turbine.services.security.torque.om.TurbinePermissionPeer;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.DataBackendException;
import org.apache.turbine.util.security.UnknownEntityException;
import org.apache.velocity.context.Context;
/**
 * Esta clase esta encargada de administrar los permisos
 * en el esquema de seguridad de Turbine
 */
public class BPermissionAction extends SecureAction implements Serializable{
	
	/**log de la aplicacion*/
	private static Log iObjLog = LogFactory.getLog(BPermissionAction.class);

    /**
     * Metodo encargado de insertar un nuevo permiso en el esquema
     * de seguridad de Turbine
     * @param data txtNombre
     * @throws Exception 
     */
    public void doInserta(RunData objDatos, Context objContexto)throws Exception{
    	
    	String strPermiso = objDatos.getParameters().getString("txtNombre");    	
    	Criteria objCriteria = new Criteria();
    	objCriteria.add(TurbinePermissionPeer.PERMISSION_NAME, objDatos.getParameters().getString("txtNombre"));
    	List objLista = TurbinePermissionPeer.doSelect(objCriteria);
    	TurbinePermission objPermiso = new TurbinePermission();
    	
    	//////////////////////////////////
    	if ( objLista.size() == 0 ){
    		iObjLog.debug(">>>>>>>>>>>>Else:");
    		objPermiso.setNew(true);
	   		iObjLog.debug(">>>>>>>>>>>setNew(true):");
	   		objPermiso.setName(objDatos.getParameters().getString("txtNombre"));
	   		iObjLog.debug(">>>>>>>>>>>setName(...txtNombre...):");
	   		objPermiso.save();
	       	iObjLog.debug(">>>>>>>>>>>>>doInserta -> Insertando Rol nuevo:"+strPermiso);
	    	String strContextoMensaje = objDatos.getParameters().getString("txtNombre");
	    	objContexto.put("strMensaje", ""+strContextoMensaje+"" );
	    	objContexto.put("bolCreado", "true");
    	}
    	else{
	    	for ( int inti = 0; inti<objLista.size(); inti++ ){
	    		iObjLog.debug(">>>>>>>>>>>>>entrando al for");
	    		objPermiso = (TurbinePermission)objLista.get(inti);
	    		iObjLog.debug(">>>>>>>>>>>>>entrando al for y asignando objRole = (TurbineRole)objLista.get("+inti+")");
	    		String strDeListaRol = objPermiso.getName();
	    		iObjLog.debug(">>>>>>>>>>>>>DeLista el nombre de rol es:"+objPermiso.getName()+" String :"+strDeListaRol);
	    		iObjLog.debug(">>>>>>>>>>>>>DeVM NombreDelRol:"+strPermiso);
	    		if ( strDeListaRol != "null" || strDeListaRol != ""){
			        if ( strDeListaRol.equals(strPermiso) ){                        
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
    	//////////////////////////////////
    	
//    	for ( int inti = 0; inti<objLista.size(); inti++ ){
//    		objPermiso = (TurbinePermission)objLista.get(inti);
//    	}
//    	if(objPermiso.getName() == strPermiso){
//    		String strContextoMensaje = objDatos.getParameters().getString("txtNombre");
//	    	objContexto.put("strMensaje", ""+strContextoMensaje+"" );
//	    	objContexto.put("bolCreado", "false");
//    	}
//    	else{
//	    	objPermiso.setName(objDatos.getParameters().getString("txtNombre"));
//	    	objPermiso.setNew(true);
//	    	objPermiso.save();
//	    	
//	    	String strContextoMensaje = objDatos.getParameters().getString("txtNombre");
//	    	objContexto.put("strMensaje", ""+strContextoMensaje+"" );
//	    	objContexto.put("bolCreado", "true");
//    	}
    }

    /**
     * Este metodo esta encargado de actualizar un permiso
     * en el esquema de seguridad de Turbine
     * @param txtNombre
     * @param hndNombreAnterior
     * @throws Exception 
     */
    public void doActualiza(RunData objDatos, Context objContexto)throws Exception{
    	Criteria objCriteria = new Criteria();    	
    	objCriteria.add(TurbinePermissionPeer.PERMISSION_NAME, objDatos.getParameters().getString("hndNombreAnterior"));
    	String strMenModif = objDatos.getParameters().getString("txtNombre");    	
    	List objLista = TurbinePermissionPeer.doSelect(objCriteria);
    	TurbinePermission objPermiso = new TurbinePermission();
    	for ( int inti = 0; inti<objLista.size(); inti++ ){
    		objPermiso = (TurbinePermission)objLista.get(inti);
    	}
    	
    	Criteria obj2Criteria = new Criteria();
    	obj2Criteria.add(TurbinePermissionPeer.PERMISSION_NAME, objDatos.getParameters().getString("txtNombre"));
    	List obj2Lista = TurbinePermissionPeer.doSelect(obj2Criteria);
    	if ( obj2Lista.size() == 0 ){
    	objPermiso.setName(objDatos.getParameters().getString("txtNombre"));
    	objPermiso.setNew(false);
    	objPermiso.save();	    	
    	objContexto.put("strMensajeModif", ""+strMenModif+"" );
    	objContexto.put("bolModif", "true");
    	}
    	else{
    		iObjLog.debug(">>>>>>>>>>>>>Ya existe el mismo Permiso:");
        	objContexto.put("strMensajeModif", ""+strMenModif+"" );
        	objContexto.put("bolModif", "false");
    	}
    }

    /**
     * Metodo encargado de eliminar un permiso en el esquema de seguridad
     * de Turbine
     * @param hndNombre
     * @throws Exception
     */
    public void doBorra(RunData objDatos, Context objContexto)throws Exception{
        Permission objPermiso = TurbineSecurity.getPermissionByName(
                objDatos.getParameters().getString("hndNombre"));
        String strPermiso = objDatos.getParameters().getString("hndNombre");
        try{
        	TurbineSecurity.removePermission(objPermiso);
        	objContexto.put("bolBorrado", "true");
        	objContexto.put("strPermisoBorrado", ""+strPermiso+"" );
        }catch (UnknownEntityException uee){
        }catch (DataBackendException dbe){
        	objContexto.put("bolNoBorro", "true");
        	objContexto.put("strPermisoNoBorrado", ""+strPermiso+"" );
        	iObjLog.debug(">>>>>>>Se captura la excepci&oacute;n de permiso: "+dbe);
        }
    }

    /**
     * Implement this to add information to the context.
     * @throws Exception
     */
    public void doPerform(RunData objDatos, Context objContexto)throws Exception{
        //gLog.debug("Running do perform!");
    	String strBuscaTodos = objDatos.getParameters().getString("chkSeleccionaTodo");
    	String strNomPermiso = objDatos.getParameters().getString("txtPermisoNombre");
        if ( strBuscaTodos != null && strBuscaTodos.length() > 0){
        	objContexto.put("strNomPermiso", "");
        }
        else{
	        iObjLog.debug("<<<<<<<<<<<<El valor de la caja de text de grupo:"+strNomPermiso);
	        objContexto.put("strNomPermiso", ""+strNomPermiso+"");
        }
        if(strNomPermiso == null){
        	 objContexto.put("strNomPermiso","");
        }
        	
    }
}
