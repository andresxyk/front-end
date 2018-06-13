var pagopacienteBean = new PagoPacienteBean();
	
function VerificaPago()
{
	if (validaPago()) {
		registraPago();
	}
} 

function registraPago() {		
   LoadPagoPacienteBean();
   DatosOrden.setPago(pagopacienteBean,registraPago_CallBack);
}	
	
function registraPago_CallBack(data)
{
   var frmPantalla = window.document.frmConsultaOrden;
	txtAdmision   = frmPantalla.txtAdmision.value;
	txtOrden = frmPantalla.txtOrden.value;
	frmPantalla.txtAdeudo.value = data.msaldo;
	frmPantalla.txtAcuenta.value = (data.manticipo + data.mpagopacienteparcial);
	document.getElementById('txtPago').className = 'textflat';
	frmPantalla.chkEntregaResultados.checked = 0;	                
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
	document.getElementById('txtPago').className = 'text';
}	 

function LoadPagoPacienteBean() {
	frmPantalla = window.document.frmConsultaOrden;
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
//	pagopacienteBean.sdigitos = frmPantalla.txtUltimosDigitos.value;	
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
	tcortecaja = null //,
//	sdigitos = null
}