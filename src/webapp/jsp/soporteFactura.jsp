<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="mx.com.web2lab.backend.dao.facturacion.mayoreo.BusquedaFacturaDao" errorPage="/jsp/error.jsp"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Previos Facturaci&oacute;n</title>
</head>
<body>
	<%
		String strBody = "";
		String strkfactura = request.getParameter("kfactura");
		
		
		BusquedaFacturaDao objfactura = new BusquedaFacturaDao();
	    strBody = objfactura.getSoporteFactura(strkfactura);
	    objfactura = null;
		String exportToExcel = "YES";
		if (exportToExcel != null
				&& exportToExcel.toString().equalsIgnoreCase("YES")) {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "inline; filename="
					+ "excel.xls");

		}
	%>
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<table align="left" border="2">
		<thead>
			<tr bgcolor="lightblue">
					<th>#</th>
					<th>kordensucursal</th>
					<th>cconvenio</th>
					<th>snombrepaciente</th>
					<th>cexamen</th>
					<th>sexamen</th>
					<th>mtotal</th>
					<th>dregistro</th>
					<th>sclasificacioncomercial</th>
					<th>ufoliofactura</th>
			</tr>
		</thead>
		<tbody>
			<%=strBody%>
		</tbody>
	</table>
</body>
</html>