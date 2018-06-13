package mx.com.web2lab.actions.seguridad;

//Java
import java.io.Serializable;
import java.util.List;

import mx.com.web2lab.actions.SecureAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.Group;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineGroup;
import org.apache.turbine.services.security.torque.om.TurbineGroupPeer;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.DataBackendException;
import org.apache.turbine.util.security.UnknownEntityException;
import org.apache.velocity.context.Context;

public class BGrupoAction extends SecureAction implements Serializable{
	
	/**log de la aplicacion*/
	private static Log iObjLog = LogFactory.getLog(BGrupoAction.class);
	
    /**
     * Metodo encargado de insertar un nuevo grupo en el esquema de seguridad
     * de Turbine
     * @param data aobjDatos
     * @param context aobjContexto
     * @throws Exception
     */
    public void doInserta(RunData aobjDatos, Context aobjContexto)throws Exception{
    	String strNombreGrupo = aobjDatos.getParameters().getString("txtNombre");
    	Criteria objCriteria = new Criteria();
    	objCriteria.add(TurbineGroupPeer.GROUP_NAME, aobjDatos.getParameters().getString("txtNombre"));
	    List objTurbineGrupos = TurbineGroupPeer.doSelect(objCriteria);
	    if(objTurbineGrupos.size()==0){	    	
	    	Group objTurbineGrupo = TurbineSecurity.createGroup(strNombreGrupo);
	    	TurbineSecurity.saveGroup(objTurbineGrupo);        
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
    	Criteria objCriteria = new Criteria();
	    objCriteria.add(TurbineGroupPeer.GROUP_NAME, aobjDatos.getParameters().getString("hdnNombreGrupoAnt"));
	    List objTurbineGrupos = TurbineGroupPeer.doSelect(objCriteria);
	    TurbineGroup objTurbineGrupo = new TurbineGroup();
	    for(int i=0; i<objTurbineGrupos.size(); i++){
	    	objTurbineGrupo = (TurbineGroup) objTurbineGrupos.get(i);
	    }
	    Criteria obj2Criteria = new Criteria();
	    obj2Criteria.add(TurbineGroupPeer.GROUP_NAME, aobjDatos.getParameters().getString("txtNombre"));
    	List obj2Lista = TurbineGroupPeer.doSelect(obj2Criteria);
    	if ( obj2Lista.size() == 0 ){
		    objTurbineGrupo.setName(aobjDatos.getParameters().getString("txtNombre"));
			objTurbineGrupo.save();		
			aobjContexto.put("strMensajeModif", aobjDatos.getParameters().getString("txtNombre"));
			aobjContexto.put("bolModif", "true");  
    	}else{
    		iObjLog.debug(">>>>>>>>>>>>>Ya existe el mismo grupo:");
    		aobjContexto.put("strMensajeModif", aobjDatos.getParameters().getString("txtNombre" ));
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
    	Group objTurbineGrupo = TurbineSecurity.getGroupByName(
                aobjDatos.getParameters().getString("hdnNombreGrupo"));
    	String strTurbineGrupo = aobjDatos.getParameters().getString("hdnNombreGrupo");
        try{
        	TurbineSecurity.removeGroup(objTurbineGrupo);
        	aobjContexto.put("bolBorrado", "true");
        	aobjContexto.put("strGrupoBorrado", ""+strTurbineGrupo+"" );
        }catch (UnknownEntityException uee){
        }catch (DataBackendException dbe){
        	aobjContexto.put("bolNoBorro", "true");
        	aobjContexto.put("strGrupoNoBorrado", ""+strTurbineGrupo+"" );
        	iObjLog.debug(">>>>>>>Se captura la excepción : "+dbe);
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
