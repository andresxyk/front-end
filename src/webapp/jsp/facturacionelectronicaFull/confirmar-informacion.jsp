<%@ page session="true" language="java" import="mx.com.web2lab.ajax.dwr.capturaorden.DatosOrdenAjax,mx.com.web2lab.backend.beans.ap.OrdenBean,mx.com.web2lab.util.Formatos,org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Olab</title>
		<link href="http://fonts.googleapis.com/css?family=Raleway:400,300,700" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronica2015/normalize.css" />
		<link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronica2015/styles.css" />
		<link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronica2015/index.css" />
		<link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronica2015/ns-default.css" />
		<link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronica2015/ns-style-other.css" />
		<link href="/web2labportal/css/facturacionelectronica2015/SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css">
		<link href="/web2labportal/css/facturacionelectronica2015/SpryAssets/SpryValidationPassword.css" rel="stylesheet" type="text/css">
		
		<script src="/web2labportal/javascript/facturacionelectronica2015/modernizr.custom.js"></script>
		<script src="/web2labportal/javascript/facturacionelectronica2015/snap.svg-min.js"></script>
        <script type="text/javascript" src="/web2labportal/javascript/facturacionelectronica2015/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="/web2labportal/javascript/facturacionelectronica2015/jquery.inputmask.bundle.js"></script>
        <script type="text/javascript" src="/web2labportal/javascript/facturacionelectronica2015/languages/jquery.validationEngine-es.js" charset="utf-8"></script>
        <script type="text/javascript" src="/web2labportal/javascript/facturacionelectronica2015/jquery.validationEngine.js" charset="utf-8"></script>
        <script src="/web2labportal/javascript/facturacionelectronica2015/facturacion.js"></script>

		<!--<script src="/web2labportal/javascript/facturacionelectronica2015/SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>-->

		<!--[if IE]>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		
		<!-- Other scripts -->
		<script src="/web2labportal/javascript/generales.js" type="text/javascript" ></script>
		<script src="/web2labportal/javascript/facturacionelectronica/pantallas/confirmar-informacion.js" type="text/javascript"></script>  
		<script src='/web2labportal/dwr/interface/OrdenFacturarAjax.js' type='text/javascript' ></script>
		<script src='/web2labportal/dwr/engine.js' type='text/javascript' ></script>
		<script src='/web2labportal/dwr/util.js'   type='text/javascript' ></script>		
	</head>
	<body>
    <% 
		Log iObjLog = LogFactory.getLog("confirmar-informacion.jsp");	
		DatosOrdenAjax objDatosOrdenAjax = new DatosOrdenAjax();
		Formatos objFormatos = new Formatos();
		String skOrdenSucursal = request.getParameter("kordensucursalfinal").trim(); 
		String sEmail = request.getParameter("emailfinal").trim(); 
		String sPassword = request.getParameter("passwordfinal").trim();
		String sSucursal = "";
		String sPaciente = "";
		int kDatoFiscal = Integer.parseInt(request.getParameter("datofiscalfinal").trim());
		int cSucursal = 0;
		double dblTotalPagar = 0.0;
		String[] sPrint = {"",""};
		String strRazonSocial = "";
		String strRFC = "";
		String strCalle = "";
		String strColonia = "";
		String strCiudad = "";
		String strMunicipio = "";
		String strEstado = "";
		String strCodigoPostal = "";
		String strMsubTotal = "";
		String strMIVA = "";
		String strMpagaPaciente = "";
		String strMacuenta = "";
		String strMadeuda = "";   
		String strEditarRFC = "";
		OrdenBean objOrdenBean = null;
		try {
			objOrdenBean = objDatosOrdenAjax.getOrdenFacturacionElectronicaInternet(Integer.parseInt(skOrdenSucursal),sPassword,kDatoFiscal);      
			strEditarRFC = ("alta-rfc.jsp?kordensucursalfinal=" + skOrdenSucursal + "&emailfinal=" + sEmail + "&passwordfinal=" + sPassword);
			if (objOrdenBean.getMadeuda() > 0 ) {
				sPrint = objOrdenBean.getDatosDemograficosFacturacionElectronica(sEmail,"La orden tiene adeudos","No se puede facturar");   	  
			} else if (objOrdenBean.getObjdatosfiscalesbean().getkDatosFiscales() == 0 && objOrdenBean.getSmensajeerror().trim().length() == 0 && objOrdenBean.getStrFactura().trim().length() == 0) {
				response.sendRedirect("alta-rfc.jsp?kordensucursalfinal=" + skOrdenSucursal + "&emailfinal=" + sEmail + "&passwordfinal=" + sPassword);     	  
			} else {    	  
				sPrint = objOrdenBean.getDatosDemograficosFacturacionElectronica(sEmail,"","");   	  
			}
			kDatoFiscal = objOrdenBean.getObjdatosfiscalesbean().getkDatosFiscales();
			cSucursal = objOrdenBean.getCsucursal();
			strRazonSocial = objOrdenBean.getObjdatosfiscalesbean().getStrRazonSocial();
			strRFC = objOrdenBean.getObjdatosfiscalesbean().getStrRFC();
			strCalle = objOrdenBean.getObjdatosfiscalesbean().getStrDireccion();
			strColonia = objOrdenBean.getObjdatosfiscalesbean().getStrColonia();
			strCiudad = objOrdenBean.getObjdatosfiscalesbean().getStrCiudad();
			strMunicipio = objOrdenBean.getObjdatosfiscalesbean().getStrDelegacionMunicipio();
			strEstado = objOrdenBean.getObjdatosfiscalesbean().getStrEstado();
			strCodigoPostal = objOrdenBean.getObjdatosfiscalesbean().getcPostal();
			strMsubTotal = objFormatos.formateaNumero(String.valueOf(objOrdenBean.getMsubtotal() - objOrdenBean.getMiva()));
			strMIVA = objFormatos.formateaNumero(String.valueOf(objOrdenBean.getMiva()));
			strMpagaPaciente = objFormatos.formateaNumero(String.valueOf(objOrdenBean.getMpagapaciente()));
			strMacuenta = objFormatos.formateaNumero(String.valueOf(objOrdenBean.getMacuenta()));
			strMadeuda = objFormatos.formateaNumero(String.valueOf(objOrdenBean.getMadeuda())); 
		} catch (Exception exp) {
			iObjLog.error("Confirmar.informacion:Exception....", exp);
			throw exp;
		}
	%>
	<input type="hidden" id="hdnProcesandoFactura" value="0" />
	<input type="hidden" id="hdnEditarRFC" value=<%=strEditarRFC %> />
	<input type="hidden" id="hdnDatosFiscales" value=<%=kDatoFiscal %> />
	<input type="hidden" id="txtAdmision" name="txtAdmision" value=<%=skOrdenSucursal %> >	
	<input type="hidden" id="txtCorreoElectronico" name="txtCorreoElectronico" value=<%=sEmail %> >	
	<input type="hidden" id="IdSucursalActual" name="IdUnidadActual" value=<%=cSucursal %> >	
	<input type="hidden" id="dblTotalPagar" name="dblTotalPagar" value=<%=dblTotalPagar %> >	
	<input type="hidden" id="strRazonSocial" name="strRazonSocial" value=<%=strRazonSocial %> >	
	<input type="hidden" id="strRFC" name="strRFC" value=<%=strRFC %> >	
	<input type="hidden" id="strCalle" name="strCalle" value=<%=strCalle %> >	
	<input type="hidden" id="strColonia" name="strColonia" value=<%=strColonia %> >	
	<input type="hidden" id="strCiudad" name="strCiudad" value=<%=strCiudad %> >	
	<input type="hidden" id="strMunicipio" name="strMunicipio" value=<%=strMunicipio %> >	
	<input type="hidden" id="strEstado" name="strEstado" value=<%=strEstado %> >	
	<input type="hidden" id="strCodigoPostal" name="strCodigoPostal" value=<%=strCodigoPostal %> >	
	<div class="container">
		<div class="header">
			<img src="/web2labportal/images/facturacionelectronica2015/olab_logo.png" height= "80px" alt="" class="logo" />
			<img src="/web2labportal/images/facturacionelectronica2015/slogan_olab.png" height="87px" alt="" class="f_right" />
		</div>
		<div class="facturacion clearfix">
			<p>Confirmaci&oacute;n de datos fiscales</p>
		</div>
		<div class="main clearfix">
			<div class="row column">
				<div class="datosFiscales">
					<label class="labDato"><strong>Sucursal</strong></label>
					<p class="labDato back2"><%=objOrdenBean.getSsucursal() %></p>
				</div>
				<div class="datosFiscales">
					<label class="labDato"><strong>Usuario</strong></label>
					<p class="labDato back2"><%=objOrdenBean.getBpacientebean().getSappaterno() + " " + objOrdenBean.getBpacientebean().getSapmaterno() + " " + objOrdenBean.getBpacientebean().getSnombre() %></p>
				</div>
			</div>
			<div class="row column">
				<div class="datosFiscales">
					<label class="labDato"><strong>Confirme sus datos fiscales</strong></label>
					<div class="datosFiscales back">				
						<%
							if (objOrdenBean.getSmensajeerror().trim().length() > 0) {
								out.println("<label><p><strong>MENSAJE</strong></p></label>");
								out.println("<p>.</p>"); 
								out.println("<label><p><strong>" + objOrdenBean.getSmensajeerror().trim() + "</strong></p></label>");
								out.println("<p>.</p>");
								out.println("<label><p><strong>.</strong></p></label>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
							} else if(objOrdenBean.getMpagapaciente() == 0) {
								out.println("<label><p><strong>MENSAJE</strong></p></label>");
								out.println("<p>.</p>"); 
								out.println("<label><p><strong>Esta orden no se puede facturar, es de empresas</strong></p></label>");
								out.println("<p>.</p>");
								out.println("<label><p><strong>.</strong></p></label>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
							} else if ((objOrdenBean.getNofacturas() >= 9) && (objOrdenBean.getStrFactura().trim().length() > 1)) {
								out.println("<label><p><strong>MENSAJE, Factura anterior " + objOrdenBean.getStrFactura().trim() + "</strong></p></label>");
								out.println("<p>.</p>"); 
								out.println("<label><p><strong>Esta orden no se puede facturar, ya tiene 9 facturas anteriores</strong></p></label>");
								out.println("<p>Puedes reimprir el <a href=javascript:void(window.open('http://192.237.150.66:9085/FacturasElectronicas_Olab/PDF/FacturacionElectronica_" + objOrdenBean.getStrFactura().trim() + ".pdf'))><img src='/web2labportal/images/icoPdf.png' alt='Factura PDF'></a> y <a href=javascript:void(window.open('http://192.237.150.66:9085/FacturasElectronicasCFDPROCESADOSXML/FacturacionElectronica_" + objOrdenBean.getStrFactura().trim() + ".xml'))><img src='/web2labportal/images/icoXml.png' alt='Factura XML'></a></p>");
								out.println("<label><p><strong>.</strong></p></label>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
							} else if (objOrdenBean.getNofacturas() >= 9) {
								out.println("<label><p><strong>MENSAJE</strong></p></label>");
								out.println("<p>.</p>"); 
								out.println("<label><p><strong>Esta orden no se puede facturar, ya tiene 9 facturas anteriores</strong></p></label>");
								out.println("<p>.</p>");
								out.println("<label><p><strong>.</strong></p></label>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");
								out.println("<p>.</p>");								
							} else if (objOrdenBean.getStrFactura().trim().length() > 1){
								out.println("<label><p><strong>REFACTURACION " + (objOrdenBean.getNofacturas() +1) + " ,Factura anterior " + objOrdenBean.getStrFactura().trim() + "</strong></p></label>");
								out.println("<p>Puedes reimprir el <a href=javascript:void(window.open('http://192.237.150.66:9085/FacturasElectronicas_Olab/PDF/FacturacionElectronica_" + objOrdenBean.getStrFactura().trim() + ".pdf'))><img src='/web2labportal/images/icoPdf.png' alt='Factura PDF'></a> y <a href=javascript:void(window.open('http://192.237.150.66:9085/FacturasElectronicasCFDPROCESADOSXML/FacturacionElectronica_" + objOrdenBean.getStrFactura().trim() + ".xml'))><img src='/web2labportal/images/icoXml.png' alt='Factura XML'></a></p>");
								out.println("<p>RFC:" + strRFC  + "</p>"); 
								out.println("<label><p><strong>Raz&oacute;n social</strong></p></label>");
								out.println("<p>" + strRazonSocial  + "</p>");
								out.println("<label><p><strong>Direcci&oacute;n</strong></p></label>");
								out.println("<p>" + strCalle + "</p>");
								out.println("<p>COLONIA " + strColonia  + "</p>");
								out.println("<p>DELEGACION " + strMunicipio + "</p>");
								out.println("<p>ESTADO " + strEstado  + "</p>");
								out.println("<p>CP " + strCodigoPostal  + "</p>");
								out.println("<label><p><strong>Correo electr&oacute;nico</strong></p></label>");
								out.println("<p>" + sEmail  + "</p>");			
							} else {
								out.println("<label><p><strong>RFC</strong></p></label>");
								out.println("<p>" + strRFC  + "</p>"); 
								out.println("<label><p><strong>Raz&oacute;n social</strong></p></label>");
								out.println("<p>" + strRazonSocial  + "</p>");
								out.println("<label><p><strong>Direcci&oacute;n</strong></p></label>");
								out.println("<p>" + strCalle + "</p>");
								out.println("<p>COLONIA " + strColonia  + "</p>");
								out.println("<p>DELEGACION " + strMunicipio + "</p>");
								out.println("<p>ESTADO " + strEstado  + "</p>");
								out.println("<p>CP " + strCodigoPostal  + "</p>");
								out.println("<label><p><strong>Correo electr&oacute;nico</strong></p></label>");
								out.println("<p>" + sEmail  + "</p>");			
							}
						%>
					</div>
				</div>
				<div class="datosFiscales back importes">
					<div class="column back">
						<label class="labDato"><strong>SUBTOTAL</strong></label>
						<label class="labDato"><strong>IVA</strong></label> 
						<label class="labDato"><strong>TOTAL</strong></label>
					</div>
					<div class="column back">
						<%
							if((objOrdenBean.getMpagapaciente() == 0) || (objOrdenBean.getSmensajeerror().trim().length() > 0)) {
								out.println("<p class=\"labDato back clear\">$0</p>");
								out.println("<p class=\"labDato back clear\">$0</p>");
								out.println("<p class=\"labDato back clear\">$0</p>");
							} else {
								out.println("<p class=\"labDato back clear\">$" + strMsubTotal  + "</p>");
								out.println("<p class=\"labDato back clear\">$" + strMIVA  + "</p>");
								out.println("<p class=\"labDato back clear\">$" + strMpagaPaciente  + "</p>");
							}
						%>
					</div>
				</div>
				<div class="bigBtns">
						<%
							if(objOrdenBean.getMpagapaciente() > 0) {				
								if ((objOrdenBean.getNofacturas() >= 5) && (objOrdenBean.getStrFactura().trim().length() > 1)) {
									
								} else {									
									
									out.println("<a href='alta-rfc.jsp?kordensucursalfinal=" + skOrdenSucursal + "&emailfinal=" + sEmail + "&passwordfinal=" + sPassword + "'>");
									out.println("	<button type=\"submit\" id=\"editarDatosFiscales\" class=\"left-btn progress-button\">");
									out.println("		<span class=\"content\">EDITAR DATOS FISCALES</span>");
									out.println("	</button>");
									if (objOrdenBean.getStrFactura().trim().length() > 1){
										out.println("</a>");
										out.println("<button type=\"submit\" id=\"continuar\" class=\"right-btn progress-button\">");
										out.println("	<span class=\"content\">REFACTURAR</span>");
										out.println("</button>");
									} else {
										out.println("</a>");
										out.println("<button type=\"submit\" id=\"continuar\" class=\"right-btn progress-button\">");
										out.println("	<span class=\"content\">FACTURAR</span>");
										out.println("</button>");
									}
								}
								
							}
						%>
				</div>
			</div>
		</div>
        <div id="modal2">
            <div class="overlay"></div>
            <div class="content">
                <h3>Aviso importante</h3>
                <p class='msg'>ESTIMADO PACIENTE, EN CASO DE HABER INGRESADO SUS DATOS FISCALES ERR&oacute;NEAMENTE, USTED TIENE HASTA EL 31 DE DICIEMBRE DEL 2015 PARA REALIZAR 2 RE FACTURACIONES.</p>
                <p class='msg'>FAVOR DE INGRESAR NUEVAMENTE LOS DATOS DE SU ORDEN, NUESTRO SISTEMA LE INDICAR&aacute; EL PROCESO</p>
<!--            <p class='reMsg'>ESTIMADO PACIENTE LE PEDIMOS VERIFICAR NUEVAMENTE SUS DATOS FISCALES YA QUE NO PODR&aacute; MODIFICARSE LA FACTURA GENERADA</p>   --> 
                <p class='reMsg'>ESTIMADO PACIENTE, EN CASO DE HABER INGRESADO SUS DATOS FISCALES ERR&oacute;NEAMENTE, USTED TIENE HASTA EL 31 DE DICIEMBRE DEL 2015 PARA REALIZAR 2 RE FACTURACIONES.</p>    
                <p class='reMsg'>FAVOR DE INGRESAR NUEVAMENTE LOS DATOS DE SU ORDEN, NUESTRO SISTEMA LE INDICAR&aacute; EL PROCESO</p>    
                <div class="buttons">
						<%
// 							String browser = request.getHeader("User-Agent");
// 							if(browser.indexOf("MSIE") > 0) {
// 								if(objOrdenBean.getStrFactura().trim().length() == 0) {				
// 									out.println("<a id='btnaceptar' href='#' onClick='guardarFactura();'>");
// 									out.println("	<font id='btn_aceptar_font'>FACTURAR</font>");
// 									out.println("</a>");
// 								} else {
// 									out.println("<a id='btnaceptar' href='#' onClick='guardarreFactura();'>");
// 									out.println("	<font id='btn_aceptar_font'>REFACTURAR</font>");
// 									out.println("</a>");
// 								}								
// 							} else {
								if(objOrdenBean.getStrFactura().trim().length() == 0) {				
									out.println("<a id='btnaceptar' href='#' onClick='guardarFactura();'>");
									out.println("	<div class='button'><font id='btn_aceptar_font'>FACTURAR</font></div>");
									out.println("</a>");
								} else {
									out.println("<a id='btnaceptar' href='#' onClick='guardarreFactura();'>");
									out.println("	<div class='button'><font id='btn_aceptar_font'>REFACTURAR</font></div>");
									out.println("</a>");
								}							
// 							}												
						%>                
                </div>
            </div>
        </div>
		<div id="footer">
			Grupo Diagn&oacute;stico Aries © Todos los derechos reservados 2015-2016.
		</div>
	</div>
	</body>
</html>