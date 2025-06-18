var pacienteBean = new PacienteBean();
var screenBean = new ScreenBean();
screenBean.uTypeShow = 0;
screenBean.uConsultaECEConvenio = 0;
/***************************************** INICIO FUNCIONES DE VALIDACIONES *****************************************************************/

	function CallPuebla(strPath)
	{
  	    var frmPantalla = window.document.frmDatosOrdenFundacion;
		if (frmPantalla.txtCodigoPaciente.value.length > 1) {
			window.location = strPath + "?kPaciente=" + frmPantalla.txtCodigoPaciente.value;
		} else {
			alert("Debes seleccionar un paciente")			
		}
	} 
	
	function CallFormato(strPath)
	{
  	    var frmPantalla = window.document.frmDatosOrdenFundacion;
		if (frmPantalla.txtAdmision.value.length > 1) {
			window.location = strPath +"?kOrdenSucursal=" + frmPantalla.txtAdmision.value + "&kPaciente=" + frmPantalla.txtCodigoPaciente.value;
		} else {
			alert("Debes seleccionar una orden")
		}
	} 

	 function VerificaModificacionExcepcion()
	 {
		if (validaFullPaciente()) {
			actualizaPacienteExepcion();
		}
	 } 	 
	 
	 	/************************Restringe la captura de los datos demograficos del paciente ******************* OMRR 05092013***********/
	 function VerificaModificacion()
     {
        var frmPantalla = window.document.frmDatosOrdenFundacion;
		if (TypeObjeto(frmPantalla.radTipo[0])) { 
		 	if (validaFullPaciente()) {
				actualizaPaciente();
			}
		} else {
		 	if (validaCotizacionPaciente()) {
				actualizaPaciente();
			}			
		}	 
	 } 	 

     function VerificaModificacion_ClickCorreoElectronico()
     {
  	    var frmPantalla = window.document.frmDatosOrdenFundacion;
  	    if (frmPantalla.txtCorreoElectronico.value.length > 7) {
	    	frmPantalla.hdlUpdatePaciente.value = "1";   	 
		 	if (validaFullPaciente()) {
				actualizaPacienteExepcion();
			}
	    	frmPantalla.hdlUpdatePaciente.value = "0";   	 
  	    } else if (((frmPantalla.optResultado.checked == true) || (frmPantalla.optPromocion.checked == true)) && (frmPantalla.txtCorreoElectronico.value.length < 7)) {
  	    	alert("No puede existir opciones seleccionadas sin correo");
  	    	onCorreoElectronico();
  	    } else if (((frmPantalla.optResultado.checked == false) && (frmPantalla.optPromocion.checked == false)) && (frmPantalla.txtCorreoElectronico.value.length > 7)) {
  	    	alert("No puede existir correo sin opciones seleccionadas");
  	    	frmPantalla.optResultado.checked = true;
  	    }
	 } 
	 
     function VerificaModificacion_KeyPress()
     {
        if (window.event && window.event.keyCode == 13) {
		 	if (validaFullPaciente()) {
				actualizaPaciente();
			}
		}
	 } 

    function iscorrectEmail() {
	    var frmPantalla = window.document.frmDatosOrdenFundacion;
    	if (!isMail(frmPantalla.txtCorreoElectronico.value)) {
    		frmPantalla.txtCorreoElectronico.value = "";
			frmPantalla.txtCorreoElectronico.disabled = true;
		    frmPantalla.optResultado.checked = false;
		    frmPantalla.optPromocion.checked = false;
    		return false;
    	}
    	return true;
    } 
    
	function viewPacienteCaptura(strLiga,KPaciente){
    	var liga = "";    	
		liga = strLiga + "?kPaciente=" + KPaciente;
		window.location.href =liga;
    	return true;    		
    }
	
	function setColorTextBox(strBlanco,strExcepcion) {
    	document.getElementById('txtApellidoPaterno').style.backgroundColor = strExcepcion;
    	document.getElementById('txtNombre').style.backgroundColor = strExcepcion;
    	document.getElementById('txtTelefono').style.backgroundColor = strBlanco;
    	document.getElementById('txtCalle').style.backgroundColor = strBlanco;
    	document.getElementById('txtColonia').style.backgroundColor = strBlanco;
    	document.getElementById('txtDelegacionMunicipio').style.backgroundColor = strBlanco;
    	document.getElementById('txtCodigoPostal').style.backgroundColor = strBlanco;
    	document.getElementById('txtFechaNacimiento').style.backgroundColor = strBlanco;
	}

	function validaFullPaciente() {
	    var frmPantalla = window.document.frmDatosOrdenFundacion;
	    var bolReturn = true;
		hdnCodigoPostal = frmPantalla.hdnCodigoPostal;
		bolSexoFemenino =  TypeObjeto(frmPantalla.radSexo[0]);
		bolSexoMasculino =  TypeObjeto(frmPantalla.radSexo[1]);		

		bolReturn = setValidarTextos('txtApellidoPaterno',2)
		bolReturn = setValidarTextos('txtNombre',2);
		bolReturn = setValidarTextos('txtTelefono',2);		
		bolReturn = setValidarTextos('txtCalle',1);		
		bolReturn = setValidarTextos('txtColonia',2);		
		bolReturn = setValidarTextos('txtDelegacionMunicipio',2);		
		bolReturn = setValidarTextos('txtCodigoPostal',2);		
		bolReturn = setValidarTextos('txtFechaNacimiento',1);		
		
		if (bolSexoFemenino == false) {
			if (bolSexoMasculino == false) {
				bolReturn = false;	   	    				    
		    } else if (hdnCodigoPostal.value == 0) {
		    	bolReturn = false;	   	    			    	
		    }
		} else {
			if (hdnCodigoPostal.value == 0) {
				bolReturn = false;	   	    			    	
			}
		}		
	    return bolReturn;	
	}	 	

	
	function validaCotizacionPaciente() {
	    var frmPantalla = window.document.frmDatosOrdenFundacion;
	    var bolReturn = true;
		bolSexoFemenino =  TypeObjeto(frmPantalla.radSexo[0]);
		bolSexoMasculino =  TypeObjeto(frmPantalla.radSexo[1]);				
		bolReturn = setValidarTextos('txtApellidoPaterno',2)
		bolReturn = setValidarTextos('txtNombre',2);
		bolReturn = setValidarTextos('txtTelefono',2);		
	    if (bolSexoFemenino == false) {
	    	if (bolSexoMasculino == false) {
	    		bolReturn = false;	   	    		
	    	}
	    }
	    return bolReturn;	
	}	 	

	
/***************************************** TERMINA FUNCIONES DE VALIDACIONES *****************************************************************/
	    			     
	 function actualizaPaciente() {		
		var frmPantalla = window.document.frmDatosOrdenFundacion;
		var strCorreoElectronico = trim(frmPantalla.txtCorreoElectronico.value);
		var intCodigoPaciente = frmPantalla.txtCodigoPaciente.value;
		if ((frmPantalla.hdlUpdatePaciente.value == "0") && (intCodigoPaciente > 0)) {		
			// No debe actualizar el Sistema si el usuario no a cambiado nada.
			return false;
		} else if ((frmPantalla.hdlNoOrdenes.value > 1) || (frmPantalla.idMarca.value > 1)) {
			OnlyReaderNombrePaciente(true);
			alert('No es posible modificar NINGUN dato del Paciente ya que tiene 2 o mas ordenes cotizadas.');
			return false;
	    } else {	
			if (TypeObjeto(frmPantalla.radTipo[0])) { 
				if (strCorreoElectronico != "") {			
		    		if(!valEmail(frmPantalla.txtCorreoElectronico.value)){
		    			alert('La direcci&oacute;n de correo no es correcta, debe corregirla e intentar de nuevo.');
		    			return false;
		    		}
				}
				LoadCompletedPaciente();
			} else {
				LoadCotizacionPaciente();
			}
			showDatosPaciente(true);			
    		DatosPaciente.actualizaPaciente(pacienteBean,actualizaPaciente_CallBack);
	    }
	 }	

	 function actualizaPacienteExepcion() {		
		var frmPantalla = window.document.frmDatosOrdenFundacion;
		var strCorreoElectronico = trim(frmPantalla.txtCorreoElectronico.value);
		var intCodigoPaciente = frmPantalla.txtCodigoPaciente.value;
		if ((frmPantalla.hdlUpdatePaciente.value == "0") && (intCodigoPaciente > 0)) {		
			// No debe actualizar el Sistema si el usuario no a cambiado nada.
			return false;
	    } else {	
			if (strCorreoElectronico != "") {			
	    		if(!valEmail(frmPantalla.txtCorreoElectronico.value)){
	    			alert('La direcci&oacute;n de correo no es correcta, debe corregirla e intentar de nuevo.');
	    			return false;
	    		}
			}			
			LoadCompletedPaciente();
			showDatosPaciente(true);			
    		DatosPaciente.actualizaPaciente(pacienteBean,actualizaPaciente_CallBack);
	    }
	 }	
	 	 
	 function onChangeUpdatePaciente() {
		 var frmPantalla = window.document.frmDatosOrdenFundacion;
		 var strCodigoPaciente = trim(frmPantalla.txtCodigoPaciente.value);
		 if ((strCodigoPaciente != "") && (strCodigoPaciente != 0)) {
			 frmPantalla.hdlUpdatePaciente.value = "1";		 
		 } else {
			 frmPantalla.hdlUpdatePaciente.value = "0";		 
		 }
	 }
	 
     function actualizaPaciente_CallBack(data)
     {
	    var frmPantalla = window.document.frmDatosOrdenFundacion;
	    showDatosPaciente(false);
		document.getElementById('txtCodigoPaciente').className = 'textflat';
		txtCodigoPaciente = frmPantalla.txtCodigoPaciente;
		hdnkPaciente = frmPantalla.hdnkPaciente;		
		txtCodigoPaciente.disabled = true;
		txtCodigoPaciente.value = data.kpacientefundacion;
		hdnkPaciente.value = data.kpacientefundacion;		
		frmPantalla.hdnCodigoPostal.value = data.ccodigopostal;
		adminDIV("cotizaExamenes","visible","inline");
		adminDIV("cotizaExamenesSeccion","visible","inline");
	    DatosOrden.consultaOrdenesGrid(data.kpacientefundacion,0,muestraOrdenesGrid_CallBack); 
	    CotizacionOrdenes.consultaCotizacionesGrid(data.kpacientefundacion,muestraCotizacionesGrid_CallBack);
		frmPantalla.txtExamenesACotizar.focus();
		frmPantalla.hdlUpdatePaciente.value = "0";
		codeDIVHTML("gridbusquedaDireccion","");
		adminDIV("gridbusquedaDireccion","hidden","none");
	 	codeDIVHTML("gridbusquedaPacientes","");
	    adminDIV("gridbusquedaPacientes","hidden","none");
     }

     function muestraOrdenesGrid_CallBack(data) {
  		var frmPantalla = window.document.frmDatosOrdenFundacion;
	    adminDIV("gridbusquedaPacientes","visible","inline");
	 	codeDIVHTML("gridbusquedaPacientes",data[0]);
	 	frmPantalla.hdlNoOrdenes.value = data[1];
		if ((frmPantalla.hdlNoOrdenes.value > 1) || (frmPantalla.idMarca.value > 1)) {
			OnlyReaderNombrePaciente(true);
			setColorTextBox('white','#FF6600');
		} else {
			setColorTextBox('white','white');
		}
     }

     function OnlyReaderNombrePaciente(bolonlyReader) {
	    var frmPantalla = window.document.frmDatosOrdenFundacion;
	    if (bolonlyReader) {
			document.getElementById('txtCodigoPaciente').className = 'textflat';
			document.getElementById('txtApellidoPaterno').className = 'textflat';
			document.getElementById('txtApellidoMaterno').className = 'textflat';
			document.getElementById('txtNombre').className = 'textflat';
//			document.getElementById('txtFechaNacimiento').className = 'textflat';
		    frmPantalla.txtApellidoPaterno.disabled = true;
			frmPantalla.txtApellidoMaterno.disabled = true;
			frmPantalla.txtNombre.disabled = true;
			frmPantalla.txtFechaNacimiento.disabled = true;
			frmPantalla.txtCodigoPaciente.disabled = true;
	    } else {
			document.getElementById('txtApellidoPaterno').className = 'text';
			document.getElementById('txtApellidoMaterno').className = 'text';
			document.getElementById('txtNombre').className = 'text';
//			document.getElementById('txtFechaNacimiento').className = 'text';
		    frmPantalla.txtApellidoPaterno.disabled = false;
			frmPantalla.txtApellidoMaterno.disabled = false;
			frmPantalla.txtNombre.disabled = false;
			frmPantalla.txtFechaNacimiento.disabled = false;
	    }     
     }

     
     function OnlyReaderDatosDemograficos() {
 	    var frmPantalla = window.document.frmDatosOrdenFundacion;
 			document.getElementById('txtCodigoPaciente').className = 'textflat';
 			document.getElementById('txtApellidoPaterno').className = 'textflat';
 			document.getElementById('txtApellidoMaterno').className = 'textflat';
 			document.getElementById('txtNombre').className = 'textflat';
 			document.getElementById('txtTelefono').className = 'textflat';
			document.getElementById('txtCelular').className = 'textflat';
			document.getElementById('txtCorreoElectronico').className = 'textflat';
			document.getElementById('txtCalle').className = 'textflat';
			document.getElementById('txtColonia').className = 'textflat';
			document.getElementById('txtDelegacionMunicipio').className = 'textflat';
			document.getElementById('txtCodigoPostal').className = 'textflat';

// 			document.getElementById('txtFechaNacimiento').className = 'textflat';
 		    frmPantalla.txtApellidoPaterno.disabled = true;
 			frmPantalla.txtApellidoMaterno.disabled = true;
 			frmPantalla.txtNombre.disabled = true;
 			frmPantalla.txtFechaNacimiento.disabled = true;
 			frmPantalla.txtCodigoPaciente.disabled = true;

 		    frmPantalla.txtTelefono.disabled = true;
 		    frmPantalla.txtCelular.disabled = true;
 		    frmPantalla.txtCorreoElectronico.disabled = true;
 		    frmPantalla.txtCalle.disabled = true;
 		    frmPantalla.txtColonia.disabled = true;
 		    frmPantalla.txtDelegacionMunicipio.disabled = true;
 		    frmPantalla.txtCodigoPostal.disabled = true;

 		    frmPantalla.selEstado.disabled = true;
 		    frmPantalla.optResultado.disabled = true;
 		    frmPantalla.optPromocion.disabled = true;
 		    frmPantalla.radSexo[0].disabled = true;
 		    frmPantalla.radSexo[1].disabled = true;
     }
     
     function buscarPaciente() {		
 		var frmPantalla = window.document.frmDatosOrdenFundacion;
		if (frmPantalla.txtCodigoPaciente.value != "" && frmPantalla.txtCodigoPaciente.value > 0) {
			LoadBasicPaciente(true);						
		 	codeDIVHTML("gridbusquedaDireccion","");
		    adminDIV("gridbusquedaDireccion","hidden","none");
		 	codeDIVHTML("gridbusquedaPacientes","");
		    adminDIV("gridbusquedaPacientes","hidden","none"); 		
			DatosPaciente.buscarPaciente(pacienteBean,buscarPaciente_CallBack);
		} else {
			alert('Ingrese un codigo de Paciente, por favor');
		}
	 }	

     
     
     /*********************************Pacientes Mayoreo ******************************/
     
//     function buscarPacienteMetro() {		
//       	if (window.event && window.event.keyCode == 13) { 	  		    	 
//	  		var frmPantalla = window.document.frmDatosOrdenFundacion;
//	 		if ((document.getElementById('txtExpedienteMetro').value != "") && (frmPantalla.txtCodigoPaciente.value == "")) {
//	 			PacienteMayoreo.buscarPacienteMetro(document.getElementById('txtExpedienteMetro').value,buscarPacienteMetro_CallBack);
//	 		} else if ((document.getElementById('txtExpedienteMetro').value != "") && (frmPantalla.txtCodigoPaciente.value == "0")) {
//	 	 		PacienteMayoreo.buscarPacienteMetro(document.getElementById('txtExpedienteMetro').value,buscarPacienteMetro_CallBack);
//	 		} else if ((document.getElementById('txtExpedienteMetro').value != "") && (frmPantalla.txtCodigoPaciente.value != "0") && (frmPantalla.txtCodigoPaciente.value != "")) {
//				LoadCompletedPaciente();
//				showDatosPaciente(true);			
//	    		DatosPaciente.actualizaPaciente(pacienteBean,actualizaPaciente_CallBack);
//	    		document.getElementById('txtExpedienteMetro').className = 'textflat';
//	    		frmPantalla.txtExpedienteMetro.disabled = true;				    		
//	 		} else {
//	 			alert('Ingrese el Expediente del Metro por favor');
//	 		}
// 		}
// 	 }	

	function buscarPacienteMetro() {		
		if (window.event && window.event.keyCode == 13) { 	  		    	 
			var frmPantalla = window.document.frmDatosOrdenFundacion;		
			if ((document.getElementById('txtExpedienteMetro').value != "") && (frmPantalla.txtCodigoPaciente.value == "")) {
				if (document.getElementById('txtPaseMetro').value  != "") {
					buscarPacienteMetroWebService();
				} else {
					alert('Ingrese un n&uacute;mero de Pase del Metro, por favor');
				}
			} else if ((document.getElementById('txtExpedienteMetro').value != "") && (frmPantalla.txtCodigoPaciente.value == "0")) {
				if (document.getElementById('txtPaseMetro').value  != "") {
					buscarPacienteMetroWebService();				
				} else {
					alert('Ingrese un n&uacute;mero de Pase del Metro, por favor');
				}
			} else if ((document.getElementById('txtExpedienteMetro').value != "") && (frmPantalla.txtCodigoPaciente.value != "0") && (frmPantalla.txtCodigoPaciente.value != "")) {
				LoadCompletedPaciente();
				showDatosPaciente(true);			
				DatosPaciente.actualizaPaciente(pacienteBean,actualizaPaciente_CallBack);
				document.getElementById('txtExpedienteMetro').className = 'textflat';
				frmPantalla.txtExpedienteMetro.disabled = true;				    		
				document.getElementById('txtPaseMetro').className = 'textflat';
				frmPantalla.txtPaseMetro.disabled = true;				    		
			} else {
				alert('Ingrese el Expediente del Metro por favor');
			}
		}
	}	
     
	function buscarPacienteMetro_CallBack(data) {
		if (data.kpacientefundacion > 0) {
			buscarPaciente_CallBack(data);
		} else {
			alert('¿¡¡¡NO localizamos el n&uacute;mero del expediente (ES NUEVO el paciente O el paciente no tiene asignado su n&uacute;mero de expediente)!!!!');
		}
	}

	/**************Cambios OMRR 18092013 *************************/
	function buscarPacienteMetroWebService() {		
		if (window.event && window.event.keyCode == 13) { 	  		
			if (document.getElementById('txtPaseMetro').value  != "") {
				if (document.getElementById('txtExpedienteMetro').value != "") {
					DatosPaciente.buscarPacienteMetroWebService(document.getElementById('txtPaseMetro').value,buscarPacienteMetroWebService_CallBack);
				} else {
					alert('Ingrese el Expediente del Metro por favor');
				}
			} else {
				alert('Ingrese un n&uacute;mero de Pase del Metro, por favor');
			}
		}
	}	
      
     /**************Cambios OMRR 18092013 *************************/
     function buscarPacienteMetroWebService_CallBack(data) {
     	if (data.kpacientefundacion > 0) {
			buscarPaciente_CallBack(data);
    	} else {
			alert('¿¡¡¡El n&uacute;mero del pase no se encuentra registrado en el Metro!!!!');
			PacienteMayoreo.buscarPacienteMetro(document.getElementById('txtExpedienteMetro').value,buscarPacienteMetro_CallBack);    		
    	}
     }     
     /**********************************************************************************/
     
     function buscarPacienteVitaMedica() {		
    	if (window.event && window.event.keyCode == 13) {
	 		if (document.getElementById('txtElegibilidadVitaMedica').value != "") {
	 			LoadBasicPaciente(true);
	 			pacienteBean.svalorexpediente = document.getElementById('txtElegibilidadVitaMedica').value ;
	 		 	codeDIVHTML("gridbusquedaDireccion","");
	 		    adminDIV("gridbusquedaDireccion","hidden","none");
	 		 	codeDIVHTML("gridbusquedaPacientes","");
	 		    adminDIV("gridbusquedaPacientes","hidden","none"); 		
	 			DatosPaciente.buscarPacienteVitaMedica(pacienteBean,buscarPaciente_CallBack);
	 		} else {
	 			alert('Ingrese un n&uacute;mero de Elegibilidad, por favor');
	 		}
    	}
 	 }	

     
     function buscarPacienteECE(kPaciente) {		
		pacienteBean.kpacientefundacion=kPaciente;
		pacienteBean.snombre="";
		pacienteBean.sappaterno="";
		pacienteBean.sapmaterno="";
		pacienteBean.cconvenio = 0;
		screenBean.uTypeShow = 1;
 		DatosPaciente.buscarPaciente(pacienteBean,buscarPaciente_CallBack);
 	 }	
     
     function buscarPacienteECEEmpresa(kPaciente,cConvenio) {		
 		pacienteBean.kpacientefundacion=kPaciente;
 		pacienteBean.snombre="";
 		pacienteBean.sappaterno="";
 		pacienteBean.sapmaterno="";
 		pacienteBean.cconvenio = 0;
		pacienteBean.cmarca=0;
//		pacienteBean.cmarca=document.getElementById("idMarca").value;
 		screenBean.uTypeShow = 1;
 		screenBean.uConsultaECEConvenio = cConvenio;
  		DatosPaciente.buscarPaciente(pacienteBean,buscarPaciente_CallBack);
  	 }	
     

     /**************Cambios OMRR 18092013 *************************/
     function buscarPaciente_CallBack(data) {
		var frmPantalla = window.document.frmDatosOrdenFundacion;
		var intTypeShow = frmPantalla.hdlTypeShow.value;
		frmPantalla.hdlUpdatePaciente.value = "0";		
		if (data.kpacientefundacion > 0) {
			document.getElementById('txtCodigoPaciente').className = 'textflat';
			frmPantalla.txtCodigoPaciente.disabled = true;
		}
		frmPantalla.txtCodigoPaciente.value = data.kpacientefundacion;
		frmPantalla.hdnkPaciente.value = data.kpacientefundacion;
		frmPantalla.txtApellidoPaterno.value = data.sappaterno;
		frmPantalla.txtApellidoMaterno.value = data.sapmaterno;		
		frmPantalla.txtNombre.value = data.snombre;
		if (pacienteBean.utipopaciente == 1) {
			frmPantalla.optCapacidadDiferente.checked == true;
		} else {
			frmPantalla.optCapacidadDiferente.checked == false;
		}
		
		if (data.scorreoelectronico != "") {
			frmPantalla.txtCorreoElectronico.value = data.scorreoelectronico;
			if (data.kpacientefundacion > 0) {
				frmPantalla.txtCorreoElectronico.disabled = true;
			}
			if (data.uopcionenviocorreo == 5) {
			    frmPantalla.optResultado.checked = false;
			    frmPantalla.optPromocion.checked = true;
			} else if (data.uopcionenviocorreo == 100) {
			    frmPantalla.optResultado.checked = true;
			    frmPantalla.optPromocion.checked = false;			    
			    document.getElementById('idTextEnvioInternet').innerHTML = "Resultados, Factura y Expediente Clinico ";
			    document.getElementById('idTextEnvioInternet').style.color  = "black";
			    if (intTypeShow == 0) {
				    document.getElementById('idTextCorreoElectronico').innerHTML = "MODIFICAR CORREO";
				    document.getElementById('idTextCorreoElectronico').style.color  = "blue";			
				    document.getElementById('idTextResetPassword').innerHTML = " Reset Pass";
				    document.getElementById('idTextResetPassword').style.color  = "black";
			    } else {
				    document.getElementById('idTextCorreoElectronico').innerHTML = "Correo Electronico";
				    document.getElementById('idTextCorreoElectronico').style.color  = "black";			
				    document.getElementById('idTextResetPassword').innerHTML = "";
			    }
			} else if (data.uopcionenviocorreo == 105) {
			    frmPantalla.optResultado.checked = true;
			    frmPantalla.optPromocion.checked = true;
			    document.getElementById('idTextEnvioInternet').innerHTML = "Resultados, Factura y Expediente Clinico ";
			    document.getElementById('idTextEnvioInternet').style.color  = "black";
			    if (intTypeShow == 0) {
				    document.getElementById('idTextCorreoElectronico').innerHTML = "MODIFICAR CORREO";
				    document.getElementById('idTextCorreoElectronico').style.color  = "blue";			
				    document.getElementById('idTextResetPassword').innerHTML = " Reset Pass";
				    document.getElementById('idTextResetPassword').style.color  = "black";
			    } else {
				    document.getElementById('idTextCorreoElectronico').innerHTML = "Correo Electronico";
				    document.getElementById('idTextCorreoElectronico').style.color  = "black";			
				    document.getElementById('idTextResetPassword').innerHTML = "";
			    }
			} else if (data.uopcionenviocorreo == 200) {
			    frmPantalla.optResultado.checked = false;
			    frmPantalla.optPromocion.checked = false;
			    document.getElementById('idTextEnvioInternet').innerHTML = "NO ENVIAR NOTIFICACIONES";
			    document.getElementById('idTextEnvioInternet').style.color  = "red";
			    if (intTypeShow == 0) {
				    document.getElementById('idTextCorreoElectronico').innerHTML = "MODIFICAR CORREO";
				    document.getElementById('idTextCorreoElectronico').style.color  = "blue";			
				    document.getElementById('idTextResetPassword').innerHTML = "";
			    } else {
				    document.getElementById('idTextCorreoElectronico').innerHTML = "Correo Electronico";
				    document.getElementById('idTextCorreoElectronico').style.color  = "black";			
				    document.getElementById('idTextResetPassword').innerHTML = "";
			    }			    
			} else if (data.uopcionenviocorreo == 205) {
			    frmPantalla.optResultado.checked = false;
			    frmPantalla.optPromocion.checked = true;
			    document.getElementById('idTextEnvioInternet').innerHTML = "NO ENVIAR NOTIFICACIONES";
			    document.getElementById('idTextEnvioInternet').style.color  = "red";
			    if (intTypeShow == 0) {
				    document.getElementById('idTextCorreoElectronico').innerHTML = "MODIFICAR CORREO";
				    document.getElementById('idTextCorreoElectronico').style.color  = "blue";			
				    document.getElementById('idTextResetPassword').innerHTML = "";
			    } else {
				    document.getElementById('idTextCorreoElectronico').innerHTML = "Correo Electronico";
				    document.getElementById('idTextCorreoElectronico').style.color  = "black";			
				    document.getElementById('idTextResetPassword').innerHTML = "";
			    }
			}
		} else {
    		frmPantalla.txtCorreoElectronico.value = "";
    		if (data.kpacientefundacion > 0) {    		
    			frmPantalla.txtCorreoElectronico.disabled = true;
    		}
		    frmPantalla.optResultado.checked = false;
		    frmPantalla.optPromocion.checked = false;
		}
		frmPantalla.txtFechaNacimiento.value = data.snacimiento;
		frmPantalla.hdnCodigoPostal.value = data.ccodigopostal;
		frmPantalla.txtCodigoPostal.value = data.scodigopostal;
		frmPantalla.txtColonia.value = data.scolonia;
		frmPantalla.txtDelegacionMunicipio.value =  data.sdelegmuni;
//		frmPantalla.txtCiudad.value = data.sciudad;
		frmPantalla.txtCalle.value = data.sdireccion;
		frmPantalla.txtTelefono.value = data.stelefono;		
		frmPantalla.txtCelular.value = data.scelular;		
		if (data.kpacientefundacion > 0) {
			if (screenBean.uTypeShow == 0) {
				validacionesPacienteMayoreo(data);
		 		frmPantalla.txtExpedienteMetro.value = data.svalorexpediente;
				document.getElementById('txtExpedienteMetro').className = 'textflat';
				frmPantalla.txtExpedienteMetro.disabled = true;				
				document.getElementById('txtPaseMetro').className = 'textflat';
				frmPantalla.txtPaseMetro.disabled = true;				    		
				document.getElementById('txtElegibilidadVitaMedica').className = 'textflat';
				frmPantalla.txtElegibilidadVitaMedica.disabled = true;				    		
			}
		}
		if (data.csexo == 0) {
			frmPantalla.radSexo[0].checked=true;
		} else if (data.csexo == 1)  {
			frmPantalla.radSexo[1].checked=true;			
		}				
		obtenerEdadfrm(window.document.frmDatosOrdenFundacion);				
//		if ((screenBean.uTypeShow == 0) && (data.kpacientefundacion > 0)) {		
		if (screenBean.uTypeShow == 0) {		
			adminDIV("cotizaExamenes","visible","inline");
			adminDIV("cotizaExamenesSeccion","visible","inline");
			codeDIVHTML("gridbusquedaDireccion","");
			adminDIV("gridbusquedaDireccion","hidden","none");
		 	codeDIVHTML("gridbusquedaPacientes","");
		    adminDIV("gridbusquedaPacientes","hidden","none");
		}
		frmPantalla.hdlUpdatePaciente.value = "0";		
		if (data.kpacientefundacion > 0) {
			DatosOrden.consultaOrdenesGrid(data.kpacientefundacion,screenBean.uConsultaECEConvenio,muestraOrdenesGrid_CallBack); 
		    CotizacionOrdenes.consultaCotizacionesGrid(data.kpacientefundacion,muestraCotizacionesGrid_CallBack);
		}
		if (data.bvitamedica > 0) {
			buscarConvenioRapido(window.document.frmDatosOrdenFundacion.selConvenios,data.cconvenio);
		    adminDIV("RegistroMedico","visible","inline");					
			frmPantalla.txtNombreMedico.value = data.snombremedico;
			frmPantalla.txtApellidoPaternoMedico.value = data.sapellidopaternomedico;
			frmPantalla.txtApellidoMaternoMedico.value = data.sapellidomaternomedico;
			consultaMedicoGrid(window.document.frmDatosOrdenFundacion);
			frmPantalla.txtExamenesACotizar.value = data.sCPT;
			if (data.smensagevitamedica != "") {
				alert(data.smensagevitamedica);
			}
			if (data.bvitamedica == 2) {
	    		DatosExamen.newExamen(data.sCPT,"","",window.document.frmDatosOrdenFundacion.IdSucursalActual.value,data.cconvenio,RefreshGrid_CallBack);
			} else {
	    		DatosExamen.newExamen(data.sCPT,"","0",window.document.frmDatosOrdenFundacion.IdSucursalActual.value,data.cconvenio,RefreshGrid_CallBack);
			}
		}		
	}	 	 
	    	
	function consultaPacienteGrid() {		
        var frmPantalla = window.document.frmDatosOrdenFundacion;
        if (frmPantalla.hdnkPaciente.value < 1) {
			txtApellidoPaterno = frmPantalla.txtApellidoPaterno;
			txtApellidoMaterno = frmPantalla.txtApellidoMaterno;
			txtNombre = frmPantalla.txtNombre;		 
			if (txtApellidoMaterno.value.length < 4  && txtApellidoPaterno.value.length < 4  && txtNombre.value.length < 4) {
			 	codeDIVHTML("gridbusquedaPacientes","");
			    adminDIV("gridbusquedaPacientes","hidden","none");
			} else {
				LoadBasicPaciente(false);
				frmPantalla.hdlUpdatePaciente.value = "0";
	    		frmPantalla.txtCodigoPaciente.value = "";
//	    		frmPantalla.txtExpedienteMetro.value = "";
				document.getElementById('txtExpedienteMetro').className = 'textflat';
				frmPantalla.txtExpedienteMetro.disabled = true;			
				document.getElementById('txtPaseMetro').className = 'textflat';
				frmPantalla.txtPaseMetro.disabled = true;				    						
				document.getElementById('txtElegibilidadVitaMedica').className = 'textflat';
				frmPantalla.txtElegibilidadVitaMedica.disabled = true;				    		
				
				DatosPaciente.consultaPacienteGrid(pacienteBean,consultaPacienteGrid_CallBack);
			}
		}
	}	
			
    function consultaPacienteGrid_CallBack(data)
    {
	    adminDIV("gridbusquedaPacientes","visible","inline");
	 	codeDIVHTML("gridbusquedaPacientes",data);
	}	 
	    
	function registroAceptado(registro){
        var frmPantalla = window.document.frmDatosOrdenFundacion;
	 	frmPantalla.txtCodigoPaciente.value = registro;
	 	frmPantalla.hdnkPaciente.value = registro;		
	 	buscarPaciente();		 	
	 	codeDIVHTML("gridbusquedaPacientes","");
	    adminDIV("gridbusquedaPacientes","hidden","none");
		codeDIVHTML("gridbusquedaDireccion","");
		adminDIV("gridbusquedaDireccion","hidden","none");
	}	 
		 
	function showDatosPaciente(bolSelect) {
	    var frmPantalla = window.document.frmDatosOrdenFundacion;
		frmPantalla.txtApellidoPaterno.disabled = bolSelect;
		frmPantalla.txtApellidoMaterno.disabled = bolSelect;
		frmPantalla.txtNombre.disabled = bolSelect;
		frmPantalla.txtFechaNacimiento.disabled = bolSelect;
		frmPantalla.txtTelefono.disabled = bolSelect;
		frmPantalla.txtCelular.disabled = bolSelect;
		frmPantalla.txtCalle.disabled = bolSelect;
		frmPantalla.txtColonia.disabled = bolSelect;
		frmPantalla.txtDelegacionMunicipio.disabled = bolSelect;
		frmPantalla.txtCodigoPostal.disabled = bolSelect;
	}
	 
	function LoadBasicPaciente(bolCode) {
		var frmPantalla = window.document.frmDatosOrdenFundacion;
		if (bolCode == true) {
			pacienteBean.kpacientefundacion=frmPantalla.txtCodigoPaciente.value;
		}
		pacienteBean.snombre=frmPantalla.txtNombre.value;
		pacienteBean.sappaterno=frmPantalla.txtApellidoPaterno.value;
		pacienteBean.sapmaterno=frmPantalla.txtApellidoMaterno.value;
		pacienteBean.cmarca=frmPantalla.idMarca.value;
        var intRol = frmPantalla.idRol.value;
        if (intRol == 330) {
    		pacienteBean.csucursal=10000;
        } else {
    		pacienteBean.csucursal=frmPantalla.IdSucursalActual.value;
        }		
		pacienteBean.csucursal=frmPantalla.IdSucursalActual.value;
		pacienteBean.cconvenio = 0;
	}

	
	function LoadCompletedPaciente() {
		var frmPantalla = window.document.frmDatosOrdenFundacion;		
		pacienteBean.kpacientefundacion=frmPantalla.hdnkPaciente.value;
		pacienteBean.snombre=frmPantalla.txtNombre.value;
		pacienteBean.sappaterno=frmPantalla.txtApellidoPaterno.value;
		pacienteBean.sapmaterno=frmPantalla.txtApellidoMaterno.value;
		pacienteBean.snacimiento=frmPantalla.txtFechaNacimiento.value;
		pacienteBean.sdireccion=frmPantalla.txtCalle.value;
		pacienteBean.scolonia=frmPantalla.txtColonia.value;
		pacienteBean.sdelegmuni=frmPantalla.txtDelegacionMunicipio.value;
		pacienteBean.ccodigopostal = frmPantalla.hdnCodigoPostal.value;		
		pacienteBean.scodigopostal=frmPantalla.txtCodigoPostal.value;
		pacienteBean.stelefono=frmPantalla.txtTelefono.value;
		pacienteBean.scelular=frmPantalla.txtCelular.value;
		if (frmPantalla.optCapacidadDiferente.checked == true) {
			pacienteBean.utipopaciente = 1;
		} else {
			pacienteBean.utipopaciente = 0;
		}
		var intOpcionesEnvio = 0;
		if ((frmPantalla.optResultado.checked == true) || (document.getElementById('idTextEnvioInternet').innerHTML == "NO ENVIAR NOTIFICACIONES")) {
			intOpcionesEnvio = (intOpcionesEnvio + parseInt(frmPantalla.optResultado.value));
		}	
		if (frmPantalla.optPromocion.checked == true) {
			intOpcionesEnvio = (intOpcionesEnvio + parseInt(frmPantalla.optPromocion.value));
		}
		pacienteBean.uopcionenviocorreo = intOpcionesEnvio;
		if (intOpcionesEnvio == 0) {
			frmPantalla.txtCorreoElectronico.value = "";
			pacienteBean.scorreoelectronico="";
		} else {
			pacienteBean.scorreoelectronico=frmPantalla.txtCorreoElectronico.value;
		}
//		pacienteBean.sciudad=frmPantalla.txtCiudad.value;
		pacienteBean.cusuario=frmPantalla.idUsuario.value;
		pacienteBean.cmarca=frmPantalla.idMarca.value;
		if (TypeObjeto(frmPantalla.radSexo[0]) == true) {
			intSexo = 0;
		} else {
			intSexo = 1;
		}		
		pacienteBean.csexo=intSexo;
		pacienteBean.cconvenio = 0;
		pacienteBean.svalorexpediente=frmPantalla.txtExpedienteMetro.value;
	}

	function LoadCotizacionPaciente() {
		var frmPantalla = window.document.frmDatosOrdenFundacion;		
		pacienteBean.kpacientefundacion=frmPantalla.hdnkPaciente.value;
		pacienteBean.snombre=frmPantalla.txtNombre.value;
		pacienteBean.sappaterno=frmPantalla.txtApellidoPaterno.value;
		pacienteBean.sapmaterno=frmPantalla.txtApellidoMaterno.value;
		pacienteBean.snacimiento=frmPantalla.txtFechaNacimiento.value;
		pacienteBean.sdireccion=frmPantalla.txtCalle.value;
		pacienteBean.scolonia=frmPantalla.txtColonia.value;
		pacienteBean.sdelegmuni=frmPantalla.txtDelegacionMunicipio.value;
		pacienteBean.ccodigopostal = frmPantalla.hdnCodigoPostal.value;		
		pacienteBean.scodigopostal=frmPantalla.txtCodigoPostal.value;
		pacienteBean.stelefono=frmPantalla.txtTelefono.value;
		pacienteBean.scelular=frmPantalla.txtCelular.value;
		if (frmPantalla.optCapacidadDiferente.checked == true) {
			pacienteBean.utipopaciente = 1;
		} else {
			pacienteBean.utipopaciente = 0;
		}
		var intOpcionesEnvio = 0;
		if ((frmPantalla.optResultado.checked == true) || (document.getElementById('idTextEnvioInternet').innerHTML == "NO ENVIAR NOTIFICACIONES")) {
			intOpcionesEnvio = (intOpcionesEnvio + parseInt(frmPantalla.optResultado.value));
		}	
		if (frmPantalla.optPromocion.checked == true) {
			intOpcionesEnvio = (intOpcionesEnvio + parseInt(frmPantalla.optPromocion.value));
		}
		pacienteBean.uopcionenviocorreo = intOpcionesEnvio;
		if (intOpcionesEnvio == 0) {
			frmPantalla.txtCorreoElectronico.value = "";
			pacienteBean.scorreoelectronico="";
		} else {
			pacienteBean.scorreoelectronico=frmPantalla.txtCorreoElectronico.value;
		}
//		pacienteBean.sciudad=frmPantalla.txtCiudad.value;
		pacienteBean.cusuario=frmPantalla.idUsuario.value;
		pacienteBean.cmarca=frmPantalla.idMarca.value;
		if (TypeObjeto(frmPantalla.radSexo[0]) == true) {
			intSexo = 0;
		} else {
			intSexo = 1;
		}		
		pacienteBean.csexo=intSexo;
		pacienteBean.cconvenio = 0;
		pacienteBean.svalorexpediente=frmPantalla.txtExpedienteMetro.value;
	}
	
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
		cmarca=null,
		csucursal=null,
		csexo=null,
		cconvenio=null,
		svalorexpediente=null,
		uopcionenviocorreo=null,
		scelular=null,
		utipopaciente=null
	}	

	function ScreenBean() {	
		uTypeShow=null,
		uConsultaECEConvenio=null
	}	
	
	function validacionesPacienteMayoreo(data) {
		var frmPantalla = window.document.frmDatosOrdenFundacion;
		/************** Start Validacion Metro *************************/
		if (data.svalorexpediente != "") {
    		frmPantalla.txtExpedienteMetro.value = data.svalorexpediente;
			document.getElementById('txtExpedienteMetro').className = 'textflat';
			frmPantalla.txtExpedienteMetro.disabled = true;			
			document.getElementById('txtPaseMetro').className = 'textflat';
			frmPantalla.txtPaseMetro.disabled = true;				    					
		} else {
    		frmPantalla.txtExpedienteMetro.value = "";
			document.getElementById('txtExpedienteMetro').className = 'text';
			frmPantalla.txtExpedienteMetro.disabled = false;			
    		frmPantalla.txtPaseMetro.value = "";
			document.getElementById('txtPaseMetro').className = 'text';
			frmPantalla.txtPaseMetro.disabled = false;			
		}		
		/************** End Validacion Metro *************************/
	}
	
	
	function onCorreoElectronico(opClick) {
		var frmPantalla = window.document.frmDatosOrdenFundacion;
		if ((opClick.name == "optResultado") && (opClick.checked == false) && (frmPantalla.txtCorreoElectronico.value.length > 3)){
		    document.getElementById('idTextEnvioInternet').innerHTML = "NO ENVIAR NOTIFICACIONES";
		    document.getElementById('idTextEnvioInternet').style.color  = "red";
		    document.getElementById('idTextCorreoElectronico').innerHTML = "MODIFICAR CORREO";
		    document.getElementById('idTextCorreoElectronico').style.color  = "blue";
			frmPantalla.txtCorreoElectronico.disabled = true;			
			opClick.value = 200;
			VerificaModificacion_ClickCorreoElectronico();
		} else 	if ((opClick.name == "optResultado") && (opClick.checked == true) && (frmPantalla.txtCorreoElectronico.value.length > 3)){
		    document.getElementById('idTextEnvioInternet').innerHTML = "Resultados, Factura y Expediente Clinico ";
		    document.getElementById('idTextEnvioInternet').style.color  = "black";
		    document.getElementById('idTextCorreoElectronico').innerHTML = "MODIFICAR CORREO";
		    document.getElementById('idTextCorreoElectronico').style.color  = "blue";		    
		    frmPantalla.txtCorreoElectronico.disabled = true;			
			opClick.value = 100;
			VerificaModificacion_ClickCorreoElectronico();
		} else {
			if (document.getElementById('idTextEnvioInternet').innerHTML != "NO ENVIAR NOTIFICACIONES") {
				if (((frmPantalla.optResultado.checked == true) || (frmPantalla.optPromocion.checked == true)) && (frmPantalla.txtCorreoElectronico.value.length < 3)) {
					frmPantalla.txtCorreoElectronico.disabled = false;			
					frmPantalla.txtCorreoElectronico.value = "";
					frmPantalla.txtCorreoElectronico.focus();
				} else if ((frmPantalla.optResultado.checked == false) && (frmPantalla.optPromocion.checked == false)) {
					frmPantalla.txtCorreoElectronico.value = "";
					frmPantalla.txtCorreoElectronico.disabled = true;			
				}
			}
		}
	}
	
	function modifyCorreoElectronico() {
		var frmPantalla = window.document.frmDatosOrdenFundacion;
		var intTypeShow = frmPantalla.hdlTypeShow.value;
		if (((frmPantalla.optResultado.checked == true) || (frmPantalla.optPromocion.checked == true)) && (frmPantalla.txtCorreoElectronico.value.length > 7) && (intTypeShow == 0)) {
			var r=confirm("¿Quieres modificar el Correo Electronico?");
			if (r==true) {
				frmPantalla.txtCorreoElectronico.disabled = false;			
			}			
		}
	}
	
	function resetPassword() {
		var frmPantalla = window.document.frmDatosOrdenFundacion;
		if (((frmPantalla.optResultado.checked == true) || (frmPantalla.optPromocion.checked == true)) && (frmPantalla.txtCorreoElectronico.value.length > 7)) {
			var r=confirm("¿Quieres un NUEVO password para el Expediente Clinico Electronico para el paciente " + frmPantalla.txtCodigoPaciente.value + "?");
			if (r==true) {
			    document.getElementById('idTextEnvioInternet').innerHTML = "Resultados, Factura y Expediente Clinico ";
			    document.getElementById('idTextEnvioInternet').style.color  = "black";
			    document.getElementById('idTextCorreoElectronico').innerHTML = "MODIFICAR CORREO";
			    document.getElementById('idTextCorreoElectronico').style.color  = "blue";		    
			    LoadBasicPaciente(true);
	    		DatosPaciente.ressetPasswordECE(pacienteBean,ressetPasswordECE_CallBack);			    
			}								
		}
	}
	
	
	function ressetPasswordECE_CallBack(data) {
		alert(data);
	}
	
    function muestraCotizacionesGrid_CallBack(data) {
	    adminDIV("gridshowCotizacionesPaciente","visible","inline");
	 	codeDIVHTML("gridshowCotizacionesPaciente",data[0]);
     }
	