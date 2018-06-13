package mx.com.web2lab.ajax.dwr.facturacion.tool;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import mx.com.web2lab.backend.beans.facturacion.DatosFiscalesBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.BodyFacturaElectronicaBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
import mx.com.web2lab.backend.dao.facturacion.tool.DatosFiscalesDao;
import mx.com.web2lab.backend.hbm.ConfiguracionProperties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import timbrador.cofidi.wstimbrador.bean.ClienteWsTimbrador;


public class FacturaElectronicaBackup30042014XML {

	private static Log iObjLog = LogFactory.getLog(FacturaElectronicaBackup30042014XML.class);

	public FacturaElectronicaBean createCFDXML(FacturaElectronicaBean objfilexmlbean) throws JDOMException, Exception {
		BodyFacturaElectronicaBean objBody = null;
		try {        	    	
			iObjLog.debug("Entrando FacturaElectronicaXMLDao.createXML:... " + objfilexmlbean.toString());
			Element root=new Element("Comprobante");        
			root.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
			Namespace XSI = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
			root.addNamespaceDeclaration(XSI);
			root.setAttribute("schemaLocation", "http://www.sat.gob.mx/cfd/2 http://www.sat.gob.mx/sitio_internet/cfd/2/cfdv22.xsd", XSI);	        
			root.setAttribute("version", "2.2");	        
			root.setAttribute("serie",objfilexmlbean.getSserie() + "");
			root.setAttribute("folio",objfilexmlbean.getSfolio() + "");
			root.setAttribute("fecha",objfilexmlbean.getFechaxml() + "");
			if((objfilexmlbean.getCconvenio() == 850) ||((objfilexmlbean.getCconvenio() == 392)||(objfilexmlbean.getCconvenio() == 948))){
				root.setAttribute("Moneda","MXN");
			} else {
				root.setAttribute("Moneda","MXP");
			}
			root.setAttribute("LugarExpedicion",this.convertUTF8(objfilexmlbean.getSciudademisor()) + "");
			boolean  bolMetodoPago = true;
			DatosFiscalesDao objDatosFiscalesDao = new DatosFiscalesDao();
			DatosFiscalesBean objDatosFiscalesBean = objDatosFiscalesDao.buscarDatosFiscalesConvenio(objfilexmlbean.getCconvenio());
			if (objDatosFiscalesBean != null) {
				if (objDatosFiscalesBean.getSdigitoscuenta().trim().length() > 3) {
					root.setAttribute("metodoDePago",objDatosFiscalesBean.getStipopago().trim() + "");
					root.setAttribute("NumCtaPago",objDatosFiscalesBean.getSdigitoscuenta().trim() + "");	        		        		
					bolMetodoPago = false;
				}
			} 
			objDatosFiscalesDao = null;
			objDatosFiscalesBean = null;
			if (bolMetodoPago) {
			/******Validar estas opciones *******************/
			if (objfilexmlbean.getcTipoPago() == 0) {
				root.setAttribute("metodoDePago","99 - Otros");
			} else {
				root.setAttribute("metodoDePago",objfilexmlbean.getsTipoPago() + "");
			}
			if ((objfilexmlbean.getsUltimosDigitos() + "").trim().length() > 3) {	        
				root.setAttribute("NumCtaPago",objfilexmlbean.getsUltimosDigitos() + "");	        		        		
			}
			/******Validar estas opciones *******************/
			}			
			root.setAttribute("noAprobacion",objfilexmlbean.getNnumeroaprobacion());
			root.setAttribute("anoAprobacion",objfilexmlbean.getSanoaprobacion() + "");
			root.setAttribute("formaDePago",objfilexmlbean.getSformapago() + "");
			root.setAttribute("subTotal",objfilexmlbean.getMsubtotalsuma() + "");
			root.setAttribute("descuento",objfilexmlbean.getStrMdescuento() + "");
			root.setAttribute("total",objfilexmlbean.getStrMtotal() + "");
			root.setAttribute("tipoDeComprobante",objfilexmlbean.getStipocomprobante() + "");
			root.setAttribute("noCertificado",objfilexmlbean.getSncertificado() + "");
			root.setAttribute("certificado",objfilexmlbean.getCert() + 	"MIIExTCCA62gAwIBAgIUMDAwMDEwMDAwMDAyMDIyMzM1MDEwDQYJKoZIhvcNAQEF" +
																		"BQAwggGVMTgwNgYDVQQDDC9BLkMuIGRlbCBTZXJ2aWNpbyBkZSBBZG1pbmlzdHJh" +
																		"Y2nDs24gVHJpYnV0YXJpYTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3Ry" +
																		"YWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBT" +
																		"ZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSEwHwYJKoZIhvcNAQkBFhJhc2lz" +
																		"bmV0QHNhdC5nb2IubXgxJjAkBgNVBAkMHUF2LiBIaWRhbGdvIDc3LCBDb2wuIEd1" +
																		"ZXJyZXJvMQ4wDAYDVQQRDAUwNjMwMDELMAkGA1UEBhMCTVgxGTAXBgNVBAgMEERp" +
																		"c3RyaXRvIEZlZGVyYWwxFDASBgNVBAcMC0N1YXVodMOpbW9jMRUwEwYDVQQtEwxT" +
																		"QVQ5NzA3MDFOTjMxPjA8BgkqhkiG9w0BCQIML1Jlc3BvbnNhYmxlOiBDZWNpbGlh" +
																		"IEd1aWxsZXJtaW5hIEdhcmPDrWEgR3VlcnJhMB4XDTEyMTAyOTE4MDkyOFoXDTE2" +
																		"MTAyOTE4MDkyOFowggEFMTQwMgYDVQQDEytFU1RVRElPUyBDTElOSUNPUyBEUi4g" +
																		"VC4gSi4gT1JJQVJEIFNBIERFIENWMTQwMgYDVQQpEytFU1RVRElPUyBDTElOSUNP" +
																		"UyBEUi4gVC4gSi4gT1JJQVJEIFNBIERFIENWMTQwMgYDVQQKEytFU1RVRElPUyBD" +
																		"TElOSUNPUyBEUi4gVC4gSi4gT1JJQVJEIFNBIERFIENWMSUwIwYDVQQtExxFQ0Q3" +
																		"NDEwMjFRQTUgLyBTQUFFNTYwNjMwSTQ0MR4wHAYDVQQFExUgLyBTQUFFNTYwNjMw" +
																		"SERGTkdEMDkxGjAYBgNVBAsTEVN1Y3Vyc2FsIFNhbnRhIEZlMIGfMA0GCSqGSIb3" +
																		"DQEBAQUAA4GNADCBiQKBgQC2P1oy/CsAklLZoICFRnD91pbFABe5fSW38O+8x0Lp" +
																		"GvaKx8lAPMXHupaUwfUMc0VobOMwMeC+VI5ul9y717lH4209KkQYnrgRvnjNK5/i" +
																		"Iqraxo+WJTVp5zoUlOIrhTYGqWC5ey25KpTa0FsHkeWA9QablqgeJzfD+V3KD9gG" +
																		"7QIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0B" +
																		"AQUFAAOCAQEAlax/kE6FPzxTLM5HHQbxJXaIUrfGqFei7gM20eFKI0V74T2MkhDQ" +
																		"w1ZWkixTofTb7fSZ9CZ9OzY4TFrodVDxbNBlCDSATQYdiTlX21qVBLULnh5rJjNV" +
																		"F/6NrDBxJv0p2ZZ9e0OKfP9iU+bgt92fwitrN7MN4Cm4rp83EPJnwdhBYrynh22I" +
																		"24D3IX9IsueywXCsNPRbfspmunjQmIoWszd1WhSl9Jle71yLzUKhdD/U4MrZRrRt" +
																		"Kfd/U5WUopb3qQrPtrxFhxTn+3jfuTc9oK3njcrufmfMOZIfjz7i809dlktbJjWU" +
																		"3PIzKEqHvxIed2ywh22tGi+kmXp8CQVHbQ==");
			root.setAttribute("sello",objfilexmlbean.getSsellodigital() + "");
			// Nodo Emisor
			Element emisor=new Element("Emisor");
			emisor.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
			emisor.setAttribute("nombre",this.convertUTF8(objfilexmlbean.getSrazonsocialemisor()) + "");
			emisor.setAttribute("rfc", this.convertUTF8(objfilexmlbean.getSrfcemisor()) + "");        
			
			Element regFis=new Element("RegimenFiscal");
			regFis.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
			regFis.setAttribute("Regimen", "Regimen General de Ley Personas Morales");
			
			Element domFis=new Element("DomicilioFiscal");
			domFis.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
			domFis.setAttribute("calle", this.convertUTF8(objfilexmlbean.getScalleemisor()) + "");
			domFis.setAttribute("colonia",this.convertUTF8(objfilexmlbean.getScoloniaemisor()) + "");
			domFis.setAttribute("estado",this.convertUTF8(objfilexmlbean.getSestadoemisor()) + "");
			domFis.setAttribute("localidad",this.convertUTF8(objfilexmlbean.getSciudademisor()) + "");
			domFis.setAttribute("municipio",this.convertUTF8(objfilexmlbean.getSmunicipioemisor()) + "");
			//domFis.setAttribute("noExterior", objfilexmlbean.getSnexterioremisor());
			domFis.setAttribute("pais", this.convertUTF8(objfilexmlbean.getSpaisemisor()) + "");
			domFis.setAttribute("codigoPostal", objfilexmlbean.getScodigopostalemisor() + "");
			Element expEn = new Element("ExpedidoEn");
			expEn.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
			expEn.setAttribute("calle", this.convertUTF8(objfilexmlbean.getScallesuc()) + "");
			expEn.setAttribute("colonia", this.convertUTF8(objfilexmlbean.getScoloniasuc()) + "");
			expEn.setAttribute("estado", this.convertUTF8(objfilexmlbean.getSestadosuc()) + "");
			//		        expEn.setAttribute("localidad", objfilexmlbean.getSciudadsuc() + "");
			expEn.setAttribute("localidad", this.convertUTF8(objfilexmlbean.getSestadosuc()) + "");
			expEn.setAttribute("municipio", this.convertUTF8(objfilexmlbean.getSmunicipiosuc()) + "");
			//expEn.setAttribute("noExterior", objfilexmlbean.getSnexteriorsuc());
			expEn.setAttribute("pais", this.convertUTF8(objfilexmlbean.getSpaissuc()) + "");
			expEn.setAttribute("codigoPostal", objfilexmlbean.getScodigopostalsuc() + "");
			//Nodo Receptor
			Element receptor = new Element("Receptor");
			receptor.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
			iObjLog.debug("Entrando FacturaElectronicaXMLDao.createXML:... Razon Social " + this.convertUTF8(objfilexmlbean.getSrazonsocialreceptor()));
			receptor.setAttribute("nombre", this.convertUTF8(objfilexmlbean.getSrazonsocialreceptor()));
			receptor.setAttribute("rfc", this.convertUTF8(objfilexmlbean.getSrfcreceptor()));
			Element domCl = new Element ("Domicilio");
			domCl.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
			domCl.setAttribute("calle",this.convertUTF8(objfilexmlbean.getScallereceptor()) + "");
			domCl.setAttribute("colonia",this.convertUTF8(objfilexmlbean.getScoloniareceptor()) + "");
			domCl.setAttribute("estado",this.convertUTF8(objfilexmlbean.getSestadoreceptor()) + "");
			domCl.setAttribute("localidad",this.convertUTF8(objfilexmlbean.getSciudadreceptor()) + "");
			domCl.setAttribute("municipio",this.convertUTF8(objfilexmlbean.getSmunicipioreceptor()) + "");
			//domCl.setAttribute("noExterior",objfilexmlbean.getSnexteriorreceptor());
			domCl.setAttribute("pais",this.convertUTF8(objfilexmlbean.getSpaisreceptor()) + "");
			domCl.setAttribute("codigoPostal",objfilexmlbean.getScodigopostalreceptor() + "");       
			//Nodo Impuestos
			Element imp = new Element ("Impuestos");
			imp.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));        
			Element tras = new Element ("Traslados");
			tras.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));        
			Element iva = new Element ("Traslado");
			iva.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
			iva.setAttribute("impuesto", "IVA");
			iva.setAttribute("tasa", "16");
			iva.setAttribute("importe", objfilexmlbean.getStrMiva());
			//Agregan Los Contenidos del Emisor
			emisor.addContent(domFis);
			emisor.addContent(expEn);
			emisor.addContent(regFis);
			//Agrega los Contenidos del Receptor
			receptor.addContent(domCl);
			//Agrega los Contenidos de los Conceptos
			//Nodos Conceptos
			Element concep = new Element ("Conceptos");
			concep.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));        
			for(int inti = 0;inti < objfilexmlbean.sizeBodys();inti++) {		
				objBody = objfilexmlbean.getBody(inti);
				if (objBody.getIntCantidad() > 0) {
					Element conceps = new Element ("Concepto");
					conceps.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));                
					iObjLog.debug("Consulta Body FacturaElectronicaXMLDao.createXML:... " + objfilexmlbean.sizeBodys() +"\n");
					conceps.setAttribute("cantidad",String.valueOf(objBody.getIntCantidad()));
					conceps.setAttribute("descripcion",this.convertUTF8(objBody.getStrDescripcion()));
					conceps.setAttribute("importe", Double.toString(this.redodedoDouble2(objBody.getDblImporte())));
					//			        	conceps.setAttribute("unidad",objBody.getStrUnidad());
					//			        	conceps.setAttribute("unidad","no aplica");
					conceps.setAttribute("unidad","estudios");
					conceps.setAttribute("valorUnitario", Double.toString(this.redodedoDouble2(objBody.getDblValorUnitario())));
					concep.addContent(conceps);
				}
			}        	
			//Agrega los Contenidos de Impuestos
			tras.addContent(iva);
			imp.addContent(tras);
			//Agrega los Nodos al Nodo Comprobante
			root.addContent(emisor);
			root.addContent(receptor);
			root.addContent(concep);
			root.addContent(imp);
			Document doc = new Document(root);	        
			XMLOutputter out=new XMLOutputter(Format.getPrettyFormat());
			objfilexmlbean.setSxml(out.outputString(doc));
			FileOutputStream file = null;
			if (objfilexmlbean.getCmarca() == 1) {
				file=new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmlcfdlocalread_Olab") + "FacturacionElectronica_" +  objfilexmlbean.getSseriofoliocompleto() + ".xml");
			} else if (objfilexmlbean.getCmarca() == 4) {
				file=new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmlcfdlocalread_Azteca") + "FacturacionElectronica_" +  objfilexmlbean.getSseriofoliocompleto() + ".xml");
			} else if (objfilexmlbean.getCmarca() == 5) {
				file=new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmlcfdlocalread_SwissLab") + "FacturacionElectronica_" +  objfilexmlbean.getSseriofoliocompleto() + ".xml");
			}
				
			out.output(doc,file);
			file.flush();
			file.close();	        
			
			try {
				//instancia de la clase que hara el timbrado
				ClienteWsTimbrador clienteWsTimbrador=new ClienteWsTimbrador();
				//se invoca al metodo para leer los parametros del archivo de configuracion
				clienteWsTimbrador.setUrl("http://test.timbrado.com.mx/wsTimbrador/wsTimbrador.asmx");
				//se settea el usuario
				clienteWsTimbrador.setUser("OLAB");
				//se settea el password
				clienteWsTimbrador.setPassword("123456789");			
				//Convierto el xml leido desde archivo en un string
				String xmlStr= out.outputString(doc);
				//Se invoca al metodo para leer los parametros en el xml el cual se le pasa como parametro en forma de string
				clienteWsTimbrador.loadParamsFromXml(xmlStr);
				//invoco al metodo para realizar el proceso del timbrado
				String result = clienteWsTimbrador.invocarWebservice();
				//Imprimo por consola la cadena resultante
				iObjLog.debug("Saliendo FacturaElectronicaXMLDao.createXML:...CFD " + objfilexmlbean.getSxml());
				iObjLog.debug("Saliendo FacturaElectronicaXMLDao.createXML:...CFDI " + result);
			} catch (Exception exp) {
				
			}
			
////			System.out.println("aqui esta el string resultante: " + result);
			javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();  
			javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();  
	        org.w3c.dom.Document document = null; //(org.w3c.dom.Document) builder.parse(null); 
          Node root1 = document.getDocumentElement();  
          NodeList childNodes = root1.getChildNodes();  
          Node currentNode;  
          Node currentNodeHijo;                        
          if (root1.getAttributes() != null) {  
              for (int z = 0; z < root1.getAttributes().getLength(); z++) {  
      			iObjLog.debug("Consulta FacturaElectronicaXMLDao.createXML:... Detalle..." + root1.getAttributes().item(z).getNodeName() + ": "  
                              + root1.getAttributes().item(z).getNodeValue());  
                  if (root1.getAttributes().item(z).getNodeName().trim().toString() == "certificado") {
                  	objfilexmlbean.setCert(root1.getAttributes().item(z).getNodeValue());
                  } else if (root1.getAttributes().item(z).getNodeName().trim().toString() == "sello") {
                  	objfilexmlbean.setSsellodigital(root1.getAttributes().item(z).getNodeValue());
                  }
              }
          }
          
          for (int i = 0; i < childNodes.getLength(); i++) {  
              currentNode = childNodes.item(i);    
              if (currentNode.getChildNodes().getLength() > 0){
                  for (int y = 0; y < currentNode.getChildNodes().getLength(); y++) {  
                      currentNodeHijo = currentNode.getChildNodes().item(y);  
                      if (currentNodeHijo.getAttributes() != null) {  
                          for (int z = 0; z < currentNodeHijo.getAttributes().getLength(); z++) {  
                  			iObjLog.debug("Consulta FacturaElectronicaXMLDao.createXML:... Detalle..." + currentNodeHijo.getAttributes().item(z).getNodeName() + ": "  
	                                        + currentNodeHijo.getAttributes().item(z).getNodeValue());  
	                                if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "FechaTimbrado") {
	                                	objfilexmlbean.setCFDIFechaTimbrado(currentNodeHijo.getAttributes().item(z).getNodeValue());
	                                } else if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "UUID") {
	                                	objfilexmlbean.setCFDIUUID(currentNodeHijo.getAttributes().item(z).getNodeValue());
	                                } else if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "noCertificadoSAT") {
	                                	objfilexmlbean.setCFDInoCertificadoSAT(currentNodeHijo.getAttributes().item(z).getNodeValue());
	                                } else if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "selloCFD") {
	                                	objfilexmlbean.setCFDIselloCFD(currentNodeHijo.getAttributes().item(z).getNodeValue());
	                                } else if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "selloSAT") {
	                                	objfilexmlbean.setCFDIselloSAT(currentNodeHijo.getAttributes().item(z).getNodeValue());
	                                }
                          }  
                      } 
                  }                	
              }  
          }  
			
			
//			/*************************Leemos el archivo de XML en el File System ****************************/
//			boolean no_exit = true;
//            while(no_exit){
//            	File archivo = new File(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread") + "FacturacionElectronica_" +  objfilexmlbean.getSseriofoliocompleto() + ".xml");
//            	if (archivo.exists()) {
//            		no_exit = false;
//        			iObjLog.debug("Saliendo FacturaElectronicaXMLDao.createXML:... Ya existe el archivo");
//            	} 
//            	archivo = null;
//            }			
//			iObjLog.debug("Saliendo FacturaElectronicaXMLDao.createXML:... Ya existe el archivo");
//            String strFileCFDI = "";
//            String strFileCFDIAll = "";
//			
//			FileReader lector=new FileReader(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread") + "FacturacionElectronica_" +  objfilexmlbean.getSseriofoliocompleto() + ".xml");
//			BufferedReader contenido=new BufferedReader(lector);
//			while((strFileCFDI=contenido.readLine())!=null) {
//    			iObjLog.debug("Saliendo FacturaElectronicaXMLDao.createXML:... Lineas leidas..." + strFileCFDI);
//				strFileCFDIAll = (strFileCFDIAll + strFileCFDI);
//			}
//			lector.close();						
//			objfilexmlbean.setSxml(strFileCFDIAll);	        
//
//			
//			javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();  
//			javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();  
//            org.w3c.dom.Document document = (org.w3c.dom.Document) builder.parse(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread") + "FacturacionElectronica_" +  objfilexmlbean.getSseriofoliocompleto() + ".xml");  
//            Node root1 = document.getDocumentElement();  
//            NodeList childNodes = root1.getChildNodes();  
//            Node currentNode;  
//            Node currentNodeHijo;                        
//            if (root1.getAttributes() != null) {  
//                for (int z = 0; z < root1.getAttributes().getLength(); z++) {  
//        			iObjLog.debug("Consulta FacturaElectronicaXMLDao.createXML:... Detalle..." + root1.getAttributes().item(z).getNodeName() + ": "  
//                                + root1.getAttributes().item(z).getNodeValue());  
//                    if (root1.getAttributes().item(z).getNodeName().trim().toString() == "certificado") {
//                    	objfilexmlbean.setCert(root1.getAttributes().item(z).getNodeValue());
//                    } else if (root1.getAttributes().item(z).getNodeName().trim().toString() == "sello") {
//                    	objfilexmlbean.setSsellodigital(root1.getAttributes().item(z).getNodeValue());
//                    }
//                }
//            }
//            
//            for (int i = 0; i < childNodes.getLength(); i++) {  
//                currentNode = childNodes.item(i);    
//                if (currentNode.getChildNodes().getLength() > 0){
//                    for (int y = 0; y < currentNode.getChildNodes().getLength(); y++) {  
//                        currentNodeHijo = currentNode.getChildNodes().item(y);  
//                        if (currentNodeHijo.getAttributes() != null) {  
//                            for (int z = 0; z < currentNodeHijo.getAttributes().getLength(); z++) {  
//                    			iObjLog.debug("Consulta FacturaElectronicaXMLDao.createXML:... Detalle..." + currentNodeHijo.getAttributes().item(z).getNodeName() + ": "  
//	                                        + currentNodeHijo.getAttributes().item(z).getNodeValue());  
//	                                if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "FechaTimbrado") {
//	                                	objfilexmlbean.setCFDIFechaTimbrado(currentNodeHijo.getAttributes().item(z).getNodeValue());
//	                                } else if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "UUID") {
//	                                	objfilexmlbean.setCFDIUUID(currentNodeHijo.getAttributes().item(z).getNodeValue());
//	                                } else if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "noCertificadoSAT") {
//	                                	objfilexmlbean.setCFDInoCertificadoSAT(currentNodeHijo.getAttributes().item(z).getNodeValue());
//	                                } else if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "selloCFD") {
//	                                	objfilexmlbean.setCFDIselloCFD(currentNodeHijo.getAttributes().item(z).getNodeValue());
//	                                } else if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "selloSAT") {
//	                                	objfilexmlbean.setCFDIselloSAT(currentNodeHijo.getAttributes().item(z).getNodeValue());
//	                                }
//                            }  
//                        } 
//                    }                	
//                }  
//            }  

			
			iObjLog.debug("Saliendo FacturaElectronicaXMLDao.createXML:... " + objfilexmlbean.getSxml());
		} catch(Exception aObjExcepcion){
			objfilexmlbean.setSxml("ERROR");
			iObjLog.error("ERROR FacturaElectronicaXMLDao.createXML: ", aObjExcepcion);
			throw aObjExcepcion;
		}
		return objfilexmlbean;
	}
	
    /*
     * Buscar este metodo para MultiMarca
     * OMRR meter en el bean cMarca 28012016
     */
    public FacturaElectronicaBean createXmlAdendaEmpresa(FacturaElectronicaBean objfilexmlbean) throws JDOMException, Exception {
        BodyFacturaElectronicaBean objBody = null;
        try {        	    	
    		iObjLog.debug("Entrando FacturaElectronicaXMLDao.createXmlAdendaEmpresa:... BY" + objfilexmlbean.getMsubtotalsuma());
			Element root=new Element("Comprobante");        
	        root.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
	        Namespace XSI = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
	        root.addNamespaceDeclaration(XSI);
	        root.setAttribute("schemaLocation", "http://www.sat.gob.mx/cfd/2 http://www.sat.gob.mx/sitio_internet/cfd/2/cfdv22.xsd", XSI);	        
	        root.setAttribute("version", "2.2");	        
	        root.setAttribute("serie",objfilexmlbean.getSserie() + "");
	        root.setAttribute("folio",objfilexmlbean.getSfolio() + "");
	        root.setAttribute("fecha",objfilexmlbean.getFechaxml() + "");
	        root.setAttribute("Moneda","MXP");
	        root.setAttribute("LugarExpedicion",this.convertUTF8(objfilexmlbean.getSciudademisor()) + "");
	        boolean  bolMetodoPago = true;
	        DatosFiscalesDao objDatosFiscalesDao = new DatosFiscalesDao();
	        DatosFiscalesBean objDatosFiscalesBean = objDatosFiscalesDao.buscarDatosFiscalesConvenio(objfilexmlbean.getCconvenio());
	        iObjLog.debug("Entrando FacturaElectronicaXMLDao.createXML:... BY-----objDatosFiscalesBean"+objDatosFiscalesBean.getSdigitoscuenta());
	        if (objDatosFiscalesBean != null) {
	        	if (objDatosFiscalesBean.getSdigitoscuenta().trim().length() > 3) {
			        root.setAttribute("metodoDePago",objDatosFiscalesBean.getStipopago().trim() + "");
		        	root.setAttribute("NumCtaPago",objDatosFiscalesBean.getSdigitoscuenta().trim() + "");	        		        		
		        	bolMetodoPago = false;
	        	}
	        } 
	        objDatosFiscalesDao = null;
	        objDatosFiscalesBean = null;
	        if (bolMetodoPago) {
		        /******Validar estas opciones *******************/
		        if (objfilexmlbean.getcTipoPago() == 0) {
			        root.setAttribute("metodoDePago","99 - Otros");
		        } else {
			        root.setAttribute("metodoDePago",objfilexmlbean.getsTipoPago() + "");
		        }
		        if ((objfilexmlbean.getsUltimosDigitos() + "").trim().length() > 3) {	        
		        	root.setAttribute("NumCtaPago",objfilexmlbean.getsUltimosDigitos() + "");	        		        		
		        }
		        /******Validar estas opciones *******************/
	        }
	        root.setAttribute("noAprobacion",objfilexmlbean.getNnumeroaprobacion());
	        root.setAttribute("anoAprobacion",objfilexmlbean.getSanoaprobacion() + "");
	        root.setAttribute("formaDePago",objfilexmlbean.getSformapago() + "");
	        root.setAttribute("subTotal",objfilexmlbean.getMsubtotalsuma() + "");
	        root.setAttribute("descuento",objfilexmlbean.getStrMdescuento() + "");
	        root.setAttribute("total",objfilexmlbean.getStrMtotal() + "");
	        root.setAttribute("tipoDeComprobante",objfilexmlbean.getStipocomprobante() + "");
	        root.setAttribute("noCertificado",objfilexmlbean.getSncertificado() + "");
	        
	        iObjLog.debug("Entrando FacturaElectronicaXMLDao.createXML:... BY-----objfilexmlbean"+objfilexmlbean.getSncertificado());
//	        root.setAttribute("certificado",objfilexmlbean.getCert() + 	"MIIEWjCCA0KgAwIBAgIUMDAwMDEwMDAwMDAxMDIxMDE3MDEwDQYJKoZIhvcNAQEF" +
//																		"BQAwggE2MTgwNgYDVQQDDC9BLkMuIGRlbCBTZXJ2aWNpbyBkZSBBZG1pbmlzdHJh" +
//																		"Y2nDs24gVHJpYnV0YXJpYTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3Ry" +
//																		"YWNpw7NuIFRyaWJ1dGFyaWExHzAdBgkqhkiG9w0BCQEWEGFjb2RzQHNhdC5nb2Iu" +
//																		"bXgxJjAkBgNVBAkMHUF2LiBIaWRhbGdvIDc3LCBDb2wuIEd1ZXJyZXJvMQ4wDAYD" +
//																		"VQQRDAUwNjMwMDELMAkGA1UEBhMCTVgxGTAXBgNVBAgMEERpc3RyaXRvIEZlZGVy" +
//																		"YWwxEzARBgNVBAcMCkN1YXVodGVtb2MxMzAxBgkqhkiG9w0BCQIMJFJlc3BvbnNh" +
//																		"YmxlOiBGZXJuYW5kbyBNYXJ0w61uZXogQ29zczAeFw0xMDEwMjExNTE0NTFaFw0x" +
//																		"MjEwMjAxNTE0NTFaMIH6MTQwMgYDVQQDEytFU1RVRElPUyBDTElOSUNPUyBEUi4g" +
//																		"VC4gSi4gT1JJQVJEIFNBIERFIENWMTQwMgYDVQQpEytFU1RVRElPUyBDTElOSUNP" +
//																		"UyBEUi4gVC4gSi4gT1JJQVJEIFNBIERFIENWMTQwMgYDVQQKEytFU1RVRElPUyBD" +
//																		"TElOSUNPUyBEUi4gVC4gSi4gT1JJQVJEIFNBIERFIENWMSUwIwYDVQQtExxFQ0Q3" +
//																		"NDEwMjFRQTUgLyBTQUFFNTYwNjMwSTQ0MR4wHAYDVQQFExUgLyBTQUFFNTYwNjMw" +
//																		"SERGTkdEMDkxDzANBgNVBAsTBk1BVFJJWjCBnzANBgkqhkiG9w0BAQEFAAOBjQAw" +
//																		"gYkCgYEAvwa+4qCCNk/XDVQGypQAptvVm1jCsvDUBnkEHzIRLCyu6SW+i0SEGtD/" +
//																		"QRu/DQK1sXZOrFVNjZdeXlHt7fiatwDDaO05Awa20wnSnwaxAbo2pimSbzcJ+bPl" +
//																		"E3kc+p6rX32Ygd1z/kM9T+j8etDpc5djdK4KiDZu3fiEg/l+SjkCAwEAAaMdMBsw" +
//																		"DAYDVR0TAQH/BAIwADALBgNVHQ8EBAMCBsAwDQYJKoZIhvcNAQEFBQADggEBAGG+" +
//																		"ILk2nARxV7qpsz8qBK0NcTrjVZuV3qGDp1mVHxXJ12/7XQGhWtMoMDvOuCB+5VbC" +
//																		"ZUuXbwf+A/Rwb2W5CRLfdKyJSckQm1Ip0ouYko7idfPM4/5A2CCvL2+iVOjj0FIe" +
//																		"HewenH/mkbe/4sqz8mCi0Feb5V3eGC9JvwGyknBxUFnyjQKgeMh5MFTaf7/Y4XP+" +
//																		"TA4lq+ScDx462Cp+q4qm3sTydyahqBDfPvn3IdXBINftQJh9gBZpZjEjli/6D1ro" +
//																		"1tNOK+spgeS1/AqCuWoDFKt4iJ8hP/51fKp1kr3FntiGOgqksAt/zN12gdtSh7EB" +
//																		"DnDbcpbNsKNPG7J69ZU=");
	        root.setAttribute("certificado",objfilexmlbean.getCert() + 	"MIIExTCCA62gAwIBAgIUMDAwMDEwMDAwMDAyMDIyMzM1MDEwDQYJKoZIhvcNAQEF" +
																		"BQAwggGVMTgwNgYDVQQDDC9BLkMuIGRlbCBTZXJ2aWNpbyBkZSBBZG1pbmlzdHJh" +
																		"Y2nDs24gVHJpYnV0YXJpYTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3Ry" +
																		"YWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBT" +
																		"ZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSEwHwYJKoZIhvcNAQkBFhJhc2lz" +
																		"bmV0QHNhdC5nb2IubXgxJjAkBgNVBAkMHUF2LiBIaWRhbGdvIDc3LCBDb2wuIEd1" +
																		"ZXJyZXJvMQ4wDAYDVQQRDAUwNjMwMDELMAkGA1UEBhMCTVgxGTAXBgNVBAgMEERp" +
																		"c3RyaXRvIEZlZGVyYWwxFDASBgNVBAcMC0N1YXVodMOpbW9jMRUwEwYDVQQtEwxT" +
																		"QVQ5NzA3MDFOTjMxPjA8BgkqhkiG9w0BCQIML1Jlc3BvbnNhYmxlOiBDZWNpbGlh" +
																		"IEd1aWxsZXJtaW5hIEdhcmPDrWEgR3VlcnJhMB4XDTEyMTAyOTE4MDkyOFoXDTE2" +
																		"MTAyOTE4MDkyOFowggEFMTQwMgYDVQQDEytFU1RVRElPUyBDTElOSUNPUyBEUi4g" +
																		"VC4gSi4gT1JJQVJEIFNBIERFIENWMTQwMgYDVQQpEytFU1RVRElPUyBDTElOSUNP" +
																		"UyBEUi4gVC4gSi4gT1JJQVJEIFNBIERFIENWMTQwMgYDVQQKEytFU1RVRElPUyBD" +
																		"TElOSUNPUyBEUi4gVC4gSi4gT1JJQVJEIFNBIERFIENWMSUwIwYDVQQtExxFQ0Q3" +
																		"NDEwMjFRQTUgLyBTQUFFNTYwNjMwSTQ0MR4wHAYDVQQFExUgLyBTQUFFNTYwNjMw" +
																		"SERGTkdEMDkxGjAYBgNVBAsTEVN1Y3Vyc2FsIFNhbnRhIEZlMIGfMA0GCSqGSIb3" +
																		"DQEBAQUAA4GNADCBiQKBgQC2P1oy/CsAklLZoICFRnD91pbFABe5fSW38O+8x0Lp" +
																		"GvaKx8lAPMXHupaUwfUMc0VobOMwMeC+VI5ul9y717lH4209KkQYnrgRvnjNK5/i" +
																		"Iqraxo+WJTVp5zoUlOIrhTYGqWC5ey25KpTa0FsHkeWA9QablqgeJzfD+V3KD9gG" +
																		"7QIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0B" +
																		"AQUFAAOCAQEAlax/kE6FPzxTLM5HHQbxJXaIUrfGqFei7gM20eFKI0V74T2MkhDQ" +
																		"w1ZWkixTofTb7fSZ9CZ9OzY4TFrodVDxbNBlCDSATQYdiTlX21qVBLULnh5rJjNV" +
																		"F/6NrDBxJv0p2ZZ9e0OKfP9iU+bgt92fwitrN7MN4Cm4rp83EPJnwdhBYrynh22I" +
																		"24D3IX9IsueywXCsNPRbfspmunjQmIoWszd1WhSl9Jle71yLzUKhdD/U4MrZRrRt" +
																		"Kfd/U5WUopb3qQrPtrxFhxTn+3jfuTc9oK3njcrufmfMOZIfjz7i809dlktbJjWU" +
																		"3PIzKEqHvxIed2ywh22tGi+kmXp8CQVHbQ==");
	        root.setAttribute("sello",objfilexmlbean.getSsellodigital() + "");
	        // Nodo Emisor
	        Element emisor=new Element("Emisor");
	        emisor.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
	        emisor.setAttribute("nombre",this.convertUTF8(objfilexmlbean.getSrazonsocialemisor()) + "");
	        emisor.setAttribute("rfc", this.convertUTF8(objfilexmlbean.getSrfcemisor()) + "");        
	        
	        Element regFis=new Element("RegimenFiscal");
	        regFis.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
	        regFis.setAttribute("Regimen", "Regimen General de Ley Personas Morales");
	        
	        Element domFis=new Element("DomicilioFiscal");
	        domFis.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
	        domFis.setAttribute("calle", this.convertUTF8(objfilexmlbean.getScalleemisor()) + "");
	        domFis.setAttribute("colonia",this.convertUTF8(objfilexmlbean.getScoloniaemisor()) + "");
	        domFis.setAttribute("estado",this.convertUTF8(objfilexmlbean.getSestadoemisor()) + "");
	        domFis.setAttribute("localidad",this.convertUTF8(objfilexmlbean.getSciudademisor()) + "");
	        domFis.setAttribute("municipio",this.convertUTF8(objfilexmlbean.getSmunicipioemisor()) + "");
	        //domFis.setAttribute("noExterior", objfilexmlbean.getSnexterioremisor());
	        domFis.setAttribute("pais", this.convertUTF8(objfilexmlbean.getSpaisemisor()) + "");
	        domFis.setAttribute("codigoPostal", objfilexmlbean.getScodigopostalemisor() + "");
	        Element expEn = new Element("ExpedidoEn");
	        expEn.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
	        expEn.setAttribute("calle", this.convertUTF8(objfilexmlbean.getScallesuc()) + "");
	        expEn.setAttribute("colonia", this.convertUTF8(objfilexmlbean.getScoloniasuc()) + "");
	        expEn.setAttribute("estado", this.convertUTF8(objfilexmlbean.getSestadosuc()) + "");
//	        expEn.setAttribute("localidad", objfilexmlbean.getSciudadsuc() + "");
	        expEn.setAttribute("localidad", this.convertUTF8(objfilexmlbean.getSestadosuc()) + "");
	        expEn.setAttribute("municipio", this.convertUTF8(objfilexmlbean.getSmunicipiosuc()) + "");
	        //expEn.setAttribute("noExterior", objfilexmlbean.getSnexteriorsuc());
	        expEn.setAttribute("pais", this.convertUTF8(objfilexmlbean.getSpaissuc()) + "");
	        expEn.setAttribute("codigoPostal", objfilexmlbean.getScodigopostalsuc() + "");
	        //Nodo Receptor
	        Element receptor = new Element("Receptor");
	        receptor.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
    		iObjLog.debug("Entrando FacturaElectronicaXMLDao.createXML:... Razon Social " + this.convertUTF8(objfilexmlbean.getSrazonsocialreceptor()));
	        receptor.setAttribute("nombre", this.convertUTF8(objfilexmlbean.getSrazonsocialreceptor()));
	        receptor.setAttribute("rfc", this.convertUTF8(objfilexmlbean.getSrfcreceptor()));
	        Element domCl = new Element ("Domicilio");
	        domCl.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
	        domCl.setAttribute("calle",this.convertUTF8(objfilexmlbean.getScallereceptor()) + "");
	        domCl.setAttribute("colonia",this.convertUTF8(objfilexmlbean.getScoloniareceptor()) + "");
	        domCl.setAttribute("estado",this.convertUTF8(objfilexmlbean.getSestadoreceptor()) + "");
	        domCl.setAttribute("localidad",this.convertUTF8(objfilexmlbean.getSciudadreceptor()) + "");
	        domCl.setAttribute("municipio",this.convertUTF8(objfilexmlbean.getSmunicipioreceptor()) + "");
	        //domCl.setAttribute("noExterior",objfilexmlbean.getSnexteriorreceptor());
	        domCl.setAttribute("pais",this.convertUTF8(objfilexmlbean.getSpaisreceptor()) + "");
	        domCl.setAttribute("codigoPostal",objfilexmlbean.getScodigopostalreceptor() + "");       
	        //Nodo Impuestos
	        Element imp = new Element ("Impuestos");
	        imp.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));        
	        Element tras = new Element ("Traslados");
	        tras.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));        
	        Element iva = new Element ("Traslado");
	        iva.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
	        iva.setAttribute("impuesto", "IVA");
	        iva.setAttribute("tasa", "16");
	        iva.setAttribute("importe", objfilexmlbean.getStrMiva());
	        //Agregan Los Contenidos del Emisor
	        emisor.addContent(domFis);
	        emisor.addContent(expEn);
	        emisor.addContent(regFis);
	        //Agrega los Contenidos del Receptor
	        receptor.addContent(domCl);
	        //Agrega los Contenidos de los Conceptos
	        //Nodos Conceptos
	        Element concep = new Element ("Conceptos");
	        concep.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));        
			for(int inti = 0;inti < objfilexmlbean.sizeBodys();inti++) {		
				objBody = objfilexmlbean.getBody(inti);
				if (objBody.getIntCantidad() > 0) {
	        		Element conceps = new Element ("Concepto");
	                conceps.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));                
	        		iObjLog.debug("Consulta Body FacturaElectronicaXMLDao.createXML:... " + objfilexmlbean.sizeBodys() +"\n");
		        	conceps.setAttribute("cantidad",String.valueOf(objBody.getIntCantidad()));
		        	conceps.setAttribute("descripcion",this.convertUTF8(objBody.getStrDescripcion()));
		        	conceps.setAttribute("importe", Double.toString(this.redodedoDouble2(objBody.getDblImporte())));
//		        	conceps.setAttribute("unidad",objBody.getStrUnidad());
//		        	conceps.setAttribute("unidad","no aplica");
		        	conceps.setAttribute("unidad","estudios");
		        	conceps.setAttribute("valorUnitario", Double.toString(this.redodedoDouble2(objBody.getDblValorUnitario())));
		        	concep.addContent(conceps);
				}
	        }        	
	        //Agrega los Contenidos de Impuestos
	        tras.addContent(iva);
	        imp.addContent(tras);
	        //Agregar la addenda
	        Element addenda=new Element("Addenda");        
	        
	        //Element ifinterfactura = new Element ("if:FacturaInterfactura");
	        Element ifinterfactura = new Element ("FacturaInterfactura","if","https://www.interfactura.com/Schemas/Documentos");
	   
	        //ifinterfactura.setNamespace(Namespace.getNamespace("https://www.interfactura.com/Schemas/Documentos"));
	        ifinterfactura.setAttribute("TipoDocumento", "Factura");
	        ifinterfactura.setAttribute("schemaLocation", "https://www.interfactura.com/Schemas/Documentos https://www.interfactura.com/Schemas/Documentos/DocumentoInterfactura.xsd");
	       // Namespace XMLNS = Namespace.getNamespace("xmlns","https://www.interfactura.com/Schemas/Documentos");
	        //ifinterfactura.addNamespaceDeclaration(XMLNS);
	        
	        
	        //Element emiAdd=new Element("if:Emisor");        
	        Element emiAdd=new Element("Emisor","if","https://www.interfactura.com/Schemas/Documentos");
	        emiAdd.setAttribute("RI", "0117722");
	        
	        //Element recepAdd=new Element("if:Receptor");        
	        Element recepAdd=new Element("Receptor","if","https://www.interfactura.com/Schemas/Documentos");
	        recepAdd.setAttribute("RI","0103196");
	        
	        //Element encaAdd=new Element("if:Encabezado");
	        Element encaAdd=new Element("Encabezado","if","https://www.interfactura.com/Schemas/Documentos");
	        encaAdd.setAttribute("Moneda","MXN");
	        encaAdd.setAttribute("FolioOrdenCompra",objfilexmlbean.getiOrdenCompra() + "");
	        encaAdd.setAttribute("FolioNotaRecepcion","");
	        encaAdd.setAttribute("Fecha",objfilexmlbean.getFechaxml() + "");
	        encaAdd.setAttribute("CondicionPago","60");
	        encaAdd.setAttribute("CargoTipo","");
	        encaAdd.setAttribute("IVAPCT","15");
	        encaAdd.setAttribute("Iva",objfilexmlbean.getStrMiva());
	        encaAdd.setAttribute("NumProveedor","3384");
	        encaAdd.setAttribute("SubTotal",objfilexmlbean.getMsubtotalsuma() + "");
	        encaAdd.setAttribute("Total",objfilexmlbean.getStrMtotal() + "");
	        encaAdd.setAttribute("Observaciones","");
	       
	        for(int inti = 0;inti < objfilexmlbean.sizeBodys();inti++) {		
				objBody = objfilexmlbean.getBody(inti);
				if (objBody.getIntCantidad() > 0) {
	        		//Element cuerpoAdd = new Element ("if:Cuerpo");
					Element cuerpoAdd = new Element ("Cuerpo","if","https://www.interfactura.com/Schemas/Documentos");
	        		iObjLog.debug("Consulta Body FacturaElectronicaXMLDao.createXMLAddenda:... " + objfilexmlbean.sizeBodys() +"\n");
	        		cuerpoAdd.setAttribute("Cantidad",String.valueOf(objBody.getIntCantidad()));
	        		cuerpoAdd.setAttribute("Concepto",this.convertUTF8(objBody.getStrDescripcion()));
	        		cuerpoAdd.setAttribute("PUnitario", Double.toString(this.redodedoDouble2(objBody.getDblValorUnitario())));
	        		cuerpoAdd.setAttribute("Importe", Double.toString(this.redodedoDouble2(objBody.getDblImporte())));
	        		cuerpoAdd.setAttribute("UnidadMedida","UN");
	        		cuerpoAdd.setAttribute("Partida", inti+1 +"");
//		        	conceps.setAttribute("unidad",objBody.getStrUnidad());
//		        	conceps.setAttribute("unidad","no aplica");
	        		encaAdd.addContent(cuerpoAdd);
				}
	        }
	        ifinterfactura.addContent(emiAdd);
	        ifinterfactura.addContent(recepAdd);
	        ifinterfactura.addContent(encaAdd);
	        addenda.addContent(ifinterfactura);
	        //Agrega los Nodos al Nodo Comprobante
	        root.addContent(emisor);
	        root.addContent(receptor);
	        root.addContent(concep);
	        root.addContent(imp);
	        root.addContent(addenda);
	        Document doc = new Document(root);	        
	        XMLOutputter out=new XMLOutputter(Format.getPrettyFormat());
	        objfilexmlbean.setSxml(out.outputString(doc));	       
	        FileOutputStream file = null;
	        if (objfilexmlbean.getCmarca() == 1) {
	            file=new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Olab") + "FacturacionElectronica_" +  objfilexmlbean.getSseriofoliocompleto() + ".xml");
	        } else if (objfilexmlbean.getCmarca() == 4) {
	            file=new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Azteca") + "FacturacionElectronica_" +  objfilexmlbean.getSseriofoliocompleto() + ".xml");
	        } else if (objfilexmlbean.getCmarca() == 5) {
	            file=new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_SwissLab") + "FacturacionElectronica_" +  objfilexmlbean.getSseriofoliocompleto() + ".xml");
	        }
            out.output(doc,file);
            file.flush();
            file.close();	        
    		iObjLog.debug("Saliendo FacturaElectronicaXMLDao.createXML:... " + objfilexmlbean.getSxml());
        } catch(Exception aObjExcepcion){
	        objfilexmlbean.setSxml("");
			iObjLog.error("ERROR FacturaElectronicaXMLDao.createXML: ", aObjExcepcion);
			throw aObjExcepcion;
        }
        return objfilexmlbean;
    }
    
    
    private String convertUTF8(String strConvert) {
    	String strChar = "";
    	String strReturn = "";
    	for(int inti=0;inti<strConvert.length();inti++) {
    		strChar = strConvert.substring(inti,inti+1);
    		if (strChar.equals("Ã±")) {
    			strChar = "&ntilde;";
    		} else if (strChar.equals("Ã‘")) {
    			strChar = "&Ntilde;";
    		} else if (strChar.equals("&")) {
    			strChar = "&amp;";
    		} else if (strChar.equals("<")) {
    			strChar = "&lt;";
//    		} else if (strChar.equals("â€œ")) {
//    			strChar = "&quot;";
//    		} else if (strChar.equals("'")) {
//    			strChar = "&apos;";
    		} else if (strChar.equals("Â°")) {
    			strChar = "&deg;";
//    		} else if (strChar.equals("Â´")) {
//    			strChar = "&acute;";
    		} else if (strChar.equals("âˆ—")) {
    			strChar = "&lowast;";
//    		} else if (strChar.equals("â€²")) {
//    			strChar = "&prime;";
    		} else if (strChar.equals("Ã�")) {
    			strChar = "&Aacute;";
    		} else if (strChar.equals("Ã‰")) {
    			strChar = "&Eacute;";
    		} else if (strChar.equals("Ã�")) {
    			strChar = "&Iacute;";
    		} else if (strChar.equals("Ã“")) {
    			strChar = "&Oacute;";
    		} else if (strChar.equals("Ãš")) {
    			strChar = "&Uacute;";
    		} else if (strChar.equals("Ã¡")) {
    			strChar = "&aacute;";
    		} else if (strChar.equals("Ã©")) {
    			strChar = "&eacute;";
    		} else if (strChar.equals("Ã­")) {
    			strChar = "&iacute;";
    		} else if (strChar.equals("Ã³")) {
    			strChar = "&oacute;";
    		} else if (strChar.equals("Ãº")) {
    			strChar = "&uacute;";
    		}
    		strReturn += strChar;
    	}
	    return strReturn;
	}    
    
    private double redodedoDouble2(double nD) {
		return Math.round(nD*Math.pow(10,2))/Math.pow(10,2);      	
    }
    
}

