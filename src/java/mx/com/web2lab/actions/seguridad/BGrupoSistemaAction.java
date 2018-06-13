package mx.com.web2lab.actions.seguridad;

//Negocio de Framwork
import java.io.Serializable;

import mx.com.web2lab.actions.SecureAction;
import mx.com.web2lab.backend.interfaz.MSeguridad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;


public class BGrupoSistemaAction extends SecureAction implements Serializable{
	
	/**log de la aplicacion*/
	private static Log iObjLog = LogFactory.getLog(BGrupoSistemaAction.class);
	
  /**
   * Metodo encargado de insertar un nuevo grupo en el esquema de seguridad
   * de Turbine
   * @param data aobjDatos
   * @param context aobjContexto
   * @throws Exception
   */
  public void doInserta(RunData aobjDatos, Context aobjContexto)throws Exception{
  	String strNombreGrupo = aobjDatos.getParameters().getString("txtNombre");
  	MSeguridad objSeguridad = new MSeguridad();
    if(objSeguridad.insertaGrupo(strNombreGrupo)){	  
    	//AQUI SE INSERTARA UN NUEVO REGISRO            	
    	aobjContexto.put("strMensaje", ""+strNombreGrupo+"");
    	aobjContexto.put("bolCreado", "true");
    }else{    		    	
    	aobjContexto.put("strMensaje", ""+strNombreGrupo+"");
    	aobjContexto.put("bolCreado", "false");	    	
    }
  }

  /**
   * Metodo encargado de actualizar un  grupo en el esquema de seguridad
   * @param data aobjDatos
   * @param context aobjContexto
   * @throws Exception
   */
  public void doActualiza(RunData aobjDatos, Context aobjContexto)throws Exception{
  		String strNombreGrupoAnt = aobjDatos.getParameters().getString("hdnNombreGrupoAnt");  			      
		String strNombreGrupo = aobjDatos.getParameters().getString("txtNombre");
		MSeguridad objSistema = new MSeguridad();
	  	if ( objSistema.actualizaGrupo(strNombreGrupoAnt,strNombreGrupo) ){	  		
	  		//Aqui se actualizara el grupo				  		
			aobjContexto.put("strMensajeModif", strNombreGrupo);
			aobjContexto.put("bolModif", "true");  
	  	}else{
	  		//No se actualiza, porque ya existe uno con el mismo nombre
	  		iObjLog.debug(">>>>>>>>>>>>>Ya existe el mismo grupo:");
	  		aobjContexto.put("strMensajeModif", strNombreGrupo);
	  		aobjContexto.put("bolModif", "false");
	  	}
  }

  /**
   * Metodo encargado de eliminar un  grupo en el esquema de seguridad
   * @param data aobjDatos
   * @param context aobjContexto
   * @throws Exception
   */
  public void doElimina(RunData aobjDatos, Context aobjContexto)throws Exception{  	
	  	String strTurbineGrupo = aobjDatos.getParameters().getString("hdnNombreGrupo");
	  	MSeguridad objSistema = new MSeguridad();
	  	if(objSistema.eliminaGrupo(strTurbineGrupo)){
	  		//Se eliminara el grupo	  		
	  		aobjContexto.put("bolBorrado", "true");
	      	aobjContexto.put("strGrupoBorrado", ""+strTurbineGrupo+"" );
	  	}else{
	  		//No se eliminara el grupo
	  		aobjContexto.put("bolNoBorro", "true");
	      	aobjContexto.put("strGrupoNoBorrado", ""+strTurbineGrupo+"" );  	     
		}
  }

  /**
   * Metodo que se ejecuta cuando no se encuentra
   * alguna accion especificada
   * @param RunData aobjDatos
   * @param Context aobjContexto
   * @throws Exception
   */
  public void doPerform(RunData aobjDatos, Context aobjContexto)throws Exception{        
      String strGrupoBuscado = aobjDatos.getParameters().getString("txtNombreGrupo");
      String strBuscaTodos = aobjDatos.getParameters().getString("chkSelTodos");
      if ( strBuscaTodos != null && strBuscaTodos.length() > 0){
      	aobjContexto.put("strNomGrupo", "" );
      }
      else{
	        iObjLog.debug("<<<<<<<<<<<<El valor de la caja de text de grupo:"+strGrupoBuscado);
	        aobjContexto.put("strNomGrupo", ""+strGrupoBuscado+"" );
      }
      if(strGrupoBuscado == null){
      	aobjContexto.put("strNomGrupo","");
      }
  }  
}
