<!DOCTYPE html>
   <% 
   	  String sURLFacturacionElectronica = request.getParameter("urlFactura"); 
      int cMarca = Integer.parseInt(request.getParameter("cmarca"));
   %>

<html lang="en" class="no-js">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>GDA</title>

		<link href="http://fonts.googleapis.com/css?family=Raleway:400,300,700" rel="stylesheet" type="text/css">
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronica2015/normalize.css" />
		<link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronica2015/styles.css" />
		<link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronica2015/index.css" />
						
        <script type="text/javascript" src="/web2labportal/javascript/facturacionelectronica2015/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="/web2labportal/javascript/facturacionelectronica2015/jquery.inputmask.bundle.js"></script>
        <script type="text/javascript" src="/web2labportal/javascript/facturacionelectronica2015/languages/jquery.validationEngine-es.js" charset="utf-8"></script>
        <script type="text/javascript" src="/web2labportal/javascript/facturacionelectronica2015/jquery.validationEngine.js" charset="utf-8"></script>
        <script src="/web2labportal/javascript/facturacionelectronica2015/facturacion.js"></script>

		<script src="/web2labportal/javascript/facturacionelectronica2015/modernizr.custom.js"></script>
		<script src="/web2labportal/javascript/facturacionelectronica2015/snap.svg-min.js"></script>
		<script src="/web2labportal/javascript/facturacionelectronica2015/classie.js"></script>
		<script src="/web2labportal/javascript/facturacionelectronica2015/notificationFx.js"></script>

	</head>

	<body>

    <div class="container">

        <div class="header">
       <img src="/web2labportal/images/facturacionelectronica2015/olab_logo.png" height= "80px" alt="" class="logo" />
            <img src="/web2labportal/images/facturacionelectronica2015/slogan_olab.png" height="87px" alt="" class="f_right" />
        </div>

        <div class="facturacion clearfix">
        	<p>Su factura se ha generado correctamente - <%=sURLFacturacionElectronica%></p>
        </div>
        <div class="reMsg">
            <div class="mensaje">
                Factura: <%=sURLFacturacionElectronica%>
            </div>
        </div>
        <div class="main clearfix">
        	<div class="center" >
				<%
					if (cMarca == 1) {
						out.println("<a id='btnaceptarPDF' href=\"javascript:void(window.open('http://192.237.150.66:9085/FacturasElectronicas_Olab/PDF/FacturacionElectronica_" + sURLFacturacionElectronica.trim() + ".pdf'))\" ");
					} else if (cMarca == 4) {																		
						out.println("<a id='btnaceptarPDF' href=\"javascript:void(window.open('http://192.237.150.66:9085/FacturasElectronicas_Azteca/PDF/FacturacionElectronica_" + sURLFacturacionElectronica.trim() + ".pdf'))\" ");
					}								
				%>        	
	                <button type="submit" id="notification-trigger" class="progress-button fileButton">
	                    <span class="content">Descargar PDF <i class="fa fa-file-pdf-o"></i></span>
	                    <span class="progress"></span>
	                </button>
	            </a>
	            <br>
	            <br>
				<%
					if (cMarca == 1) {
						out.println("<a id='btnaceptarXML' href=\"javascript:void(window.open('http://192.237.150.66:9085/FacturasElectronicas_Olab/XML/FacturacionElectronica_" + sURLFacturacionElectronica.trim() + ".xml'))\" ");
					} else if (cMarca == 4) {																		
						out.println("<a id='btnaceptarXML' href=\"javascript:void(window.open('http://192.237.150.66:9085/FacturasElectronicas_Azteca/XML/FacturacionElectronica_" + sURLFacturacionElectronica.trim() + ".xml'))\" ");
					}								
				%>        		            
	                <button type="submit" id="notification-trigger" class="progress-button fileButton">
	                    <span class="content">Descargar XML <i class="fa fa-file-text"></i></span>
	                    <span class="progress"></span>
	                </button>
	            </a>
            </div>
        </div>
        <div id="footer">
        	Grupo Diagn&oacute;stico Aries  SA de CV. © Todos los derechos reservados 2015-2016
        </div>
    </div>
</body>
</html>