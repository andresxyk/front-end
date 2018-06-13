package mx.com.web2lab.actions.sms;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import mx.com.web2lab.actions.SecureAction;
import mx.com.web2lab.backend.dao.sms.AdministracionSmsDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.DataBackendException;
import org.apache.turbine.util.security.PasswordMismatchException;
import org.apache.turbine.util.security.TurbineSecurityException;
import org.apache.turbine.util.security.UnknownEntityException;
import org.apache.velocity.context.Context;

public class SmsAction extends SecureAction {

    private static Log iObjLog = LogFactory.getLog(SmsAction.class);

    public SmsAction() {
    }

    public void doRecepcionsmsaltamedico(RunData aObjData, Context aObjContext)
    throws Exception{	
        try {
        	AdministracionSmsDao objAdminSms = new AdministracionSmsDao();
            String strNumero = aObjData.getParameters().getString("snumero");
            String strMensaje = aObjData.getParameters().getString("smensaje");
    		objAdminSms.recepcionSms((new Integer(strNumero)).intValue(), strMensaje);
            aObjContext.put("mensaje","Mensaje recibido.");
            aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");
        }catch(DataBackendException aError){
            iObjLog.error("TurbineSecurityException LoginAction.doPerform", aError);
            aObjContext.put("mensaje","Error en la recepcion del Mensaje.");
            aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");
        }catch(UnknownEntityException aError){
            iObjLog.error("TurbineSecurityException LoginAction.doPerform", aError);
            aObjContext.put("mensaje","Error en la recepcion del Mensaje.");
            aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");
        }catch(PasswordMismatchException aError){
            iObjLog.error("TurbineSecurityException LoginAction.doPerform", aError);
            aObjContext.put("mensaje","Error en la recepcion del Mensaje.");
            aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");
        }catch(TurbineSecurityException aError){
            iObjLog.error("TurbineSecurityException LoginAction.doPerform", aError);
            aObjContext.put("mensaje","Error en la recepcion del Mensaje.");
            aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");
        }catch (Exception aError){
        	iObjLog.error(" Error en LoginAction.doPerform..... ", aError);
            aObjContext.put("mensaje","Error en la recepcion del Mensaje.");
        	aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");
        	throw aError;
        }
    }

    public void doEnviosms(RunData aObjData, Context aObjContext)
    throws Exception{	
        try {
            String strNumero = aObjData.getParameters().getString("snumero");
            String strMensaje = aObjData.getParameters().getString("smensaje");
            String strURL = "http:////gtp.bz//olab/index.php?ani=" + strNumero + "&msj=" + strMensaje + "&carr=C";
            iObjLog.debug(strURL);
            URL url = new URL(strURL);
            URLConnection connection = url.openConnection();
            connection.setDoInput(true);
            InputStream inStream = connection.getInputStream();
            BufferedReader input =
            new BufferedReader(new InputStreamReader(inStream));
            String line = "";
            while ((line = input.readLine()) != null) {
                aObjContext.put("mensaje",line);            	
            }
            aObjContext.put("mensaje","Mensaje recibido.");
            aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");
        }catch (Exception aError){
        	iObjLog.error(" Error en LoginAction.doPerform..... ", aError);
            aObjContext.put("mensaje","Error en el envio del Mensaje.");
        	aObjData.setScreenTemplate("/web2lab,seguridad,Login.vm");
        	throw aError;
        }
    }
}