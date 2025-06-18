package mx.com.web2lab.reportes;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mx.com.web2lab.backend.hbm.ConfiguracionProperties;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

public class GeneraReporte  implements Serializable{

	/** log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(GeneraReporte.class);
	
	private Connection connection = null;
	private JRDataSource jrDataSrc = null; 
	private Map params = null;
	private Map subreportes = null;
	private RunData data = null;
	private Context context = null;
	private String jasperFileName = null;
	private String jasperFileNameFull = null;
	private String pdfFileName = null;
	private String pdfFileNameFull = null;
	private String httpPath = null;
	private String deafultContentType = "application/pdf";
	private String defaultHtmlHeader = "inline;filename=Reporte_"+ new Date().getTime() + ".pdf";
	private String contentType = null;
	private String htmlHeader = null;
	private byte[] bytes = null; 
	
	/** Constructor default */
	public GeneraReporte(){
		
	}
	
	/**
	 * Este metodo genera el reporte solicitado, ya sea como 
	 * un archivo pdf o un stream de bytes que se manda al 
	 * browser, dependiendo de la configuraci&oacute;n del 
	 * Configuracion.properties en la propiedad <b>reporte.genera.stream<b>. 
	 * nombre que esta configurada en el archivo 
	 * <b>Configuracion.properties</b>. 
	 * Regresa la ruta http del archivo pdf que se 
	 * haya generado. 
	 * @param jasperFileName
	 * @param pdfFileName
	 * @param params
	 * @param subreportes
	 * @param connection
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public void generaReportePdf(String jasperFileName, String pdfFileName, Map params, Map subreportes, Connection connection, RunData data, Context context) throws Exception{
		try {
			iObjLog.debug("generaReportePdf....Tipo de Reporte ...." + ConfiguracionProperties.getPropiedad("reporte.genera.stream"));
			String sJasperPath = ConfiguracionProperties.getPropiedad("reporte.genera.stream");
			if(sJasperPath!=null && sJasperPath.trim().equals("1")) {
				this.generarPdfStream(jasperFileName, pdfFileName, params, subreportes, connection, data);
			}else {				
				String sHttpPath = this.generarPdf(jasperFileName, pdfFileName, params, subreportes, connection, data);
				context.put("reporte",sHttpPath);
			}
		} catch (JRException jexp) {
			iObjLog.error("Exception Jasper generaReportePdf", jexp);
			throw jexp;			
		} catch(Exception aError) {
			iObjLog.error("generaReportePdf", aError);
			throw aError;
		}
	}


	public void generaReporteExcel(String jasperFileName, String pdfFileName, Map params, Map subreportes, Connection connection, RunData data, Context context) throws Exception{
		try {
			iObjLog.debug("generaReportePdf....Tipo de Reporte ...." + ConfiguracionProperties.getPropiedad("reporte.genera.stream"));
			String sHttpPath = this.generarExcel(jasperFileName, pdfFileName, params, subreportes, connection, data);
			context.put("reporte",sHttpPath);
		} catch (JRException jexp) {
			iObjLog.error("Exception Jasper generaReportePdf", jexp);
			throw jexp;			
		} catch(Exception aError) {
			iObjLog.error("generaReportePdf", aError);
			throw aError;
		}
	}
	
	
	/**
	 * Este metodo genera el reporte solicitado, ya sea como 
	 * un archivo pdf o un stream de bytes que se manda al 
	 * browser, dependiendo de la configuraci&oacute;n del 
	 * Configuracion.properties en la propiedad <b>reporte.genera.stream<b>. 
	 * nombre que esta configurada en el archivo 
	 * <b>Configuracion.properties</b>. 
	 * Regresa la ruta http del archivo pdf que se 
	 * haya generado. 
	 * @param jasperFileName
	 * @param pdfFileName
	 * @param params
	 * @param subreportes
	 * @param connection
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public void generaReporteOrdenPdf(String jasperFileName, String pdfFileName, Map params, Map subreportes, Connection connection, RunData data, Context context) throws Exception{
		try {
			iObjLog.debug("generaReportePdf....Tipo de Reporte ...." + ConfiguracionProperties.getPropiedad("reporte.genera.orden.stream"));
			String sJasperPath = ConfiguracionProperties.getPropiedad("reporte.genera.orden.stream");
			if(sJasperPath!=null && sJasperPath.trim().equals("1")) {
				this.generarPdfStream(jasperFileName, pdfFileName, params, subreportes, connection, data);
			}else {				
				String sHttpPath = this.generarPdf(jasperFileName, pdfFileName, params, subreportes, connection, data);
				context.put("reporte",sHttpPath);
			}
		} catch (JRException jexp) {
			iObjLog.error("Exception Jasper generaReportePdf", jexp);
			throw jexp;			
		} catch(Exception aError) {
			iObjLog.error("generaReportePdf", aError);
			throw aError;
		}
	}
	
	
	/**
	 * Este metodo genera el reporte solicitado, ya sea como 
	 * un archivo pdf o un stream de bytes que se manda al 
	 * browser, dependiendo de la configuraci&oacute;n del 
	 * Configuracion.properties en la propiedad <b>reporte.genera.stream<b>. 
	 * nombre que esta configurada en el archivo 
	 * <b>Configuracion.properties</b>. 
	 * Regresa la ruta http del archivo pdf que se 
	 * haya generado. 
	 * @param jasperFileName
	 * @param pdfFileName
	 * @param params
	 * @param subreportes
	 * @param connection
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public void generaReportePdf(String jasperFileName, String pdfFileName, Map params, Map subreportes, JRDataSource jrDataSource, RunData data, Context context) throws Exception{
		try {
			String sJasperPath = ConfiguracionProperties.getPropiedad("reporte.genera.stream");
			if(sJasperPath!=null && sJasperPath.trim().equals("1")) {
				this.generarPdfStream(jasperFileName, pdfFileName, params, subreportes, jrDataSource, data);
			}else {				
				String sHttpPath = this.generarPdf(jasperFileName, pdfFileName, params, subreportes, jrDataSource, data);
				context.put("reporte",sHttpPath);
			}
		}catch(Exception aError) {
			iObjLog.error("generaReportePdf", aError);
			throw aError;
		}
	}
	
	/**
	 * Este metodo genera el archivo pdf con la ruta y  
	 * nombre que esta configurada en el archivo 
	 * <b>Configuracion.properties</b>. 
	 * Regresa la ruta http del archivo pdf que se 
	 * haya generado. 
	 * @param jasperFileName
	 * @param pdfFileName
	 * @param params
	 * @param subreportes
	 * @param connection
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean generarPdfStream(String jasperFileName, String pdfFileName, Map params, Map subreportes, Connection connection, RunData data) 
	throws Exception{
		boolean generado = false;
		iObjLog.debug("generarPdfStream:ENTRANDO");
		try{
			this.contentType = this.deafultContentType;
			this.htmlHeader = this.defaultHtmlHeader;
			this.jasperFileName = jasperFileName; 
			this.pdfFileName = pdfFileName;
			this.params = params;
			this.subreportes = subreportes;
			this.connection = connection;
			this.data = data;
			this.setRutasReportes();
			Map parametrosFull = new HashMap();
			parametrosFull.putAll(this.params);
			if(subreportes!=null){
				parametrosFull.putAll(this.subreportes);
			}
			iObjLog.info("generarPdfStream:ANTES: " + jasperFileName);
			this.bytes = JasperRunManager.runReportToPdf(this.jasperFileNameFull, parametrosFull, this.connection);
			iObjLog.info("generarPdfStream:DESPUES: " + jasperFileName);
			if(this.bytes!=null){
				generado = ReportStream.setReportStream(data, this.bytes, this.contentType, this.htmlHeader);
			}
		} catch (JRException jaError) {
			iObjLog.error("generarPdfStream:ERROR JASPER", jaError);
			throw jaError;		
		}catch (Exception aError){
			iObjLog.error("generarPdfStream:ERROR", aError);
			throw aError;
		}
		iObjLog.debug("generarPdfStream:SALIENDO");
		return generado;
	}
	
	/**
	 * Este metodo genera el archivo pdf con la ruta y  
	 * nombre que esta configurada en el archivo 
	 * <b>Configuracion.properties</b>. 
	 * Regresa la ruta http del archivo pdf que se 
	 * haya generado. 
	 * @param jasperFileName
	 * @param pdfFileName
	 * @param params
	 * @param subreportes
	 * @param connection
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String generarPdf(String jasperFileName, String pdfFileName, Map params, Map subreportes, Connection connection, RunData data) 
	throws Exception{
		iObjLog.debug("generarPdf:ENTRANDO:\n-Reporte=" + jasperFileName + "\n-parametros: " + params);
		try{
			this.jasperFileName = jasperFileName; 
			this.pdfFileName = pdfFileName;
			this.params = params;
			this.subreportes = subreportes;
			this.connection = connection;
			this.data = data;
			this.setRutasReportes();
			Map parametrosFull = new HashMap();
			if(params!=null) parametrosFull.putAll(params);
			if(subreportes!=null)parametrosFull.putAll(subreportes);
			iObjLog.info("generarPdf:ANTES: " + jasperFileName);
/*			
			MReportes mReportes = new MReportes();
			iObjLog.debug("Primer parametro " +  this.jasperFileNameFull);
			iObjLog.debug("Segundo parametro " +  this.pdfFileNameFull);
			mReportes.getReporte(this.jasperFileNameFull, this.pdfFileNameFull, parametrosFull);
			*/
// Oliver			
			JasperRunManager.runReportToPdfFile(this.jasperFileNameFull, this.pdfFileNameFull, parametrosFull, this.connection);			
			iObjLog.info("generarPdf:DESPUES:" + jasperFileName);
						
			
		} catch (JRException jexp) {
			iObjLog.error("Exception Jasper generarPdf", jexp);
			throw jexp;			
		}catch (Exception aError){
			iObjLog.error("generarPdf:ERROR", aError);
			throw aError;
		}
		iObjLog.debug("generarPdf:SALIENDO:RutaSalida=" + this.httpPath);
		return this.httpPath;
	}

	
	public String generarExcel(String jasperFileName, String pdfFileName, Map params, Map subreportes, Connection connection, RunData data) 
	throws Exception{
		iObjLog.debug("generarPdf:ENTRANDO:\n-Reporte=" + jasperFileName + "\n-parametros: " + params);
		try{
			this.jasperFileName = jasperFileName; 
			this.pdfFileName = pdfFileName;
			this.params = params;
			this.subreportes = subreportes;
			this.connection = connection;
			this.data = data;
			this.setRutasReportes();
			Map parametrosFull = new HashMap();
			if(params!=null) {
				parametrosFull.putAll(this.params);
			}
			iObjLog.info("generarPdf:ANTES: " + jasperFileName);
//			this.bytes = JasperRunManager.runReportToPdf(this.jasperFileNameFull, parametrosFull, this.connection);			
			JasperPrint jasperPrint=JasperFillManager.fillReport(this.jasperFileNameFull,  parametrosFull,this.connection);
			JRXlsExporter exporter = new JRXlsExporter ();
			exporter.setParameter (JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter (JRExporterParameter.OUTPUT_FILE_NAME, ConfiguracionProperties.getPropiedad("reporte.ruta.jasperpdfwrite") + this.pdfFileName);
			exporter.setParameter (JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
			exporter.exportReport ();			
			iObjLog.info("generarPdf:DESPUES:" + this.pdfFileName);
			iObjLog.info("generarPdf:DESPUES:" + this.httpPath);
			iObjLog.info("generarPdf:DESPUES:" + this.jasperFileNameFull);
		} catch (JRException jexp) {
			iObjLog.error("Exception Jasper generarPdf", jexp);
			throw jexp;			
		}catch (Exception aError){
			iObjLog.error("generarPdf:ERROR", aError);
			throw aError;
		}
		iObjLog.debug("generarPdf:SALIENDO:RutaSalida=" + this.httpPath);
		return this.httpPath;
	}
	
	/**
	 * Este metodo genera el archivo pdf con la ruta y  
	 * nombre que esta configurada en el archivo 
	 * <b>Configuracion.properties</b>. 
	 * Regresa la ruta http del archivo pdf que se 
	 * haya generado. 
	 * @param jasperFileName
	 * @param pdfFileName
	 * @param params
	 * @param subreportes
	 * @param connection
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean generarPdfStream(String jasperFileName, String pdfFileName, Map params, Map subreportes, JRDataSource jrDataSrc, RunData data) 
	throws Exception{
		boolean generado = false;
		iObjLog.debug("generarPdf:ENTRANDO");
		try{
			this.contentType = this.deafultContentType;
			this.htmlHeader = this.defaultHtmlHeader;
			this.jasperFileName = jasperFileName; 
			this.pdfFileName = pdfFileName;
			this.params = params;
			this.subreportes = subreportes;
			this.jrDataSrc = jrDataSrc;
			this.data = data;
			this.setRutasReportes();
			Map parametrosFull = new HashMap();
			parametrosFull.putAll(this.params);
			if(subreportes!=null){
				parametrosFull.putAll(this.subreportes);
			}
			this.bytes = JasperRunManager.runReportToPdf(this.jasperFileNameFull, parametrosFull, this.jrDataSrc);
			if(this.bytes!=null){
				generado = ReportStream.setReportStream(data, this.bytes, this.contentType, this.htmlHeader);
			}
		}catch (Exception aError){
			iObjLog.error("generarPdfStream:ERROR", aError);
			throw aError;
		}
		return generado;
	}
	
	/**
	 * Este metodo genera el archivo pdf con la ruta y  
	 * nombre que esta configurada en el archivo 
	 * <b>Configuracion.properties</b>. 
	 * Regresa la ruta http del archivo pdf que se 
	 * haya generado. 
	 * @param jasperFileName
	 * @param pdfFileName
	 * @param params
	 * @param subreportes
	 * @param jrDataSrc
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String generarPdf(String jasperFileName, String pdfFileName, Map params, Map subreportes, JRDataSource jrDataSrc, RunData data) 
	throws Exception{
		iObjLog.debug("generarPdf:ENTRANDO");
		try{
			this.jasperFileName = jasperFileName; 
			this.pdfFileName = pdfFileName;
			this.params = params;
			this.subreportes = subreportes;
			this.jrDataSrc = jrDataSrc;
			this.data = data;
			this.setRutasReportes();
			Map parametrosFull = new HashMap();
			parametrosFull.putAll(this.params);
			if(subreportes!=null){
				parametrosFull.putAll(this.subreportes);
			}
			JasperRunManager.runReportToPdfFile(this.jasperFileNameFull, this.pdfFileNameFull, parametrosFull, this.jrDataSrc);
		}catch (Exception aError){
			iObjLog.error("generarPdf:ERROR", aError);
			throw aError;
		}
		return this.httpPath;
	}
	
	/**
     * Metodo utilizado para completar las rutas de los reportes 
     * y subreportes, asi como para el nombre del pdf. 
     * @throws Exception
     */
    private void setRutasReportes() throws Exception{
    	iObjLog.debug("setRutasReportes:ENTRANDO");
    	try {
    		String sJasperPath = ConfiguracionProperties.getPropiedad("reporte.ruta.jasperread");
    		String sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.jasperpdfwrite");
    		String sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.jasperpdfread");
    		
//    		String sJasperPath = "C:/Reportes/jasper/";
//    		String sPdfPath = "C:/Reportes/write/";
//    		String sHttpPath = "";

    		iObjLog.debug("DATOS DEL PROPERTIES:" + 
    				"\nJasper Path =" + sJasperPath + 
    				"\nPdf Path =" + sPdfPath +
    				"\nhttp Path =" + sHttpPath);
    		
    		//si no se encuentran las rutas en el properties se obtiene la ruta por default 
    		if(sJasperPath==null)sJasperPath = this.getRutaDefault("jasperread");
    		if(sPdfPath==null)sPdfPath = this.getRutaDefault("pdfwrite");
    		if(sHttpPath==null)sHttpPath = this.getRutaDefault("pdfread");
    		
    		//se asignan los paths encontrados a las variables de instancia  
    		this.jasperFileNameFull = sJasperPath + this.jasperFileName;
    		this.pdfFileNameFull = sPdfPath + this.pdfFileName;
    		this.httpPath = sHttpPath + this.pdfFileName;
    		
    		if(subreportes!=null && !subreportes.isEmpty()){
    			Iterator itera = subreportes.keySet().iterator();
    			while(itera.hasNext()){
    				String key = (String)itera.next();
    				String valor = (String)subreportes.get(key);
    				String sNuevoValor = sJasperPath + valor;
    				iObjLog.debug("\nVerificando:" + key + 
    						"\nvalor inicial=" + valor + 
    						"\nvalor nuevo="+sNuevoValor);
    				subreportes.put(key, sNuevoValor);
    			}
    		}
    		iObjLog.debug(
    				"\nNombre PDF Principal =" + pdfFileNameFull + 
    				"\nnombre jasper principal =" + jasperFileNameFull +
    				"\nruta http pdf =" + this.httpPath + 
    				"\nsubreportes=" + subreportes);
		} catch (Exception aError) {
			iObjLog.error("setRutasReportes:ERROR", aError);
			throw aError;
		}
    }
    
    /**
     * Metodo que obtiene la ruta default para los reportes en 
     * caso de no encontrar dichas rutas en el archivo de configuracion
     * @return
     * @throws Exception
     */
    private String getRutaDefault(String tipo) throws Exception{
    	try {
    		String sRuta = "";
    		if(tipo.trim().equals("jasperread")){
    			sRuta = this.data.getServletContext().getRealPath("/reportes/jasper/")+ "/"; 
    		}else if(tipo.trim().equals("pdfwrite")){
    			sRuta = this.data.getServletContext().getRealPath("/reportes/generados/") + "/"; 
    		}else if(tipo.trim().equals("pdfread")){
    			sRuta = "http://" + data.getServerName() + ":" + 
					data.getServerPort() + data.getContextPath() + "/reportes/generados/"; 
    		}
    		iObjLog.debug("\nSe Regresa default para " + tipo + " valor : " + sRuta);
    		return sRuta; 
		} catch (Exception aError) {
			iObjLog.error("getRutaDefault:ERROR", aError);
			throw aError;
		}
    	
    }
    
}
