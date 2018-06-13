
	function visualizaDatosOrden(strLiga, nombre){
       if (window.event && window.event.keyCode == 13) {
	        var frmPantalla = window.document.frmDatosOrdenFactuacion;
	    	if (frmPantalla.txtAdmision.value != "" && frmPantalla.txtAdmision.value > 0) {
		    	var liga = "";    	
				liga = strLiga + "?kOrdenSucursal=" + frmPantalla.txtAdmision.value + "&uOrden=0&uTipoLlamado=2";
		    	abrirVentana(liga,nombre);
//				window.location.href =liga;
//				window.location.reload(true);
		    	return true;    		
	    	} else {
	    		return false;
	    	}
       }
    }

	
	function generarFacturaGlobal() {
		if (confirm("Estas seguro de generar la Factura Global?")) {
    		DatosOrden.registraResultados(frmPantalla.txtAdmision.value,frmPantalla.idUsuario.value,actualizarEntregaResultado_CallBack);
		}		
	}