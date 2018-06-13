package mx.com.web2lab.ajax.dwr.capturaorden;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;

import mx.com.web2lab.backend.beans.ap.OrdenBean;
import mx.com.web2lab.backend.beans.ap.PagoPacienteBean;
import mx.com.web2lab.backend.dao.ap.DatosOrdenDao;
import mx.com.web2lab.backend.dao.ap.PagosDao;

import mx.com.web2lab.backend.util.formatos.Formatos;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DatosPagoAjax extends AjaxAction {
	private static Log iObjLog = LogFactory.getLog(DatosPagoAjax.class);
	
	public DatosPagoAjax(){
	}
		    
	public String consultaOrdenesGrid(int kPaciente) throws Exception {
		iObjLog.debug("Entrando a DatosPagoAjax.consultaOrdenesGrid:Entrando...Parametros... " + kPaciente);
		DatosOrdenDao objDAOOrden = new DatosOrdenDao();
		OrdenBean objOrden = null;
		List lstOrdenes = objDAOOrden.buscarOrdenesVSPaciente(kPaciente,0,false);
		String strReturn = "";		
		try {//883px
			 strReturn = ("<table border='0' align='center' style='width: 883px' class='tabla'>" + 
							"<tr>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>C&oacute;digo de Orden" + 
							"	</font></b>" +
							"</th>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>Fecha Orden" + 
							"</th>" + 
							"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
							"	<b><font color='black'>Interpretacion" + 
							"</th>" + 
						"</tr>");			    
			 if (lstOrdenes != null) {
				String strColor = ""; 
				String strColorDiagnostico = ""; 
				for (int i = 0; i < lstOrdenes.size() ; i++)
				{
					objOrden = (OrdenBean)lstOrdenes.get(i);						
					if (objOrden.getCestado() == 17) {
						strColor = "red";
						strColorDiagnostico = "red";
					} else {
						strColor = "black";						
						strColorDiagnostico = "green";													

					}
					strReturn += ("<tr>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:mostrarFactura(" + objOrden.getKadmision() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'>" + 
//											this.llenaIdFactura(objOrden.getSordenfundacion().trim(),String.valueOf(objOrden.getCordenfundacion()),8) + 
										"</a></td>" + 
										"<td align='center' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'>"+
											new Formatos().getFechaNumeros(objOrden.getDregistro()) +
										"</td>" +
										"<td align='center' onClick='javascript:visualizarResultado(" + objOrden.getKadmision() + ");' style='font-weight: normal; font-size: x-small; color: " + strColor + "; font-style: normal; font-variant: normal;'> " + "" +
											"<a href='javascript:visualizarResultado(" + objOrden.getKadmision() + ");' onClick='javascript:visualizarResultado(" + objOrden.getKadmision() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: " + strColorDiagnostico + "; font-style: normal; font-variant: normal;'>Diagnostico"  + 
											"</a>" + 
										"</td>" + 
									 "</tr>");
				}
			}
			strReturn += ("</table>");
			return strReturn; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosPagoAjax.consultaPacienteGrid:Exception....", aObjException);
    	    throw aObjException;
		} 
	}	
	
	public PagoPacienteBean setPago(PagoPacienteBean objPago) throws Exception {
		PagosDao objDaoPagos = new PagosDao();
		iObjLog.debug("Entrando a DatosPagoAjax.setPago:Entrando... ");
		try {
		    objPago = objDaoPagos.setPago(objPago);
			return objPago; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosPagoAjax.setPago:Exception....", aObjException);
    	    throw aObjException;
		} 
	}		

	public String showOrdenesSinPago(int cSucursal) throws Exception {
		PagosDao objDaoPagos = new PagosDao();
		String strReturn = "";
		iObjLog.debug("Entrando a DatosPagoAjax.showOrdenesSinPago:Entrando... " + cSucursal);
		try {
			strReturn = objDaoPagos.getOrdenesSinPago(cSucursal);
			iObjLog.debug("Saliendo a DatosPagoAjax.showOrdenesSinPago:Numero de Ordenes... ");
			return strReturn; 
		}catch (Exception aObjException){
    	    iObjLog.error("DatosPagoAjax.showOrdenesSinPago:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objDaoPagos = null;
		}
	}		

	public int setInicioTicket(int cSucursal,int kOrdenSucursal,int user_id, String smodulo, String snemonicoconsecutivo) throws Exception {
		PagosDao objDaoPagos = new PagosDao();
		int intReturn = 0;
		iObjLog.debug("Entrando a DatosPagoAjax.setInicioTicket:Entrando... " + cSucursal);
		try {
			intReturn = objDaoPagos.setInicioTicket(cSucursal, kOrdenSucursal, user_id, smodulo, snemonicoconsecutivo);
			iObjLog.debug("Saliendo a DatosPagoAjax.setInicioTicket:Numero de Ordenes... ");
		}catch (Exception aObjException){
    	    iObjLog.error("DatosPagoAjax.setInicioTicket:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objDaoPagos = null;
		}
		return intReturn;
	}		

	public int setTerminoAtencionTicket(int kTicketCaja) throws Exception {
		PagosDao objDaoPagos = new PagosDao();
		int intReturn = 0;
		iObjLog.debug("Entrando a DatosPagoAjax.setTerminoAtencionTicket:Entrando... " + kTicketCaja);
		try {
			objDaoPagos.setTerminoAtencionTicket(kTicketCaja);
			iObjLog.debug("Saliendo a DatosPagoAjax.setTerminoAtencionTicket...");
		}catch (Exception aObjException){
    	    iObjLog.error("DatosPagoAjax.setTerminoAtencionTicket:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objDaoPagos = null;
		}
		return intReturn;
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
    
}
