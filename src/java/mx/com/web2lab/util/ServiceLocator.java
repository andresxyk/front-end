package mx.com.web2lab.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class ServiceLocator  implements Serializable{
    private InitialContext iObjIniCtx;
    /* usada para almacenar las referencias al home de las fachadas*/
    private Map iObjCache; 
    /* usada para el iObjLog del sistema */
	private static Log iObjLog = LogFactory.getLog(ServiceLocator.class);
	/* unica instancia disponible de la clase*/
    private static ServiceLocator objServLoc;
    /* inicializacion del ServiceLocator*/
    static {
        try {
            iObjLog.info("CreandoServiceLocator......");
            objServLoc = new ServiceLocator();
        } catch (Exception e) {
            iObjLog.error("ErrorAlCrearServiceLocator......", e);
            throw new RuntimeException("ErrorAlIniciar:ServiLocator....");
        }
    }
    /**
     * implementacion clasica del patron Singelton
     * 
     * @throws Exception -
     *             si un error o algo inesperado ocurre
     */
    private ServiceLocator() throws Exception {
        try {
            iObjLog.info("IniciandoServiceLocator......");
            iObjIniCtx = new InitialContext();
            iObjCache = Collections.synchronizedMap(new HashMap());
            iObjLog.info("ServiceLocatorIniciado......");
        } catch (NamingException aObjNException) {
            iObjLog.error("ErrorAlIniciar:ServiceLocator..", aObjNException);
            throw new Exception(aObjNException.getMessage());
        } catch (Exception aObjException) {
            iObjLog.error("ErrorAlIniciar:ServiceLocator..", aObjException);
            throw new Exception(aObjException.getMessage());
        }
    }
    /**
     * Regresa la instancia del ServiceLocator
     *  
     * @return instancia de ServiceLocator
     * @throws Exception -
     *             si un error o algo inesperado ocurre
     */
    static public ServiceLocator getInstance() {
        return objServLoc;
    }
    /**
     * obtiene el LocalHome especificado
     *  
     * @param JNDI Home Name
     * @return el EJB que corresponde con el nombre especificado
     * @throws Exception -
     *             si un error o algo inesperado ocurre
     */
    public EJBLocalHome getLocalHome(String objJndiHomeName)
            throws Exception {
        EJBLocalHome objLocalHome = null;
        try {
            if (iObjCache.containsKey(objJndiHomeName)) {
                iObjLog.debug("ObteniendoEjbHomeDeCache....");
                objLocalHome = (EJBLocalHome) iObjCache.get(objJndiHomeName);
            } else {
                iObjLog.debug("BuscandoEJBConJNDI....");
                objLocalHome = (EJBLocalHome) iObjIniCtx.lookup(objJndiHomeName);
                iObjCache.put(objJndiHomeName, objLocalHome);
            }
        } catch (NamingException aObjNException) {
            iObjLog.error("ErrorAlPedirEJBEn:ServiceLocator..", aObjNException);
            throw new Exception(aObjNException.getMessage());
        } catch (Exception aObjException) {
            iObjLog.error("ErrorAlPedirEJBEn:ServiceLocator..", aObjException);
            throw new Exception(aObjException.getMessage());
        }
        return objLocalHome;
    }
    /**
     * obtiene el home remoto especificado
     *  
     * @param JNDI home name
     * @param Clase del EJB
     * 
     * @return el EJB Home correspondiende al home name
     */
    public EJBHome getRemoteHome(String objJndiHomeName, Class className)
            throws Exception {
        EJBHome objHome = null;
        try {
            if (iObjCache.containsKey(objJndiHomeName)) {
                iObjLog.debug("ObteniendoEjbHomeDeCache....");
                objHome = (EJBHome) iObjCache.get(objJndiHomeName);
            } else {
                iObjLog.debug("BuscandoEJBConJNDI....");
                Object objref = iObjIniCtx.lookup(objJndiHomeName);
                Object obj = PortableRemoteObject.narrow(objref, className);
                objHome = (EJBHome) obj;
                iObjCache.put(objJndiHomeName, objHome);
            }
        } catch (NamingException aObjNException) {
            iObjLog.error("ErrorAlPedirEJBEn:ServiceLocator..", aObjNException);
            throw new Exception(aObjNException.getMessage());
        } catch (Exception aObjException) {
            iObjLog.error("ErrorAlPedirEJBEn:ServiceLocator..", aObjException);
            throw new Exception(aObjException.getMessage());
        }
        return objHome;
    }

}