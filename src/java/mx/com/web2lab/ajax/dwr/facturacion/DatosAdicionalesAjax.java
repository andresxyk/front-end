package mx.com.web2lab.ajax.dwr.facturacion;

import mx.com.web2lab.ajax.dwr.capturaorden.DatosOrdenAjax;
import mx.com.web2lab.ajax.dwr.capturaorden.DatosPacienteAjax;
import mx.com.web2lab.ajax.dwr.http.AjaxAction;

import mx.com.web2lab.backend.beans.ap.OrdenBean;
import mx.com.web2lab.backend.beans.ap.PacienteBean;
import mx.com.web2lab.backend.beans.ap.PagoPacienteBean;
import mx.com.web2lab.backend.beans.facturacion.DatosAdicionalesBean;
import mx.com.web2lab.backend.beans.facturacion.TdatoAdicionalBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
import mx.com.web2lab.backend.dao.ap.DatosOrdenDao;
import mx.com.web2lab.backend.dao.ap.PagosDao;
import mx.com.web2lab.backend.dao.facturacion.electronica.orden.OrdenDatosFacturacionDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.DatosAdicionalesDao;
import mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionPrevioDao;
import mx.com.web2lab.backend.hbm.HibernateUtil;
import mx.com.web2lab.backend.hbm.om.ap.TDatoAdicional;

import mx.com.web2lab.backend.util.exceptions.AjaxDwrException;
import mx.com.web2lab.backend.util.formatos.Formatos;


import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DatosAdicionalesAjax extends AjaxAction {
	
	
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(DatosAdicionalesAjax.class);
 	private Date objDate = new Date();
	
	public String ordenGrid(OrdenBean objOrdenBean){
		String strReturn=null;
		String strColor = ""; 
		
		if (objOrdenBean.getCestado() == 17) {
			strColor = "red";
			
		} else {
			strColor = "black";						
		}
		
		iObjLog.debug("Entrando a DatosAdicionalesAjax.ordenGrid:Entrando...Parametros... " + objOrdenBean.getKadmision());
		
		strReturn = ("<table border='0' align='center' style='width: 883px' class='tabla'>" + 
							"<tr>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Id Paciente" + 
								"	</font></b>" +
								"</th>" +
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Nombre Paciente" + 
								"	</font></b>" +
								"</th>" +
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Consecutivo de Orden" + 
								"	</font></b>" +
								"</th>" +
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Orden" + 
								"	</font></b>" +
								"</th>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Fecha Orden" + 
								"</th>" + 
							"</tr>" +
							"<tr>"+
								"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:mostrarFactura(" + objOrdenBean.getKadmision() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
									objOrdenBean.getBpacientebean().getKpacientefundacion()+ 
								"</a></td>" +
								"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:mostrarFactura(" + objOrdenBean.getKadmision() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
									objOrdenBean.getBpacientebean().getSnombre()+" "+objOrdenBean.getBpacientebean().getSappaterno()+" "+objOrdenBean.getBpacientebean().getSapmaterno()+ 
								"</a></td>" +
								"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:mostrarFactura(" + objOrdenBean.getKadmision() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
									objOrdenBean.getKadmision() + 
								"</a></td>" + 
								"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> <a href='javascript:doNothing()' onClick='javascript:mostrarFactura(" + objOrdenBean.getKadmision() + ");' align='bottom' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
									this.llenaIdFactura(objOrdenBean.getSordenfundacion().trim(),String.valueOf(objOrdenBean.getCordenfundacion()),8) + 
								"</a></td>" + 
								"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>"+
									new Formatos().getFechaNumeros(objOrdenBean.getDregistro()) +
								"</td>" +
							"</tr>"+
					  "</table>"	);			 

		iObjLog.debug("Saliendo DatosAdicionalesAjax.ordenGrid:Saliendo...Parametros... " + strReturn);
		return strReturn;
	}
	
	
	public String pacienteGrid(PacienteBean objPacienteBean) throws Exception{
		String strReturn=null;
		DatosOrdenAjax objDatosOrdenAjax = new DatosOrdenAjax();
		String strReturnArray[] = new String[2]; 
		strReturnArray = objDatosOrdenAjax.consultaOrdenesGrid(objPacienteBean.getKpacientefundacion().intValue(), 0);
		
		iObjLog.debug("Entrando a DatosAdicionalesAjax.pacienteGrid:Entrando...Parametros... " + objPacienteBean.getKpacientefundacion());
		
		strReturn = ("<table border='0' align='center' style='width: 883px' class='tabla'>" + 
							"<tr>" + 
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Id Paciente" + 
								"	</font></b>" +
								"</th>" +
								"<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" +
								"	<b><font color='black'>Nombre Paciente" + 
								"	</font></b>" +
								"</th>" + 
							"</tr>" +
							"<tr>"+
								"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'> " + 
									objPacienteBean.getKpacientefundacion()+ 
								"</a></td>" +
								"<td align='center' style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>" + 
									objPacienteBean.getSnombre()+" "+objPacienteBean.getSappaterno()+" "+objPacienteBean.getSapmaterno()+ 
								"</a></td>" +
							"</tr>"+
					  "</table>"	);			 
		strReturn=strReturn+strReturnArray[0];
		
		iObjLog.debug("Saliendo DatosAdicionalesAjax.ordenGrid:Saliendo...Parametros... " + strReturn);
		return strReturn;
	}
	
	static String llenaIdFactura(String strNemonico,String intFactura,int MaxLength) {
		String strReturn = "";
		int intTotal = (strNemonico.length() + intFactura.length());
		for(int i = intTotal;i <= MaxLength;i++) {
			strReturn += "0";
		}		
		return strNemonico + strReturn;
	}
	
	public String getDatoAdicional(int intKOrdenSucursal){
		String strreturn=null;
		OrdenBean objOrdenBean = new OrdenBean();
		DatosOrdenDao objDatosOrdenDAO = new DatosOrdenDao();
		DatosAdicionalesDao objDatosAdicionalesDAO = new DatosAdicionalesDao();
		
		iObjLog.debug("Entrando DatosAdicionalesAjax.getDatoAdicional:...Parametros... " + intKOrdenSucursal);
		try {
				objOrdenBean=objDatosOrdenDAO.buscarOrdenFacOnly(intKOrdenSucursal);
				strreturn= objDatosAdicionalesDAO.getDatosAdicionales(objOrdenBean);
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strreturn;
	}
	
	public String getDatosAdicionalesIndividuales(int cconvenio) throws AjaxDwrException{
		String strReturn = null;
		iObjLog.debug("FacturacionDatosAjax.getDatosAdicionalesIndividuales:Entrando..." + cconvenio);
		try
		{
			if(!isSesionValida())throw new AjaxDwrException(1, "La sesion ha caducado o no hay una sesi&oacute;n v&aacute;lida ...");
			DatosAdicionalesDao objFacuracionPrevioDAO = new DatosAdicionalesDao();
			List lstDatosAdicionales = objFacuracionPrevioDAO.getDatoAdicional(cconvenio);
			boolean bmostrarsniveldatoadicional=false;
			String strscript = "";
			String strniveldatoadicional;
			String strnameniveldatoadicional;
			
			if (lstDatosAdicionales != null) {
				iObjLog.debug("Existen Datos Adicionales");
				strReturn ="<table  align='center' style='width: 883px' class='tabla'>"+
							"<tr >"+
							"  <th colspan='2'>"+
							"     <b style='font-weight: bold; font-size: medium; color: black; font-style: normal; font-variant: normal'>Ingrese los datos adicionales</b>"+
							"  </th>"+
							"</tr>"+
							"<tr>";
				for(int i = 0; i < lstDatosAdicionales.size() ; i++) {
					DatosAdicionalesBean objDatosAdicionalesBean  = (DatosAdicionalesBean)lstDatosAdicionales.get(i);
					if(!bmostrarsniveldatoadicional){
						bmostrarsniveldatoadicional=true;
						if(objDatosAdicionalesBean.getSniveldatoadicional().equals("ORDEN")){
							strniveldatoadicional="CONSECUTIVO DE LA ORDEN";
							strnameniveldatoadicional="00";
						}else{
							strniveldatoadicional="ID PACIENTE";
							strnameniveldatoadicional="01";
						}
						strReturn+= "<tr>"+
									"</tr>"+
									"<tr>"+
									"<td>" + 
									"	<b><font color='black'>" + strniveldatoadicional+
									"	</font></b>" +
									"</td>"+
									"<td>"+
									" <input type=\"text\" name=\""+strnameniveldatoadicional+"\" onKeyPress=\"numero();\" onChange=\"buscaDato();\" size=\"12\" >"+
									"	</font></b>" +
									"</td>"+
									"</tr>";	
					}if(objDatosAdicionalesBean.getStipodatoadicional().equals("VARCHAR")){
						strscript="onKeyPress=\"mayuscula();\"";	
					}else if(objDatosAdicionalesBean.getStipodatoadicional().equals("NUMERICO")) {
						strscript="onKeyPress=\"numero();\"";
					}else if(objDatosAdicionalesBean.getStipodatoadicional().equals("DATE")) {
						strscript="onKeyPress=\"javascript:agregaDiag(this);\" onChange=\"javascript:this.value=validaFormatoFecha(this.value);\"";
					}
					strReturn+= "<tr>"+
								"</tr>"+
								"<tr>"+
								"<td>" + 
								"	<b><font color='black'>" + objDatosAdicionalesBean.getSdatoadicional()+
								"	</font></b>" +
								"</td>"+
								"<td>"+
								" <input type=\"text\" name=\""+objDatosAdicionalesBean.getCdatoadicional()+"\" "+strscript+" size=\"12\" >"+
								"	</font></b>" +
								"</td>"+
								"</tr>"; 
				}
				strReturn+= "<tr>"+
							"</tr>"+
						    "	<tr colspan='2'>"+
							"		<td>" + 
						  	"			<input type='button' id='idGuardarDatos' name='idGudardarDatos' value='Guardar' onClick='javascript:guardaDatos();' class='boton'>"+
						  	"		</td>" +
						  	"		<td>" + 
						  	"			<input type='button' id='idLimpiarDatos' name='idLimpiarDatos' value='Limpiar' onClick='javascript:limpiarDatos();' class='boton'>"+
						  	"		</td>" +
						  	"	</tr>"+
						  	"</table>";
				iObjLog.debug("TAG"+strReturn);
			}
		} catch (Exception e){
			iObjLog.error("FacturacionAjax.getDatosAdicionalesIndividuales:ERROR",e);
			if(e instanceof AjaxDwrException){
				throw new AjaxDwrException(e);
			}else{
				throw new AjaxDwrException(0,"FacturacionAjax.getDatosAdicionalesIndividuales:Ocurri&oacute; un error al buscar Datos Adicionales ..." + cconvenio);
			}
		}		
		return strReturn;
	}
	
	public String persistirDatoAdicional(String strdatos,boolean bactualizacion){
		String strMensaje="";
		DatosAdicionalesDao objDatosAdicionalesDAO = new DatosAdicionalesDao();
		try {
			iObjLog.debug("Entrando a DatosAdicionalesAjax:persistirDatoAdicional...... strdatos"+strdatos+" bactualizacion:"+bactualizacion);
				strMensaje=objDatosAdicionalesDAO.persistirDatoAdicional(strdatos,bactualizacion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strMensaje;
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
