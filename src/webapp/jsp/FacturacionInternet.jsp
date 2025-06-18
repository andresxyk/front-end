<%@ page session="true" language="java" import="mx.com.web2lab.tools.CaptchasDotNet" errorPage="/jsp/error.jsp"%>
<html>
  <head>
    <title>Facturaci&oacute;n Electr&oacute;nica</title>
	<link rel="stylesheet" type="text/css" href="/web2labportal/css/web2lab.css" />
	<script language="javascript" SRC="/web2labportal/javascript/generales.js"></script>
	<script language='javascript' src="/web2labportal/javascript/calendarfoco.js"></script>
	<script language="javascript" SRC="/web2labportal/javascript/CalculaEdad.js"></script>
	
	<script language="javascript" SRC="/web2labportal/javascript/validacionobjeto/cliente/convenio/CapturaOrdenConvenio.js"></script>
	<script language="javascript" SRC="/web2labportal/javascript/validacionobjeto/examen/CapturaOrdenExamen.js"></script>
	<script language="javascript" SRC="/web2labportal/javascript/validacionobjeto/factura/CapturaOrdenFactura.js"></script>
	<script language="javascript" SRC="/web2labportal/javascript/validacionobjeto/medico/CapturaOrdenMedico.js"></script>
	<script language="javascript" SRC="/web2labportal/javascript/validacionobjeto/paciente/CapturaOrdenPaciente.js"></script>
	<script language="javascript" SRC="/web2labportal/javascript/validacionobjeto/pagos/CapturaOrdenPago.js"></script>
	<script language="javascript" SRC="/web2labportal/javascript/validacionobjeto/pantallas/CapturaOrden.js"></script>
	<script language="javascript" SRC="/web2labportal/javascript/validacionobjeto/tools/Tools.js"></script>
	<script language="javascript" SRC="/web2labportal/javascript/tooljquery/submodal/common.js"></script>
	<script language="javascript" SRC="/web2labportal/javascript/tooljquery/submodal/subModal.js"></script>
	<script language='javascript' src="/web2labportal/javascript/reportes.js"></script>
	
	
	<script type='text/javascript' src='/web2labportal/dwr/interface/ToolsAjax.js'></script>
	<script type='text/javascript' src='/web2labportal/dwr/interface/DatosPaciente.js'></script>
	<script type='text/javascript' src='/web2labportal/dwr/interface/PacienteMayoreo.js'></script>
	<script type='text/javascript' src='/web2labportal/dwr/interface/DatosMedico.js'></script>
	<script type='text/javascript' src='/web2labportal/dwr/interface/DatosOrden.js'></script>
	<script type='text/javascript' src='/web2labportal/dwr/interface/DatosExamen.js'></script>
	<script type='text/javascript' src='/web2labportal/dwr/interface/CotizacionOrdenes.js'></script>
	<script type='text/javascript' src='/web2labportal/dwr/interface/Tickets.js'></script>
	<script type='text/javascript' src='/web2labportal/dwr/engine.js'></script>
	<script type='text/javascript' src='/web2labportal/dwr/util.js'></script>
	
  </head>
<body>
<div id="header"> <a href="http://www.olab.com.mx"><img src="/web2labportal/images/logo_olab.png" border="0" align="left" width="300" height="150"/></a>
</div>
</div>
  <h1>Facturaci&oacute;n Electr&oacute;nica</h1>
	<%
		CaptchasDotNet captchas = new CaptchasDotNet(request.getSession(true),"ECEOlab","3HtGXyW4X1Uri2FBfGGgjbktAxtLd0Y0fNUWZx7k");
		String strusername  = request.getParameter("strusername").trim();
		String strpassword  = request.getParameter("strpassword").trim();
	%>
  <form>
	<%
		if(request.getParameter("strmensaje")!=null) {
			out.println("<h2>" + request.getParameter("strmensaje") + "</h2>");
		}
	%>
    <table>
      <tr>
        <td>
          <input type="hidden" name="strusername" size="60" value='<%=strusername%>' />
          <input type="hidden" name="strpassword" size="60" value='<%=strpassword%>' />
        </td>
      </tr>
      <tr>
        <td>
          Escribe el mensaje de la imagen:
        </td>
        <td>
          <input name="strimagen" size="16" STYLE="font-size: 30px;" />
        </td>
      </tr>
      <tr>
        <td>
        </td>
        <td>
          <%= captchas.image() %><br>
          <a href="<%= captchas.audioUrl() %>" STYLE="font-size: 20px;">¿Quieres escuchar como se pronuncia la imagen? (mp3)</a>
        </td>
      </tr>
      <tr>
        <td>
          Escribe el consecutivo de la orden:
        </td>
        <td>
          <input id="idConsecutivo" size="16" STYLE="font-size: 30px;" />
        </td>
      </tr>
      <tr>
        <td>
          Escribe la contrase&ntilde;a de la orden:
        </td>
        <td>
          <input id="idPassword" size="16" STYLE="font-size: 30px;" />
        </td>
      </tr>
      <tr>
        <td>
          Escribe tu correo electronico:
        </td>
        <td>
          <input id="idemail" size="16" STYLE="font-size: 30px;" />
        </td>
      </tr>
      <tr>
        <td>
        </td>
        <td>
          <input type="submit" value="Crear Factura" class="boton" onClick="javascript:crearFactura(2154588);" />
        </td>
      </tr>
    </table>
  </form>
</body>
</html>