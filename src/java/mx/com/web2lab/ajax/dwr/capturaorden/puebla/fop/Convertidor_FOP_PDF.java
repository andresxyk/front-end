package mx.com.web2lab.ajax.dwr.capturaorden.puebla.fop;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import mx.com.web2lab.backend.hbm.ConfiguracionProperties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FormattingResults;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.apps.PageSequenceResults;

public class Convertidor_FOP_PDF {
	
	private static Log iObjLog = LogFactory.getLog(Convertidor_FOP_PDF.class);	
    
    public String createDocument(String strFileFop,String strNomArchivo) throws Exception {
    	String strReturn = "";
    	try {
    		String sPdfPath = ConfiguracionProperties.getPropiedad("reporte.formatoSSPuebla.guardar");      		
    		String sHttpPath = ConfiguracionProperties.getPropiedad("reporte.formatoSSPuebla.leer"); 
    		
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(sPdfPath + strNomArchivo + ".fo"));
            dos.writeBytes(strFileFop);
            dos.close();
            File baseDir = new File(".");
            File outDir = new File(".");
            outDir.mkdirs();
            File fofile = new File( sPdfPath + strNomArchivo + ".fo");
            File pdffile = new File( sPdfPath + strNomArchivo + ".pdf");            
            this.convertFO2PDF(fofile, pdffile);    		
            strReturn = sHttpPath + strNomArchivo + ".pdf";
	    } catch(FileNotFoundException aError) {
	 		//iObjLog.error("FileFOP::run:FileNotFoundException: ", aError);
	 		throw aError;
	    } catch(IOException aErrorIOException) {
	 		//iObjLog.error("FileFOP::run:IOException: ", aErrorIOException);
	 		throw aErrorIOException;
	    } catch(Exception aErrorException) {
	 		//iObjLog.error("FileFOP::run:Exception: ", aErrorException);
	 		throw aErrorException;
	    } 
    	return strReturn;
    }
    
   
    /**
     * Converts an FO file to a PDF file using FOP
     * @param fo the FO file
     * @param pdf the target PDF file
     * @throws IOException In case of an I/O problem
     * @throws FOPException In case of a FOP problem
     */
    public void convertFO2PDF(File fo, File pdf) throws IOException, FOPException, Exception {

    	FopFactory fopFactory = FopFactory.newInstance();
        
        OutputStream out = null;
        
        try {
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            // configure foUserAgent as desired
    
            // Setup output stream.  Note: Using BufferedOutputStream
            // for performance reasons (helpful with FileOutputStreams).
            out = new FileOutputStream(pdf);
            out = new BufferedOutputStream(out);

            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // Setup JAXP using identity transformer
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(); // identity transformer
            
            // Setup input stream
            Source src = new StreamSource(fo);

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());
            
            // Start XSLT transformation and FOP processing
            transformer.transform(src, res);
            
            // Result processing
            FormattingResults foResults = fop.getResults();
            java.util.List pageSequences = foResults.getPageSequences();
            for (java.util.Iterator it = pageSequences.iterator(); it.hasNext();) {
                PageSequenceResults pageSequenceResults = (PageSequenceResults)it.next();
                System.out.println("PageSequence " 
                        + (String.valueOf(pageSequenceResults.getID()).length() > 0 
                                ? pageSequenceResults.getID() : "<no id>") 
                        + " generated " + pageSequenceResults.getPageCount() + " pages.");
            }
            System.out.println("Generated " + foResults.getPageCount() + " pages in total.");
        } catch(FileNotFoundException aError) {
	 		//iObjLog.error("FileFOP::convertFO2PDF:FileNotFoundException: ", aError);
        	System.out.println("no existe"+aError);
	 		throw aError;
        } catch(IOException aErrorIOException) {
	 		//iObjLog.error("FileFOP::convertFO2PDF:IOException: ", aErrorIOException);
        	System.out.println("no "+aErrorIOException);
	 		throw aErrorIOException;
        } catch (Exception exp) {
	 		//iObjLog.error("FileFOP::convertFO2PDF:Exception: ", exp);
        	System.out.println("otro "+exp);
	 		throw exp;
        } finally {
            out.close();
        }
    }    

}
