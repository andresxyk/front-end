<!DOCTYPE html>
<html>
 <head>

  <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
  <meta name="generator" content="4.1.8.204"/>
  <title>Facturacion Exitosa</title>
  <!-- CSS -->
  <link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronicaBackup/site_global.css?3763450218"/>
  <link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronicaBackup/master_b-p_gina-maestra.css?8104872"/>
  <link rel="stylesheet" type="text/css" href="/web2labportal/css/facturacionelectronicaBackup/facturacion-exitosa.css?363365112" id="pagesheet"/>
  <!-- Other scripts -->
  <script type="text/javascript">
   document.documentElement.className += ' js';
</script>
   </head>
 <body>
   <% 
   	  String sURLFacturacionElectronica = request.getParameter("urlFactura"); 
   %>
  <div class="clearfix" id="page"><!-- column -->
   <div class="position_content" id="page_position_content">
    <div class="gradient rounded-corners clearfix colelem" id="u8066"><!-- column -->
     <div class="position_content" id="u8066_position_content">
      <div class="clearfix colelem" id="pu8069"><!-- group -->
       <div class="grpelem" id="u8069"><!-- image -->
        <img class="block" id="u8069_img" src="/web2labportal/images/facturacionelectronicaBackup/logo%20olab.png" alt="" width="154" height="83"/>
       </div>
       <div class="clearfix grpelem" id="u8067"><!-- group -->
        <div class="clearfix grpelem" id="u8068-4"><!-- content -->
         <p>Facturaci&oacute;n Electr&oacute;nica</p>
        </div>
       </div>
      </div>
      <div class="clearfix colelem" id="u8055"><!-- group -->
       <div class="clearfix grpelem" id="u8056-4"><!-- content -->
        <p>¡Su factura se ha generado con &eacute;xito!</p>        
       </div>
      </div>
      <div class="clearfix colelem" id="pu8044"><!-- group -->
       <div class="grpelem" id="u8044"><!-- image -->
        <img class="block" id="u8044_img" src="/web2labportal/images/facturacionelectronicaBackup/dro5.png" alt="" width="144" height="180"/>
       </div>
       <div class="clearfix grpelem" id="pu8126"><!-- column -->
        <div class="rgba-background rounded-corners clearfix colelem" id="u8126"><!-- group -->
         <div class="grpelem" id="u8121"><!-- image -->
          <img class="block" id="u8121_img" src="/web2labportal/images/facturacionelectronicaBackup/factura.jpg" alt="" width="338" height="439"/>
         </div>
        </div>
       </div>
      	<a class="nonblock nontext rgba-background rounded-corners clearfix grpelem" id="u8050" href='http://192.237.150.66:9085/FacturasElectronicas_Olab/PDF/FacturacionElectronica_<%=sURLFacturacionElectronica%>.pdf'> <!-- group --><div class="clearfix grpelem" id="u8052-4"><!-- content --><p>Descargar PDF</p></div></a>
      	<a class="nonblock nontext rgba-background rounded-corners clearfix grpelem" id="u80509" href='http://192.237.150.66:9085/FacturasElectronicas_Olab/XML/FacturacionElectronica_<%=sURLFacturacionElectronica%>.xml'> <!-- group --><div class="clearfix grpelem" id="u8052-49"><!-- content --><p>Descargar XML</p></div></a>
      </div>
     </div>
    </div>
    <div class="browser_width colelem" id="u7728"><!-- group -->
     <div class="clearfix" id="u7728_align_to_page">
      <div class="clearfix grpelem" id="u7729-6"><!-- content -->
       <p>© 2013 Todos los Derechos Reservados</p>
       <p>Olab Diagn&oacute;sticos M&eacute;dicos</p>
      </div>
     </div>
    </div>
   </div>
  </div>
  <!-- JS includes -->
  <script type="text/javascript">
   if (document.location.protocol != 'https:') document.write('\x3Cscript src="http://musecdn2.businesscatalyst.com/scripts/4.0/jquery-1.8.3.min.js" type="text/javascript">\x3C/script>');
</script>
  <script type="text/javascript">
   window.jQuery || document.write('\x3Cscriptsrc="/web2labportal/javascript/facturacionelectronica/jquery-1.8.3.min.js" type="text/javascript">\x3C/script>');
</script>
  <scriptsrc="/web2labportal/javascript/facturacionelectronica/museutils.js?4215913851" type="text/javascript"></script>
  <scriptsrc="/web2labportal/javascript/facturacionelectronica/jquery.tobrowserwidth.js?4052033419" type="text/javascript"></script>
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
