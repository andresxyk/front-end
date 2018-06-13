package mx.com.web2lab.tools;


//FRAMEWORK
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.services.pull.ApplicationTool;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.pool.Recyclable;

public class RelativePathTool
    implements Recyclable, ApplicationTool, Serializable{
    
    /** The Object containing request specific iObjData*/
    private RunData iObjData;
    /** log de la aplicacion */
    private static Log iObjLog = LogFactory.getLog(RelativePathTool.class);    

	
	/** path principal  <code>iObjStrBasePath</code> */
	private String iObjStrBasePath		 	 = "";
	/** path secundario <code>iObjStrBaseURI</code> */
	private String iObjStrBaseURI			 = "";
	/** path base de templates <code>iObjStrBasePathTemplate</code> */
	private String iObjStrBasePathTemplate 	 = "";
	/** path base de las acciones <code>iObjStrBasePathAction</code> */
	private String iObjStrBasePathAction	 = "";
	/** parametros del URL <code>iObjStrParameters</code> */
	private String iObjStrParameters		 = "";
	/** pantalla solicitada <code>iObjStrScreen</code> */
	private String iObjStrScreen			 = "";
	/** accion solicitada <code>iObjStrAction</code> */
	private String iObjStrAction			 = "";

	
	/**
	 * inicializa el tool
	 * @param aObjData
	 */
    public void init(Object aObjData) {
        this.iObjData = (RunData)aObjData;
        String objStrContextRoot = iObjData.getContextPath();
        iObjStrBasePath = objStrContextRoot + "/servlet";
        iObjStrBaseURI = objStrContextRoot + "/";
        iObjLog.debug("ElValorPara:BasePath="+iObjStrBasePath+"ElValorPara:BaseURI="+iObjStrBaseURI);
    }

    /**
     * nulls out the issue and user objects
     */
    public void refresh() {
        // do not need since it is a request tool
    }

    /**
     * Constructor does initialization stuff
     */
    public RelativePathTool() {
    }

    /**
     * establece la pagina a mostrar
     * @param aObjStrScreen
     * @return esta misma instancia
     */	
	public RelativePathTool setPage(String aObjStrScreen) {
		this.iObjStrBasePathTemplate = "/template/";
		this.iObjStrScreen = aObjStrScreen;
		return this;
	}
	
    /**
     * establece la pagina a mostrar
     * @param aObjStrScreen
     * @return esta misma instancia
     */	
	public String getPage(String aObjStrScreen) {
		this.iObjStrBasePathTemplate = "/template/";
		this.iObjStrScreen = aObjStrScreen;
		return this.toString();
	}

	/**
	 * establece el action a ejecutar
	 * @param aObjStrAction
	 * @return esta misma instancia
	 */
	public RelativePathTool setAction(String aObjStrAction) {
		this.iObjStrBasePathAction = "/action/";
		this.iObjStrAction = aObjStrAction;
		return this;
	}

	/**
	 * establece los parametros pasados al URL
	 * @param aObjStrParam nombre parametro
	 * @param aObjStrValue valor parametro
	 * @return esta misma instancia
	 */	
	public RelativePathTool addQueryData(String aObjStrParam, String aObjStrValue) {
		if(	iObjStrParameters.equals("")) {
			this.iObjStrParameters += "?" + aObjStrParam + "=" + aObjStrValue;
		}else {
			this.iObjStrParameters += "&" + aObjStrParam + "=" + aObjStrValue;
		}
		return this;
	}

	/**
	 * agrega parametros al URL
	 * @param aObjStrParam nombre del parametro
	 * @param aObjStrValue valor del parametro
	 * @return esta misma instancia
	 */	
	public RelativePathTool addPathInfo(String aObjStrParam, String aObjStrValue) {
		addQueryData(aObjStrParam, aObjStrValue);
		return this;
	}

	/**
	 * representacion del objeto
	 */
	public String toString() {
		String ruta = this.iObjStrBasePath + this.iObjStrBasePathTemplate + this.iObjStrScreen + this.iObjStrParameters + this.iObjStrBasePathAction + this.iObjStrAction;
		this.iObjStrBasePathTemplate  = "";
		this.iObjStrBasePathAction	  = "";
		this.iObjStrScreen			  = "";
		this.iObjStrParameters		  = "";
		this.iObjStrAction			  = "";
		return ruta;
	}

	/**
	 * Se utiliza para obtener la ruta de algun recurso
	 * @param aObjStrCarpetaRaiz recurso solicitado
	 * @return la ruta del recurso solicitado
	 */
	public String getURI(String aObjStrCarpetaRaiz) {
		return this.iObjStrBaseURI + aObjStrCarpetaRaiz;
	}

    // ****************** Recyclable implementation ************************ /

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
    public void recycle() {
        disposed = false;
    }

    /**
     * Disposes the object after use. The method is called
     * when the object is returned to its pool.
     * The dispose method must call its super.
     */
    public void dispose() {
        iObjData = null;
        disposed = true;
    }

    /**
     * Checks whether the recyclable has been disposed.
     * @return true, if the recyclable is disposed.
     */
    public boolean isDisposed() {
        return disposed;
    }
}

