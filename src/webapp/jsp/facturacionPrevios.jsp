<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
import="mx.com.web2lab.backend.dao.facturacion.mayoreo.FacturacionPrevioDao,java.util.Date,mx.com.web2lab.backend.beans.facturacion.Definitivo,mx.com.web2lab.backend.beans.facturacion.FacturacionBean;" errorPage="/jsp/error.jsp"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Previos Facturaci&oacute;n</title>
</head>

<script>
function init(kfactura){
	window.opener.agregarKFactura(kfactura);
}
</script>
<body>
	<%
		Definitivo definitivo = null;
		FacturacionBean facturacionBean = null;git co
		
		int tipofactura = 0;
	 	String smetodopago = "";
	 	String nocuenta = "";
	 	String uuidSustitucion = "";
	 	boolean bSustitucion = false;
	 	boolean bDescuento = false;
	 	String descuentos = "";
	 	String notaDescuentos = "";
	 	boolean bRetencion = false;
	 	String descripcionfactura = "";
		int cmarca = 0;	
		
		String strBody = "";
		String strconvenio = request.getParameter("cconvenio");
		String struserid = request.getParameter("userid");
		String strbloques = request.getParameter("strbloques");
		String strtipoprevio = request.getParameter("tipoprevio");
		String strtipofacturacion = request.getParameter("btipofactura");
		String strmonto = request.getParameter("monto");
		String strrazon = request.getParameter("razon");
		if(strtipofacturacion == "1"){
			tipofactura = Integer.parseInt(request.getParameter("tipofactura"));
		 	smetodopago = request.getParameter("smetodopago");
		 	nocuenta = request.getParameter("nocuenta");
		 	uuidSustitucion = request.getParameter("uuidSustitucion");
		 	bSustitucion = Boolean.getBoolean(request.getParameter("bSustitucion"));
		 	bDescuento = Boolean.getBoolean(request.getParameter("bDescuento"));
		 	descuentos = request.getParameter("descuentos");
		 	notaDescuentos = request.getParameter("notaDescuentos");
		 	bRetencion = Boolean.getBoolean(request.getParameter("bRetencion"));
		 	descripcionfactura = request.getParameter("descripcionfactura");
			cmarca = 0;			
		}
		
		FacturacionPrevioDao objfacturacionPrevioDao = new FacturacionPrevioDao();
		definitivo = objfacturacionPrevioDao.getPrevioFacturacion(strconvenio,struserid,strbloques,strtipoprevio,strtipofacturacion,strmonto,strrazon); 
		
		strBody = definitivo.getReporte();
		%>
		<script>
		init(<%= definitivo.getKfactura()%>)
		</script>
		<%
		String exportToExcel = "YES";
		if (exportToExcel != null
				&& exportToExcel.toString().equalsIgnoreCase("YES")) {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "inline; filename="
					+ strconvenio + "-" + new Date().getTime() + ".xls");

		}
		
		
		if(definitivo.getKfactura()>0){
			facturacionBean = objfacturacionPrevioDao.getFacturaById(definitivo.getKfactura());			
		}		
		if(facturacionBean != null){
			if(facturacionBean.getCmarca()==7){
				if(strrazon == "1"){
					cmarca = 7;
				}else if(strrazon == "2"){
					cmarca = 8;
				}
			}else{
				cmarca = facturacionBean.getCmarca();
			}
			String url = "http://10.20.26.6:8192/facturas/ordenes/empresas-anticipadas?" +
			"folio="+facturacionBean.getUfoliofactura()+"&tipofactura="+tipofactura+"&msubtotal="+facturacionBean.getMsubtotal()+"&miva="+facturacionBean.getMiva()+"&mtotal="+facturacionBean.getMtotal()+"&" +
			"strnocuenta="+nocuenta+"&strmetodopago="+smetodopago+"&uuidSustitucion="+uuidSustitucion+"&" +
			"sustitucion="+bSustitucion+"&descuento="+bDescuento+"&" +
			"descuentos="+descuentos+"&notaDescuento="+notaDescuentos+"&retencion="+bRetencion+"&marca="+cmarca+"&" +
			"descripcionFactura="+descripcionfactura;
			response.sendRedirect(url);
			
		}
		
		objfacturacionPrevioDao = null;
		
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