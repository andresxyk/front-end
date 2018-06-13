<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="mx.com.web2lab.backend.dao.ap.PacientesDao,mx.com.web2lab.backend.beans.ap.PacienteBean" errorPage="/jsp/error.jsp"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Envio Correo de Bienvenida al ECE</title>
</head>
<body>
	<%
		String kPaciente = request.getParameter("kPaciente");
		PacientesDao objDAOPaciente = new PacientesDao();
		PacienteBean objPacienteBean = new PacienteBean();
		objPacienteBean.setKpacientefundacion(new Integer(kPaciente));				
		objDAOPaciente.enviarCorreoBienvenidaECEPaciente(objPacienteBean);
		objDAOPaciente = null;
		objPacienteBean = null;
	%>
</body>
</html>