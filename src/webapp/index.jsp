<html>
<head>
<%
	String objStrServer = request.getServerName();
	objStrServer += ":" + request.getServerPort();
	String objContextRoot = request.getContextPath();
%>
<script language="JavaScript">

        var servidor = "<%=objStrServer%>";
        var applicationName = "<%=objContextRoot%>"
        var opciones = "";
        var ancho = (screen.availWidth -50 );
        var alto  = (screen.availHeight -70 );
        opciones += "directories=no, hotkeys=yes, location=no, menubar=no, ";
        opciones += "personalbar=no, resizable=yes, screenX=0, screenY=0, ";
        opciones += "status=yes, fullscreen=yes, toolbar=no, scrollbars=yes, ";
        opciones += "width=" + ancho + ", height="+ alto +" ";
        window.open("http://" + servidor + applicationName + "/servlet/template/web2lab,seguridad,ExpedienteElectronico.vm", 'new', opciones);
        window.opener=null;
        window.close();
</script>

<object id="closeWin" type="application/x-oleobject" classid="clsid:adb880a6-d8ff-11cf-9377-00aa003b7a11">
</head>
<body>
</body>
</html>