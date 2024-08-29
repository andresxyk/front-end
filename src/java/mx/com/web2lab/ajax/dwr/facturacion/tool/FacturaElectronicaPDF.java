package mx.com.web2lab.ajax.dwr.facturacion.tool;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
//import java.util.Calendar;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import mx.com.web2lab.backend.beans.comer.ClienteBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
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

public class FacturaElectronicaPDF {
    
	private static Log iObjLog = LogFactory.getLog(FacturaElectronicaPDF.class);	
    
    public String createDocument(FacturaElectronicaBean objFacturaBean) throws Exception {
    	String strReturn = "";
//        File baseDir = new File(".");
        File outDir = new File(".");
        String sPdfPath = "";
        String sHttpPath = "";
    	try {
            String strNomArchivo = "FacturacionElectronica_" +  objFacturaBean.getSseriofoliocompleto();   		
            iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + strNomArchivo);    	
            
            if (objFacturaBean.getCmarca() == 1) {                        
	    		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_Olab");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);
	    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_OLab");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);    	
            } else if (objFacturaBean.getCmarca() == 4) {
	    		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_Azteca");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);    	
	    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_Azteca");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);    	
            } else if (objFacturaBean.getCmarca() == 5 || objFacturaBean.getCmarca() == 15) {
	    		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_Swisslab");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);    	
	    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_Swisslab");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);    	
            } else if (objFacturaBean.getCmarca() == 19) {
	    		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_FamilyLabs");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);    	
	    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_FamilyLabs");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);    	
            }else if (objFacturaBean.getCmarca() == 20) {
		    		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_Exakta");
		     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);    	
		    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_Exakta");
		     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);    	
            }else if (objFacturaBean.getCmarca() == 21) {
	    		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_AsesoresSur");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);    	
	    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_AsesoresSur");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);    	
            
            }else if (objFacturaBean.getCmarca() == 16) {
	    		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_Moreira");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);    	
	    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_Moreira");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);    	
            
            }else if (objFacturaBean.getCmarca() == 22) {
	    		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_Polab");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);    	
	    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_Polab");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);    	
            
            }else if (objFacturaBean.getCmarca() == 25) {
	    		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_BiomedicaReferencia");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);    	
	    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_BiomedicaReferencia");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);    	
            
            }else if (objFacturaBean.getCmarca() == 26) {
	    		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_Promedic");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);    	
	    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_Promedic");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);    	
            
            } else if (objFacturaBean.getCmarca() == 7) {
            	if(objFacturaBean.getSserie().equals("AJP")){
            		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_JennerPrado");
    	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);    	
    	    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_JennerPrado");
    	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);  
            	}else if(objFacturaBean.getSserie().equals("AJL")){
            		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_JennerLean");
    	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);    	
    	    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_JennerLean");
    	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);  
            	}	
            }
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(sPdfPath + strNomArchivo + ".fo"));
            dos.writeBytes(objFacturaBean.getSfop());
            dos.close();
            outDir.mkdirs();
            File fofile = new File( sPdfPath + strNomArchivo + ".fo");
            File pdffile = new File( sPdfPath + strNomArchivo + ".pdf");            
     		iObjLog.debug("Consulta FacturaElectronicaPDF.createFactura..... " + sPdfPath + strNomArchivo + ".fo " + sPdfPath + strNomArchivo + ".pdf");    	
           // this.convertFO2PDF(fofile, pdffile);
            strReturn = sHttpPath + strNomArchivo + ".pdf";
            fofile.delete();
	    } catch(FileNotFoundException aError) {
	 		iObjLog.error("FileFOP::run:FileNotFoundException: ", aError);
	 		throw aError;
	    } catch(IOException aErrorIOException) {
	 		iObjLog.error("FileFOP::run:IOException: ", aErrorIOException);
	 		throw aErrorIOException;
	    } catch(Exception aErrorException) {
	 		iObjLog.error("FileFOP::run:Exception: ", aErrorException);
	 		throw aErrorException;
	    } finally {
	    	
	    }
	    
    	return strReturn;
    }

    public String createDocument(String sFOP,String strNomArchivo, int cmarca) throws Exception {
    	String strReturn = "";
        File outDir = new File(".");
        String sPdfPath = "";
        String sHttpPath = "";
    	try {

    		if (cmarca == 1) {
	    		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + strNomArchivo);    	
	    		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_Olab");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);    	
	    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_Olab");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);    	
    		} else if (cmarca == 4) {
	    		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + strNomArchivo);    	
	    		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_Azteca");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);    	
	    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_Azteca");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);    	
    		} else if (cmarca == 5) {
	    		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + strNomArchivo);    	
	    		sPdfPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfwrite_Swisslab");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sPdfPath);    	
	    		sHttpPath = ConfiguracionProperties.getPropiedad("reporte.ruta.pdfreadURL_Swisslab");
	     		iObjLog.debug("Entrando FacturaElectronicaPDF.createFactura..... " + sHttpPath);    	
    		}
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(sPdfPath + strNomArchivo + ".fo"));
            dos.writeBytes(sFOP);
            dos.close();
            outDir.mkdirs();
            File fofile = new File( sPdfPath + strNomArchivo + ".fo");
            File pdffile = new File( sPdfPath + strNomArchivo + ".pdf");            
     		iObjLog.debug("Consulta FacturaElectronicaPDF.createFactura..... " + sPdfPath + strNomArchivo + ".fo " + sPdfPath + strNomArchivo + ".pdf");    	
           // this.convertFO2PDF(fofile, pdffile);
            strReturn = sHttpPath + strNomArchivo + ".pdf";
            fofile.delete();
	    } catch(FileNotFoundException aError) {
	 		iObjLog.error("FileFOP::run:FileNotFoundException: ", aError);
	 		throw aError;
	    } catch(IOException aErrorIOException) {
	 		iObjLog.error("FileFOP::run:IOException: ", aErrorIOException);
	 		throw aErrorIOException;
	    } catch(Exception aErrorException) {
	 		iObjLog.error("FileFOP::run:Exception: ", aErrorException);
	 		throw aErrorException;
	    } finally {
	    	
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

        OutputStream out = null;
        iObjLog.debug("Entrando FacturaElectronicaPDFDao.convertFO2PDFModificacion..... ");    	
        try {
            iObjLog.debug("Entrando FacturaElectronicaPDFDao.convertFO2PDF..... ");    	
        	FopFactory fopFactory = FopFactory.newInstance();            
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
                iObjLog.debug("PageSequence " 
                        + (String.valueOf(pageSequenceResults.getID()).length() > 0 
                                ? pageSequenceResults.getID() : "<no id>") 
                        + " generated " + pageSequenceResults.getPageCount() + " pages.");
            }
        } catch (FOPException aErrorFOP) {
	 		iObjLog.error("FileFOP::convertFO2PDF:FOPException: ", aErrorFOP);
	 		throw aErrorFOP;
        } catch(FileNotFoundException aError) {
	 		iObjLog.error("FileFOP::convertFO2PDF:FileNotFoundException: ", aError);
	 		throw aError;
        } catch(IOException aErrorIOException) {
	 		iObjLog.error("FileFOP::convertFO2PDF:IOException: ", aErrorIOException);
	 		throw aErrorIOException;
        } catch (Exception exp) {
	 		iObjLog.error("FileFOP::convertFO2PDF:Exception: ", exp);
	 		throw exp;
        } finally {
        	if (out != null) {
        		out.close();
        	}
        }
    }    
}
