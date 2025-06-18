function showSubModalPago() {
	var frmPantalla = window.document.frmAdminClientes;
	showPopWin(frmPantalla.hdenRutaPago.value, 800, 400, "Captura Orden");
}

function MetricasClieConBean() {
	intClientesActivos=null,
	intClientesNOActivos=null, 
	intConveniosActivos=null,
	intConveniosNOActivos=null,
	intClientesSinConvenio=null,
	intClientesSinConvenioActivos=null
} 

function init() 
{
	var frmPantalla = window.document.frmAdminClientes;
	var userid = frmPantalla.idUsuario.value;
	var marca = frmPantalla.idMarca.value;
    //DWRUtil.useLoadingMessage();
    disableDIV(); 
    adminDIV("gridbusquedaConvenios","hidden","none");	    	
 	codeDIVHTML("gridbusquedaConvenios","");
    adminDIV("gridbusquedaFacturas","hidden","none");	    	
 	codeDIVHTML("gridbusquedaFacturas","");
	codigoBean(frmPantalla.txtEstadoCliente,true,"");			
	codigoBean(frmPantalla.txtConvenio,true,0);		
	DatosCliente.estatusAltas(userid,estatusAltas_CallBack);
	frmPantalla.idEstado.value = ""		
	frmPantalla.idEstado.disabled=true;
	frmPantalla.txtCorreoElectronico.value = "";
	frmPantalla.optResultado.checked = false;
	
}


function initOperaciones() 
{
	
	
}

function cargaMasiva() {
	 var url = "http://10.20.26.6:9012/gda/administracion-clientes-convenios/home";
	 window.open(url, "_blank");  
}

function operacionConvenios(){
	 var frmPantalla = window.document.frmOpConvenios;
	 var idUser = document.getElementById("idUsuario").value;	 
	 DatosCliente.getMarcasUser(idUser,getMarcasUser_CallBack);	  
} 

function getMarcasUser_CallBack(data) {
	 var frmPantalla = window.document.frmOpConvenios;
	 var idUser = document.getElementById("idUsuario").value;
//	 var url = "http://10.20.26.6:8021/webInfodiamex/homeGdaInit/"+idUser+"/"+data;
//	 var url = "http://10.20.20.12:8021/webInfodiamex/homeGda/"+idUser;
	 var url = "http://10.20.26.6:8021/webInfodiamex/homeGda/"+idUser;
	 window.open(url, "_blank");  
}






function showEstadistica(uValor) {
	DatosCliente.showEstadistica(uValor,showEstadistica_CallBack);	
}

function showEstadistica_CallBack(data) {
	disableDIV();
    adminDIV("showControles","visible","inline");
 	codeDIVHTML("showControles",data);	
}

function estatusAltas_CallBack(data) {
	var frmPantalla = window.document.frmAdminClientes;
	codigoBean(frmPantalla.txtClienteActivos,true,data.intClientesActivos);		
	codigoBean(frmPantalla.txtClienteNOActivos,true,data.intClientesNOActivos);		
	codigoBean(frmPantalla.txtConveniosActivos,true,data.intConveniosActivos);		
	codigoBean(frmPantalla.txtConveniosNOActivos,true,data.intConveniosNOActivos);			
	codigoBean(frmPantalla.txtClienteSINConvenios,true,data.intClientesSinConvenio);			
	codigoBean(frmPantalla.txtClienteSINConveniosActivos,true,data.intClientesSinConvenioActivos);		
	
	var frmPantalla = window.document.frmAdminClientes;
	
	frmPantalla.marcasUser.value = data.marcasUser;
	var lstcmarca = data.lstCmarca;
	var lstsmarca = data.lstSmarca;
	var selector = document.getElementById("selMarca");
	 for(var i=0;i<lstcmarca.length;i++){		 
		 selector.options[i] = new Option(lstsmarca[i], lstcmarca[i]);
	 }
	
}

function disableDIV() {
    adminDIV("showControles","hidden","none");
    adminDIV("gridPorDepto","hidden","none");
    adminDIV("gridPorExamen","hidden","none");
    adminDIV("gridConvenios","hidden","none");
    adminDIV("gridbusquedaClientes","hidden","none");
    adminDIV("gridbusquedaDireccion","hidden","none");	    
    adminDIV("ExamenesCotizados","hidden","none");
    adminDIV("gridprogressbar","hidden","none");
 	codeDIVHTML("showControles","");
 	codeDIVHTML("gridbusquedaDireccion","");
 	codeDIVHTML("gridbusquedaClientes","");
 	codeDIVHTML("ExamenesCotizados",""); 	
}


function noShowDIV() {
    adminDIV("showControles","hidden","none");
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
    adminDIV("showControles","visible","inline");
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
	frmPantalla.selRegimenFiscal.selectedIndex = 0;
	frmPantalla.selUsoCfdi.selectedIndex = 0;
	frmPantalla.selDias.selectedIndex = 0;
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


function TipoConvenio(tipoConvenio)
{
	var frmPantalla = window.document.frmAdminClientes;
	if (document.getElementById("txtConvenio").value != "0") {
		if (tipoConvenio == 0) {
		    adminDIV("gridPorDepto","visible","inline");	    			
		    adminDIV("gridPorExamen","hidden","none");			
		    frmPantalla.radTipoConv[0].checked = true; 	    
		    frmPantalla.radTipoConv[0].disabled = true;
		    frmPantalla.radTipoConv[1].disabled = true;
		} else {
		    adminDIV("gridPorDepto","hidden","none");
		    adminDIV("gridPorExamen","visible","inline");	    			
		    frmPantalla.radTipoConv[1].checked = true; 	    
		    frmPantalla.radTipoConv[0].disabled = true;
		    frmPantalla.radTipoConv[1].disabled = true;
		}
	} else {
		alert("Debe primero seleccionar un convenio o guardar el convenio");
	}
}

function VerificaModificacion_KeyPress() 
{
    if (window.event && window.event.keyCode == 13) {
	 	if (validaFullCliente()) {
			actualizaCliente();
		}
	}
}

function VerificaModificacion() 
{
 	if (validaFullCliente()) {
 		actualizaCliente();
	}
} 

function actualizaCliente() {	 
	habilitarSpinner();	
	disableDIV();
	LoadCompletedCliente();
	DatosCliente.actualizaCliente(clienteBean,actualizaCliente_CallBack); 
}	
		
function actualizaCliente_CallBack(data)
{
	deshabilitarSpinner();
	alert("Usuario creado/modificado exitosamente");
	var frmPantalla = window.document.frmAdminClientes;
	codigoBean(frmPantalla.txtCliente,true,data.ccliente);		
	CleanScreen();
    adminDIV("gridConvenios","visible","inline");
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

function pagoFactura(intFactura) {
	pago = prompt("Cuanto es el monto del Pago?", "0");
	if (pago != null) {
		if (pago > 0) {
			if (confirm("Estas seguro de registrar el pago por $" + pago + " para la factura " + intFactura + "?")) {
				DatosCliente.pagoFactura(intFactura,pago,pagoFactura_CallBack);			
			}
		}
	}
}

function pagoFactura_CallBack(data) {
	alert(data);
	var frmPantalla = window.document.frmAdminClientes;
	clienteAceptado(frmPantalla.txtCliente.value);
} 

function clienteAceptado(intCliente) {
	var frmPantalla = window.document.frmAdminClientes;
	frmPantalla.txtCliente.value = intCliente;	 
	LoadBusquedaCliente();
	disableDIV();
	DatosCliente.buscarCliente(clienteBean,clienteAceptado_CallBack);	
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
	
	frmPantalla.txtNombreComercial[1].value = data.snombreejecutivocomercial;
	frmPantalla.txtDepartamentoComercial.value = data.sdeptoejecomer;
	frmPantalla.txtDireccionComercial.value = data.sdirejecomer;
	frmPantalla.txtEmailComercial.value = data.scorreoejecutivocomercial;
	frmPantalla.txtTelefonoComercial.value = data.stelejecomer;

	frmPantalla.txtNombreRevision.value = data.snombrecontacto;
	frmPantalla.txtDepartamentoRevision.value = data.sdeptocontacto;
	frmPantalla.txtDireccionRevision.value = data.sdircontacto;
	frmPantalla.txtDiasHoraRevision.value = data.sdiashrscontacto;
	frmPantalla.txtEmailRevision.value = data.scorreocontacto;
	frmPantalla.txtTelefonoRevision.value = data.stelefonocontacto;
	frmPantalla.txtFechaCierreRevision.value = data.dfeccierrecontacto;

	frmPantalla.txtNombreCobranza.value = data.sejecutivocobranza;
	frmPantalla.txtEmailCobranza.value = data.scorreoejecutivocobranza;
	frmPantalla.txtDepartamentoCobranza.value = data.sdeptoejecob;
	frmPantalla.txtDireccionCobranza.value = data.sdirejecob;
	frmPantalla.txtTelefonoCobranza.value = data.stelejecob;
	

	valorCombo(document.getElementById('selTipoCliente'),data.ctipocliente);
	
	frmPantalla.selRegimenFiscal.selectedIndex = data.cregimenfiscal;
	
	valorCombo(document.getElementById('selUsoCfdi'),data.cusocfdi);
	
	valorCombo(document.getElementById('selDias'),data.udiascredito);
		
	valorCombo(document.getElementById('selGiro'),data.cgirocliente);	
	compareSelectvalue(frmPantalla.selZonaAsignada,data.czonaventa);		
	compareSelectvalue(frmPantalla.selMarca,data.cmarca);			
	codigoBean(frmPantalla.txtEstadoCliente,true,data.sestadoregistro);		
    adminDIV("gridbusquedaConvenios","visible","inline");
 	codeDIVHTML("gridbusquedaConvenios",data.strConvenioGrid); 	
    adminDIV("gridbusquedaFacturas","visible","inline");
 	codeDIVHTML("gridbusquedaFacturas",data.strFacturasGrid); 	
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

function ocultarFacturas() {
	var objDIV = document.getElementById("gridGridFacturasConvenios");
	if ((objDIV.style.visibility == "hidden") && (objDIV.style.display == "none")) {
	    adminDIV("gridGridFacturasConvenios","visible","inline");			
	} else {
	    adminDIV("gridGridFacturasConvenios","hidden","none");			
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
	clienteBean.cmarca=frmPantalla.selMarca[frmPantalla.selMarca.selectedIndex].value;	
	clienteBean.czonaventa=frmPantalla.selZonaAsignada[frmPantalla.selZonaAsignada.selectedIndex].value;
	clienteBean.cregimenfiscal=frmPantalla.selRegimenFiscal[frmPantalla.selRegimenFiscal.selectedIndex].value;
	clienteBean.cusocfdi=frmPantalla.selUsoCfdi[frmPantalla.selUsoCfdi.selectedIndex].value;
	clienteBean.udiascredito=frmPantalla.selDias[frmPantalla.selDias.selectedIndex].value;
	
	
	clienteBean.snombreejecutivocomercial = frmPantalla.txtNombreComercial[1].value;	 
	clienteBean.sdeptoejecomer = frmPantalla.txtDepartamentoComercial.value;
	clienteBean.sdirejecomer = frmPantalla.txtDireccionComercial.value;
	clienteBean.scorreoejecutivocomercial = frmPantalla.txtEmailComercial.value
	clienteBean.stelejecomer = frmPantalla.txtTelefonoComercial.value;

	clienteBean.snombrecontacto = frmPantalla.txtNombreRevision.value;
	clienteBean.sdeptocontacto = frmPantalla.txtDepartamentoRevision.value;
	clienteBean.sdircontacto = frmPantalla.txtDireccionRevision.value;
	clienteBean.sdiashrscontacto = frmPantalla.txtDiasHoraRevision.value;
	clienteBean.scorreocontacto = frmPantalla.txtEmailRevision.value;
	clienteBean.stelefonocontacto = frmPantalla.txtTelefonoRevision.value;
	clienteBean.dfeccierrecontacto = frmPantalla.txtFechaCierreRevision.value;

	clienteBean.sejecutivocobranza =  frmPantalla.txtNombreCobranza.value;
	clienteBean.scorreoejecutivocobranza =  frmPantalla.txtEmailCobranza.value;
	clienteBean.sdeptoejecob = frmPantalla.txtDepartamentoCobranza.value;
	clienteBean.sdirejecob = frmPantalla.txtDireccionCobranza.value;
	clienteBean.stelejecob = frmPantalla.txtTelefonoCobranza.value;
}

function LoadBusquedaCliente() {
	var frmPantalla = window.document.frmAdminClientes;
	clienteBean.ccliente=frmPantalla.txtCliente.value;
	clienteBean.srazonsocial=frmPantalla.txtRazonSocial.value;
	clienteBean.srfc=frmPantalla.txtRFC.value;
	clienteBean.smnemonico=frmPantalla.txtMNEMONICO.value;
	clienteBean.smarcauser = frmPantalla.marcasUser.value;
	
}

function validaFullCliente() {
    var frmPantalla = window.document.frmAdminClientes;        
    txtRazonSocial = frmPantalla.txtRazonSocial;
    txtMNEMONICO = frmPantalla.txtMNEMONICO;
    txtRFC = frmPantalla.txtRFC;
	txtCalle = frmPantalla.txtCalle;
	txtColonia = frmPantalla.txtColonia;
	txtDelegacionMunicipio = frmPantalla.txtDelegacionMunicipio;
	txtCodigoPostal = frmPantalla.txtCodigoPostal;
	selTipoPersona =  TypeObjeto(frmPantalla.selTipoPersona);	
	selTipoCliente =  TypeObjeto(frmPantalla.selTipoCliente);
	selGiro =  TypeObjeto(frmPantalla.selGiro);
	selZonaAsignada =  TypeObjeto(frmPantalla.selZonaAsignada);	
	cregimefiscal =  TypeObjeto(frmPantalla.selRegimenFiscal);
	cusocfdi =  TypeObjeto(frmPantalla.selUsoCfdi);
	
    if( !validaVacios(txtRazonSocial.value) ) {
    	alert('Existe un error en la Razon Social');
    	return false;
    }
    else if( !validaTextosNuevos(txtRazonSocial.value) ) {
    	alert('Existe un error en la Razon Social');
    	return false;
    }
    else if( !validaVacios(txtRFC.value) ) {
    	alert('Existe un error en el RFC');
    	return false;	    
    }
    else if( !validaTextosNuevos(txtMNEMONICO.value) ) {
    	alert('Existe un error en el MNemonico');
    	return false;
    }	    
    else if( !validaVacios(txtMNEMONICO.value) ) {
    	alert('Existe un error en el MNemonico');
    	return false;	    
    }
    else if( !validaVacios(txtCalle.value) ) {
    	alert('Existe un error en la Calle');
    	return false;	    
    }
    else if( !validaVacios(txtColonia.value) ) {
    	alert('Existe un error en la Colonia');
    	return false;	    
    }
    else if( !validaVacios(txtDelegacionMunicipio.value) ) {
    	alert('Existe un error en la Delegacion o Municipio');
    	return false;	    
    }
    else if( !validaVacios(txtCodigoPostal.value) ) {
    	alert('Existe un error en la Codigo Postal');
    	return false;	    
    }
    else if (selTipoPersona == 0) {
    	alert('Existe un error en el Tipo de Persona');
    	return false;	        	
    }
    else if (selTipoCliente == 0) {
    	alert('Existe un error en el Tipo de Cliente');
    	return false;	        	
    }
    else if (selGiro == 0) {
    	alert('Existe un error en el Giro');
    	return false;	        	
    }
    else if (cregimefiscal == 0) {
    	alert('Existe un error en el Regimen Fiscal');
    	return false;	        	
    }
    else if (cusocfdi == 0) {
    	alert('Existe un error en el Uso Cfdi');
    	return false;	        	
    }
	return true;	
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
	cmarca=null,
	czonaventa=null,
	smarca=null
	smarcauser = null;
}

/************************************** TERMINA FUNCIONES DEL CLIENTE *******************************************************************/
/************************************** INICIA FUNCIONES DEL CONVENIO *******************************************************************/

var convenioBean = new ConvenioBean();
var convenioBeanActualizacion = new ConvenioBean();

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

function crearNuevoConvenio() {
    CleanScreen();
    adminDIV("gridGridClienteConvenios","hidden","none");			
}


function convenioModificacion() 
{
 	if (validaFullConvenio()) {
 		actualizaConvenio();
	}
}

function convenioModificacion_KeyPress() 
{
    if (window.event && window.event.keyCode == 13) {
	 	if (validaFullConvenio()) {
			actualizaConvenio();
		}
	}
}


function imprimirReporte(liga, nombre) 
{
	url = liga+'?eventSubmit_doReporteconvenio=action';	
	abrirVentana(url,nombre);
	return  true;
}

function actualizaConvenio() {			
	if (LoadCompletedConvenio()) {
		DatosCliente.actualizaConvenio(convenioBeanActualizacion,actualizaConvenio_CallBack);
	}
}	

function actualizaConvenio_CallBack(data) {
    loadPantallaConvenio(data);	
    LoadClienteConvenio();
	DatosCliente.buscarConvenios(convenioBean,buscarConvenios_CallBack);
    adminDIV("gridGridClienteConvenios","hidden","none");			
}

function buscarConvenios_CallBack(data) {
	var frmPantalla = window.document.frmAdminClientes;
	adminDIV("gridbusquedaConvenios","visible","inline");
	codeDIVHTML("gridbusquedaConvenios",data);
	convenioAceptado(frmPantalla.txtConvenio.value)
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
	
	if(data.montoCopago == null){
		frmPantalla.txtPorcentajeMonto.value = '';
	}else{
		frmPantalla.txtPorcentajeMonto.value = data.montoCopago;
	}
	valorCombo(frmPantalla.selMontoCopago,data.tipocopago);
	
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
    var frmPantalla = document.getElementById('frmAdminClientes');    
    if( !validaVacios(frmPantalla.txtFechaInicio.value)) {
    	alert("La fecha de inicio es un dato obligatorio.");
    	frmPantalla.txtFechaInicio.focus();
    	return false;
    } else if( !validaVacios(frmPantalla.txtFechaFin.value) ) {
    	alert("La fecha final es un dato obligatorio.");
    	frmPantalla.txtFechaInicio.focus();
    	return false;
    } else if( !validaVacios(frmPantalla.txtNombreConvenio.value) ) {
    	alert("El nombre del convenio es un dato oblgatorio");
    	frmPantalla.txtNombreConvenio.focus();
    	return false;
    } else if(TypeObjeto(frmPantalla.selTipoConvenio) < 20 ) {
    	alert("Debe indicar de que tipo es el convenio");
    	return false;
    } else {
    	convenioBeanActualizacion.cconvenio = frmPantalla.txtConvenio.value;
    	convenioBeanActualizacion.sconvenio = frmPantalla.txtNombreConvenio.value;	
        convenioBeanActualizacion.siniciovigencia = frmPantalla.txtFechaInicio.value;
        convenioBeanActualizacion.sterminovigencia = frmPantalla.txtFechaFin.value;
        convenioBeanActualizacion.ctipoconvenio = TypeObjeto(frmPantalla.selTipoConvenio);
        convenioBeanActualizacion.ccliente = frmPantalla.txtCliente.value;
        convenioBeanActualizacion.clistacorporativa = 1;
        convenioBeanActualizacion.uestadoconvenio = frmPantalla.hdnEstadoConvenio.value;	
        convenioBeanActualizacion.cuser = frmPantalla.idUsuario.value;
        convenioBeanActualizacion.scorreoelectronico = frmPantalla.txtCorreoElectronico.value;
        convenioBeanActualizacion.cmarca=frmPantalla.selMarca[frmPantalla.selMarca.selectedIndex].value;	
        convenioBeanActualizacion.tipocopago=TypeObjeto(frmPantalla.selMontoCopago);
        convenioBeanActualizacion.montoCopago=frmPantalla.txtPorcentajeMonto.value;
        
    	return true;
    }   
}

function LoadSmallConvenio() {
	var frmPantalla = window.document.frmAdminClientes;
	convenioBean.cconvenio = frmPantalla.txtConvenio.value;
	convenioBean.sconvenio = frmPantalla.txtNombreConvenio.value;
}

function validaFullConvenio() {
    var frmPantalla = window.document.frmAdminClientes;
        
    txtConvenio = frmPantalla.txtConvenio;
    txtNombreConvenio = frmPantalla.txtNombreConvenio;
    selTipoConvenio = TypeObjeto(frmPantalla.selTipoConvenio);
    txtFechaInicio = frmPantalla.txtFechaInicio;
    txtFechaFin = frmPantalla.txtFechaFin;
    radTipoConv = frmPantalla.radTipoConv;
	txtCliente = frmPantalla.txtCliente.value;
    if( !validaVacios(txtFechaInicio.value)) {
    	alert("La fecha de inicio es un dato obligatorio.");
    	frmPantalla.txtFechaInicio.focus();
    	return false;
    } else if( !validaVacios(txtFechaFin.value) ) {
    	alert("La fecha final es un dato obligatorio.");
    	frmPantalla.txtFechaInicio.focus();
    	return false;
    } else if( !validaVacios(txtNombreConvenio.value) ) {
    	alert("El nombre del convenio es un dato oblgatorio");
    	frmPantalla.txtNombreConvenio.focus();
    	return false;
    } else if(selTipoConvenio < 20 ) {
    	alert("Debe indicar de que tipo es el convenio");
    	return false;
    } else if( !validaVacios(txtConvenio.value) ) {
    	return false;
    } else if (txtCliente == 0) {
    	return false;	        	
    }
	return true;	
}	 	

function newExamen() {		
	var key=window.event.keyCode;				
	if(key==13){
	    var frmPantalla = window.document.frmAdminClientes;
		if (frmPantalla.txtExamenesACotizar.value != "") {
			var strProductos = frmPantalla.txtExamenesACotizar.value;	
			frmPantalla.txtExamenesACotizar.value = "";		
			LoadSmallConvenio();		
			DatosCliente.buscarConvenioExamen(convenioBean,strProductos,1,buscarConvenio_CallBack);		
		}
	}
}	

function newNombreExamen() {		
	var key=window.event.keyCode;				
	if(key==13){
	    var frmPantalla = window.document.frmAdminClientes;
		if (frmPantalla.txtNombreExamenesACotizar.value != "") {
			var strProductos = frmPantalla.txtNombreExamenesACotizar.value;	
			frmPantalla.txtNombreExamenesACotizar.value = "";		
			LoadSmallConvenio();		
			DatosCliente.buscarConvenioExamen(convenioBean,strProductos,2,buscarConvenio_CallBack);		
		}
	}
}	


function newExamenAdministrador() {		
	var key=window.event.keyCode;				
	if(key==13){
	    var frmPantalla = window.document.frmAdminClientes;
		if (frmPantalla.txtExamenesACotizar.value != "") {
			var strProductos = frmPantalla.txtExamenesACotizar.value;	
			frmPantalla.txtExamenesACotizar.value = "";		
			LoadSmallConvenio();		
			DatosCliente.buscarConvenioExamenAdministrador(convenioBean,strProductos,buscarConvenio_CallBack);		
		}
	}
}	


function changeEstadoConvenio() {
    var frmPantalla = window.document.frmAdminClientes;
	if (frmPantalla.hdnEstadoConvenio.value == 22) {	
		/********ACTIVO**********/
		if (confirm("Quieres desactivar el convenio?")) {
			frmPantalla.hdnEstadoConvenio.value = 23;
			codigoBean(frmPantalla.txtEstadoConvenio,true,"INACTIVO");		
			convenioModificacion();
		}
	} else if (frmPantalla.hdnEstadoConvenio.value == 23) {
		/********INACTIVO**********/
		if (confirm("Quieres activar el convenio?")) {
			frmPantalla.hdnEstadoConvenio.value = 22;
			codigoBean(frmPantalla.txtEstadoConvenio,true,"ACTIVO");		
			convenioModificacion();
		}			
	} 
}

function AltaPorcentaje() {
    var frmPantalla = window.document.frmAdminClientes;
	if ((frmPantalla.txtPorcentaje.value != "") && (frmPantalla.txtConvenio.value > 0)) {	
		if (confirm("Quieres dar de alta para el convenio " + frmPantalla.txtConvenio.value + " el " + frmPantalla.txtPorcentaje.value + "% a la Clasificacion " + frmPantalla.selTipoComercial[frmPantalla.selTipoComercial.selectedIndex].text + "?")) {
			DatosCliente.altaPorcentajeClasificacionConvenio(frmPantalla.txtConvenio.value,frmPantalla.selTipoComercial[frmPantalla.selTipoComercial.selectedIndex].value,frmPantalla.txtPorcentaje.value,AltaPorcentaje_CallBack);					
		}		
	}	
}

function altaExamen(examen) {
    var frmPantalla = window.document.frmAdminClientes;
	var pMontoFacturarNew = parseFloat(prompt('¿Cual es el $ facturar sin IVA?',0));
	if (confirm("Quieres dar de alta para el convenio " + frmPantalla.txtConvenio.value + " el $ " + pMontoFacturarNew + " para el examen " + examen + "?")) {
		DatosCliente.altaPorcentajeExamenConvenio(frmPantalla.txtConvenio.value,examen,pMontoFacturarNew,AltaPorcentaje_CallBack);					
	}		
}

function actualizarExamen(kConvenioDetalle,examen) {
	if (kConvenioDetalle == 0 ) {
		alert('El Convenio no es del tipo por Clasificacion Comercial');
	} else {
	    var frmPantalla = window.document.frmAdminClientes;
		var pMontoFacturarNew = parseFloat(prompt('¿Cual es el $ facturar sin IVA?',0));
		if (confirm("Quieres actualizar para el convenio " + frmPantalla.txtConvenio.value + " el $ " + pMontoFacturarNew + " para el examen " + examen + "?")) {
			DatosCliente.actualizarPorcentajeExamenConvenio(kConvenioDetalle,frmPantalla.txtConvenio.value,examen,pMontoFacturarNew,AltaPorcentaje_CallBack);					
		    adminDIV("gridGridClienteConvenios","hidden","none");			
		}		
	}
}


function eliminarExamenConvenio(kConvenioDetalle,examen,pMontoFacturarNew) {
    var frmPantalla = window.document.frmAdminClientes;
	if (confirm("Quieres eliminar para el convenio " + frmPantalla.txtConvenio.value + " el examen " + examen + "?")) {
		DatosCliente.eliminarExamenConvenio(kConvenioDetalle,frmPantalla.txtConvenio.value,examen,pMontoFacturarNew,AltaPorcentaje_CallBack);					
	}		
}


function actualizarClasificacion(kClasificacion,uClasificacion) {
    var frmPantalla = window.document.frmAdminClientes;
	var pDescuentoNew = parseFloat(prompt('¿Cual es el nuevo % de Descuento?',0));
	if (pDescuentoNew > -1 && pDescuentoNew < 100) {		
		if (confirm("Quieres actualizar el convenio " + frmPantalla.txtConvenio.value + " el " + pDescuentoNew + "% a la Clasificacion " + uClasificacion + "?")) {
			DatosCliente.actualizarPorcentajeClasificacionConvenio(kClasificacion,frmPantalla.txtConvenio.value,uClasificacion,pDescuentoNew,AltaPorcentaje_CallBack);					
		}		
	}
}


function AltaPorcentaje_CallBack(data) {
	alert(data);
	convenioAceptado(window.document.frmAdminClientes.txtConvenio.value);
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
    cuser=null,
    cmarca=null
}


function onCorreoElectronico(opClick) {
    var frmPantalla = window.document.frmAdminClientes;
	if ((opClick.name == "optResultado") && (opClick.checked == false) && (frmPantalla.txtCorreoElectronico.value.length > 3)){
	    document.getElementById('idTextCorreoElectronico').innerHTML = "NO ENVIAR NOTIFICACIONES";
	    document.getElementById('idTextCorreoElectronico').style.color  = "red";
	    opClick.disabled = true;
	    frmPantalla.txtCorreoElectronico.disabled = true;
		LoadCompletedConvenio();
		DatosCliente.actualizaConvenioPasswordECE(convenioBeanActualizacion,actualizaConvenioPasswordECE_CallBack);
	} else 	if ((opClick.name == "optResultado") && (opClick.checked == true) && (frmPantalla.txtCorreoElectronico.value.length > 3)){
	    document.getElementById('idTextCorreoElectronico').innerHTML = "MODIFICAR CORREO";
	    document.getElementById('idTextCorreoElectronico').style.color  = "blue";		    
	    frmPantalla.txtCorreoElectronico.disabled = true;
	    opClick.disabled = true;
		LoadCompletedConvenio();
		DatosCliente.actualizaConvenioPasswordECE(convenioBeanActualizacion,actualizaConvenioPasswordECE_CallBack);
	} else {
	    frmPantalla.txtCorreoElectronico.disabled = false;
	    opClick.disabled = false;
		opClick.checked = false;
		frmPantalla.txtCorreoElectronico.value = "";
		document.getElementById('txtCorreoElectronico').focus();
	}
}

function actualizaConvenioPasswordECE_CallBack(data) {
    var frmPantalla = window.document.frmAdminClientes;
	alert(data);
    frmPantalla.txtCorreoElectronico.disabled = false;
    frmPantalla.optResultado.disabled = false;
}

function iscorrectEmail() {
    var frmPantalla = window.document.frmAdminClientes;
	if (!isMail(frmPantalla.txtCorreoElectronico.value)) {
		frmPantalla.optResultado.checked = false;
		frmPantalla.txtCorreoElectronico.value = "";
		return false;
	}
	return true;
} 



/************************************** TERMINA FUNCIONES DEL CONVENIO *******************************************************************/
