package mx.com.web2lab.ajax.dwr.tiemposmovimientos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.com.web2lab.ajax.dwr.http.AjaxAction;
import mx.com.web2lab.backend.dao.tiemposmovimientos.TicketDao;
import mx.com.web2lab.backend.hbm.om.tiemposmovimientos.TTicketSucursal;
import mx.com.web2lab.backend.util.formatos.Formatos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TicketsAjax extends AjaxAction {
	/** Log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(TicketsAjax.class);
	private Formatos objFormatos = new Formatos();
			
	public TTicketSucursal nuevoTicket(int cSucursal, String strNemonicoTurno) throws Exception {	
		TicketDao objTicketDao = new TicketDao();
		TTicketSucursal objTTicketSucursal = new TTicketSucursal();
		iObjLog.debug("Entrando TicketsAjax.nuevoTicket:Entrando...cSucursal.." + cSucursal);		
		try {
			objTTicketSucursal.setCestadoregistro(60);
			objTTicketSucursal.setCsucursal(cSucursal);
			objTTicketSucursal.setDinicio(new Date());
			objTTicketSucursal.setDtermino(new Date());
			objTTicketSucursal.setDterminorecepcion(new Date());
			objTTicketSucursal.setKordensucursal(0);
			objTTicketSucursal.setKordensucursalcotizacion(0);
			objTTicketSucursal.setSmodulo(" ");
			objTTicketSucursal.setSnemonicoconsecutivo(strNemonicoTurno);
			objTTicketSucursal.setUserId(1);
			objTTicketSucursal = objTicketDao.setTicket(objTTicketSucursal);
			iObjLog.debug("Saliendo TicketsAjax.nuevoTicket:Saliendo...  " + objTTicketSucursal.getUconsecutivoticketsucursal());
		} catch (Exception aObjException) {
    	    iObjLog.error("Error TicketsAjax.nuevoTicket:Exception....", aObjException);
    	    objTTicketSucursal = null;
    	    throw aObjException;
		} finally {
			objTicketDao = null;
		}
		return objTTicketSucursal;
	}
	
	public String nuevoTicketZPL(int cSucursal, String strNemonicoTurno) throws Exception {	
		String strZPL=null;
		TicketDao objTicketDao = new TicketDao();
		TTicketSucursal objTTicketSucursal = new TTicketSucursal();
		iObjLog.debug("Entrando TicketsAjax.nuevoTicketZPL:Entrando...cSucursal.." + cSucursal);		
		try {
			objTTicketSucursal.setCestadoregistro(60);
			objTTicketSucursal.setCsucursal(cSucursal);
			objTTicketSucursal.setDinicio(new Date());
			objTTicketSucursal.setDtermino(new Date());
			objTTicketSucursal.setDterminorecepcion(new Date());
			objTTicketSucursal.setKordensucursal(0);
			objTTicketSucursal.setKordensucursalcotizacion(0);
			objTTicketSucursal.setSmodulo(" ");
			objTTicketSucursal.setUserId(1);
			objTTicketSucursal.setSnemonicoconsecutivo(strNemonicoTurno);
			objTTicketSucursal = objTicketDao.setTicket(objTTicketSucursal);
			strZPL=this.imprimeEtiquetasZPL(objTTicketSucursal);			
			iObjLog.debug("Saliendo TicketsAjax.nuevoTicketZPL:Saliendo...  " + objTTicketSucursal.getUconsecutivoticketsucursal());
		} catch (Exception aObjException) {
    	    iObjLog.error("Error TicketsAjax.nuevoTicketZPL:Exception....", aObjException);
    	    objTTicketSucursal = null;
    	    throw aObjException;
		} finally {
			objTicketDao = null;
		}
		return strZPL;
	}
	
	private String imprimeEtiquetasZPL(TTicketSucursal objTTicketSucursal) throws Exception {
		String strEtiquetas = "";
		
    	try{
			if(objTTicketSucursal != null) {
				
					strEtiquetas = "";
					strEtiquetas += "^XA " +
									"^LH5,10 " +
									"^FO70,10^ADN,30,20^FDT U R N O^FS"+
									"^FO110,50^ADN,110,20^FD"+objTTicketSucursal.getSnemonicoconsecutivo()+"-"+this.rellenar(objTTicketSucursal.getUconsecutivoticketsucursal())+"^FS "+
									"^FO80,170^ADN,10,10^FD"+objFormatos.getFecha(objTTicketSucursal.getDinicio(), 1)+ "^FS " +
									"^XZ";			
			}		
		} catch (Exception aObjExcepcion) { 
    	    iObjLog.error("ExamenesDao.imprimeEtiquetasZPL:Exception....", aObjExcepcion);
    	    throw aObjExcepcion;
		} 
		iObjLog.debug("ExamenesDao.imprimeEtiquetasZPL:Exception....   " + strEtiquetas);
		return strEtiquetas;
	}				
	
	public String rellenar(int Uconsecutivoticketsucursal){
		String strconsecutivo=new Integer(Uconsecutivoticketsucursal).toString();
		String strReturn="";
		
		int total=strconsecutivo.length();
		
		for(int i=total;i<3;i++){
			strReturn+='0';
		}
		return strReturn+strconsecutivo;
	}
	
//	public List buscarTicketNuevos(int cSucursal) throws Exception {	
//		TicketDao objTicketDao = new TicketDao();
//		List lstTickets = new ArrayList();
//		iObjLog.debug("Entrando TicketsAjax.buscarTicketNuevos:Entrando...cSucursal.." + cSucursal);		
//		try {
//			lstTickets = objTicketDao.getTicketsSucursalEstado(cSucursal,60,"");
//			iObjLog.debug("Saliendo TicketsAjax.buscarTicketNuevos:Saliendo...  " + lstTickets.size());
//		} catch (Exception aObjException) {
//    	    iObjLog.error("Error TicketsAjax.buscarTicketNuevos:Exception....", aObjException);
//    	    throw aObjException;
//		} finally {
//			objTicketDao = null;
//		}
//		return lstTickets;
//	}
	
	public String buscarTicketNuevosHTML(int cSucursal, String strCubiculo,int cUsuario) throws Exception {	
		TicketDao objTicketDao = new TicketDao();
		List lstTickets = new ArrayList();
		TTicketSucursal objTTicketSucursal = null;
		String strSize = "25";
		String strHTMLReturn =  "<table border='0'  align='center' style='width: 100%' class='tabla'> 	" +
							    "	<th colspan='4'> 																														" +
			        	        "		<center>																															" +
			        	        "			<b style='font-weight: bold; font-size: medium; color: black; font-style: normal; font-variant: normal'>Atencion Tickets</b>	" +
			        	        "		</center>																															" +
							    "	</th>																																	" +
								"	<tr>																																	" + 
								"		<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>				" +
								"			<b><font color='black'>Llamar Paciente																							" + 
								"			</font></b>																														" +
								"		</th>																																" +
								"		<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>				" +
								"			<b><font color='black'>Turno																									" + 
								"			</font></b>																														" +
								"		</th>																																" + 
								"		<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>				" +
								"			<b><font color='black'>Fecha y Hora Llegada																						" + 
								"		</th>																																" + 
								"		<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>				" +
								"			<b><font color='black'>Tiempo Espera																							" + 
								"		</th>																																" +
								"</tr>";		
		iObjLog.debug("Entrando TicketsAjax.buscarTicketNuevosHTML:Entrando...cSucursal.." + cSucursal);		
		try {
			String strNemonico = "";
			
			
			
			
			String[] dias={"Domingo","Lunes","Martes", "Mi&eacute;rcoles","Jueves","Viernes","S&aacute;bado"};
			Date hoy=new Date();
			int numeroDia=0;
			Calendar cal= Calendar.getInstance();
			cal.setTime(hoy);
			numeroDia=cal.get(Calendar.DAY_OF_WEEK);
			if ((dias[numeroDia - 1] == "Domingo") || (dias[numeroDia - 1].equals("Domingo"))) {
				if (strCubiculo.trim().toUpperCase().toString() == "A" || strCubiculo.trim().toUpperCase().toString().equals("A")) {
					strNemonico = "'EN'";
				} else if (strCubiculo.trim().toUpperCase().toString() == "B" || strCubiculo.trim().toUpperCase().toString().equals("B")) {
					strNemonico = "'RE','CO','CI','CB','CA','EN'";
				} else {
					strNemonico = "'RE','CO','CI','CB','EN'";
				}
			} else {			
				if (strCubiculo.trim().toUpperCase().toString() == "A" || strCubiculo.trim().toUpperCase().toString().equals("A")) {
					strNemonico = "'EN'";
				} else if (strCubiculo.trim().toUpperCase().toString() == "B" || strCubiculo.trim().toUpperCase().toString().equals("B")) {
					strNemonico = "'RE','CO','CI','CB','CA'";
				} else if (strCubiculo.trim().toUpperCase().toString() == "Z" || strCubiculo.trim().toUpperCase().toString().equals("Z")) {
					strNemonico = "'RE','CO','CI','CB','CA','EN'";
				} else {
					strNemonico = "'RE','CO','CI','CB'";
				}
			}
			cal = null;
			dias = null;
			hoy = null;
			boolean bolTicketsCerrados = true;
			lstTickets = objTicketDao.getTicketsSucursalEstado(cSucursal,60,strNemonico,cUsuario);
			if (lstTickets != null) {
				if (lstTickets.size() == 0) {
					bolTicketsCerrados = true;
					lstTickets = objTicketDao.getTicketsSucursalEstado(cSucursal,60,strNemonico,0);
				} else {
					bolTicketsCerrados = false;
				}
			} else {
				bolTicketsCerrados = true;
				lstTickets = objTicketDao.getTicketsSucursalEstado(cSucursal,60,strNemonico,0);
			}
			double dblMinutos = 0.0;
			String strEspera = "";
			if (lstTickets != null) {
				if (lstTickets.size() > 0) {
					for (int inti=0;inti<lstTickets.size();inti++) {
						objTTicketSucursal = (TTicketSucursal)lstTickets.get(inti);						
						if (bolTicketsCerrados) {
							dblMinutos = objTicketDao.getEspera(objTTicketSucursal.getKticketsucursal().intValue());													
							if (dblMinutos < 10.0) {								
								strEspera = "<img alt='HumorPaciente' id=\"imgPDF\" width=\"" + strSize + "\" height=\"" + strSize + "\" border='0' src='/web2labportal/images/happy.png' />";
							} else if (dblMinutos < 15.0) {
								strEspera = "<img alt='HumorPaciente' id=\"imgPDF\" width=\"" + strSize + "\" height=\"" + strSize + "\" border='0' src='/web2labportal/images/serious.png' />";
							} else {
								strEspera = "<img alt='HumorPaciente' id=\"imgPDF\" width=\"" + strSize + "\" height=\"" + strSize + "\" border='0' src='/web2labportal/images/sad.png' />";
							}													
							strHTMLReturn = strHTMLReturn + "	<tr> 															" +
							        						"   	<td align='center'> 														" +
															"			<a href=\"javascript:llamarPaciente(" + objTTicketSucursal.getKticketsucursal().intValue() + ");\"  align='bottom' style='font-weight: normal; font-size: xx-small; color: black; font-style: normal; font-variant: normal;'>"  +  
															"				" + strEspera  + " 									" +
															"			</a>													" + 													
															"   	</td> 														" +     	                
													        "   	<td align='center'> 										" +
										        			"			<font color='black'>"  + objTTicketSucursal.getSnemonicoconsecutivo() + "-" + objTTicketSucursal.getUconsecutivoticketsucursal() + "</font></a>"  +
// 23 de Mayo del 2013 OMRR													        
//													        			"<a href=\"javascript:llamarPaciente(" + objTTicketSucursal.getKticketsucursal().intValue() + ");\"><font color='black'>"  + objTTicketSucursal.getSnemonicoconsecutivo() + "-" + objTTicketSucursal.getUconsecutivoticketsucursal() + "</font></a>"  +
													        "   	</td> 														" +     	                
													        "    	<td align='center'> 										" +
													        			(new Formatos()).getFecha(objTTicketSucursal.getDinicio(),1) 						  +
													        "    	</td> 														" +     	                
													        "    	<td align='center'> 										" +
													        			 String.valueOf(redodedo((int)dblMinutos)) + " minutos 	" +
													        "    	</td> 														" +     	                
													        "    </tr>";
						} else {
							dblMinutos = objTicketDao.getEspera(objTTicketSucursal.getKticketsucursal().intValue());													
							strHTMLReturn = strHTMLReturn + "	<tr> 															" +
							        						"   	<td align='center'> 														" +
															"			<a href=\"javascript:cerrarTicketAnteriores(" + objTTicketSucursal.getKticketsucursal().intValue() + ");\"  align='bottom' style='font-weight: normal; font-size: xx-small; color: black; font-style: normal; font-variant: normal;'>"  +  
															"				Ticket NO CERRADO,¿Lo quieres Cerrar?  				" +
															"			</a>													" + 													
															"   	</td> 														" +     	                
													        "   	<td align='center'> 										" +
													        			"<a href=\"javascript:cerrarTicketAnteriores(" + objTTicketSucursal.getKticketsucursal().intValue() + ");\"><font color='black'>"  + objTTicketSucursal.getSnemonicoconsecutivo() + "-" + objTTicketSucursal.getUconsecutivoticketsucursal() + "</font></a>"  +
													        "   	</td> 														" +     	                
													        "    	<td align='center'> 										" +
													        			(new Formatos()).getFecha(objTTicketSucursal.getDinicio(),1) 						  +
													        "    	</td> 														" +     	                
													        "    	<td align='center'> 										" +
													        			 String.valueOf(redodedo((int)dblMinutos)) + " minutos 	" +
													        "    	</td> 														" +     	                
													        "    </tr>";
						}
					}
				}
			}
			strHTMLReturn = strHTMLReturn + "</table>";
			iObjLog.debug("Saliendo TicketsAjax.buscarTicketNuevosHTML:Saliendo...  " + lstTickets.size());
		} catch (Exception aObjException) {
    	    iObjLog.error("Error TicketsAjax.buscarTicketNuevosHTML:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objTicketDao = null;
			if (lstTickets != null) {
				lstTickets.clear();
			}
			lstTickets = null;
		}
		return strHTMLReturn;
	}

	public String llamarTicket(int kTicketSucursal,String strModulo,int Id_Turbine) throws Exception {	
		TicketDao objTicketDao = new TicketDao();
		TTicketSucursal objTTicketSucursal = new TTicketSucursal();
		String strHTMLReturn = "";
		iObjLog.debug("Entrando TicketsAjax.llamarTicket:Entrando...kTicketSucursal.." + kTicketSucursal + " strModulo " + strModulo + " Id_Turbine " + Id_Turbine);		
		try {
			objTTicketSucursal = objTicketDao.getTicket(kTicketSucursal);
			if (objTTicketSucursal.getKticketsucursal().intValue() > 0) {						
				objTTicketSucursal.setCestadoregistro(61);
				objTTicketSucursal.setSmodulo(strModulo);
				objTTicketSucursal.setUserId(Id_Turbine);
				objTTicketSucursal.setDtermino(new Date());
				objTTicketSucursal = objTicketDao.updateTicket(objTTicketSucursal);
			}
			strHTMLReturn = "<table border='0'  align='center' style='width: 100%' class='tabla'> 	" +
						    "	<th colspan='3'> 																														" +
		        	        "		<center>																															" +
		        	        "			<b style='font-weight: bold; font-size: medium; color: black; font-style: normal; font-variant: normal'>Atendiendo Tickets</b>	" +
		        	        "		</center>																															" +
						    "	</th>																																	" +
							"	<tr>																																	" + 
							"		<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>				" +
							"			<b><font color='black'>Atendiendo Paciente																						" + 
							"			</font></b>																														" +
							"		</th>																																" +
							"		<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>				" +
							"			<b><font color='black'>Turno																									" + 
							"			</font></b>																														" +
							"		</th>																																" + 
							"		<th nowrap style='font-weight: normal; font-size: x-small; color: black; font-style: normal; font-variant: normal;'>				" +
							"			<b><font color='black'>Fecha y Hora Llegada																						" + 
							"		</th>																																" + 
							"	</tr>																																	" +																							
							"	<tr> 																	" +
							"   	<td align='center'> 																" +
							"			<a href=\"javascript:cerrarTicketSucursal(" + objTTicketSucursal.getKticketsucursal().intValue() + ");\"  align='bottom' style='font-weight: normal; font-size: xx-small; color: black; font-style: normal; font-variant: normal;'>"  +  
							"				<img alt='Atendiendo Paciente' id=\"imgPDF\" width=\"35\" height=\"35\" border='0' src='/web2labportal/images/icoAtendido.png' />" +
							"			</a>															" + 	
							"       	<input type='hidden' id='hdlTicketSucursal'  value='" + objTTicketSucursal.getKticketsucursal().intValue() + "'>	" +
							"   	</td> 																" +     							
					        "   	<td align='center'> 																" +
					        			objTTicketSucursal.getUconsecutivoticketsucursal()  	  		  +
					        "   	</td> 																" +     	                
					        "    	<td align='center'> 																" +
					        			objTTicketSucursal.getDinicio() 						          +
					        "    	</td> 																" +     	                
					        "    </tr>"+
							"</table>";			
			iObjLog.debug("Saliendo TicketsAjax.llamarTicket:Saliendo...  " + objTTicketSucursal.getUconsecutivoticketsucursal());
		} catch (Exception aObjException) {
    	    iObjLog.error("Error TicketsAjax.llamarTicket:Exception....", aObjException);
    	    objTTicketSucursal = null;
    	    throw aObjException;
		} finally {
			objTicketDao = null;
		}
		return strHTMLReturn;
	}

	
	public List buscarTicketLlamados(int cSucursal) throws Exception {	
		TicketDao objTicketDao = new TicketDao();
		List lstTickets = new ArrayList();
		iObjLog.debug("Entrando TicketsAjax.buscarTicketLlamados:Entrando...cSucursal.." + cSucursal);		
		try {
			lstTickets = objTicketDao.getTicketsSucursalEstado(cSucursal,61,"",0);
			iObjLog.debug("Saliendo TicketsAjax.buscarTicketLlamados:Saliendo...  " + lstTickets.size());
		} catch (Exception aObjException) {
    	    iObjLog.error("Error TicketsAjax.buscarTicketLlamados:Exception....", aObjException);
    	    throw aObjException;
		} finally {
			objTicketDao = null;
		}
		return lstTickets;
	}
	
	public int atendiendoTicket(int kTicketSucursal) throws Exception {	
		TicketDao objTicketDao = new TicketDao();
		TTicketSucursal objTTicketSucursal = new TTicketSucursal();
		iObjLog.debug("Entrando TicketsAjax.atendiendoTicket:Entrando...kTicketSucursal.." + kTicketSucursal);		
		try {
			objTTicketSucursal = objTicketDao.getTicket(kTicketSucursal);
			if (objTTicketSucursal.getKticketsucursal().intValue() > 0) {						
				objTTicketSucursal.setCestadoregistro(62);
				objTTicketSucursal = objTicketDao.updateTicket(objTTicketSucursal);
			}
			iObjLog.debug("Saliendo TicketsAjax.atendiendoTicket:Saliendo...  " + objTTicketSucursal.getUconsecutivoticketsucursal());
		} catch (Exception aObjException) {
    	    iObjLog.error("Error TicketsAjax.atendiendoTicket:Exception....", aObjException);
    	    objTTicketSucursal = null;
    	    throw aObjException;
		} finally {
			objTicketDao = null;
		}
		return kTicketSucursal;
	}

//	public TTicketSucursal atendidoTicket(int kTicketSucursal,int kOrdenSucursal, int kOrdenSucursalCotizacion) throws Exception {	
	public int atendidoTicket(int kTicketSucursal,int kOrdenSucursal, int kOrdenSucursalCotizacion) throws Exception {	
		TicketDao objTicketDao = new TicketDao();
		TTicketSucursal objTTicketSucursal = new TTicketSucursal();
		iObjLog.debug("Entrando TicketsAjax.atendidoTicket:Entrando...kTicketSucursal.." + kTicketSucursal);		
		try {
			objTTicketSucursal = objTicketDao.getTicket(kTicketSucursal);
			if (objTTicketSucursal.getKticketsucursal().intValue() > 0) {						
				objTTicketSucursal.setCestadoregistro(63);
				objTTicketSucursal.setDterminorecepcion(new Date());
				if (objTTicketSucursal.getKordensucursal() > 0 || objTTicketSucursal.getKordensucursalcotizacion() > 0) {
					objTTicketSucursal.setKordensucursal(kOrdenSucursal);
					objTTicketSucursal.setKordensucursalcotizacion(kOrdenSucursalCotizacion);
					objTTicketSucursal.setKticketsucursalinicial(kTicketSucursal);
					objTTicketSucursal = objTicketDao.duplicarTicket(objTTicketSucursal);
				} else {
					objTTicketSucursal.setKordensucursal(kOrdenSucursal);
					objTTicketSucursal.setKticketsucursalinicial(kTicketSucursal);					
					objTTicketSucursal.setKordensucursalcotizacion(kOrdenSucursalCotizacion);
					objTTicketSucursal = objTicketDao.updateTicket(objTTicketSucursal);
				}
			}
			iObjLog.debug("Saliendo TicketsAjax.atendidoTicket:Saliendo...  " + objTTicketSucursal.getUconsecutivoticketsucursal());
		} catch (Exception aObjException) {
    	    iObjLog.error("Error TicketsAjax.atendidoTicket:Exception....", aObjException);
    	    objTTicketSucursal = null;
    	    throw aObjException;
		} finally {
			objTicketDao = null;
		}
		return kTicketSucursal;
	}

	public TTicketSucursal cancelarTicket(int kTicketSucursal) throws Exception {	
		TicketDao objTicketDao = new TicketDao();
		TTicketSucursal objTTicketSucursal = new TTicketSucursal();
		iObjLog.debug("Entrando TicketsAjax.cancelarTicket:Entrando...kTicketSucursal.." + kTicketSucursal);		
		try {
			objTTicketSucursal = objTicketDao.getTicket(kTicketSucursal);
			if (objTTicketSucursal.getKticketsucursal().intValue() > 0) {						
				objTTicketSucursal.setCestadoregistro(64);
				objTTicketSucursal = objTicketDao.updateTicket(objTTicketSucursal);
			}
			iObjLog.debug("Saliendo TicketsAjax.cancelarTicket:Saliendo...  " + objTTicketSucursal.getUconsecutivoticketsucursal());
		} catch (Exception aObjException) {
    	    iObjLog.error("Error TicketsAjax.cancelarTicket:Exception....", aObjException);
    	    objTTicketSucursal = null;
    	    throw aObjException;
		} finally {
			objTicketDao = null;
		}
		return objTTicketSucursal;
	}
	
	public String cerrarTicket(int kTicketSucursal) throws Exception {	
		TicketDao objTicketDao = new TicketDao();
		TTicketSucursal objTTicketSucursal = new TTicketSucursal();
		iObjLog.debug("Entrando TicketsAjax.cerrarTicket:Entrando...kOrdenSucursal.." + kTicketSucursal);		
		try {
			objTTicketSucursal.setKticketsucursal(new Integer(kTicketSucursal));
			objTicketDao.cerrarTicket(objTTicketSucursal);
			iObjLog.debug("Saliendo TicketsAjax.cerrarTicket:Saliendo...  ");
		} catch (Exception aObjException) {
    	    iObjLog.error("Error TicketsAjax.cerrarTicket:Exception....", aObjException);
    	    objTTicketSucursal = null;
    	    throw aObjException;
		} finally {
			objTicketDao = null;
			objTTicketSucursal = null;
		}
		return "";
	}

	public String cerrarTicketAnteriores(int kTicketSucursal) throws Exception {	
		TicketDao objTicketDao = new TicketDao();
		TTicketSucursal objTTicketSucursal = new TTicketSucursal();
		iObjLog.debug("Entrando TicketsAjax.cerrarTicket:Entrando...kOrdenSucursal.." + kTicketSucursal);		
		try {
			objTTicketSucursal.setKticketsucursal(new Integer(kTicketSucursal));
			objTicketDao.cerrarTicketAnteriores(objTTicketSucursal);
			iObjLog.debug("Saliendo TicketsAjax.cerrarTicket:Saliendo...  ");
		} catch (Exception aObjException) {
    	    iObjLog.error("Error TicketsAjax.cerrarTicket:Exception....", aObjException);
    	    objTTicketSucursal = null;
    	    throw aObjException;
		} finally {
			objTicketDao = null;
			objTTicketSucursal = null;
		}
		return "";
	}
	
	public TTicketSucursal buscarTicket(int kTicketSucursal) throws Exception {	
		TicketDao objTicketDao = new TicketDao();
		TTicketSucursal objTTicketSucursal = new TTicketSucursal();
		iObjLog.debug("Entrando TicketsAjax.buscarTicket:Entrando...kTicketSucursal.." + kTicketSucursal);		
		try {
			objTTicketSucursal = objTicketDao.getTicket(kTicketSucursal);
			iObjLog.debug("Saliendo TicketsAjax.buscarTicket:Saliendo...  " + objTTicketSucursal.getUconsecutivoticketsucursal());
		} catch (Exception aObjException) {
    	    iObjLog.error("Error TicketsAjax.buscarTicket:Exception....", aObjException);
    	    objTTicketSucursal = null;
    	    throw aObjException;
		} finally {
			objTicketDao = null;
		}
		return objTTicketSucursal;
	}
	
    private double redodedo(double nD) {
		return Math.round(nD*Math.pow(10,0))/Math.pow(10,0);      	
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
