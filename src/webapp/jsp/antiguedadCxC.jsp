<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="mx.com.web2lab.backend.dao.comer.PagoFacturaDao" errorPage="/jsp/error.jsp"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Antiguedad de Saldos CxC</title>
</head>
<body>
	<%
		String strBody = "";
	    PagoFacturaDao objPagoFacturaDao = new PagoFacturaDao();
	    strBody = objPagoFacturaDao.getAntiguedadCxC();
	    objPagoFacturaDao = null;
		String exportToExcel = request.getParameter("exportToExcel");
		if (exportToExcel != null
				&& exportToExcel.toString().equalsIgnoreCase("YES")) {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "inline; filename="
					+ "excel.xls");

		}
	%>
	<%
		if (exportToExcel == null) {
	%>
	<a href="antiguedadCxC.jsp?exportToExcel=YES">Exportar a Excel</a>
	<%
		}
	%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<table align="left" border="2">
		<thead>
			<tr bgcolor="lightgreen">
				<th>#</th>
				<th>serie</th>
				<th>cliente</th>
				<th>razonsocial</th>
				<th>convenio</th>
				<th>nombre</th>
				<th>registro</th>
				<th>vencido</th>
				<th>diasvencido</th>
				<th>totalfactura</th>
				<th>pagado</th>
				<th>saldo</th>
				<th>dentroplazo</th>
				<th>m01a30</th>
				<th>m31a60</th>
				<th>m61a90</th>
				<th>Mas90</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<%=strBody%>
		</tbody>
	</table>
</body>
</html>