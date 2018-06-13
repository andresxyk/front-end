<%@ page session="true" language="java" import="mx.com.web2lab.tools.CaptchasDotNet" errorPage="/jsp/error.jsp"%>
<html>
  <head>
    <title>Acceso del Expediente Clinico Electronico</title>
	<link rel="stylesheet" type="text/css" href="/web2labportal/css/web2lab.css" />
  </head>
<body>
<div id="header"> <a href="http://www.olab.com.mx"><img src="/web2labportal/images/logo_olab.png" border="0" align="left" width="300" height="150"/></a>
</div>
</div>
  <h1>Acceso del Expediente Clinico Electronico</h1>
	<%
		CaptchasDotNet captchas = new CaptchasDotNet(request.getSession(true),"ECEOlab","3HtGXyW4X1Uri2FBfGGgjbktAxtLd0Y0fNUWZx7k");
		String strusername  = request.getParameter("strusername").trim();
		String strpassword  = request.getParameter("strpassword").trim();
	%>
  <form method="get" action="<%=response.encodeUrl("ValidacionAccesoECE.jsp")%>">
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
        </td>
        <td>
          <input type="submit" value="Submit" class="boton" />
        </td>
      </tr>
    </table>
  </form>
</body>
</html>