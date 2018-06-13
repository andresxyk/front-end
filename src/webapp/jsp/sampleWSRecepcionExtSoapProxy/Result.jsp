<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result </H1> <% request.getParameter("endpoint"); %>


<jsp:useBean id="sampleWSRecepcionExtSoapProxyid" scope="session" class="mx.com.vitamedica.vitamedica_webservices.WSRecepcionExtSoapProxy" />
<%
	if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleWSRecepcionExtSoapProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
	String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleWSRecepcionExtSoapProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp%>
<%
	}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
%>
        <%=tempResultreturnp3%>
        <%
        	}
                break;
                case 5:
                        gotMethod = true;
                        String endpoint_0id=  request.getParameter("endpoint8");
                            java.lang.String endpoint_0idTemp = null;
                        if(!endpoint_0id.equals("")){
                         endpoint_0idTemp  = endpoint_0id;
                        }
                        sampleWSRecepcionExtSoapProxyid.setEndpoint(endpoint_0idTemp);
                break;
                case 10:
                        gotMethod = true;
                        mx.com.vitamedica.vitamedica_webservices.recepcion.WSRecepcionExtSoap.WSRecepcionExtSoap getWSRecepcionExtSoap10mtemp = sampleWSRecepcionExtSoapProxyid.getWSRecepcionExtSoap();
                if(getWSRecepcionExtSoap10mtemp == null){
        %>
<%=getWSRecepcionExtSoap10mtemp %>
<%
}else{
        if(getWSRecepcionExtSoap10mtemp!= null){
        String tempreturnp11 = getWSRecepcionExtSoap10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String tipo_1id=  request.getParameter("tipo16");
            java.lang.String tipo_1idTemp = null;
        if(!tipo_1id.equals("")){
         tipo_1idTemp  = tipo_1id;
        }
        String elegibilidad_2id=  request.getParameter("elegibilidad18");
            java.lang.String elegibilidad_2idTemp = null;
        if(!elegibilidad_2id.equals("")){
         elegibilidad_2idTemp  = elegibilidad_2id;
        }
        java.lang.String buscaOrdenLaboratorio13mtemp = sampleWSRecepcionExtSoapProxyid.buscaOrdenLaboratorio(tipo_1idTemp,elegibilidad_2idTemp);
if(buscaOrdenLaboratorio13mtemp == null){
%>
<%=buscaOrdenLaboratorio13mtemp %>
<%
}else{
        String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(buscaOrdenLaboratorio13mtemp));
        %>
        <%= tempResultreturnp14 %>
        <%
}
break;
case 20:
        gotMethod = true;
        String tipo_3id=  request.getParameter("tipo23");
            java.lang.String tipo_3idTemp = null;
        if(!tipo_3id.equals("")){
         tipo_3idTemp  = tipo_3id;
        }
        String elegibilidad_4id=  request.getParameter("elegibilidad25");
            java.lang.String elegibilidad_4idTemp = null;
        if(!elegibilidad_4id.equals("")){
         elegibilidad_4idTemp  = elegibilidad_4id;
        }
        java.lang.String buscaServiciosPorRealizar20mtemp = sampleWSRecepcionExtSoapProxyid.buscaServiciosPorRealizar(tipo_3idTemp,elegibilidad_4idTemp);
if(buscaServiciosPorRealizar20mtemp == null){
%>
<%=buscaServiciosPorRealizar20mtemp %>
<%
}else{
        String tempResultreturnp21 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(buscaServiciosPorRealizar20mtemp));
        %>
        <%= tempResultreturnp21 %>
        <%
}
break;
case 27:
        gotMethod = true;
        String tipo_5id=  request.getParameter("tipo30");
            java.lang.String tipo_5idTemp = null;
        if(!tipo_5id.equals("")){
         tipo_5idTemp  = tipo_5id;
        }
        String proveedorConsulta_6id=  request.getParameter("proveedorConsulta32");
            java.lang.String proveedorConsulta_6idTemp = null;
        if(!proveedorConsulta_6id.equals("")){
         proveedorConsulta_6idTemp  = proveedorConsulta_6id;
        }
        String grupoID_7id=  request.getParameter("grupoID34");
            java.lang.String grupoID_7idTemp = null;
        if(!grupoID_7id.equals("")){
         grupoID_7idTemp  = grupoID_7id;
        }
        String proveedorLaboratorio_8id=  request.getParameter("proveedorLaboratorio36");
            java.lang.String proveedorLaboratorio_8idTemp = null;
        if(!proveedorLaboratorio_8id.equals("")){
         proveedorLaboratorio_8idTemp  = proveedorLaboratorio_8id;
        }
        String elegibilidad_9id=  request.getParameter("elegibilidad38");
            java.lang.String elegibilidad_9idTemp = null;
        if(!elegibilidad_9id.equals("")){
         elegibilidad_9idTemp  = elegibilidad_9id;
        }
        String ICD_10id=  request.getParameter("ICD40");
            java.lang.String ICD_10idTemp = null;
        if(!ICD_10id.equals("")){
         ICD_10idTemp  = ICD_10id;
        }
        String CPT_11id=  request.getParameter("CPT42");
            java.lang.String CPT_11idTemp = null;
        if(!CPT_11id.equals("")){
         CPT_11idTemp  = CPT_11id;
        }
        String fechaConsulta_12id=  request.getParameter("fechaConsulta44");
            java.lang.String fechaConsulta_12idTemp = null;
        if(!fechaConsulta_12id.equals("")){
         fechaConsulta_12idTemp  = fechaConsulta_12id;
        }
        String preautorizacion_13id=  request.getParameter("preautorizacion46");
            java.lang.String preautorizacion_13idTemp = null;
        if(!preautorizacion_13id.equals("")){
         preautorizacion_13idTemp  = preautorizacion_13id;
        }
        String unidades_14id=  request.getParameter("unidades48");
        int unidades_14idTemp  = Integer.parseInt(unidades_14id);
        java.lang.String validaServicioPorRealizar27mtemp = sampleWSRecepcionExtSoapProxyid.validaServicioPorRealizar(tipo_5idTemp,proveedorConsulta_6idTemp,grupoID_7idTemp,proveedorLaboratorio_8idTemp,elegibilidad_9idTemp,ICD_10idTemp,CPT_11idTemp,fechaConsulta_12idTemp,preautorizacion_13idTemp,unidades_14idTemp);
if(validaServicioPorRealizar27mtemp == null){
%>
<%=validaServicioPorRealizar27mtemp %>
<%
}else{
        String tempResultreturnp28 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(validaServicioPorRealizar27mtemp));
        %>
        <%= tempResultreturnp28 %>
        <%
}
break;
case 50:
        gotMethod = true;
        String tipo_15id=  request.getParameter("tipo53");
            java.lang.String tipo_15idTemp = null;
        if(!tipo_15id.equals("")){
         tipo_15idTemp  = tipo_15id;
        }
        String proveedorConsulta_16id=  request.getParameter("proveedorConsulta55");
            java.lang.String proveedorConsulta_16idTemp = null;
        if(!proveedorConsulta_16id.equals("")){
         proveedorConsulta_16idTemp  = proveedorConsulta_16id;
        }
        String grupoID_17id=  request.getParameter("grupoID57");
            java.lang.String grupoID_17idTemp = null;
        if(!grupoID_17id.equals("")){
         grupoID_17idTemp  = grupoID_17id;
        }
        String proveedorLaboratorio_18id=  request.getParameter("proveedorLaboratorio59");
            java.lang.String proveedorLaboratorio_18idTemp = null;
        if(!proveedorLaboratorio_18id.equals("")){
         proveedorLaboratorio_18idTemp  = proveedorLaboratorio_18id;
        }
        String elegibilidad_19id=  request.getParameter("elegibilidad61");
            java.lang.String elegibilidad_19idTemp = null;
        if(!elegibilidad_19id.equals("")){
         elegibilidad_19idTemp  = elegibilidad_19id;
        }
        String ICD_20id=  request.getParameter("ICD63");
            java.lang.String ICD_20idTemp = null;
        if(!ICD_20id.equals("")){
         ICD_20idTemp  = ICD_20id;
        }
        String CPT_21id=  request.getParameter("CPT65");
            java.lang.String CPT_21idTemp = null;
        if(!CPT_21id.equals("")){
         CPT_21idTemp  = CPT_21id;
        }
        String fechaConsulta_22id=  request.getParameter("fechaConsulta67");
            java.lang.String fechaConsulta_22idTemp = null;
        if(!fechaConsulta_22id.equals("")){
         fechaConsulta_22idTemp  = fechaConsulta_22id;
        }
        String preautorizacion_23id=  request.getParameter("preautorizacion69");
            java.lang.String preautorizacion_23idTemp = null;
        if(!preautorizacion_23id.equals("")){
         preautorizacion_23idTemp  = preautorizacion_23id;
        }
        String unidades_24id=  request.getParameter("unidades71");
        int unidades_24idTemp  = Integer.parseInt(unidades_24id);
        java.lang.String generaReclamacion50mtemp = sampleWSRecepcionExtSoapProxyid.generaReclamacion(tipo_15idTemp,proveedorConsulta_16idTemp,grupoID_17idTemp,proveedorLaboratorio_18idTemp,elegibilidad_19idTemp,ICD_20idTemp,CPT_21idTemp,fechaConsulta_22idTemp,preautorizacion_23idTemp,unidades_24idTemp);
if(generaReclamacion50mtemp == null){
%>
<%=generaReclamacion50mtemp %>
<%
}else{
        String tempResultreturnp51 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(generaReclamacion50mtemp));
        %>
        <%= tempResultreturnp51 %>
        <%
}
break;
case 73:
        gotMethod = true;
        String tipo_25id=  request.getParameter("tipo76");
            java.lang.String tipo_25idTemp = null;
        if(!tipo_25id.equals("")){
         tipo_25idTemp  = tipo_25id;
        }
        String proveedorLaboratorio_26id=  request.getParameter("proveedorLaboratorio78");
            java.lang.String proveedorLaboratorio_26idTemp = null;
        if(!proveedorLaboratorio_26id.equals("")){
         proveedorLaboratorio_26idTemp  = proveedorLaboratorio_26id;
        }
        String elegibilidad_27id=  request.getParameter("elegibilidad80");
            java.lang.String elegibilidad_27idTemp = null;
        if(!elegibilidad_27id.equals("")){
         elegibilidad_27idTemp  = elegibilidad_27id;
        }
        String CPT_28id=  request.getParameter("CPT82");
            java.lang.String CPT_28idTemp = null;
        if(!CPT_28id.equals("")){
         CPT_28idTemp  = CPT_28id;
        }
        String reclamacion_29id=  request.getParameter("reclamacion84");
            java.lang.String reclamacion_29idTemp = null;
        if(!reclamacion_29id.equals("")){
         reclamacion_29idTemp  = reclamacion_29id;
        }
        java.lang.String cancelaServicios73mtemp = sampleWSRecepcionExtSoapProxyid.cancelaServicios(tipo_25idTemp,proveedorLaboratorio_26idTemp,elegibilidad_27idTemp,CPT_28idTemp,reclamacion_29idTemp);
if(cancelaServicios73mtemp == null){
%>
<%=cancelaServicios73mtemp %>
<%
}else{
        String tempResultreturnp74 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(cancelaServicios73mtemp));
        %>
        <%= tempResultreturnp74 %>
        <%
}
break;
case 86:
        gotMethod = true;
        String tipo_30id=  request.getParameter("tipo89");
            java.lang.String tipo_30idTemp = null;
        if(!tipo_30id.equals("")){
         tipo_30idTemp  = tipo_30id;
        }
        String grupoID_31id=  request.getParameter("grupoID91");
            java.lang.String grupoID_31idTemp = null;
        if(!grupoID_31id.equals("")){
         grupoID_31idTemp  = grupoID_31id;
        }
        String nomina_poliza_32id=  request.getParameter("nomina_poliza93");
            java.lang.String nomina_poliza_32idTemp = null;
        if(!nomina_poliza_32id.equals("")){
         nomina_poliza_32idTemp  = nomina_poliza_32id;
        }
        String certificado_33id=  request.getParameter("certificado95");
            java.lang.String certificado_33idTemp = null;
        if(!certificado_33id.equals("")){
         certificado_33idTemp  = certificado_33id;
        }
        String beneficiario_34id=  request.getParameter("beneficiario97");
        int beneficiario_34idTemp  = Integer.parseInt(beneficiario_34id);
        java.lang.String buscaDatosUsuario86mtemp = sampleWSRecepcionExtSoapProxyid.buscaDatosUsuario(tipo_30idTemp,grupoID_31idTemp,nomina_poliza_32idTemp,certificado_33idTemp,beneficiario_34idTemp);
if(buscaDatosUsuario86mtemp == null){
%>
<%=buscaDatosUsuario86mtemp %>
<%
}else{
        String tempResultreturnp87 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(buscaDatosUsuario86mtemp));
        %>
        <%= tempResultreturnp87 %>
        <%
}
break;
case 99:
        gotMethod = true;
        String elegibilidad_35id=  request.getParameter("elegibilidad102");
            java.lang.String elegibilidad_35idTemp = null;
        if(!elegibilidad_35id.equals("")){
         elegibilidad_35idTemp  = elegibilidad_35id;
        }
        java.lang.String limpiaServiciosPorRealizar99mtemp = sampleWSRecepcionExtSoapProxyid.limpiaServiciosPorRealizar(elegibilidad_35idTemp);
if(limpiaServiciosPorRealizar99mtemp == null){
%>
<%=limpiaServiciosPorRealizar99mtemp %>
<%
}else{
        String tempResultreturnp100 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(limpiaServiciosPorRealizar99mtemp));
        %>
        <%= tempResultreturnp100 %>
        <%
}
break;
case 104:
        gotMethod = true;
        String reclamacion_36id=  request.getParameter("reclamacion107");
            java.lang.String reclamacion_36idTemp = null;
        if(!reclamacion_36id.equals("")){
         reclamacion_36idTemp  = reclamacion_36id;
        }
        String cveProveedor_37id=  request.getParameter("cveProveedor109");
            java.lang.String cveProveedor_37idTemp = null;
        if(!cveProveedor_37id.equals("")){
         cveProveedor_37idTemp  = cveProveedor_37id;
        }
        String cveSucursal_38id=  request.getParameter("cveSucursal111");
            java.lang.String cveSucursal_38idTemp = null;
        if(!cveSucursal_38id.equals("")){
         cveSucursal_38idTemp  = cveSucursal_38id;
        }
        java.lang.String registraServiciosProveedor104mtemp = sampleWSRecepcionExtSoapProxyid.registraServiciosProveedor(reclamacion_36idTemp,cveProveedor_37idTemp,cveSucursal_38idTemp);
if(registraServiciosProveedor104mtemp == null){
%>
<%=registraServiciosProveedor104mtemp %>
<%
}else{
        String tempResultreturnp105 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(registraServiciosProveedor104mtemp));
        %>
        <%= tempResultreturnp105 %>
        <%
}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>