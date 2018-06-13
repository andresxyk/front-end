<%@ page session="true" language="java" import="mx.com.web2lab.tools.CaptchasDotNet" %>

<html>
  <head>
    <title>Acceso a la Facturacion Electronica</title>
  </head>
  <h1>Acceso a la Facturacion Electronica</h1>
<%
	CaptchasDotNet captchas = new CaptchasDotNet(request.getSession(true),"ECEOlab","3HtGXyW4X1Uri2FBfGGgjbktAxtLd0Y0fNUWZx7k");
	String skOrdenSucursal = request.getParameter("kordensucursalfinal"); 
	String sEmail = request.getParameter("emailfinal"); 
	String sPassword = request.getParameter("passwordfinal");
	String strimagen = request.getParameter("strimagenfinal");
	String body;
// 	switch (captchas.check(strimagen)) {
// 	  case 's':
// 	    body = "Session seems to be timed out or broken. ";
// 	    body += "Please try again or report error to administrator.";
// 	    break;
// 	  case 'm':
// 	    body = "Every CAPTCHA can only be used once. ";
// 	    body += "The current CAPTCHA has already been used. ";
// 	    body += "Please use back button and reload";
// 	    break;
// 	  case 'w':
/* 		response.sendRedirect("/web2labportal/jsp/consultaresultados/paso-1.jsp?kordensucursal=" + skOrdenSucursal +"&email=" + sEmail + "&password=" + sPassword + "&strmensaje=El mensaje de la imagen escrito es erroneo, Por favor escribe el mensaje identico.");    	*/
// 	    body = "";
// 	    break;
// 	  default:
		response.sendRedirect("/web2labportal/jsp/consultaresultados/confirmar-informacion.jsp?kordensucursalfinal=" + skOrdenSucursal +"&emailfinal=" + sEmail + "&passwordfinal=" + sPassword + "&datofiscalfinal=0");    	
 	    body = "";
// 	    break;
// 	}
%>
<%=body%>
</html>