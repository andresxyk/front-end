<%@ page session="true" language="java" import="mx.com.web2lab.tools.CaptchasDotNet" %>

<html>
  <head>
    <title>Acceso a la Facturacion Electronica</title>
  </head>
  <h1>Acceso a la Facturacion Electronica</h1>
<%
	String skOrdenSucursal = request.getParameter("kordensucursalfinal"); 
	String sEmail = request.getParameter("emailfinal"); 
	String sPassword = request.getParameter("passwordfinal");
	response.sendRedirect("/web2labportal/jsp/facturacionelectronica/confirmar-informacion.jsp?kordensucursalfinal=" + skOrdenSucursal +"&emailfinal=" + sEmail + "&passwordfinal=" + sPassword + "&datofiscalfinal=0");    	
%>
</html>