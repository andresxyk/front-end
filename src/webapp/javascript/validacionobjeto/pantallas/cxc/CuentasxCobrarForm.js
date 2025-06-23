//var hostServerWebApp = "http://10.20.26.6:8192"; 
var hostServerWebApp = "http://10.20.26.6:8192";

function showSubModalPago(kfactura,formatFactura) {
	var frmPantalla = window.document.frmAdminClientes;changeMontoPagar
	showPopWin(frmPantalla.hdenRutaPago.value + "?kFactura=" + kfactura + "&formatFactura=" + formatFactura, 800, 400, "Registra Factura");
}

function buscarFactura() {
	var frmPantalla = window.document.frmAdminClientes;
	showPopWin(frmPantalla.hdenRutaPago.value + "?kFactura=0&formatFactura=0", 800, 500, "Buscar Factura");	
}

function showReporteFacturaPagos() {
	var frmPantalla = window.document.frmAdminClientes;
	showPopWin(frmPantalla.hdenRutaReportes.value, 800, 400, "Reportes Factura y Pagos");
}

function showAntiguedadCxC() { 
	var frmPantalla = window.document.frmAdminClientes;
	showPopWin("/web2labportal/jsp/antiguedadCxC.jsp", 800, 400, "Antiguedad Saldos");
}


function init() 
{	
	var frmPantalla = window.document.frmAdminClientes;
    //DWRUtil.useLoadingMessage();
    disableDIV();
    adminDIV("gridbusquedaConvenios","hidden","none");	    	
 	codeDIVHTML("gridbusquedaConvenios","");
    adminDIV("gridbusquedaFacturas","hidden","none");	    	
 	codeDIVHTML("gridbusquedaFacturas","");
	codigoBean(frmPantalla.txtEstadoCliente,true,"");			
	codigoBean(frmPantalla.txtConvenio,true,0);		
	frmPantalla.idEstado.value = ""		
	frmPantalla.idEstado.disabled=true;
	frmPantalla.txtCorreoElectronico.value = "";
	frmPantalla.optResultado.checked = false;
	DatosCliente.getMarcasUser(frmPantalla.idUsuario.value,getMarcasUser_CallBack);
}

function initEmpresas() 
{
	var frmPantalla = document.getElementById("frmAdminClientes");
    //DWRUtil.useLoadingMessage();
    disableDIVEmpresas();
    adminDIV("gridbusquedaConvenios","hidden","none");	    	
 	codeDIVHTML("gridbusquedaConvenios","");
    adminDIV("gridbusquedaFacturas","hidden","none");	    	
 	codeDIVHTML("gridbusquedaFacturas","");
	codigoBean(frmPantalla.txtEstadoCliente,true,"");			
	codigoBean(frmPantalla.txtConvenio,true,0);		
	frmPantalla.idEstado.value = ""		
	frmPantalla.idEstado.disabled=true;
	frmPantalla.txtCorreoElectronico.value = "";
	frmPantalla.optResultado.checked = false;
	DatosCliente.getMarcasUser(frmPantalla.idUsuario.value,getMarcasUser_CallBack);
}

function getMarcasUser_CallBack(data) {
	var frmPantalla = window.document.frmAdminClientes;
	frmPantalla.marcasUser.value = data;		
}

function showclienteEmpresas(cCliente) {
	var frmPantalla = document.getElementById("frmAdminClientes");
	frmPantalla.txtCliente.value = cCliente;	 
	LoadBusquedaCliente();

	frmPantalla.txtCliente.disabled = true;
	document.getElementById('txtCliente').className = 'textflat';
    frmPantalla.txtRazonSocial.disabled = true;
	document.getElementById('txtRazonSocial').className = 'textflat';
    frmPantalla.txtRFC.disabled = true;
	document.getElementById('txtRFC').className = 'textflat';
    frmPantalla.txtMNEMONICO.disabled = true;
	document.getElementById('txtMNEMONICO').className = 'textflat';
    frmPantalla.txtNombreComercial.disabled = true;
	document.getElementById('txtNombreComercial').className = 'textflat';
	
	disableDIV();
    adminDIV("gridprogressbar","visible","inline");	
	clienteBean.breadonly = true;
	DatosCliente.buscarClienteCxC(clienteBean,clienteAceptado_CallBack);		
    adminDIV("TableConsultas","hidden","none");	    	    
}

function disableDIV() {
    adminDIV("gridPorDepto","hidden","none");
    adminDIV("gridPorExamen","hidden","none");
    adminDIV("gridConvenios","hidden","none");  
    adminDIV("gridbusquedaClientes","hidden","none");
    adminDIV("gridbusquedaDireccion","hidden","none");	    
    adminDIV("ExamenesCotizados","hidden","none");
    adminDIV("gridprogressbar","hidden","none");
 	codeDIVHTML("gridbusquedaDireccion","");
 	codeDIVHTML("gridbusquedaClientes","");
 	codeDIVHTML("ExamenesCotizados",""); 	
}

function disableDIVEmpresas() {
    adminDIV("gridPorDepto","hidden","none");
    adminDIV("gridPorExamen","hidden","none");
    adminDIV("gridConvenios","hidden","none");  
    adminDIV("gridbusquedaClientes","hidden","none");
    adminDIV("gridbusquedaDireccion","hidden","none");	    
    adminDIV("ExamenesCotizados","hidden","none");
 	codeDIVHTML("gridbusquedaDireccion","");
 	codeDIVHTML("gridbusquedaClientes","");
 	codeDIVHTML("ExamenesCotizados",""); 	
}


function noShowDIV() {
    adminDIV("gridPorDepto","hidden","none");
    adminDIV("gridPorExamen","hidden","none");
    adminDIV("gridConvenios","hidden","none");
    
    adminDIV("ExamenesCotizados","hidden","none");
    adminDIV("gridGridClienteConvenios","hidden","none");
    adminDIV("gridbusquedaConvenios","hidden","none");
    adminDIV("gridbusquedaFacturas","hidden","none");
    adminDIV("gridprogressbar","visible","inline");
}

function ShowDIV() {
    adminDIV("gridPorDepto","visible","inline");
    adminDIV("gridPorExamen","visible","inline");
    adminDIV("gridConvenios","visible","inline");
    adminDIV("ExamenesCotizados","visible","inline");
    adminDIV("gridGridClienteConvenios","visible","inline");
    adminDIV("gridbusquedaConvenios","visible","inline");
    adminDIV("gridbusquedaFacturas","visible","inline");
    adminDIV("gridprogressbar","hidden","none");
}

/************************************** INICIA FUNCIONES DEL CLIENTE *******************************************************************/
var clienteBean = new ClienteBean();

function cleanCliente() {
	var frmPantalla = window.document.frmAdminClientes;
	codigoBean(frmPantalla.txtCliente,false,"");
	frmPantalla.txtBuscarConvenio.value = "";
	frmPantalla.txtCliente.value = "";
	frmPantalla.txtRazonSocial.value = "";
	frmPantalla.txtRFC.value = "";
	frmPantalla.txtMNEMONICO.value = "";
	frmPantalla.txtCalle.value = "";
	frmPantalla.txtObservacion.value = "";
	frmPantalla.hdnCodigoPostal.value = "0";
	frmPantalla.txtColonia.value = "";
	frmPantalla.txtDelegacionMunicipio.value = "";
	frmPantalla.txtCodigoPostal.value = "";
	frmPantalla.selTipoPersona.selectedIndex = 0;
	frmPantalla.selEstado.selectedIndex = 0;
	frmPantalla.selTipoCliente.selectedIndex = 0;
	frmPantalla.selGiro.selectedIndex = 0;
	frmPantalla.selZonaAsignada.selectedIndex = 0;
	codigoBean(frmPantalla.txtEstadoCliente,true,"");
    adminDIV("gridbusquedaConvenios","hidden","none");	    	
 	codeDIVHTML("gridbusquedaConvenios","");
    adminDIV("gridbusquedaFacturas","hidden","none");	    	
 	codeDIVHTML("gridbusquedaFacturas","");
    disableDIV();
    frmPantalla.radTipoConv[0].disabled = false;
    frmPantalla.radTipoConv[1].disabled = false;
	frmPantalla.txtCorreoElectronico.value = "";
	frmPantalla.optResultado.checked = false;
}

		
function consultaClienteGrid(frmPantalla) 
{		
	txtRazonSocial = frmPantalla.txtRazonSocial;
	txtRFC = frmPantalla.txtRFC;
	txtCliente = frmPantalla.txtCliente;		
	txtMnemonico = frmPantalla.txtMNEMONICO;
	if (txtRazonSocial.value.length < 4  && txtRFC.value.length < 4  && txtMnemonico.value.length < 4 && (txtCliente.value==0 || txtCliente.value=="")) {
		disableDIV();
	} else {
		LoadBusquedaCliente();
		DatosCliente.buscarClientes(clienteBean,consultaClienteGrid_CallBack);
	}
}	
 

function clienteAceptado(intCliente) {
	var frmPantalla = window.document.frmAdminClientes;
	frmPantalla.txtCliente.value = intCliente;	 
	LoadBusquedaCliente();
	disableDIV();
    adminDIV("gridprogressbar","visible","inline");	
	DatosCliente.buscarClienteCxC(clienteBean,clienteAceptado_CallBack);	
}

function antiguedadSaldosCxC() {
	DatosCliente.antiguedadSaldosCxC("",antiguedadSaldosCxC_CallBack);	
}

function antiguedadSaldosCxC_CallBack(data){
	alert(data);
}

function clienteAceptado_CallBack(data) {
	var frmPantalla = window.document.frmAdminClientes;
	frmPantalla.txtCliente.value = data.ccliente;
	frmPantalla.txtRazonSocial.value = data.srazonsocial;
	frmPantalla.txtRFC.value = data.srfc;
	frmPantalla.txtMNEMONICO.value = data.smnemonico;
	frmPantalla.txtCalle.value = data.sdireccion;
	frmPantalla.txtObservacion.value = data.sobservaciones;
	frmPantalla.hdnCodigoPostal.value = data.ccodigopostal;
	frmPantalla.txtColonia.value = data.scolonia;
	frmPantalla.txtDelegacionMunicipio.value = data.sdelegacionmunicipio;
	frmPantalla.txtCodigoPostal.value = data.scodigopostal;
	frmPantalla.selEstado.selectedIndex = compareSelect(frmPantalla.selEstado,data.sestado);	
	frmPantalla.selTipoCliente.selectedIndex = data.ctipocliente;
	frmPantalla.selGiro.selectedIndex = data.cgirocliente;
	frmPantalla.selZonaAsignada.selectedIndex = 0;
	codigoBean(frmPantalla.txtEstadoCliente,true,data.sestadoregistro);		
    adminDIV("gridbusquedaFacturas","visible","inline");
 	codeDIVHTML("gridbusquedaFacturas",data.strFacturasGrid); 	
    adminDIV("gridprogressbar","hidden","none");
}

function consultaClienteGrid_CallBack(data) {
    adminDIV("gridbusquedaClientes","visible","inline");
 	codeDIVHTML("gridbusquedaClientes",data);
 	codeDIVHTML("gridbusquedaDireccion","");
    adminDIV("gridbusquedaDireccion","hidden","none");
}


function ocultarConvenios() {
	var objDIV = document.getElementById("gridGridClienteConvenios");
	if ((objDIV.style.visibility == "hidden") && (objDIV.style.display == "none")) {
	    adminDIV("gridGridClienteConvenios","visible","inline");			
	} else {
	    adminDIV("gridGridClienteConvenios","hidden","none");			
	}		
}

function ocultarGridFacturas(strGridName) {
	 
	var objDIV = document.getElementById(strGridName);
	if ((objDIV.style.visibility == "hidden") && (objDIV.style.display == "none")) {
	    adminDIV(strGridName,"visible","inline");
	    if(strGridName=='gridGridFacturasConvenios'){
	    	cargarHora(); 
	    }
	} else {
	    adminDIV(strGridName,"hidden","none");			
	}		
}

function cargarHora(){
	document.getElementById("selhora").innerHTML="";
	document.getElementById("selminutos").innerHTML="";
	document.getElementById("selsegundos").innerHTML="";

	var selecthora = document.getElementById('selhora');
	for (var i = 0; i<=23; i++){
	    var opt = document.createElement('option');
	    opt.value = i;
	    if(i<10){
	    	opt.innerHTML = '0'+i+' hrs';
	    }else{
	    	if (i==12) {
	    		opt.selected=true;
	    		opt.innerHTML = i+' hrs';
	    	}else{
	    		opt.innerHTML = i+' hrs';
	    	}	
	    }		    
	    selecthora.appendChild(opt);
	}		

	var selectmin = document.getElementById('selminutos');
	for (var i = 0; i<=59; i++){
	    var opt = document.createElement('option');
	    opt.value = i;
	    if(i<10){
	    	opt.innerHTML = '0'+i+' min';
	    }else{		    	
    		opt.innerHTML = i+' min';		    		
	    }		    
	    selectmin.appendChild(opt);
	}
	var selectseg = document.getElementById('selsegundos');
	for (var i = 0; i<=59; i++){
	    var opt = document.createElement('option');
	    opt.value = i;
	    if(i<10){
	    	opt.innerHTML = '0'+i+' seg';
	    }else{
	    	opt.innerHTML = i+' seg';
	    }		    
	    selectseg.appendChild(opt);
	}
}


function LoadCompletedCliente() {
	var frmPantalla = window.document.frmAdminClientes;
	clienteBean.ccliente=frmPantalla.txtCliente.value;
	clienteBean.srazonsocial=frmPantalla.txtRazonSocial.value;
	clienteBean.srfc=frmPantalla.txtRFC.value;
	clienteBean.smnemonico=frmPantalla.txtMNEMONICO.value;
	clienteBean.sdireccion=frmPantalla.txtCalle.value;
	clienteBean.sobservaciones=frmPantalla.txtObservacion.value;
	clienteBean.ccodigopostal=frmPantalla.hdnCodigoPostal.value;
	clienteBean.scolonia=frmPantalla.txtColonia.value;
	clienteBean.sdelegacionmunicipio=frmPantalla.txtDelegacionMunicipio.value;
	clienteBean.sestado=frmPantalla.selEstado[frmPantalla.selEstado.selectedIndex].text;
	clienteBean.scodigopostal=frmPantalla.txtCodigoPostal.value;
	clienteBean.ctipocliente=frmPantalla.selTipoCliente[frmPantalla.selTipoCliente.selectedIndex].value;
	clienteBean.cgirocliente=frmPantalla.selGiro[frmPantalla.selGiro.selectedIndex].value;
	clienteBean.ctipopersona=frmPantalla.selTipoPersona[frmPantalla.selTipoPersona.selectedIndex].value;
}

function LoadBusquedaCliente() {
	var frmPantalla = window.document.frmAdminClientes;
	clienteBean.ccliente=frmPantalla.txtCliente.value;
	clienteBean.srazonsocial=frmPantalla.txtRazonSocial.value;
	clienteBean.srfc=frmPantalla.txtRFC.value;
	clienteBean.smnemonico=frmPantalla.txtMNEMONICO.value;
	clienteBean.ctipocliente=69;
	clienteBean.breadonly = false;
	clienteBean.smarcauser = frmPantalla.marcasUser.value;
}


function ClienteBean() {
	ccliente=null,
	srazonsocial=null,
	srfc=null,
	smnemonico=null,
	sdireccion=null,	
	sobservaciones=null,
	ccodigopostal=null,
	scolonia=null,
	sdelegacionmunicipio=null,
	sestado=null,
	scodigopostal=null,
	ctipocliente=null,
	cgirocliente=null,
	ctipopersona=null,
	cestadoregistro=null,
	sestadoregistro=null,
	breadonly = null
}

/************************************** TERMINA FUNCIONES DEL CLIENTE *******************************************************************/
/************************************** INICIA FUNCIONES DEL CONVENIO *******************************************************************/

var convenioBean = new ConvenioBean();

function CleanScreen() {
	disableDIV();
	var frmPantalla = window.document.frmAdminClientes;
	codigoBean(frmPantalla.txtConvenio,true,0);		
	frmPantalla.txtNombreConvenio.value = "";
	frmPantalla.txtFechaInicio.value = "";
	frmPantalla.txtFechaFin.value = "";
 	valorCombo(frmPantalla.selTipoConvenio,0);
	codigoBean(frmPantalla.txtEstadoConvenio,true,"");		
	frmPantalla.hdnEstadoConvenio.value = "22"; 	
// 	TipoConvenio(data.ctipodescuento);	
    adminDIV("gridConvenios","visible","inline");
	frmPantalla.idEstado.value = ""		
	frmPantalla.idEstado.disabled=true; 
    frmPantalla.txtPorcentaje.value = "";
	frmPantalla.txtCorreoElectronico.value = "";
	frmPantalla.optResultado.checked = false;
 	valorCombo(frmPantalla.selTipoComercial,0);
    frmPantalla.radTipoConv[0].disabled = false;
    frmPantalla.radTipoConv[1].disabled = false;
}

function convenioAceptado(registro){
	noShowDIV();
	convenioBean.cconvenio=registro;
    buscarConvenio();		 	
}	 

function buscarConvenio() {		
	DatosCliente.buscarConvenio(convenioBean,buscarConvenio_CallBack);
}	
		
function buscarConvenio_CallBack(data)
{
    loadPantallaConvenio(data);
}	 	 

function visualizarFactura(strRuta) {
	snombre = "Factura";
	abrirVentanaOrden(strRuta,snombre);	   			     			    
}


function imprimirReporte(liga, nombre) 
{
	url = liga+'?eventSubmit_doReporteconvenio=action';	
	abrirVentana(url,nombre);
	return  true;
} 

	
function loadPantallaConvenio(data) {
	ShowDIV();
    CleanScreen();
	var frmPantalla = window.document.frmAdminClientes;
	codigoBean(frmPantalla.txtConvenio,true,data.cconvenio);		
	frmPantalla.txtNombreConvenio.value = data.sconvenio;
	frmPantalla.txtFechaInicio.value = data.siniciovigencia;
	frmPantalla.txtFechaFin.value = data.sterminovigencia;
    adminDIV("ExamenesCotizados","visible","inline");
 	codeDIVHTML("ExamenesCotizados",data.strDetalleExamenes);
 	valorCombo(frmPantalla.selTipoConvenio,data.ctipoconvenio);
	codigoBean(frmPantalla.txtEstadoConvenio,true,data.sestadoconvenio);		
	frmPantalla.hdnEstadoConvenio.value = data.uestadoconvenio;
	frmPantalla.txtCorreoElectronico.value = data.scorreoelectronico + " ";
	if (frmPantalla.txtCorreoElectronico.value.length > 4) {
		frmPantalla.optResultado.checked = true;
	} else {
		frmPantalla.optResultado.checked = false;
	}
	TipoConvenio(data.ctipodescuento);
	frmPantalla.idEstado.disabled=false;
	frmPantalla.idEstado.value = "Cambiar Estado"		 	
}

function LoadClienteConvenio() {
	var frmPantalla = window.document.frmAdminClientes;
	convenioBean.cconvenio = 0;
	convenioBean.sconvenio = "";
	convenioBean.ccliente = frmPantalla.txtCliente.value;
}


function LoadCompletedConvenio() {
	var frmPantalla = window.document.frmAdminClientes;
	convenioBean.cconvenio = frmPantalla.txtConvenio.value;
	convenioBean.sconvenio = frmPantalla.txtNombreConvenio.value;
	convenioBean.siniciovigencia = frmPantalla.txtFechaInicio.value;
	convenioBean.sterminovigencia = frmPantalla.txtFechaFin.value;
	convenioBean.ctipoconvenio = TypeObjeto(frmPantalla.selTipoConvenio);
	convenioBean.ccliente = frmPantalla.txtCliente.value;
	convenioBean.clistacorporativa = 1;
	convenioBean.uestadoconvenio = frmPantalla.hdnEstadoConvenio.value;	
	convenioBean.cuser = frmPantalla.idUsuario.value;
	convenioBean.scorreoelectronico = frmPantalla.txtCorreoElectronico.value;
}

function LoadSmallConvenio() {
	var frmPantalla = window.document.frmAdminClientes;
	convenioBean.cconvenio = frmPantalla.txtConvenio.value;
	convenioBean.sconvenio = frmPantalla.txtNombreConvenio.value;
}


function ConvenioBean() {
    kconvenio=null,
    cconvenio=null,
    sconvenio=null,
    siniciovigencia=null,
    sterminovigencia=null,
    ctipoconvenio=null,
    ccliente=null,
    cvigencia=null,
    clistacorporativa=null,
    strDetalleExamenes=null,
    ctipodescuento=null,
    sestadoconvenio=null,    
    uestadoconvenio=null,
    scorreoelectronico=null,
    cuser=null
}

function aceptaPago(strConvenio) {
	var frm = document.getElementById("frmAdminClientes");
	document.getElementById("txtMontoAPagarTotal").value = "0";
	for (i=0;i<frm.chkPagos.length;i++) {
		if (frm.chkPagos[i].checked) {
			frm.txtMontoAPagar[i].value = validarString(frm.txtMontoAPagar[i].value);
			if (parseFloat(frm.hdnSaldoFactura[i].value) >= parseFloat(frm.txtMontoAPagar[i].value)) { 
				document.getElementById("txtMontoAPagarTotal").value = (parseFloat(frm.txtMontoAPagar[i].value) + parseFloat(document.getElementById("txtMontoAPagarTotal").value)).toFixed(2);				
			} else {
				alert("El monto del pago no puede ser mayor al saldo de la factura");
				frm.txtMontoAPagar[i].value = parseFloat(frm.hdnSaldoFactura[i].value).toFixed(2);
			}
		}
	}
	
	
//	document.getElementById("txtMontoAPagarTotal" + strConvenio).value = "0";
//    for (i=0;i<frm.chkPagos.length;i++) {
//        if (frm.chkPagos[i].checked) {
//			frm.txtMontoAPagar[i].value = validarString(frm.txtMontoAPagar[i].value);
//			if (parseFloat(frm.hdnSaldoFactura[i].value) >= parseFloat(frm.txtMontoAPagar[i].value)) { 
//				document.getElementById("txtMontoAPagarTotal" + strConvenio).value = (parseFloat(frm.txtMontoAPagar[i].value) + parseFloat(document.getElementById("txtMontoAPagarTotal" + strConvenio).value)).toFixed(2);				
//			} else {
//				alert("El monto del pago no puede ser mayor al saldo de la factura");
//				frm.txtMontoAPagar[i].value = parseFloat(frm.hdnSaldoFactura[i].value).toFixed(2);
//			}
//        }
//    }
    changeMontoPagar(strConvenio);
}

function validarString(cadenaAnalizar) {
   var bolEquacionPrimerSimbolo = false;	
   var bolEquacionSegundoSimbolo = false;	
   var bolEquacion = false;
   var bolResta = false;
   var bolSuma = false;
   var strPrimerNumero = "";
   var strSegundoNumero = "";
   for (var i = 0; i< cadenaAnalizar.length; i++) {
         var caracter = cadenaAnalizar.charAt(i);
         caracter = trimStr(caracter);
         if( caracter == "=") {
        	 bolEquacionPrimerSimbolo = true;
        	 bolEquacion = true;
         } else if (caracter == "+") {
        	 bolEquacionPrimerSimbolo = false;
        	 bolEquacionSegundoSimbolo = true;
        	 bolSuma = true;
        	 bolResta = false;
         } else if (caracter == "-") {
        	 bolEquacionPrimerSimbolo = false;
        	 bolEquacionSegundoSimbolo = true;
        	 bolSuma = false;
        	 bolResta = true;
         } else {
        	 if (bolEquacionPrimerSimbolo) {
        		 strPrimerNumero = (strPrimerNumero + caracter);
        	 } else if (bolEquacionSegundoSimbolo) {
        		 strSegundoNumero = (strSegundoNumero + caracter);
        	 }
         }         
    }
    if (bolEquacion) {
    	if (bolSuma) {
    		return (parseFloat(strPrimerNumero) + parseFloat(strSegundoNumero)).toFixed(2);
    	} else if (bolResta) {
    		return (parseFloat(strPrimerNumero) - parseFloat(strSegundoNumero)).toFixed(2);
    	}
    } else {
    	return parseFloat(cadenaAnalizar);
    }
}


var ventana_secundaria = null;     

function showSubModalSustitucion(){
	   if(ventana_secundaria != null){
		   ventana_secundaria.close();
	   }
	   var frm = document.getElementById("frmAdminClientes");
	   var kfacturas="";
	   for (i=0;i<frm.chkPagos.length;i++) {		   
			if (frm.chkPagos[i].checked) {	
				if(kfacturas==""){
					kfacturas += frm.hdnkFacturaSaldo[i].value;
				}else{
					kfacturas += ","+frm.hdnkFacturaSaldo[i].value;					
				}
			}
		}	
	    if(kfacturas!=""){
	    	ventana_secundaria = window.open('/web2labportal/servlet/template/web2lab,sustitucion,SustitucionFactura.vm?ufoliofactura='+kfacturas+'&cmarca='+0+'&tipoFactura=4&cconvenio=0',"SustitucionFactura","width=900,height=300,menubar=no,scrollbars=yes");	    	
	    }else{
	    	alert('Tienes que seleccionar algun pago');
	    }
	   //('/web2labportal/servlet/template/web2lab,sustitucion,SustitucionFactura.vm?ufoliofactura='+ufoliofactura+'&cmarca='+selectedMarca, 950, 300, "Sustitucion");
}
         
function agregarValoresSustitucion(datafolio,datakfactura,datauuid){
	   document.getElementById("txtUfoliofacturaSustitucion").value=datafolio;
	   document.getElementById("hdenkfacturaSustitucion").value=datakfactura;
	   document.getElementById("hdenUuidSustitucion").value=datauuid;
}



function pagosFacturas() { 
	var frm = document.getElementById("frmAdminClientes");
	var pago = "";
	var saldo = "";
	var anticipo = "";
	var strfactura = "";
	var intFactura = "";
	var sFechaPago = document.getElementById("txtFechaDepositoGlobal").value;
	var idUsuario = document.getElementById("idUsuario").value; 
	var cTipoPago = TypeObjeto(document.getElementById("selTipoPago")); 
	var txtTotal = document.getElementById("txtMontoAPagarTotal").value;
	var shora = document.getElementById("selhora").value;
	var sminutos = document.getElementById("selminutos").value; 
	var ssegundos = document.getElementById("selsegundos").value; 
	var cFormaPago = document.getElementById("selFormaPago").value; 
	var checkbox =	document.getElementById("chkCrearComplento").checked;
	
	var checkboxOpcional = document.getElementById("chkAgregarOpcionales").checked;
	var txtRfcBanco = document.getElementById("txtRfcBanco").value;
	var txtNomBanco = document.getElementById("txtNombreBanco").value;
	var txtNomCuentaClabe= document.getElementById("txtNumCuentaClabe").value;
	var txtNomOperacion= document.getElementById("txtNumOperacion").value;
	
	var checkboxSustitucion = document.getElementById("chkAgregarSustitucion").checked;
	var txtfoliosustitucion = document.getElementById("txtUfoliofacturaSustitucion").value;
	var txtUuidSustitucion = document.getElementById("hdenUuidSustitucion").value;
	
	var kfacturas="";
	var sFechaPagoCompleta =sFechaPago+" "+shora+":"+sminutos+":"+ssegundos;	
	
	var sustitucion = true;
	
	var txtConfirmacionOpcionales = "";
		if(txtRfcBanco != ""){
			txtConfirmacionOpcionales += "Rfc del Banco: "+txtRfcBanco+"\n";
		}
		if(txtNomBanco != ""){
			txtConfirmacionOpcionales += "Nombre del Banco: "+txtNomBanco+"\n";
		}
		if(txtNomCuentaClabe != ""){
			txtConfirmacionOpcionales += "Numero de Cuenta/Clabe: "+txtNomCuentaClabe+"\n";
		}
		if(txtNomOperacion != ""){
			txtConfirmacionOpcionales += "Numero de Operacion: "+txtNomOperacion+"\n";
		}
		
		
		if(checkboxSustitucion){
			if(txtfoliosustitucion<1){
				sustitucion= false;
				alert("Tienes que ingresar un Folio Correcto");
			}	
		}
	
		if((sFechaPago!=null) && (sFechaPago!='') && (parseInt(cFormaPago)!=0)){
			if(sustitucion){
				for (i=0;i<frm.chkPagos.length;i++) {
					if (frm.chkPagos[i].checked) {					
						kfacturas += frm.hdnkFacturaSaldo[i].value+",";
					}
				}	
				if(kfacturas!=""){
					if (checkboxOpcional){
						if(confirm("Estas seguro de agregar los siguientes campos opcionales?\n"+txtConfirmacionOpcionales)){
							CuentasxCobrarMayoreo.getKeyPago(sFechaPagoCompleta,txtTotal,cFormaPago,1, kfacturas,
									txtRfcBanco,txtNomBanco,txtNomCuentaClabe,txtNomOperacion,getKeyPago2_CallBack);
						}
					}else{
						CuentasxCobrarMayoreo.getKeyPago(sFechaPagoCompleta,txtTotal,cFormaPago,1, kfacturas,
								"","","","",getKeyPago2_CallBack);
					} 
				}
			}
		}else{
			if(parseInt(cFormaPago)==0){
				alert("Tienes que seleccionar una Forma de Pago");
			}
			if(sFechaPago==null || sFechaPago==''){
				alert("Tienes que ingresar una fecha correcta");						
			}
		}		
	
}

var gblmjsExito=0;
var gblmjsError=0;
var cont=0;
var keyPago =0;
function pagosFacturas_CallBack(data) {
	var checkbox =	document.getElementById("chkCrearComplento").checked;
	//alert(data.smensaje);
	if(data.smensaje=="Exito en el registro del Pago"){
		gblmjsExito++;
	}else{
		gblmjsError++;
	}
	//alert(keyPago+'    '+cont+'    '+gblmjsExito+'     '+gblmjsError);
	if((gblmjsExito+gblmjsError)==cont){
		if(gblmjsError==0){	    	
			alert('Registro de pagos Exitosos.');
			if(checkbox==true){

				window.open(hostServerWebApp+"/facturas/complemento-pagos/"+keyPago, "_blank");


			}
		}else{
		  alert('Error al generar el complemento de pago');
		}		  
	} 
	 
}	

function getKeyPago2_CallBack(data){
	var keyPagocomplemento=parseInt(data);
	keyPago=keyPagocomplemento;
	var frm = document.getElementById("frmAdminClientes");
	var pago = "";
	var saldo = "";
	var anticipo = "";
	var strfactura = "";
	var intFactura = "";
	var convenio="";
	var sFechaPago = document.getElementById("txtFechaDepositoGlobal").value;
	var idUsuario = document.getElementById("idUsuario").value; 
	var cTipoPago = TypeObjeto(document.getElementById("selTipoPago")); 
	var txtTotal = document.getElementById("txtMontoAPagarTotal").value;
	var shora = document.getElementById("selhora").value;
	var sminutos = document.getElementById("selminutos").value; 
	var ssegundos = document.getElementById("selsegundos").value; 
	var cFormaPago = document.getElementById("selFormaPago").value;
	var sFechaPagoCompleta =sFechaPago+" "+shora+":"+sminutos+":"+ssegundos;
	//	var cTipoPago = TypeObjeto(frm.selTipoPago);        	
		var randomnumber = Math.floor(Math.random()*101);
	
	var arrayPagos=[];
	var cadenaPagos="";
	if (confirm("Estas seguro de generar el complemento de pago?")) {
	    for (i=0;i<frm.chkPagos.length;i++) {
	        if (frm.chkPagos[i].checked) {
	        	
	        	pago = frm.txtMontoAPagar[i].value;
	        	saldo = frm.hdnSaldoFactura[i].value;
	        	anticipo = frm.hdnAnticipoSaldo[i].value;
	        	intFactura = frm.hdnkFacturaSaldo[i].value;
	        	strfactura = frm.hdnsFacturaSaldo[i].value;
	        	convenio=frm.hdnsConvenio[i].value;
	        	cadenaPagos=intFactura+","+anticipo+","+pago+","+saldo+","+cTipoPago+","+idUsuario+","+sFechaPagoCompleta+","+randomnumber+","+keyPagocomplemento;        	
	        	arrayPagos.push(cadenaPagos);
	        	cadenaPagos="";
	        }
	    }	
	    CuentasxCobrarMayoreo.pagosFacturasArray(arrayPagos,pagosFacturasArray_CallBack);    
    }
	
}

function pagosFacturasArray_CallBack(data){
	var checkbox =	document.getElementById("chkCrearComplento").checked;
	//alert('status: '+data) 
	if(data){	    	
		alert('Registro de pagos Exitosos.');
		if(checkbox==true){


			var checkboxSustitucion = document.getElementById("chkAgregarSustitucion").checked;
			var txtfoliosustitucion = 0;
			var txtUuidSustitucion = "";
			if(checkboxSustitucion){
				txtfoliosustitucion = document.getElementById("txtUfoliofacturaSustitucion").value;
				txtUuidSustitucion = document.getElementById("hdenUuidSustitucion").value;				
			}
			
			window.open(hostServerWebApp+"/facturas/complemento-unitario-pagos?idPago="+keyPago+"&folio="+txtfoliosustitucion+"&uuid="+txtUuidSustitucion, "_blank");

		}
	}else{
	  alert('Error al generar el complemento de pago');
	}
	
}

function getKeyPago_CallBack(data){
	gblmesExito=0;
	gblmjsError=0;
	cont=0;
	keyPago =0;
	keyPago=parseInt(data);
	//alert('Key  '+keyPago);
	var frm = document.getElementById("frmAdminClientes");
	var pago = "";
	var saldo = "";
	var anticipo = "";
	var strfactura = "";
	var intFactura = "";
	var convenio="";
	var sFechaPago = document.getElementById("txtFechaDepositoGlobal").value;
	var idUsuario = document.getElementById("idUsuario").value; 
	var cTipoPago = TypeObjeto(document.getElementById("selTipoPago")); 
	var txtTotal = document.getElementById("txtMontoAPagarTotal").value;
	var shora = document.getElementById("selhora").value;
	var sminutos = document.getElementById("selminutos").value; 
	var ssegundos = document.getElementById("selsegundos").value; 
	var cFormaPago = document.getElementById("selFormaPago").value; 
	
	var sFechaPagoCompleta =sFechaPago+" "+shora+":"+sminutos+":"+ssegundos;
	//	var cTipoPago = TypeObjeto(frm.selTipoPago);        	
		var randomnumber = Math.floor(Math.random()*101);
		for (j=0;j<frm.chkPagos.length;j++) {
			if (frm.chkPagos[j].checked) {
				cont++;
			}
		}		
	 
	
	//if (confirm("Estas seguro de generar el complemento de pago?")) {
	    for (i=0;i<frm.chkPagos.length;i++) {
	        if (frm.chkPagos[i].checked) {
	        	
	        	pago = frm.txtMontoAPagar[i].value;
	        	saldo = frm.hdnSaldoFactura[i].value;
	        	anticipo = frm.hdnAnticipoSaldo[i].value;
	        	intFactura = frm.hdnkFacturaSaldo[i].value;
	        	strfactura = frm.hdnsFacturaSaldo[i].value;
	        	convenio=frm.hdnsConvenio[i].value;
				if (confirm("Estas seguro de registrar el pago por $" + pago + " para la factura " + strfactura + "?")) {
					CuentasxCobrarMayoreo.pagoFacturaMulti(intFactura,anticipo,pago,saldo,cTipoPago,idUsuario,sFechaPagoCompleta,randomnumber,keyPago,pagosFacturas_CallBack);
				}
	        }
	    }	    
	//}
}

function changeMontoPagar(strConvenio) {
	if (parseFloat(document.getElementById("txtMontoAPagarTotal" + strConvenio).value) > 0) {
		adminDIV("gridPagoGlobal" + strConvenio,"visible","inline");
		
	} else {
		adminDIV("gridPagoGlobal" + strConvenio,"hidden","none");
	}	
}

function showCamposOpcionales(){
	if (document.getElementById("chkAgregarOpcionales").checked){	
		adminDIV("divCamposOpcionales","visible","inline");
		document.getElementById("txtUfoliofactura").focus();
	}else{
		adminDIV("divCamposOpcionales","hidden","none");
		document.getElementById("chkAgregarOpcionales").checked = false;
		document.getElementById("txtRfcBanco").value="";
		document.getElementById("txtNombreBanco").value="";
		document.getElementById("txtNumCuentaClabe").value="";
	}
}

function showCamposSustitucion(){
	if (document.getElementById("chkAgregarSustitucion").checked){	
		adminDIV("divCamposSustitucion","visible","inline");
		document.getElementById("txtUfoliofacturaSustitucion").focus();
	}else{
		adminDIV("divCamposSustitucion","hidden","none");
		document.getElementById("chkAgregarSustitucion").checked = false;
		document.getElementById("txtUfoliofacturaSustitucion").value="";
		document.getElementById("txtFolioFiscal").value="";
	}
}

function showCheckSustitucion(){
	if (document.getElementById("chkCrearComplento").checked){	
		adminDIV("divCheckSustitucion","visible","inline");
		adminDIV("divCamposSustitucion","hidden","none");
		document.getElementById("txtUfoliofacturaSustitucion").value="";
		document.getElementById("txtFolioFiscal").value="";
		document.getElementById("chkAgregarSustitucion").checked = false;
	}else{
		adminDIV("divCheckSustitucion","hidden","none");
		adminDIV("divCamposSustitucion","hidden","none");
		document.getElementById("txtUfoliofacturaSustitucion").value="";
		document.getElementById("txtFolioFiscal").value="";
		document.getElementById("chkAgregarSustitucion").checked = false;
	}
}

/****************Versi&oacute;n 25 de Marzo 2013 BY*******************/
function buscarConvenioRapido(objConvenio,strBuscar) {
    var frmPantalla = window.document.frmAdminClientes;		
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

function displayCargaDatos(){
	   var frmPantalla = window.document.frmAdminClientes;
	   var cconvenio = TypeObjeto(frmPantalla.selConvenios);
	   
	   frmPantalla.hdnCconvenio.value=cconvenio;
	   
	   CuentasxCobrarMayoreo.getCcliente(cconvenio,buscaXconvenio_CallBack);        
	}

function buscaXconvenio_CallBack(data){
	LoadBusquedaClienteConvenio(data);
	DatosCliente.buscarClientes(clienteBean,consultaClienteGrid_CallBack);
}

function LoadBusquedaClienteConvenio(data) {
	var frmPantalla = window.document.frmAdminClientes;
	clienteBean.ccliente=data;
	clienteBean.srazonsocial=frmPantalla.txtRazonSocial.value;
	clienteBean.srfc=frmPantalla.txtRFC.value;
	clienteBean.smnemonico=frmPantalla.txtMNEMONICO.value;
	clienteBean.ctipocliente=69;
	clienteBean.breadonly = false;
	clienteBean.smarcauser = frmPantalla.marcasUser.value;
} 

/************************************** TERMINA FUNCIONES DEL CONVENIO *******************************************************************/
