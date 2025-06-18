package mx.com.web2lab.actions.seguridad;

//Java
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.com.web2lab.util.Formatos;
import mx.com.web2lab.actions.SecureAction;
import mx.com.web2lab.tools.seguridad.CSeguridadTool;
import mx.com.web2lab.backend.hbm.om.sistema.TurbineCalendar;
import mx.com.web2lab.backend.interfaz.MSeguridad;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;
import org.apache.torque.util.BasePeer;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.Group;
import org.apache.turbine.om.security.Role;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.velocity.context.Context;

public class BUserAction extends SecureAction implements Serializable{
	
	/**log de la aplicaci&oacute;n*/	
	private static Log iObjLog = LogFactory.getLog(BUserAction.class);

    /**
     * Metodo encargado de insertar un nuevo usuario en
     * el esquema de seguridad de Turbine
     * @param RunData aObjDatos
     * @param Context aobjContexto
     * @throws Exception
     */
    public void doInserta(RunData aObjDatos, Context aobjContexto)throws Exception{    	
        TurbineUser objTurbineUsuario = new TurbineUser();
        aObjDatos.getParameters().setProperties(objTurbineUsuario);
        String strNombreUsuario = aObjDatos.getParameters().getString("username");
        String strNuePasswd = aObjDatos.getParameters().getString("password");
        if (TurbineSecurity.accountExists(strNombreUsuario)){                        
            String strContextoMensaje = aObjDatos.getParameters().getString("username");
	    	aobjContexto.put("strUsuarioExiste", ""+strContextoMensaje+"" );
	    	aobjContexto.put("bolUsuarioExiste", "true");
        }
        else{
        	objTurbineUsuario.setNew(true);
        	objTurbineUsuario.setCreateDate(new Date());
        	objTurbineUsuario.setModified(new Date());
        	objTurbineUsuario.setPassword(TurbineSecurity.encryptPassword(strNuePasswd));
			objTurbineUsuario.save();
			String strContextoMensaje = aObjDatos.getParameters().getString("username");
	    	aobjContexto.put("strMensaje", ""+strContextoMensaje+"" );
	    	aobjContexto.put("bolCreado", "true");
        }
    }

    /**
     * Metodo encargado de actualizar un nuevo usuario en
     * el esquema de seguridad de Turbine
     * @param RunData aObjDatos
     * @param Context aobjContexto
     * @throws Exception
     */
    public void doActualiza(RunData aObjDatos, Context aobjContexto)throws Exception{
    	Criteria objCriteria = new Criteria();
	    objCriteria.add(TurbineUserPeer.LOGIN_NAME, aObjDatos.getParameters().getString("username"));
	    List objTurbineUsuarios = TurbineUserPeer.doSelect(objCriteria);
	    TurbineUser objTurbineUsuario = new TurbineUser();
	    for(int i=0; i<objTurbineUsuarios.size(); i++){
	    	objTurbineUsuario = (TurbineUser) objTurbineUsuarios.get(i);
	    }
	    aObjDatos.getParameters().setProperties(objTurbineUsuario);
	    objTurbineUsuario.setModified(new Date());
	    if(aObjDatos.getParameters().getString("hdnCambioPass").equals("true")){
	    	iObjLog.debug("<<<<<ACTUALIZANDOCONTRASENIA");
	    	String strNuePasswd = aObjDatos.getParameters().getString("password");
		    objTurbineUsuario.setPassword(TurbineSecurity.encryptPassword(strNuePasswd));
	    }	    
		objTurbineUsuario.save();		
		String strMenModif = aObjDatos.getParameters().getString("username");
    	aobjContexto.put("strMensajeModif", ""+strMenModif+"" );
    	aobjContexto.put("bolModif", "true");
    }
    

    /**
     * Metodo encargado de eliminar un usuario en el esquema de seguridad
     * de Turbine
     * @param RunData aObjDatos
     * @param Context aobjContexto
     * @throws Exception
     */
    public void doElimina(RunData aObjDatos, Context aobjContexto)throws Exception{
        User objUsuario = TurbineSecurity.getUser(
                aObjDatos.getParameters().getString("username"));
        TurbineSecurity.removeUser(objUsuario);
        String strMenBorrado = aObjDatos.getParameters().getString("username");
    	aobjContexto.put("strMensajeBorrado", ""+strMenBorrado+"" );
    	aobjContexto.put("bolBorrado", "true");
    }
  

    /**
     * Actualiza los roles que son asignados al usuario.
     * @param RunData aObjDatos
     * @param Context aobjContexto
     * @throws Exception
     */
    public void doRoles(RunData aObjDatos, Context aobjContexto)throws Exception{		
        //Obtiene el usuario que intenta actualizar. El usuario
        //viene oculto en el form.         
        String strNombreUsuario = aObjDatos.getParameters().getString("hdnNombreUsuario");
        User objUsuario = TurbineSecurity.getUser(strNombreUsuario);
        AccessControlList objAccesos = TurbineSecurity.getACL(objUsuario);      
        //Graba todos los grupos y roles en el sistema        
        Group[] objGrupos = TurbineSecurity.getAllGroups().getGroupsArray();
        Role[] objRoles = TurbineSecurity.getAllRoles().getRolesArray();
        for (int i = 0; i < objGrupos.length; i++){
            String strNombreGrupo = objGrupos[i].getName();
            for (int j = 0; j < objRoles.length; j++){
               //En el vm del usuario se hace un checkbox
               //por cada combinacion posible de Grupos y Roles               
               String strNombreRole = objRoles[j].getName();
               String strGrupoRole = strNombreGrupo + strNombreRole;
               String strFrmGrupoRole = aObjDatos.getParameters().getString(strGrupoRole);
               if (strFrmGrupoRole != null && !objAccesos.hasRole(objRoles[j], objGrupos[i])){
                   TurbineSecurity.grant(objUsuario, objGrupos[i], objRoles[j]);
               }else if (strFrmGrupoRole == null
                         && objAccesos.hasRole(objRoles[j], objGrupos[i])){
                   TurbineSecurity.revoke(objUsuario, objGrupos[i], objRoles[j]);
               }
            }
        }
        String strMensActualizado = aObjDatos.getParameters().getString("hdnNombreUsuario");
    	aobjContexto.put("strMensajeActualizado", ""+strMensActualizado+"" );
    	aobjContexto.put("bolActualizado", "true");
          
    }

    /**
     * Metodo que se ejecuta cuando no se encuentra
     * alguna accion especificada
     * @param RunData aObjDatos
     * @param Context aobjContexto
     * @throws Exception
     */
    public void doPerform(RunData aObjDatos, Context aobjContexto)throws Exception{
        iObjLog.debug("Corriendo do perform!");
        aObjDatos.setMessage("No se puede encontrar la accion solicitada");        
    }
        
    /**
     * Metodo que se ejecuta cuando se requiere realizar
     * la accion de busqueda en el modulo de usuarios
     * @param RunData aObjDatos
     * @param Context aobjContexto
     * @throws Exception
     */
    public void doBuscar(RunData aObjDatos, Context aobjContexto)throws Exception{        
        String strBuscaTodos = aObjDatos.getParameters().getString("chkSelTodos");
        String strNombreUsuario = aObjDatos.getParameters().getString("txtNombreUsuario");
        String strNombre = aObjDatos.getParameters().getString("txtNombre");
        String strApellido = aObjDatos.getParameters().getString("txtApellido");                        
        if ( strBuscaTodos != null && strBuscaTodos.length() > 0){
            
            aobjContexto.put("strNomUsuVuel", "");
            aobjContexto.put("strNomVuel", "");
            aobjContexto.put("strApellidoVuel", "");
        }
        else{
		    aobjContexto.put("strNomUsuVuel", ""+strNombreUsuario+"");
	        aobjContexto.put("strNomVuel", ""+strNombre+"");
	        aobjContexto.put("strApellidoVuel", ""+strApellido+"");
      	}
        if ( strNombreUsuario == null ){
		    
		   	aobjContexto.put("strNomUsuVuel","");
		}
		if ( strNombre == null ){
		    
		   	aobjContexto.put("strNomVuel","");
		}
		if ( strApellido == null ){
		    
		   	aobjContexto.put("strApellidoVuel","");
		}
    }
    
    /**
     * Este metodo esta encargado de obtener los roles
     * deacuerdo al usuario y a un grupo en especifico.
     * Los roles asignados y sin asignar se agregan
     * al tool CSeguridadTool.
     * @param RunData aObjDatos
     * @param Context aobjContexto
     * @throws Exception
     */
    public void doEditagruporol(RunData aObjDatos, Context aobjContexto)
    throws Exception{
    	iObjLog.debug("EDITANDOLOSGRUPOSROLES");
    	CSeguridadTool tool = (CSeguridadTool)aobjContexto.get("CSeguridadTool");    	
    	Role objRoles[]  = tool.getRoles();    	
    	iObjLog.debug("doEditagruporol-GRUPO:|"+aObjDatos.getParameters().getString("cboGrupos")+"|");
    	iObjLog.debug("doEditagruporol-USER:|"+aObjDatos.getParameters().getString("hdnUsuario")+"|");
    	
    	String strGrupo = aObjDatos.getParameters().getString("cboGrupos");
    	    	
    	Group objGrupo = TurbineSecurity.getGroupByName(strGrupo);
    	iObjLog.debug("ELGRUPOENCONTRADOES:|"+objGrupo+"|");
    	String strUser = aObjDatos.getParameters().getString("hdnUsuario");    	
    	AccessControlList objACL = tool.getACL(strUser);    	
    	Role objRole = null;    	    	
    	List objRolesAsignados = new ArrayList();
    	List objRolesSinAsignar = new ArrayList();
    	if(objRoles != null && objRoles.length > 0){
    		for(int i=0; i<objRoles.length; i++){
    			objRole = objRoles[i];
    			//Pregunta si el ACL tiene ese ROL
    			if(objACL.hasRole(objRole,objGrupo)){
    				iObjLog.debug("ROL-ASIGNADO:|"+objRole.getName()+"|");
    				objRolesAsignados.add(objRole);
    			}else{
    				iObjLog.debug("ROL-NO-ASIGNADO:|"+objRole.getName()+"|");
    				objRolesSinAsignar.add(objRole);
    			}
    		}
    	}
    	tool.setRolesAsignados(objRolesAsignados);
    	tool.setRolesSinAsignar(objRolesSinAsignar);
    	
    	aObjDatos.getParameters().add("strNomUser",strUser);
    	aobjContexto.put("ctxGrupo",objGrupo);
    }
    
    public void doEditagruporol2(RunData aObjDatos, Context aobjContexto)
    throws Exception
    {
    	CSeguridadTool tool = (CSeguridadTool)aobjContexto.get("CSeguridadTool");
    	String strGrupo = aObjDatos.getParameters().getString("cboGruposAsignados");
    	String strUser = aObjDatos.getParameters().getString("hdnUsuario");
    	Group objGrupo = TurbineSecurity.getGroupByName(strGrupo);
    	
    	//roles se  guardan el el tool de session
    	obtenerRoles(tool, strUser, objGrupo);
    	
    	aObjDatos.getParameters().add("strNomUser",strUser);
    	aobjContexto.put("ctxGrupo",objGrupo);
    }
    
    public void obtenerRoles(CSeguridadTool tool, String strUsuario, Group objGrupo) throws Exception
    {
    	Role objRoles[]  = tool.getRoles();
    	    	
    	AccessControlList objACL = tool.getACL(strUsuario);    	
    	Role objRole = null;    	    	
    	List objRolesAsignados = new ArrayList();
    	List objRolesSinAsignar = new ArrayList();
    	if(objRoles != null && objRoles.length > 0){
    		for(int i=0; i<objRoles.length; i++){
    			objRole = objRoles[i];
    			//Pregunta si el ACL tiene ese ROL
    			if(objACL.hasRole(objRole,objGrupo)){
    				iObjLog.debug("ROL-ASIGNADO:|"+objRole.getName()+"|");
    				objRolesAsignados.add(objRole);
    			}else{
    				iObjLog.debug("ROL-NO-ASIGNADO:|"+objRole.getName()+"|");
    				objRolesSinAsignar.add(objRole);
    			}
    		}
    	}
    	tool.setRolesAsignados(objRolesAsignados);
    	tool.setRolesSinAsignar(objRolesSinAsignar);
    }
    
    /**
     * Este metodo esta encargado de editar los roles
     * que el usuario tenga (Los agrega o los elimina)
     * @param aObjDatos
     * @param aobjContexto
     * @throws Exception
     */

    public void doEditarolusuario(RunData aObjDatos, Context aobjContexto) throws Exception
    {
    	List objLstRoles = new ArrayList();
    	String strNombreUsuario = aObjDatos.getParameters().getString("hdnNombreUsuario");
        String strGrupo = aObjDatos.getParameters().getString("hdnGrupo");
        
        String strGrupoRol[] = aObjDatos.getParameters().getStrings("cboRolesAsig");        
        //Se pasa el arreglo a una lista para mejor manejo
        if(strGrupoRol != null)
        {
	        for (int i=0; i<strGrupoRol.length; i++)
	        {
	        	objLstRoles.add(strGrupoRol[i]);
	        }
    	}        
        User objUsuario = TurbineSecurity.getUser(strNombreUsuario);
        AccessControlList objAccesos = TurbineSecurity.getACL(objUsuario);      
        //Graba todos los grupos y roles en el sistema        
        //Group[] objGrupos = TurbineSecurity.getAllGroups().getGroupsArray();
        //Se obtiene el grupo en donde se editaran sus roles
        Group objGrupo = TurbineSecurity.getGroupByName(strGrupo);
        
        Role[] objRoles = TurbineSecurity.getAllRoles().getRolesArray();
        //for (int i = 0; i < objGrupos.length; i++){
            //String strNombreGrupo = objGrupos[i].getName();
        	String strNombreGrupo = objGrupo.getName();
            for (int j = 0; j < objRoles.length; j++)
            {                             
               String strNombreRole = objRoles[j].getName();
               String strGrupoRole = strNombreGrupo + strNombreRole;
               //Si contiene el grupo-rol y no lo tenia asignado lo ASIGNA
               if(objLstRoles.contains(strGrupoRole) && !objAccesos.hasRole(objRoles[j], objGrupo))
               {               	
               		TurbineSecurity.grant(objUsuario, objGrupo, objRoles[j]);               		
               //Si no contiene el grupo-rol y lo tenia asignado lo QUITA
               }
               else if(!objLstRoles.contains(strGrupoRole) && objAccesos.hasRole(objRoles[j], objGrupo))
               {
               		TurbineSecurity.revoke(objUsuario, objGrupo, objRoles[j]);               			
               }               
            }
        //}
            
        CSeguridadTool tool = (CSeguridadTool)aobjContexto.get("CSeguridadTool");
        obtenerRoles(tool, strNombreUsuario, objGrupo);
        	
        aObjDatos.getParameters().add("strNomUser",strNombreUsuario);
        aobjContexto.put("ctxGrupo",objGrupo);
    	aobjContexto.put("bolActualizado", "true");
    }
    
    
    /**
     * Este metodo esta encargado de editar los roles
     * que el usuario tenga (Los agrega o los elimina)
     * @param aObjDatos
     * @param aobjContexto
     * @throws Exception
     */

    public void doEliminargrupo(RunData aObjDatos, Context aobjContexto)
    throws Exception
    {
    	String loginUser = aObjDatos.getParameters().getString("hdnUsuario");
    	
    	String listaIdesGrupos [] = aObjDatos.getParameters().getStrings("cboGruposAsignados");
    	if(listaIdesGrupos != null && listaIdesGrupos.length > 0)
    	{
    		for (int i = 0; i < listaIdesGrupos.length; i++)
        	{
    			String nombreGrupo = listaIdesGrupos[i];
    			
    			StringBuffer bufferQueryExiste = new StringBuffer("delete " +
    					"from turbine_user_group_role " +
    					"where user_id= (select turbine_user.USER_ID from turbine_user where turbine_user.LOGIN_NAME='" + loginUser + "')" +
    					" and group_id = (select turbine_group.GROUP_ID from turbine_group where turbine_group.GROUP_NAME='" + nombreGrupo + "')");
    			iObjLog.debug(bufferQueryExiste.toString());
    			BasePeer.executeStatement(bufferQueryExiste.toString());
    		}
    	}
    	
    	aObjDatos.getParameters().add("txtNombreUsuario",loginUser);
    	aObjDatos.getParameters().add("strModo","modificar");
    }
    /**
     * Este metodo esta encargado de agregar un registro
     * a la tabla turbine
     * @param aObjDatos
     * @param aobjContexto
     * @throws Exception
     */
    public void doEditaunidad(RunData aObjDatos,Context aobjContexto) throws Exception
    {   
    	iObjLog.debug(" Entrando BUserAction.doEditaunidad");
       	try {
	        TurbineCalendar objTurbinemod =new TurbineCalendar();
	        Formatos ObjFormat = new Formatos();
	        MSeguridad objUsuarioMod = new MSeguridad();		        
	        int loginUsuario = aObjDatos.getUser().getId();//-----Usuario que captura la modificacion
	        int Usuarioedit =aObjDatos.getParameters().getInt("hdnIdUsuario");//Usuario que se modificara
	        int Gruponew = aObjDatos.getParameters().getInt("cboGruposAsignados");//--nuevo grupo al que va
	        int Grupoold=aObjDatos.getParameters().getInt("hdnGrupoUsuario");//--viejo grupo al que pertenece
	        int RolUsuario=aObjDatos.getParameters().getInt("hdnRolUsuario");//Rol del usuario
	        
	        String fechainicial=aObjDatos.getParameters().getString("txtini");//--fecha inicial
	        String fechafinal=aObjDatos.getParameters().getString("txtfin");//--fecha final
	        //int intigual=new Date().compareTo(new Date(fechainicial));//verificar si es hoy el cambio
	        int intigual=new Date().compareTo(ObjFormat.getFecha(fechainicial));//verificar si es hoy el cambio
	        
	        iObjLog.debug("variable intigual"+intigual);
	        
	        if(intigual==1)
	        {  
	        	iObjLog.debug("--------------ESTA INSERTANDO UN REBGISTRO A LA  TABLA TURBINE_CALENDAR-------doEditaunidad--------");
	        	iObjLog.debug("Unidad vieja----"+Grupoold);
		        iObjLog.debug("Rol viejo----"+RolUsuario);
		        iObjLog.debug("Unidad nueva----"+Gruponew);
		        iObjLog.debug("Rol nuevo----"+RolUsuario);
		        iObjLog.debug("Fecha inicial----"+fechainicial);
		        iObjLog.debug("Fecha final----"+fechafinal);
		        iObjLog.debug("Usuariologin----"+loginUsuario);
		        
		        iObjLog.debug("FECHAINICIAL----"+ObjFormat.getFecha(fechainicial));
		        iObjLog.debug("FECHAFINAL----"+ObjFormat.getFecha(fechafinal));
		        
		     
	        	objTurbinemod.setGroupId(Grupoold);
		        objTurbinemod.setRoleId(RolUsuario);
		        objTurbinemod.setGroupIdNew(Gruponew);
		        objTurbinemod.setRoleIdNew(RolUsuario);
		        objTurbinemod.setDfirst(ObjFormat.getFecha(fechainicial));
		        objTurbinemod.setDlast(ObjFormat.getFecha(fechafinal));
		        objTurbinemod.setCusuariomodi(loginUsuario);
		        objTurbinemod.setUserId(Usuarioedit);
		        objUsuarioMod.ModificaUsuario(objTurbinemod);
		       
		        iObjLog.debug("--------------ACTUALIZAR LA TABLA DE ------turbine_user_group_role---------");
	        	StringBuffer bufferQueryExiste = new StringBuffer("update " +
					" turbine_user_group_role set group_id=" + Gruponew +
					" where user_id=" + Usuarioedit );
			  iObjLog.debug(bufferQueryExiste.toString());
			  BasePeer.executeStatement(bufferQueryExiste.toString());
			  
			    aobjContexto.put("bolActualizado", "true");        
		    	iObjLog.debug(" Saliendo BUserAction.doEditaunidad");
	        }
	        else
		   {    iObjLog.debug("--------------SOLO INSERTA EL REGISTRO EN TURBINE_CALENDAR---------------");
	        	objTurbinemod.setGroupId(Grupoold);
		        objTurbinemod.setRoleId(RolUsuario);
		        objTurbinemod.setGroupIdNew(Gruponew);
		        objTurbinemod.setRoleIdNew(RolUsuario);
		        objTurbinemod.setCusuariomodi(loginUsuario);
		        objTurbinemod.setDfirst(ObjFormat.getFecha(fechainicial));
		        objTurbinemod.setDlast(ObjFormat.getFecha(fechafinal));
		        objTurbinemod.setUserId(Usuarioedit);
		        iObjLog.debug("Unidad vieja----"+Grupoold);
		        iObjLog.debug("Rol viejo----"+RolUsuario);
		        iObjLog.debug("Unidad nueva----"+Gruponew);
		        iObjLog.debug("Rol nuevo----"+RolUsuario);
		        iObjLog.debug("Fecha inicial----"+fechainicial);
		        iObjLog.debug("Fecha final----"+fechafinal);
		        iObjLog.debug("Usuariologin----"+loginUsuario);       
		        objUsuarioMod.ModificaUsuario(objTurbinemod);
		        aobjContexto.put("bolActualizado", "true");        
		    	iObjLog.debug(" Saliendo BUserAction.doEditaunidad");
	        	
	        }
	        
	        
       } catch (Exception aError)
       {
	       	iObjLog.error("ERROR EN BUSERACTION: doEditaunidad" + aError.toString());
	    	throw aError;	   
       }        
    }
    /**
     * Este metodo esta encargado de dar de alta a los 
     * usuarios (Los inserta en una tabla)
     * @param aObjDatos
     * @param aobjContexto
     * @throws Exception
     */

    public boolean UserExist(int Usuario)
    throws Exception
    {   iObjLog.debug("<<<<<<Entra al UserExist>>>>");
	
	    boolean bflagactivated=false;
		String strQuery = "select user_id from turbine_user_deactivated where user_id=" +Usuario;
		iObjLog.debug("El query es:------"+ strQuery);
		
	try{
    	List objRecord = BasePeer.executeQuery(strQuery);
    	if(!objRecord.isEmpty()){
    		iObjLog.debug("----Usuario desactivado----");
    		bflagactivated=true;
        }
    	iObjLog.debug("La bandera trae el valor-----: "+ bflagactivated);
    	return bflagactivated;  
    	
	    }catch(TorqueException aObjException){
	    iObjLog.error("Error al obtener el usuario desactivado..", aObjException);
	    throw aObjException;
	    }
    }
 
    /**
     * Este metodo esta encargado de dar de alta a los 
     * usuarios (Los inserta en una tabla)
     * @param aObjDatos
     * @param aobjContexto
     * @throws Exception
     */

    public void doAlta(RunData aObjDatos, Context aobjContexto)
    throws Exception
    {     int imodo=aObjDatos.getParameters().getInt("imodo");
    	  String strMenModif = aObjDatos.getParameters().getString("hdnUserName");
	          
    	  iObjLog.debug("<<<<<<<<<<Entrando doAlta>>>>>>>>>>>>");
    	  try {
    		    iObjLog.debug("MODO----"+imodo);
    	        int UsuarioEdit =aObjDatos.getParameters().getInt("hdnUserId");
    			int loginUsuario = aObjDatos.getUser().getId();
    			if(imodo==0){
    				boolean bexiste=false;
    				
    				bexiste=UserExist(UsuarioEdit);
    				if(bexiste==false){
		    			StringBuffer bufferQueryExiste = new StringBuffer("insert into " +
		    					"TURBINE_USER_DEACTIVATED values (" +UsuarioEdit+","+loginUsuario+","+"SYSDATE"+")");
		    			iObjLog.debug(bufferQueryExiste.toString());
		    			BasePeer.executeStatement(bufferQueryExiste.toString());
		    			aobjContexto.put("strMensajeModif", ""+strMenModif+"" );
		    			aobjContexto.put("bolBaja", "true");
    				}//fin del if bexiste
    				else
    				{  aobjContexto.put("bolExiste", "true");
    				   aobjContexto.put("strMensajeModif", ""+strMenModif+"" );
    				}//fin de else de bexiste		
    			}//fin de if modo==0
    			else
	    		{    StringBuffer bufferQueryExiste = new StringBuffer("delete " +
	    					"TURBINE_USER_DEACTIVATED where user_id=" +UsuarioEdit);
	    			iObjLog.debug(bufferQueryExiste.toString());
	    			BasePeer.executeStatement(bufferQueryExiste.toString());
	    			aobjContexto.put("strMensajeModif", ""+strMenModif+"" );
	    			aobjContexto.put("bolAlta", "true");
	    				
	    		}//fin de else modo=0
    	  }catch(Exception aError)
          {
  	       	iObjLog.error("ERROR EN BUSERACTION: doAlta" + aError.toString());
  	    	throw aError;	   
         }        	
    }
 
    /**
     * Este metodo esta encargado de agregar un registro
     * a la tabla turbine
     * @param aObjDatos
     * @param aobjContexto
     * @throws Exception
     */
    public void doEditarol(RunData aObjDatos,Context aobjContexto) throws Exception
    {   
    	iObjLog.debug(" Entrando BUserAction.doEditaRol");
       	try { 
       		  
       		  TurbineCalendar objTurbinemod =new TurbineCalendar();
	          MSeguridad objUsuarioMod = new MSeguridad();	
	          Formatos ObjFormat = new Formatos();
	          
	          int loginUsuario = aObjDatos.getUser().getId();//-----Usuario que captura la modificacion
	          int Usuarioedit =aObjDatos.getParameters().getInt("hdnIdUsuario");//Usuario que se modificara
	          int Rolnew = aObjDatos.getParameters().getInt("cboRolesAsig");//--nuevo rol
	          int GrupoId=aObjDatos.getParameters().getInt("hdnGrupoUsuario");//--Grupo al que pertenece
	          int RolUsuario=aObjDatos.getParameters().getInt("hdnRolUsuario");//Rol viejo
	          String fechainicial=aObjDatos.getParameters().getString("txtini");//--fecha inicial
	          String fechafinal=aObjDatos.getParameters().getString("txtfin");//--fecha final	
	          
	          int intigual=new Date().compareTo(ObjFormat.getFecha(fechainicial));//verificar si es hoy el cambio
	          
	          if(intigual==1)
		        { iObjLog.debug("--------------ESTA INSERTANDO UN REBGISTRO A LA  TABLA TURBINE_CALENDAR-------doEditarol--------");
	        	  objTurbinemod.setGroupId(GrupoId);
		          objTurbinemod.setRoleId(RolUsuario);
		          objTurbinemod.setGroupIdNew(GrupoId);
		          objTurbinemod.setRoleIdNew(Rolnew);
		          objTurbinemod.setDfirst(ObjFormat.getFecha(fechainicial));
		          objTurbinemod.setDlast(ObjFormat.getFecha(fechafinal));
		          objTurbinemod.setCusuariomodi(loginUsuario);
		          objTurbinemod.setUserId(Usuarioedit);
		          
		          iObjLog.debug("Unidad ----"+GrupoId);
		          iObjLog.debug("Rol viejo----"+RolUsuario);
		          iObjLog.debug("Unidad ----"+GrupoId);
		          iObjLog.debug("Rol nuevo----"+Rolnew);
		          iObjLog.debug("Fecha inicial----"+fechainicial);
		          iObjLog.debug("Fecha final----"+fechafinal);
		          iObjLog.debug("Usuarioa editar----"+Usuarioedit);
		          iObjLog.debug("Usuariologin----"+loginUsuario); 
		          objUsuarioMod.ModificaRol(objTurbinemod);
		          
		          iObjLog.debug("--------------ACTUALIZAR LA TABLA DE ------turbine_user_group_role---------");
		        	StringBuffer bufferQueryExiste = new StringBuffer("update " +
						" turbine_user_group_role set role_id=" + Rolnew +
						" where user_id=" + Usuarioedit );
		        	
				  iObjLog.debug(bufferQueryExiste.toString());
				  BasePeer.executeStatement(bufferQueryExiste.toString());
				  
		          aobjContexto.put("bolActualizado", "true");        
		    	  iObjLog.debug(" Saliendo BUserAction.doEditaRol"); 
		        }
	          else
	          {
	        	  iObjLog.debug("--------------ESTA INSERTANDO UN REBGISTRO A LA  TABLA TURBINE_CALENDAR-------doEditarol--------");
	        	  objTurbinemod.setGroupId(GrupoId);
		          objTurbinemod.setRoleId(RolUsuario);
		          objTurbinemod.setGroupIdNew(GrupoId);
		          objTurbinemod.setRoleIdNew(Rolnew);
		          objTurbinemod.setDfirst(ObjFormat.getFecha(fechainicial));
		          objTurbinemod.setDlast(ObjFormat.getFecha(fechafinal));
		          objTurbinemod.setCusuariomodi(loginUsuario);
		          objTurbinemod.setUserId(Usuarioedit);
		          iObjLog.debug("Unidad ----"+GrupoId);
		          iObjLog.debug("Rol viejo----"+RolUsuario);
		          iObjLog.debug("Unidad ----"+GrupoId);
		          iObjLog.debug("Rol nuevo----"+Rolnew);
		          iObjLog.debug("Fecha inicial----"+fechainicial);
		          iObjLog.debug("Fecha final----"+fechafinal);
		          iObjLog.debug("Usuarioa editar----"+Usuarioedit);
		          iObjLog.debug("Usuariologin----"+loginUsuario); 
		          objUsuarioMod.ModificaRol(objTurbinemod);
		          
		          aobjContexto.put("bolActualizado", "true");        
		    	  iObjLog.debug(" Saliendo BUserAction.doEditaRol"); 
	          }
	         
           }catch (Exception aError)
         {  
	       	iObjLog.error("ERROR EN BUSERACTION: doEditaRol" + aError.toString());
	    	throw aError;	   
         }        
    } 
}
