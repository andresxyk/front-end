package mx.com.web2lab.util.seguridad;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.util.BasePeer;

import com.workingdogs.village.Record;


public class BPermissionMenuUtil implements Serializable{

	private static Log iObjlog = LogFactory.getLog(BPermissionMenuUtil.class);

	/**
	 * Este metodo realiza un inserci&oacute;n de la
	 * BD, en la tabla MENU_PERMISSION
	 * @param int id permiso
	 * @param int id grupo_menu
	 * @param String descripcion
	 * @return boolean true si inserto, false de lo contrario
	 */
	public boolean setInserta(int aIntIdPerm, int aIntIdGpoMenu, String aStrMenuLiga) throws Exception{
		boolean bolInserto = false; // validacion de insercion
		int intId=getNumMax("ID_MENU_PERMISSION", "MENU_PERMISSION");
		String strConsulta = "insert into MENU_PERMISSION values("+intId+","+aIntIdPerm+","+
						aIntIdGpoMenu+", '"+aStrMenuLiga+"')";
		iObjlog.debug("<<<<<<QUERY:|"+strConsulta+"|");
		int intEstado = BasePeer.executeStatement(strConsulta);
		if(intEstado > 0){
			bolInserto = true; // si inserto
		}
		return bolInserto;
	}

	/**
	 * Este metodo realiza un update de la
	 * BD, en la tabla MENU_PERMISSION
	 * @param int id
	 * @param String descripcion
	 * @return boolean true si inserto, false de lo contrario
	 */
	public boolean setActualiza(int aIntIdPerm, int aIntIdGpoMenu, String aStrMenuLiga) throws Exception{
		boolean bolActualiza = false; // validacion de actualizacion
		String strConsulta = "update MENU_PERMISSION set ID_GRUPO_MENU="+aIntIdGpoMenu+
					   ", MENU_LINK='"+aStrMenuLiga+"' where PERMISSION_ID="+aIntIdPerm;		
		int intEstado = BasePeer.executeStatement(strConsulta);
		if(intEstado > 0){
			bolActualiza = true; // si inserto
		}
		return bolActualiza;
	}

	/**
	 * Este metodo obtiene el maximo valor consecutivo
	 * (el siguiente id) del campo y la tabla que se
	 * pasa como parametro
	 * @param String Nombre del campo
	 * @param String Nombre de la tabla
	 * @return int Siguiente consecutivo
	 */
	public int getNumMax(String aStrNomCampo, String aStrNomTabla) throws Exception{
        String strConsulta = "select max("+aStrNomCampo+") from "+aStrNomTabla;
        int intIdmax = 0;
        List iIdsmax = BasePeer.executeQuery(strConsulta);
        for (int i = 0; i < iIdsmax.size(); i++){
            Record objRecord = (Record)iIdsmax.get(i);
            intIdmax = objRecord.getValue(1).asInt();
            intIdmax ++;
        }
        return intIdmax;
    }

	/**
     * Este metodo obtiene todos los permisos que estan
     * contenidos en la tabla MENU_PERMISSION
     * @return List Permisos
     */
    public List getTodosLosPermisos() throws Exception{
    	String strConsulta="select * from TURBINE_PERMISSION order by permission_name";
		List iPermisos = BasePeer.executeQuery(strConsulta);
		return iPermisos;
    }

    /**
     * Este metodo obtiene todos los permisos que estan
     * contenidos en la tabla MENU_PERMISSION
     * @return List Permisos
     */
    public List getPermisosNoEnMenuPermission() throws Exception{
    	String strConsulta="select * from turbine_permission where permission_id "+
    				 "not in (select permission_id  from menu_permission) order by permission_name";
		List iPermisos = BasePeer.executeQuery(strConsulta);
		return iPermisos;
    }

    /**
     * Este metodo obtiene todos los permisos que estan
     * contenidos en la tabla MENU_PERMISSION
     * @return List Permisos
     */
    public List getPermisosEnMenuPermission() throws Exception{
    	String strConsulta="select distinct a.permission_id, b.permission_name, a.menu_link, "+
					 " a.id_grupo_menu from menu_permission a, turbine_permission b "+
					 " where b.permission_id = a.permission_id order by permission_name";
		List iPermisos = BasePeer.executeQuery(strConsulta);
		return iPermisos;
    }
}
