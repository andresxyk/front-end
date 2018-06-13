 package mx.com.web2lab.screens;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.modules.screens.VelocitySecureScreen;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

 public class Default extends VelocitySecureScreen implements Serializable
 {
     /** Logging class from commons.logging */
     private static Log iObjLog = LogFactory.getLog(Default.class);

     /**
      * Build the velocity context.
      * @param data - The Turbine RunData.
      * @param context - The velocity context.
      */
     protected void doBuildTemplate( RunData aObjData, Context aObjContext )
         throws Exception
     {
     	iObjLog.debug("- - - - - - entro al default");
     	User objUsuario = aObjData.getUser();
     	if(objUsuario.hasLoggedIn()){
     		HttpSession objSesion = aObjData.getSession();
     		String menu = objSesion.getAttribute("menu")+"";
     		aObjContext.put("menuBar2", menu);
     	}else{
     		aObjData.setScreenTemplate("web2lab,seguridad,ExpedienteElectronico.vm");
     	}
     }

     protected boolean isAuthorized(RunData data)
	    throws Exception
	{
	    return true;
	}

 }
