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

public class ReporteFacturacionAction extends SecureAction {

    private static Log iObjLog = LogFactory.getLog(ReporteFacturacionAction.class);

    public ReporteFacturacionAction() {
    }

           
    public void doImprimir(RunData aObjDatos, Context aObjContexto)
            throws Exception {
        Connection objCon = null;
        String strReporte = "";
        try {
        	iObjLog.debug("Entrando ReporteFacturacionAction.doImprimir:Entrando... ");
			GenericDAO objConn = new GenericDAO();
			objCon = objConn.getConnection();                			        									
            String strImagen = aObjDatos.getServletContext().getRealPath("/images/web2lab.jpg");            
			String strFechaIni = aObjDatos.getParameters().getString("txtDeFecha");
    		String strFechaFin = aObjDatos.getParameters().getString("txtAFecha");
    		int intTipoReporte = aObjDatos.getParameters().getInt("TipoReporte");
    		int intCconvenio = aObjDatos.getParameters().getInt("cconvenio");
    		Formatos formatos = new Formatos(); 
    		Map subreportes = new HashMap();  
			Map params = new HashMap();
			 
    		switch (intTipoReporte) {
    			case 1: 
    			{	
    				if(intCconvenio>0){
	    				strReporte = "RepNotaCreditoConvenio";
	    			
	    		            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
	    		            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
	    		            params.put("objfechaini", formatos.getFecha(strFechaIni));
	    		            params.put("objfechafin", formatos.getFecha(strFechaFin));
	    		            params.put("convenio", new Integer(intCconvenio)); 
	    		            params.put("imagen", strImagen);            
	    		                      
	    				
    				}else{
    					strReporte = "RepNotaCreditoFechas";
    			            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
    			            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
    			            params.put("objfechaini", formatos.getFecha(strFechaIni));
    			            params.put("objfechafin", formatos.getFecha(strFechaFin));            
    			            params.put("imagen", strImagen);            
    			                      
    				}
    				break;
    			}
    			case 2: 
    			{	
    				if(intCconvenio>0){
	    				strReporte = "ReporteFacturacion";
	    			
	    		            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
	    		            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
	    		            params.put("objfechaini", formatos.getFecha(strFechaIni));
	    		            params.put("objfechafin", formatos.getFecha(strFechaFin));
	    		            params.put("cconvenio", new Integer(intCconvenio)); 
	    		            params.put("imagen", strImagen);            
	    		                      
	    				
    				}else{
    					strReporte = "ReporteFacturacionAllClientes";
    			            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
    			            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
    			            params.put("objfechaini", formatos.getFecha(strFechaIni));
    			            params.put("objfechafin", formatos.getFecha(strFechaFin));            
    			            params.put("imagen", strImagen);            
    			                      
    				}
    				break;
    			}
    			case 3: 
    			{	
    				if(intCconvenio>0){
	    				strReporte = "ReporteOrdenesPendientesSucursalConvenio";
	    			
	    		            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
	    		            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
	    		            params.put("objfechaini", formatos.getFecha(strFechaIni));
	    		            params.put("objfechafin", formatos.getFecha(strFechaFin));
	    		            params.put("cconvenio", new Integer(intCconvenio)); 
	    		            params.put("imagen", strImagen);            
	    		                      
	    				
    				}else{
    					strReporte = "ReporteOrdenesPendientesSucursal";
    			            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
    			            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
    			            params.put("objfechaini", formatos.getFecha(strFechaIni));
    			            params.put("objfechafin", formatos.getFecha(strFechaFin));            
    			            params.put("imagen", strImagen);            
    			                      
    				}
    				break;
    			}
    			case 4: 
    			{	
    				if(intCconvenio>0){
	    				strReporte = "ReporteOrdenesPendientesFacturacionConvenio";
	    			
	    		            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
	    		            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
	    		            params.put("objfechaini", formatos.getFecha(strFechaIni));
	    		            params.put("objfechafin", formatos.getFecha(strFechaFin));
	    		            params.put("cconvenio", new Integer(intCconvenio)); 
	    		            params.put("imagen", strImagen);            
	    		                      
	    				
    				}else{
    					strReporte = "ReporteOrdenesPendientesFacturacion";
    			            params.put("fechaini", formatos.getFechaBD(strFechaIni,"i"));
    			            params.put("fechafin", formatos.getFechaBD(strFechaFin,"f"));
    			            params.put("objfechaini", formatos.getFecha(strFechaIni));
    			            params.put("objfechafin", formatos.getFecha(strFechaFin));            
    			            params.put("imagen", strImagen);            
    			                      
    				}
    				break;
    			}
    			
    		}
    		
            String strNomArchivo = strReporte + "_Ini" + strFechaIni +"Fin" + strFechaFin + "_" + Calendar.getInstance().getTimeInMillis() + ".pdf";
                                               
                         
                new GeneraReporte().generaReportePdf(strReporte + ".jasper", strNomArchivo, params, subreportes, objCon, aObjDatos, aObjContexto);                        	
            
        }catch (Exception aError) {
            iObjLog.error("Error en ReportesFacturacionAction.doImprimir ",aError);
            aObjContexto.put("resultado", aError);
            throw aError;
        } finally {
        	if(objCon!=null)objCon.close();        	
        }
    }        
}