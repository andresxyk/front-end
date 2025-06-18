<!DOCTYPE html>
<html>
 <head>

  <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
  <meta name="generator" content="4.1.8.204"/>
  <title>Alta RFC</title>
  <!-- CSS -->
  <link rel="stylesheet" type="text/css" href="/web2labportal/css/consultaresultados/site_global.css?3763450218"/>
  <link rel="stylesheet" type="text/css" href="/web2labportal/css/consultaresultados/master_b-p_gina-maestra.css?199516088"/>
  <link rel="stylesheet" type="text/css" href="/web2labportal/css/consultaresultados/alta-rfc.css?6823712" id="pagesheet"/>
  <!-- Other scripts -->
  <script src="/web2labportal/javascript/generales.js" type="text/javascript" ></script>
  <script src="/web2labportal/javascript/consultaresultados/pantallas/alta-rfc.js" type="text/javascript"></script>  
  <script src='/web2labportal/dwr/interface/DatosFiscales.js' type='text/javascript' ></script>
  <script src='/web2labportal/dwr/engine.js' type='text/javascript' ></script>
  <script src='/web2labportal/dwr/util.js'   type='text/javascript' ></script>
  <script type="text/javascript">
  		document.documentElement.className += ' js';
  </script>
  </head>
  
  <script src="/web2labportal/javascript/tooljquery/jquery-1.7.2.min.js" type="text/javascript" ></script>
  <script src="/web2labportal/javascript/consultaresultados/pantallas/alta-rfc-jq.js" type="text/javascript"></script>  
  
 <body>
   <% 
		String skOrdenSucursal = request.getParameter("kordensucursalfinal").trim(); 
		String sEmail = request.getParameter("emailfinal").trim(); 
		String sPassword = request.getParameter("passwordfinal").trim();
   %>
 
  <input type="hidden" id="hdnDatosFiscales" value="0"/>
  <input type="hidden" id="txtAdmision" name="txtAdmision" value=<%=skOrdenSucursal %> >	
  <input type="hidden" id="txtCorreoElectronico" name="txtCorreoElectronico" value=<%=sEmail %> >	
  <input type="hidden" id="txtPassword" value=<%=sPassword %>   >

  <div class="clearfix" id="page"><!-- column -->
   <div class="position_content" id="page_position_content">
    <div class="clearfix colelem" id="pu8066"><!-- group -->
     <div class="gradient rounded-corners clearfix grpelem" id="u8066"><!-- column -->
      <div class="position_content" id="u8066_position_content">
       <div class="clearfix colelem" id="pu8069"><!-- group -->
        <div class="grpelem" id="u8069"><!-- image -->
         <img class="block" id="u8069_img" src="/web2labportal/images/consultaresultados/logo%20olab.png" alt="" width="154" height="83"/>
        </div>
        <div class="clearfix grpelem" id="pu8067"><!-- column -->
         <div class="clearfix colelem" id="u8067"><!-- group -->
          <div class="clearfix grpelem" id="u8068-4"><!-- content -->
           <p>Consulta de Resultados</p>
          </div>
         </div>
         <div class="clearfix colelem" id="u8053"><!-- group -->
          <div class="clearfix grpelem" id="u6586-4"><!-- content -->
           <p>Por favor, reg&iacute;strese con sus datos fiscales</p>
          </div>
         </div>
        </div>
       </div>
       <div class="colelem" id="u6638"><!-- image -->
        <img class="block" id="u6638_img" src="/web2labportal/images/consultaresultados/dr%202.png" alt="" width="643" height="440"/>
       </div>
      </div>
     </div>
     <div id="loescrito"></div>
     <div class="grpelem" id="u8014"><!-- simple frame --></div>
     <form class="form-grp clearfix grpelem" id="widgetu7824" method="post" enctype="multipart/form-data" onSubmit="altaDatosFiscales();"><!-- group -->
      <div class="fld-grp clearfix grpelem" id="widgetu7841" data-required="true"><!-- group -->
       <label class="fld-label actAsDiv clearfix grpelem" id="u7844-4" for="widgetu7841_input"><!-- content -->
        <span class="actAsPara">Nombre o Raz&oacute;n Social</span>
       </label>
       <span class="fld-input NoWrap actAsDiv clearfix grpelem" id="u7842-3"><!-- content --><input class="wrapped-input" type="text" spellcheck="false" id="widgetu7846_input" name="FormFieldName" tabindex="2"/></span>
      </div>
      <div class="fld-grp clearfix grpelem" id="widgetu7846" data-required="true" data-type="email"><!-- group -->
       <label class="fld-label actAsDiv clearfix grpelem" id="u7849-4" for="widgetu7846_input"><!-- content -->
        <span class="actAsPara">RFC</span>
       </label>
       <span class="fld-input NoWrap actAsDiv clearfix grpelem" id="u7847-3"><!-- content --><input class="wrapped-input" type="text" spellcheck="false" id="widgetu7841_input" name="FormFieldName" tabindex="1"/></span>
      </div>
      <div class="clearfix grpelem" id="u7857-4"><!-- content -->
       <p>Enviando formulario...</p>
      </div>
      <div class="clearfix grpelem" id="u7840-4"><!-- content -->
       <p>El servidor ha detectado un error.</p>
      </div>
      <div class="clearfix grpelem" id="u7839-4"><!-- content -->
       <p>Formulario recibido.</p>
      </div>
      <input class="submit-btn NoWrap grpelem" id="u7856-17" type="submit" value="" tabindex="8"/><!-- state-based BG images -->
      <div class="fld-grp clearfix grpelem" id="widgetu7987" data-required="true"><!-- group -->
       <label class="fld-label actAsDiv clearfix grpelem" id="u7989-4" for="widgetu7987_input"><!-- content -->
        <span class="actAsPara">Direcci&oacute;n</span>
       </label>
       <span class="fld-input NoWrap actAsDiv clearfix grpelem" id="u7988-3"><!-- content --><input class="wrapped-input" type="text" id="widgetu7987_input" name="FormFieldName" tabindex="3"/></span>
      </div>
      <div class="fld-grp clearfix grpelem" id="widgetu7992" data-required="true"><!-- group -->
       <label class="fld-label actAsDiv clearfix grpelem" id="u7995-4" for="widgetu7992_input"><!-- content -->
        <span class="actAsPara">Colonia</span>
       </label>
       <span class="fld-input NoWrap actAsDiv clearfix grpelem" id="u7996-3"><!-- content --><input class="wrapped-input" type="text" id="widgetu7992_input" name="FormFieldName" tabindex="4"/></span>
      </div>
      <div class="fld-grp clearfix grpelem" id="widgetu7997" data-required="true"><!-- group -->
       <label class="fld-label actAsDiv clearfix grpelem" id="u8000-4" for="widgetu7997_input"><!-- content -->
        <span class="actAsPara">Delegaci&oacute;n o Municipio</span>
       </label>
       <span class="fld-input NoWrap actAsDiv clearfix grpelem" id="u7999-3"><!-- content --><input class="wrapped-input" type="text" id="widgetu7997_input" name="FormFieldName" tabindex="5"/></span>
      </div>
      <div class="fld-grp clearfix grpelem" id="widgetu8003" data-required="true"><!-- group -->
       <label class="fld-label actAsDiv clearfix grpelem" id="u8004-4" for="widgetu8003_input"><!-- content -->
        <span class="actAsPara">Estado</span>
       </label>
       <span class="fld-input NoWrap actAsDiv clearfix grpelem" id="u8007-3"><!-- content --><input class="wrapped-input" type="text" id="widgetu8003_input" name="FormFieldName" tabindex="6"/></span>
      </div>
      <div class="fld-grp clearfix grpelem" id="widgetu8008" data-required="true"><!-- group -->
       <label class="fld-label actAsDiv clearfix grpelem" id="u8010-4" for="widgetu8008_input"><!-- content -->
        <span class="actAsPara">C&oacute;digo Postal</span>
       </label>
       <span class="fld-input NoWrap actAsDiv clearfix grpelem" id="u8011-3"><!-- content --><input class="wrapped-input" type="text" id="widgetu8008_input" name="FormFieldName" tabindex="7"/></span>
      </div>
     </form>
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
  <div class="preload_images">
   <img class="preload" src="/web2labportal/images/consultaresultados/u7856-17-r.png" alt=""/>
   <img class="preload" src="/web2labportal/images/consultaresultados/u7856-17-m.png" alt=""/>
   <img class="preload" src="/web2labportal/images/consultaresultados/u7856-17-fs.png" alt=""/>
  </div>
  <!-- JS includes -->
  <script type="text/javascript">
   if (document.location.protocol != 'https:') document.write('\x3Cscript src="http://musecdn2.businesscatalyst.com/scripts/4.0/jquery-1.8.3.min.js" type="text/javascript">\x3C/script>');
</script>
  <script type="text/javascript">
   window.jQuery || document.write('\x3Cscript src="/web2labportal/javascript/consultaresultados/jquery-1.8.3.min.js" type="text/javascript">\x3C/script>');
</script>
  <script src="/web2labportal/javascript/consultaresultados/museutils.js?4215913851" type="text/javascript"></script>
  <script src="/web2labportal/javascript/consultaresultados/webpro.js?4060715896" type="text/javascript"></script>
  <script src="/web2labportal/javascript/consultaresultados/jquery.tobrowserwidth.js?4052033419" type="text/javascript"></script>
  <!-- Other scripts -->
  <script type="text/javascript">
   Muse.Utils.addSelectorFn('body', Muse.Utils.transformMarkupToFixBrowserProblemsPreInit);/* body */
$(document).ready(function() { $('.browser_width').toBrowserWidth(); });/* browser width elements */
Muse.Utils.addSelectorFn('body', Muse.Utils.prepHyperlinks); /* body */
Muse.Utils.addSelectorFn('#widgetu7824', function(elem) { new WebPro.Widget.Form(elem, {validationEvent:'submit',errorStateSensitivity:'high',fieldWrapperClass:'fld-grp',formSubmittedClass:'frm-sub-st',formErrorClass:'frm-subm-err-st',formDeliveredClass:'frm-subm-ok-st',notEmptyClass:'non-empty-st',focusClass:'focus-st',invalidClass:'fld-err-st',requiredClass:'fld-err-st',ajaxSubmit:false}); });/* #widgetu7824 */
Muse.Utils.addSelectorFn('body', Muse.Utils.showWidgetsWhenReady);/* body */
Muse.Utils.addSelectorFn('body', Muse.Utils.transformMarkupToFixBrowserProblems);/* body */

</script>
   </body>
</html>
