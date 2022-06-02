package mx.com.web2lab.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mx.com.web2lab.backend.hbm.ConfiguracionProperties;

public class ConfiguracionPropertiesFront {


    /** iObjLog de la aplicacion */
    private static Log iObjLog = LogFactory.getLog(ConfiguracionProperties.class);

    /** Almacena las propiedades del archivo Configuracion.properties <code>iObjProperties</code> */
    private static Properties iObjProperties;
    
    public static boolean bolProduccion = true;

    /**
     * Constructor
     *  
     */
    private ConfiguracionPropertiesFront() {
    }

    // Crea el objeto Properties para el archivo Configuracion.properties
    // en el archivo Hibernate.properties
    static {
        InputStream objIs = null;
        try {
            iObjLog.debug("ConfigurandoHibernate:!!");
            // Ruta del archivo Configuracion.properties
            String fileConfig = "";
            if (bolProduccion) {
              fileConfig = "mx/com/web2lab/util/Configuracion.properties";
            } else {
              fileConfig = "c:/infodiamex/Configuracion.properties";
            }
            iObjLog.info("RutaArchivoPropiedades:Configuracion=" + fileConfig);
            // Abre un flujo de entrada desde el archivo de
            // configuracion Configuracion.properties            
            if (bolProduccion) {
            	objIs = ConfiguracionProperties.class.getClassLoader().getResourceAsStream(fileConfig);
            } else {
                objIs = new java.io.FileInputStream(new java.io.File(fileConfig));            
            }
            if (objIs == null) {
                iObjLog.debug("ElFlujoDeConfiguracionEsNulo:Configuracion.properties!!!!");
                throw new RuntimeException("ErrorAlBuscarElArchivo:Carper.properties en la ruta:"+ fileConfig);
            }
            // Instancia un objeto de propiedades
            iObjProperties = new Properties();
            // Lee el listado de propiedades (pares llave y valor) del
            // flujo de entrada.
            iObjLog.info("CargandoPropiedades....:Configuracion.properties");
            iObjProperties.load(objIs);
        } catch (IOException ioe) {
            iObjLog.error("ErrorIOException:!!!", ioe);
            throw new RuntimeException("Exception building Propiedades:Configuracion.propierties "+ioe.getMessage()+ioe);
        } finally {
            try {
                objIs.close();
            } catch (Exception e) {
                iObjLog.error("ErrorAlCerrarElFlujoDeConfiguracion:Configuracion.properties!!!",e);
            }
        }
    }

    /**
     * obtiene el valor para la propiedad especificada en el archivo
     * Configuracion.properties
     * @param aObjStrNombrePropiedad
     * @return Valor para la propiedad especificada
     */
    public static String getPropiedad(String aObjStrNombrePropiedad) {
        //se verifica si fueinicializado correctamente
        if (iObjProperties!=null){
        	return iObjProperties.getProperty(aObjStrNombrePropiedad);
        }
        return null;
    }


}
