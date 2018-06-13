var pagopacienteBean = new PagoPacienteBean();

function cambiaTipoPago() {
	frmPantalla = window.document.frmDatosOrdenFundacion;
	var intTipoPago = (TypeObjeto(frmPantalla.selTipoPago) * 1);
	if (intTipoPago == 33) {
		adminDIV("txtUltimosDigitos","hidden","none");		    
		adminDIV("idDigitos","hidden","none");		    
		frmPantalla.txtUltimosDigitos.disabled = true;
		var url = "http://173.203.12.185:8081/PuntoVentaTAE/jsp/tae/safetypay/integrar/CreateExpressToken.jsp?" +
		"referencia="+ frmPantalla.txtOrden.value + 
		"&uTelefono=" + frmPantalla.txtAdmision.value +
		"&sMonto=" + frmPantalla.txtAdeudo.value;
    	snombre = "PagoSafetyPay";
    	abrirVentana(url,snombre);
		
	} else if (intTipoPago != 1) {
		    adminDIV("txtUltimosDigitos","visible","inline");
		    adminDIV("idDigitos","visible","inline");
			frmPantalla.txtUltimosDigitos.disabled = false;
	} else {
		adminDIV("txtUltimosDigitos","hidden","none");		    
		adminDIV("idDigitos","hidden","none");		    
		frmPantalla.txtUltimosDigitos.disabled = true;
	}
}

function validaPago()
{
	frmPantalla = window.document.frmDatosOrdenFundacion;
	txtAdeudo = frmPantalla.txtAdeudo.value;
	txtPaga = frmPantalla.txtPago.value;
	var intAdeudo = (txtAdeudo * 1); 	 			
	var intPaga   = (txtPaga * 1);	
	var porPago = (((txtPaga * 1)/txtAdeudo)*100);
	var intTipoPago = (TypeObjeto(frmPantalla.selTipoPago) * 1);
	if ((intTipoPago != 1) && (frmPantalla.txtUltimosDigitos.value.length < 4)) {
		alert(corrigeAcentos("Capture los ultimos 4 digitos de la tarjeta del paciente"));
		frmPantalla.txtUltimosDigitos.value="";
     	frmPantalla.txtUltimosDigitos.focus();
		return false;
	}
    if(!validaSoloNumeros(txtPaga))
    {
		alert(corrigeAcentos("Solo se permite ingresar números."));
		frmPantalla.txtPago.value="";
		return false;
	} 		
    if(porPago < 29)
    {
     	alert("El pago no puede ser menor al 30%, El % de Pago es del " + Math.floor(porPago) + "%");
     	frmPantalla.txtPago.value="";
     	frmPantalla.txtPago.focus();
     	return false;
    }
    if(intPaga > intAdeudo)
    {
     	alert("El pago es mayor que el adeudo, favor de verificar, Adeuda " + intAdeudo + " Paga " + intPaga);
     	frmPantalla.txtPago.value="";
     	frmPantalla.txtPago.focus();
     	return false;
    }
    if(0 >= intPaga)
    {
     	alert("El pago debe mayor que $0.0 ");
     	frmPantalla.txtPago.value="";
     	frmPantalla.txtPago.focus();
     	return false;
    }
    return true;
}
	
function VerificaPago()
{
	if (validaPago()) {
		registraPago();
	}
} 

function registraPago() {		
   window.document.frmDatosOrdenFundacion.idGuardarPago.disabled=true;	
   LoadPagoPacienteBean();
   DatosOrden.setPago(pagopacienteBean,registraPago_CallBack);
}	
	
function registraPago_CallBack(data)
{
   var frmPantalla = window.document.frmDatosOrdenFundacion;
	txtAdmision   = frmPantalla.txtAdmision.value;
	txtOrden = frmPantalla.txtOrden.value;
	frmPantalla.txtAdeudo.value = data.msaldo;
	frmPantalla.txtAcuenta.value = (data.manticipo + data.mpagopacienteparcial);
	document.getElementById('txtPago').className = 'textflat';
	frmPantalla.chkEntregaResultados.checked = 0;	                
	window.document.frmDatosOrdenFundacion.idGuardarPago.disabled=false;	
	if (data.madeuda == 0) {
		frmPantalla.chkEntregaResultados.disabled = false;	
	} else {
		frmPantalla.chkEntregaResultados.disabled = true;	
	}
	if (data.madeuda <= 0) {
	    adminDIV("RegistraUsuario","hidden","none");	
	}			
	alert("Se registro el pago de $" + frmPantalla.txtPago.value + " correspondiente a la factura " + txtOrden);
	frmPantalla.txtPago.value = "0";
	frmPantalla.txtUltimosDigitos.value = "";
	document.getElementById('txtPago').className = 'text';
}	 

function LoadPagoPacienteBean() {
	frmPantalla = window.document.frmDatosOrdenFundacion;
	pagopacienteBean.kpagopaciente = 0;
	pagopacienteBean.mpagopacientetotal = frmPantalla.txtTotalPagar.value;
	pagopacienteBean.manticipo = frmPantalla.txtAcuenta.value;
	pagopacienteBean.mpagopacienteparcial = frmPantalla.txtPago.value;
	pagopacienteBean.mdevolucionpaciente = 0;
	pagopacienteBean.msaldo = frmPantalla.txtAdeudo.value;
	pagopacienteBean.userid = frmPantalla.idUsuarioPago.value;
//	pagopacienteBean.dregistro = null,
	pagopacienteBean.ctipopago = TypeObjeto(frmPantalla.selTipoPago);
//	pagopacienteBean.cestadoregistro = null,
	pagopacienteBean.tordensucursal = frmPantalla.txtAdmision.value;
	pagopacienteBean.sdigitos = frmPantalla.txtUltimosDigitos.value;	
}

function PagoPacienteBean() {	
	kpagopaciente = null,
	mpagopacientetotal = null,
	manticipo = null,
	mpagopacienteparcial = null,
	mdevolucionpaciente = null,
	msaldo = null,
	userid = null,
	dregistro = null,
	ctipopago = null,
	cestadoregistro = null,
	tordensucursal = null,
	tcortecaja = null,
	sdigitos = null
}