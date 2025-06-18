package mx.com.web2lab.util.seguridad;


// Java
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import mx.com.web2lab.beans.BMenuOptions;
import mx.com.web2lab.backend.util.beans.sistema.TurbineGroup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.TorqueException;
import org.apache.torque.util.BasePeer;

import com.workingdogs.village.DataSetException;
import com.workingdogs.village.Record;

/**
 * La clase utilitar�a GrupoMenuUtil se dedica a insertar y modificar la tabla GRUPO_MENU
 */


public class BGrupoMenuUtil implements Serializable{
	
	/** Log de la aplicaci&oacute;n*/
    private static Log iObjLog = LogFactory.getLog(BGrupoMenuUtil.class);
  
    /**
     * El metodo setInserta obtiene un parametro String para completar el query de inserci&oacute;n
     * @param aStrNombre
     * @return true, si se logra la inserci&oacute;n
     */

    public boolean setInserta(String aStrNombre) throws Exception{
    	iObjLog.debug("Corriendo en el Util Inserta");

        boolean bolInserto = false;  // validacion de insercion
        int intId= new BPermissionMenuUtil().getNumMax("ID_GRUPO_MENU", "GRUPO_MENU"); 
        String strQuery = "insert into GRUPO_MENU values("+intId+", '"+aStrNombre+"')";
        int intI = BasePeer.executeStatement(strQuery);
        if(intI > 0){
        	bolInserto = true;   // si inserto
            iObjLog.debug(">>>>>>>>>>>>>>>Si insert�");
        }
        return bolInserto;
    }

	/**
     * El metodo actualiza un dato de la tabla
     * @param aStrNombre
     * @param aStrNombreAntrior
     * @return true, si se logra la modificaci&oacute;n
     */

	public boolean setActualiza(String aStrNombre, String aStrNombreAnterior) throws Exception{
		iObjLog.debug("Corriendo en el Util Update");
		iObjLog.debug(">>>>>>>>>>>>>>>>El valor de Nombre:"+aStrNombre);
		iObjLog.debug(">>>>>>>>>>>>>>>>El valor de Nombre_anterior:"+aStrNombreAnterior);
			
		boolean bolActualiza = false; // validacion de actualizacion
		String strQuery = "update GRUPO_MENU set DESC_GRUPO='"+aStrNombre+"' where DESC_GRUPO like '"+aStrNombreAnterior+"' ";
					   
		int intI = BasePeer.executeStatement(strQuery);
		if(intI > 0){
			bolActualiza = true; // si inserto
			iObjLog.debug(">>>>>>>>>>>>>>>>Si modific�");
		}
		return bolActualiza;
	}
	
	/**
	 * Obtiene los datos de la tabla GRUPO_MENU
	 * @return lista, el cual contiene los datos de la tabla.
	 */

	public List getGrupoMenuUtil() throws Exception{
		iObjLog.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>ENTR� al Util");		
    	String strQuery="select * from GRUPO_MENU order by 2";
		List objLista = BasePeer.executeQuery(strQuery);
		
		for(int intI=1; intI<objLista.size(); intI++){
			Record objrecord = (Record)objLista.get(intI);
			iObjLog.debug(">>>>>>>>>>>>>>>>>>>>>>>>>Valores de la tabla:"+objrecord.getValue(2).asString());
			
		}
		return objLista;
		
    }
    
    /**
	 * Obtiene un dato de la tabla GRUPO_MENU donde DESC_GRUPO se paresca al parametro
	 * @param aStrGrupoMenu
	 * @return lista, el cual contiene el dato.
	 */
    
    public List getGrupoUtil(String aStrGrupoMenu) throws Exception{
    	iObjLog.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>ENTR� al Util");		
    	String strQuery="select * from GRUPO_MENU where DESC_GRUPO like '%"+aStrGrupoMenu+"%' order by 2";
    	iObjLog.debug("QueryGrupoUtil="+strQuery);
		List objLista = BasePeer.executeQuery(strQuery);
		
		for(int intI=1; intI<objLista.size(); intI++){
			Record objrecord = (Record)objLista.get(intI);
			iObjLog.debug(">>>>>>>>>>>>>>>>>>>>>>>>>Valores de la tabla:"+objrecord.getValue(2).asString());
			
		}
		return objLista;
		
    }
    
    /**
	 * Obtiene un dato de la tabla GRUPO_MENU donde DESC_GRUPO se paresca al parametro
	 * @param aStrGrupoMenu
	 * @return bolExistente, booleano que nos indica true en el caso de que si hay dato
	 * 					igual al parametro y false en caso contrario.
	 */    
    public boolean getGrupoExistente(String aStrGrupoMenu) throws Exception{
    	iObjLog.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>ENTR� al Util");	
    	iObjLog.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>Buscando :"+aStrGrupoMenu);
    	String strQuery="select * from GRUPO_MENU where DESC_GRUPO = '"+aStrGrupoMenu+"' order by 2";
    	iObjLog.debug("QueryGrupoExistente="+strQuery);
		boolean bolExistente = false; // validacion de actualizacion
		List objList = BasePeer.executeQuery(strQuery);
		if(objList != null && objList.size() > 0){
			bolExistente = true; // si hay dato
			iObjLog.debug(">>>>>>>>>>>>>>>>Encontr� un dato igual");
		}
		return bolExistente;
		
    }

    /**
	 * Valida si es Gente el usuario
	 * @param aStrGrupoMenu
	 * @return bolExistente, booleano que nos indica true en el caso de que si hay dato
	 * 					igual al parametro y false en caso contrario.
	 */    
    public boolean isGerente(String user_id) throws Exception{
    	iObjLog.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>ENTR� al BgrupoMenuUtil.isGerente");	
    	String strQuery="SELECT ROLE_ID FROM TURBINE_USER_GROUP_ROLE WHERE USER_ID IN (" + user_id + ") AND ROLE_ID IN (330,611,612,614)";
    	iObjLog.debug("Consulta BgrupoMenuUtil.isGerente "+strQuery);
		boolean bolExistente = false; 
		List objList = BasePeer.executeQuery(strQuery);
		if(objList != null && objList.size() > 0){
			bolExistente = true; // si hay dato
			iObjLog.debug(">>>>>>>>>>>>>>>>Encontr� un dato igual");
		}
		return bolExistente;		
    }
    
    
    /**
     * Metodo que obtiene un Vector conteniendo las opciones 
     * por cada grupo de menu en un vector que contiene objetos
     * del tipo Bean MenuOptions 
     * @return Un hashmap con las opciones por seccion
     * @throws SQLException
     * @throws TorqueException
     * @throws DataSetException
     */
    public Vector getMenu(List aObjListasRoles) throws SQLException, TorqueException, DataSetException{
        iObjLog.debug("Entro a getMenu");
    	String objRoles = "0";
        iObjLog.debug("regreso de hacer consultas de roles "+aObjListasRoles.size());
    	Iterator objIter = aObjListasRoles.iterator();
    	while(objIter.hasNext()){
            iObjLog.debug("Encontrando roles asignados ");
    		TurbineGroup objTurbineGroup =(TurbineGroup)objIter.next();
    		objRoles += ", "+objTurbineGroup.getGroupId();
    	}
        iObjLog.debug("variable a recuperar para conocer menu "+objRoles);
        //System.out.println("variable a recuperar para conocer menu "+objRoles);
    	String strQuery = "SELECT DISTINCT c.id_grupo_menu, c.DESC_GRUPO, b.PERMISSION_NAME," +
    			" a.MENU_LINK  from MENU_PERMISSION a, TURBINE_PERMISSION b, " +
    			" GRUPO_MENU c, TURBINE_ROLE_PERMISSION d " +
    			" WHERE a.PERMISSION_ID = b.PERMISSION_ID " +
    			"	 AND a.ID_GRUPO_MENU = c.ID_GRUPO_MENU " +
    			"	 AND b.PERMISSION_ID = d.PERMISSION_ID " +
    			" 	 AND d.ROLE_ID IN ("+objRoles+")" +
    			" ORDER BY 1"; 
    	iObjLog.debug("query para generar menu = "+objRoles);
    	List objOpcionesMenu = BasePeer.executeQuery(strQuery);
    	Vector  objMenuBar = new Vector();
    	for (int intI=0; intI<objOpcionesMenu.size(); intI++){
    		Record objRecord = (Record)objOpcionesMenu.get(intI);
    		BMenuOptions objMenu = new BMenuOptions();
    		objMenu.setIdGRupoMenu(objRecord.getValue("ID_GRUPO_MENU").asInt());
    		objMenu.setDescGrupoMenu(objRecord.getValue("DESC_GRUPO").asString());
    		objMenu.setDescPermiso(objRecord.getValue("PERMISSION_NAME").asString());
    		objMenu.setLiga(objRecord.getValue("MENU_LINK").asString());
    		objMenuBar.add(objMenu);
    	}
		return objMenuBar;
    }
    
    /**
     * Metodo que obtiene el ID de Usuario a apartir del Username 
     * @return el int correspondiente al id de usuario
     * @throws SQLException
     * @throws TorqueException
     * @throws DataSetException
     */
    public int getIdUsuario(String strLogin) throws SQLException, TorqueException, DataSetException{
        iObjLog.debug("Entro a getIdUsuario");
    	String strQuery = "select USER_ID from TURBINE_USER where LOGIN_NAME='"+strLogin+"'"; 
    	iObjLog.debug("query para obtener el id de usuario a partir del nombre de usuario = " + strQuery);
    	try{
    		List lstidUsuario = BasePeer.executeQuery(strQuery);
    		if(lstidUsuario != null && lstidUsuario.size() > 0){
    			Record objRecord = (Record)lstidUsuario.get(0);
    			int intUserId  =objRecord.getValue("USER_ID").asInt();
    			return intUserId;
    		}else{
    			return 0;
    		}
    	}
    	catch(Exception error)
		{
			iObjLog.error("getIdUsuario:", error);
			return 0;
		}
    }
}