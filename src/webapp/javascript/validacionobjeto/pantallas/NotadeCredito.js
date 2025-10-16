

// var hostServerWebApp = "http://10.20.26.6:8192";
 
 var hostServerWebApp = "http://10.20.26.6:8192";


/********************* Negocio *******************************/
 function buscarConvenioRapido(objConvenio,strBuscar) {
       var frmPantalla = window.document.frmNotasdeCredito;	
		if ((strBuscar.length == 9) && (validaSoloNumeros(strBuscar))) {
				var strPromocion = strBuscar.substring(0,4);
				
				if (strPromocion.substring(0,1) == "0") {
					buscaTextoSelect(objConvenio,strPromocion.substring(1));
				} else {
					buscaTextoSelect(objConvenio,strPromocion);
				}
		} else {
			buscaTextoSelect(objConvenio,strBuscar);
			displayCargaDatos();
		}
	}
 
 
 function buscarConvenioRapidoAsignacion(objConvenio,strBuscar) {
     var frmPantalla = window.document.frmAsignacionBloques;
     var strbloques = loopSelected();
		if ((strBuscar.length == 9) && (validaSoloNumeros(strBuscar))) {
				var strPromocion = strBuscar.substring(0,4);
				
				if (strPromocion.substring(0,1) == "0") {
					buscaTextoSelect(objConvenio,strPromocion.substring(1));
				} else {
					buscaTextoSelect(objConvenio,strPromocion);
				}
		} else {
			buscaTextoSelect(objConvenio,strBuscar);
			 
			displayCargaDatosAsignacion(strbloques);
		}
	}
 
 function buscarConvenioAsignarBloque(objConvenio,strBuscar) {
     var frmPantalla = window.document.frmCambiodeBloque;
     var strbloques = loopSelected();
		if ((strBuscar.length == 9) && (validaSoloNumeros(strBuscar))) {
				var strPromocion = strBuscar.substring(0,4);
				
				if (strPromocion.substring(0,1) == "0") {
					buscaTextoSelect(objConvenio,strPromocion.substring(1));
				} else {
					buscaTextoSelect(objConvenio,strPromocion);
				}
		} else {
			buscaTextoSelect(objConvenio,strBuscar);
			 
			displayCargarBloques(strbloques);
		}
	}
 
   
   function limpiaPantalla()
   {
	   var frmPantalla = window.document.frmNotasdeCredito;
	   valorCombo(frmPantalla.selConvenios,356);
	   adminDIV("gridcargaFacturas","hidden","none");
	   frmPantalla.txtBuscarConvenio.value="";
	   document.getElementById("txtBuscarConvenio").focus();
   }
   
   
   function limpiaPantallaAsignacionB()
   {
	   var frmPantalla = window.document.frmAsignacionBloques;
	   valorCombo(frmPantalla.selConvenios,356);
	   adminDIV("gridcargaFacturas","hidden","none");
	   frmPantalla.txtBuscarConvenio.value="";
	   document.getElementById("txtBuscarConvenio").focus();
   }
   
   function limpiaPantallaAsignacionBloques()
   {
	   var frmPantalla = window.document.frmCambioBloques;
	   valorCombo(frmPantalla.selConvenios,356);	   
	   frmPantalla.txtBuscarConvenio.value="";
	   frmPantalla.txtBloqueAsignar.value="";
	   document.getElementById("txtBuscarConvenio").focus();
   }
  
   function displayCargaDatos(){
	   var frmPantalla = window.document.frmNotasdeCredito;
	   var cconvenio = TypeObjeto(frmPantalla.selConvenios); 
	   frmPantalla.hdnCconvenio.value=cconvenio;
	   NotasdeCredito.getFacturasConvenio(cconvenio,displayfacturas_CallBack);    
	     
	} 
    
   
   function displayCargaDatosAsignacion(){
	   var frmPantalla = window.document.frmAsignacionBloques;
	   var cconvenio = TypeObjeto(frmPantalla.selConvenios); 
	   var strbloques = loopSelected();
	   frmPantalla.hdnCconvenio.value=cconvenio;
	   NotasdeCredito.getFacturasConvenioAsignacion(cconvenio,strbloques,displayfacturasAsignacion_CallBack);   
		  //aquimodificque   
	} 
   
   function displayCargarBloques(){
	   var frmPantalla = window.document.frmCambioBloques;
	   var cconvenio = TypeObjeto(frmPantalla.selConvenios); 
	   var strbloques = loopSelected();
	   var nuevoBloque = frmPantalla.txtBloqueAsignar.value;
	   frmPantalla.hdnCconvenio.value=cconvenio;
	   
	} 
   
   function datoscambioBloque(){
	   var frmPantalla = window.document.frmCambioBloques;
	   var cconvenio = TypeObjeto(frmPantalla.selConvenios); 
	   var strbloques = loopSelected();
	   var nuevoBloque = frmPantalla.txtBloqueAsignar.value;
	   frmPantalla.hdnCconvenio.value=cconvenio;
	   //alert("Variables "+cconvenio+"  "+strbloques+"  "+nuevoBloque );
	   NotasdeCredito.getCambioDeBloque(cconvenio,strbloques,nuevoBloque,displayBloqueAsignacion_CallBack);   
		    
	} 
   
   function displayBloqueAsignacion_CallBack(data){
	   
	   alert("- "+data );
   }
   
   function displayfacturas_CallBack(data){
	   
	   adminDIV("gridcargaFacturas","visible","inline");
	   codeDIVHTML("gridcargaFacturas",data);
   }
   
   function displayfacturasAsignacion_CallBack(data){
	   
	   adminDIV("gridcargaFacturas","visible","inline");
	   codeDIVHTML("gridcargaFacturas",data);
   }
   
   
   
   function elegirfactura(ObjChkId){
	    var frm=document.getElementById("frmNotasdeCredito");
		var suma=0;
		var facturastotales="";
		for (i=0;i<frm.elements.length;i++)
	   {
		   if(frm.elements[i].type=="checkbox"){
			   if(frm.elements[i].checked==true){
					 var factura = frm.elements[i].name;
					 var nombre = factura.substring(5,factura.length);
					 var nombrecajatexto='txt'+nombre;

								//alert("nombrecajatexto"+nombrecajatexto+" valor"+"frm."+nombrecajatexto+".value");
					 			facturastotales=facturastotales+nombre+","+eval("frm."+nombrecajatexto+".value")+";";
								suma = suma+ parseFloat(eval("frm."+nombrecajatexto+".value")); 
								//alert("facturastotales"+facturastotales);
								frm.txtTotalNota.value=roundit(suma,2);
								frm.hdenFacturasNotas.value=facturastotales;
								frm.hdenmontoFactura.value=suma;
								 
			   }else{
					frm.txtTotalNota.value=suma;
				}
		   	}
	   }
	}

   

   function elegirfacturaAsignarBloque(ObjChkId){
	    var frm=document.getElementById("frmAsignacionBloques");
		var suma=0;
		var facturastotales="";
		var frmPantalla = window.document.frmAsignacionBloques;
		var srtbloques = loopSelected();
	 	var bloques =frmPantalla.txtBuscarConvenio.value;
		
		for (i=0;i<frm.elements.length;i++)
	   {
		   if(frm.elements[i].type=="checkbox"){
			   if(frm.elements[i].checked==true){
					 var factura = frm.elements[i].name;
					 var nombre = factura.substring(5,factura.length);
					 var nombrecajatexto='txt'+nombre;
					 		//	alert ("por fin el bloque "+srtbloques);
					 		//	alert("nombre.---"+nombre+" ------"+nombrecajatexto+".value"+"MONTOS.-"+"--EL Valor ---"+bloques);
							//	alert("nombrecajatexto"+nombrecajatexto+" valor"+"frm."+nombrecajatexto+".value");
					 			facturastotales=facturastotales+nombre+","+eval("frm."+nombrecajatexto+".value")+";";
								suma = suma+ parseFloat(eval("frm."+nombrecajatexto+".value")); 
							//	alert("facturastotales.--"+facturastotales);
								frm.txtTotalNota.value=roundit(suma,2);
								frm.hdenFacturasNotas.value=facturastotales;
								frm.hdenmontoFactura.value=suma;
								frm.hdnABloques.value=srtbloques;
								 
			   }else{
					frm.txtTotalNota.value=suma;
				}
		   	}
	   }
	}
   
   
   function generarNota(){
	   var frm=document.getElementById("frmNotasdeCredito");
	   var generanota=false;
	   for (i=0;i<frm.elements.length;i++)
		   {
			   if(frm.elements[i].type=="checkbox"){
				   if(frm.elements[i].checked==true){
					  generanota=true;
					  break;
					}
				}
			}
			if(generanota){
				//alert ("revisar"+frm.hdenRutaNotaCredito.value+ "?strKfacturas="+ frm.hdenFacturasNotas.value+ "&montoTotalNota="+ frm.hdenmontoFactura.value+"&cConvenio="+ frm.hdnCconvenio.value, 900, 500, "CrearNotaCredito");
				showPopWin(frm.hdenRutaNotaCredito.value + "?strKfacturas="+ frm.hdenFacturasNotas.value+ "&montoTotalNota="+ frm.hdenmontoFactura.value+"&cConvenio="+ frm.hdnCconvenio.value, 900, 500, "CrearNotaCredito");   
			}else{
				alert('Debe seleccionar por lo menos una factura para generar la nota de cr&eacute;dito');
			}
			
   }
   
   function asignarBloqueFactura(){
	   var frm=document.getElementById("frmAsignacionBloques");
	   var asignarbloque=false;
	   for (i=0;i<frm.elements.length;i++)
		   {
			   if(frm.elements[i].type=="checkbox"){
				   if(frm.elements[i].checked==true){
					   asignarbloque=true;
					  break;
					}
				}
			}
			if(asignarbloque){
				alert ('revisar----'+frm.hdenRutaNotaCredito.value+ '?strKfacturas='+ frm.hdenFacturasNotas.value+ '&montoTotalNota='+ frm.hdenmontoFactura.value+'--*bloques**-'+frm.hdnABloques.value+'-**-&cConvenio='+ frm.hdnCconvenio.value, 900, 500, 'CrearNotaCredito')
				showPopWin(frm.hdenRutaNotaCredito.value + "?strKfacturas="+ frm.hdenFacturasNotas.value+ "&montoTotalNota="+ frm.hdenmontoFactura.value+"&aBloque="+frm.hdnABloques.value+"&cConvenio="+ frm.hdnCconvenio.value, 900, 500, "CrearNotaCredito");   
			}else{
				//alert ('revisar----'+frm.hdenRutaNotaCredito.value+ '?strKfacturas='+ frm.hdenFacturasNotas.value+ '&montoTotalNota='+ frm.hdenmontoFactura.value+'--***-'+frm.hdnABloques.value+'-**-&cConvenio='+ frm.hdnCconvenio.value, 900, 500, 'CrearNotaCredito')
				alert('Debe seleccionar una factura para asignar un bloque');
			}
			
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
   
   
   function mostrarFacturas(strKfacturas){ 
	   NotasdeCredito.getFacturasConvenioElegidas(strKfacturas,displayfacturaselegidas_CallBack);
	   }
  
//   function mostrarFacturasAsignacionBloque(strKfacturas,){ 
//	   if (strKfacturas.length > 0){
//		   var  strbloques = loopSelected();
//		   
//		    alert ("Si tienes el valor +" + strbloques);
//		   // NotasdeCredito.getFacturasConvenioElegidasAsignacionBloque(strKfacturas,strbloques,displayfacturaselegidasAsignacionBloque_CallBack); 
//	   } else {
//		   alert ("Si tienes el valor +" + strbloques);
//		   //NotasdeCredito.getFacturasConvenioElegidasAsignacionBloque(strKfacturas,strbloques,displayfacturaselegidasAsignacionBloque_CallBack); 
//	   }
//	
//	  
//	   }
   
   
   function mostrarFacturasAsignacionBloque(strKfacturas,srtbloques){ 

		   NotasdeCredito.getFacturasConvenioElegidasAsignacionBloque(strKfacturas,srtbloques,displayfacturaselegidasAsignacionBloque_CallBack); 

	   }
   

   

   function displayfacturaselegidas_CallBack(data){
		 codeDIVHTML("gridFactuasElegidas",data);
	 }
   
   function displayfacturaselegidasAsignacionBloque_CallBack(data){
		 codeDIVHTML("gridFactuasElegidasAsignacionBloque",data);
	 }
   
   function noseleccionarfacturas(){
	    var frm=document.getElementById("frmNotasdeCredito");
	    
	    for (i=0;i<frm.elements.length;i++)
		   {
			   if(frm.elements[i].type=="checkbox"){
				   frm.elements[i].checked=false;
			   }
		   }
	    frm.txtTotalNota.value=0;
	    displayCargaDatos();
   }
   
   
   function noseleccionarfacturasAsignar(){
	    var frm=document.getElementById("frmAsignacionBloques");
	    
	    for (i=0;i<frm.elements.length;i++)
		   {
			   if(frm.elements[i].type=="checkbox"){
				   frm.elements[i].checked=false;
			   }
		   }
	    frm.txtTotalNota.value=0;
	    displayCargaDatosAsignacion();
  }
   
   function porcentaje(obj){
	   var frm = document.getElementById("frmNotasdeCredito");
	   var montofactura=eval("frm."+obj+".value");
	   
		 for (i=0;i<frm.elements.length;i++){
			   if(frm.elements[i].type=="text"){
					if(frm.elements[i].name==obj){
						frm.hdenmontoFactura.value=roundit(montofactura,2);
						frm.elements[i].value="";
					}
			   }
		}
		
   }
   
   function dameporcentaje(obj){
	   var frm = document.getElementById("frmNotasdeCredito");
	   var porcenaje=eval("frm."+obj+".value");
	   var montoatomar;
	   var key=window.event.keyCode;
	   
	   if(key==13){
		   for (i=0;i<frm.elements.length;i++){
			   if(frm.elements[i].type=="text"){
					if(frm.elements[i].name==obj){
						montoatomar = (parseFloat(eval(frm.hdenmontoFactura.value))*parseFloat(porcenaje))/100;
						if(!parseFloat(eval(montoatomar))>parseFloat(eval(frm.hdenmontoFactura.value))) {
							frm.elements[i].value=montoatomar;
						} else{
							alert('El Monto es mas alto al de la Factura, esto no puede ser posible,Verifica el monto');
							frm.elements[i].value=frm.hdenmontoFactura.value;
						}
					}
			   }
		   }
		   
	   }
   }
	
  function crearNota(){ 
	  var frm = document.getElementById("frmAltaNotaCredito");
	  var facturasnota = frm.hdnFacturas.value;
	  var montonota = frm.hdnMontoNota.value;
	  var idusuario = frm.idUsuario.value;
	  var descripcionnota = frm.txtdescripcionNota.value;
	  var cconvenio = frm.hdnCconvenio.value;
	 
	  NotasdeCredito.creaNota(facturasnota,cconvenio,descripcionnota,montonota,idusuario,creaNota_CallBack);
	  
  }
  
  function creaNota_CallBack(data) {		
	  // strKfacturas=3288198,1;&montoTotalNota=1&cConvenio=309
	  var frm = document.getElementById("frmAltaNotaCredito");
	  var facturasnota = frm.hdnFacturas.value;
	  if(data!=""){
		  alert('Se genero la NC '+data);
		  document.getElementById("txtNumNota").value=data;
		  adminDIV("divFormFacturaNC","visible","inline");
	  }else{
		  alert('Error al generar la Nota de Credito');
	  }
  }	
  
  function showCamposSustitucion(){
		if (document.getElementById("chkAgregarSustitucion").checked){	
			adminDIV("divuuidSustitucionLabel","visible","inline");
			adminDIV("divuuidSustitucionText","visible","inline");
			
			adminDIV("divuuidFacturaLabel","hidden","none");
			adminDIV("divuuidFacturaText","hidden","none");
		}else{
			adminDIV("divuuidSustitucionLabel","hidden","none");
			adminDIV("divuuidSustitucionText","hidden","none");
			
			adminDIV("divuuidFacturaLabel","visible","inline");
			adminDIV("divuuidFacturaText","visible","inline");
			
			document.getElementById("chkAgregarSustitucion").checked = false;
		}
	}
  
  var ventana_secundaria = null;     
  function showSubModalSustitucion() {
	   if (ventana_secundaria != null){
		   ventana_secundaria.close();
	   }
	   
	   var ufoliofactura = document.getElementById("txtNumNota").value;
	   ventana_secundaria = window.open('/web2labportal/servlet/template/web2lab,sustitucion,SustitucionFactura.vm?ufoliofactura='+ufoliofactura+'&cmarca=0&tipoFactura=2&cconvenio=0',"SustitucionFactura","width=900,height=300,top=200,left=200,menubar=no,scrollbars=yes");
	   //('/web2labportal/servlet/template/web2lab,sustitucion,SustitucionFactura.vm?ufoliofactura='+ufoliofactura+'&cmarca='+selectedMarca, 950, 300, "Sustitucion");
  }
     
  function agregarValoresSustitucion(folio,kfactura,uuid){
	   //document.getElementById("txtUfoliofacturaSustitucion").value=folio;
	   //document.getElementById("hdenkfacturaSustitucion").value=kfactura;
	   document.getElementById("txtUuidSustitucion").value=uuid;
  }
  
  function emitirNota(){
	  var valida = true;
	  var numNotaCre = document.getElementById("txtNumNota").value;
	  var numFactura = document.getElementById("txtNumFactura").value;
	  var formaPago = document.getElementById("selFormaPago").value;
	  var metodoPago = document.getElementById("selMetodoPago").value;
	  var uuidFactura = document.getElementById("txtUuidFactura").value;
	  var checkboxSustitucion = document.getElementById("chkAgregarSustitucion").checked;
	  var uuidSustitucion = document.getElementById("txtUuidSustitucion").value;	  
	  
	  var concepto = document.getElementById("txtConcepto").value;
	  var cantidad = document.getElementById("txtCantidad").value;
	  var iva = document.getElementById("selIva").value;
	  
	  
	  if(concepto==""){
		  valida=false;
	  }
	  if(cantidad==""){
		  valida=false;
	  }
	  if(checkboxSustitucion){
		  if(uuidSustitucion==""){
			  valida=false;
		  }
	  }
	  
	  if(valida){
		  var uuid="";
		  if(checkboxSustitucion){
			  uuid=uuidSustitucion;
		  }else{
			  uuid=uuidFactura;
		  }
		window.open(hostServerWebApp+"/facturas/nota-credito/emitir?numeroNC="+numNotaCre+"&numeroFactura="+numFactura+"&formaPago="+formaPago+
			"&metodoPago="+metodoPago+"&uuid="+uuid+"&concepto="+concepto+"&cantidad="+cantidad+
			"&iva="+iva+"&sustitucion="+checkboxSustitucion, "_blank");
	  }
  }
           
  function asignarBloquesFactura(){
	  var frm = document.getElementById("frmAltaBloques");//versi cvambbio
	  var facturasnota = frm.hdnFacturas.value;
	  var montonota = frm.hdnMontoNota.value;
	  var idusuario = frm.idUsuario.value;
	  var descripcionnota = 23;//frm.txtdescripcionNota.value;
	  var cconvenio = frm.hdnCconvenio.value;
	  var strbloques =  frm.hdnABloques.value;
	  
	  //alert ("-S-"+facturasnota+""+cconvenio+"---cconvenio-"+descripcionnota+"--descripcionnota-"+montonota+"--montonota-"+idusuario+"--"+strbloques);
	 NotasdeCredito.asignarBloqueaFactura(facturasnota,cconvenio,descripcionnota,montonota,idusuario,strbloques,asignarBloquesFactura_CallBack);
	  
  }
  
  function asignarBloquesFactura_CallBack(data) {		
	  alert(data);
  }	
  
  
  
  
  function validamonto(montofactura,name){
		 var frm = document.getElementById("frmNotasdeCredito");
		 
		 var montonota=eval("frm."+name+".value");
		 //alert(montonota);
		 if(parseFloat(eval(montonota))>parseFloat(eval(montofactura))){
			 alert("El monto es mas alto a la factura, por favor verif&iacute;quelo");
			 document.getElementById(name).value="";
			 document.getElementById(name).focus();
		 }else{
			if((montonota =="")||(parseFloat(montonota)<="0")){
				alert("Por favor ingrese montos validos");
				document.getElementById(name).value="";
				document.getElementById(name).focus();
			}
		 }
	 }
  
  function buscarFactura() {
		var frmPantalla = window.document.frmNotasdeCredito;
		showPopWin(frmPantalla.hdenRutaFactura.value + "?kFactura=0&formatFactura=0", 800, 500, "Buscar Factura");	
	}
  
  
  
  