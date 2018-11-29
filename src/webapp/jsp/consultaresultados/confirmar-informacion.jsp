<%@ page session="true" language="java" import="mx.com.web2lab.ajax.dwr.capturaorden.DatosOrdenAjax,mx.com.web2lab.backend.beans.ap.OrdenBean,mx.com.web2lab.util.Formatos,org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory" %>
<!DOCTYPE html>
<html>
 <head>

  <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
  <meta name="generator" content="4.1.8.204"/>
  <title>Confirmar Informacion</title>
  <!-- CSS -->
  <link rel="stylesheet" type="text/css" href="/web2labportal/css/consultaresultados/site_global.css?3763450218"/>
  <link rel="stylesheet" type="text/css" href="/web2labportal/css/consultaresultados/master_b-p_gina-maestra.css?8104872"/>
  <link rel="stylesheet" type="text/css" href="/web2labportal/css/consultaresultados/confirmar-informacion.css?3771000282" id="pagesheet"/>
  <!-- Other scripts -->
  <script src="/web2labportal/javascript/generales.js" type="text/javascript" ></script>
  <script src="/web2labportal/javascript/consultaresultados/pantallas/confirmar-informacion.js" type="text/javascript"></script>  
  <script src='/web2labportal/dwr/interface/OrdenFacturarAjax.js' type='text/javascript' ></script>
  <script src='/web2labportal/dwr/engine.js' type='text/javascript' ></script>
  <script src='/web2labportal/dwr/util.js'   type='text/javascript' ></script>
  <script type="text/javascript">
  	document.documentElement.className += ' js';
  	window.onload = init;		
  </script>
   </head>
 <body>
   <% 
   		Log iObjLog = LogFactory.getLog("confirmar-informacion.jsp");	
		DatosOrdenAjax objDatosOrdenAjax = new DatosOrdenAjax();
		Formatos objFormatos = new Formatos();
		String skOrdenSucursal = request.getParameter("kordensucursalfinal").trim(); 
		String sEmail = request.getParameter("emailfinal").trim(); 
		String sPassword = request.getParameter("passwordfinal").trim();
		String sSucursal = "";
		String sPaciente = "";
		int kDatoFiscal = Integer.parseInt(request.getParameter("datofiscalfinal").trim());
		int cSucursal = 0;
		double dblTotalPagar = 0.0;
		String[] sPrint = {"",""};
		String strRazonSocial = "";
		String strRFC = "";
		String strCalle = "";
		String strColonia = "";
		String strCiudad = "";
		String strMunicipio = "";
		String strEstado = "";
		String strCodigoPostal = "";
		String strMsubTotal = "";
		String strMpagaPaciente = "";
		String strMacuenta = "";
		String strMadeuda = "";   
		String strEditarRFC = "";
		OrdenBean objOrdenBean = null;
   		try {
		      objOrdenBean = objDatosOrdenAjax.getOrdenFacturacionElectronicaInternet(Integer.parseInt(skOrdenSucursal),sPassword,kDatoFiscal);      
		      strEditarRFC = ("alta-rfc.jsp?kordensucursalfinal=" + skOrdenSucursal + "&emailfinal=" + sEmail + "&passwordfinal=" + sPassword);
		      if (objOrdenBean.getMadeuda() > 0 ) {
		    	  sPrint = objOrdenBean.getDatosDemograficosFacturacionElectronica(sEmail,"La orden tiene adeudos","No se puede mostrar sus Resultados");   	  
		      } else if (objOrdenBean.getObjdatosfiscalesbean().getkDatosFiscales() == 0 && objOrdenBean.getSmensajeerror().trim().length() == 0 && objOrdenBean.getStrFactura().trim().length() == 0) {
		    	  //response.sendRedirect("http://173.203.12.185:8081/ResultadosOlab/jsp/resultado/ServicioResultadosOlabECE.jsp?" + "kOrden=" + skOrdenSucursal +"&sPassword=" + sPassword);
		    	  response.sendRedirect("http://201.150.42.46:8081/ResultadosOlab/jsp/resultado/ServicioResultadosOlabECE.jsp?" + "kOrden=" + skOrdenSucursal +"&sPassword=" + sPassword);     	  
		      } else {    	  
		    	  //response.sendRedirect("http://173.203.12.185:8081/ResultadosOlab/jsp/resultado/ServicioResultadosOlabECE.jsp?" + "kOrden=" + skOrdenSucursal +"&sPassword=" + sPassword);
		    	  response.sendRedirect("http://201.150.42.46:8081/ResultadosOlab/jsp/resultado/ServicioResultadosOlabECE.jsp?" + "kOrden=" + skOrdenSucursal +"&sPassword=" + sPassword);     	  
		      }
		      kDatoFiscal = objOrdenBean.getObjdatosfiscalesbean().getkDatosFiscales();
		      cSucursal = objOrdenBean.getCsucursal();
		      dblTotalPagar = objOrdenBean.getMpagapaciente();
		      strRazonSocial = objOrdenBean.getObjdatosfiscalesbean().getStrRazonSocial();
		      strRFC = objOrdenBean.getObjdatosfiscalesbean().getStrRFC();
		      strCalle = objOrdenBean.getObjdatosfiscalesbean().getStrDireccion();
		      strColonia = objOrdenBean.getObjdatosfiscalesbean().getStrColonia();
		      strCiudad = objOrdenBean.getObjdatosfiscalesbean().getStrCiudad();
		      strMunicipio = objOrdenBean.getObjdatosfiscalesbean().getStrDelegacionMunicipio();
		      strEstado = objOrdenBean.getObjdatosfiscalesbean().getStrEstado();
		      strCodigoPostal = objOrdenBean.getObjdatosfiscalesbean().getcPostal();
		      strMsubTotal = objFormatos.formateaNumero(String.valueOf(objOrdenBean.getMsubtotal()));
		      strMpagaPaciente = objFormatos.formateaNumero(String.valueOf(objOrdenBean.getMpagapaciente()));
		      strMacuenta = objFormatos.formateaNumero(String.valueOf(objOrdenBean.getMacuenta()));
		      strMadeuda = objFormatos.formateaNumero(String.valueOf(objOrdenBean.getMadeuda())); 
		} catch (Exception exp) {
    	    iObjLog.error("Confirmar.informacion:Exception....", exp);
    	    throw exp;
   		}
   %>
  <input type="hidden" id="hdnEditarRFC" value=<%=strEditarRFC %> />
  <input type="hidden" id="hdnDatosFiscales" value=<%=kDatoFiscal %> />
  <input type="hidden" id="txtAdmision" name="txtAdmision" value=<%=skOrdenSucursal %> >	
  <input type="hidden" id="txtCorreoElectronico" name="txtCorreoElectronico" value=<%=sEmail %> >	
  <input type="hidden" id="IdSucursalActual" name="IdUnidadActual" value=<%=cSucursal %> >	
  <input type="hidden" id="dblTotalPagar" name="dblTotalPagar" value=<%=dblTotalPagar %> >	
  <input type="hidden" id="strRazonSocial" name="strRazonSocial" value=<%=strRazonSocial %> >	
  <input type="hidden" id="strRFC" name="strRFC" value=<%=strRFC %> >	
  <input type="hidden" id="strCalle" name="strCalle" value=<%=strCalle %> >	
  <input type="hidden" id="strColonia" name="strColonia" value=<%=strColonia %> >	
  <input type="hidden" id="strCiudad" name="strCiudad" value=<%=strCiudad %> >	
  <input type="hidden" id="strMunicipio" name="strMunicipio" value=<%=strMunicipio %> >	
  <input type="hidden" id="strEstado" name="strEstado" value=<%=strEstado %> >	
  <input type="hidden" id="strCodigoPostal" name="strCodigoPostal" value=<%=strCodigoPostal %> >	
  <div class="clearfix" id="page"><!-- column -->
   <div class="position_content" id="page_position_content">
    <div class="clearfix colelem" id="pu8066"><!-- group -->
     <div class="gradient rounded-corners clearfix grpelem" id="u8066"><!-- column -->
      <div class="position_content" id="u8066_position_content">
       <div class="clearfix colelem" id="pu8069"><!-- group -->
        <div class="grpelem" id="u8069"><!-- image -->
         <img class="block" id="u8069_img" src="/web2labportal/images/consultaresultados/logo%20olab.png" alt="" width="154" height="83"/>
        </div>
        <div class="clearfix grpelem" id="u8067"><!-- group -->
         <div class="clearfix grpelem" id="u8068-4"><!-- content -->
          <p>Consulta de Resultados</p>
         </div>
        </div>
       </div>
       <div class="clearfix colelem" id="u7884"><!-- group -->
        <div class="clearfix grpelem" id="u7887-4"><!-- content -->
         <p>Sucursal: <%=objOrdenBean.getSsucursal() %></p>
        </div>
       </div>
       <div class="clearfix colelem" id="u7889"><!-- group -->
        <div class="clearfix grpelem" id="u7888-4"><!-- content -->
         <p>Usuario: <%=objOrdenBean.getBpacientebean().getSappaterno() + " " + objOrdenBean.getBpacientebean().getSapmaterno() + " " + objOrdenBean.getBpacientebean().getSnombre() %></p>
        </div>
       </div>
       <div class="clearfix colelem" id="pu7908"><!-- group -->
        <div class="clearfix grpelem" id="u7908"><!-- column -->
          <div class="position_content" id="u7908_position_content">
           	<%=sPrint[0] %>
          <div class="clearfix colelem" id="pu7915">
          	<%=sPrint[1] %>
          </div>
         </div>
        </div>
        <div class="clearfix grpelem" id="u7886"><!-- column -->
         <div class="clearfix colelem" id="pu7895-4"><!-- group -->
          <div class="clearfix grpelem" id="u7895-4"><!-- content -->
           <p>Subtotal</p>
          </div>
          <div class="clearfix grpelem" id="u7890"><!-- group -->
           <div class="clearfix grpelem" id="u7924-4-BY"><!-- content -->
            <p>$ <%=strMsubTotal %></p>
           </div>
          </div>
         </div>
         <div class="clearfix colelem" id="pu7896-4"><!-- group -->
          <div class="clearfix grpelem" id="u7896-4"><!-- content -->
           <p>Descuento</p>
          </div>
          <div class="clearfix grpelem" id="u7891"><!-- group -->
           <div class="clearfix grpelem" id="u7926-4-BY"><!-- content -->
            <p>$0.00</p>
           </div>
          </div>
         </div>
         <div class="clearfix colelem" id="pu7897-4"><!-- group -->
          <div class="clearfix grpelem" id="u7897-4"><!-- content -->
           <p>Total a pagar</p>
          </div>
          <div class="clearfix grpelem" id="u7892"><!-- group -->
           <div class="clearfix grpelem" id="u7927-4-BY"><!-- content -->
            <p>$ <%=strMpagaPaciente %></p>
           </div>
          </div>
         </div>
         <div class="clearfix colelem" id="pu7898-4"><!-- group -->
          <div class="clearfix grpelem" id="u7898-4"><!-- content -->
           <p>A cuenta</p>
          </div>
          <div class="clearfix grpelem" id="u7893"><!-- group -->
           <div class="clearfix grpelem" id="u7928-4-BY"><!-- content -->
            <p>$ <%=strMacuenta %></p>
           </div>
          </div>
         </div>
         <div class="clearfix colelem" id="pu7899-4"><!-- group -->
          <div class="clearfix grpelem" id="u7899-4"><!-- content -->
           <p>Adeuda</p>
          </div>
          <div class="clearfix grpelem" id="u7894"><!-- group -->
           <div class="clearfix grpelem" id="u7929-4-BY"><!-- content -->
            <p>$ <%=strMadeuda %></p>
           </div>
          </div>
         </div>
        </div>
       </div>
      </div>
     </div>
     <div class="grpelem" id="u8020"><!-- image -->
      <img class="block" id="u8020_img" src="/web2labportal/images/consultaresultados/dro6.png" alt="" width="126" height="198"/>
     </div>
    </div>
    <div id="gridprogressbar">                
        <table border="0" cellspacing="2" cellpadding="3" width="100%" class="tabla">
            <td>
        		<p align=center><img src="/web2labportal/images/ajax-loader.gif" >	
            </td>
    	</table>
	</div>            	     
    <div class="browser_width colelem" id="u7728"><!-- group -->
     <div class="clearfix" id="u7728_align_to_page">
      <div class="clearfix grpelem" id="u7729-6"><!-- content -->
       <p>© 2013 Todos los Derechos Reservados</p>
       <p>Olab Diagnósticos Médicos</p>
      </div>
     </div>
    </div>
   </div>
  </div>
  <!-- JS includes -->
  <script type="text/javascript">
   if (document.location.protocol != 'https:') document.write('\x3Cscript src="/web2labportal/javascript/consultaresultados/jquery-1.8.3.min.js" type="text/javascript">\x3C/script>');
</script>
  <script type="text/javascript">
   window.jQuery || document.write('\x3Cscript src="/web2labportal/javascript/consultaresultados/jquery-1.8.3.min.js" type="text/javascript">\x3C/script>');
</script>
  <script src="/web2labportal/javascript/consultaresultados/museutils.js?4215913851" type="text/javascript"></script>
  <script src="/web2labportal/javascript/consultaresultados/jquery.tobrowserwidth.js?4052033419" type="text/javascript"></script>
  <!-- Other scripts -->
  <script type="text/javascript">
   Muse.Utils.addSelectorFn('body', Muse.Utils.transformMarkupToFixBrowserProblemsPreInit);/* body */
$(document).ready(function() { $('.browser_width').toBrowserWidth(); });/* browser width elements */
Muse.Utils.addSelectorFn('body', Muse.Utils.prepHyperlinks); /* body */
Muse.Utils.addSelectorFn('body', Muse.Utils.showWidgetsWhenReady);/* body */
Muse.Utils.addSelectorFn('body', Muse.Utils.transformMarkupToFixBrowserProblems);/* body */

</script>
   </body>
</html>
