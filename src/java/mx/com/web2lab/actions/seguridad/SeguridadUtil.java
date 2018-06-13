package mx.com.web2lab.actions.seguridad;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import mx.com.web2lab.util.GenericDAO;
import mx.com.web2lab.backend.util.beans.sistema.TurbineGroup;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbinePermission;
import org.apache.turbine.services.security.torque.om.TurbineRolePermission;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.util.security.TurbineSecurityException;

import com.workingdogs.village.DataSetException;

/**
 *
 * Brinda diferentes servicios relacionados a la seguridad
 */
public class SeguridadUtil implements Serializable{

    /**log de la aplicacion*/
	private static Log iObjLog = LogFactory.getLog(SeguridadUtil.class);
    
    
    /**
     * Constructor
     *
     */
    private SeguridadUtil(){
        
    }
    
	/**
	* Se encarga de obtener la lista de control de acceso de un usuario dado
	* @param	ideUser clave de usuario
	* @return	ACL acl del usuario
	*/
	public static ACL obtenACL(int aIntIdUsuario)
					throws Exception{
		iObjLog.debug("obteniendoACLUsuario:" + aIntIdUsuario);
		Map roles	 = obtenRoleSets(aIntIdUsuario);
		Map permisos = obtenPermissionSets(aIntIdUsuario);
		return new ACL(roles, permisos);
	}

    
	/**
	* Este metodo establece el mapa de roles de un usuario
	* para un grupo dado y es utilizado para crear el ACL
	*/
	private static Map obtenRoleSets(int aIntIdeUser)
					throws Exception{
		HashMap rolesGrupo			 = null;
		Collection roles			 = null;
		Criteria objCri				 = new Criteria();

		try{
			iObjLog.debug("obtenRoleSets:obteniendoACLUsuario:" + aIntIdeUser);
			objCri.add(TurbineUserGroupRolePeer.USER_ID, aIntIdeUser);
			roles = TurbineUserGroupRolePeer.doSelect(objCri);
			//Valida que se hayan encontrado roles para el permiso dado
			if(roles == null || roles.size() < 0) {
				throw new Exception("obtenRoleSets:NoSeEncontraronRolesParaElUsuario:"+aIntIdeUser);
			}
			iObjLog.debug("obtenRoleSets:SeObtuboElSiguienteNumeroDeRoles:"+roles.size());
			rolesGrupo = new HashMap();
			Iterator it = roles.iterator();
			while(it.hasNext()){
				TurbineUserGroupRole ugr = (TurbineUserGroupRole)it.next();
				rolesGrupo.put(ugr.getTurbineGroup().getName().trim(), ugr.getTurbineRole().getName().trim());
				iObjLog.debug("obtenRoleSets:CreandoRelacion:Grupo="+ugr.getTurbineGroup().getName().trim()+":Rol="+ugr.getTurbineRole().getName().trim());
			}
			return rolesGrupo;
		}catch(TorqueException aObjException){
			iObjLog.error("ErrorEn:obtenRoleSets....", aObjException);
			throw aObjException;
		}
	}

	/**
	* Este metodo establece el mapa de roles de un usuario
	* para un grupo dado y es utilizado para crear el ACL
	*/
	public static boolean isUsuarioGrupoRol(int aIntIdeUser, int idGrupo, String sRole)
	throws Exception{
		boolean autoriza = false;
		try{
			iObjLog.debug("obtenRoleSets:obteniendoACLUsuario:" + aIntIdeUser);
			Criteria objCri	= new Criteria();
			objCri.add(TurbineUserGroupRolePeer.USER_ID, aIntIdeUser);
			objCri.add(TurbineUserGroupRolePeer.GROUP_ID, idGrupo);
			Collection roles = TurbineUserGroupRolePeer.doSelect(objCri);
			Iterator it = roles.iterator();
			//Valida que se hayan encontrado roles para el permiso y usuario dado
			if(roles == null || roles.size() < 0) {
				autoriza = false;
			}else {
				while(it.hasNext()) {
					TurbineUserGroupRole ugr = (TurbineUserGroupRole)it.next();
					String rol = ugr.getTurbineRole().getName().trim();
					iObjLog.debug("isUsuarioGrupoRol:Comparando rol: " + rol + " contra rol: " + sRole);
					if(rol.equals(sRole)) {
						autoriza = true;
						break;
					}
				}
			}
			return autoriza;
		}catch(TorqueException aObjException){
			iObjLog.error("validaUsuarioGrupoRol:ERROR", aObjException);
			throw aObjException;
		}
	}

	/**
	* Este metodo establece el mapa de permisos de un usuario
	* para un grupo dado y es utilizado para crear el ACL 
	*/
	private static Map obtenPermissionSets(int aIntIdeUser)
					throws Exception{
		HashMap permisosGrupo		 = null;
		Collection permisos			 = null;
		Criteria objCri				 = new Criteria();

		try{
			iObjLog.debug("obtenPermissionSets:" + aIntIdeUser);
			objCri.add(TurbineUserGroupRolePeer.USER_ID, aIntIdeUser);
			permisos = TurbineUserGroupRolePeer.doSelect(objCri);			
			//Valida que se hayan encontrado roles para el permiso dado
			if(permisos == null || permisos.size() < 0) {
				throw new Exception("obtenPermissionSets:NoSeEncontraronPermisosParaElUsuario:" + aIntIdeUser);
			}
			iObjLog.debug("obtenPermissionSets:SeObtuboElSiguienteNumeroDeRoles:"+permisos.size());
			permisosGrupo	= new HashMap();
			Iterator it		= permisos.iterator();
			while(it.hasNext()){
				TurbineUserGroupRole ugr = (TurbineUserGroupRole)it.next();
				Vector auxPermisos2 = new Vector();
				List auxPermisos = ugr.getTurbineRole().getTurbineRolePermissions();
				Iterator it2 = auxPermisos.iterator();
				while(it2.hasNext()){
					TurbineRolePermission rp = (TurbineRolePermission)it2.next();
					TurbinePermission tp = rp.getTurbinePermission();
					auxPermisos2.add(tp.getName().trim());
				}
				permisosGrupo.put(ugr.getTurbineGroup().getName().trim(), auxPermisos2);
				iObjLog.debug("obtenPermissionSets:CreandoRelacion:Grupo="+ugr.getTurbineGroup().getName().trim()+":Permisos="+auxPermisos2);
			}
			return permisosGrupo;
		}catch(TorqueException aObjException){
			iObjLog.error("ErrorEn:obtenPermisosSets()...", aObjException);
			throw aObjException;
		}
	}

    /**
     * Metodo que busca datos de un usuario a traves de su login
     * para conocer mayor informacion del TurbineSecurity para un usuario 
     * @param strName
     * @return
     */
    public static TurbineUser getUserByName(String strName) throws Exception {
    	TurbineUser objTurbineUser = null;
    	Criteria objCrit = new Criteria();
    	objCrit.add(TurbineUserPeer.LOGIN_NAME, strName);
    	try{
    		List objTurbinUsers = TurbineUserPeer.doSelect(objCrit);
    		for(int intI = 0; intI < objTurbinUsers.size(); intI++){
    			objTurbineUser = (TurbineUser)objTurbinUsers.get(intI);
    		}
    	}catch(TorqueException aObjException){
    		iObjLog.error("ErrorAlObtenerDatosDelUsuario ", aObjException);
    		throw aObjException;
    	}
    	return objTurbineUser;
    }
    
    /**
     * Este metodo esta encargado de verificar
     * si el usuario que es pasado como parametro
     * tiene permiso para realizar cierta accion
     * @param aStrUserName Usuario
     * @param aIntUserID ID Usuario
     * @param aStrPermiso Permiso
     * @return
     * @throws Exception
     */
    public static boolean tieneAutorizacion(String aStrUserName, String aStrPassword,  
    		String aStrPermiso)throws Exception{
    	iObjLog.debug("AUTORIZANDO..."+aStrUserName+"|"+aStrPermiso+"|");
    	boolean autoriza = false;
    	List objGrupos = null;
    	try{
            TurbineUser objTurbineUser = getUserByName(aStrUserName);
            objGrupos = getGroupByUser(aStrUserName);
            ACL acl =  obtenACL(objTurbineUser.getUserId());
            autoriza = acl.hasPermission(aStrPermiso,objGrupos);
    	}catch ( TurbineSecurityException aObjException ){        	
            iObjLog.error("TurbineSecurityException ", aObjException);
            return autoriza;
        }catch ( Exception aObjException ){
        	iObjLog.error(" Error en tieneAutorizacion..... ", aObjException);        	
        	return autoriza;        	
        }
        iObjLog.debug("RETORNAAUTORIZACION:|"+autoriza+"|");
    	return autoriza;    		    	   
    }
    
    /**
     * Este metodo esta encargado de verificar
     * si el usuario que es pasado como parametro
     * tiene permiso para realizar cierta accion
     * @param aStrUserName Usuario
     * @param aIntUserID ID Usuario
     * @param aStrRol Permiso
     * @return
     * @throws Exception
     */
    public static boolean tieneRolUsuarioGrupo(
    		String aStrUserName, String aStrPassword,  
    		String aStrRol, String idgrupo) throws Exception{
    	iObjLog.debug("tieneRolUsuarioGrupo:AUTORIZANDO...Usuario="+aStrUserName+"|Rol="+aStrRol+"|");
    	boolean autoriza = false;
    	try{
    		User usuario = TurbineSecurity.getAuthenticatedUser(aStrUserName,aStrPassword);
    		//si es un usuario valido y tiene el rol indicado true
    		if(usuario!=null) {
    			TurbineUser objTurbineUser = getUserByName(aStrUserName);
    			autoriza = isUsuarioGrupoRol(objTurbineUser.getUserId(), Integer.parseInt(idgrupo), aStrRol);
    		}else {
    			iObjLog.debug("tieneRolUsuarioGrupo:El usuario o el password son inv&aacute;lidos");
    		}
    	}catch ( TurbineSecurityException aObjException ){        	
            iObjLog.error("TurbineSecurityException ", aObjException);
            return autoriza;
        }catch ( Exception aObjException ){
        	iObjLog.error("tieneRolUsuarioGrupo:ERROR", aObjException);        	
        	return autoriza;        	
        }
        iObjLog.debug("tieneRolUsuarioGrupo:regresa|"+autoriza+"|");
    	return autoriza;    		    	   
    }
    
    /**
     * Este metodo esta encargado de verificar
     * si el usuario que es pasado como parametro
     * tiene permiso para realizar cierta accion
     * @param aStrUserName Usuario
     * @param aIntUserID ID Usuario
     * @param aStrRol Permiso
     * @return
     * @throws Exception
     */
    public static boolean tieneRolUsuarioGrupo(User usuario,  
    		String aStrRol, String idgrupo) throws Exception{
    	iObjLog.debug("tieneRolUsuarioGrupo:AUTORIZANDO...Usuario="+usuario+"|Rol="+aStrRol+"|");
    	boolean autoriza = false;
    	try{
    		//si es un usuario valido y tiene el rol indicado true
    		if(usuario!=null) {
    			TurbineUser objTurbineUser = getUserByName(usuario.getName());
    			autoriza = isUsuarioGrupoRol(objTurbineUser.getUserId(), Integer.parseInt(idgrupo), aStrRol);
    		}else {
    			iObjLog.debug("tieneRolUsuarioGrupo:El usuario o el password son inv&aacute;lidos");
    		}
    	}catch ( TurbineSecurityException aObjException ){        	
            iObjLog.error("tieneRolUsuarioGrupo:TurbineSecurityException", aObjException);
            return autoriza;
        }catch ( Exception aObjException ){
        	iObjLog.error("tieneRolUsuarioGrupo:ERROR", aObjException);        	
        	return autoriza;        	
        }
        iObjLog.debug("tieneRolUsuarioGrupo:regresa|"+autoriza+"|");
    	return autoriza;    		    	   
    }
    
    
    
    
    /**
     * 
     * @param strLoginUser
     * @return
     * @throws TorqueException
     * @throws DataSetException
     */
    public static List getGroupByUser(String  strLoginUser) throws Exception {
    	List objTurbinGroupLLena = null;
    	CallableStatement objCstmt = null;
    	ResultSet objRs = null;
    	Connection con  = null;
    	String strSQL = "";
    	try{
    		//con = Torque.getConnection();
    		GenericDAO objConn = new GenericDAO();
    		con = objConn.getConnection();
			strSQL = "select distinct tg.group_id, group_name, tgd.claboratoriodepartamento " +
			 "from turbine_user_group_role tugr, turbine_user tu, turbine_group tg, turbine_grupo_departamento tgd " +
			 "where tugr.user_id =tu.user_id " +
			 "and tugr.group_id = tg.group_id " +
			 "and tg.GROUP_ID = tgd.GROUP_ID " +
			 "and tu.login_name = " + strLoginUser + 
			 "order by 2";
			/*			
				objCstmt = con.prepareCall("{call USUARIOS.GETDEPTOS(?,?)}");    	
				objCstmt.setString(1, strLoginUser);
				objCstmt.registerOutParameter(2, OracleTypes.CURSOR);
				objCstmt.execute();
				objRs = (ResultSet)objCstmt.getObject(2);
			*/
				Statement objsmt = con.createStatement();
				objRs = objsmt.executeQuery(strSQL);
    		objTurbinGroupLLena = new Vector();
    		while (objRs.next()){
        		TurbineGroup objTurbineGroup = new TurbineGroup();
        		objTurbineGroup.setGroupId(new BigDecimal(objRs.getString(1)));
        		iObjLog.debug(" Datos de Group Id "+objTurbineGroup.getGroupId());
        		objTurbineGroup.setGroupName(objRs.getString(2));
        		objTurbinGroupLLena.add(objTurbineGroup);
        	}
    	}catch (TorqueException aObjException){
    	    iObjLog.error("Error al ejecutar PL GETDEPTOS....", aObjException);
    	    throw aObjException;
    	}catch (SQLException aObjException){
    	    iObjLog.error("Error al ejecutar PL GETDEPTOS....", aObjException);
    	    throw aObjException;
    	}finally {
    	    objRs.close();
    	    objCstmt.close();
    	    //Torque.closeConnection(con);
    	    if(con!=null)con.close();
    	}
    	return objTurbinGroupLLena;    	
    }
    
    
    /**
     * 
     * @param strLoginUser
     * @return
     * @throws TorqueException
     * @throws DataSetException
    public static List getGroupByUser(String  strLoginUser) throws Exception {
    	List objTurbinGroupLLena = null;
    	OracleCallableStatement objCstmt = null;
    	ResultSet objRs = null;
    	Connection con  = null;
    	try{
    		//con = Torque.getConnection();
			GenericDAO objConn = new GenericDAO();
			con = objConn.getConnection();    		
    		objCstmt =  (OracleCallableStatement) con.prepareCall("CALL USUARIOS.GETDEPTOS(?,?)");
    		objCstmt.setString(1, strLoginUser);
    		objCstmt.registerOutParameter(2, OracleTypes.CURSOR);
    		objCstmt.execute();
    		objRs = objCstmt.getCursor(2);
    		objTurbinGroupLLena = new Vector();
    		while (objRs.next()){
        		TurbineGroup objTurbineGroup = new TurbineGroup();
        		objTurbineGroup.setGroupId(new BigDecimal(objRs.getString(1)));
        		iObjLog.debug(" Datos de Group Id "+objTurbineGroup.getGroupId());
        		objTurbineGroup.setGroupName(objRs.getString(2));
        		objTurbinGroupLLena.add(objTurbineGroup);
        	}
    	}catch (TorqueException aObjException){
    	    iObjLog.error("Error al ejecutar PL GETDEPTOS....", aObjException);
    	    throw aObjException;
    	}catch (SQLException aObjException){
    	    iObjLog.error("Error al ejecutar PL GETDEPTOS....", aObjException);
    	    throw aObjException;
    	}finally {
    	    objRs.close();
    	    objCstmt.close();
    	    //Torque.closeConnection(con);
    	    if(con!=null)con.close();
    	}
    	return objTurbinGroupLLena;    	
    }
    
*/    
    /**
     * Este metodo esta encargado de verificar
     * si el usuario que es válido y pertenece a la unidad
     * que se envia como parametro
     * @param aStrUserName Usuario
     * @param aIntUserID ID Usuario
     * @param aStrRol Permiso
     * @param idgrupo Permiso
     * @return
     * @throws Exception
     */
    public static boolean validaUsuarioUnidad(
    		String aStrUserName, String aStrPassword,  
    		String idgrupo) throws Exception{
    	iObjLog.debug("tieneRolUsuarioGrupo:AUTORIZANDO...Usuario="+aStrUserName);
    	boolean autoriza = false;
    	try{
    		User usuario = TurbineSecurity.getAuthenticatedUser(aStrUserName,aStrPassword);
    		//si es un usuario valido y tiene el rol indicado true
    		if(usuario!=null) {
    			TurbineUser objTurbineUser = getUserByName(aStrUserName);
    			autoriza = isUsuarioGrupo(objTurbineUser.getUserId(), Integer.parseInt(idgrupo));
    		}else {
    			iObjLog.debug("El usuario o el password son invalidos");
    		}
    	}catch ( TurbineSecurityException aObjException ){        	
            iObjLog.error("TurbineSecurityException ", aObjException);
            return autoriza;
        }catch ( Exception aObjException ){
        	iObjLog.error("tieneRolUsuarioGrupo:ERROR", aObjException);        	
        	return autoriza;        	
        }
        iObjLog.debug("tieneRolUsuarioGrupo:regresa|"+autoriza+"|");
    	return autoriza;    		    	   
    }
    
    /**
	* Este metodo establece comprueba si el usuario pertenece a
	* un grupo dadp
	* 
	*/
	private static boolean isUsuarioGrupo(int aIntIdeUser, int idGrupo)
	throws Exception{
		boolean existe = false;
		try{
			Criteria objCri	= new Criteria();
			objCri.add(TurbineUserGroupRolePeer.USER_ID, aIntIdeUser);
			objCri.add(TurbineUserGroupRolePeer.GROUP_ID, idGrupo);
			Collection roles = TurbineUserGroupRolePeer.doSelect(objCri);
			if(roles!= null  && roles.size() > 0){
				existe = true;
			}
			return existe;
		}catch(TorqueException aObjException){
			iObjLog.error("isUsuarioGrupo:ERROR", aObjException);
			throw aObjException;
		}
	}

	public static List userRoles(int intUserId, int  idgrupo)throws Exception{
		
		List lObjroles= new ArrayList();
		
		Criteria objCri	= new Criteria();
		objCri.add(TurbineUserGroupRolePeer.USER_ID, intUserId);
		objCri.add(TurbineUserGroupRolePeer.GROUP_ID, idgrupo);
		Collection roles = TurbineUserGroupRolePeer.doSelect(objCri);
		Iterator it = roles.iterator();
		//Valida que se hayan encontrado roles para el permiso y usuario dado
		
			while(it.hasNext()) {
				TurbineUserGroupRole ugr = (TurbineUserGroupRole)it.next();
				String rol = ugr.getTurbineRole().getName().trim();
				iObjLog.debug("userRoles: " + rol );
				lObjroles.add(rol);
				
			}
return lObjroles;
}
	
    
}
