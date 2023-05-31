package mx.com.web2lab.ajax.dwr.facturacion.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import mx.com.web2lab.backend.beans.facturacion.DatosFiscalesBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.BodyFacturaElectronicaBean;
import mx.com.web2lab.backend.beans.facturacion.electronica.FacturaElectronicaBean;
import mx.com.web2lab.backend.dao.facturacion.tool.DatosFiscalesDao;
import mx.com.web2lab.backend.hbm.ConfiguracionProperties;
import mx.com.web2lab.util.UtilidadesGenerales;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FacturaElectronicaXML
{
  private static Log iObjLog = LogFactory.getLog(FacturaElectronicaXML.class);

  public FacturaElectronicaBean createCFDXML(FacturaElectronicaBean objfilexmlbean) throws JDOMException, Exception {
    BodyFacturaElectronicaBean objBody = null;
    DatosFiscalesDao objDatosFiscalesDao = new DatosFiscalesDao();
    DatosFiscalesBean objDatosFiscalesBean = null;
    try {
      iObjLog.debug("Entrando FacturaElectronicaXMLDao.createXML:... " + objfilexmlbean.toString());
      Element root = new Element("Comprobante");
      root.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      Namespace XSI = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
      root.addNamespaceDeclaration(XSI);
      root.setAttribute("schemaLocation", "http://www.sat.gob.mx/cfd/2 http://www.sat.gob.mx/sitio_internet/cfd/2/cfdv22.xsd", XSI);
      root.setAttribute("version", "2.2");
      root.setAttribute("uuid",objfilexmlbean.getUuid()+"");
      root.setAttribute("notaDescuento", objfilexmlbean.getNotaDescuento()+"");
      root.setAttribute("bandDescuento", String.valueOf(objfilexmlbean.isBandDescuento()));
      root.setAttribute("bandRetencion", String.valueOf(objfilexmlbean.isBandRetencion()));
      root.setAttribute("serie", objfilexmlbean.getSserie() + "");
      root.setAttribute("folio", objfilexmlbean.getSfolio() + "");
      root.setAttribute("fecha", objfilexmlbean.getFechaxml() + "");
      if ((objfilexmlbean.getCconvenio() == 850L) || (objfilexmlbean.getCconvenio() == 392L) || (objfilexmlbean.getCconvenio() == 948L))
        root.setAttribute("Moneda", "MXN");
      else {
        root.setAttribute("Moneda", "MXP");
      }
      root.setAttribute("LugarExpedicion", convertUTF8(objfilexmlbean.getSciudademisor()) + "");
      objDatosFiscalesBean = objDatosFiscalesDao.buscarDatosFiscalesConvenio(objfilexmlbean.getCconvenio());
    
	  if (objfilexmlbean.getcTipoPago() == 0) {
	      if (objDatosFiscalesBean != null) {
	          root.setAttribute("metodoDePago", objDatosFiscalesBean.getStipopago().trim() + "");
	          root.setAttribute("NumCtaPago", objDatosFiscalesBean.getSdigitoscuenta().trim() + "");
	      } else {
	          root.setAttribute("metodoDePago", "99 - Otros");
	      }    
	  } else {
		  root.setAttribute("metodoDePago", objfilexmlbean.getsTipoPago().trim() + "");
		  root.setAttribute("NumCtaPago", objfilexmlbean.getsUltimosDigitos().trim() + "");
	  }
	  
      root.setAttribute("noAprobacion", objfilexmlbean.getNnumeroaprobacion());
      root.setAttribute("anoAprobacion", objfilexmlbean.getSanoaprobacion() + "");
      root.setAttribute("formaDePago", objfilexmlbean.getSformapago() + "");
      root.setAttribute("subTotal", objfilexmlbean.getMsubtotal() + "");      
//      root.setAttribute("subTotal", objfilexmlbean.getMsubtotalsuma() + "");
      root.setAttribute("descuento", objfilexmlbean.getStrMdescuento() + "");
      root.setAttribute("total", objfilexmlbean.getStrMtotal() + "");
      root.setAttribute("tipoDeComprobante", objfilexmlbean.getStipocomprobante() + "");
      root.setAttribute("noCertificado", objfilexmlbean.getSncertificado() + "");
      if (objfilexmlbean.getCmarca() == 1) {
    	  root.setAttribute("certificado", objfilexmlbean.getCert() + "MIIExTCCA62gAwIBAgIUMDAwMDEwMDAwMDAyMDIyMzM1MDEwDQYJKoZIhvcNAQEF" + "BQAwggGVMTgwNgYDVQQDDC9BLkMuIGRlbCBTZXJ2aWNpbyBkZSBBZG1pbmlzdHJh" + "Y2nDs24gVHJpYnV0YXJpYTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3Ry" + "YWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBT" + "ZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSEwHwYJKoZIhvcNAQkBFhJhc2lz" + "bmV0QHNhdC5nb2IubXgxJjAkBgNVBAkMHUF2LiBIaWRhbGdvIDc3LCBDb2wuIEd1" + "ZXJyZXJvMQ4wDAYDVQQRDAUwNjMwMDELMAkGA1UEBhMCTVgxGTAXBgNVBAgMEERp" + "c3RyaXRvIEZlZGVyYWwxFDASBgNVBAcMC0N1YXVodMOpbW9jMRUwEwYDVQQtEwxT" + "QVQ5NzA3MDFOTjMxPjA8BgkqhkiG9w0BCQIML1Jlc3BvbnNhYmxlOiBDZWNpbGlh" + "IEd1aWxsZXJtaW5hIEdhcmPDrWEgR3VlcnJhMB4XDTEyMTAyOTE4MDkyOFoXDTE2" + "MTAyOTE4MDkyOFowggEFMTQwMgYDVQQDEytFU1RVRElPUyBDTElOSUNPUyBEUi4g" + "VC4gSi4gT1JJQVJEIFNBIERFIENWMTQwMgYDVQQpEytFU1RVRElPUyBDTElOSUNP" + "UyBEUi4gVC4gSi4gT1JJQVJEIFNBIERFIENWMTQwMgYDVQQKEytFU1RVRElPUyBD" + "TElOSUNPUyBEUi4gVC4gSi4gT1JJQVJEIFNBIERFIENWMSUwIwYDVQQtExxFQ0Q3" + "NDEwMjFRQTUgLyBTQUFFNTYwNjMwSTQ0MR4wHAYDVQQFExUgLyBTQUFFNTYwNjMw" + "SERGTkdEMDkxGjAYBgNVBAsTEVN1Y3Vyc2FsIFNhbnRhIEZlMIGfMA0GCSqGSIb3" + "DQEBAQUAA4GNADCBiQKBgQC2P1oy/CsAklLZoICFRnD91pbFABe5fSW38O+8x0Lp" + "GvaKx8lAPMXHupaUwfUMc0VobOMwMeC+VI5ul9y717lH4209KkQYnrgRvnjNK5/i" + "Iqraxo+WJTVp5zoUlOIrhTYGqWC5ey25KpTa0FsHkeWA9QablqgeJzfD+V3KD9gG" + "7QIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0B" + "AQUFAAOCAQEAlax/kE6FPzxTLM5HHQbxJXaIUrfGqFei7gM20eFKI0V74T2MkhDQ" + "w1ZWkixTofTb7fSZ9CZ9OzY4TFrodVDxbNBlCDSATQYdiTlX21qVBLULnh5rJjNV" + "F/6NrDBxJv0p2ZZ9e0OKfP9iU+bgt92fwitrN7MN4Cm4rp83EPJnwdhBYrynh22I" + "24D3IX9IsueywXCsNPRbfspmunjQmIoWszd1WhSl9Jle71yLzUKhdD/U4MrZRrRt" + "Kfd/U5WUopb3qQrPtrxFhxTn+3jfuTc9oK3njcrufmfMOZIfjz7i809dlktbJjWU" + "3PIzKEqHvxIed2ywh22tGi+kmXp8CQVHbQ==");
          root.setAttribute("sello", objfilexmlbean.getSsellodigital() + "");
      } else {
    	  root.setAttribute("certificado", "");
          root.setAttribute("sello", "");
      }

      Element emisor = new Element("Emisor");
      emisor.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      emisor.setAttribute("nombre", convertUTF8(objfilexmlbean.getSrazonsocialemisor()) + "");
      emisor.setAttribute("rfc", convertUTF8(objfilexmlbean.getSrfcemisor()) + "");

      Element regFis = new Element("RegimenFiscal");
      regFis.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      regFis.setAttribute("Regimen", "Regimen General de Ley Personas Morales");

      Element domFis = new Element("DomicilioFiscal");
      domFis.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      domFis.setAttribute("calle", convertUTF8(objfilexmlbean.getScalleemisor()) + "");
      domFis.setAttribute("colonia", convertUTF8(objfilexmlbean.getScoloniaemisor()) + "");
      domFis.setAttribute("estado", convertUTF8(objfilexmlbean.getSestadoemisor()) + "");
      domFis.setAttribute("localidad", convertUTF8(objfilexmlbean.getSciudademisor()) + "");
      domFis.setAttribute("municipio", convertUTF8(objfilexmlbean.getSmunicipioemisor()) + "");

      domFis.setAttribute("pais", convertUTF8(objfilexmlbean.getSpaisemisor()) + "");
      domFis.setAttribute("codigoPostal", objfilexmlbean.getScodigopostalemisor() + "");
      Element expEn = new Element("ExpedidoEn");
      expEn.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      expEn.setAttribute("calle", convertUTF8(objfilexmlbean.getScallesuc()) + "");
      expEn.setAttribute("colonia", convertUTF8(objfilexmlbean.getScoloniasuc()) + "");
      expEn.setAttribute("estado", convertUTF8(objfilexmlbean.getSestadosuc()) + "");

      expEn.setAttribute("localidad", convertUTF8(objfilexmlbean.getSestadosuc()) + "");
      expEn.setAttribute("municipio", convertUTF8(objfilexmlbean.getSmunicipiosuc()) + "");

      expEn.setAttribute("pais", convertUTF8(objfilexmlbean.getSpaissuc()) + "");
      expEn.setAttribute("codigoPostal", objfilexmlbean.getScodigopostalsuc() + "");

      Element receptor = new Element("Receptor");
      receptor.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      iObjLog.debug("Entrando FacturaElectronicaXMLDao.createXML:... Razon Social " + convertUTF8(objfilexmlbean.getSrazonsocialreceptor()));
      receptor.setAttribute("nombre", convertUTF8(objfilexmlbean.getSrazonsocialreceptor()));
      receptor.setAttribute("rfc", convertUTF8(objfilexmlbean.getSrfcreceptor()));
      Element domCl = new Element("Domicilio");
      domCl.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      domCl.setAttribute("calle", convertUTF8(objfilexmlbean.getScallereceptor()) + "");
      domCl.setAttribute("colonia", convertUTF8(objfilexmlbean.getScoloniareceptor()) + "");
      domCl.setAttribute("estado", convertUTF8(objfilexmlbean.getSestadoreceptor()) + "");
      domCl.setAttribute("localidad", convertUTF8(objfilexmlbean.getSciudadreceptor()) + "");
      domCl.setAttribute("municipio", convertUTF8(objfilexmlbean.getSmunicipioreceptor()) + "");

      domCl.setAttribute("pais", convertUTF8(objfilexmlbean.getSpaisreceptor()) + "");
      domCl.setAttribute("codigoPostal", objfilexmlbean.getScodigopostalreceptor() + "");

      Element imp = new Element("Impuestos");
      imp.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      Element tras = new Element("Traslados");
      tras.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      Element iva = new Element("Traslado");
      iva.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      iva.setAttribute("impuesto", "IVA");
      iva.setAttribute("tasa", "16");
      iva.setAttribute("importe", objfilexmlbean.getStrMiva());

      emisor.addContent(domFis);
      emisor.addContent(expEn);
      emisor.addContent(regFis);

      receptor.addContent(domCl);

      Element concep = new Element("Conceptos");
      concep.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      for (int inti = 0; inti < objfilexmlbean.sizeBodys(); inti++) {
        objBody = objfilexmlbean.getBody(inti);
        if (objBody.getIntCantidad() > 0) {

        	
          Element conceps = new Element("Concepto");
          conceps.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
          iObjLog.debug("Consulta Body FacturaElectronicaXMLDao.createXML:... " + objfilexmlbean.sizeBodys() + "\n");
          conceps.setAttribute("cantidad", String.valueOf(objBody.getIntCantidad()));
          conceps.setAttribute("descripcion", convertUTF8(objBody.getStrDescripcion()));
          conceps.setAttribute("importe", Double.toString(redodedoDouble2(objBody.getDblImporte())));

          conceps.setAttribute("unidad", "estudios");
          conceps.setAttribute("valorUnitario", Double.toString(redodedoDouble2(objBody.getDblValorUnitario())));
          
          conceps.setAttribute("StrCodigo", objBody.getStrCodigo());
          
          String descuento = "0.00";
          if(objfilexmlbean.isBandDescuento()){
	          String [] splitdesc = objfilexmlbean.getDescuentos().split("\\|");
	          for(int i = 0; i<splitdesc.length; i++){
	        	  String [] splitDescuento = splitdesc[i].split("\\=");
	        	  if(splitDescuento.length>0){
	        		  System.out.println("splitDescuento[0]:"+splitDescuento[0]);
	        		  if(splitDescuento[0].equals(objBody.getStrCodigo())){
	        			  descuento = splitDescuento[1];
	        		  }
	        	  }
	          }
          }
          conceps.setAttribute("descuento", descuento);
          
          concep.addContent(conceps);
        }
      }

      tras.addContent(iva);
      imp.addContent(tras);

      root.addContent(emisor);
      root.addContent(receptor);
      root.addContent(concep);
      root.addContent(imp);
      
     if (objfilexmlbean.getisAddenda()) { 
      
						  Element addenda = new Element("Addenda");
						
						  Element ifinterfactura = new Element("FacturaInterfactura", "if", "https://www.interfactura.com/Schemas/Documentos");
						
						  ifinterfactura.setAttribute("TipoDocumento", "Factura");
						  ifinterfactura.setAttribute("schemaLocation", "https://www.interfactura.com/Schemas/Documentos https://www.interfactura.com/Schemas/Documentos/DocumentoInterfactura.xsd");
						
						  Element emiAdd = new Element("Emisor", "if", "https://www.interfactura.com/Schemas/Documentos");
						  emiAdd.setAttribute("RI", "0117722");
						
						  Element recepAdd = new Element("Receptor", "if", "https://www.interfactura.com/Schemas/Documentos");
						  recepAdd.setAttribute("RI", "0103196");
						
						  Element encaAdd = new Element("Encabezado", "if", "https://www.interfactura.com/Schemas/Documentos");
						  encaAdd.setAttribute("Moneda", "MXN");
						  encaAdd.setAttribute("FolioOrdenCompra", objfilexmlbean.getiOrdenCompra() + "");
						  encaAdd.setAttribute("FolioNotaRecepcion",objfilexmlbean.getFolioOrdenCompra() + "");
						  encaAdd.setAttribute("Fecha", objfilexmlbean.getFechaxml() + "");
						  encaAdd.setAttribute("CondicionPago", "60");
						  encaAdd.setAttribute("CargoTipo", objfilexmlbean.getCargoTipo());//valores "" y "E"
						  encaAdd.setAttribute("IVAPCT", "16");
						  encaAdd.setAttribute("Iva", "0.0");
						  encaAdd.setAttribute("NumProveedor", "6124");
						  encaAdd.setAttribute("SubTotal", objfilexmlbean.getMsubtotalsuma() + "");
						  encaAdd.setAttribute("Total", objfilexmlbean.getStrMtotal() + "");
						  encaAdd.setAttribute("Observaciones", convertUTF8(objfilexmlbean.getSobservaciones()));
						
						  for (int inti = 0; inti < objfilexmlbean.sizeBodys(); inti++) {
						    objBody = objfilexmlbean.getBody(inti);
						    if (objBody.getIntCantidad() > 0)
						    {
						      Element cuerpoAdd = new Element("Cuerpo", "if", "https://www.interfactura.com/Schemas/Documentos");
						  iObjLog.debug("Consulta Body FacturaElectronicaXMLDao.createXMLAddenda:... " + objfilexmlbean.sizeBodys() + "\n");
						  objBody.getStrCodigo();
						  cuerpoAdd.setAttribute("Cantidad", String.valueOf(objBody.getIntCantidad()));
						  cuerpoAdd.setAttribute("Concepto", convertUTF8(objBody.getStrDescripcion()));
						  cuerpoAdd.setAttribute("PUnitario", Double.toString(redodedoDouble2(objBody.getDblValorUnitario())));
						  cuerpoAdd.setAttribute("Importe", Double.toString(redodedoDouble2(objBody.getDblImporte())));
						  cuerpoAdd.setAttribute("UnidadMedida", "PZA");
						  cuerpoAdd.setAttribute("Partida", UtilidadesGenerales.completaCadena(String.valueOf(inti + 1), '0', 5, "L"));
						
						      encaAdd.addContent(cuerpoAdd);
						    }
						  }
						  ifinterfactura.addContent(emiAdd);
						  ifinterfactura.addContent(recepAdd);
						  ifinterfactura.addContent(encaAdd);
						  addenda.addContent(ifinterfactura);
						  root.addContent(addenda);
      
    }      
      
      
      org.jdom.Document doc = new org.jdom.Document(root);
      XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
      objfilexmlbean.setSxml(out.outputString(doc));
      FileOutputStream file = null;
      if (objfilexmlbean.getCmarca() == 1) {      
    	  file = new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmlcfdlocalread_Olab") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      } else if(objfilexmlbean.getCmarca() == 5 || objfilexmlbean.getCmarca() == 15){
    	  file = new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmlcfdlocalread_Swisslab") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      } else if(objfilexmlbean.getCmarca() == 4) {
    	  file = new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmlcfdlocalread_Azteca") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      } else if(objfilexmlbean.getCmarca() == 19) {
    	  file = new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmlcfdlocalread_FamilyLabs") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      } else if(objfilexmlbean.getCmarca() == 20) {
    	  file = new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmlcfdlocalread_Exakta") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      
      } else if(objfilexmlbean.getCmarca() == 7) {
    	  if(objfilexmlbean.getSserie().equals("AJP")){
    		  file = new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmlcfdlocalread_JennerPrado") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
    	  }else if(objfilexmlbean.getSserie().equals("AJL")){
    		  file = new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmlcfdlocalread_JennerLean") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
    	  }
      }
      out.output(doc, file);
      file.flush();
      file.close(); 

      boolean no_exit = true;
      while (no_exit) {
    	  File archivo = null;
          if (objfilexmlbean.getCmarca() == 1) {      
        	  archivo = new File(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Olab") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
          } else if (objfilexmlbean.getCmarca() == 5 || objfilexmlbean.getCmarca() == 15){
        	  archivo = new File(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Swisslab") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
          }else if (objfilexmlbean.getCmarca() == 4){
        	  archivo = new File(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Azteca") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
          }else if (objfilexmlbean.getCmarca() == 19){
        	  archivo = new File(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_FamilyLabs") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
          }else if (objfilexmlbean.getCmarca() == 20){
        	  archivo = new File(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Exakta") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
          
          }else if (objfilexmlbean.getCmarca() == 7){
        	  if(objfilexmlbean.getSserie().equals("AJP")){
        		  archivo = new File(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_JennerPrado") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
        	  }else if(objfilexmlbean.getSserie().equals("AJL")){
        		  archivo = new File(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_JennerLean") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
        	  }
          }
        if (archivo.exists()) {
          no_exit = false;
          iObjLog.debug("Saliendo FacturaElectronicaXMLDao.createXML:... Ya existe el archivo");
        }
        archivo = null;
      }
      iObjLog.debug("Saliendo FacturaElectronicaXMLDao.createXML:... Ya existe el archivo");
      String strFileCFDI = "";
      String strFileCFDIAll = "";

      FileReader lector = null;
      if (objfilexmlbean.getCmarca() == 1) {      
    	  lector = new FileReader(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Olab") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      } else if(objfilexmlbean.getCmarca() == 5 || objfilexmlbean.getCmarca() == 15){
    	  lector = new FileReader(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Swisslab") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      }else if(objfilexmlbean.getCmarca() == 4){
    	  lector = new FileReader(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Azteca") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      }else if(objfilexmlbean.getCmarca() == 19){
    	  lector = new FileReader(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_FamilyLabs") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      }else if(objfilexmlbean.getCmarca() == 20){
    	  lector = new FileReader(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Exakta") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      
      } else if(objfilexmlbean.getCmarca() == 7){
    	  if(objfilexmlbean.getSserie().equals("AJP")){
    		  lector = new FileReader(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_JennerPrado") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
    	  }else if(objfilexmlbean.getSserie().equals("AJL")){
    		  lector = new FileReader(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_JennerLean") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
    	  }
      }
      BufferedReader contenido = new BufferedReader(lector);
      while ((strFileCFDI = contenido.readLine()) != null) {
        iObjLog.debug("Saliendo FacturaElectronicaXMLDao.createXML:... Lineas leidas..." + strFileCFDI);
        strFileCFDIAll = strFileCFDIAll + strFileCFDI;
      }
      lector.close();
      objfilexmlbean.setSxml(strFileCFDIAll);

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      org.w3c.dom.Document document = null;
      if (objfilexmlbean.getCmarca() == 1) {            
    	  document = builder.parse(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Olab") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      } else if(objfilexmlbean.getCmarca() == 5 || objfilexmlbean.getCmarca() == 15){
    	  document = builder.parse(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Swisslab") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      }	else if(objfilexmlbean.getCmarca() == 4){
    	  document = builder.parse(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Azteca") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      }	else if(objfilexmlbean.getCmarca() == 19){
    	  document = builder.parse(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_FamilyLabs") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      }	else if(objfilexmlbean.getCmarca() == 20){
    	  document = builder.parse(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Exakta") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      
      } else if(objfilexmlbean.getCmarca() == 7){
    	  if(objfilexmlbean.getSserie().equals("AJP")){
    		  document = builder.parse(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_JennerPrado") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
    	  }else if(objfilexmlbean.getSserie().equals("AJL")){
    		  document = builder.parse(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_JennerLean") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
    	  }
      }
      Node root1 = document.getDocumentElement();
      NodeList childNodes = root1.getChildNodes();

      if (root1.getAttributes() != null) {
        for (int z = 0; z < root1.getAttributes().getLength(); z++) {
          iObjLog.debug("Consulta FacturaElectronicaXMLDao.createXML:... Detalle..." + root1.getAttributes().item(z).getNodeName() + ": " + root1.getAttributes().item(z).getNodeValue());

          if (root1.getAttributes().item(z).getNodeName().trim().toString() == "certificado")
            objfilexmlbean.setCert(root1.getAttributes().item(z).getNodeValue());
          else if (root1.getAttributes().item(z).getNodeName().trim().toString() == "sello") {
            objfilexmlbean.setSsellodigital(root1.getAttributes().item(z).getNodeValue());
          }
        }
      }

      for (int i = 0; i < childNodes.getLength(); i++) {
        Node currentNode = childNodes.item(i);
        if (currentNode.getChildNodes().getLength() > 0) {
          for (int y = 0; y < currentNode.getChildNodes().getLength(); y++) {
            Node currentNodeHijo = currentNode.getChildNodes().item(y);
            if (currentNodeHijo.getAttributes() != null) {
              for (int z = 0; z < currentNodeHijo.getAttributes().getLength(); z++) {
                iObjLog.debug("Consulta FacturaElectronicaXMLDao.createXML:... Detalle..." + currentNodeHijo.getAttributes().item(z).getNodeName() + ": " + currentNodeHijo.getAttributes().item(z).getNodeValue());

                if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "FechaTimbrado")
                  objfilexmlbean.setCFDIFechaTimbrado(currentNodeHijo.getAttributes().item(z).getNodeValue());
                else if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "UUID")
                  objfilexmlbean.setCFDIUUID(currentNodeHijo.getAttributes().item(z).getNodeValue());
                else if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "noCertificadoSAT")
                  objfilexmlbean.setCFDInoCertificadoSAT(currentNodeHijo.getAttributes().item(z).getNodeValue());
                else if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "selloCFD")
                  objfilexmlbean.setCFDIselloCFD(currentNodeHijo.getAttributes().item(z).getNodeValue());
                else if (currentNodeHijo.getAttributes().item(z).getNodeName().trim().toString() == "selloSAT") {
                  objfilexmlbean.setCFDIselloSAT(currentNodeHijo.getAttributes().item(z).getNodeValue());
                }
              }
            }
          }
        }

      }

      iObjLog.debug("Saliendo FacturaElectronicaXMLDao.createXML:... " + objfilexmlbean.getSxml());
    } catch (Exception aObjExcepcion) {
      objfilexmlbean.setSxml("ERROR");
      iObjLog.error("ERROR FacturaElectronicaXMLDao.createXML: ", aObjExcepcion);
      throw aObjExcepcion;
    } finally {
        objDatosFiscalesDao = null;
        objDatosFiscalesBean = null;
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
      Element root = new Element("Comprobante");
      root.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      Namespace XSI = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
      root.addNamespaceDeclaration(XSI);
      root.setAttribute("schemaLocation", "http://www.sat.gob.mx/cfd/2 http://www.sat.gob.mx/sitio_internet/cfd/2/cfdv22.xsd", XSI);
      root.setAttribute("version", "2.2");
      root.setAttribute("serie", objfilexmlbean.getSserie() + "");
      root.setAttribute("folio", objfilexmlbean.getSfolio() + "");
      root.setAttribute("fecha", objfilexmlbean.getFechaxml() + "");
      root.setAttribute("Moneda", "MXP");
      root.setAttribute("LugarExpedicion", convertUTF8(objfilexmlbean.getSciudademisor()) + "");
      boolean bolMetodoPago = true;
      DatosFiscalesDao objDatosFiscalesDao = new DatosFiscalesDao();
      DatosFiscalesBean objDatosFiscalesBean = objDatosFiscalesDao.buscarDatosFiscalesConvenio(objfilexmlbean.getCconvenio());
      iObjLog.debug("Entrando FacturaElectronicaXMLDao.createXML:... BY-----objDatosFiscalesBean" + objDatosFiscalesBean.getSdigitoscuenta());
      if ((objDatosFiscalesBean != null) && 
        (objDatosFiscalesBean.getSdigitoscuenta().trim().length() > 3)) {
        root.setAttribute("metodoDePago", objDatosFiscalesBean.getStipopago().trim() + "");
        root.setAttribute("NumCtaPago", objDatosFiscalesBean.getSdigitoscuenta().trim() + "");
        bolMetodoPago = false;
      }

      objDatosFiscalesDao = null;
      objDatosFiscalesBean = null;
      if (bolMetodoPago)
      {
        if (objfilexmlbean.getcTipoPago() == 0)
          root.setAttribute("metodoDePago", "99 - Otros");
        else {
//          root.setAttribute("metodoDePago", objfilexmlbean.getsTipoPago() + "");
          root.setAttribute("metodoDePago", "99 - Otros");
        }
        if ((objfilexmlbean.getsUltimosDigitos() + "").trim().length() > 3) {
          root.setAttribute("NumCtaPago", objfilexmlbean.getsUltimosDigitos() + "");
        }
      }

      root.setAttribute("noAprobacion", objfilexmlbean.getNnumeroaprobacion());
      root.setAttribute("anoAprobacion", objfilexmlbean.getSanoaprobacion() + "");
      root.setAttribute("formaDePago", objfilexmlbean.getSformapago() + "");
      root.setAttribute("subTotal", objfilexmlbean.getMsubtotalsuma() + "");
      root.setAttribute("descuento", objfilexmlbean.getStrMdescuento() + "");
      root.setAttribute("total", objfilexmlbean.getStrMtotal() + "");
      root.setAttribute("tipoDeComprobante", objfilexmlbean.getStipocomprobante() + "");
      root.setAttribute("noCertificado", objfilexmlbean.getSncertificado() + "");

      iObjLog.debug("Entrando FacturaElectronicaXMLDao.createXML:... BY-----objfilexmlbean" + objfilexmlbean.getSncertificado());

      root.setAttribute("certificado", objfilexmlbean.getCert() + "MIIExTCCA62gAwIBAgIUMDAwMDEwMDAwMDAyMDIyMzM1MDEwDQYJKoZIhvcNAQEF" + "BQAwggGVMTgwNgYDVQQDDC9BLkMuIGRlbCBTZXJ2aWNpbyBkZSBBZG1pbmlzdHJh" + "Y2nDs24gVHJpYnV0YXJpYTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3Ry" + "YWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBT" + "ZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSEwHwYJKoZIhvcNAQkBFhJhc2lz" + "bmV0QHNhdC5nb2IubXgxJjAkBgNVBAkMHUF2LiBIaWRhbGdvIDc3LCBDb2wuIEd1" + "ZXJyZXJvMQ4wDAYDVQQRDAUwNjMwMDELMAkGA1UEBhMCTVgxGTAXBgNVBAgMEERp" + "c3RyaXRvIEZlZGVyYWwxFDASBgNVBAcMC0N1YXVodMOpbW9jMRUwEwYDVQQtEwxT" + "QVQ5NzA3MDFOTjMxPjA8BgkqhkiG9w0BCQIML1Jlc3BvbnNhYmxlOiBDZWNpbGlh" + "IEd1aWxsZXJtaW5hIEdhcmPDrWEgR3VlcnJhMB4XDTEyMTAyOTE4MDkyOFoXDTE2" + "MTAyOTE4MDkyOFowggEFMTQwMgYDVQQDEytFU1RVRElPUyBDTElOSUNPUyBEUi4g" + "VC4gSi4gT1JJQVJEIFNBIERFIENWMTQwMgYDVQQpEytFU1RVRElPUyBDTElOSUNP" + "UyBEUi4gVC4gSi4gT1JJQVJEIFNBIERFIENWMTQwMgYDVQQKEytFU1RVRElPUyBD" + "TElOSUNPUyBEUi4gVC4gSi4gT1JJQVJEIFNBIERFIENWMSUwIwYDVQQtExxFQ0Q3" + "NDEwMjFRQTUgLyBTQUFFNTYwNjMwSTQ0MR4wHAYDVQQFExUgLyBTQUFFNTYwNjMw" + "SERGTkdEMDkxGjAYBgNVBAsTEVN1Y3Vyc2FsIFNhbnRhIEZlMIGfMA0GCSqGSIb3" + "DQEBAQUAA4GNADCBiQKBgQC2P1oy/CsAklLZoICFRnD91pbFABe5fSW38O+8x0Lp" + "GvaKx8lAPMXHupaUwfUMc0VobOMwMeC+VI5ul9y717lH4209KkQYnrgRvnjNK5/i" + "Iqraxo+WJTVp5zoUlOIrhTYGqWC5ey25KpTa0FsHkeWA9QablqgeJzfD+V3KD9gG" + "7QIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0B" + "AQUFAAOCAQEAlax/kE6FPzxTLM5HHQbxJXaIUrfGqFei7gM20eFKI0V74T2MkhDQ" + "w1ZWkixTofTb7fSZ9CZ9OzY4TFrodVDxbNBlCDSATQYdiTlX21qVBLULnh5rJjNV" + "F/6NrDBxJv0p2ZZ9e0OKfP9iU+bgt92fwitrN7MN4Cm4rp83EPJnwdhBYrynh22I" + "24D3IX9IsueywXCsNPRbfspmunjQmIoWszd1WhSl9Jle71yLzUKhdD/U4MrZRrRt" + "Kfd/U5WUopb3qQrPtrxFhxTn+3jfuTc9oK3njcrufmfMOZIfjz7i809dlktbJjWU" + "3PIzKEqHvxIed2ywh22tGi+kmXp8CQVHbQ==");

      root.setAttribute("sello", objfilexmlbean.getSsellodigital() + "");

      Element emisor = new Element("Emisor");
      emisor.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      emisor.setAttribute("nombre", convertUTF8(objfilexmlbean.getSrazonsocialemisor()) + "");
      emisor.setAttribute("rfc", convertUTF8(objfilexmlbean.getSrfcemisor()) + "");

      Element regFis = new Element("RegimenFiscal");
      regFis.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      regFis.setAttribute("Regimen", "Regimen General de Ley Personas Morales");

      Element domFis = new Element("DomicilioFiscal");
      domFis.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      domFis.setAttribute("calle", convertUTF8(objfilexmlbean.getScalleemisor()) + "");
      domFis.setAttribute("colonia", convertUTF8(objfilexmlbean.getScoloniaemisor()) + "");
      domFis.setAttribute("estado", convertUTF8(objfilexmlbean.getSestadoemisor()) + "");
      domFis.setAttribute("localidad", convertUTF8(objfilexmlbean.getSciudademisor()) + "");
      domFis.setAttribute("municipio", convertUTF8(objfilexmlbean.getSmunicipioemisor()) + "");

      domFis.setAttribute("pais", convertUTF8(objfilexmlbean.getSpaisemisor()) + "");
      domFis.setAttribute("codigoPostal", objfilexmlbean.getScodigopostalemisor() + "");
      Element expEn = new Element("ExpedidoEn");
      expEn.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      expEn.setAttribute("calle", convertUTF8(objfilexmlbean.getScallesuc()) + "");
      expEn.setAttribute("colonia", convertUTF8(objfilexmlbean.getScoloniasuc()) + "");
      expEn.setAttribute("estado", convertUTF8(objfilexmlbean.getSestadosuc()) + "");

      expEn.setAttribute("localidad", convertUTF8(objfilexmlbean.getSestadosuc()) + "");
      expEn.setAttribute("municipio", convertUTF8(objfilexmlbean.getSmunicipiosuc()) + "");

      expEn.setAttribute("pais", convertUTF8(objfilexmlbean.getSpaissuc()) + "");
      expEn.setAttribute("codigoPostal", objfilexmlbean.getScodigopostalsuc() + "");

      Element receptor = new Element("Receptor");
      receptor.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      iObjLog.debug("Entrando FacturaElectronicaXMLDao.createXML:... Razon Social " + convertUTF8(objfilexmlbean.getSrazonsocialreceptor()));
      receptor.setAttribute("nombre", convertUTF8(objfilexmlbean.getSrazonsocialreceptor()));
      receptor.setAttribute("rfc", convertUTF8(objfilexmlbean.getSrfcreceptor()));
      Element domCl = new Element("Domicilio");
      domCl.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      domCl.setAttribute("calle", convertUTF8(objfilexmlbean.getScallereceptor()) + "");
      domCl.setAttribute("colonia", convertUTF8(objfilexmlbean.getScoloniareceptor()) + "");
      domCl.setAttribute("estado", convertUTF8(objfilexmlbean.getSestadoreceptor()) + "");
      domCl.setAttribute("localidad", convertUTF8(objfilexmlbean.getSciudadreceptor()) + "");
      domCl.setAttribute("municipio", convertUTF8(objfilexmlbean.getSmunicipioreceptor()) + "");

      domCl.setAttribute("pais", convertUTF8(objfilexmlbean.getSpaisreceptor()) + "");
      domCl.setAttribute("codigoPostal", objfilexmlbean.getScodigopostalreceptor() + "");

      Element imp = new Element("Impuestos");
      imp.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      Element tras = new Element("Traslados");
      tras.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      Element iva = new Element("Traslado");
      iva.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      iva.setAttribute("impuesto", "IVA");
      iva.setAttribute("tasa", "16");
      iva.setAttribute("importe", objfilexmlbean.getStrMiva());

      emisor.addContent(domFis);
      emisor.addContent(expEn);
      emisor.addContent(regFis);

      receptor.addContent(domCl);

      Element concep = new Element("Conceptos");
      concep.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
      for (int inti = 0; inti < objfilexmlbean.sizeBodys(); inti++) {
        objBody = objfilexmlbean.getBody(inti);
        if (objBody.getIntCantidad() > 0) {
          Element conceps = new Element("Concepto");
          conceps.setNamespace(Namespace.getNamespace("http://www.sat.gob.mx/cfd/2"));
          iObjLog.debug("Consulta Body FacturaElectronicaXMLDao.createXML:... " + objfilexmlbean.sizeBodys() + "\n");
          conceps.setAttribute("cantidad", String.valueOf(objBody.getIntCantidad()));
          conceps.setAttribute("descripcion", convertUTF8(objBody.getStrDescripcion()));
          conceps.setAttribute("importe", Double.toString(redodedoDouble2(objBody.getDblImporte())));

          conceps.setAttribute("unidad", "estudios");
          conceps.setAttribute("valorUnitario", Double.toString(redodedoDouble2(objBody.getDblValorUnitario())));
          concep.addContent(conceps);
        }
      }

      tras.addContent(iva);
      imp.addContent(tras);

//      Element addenda = new Element("Addenda");
//
//      Element ifinterfactura = new Element("FacturaInterfactura", "if", "https://www.interfactura.com/Schemas/Documentos");
//
//      ifinterfactura.setAttribute("TipoDocumento", "Factura");
//      ifinterfactura.setAttribute("schemaLocation", "https://www.interfactura.com/Schemas/Documentos https://www.interfactura.com/Schemas/Documentos/DocumentoInterfactura.xsd");
//
//      Element emiAdd = new Element("Emisor", "if", "https://www.interfactura.com/Schemas/Documentos");
//      emiAdd.setAttribute("RI", "0117722");
//
//      Element recepAdd = new Element("Receptor", "if", "https://www.interfactura.com/Schemas/Documentos");
//      recepAdd.setAttribute("RI", "0103196");
//
//      Element encaAdd = new Element("Encabezado", "if", "https://www.interfactura.com/Schemas/Documentos");
//      encaAdd.setAttribute("Moneda", "MXN");
//      encaAdd.setAttribute("FolioOrdenCompra", objfilexmlbean.getiOrdenCompra() + "");
//      encaAdd.setAttribute("FolioNotaRecepcion", "");
//      encaAdd.setAttribute("Fecha", objfilexmlbean.getFechaxml() + "");
//      encaAdd.setAttribute("CondicionPago", "60");
//      encaAdd.setAttribute("CargoTipo", "");
//      encaAdd.setAttribute("IVAPCT", "15");
//      encaAdd.setAttribute("Iva", objfilexmlbean.getStrMiva());
//      encaAdd.setAttribute("NumProveedor", "3384");
//      encaAdd.setAttribute("SubTotal", objfilexmlbean.getMsubtotalsuma() + "");
//      encaAdd.setAttribute("Total", objfilexmlbean.getStrMtotal() + "");
//      encaAdd.setAttribute("Observaciones", "");
//
//      for (int inti = 0; inti < objfilexmlbean.sizeBodys(); inti++) {
//        objBody = objfilexmlbean.getBody(inti);
//        if (objBody.getIntCantidad() > 0)
//        {
//          Element cuerpoAdd = new Element("Cuerpo", "if", "https://www.interfactura.com/Schemas/Documentos");
//          iObjLog.debug("Consulta Body FacturaElectronicaXMLDao.createXMLAddenda:... " + objfilexmlbean.sizeBodys() + "\n");
//          cuerpoAdd.setAttribute("Cantidad", String.valueOf(objBody.getIntCantidad()));
//          cuerpoAdd.setAttribute("Concepto", convertUTF8(objBody.getStrDescripcion()));
//          cuerpoAdd.setAttribute("PUnitario", Double.toString(redodedoDouble2(objBody.getDblValorUnitario())));
//          cuerpoAdd.setAttribute("Importe", Double.toString(redodedoDouble2(objBody.getDblImporte())));
//          cuerpoAdd.setAttribute("UnidadMedida", "UN");
//          cuerpoAdd.setAttribute("Partida", inti + 1 + "");
//
//          encaAdd.addContent(cuerpoAdd);
//        }
//      }
//      ifinterfactura.addContent(emiAdd);
//      ifinterfactura.addContent(recepAdd);
//      ifinterfactura.addContent(encaAdd);
//      addenda.addContent(ifinterfactura);
//
//      root.addContent(emisor);
//      root.addContent(receptor);
//      root.addContent(concep);
//      root.addContent(imp);
//      root.addContent(addenda);
      org.jdom.Document doc = new org.jdom.Document(root);
      XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
      objfilexmlbean.setSxml(out.outputString(doc));
      FileOutputStream file = null;
      if (objfilexmlbean.getCmarca() == 1) {
          file = new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Olab") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      } else if (objfilexmlbean.getCmarca() == 4) {
          file = new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Azteca") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      }	else if (objfilexmlbean.getCmarca() == 5) {
          file = new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Swisslab") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      }	else if (objfilexmlbean.getCmarca() == 19) {
          file = new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_FamilyLabs") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      }	else if (objfilexmlbean.getCmarca() == 20) {
          file = new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_Exakta") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
      
      } else if (objfilexmlbean.getCmarca() == 7) {
    	  if(objfilexmlbean.getSserie().equals("AJP")){
    		  file = new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_JennerPrado") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
    	  }else if(objfilexmlbean.getSserie().equals("AJL")){
    		  file = new FileOutputStream(ConfiguracionProperties.getPropiedad("reporte.ruta.xmllocalread_JennerLean") + "FacturacionElectronica_" + objfilexmlbean.getSseriofoliocompleto() + ".xml");
    	  }
	  }
      out.output(doc, file);
      file.flush();
      file.close();
      iObjLog.debug("Saliendo FacturaElectronicaXMLDao.createXML:... " + objfilexmlbean.getSxml());
    } catch (Exception aObjExcepcion) {
      objfilexmlbean.setSxml("");
      iObjLog.error("ERROR FacturaElectronicaXMLDao.createXML: ", aObjExcepcion);
      throw aObjExcepcion;
    }
    return objfilexmlbean;
  }

  private String convertUTF8(String strConvert)
  {
    String strChar = "";
    String strReturn = "";
    for (int inti = 0; inti < strConvert.length(); inti++) {
      strChar = strConvert.substring(inti, inti + 1);
      if (strChar.equals("ÃƒÆ’Ã‚Â±"))
        strChar = "&ntilde;";
      else if (strChar.equals("ÃƒÆ’Ã¢â‚¬Ëœ"))
        strChar = "&Ntilde;";
      else if (strChar.equals("&"))
        strChar = "&amp;";
      else if (strChar.equals("<")) {
        strChar = "&lt;";
      }
      else if (strChar.equals("Ãƒâ€šÃ‚Â°")) {
        strChar = "&deg;";
      }
      else if (strChar.equals("ÃƒÂ¢Ã‹â€ Ã¢â‚¬â€�")) {
        strChar = "&lowast;";
      }
      else if (strChar.equals("ÃƒÆ’Ã¯Â¿Â½"))
        strChar = "&Aacute;";
      else if (strChar.equals("ÃƒÆ’Ã¢â‚¬Â°"))
        strChar = "&Eacute;";
      else if (strChar.equals("ÃƒÆ’Ã¯Â¿Â½"))
        strChar = "&Iacute;";
      else if (strChar.equals("ÃƒÆ’Ã¢â‚¬Å“"))
        strChar = "&Oacute;";
      else if (strChar.equals("ÃƒÆ’Ã…Â¡"))
        strChar = "&Uacute;";
      else if (strChar.equals("ÃƒÆ’Ã‚Â¡"))
        strChar = "&aacute;";
      else if (strChar.equals("ÃƒÆ’Ã‚Â©"))
        strChar = "&eacute;";
      else if (strChar.equals("ÃƒÆ’Ã‚Â­"))
        strChar = "&iacute;";
      else if (strChar.equals("ÃƒÆ’Ã‚Â³"))
        strChar = "&oacute;";
      else if (strChar.equals("ÃƒÆ’Ã‚Âº")) {
        strChar = "&uacute;";
      }
      strReturn = strReturn + strChar;
    }
    return strReturn;
  }

  private double redodedoDouble2(double nD) {
    return Math.round(nD * Math.pow(10.0D, 2.0D)) / Math.pow(10.0D, 2.0D);
  }
}