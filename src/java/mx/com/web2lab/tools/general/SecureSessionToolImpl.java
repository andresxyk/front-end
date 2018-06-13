package mx.com.web2lab.tools.general;

import java.io.Serializable;

import org.apache.turbine.services.pull.RunDataApplicationTool;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.pool.Recyclable;

public class SecureSessionToolImpl implements Recyclable, RunDataApplicationTool, Serializable{

    /**
     * The object containing request specific data
     */
    private RunData data;
	private boolean disposed;
    
	/**
     * Does not need to init beacuse it's s Session tool 
     * @param data aobjDatos
     */
    public void init(Object data){
//        this.data = (RunData)data;
    }
//
    /**
     * nulls out the issue and user objects
     */
    public void refresh(RunData data){
    	this.data = (RunData)data;
        // do not need since it is a request tool
    }

    /**
     * Constructor does initialization stuff
     */
    public SecureSessionToolImpl(){
    }


//  ****************** Recyclable implementation ************************

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
        data = null;
        disposed = true;
    }

    /**
     * Checks whether the recyclable has been disposed.
     * @return true, if the recyclable is disposed.
     */
    public boolean isDisposed(){
        return disposed;
    }
                
}
