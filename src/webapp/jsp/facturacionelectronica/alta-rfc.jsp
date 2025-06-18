<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <title>GDA</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <link href="http://fonts.googleapis.com/css?family=Raleway:400,300,700" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronica2015/normalize.css" />
    <link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronica2015/ns-default.css" />
    <link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronica2015/ns-style-other.css" />
    <link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronica2015/validationEngine.jquery.css" />
    <link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronica2015/styles.css" />
    <link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronica2015/index.css" />

    <script type="text/javascript" src="/web2labportal/javascript/facturacionelectronica2015/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="/web2labportal/javascript/facturacionelectronica2015/jquery.inputmask.bundle.js"></script>
    <script type="text/javascript" src="/web2labportal/javascript/facturacionelectronica2015/languages/jquery.validationEngine-es.js" charset="utf-8"></script>
    <script type="text/javascript" src="/web2labportal/javascript/facturacionelectronica2015/jquery.validationEngine.js" charset="utf-8"></script>
	<script src="/web2labportal/javascript/facturacionelectronica2015/modernizr.custom.js"></script>
    <script src="/web2labportal/javascript/facturacionelectronica2015/snap.svg-min.js"></script>
    
  <!-- Other scripts -->
  <script src="/web2labportal/javascript/generales.js" type="text/javascript" ></script>
  <script src="/web2labportal/javascript/facturacionelectronica/pantallas/alta-rfc.js" type="text/javascript"></script>  
  <script src='/web2labportal/dwr/interface/DatosFiscales.js' type='text/javascript' ></script>
  <script src='/web2labportal/dwr/engine.js' type='text/javascript' ></script>
  <script src='/web2labportal/dwr/util.js'   type='text/javascript' ></script>
    
</head>
<body>
	<% 
		String skOrdenSucursal = request.getParameter("kordensucursalfinal").trim(); 
		String sEmail = request.getParameter("emailfinal").trim(); 
		String sPassword = request.getParameter("passwordfinal").trim();
	%>
	<input type="hidden" id="hdnDatosFiscales" value="0"/>
	<input type="hidden" id="txtAdmision" name="txtAdmision" value=<%=skOrdenSucursal %> >	
	<input type="hidden" id="txtCorreoElectronico" name="txtCorreoElectronico" value=<%=sEmail %> >	
	<input type="hidden" id="txtPassword" value=<%=sPassword %>   >
	<input type="hidden" id="txtMensajeAltaRFC" value="" >

    <div class="notification-shape shape-box" id="notification-shape" data-path-to="m 0,0 500,0 0,500 -500,0 z">
        <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 500 500" preserveAspectRatio="none">
            <path d="m 0,0 500,0 0,500 0,-500 z"/>
        </svg>
    </div>
    <div class="container">

        <div class="header">
            <img src="/web2labportal/images/facturacionelectronica2015/olab_logo.png" height= "80px" alt="" class="logo" />
            <img src="/web2labportal/images/facturacionelectronica2015/slogan_olab.png" height="87px" alt="" class="f_right" />
        </div>

        <div class="facturacion clearfix">
			<p>Alta de Datos Fiscales, orden <%=skOrdenSucursal%> se enviara al correo <%=sEmail%></p>
        </div>
        <div class="mensaje">
            El RFC debe tener 12 caracteres para persona moral y 13 caracteres para persona f&iacute;sica
        </div>
    	<div id="form_dat" class="main clearfix">
			<form id="facturaDatos" method="post">
            <div class="column">
                    <div class="checkbox">
                        <input type="radio" value="moral" id="moral" name="tipoPersona" />
                        <label for="checkbox"></label>
                    </div>
                    <p class="tipoPersona">Persona moral</p>
                    <div class="checkbox">
                        <input type="radio" value="fisica" id="fisica" name="tipoPersona" checked/>
                        <label for="checkbox"></label>
                    </div>
                    <p class="tipoPersona">Persona f&iacute;sica</p>
<!--                      <p class="right"><label for="idRFC" >RFC:</label><p class="right"><input name="rfc" type="text" class="validate[required,funcCall[ValidaRfc],funcCall[ValidaIniciales],funcCall[ValidaAno],funcCall[ValidaMes],funcCall[ValidaDia],funcCall[ValidaHomoclave]] text-input" id="rfc" placeholder="RFC:" /></p>-->
                    <p class="right"><label for="idRFC" >RFC:</label><p class="right"><input name="rfc" type="text" class="validate[required,funcCall[ValidaRfc]] text-input" id="rfc" placeholder="RFC:" /></p>
                    <p class="right"><label for="idDireccion" >Direcci&oacute;n:</label><p class="right"><textarea name="direccion" rows="2" class="validate[required] text-input right" id="direccion" placeholder="Direcci&oacute;n: " size="50"></textarea></p>
                    <p class="right"><label for="idDelegacion" >Delegaci&oacute;n/Municipio:</label><p class="right"><input class="validate[required] text-input" type="text" id="delegacion" name="delegacion" placeholder="Delegaci&oacute;n/Municipio:"  /></p>
                    <p class="right"><label for="idEstado" >Estado:</label><p class="right"><input class="validate[required] text-input" type="text" id="estado" name="estado" placeholder="Estado:"  />
                    	<!--<select id="selEstado" name="selEstado">
                              <option value="-1">--Elige un estado--</option> 
                            <option value="9" selected>DISTRITO FEDERAL</option>
                            <option value="11">ESTADO DE M&eacute;XICO</option>
                            <option value="1">AGUASCALIENTES</option>
                            <option value="2">BAJA CALIFORNIA NORTE</option>
                            <option value="3">BAJA CALIFORNIA SUR</option>
                            <option value="4">CAMPECHE</option>
                            <option value="5">CHIAPAS</option>
                            <option value="6">CHIHUAHUA</option>
                            <option value="7">COAHUILA</option>
                            <option value="8">COLIMA</option>
                            <option value="10">DURANGO</option>
                            <option value="12">GUANAJUATO</option>
                            <option value="13">GUERRERO</option>
                            <option value="14">HIDALGO</option>
                            <option value="15">JALISCO</option>
                            <option value="16">MICHOACAN</option>
                            <option value="17">MORELOS</option>
                            <option value="18">NAYARIT</option>
                            <option value="19">NUEVO LEON</option>
                            <option value="20">OAXACA</option>
                            <option value="21">PUEBLA</option>
                            <option value="22">QUERETARO</option>
                            <option value="23">QUINTANA ROO</option>
                            <option value="24">SAN LUIS POTOSI</option>
                            <option value="25">SINALOA</option>
                            <option value="26">SONORA</option>
                            <option value="27">TABASCO</option>
                            <option value="28">TAMAULIPAS</option>
                            <option value="29">TLAXCALA</option>
                            <option value="30">VERACRUZ</option>
                            <option value="31">YUCATAN</option>
                            <option value="32">ZACATECAS</option>
					</select>--></p>
			</div>
            <div class="column">
            	<p class="left"><label for="idRazonSocial" >Persona F&iacute;sica (Nombre), Persona Moral (Raz&oacute;n o Denominaci&oacute;n fiscal):</label><p class="left"><input class="validate[required] text-input" type="text" id="razonSocial" name="razonSocial" placeholder="Razon social:"  /></p>
                <p class="left"><label for="idColonia" >Colonia:</label><p class="left"><input class="validate[required] text-input" type="text" id="colonia" name="colonia" placeholder="Colonia:"  /></p>
                <p class="left"><label for="idCodigoPostal" >Codigo Postal:</label><p class="left"><input class="validate[required,funcCall[ValidaCP]] text-input" type="text" id="codigoPostal" name="codigoPostal" placeholder="Codigo Postal:" maxlength="5"  /></p>
<!--                  <p class="left"><label for="idCorreo" >Correo:</label><p class="left"><input data-prompt-position="inline" class="validate[required, funcCall[ValidaCorreo]] text-input texto email" type="text" name="email" id="email" placeholder="Correo" data-inputmask="'alias': 'email'"></p>-->
                <p>
					<%
// 						String browser = request.getHeader("User-Agent");
// 						if(browser.indexOf("MSIE") > 0) {
// 							out.println("<button type=\"submit\"  id=\"notification-trigger\">");
// 							out.println("	Enviar");
// 							out.println("</button>");
// 						} else {
							out.println("<button type=\"submit\" class=\"progress-button\" id=\"notification-trigger\">");
							out.println("	<span class=\"content\">Enviar</span>");
							out.println("	<span class=\"progress\"></span>");
							out.println("</button>");
// 						}												
					%>
                </p>
            </div>
            </form>
		</div>
        <div id="footer">
	        Grupo Diagn&oacute;stico Aries © Todos los derechos reservados 2015-2016.
        </div>
        <div id="modal">
            <div class="overlay"></div>
            <div class="content">
                <h3>Aseg&uacute;rate de que est&eacute;n bien tus datos fiscales</h3>
                <table>
                    <tr>
                        <th>Tipo de persona</th>
                        <td id="tipoPersonaInfo"></td>
                    </tr>
                    <tr>
                        <th>RFC</th>
                        <td id="rfcInfo"></td>
                    </tr>
                    <tr>
                        <th>Raz&oacute;n Social</th>
                        <td id="razonSocialInfo"></td>
                    </tr>
                    <tr>
                        <th>Direcci&oacute;n</th>
                        <td id="direccionInfo"></td>
                    </tr>
                    <tr>
                        <th>Delegaci&oacute;n</th>
                        <td id="delegacionInfo"></td>
                    </tr>
                    <tr>
                        <th>Estado</th>
                        <td id="estadoInfo"></td>
                    </tr>
                    <tr>
                        <th>Colonia</th>
                        <td id="coloniaInfo"></td>
                    </tr>
                    <tr>
                        <th>CP</th>
                        <td id="codigoPostalInfo"></td>
                    </tr>
<!--                      
                    <tr>
                        <th>Correo electr&oacute;nico</th>
                        <td id="emailInfo"></td>
                    </tr>
-->                    
                </table>
                <div class="buttons">
                    <div class="button" id="modalBack">Regresar</div>
                    <div class="button" id="modalAccept">Continuar</div>
                </div>
            </div>
        </div>

	</div><!-- /container -->
    <script src="/web2labportal/javascript/facturacionelectronica2015/classie.js"></script>
    <script src="/web2labportal/javascript/facturacionelectronica2015/notificationFx.js"></script>
	<script src="/web2labportal/javascript/facturacionelectronica2015/facturacion_altaRFC.js"></script>
	<script>
		if ( $.browser.msie ) {
		} else {
			$('label[for="idRFC"]').hide();
			$('label[for="idDireccion"]').hide();
			$('label[for="idDelegacion"]').hide();								
			$('label[for="idEstado"]').hide();								
			$('label[for="idRazonSocial"]').hide();								
			$('label[for="idColonia"]').hide();								
			$('label[for="idCodigoPostal"]').hide();								
			$('label[for="idCorreo"]').hide();								
		}
	</script>	
</body>
</html>
