<%@ page session="true" language="java" import="mx.com.web2lab.tools.CaptchasDotNet" errorPage="/jsp/error.jsp"%>

<html>
  <head>
    <title>Acceso del Expediente Clinico Electronico</title>
  </head>
  <h1>Acceso del Expediente Clinico Electronico</h1>
<%
	CaptchasDotNet captchas = new CaptchasDotNet(request.getSession(true),"ECEOlab","3HtGXyW4X1Uri2FBfGGgjbktAxtLd0Y0fNUWZx7k");
	String strusername  = request.getParameter("strusername").trim();
	String strpassword  = request.getParameter("strpassword").trim();
	String strimagen = request.getParameter("strimagen");
	String body;
	switch (captchas.check(strimagen)) {
	  case 's':
	    body = "Session seems to be timed out or broken. ";
	    body += "Please try again or report error to administrator.";
	    break;
	  case 'm':
	    body = "Every CAPTCHA can only be used once. ";
	    body += "The current CAPTCHA has already been used. ";
	    body += "Please use back button and reload";
	    break;
	  case 'w':
		response.sendRedirect("/web2labportal/jsp/AccesoECE.jsp?strusername=" + strusername + "&strpassword=" + strpassword + "&strmensaje=El mensaje de la imagen escrito es erroneo, Por favor escribe el mensaje identico.");    	
	    body = "";
	    break;
	  default:
		response.sendRedirect("/web2labportal/servlet/template/web2lab,seguridad,ExpedienteElectronico.vm/action/seguridad.LoginExpedienteClinicoElectronicoAction?username=" + strusername + "&password=" + strpassword);    	
	    body = "";
	    break;
	}
%>
<%=body%>
</html>