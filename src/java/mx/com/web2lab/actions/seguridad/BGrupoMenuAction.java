package mx.com.web2lab.actions.seguridad;

//Negocio ITWEB
import java.io.Serializable;

import mx.com.web2lab.actions.SecureAction;
import mx.com.web2lab.util.seguridad.BGrupoMenuUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;


public class BGrupoMenuAction extends SecureAction implements Serializable{
	/** Log de la aplicaci&oacute;n*/
	private static Log iObjLog = LogFactory.getLog(BGrupoMenuAction.class);
	
	/**
	 *
	 * El metodo doInserta obtiene el dato nombre para insertarlo a la tabla GRUPO_MENU,
	 * @param txtNombre
	 *
	 */
	public void doInserta(RunData objDatos, Context objContexto)throws Exception{
		iObjLog.debug("----------------------------Corriendo en el Action Insert");		
		String strNombre = objDatos.getParameters().getString("txtNombre");
		BGrupoMenuUtil objBGrupoMenuUtil = new BGrupoMenuUtil();
		boolean bolGrupoMenu = objBGrupoMenuUtil.getGrupoExistente(strNombre);
		iObjLog.debug("----------------------------bolGrupoMenu :"+bolGrupoMenu);
		if ( bolGrupoMenu == true){
			iObjLog.debug(">>>>dato: "+strNombre+" - ya existe" );
			String strContextoMensaje = objDatos.getParameters().getString("txtNombre");	
			objContexto.put("strMensaje", ""+strContextoMensaje+"" ); 
			objContexto.put("bolCreado", "false");
		}
		if ( bolGrupoMenu == false ){
			objBGrupoMenuUtil.setInserta(strNombre);
			iObjLog.debug(">>>>dato: "+strNombre+" - es nuevo y se insert�" );
			String strContextoMensaje = objDatos.getParameters().getString("txtNombre");
			objContexto.put("strMensaje", ""+strContextoMensaje+"" ); 
			objContexto.put("bolCreado", "true");
		}
	}
	
	
	/**
	 * Este metodo doActualiza lo utilizamos para realizar la modificaci&oacute;n de la tabla
	 * GRUPO_MENU en uno de sus registros.
	 * @param txtNombre
	 * @param hndNombreAnterior
	 *
	 */
	public void doActualiza(RunData objDatos, Context objContexto)throws Exception{
		iObjLog.debug("Corriendo en el Action Update");	
		String strNombre = objDatos.getParameters().getString("txtNombre");
		String strNombreAnterior = objDatos.getParameters().getString("hndNombreAnterior");
		BGrupoMenuUtil objBGrupoMenuUtil = new BGrupoMenuUtil();
		boolean bolGrupoMenu = objBGrupoMenuUtil.getGrupoExistente(strNombre);
		if ( bolGrupoMenu == true){
			iObjLog.debug(">>>>dato: "+strNombre+" - ya existe" );
			String strMenModif = objDatos.getParameters().getString("txtNombre");	
			objContexto.put("strMensajeModif", ""+strMenModif+"" );
	    	objContexto.put("bolModif", "false");
		}
		if ( bolGrupoMenu == false ){						
			objBGrupoMenuUtil.setActualiza(strNombre, strNombreAnterior);
			String strMenModif = objDatos.getParameters().getString("txtNombre");
	    	objContexto.put("strMensajeModif", ""+strMenModif+"" );
	    	objContexto.put("bolModif", "true");
		}
	}
	
	/**
	 * En este metodo se realiza el regreso del dato buscado
	 */
	public void doPerform(RunData objDatos, Context objContexto)throws Exception{
		iObjLog.debug("Running do perform!");
    	String strBuscaTodos = objDatos.getParameters().getString("chkSeleccionaTodo");
    	String strGrupoMenu = objDatos.getParameters().getString("txtGrupoMenu");
    	iObjLog.debug("<<<<<<<<<<<<El valor de la caja de text de grupomenu:"+strGrupoMenu);
        if ( strBuscaTodos != null && strBuscaTodos.length() > 0){
        	objContexto.put("strGruMeVuel", "");
        }
        else{
	        iObjLog.debug("<<<<<<<<<<<<El valor de la caja de text de grupomenu:"+strGrupoMenu);
	        objContexto.put("strGruMeVuel", ""+strGrupoMenu+"");
        }
        if(strGrupoMenu == null){
        	 objContexto.put("strGruMeVuel","");
        }
        	
    }
	
}
