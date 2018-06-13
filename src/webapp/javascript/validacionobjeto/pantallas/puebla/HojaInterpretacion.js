var pacienteBean = new PacienteBean();
var cuestionariopantallainterpretacionBean = new CuestionarioPantallaInterpretacionBean();

	function init() 
	{
	    DWRUtil.useLoadingMessage();
	    document.getElementById("cboRfcUno").disabled = true;
	    document.getElementById("cboResultadoDos").disabled = true;
	    document.getElementById("cboRfcDos").disabled = true;
	    document.getElementById("cboResultadoDesicion").disabled = true;
	    document.getElementById("cboRfcDesicion").disabled = true;	    
	}
	
	function UnoOnChange() {
		var frmPantalla = window.document.frmPantallaPuebla;
		 if (TypeObjeto(frmPantalla.cboResultadoUno) > -1) {
		    document.getElementById("cboRfcUno").disabled = false;			 
		    if (TypeObjeto(frmPantalla.cboResultadoUno) > 3) {
		    	document.getElementById("cboResultadoDos").disabled = false;
		    } else {
			    document.getElementById("cboResultadoDos").disabled = true;
			    document.getElementById("cboRfcDos").disabled = true;
			    document.getElementById("cboResultadoDesicion").disabled = true;
			    document.getElementById("cboRfcDesicion").disabled = true;	    
			    valorCombo(frmPantalla.cboResultadoDos,-1); 
			    valorCombo(frmPantalla.cboRfcDos,-1); 
			    valorCombo(frmPantalla.cboResultadoDesicion,-1); 
			    valorCombo(frmPantalla.cboRfcDesicion,-1); 			
		    }
		 } else {
		    document.getElementById("cboRfcUno").disabled = true;			 			 
		    document.getElementById("cboResultadoDos").disabled = true;
		    valorCombo(frmPantalla.cboRfcUno,-1); 
		 }		
	}

	function DosOnChange() {
		var frmPantalla = window.document.frmPantallaPuebla;
		 if (TypeObjeto(frmPantalla.cboResultadoDos) > -1) {
		    document.getElementById("cboRfcDos").disabled = false;			 
	    	document.getElementById("cboResultadoDesicion").disabled = false;
		 } else {
		    document.getElementById("cboRfcDos").disabled = true;			 			 
		    document.getElementById("cboResultadoDesicion").disabled = true;
		    valorCombo(frmPantalla.cboRfcDos,-1); 
		 }		
	}

	function TresOnChange() {
		var frmPantalla = window.document.frmPantallaPuebla;
		 if (TypeObjeto(frmPantalla.cboResultadoDesicion) > -1) {
		    document.getElementById("cboRfcDesicion").disabled = false;			 
		 } else {
		    document.getElementById("cboRfcDesicion").disabled = true;			 			 
		    valorCombo(frmPantalla.cboRfcDesicion,-1); 
		 }		
	}
	
	function MastografiaAdecuadaOnChange() {
		var frmPantalla = window.document.frmPantallaPuebla;
		if (TypeObjeto(frmPantalla.cboMastoAdecuada) == 1) {
		    adminDIV("DatosInterpretacion","visible","inline");	    
		    document.getElementById("cboResultadoUno").disabled = false;		
			UnoOnChange();
			DosOnChange();
			TresOnChange();		    
		} else if (TypeObjeto(frmPantalla.cboMastoAdecuada) == 2) {
		    adminDIV("DatosInterpretacion","hidden","none");	
			var frmPantalla = window.document.frmPantallaPuebla;
		    document.getElementById("cboResultadoUno").disabled = true;
		    document.getElementById("cboRfcUno").disabled = true;
		    document.getElementById("cboResultadoDos").disabled = true;
		    document.getElementById("cboRfcDos").disabled = true;
		    document.getElementById("cboResultadoDesicion").disabled = true;
		    document.getElementById("cboRfcDesicion").disabled = true;
		    valorCombo(frmPantalla.cboResultadoUno,-1); 
		    valorCombo(frmPantalla.cboRfcUno,-1); 
		    valorCombo(frmPantalla.cboResultadoDos,-1); 
		    valorCombo(frmPantalla.cboRfcDos,-1); 
		    valorCombo(frmPantalla.cboResultadoDesicion,-1); 
		    valorCombo(frmPantalla.cboRfcDesicion,-1); 			
		}
	}
	
/************************************** FUNCIONES PACIENTE *********************************************/	
	function buscarPacienteECE(kPaciente) {	
		pacienteBean.kpacientefundacion=kPaciente;
		pacienteBean.snombre="";
		pacienteBean.sappaterno="";
		pacienteBean.sapmaterno="";
		pacienteBean.cconvenio = 0;
		DatosPaciente.buscarPaciente(pacienteBean,buscarPaciente_CallBack);
	}	

    function buscarPaciente_CallBack(data)
	 {
		var frmPantalla = window.document.frmPantallaPuebla;
		document.getElementById('txtCodigoPaciente').className = 'textflat';
		frmPantalla.txtCodigoPaciente.disabled = true;
		frmPantalla.txtCodigoPaciente.value = data.kpacientefundacion;

		document.getElementById('txtApellidoPaterno').className = 'textflat';
		frmPantalla.txtApellidoPaterno.disabled = true;
		frmPantalla.txtApellidoPaterno.value = data.sappaterno;

		document.getElementById('txtApellidoMaterno').className = 'textflat';
		frmPantalla.txtApellidoMaterno.disabled = true;
		frmPantalla.txtApellidoMaterno.value = data.sapmaterno;		

		document.getElementById('txtNombre').className = 'textflat';
		frmPantalla.txtNombre.disabled = true;
		frmPantalla.txtNombre.value = data.snombre;

		frmPantalla.txtFechaNacimiento.value = data.snacimiento;

		document.getElementById('txtCodigoPostal').className = 'textflat';
		frmPantalla.txtCodigoPostal.disabled = true;
		frmPantalla.txtCodigoPostal.value = data.scodigopostal;

		document.getElementById('txtColonia').className = 'textflat';
		frmPantalla.txtColonia.disabled = true;
		frmPantalla.txtColonia.value = data.scolonia;

		document.getElementById('txtDelegacionMunicipio').className = 'textflat';
		frmPantalla.txtDelegacionMunicipio.disabled = true;
		frmPantalla.txtDelegacionMunicipio.value =  data.sdelegmuni;
		
		document.getElementById('txtCalle').className = 'textflat';
		frmPantalla.txtCalle.disabled = true;
		frmPantalla.txtCalle.value = data.sdireccion;

		document.getElementById('txtEstado').className = 'textflat';
		frmPantalla.txtEstado.disabled = true;
		frmPantalla.txtEstado.value = data.sciudad;
		
		document.getElementById('txtTelefono').className = 'textflat';
		frmPantalla.txtTelefono.disabled = true;
		frmPantalla.txtTelefono.value = data.stelefono;		

		obtenerEdadfrm(window.document.frmPantallaPuebla);				
		frmPantalla.txtFechaNacimiento.disabled = true;
		buscarInterpretacion();
	}	 	 
	
	function viewPacienteCaptura(strLiga,KPaciente){
    	var liga = "";    	
		liga = strLiga + "?kPacientePuebla=" + KPaciente;
		window.location.href =liga;
    	return true;    		
    }
/************************************** FUNCIONES PACIENTE *********************************************/	
/************************************** FUNCIONES INTERPRETACION *********************************************/	

	function actualizaInterpretacion() {
		var bolValidacion = false; 
		if (parseInt(window.document.frmPantallaPuebla.txtCodigoPaciente.value) > 0) {
		    if ((TypeObjeto(frmPantalla.cboResultadoUno) < 4) && (TypeObjeto(frmPantalla.cboResultadoDos) == -1) && (TypeObjeto(frmPantalla.cboResultadoDesicion) == -1)) {
		    	bolValidacion = true;
		    } else if ((TypeObjeto(frmPantalla.cboResultadoUno) > 3) && (TypeObjeto(frmPantalla.cboResultadoDos) > -1) && (TypeObjeto(frmPantalla.cboResultadoDesicion) > -1)) {
		    	bolValidacion = true;
		    } else if ((TypeObjeto(frmPantalla.cboResultadoUno) > -1) && (TypeObjeto(frmPantalla.cboResultadoDos) > 3) && (TypeObjeto(frmPantalla.cboResultadoDesicion) > -1)) {
		    	bolValidacion = true;
		    } else {
		    	bolValidacion = false;
		    }
		    if (bolValidacion) {
				loadInterpretacionBean();
				Puebla.persistirFormato(cuestionariopantallainterpretacionBean,showInterpretacion_CallBack)
		    } else {
		    	alert('Por favor valida los BIRADS');
		    }
		} 
	}
	
	function buscarInterpretacion() {
		if (parseInt(window.document.frmPantallaPuebla.txtCodigoPaciente.value) > 0) {
			loadInterpretacionBean();
			Puebla.buscarFormato(cuestionariopantallainterpretacionBean,showInterpretacion_CallBack)			
		}	
	}
	
	function imprimirInterpretacion() {
		if (parseInt(window.document.frmPantallaPuebla.txtCodigoPaciente.value) > 0) {
		    if ((TypeObjeto(frmPantalla.cboResultadoUno) < 4) && (TypeObjeto(frmPantalla.cboResultadoDos) == -1) && (TypeObjeto(frmPantalla.cboResultadoDesicion) == -1)) {
		    	bolValidacion = true;
		    } else if ((TypeObjeto(frmPantalla.cboResultadoUno) > 3) && (TypeObjeto(frmPantalla.cboResultadoDos) > -1) && (TypeObjeto(frmPantalla.cboResultadoDesicion) > -1)) {
		    	bolValidacion = true;
		    } else if ((TypeObjeto(frmPantalla.cboResultadoUno) > -1) && (TypeObjeto(frmPantalla.cboResultadoDos) > 3) && (TypeObjeto(frmPantalla.cboResultadoDesicion) > -1)) {
		    	bolValidacion = true;
		    } else if ((TypeObjeto(frmPantalla.cboResultadoUno) == -1) && (TypeObjeto(frmPantalla.cboResultadoDos) == -1) && (TypeObjeto(frmPantalla.cboResultadoDesicion) == -1)) {
		    	bolValidacion = true;
		    } else {
		    	bolValidacion = false;
		    }
		    if (bolValidacion) {
				loadInterpretacionBean();
				Puebla.imprimirFormato(cuestionariopantallainterpretacionBean,imprimirInterpretacion_CallBack)			
		    } else {
		    	alert('Por favor valida los BIRADS');
		    }			
		}	
	}
	
	function showInterpretacion_CallBack(data) {
		loadInterpretacionScreen(data);
		if (data.smensajeoperacion != "") {
			alert(data.smensajeoperacion);
		}
	}
	
	function imprimirInterpretacion_CallBack(data) {
		abrirVentana(data.smensajeoperacion,'HojaClinica');
	}
	
	function loadInterpretacionBean() {
		var frmPantalla = window.document.frmPantallaPuebla;						
		cuestionariopantallainterpretacionBean.kordensucursal=frmPantalla.kordensucursal.value;
		cuestionariopantallainterpretacionBean.kPaciente=frmPantalla.txtCodigoPaciente.value;	
		cuestionariopantallainterpretacionBean.sentidadnacimiento=frmPantalla.txtEstado.value;	
		cuestionariopantallainterpretacionBean.sinstitucion=frmPantalla.txtInstitucion.value;	
		cuestionariopantallainterpretacionBean.sentidad=frmPantalla.txtEntidad.value;
		cuestionariopantallainterpretacionBean.sclues=frmPantalla.txtClues.value;
		cuestionariopantallainterpretacionBean.ujurisdiccion=TypeObjeto(frmPantalla.cboJurisdiccion);
		cuestionariopantallainterpretacionBean.smunicipio=frmPantalla.txtMunicipio.value;
		cuestionariopantallainterpretacionBean.sunidadmedica=frmPantalla.txtUnidadMed.value;
		cuestionariopantallainterpretacionBean.sclaveinstitucional=frmPantalla.txtClaveInstitucional.value;
		cuestionariopantallainterpretacionBean.scurp=frmPantalla.txtCurp.value;
		cuestionariopantallainterpretacionBean.uderechohabiencia=TypeObjeto(frmPantalla.cboDerechoHabiencia);
	    cuestionariopantallainterpretacionBean.slugarnacimiento=TypeObjeto(frmPantalla.selEntidadNacimiento);	    	    

	    if(TypeObjeto(frmPantalla.radMastoAnterior[0])){
			cuestionariopantallainterpretacionBean.uantescedentedemastografia=frmPantalla.radMastoAnterior[0].value;
		} else {
			cuestionariopantallainterpretacionBean.uantescedentedemastografia=frmPantalla.radMastoAnterior[1].value;
		}
		cuestionariopantallainterpretacionBean.umastografiaadecuada= TypeObjeto(frmPantalla.cboMastoAdecuada);
		if(TypeObjeto(frmPantalla.chkImagenIncompleta)){
			cuestionariopantallainterpretacionBean.bolimagenincompleta =true;
		} else {
			cuestionariopantallainterpretacionBean.bolimagenincompleta =false;
		}
		if(TypeObjeto(frmPantalla.chkBajoContraste)){
			cuestionariopantallainterpretacionBean.bolbajocontraste =true;
		} else {
			cuestionariopantallainterpretacionBean.bolbajocontraste =false;
		}
		if(TypeObjeto(frmPantalla.chkBajaResolucion)){
			cuestionariopantallainterpretacionBean.bolbajaresolucion =true;
		} else {
			cuestionariopantallainterpretacionBean.bolbajaresolucion =false;
		}
		if(TypeObjeto(frmPantalla.chkArtefactos)){
			cuestionariopantallainterpretacionBean.bolartefactos =true;
		} else {
			cuestionariopantallainterpretacionBean.bolartefactos =false;
		}
		if(TypeObjeto(frmPantalla.chkMalPosicionamiento)){
			cuestionariopantallainterpretacionBean.bolmalposcicionamiento =true;
		} else {
			cuestionariopantallainterpretacionBean.bolmalposcicionamiento =false;
		}
		if(TypeObjeto(frmPantalla.chkOtros)){
			cuestionariopantallainterpretacionBean.bolotros =true;
		} else {
			cuestionariopantallainterpretacionBean.bolotros =false;
		}
		cuestionariopantallainterpretacionBean.sfechaultimamastografia=frmPantalla.txtFechaUltMasto.value;
		if(frmPantalla.txtBiradsAnt.value==""){
			cuestionariopantallainterpretacionBean.uresultadobiradsmastografia=-1;
		} else {
			cuestionariopantallainterpretacionBean.uresultadobiradsmastografia=frmPantalla.txtBiradsAnt.value;
		}		
		cuestionariopantallainterpretacionBean.umodalidadmastografiatamizaje=TypeObjeto(frmPantalla.cboTamizaje);
		cuestionariopantallainterpretacionBean.umodalidadmastografiadiagnostica=TypeObjeto(frmPantalla.cboDiagnostica);
		cuestionariopantallainterpretacionBean.sfechatomamastografia=frmPantalla.txtFechaToma.value;
		cuestionariopantallainterpretacionBean.sfechainterpretacionmastografia=frmPantalla.txtFechaInterpretacion.value;
		if(TypeObjeto(frmPantalla.chkTumorDer)){
			cuestionariopantallainterpretacionBean.boltumorderecho =true;
		} else {
			cuestionariopantallainterpretacionBean.boltumorderecho =false;
		}
	    if(TypeObjeto(frmPantalla.chkTumorIzq)){
			cuestionariopantallainterpretacionBean.boltumorizquierdo =true;
		} else {
			cuestionariopantallainterpretacionBean.boltumorizquierdo =false;
		}
	    if(TypeObjeto(frmPantalla.chkAsimetriaDer)){
			cuestionariopantallainterpretacionBean.bolasimetriaderecho =true;
		} else {
			cuestionariopantallainterpretacionBean.bolasimetriaderecho =false;
		}
	    if(TypeObjeto(frmPantalla.chkAsimetriaIzq)){
			cuestionariopantallainterpretacionBean.bolasimetriaizquierdo =true;
		} else {
			cuestionariopantallainterpretacionBean.bolasimetriaizquierdo =false;
		}
	    if(TypeObjeto(frmPantalla.chkDeformidadDer)){
			cuestionariopantallainterpretacionBean.boldeformidadderecho =true;
		} else {
			cuestionariopantallainterpretacionBean.boldeformidadderecho =false;
		}
	    if(TypeObjeto(frmPantalla.chkDeformidadIzq)){
			cuestionariopantallainterpretacionBean.boldeformidadizquierdo =true;
		} else {
			cuestionariopantallainterpretacionBean.boldeformidadizquierdo =false;
		}
	    if(TypeObjeto(frmPantalla.chkCalcificacionDer)){
			cuestionariopantallainterpretacionBean.bolcalcificacionderecho =true;
		} else {
			cuestionariopantallainterpretacionBean.bolcalcificacionderecho =false;
		}
	    if(TypeObjeto(frmPantalla.chkCalcificacionIzq)){
			cuestionariopantallainterpretacionBean.bolcalcificacionizquierdo =true;
		} else {
			cuestionariopantallainterpretacionBean.bolcalcificacionizquierdo =false;
		}
	    if(TypeObjeto(frmPantalla.chkDensidadDer)){
			cuestionariopantallainterpretacionBean.boldensidadasimetricaderecho =true;
		} else {
			cuestionariopantallainterpretacionBean.boldensidadasimetricaderecho =false;
		}
	    if(TypeObjeto(frmPantalla.chkDensidadIzq)){
			cuestionariopantallainterpretacionBean.boldensidadasimetricaizquierdo =true;
		} else {
			cuestionariopantallainterpretacionBean.boldensidadasimetricaizquierdo =false;
		}
	    if(TypeObjeto(frmPantalla.chkOtrosDer)){
			cuestionariopantallainterpretacionBean.bolotrosderecho =true;
		} else {
			cuestionariopantallainterpretacionBean.bolotrosderecho =false;
		}
	    if(TypeObjeto(frmPantalla.chkOtrosIzq)){
			cuestionariopantallainterpretacionBean.bolotrosizquierdo =true;
		} else {
			cuestionariopantallainterpretacionBean.bolotrosizquierdo =false;
		}
	    
	    cuestionariopantallainterpretacionBean.uresultadobiradsuno     = TypeObjeto(frmPantalla.cboResultadoUno);
	    cuestionariopantallainterpretacionBean.uresultadobiradsdos     = TypeObjeto(frmPantalla.cboResultadoDos);
	    cuestionariopantallainterpretacionBean.uresultadobiradstres    = TypeObjeto(frmPantalla.cboResultadoDesicion);	    	    
	    cuestionariopantallainterpretacionBean.srfcresultadobiradsuno  = TypeObjeto(frmPantalla.cboRfcUno); 
	    cuestionariopantallainterpretacionBean.srfcresultadobiradsdos  = TypeObjeto(frmPantalla.cboRfcDos); 
	    cuestionariopantallainterpretacionBean.srfcresultadobiradstres = TypeObjeto(frmPantalla.cboRfcDesicion); 
	    cuestionariopantallainterpretacionBean.snombreradiologo        = frmPantalla.cboRFCRadiologo[frmPantalla.cboRFCRadiologo.selectedIndex].text;
	    cuestionariopantallainterpretacionBean.srfcradiologo           = TypeObjeto(frmPantalla.cboRFCRadiologo); 
	    cuestionariopantallainterpretacionBean.sobservaciones          = frmPantalla.txtObservaciones.value;
	    cuestionariopantallainterpretacionBean.sfechainformeresultado  = frmPantalla.txtFechaInforme.value;

	    
	    if(TypeObjeto(frmPantalla.chkRepeticionEst)){
			cuestionariopantallainterpretacionBean.bolrepeticionestudio =true;
		} else {
			cuestionariopantallainterpretacionBean.bolrepeticionestudio =false;
		}
	    if(TypeObjeto(frmPantalla.chkProxMasto)){
			cuestionariopantallainterpretacionBean.bolproximadeteccion =true;
		} else {
			cuestionariopantallainterpretacionBean.bolproximadeteccion =false;
		}
	    if(TypeObjeto(frmPantalla.chkRefeGineco)){
			cuestionariopantallainterpretacionBean.bolreferenciaginecologia =true;
		} else {
			cuestionariopantallainterpretacionBean.bolreferenciaginecologia =false;
		}
	    cuestionariopantallainterpretacionBean.sreferenciaevaluacion=frmPantalla.txtReferenciaEva.value;
	    cuestionariopantallainterpretacionBean.sfechareferencia=frmPantalla.txtFechaReferencia.value;
	    
	    
	    
	    cuestionariopantallainterpretacionBean.smensajeoperacion="";
	}

	function loadInterpretacionScreen(data) {
		var frmPantalla = window.document.frmPantallaPuebla;						
		if (data.kcuestionariopacienteinterpretacionpuebla == 0) {
			frmPantalla.butImprimirFormato.disabled=true;		
			adminDIV("DatosInterpretacion","hidden","none");
		} else {
			frmPantalla.butImprimirFormato.disabled=false;		
			adminDIV("DatosInterpretacion","visible","inline");
			frmPantalla.kcuestionariopacienteinterpretacionpuebla.value=data.kcuestionariopacienteinterpretacionpuebla;			 
			frmPantalla.txtInstitucion.value=data.sinstitucion;	
			frmPantalla.txtEntidad.value=data.sentidad;
			frmPantalla.txtClues.value=data.sclues;
			frmPantalla.cboJurisdiccion.selectedIndex = data.ujurisdiccion;
			frmPantalla.txtMunicipio.value=data.smunicipio;
			frmPantalla.txtUnidadMed.value=data.sunidadmedica;
			frmPantalla.txtClaveInstitucional.value=data.sclaveinstitucional;
			frmPantalla.txtCurp.value=data.scurp;
			valorCombo(frmPantalla.cboDerechoHabiencia,data.uderechohabiencia);			
			valorCombo(frmPantalla.selEntidadNacimiento,data.slugarnacimiento);			
			
			if (data.uantescedentedemastografia==1) {
				frmPantalla.radMastoAnterior[0].checked = true;
				frmPantalla.radMastoAnterior[1].checked = false;	     
			} else {
				frmPantalla.radMastoAnterior[0].checked = false;
				frmPantalla.radMastoAnterior[1].checked = true;    
			}			
			valorCombo(frmPantalla.cboMastoAdecuada,data.umastografiaadecuada);
			if (data.umastografiaadecuada == 1) {
			    adminDIV("DatosInterpretacion","visible","inline");	    
				valorCombo(frmPantalla.cboTamizaje,data.umodalidadmastografiatamizaje);
				valorCombo(frmPantalla.cboDiagnostica,data.umodalidadmastografiadiagnostica);
				valorCombo(frmPantalla.cboResultadoUno,data.uresultadobiradsuno);
				valorCombo(frmPantalla.cboResultadoDos,data.uresultadobiradsdos);
				valorCombo(frmPantalla.cboResultadoDesicion,data.uresultadobiradstres);
				valorCombo(frmPantalla.cboRfcUno,data.srfcresultadobiradsuno); 
				valorCombo(frmPantalla.cboRfcDos,data.srfcresultadobiradsdos); 
				valorCombo(frmPantalla.cboRfcDesicion,data.srfcresultadobiradstres); 			
				valorCombo(frmPantalla.cboRFCRadiologo,data.srfcradiologo); 
				frmPantalla.txtFechaInterpretacion.value=data.sfechainterpretacionmastografia;
				frmPantalla.txtFechaInforme.value=data.sfechainformeresultado;
				if (data.bolproximadeteccion) {frmPantalla.chkProxMasto.checked = true;} else {frmPantalla.chkProxMasto.checked = false;}
				if (data.boltumorderecho) {frmPantalla.chkTumorDer.checked = true;} else {frmPantalla.chkTumorDer.checked = false;}
				if (data.boltumorizquierdo) {frmPantalla.chkTumorIzq.checked = true;} else {frmPantalla.chkTumorIzq.checked = false;}
				if (data.bolasimetriaderecho) {frmPantalla.chkAsimetriaDer.checked = true;} else {frmPantalla.chkAsimetriaDer.checked = false;}
				if (data.bolasimetriaizquierdo) {frmPantalla.chkAsimetriaIzq.checked = true;} else {frmPantalla.chkAsimetriaIzq.checked = false;}
				if (data.boldeformidadderecho) {frmPantalla.chkDeformidadDer.checked = true;} else {frmPantalla.chkDeformidadDer.checked = false;}
				if (data.boldeformidadizquierdo) {frmPantalla.chkDeformidadIzq.checked = true;} else {frmPantalla.chkDeformidadIzq.checked = false;}
				if (data.bolcalcificacionderecho) {frmPantalla.chkCalcificacionDer.checked = true;} else {frmPantalla.chkCalcificacionDer.checked = false;}
				if (data.bolcalcificacionizquierdo) {frmPantalla.chkCalcificacionIzq.checked = true;} else {frmPantalla.chkCalcificacionIzq.checked = false;}
				if (data.boldensidadasimetricaderecho) {frmPantalla.chkDensidadDer.checked = true;} else {frmPantalla.chkDensidadDer.checked = false;}
				if (data.boldensidadasimetricaizquierdo) {frmPantalla.chkDensidadIzq.checked = true;} else {frmPantalla.chkDensidadIzq.checked = false;}
				if (data.bolotrosderecho) {frmPantalla.chkOtrosDer.checked = true;} else {frmPantalla.chkOtrosDer.checked = false;}
				if (data.bolotrosizquierdo) {frmPantalla.chkOtrosIzq.checked = true;} else {frmPantalla.chkOtrosIzq.checked = false;}
				frmPantalla.txtObservaciones.value=data.sobservaciones;
				if (data.bolreferenciaginecologia) {frmPantalla.chkRefeGineco.checked = true;} else {frmPantalla.chkRefeGineco.checked = false;}
				if (data.bolrepeticionestudio) {frmPantalla.chkRepeticionEst.checked = true;} else {frmPantalla.chkRepeticionEst.checked = false;}
				
			} else if (data.umastografiaadecuada == 2) {
			    adminDIV("DatosInterpretacion","hidden","none");	
			    document.getElementById("cboResultadoUno").disabled = true;
			    document.getElementById("cboRfcUno").disabled = true;
			    document.getElementById("cboResultadoDos").disabled = true;
			    document.getElementById("cboRfcDos").disabled = true;
			    document.getElementById("cboResultadoDesicion").disabled = true;
			    document.getElementById("cboRfcDesicion").disabled = true;
			    valorCombo(frmPantalla.cboResultadoUno,-1); 
			    valorCombo(frmPantalla.cboRfcUno,-1); 
			    valorCombo(frmPantalla.cboResultadoDos,-1); 
			    valorCombo(frmPantalla.cboRfcDos,-1); 
			    valorCombo(frmPantalla.cboResultadoDesicion,-1); 
			    valorCombo(frmPantalla.cboRfcDesicion,-1);
			}
			frmPantalla.txtFechaToma.value=data.sfechatomamastografia;
			frmPantalla.txtFechaUltMasto.value=data.sfechaultimamastografia;
			if(data.uresultadobiradsmastografia==-1){
				frmPantalla.txtBiradsAnt.value="";
			} else {
				frmPantalla.txtBiradsAnt.value=data.uresultadobiradsmastografia;
			}
			
			if (data.bolimagenincompleta) {
				frmPantalla.chkImagenIncompleta.checked = true;  
			} else {
				 frmPantalla.chkImagenIncompleta.checked = false;
			}
			if (data.bolbajocontraste) {frmPantalla.chkBajoContraste.checked = true;} else {frmPantalla.chkBajoContraste.checked = false;}		
			if (data.bolbajaresolucion) {frmPantalla.chkBajaResolucion.checked = true;} else {frmPantalla.chkBajaResolucion.checked = false;}
			if (data.bolartefactos) {frmPantalla.chkArtefactos.checked = true;} else {frmPantalla.chkArtefactos.checked = false;}
			if (data.bolmalposcicionamiento) {frmPantalla.chkMalPosicionamiento.checked = true;} else {frmPantalla.chkMalPosicionamiento.checked = false;}
			if (data.bolotros) {frmPantalla.chkOtros.checked = true;} else {frmPantalla.chkOtros.checked = false;}
		}
		UnoOnChange();
		DosOnChange();
		TresOnChange();
	}

	
/************************************** FUNCIONES INTERPRETACION *********************************************/	
	

/************************************** BEANS *********************************************/	
	function PacienteBean() {	
		kpacientefundacion=null,
		snombre=null,
		sappaterno=null,
		sapmaterno=null,
		dnacimiento=null,
		snacimiento=null,
		sdireccion=null,
		scolonia=null,
		sdelegmuni=null,
		ccodigopostal=null,
		scodigopostal=null,
		stelefono=null,
		scelular=null,
		scorreoelectronico=null,
		sciudad=null,
		bregistroactivo=null,
		cusuario=null,
		csexo=null,
		cconvenio=null,
		svalorexpediente=null,
		uopcionenviocorreo=null,
		scelular=null
	}	
	
	function CuestionarioPantallaInterpretacionBean() {
		kcuestionariopacienteinterpretacionpuebla=null,	
		kordensucursal=null,	
		sentidadnacimiento=null,
		sinstitucion=null,
		sentidad=null,
		sclues=null,
		ujurisdiccion=null,	
		smunicipio=null,
		sunidadmedica=null,
		sclaveinstitucional=null,
		scurp=null,
		uderechohabiencia=null,
		uantescedentedemastografia=null,	
		sfechaultimamastografia=null,
		uresultadobiradsmastografia=null,
		umodalidadmastografiatamizaje=null,
		umodalidadmastografiadiagnostica=null,
		sfechatomamastografia=null,
		umastografiaadecuada=null,
		bolimagenincompleta=null,
		bolbajocontraste=null,
		bolbajaresolucion=null,
		bolartefactos=null,
		bolmalposcicionamiento=null,
		bolotros=null,
		sfechainterpretacionmastografia=null,
		boltumorderecho=null,
		boltumorizquierdo=null,
		bolasimetriaderecho=null,
		bolasimetriaizquierdo=null,
		boldeformidadderecho=null,
		boldeformidadizquierdo=null,
		bolcalcificacionderecho=null,
		bolcalcificacionizquierdo=null,
		boldensidadasimetricaderecho=null,
		boldensidadasimetricaizquierdo=null,
		bolotrosderecho=null,
		bolotrosizquierdo=null,
		uresultadobiradsuno=null,
		uresultadobiradsdos=null,
		uresultadobiradstres=null,
		srfcresultadobiradsuno=null,
		srfcresultadobiradsdos=null,
		srfcresultadobiradstres=null,
		sobservaciones=null,
		sfechainformeresultado=null,
		bolrepeticionestudio=null,
		bolproximadeteccion=null,
		bolreferenciaginecologia=null,
		sreferenciaevaluacion=null,
		sfechareferencia=null,
		snombreradiologo=null,
		srfcradiologo=null,
		smensajeoperacion=null,
		slugarnacimiento=null
	}
	
	/**
	adminDIV* constantes para validaciones
	*/
	var letras  = "abcdefghijklmn?opqrstuvwxyzABCDEFGHIJKLMN?OPQRSTUVWXYZ ";
	var digito = "0123456789";
	var dPD = ".";
	var space = " \t\n\r";
	var ok = "yes";

	/************* Funciones Genericas ********************/
	function ValidaRfc(rfcStr) {
		var strCorrecta;
		strCorrecta = rfcStr;	
		if (rfcStr.length == 12){
		var valid = '^(([A-Z]|[a-z]){3})([0-9]{6})((([A-Z]|[a-z]|[0-9]){3}))';
		}else{
		var valid = '^(([A-Z]|[a-z]|\s){1})(([A-Z]|[a-z]){3})([0-9]{6})((([A-Z]|[a-z]|[0-9]){3}))';
		}
		var validRfc=new RegExp(valid);
		var matchArray=strCorrecta.match(validRfc);
		if (matchArray==null) {
			alert('Debe ingresar un RFC valido');

			return false;
		}
		else
		{
			//alert('Cadena correcta:' + strCorrecta);
			return true;
		}
		
	}

	function validaCurp(curpStr){

		if(curpStr.match(/^([a-z]{4})([0-9]{6})([a-z]{6})([0-9]{2})$/i))
		{
			//alert('curp válida!');
			return true;
		}else
		{
			alert('Debe ingresar un CURP correcto!');
			frmPantalla.txtCurp.value="";
			return false;
		}
		 
	 } 

	
	
