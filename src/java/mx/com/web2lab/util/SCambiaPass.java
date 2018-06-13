package mx.com.web2lab.util;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mx.com.web2lab.backend.util.beans.sistema.UsuarioBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.util.BasePeer;
import org.apache.torque.util.Criteria;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.util.security.TurbineSecurityException;

import com.workingdogs.village.Record;




public class SCambiaPass  implements Serializable{
	
	private static Log iObjLog = LogFactory.getLog(SCambiaPass.class);
	
	 /**
     * Metod que define si el usuario debe de cambiar su password al ser 
     * logeado esto esta definido por un periodo que se toma de un archivo
     * de propiedades
     * @param aObjUser
     * @return true si el tiempo valido de su password ha expirado,
     * false en caso contrario
     * @throws Exception
     */
    public boolean getCambiaPasswd(String aStrUserName) throws Exception{
    	boolean bolCamPasswd = true;
    	int intDias = 0;
    	String strQuery = "select sysdate-modified from " +
    			" turbine_user where LOGIN_NAME = '"+aStrUserName+"'";
    	iObjLog.debug("   queryy a ejecutar "+strQuery);
    	List objDiffDias = BasePeer.executeQuery(strQuery);
   		Iterator objIter = objDiffDias.iterator();
   		while (objIter.hasNext()){
   			Record objRecord = (Record)objIter.next();
   			intDias = (objRecord.getValue(1).asInt());
   		}
    	if( intDias < 90 ){
    		bolCamPasswd = false;
    	}
    	iObjLog.debug("valor regresado "+bolCamPasswd);
    	return bolCamPasswd;
    }
    
    /**
     * Metodo que obtiene los datos de un empleado a travez de su usuario 
     * @param aStrLogin
     * @param aStrPasswd
     * @return
     * @throws TurbineSecurityException
     */
    public UsuarioBean getDatosUsuario(String aStrLogin)
	 throws TurbineSecurityException, Exception {
    	Criteria objCriteria = new Criteria();
    	objCriteria.add(TurbineUserPeer.LOGIN_NAME, aStrLogin);
    	List objUsuariosPeer = TurbineUserPeer.doSelect(objCriteria);
    	Iterator objIter = objUsuariosPeer.iterator();
    	UsuarioBean objUsuarioBean = new UsuarioBean();
    	while (objIter.hasNext()){
    		TurbineUser objTurbineUser = (TurbineUser)objIter.next(); 
	    	objUsuarioBean.setIStrObjApellido(objTurbineUser.getLastName());
	    	objUsuarioBean.setIStrObjNombre(objTurbineUser.getFirstName());
	    	objUsuarioBean.setIIntIdUsuario(objTurbineUser.getUserId());
	    	objUsuarioBean.setIStrObjLogin(objTurbineUser.getUserName());
            objUsuarioBean.setIStrObjEmail(objTurbineUser.getEmail());
    	}
    	return objUsuarioBean;
    }
    
    /**
     * Metodo que actualiza el password de un usuario cuando este decide
     * actualizarlo o el sistema decide que su password ha expirado
     * @param aStrUser usuario que esta logeado
     * @param aStrNuePasswd nuevo password que sera actualizado
     * @return true si el password fue actualizado, de lo contrario false
     * @throws Exception
     */
    public boolean setActuPasswd(String aStrUser, String aStrNuePasswd) 
		throws Exception{
    	boolean bolActuPass = false;
    	try{
    		Criteria objCriteria = new Criteria();
    		objCriteria.add(TurbineUserPeer.LOGIN_NAME, aStrUser);
    		List objTurbinUsers = TurbineUserPeer.doSelect(objCriteria);
    		Iterator objIter = objTurbinUsers.iterator();
    		TurbineUser aObjUsuarioBean = new TurbineUser();
    		while(objIter.hasNext()){
    			aObjUsuarioBean = (TurbineUser)objIter.next();
    		}
    	boolean bolPassIgual = TurbineSecurity.checkPassword(aStrNuePasswd, aObjUsuarioBean.getPassword());
    	if(bolPassIgual){
    		bolActuPass = false;
    	}else {
        	aObjUsuarioBean.setPassword(TurbineSecurity.encryptPassword(aStrNuePasswd));
            aObjUsuarioBean.setModified(new Date());
    		//aObjUsuarioBean.setPassword(aStrNuePasswd);
    		bolActuPass = true;
    	}
        aObjUsuarioBean.save();
    	}catch(Exception aObjException){
    		throw aObjException;
    	}
    	return bolActuPass;
    }

    /**
     * Metodo que actualiza la fecha en que se logeo el usuario
     * por ultima vez 
     * @param aStrUser
     * @return un true si la actualizacion se realizo satisfactoriamente
     * @throws Exception
     */
    public boolean setActuLogin(String aStrUser) 
		throws Exception{
    	boolean bolActuLog = false;
    	try{
    		Criteria objCriteria = new Criteria();
    		objCriteria.add(TurbineUserPeer.LOGIN_NAME, aStrUser);
    		List objTurbinUsers = TurbineUserPeer.doSelect(objCriteria);
    		Iterator objIter = objTurbinUsers.iterator();
    		TurbineUser aObjUsuarioBean = new TurbineUser();
    		while(objIter.hasNext()){
    			aObjUsuarioBean = (TurbineUser)objIter.next();
    		}
            aObjUsuarioBean.setLastLogin(new Date());
            aObjUsuarioBean.save();
            bolActuLog = true; 
    	}catch(Exception aObjException){
    		throw aObjException;
    	}
    	return bolActuLog;

    }
}
