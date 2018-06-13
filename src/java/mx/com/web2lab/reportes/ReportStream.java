package mx.com.web2lab.reportes;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.util.RunData;

public class ReportStream  implements Serializable{

	//** log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(ReportStream.class);

	/** Constructor privado default */
	private ReportStream(){
	}
	
	/**
	 * se encarga de poner el arreglo de bytes del reporte 
	 * en el request
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param reportName Por ejemplo: reporte.pdf
	 * @param reportBytes el arreglo de bytes del reporte 
	 * @param contentType Por ejemplo: 
	 * 					acrobar reader: "application/pdf", 
	 * 					xml: "application/xml",
	 * 					texto y html: "text/html", 
	 * 					imagenes: "image/jpeg"
	 * @param htmlHeader Por ejemplo: 
	 * 					para Download: "Content-Disposition", "inline;filename=reporte.pdf"  
	 * 					para abrir el archivo: "Content-Disposition", "attachment;filename=reporte.pdf"
	 * @throws Exception
	 */
	public static boolean setReportStream(
			RunData data, 
			byte[] reportBytes, 
			String contentType,
			String htmlHeader)
	throws Exception {
		boolean generado = false;
		try{
			iObjLog.debug("ContentType|Header|:"+contentType+"|"+htmlHeader);
			if(reportBytes!=null && reportBytes.length>0){
				if(contentType!=null && !contentType.trim().equals(""))
					data.getResponse().setContentType(contentType);
				if(htmlHeader!=null && !htmlHeader.trim().equals(""))
				data.getResponse().addHeader("Content-Disposition",htmlHeader);
				data.getResponse().setContentLength(reportBytes.length); 
				data.getResponse().getOutputStream().write(reportBytes); 
				data.getResponse().getOutputStream().flush(); 
				data.getResponse().getOutputStream().close();
				generado = true;
				iObjLog.debug("Se ha escrito el reporte al request");
			}else{
				iObjLog.debug("No hay datos para poner en el request");
			}
		}catch(Exception aError){
				iObjLog.error("Error en action:",aError);
		}
		return generado;
	}
	
}
