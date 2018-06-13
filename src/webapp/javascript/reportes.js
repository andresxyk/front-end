function repFundacionSelect(liga, nombre){
	var fecha1 = document.reporte.txtDeFecha.value;
	var fecha2 = document.reporte.txtAFecha.value;
	var opc = document.reporte.selReporte[document.reporte.selReporte.selectedIndex].value;	
	var url='';
	if (fecha1=='' || fecha2==''){
		alert('Es necesario capturar las Fechas...');
		return false;
	}
	url = liga+'?TipoReporte='+opc+'&txtDeFecha='+fecha1+'&txtAFecha='+fecha2+'&eventSubmit_doImprimir=action';
	abrirVentana(url,nombre);
	return  true;
}


function repOrdenTipoComunidadFundacion(liga, nombre){
	var fecha1 = document.reporte.txtDeFecha.value;
	var fecha2 = document.reporte.txtAFecha.value;
	var ncomunidad = document.reporte.selComunidad[document.reporte.selComunidad.selectedIndex].value;	
	var url='';
	if (fecha1=='' || fecha2==''){
		alert('Es necesario capturar las Fechas...');
		return false;
	}
	url = liga+'?txtDeFecha='+fecha1+'&txtAFecha='+fecha2+'&ncomunidad=' + ncomunidad + '&eventSubmit_doImprimirtipocomunidad=action';
	abrirVentana(url,nombre);
	return  true;
}

function repTipoExamenFundacion(liga, nombre){
	var fecha1 = document.reporte.txtDeFecha.value;
	var fecha2 = document.reporte.txtAFecha.value;
	var nexamen = document.reporte.selCatalogoExamenes[document.reporte.selCatalogoExamenes.selectedIndex].value;	
	var url='';
	if (fecha1=='' || fecha2==''){
		alert('Es necesario capturar las Fechas...');
		return false;
	}
	url = liga+'?txtDeFecha='+fecha1+'&txtAFecha='+fecha2+'&nexamen=' + nexamen + '&eventSubmit_doImprimirtipoexamen=action';
	abrirVentana(url,nombre);
	return  true;
}


function repCorteCajaPrevio(liga, nombre){
	if (confirm("Seguro que quieres el corte de caja previo?")) {
		url = liga+'?Actualizacion=0&CorteCaja=0&eventSubmit_doCortecaja=action';	
		abrirVentana(url,nombre);
		return  true;
	}
	return false;
}


function repCotizacion(liga, nombre,intCotizacion){
	url = liga+'?kcotizacion=' + intCotizacion + '&eventSubmit_doIndicaciones=action';	
	abrirVentana(url,nombre);
	return  true;
}


function repFactura(liga, nombre,intAdmision){
	url = liga+'?kAdmision=' + intAdmision + '&eventSubmit_doRecibo=action';	
	abrirVentana(url,nombre);
	return  true;
}

function repOrdenFactura(liga, nombre,intAdmision){
	url = liga+'?kAdmision=' + intAdmision + '&eventSubmit_doRecibofactura=action';	
	abrirVentana(url,nombre);
	return  true;
}


function repCorteCajaDefinitivo(liga, nombre){
	if (confirm("Seguro que quieres el corte de caja definitivo?")) {
		url = liga+'?Actualizacion=1&CorteCaja=0&eventSubmit_doCortecaja=action';	
		abrirVentana(url,nombre);
		return  true;
	} 
	return false;
}

function repCorteCajaBackup(liga, nombre){
	var frmPantalla = window.document.reporte;
	kCorteCaja = frmPantalla.selCortesCaja[frmPantalla.selCortesCaja.selectedIndex].value;	
	url = liga+'?Actualizacion=0&CorteCaja=' + kCorteCaja + '&eventSubmit_doCortecaja=action';	
	abrirVentana(url,nombre);
	return  true;
}