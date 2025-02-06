package mx.com.web2lab.ajax.dwr.capturaorden;

import java.util.List;

import mx.com.web2lab.actions.seguridad.SeguridadUtil;
import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;

import mx.com.web2lab.backend.beans.comer.ClienteBean;
import mx.com.web2lab.backend.beans.comer.ConvenioBean;
import mx.com.web2lab.backend.dao.comer.ClientesNewDao;
import mx.com.web2lab.backend.dao.tools.ConsultaOrdenesDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineUser;


public class DatosImprimeFacturaAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(DatosImprimeFacturaAjax.class);	
	
	public ClienteBean buscarCliente(ClienteBean objClienteBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarCliente:Entrando... ");		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			objClienteBean = (ClienteBean)objDAOCliente.buscarCliente(objClienteBean).get(0);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarCliente:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarCliente:Exception....", aObjException);
			objClienteBean = null;
			throw aObjException;
		}
		return objClienteBean;
	}	

	public String buscarClientes(ClienteBean objClienteBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarClientes:Entrando... ");		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			strReturn = this.showClientes(objDAOCliente.buscarCliente(objClienteBean));
			iObjLog.debug("Saliendo DatosClienteAjax.buscarClientes:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarClientes:Exception....", aObjException);
			objClienteBean = null;
			throw aObjException;
			
		}
		return strReturn;
	}	

	public ConvenioBean buscarConvenio(ConvenioBean objConvenioBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarConvenio:Entrando... ");		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			objConvenioBean = (ConvenioBean)objDAOCliente.buscarConvenio(objConvenioBean,"").get(0);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarConvenio:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarConvenio:Exception....", aObjException);
			objConvenioBean = null;
			throw aObjException;
		}
		return objConvenioBean;
	}	
	
	public String buscarConvenios(ConvenioBean objConvenioBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarConvenios:Entrando... ");		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			strReturn = objDAOCliente.buscarConvenios(objConvenioBean);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarConvenios:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarConvenios:Exception....", aObjException);
			objConvenioBean = null;
			throw aObjException;
		}
		return strReturn;
	}	

	public String buscarConveniosOrdenes(ConvenioBean objConvenioBean,String strFechaInicio, String strFechaTermino) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.buscarConveniosOrdenes:Entrando... ");		
		String strReturn = "";
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ConsultaOrdenesDao objDAOCliente = new ConsultaOrdenesDao();
			strReturn = objDAOCliente.buscarOrdenesConvenios(objConvenioBean,strFechaInicio,strFechaTermino);
			iObjLog.debug("Saliendo DatosClienteAjax.buscarConveniosOrdenes:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.buscarConveniosOrdenes:Exception....", aObjException);
			objConvenioBean = null;
			throw aObjException;
		}
		return strReturn;
	}	
	
	
	public ClienteBean actualizaCliente(ClienteBean objClienteBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.actualizaCliente:Entrando... ");		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			objClienteBean = objDAOCliente.setClienteActualizacion(objClienteBean);
			iObjLog.debug("Saliendo DatosClienteAjax.actualizaCliente:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.actualizaCliente:Exception....", aObjException);
			objClienteBean = null;
			throw aObjException;
		}
		return objClienteBean;
	}	

	public ConvenioBean actualizaConvenio(ConvenioBean objConvenioBean) throws Exception
	{
		iObjLog.debug("Entrando DatosClienteAjax.actualizaConvenio:Entrando... ");		
		try {			
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");						
			ClientesNewDao objDAOCliente = new ClientesNewDao();
			objConvenioBean = objDAOCliente.setConvenioActualizacion(objConvenioBean);
			iObjLog.debug("Saliendo DatosClienteAjax.actualizaConvenio:Saliendo...  ");
			objDAOCliente = null;
		} catch (Exception aObjException){
			iObjLog.error("Error DatosClienteAjax.actualizaConvenio:Exception....", aObjException);
			objConvenioBean = null;
			throw aObjException;
		}
		return objConvenioBean;
	}	
	
	private String showClientes(List lstClientes) throws Exception
	{		
		ClienteBean objClientes = new ClienteBean();
		String strReturn = "";		
		int intClientes = 0;
		iObjLog.debug("Entrando a DatosPacienteAjax.consultaMedicosGrid:Entrando... ");
		try {
			 if (lstClientes != null) {
				 intClientes = lstClientes.size();
			 }
			 strReturn = ("<table border='0' align='center' style='width: 883px' class='tabla'>" + 
					 		"<tr>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Total de Clientes " + intClientes +
								"	</font></b>" +
								"</th>"  + 
							"</tr>" +
						 "</table>" +	
						 "<table border='0' align='center' style='width: 883px' class='tabla'>" +
							"<tr>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>#" + 
								"	</font></b>" +
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Clave" + 
								"	</font></b>" +
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Razon Social" + 
								"	</font></b>" +
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>RFC" + 
								"</th>" + 
							"</tr>");			    
			 if (lstClientes != null) {
				int y; 
				for (int i = 0; i < lstClientes.size() ; i++)
				{
					objClientes = (ClienteBean)lstClientes.get(i);						
					y = i + 1;
					strReturn += ("<tr>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:clienteAceptado(" + objClientes.getCcliente() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											y + 
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:clienteAceptado(" + objClientes.getCcliente() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objClientes.getCcliente() + 
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:clienteAceptado(" + objClientes.getCcliente() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objClientes.getSrazonsocial() +
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:clienteAceptado(" + objClientes.getCcliente() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
											objClientes.getSrfc()+
										"</a></td>" + 
									 "</tr>");
				}
			}
			strReturn += ("</table>");
			return strReturn; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosMedicoAjax.consultaMedicosGrid:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	
	
	
	/**
     * Metodo que verifica que exista una sesion valida 
     * @return
     * @throws Exception
     */
    public boolean isSesionValida() {
    	boolean valida = false;
    	try{
    		valida = super.isSesionValida(true);
    	}catch(Exception e ){
    		iObjLog.error("isSesionValida:No existe una sesion valida para el usuario");
    		return valida;
    	}
    	return valida;
    }
    	
	public int validaAutetificacion(String strUsuario,String strPassword) throws Exception
	{
		int intReturn = 0;
		iObjLog.debug("Entrando DatosMedicoAjax.validaAutetificacion:Entrando... ");		
		try {			
    		User objUsuario = TurbineSecurity.getAuthenticatedUser(strUsuario.trim(), strPassword.trim());
    		if(objUsuario!=null) {
                TurbineUser objTurbineUser = getUserByName(strUsuario.trim());
                intReturn = objTurbineUser.getUserId();    						
    		} else {
    			intReturn = 0;    			
    		}
			iObjLog.debug("Saliendo DatosMedicoAjax.validaAutetificacion:Saliendo...  " + intReturn);
		} catch (Exception aObjException){
			iObjLog.error("Error DatosMedicoAjax.validaAutetificacion:Exception....", aObjException);
			throw aObjException;
		}
		return intReturn;
	}	
	
    public TurbineUser getUserByName(String strName) throws Exception {
        return SeguridadUtil.getUserByName(strName);
    }	
}
