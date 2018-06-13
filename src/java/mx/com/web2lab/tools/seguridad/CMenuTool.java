package mx.com.web2lab.tools.seguridad;

//java
import java.io.Serializable;
import java.util.List;

import mx.com.web2lab.util.seguridad.BGrupoMenuUtil;
import mx.com.web2lab.util.seguridad.BPermissionMenuUtil;

import org.apache.turbine.services.pull.ApplicationTool;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.pool.Recyclable;

public class CMenuTool implements Recyclable, ApplicationTool, Serializable{
	
    /**
     * El objeto que contiene los datos de la solicitud
     */
    private RunData iObjDatos;

    /**
     * Inicializa al objeto
     * @param data comment
     */
    public void init(Object aObjDatos){
    	this.iObjDatos = (RunData) aObjDatos;
    }

    /**
     * nulls out the issue and user objects
     */
    public void refresh(){
        // do not need since it is a request tool
    }

    /**
     * Constructor por omision
     */
    public CMenuTool(){
    }

// ****************** Recyclable implementation ************************


    private boolean gbolDisposed;

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
        gbolDisposed = false;
    }

    /**
     * Disposes the object after use. The method is called
     * when the object is returned to its pool.
     * The dispose method must call its super.
     */
    public void dispose(){
        iObjDatos = null;
        gbolDisposed = true;
    }

    /**
     * Checks whether the recyclable has been disposed.
     * @return true, if the recyclable is disposed.
     */
    public boolean isDisposed(){
        return gbolDisposed;
    }

    /**
     * Metodo encargado de obtener todos
     * los grupos de la tabla GRUPO_MENU
     * @return List grupos
     */
	public List getGruposMenu()throws Exception{
		BGrupoMenuUtil objBGrupoMenuUtil = new BGrupoMenuUtil();
		return objBGrupoMenuUtil.getGrupoMenuUtil();
	}

	/**
     * Metodo encargado de obtener todos
     * los permisos de la tabla MENU_PERMISSION
     * @return List permisos
     */
	public List getPermisosMenu()throws Exception{
		BPermissionMenuUtil objBPermissionMenuUtil = new BPermissionMenuUtil();
		return objBPermissionMenuUtil.getTodosLosPermisos();
	}

	/**
     * Metodo encargado de obtener todos
     * los permisos de la tabla TURBINE_PERMISSION
     * que NO estan contenidos en la tabla
     * MENU_PERMISSION
     * @return List permisos
     */
	public List getPermisosMenuNoEnMenuPermission()throws Exception{
		BPermissionMenuUtil objBPermissionMenuUtil = new BPermissionMenuUtil();
		return objBPermissionMenuUtil.getPermisosNoEnMenuPermission();
	}

	/**
     * Metodo encargado de obtener todos
     * los permisos de la tabla TURBINE_PERMISSION
     * que SI estan contenidos en la tabla
     * MENU_PERMISSION
     * @return List permisos
     */
	public List getPermisosMenuEnMenuPermission()throws Exception{
		BPermissionMenuUtil objBPermissionMenuUtil = new BPermissionMenuUtil();
		return objBPermissionMenuUtil.getPermisosEnMenuPermission();
	}

	/**
	 * Metodo que obtiene los componentes del menu para ser desplegados
	 * @return
	 * @throws Exception
	 */
	/*public Vector getOpcionMenu() throws Exception{
		BGrupoMenuUtil util = new BGrupoMenuUtil();
		iObjDatos.getParameters().getString("")
		Vector ve = util.getMenu();
		return ve;
	}*/
}