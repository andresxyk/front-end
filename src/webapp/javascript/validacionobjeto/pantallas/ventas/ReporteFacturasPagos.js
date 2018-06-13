function repFacturas(liga, nombre){
    var frmPantalla = window.document.frmReportesPagoFactura;		
	var fecha1 = frmPantalla.txtFechaInicio.value;
	var fecha2 = frmPantalla.txtFechaFinal.value;
	var opc = frmPantalla.selConvenios[frmPantalla.selConvenios.selectedIndex].value;	
	var tiporeporte = frmPantalla.selTipoReporte[frmPantalla.selTipoReporte.selectedIndex].value;	
	var url='';
	if (fecha1=='' || fecha2==''){
		alert('Es necesario capturar las Fechas...');
		return false;
	}
	url = liga+'?cConvenio='+opc+'&uTipoFile=' + tiporeporte + '&txtDeFecha='+fecha1+'&txtAFecha='+fecha2+'&eventSubmit_doReportefacturas=action';
	abrirVentana(url,nombre);
	return  true;
}

function repRefacturas(liga, nombre){
    var frmPantalla = window.document.frmReportesPagoFactura;		
	var fecha1 = frmPantalla.txtFechaInicio.value;
	var fecha2 = frmPantalla.txtFechaFinal.value;
	var opc = frmPantalla.selConvenios[frmPantalla.selConvenios.selectedIndex].value;	
	var tiporeporte = frmPantalla.selTipoReporte[frmPantalla.selTipoReporte.selectedIndex].value;	
	var url='';
	if (fecha1=='' || fecha2==''){
		alert('Es necesario capturar las Fechas...');
		return false;
	}
	url = liga+'?cConvenio='+opc+'&uTipoFile=' + tiporeporte + '&txtDeFecha='+fecha1+'&txtAFecha='+fecha2+'&eventSubmit_doReporterefacturas=action';
	abrirVentana(url,nombre);
	return  true;
}


function repPagos(liga, nombre){
    var frmPantalla = window.document.frmReportesPagoFactura;		
	var fecha1 = frmPantalla.txtFechaInicio.value;
	var fecha2 = frmPantalla.txtFechaFinal.value;
	var opc = frmPantalla.selConvenios[frmPantalla.selConvenios.selectedIndex].value;	
	var tiporeporte = frmPantalla.selTipoReporte[frmPantalla.selTipoReporte.selectedIndex].value;	
	var url='';
	if (fecha1=='' || fecha2==''){
		alert('Es necesario capturar las Fechas...');
		return false;
	}
	url = liga+'?cConvenio='+opc+'&uTipoFile=' + tiporeporte + '&txtDeFecha='+fecha1+'&txtAFecha='+fecha2+'&eventSubmit_doReportepagos=action';
	abrirVentana(url,nombre);
	return  true;
}

function repNotas(liga, nombre){
    var frmPantalla = window.document.frmReportesPagoFactura;		
	var fecha1 = frmPantalla.txtFechaInicio.value;
	var fecha2 = frmPantalla.txtFechaFinal.value;
	var opc = frmPantalla.selConvenios[frmPantalla.selConvenios.selectedIndex].value;	
	var tiporeporte = frmPantalla.selTipoReporte[frmPantalla.selTipoReporte.selectedIndex].value;	
	var url='';
	if (fecha1=='' || fecha2==''){
		alert('Es necesario capturar las Fechas...');
		return false;
	}
	url = liga+'?cConvenio='+opc+'&uTipoFile=' + tiporeporte + '&txtDeFecha='+fecha1+'&txtAFecha='+fecha2+'&eventSubmit_doReportenotas=action';
	abrirVentana(url,nombre);
	return  true;
}

function repPendienteSucursal(liga, nombre){
    var frmPantalla = window.document.frmReportesPagoFactura;		
	var fecha1 = frmPantalla.txtFechaInicio.value;
	var fecha2 = frmPantalla.txtFechaFinal.value;
	var opc = frmPantalla.selConvenios[frmPantalla.selConvenios.selectedIndex].value;	
	var tiporeporte = frmPantalla.selTipoReporte[frmPantalla.selTipoReporte.selectedIndex].value;	
	var url='';
	if (fecha1=='' || fecha2==''){
		alert('Es necesario capturar las Fechas...');
		return false;
	}
	url = liga+'?cConvenio='+opc+'&uTipoFile=' + tiporeporte + '&txtDeFecha='+fecha1+'&txtAFecha='+fecha2+'&eventSubmit_doReportependientesucursales=action';
	abrirVentana(url,nombre);
	return  true;
}

function repPendienteFacturacion(liga, nombre){
    var frmPantalla = window.document.frmReportesPagoFactura;		
	var fecha1 = frmPantalla.txtFechaInicio.value;
	var fecha2 = frmPantalla.txtFechaFinal.value;
	var opc = frmPantalla.selConvenios[frmPantalla.selConvenios.selectedIndex].value;	
	var tiporeporte = frmPantalla.selTipoReporte[frmPantalla.selTipoReporte.selectedIndex].value;	
	var url='';
	if (fecha1=='' || fecha2==''){
		alert('Es necesario capturar las Fechas...');
		return false;
	}
	url = liga+'?cConvenio='+opc+'&uTipoFile=' + tiporeporte + '&txtDeFecha='+fecha1+'&txtAFecha='+fecha2+'&eventSubmit_doReportependientefacturacion=action';
	abrirVentana(url,nombre);
	return  true;
}