package mx.com.web2lab.tools.general;

import java.io.Serializable;

import org.apache.turbine.services.pull.ApplicationTool;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.pool.Recyclable;

public class SecureRequestToolImpl implements Recyclable, ApplicationTool, Serializable {

    /**
     * The object containing request specific data
     */
    private RunData data;
	private boolean disposed;

	public void init(Object data){
        this.data = (RunData)data;
    }

	public void refresh(){
        // do not need since it is a request tool
    }

    /**
     * Constructor does initialization stuff
     */
    public SecureRequestToolImpl(){
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
