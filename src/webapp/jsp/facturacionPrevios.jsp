<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionPrevioDao,java.util.Date;" errorPage="/jsp/error.jsp"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Previos Facturación</title>
</head>
<body>
	<%
		String strBody = "";
		String strconvenio = request.getParameter("cconvenio");
		String struserid = request.getParameter("userid");
		String strbloques = request.getParameter("strbloques");
		String strtipoprevio = request.getParameter("tipoprevio");
		String strtipofacturacion = request.getParameter("btipofactura");
		String strmonto = request.getParameter("monto");
		
		
		
		FacturacionPrevioDao objfacturacionPrevioDao = new FacturacionPrevioDao();
	    strBody = objfacturacionPrevioDao.getPrevioFacturacion(strconvenio,struserid,strbloques,strtipoprevio,strtipofacturacion,strmonto);
	    objfacturacionPrevioDao = null;
		String exportToExcel = "YES";
		if (exportToExcel != null
				&& exportToExcel.toString().equalsIgnoreCase("YES")) {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "inline; filename="
					+ strconvenio + "-" + new Date().getTime() + ".xls");

		}
	%>
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<table align="left" border="2">
		<thead>
			<tr bgcolor="lightblue">
				<th>#</th>
				<% if(strtipoprevio.equals("1")) { %>
					<th>ufoliofactura</th>
					<th>kfactura</th>
					<th>kordensucursal</th>
					<th>kordensucrusalfac</th>
					<th>kpaciente</th>
					<th>cconvenio</th>
					<th>snombrepaciente</th>
					<th>cexamen</th>
					<th>sexamen</th>
					<th>msubtotal</th>
					<th>miva</th>
					<th>mtotal</th>
					<th>uconsecutivo</th>
					<th>dregistro</th>
					<th>sclasificacioncomercial</th>
					<th>sdatoadicional1</th>
					<th>sdatoadicional2</th>
					<th>sdatoadicional3</th>
					<th>sdatoadicional4</th>
					<th>sdatoadicional5</th>
					<th>sdatoadicional6</th>
					<th>sdatoadicional7</th>
					<th>sdatoadicional8</th>
					<th>sdatoadicional9</th>
					<th>sdatoadicional10</th>
				<% } else if(strtipoprevio.equals("2")) {%>
					<th>ufoliofactura</th>
					<th>kfactura</th>
					<th>kordensucursal</th>
					<th>kordensucrusalfac</th>
					<th>cconvenio</th>
					<th>snombrepaciente</th>
					<th>msubtotal</th>
					<th>miva</th>
					<th>mtotal</th>
					<th>uconsecutivo</th>
					<th>dregistro</th>
					<th>sdatoadicional1</th>
					<th>sdatoadicional2</th>
					<th>sdatoadicional3</th>
					<th>sdatoadicional4</th>
					<th>sdatoadicional5</th>
					<th>sdatoadicional6</th>
					<th>sdatoadicional7</th>
					<th>sdatoadicional8</th>
					<th>sdatoadicional9</th>
					<th>sdatoadicional10</th>
				<%} else if(strtipoprevio.equals("3")) {%>
					<th>ufoliofactura</th>
					<th>kfactura</th>
					<th>cconvenio</th>
					<th>cexamen</th>
					<th>cantidad</th>
					<th>sexamen</th>
					<th>msubtotal</th>
					<th>miva</th>
					<th>mtotal</th>
					<th>uconsecutivo</th>
				<%}  %>
				
			</tr>
		</thead>
		<tbody>
			<%=strBody%>
		</tbody>
	</table>
</body>
</html>