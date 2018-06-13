<%@ page session="true" language="java" import="mx.com.web2lab.tools.CaptchasDotNet" errorPage="/jsp/error.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
  <meta name="generator" content="4.1.8.204"/>
  <title>Paso 3</title>
  <!-- CSS -->
  <link rel="stylesheet" type="text/css" href="/web2labportal/css/consultaresultados/site_global.css?3763450218"/>
  <link rel="stylesheet" type="text/css" href="/web2labportal/css/consultaresultados/master_b-master.css?3988633287"/>
  <link rel="stylesheet" type="text/css" href="/web2labportal/css/consultaresultados/paso-3.css?62070716" id="pagesheet"/>
  <!-- Other scripts -->
  <script src="/web2labportal/javascript/consultaresultados/pantallas/paso.js" type="text/javascript"></script>
  <script type="text/javascript">
   document.documentElement.className += ' js';
</script>
   </head>
 <body>
   <% 
	  CaptchasDotNet captchas = new CaptchasDotNet(request.getSession(true),"ECEOlab","3HtGXyW4X1Uri2FBfGGgjbktAxtLd0Y0fNUWZx7k");   
   	  String skOrdenSucursal = request.getParameter("kordensucursalpaso"); 
      String sEmail = request.getParameter("emailpaso"); 
      String sPassword = request.getParameter("passwordpaso");
      if (request.getParameter("kordensucursalpaso") == null){
	 	skOrdenSucursal = "";
	  }
      if (request.getParameter("emailpaso") == null){
	 	sEmail = "";
	  }
      if (request.getParameter("passwordpaso") == null){
	 	sPassword = "";
	  }
   %>
  <div class="clearfix" id="page"><!-- column -->
   <div class="position_content" id="page_position_content">
    <div class="clearfix colelem" id="pu7618"><!-- group -->
     <div class="gradient rounded-corners clearfix grpelem" id="u7618"><!-- column -->
      <div class="position_content" id="u7618_position_content">
       <div class="clearfix colelem" id="pu7630"><!-- group -->
        <div class="grpelem" id="u7630"><!-- image -->
         <img class="block" id="u7630_img" src="/web2labportal/images/consultaresultados/logo%20olab.png" alt="" width="154" height="83"/>
        </div>
        <div class="clearfix grpelem" id="u7619"><!-- group -->
         <div class="clearfix grpelem" id="u7402-4"><!-- content -->
          <p>Consulta de Resultados</p>
         </div>
        </div>
       </div>
       <div class="clearfix colelem" id="pu8091"><!-- group -->
        <a class="nonblock nontext rgba-background rounded-corners clearfix grpelem" id="u8091" href="javascript:envioPasos('paso-2.jsp');"><!-- group --><div class="clearfix grpelem" id="u8093-4"><!-- content --><p>Regresar</p></div></a>
        <a class="nonblock nontext rgba-background rounded-corners clearfix grpelem" id="u8092" href="javascript:envioPasos('paso-4.jsp');"><!-- group --><div class="clearfix grpelem" id="u8094-4"><!-- content --><p>Continuar</p></div></a>
       </div>
       <div class="clearfix colelem" id="pu7455"><!-- group -->
        <div class="rounded-corners grpelem" id="u7455"><!-- simple frame --></div>
        <div class="rgba-background rounded-corners clearfix grpelem" id="u7493"><!-- group -->
         <div class="grpelem" id="u8148"><!-- image -->
          <img class="block" id="u8148_img" src="/web2labportal/images/consultaresultados/recibo%20color.jpg" alt="" width="529" height="333"/>
         </div>
        </div>
       </div>
      </div>
     </div>
     <form class="form-grp clearfix grpelem" id="widgetu7930" method="post" enctype="multipart/form-data" action="confirmar-informacion.jsp" onSubmit="envioFinal();"><!-- group -->
      <div class="fld-grp clearfix grpelem" id="widgetu7947" data-required="true"><!-- group -->
       <label class="fld-label actAsDiv clearfix grpelem" id="u7950-4" for="widgetu7947_input"><!-- content -->
        <span class="actAsPara">Consecutivo</span>
       </label>
       <span class="fld-input NoWrap actAsDiv clearfix grpelem" id="u7948-3"><!-- content --><input class="wrapped-input" type="text" spellcheck="false" id="widgetu7947_input" name="FormFieldName" value="<%=skOrdenSucursal%>"  tabindex="1"/></span>
      </div>
      <div class="fld-grp clearfix grpelem" id="widgetu7952" data-required="true" data-type="email"><!-- group -->
       <label class="fld-label actAsDiv clearfix grpelem" id="u7955-4" for="widgetu7952_input"><!-- content -->
        <span class="actAsPara">Correo electrónico:</span>
       </label>
       <span class="fld-input NoWrap actAsDiv clearfix grpelem" id="u7953-3"><!-- content --><input class="wrapped-input" type="text" spellcheck="false" id="widgetu7952_input" name="FormFieldName" value="<%=sEmail%>" tabindex="3"/></span>
      </div>
      <div class="clearfix grpelem" id="u7963-4"><!-- content -->
       <p>Enviando formulario...</p>
      </div>
      <div class="clearfix grpelem" id="u7946-4"><!-- content -->
       <p>El servidor ha detectado un error.</p>
      </div>
      <div class="clearfix grpelem" id="u7945-4"><!-- content -->
       <p>Formulario recibido.</p>
      </div>
      <input class="submit-btn NoWrap grpelem" id="u7962-17" type="submit" value="" tabindex="5"/><!-- state-based BG images -->
      <div class="fld-grp clearfix grpelem" id="widgetu7973" data-required="true"><!-- group -->
       <label class="fld-label actAsDiv clearfix grpelem" id="u7977-4" for="widgetu7973_input"><!-- content -->
        <span class="actAsPara">Contraseña</span>
       </label>
       <span class="fld-input NoWrap actAsDiv clearfix grpelem" id="u7976-3"><!-- content --><input class="wrapped-input" type="text" id="widgetu7973_input" name="FormFieldName"  value="<%=sPassword%>"  tabindex="2"/></span>
      </div>
      <div class="fld-grp clearfix grpelem" id="widgetu7978" data-required="true" data-type="captcha"><!-- group -->
       <label class="fld-label actAsDiv clearfix grpelem" id="u7980-4" for="widgetu7978_input"><!-- content -->
        <span class="actAsPara">Comprobación de imagen:</span>
       </label>
       <span class="fld-input NoWrap actAsDiv clearfix grpelem" id="u7982-3"><!-- content --><input class="wrapped-input" type="text" autocomplete="off" spellcheck="false" id="widgetu7978_input" name="FormFieldName" tabindex="4"/></span>
       <%= captchas.image() %><br><br><br>
<!--   <img class="grpelem" src="/web2labportal/images/consultaresultados/captcha3.png" alt="Imagen de marcador de posición de CAPTCHA" title="Esta imagen es un marcador de posición para una imagen CAPTCHA. Si publica el sitio con Adobe Business Catalyst, esta imagen será reemplazada por una imagen CAPTCHA generada por el servidor." id="u7983"/><!-- captcha placeholder -->
      </div>
     </form>
     <div class="rounded-corners grpelem" id="u8099"><!-- simple frame --></div>
     <div class="grpelem" id="u8138"><!-- image -->
      <img class="block" id="u8138_img" src="/web2labportal/images/consultaresultados/dro3%20copy.png" alt="" width="300" height="254"/>
     </div>
    </div>
    <div class="browser_width colelem" id="u4224"><!-- group -->
     <div class="clearfix" id="u4224_align_to_page">
      <div class="clearfix grpelem" id="u4223-6"><!-- content -->
       <p>© 2013 Todos los Derechos Reservados</p>
       <p>Olab Diagnósticos Médicos</p>
      </div>
     </div>
    </div>
   </div>
  </div>
  <div class="preload_images">
   <img class="preload" src="/web2labportal/images/consultaresultados/u7962-17-r.png" alt=""/>
   <img class="preload" src="/web2labportal/images/consultaresultados/u7962-17-m.png" alt=""/>
   <img class="preload" src="/web2labportal/images/consultaresultados/u7962-17-fs.png" alt=""/>
  </div>
  <div id="gridprogressbar" style="display:none">                
  	<table border="0" cellspacing="2" cellpadding="3" width="100%" align=center class="tabla">
           <td>
       		<p align=center><img id="loaderAjax" src="/web2labportal/images/ajax-loader.gif" >	
           </td>
   	</table>
  </div>            	     
  <!-- JS includes -->
  <script type="text/javascript">
   if (document.location.protocol != 'https:') document.write('\x3Cscript src="http://musecdn2.businesscatalyst.com/scripts/4.0/jquery-1.8.3.min.js" type="text/javascript">\x3C/script>');
</script>
  <script type="text/javascript">
   window.jQuery || document.write('\x3Cscriptsrc="/web2labportal/javascript/consultaresultados/jquery-1.8.3.min.js" type="text/javascript">\x3C/script>');
</script>
  <script src="/web2labportal/javascript/consultaresultados/museutils.js?4215913851" type="text/javascript"></script>
  <script src="/web2labportal/javascript/consultaresultados/webpro.js?4060715896" type="text/javascript"></script>
  <script src="/web2labportal/javascript/consultaresultados/jquery.tobrowserwidth.js?4052033419" type="text/javascript"></script>
  <!-- Other scripts -->
  <script type="text/javascript">
   Muse.Utils.addSelectorFn('body', Muse.Utils.transformMarkupToFixBrowserProblemsPreInit);/* body */
$(document).ready(function() { $('.browser_width').toBrowserWidth(); });/* browser width elements */
Muse.Utils.addSelectorFn('body', Muse.Utils.prepHyperlinks); /* body */
Muse.Utils.addSelectorFn('#widgetu7930', function(elem) { new WebPro.Widget.Form(elem, {validationEvent:'submit',errorStateSensitivity:'high',fieldWrapperClass:'fld-grp',formSubmittedClass:'frm-sub-st',formErrorClass:'frm-subm-err-st',formDeliveredClass:'frm-subm-ok-st',notEmptyClass:'non-empty-st',focusClass:'focus-st',invalidClass:'fld-err-st',requiredClass:'fld-err-st',ajaxSubmit:false}); });/* #widgetu7930 */
Muse.Utils.addSelectorFn('body', Muse.Utils.showWidgetsWhenReady);/* body */
Muse.Utils.addSelectorFn('body', Muse.Utils.transformMarkupToFixBrowserProblems);/* body */

</script>
 	<script type="text/javascript">
            $(function(){
                $('#widgetu7947_input').validaciones('0123456789');    
            });
            
            (function( $ ) {
            	$.fn.validaciones = function(cadena) {
                	$(this).on({
            			keypress : function(e){
                			var key = e.which,
                				keye = e.keyCode,
                				tecla = String.fromCharCode(key).toLowerCase(),
                				letras = cadena;
            			    if(letras.indexOf(tecla)==-1 && keye!=9&& (key==37 || keye!=37)&& (keye!=39 || key==39) && keye!=8 && (keye!=46 || key==46) || key==161){
            			    	e.preventDefault();
            			    }
            			}
            		});
            	};
            })( jQuery );            
  	</script>     

   </body>
</html>
