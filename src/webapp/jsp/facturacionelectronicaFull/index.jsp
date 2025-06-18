<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<title>Olab</title>
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
	
</head>
<body>
	<div class="notification-shape shape-box" id="notification-shape" data-path-to="m 0,0 500,0 0,500 -500,0 z">
		<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 500 500" preserveAspectRatio="none">
			<path d="m 0,0 500,0 0,500 0,-500 z" />
		</svg>
	</div>
	<div class="container">
		<div class="header">
			<img src="/web2labportal/images/facturacionelectronica2015/olab_logo.png" height= "80px" alt="" class="logo" />
			<img src="/web2labportal/images/facturacionelectronica2015/slogan_olab.png" height="87px" alt="" class="f_right" />
		</div>
		<div class="facturacion clearfix">
			<p>facturaci&oacute;n electr&oacute;nica</p>
		</div>
		<div class="mensaje">
			Estimado paciente, te recordamos que para poder generar tu factura electr&oacute;nica
			<br><br>
			***   Es necesario que su orden se encuentre pagada al 100%.
			<br>
			***   Es indispensable la homoclave en su R.F.C.
			<br>
			***   Solo tienes el año corriente como limite.
			<br>
			***   Si existe alg&uacute;n problema con mucho gusto lo atenderemos:
			<br>
			.......... Env&iacute;anos un correo a soporte.facturacion@grupodiagnosticoaries.com.mx, nombre completo, n&uacute;mero telef&oacute;nico, contrase&ntilde;a y n&uacute;mero de orden(es).
			<br>
			<br><br>
			*** AYUDA: Pase por encima de la imagen del recibo (c&iacute;rculos naranjas) el puntero del mouse.
		</div>
		<div class="main clearfix">
			<div class="column image">
				<div class="magnify">
					<div class="large"></div>
					<img class="small" src="/web2labportal/images/facturacionelectronica2015/factura.png" width="400" />
				</div>
				<script src="/web2labportal/javascript/facturacionelectronica2015/prefixfree.js" type="text/javascript"></script>
			</div>
			<div class="column">
				<form id="facturas" method="post" action="">
					<p><label for="idConsecutivo" >Consecutivo</label><p><input data-prompt-position="inline" class="validate[required] text-input texto" type="text" name="consecutivo" id="consecutivo" maxlength="7" autofocus placeholder="Consecutivo"></p>
					<p><label for="idPassword" >Password</label><p><input data-prompt-position="inline" class="validate[required, funcCall[ValidaPassword]] text-input texto pass" type="text" name="password" id="password" maxlength="4" placeholder="contrase&ntilde;a"></p>
					<p><label for="idCorreo" >Correo</label><p><input data-prompt-position="inline" class="validate[required, funcCall[ValidaCorreo]] text-input texto email" type="text" name="email" id="email" placeholder="Correo" data-inputmask="'alias': 'email'"></p>
					<p>
						<button type="submit" id="notification-trigger" class="progress-button">
							<span class="content">Pedir factura</span>
							<span class="progress"></span>
						</button>
					</p>
				</form>
			</div>
		</div>
		<div id="footer">Grupo Diagn&oacute;stico Aries © Todos los derechos reservados 2015-2016.</div>
	</div>
	<script src="/web2labportal/javascript/facturacionelectronica2015/classie.js"></script>
	<script src="/web2labportal/javascript/facturacionelectronica2015/notificationFx.js"></script>
	<script src="/web2labportal/javascript/facturacionelectronica2015/facturacion.js"></script>
</body>
<script>
	if ( $.browser.msie ) {
	} else {
		$('label[for="idConsecutivo"]').hide();
		$('label[for="idPassword"]').hide();
		$('label[for="idCorreo"]').hide();								
	}
</script>

</html>