package mx.com.web2lab.tools.seguridad;

//Java
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mx.com.web2lab.util.Formatos;
import mx.com.web2lab.util.SCambiaPass;
import mx.com.web2lab.util.seguridad.BGrupoMenuUtil;
import mx.com.web2lab.util.seguridad.BTurbineGroup;
import mx.com.web2lab.backend.hbm.om.sistema.TurbineSistema;
import mx.com.web2lab.backend.util.beans.sistema.UsuarioBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.util.BasePeer;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.Group;
import org.apache.turbine.om.security.Permission;
import org.apache.turbine.om.security.Role;
import org.apache.turbine.om.security.User;
import org.apache.turbine.om.security.peer.TurbineUserPeer;
import org.apache.turbine.services.pull.ApplicationTool;
import org.apache.turbine.services.security.TurbineSecurity;

import org.apache.turbine.services.security.torque.om.TurbineGroup;
import org.apache.turbine.services.security.torque.om.TurbineGroupPeer;
import org.apache.turbine.services.security.torque.om.TurbinePermissionPeer;
import org.apache.turbine.services.security.torque.om.TurbineRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.pool.Recyclable;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.util.security.GroupSet;
import org.apache.turbine.util.security.PermissionSet;
import org.apache.turbine.util.security.RoleSet;

import com.workingdogs.village.DataSetException;
import com.workingdogs.village.Record;

public class CSeguridadTool implements Recyclable, ApplicationTool, Serializable{
	
/**Log de la aplicaci&oacute;n*/
private static Log iObjLog = LogFactory.getLog(CSeguridadTool.class);

  /**
   * The object containing request specific data
   */
  private RunData iObjDatos;
  /**
   * A Group object for use within the Flux API.
   */
  private Group iObjGrupo = null;
  
  private TurbineSistema iObjSistema = null;

  /**
   * A Issue object for use within the Flux API.
   */
  private Role iObjRole = null;

  /**
   * A Permission object for use within the Flux API.
   */
  private Permission iObjPermiso = null;

  /**
   * A User object for use within the Flux API.
   */
  private User iObjUsuario = null;
  
  /** 
   * Roles que dependiendo del usuario y del grupo
   * aun no han sido asignados
   */
  private List rolesSinAsignar = null;
  
  /** 
   * Roles que dependiendo del usuario y del grupo
   * ya fueron sido asignados
   */
  private List rolesAsignados = null;

  /**
   * Comment
   * @param data comment
   */
  public void init(Object aObjDatos){
      this.iObjDatos = (RunData)aObjDatos;
  }

  /**
   * nulls out the issue and user objects
   */
  public void refresh(){
      // do not need since it is a request tool
  }

  /**
   * Constructor does initialization stuff
   */
  public CSeguridadTool(){
  }

  /**
   * 
   * @throws Exception comment
   */
  public Group getGrupo() throws Exception{
  	iObjLog.debug("OBTENIENDOELGRUPO...");
      if (iObjGrupo == null)
      {
      	  iObjLog.debug("ELOBJETOGRUPOESNULO...");
          String strNombreGrupo = iObjDatos.getParameters().getString("strNombreGrupo");
          iObjLog.debug("ELGRUPORECUPERADOES:|"+strNombreGrupo+"|");          
          if (strNombreGrupo == null || strNombreGrupo.length() == 0){
          	iObjLog.debug("OBTENIENDOINSTANCIANULADEGRUPO...");
          	iObjGrupo = TurbineSecurity.getGroupInstance(null);
          }
          else{
          	iObjLog.debug("OBTENIENDOINSTANCIAPORNOMBRE...");
          	iObjGrupo = TurbineSecurity.getGroupByName(strNombreGrupo);
          }
      }
      iObjLog.debug("OBJETOGRUPORETORNADO:|"+iObjGrupo+"|");
      return iObjGrupo;
  }

  /**
   * Obtiene el modo en el que se desplegara la p�gina consecuente
   */
  public String getModo(){
      return iObjDatos.getParameters().getString("strModo");
  }

	/**
	 * El metodo obtiene el nombre del grupo del menu
	 * @param strNombre
	 */

	public String getGrupoDeMenu(){
		return iObjDatos.getParameters().getString("strNombre");
	}

	/**
	 * El tool getId se implementa para obtener un entero del query y pasarlo al VM
	 * @param id
	 */
	public int getId(){
		return iObjDatos.getParameters().getInt("id");
	}

  /**
   * Comment
   * @return comment
   * @throws Exception comment
   */
  public Object[] getGrupos() throws Exception{  	  	  
    Criteria criteria = new Criteria();        
    iObjLog.debug("TurbineGroupPeer.GROUP_NAME:|"+TurbineGroupPeer.GROUP_NAME+"|");
    Criteria.Criterion crit = criteria.getNewCriterion(
            TurbineGroupPeer.GROUP_NAME, 
            "1=1 ORDER BY TURBINE_GROUP.GROUP_NAME", Criteria.CUSTOM);
    criteria.add(crit);  
    List objListaGrupos = TurbineGroupPeer.doSelect(criteria);
    List objLstGroups = new ArrayList();
    if(objListaGrupos!= null && objListaGrupos.size()>0){
    	Iterator itera = objListaGrupos.iterator();
    	TurbineGroup grupo = null;     	
    	while(itera.hasNext()){
    		grupo = (TurbineGroup)itera.next();    		
    		iObjLog.debug("GRUPO:|"+grupo+"|");    		    		
    		objLstGroups.add(grupo);
    	}    	
    } 
    return objLstGroups.toArray();
  }
  
  /**
   * Lista  de  grupos no asignados a un usuario
   * @param String id_user
   * @return un  arreglo de objetos   del  tipo turbine_group
   * @throws Exception comment
   */
  public Object[] getGruposNoAsignados(String loginName) throws Exception
  {  	  	  
	  List objLstGroups = new ArrayList();
		StringBuffer bufferQuery = new StringBuffer("select " +
				"tg0.GROUP_ID as grupoId, " +
				"tg0.GROUP_NAME as name " +
				"from turbine_group tg0 " +
				"where tg0.GROUP_ID not in ( " +
				"select distinct tg.GROUP_ID " +
				"from turbine_group tg " +
				"inner join turbine_user_group_role tugr on tugr.GROUP_ID = tg.GROUP_ID " +
				"inner join turbine_user tu on tu.USER_ID = tugr.USER_ID " +
				"and tu.LOGIN_NAME='" + loginName + "') order by tg0.GROUP_NAME asc");
		iObjLog.debug(">>>><<<< getGruposNoAsignados(String " + loginName + " : )" + bufferQuery.toString());
		Iterator iterListaGrupos = (BasePeer.executeQuery(bufferQuery.toString())).iterator();
		
		while (iterListaGrupos.hasNext())
		{
			Record element = (Record) iterListaGrupos.next();
			TurbineGroup grupo = new TurbineGroup();
			
			grupo.setGroupId(element.getValue("grupoId").asInt());
			grupo.setName(element.getValue("name").asString());
			
			objLstGroups.add(grupo);
		} 
	    return objLstGroups.toArray();
  }
  
  /**
   * Lista  de  grupos Asignados a un usuario
   * @param String id_user
   * @return un  arreglo de objetos   del  tipo turbine_group
   * @throws Exception comment
   */
  public Object[] getGruposAsignados(String loginName) throws Exception
  {
	List objLstGroups = new ArrayList();
	StringBuffer bufferQuery = new StringBuffer("select  distinct " +
			"tg.GROUP_ID as grupoId, " +
			"tg.GROUP_NAME as name " +
			"from turbine_group tg " +
			"inner join turbine_user_group_role tugr on tugr.GROUP_ID = tg.GROUP_ID " +
			"inner join turbine_user tu on tu.USER_ID = tugr.USER_ID " +
			"	and tu.LOGIN_NAME='" + loginName+ "'");
	iObjLog.debug(">>>><<<< getGruposAsignados(String " + loginName + " : )" + bufferQuery.toString());
	Iterator iterListaGrupos = (BasePeer.executeQuery(bufferQuery.toString())).iterator();
	
	while (iterListaGrupos.hasNext())
	{
		Record element = (Record) iterListaGrupos.next();
		TurbineGroup grupo = new TurbineGroup();
		
		grupo.setGroupId(element.getValue("grupoId").asInt());
		grupo.setName(element.getValue("name").asString());
		
		objLstGroups.add(grupo);
	} 
    return objLstGroups.toArray();
  }

  /**
   * Lista  de  Roles no asignados a un usuario
   * @param String id_user
   * @return un  arreglo de objetos   del  tipo turbine_group
   * @throws Exception comment
   */
  public Object[] getRolesNoAsignados(String loginName) throws Exception
  {  	  	  
	  List objLstGroups = new ArrayList();
		StringBuffer bufferQuery = new StringBuffer("select " +
				"tg0.ROLE_ID as roleId, " +
				"tg0.ROLE_NAME as name " +
				"from turbine_ROLE tg0 " +
				"where tg0.ROLE_ID not in (  " +
				"select distinct tg.ROLE_ID  " +
				"from turbine_ROLE tg " +
				"inner join turbine_user_group_role tugr on tugr.ROLE_ID = tg.ROLE_ID " +
				"inner join turbine_user tu on tu.USER_ID = tugr.USER_ID " +
				"and tu.LOGIN_NAME='" + loginName + "')");
		iObjLog.debug(">>>><<<< getRolesNoAsignados(String " + loginName + " : )" + bufferQuery.toString());
		Iterator iterListaGrupos = (BasePeer.executeQuery(bufferQuery.toString())).iterator();
		
		while (iterListaGrupos.hasNext())
		{
			Record element = (Record) iterListaGrupos.next();
			TurbineGroup grupo = new TurbineGroup();
			
			grupo.setGroupId(element.getValue("roleId").asInt());
			grupo.setName(element.getValue("name").asString());
			
			objLstGroups.add(grupo);
		} 
	    return objLstGroups.toArray();
  }
  
  /**
   * Lista  de  Roles Asignados a un usuario
   * @param String id_user
   * @return un  arreglo de objetos   del  tipo turbine_group
   * @throws Exception comment
   */
  public Object[] getRolesAsignados(String loginName) throws Exception
  {
	List objLstGroups = new ArrayList();
	StringBuffer bufferQuery = new StringBuffer("select  distinct " +
			"tg.ROLE_ID as roleId, " +
			"tg.ROLE_NAME as name " +
			"from turbine_ROLE tg " +
			"inner join turbine_user_group_role tugr on tugr.ROLE_ID = tg.ROLE_ID  " +
			"inner join turbine_user tu on tu.USER_ID = tugr.USER_ID " +
			"	and tu.LOGIN_NAME='" + loginName+ "'");
	iObjLog.debug(">>>><<<< getRolAsignados(String " + loginName + " : )" + bufferQuery.toString());
	Iterator iterListaGrupos = (BasePeer.executeQuery(bufferQuery.toString())).iterator();
	
	while (iterListaGrupos.hasNext())
	{
		Record element = (Record) iterListaGrupos.next();
		TurbineGroup grupo = new TurbineGroup();
		
		grupo.setGroupId(element.getValue("roleId").asInt());
		grupo.setName(element.getValue("name").asString());
		
		objLstGroups.add(grupo);
	} 
    return objLstGroups.toArray();
  }
  

  /**
   * Metodo que obtiene un role
   * @return iObjRole
   * @throws Exception comment
   */
  public Role getRole() throws Exception{
      if (iObjRole == null){
          String strNombreRole = iObjDatos.getParameters().getString("strNombreRole");

          if (strNombreRole == null || strNombreRole.length() == 0){
          	iObjRole = TurbineSecurity.getRoleInstance(null);
          }
          else{
          	iObjRole = TurbineSecurity.getRoleByName(strNombreRole);
          }
      }
      return iObjRole;
  }

  /**
   * Comment
   * @return Role[] arreglo de Roles
   * @throws Exception comment
   */
  public Role[] getRoles() throws Exception{
      Criteria objCriteria = new Criteria();
      return TurbineSecurity.getRoles(objCriteria).getRolesArray();
  }

  /**
   * Comment
   * @return comment
   * @throws Exception comment
   */
  public Permission getPermiso() throws Exception{
      if (iObjPermiso == null){
          String strNombrePermiso = iObjDatos.getParameters().getString("strNombrePermiso");

          if (strNombrePermiso == null || strNombrePermiso.length() == 0){
          	iObjPermiso = TurbineSecurity.getPermissionInstance(null);
          }
          else{
          	iObjPermiso = TurbineSecurity.getPermissionByName(strNombrePermiso);
          }
      }
      return iObjPermiso;
  }

  /**
   * Get all permissions.
   * @return comment
   * @throws Exception comment
   */
  public Permission[] getPermisos() throws Exception{
      return TurbineSecurity.getAllPermissions().getPermissionsArray();
  }


  /**
   * Este metodo esta encargado de obtener el usuario de la
   * a traves del campo de texto txtNombreusuario del VM Usuarios.vm
   * @return User gobjUsuario
   * @throws Exception
   */
  public User getUsuario() throws Exception{
      if (iObjUsuario == null){
          String strNombreUsuario = iObjDatos.getParameters().getString("txtNombreUsuario");

          if (strNombreUsuario == null || strNombreUsuario.length() == 0){
          	iObjUsuario = TurbineSecurity.getUserInstance();
          }
          else{
          	iObjUsuario = TurbineSecurity.getUser(strNombreUsuario);
          }
      }
      return iObjUsuario;
  }

  /**
   * Comment
   * @return comment
   * @throws Exception comment
   */
  public AccessControlList getACL() throws Exception{
      return  TurbineSecurity.getACL(getUsuario());
  }
  
  /**
   * Comment
   * @return comment
   * @throws Exception comment
   */
  public AccessControlList getACL(String aStrUser) throws Exception{
      return  TurbineSecurity.getACL(getUsuario(aStrUser));
  }


  /**
   * Este metodo se encarga de obtener el(los) usuarios
   * dependiendo del criterio de busqueda especificado
   * en Usuarios.vm
   * @return List iobjUsuarios
   */
  public List getUsuarioPorCriterio(){
  	boolean bolBandera=false;
  	List iObjUsuarios = null;
  	String strNombreUsuario = iObjDatos.getParameters().getString("txtNombreUsuario");
  	String strNombre = iObjDatos.getParameters().getString("txtNombre");
  	String strApellido = iObjDatos.getParameters().getString("txtApellido");
  	String strGrupo = iObjDatos.getParameters().getString("cboGrupo");
  	String strSelTodos = iObjDatos.getParameters().getString("chkSelTodos");
  	String strCriterio = "";
  	try{
      	Criteria objCriteria = new Criteria();
      	if(strSelTodos != null && strSelTodos.length() > 0){
      	   	bolBandera=true;
      	}
      	else{
      		if(strNombreUsuario != null && strNombreUsuario.length() > 0){
	        		objCriteria.and(TurbineUserPeer.USERNAME,
	        				(Object)("%"+strNombreUsuario+"%"),
	        				Criteria.LIKE);
	        		strCriterio += " and login_name like '%" + strNombreUsuario + "%'";
	        		bolBandera=true;
	        	}
	        	if(strNombre != null && strNombre.length() > 0){
	        		objCriteria.and(TurbineUserPeer.FIRST_NAME,
	        				(Object)("%"+strNombre+"%"),
	        				Criteria.LIKE);
	        		strCriterio += " and first_name like '%" + strNombre + "%'";
	        		bolBandera=true;
	        	}
	        	if(strApellido != null && strApellido.length() > 0){
	        		objCriteria.and(TurbineUserPeer.LAST_NAME,
	        				(Object)("%"+strApellido+"%"),
	        				Criteria.LIKE);
	        		strCriterio += " and last_name like '%" + strApellido + "%'";
	        		bolBandera=true;
	        	}
	        	if(strGrupo != null && strGrupo.length() > 0){
	        	    String objStrQuery = "SELECT DISTINCT TURBINE_USER.USER_ID, " +
	        	        				 "TURBINE_USER.CONFIRM_VALUE, TURBINE_USER.EMAIL, " +
	        	        				 "TURBINE_USER.FIRST_NAME, TURBINE_USER.LOGIN_NAME, " + 
	        	        				 "TURBINE_USER.LAST_NAME ||' -- '|| TURBINE_GROUP.GROUP_NAME LAST_NAME, TURBINE_USER.PASSWORD_VALUE, " + 
	        	        				 "TURBINE_USER.MODIFIED, TURBINE_USER.CREATED, " + 
	        	        				 "TURBINE_USER.LAST_LOGIN " + 
	        	        				 "FROM TURBINE_USER, TURBINE_USER_GROUP_ROLE, TURBINE_GROUP " +
	        	        				 "WHERE TURBINE_USER.USER_ID=TURBINE_USER_GROUP_ROLE.USER_ID " +
	        	        				 " AND TURBINE_USER_GROUP_ROLE.GROUP_ID = " + strGrupo + strCriterio+
	        	        				 " AND TURBINE_USER_GROUP_ROLE.GROUP_ID = TURBINE_GROUP.GROUP_ID";
	        	    List objResultadosGrupo = new ArrayList();
	        	    iObjLog.debug("QueryJoin="+objStrQuery);
	        	    List objResultados = BasePeer.executeQuery(objStrQuery);
	        	    if(objResultados != null && objResultados.size() > 0){
	        	        Iterator objItera = objResultados.iterator();
	        	        while(objItera.hasNext()){
	        	            Record objAux = (Record)objItera.next();
	        	            TurbineUser objAgrega = new TurbineUser();
	        	            objAgrega.setUserId(objAux.getValue("USER_ID").asInt());
	        	            objAgrega.setConfirmed(objAux.getValue("CONFIRM_VALUE").asString());
	        	            objAgrega.setEmail(objAux.getValue("EMAIL").asString());
	        	            objAgrega.setFirstName(objAux.getValue("FIRST_NAME").asString());
	        	            objAgrega.setUserName(objAux.getValue("LOGIN_NAME").asString());
	        	            objAgrega.setLastName(objAux.getValue("LAST_NAME").asString());
	        	            objAgrega.setPassword(objAux.getValue("PASSWORD_VALUE").asString());
	        	            objAgrega.setModified(objAux.getValue("MODIFIED").asDate());
	        	            objAgrega.setCreateDate(objAux.getValue("CREATED").asDate());
	        	            objAgrega.setLastLogin(objAux.getValue("LAST_LOGIN").asDate());	        	            
	        	            objResultadosGrupo.add(objAgrega);
	        	            
	        	        }
	        	    }
	        	    return objResultadosGrupo;
	        	}
	        }
      	if(bolBandera){
      	    iObjUsuarios = TurbineSecurity.getUserList(objCriteria);
        	iObjLog.debug("Entro en el iObjUsuarios");

      	}
          return iObjUsuarios;
      }
      catch (Exception ex){
      	iObjLog.error("Exception caught:", ex);
          return null;
      }
  }
  
  /**
   * Este metodo se encarga de obtener el(los) usuarios
   * dependiendo del criterio de busqueda especificado
   * en Usuarios.vm
   * @return List iobjUsuarios
   */
  public List getUsuarioPersistente(){
  	boolean bolBandera=false;
  	List iObjUsuarios = null;
  	String strNombreUsuario = iObjDatos.getParameters().getString("txtNombreUsuario");
  	String strNombre = iObjDatos.getParameters().getString("txtNombre");
  	String strApellido = iObjDatos.getParameters().getString("txtApellido");
  	String strGrupo = iObjDatos.getParameters().getString("cboGrupo");
  	String strSelTodos = iObjDatos.getParameters().getString("chkSelTodos");
  	String strCriterio = "";
  	try{
  		Criteria objCriteria = new Criteria();
      	if(strSelTodos != null && strSelTodos.length() > 0){
      	   	bolBandera=true;
      	} else 
      	{
	      		if(strNombreUsuario != null && strNombreUsuario.length() > 0){
		        		objCriteria.and(TurbineUserPeer.USERNAME,
		        				(Object)("%"+strNombreUsuario+"%"),
		        				Criteria.LIKE);
		        		strCriterio += " and login_name like '%" + strNombreUsuario + "%'";
		        		bolBandera=true;
		        	}
		        	if(strNombre != null && strNombre.length() > 0){
		        		objCriteria.and(TurbineUserPeer.FIRST_NAME,
		        				(Object)("%"+strNombre+"%"),
		        				Criteria.LIKE);
		        		strCriterio += " and first_name like '%" + strNombre + "%'";
		        		bolBandera=true;
		        	}
		        	if(strApellido != null && strApellido.length() > 0){
		        		objCriteria.and(TurbineUserPeer.LAST_NAME,
		        				(Object)("%"+strApellido+"%"),
		        				Criteria.LIKE);
		        		strCriterio += " and last_name like '%" + strApellido + "%'";
		        		bolBandera=true;
		        	}
		        	if(strGrupo != null && strGrupo.length() > 0){
		        	    String objStrQuery = "SELECT DISTINCT TURBINE_USER.USER_ID, " +
		        	        				 "TURBINE_USER.CONFIRM_VALUE, TURBINE_USER.EMAIL, " +
		        	        				 "TURBINE_USER.FIRST_NAME, TURBINE_USER.LOGIN_NAME, " + 
		        	        				 "TURBINE_USER.LAST_NAME ||' -- '|| TURBINE_GROUP.GROUP_NAME LAST_NAME, TURBINE_USER.PASSWORD_VALUE, " + 
		        	        				 "TURBINE_USER.MODIFIED, TURBINE_USER.CREATED, " + 
		        	        				 "TURBINE_USER.LAST_LOGIN " + 
		        	        				 "FROM TURBINE_USER, TURBINE_USER_GROUP_ROLE, TURBINE_GROUP " +
		        	        				 "WHERE TURBINE_USER.USER_ID=TURBINE_USER_GROUP_ROLE.USER_ID " +
		        	        				 " AND TURBINE_USER_GROUP_ROLE.GROUP_ID = " + strGrupo + strCriterio+
		        	        				 " AND TURBINE_USER_GROUP_ROLE.GROUP_ID = TURBINE_GROUP.GROUP_ID";
		        	    List objResultadosGrupo = new ArrayList();
		        	    iObjLog.debug("QueryJoin="+objStrQuery);
		        	    List objResultados = BasePeer.executeQuery(objStrQuery);
		        	    if(objResultados != null && objResultados.size() > 0){
		        	        Iterator objItera = objResultados.iterator();
		        	        while(objItera.hasNext()){
		        	            Record objAux = (Record)objItera.next();
		        	            TurbineUser objAgrega = new TurbineUser();
		        	            objAgrega.setUserId(objAux.getValue("USER_ID").asInt());
		        	            objAgrega.setConfirmed(objAux.getValue("CONFIRM_VALUE").asString());
		        	            objAgrega.setEmail(objAux.getValue("EMAIL").asString());
		        	            objAgrega.setFirstName(objAux.getValue("FIRST_NAME").asString());
		        	            objAgrega.setUserName(objAux.getValue("LOGIN_NAME").asString());
		        	            objAgrega.setLastName(objAux.getValue("LAST_NAME").asString());
		        	            objAgrega.setPassword(objAux.getValue("PASSWORD_VALUE").asString());
		        	            objAgrega.setModified(objAux.getValue("MODIFIED").asDate());
		        	            objAgrega.setCreateDate(objAux.getValue("CREATED").asDate());
		        	            objAgrega.setLastLogin(objAux.getValue("LAST_LOGIN").asDate());	        	            
		        	            objResultadosGrupo.add(objAgrega);
		        	        }
		        	    }
		        	    return objResultadosGrupo;
		        	}
	        }      	
	      	if(bolBandera) 
	      	{
		      		if(strNombreUsuario != null && strNombreUsuario.length() > 0){
		        		strCriterio += " and login_name like '%" + strNombreUsuario + "%'";
		        	}
		        	if(strNombre != null && strNombre.length() > 0){
		        		strCriterio += " and first_name like '%" + strNombre + "%'";
		        	}
		        	if(strApellido != null && strApellido.length() > 0){
		        		strCriterio += " and last_name like '%" + strApellido + "%'";
		        	}
	        	    String objStrQuery = "SELECT DISTINCT TURBINE_USER.USER_ID, " +
	        	        				 "TURBINE_USER.LOGIN_NAME, " + 
	        	        				 "TURBINE_USER.FIRST_NAME, " + 
	        	        				 "TURBINE_USER.LAST_NAME, " + 
	        	        				"TURBINE_USER_GROUP_ROLE.GROUP_ID, " +
	        	        				// "TURBINE_USER_GROUP_ROLE.ROLE_ID, " +
	        	        				 "TURBINE_USER.LAST_LOGIN " + 
	        	        				 "FROM TURBINE_USER, TURBINE_USER_GROUP_ROLE, TURBINE_GROUP " +
	        	        				 "WHERE TURBINE_USER.USER_ID=TURBINE_USER_GROUP_ROLE.USER_ID " + strCriterio +
	        	        				 " AND TURBINE_USER_GROUP_ROLE.GROUP_ID = TURBINE_GROUP.GROUP_ID";
	        	    iObjUsuarios = new ArrayList();
	        	    List objResultados = BasePeer.executeQuery(objStrQuery);
	        	    if(objResultados != null && objResultados.size() > 0){
	        	        Iterator objItera = objResultados.iterator();
	        	        while(objItera.hasNext()){
	        	            Record objAux = (Record)objItera.next();
	        	            TurbineUser objAgrega = new TurbineUser();
	        	            objAgrega.setUserId(objAux.getValue("USER_ID").asInt());
	        	            objAgrega.setUserName(objAux.getValue("LOGIN_NAME").asString());
	        	            objAgrega.setFirstName(objAux.getValue("FIRST_NAME").asString());
	        	            objAgrega.setLastName(objAux.getValue("LAST_NAME").asString());
	        	            objAgrega.setConfirmed(objAux.getValue("GROUP_ID").asString());
	        	            //objAgrega.setEmail(objAux.getValue("ROLE_ID").asString());
	        	            iObjUsuarios.add(objAgrega);
	        	        }
	        	    }
	        	     
	      	}
	      	if(iObjUsuarios.size()==0){
	      		iObjUsuarios = TurbineSecurity.getUserList(objCriteria);	 
	      	}
	      	
	      	return iObjUsuarios;
       
  	}
      catch (Exception ex){
      	iObjLog.error("Exception caught:", ex);
          return null;
      }
  }

  

  /**
   * Select all the roles and place them in an array
   * @return comment
   */
  public Role[] getRolesPorCriteria(){
  	boolean bolbandera=false;
  	RoleSet objRoles = null;
  	Role [] objRolesArreglo = null;
  	String strRoleNombre = this.getTxtRole();
  	String strSeleccionaTodo = this.getChkSel();
      try{
      	Criteria objCriteria = new Criteria();
      	if(strSeleccionaTodo != null && strSeleccionaTodo.length() > 0){
      	   	bolbandera=true;
      	}
      	else{
	        	if(strRoleNombre != null && strRoleNombre.length() > 0){
                  objCriteria.and(TurbineRolePeer.ROLE_NAME, (Object)("%"+strRoleNombre+"%"), Criteria.LIKE);
	        		bolbandera=true;
	        	}
	        }
      	if(bolbandera){
      		objRoles = TurbineSecurity.getRoles(objCriteria);
      		objRolesArreglo = objRoles.getRolesArray();
      		if (objRolesArreglo!=null && Array.getLength(objRolesArreglo)==0){
      			objRolesArreglo=null;
      		}
      	}
          return objRolesArreglo;
      }
      catch (Exception ex){
      	iObjLog.error("Exception caught:", ex);
          return null;
      }
  }

  /**
   * Select all the permissions and place them in an array
   * @return comment
   */
  public Permission[] getPermisosPorCriteria(){
  	boolean bolbandera=false;
  	PermissionSet objPermisos = null;
  	Permission [] objPermisosArreglo = null;
  	String strPermisoNombre = this.getTxtPermiso();
  	String strSeleccionaTodo = this.getChkSel();
      try{
      	Criteria objCriteria = new Criteria();      	
      	if(strSeleccionaTodo != null && strSeleccionaTodo.length() > 0){
      	   	bolbandera=true;
      	}
      	else{
	        	if(strPermisoNombre != null && strPermisoNombre.length() > 0){
	        		objCriteria.and(TurbinePermissionPeer.PERMISSION_NAME,
	        		(Object)("%"+strPermisoNombre+"%"), Criteria.LIKE);	        		
	        		bolbandera=true;
	        	}
	        }
      	if(bolbandera){
      		//objCriteria.addAscendingOrderByColumn("TURBINE_PERMISSION.PERMISSION_NAME");
      		objPermisos = TurbineSecurity.getPermissions(objCriteria);
      		objPermisosArreglo = objPermisos.getPermissionsArray();
      		if (objPermisosArreglo!=null && Array.getLength(objPermisosArreglo)==0){
      			objPermisosArreglo=null;
      		}
      	}
          return objPermisosArreglo;
      }
      catch (Exception ex){
      	iObjLog.error("Exception caught:", ex);
          return null;
      }
  }

  /*public Permission[] getPermissionsByCriteria()
  	throws Exception{
  		PermissionSet permissions = null;
	    	Permission [] permissionsArray = null;
	    	Criteria criteria = new Criteria();
			permissions = TurbineSecurity.getPermissions(criteria);
			permissionsArray = permissions.getPermissionsArray();
	        return permissionsArray;
  }*/

  /**
   * Este metodo se encarga de obtener el(los) grupos
   * dependiendo del criterio de busqueda especificado
   * en Grupos.vm
   * @return List objGruposArreglo
   */
  public Group[] getGrupoPorCriterio(){
  	boolean bolBandera=false;
  	GroupSet objTurbineGrupos = null;
  	Group [] objGruposArreglo = null;
  	String strNombreGrupo = this.getTxtGrupo();
  	String strSelTodos = this.getChkSelTodos();
      try{
      	Criteria objCriteria = new Criteria();
      	if(strSelTodos != null && strSelTodos.length() > 0){
      	   	bolBandera=true;
      	}
      	else{
	        	if(strNombreGrupo != null && strNombreGrupo.length() > 0){
	        		objCriteria.and(TurbineGroupPeer.GROUP_NAME,
	        		(Object)("%"+strNombreGrupo+"%"), Criteria.LIKE);
	        		bolBandera=true;
	        	}
	        }
      	if(bolBandera){
      		objTurbineGrupos = TurbineSecurity.getGroups(objCriteria);
      		objGruposArreglo = objTurbineGrupos.getGroupsArray();
      		if (objGruposArreglo!=null && Array.getLength(objGruposArreglo)==0){
      			objGruposArreglo=null;
      		}
      	}
          return objGruposArreglo;
      }
      catch (Exception ex){
      	iObjLog.error("Exception caught:", ex);
          return null;
      }
  }



  /**
   * Return a string with all the roles and permissions
   * for each group.
   * @return comment
   */
  public String debugACL(){
      StringBuffer objStringBuffer = new StringBuffer();
      try{
          User objUsuario = getUsuario();
          if (objUsuario != null){
              AccessControlList objcontrolaaccesolista = getACL();
              Object[] groups = getGrupos();
              //Group[] groups = getGrupos();
              objStringBuffer.append("{" + objUsuario.getName());
              for (int inti = 0; inti < groups.length; inti++){
                  Group group = (Group)groups[inti];
                  objStringBuffer.append(" group:" + group.getName());
                  Iterator objiterador = objcontrolaaccesolista.getRoles(group).iterator();
                  objStringBuffer.append(" roles {");
                  while (objiterador.hasNext()){
                      Role objrole = (Role) objiterador.next();
                      objStringBuffer.append(objrole.getName() + " ");
                  }
                  objStringBuffer.append("}");
                  objiterador = objcontrolaaccesolista.getPermissions(group).iterator();
                  objStringBuffer.append(" permissions {");
                  while (objiterador.hasNext()){
                      Permission objpermiso = (Permission) objiterador.next();
                      objStringBuffer.append(objpermiso.getName() + " ");
                  }
                  objStringBuffer.append("}");
              }
              objStringBuffer.append("}");
          }
          else{
          	objStringBuffer.append("User is null");
          }
      }
      catch (Exception ex){
      	iObjLog.error("Exception caught", ex);
      	objStringBuffer.append("Exception caught:" + ex.toString());
      }

      return objStringBuffer.toString();
  }
  
  /*
  *
  * este tool getGrupoMenu(), obtiene los valores de la tabla GRUPO_MENU
  * y al obtener el dato del checkbox select_all, se ayuda de una clase utilitaria
  * para sacar los datos en una lista llamada tabla
  * @ return tabla,
  */
 public List getGrupoMenu()throws Exception{
 	boolean bolbandera=false;
 	List objtabla= null;
 	//List grupo= null;
 	String strGrupoMenu = iObjDatos.getParameters().getString("txtGrupoMenu");
 	String strSeleccionaTodo = iObjDatos.getParameters().getString("chkSeleccionaTodo");
 	if(strSeleccionaTodo != null && strSeleccionaTodo.length() > 0){
 		iObjLog.debug(">>>>>>>>>>>>>ENTR� al tool");
 		bolbandera=true;
 		
 	}
 	else{
 		if(strGrupoMenu != null && strGrupoMenu.length() > 0){
 			BGrupoMenuUtil objBGrupoMenuUtil = new BGrupoMenuUtil();
 			objtabla = objBGrupoMenuUtil.getGrupoUtil(strGrupoMenu);
 		}
 	}
 	if(bolbandera){
 		BGrupoMenuUtil objBGrupoMenuUtil = new BGrupoMenuUtil();
 		objtabla = objBGrupoMenuUtil.getGrupoMenuUtil();
 	}
 	return objtabla;
  }

  /**
   * Este metodo obtiene los valores de un bean para utilizar la pantalla 
   * CamContras cambio de contrase&ntilde;a.
   * @return UsuarioBean
   */
  public UsuarioBean getInformacionUsuario() throws Exception{
  	String strLoginName = iObjDatos.getParameters().getString("username");
  	UsuarioBean objUsuBean =  new SCambiaPass().getDatosUsuario(strLoginName);

 /*
 // Lo que se intenta aqui es castear los Grupos a TurbineGroup 
 public String getNombreGrupo(Group aObjGrupo){
 	String strGrupo ="";
 	TurbineGroup objGrupo = (TurbineGroup)aObjGrupo;
 	strGrupo = (String)objGrupo.getAttribute(TurbineGroupPeer.GROUP_NAME);
 	return strGrupo; 
 }
 
 public String getIdGrupo(Group aObjGrupo){
 	String strGrupo ="";
 	TurbineGroup objGrupo = (TurbineGroup)aObjGrupo;
 	strGrupo = (String)objGrupo.getAttribute(TurbineGroupPeer.GROUP_ID);
 	return strGrupo; 
 }
 */


  	return objUsuBean;
  }

  /**
   * Metodo para formatear un date a un string del tipo mes dia y a�o
   */
  public String getFechaModiParaDate(Date aStrFecha) 
  throws Exception{
		if (aStrFecha != null){
			Formatos objFormato = new Formatos();
			return objFormato.getFecha(aStrFecha);
		}
		return null;
	}
  
  /**
   * Comment
   * @return comment
   * @throws Exception comment
   */
  public List getGruposQuery() throws Exception{
  	String strQuery = "Select GROUP_ID, GROUP_NAME from TURBINE_GROUP ORDER BY GROUP_NAME ASC ";
  	iObjLog.debug("  + * + * + * + * + * + * Query ejecutado  "+strQuery);
  	List objGrupos = BasePeer.executeQuery(strQuery);
  	iObjLog.debug(" Numero de opciones recuperadas "+objGrupos.size());
  	List objGruposCast = new ArrayList();
  	try{
//  	Iterator objIter = objGrupos.iterator();
//  	while (objIter.hasNext()){
  	for (int intContador = 0; intContador < objGrupos.size(); intContador++ ){
//  		Record objRecord = (Record)objIter.next();
  		Record objRecord = (Record)objGrupos.get(intContador);
  		BTurbineGroup objTurGroup = new BTurbineGroup();
  		objTurGroup.setIIntGroupId(Integer.parseInt(objRecord.getValue("GROUP_ID").asString()));
  		objTurGroup.setIStrObjGroupName(objRecord.getValue("GROUP_NAME").asString());
  		objGruposCast.add(objTurGroup);
  	}
  	}catch(Exception e){
  		iObjLog.debug(" Error al llenar bean ", e);
  	}
      return objGruposCast;
  }
  
  public String getGrupoSeleccionado(){
      return iObjDatos.getParameters().getString("cboGrupo", "");
  }
  
  // ****************** Recyclable implementation ************************

  /** */
  private boolean disposed;

  /**
   * Recycles the object for a new client. Recycle methods with
   * parameters must be added to implementing object and they will be
   * automatically called by pool implementations when the object is
   * taken from the pool for a new client. The parameters must
   * correspond to the parameters of the constructors of the object.
   * For new objects, constructors can call their corresponding recycle
   * methods whenever applicable.
   * The recycle methods must call their super.
   */
  public void recycle(){
      disposed = false;
  }

  /**
   * Disposes the object after use. The method is called
   * when the object is returned to its pool.
   * The dispose method must call its super.
   */
  public void dispose(){
  	iObjDatos = null;
      iObjUsuario = null;
      iObjGrupo = null;
      iObjRole = null;
      iObjPermiso = null;
      iObjSistema = null;

      disposed = true;
  }

  /**
   * Checks whether the recyclable has been disposed.
   * @return true, if the recyclable is disposed.
   */
  public boolean isDisposed(){
      return disposed;
  }
  
  /**
   * Este M&eacute;todo esta encargado de obtener
   * el role del vm Roles.vm
   */     
  public String getTxtRole(){
		String strRole = iObjDatos.getParameters().getString("txtRoleNombre");
		iObjLog.debug("CResultadosTool: "+ strRole);
		if (strRole == null || strRole.length()==0){
			strRole="";
		}
		return strRole;
	}
		
   /**
   * Este M&eacute;todo esta encargado de obtener
   * el role del vm Roles.vm
   */     
  public String getChkSel(){
		String strSel = iObjDatos.getParameters().getString("chkSeleccionaTodo");
		iObjLog.debug("CResultadosTool: "+ strSel);
		if (strSel == null || strSel.length()==0){
			strSel="";
		}
		return strSel;
	}
	
   /**
   * Este M&eacute;todo esta encargado de obtener
   * el grupo del vm Grupos.vm
   */     
  public String getTxtGrupo(){
		String strGrupo = iObjDatos.getParameters().getString("txtNombreGrupo");
		iObjLog.debug("CResultadosTool: "+ strGrupo);
		if (strGrupo == null || strGrupo.length()==0){
			strGrupo="";
		}
		return strGrupo;
	}
		
   /**
   * Este M&eacute;todo esta encargado de obtener
   * el role del vm Grupos.vm y Permisos.vm
   */     
  public String getChkSelTodos(){
		String strSel = iObjDatos.getParameters().getString("chkSelTodos");
		iObjLog.debug("CResultadosTool: "+ strSel);
		if (strSel == null || strSel.length()==0){
			strSel="";
		}
		return strSel;
	}
	
   /**
   * Este M&eacute;todo esta encargado de obtener
   * el grupo del vm Permisos.vm
   */     
  public String getTxtPermiso(){
		String strPermiso = iObjDatos.getParameters().getString("txtPermisoNombre");
		iObjLog.debug("CResultadosTool: "+ strPermiso);
		if (strPermiso == null || strPermiso.length()==0){
			strPermiso="";
		}
		return strPermiso;
	}
     
	/**
	 * @return Returns the rolesAsignados.
	 */
	public List getRolesAsignados() {
		return rolesAsignados;
	}
	/**
	 * @param rolesAsignados The rolesAsignados to set.
	 */
	public void setRolesAsignados(List rolesAsignados) {
		this.rolesAsignados = rolesAsignados;
	}
	/**
	 * @return Returns the rolesSinAsignar.
	 */
	public List getRolesSinAsignar() {
		return rolesSinAsignar;
	}
	/**
	 * @param rolesSinAsignar The rolesSinAsignar to set.
	 */
	public void setRolesSinAsignar(List rolesSinAsignar) {
		this.rolesSinAsignar = rolesSinAsignar;
	}
  /**
   * Este metodo esta encargado de obtener el usuario de la
   * a traves del campo de texto txtNombreusuario del VM Usuarios.vm
   * @return User gobjUsuario
   * @throws Exception
   */
  public User getUsuario(String aStrUser) throws Exception{
      if (iObjUsuario == null){
          String strNombreUsuario = aStrUser;

          if (strNombreUsuario == null || strNombreUsuario.length() == 0){
          	iObjUsuario = TurbineSecurity.getUserInstance();
          }
          else{
          	iObjUsuario = TurbineSecurity.getUser(strNombreUsuario);
          }
      }
      return iObjUsuario;
  }
  
  /**
   * Este metodo esta encargado de obtener los permisos
   * que NO fueron asignados al rol
   * @param strNombreRole
   * @return List de permisos
   * @throws Exception
   */
  public List getPermisosSinAsig(String strNombreRole)
  throws Exception{
  	Permission permisos[] = TurbineSecurity.getAllPermissions().getPermissionsArray();
  	Permission objPerm = null;
  	Role objRole = null;
  	objRole = TurbineSecurity.getRoleByName(strNombreRole);
  	List objLstPerm = new ArrayList();
  	for(int i = 0; i< permisos.length; i++){
  		objPerm = permisos[i];
  		if(objRole.getPermissions().contains(objPerm)){
  			iObjLog.debug("EL-ROL:|"+objRole+"SI-TIENE-PERMISO:|"+objPerm+"|");
  		}else{
  			iObjLog.debug("EL-ROL:|"+objRole+"NO-TIENE-PERMISO:|"+objPerm+"|");
  			objLstPerm.add(objPerm);
  		}
  	}
  	return objLstPerm;
  }
  /**
   * Este metodo esta encargado de obtener los permisos
   * que fueron asignados al rol
   * @param strNombreRole
   * @return List de permisos
   * @throws Exception
   */
  public List getPermisosAsig(String strNombreRole)
  throws Exception{
  	Permission permisos[] = TurbineSecurity.getAllPermissions().getPermissionsArray();
  	Permission objPerm = null;
  	Role objRole = null;
  	objRole = TurbineSecurity.getRoleByName(strNombreRole);
  	List objLstPerm = new ArrayList();
  	for(int i = 0; i< permisos.length; i++){
  		objPerm = permisos[i];
  		if(objRole.getPermissions().contains(objPerm)){
  			objLstPerm.add(objPerm);
  			iObjLog.debug("EL-ROL:|"+objRole+"SI-TIENE-PERMISO:|"+objPerm+"|");
  		}else{
  			iObjLog.debug("EL-ROL:|"+objRole+"NO-TIENE-PERMISO:|"+objPerm+"|");  			
  		}
  	}
  	return objLstPerm;
  }
  
  /**
   * Este metodo esta encargado de asignar
   * el rol, al del tool
   * @param aObjRole
   * @throws Exception
   */
  public void setRole(Role aObjRole)throws Exception{
  	this.iObjRole = aObjRole;
  }
  
  /**
   * Metodo que pone en sesion el codigo y descripcion 
   * de Laboratorio y Departamento
   */
  /*public boolean setSesionVars() throws Exception{
  	boolean bolResul = false;
  	String strGroupId = iObjDatos.getSession().getAttribute("strIdDepartamentoActual").toString();
  	iObjLog.debug("Grupo de session setSesionVars ----> :" +strGroupId);
	System.out.println("Grupo de session setSesionVars ----> :" +strGroupId);
  	if (strGroupId!=null && strGroupId.length()>0){
  	  	try{
  	  	  	List objLista = iObjConsultasUtil.getLaboratorioByUser(strGroupId);
  	    	iObjLog.debug("Lista setSesionVars ----> :" +objLista);
  	    	System.out.println("Lista setSesionVars ----> :" +objLista);
  	    	System.out.println("Lista setSesionVars ----> :" +objLista.toString());
  	  	  	if (objLista!=null){
  	  	  		System.out.println("Lista primero ----> :" +objLista.get(0).getClass());
  	  	  		String strCodLab = objLista.get(0)+"";
  	  	  		iObjDatos.getSession().setAttribute("codigoLaboratorio",strCodLab);  
  	  	  		String strDesLab = objLista.get(1)+"";
  	  	  		iObjDatos.getSession().setAttribute("descLaboratorio",strDesLab);
  	  	  		String strCodDep = objLista.get(2)+"";
  	  	  		iObjDatos.getSession().setAttribute("codigoDepartamento",strCodDep);
  	  	  		String strDesDep = objLista.get(3)+"";
  	  	  		iObjDatos.getSession().setAttribute("descDepartamento",strDesDep);
  	  	  		bolResul = true;
  	  	  	}
  	  	}
  	  	catch(Exception aError){
  	  		throw aError;
  	  	}
  	}
  	return bolResul;
  }*/
  
  /**
   * Este metodo se encarga de obtener el(los) grupos del sistema
   * dependiendo del criterio de busqueda especificado
   * en Grupos.vm
   * @return List objGruposArreglo
   */
  public List getGrupoSistemaPorCriterio()throws Exception{  		  	 
  	String strNombreGrupo = this.getTxtGrupo();
  	String strSelTodos = this.getChkSelTodos();  	  	
  	String strQuery = "Select CSISTEMA, SSISTEMA from TURBINE_SISTEMA ";
  	if(strSelTodos != null && strSelTodos.length() > 0){
  	   	iObjLog.debug("BUSCARATODOS...");
  	}
  	else{
  		if(strNombreGrupo != null && strNombreGrupo.length() > 0){
  			if(this.esSinLike()){
  				strQuery += " WHERE SSISTEMA = '"+strNombreGrupo+"' ";
  			}else{
  				strQuery += " WHERE SSISTEMA LIKE '%"+strNombreGrupo+"%' ";
  			}  			        
        }
    }
  	strQuery += " ORDER BY SSISTEMA ASC ";
  	iObjLog.debug("QuerySISTEMAejecutado  "+strQuery);
  	List objGruposSistema = BasePeer.executeQuery(strQuery);  	
  	List objGruposCast = new ArrayList();
  	try{
  		if(objGruposSistema != null && objGruposSistema.size() > 0){
  			iObjLog.debug("GruposSistemaReecuperados"+objGruposSistema.size());
  			Iterator itera = objGruposSistema.iterator();
  			Record objRecord = null;
  			TurbineSistema objSistema = null;  			
  			while(itera.hasNext()){
  				objRecord = (Record)itera.next();
  				objSistema = new TurbineSistema();  				
  				objSistema.setCsistema(objRecord.getValue("CSISTEMA").asIntegerObj());
  				objSistema.setSsistema(objRecord.getValue("SSISTEMA").asString());
  				objGruposCast.add(objSistema);
  			}
  		}
  		return objGruposCast;
      }catch (Exception ex){
      	iObjLog.error("Exception caught:", ex);
          return null;
      }
  }     
  
  public TurbineSistema getGrupoSistema()throws Exception{
  	iObjLog.debug("OBTENIENDOELGRUPO...");
  	List objGrupos = null;
    if (iObjSistema == null){
    	  iObjLog.debug("ELOBJETOGRUPOSISTEMAESNULO...");
        String strNombreGrupo = iObjDatos.getParameters().getString("strNombreGrupo");
        iObjLog.debug("ELGRUPORECUPERADOES:|"+strNombreGrupo+"|");          
        if (strNombreGrupo == null || strNombreGrupo.length() == 0){
        	iObjLog.debug("OBTENIENDOINSTANCIANULADEGRUPO...");        	
        }
        else{
        	iObjLog.debug("OBTENIENDOINSTANCIAPORNOMBRE...");
        	iObjDatos.getParameters().add("chkSelTodos","");
        	iObjDatos.getParameters().add("txtNombreGrupo",strNombreGrupo);  
        	iObjDatos.getParameters().add("sinLike","true");
        	objGrupos = this.getGrupoSistemaPorCriterio();
        	iObjSistema = (TurbineSistema)objGrupos.get(0);
        }
    }
    iObjLog.debug("OBJETOGRUPORETORNADO:|"+iObjGrupo+"|");
    return iObjSistema;  	
  }
  
  /**
   * Este M&eacute;todo esta encargado de 
   * indicarnos si el query es con like
   */     
  public boolean esSinLike(){
  		boolean bolLike = false;
		String strLike = iObjDatos.getParameters().getString("sinLike");		
		if (strLike != null){
			bolLike=true;
		}
		return bolLike;
	}
  
  /**
   * Este metodo esta encargado de obtener el usuario de la
   * a traves del campo de texto txtNombreusuario del VM Usuarios.vm
   * @return User gobjUsuario
   * @throws Exception
   */
  public int getIdUser(String aStrUser) throws Exception{
	  iObjLog.debug("getIdUser " + aStrUser);
	  int iduser=0;
          String strQuery = "Select TU.USER_ID as id from TURBINE_USER TU WHERE TU.LOGIN_NAME='" + aStrUser +"'";
          
          iObjLog.debug("query" + strQuery);
          try{
           	List objRecord = BasePeer.executeQuery(strQuery);
           	if(!objRecord.isEmpty()){
               	Iterator iLista = objRecord.iterator();
               	while (iLista.hasNext()){
               		Record objRecordDet = (Record)iLista.next();
               		iduser = objRecordDet.getValue("id").asInt();
               		iObjLog.debug("El id encontrado "+ iduser);
               	}
               }
       	  }catch(DataSetException aObjException){
       		iObjLog.error("Error al obtener datos en getIdUser:" + aObjException.toString());
     	    throw aObjException;
       		  }  
          
      return iduser;
  }
}
