package mx.com.web2lab.actions.reportes;

import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import mx.com.web2lab.actions.SecureAction;
import mx.com.web2lab.reportes.GeneraReporte;
import mx.com.web2lab.util.Formatos;
import mx.com.web2lab.util.GenericDAO;
import mx.com.web2lab.backend.dao.ap.PagosDao;
import mx.com.web2lab.backend.hbm.om.ap.TCorteCaja;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

public class ReporteAction extends SecureAction {

    private static Log iObjLog = LogFactory.getLog(ReporteAction.class);

    public ReporteAction() {
    }

    public void doReporteconvenio(RunData aObjDatos, Context aObjContexto)
    throws Exception {
		iObjLog.debug("Entrando ReporteAction.doReporteConvenio:Entrando... ");		
			Connection objCon = null;
			String strReporte = "";
			try {
				GenericDAO objConn = new GenericDAO();
				objCon = objConn.getConnection();                			        									
			    String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
				String strIdUnidad = aObjDatos.getSession().getAttribute("strIdUnidadActual") + "";
				strIdUnidad = aObjDatos.getSession().getAttribute("idgrupo").toString();
				String strNombreUnidad = aObjDatos.getSession().getAttribute("grupo").toString();
				String strNombreUsuario = aObjDatos.getSession().getAttribute("strNombreCompleto").toString();
				String strFechaIni = aObjDatos.getParameters().getString("txtDeFecha");
				String strFechaFin = aObjDatos.getParameters().getString("txtAFecha");
				strReporte = "RepConvenioReporte";
			    String strNomArchivo = strReporte + "_Ini" + strFechaIni +"Fin" + strFechaFin + "_" + Calendar.getInstance().getTimeInMillis() + ".pdf";
			    Map params = new HashMap();
			    params.put("csucursal", new Integer(strIdUnidad));           
			    params.put("nombreusuario", strNombreUnidad + " " + strNombreUsuario);
			    params.put("imagen", strImagen);            
			    Map subreportes = new HashMap();                        
		        new GeneraReporte().generaReportePdf(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                        	
			}catch (Exception aError) {
			    iObjLog.error("Error en RepPacientesFundacion.doImprimir ",aError);
			    aObjContexto.put("resultado", aError);
			    throw aError;
			} finally {
				if(objCon!=null)objCon.close();        	
			}
	}            

    public void doReportefacturas(RunData aObjDatos, Context aObjContexto)
    throws Exception {
		iObjLog.debug("Entrando ReporteAction.doReportefacturas:Entrando... ");		
			Connection objCon = null;
			String strReporte = "";
            Formatos formatos = new Formatos();                                    
			try {
				GenericDAO objConn = new GenericDAO();
				objCon = objConn.getConnection();                			        									
			    String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
				String cConvenio = aObjDatos.getParameters().getString("cConvenio") + "";
				String strFechaIni = aObjDatos.getParameters().getString("txtDeFecha");
				String strFechaFin = aObjDatos.getParameters().getString("txtAFecha");
				String uTipoFile = aObjDatos.getParameters().getString("uTipoFile") + "";
			    if (new Integer(cConvenio).intValue() != 0) {				
			    	strReporte = "ReporteFacturacion";
			    } else {
			    	strReporte = "ReporteFacturacionAllClientes";
			    }
			    String strNomArchivo = "";
			    if (new Integer(uTipoFile).intValue() != 0) {							    
			    	strNomArchivo = strReporte + "_cConvenio" + cConvenio  + "_" + Calendar.getInstance().getTimeInMillis() + ".pdf";
			    } else {
				    strNomArchivo = strReporte + "_cConvenio" + cConvenio  + "_" + Calendar.getInstance().getTimeInMillis() + ".xls";
			    }
			    Map params = new HashMap();
			    if (new Integer(cConvenio).intValue() != 0) {
				    params.put("cconvenio", new Integer(cConvenio));           
			    }
	            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
	            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
	            params.put("objfechaini", formatos.getFecha(strFechaIni));
	            params.put("objfechafin", formatos.getFecha(strFechaFin));            
			    params.put("imagen", strImagen);            
			    Map subreportes = new HashMap();                        
			    if (new Integer(uTipoFile).intValue() != 0) {							    
			    	new GeneraReporte().generaReportePdf(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                   
			    } else {
			    	new GeneraReporte().generaReporteExcel(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                   
			    }
			}catch (Exception aError) {
			    iObjLog.error("Error en doReportefacturas.doImprimir ",aError);
			    aObjContexto.put("resultado", aError);
			    throw aError;
			} finally {
				if(objCon!=null)objCon.close();        	
			}
	}
    
    public void doReportependientesucursales(RunData aObjDatos, Context aObjContexto)
    	    throws Exception {
    			iObjLog.debug("Entrando ReporteAction.repPendienteSucursal:Entrando... ");		
    				Connection objCon = null;
    				String strReporte = "";
    	            Formatos formatos = new Formatos();                                    
    				try {
    					GenericDAO objConn = new GenericDAO();
    					objCon = objConn.getConnection();                			        									
    				    String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
    					String cConvenio = aObjDatos.getParameters().getString("cConvenio") + "";
    					String strFechaIni = aObjDatos.getParameters().getString("txtDeFecha");
    					String strFechaFin = aObjDatos.getParameters().getString("txtAFecha");
    					String uTipoFile = aObjDatos.getParameters().getString("uTipoFile") + "";
    				    if (new Integer(cConvenio).intValue() != 0) {				
    				    	strReporte = "ReporteOrdenesPendientesSucursalConvenio";
    				    } else {
    				    	strReporte = "ReporteOrdenesPendientesSucursal";
    				    }
    				    String strNomArchivo = "";
    				    if (new Integer(uTipoFile).intValue() != 0) {							    
    				    	strNomArchivo = strReporte + "_cConvenio" + cConvenio  + "_" + Calendar.getInstance().getTimeInMillis() + ".pdf";
    				    } else {
    					    strNomArchivo = strReporte + "_cConvenio" + cConvenio  + "_" + Calendar.getInstance().getTimeInMillis() + ".xls";
    				    }
    				    Map params = new HashMap();
    				    if (new Integer(cConvenio).intValue() != 0) {
    					    params.put("cconvenio", new Integer(cConvenio));           
    				    }
    		            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
    		            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
    		            params.put("objfechaini", formatos.getFecha(strFechaIni));
    		            params.put("objfechafin", formatos.getFecha(strFechaFin));            
    				    params.put("imagen", strImagen);            
    				    
    				    
    				    Map subreportes = new HashMap();                        
    				    if (new Integer(uTipoFile).intValue() != 0) {							    
    				    	new GeneraReporte().generaReportePdf(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                   
    				    } else {
    				    	new GeneraReporte().generaReporteExcel(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                   
    				    }
    				}catch (Exception aError) {
    				    iObjLog.error("Error en doReporteNotas.doImprimir ",aError);
    				    aObjContexto.put("resultado", aError);
    				    throw aError;
    				} finally {
    					if(objCon!=null)objCon.close();        	
    				}
    		}
   
    public void doReportependientefacturacion(RunData aObjDatos, Context aObjContexto)
    	    throws Exception {
    			iObjLog.debug("Entrando ReporteAction.repPendienteFacturacion:Entrando... ");		
    				Connection objCon = null;
    				String strReporte = "";
    	            Formatos formatos = new Formatos();                                    
    				try {
    					GenericDAO objConn = new GenericDAO();
    					objCon = objConn.getConnection();                			        									
    				    String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
    					String cConvenio = aObjDatos.getParameters().getString("cConvenio") + "";
    					String strFechaIni = aObjDatos.getParameters().getString("txtDeFecha");
    					String strFechaFin = aObjDatos.getParameters().getString("txtAFecha");
    					String uTipoFile = aObjDatos.getParameters().getString("uTipoFile") + "";
    				    if (new Integer(cConvenio).intValue() != 0) {				
    				    	strReporte = "ReporteOrdenesPendientesFacturacionConvenio";
    				    } else {
    				    	strReporte = "ReporteOrdenesPendientesFacturacion";
    				    }
    				    String strNomArchivo = "";
    				    if (new Integer(uTipoFile).intValue() != 0) {							    
    				    	strNomArchivo = strReporte + "_cConvenio" + cConvenio  + "_" + Calendar.getInstance().getTimeInMillis() + ".pdf";
    				    } else {
    					    strNomArchivo = strReporte + "_cConvenio" + cConvenio  + "_" + Calendar.getInstance().getTimeInMillis() + ".xls";
    				    }
    				    Map params = new HashMap();
    				    if (new Integer(cConvenio).intValue() != 0) {
    					    params.put("cconvenio", new Integer(cConvenio));           
    				    }
    		            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
    		            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
    		            params.put("objfechaini", formatos.getFecha(strFechaIni));
    		            params.put("objfechafin", formatos.getFecha(strFechaFin));            
    				    params.put("imagen", strImagen);            
    				    
    				    
    				    Map subreportes = new HashMap();                        
    				    if (new Integer(uTipoFile).intValue() != 0) {							    
    				    	new GeneraReporte().generaReportePdf(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                   
    				    } else {
    				    	new GeneraReporte().generaReporteExcel(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                   
    				    }
    				}catch (Exception aError) {
    				    iObjLog.error("Error en doReporteNotas.doImprimir ",aError);
    				    aObjContexto.put("resultado", aError);
    				    throw aError;
    				} finally {
    					if(objCon!=null)objCon.close();        	
    				}
    		}

    public void doReportenotas(RunData aObjDatos, Context aObjContexto)
    	    throws Exception {
    			iObjLog.debug("Entrando ReporteAction.doReporteNotas:Entrando... ");		
    				Connection objCon = null;
    				String strReporte = "";
    	            Formatos formatos = new Formatos();                                    
    				try {
    					GenericDAO objConn = new GenericDAO();
    					objCon = objConn.getConnection();                			        									
    				    String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
    					String cConvenio = aObjDatos.getParameters().getString("cConvenio") + "";
    					String strFechaIni = aObjDatos.getParameters().getString("txtDeFecha");
    					String strFechaFin = aObjDatos.getParameters().getString("txtAFecha");
    					String uTipoFile = aObjDatos.getParameters().getString("uTipoFile") + "";
    				    if (new Integer(cConvenio).intValue() != 0) {				
    				    	strReporte = "RepNotaCreditoConvenio";
    				    } else {
    				    	strReporte = "RepNotaCreditoFechas";
    				    }
    				    String strNomArchivo = "";
    				    if (new Integer(uTipoFile).intValue() != 0) {							    
    				    	strNomArchivo = strReporte + "_cConvenio" + cConvenio  + "_" + Calendar.getInstance().getTimeInMillis() + ".pdf";
    				    } else {
    					    strNomArchivo = strReporte + "_cConvenio" + cConvenio  + "_" + Calendar.getInstance().getTimeInMillis() + ".xls";
    				    }
    				    Map params = new HashMap();
    				    if (new Integer(cConvenio).intValue() != 0) {
    					    params.put("convenio", new Integer(cConvenio));           
    				    }
    		            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
    		            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
    		            params.put("objfechaini", formatos.getFecha(strFechaIni));
    		            params.put("objfechafin", formatos.getFecha(strFechaFin));            
    				    params.put("imagen", strImagen);            
    				    
    				    
    				    Map subreportes = new HashMap();                        
    				    if (new Integer(uTipoFile).intValue() != 0) {							    
    				    	new GeneraReporte().generaReportePdf(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                   
    				    } else {
    				    	new GeneraReporte().generaReporteExcel(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                   
    				    }
    				}catch (Exception aError) {
    				    iObjLog.error("Error en doReporteNotas.doImprimir ",aError);
    				    aObjContexto.put("resultado", aError);
    				    throw aError;
    				} finally {
    					if(objCon!=null)objCon.close();        	
    				}
    		}
    public void doReportepagos(RunData aObjDatos, Context aObjContexto)
    throws Exception {
		iObjLog.debug("Entrando ReporteAction.doReportepagos:Entrando... ");		
			Connection objCon = null;
			String strReporte = "";
            Formatos formatos = new Formatos();                                    
			try {
				GenericDAO objConn = new GenericDAO();
				objCon = objConn.getConnection();                			        									
			    String strImagen = aObjDatos.getServletContext().getRealPath("/images/4-LOGOS-ALT.png");            
				String cConvenio = aObjDatos.getParameters().getString("cConvenio") + "";
				String uTipoFile = aObjDatos.getParameters().getString("uTipoFile") + "";
				String strFechaIni = aObjDatos.getParameters().getString("txtDeFecha");
				String strFechaFin = aObjDatos.getParameters().getString("txtAFecha");
			    if (new Integer(cConvenio).intValue() != 0) {				
					strReporte = "ReportePagos";
			    } else {
			    	strReporte = "ReportePagosAllClientes";
			    }
			    String strNomArchivo = "";
			    if (new Integer(uTipoFile).intValue() != 0) {							    
			    	strNomArchivo = strReporte + "_cConvenio" + cConvenio  + "_" + Calendar.getInstance().getTimeInMillis() + ".pdf";
			    } else {
				    strNomArchivo = strReporte + "_cConvenio" + cConvenio  + "_" + Calendar.getInstance().getTimeInMillis() + ".xls";
			    }
			    Map params = new HashMap();
			    if (new Integer(cConvenio).intValue() != 0) {
				    params.put("cconvenio", new Integer(cConvenio));           
			    }
	            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
	            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
	            params.put("objfechaini", formatos.getFecha(strFechaIni));
	            params.put("objfechafin", formatos.getFecha(strFechaFin));            
			    params.put("imagen", strImagen);            
			    Map subreportes = new HashMap();                        
			    if (new Integer(uTipoFile).intValue() != 0) {							    
			    	new GeneraReporte().generaReportePdf(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                   
			    } else {
			    	new GeneraReporte().generaReporteExcel(strReporte + "xls.jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                   
			    }
			}catch (Exception aError) {
			    iObjLog.error("Error en doReportepagos.doImprimir ",aError);
			    aObjContexto.put("resultado", aError);
			    throw aError;
			} finally {
				if(objCon!=null)objCon.close();        	
			}
	}            
    
    
    public void doEstadocuentapuntosmedico(RunData aObjDatos, Context aObjContexto)
    throws Exception {
		iObjLog.debug("Entrando ReporteAction.doEstadocuentapuntosmedico:Entrando... ");		
			Connection objCon = null;
			String strReporte = "";
            Formatos formatos = new Formatos();                                    
			try {
				GenericDAO objConn = new GenericDAO();
				objCon = objConn.getConnection();                			        									
			    String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
				String cMedico = aObjDatos.getParameters().getString("cMedico") + "";
				String strFechaIni = aObjDatos.getParameters().getString("txtDeFecha");
				String strFechaFin = aObjDatos.getParameters().getString("txtAFecha");
				strReporte = "RepOrdenEstadoCuentaPuntos";
			    String strNomArchivo = strReporte + "_cMedico" + cMedico  + "_" + Calendar.getInstance().getTimeInMillis() + ".pdf";
			    Map params = new HashMap();
			    params.put("cmedico", new Integer(cMedico));           
	            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
	            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
	            params.put("objfechaini", formatos.getFecha(strFechaIni));
	            params.put("objfechafin", formatos.getFecha(strFechaFin));            
			    params.put("imagen", strImagen);            
			    Map subreportes = new HashMap();                        
		        new GeneraReporte().generaReportePdf(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                        	
			}catch (Exception aError) {
			    iObjLog.error("Error en doEstadocuentapuntosmedico.doImprimir ",aError);
			    aObjContexto.put("resultado", aError);
			    throw aError;
			} finally {
				if(objCon!=null)objCon.close();        	
			}
	}            
    
    public void doImprimir(RunData aObjDatos, Context aObjContexto)
            throws Exception {
        Connection objCon = null;
        String strReporte = "";
        try {
			GenericDAO objConn = new GenericDAO();
			objCon = objConn.getConnection();                			        									
            String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
			String strIdUnidad = aObjDatos.getSession().getAttribute("strIdUnidadActual") + "";
			strIdUnidad = aObjDatos.getSession().getAttribute("idgrupo").toString();
			String strNombreUnidad = aObjDatos.getSession().getAttribute("grupo").toString();
			String strNombreUsuario = aObjDatos.getSession().getAttribute("strNombreCompleto").toString();
    		String strFechaIni = aObjDatos.getParameters().getString("txtDeFecha");
    		String strFechaFin = aObjDatos.getParameters().getString("txtAFecha");
    		int intTipoReporte = aObjDatos.getParameters().getInt("TipoReporte");
    		switch (intTipoReporte) {
    			case 1: 
    				strReporte = "RepEntregaResultados";
    				break;
	    		case 2: 
					strReporte = "RepPacientesFundacion";
					break;
	    		case 3: 
					strReporte = "RepOrdenExamenFundacion";
					break;
	    		case 4: 
					strReporte = "RepOrdenExcentasFundacion";
					break;
	    		case 5: 
					strReporte = "RepOrdenCanceladaFundacion";
					break;
	    		case 6: 
					strReporte = "RepOrdenAdeudoFundacion";
					break;
	    		case 7: 
					strReporte = "RepOrdenExamenMedNuevosAquien";
					break;
	    		case 8: 
					strReporte = "RepOrdenEntregadaFundacion";
					break;
	    		case 9: 
					strReporte = "RepOrdenEntregadaRegistradaFundacion";
					break;
	    		case 10: 
					strReporte = "RepOrdenNoEntregadaRegistradaFundacion";
					break;						    			
	    		case 11: 
					strReporte = "RepOrdenExamenMuestraFundacion";
					break;						    			
	    		case 12: 
					strReporte = "RepPacienteNuevoFundacion";
					break;						    			
	    		case 13: 
					strReporte = "RepPacienteSubsecuenteFundacion";
					break;						    			
	    		case 14: 
					strReporte = "RepPacienteNuevoDetalleFundacion";
					break;						    			
	    		case 15: 
					strReporte = "RepPacienteSubsecuenteDetalleFundacion";
					break;						    			
	    		case 16: 
                    strReporte = "RepEstadisticaInfodiamex";
                    break;					
	    		case 17: 
                    strReporte = "RepOrdenEstadoCuenta";
                    break;					
    		}
            String strNomArchivo = strReporte + "_Ini" + strFechaIni +"Fin" + strFechaFin + "_" + Calendar.getInstance().getTimeInMillis() + ".pdf";
            Formatos formatos = new Formatos();                                    
            Map params = new HashMap();
            params.put("csucursal", new Integer(strIdUnidad));           
            params.put("nombreusuario", strNombreUnidad + " " + strNombreUsuario);
            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
            params.put("objfechaini", formatos.getFecha(strFechaIni));
            params.put("objfechafin", formatos.getFecha(strFechaFin));            
            params.put("imagen", strImagen);            
            Map subreportes = new HashMap();                        
            if(intTipoReporte==14) {
                 subreportes.put("represumen", "RepResumenEstadistica.jasper");
                 subreportes.put("repdetalle", "RepDetalleEstadistica.jasper");
            }            
            if(intTipoReporte==17) {
	            java.sql.Statement objstmt = objCon.createStatement();
	            java.sql.ResultSet objrst = objstmt.executeQuery("SELECT CMEDICO,CCLAVE,CCHEQUE  FROM T_COMISION_MEDICO WHERE cclave in (13228, 856, 865,925,1053,1779,7540,13327,933,961,1006,1081,1257,1261,1270,1285,1367,1463,1633,6839,7003,7252,7840,7867,8409,8565,8601,7939,8993,9420,12032,13328,13992,14501,15652,17305,17842,19096) ORDER BY CMEDICO");
	            while (objrst.next()) {
		            	params.put("cmedico", new Integer(objrst.getInt("CMEDICO")));
		                strNomArchivo = strReporte + "_Medico_" + objrst.getInt("CCLAVE") +"_Cheque_" + objrst.getInt("CCHEQUE") + "_" + Calendar.getInstance().getTimeInMillis() + ".pdf";
			            new GeneraReporte().generaReportePdf(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);            
	            }
            } else {
                new GeneraReporte().generaReportePdf(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                        	
            }
        }catch (Exception aError) {
            iObjLog.error("Error en RepPacientesFundacion.doImprimir ",aError);
            aObjContexto.put("resultado", aError);
            throw aError;
        } finally {
        	if(objCon!=null)objCon.close();        	
        }
    }        

    public void doImprimirtipocomunidad(RunData aObjDatos, Context aObjContexto)
    throws Exception {
		Connection objCon = null;
		try {
			GenericDAO objConn = new GenericDAO();
			objCon = objConn.getConnection();                			        									
		    String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
			String strFechaIni = aObjDatos.getParameters().getString("txtDeFecha");
			String strFechaFin = aObjDatos.getParameters().getString("txtAFecha");
			String strIdUnidad = aObjDatos.getSession().getAttribute("strIdUnidadActual") + "";
			strIdUnidad = aObjDatos.getSession().getAttribute("idgrupo").toString();
			Integer nComunidad = aObjDatos.getParameters().getInteger("ncomunidad");
		    String strNomArchivo = "RepOrdenTipoComunidadFundacion" + "_Ini" + strFechaIni +"Fin" + strFechaFin + "_" + Calendar.getInstance().getTimeInMillis() + ".pdf";
		    Formatos formatos = new Formatos();                                    
		    Map params = new HashMap();
		    params.put("csucursal", new Integer(strIdUnidad));            
		    params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
		    params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
		    params.put("objfechaini", formatos.getFecha(strFechaIni));
		    params.put("objfechafin", formatos.getFecha(strFechaFin));            
		    params.put("imagen", strImagen);            
		    params.put("ncomunidad", nComunidad);            
		    Map subreportes = new HashMap();                        
		    new GeneraReporte().generaReportePdf("RepOrdenTipoComunidadFundacion.jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);            
		}catch (Exception aError) {
		    iObjLog.error("Error en RepOrdenExcentasFundacion.doImprimirtipocomunidad ",aError);
		    aObjContexto.put("resultado", aError);
		    throw aError;
		} finally {
			if(objCon!=null)objCon.close();        	
		}
	}    

    public void doImprimirtipoexamen(RunData aObjDatos, Context aObjContexto)
    throws Exception {
		Connection objCon = null;
		try {
			int intUser = aObjDatos.getUser().getId();
			String strIdUnidad = aObjDatos.getSession().getAttribute("strIdUnidadActual") + "";
			strIdUnidad = aObjDatos.getSession().getAttribute("idgrupo").toString();
			GenericDAO objConn = new GenericDAO();
			objCon = objConn.getConnection();                			        									
		    String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
			String strFechaIni = aObjDatos.getParameters().getString("txtDeFecha");
			String strFechaFin = aObjDatos.getParameters().getString("txtAFecha");
			Integer nexamen = aObjDatos.getParameters().getInteger("nexamen");
		    String strNomArchivo = "RepTipoExamenFundacion" + "_Ini" + strFechaIni +"Fin" + strFechaFin + "_" + Calendar.getInstance().getTimeInMillis() + ".pdf";
		    Formatos formatos = new Formatos();                                    
		    Map params = new HashMap();
		    params.put("csucursal", new Integer(strIdUnidad));            
		    params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
		    params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
		    params.put("objfechaini", formatos.getFecha(strFechaIni));
		    params.put("objfechafin", formatos.getFecha(strFechaFin));            
		    params.put("imagen", strImagen);            
		    params.put("nexamen", nexamen);            
		    Map subreportes = new HashMap();                        
		    new GeneraReporte().generaReportePdf("RepTipoExamenFundacion.jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);            
		}catch (Exception aError) {
		    iObjLog.error("Error en RepOrdenExcentasFundacion.doImprimirtipoexamen ",aError);
		    aObjContexto.put("resultado", aError);
		    throw aError;
		} finally {
			if(objCon!=null)objCon.close();        	
		}
	}    
    
    
    public void doCortecaja(RunData aObjDatos, Context aObjContexto)
    throws Exception {
    	Connection objCon = null;
    	PagosDao objPagos = new PagosDao();
    	TCorteCaja objCorteCaja =  new TCorteCaja();
    	int ccortecaja = 0;
		try {
			int intUser = aObjDatos.getUser().getId();
			String strIdUnidad = aObjDatos.getSession().getAttribute("strIdUnidadActual") + "";
			strIdUnidad = aObjDatos.getSession().getAttribute("idgrupo").toString();
			GenericDAO objConn = new GenericDAO();
			objCon = objConn.getConnection();                			        									
		    String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
			int intActualiza = aObjDatos.getParameters().getInt("Actualizacion",0);
			int intCorteCaja = aObjDatos.getParameters().getInt("CorteCaja",0);
			if (intActualiza == 1) {
				objCorteCaja = objPagos.actualizaCorteCaja(aObjDatos.getUser().getId(),new Integer(strIdUnidad).intValue());
				intCorteCaja = objCorteCaja.getKcortecaja().intValue();
				ccortecaja = objCorteCaja.getUcortecajasucursal();
			}			
		    String strNomArchivo = "RepCorteCaja" + Calendar.getInstance().getTimeInMillis() + ".pdf";
		    Map params = new HashMap();
            iObjLog.debug("EL CORTE DE CAJA QUE ESTOY EJECUTANDO ES " +intCorteCaja + " " + intActualiza);
		    params.put("csucursal", new Integer(strIdUnidad));            
		    params.put("kcortecaja", new Integer(intCorteCaja));            
		    params.put("ccortecaja", new Integer(ccortecaja));            
		    params.put("csucursal", new Integer(strIdUnidad));            
		    params.put("nombreusuario", aObjDatos.getUser().getFirstName() + " " + aObjDatos.getUser().getLastName());            
		    params.put("imagen", strImagen);            
		    Map subreportes = new HashMap();     
            subreportes.put("repdetalle", "RepCorteCajaDetalleFundacion.jasper");
            subreportes.put("represumen","RepResumenCorteCajaFundacion.jasper");
		    new GeneraReporte().generaReportePdf("RepCorteCajaFundacion.jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);            
		}catch (Exception aError) {
		    iObjLog.error("Error en RepPacientesFundacion.doImprimir ",aError);
		    aObjContexto.put("resultado", aError);
		    throw aError;
		} finally {
			if(objCon!=null)objCon.close();        	
		}
    }        
    
    public void doRecibo(RunData aObjDatos, Context aObjContexto)
    throws Exception {
    	Connection objCon = null;
		try {
			GenericDAO objConn = new GenericDAO();
			objCon = objConn.getConnection();                			        									
		    String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
			int intAdmision = aObjDatos.getParameters().getInt("kAdmision",0);
		    String strNomArchivo = "RepOrdenFactura" + Calendar.getInstance().getTimeInMillis() + ".pdf";
		    Map params = new HashMap();
            iObjLog.debug("Procesando la Admision " +intAdmision);
		    params.put("kadmision", new Double(intAdmision));            
            Map subreportes = new HashMap();            
            subreportes.put("repexamenesordenpac", "RepExamenesordenpac.jasper");
            subreportes.put("repexamenesorden", "RepExamenesorden.jasper");
		    new GeneraReporte().generaReporteOrdenPdf("RepCompFacInfodiamex.jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);            
		}catch (Exception aError) {
		    iObjLog.error("Error en RepPacientesFundacion.doImprimir ",aError);
		    aObjContexto.put("resultado", aError);
		    throw aError;
		} finally {
			if(objCon!=null)objCon.close();        	
		}
    }            
    
    public void doRecibofactura(RunData aObjDatos, Context aObjContexto)
    	    throws Exception {
    	    	Connection objCon = null;
    			try {
    	            iObjLog.debug("Entrando la Admision doRecibofactura ");
    				GenericDAO objConn = new GenericDAO();
    				objCon = objConn.getConnection();                			        									
    			    String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
    				int intAdmision = aObjDatos.getParameters().getInt("kAdmision",0);
    			    String strNomArchivo = "RepOrdenFactura" + Calendar.getInstance().getTimeInMillis() + ".pdf";
    	            iObjLog.debug("Procesando la Admision doRecibofactura " +intAdmision);
    			    Map params = new HashMap();
    			    params.put("kadmision", new Double(intAdmision));            
    	            Map subreportes = new HashMap();            
    	            subreportes.put("repexamenesordenpac", "RepExamenesordenpacFac.jasper");
    	            subreportes.put("repexamenesorden", "RepExamenesordenFac.jasper");
    			    new GeneraReporte().generaReporteOrdenPdf("RepCompFacInfodiamexFac.jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);            
    	            iObjLog.debug("Saliendo la Admision doRecibofactura " +intAdmision);
    			}catch (Exception aError) {
    			    iObjLog.error("Error en ReporteAction.doRecibofactura ",aError);
    			    aObjContexto.put("resultado", aError);
    			    throw aError;
    			} finally {
    				if(objCon!=null)objCon.close();        	
    			}
    	    }

	/**
	 * Metodo que llama la generaci&oacute;n del Formato de indicaciones para el paciente,   
	 * @param data
	 * @param context
	 * @throws Exception
	 */
    public void doIndicaciones(RunData aObjDatos, Context aObjContexto) throws Exception {
    	iObjLog.debug("doImprimir:ENTRANDO"); 
        Connection objCon = null;
        try {
            iObjLog.debug("Entrando la Admision doRecibofactura ");
			GenericDAO objConn = new GenericDAO();
			objCon = objConn.getConnection();                			        									
		    String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
			int intCotizacion = aObjDatos.getParameters().getInt("kcotizacion",0);
		    String strNomArchivo = "RepIndicacionPaciente" + Calendar.getInstance().getTimeInMillis() + ".pdf";
            iObjLog.debug("Procesando la Admision doRecibofactura " +intCotizacion);
		    Map params = new HashMap();
		    params.put("kordensucursalcotizacion", new Double(intCotizacion));            
		    params.put("imagen", strImagen);            
            Map subreportes = new HashMap();            
            subreportes.put("repprocedimientoexamen","RepProcedimientoExamen.jasper");
            subreportes.put("repindicacionpaciente","RepIndicacionPaciente.jasper");            
		    new GeneraReporte().generaReporteOrdenPdf("RepIndicaciones.jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);            
            iObjLog.debug("Saliendo la Admision doRecibofactura " +intCotizacion);        	
		}catch (Exception aError) {
		    iObjLog.error("Error en ReporteAction.doRecibofactura ",aError);
		    aObjContexto.put("resultado", aError);
		    throw aError;
		} finally {
			if(objCon!=null)objCon.close();        	
		}
    }
    
    /**
     * Versi&oacute;n 25 de Marzo 2013 
     BY
     */
    public void doReporterefacturas(RunData aObjDatos, Context aObjContexto)
    	    throws Exception {
    			iObjLog.debug("Entrando ReporteAction.doReporterefacturas:Entrando... ");		
    				Connection objCon = null;
    				String strReporte = "";
    	            Formatos formatos = new Formatos();                                    
    				try {
    					GenericDAO objConn = new GenericDAO();
    					objCon = objConn.getConnection();                			        									
    				    String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
    					String cConvenio = aObjDatos.getParameters().getString("cConvenio") + "";
    					String strFechaIni = aObjDatos.getParameters().getString("txtDeFecha");
    					String strFechaFin = aObjDatos.getParameters().getString("txtAFecha");
    					String uTipoFile = aObjDatos.getParameters().getString("uTipoFile") + "";
    				    if (new Integer(cConvenio).intValue() != 0) {				
    				    	strReporte = "ReporteRefacturacion";
    				    } else {
    				    	strReporte = "ReporteRefacturacionAllClientes";
    				    }
    				    String strNomArchivo = "";
    				    if (new Integer(uTipoFile).intValue() != 0) {							    
    				    	strNomArchivo = strReporte + "_cConvenio" + cConvenio  + "_" + Calendar.getInstance().getTimeInMillis() + ".pdf";
    				    } else {
    					    strNomArchivo = strReporte + "_cConvenio" + cConvenio  + "_" + Calendar.getInstance().getTimeInMillis() + ".xls";
    				    }
    				    Map params = new HashMap();
    				    if (new Integer(cConvenio).intValue() != 0) {
    					    params.put("cconvenio", new Integer(cConvenio));           
    				    }
    		            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
    		            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
    		            params.put("objfechaini", formatos.getFecha(strFechaIni));
    		            params.put("objfechafin", formatos.getFecha(strFechaFin));            
    				    params.put("imagen", strImagen);            
    				    Map subreportes = new HashMap();                        
    				    if (new Integer(uTipoFile).intValue() != 0) {							    
    				    	new GeneraReporte().generaReportePdf(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                   
    				    } else {
    				    	new GeneraReporte().generaReporteExcel(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                   
    				    }
    				}catch (Exception aError) {
    				    iObjLog.error("Error en doReportefacturas.doImprimir ",aError);
    				    aObjContexto.put("resultado", aError);
    				    throw aError;
    				} finally {
    					if(objCon!=null)objCon.close();        	
    				}
    		}
}
