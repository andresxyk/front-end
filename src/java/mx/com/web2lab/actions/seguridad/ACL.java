package mx.com.web2lab.actions.seguridad;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import mx.com.web2lab.backend.util.beans.sistema.TurbineGroup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 *
 * Administra las autorizaciones de un usuario
 */
public class ACL implements Serializable{

    	/**log de la aplicacion*/
		private static Log iObjLog = LogFactory.getLog(ACL.class);
    
    	/**Almacena los roles de un usuario*/
    	private HashMap roles	 = null;
    	/**Almacena los permisos de un usuario*/
    	private HashMap permisos = null;

    	/**
    	 * Crea un ACL con los roles y los permisos dados
    	 * @param roleSets
    	 * @param permissionSets
    	 */
    	public ACL(Map roleSets, Map permissionSets){
    		roles	 = (HashMap)roleSets;
    		permisos = (HashMap)permissionSets;
    	}

    	/*
    	* Valida si existe el permiso, en el grupo
    	* para un ACL en particular
    	*/
    	public boolean hasPermission(String permiso, String grupo){
    		Collection permisos = (Vector)this.permisos.get(grupo);
    		if(permisos != null && permisos.size() > 0){
    			if(this.tienePermisoEn(permiso, permisos)){
    				return true;
    			}
    		}
    		return false;
    	}
    	

    	/*
    	* Valida si existe el permiso, en alguno de los grupos
    	* para un ACL en particular
    	*/
    	public boolean hasPermission(String permiso, Collection grupo){
    	    if(grupo == null || grupo.size() == 0){
    	        iObjLog.debug("LaColeccionDeGruposEstaNula....");
    	    }
    	    iObjLog.debug("Permiso="+permiso+"Grupos="+grupo.size());
    		Iterator it = grupo.iterator();
    		while(it.hasNext()){
    		    String tg = ((TurbineGroup)it.next()).getGroupName().trim();
    		    iObjLog.debug("Grupo="+tg);
    			Collection permisos = (Vector)this.permisos.get(tg.trim());
    			if(permisos != null && permisos.size() > 0){
    				if(this.tienePermisoEn(permiso, permisos)){
    					return true;
    				}
    			}
    		}
    		return false;
    	}


    	/*
    	* Valida si tiene el rol, en el grupo dado
    	* para un ACL en particular
    	*/
    	public boolean hasRole(String role, String grupo){
    		return tieneRolEn(role, grupo);
    	}


    	/*
    	* Se encarga de buscar el permiso dado en una coleccion determinada
    	* de permisos
    	*/
    	private boolean tienePermisoEn(String permiso, Collection permisos){
    		if(permisos != null && permisos.size() > 0){
    			Iterator it = permisos.iterator();
    			while(it.hasNext()){
    				String p = (String)it.next();
    				iObjLog.debug("ValidandoPermiso="+permiso+" ContraPermiso="+p);
    				if(p.trim().equals(permiso.trim())){
    					return true;
    				}
    			}
    		}else{
    		    iObjLog.debug("LaColeccionPermisosEstaNula....");
    		}
    		return false;
    	}

    	/*
    	* Se encarga de buscar el rol dado en el mapa de roles del ACL
    	*/
    	private boolean tieneRolEn(String role, String grupo){
    		iObjLog.debug("tieneRolEn:BuscandoRole: " + role + " en el grupo: " + grupo);
    		String rol = (String)this.roles.get(grupo);
    		if(rol != null && rol.equals(role.trim())){
    			return true;
    		}
    		return false;
    	}

}

