package mx.com.web2lab.actions.seguridad;

import java.io.Serializable;

import mx.com.web2lab.actions.SecureAction;
import mx.com.web2lab.backend.hbm.ConfiguracionProperties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;


public class BAutorizaAction extends SecureAction implements Serializable{


	private static Log iObjLog = LogFactory.getLog(BAutorizaAction.class);

    /**
    * Metodo que indica si el usuario es valido o no a travez
    * de  si el resultado no arroja una escepcion, el usuario 
    * es logeado de lo contrario arrogara una escepcion segun el caso
    */
    public void doPerform(RunData aObjData, Context aObjContext)
        throws Exception{
    	iObjLog.debug("AUTORIZANDODOPERFORM");
        String nomUser = aObjData.getParameters().getString("username");        
        String passwd = aObjData.getParameters().getString("password");       
        String strConcilia = aObjData.getParameters().getString("hdnConcilia");
        aObjData.setScreenTemplate("/web2lab,seguridad,Autoriza.vm");
        try{                                               
            if(SeguridadUtil.tieneAutorizacion(nomUser,
            								passwd,											
											ConfiguracionProperties.
											getPropiedad("web2lab.autorizacion.permiso")))
            	{
            		aObjContext.put("bolAutoriza","true");
            		if(strConcilia != null && !strConcilia.equals("")){
            			aObjContext.put("bolConcilia","true");
            		}
            		//aObjContext.put("mensaje","La operacion esta autorizada");
            	}else{
            		aObjContext.put("mensaje","El usuario no tiene permiso para realizar esta operacion");
            	}                   
        }catch ( Exception aObjException ){
        	iObjLog.error(" Error en doPerform..... ", aObjException);
        	throw aObjException;
        }

    }
}
