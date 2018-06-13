	function validaTextNumerico(txtValidar,intInicio,intFinal,strCampo) {
   		var dblValidar = txtValidar.value;
   		if (isNumerico(txtValidar)) {
			if ((dblValidar >= intInicio) && (dblValidar <= intFinal)) {
				return true;
			} else {
				alert('No es correcto el valor de la ' + strCampo + ' el rango es: ' + intInicio  + ' - ' + intFinal + ', validalo por favor!!!')
				txtValidar.value = intInicio;
				return false;
			}			
   		}
	}

	function calculaIndice() {
        var frmPantalla = window.document.frmConsultaOrden;
   		var dblPeso = frmPantalla.txtPeso.value;
   		var dblTalla = frmPantalla.txtTalla.value;
		if ((dblTalla > 0.99) && (dblTalla < 2.21)) {			
			var dblimc = ((dblTalla * dblTalla) / dblPeso);
			frmPantalla.txtImc.value = dblimc;
		}
	}
	
	
	function initExpedinteEmpresa() {
        var frmPantalla = window.document.frmConsultaOrden;
        frmPantalla.chktetdif1.checked = false;
        frmPantalla.chktetdif2.checked = false;
        frmPantalla.chktetdif3.checked = false;
        frmPantalla.chktetdif4.checked = false;
        frmPantalla.txtImc.disabled = true;	    
        frmPantalla.txtFechaTetDif1.disabled = true;	    
        frmPantalla.txtFechaTetDif2.disabled = true;	    
        frmPantalla.txtFechaTetDif3.disabled = true;	    
        frmPantalla.txtFechaTetDif4.disabled = true;	    
	}

	
	function habilitarFecha() {
		
		
	}
	
	function agregarOption(objSelect) {		
		motivo = prompt("Cual es el nombre de la nueva Opcion?", "");
		if ((motivo != null) && (motivo != "")) {
			variable=new Option(motivo,objSelect.options.length);
			objSelect.options[objSelect.options.length]=variable;
		}
	}
	
    function visualizaDiagnostico(strLiga, nombre){
 //       var frmPantalla = window.document.frmDiagnostico;
//    	if (frmPantalla.hdnkPaciente.value != "" && frmPantalla.hdnkPaciente.value > 0) {
//	    	var liga = "";    	
//			liga = strLiga + "?kPaciente=" + frmPantalla.hdnkPaciente.value;
//			window.location.href =liga;
			window.location.href =strLiga;
//	    	return true;    		
//    	} else {
 //   		return false;
//    	}
    }
	