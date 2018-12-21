/******************** General ********************************/
var facturaelectronicaBean = new FacturaElectronicaBean();

   function init() 
   {
	    DWRUtil.useLoadingMessage();
   }

/********************* Negocio *******************************/
   
   function GeneracionPrevioFactura()
   {
       	var frmPantalla = window.document.frmFacturacion;
       	var cconvenio = TypeObjeto(frmPantalla.selConvenios); 
       	var strbloques = loopSelected();
       	var tipoprevio = TypeObjeto(frmPantalla.selTipoPrevio);
       	var userid =frmPantalla.idUsuario.value;
       	var monto =frmPantalla.txtMontoMaximo.value;
       	
       	
       	if (((cconvenio>0) && (strbloques !=',')&& (strbloques !='')&&(tipoprevio>0))) {
				showPopWin("/web2labportal/jsp/facturacionPrevios.jsp?cconvenio="+cconvenio+"&strbloques="+strbloques+"&tipoprevio="+tipoprevio+"&userid="+userid+"&monto="+monto+"&btipofactura=0", 300, 300, "Previo");
		} else {
			alert('Debe seleccionar al menos un bloque y tipo de Reporte que requiere');
		} 		   
   }
   
   function GeneracionListaPreciosActual()
   {
       	var frmPantalla = window.document.frmListaPrecios;
       	var cconvenio = TypeObjeto(frmPantalla.selConvenios);
       	var tipoReporte = TypeObjeto(frmPantalla.selTipoReporte);
       	
       	if ((cconvenio>0) && (tipoReporte>0)) {
				//showPopWin("/web2labportal/jsp/facturacionPrevios.jsp?cconvenio="+cconvenio+"&strbloques="+strbloques+"&tipoprevio="+tipoprevio+"&userid="+userid+"&monto="+monto+"&btipofactura=0", 300, 300, "Previo");
       		if(tipoReporte==1){
       			showPopWin("/web2labportal/jsp/ReporteDetalleExamen.jsp?cconvenio="+cconvenio, 300, 300, "Detalle");
       			
       		}else if(tipoReporte==2){
       			showPopWin("/web2labportal/jsp/ReporteDetallePerfilPaquete.jsp?cconvenio="+cconvenio, 300, 300, "Detalle");
       			
       		}
		} else {
			alert('Debe seleccionar un tipo de Reporte que requiere');
		} 		   
   }
   
   function muestraMensaje(){
	   
	   var frmPantalla = window.document.frmListaPrecios;
      	var cconvenio = TypeObjeto(frmPantalla.selConvenios);
      	var tipoReporte = TypeObjeto(frmPantalla.selTipoReporte);
      	
      	if (confirm("¿Esta seguro de eliminar los registros del convenio? "+cconvenio)) {
	      	if ((cconvenio>0) && (tipoReporte>0)) {
					//showPopWin("/web2labportal/jsp/facturacionPrevios.jsp?cconvenio="+cconvenio+"&strbloques="+strbloques+"&tipoprevio="+tipoprevio+"&userid="+userid+"&monto="+monto+"&btipofactura=0", 300, 300, "Previo");
	      		if(tipoReporte==1){
	      			FacturaElectronicaEmpresaAjax.eliminaPreciosActual(cconvenio,"e_convenio_detalle");
	      			
	      		}else if(tipoReporte==2){
	      			
	      			FacturaElectronicaEmpresaAjax.eliminaPreciosActual(cconvenio,"e_convenio_perfil");
	      			
	      		}
	      		alert('Registros eliminados');
			} else {
				alert('Debe seleccionar un tipo de Reporte');
			} 
      	}
	   
   }
   
      
   
   function GeneracionReporteConvenio()
   {
       	var frmPantalla = window.document.frmReportesConvenios;      	
       //	alert('Generando reporte..');
       		showPopWin("/web2labportal/jsp/ReporteConvenioActivosInactivos.jsp", 300, 300, "Convenio");
				   
   }
   
   
   function GeneracionReporte()
   {
       	var frmPantalla = window.document.frmReportesS;       
       	var tipoprevio = TypeObjeto(frmPantalla.selTipoPrevio);
       	var fecha1 = document.frmReportesS.txtDeFecha.value;
    	var fecha2 = document.frmReportesS.txtAFecha.value;
    	if (fecha1!='' || fecha2!=''){
	    	var arraySepara1= fecha1.split("-");
	    	var mes="";
	    	
	    	switch (arraySepara1[1]){
	    		case 'ENE': mes="01";
	    		break;
	    		case 'FEB': mes="02";
	    		break;
	    		case 'MAR': mes="03";
	    		break;
	    		case 'ABR': mes="04";
	    		break;
	    		case 'MAY': mes="05";
	    		break;
	    		case 'JUN': mes="06";
	    		break;
	    		case 'JUL': mes="07";
	    		break;
	    		case 'AGO': mes="08";
	    		break;
	    		case 'SEP': mes="09";
	    		break;
	    		case 'OCT': mes="10";
	    		break;
	    		case 'NOV': mes="11";
	    		break;
	    		case 'DIC': mes="12";
	    		break;
	    	}
	    	var arraySepara2= fecha2.split("-");
	    	var mes2="";
	    	switch (arraySepara2[1]){
	    		case 'ENE': mes2="01";
	    		break;
	    		case 'FEB': mes2="02";
	    		break;
	    		case 'MAR': mes2="03";
	    		break;
	    		case 'ABR': mes2="04";
	    		break;
	    		case 'MAY': mes2="05";
	    		break;
	    		case 'JUN': mes2="06";
	    		break;
	    		case 'JUL': mes2="07";
	    		break;
	    		case 'AGO': mes2="08";
	    		break;
	    		case 'SEP': mes2="09";
	    		break;
	    		case 'OCT': mes2="10";
	    		break;
	    		case 'NOV': mes2="11";
	    		break;
	    		case 'DIC': mes2="12";
	    		break;
	    	}
	    	//alert('mensaje   '+arraySepara1[0]+'-'+mes+'-'+arraySepara1[2]+'           '+arraySepara2[0]+'-'+mes2+'-'+arraySepara2[2]);
	    	
	       //	var fecha1 = "01-12-2012";
	       	//var fecha2 =  "17-12-2012";
	    	
	    	var fecha1new=arraySepara1[0]+'-'+mes+'-'+arraySepara1[2];
	    	var fecha2new=arraySepara2[0]+'-'+mes2+'-'+arraySepara2[2];
	    	//alert('Fechas   '+fecha1new+'     '+fecha2new);
	       	if (tipoprevio==1) {
	       		//alert('Entra a opc = '+tipoprevio+'     '+fecha1 +'   '+fecha2);
	    		showPopWin("/web2labportal/jsp/ReporteFacturasSerieB.jsp?fecha1="+fecha1new+"&fecha2="+fecha2new, 300, 300, "Previo");
	    		} else {
				alert('Debe seleccionar un reporte');
			} 
    	}else{
    		alert('Es necesario capturar las Fechas...');
    	}
   }
   
     
   function GeneracionDefinitivoFactura()
   {
	   	var frmPantalla = window.document.frmFacturacion;
      	var cconvenio = TypeObjeto(frmPantalla.selConvenios); 
      	var strbloques = loopSelected();
      	var tipoprevio = TypeObjeto(frmPantalla.selTipoPrevio);
      	var userid =frmPantalla.idUsuario.value;
      	var monto =frmPantalla.txtMontoMaximo.value;
       	
       	
      	if (((cconvenio>0) && (strbloques !=',')&& (strbloques !='')&&(tipoprevio>0))) {
      		//alert('convenio: '+cconvenio);
      		BusquedaFacturas.buscarMarca(cconvenio,GeneracionDefinitivoFactura_CallBack);
      		
		} else {
			alert('Debe seleccionar al menos un bloque y tipo de Reporte que requiere');
		}   			   
   }
   
   function GeneracionDefinitivoFactura_CallBack(data){
	   
		var frmPantalla = window.document.frmFacturacion;
	  	var cconvenio = TypeObjeto(frmPantalla.selConvenios); 
	  	var strbloques = loopSelected();
	  	var tipoprevio = TypeObjeto(frmPantalla.selTipoPrevio);
	  	var userid =frmPantalla.idUsuario.value;
	  	var monto =frmPantalla.txtMontoMaximo.value;
	  	var razonSocial = TypeObjeto(frmPantalla.selRazonSocial);
      	
	   if(data==7){
		   if(razonSocial>0){
			   if (confirm("¿Esta seguro de generar la facturacion definitiva JENNER del convenio? "+cconvenio)) {	 	
					showPopWin("/web2labportal/jsp/facturacionPrevios.jsp?cconvenio="+cconvenio+"&strbloques="+strbloques+"&tipoprevio="+tipoprevio+"&userid="+userid+"&monto="+monto+"&btipofactura=1&razon="+razonSocial, 300, 300, "Definitivo"); 	
				}
		   }else{
			   alert('El convenio es de Jenner, debes seleccionar una Razon Social');
		   }
	   }else{
		   if (confirm("¿Esta seguro de generar la facturacion definitiva del convenio? "+cconvenio)) {	 	
				showPopWin("/web2labportal/jsp/facturacionPrevios.jsp?cconvenio="+cconvenio+"&strbloques="+strbloques+"&tipoprevio="+tipoprevio+"&userid="+userid+"&monto="+monto+"&btipofactura=1&razon=0", 300, 300, "Definitivo"); 	
			}
	   }
   }
   
   function buscarConvenioRapido(objConvenio,strBuscar) {
       var frmPantalla = window.document.frmFacturacion;		
		if ((strBuscar.length == 9) && (validaSoloNumeros(strBuscar))) {
				var strPromocion = strBuscar.substring(0,4);
				
				if (strPromocion.substring(0,1) == "0") {
					buscaTextoSelect(objConvenio,strPromocion.substring(1));
				} else {
					buscaTextoSelect(objConvenio,strPromocion);
				}
		} else {
			buscaTextoSelect(objConvenio,strBuscar);
		}
	}

   function limpiaPantallaSerieB()
   {
	   var frmPantalla = window.document.frmFacturacionSerieAyB;
	   	
		   frmPantalla.txtRFC.value="";
		   valorCombo(frmPantalla.selTipoUsoCFDI,"G01");
		   valorCombo(frmPantalla.selMarca,"1");
		   frmPantalla.txtMontoTotal.value="";
		   valorCombo(frmPantalla.selFormaPago,"01");
		   valorCombo(frmPantalla.selCantidad,"1");
		   document.getElementById("CamposConceptos").innerHTML="";
		   document.getElementById("selRazonSocial").innerHTML ="";
		   adminDIV("proceso2","hidden","none");  
		   adminDIV("CamposConceptos","hidden","none");
		   document.getElementById("txtRFC").focus();	 
		   
		   document.getElementById("txtRFC").disabled = false;
		   document.getElementById("selTipoUsoCFDI").disabled = false;
		   document.getElementById("selMarca").disabled = false;
		   document.getElementById("txtMontoTotal").disabled = false;
		   document.getElementById("selFormaPago").disabled = false;
		   document.getElementById('idProcesarInfo').style.display = 'inline';
		   document.getElementById('idLimpiarSerieB').style.display = 'inline';
		   document.getElementById('idLimpiarSerieBP2').style.display = 'none';
		   document.getElementById("selCantidad").disabled = false;
		   document.getElementById("selRazonSocial").disabled = false;
	   
   }

   function limpiaPantallaSerieBProceso2()
   {
	   var frmPantalla = window.document.frmFacturacionSerieAyB;
	   if (confirm('¿Esta seguro de limpiar la pantalla?')) {
		   frmPantalla.txtRFC.value="";
		   valorCombo(frmPantalla.selTipoUsoCFDI,"G01");
		   valorCombo(frmPantalla.selMarca,"1");
		   frmPantalla.txtMontoTotal.value="";
		   valorCombo(frmPantalla.selFormaPago,"01");
		   valorCombo(frmPantalla.selCantidad,"1");
		   document.getElementById("CamposConceptos").innerHTML="";
		   document.getElementById("selRazonSocial").innerHTML ="";
		   adminDIV("proceso2","hidden","none");  
		   adminDIV("CamposConceptos","hidden","none");
		   document.getElementById("txtRFC").focus();	 
		   
		   document.getElementById("txtRFC").disabled = false;
		   document.getElementById("selTipoUsoCFDI").disabled = false;
		   document.getElementById("selMarca").disabled = false;
		   document.getElementById("txtMontoTotal").disabled = false;
		   document.getElementById("selFormaPago").disabled = false;
		   document.getElementById('idProcesarInfo').style.display = 'inline';
		   document.getElementById('idLimpiarSerieB').style.display = 'inline';
		   document.getElementById('idLimpiarSerieBP2').style.display = 'none';
		   
		   document.getElementById("selCantidad").disabled = false;
		   document.getElementById("selRazonSocial").disabled = false;
	   }
   }
   
   function limpiaPantalla()
   {
	   var frmPantalla = window.document.frmFacturacion;
	   valorCombo(frmPantalla.selConvenios,356);
	   valorCombo(frmPantalla.selTipoPrevio,0);
	   valorCombo(frmPantalla.selRazonSocial,0);
	   frmPantalla.txtMontoMaximo.value=0;
	   frmPantalla.txtBuscarConvenio.value="";
	   document.getElementById("txtBuscarConvenio").focus();
	   for (i=0; ele = document.getElementById('selBloques').options[i]; i++)
	   ele.selected = false;
   }
   
   function limpiaPantallaBloques()
   {
	   var frmPantalla = window.document.frmAsiganacionBloques;
	   valorCombo(frmPantalla.selConvenios,356);
	   valorCombo(frmPantalla.selTipoPrevio,0);
	   frmPantalla.txtMontoMaximo.value=0;
	   frmPantalla.txtBuscarConvenio.value="";
	   document.getElementById("txtBuscarConvenio").focus();
	   for (i=0; ele = document.getElementById('selBloques').options[i]; i++)
	   ele.selected = false;
   }
   
   function loopSelected()
	{
	  var strElecciones;
	  var strcadena=',';
	  var selObj = document.getElementById('selBloques');
	  var i;
	  var count = 0;
	  for (i=0; i<selObj.options.length; i++) {
	    if (selObj.options[i].selected) {
	    	if(selObj.options[i].value != 0) {
		    	strcadena += selObj.options[i].value+',';
		    	count++;
	    	} else {
	    		strcadena='';
	    	}
	    }
	  }
	  return strcadena;
	}
   
   function showFacturaSerie(){
	   
	   var frmPantalla = window.document.frmFacturacionSerieAyB;
     	var tipoReporte = TypeObjeto(frmPantalla.selTipoSerieFactura);
     	if(tipoReporte>0){
     		limpiaPantallaSerieB();
     		adminDIV("creacionSerieB","hidden","none");
     		
     		if(tipoReporte==1){
     			adminDIV("creacionSerieB","visible","inline");
     			document.getElementById("txtRFC").focus();
     		}
     		if(tipoReporte==5){
     			adminDIV("creacionAnticipadaSerieA","visible","inline");
     			document.getElementById("txtConvenio").focus();
     		}
     	}else{
     		alert('Debe seleccionar un tipo de Factura');
     	}
	   
	}
   
   function agregar_conceptos(){
	   document.getElementById("CamposConceptos").innerHTML="";
	   adminDIV("CamposConceptos","visible","inline");
	   var cajas=0;
	   var n=document.getElementById('selCantidad').value;
	   var tabla="<table border='0' class='tabla' width='100%'>";
	   for(var i=0;i<parseInt(n);i++){
			 tabla+="<tr><td colspan='6'>Concepto "+new String(cajas+1)+"</td></tr>"+
		     "<tr><td valign='middle' align='right'>*Codigo de Concepto : </td><td valign='middle'><input type=text  name='CodigoConcepto"+new String(cajas+1)+"' size='25' style='width:70px;' onKeyPress='numero();'></td>"+
		     "<td valign='middle' align='right'>*Codigo de OLAB/AZTECA : </td><td valign='middle'><input type=text  name='CodigoOLABAZTECA"+new String(cajas+1)+"' size='25' style='width:200px;' onKeyPress='mayuscula();'></td>"+
		     "<td valign='middle' align='right'>*Cantidad : </td><td valign='middle'><input type=text  name='Cantidad"+new String(cajas+1)+"' size='25' style='width:100px;' onKeyPress='numero();'></td></tr>"+
		     "<tr><td valign='middle' align='right'>*Clave de Unidad : </td><td valign='middle'><input type=text  name='ClaveUnidad"+new String(cajas+1)+"' size='25' style='width:70px;' onKeyPress='mayusculaIgnorandoCaracteres();'></td>"+
		     "<td valign='middle' align='right'>*Nombre Concepto : </td><td valign='middle'><input type=text  name='NomConcepto"+new String(cajas+1)+"' size='25' style='width:200px;' onKeyPress='mayuscula();'></td>"+
		     "<td valign='middle' align='right'>*Precio Unitario : </td><td valign='middle'><input type=text  name='PrecioUnitario"+new String(cajas+1)+"' size='25' style='width:100px;' onKeyPress='montos();'></td></tr><tr><td colspan='6'><br></td></tr>";
		     cajas=cajas+1;
	     }
	   tabla+="<tr><td colspan='6'><input type='button' id='idGenerarFactura' name='idGenerarFactura' value='Generar Factura' onClick='generaCadenaSerieBRFC();' class='boton'>" +
	   		"<input type='button' id='idLimpiarSerieBP3' name='idLimpiarSerieBP3' value='Limpiar Pantalla' onClick='limpiaPantallaSerieBProceso2();' class='boton'></td></tr>" +
  		"</table>";
	   document.getElementById("CamposConceptos").innerHTML=tabla;
	   document.getElementById('idLimpiarSerieBP2').style.display = 'none';
	   document.getElementById("selCantidad").disabled = true;
	   document.getElementById("selRazonSocial").disabled = true;
	 }
   
  
   
   function generaCadenaSerieBRFC(){
	   var frmPantalla = window.document.frmFacturacionSerieAyB;
	   var rfc=frmPantalla.txtRFC.value;
	   var UsoCFDI= TypeObjeto(frmPantalla.selTipoUsoCFDI);
	   var marca=TypeObjeto(frmPantalla.selMarca);
	   var montoTotal=frmPantalla.txtMontoTotal.value;
	   var formaPago=TypeObjeto(frmPantalla.selFormaPago);
	   var convenio=TypeObjeto(frmPantalla.selRazonSocial);
	   var cantConceptos=TypeObjeto(frmPantalla.selCantidad);
	   var cadena="2|N|N|"+rfc+"@"+convenio+"|"+UsoCFDI+"|"+marca+"|S|"+montoTotal+"|N|0|0|"+formaPago+"|"+cantConceptos+"|";
	   	   
	   for(var i=0;i<parseInt(cantConceptos);i++){
		   cadena+=document.getElementById("CodigoConcepto"+new String(i+1)).value+"|";
		   cadena+=document.getElementById("CodigoOLABAZTECA"+new String(i+1)).value+"|";
		   cadena+=document.getElementById("Cantidad"+new String(i+1)).value+"|";
		   cadena+=document.getElementById("ClaveUnidad"+new String(i+1)).value+"|";
		   cadena+=document.getElementById("NomConcepto"+new String(i+1)).value+"|";
		   if((i+1)==parseInt(cantConceptos)){
			   cadena+=document.getElementById("PrecioUnitario"+new String(i+1)).value+"|0.16";
		   }else{
			   cadena+=document.getElementById("PrecioUnitario"+new String(i+1)).value+"|0.16|";
		   }		   
	   }
	   //alert('cadena: '+cadena);
	   if(cadena.indexOf("||")!=-1){
		   alert('Se requiere que todos los campos esten llenos');
	   }else{
		   //alert('rfc: '+rfc);
		   //FacturaElectronicaEmpresaAjax.validaRFC(rfc,displayValidaRFC_CallBack);
		   if(rfc.length==13){
			   var expreg = new RegExp("[A-Z&Ñ]{4}[0-9]{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[A-Z0-9]{2}[0-9A]");
			   if(expreg.test(rfc)){
				   if (confirm("¿Esta seguro de generar la factura?")) {	 
					   alert('Campos Correctos : '+cadena);
					   //FacturaElectronicaEmpresaAjax.pruebaLog(cadena);
				   }	 
			   }else{
				   alert('RFC no valido');
			   }
		   }else if(rfc.length==12){
			   var expreg = new RegExp("[A-Z&Ñ]{3}[0-9]{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[A-Z0-9]{2}[0-9A]");
			   if(expreg.test(rfc)){
				   if (confirm("¿Esta seguro de generar la factura?")) {	 
					   alert('Campos Correctos : '+cadena);
					   //FacturaElectronicaEmpresaAjax.pruebaLog(cadena);
				   }	 
			   }else{
				   alert('RFC no valido');
			   }
		   }else{
			   alert('RFC no valido');
		   }		   
	   }
   }
    
   function procesarInformacion(){
	   var frmPantalla = window.document.frmFacturacionSerieAyB;
	   var rfc=frmPantalla.txtRFC.value;
	   var montoTotal=frmPantalla.txtMontoTotal.value;
	   
	   if((rfc=="")||(montoTotal=="")){
		   alert('Se requiere que todos los campos esten llenos');
	   }else{
		   if(rfc.length==13){
			   var expreg = new RegExp("[A-Z&Ñ]{4}[0-9]{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[A-Z0-9]{2}[0-9A]");
			   if(expreg.test(rfc)){				   
				   consultaRFCSerieB(); 
			   }else{
				   alert('RFC no valido');
			   }
		   }else if(rfc.length==12){
			   var expreg = new RegExp("[A-Z&Ñ]{3}[0-9]{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[A-Z0-9]{2}[0-9A]");
			   if(expreg.test(rfc)){
				   consultaRFCSerieB();	
			   }else{
				   alert('RFC no valido');
			   }
		   }else{
			   alert('RFC no valido');
		   }
	   }
	   
   }
   
   function consultaRFCSerieB(){
	   var frmPantalla = window.document.frmFacturacionSerieAyB;
	   var rfc=frmPantalla.txtRFC.value;
	   
	   FacturaElectronicaEmpresaAjax.obtenerRfc(rfc,displayBloqueAsignacion_CallBack);
	   
   }
   
   function displayBloqueAsignacion_CallBack(data){
	   if(data=="-1"){
		   alert('El RFC no se encuentra en Base de Datos');
	   }else{
		   var sel=document.getElementById("selRazonSocial");
		   var array=data.split("|");
		   
		   for(var i=0;i<array.length;i++){
			   var option = document.createElement("option");
			    option.text = new String(array[i].replace("@",""));
			    var valu=array[i].split("@");
			    option.value=new String(valu[0]);
			    sel.add(option);
		   }	   
		   adminDIV("proceso2","visible","inline");
		   document.getElementById("txtRFC").disabled = true;
		   document.getElementById("selTipoUsoCFDI").disabled = true;
		   document.getElementById("selMarca").disabled = true;
		   document.getElementById("txtMontoTotal").disabled = true;
		   document.getElementById("selFormaPago").disabled = true;
		   document.getElementById('idProcesarInfo').style.display = 'none';
		   document.getElementById('idLimpiarSerieB').style.display = 'none';
		   document.getElementById('idLimpiarSerieBP2').style.display = 'inline';
	   }
   }
   
   function showCajaFactura(){
		if (document.getElementById("chkCrearFactura").checked){	
			adminDIV("creacionPdf","visible","inline");
			//document.getElementById("selTipoFactura").focus();
			document.getElementById("txtUfoliofactura").focus();
		}else{
			adminDIV("creacionPdf","hidden","none");
			document.getElementById("chkCrearFactura").checked = false;
			document.getElementById("txtUfoliofactura").value="";
			valorCombo(frmPantalla.selTipoFactura,0);
		}
	} 
   
   function showFormSustitucion(){
	   document.getElementById("txtUfoliofacturaSustitucion").value="";
	   if(document.getElementById("chkSustitucion").checked){
		   adminDIV("labelFolioInterno","visible","inline");
		   adminDIV("txtUfoliofacturaSustitucion","visible","inline");
		   adminDIV("popupBuscar","visible","inline");
	   }else{
		   adminDIV("labelFolioInterno","hidden","none");
		   adminDIV("txtUfoliofacturaSustitucion","hidden","none");
		   adminDIV("popupBuscar","hidden","none");
	   }
   }
   
   
   function showAsignacionBloques(){
		if (document.getElementById("chkAsigancionBloque").checked){	
			adminDIV("asignacionBloques","visible","inline");
			//document.getElementById("selTipoFactura").focus();
			////document.getElementById("txtUfoliofactura").focus();
		}else{
			adminDIV("asignacionBloques","hidden","none");
			document.getElementById("chkAsigancionBloque").checked = false;
			////document.getElementById("txtUfoliofactura").value="";
			valorCombo(frmPantalla.selTipoFactura,0);
		}
	}
   
   function showAsignacionOrdenesFacturas(){
		if (document.getElementById("chkAsignacionBloquesFactura").checked){	
			adminDIV("asignacionOrdenesFactura","visible","inline");
			//document.getElementById("selTipoFactura").focus();
			document.getElementById("txtUfoliofactura").focus();
		}else{
			adminDIV("asignacionOrdenesFactura","hidden","none");
			document.getElementById("chkAsignacionBloquesFactura").checked = false;
			document.getElementById("txtUfoliofactura").value="";
			valorCombo(frmPantalla.selTipoFactura,0);
		}
	}
   

   /*
	function tipoFactura(tipofactura){
		document.getElementById("txtUfoliofactura").focus();
		if(tipofactura==1){
			adminDIV("txtDescripcion","visible","inline");
			adminDIV("labelfactura","visible","inline");
			document.getElementById("txtDescripcion").focus();
		}else{
			adminDIV("txtDescripcion","hidden","none");	
			adminDIV("labelfactura","hidden","none");	
		}
	}*/
   //Modificacion BY 02/09/2013
   function tipoFactura(tipofactura){
		document.getElementById("txtUfoliofactura").focus();
		if((tipofactura==1) ||(tipofactura==3)){
			adminDIV("txtDescripcion","visible","inline");
			adminDIV("labelfactura","visible","inline");
			document.getElementById("txtDescripcion").focus();
		}else{
			if(tipofactura==4){
				adminDIV("txtDescripcion","hidden","none");	
				adminDIV("labelfactura","hidden","none");
				adminDIV("labelordencompra","visible","inline");
				adminDIV("txtOrdenCompra","visible","inline");
				document.getElementById("txtOrdenCompra").focus();
			}else{
				adminDIV("txtDescripcion","hidden","none");	
				adminDIV("labelfactura","hidden","none");
			}
		}
	}
	
	
/*	
   function generacionPdfXml(){
	   var ufoliofactura = document.getElementById("txtUfoliofactura").value; 
	   var tipofactura = document.getElementById("selTipoFactura").value;
	   
	   var msubtotal = document.getElementById("txtmSubtotal").value;
	   var miva = document.getElementById("txtmIva").value;
	   var mtotal = document.getElementById("txtmTotal").value;
	   
	   
	   var smetodopago = document.getElementById("txtTipoPago").value;
	   
	   if(smetodopago!='99'){
		   var nocuenta = document.getElementById("txtNoCuenta").value;
	   } else {
		   var nocuenta = '';
	   }
	   //alert('M�todo pago' + smetodopago);
	   if((tipofactura>0)&&(ufoliofactura>0)){
			if((tipofactura==1)){
				var descripcionfactura = document.getElementById("txtDescripcion").value;
				if(descripcionfactura==""){
					alert("Debe ingresar una descripci�n para la factura");
					document.getElementById("txtDescripcion").focus();
				}else{
					LoadFacturaElectronica(descripcionfactura);
					FacturaElectronicaEmpresaAjax.crearFacturaEmpresa(facturaelectronicaBean,ufoliofactura,tipofactura,msubtotal,miva,mtotal,nocuenta,smetodopago,generarFactura_CallBack);
				}
			}if(tipofactura==2){
				LoadFacturaElectronica("");
				FacturaElectronicaEmpresaAjax.crearFacturaEmpresa(facturaelectronicaBean,ufoliofactura,tipofactura,msubtotal,miva,mtotal,nocuenta,smetodopago,generarFactura_CallBack);
			}
		}else{
			if(tipofactura==0){
				alert('Debe elegir un formato adecuado de factura');
				document.getElementById("selTipoFactura").focus();
				return false;
			}if((ufoliofactura==0)||(ufoliofactura=="")){
				alert('Debe ingresar un folio de factura valido');
				document.getElementById("txtUfoliofactura").focus();	
			}
		}
   }
  */
  // Modificacion BY 02/09/2013
   function generacionPdfXml(){
	   var ufoliofactura = document.getElementById("txtUfoliofactura").value; 
	   var tipofactura = document.getElementById("selTipoFactura").value;
	   var msubtotal = document.getElementById("txtmSubtotal").value;
	   var miva = document.getElementById("txtmIva").value;
	   var mtotal = document.getElementById("txtmTotal").value;
	   var smetodopago = document.getElementById("txtTipoPago").value;
	   var cmarca = document.getElementById('cMarca').value;
	   var folioSustitucion = document.getElementById("txtUfoliofacturaSustitucion").value;
	   var uuidSustitucion = document.getElementById("hdenUuidSustitucion").value;
	   var checkSustitucion = document.getElementById("chkSustitucion").checked
	    
	   if(smetodopago!='99'){
		   var nocuenta = document.getElementById("txtNoCuenta").value;
	   } else {
		   var nocuenta = '';
	   } 
 
	   alert('Metodo pago' + smetodopago); 

		   if((tipofactura>0)&&(ufoliofactura>0)){
				if((tipofactura==1) ||(tipofactura==3)){
					var descripcionfactura = document.getElementById("txtDescripcion").value;
					if(descripcionfactura==""){
						alert("Debe ingresar una descripcion para la factura");
						document.getElementById("txtDescripcion").focus();
					}else{
						LoadFacturaElectronica(descripcionfactura,0);
						FacturaElectronicaEmpresaAjax.crearFacturaEmpresa(facturaelectronicaBean,ufoliofactura,tipofactura,msubtotal,miva,mtotal,nocuenta,smetodopago,uuidSustitucion,checkSustitucion,generarFactura_CallBack);
						//FacturaElectronicaEmpresaAjax.crearFacturaEmpresa(facturaelectronicaBean,ufoliofactura,tipofactura,msubtotal,miva,mtotal,nocuenta,smetodopago,generarFactura_CallBack);
					}
				}else if((tipofactura==2)){
					LoadFacturaElectronica("",0);
						FacturaElectronicaEmpresaAjax.crearFacturaEmpresa(facturaelectronicaBean,ufoliofactura,tipofactura,msubtotal,miva,mtotal,nocuenta,smetodopago,uuidSustitucion,checkSustitucion,generarFactura_CallBack);
						//FacturaElectronicaEmpresaAjax.crearFacturaEmpresa(facturaelectronicaBean,ufoliofactura,tipofactura,msubtotal,miva,mtotal,nocuenta,smetodopago,generarFactura_CallBack);
					
				}else if(tipofactura==4){
					var ordencompra = document.getElementById("txtOrdenCompra").value;
					if((ordencompra=="") ||(ordencompra==0)){
						alert("Debe ingresar una orden de compra valida");
						document.getElementById("txtOrdenCompra").focus();
					}else{
						LoadFacturaElectronica("",ordencompra); 
						//FacturaElectronicaEmpresaAjax.crearFacturaEmpresa(facturaelectronicaBean,ufoliofactura,tipofactura,msubtotal,miva,mtotal,nocuenta,smetodopago,generarFactura_CallBack);
						
					}
				}
			}else{
				if(tipofactura==0){
					alert('Debe elegir un formato adecuado de factura');
					document.getElementById("selTipoFactura").focus();
					return false;
				}if((ufoliofactura==0)||(ufoliofactura=="")){
					alert('Debe ingresar un folio de factura valido');
					document.getElementById("txtUfoliofactura").focus();	
				}
			}
	   
	   
	   
   }

   function generarFactura_CallBack(data) {
	   //alert(data);
	   if (data!=""){
		   	alert(data);
			 adminDIV("creacionPdf","hidden","none");
			 snombre = "FacturaOrden";
			 showPopWin(data, 800, 400, snombre);			 
			 // abrirVentanaOrden(data,snombre); 
		 }
   }
   /*
   function limpiaFactura()
   {
	   var frmPantalla = window.document.frmFacturacion;
	   //valorCombo(frmFacturacion.selTipoFactura,0);
	   document.getElementById("txtUfoliofactura").value=""; 
	   //document.getElementById("txtDescripcion").value="ESTUDIOS REALIZADOS SEGUN RELACION ADJUNTA";
	   //adminDIV("txtDescripcion","hidden","none");	
	   //adminDIV("labelfactura","hidden","none");
	   adminDIV("montosFactura","hidden","none");
	   document.getElementById("txtUfoliofactura").focus();
	  
	   
   }*/
   //Modificacion BY 02/09/2013
   function limpiaFactura()
   {
	   var frmPantalla = window.document.frmFacturacion;
	   //valorCombo(frmFacturacion.selTipoFactura,0);
	   document.getElementById("txtUfoliofactura").value=""; 
	   //document.getElementById("txtDescripcion").value="ESTUDIOS REALIZADOS SEGUN RELACION ADJUNTA";
	   //adminDIV("txtDescripcion","hidden","none");	
	   //adminDIV("labelfactura","hidden","none");
	   adminDIV("montosFactura","hidden","none");
	   document.getElementById("txtUfoliofactura").focus();	   
	   frmPantalla.txtUfoliofactura.disabled = false;
  		frmPantalla.selMarca.disabled = false;
   }

   
   function buscaFactura(){
	   var ufoliofactura = document.getElementById("txtUfoliofactura").value; 
	   var selectedMarca = document.getElementById("selMarca").value; 
	   var marca='';
	   if(selectedMarca==1){
		   marca='OLAB';
	   } else if (selectedMarca==4){
		   marca='AZTECA';
	   } else if (selectedMarca==5){
		   marca='SWISSLAB';
	   } else if (selectedMarca==7){
		   marca='JENNER PRADO';
	   } else if (selectedMarca==8){
		   marca='JENNER LEAN';
	   } 
	       if ((ufoliofactura>0) ||(ufoliofactura !="")) {
	    	   if(confirm("¿Esta seguro de mostrar la factura con folio "+ufoliofactura+" de la marca "+marca+" ?")){
		        var frmPantalla = window.document.frmFacturacion;
		    		//adminDIV("gridbusquedaFacturas","visible","inline");
			    	BusquedaFacturas.buscarFacturaAjuste(frmPantalla.txtUfoliofactura.value, frmPantalla.selMarca.value, buscarFacturaAjuste_CallBack);
	    	   }
			}else{
					alert('Debe ingresar un folio de factura adecuado');//mio EMZ
					document.getElementById("txtUfoliofactura").focus();		
			} 
	   	
   }
      
   var ventana_secundaria = null;     
   function showSubModalSustitucion() {
	   if(ventana_secundaria != null){
		   ventana_secundaria.close();
	   }
	   var ufoliofactura = document.getElementById("txtUfoliofactura").value; 
	   var selectedMarca = document.getElementById("selMarca").value; 
	   ventana_secundaria = window.open('/web2labportal/servlet/template/web2lab,sustitucion,SustitucionFactura.vm?ufoliofactura='+ufoliofactura+'&cmarca='+selectedMarca+'&tipoFactura=1',"SustitucionFactura","width=900,height=300,menubar=no,scrollbars=yes");
	   //('/web2labportal/servlet/template/web2lab,sustitucion,SustitucionFactura.vm?ufoliofactura='+ufoliofactura+'&cmarca='+selectedMarca, 950, 300, "Sustitucion");
   }
            
   function agregarValoresSustitucion(datafolio,datakfactura,datauuid){
	   document.getElementById("txtUfoliofacturaSustitucion").value=datafolio;
	   document.getElementById("hdenkfacturaSustitucion").value=datakfactura;
	   document.getElementById("hdenUuidSustitucion").value=datauuid;
   }
   
   function buscarFacturaAjuste_CallBack(data){ 
	   var frmPantalla = window.document.frmFacturacion;
	   	if(data!=""){
	   		adminDIV("montosFactura","visible","inline");
	   		codeDIVHTML("montosFactura",data);	  
	   		frmPantalla.txtUfoliofactura.disabled = true;
	   		frmPantalla.selMarca.disabled = true;
	   	}else{
	   		frmPantalla.txtUfoliofactura.disabled = false;
	   		frmPantalla.selMarca.disabled = false;
	   	}
		  
		  
	  }
   
   function validaMonto (obj) {
		var montopermitido = 3;
		var frm = document.getElementById("frmFacturacion");
		
		var msubtotalfactura = document.getElementById("hdenMsubtotal").value;
		var mivafactura = document.getElementById("hdenMiva").value;
		var mtotalfactura = document.getElementById("hdenMtotal").value;
		var montoactual = eval("frm."+obj+".value");
		
		if(obj == "txtmSubtotal") {
		var monto = parseFloat(msubtotalfactura - montoactual);
			if(monto < 0) {
				monto = monto * -1;
			}
			if(montopermitido < monto) {
				alert('No esta permitido un ajuste tan alto, favor de verificarlo');
				document.getElementById(obj).value = msubtotalfactura;
				document.getElementById(obj).focus();
				document.getElementById('idCrearPdfXml').disabled = true;
			}else{
				if((eval("frm."+obj+".value") == 0) ||(monto < 0)){
					alert('No puede realizar un ajuste mayor a 3 pesos');
					document.getElementById(obj).value = msubtotalfactura;
					document.getElementById(obj).focus();
					document.getElementById('idCrearPdfXml').disabled = true;
				} else {
					document.getElementById('idCrearPdfXml').disabled = false;	
				}
			}
		}if(obj == "txtmIva") {
		var monto = parseFloat(mivafactura - montoactual);
			if(monto < 0) {
				monto = monto * -1;
			}
			if(montopermitido < monto) {
				alert('No esta permitido un ajuste tan alto, favor de verificarlo');
				document.getElementById(obj).value = mivafactura;
				document.getElementById(obj).focus();
				document.getElementById('idCrearPdfXml').disabled = true;
			}else{
				if((eval("frm."+obj+".value") == 0) ||(monto < 0)){
					alert('No puede realizar un ajuste mayor a 3 pesos');
					document.getElementById(obj).value = mivafactura;
					document.getElementById(obj).focus();
					document.getElementById('idCrearPdfXml').disabled = true;
				} else {
					document.getElementById('idCrearPdfXml').disabled = false;	
				}
			}		
		}if(obj == "txtmTotal") {
		var monto = parseFloat(mtotalfactura - montoactual);
			if(monto < 0) {
				monto = monto * -1;
			}
			if(montopermitido < monto) {
				alert('No esta permitido un ajuste tan alto, favor de verificarlo');
				document.getElementById(obj).value = mtotalfactura;
				document.getElementById(obj).focus();
				document.getElementById('idCrearPdfXml').disabled = true;
			}else{
				if((eval("frm."+obj+".value") == 0) ||(monto < 0)){
					alert('No puede realizar un ajuste mayor a 3 pesos');
					document.getElementById(obj).value = mtotalfactura;
					document.getElementById(obj).focus();
					document.getElementById('idCrearPdfXml').disabled = true;
				} else {
					document.getElementById('idCrearPdfXml').disabled = false;	
				}
			}
		}

	}
   
   /*
	 function LoadFacturaElectronica(sdescripcion) {
			facturaelectronicaBean.sserie="A";
			facturaelectronicaBean.sfolio="1";
			facturaelectronicaBean.fecha="2010-12-31 10:55:31";
			facturaelectronicaBean.cconvenio=309;
			facturaelectronicaBean.nnumeroaprobacion="422843";
			facturaelectronicaBean.sanoaprobacion="2010";
			facturaelectronicaBean.sformapago="pago en una sola exhibicion"; 
			facturaelectronicaBean.mtotal= "0";
			facturaelectronicaBean.miva="0";
			facturaelectronicaBean.mdescuento="0";
			facturaelectronicaBean.stipocomprobante="ingreso";
			facturaelectronicaBean.sncertificado="00001000000202233501";
//			facturaelectronicaBean.sncertificado="00001000000102101701";
			//variables del emisorOlab	
			facturaelectronicaBean.srazonsocialemisor="ESTUDIOS CLINICOS DR TJ ORIARD SA DE CV";
			facturaelectronicaBean.srfcemisor="ECD741021QA5";
			facturaelectronicaBean.scalleemisor="AV. REVOLUCION No.56";
			facturaelectronicaBean.snexterioremisor="";
			facturaelectronicaBean.sninterioremisor="";
			facturaelectronicaBean.scoloniaemisor="ESCANDON";
			facturaelectronicaBean.sciudademisor="MEXICO D.F.";
			facturaelectronicaBean.smunicipioemisor="MIGUEL HIDALGO";
			facturaelectronicaBean.sestadoemisor="MEXICO D.F.";
			facturaelectronicaBean.scodigopostalemisor="11800";
			facturaelectronicaBean.spaisemisor="MEXICO";				
			//variables del emisorSucursal
			facturaelectronicaBean.scallesuc="AV. VASCO DE QUIROGA NO.3380 P.B.";
			facturaelectronicaBean.snexteriorsuc="";
			facturaelectronicaBean.sninteriorsuc="";
			facturaelectronicaBean.scoloniasuc="LOMAS DE SANTA FE";
			facturaelectronicaBean.sciudadsuc="MEXICO D.F.";
			facturaelectronicaBean.smunicipiosuc="CUAJIMALPA";
			facturaelectronicaBean.sestadosuc="DISTRITO FEDERAL";
			facturaelectronicaBean.scodigopostalsuc="05300";
			facturaelectronicaBean.spaissuc="MEXICO";				
			//Datos del receptor
			facturaelectronicaBean.hDatosFiscal="";
			facturaelectronicaBean.srazonsocialreceptor="";
			facturaelectronicaBean.srfcreceptor="";
			facturaelectronicaBean.scallereceptor="";
			facturaelectronicaBean.snexteriorreceptor="";
			facturaelectronicaBean.sninteriorreceptor="";
			facturaelectronicaBean.scoloniareceptor="";
			facturaelectronicaBean.sciudadreceptor="";
			facturaelectronicaBean.smunicipioreceptor="";
			facturaelectronicaBean.sestadoreceptor="";
			facturaelectronicaBean.scodigopostalreceptor="";
			facturaelectronicaBean.spaisreceptor="MEXICO";
			//impuesto
			facturaelectronicaBean.sdescripcion=sdescripcion;
			facturaelectronicaBean.ssellodigital="";
			facturaelectronicaBean.scadenaoriginal="";
			facturaelectronicaBean.ccliente=94; 	
		}
	 */
   //Modificacion BY 02/09/2013
	 function LoadFacturaElectronica(sdescripcion,iordencompra) {
		 
			
			facturaelectronicaBean.sfolio="1";
			facturaelectronicaBean.fecha="2010-12-31 10:55:31";
			facturaelectronicaBean.cconvenio=309;
			facturaelectronicaBean.nnumeroaprobacion="422843";
			facturaelectronicaBean.sanoaprobacion="2010";
			facturaelectronicaBean.sformapago="pago en una sola exhibicion"; 
			facturaelectronicaBean.mtotal= "0";
			facturaelectronicaBean.miva="0";
			facturaelectronicaBean.mdescuento="0";
			facturaelectronicaBean.stipocomprobante="ingreso";
			facturaelectronicaBean.sncertificado="00001000000202233501";
//			facturaelectronicaBean.sncertificado="00001000000102101701";
			//variables del emisorOlab	
			if (document.getElementById('cMarca').value == 1) {
				facturaelectronicaBean.sserie="A";
				facturaelectronicaBean.srazonsocialemisor="ESTUDIOS CLINICOS DR TJ ORIARD SA DE CV";
				facturaelectronicaBean.srfcemisor="ECD741021QA5";
				facturaelectronicaBean.scalleemisor="AV. REVOLUCION No.56";
				facturaelectronicaBean.snexterioremisor="";
				facturaelectronicaBean.sninterioremisor="";
				facturaelectronicaBean.scoloniaemisor="ESCANDON";
				facturaelectronicaBean.sciudademisor="MEXICO D.F.";
				facturaelectronicaBean.smunicipioemisor="MIGUEL HIDALGO";
				facturaelectronicaBean.sestadoemisor="MEXICO D.F.";
				facturaelectronicaBean.scodigopostalemisor="11800";
				facturaelectronicaBean.spaisemisor="MEXICO";
				facturaelectronicaBean.cmarca = 1;
			} else if (document.getElementById('cMarca').value == 4){ 
				facturaelectronicaBean.sserie="AZ";
				facturaelectronicaBean.srazonsocialemisor="LABORATORIO QUIMICO CLINICO AZTECA S.A.P.I. DE C.V.";
				facturaelectronicaBean.srfcemisor="LQC920131M20";
				facturaelectronicaBean.scalleemisor="SIMON BOLIVAR 15";
				facturaelectronicaBean.snexterioremisor="";
				facturaelectronicaBean.sninterioremisor="";
				facturaelectronicaBean.scoloniaemisor="LOS REYES ACAQUILPAN CENTRO";
				facturaelectronicaBean.sciudademisor="ESTADO DE MEXICO";
				facturaelectronicaBean.smunicipioemisor="LA PAZ";
				facturaelectronicaBean.sestadoemisor="MEXICO D.F.";
				facturaelectronicaBean.scodigopostalemisor="56400";
				facturaelectronicaBean.spaisemisor="MEXICO";				
				facturaelectronicaBean.cmarca = 4;
			} else if (document.getElementById('cMarca').value == 5){
				facturaelectronicaBean.sserie="AS";
				facturaelectronicaBean.srazonsocialemisor="SWISSLAB S.A. de C.V.";
				facturaelectronicaBean.srfcemisor="SWI1201268J8";
				facturaelectronicaBean.scalleemisor="AV. MIGUEL HIDALGO 1729 PTE.";
				facturaelectronicaBean.snexterioremisor="";
				facturaelectronicaBean.sninterioremisor="";
				facturaelectronicaBean.scoloniaemisor="OBISPADO";
				facturaelectronicaBean.sciudademisor="MONTERREY";
				facturaelectronicaBean.smunicipioemisor="MONTERREY";
				facturaelectronicaBean.sestadoemisor="NUEVO LEON";
				facturaelectronicaBean.scodigopostalemisor="64060";
				facturaelectronicaBean.spaisemisor="MEXICO";				
				facturaelectronicaBean.cmarca = 5;
			} else if (document.getElementById('cMarca').value == 7){
				if(document.getElementById("selMarca").value==7){
					facturaelectronicaBean.sserie="AJP";
					facturaelectronicaBean.srazonsocialemisor="LABORATORIO CLINICO DEL PRADO S.A. DE C.V.";
					facturaelectronicaBean.srfcemisor="LCP061017PA9";
					facturaelectronicaBean.scalleemisor="MEDELLIN 153";
					facturaelectronicaBean.snexterioremisor="";
					facturaelectronicaBean.sninterioremisor="";
					facturaelectronicaBean.scoloniaemisor="ROMA NORTE";
					facturaelectronicaBean.sciudademisor="CIUDAD DE MEXICO";
					facturaelectronicaBean.smunicipioemisor="CUAUHTEMOC";
					facturaelectronicaBean.sestadoemisor="CIUDAD DE MEXICO";
					facturaelectronicaBean.scodigopostalemisor="06700";
					facturaelectronicaBean.spaisemisor="MEXICO";				
					facturaelectronicaBean.cmarca = 7;
				}else if (document.getElementById("selMarca").value==8){
					facturaelectronicaBean.sserie="AJL";
					facturaelectronicaBean.srazonsocialemisor="LABORATORIO CLINICO LEAN S.A. DE C.V.";
					facturaelectronicaBean.srfcemisor="LCL050622DD9";
					facturaelectronicaBean.scalleemisor="MEDELLIN 153";
					facturaelectronicaBean.snexterioremisor="";
					facturaelectronicaBean.sninterioremisor="";
					facturaelectronicaBean.scoloniaemisor="ROMA NORTE";
					facturaelectronicaBean.sciudademisor="CIUDAD DE MEXICO";
					facturaelectronicaBean.smunicipioemisor="CUAUHTEMOC";
					facturaelectronicaBean.sestadoemisor="CIUDAD DE MEXICO";
					facturaelectronicaBean.scodigopostalemisor="06700";
					facturaelectronicaBean.spaisemisor="MEXICO";				
					facturaelectronicaBean.cmarca = 7;
				}
			}
			//variables del emisorSucursal
			facturaelectronicaBean.scallesuc="AV. VASCO DE QUIROGA NO.3380 P.B.";
			facturaelectronicaBean.snexteriorsuc="";
			facturaelectronicaBean.sninteriorsuc="";
			facturaelectronicaBean.scoloniasuc="LOMAS DE SANTA FE";
			facturaelectronicaBean.sciudadsuc="MEXICO D.F.";
			facturaelectronicaBean.smunicipiosuc="CUAJIMALPA";
			facturaelectronicaBean.sestadosuc="DISTRITO FEDERAL";
			facturaelectronicaBean.scodigopostalsuc="05300";
			facturaelectronicaBean.spaissuc="MEXICO";				
			//Datos del receptor
			facturaelectronicaBean.hDatosFiscal="";
			facturaelectronicaBean.srazonsocialreceptor="";
			facturaelectronicaBean.srfcreceptor="";
			facturaelectronicaBean.scallereceptor="";
			facturaelectronicaBean.snexteriorreceptor="";
			facturaelectronicaBean.sninteriorreceptor="";
			facturaelectronicaBean.scoloniareceptor="";
			facturaelectronicaBean.sciudadreceptor="";
			facturaelectronicaBean.smunicipioreceptor="";
			facturaelectronicaBean.sestadoreceptor="";
			facturaelectronicaBean.scodigopostalreceptor="";
			facturaelectronicaBean.spaisreceptor="MEXICO";
			//impuesto
			facturaelectronicaBean.sdescripcion=sdescripcion;
			facturaelectronicaBean.iOrdenCompra=iordencompra;
			facturaelectronicaBean.ssellodigital="";
			facturaelectronicaBean.scadenaoriginal="";
			facturaelectronicaBean.ccliente=94; 	
		}
	 function FacturaElectronicaBean() {
			sserie=null,
			sfolio=null,
			fecha=null,
			cconvenio=null,
			nnumeroaprobacion=null,
			sanoaprobacion=null,
			sformapago=null, 
			msubtotal=null,
			msubtotaluniemp=null,
			mdescuento=null,
			mtotal=null,
			stipocomprobante=null,
			sncertificado=null,
			cert=null,
		    //variables del emisorOlab	
			srazonsocialemisor=null,
			srfcemisor=null,
			scalleemisor=null,
			snexterioremisor=null,
			sninterioremisor=null,
			scoloniaemisor=null,
			sciudademisor=null,
			smunicipioemisor=null,
			sestadoemisor=null,
			scodigopostalemisor=null,
			spaisemisor=null,
			//variables del emisorSucursal
			scallesuc=null,
			snexteriorsuc=null,
			sninteriorsuc=null,
			scoloniasuc=null,
			sciudadsuc=null,
			smunicipiosuc=null,
			sestadosuc=null,
			scodigopostalsuc=null,
			spaissuc=null,
			//Datos del receptor
			hDatosFiscal=null,
			srazonsocialreceptor=null,
			srfcreceptor=null,
			scallereceptor=null,
			snexteriorreceptor=null,
			sninteriorreceptor=null,
			scoloniareceptor=null,
			sciudadreceptor=null,
			smunicipioreceptor=null,
			sestadoreceptor=null,
			scodigopostalreceptor=null,
			spaisreceptor=null,
			//impuesto
			miva=null,
			sdescripcion=null,
			ssellodigital=null,
			lstexamenes=null,
			scadenaoriginal=null,
			ccliente=null,
			cmarca=null
		}