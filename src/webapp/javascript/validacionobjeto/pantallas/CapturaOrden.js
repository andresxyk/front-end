	function onChangeTipoOperacion() {
        var frmPantalla = document.getElementById('frmDatosOrdenFundacion');
		if (TypeObjeto(frmPantalla.radTipo[0])) { 		
//			document.getElementById('idGuardarNuevaOrden').disabled = false;
//			document.getElementById('idGuardarNuevaCotizacion').disabled = true;
		} else {
//			document.getElementById('idGuardarNuevaOrden').disabled = true;
//			document.getElementById('idGuardarNuevaCotizacion').disabled = false;
		}
	}

	function buscarConvenioRapido(objConvenio,strBuscar) {
        var frmPantalla = window.document.frmDatosOrdenFundacion;		
		if ((strBuscar.length == 9) && (validaSoloNumeros(strBuscar))) {
				var strPromocion = strBuscar.substring(0,4);
				var strMedico = strBuscar.substring(4);
				medicoAceptadoClave(strMedico);
		        frmPantalla.idLimpiarMedico.disabled = true;
				if (strPromocion.substring(0,1) == "0") {
					buscaTextoSelect(objConvenio,strPromocion.substring(1));
				} else {
					buscaTextoSelect(objConvenio,strPromocion);
				}
		} else {
			buscaTextoSelect(objConvenio,strBuscar);
 		}
	}
	
	function validaCamposFundacion() {
		txtCodigoPaciente = window.document.frmDatosOrdenFundacion.txtCodigoPaciente;
		txtApellidoPaterno = window.document.frmDatosOrdenFundacion.txtApellidoPaterno;
		txtApellidoMaterno = window.document.frmDatosOrdenFundacion.txtApellidoMaterno;
		txtNombre = window.document.frmDatosOrdenFundacion.txtNombre;
		txtFechaNacimiento = window.document.frmDatosOrdenFundacion.txtFechaNacimiento;
		txtTelefono = window.document.frmDatosOrdenFundacion.txtTelefono;
		txtCalle = window.document.frmDatosOrdenFundacion.txtCalle;
		txtColonia = window.document.frmDatosOrdenFundacion.txtColonia;
		txtDelegacionMunicipio = window.document.frmDatosOrdenFundacion.txtDelegacionMunicipio;
		txtCodigoPostal = window.document.frmDatosOrdenFundacion.txtCodigoPostal;
		txtAdmision     = window.document.frmDatosOrdenFundacion.txtAdmision    ;
		txtOrden = window.document.frmDatosOrdenFundacion.txtOrden;
		txtFechaEntrega = window.document.frmDatosOrdenFundacion.txtFechaEntrega;
		chkEntregaResultados = window.document.frmDatosOrdenFundacion.chkEntregaResultados;
		txtTotalPagar = window.document.frmDatosOrdenFundacion.txtTotalPagar;
		txtAcuenta = window.document.frmDatosOrdenFundacion.txtAcuenta;
		txtAdeudo  = window.document.frmDatosOrdenFundacion.txtAdeudo ;
		txtPago = window.document.frmDatosOrdenFundacion.txtPago;
		txtDineroRecibido = window.document.frmDatosOrdenFundacion.txtDineroRecibido;
		txtCambio = window.document.frmDatosOrdenFundacion.txtCambio;

	    if( !validaVacios(txtApellidoPaterno.value) ) {
	    	alert("El Apellido paterno del paciente es un dato obligatorio.");
	    	txtApellidoPaterno.focus();
	    	return false;
	    }
	    else if( !validaTextos(txtApellidoPaterno.value) ) {
	    	alert("El Apellido paterno contiene caracteres invalidos.");
	    	txtApellidoPaterno.focus();
	    	return false;
	    }
	    else if( !validaTextos(txtApellidoPaterno.value) ) {
	    	alert("El Apellido paterno contiene caracteres invalidos.");
	    	txtApellidoPaterno.focus();
	    	return false;
	    }	 
	    else if( !validaVacios(txtApellidoMaterno.value) ) {
	    	alert("El Apellido materno del paciente es un dato obligatorio.");
	    	txtApellidoMaterno.focus();
	    	return false;	    
	    }
	    else if( !validaTextos(txtApellidoMaterno.value) ) {
	    	alert("El Apellido materno contiene caracteres invalidos.");
	    	txtApellidoMaterno.focus();
	    	return false;
	    }	 	       	    
	    else if( !validaVacios(txtNombre.value) ) {
	    	alert("El Nombre del paciente es un dato obligatorio.");
	    	txtNombre.focus();
	    	return false;	    
	    }
	    else if( !validaTextos(txtNombre.value) ) {
	    	alert("El Nombre del paciente contiene caracteres invalidos.");
	    	txtNombre.focus();
	    	return false;
	    }	    
	    else if( !validaVacios(txtCalle.value) ) {
	    	alert("La Calle del paciente es un dato obligatorio.");
	    	txtCalle.focus();
	    	return false;	    
	    }
	    else if( !validaVacios(txtColonia.value) ) {
	    	alert("La Colonia del paciente es un dato obligatorio.");
	    	txtColonia.focus();
	    	return false;	    
	    }
	    else if( !validaVacios(txtDelegacionMunicipio.value) ) {
	    	alert("La Delegacion del paciente es un dato obligatorio.");
	    	txtDelegacionMunicipio.focus();
	    	return false;	    
	    }
	    else if( !validaVacios(txtCodigoPostal.value) ) {
	    	alert("El Codigo Postal del paciente es un dato obligatorio.");
	    	txtCodigoPostal.focus();
	    	return false;	    
	    }
	    else if( !validaVacios(txtFechaNacimiento.value) ) {
	    	alert("La Fecha de nacimiento del paciente es un dato obligatorio.");
	    	txtFechaNacimiento.focus();
	    	return false;	   
	    } 
	    else if( !validaVacios(txtTelefono.value) ) {
	    	alert("El Numero telefonico del paciente es un dato obligatorio.");
	    	txtTelefono.focus();
	    	return false;	   
	    } 
	    else if( !validaTelefono(txtTelefono.value) ) {
	    	alert("El Numero telefonico del paciente contiene caracteres invalidos.");
	    	txtTelefono.focus();
	    	return false;	    
	    }	 	    
		return true;	
	}

//	function eliminarElemento(id){
//		imagen = document.getElementById(id);
//		if (!imagen){
////			alert("El elemento selecionado no existe");
//		} else {
//			padre = imagen.parentNode;
//			padre.removeChild(imagen);
//		}
//	}	
	
	function consultaExpedienteClinico() {
		eliminarElemento('buttonNuevaOrden');
		eliminarElemento('idTypeBusqueda');
		eliminarElemento('radTipo');
		eliminarElemento('videoPaciente');
//		eliminarElemento('idGuardarNuevaOrden');
//		eliminarElemento('idGuardarNuevaCotizacion');
		eliminarElemento('idImprimeEtiquetas');
//		eliminarElemento('idImprimeRecibo');
		eliminarElemento('idLimpiarMedico');
		eliminarElemento('idGuardarNuevoMedico');
		eliminarElemento('imgFechaNaciemiento');
		eliminarElemento('buscarExamenesACotizar');
		eliminarElemento('indicacionesExamen');
		eliminarElemento('Grilla');
		eliminarElemento('Medico');
		eliminarElemento('pagoOrden');
		eliminarElemento('cotizaExamenes');
		eliminarElemento('RegistraPago');
		eliminarElemento('RegistraUsuario');		
		eliminarElemento('gridToolEmpresas');
		eliminarElemento('gridbusquedaPacientesEmpresas');		
//		htmlOb=document.getElementById("gridbusquedaPacientes");
//		htmlOb.innerHTML="";
		OnlyReaderDatosDemograficos();
		eliminarElemento('imgFechaNacimiento');		
   		window.document.frmDatosOrdenFundacion.hdlTypeShow.value = "1";		   		
	}
	
	function limpiaPantalla() {
        var frmPantalla = window.document.frmDatosOrdenFundacion;		
		setColorTextBox('white','white');
		OnlyReaderNombrePaciente(false);
		desactivarBuscadorConvenios();
		frmPantalla.hdlUpdatePaciente.value = "0";
		frmPantalla.hdlCotizacionUtilizado.value = "0";
        frmPantalla.txtCodigoPaciente.value = "0";
        frmPantalla.txtExpedienteMetro.value = "0";
        frmPantalla.txtApellidoPaterno.value = "";
		frmPantalla.txtApellidoPaterno.focus();
        frmPantalla.txtApellidoMaterno.value = "";
        frmPantalla.txtNombre.value = "";
        frmPantalla.txtFechaNacimiento.value = "";
        frmPantalla.txtTelefono.value = "";
        frmPantalla.txtCalle.value = "";
        frmPantalla.txtColonia.value = "";
        frmPantalla.txtDelegacionMunicipio.value = "";
//        frmPantalla.txtCiudad.value = "";		
		frmPantalla.hdnCodigoPostal.value = "0";
        frmPantalla.txtCodigoPostal.value = "";
        frmPantalla.txtAdmision.value = "";
        frmPantalla.txtOrden.value = "";
        frmPantalla.txtFechaEntrega.value = "";
        frmPantalla.txtCorreoElectronico.value = "";		
	    frmPantalla.optResultado.checked = false;
	    frmPantalla.optPromocion.checked = false;        
        frmPantalla.chkEntregaResultados.check;
        frmPantalla.txtTotalPagar.value = "";
        frmPantalla.txtAcuenta.value = "";
        frmPantalla.txtAdeudo.value = "" ;
        frmPantalla.txtPago.value = "";
        frmPantalla.txtObservaciones.value = "" ;
        frmPantalla.idImprimeEtiquetas.disable = true;
   		frmPantalla.hdnkMedico.value = "0";		
   		frmPantalla.hdlNoOrdenes.value = "0";		
   		codigoBean(frmPantalla.txtCodigoMedico,false,"0");		
   		codigoBean(frmPantalla.txtExpedienteMetro,false,"");		
   		codigoBean(frmPantalla.txtPaseMetro,false,"");		
   		codigoBean(frmPantalla.txtApellidoPaternoMedico,false,"");		
   		codigoBean(frmPantalla.txtApellidoMaternoMedico,false,"");		
   		codigoBean(frmPantalla.txtNombreMedico,false,"");		
   		codigoBean(frmPantalla.txtCorreoElectronicoMedico,false,"");
   		codigoBean(frmPantalla.txtTelefono,false,"");
   		codigoBean(frmPantalla.txtCelular,false,"");
   		frmPantalla.txtExamenesACotizar.value = "";		
        frmPantalla.radSexo[0].checked = false;
        frmPantalla.radSexo[1].checked = false;

        frmPantalla.radCorreoElectronicoMedico[0].checked = true;
        frmPantalla.radCorreoElectronicoMedico[1].checked = false;

        frmPantalla.radAQuienCorresponda[0].checked = false;
        frmPantalla.radAQuienCorresponda[1].checked = false;
        frmPantalla.radAQuienCorresponda[2].checked = false;
        frmPantalla.radAQuienCorresponda[3].checked = false;

        frmPantalla.radAQuienCorresponda[0].disabled = false;
        frmPantalla.radAQuienCorresponda[1].disabled = false;
        frmPantalla.radAQuienCorresponda[2].disabled = false;
        frmPantalla.radAQuienCorresponda[3].disabled = false;
        
	    adminDIV("RegistroMedico","hidden","none");					
        
        //		window.document.frmDatosOrdenFundacion.txtDineroRecibido.value = "";
//		window.document.frmDatosOrdenFundacion.txtCambio.value = "";
        frmPantalla.txtEdadDescompuesta.value = "";
        frmPantalla.hdnkPaciente.value = "";		
        frmPantalla.hdnExamenesCotizados.value = "";		
		document.getElementById('txtCodigoPaciente').className = 'text';
        frmPantalla.txtCodigoPaciente.disabled = false;
        frmPantalla.txtCodigoPaciente.disabled = false;
		document.getElementById('txtCodigoPaciente').className = 'text';
        frmPantalla.txtExpedienteMetro.disabled = false;
		document.getElementById('txtExpedienteMetro').className = 'text';
		document.getElementById('txtPaseMetro').className = 'text';
		frmPantalla.txtPaseMetro.disabled = false;			
		document.getElementById('txtElegibilidadVitaMedica').value = "";
		document.getElementById('txtElegibilidadVitaMedica').className = 'text';
		frmPantalla.txtElegibilidadVitaMedica.disabled = false;				    		
		
		
	    document.getElementById('idTextResetPassword').innerHTML = "";
	    document.getElementById('idTextResetPassword').style.color  = "black";
	    document.getElementById('idTextCorreoElectronico').innerHTML = "Correo Electronico";
	    document.getElementById('idTextCorreoElectronico').style.color  = "black";
	    
		frmPantalla.hdnExamenesCotizados.value = "0";
		frmPantalla.hdnVolumenesExamenesCotizados.value = "";
		frmPantalla.txtBuscarConvenio.value = "";
		frmPantalla.chkEntregaResultados.disabled = true;	
		frmPantalla.chkEntregaResultados.checked = 0;	               
		frmPantalla.selConvenios.selectedIndex = 0;
        frmPantalla.idLimpiarMedico.disabled = false;
		htmlOb=document.getElementById("Grilla");
		htmlOb.innerHTML="";
		htmlOb=document.getElementById("gridbusquedaPacientes");
		htmlOb.innerHTML="";
		var Examenes = document.getElementById("cotizaExamenes");
		var Pagos = document.getElementById("pagoOrden");
		var Usuario = document.getElementById("RegistraUsuario");
		var RegistraPago = document.getElementById("RegistraPago");		
		RegistraPago.style.visibility = "hidden";
		Usuario.style.visibility = "hidden";		
		Examenes.style.visibility = "hidden";
		Pagos.style.visibility = "hidden";
		RegistraPago.style.display = "none";
		Usuario.style.display = "none";
		Examenes.style.display = "none";
		Pagos.style.display = "none";
	 	codeDIVHTML("buscarExamenesACotizar","");
	    adminDIV("buscarExamenesACotizar","hidden","none");					
	 	codeDIVHTML("gridbusquedaDireccion","");
	    adminDIV("gridbusquedaDireccion","hidden","none");
	 	codeDIVHTML("gridbusquedaPacientes","");
	    adminDIV("gridbusquedaPacientes","hidden","none");	    
	 	codeDIVHTML("gridshowCotizacionesPaciente","");
	    adminDIV("gridshowCotizacionesPaciente","hidden","none");						    
		document.getElementById('txtPago').className = 'text';
		frmPantalla.txtPago.disabled = false;	  
		document.getElementById('txtPago').style.color="black";		
		document.getElementById("txtEntregaResultadoA").value = "";
		limpiaMedico();
		document.getElementById('txtAdmision').style.color="black";			            
		document.getElementById('txtOrden').style.color="black";			            
		document.getElementById('txtFechaEntrega').style.color="black";			  		
		document.getElementById('txtSubTotal').style.color="black";			            
		document.getElementById('txtDescuentoPaciente').style.color="black";			            
		document.getElementById('txtDescuentoEmpresa').style.color="black";			            
		document.getElementById('txtFacturarEmpresa').style.color="black";			            	
		document.getElementById('txtTotalPagar').style.color="black";			            
		document.getElementById('txtAdeudo').style.color="black";			            
		document.getElementById('txtPago').style.color="black";			            
		document.getElementById('txtAcuenta').style.color="black";			            		
//		document.getElementById('idGuardarNuevaOrden').disabled=false;
//		document.getElementById('idGuardarNuevaCotizacion').disabled=false;
		OnlyReaderNombrePaciente(false);
		onChangeTipoOperacion();
	}
	

    function visualizaCuestionario(strLiga, nombre){
        var frmPantalla = window.document.frmDatosOrdenFundacion;
    	if (frmPantalla.hdnkPaciente.value != "" && frmPantalla.hdnkPaciente.value > 0) {
	    	var liga = "";    	
			liga = strLiga + "?kPaciente=" + frmPantalla.hdnkPaciente.value;
			window.location.href =liga;
	    	return true;    		
    	} else {
    		return false;
    	}
    }
    
    function viewDiag(strLiga, nombre,KOrdenFundacion){
        var frmPantalla = window.document.frmDatosOrdenFundacion;
    	if (frmPantalla.hdnkPaciente.value != "" && frmPantalla.hdnkPaciente.value > 0) {
	    	var liga = "";    	
			liga = strLiga + "?kOrdenFundacion=" + KOrdenFundacion +"&kPaciente=" + frmPantalla.hdnkPaciente.value;
			window.location.href =liga;
	    	return true;    		
    	} else {
    		return false;
    	}
    }

    function viewDiagCuestionario(strLiga, nombre,KOrdenFundacion){
        var frmPantalla = window.document.frmCuestionario;
    	if (frmPantalla.hdnkPaciente.value != "" && frmPantalla.hdnkPaciente.value > 0) {
	    	var liga = "";    	
			liga = strLiga + "?kOrdenFundacion=" + KOrdenFundacion +"&kPaciente=" + frmPantalla.hdnkPaciente.value;
			window.location.href =liga;
	    	return true;    		
    	} else {
    		return false;
    	}
    }

	function validaFullUsuario() {
		usuarioautoriza = window.document.frmDatosOrdenFundacion.usuarioautoriza;
		passwordautoriza = window.document.frmDatosOrdenFundacion.passwordautoriza;
	    if( !validaVacios(usuarioautoriza.value) ) {
	    	return false;
	    }
	    else if( !validaVacios(passwordautoriza.value) ) {
	    	return false;	    
	    }
		return true;	
	}

	function init() 
	{
        var frmPantalla = window.document.frmDatosOrdenFundacion;
	    DWRUtil.useLoadingMessage();
	    adminDIV("cotizaExamenes","hidden","none");
	    adminDIV("pagoOrden","hidden","none");
	    adminDIV("RegistraUsuario","hidden","none");
	    adminDIV("RegistraPago","hidden","none");
	    adminDIV("gridbusquedaDireccion","hidden","none");	
	    adminDIV("cotizaExamenesSeccion","visible","inline");	    
	    adminDIV("Medico","hidden","none");	
	    adminDIV("buscarExamenesACotizar","hidden","none");			
	    adminDIV("gridshowCotizacionesPaciente","hidden","none");					
		OnlyReaderNombrePaciente(false);
	    frmPantalla.txtApellidoPaterno.focus();
	    frmPantalla.idImprimeEtiquetas.disable = true;	    
   		frmPantalla.hdnkMedico.value = "0";		
		frmPantalla.hdlCotizacionUtilizado.value = "0";   
		if ((document.getElementById('IdSucursalActual').value == 10) || (document.getElementById('IdSucursalActual').value == 12)) {
		    adminDIV("botonesSeccion","hidden","none");			
		} else {
		    adminDIV("botonesSeccion","visible","inline");	    
		}
	}

	function initConsulta() 
	{
        var frmPantalla = window.document.frmDatosOrdenFundacion;
	    DWRUtil.useLoadingMessage();
//	    adminDIV("cotizaExamenes","hidden","none");
//	    adminDIV("pagoOrden","hidden","none");
//	    adminDIV("RegistraUsuario","hidden","none");
//	    adminDIV("RegistraPago","hidden","none");
//	    adminDIV("gridbusquedaDireccion","hidden","none");	
//	    adminDIV("cotizaExamenesSeccion","visible","inline");	    
//	    adminDIV("Medico","hidden","none");	
//	    adminDIV("buscarExamenesACotizar","hidden","none");			
		OnlyReaderNombrePaciente(true);
	    frmPantalla.txtApellidoPaterno.focus();
//	    frmPantalla.idImprimeEtiquetas.disable = true;	    
//   	frmPantalla.hdnkMedico.value = "0";		
	}

	function actualizarEntregaResultado() {
        var frmPantalla = window.document.frmDatosOrdenFundacion;
		if (confirm("Estas seguro de la entrega de resultados de la factura: " + frmPantalla.txtOrden.value + "?")) {
    		DatosOrden.registraResultados(frmPantalla.txtAdmision.value,frmPantalla.idUsuario.value,actualizarEntregaResultado_CallBack);
		}
	}
	
	function actualizarEntregaResultado_CallBack(){
		window.document.frmDatosOrdenFundacion.chkEntregaResultados.disabled = true;	
		alert('Resultado Entregado');
	}
		
	 
    function validaAutetificacion()
    {
       var frmPantalla = window.document.frmDatosOrdenFundacion;
       if (window.event && window.event.keyCode == 13) {
		 	if (validaFullUsuario()) {
		     	DatosPaciente.validaAutetificacion(frmPantalla.usuarioautoriza.value,frmPantalla.passwordautoriza.value,validaAutetificacion_CallBack);
			}
		}
	} 

    function validaAutetificacion_CallBack(data)
    {
       var frmPantalla = window.document.frmDatosOrdenFundacion;
    	if (data > 0) {
		 	window.document.frmDatosOrdenFundacion.idUsuarioPago.value = data;
		    adminDIV("RegistraUsuario","hidden","none");
		    adminDIV("RegistraPago","visible","inline");
			window.document.frmDatosOrdenFundacion.txtPago.disabled = false;	  
			document.getElementById('txtPago').style.color="black";		
			frmPantalla.txtPago.focus();
		}
		frmPantalla.usuarioautoriza.value = "";
		frmPantalla.passwordautoriza.value = "";
	}
    
    function altaMedico() {
        var frmPantalla = window.document.frmDatosOrdenFundacion;
    	htmlOb=document.getElementById("MedicoBusqueda");
    	if (frmPantalla.hdnkMedico.value == 0) {
    		strApellidoPaterno = trim(frmPantalla.txtApellidoPaternoMedico.value);
    		strApellidoMaterno = trim(frmPantalla.txtApellidoMaternoMedico.value);
    		strNombre = trim(frmPantalla.txtNombreMedico.value);
    		strCorreoElectronico = trim(frmPantalla.txtCorreoElectronicoMedico.value);
    		if (strApellidoPaterno == "" || strApellidoPaterno.length < 4) {
        		alert('Capture por lo menos el Apellido Paterno');
    		} else {
    			if (strApellidoMaterno != "" && strApellidoMaterno.length < 4) {
            		alert('Capture por lo menos 4 caracteres en el Apellido Materno o no capture el Apellido Materno');    				
            		return false;
    			}
    			if (strNombre != "" && strNombre.length < 4) {
            		alert('Capture por lo menos 4 caracteres en el Nombre o no capture el Nombre');    				
            		return false;
    			}
    			if (strApellidoPaterno.indexOf("RESPONDA") > -1){
            		alert('No debes capturar A QUIEN CORRESPONDA, selecciona una opci&oacute;n de los c&iacute;rculo');    				
            		return false;
    			}
    			if (strApellidoPaterno.indexOf("QUIEN") > -1){
            		alert('No debes capturar A QUIEN CORRESPONDA, selecciona una opci&oacute;n de los c&iacute;rculo');    				
            		return false;
    			}
    			if (strApellidoPaterno == "A QUIEN"){
            		alert('No debes capturar A QUIEN CORRESPONDA, selecciona una opci&oacute;n de los c&iacute;rculo');    				
            		return false;
    			}
    			if (strApellidoPaterno.indexOf("CORRESPONA") > -1){
            		alert('No debes capturar A QUIEN CORRESPONDA, selecciona una opci&oacute;n de los c&iacute;rculo');    				
            		return false;
    			}
    			if (strCorreoElectronico == "" || strCorreoElectronico.length < 4) {
    				alert('Preg&uacute;ntale al Paciente si su m&eacute;dico TIENE CORREO ELECTRONICO?, por favor');
    				if (confirm("Tiene correo electr&oacute;nico su m&eacute;dico?")) {
                		alert('Escr&iacute;belo y despu&eacute;s guarda al m&eacute;dico, por favor');    				
                		return false;    					
    				}
    			}
		        frmPantalla.idGuardarNuevoMedico.disabled = true;
		        activaDemograficosMedico(false);
    			DatosMedico.altaMedicoBasico(strNombre,strApellidoPaterno,strApellidoMaterno,strCorreoElectronico,medicoAceptado_CallBack);    	    			
    		}
    	} else {
    		alert('Para guardar una medico no debio seleccionar ningun medico');
    	}
    }

    function limpiaMedico() {
        var frmPantalla = window.document.frmDatosOrdenFundacion;
        if ((frmPantalla.idLimpiarMedico.value == "Cambiar Medico") && (frmPantalla.txtAdmision.value != "")) {
//			clavenew = prompt("Cual es la clave del nuevo medico?", "0");
//			if (confirm("Estas seguro de actualizar la orden con la clave de medico " + clavenew + "?")) {
//	            var ClaveDoctorOld = frmPantalla.txtCodigoMedico.value;
//	            frmPantalla.idGuardarNuevoMedico.disabled = true;
//	    		frmPantalla.idLimpiarMedico.value = "Limpiar";			
//    			DatosMedico.cambiarMedicoOrden(frmPantalla.txtAdmision.value,
//    										   ClaveDoctorOld,
//    										   clavenew,
//    										   frmPantalla.idUsuario.value,
//    										   cambiarMedicoOrden_CallBack);    	    			
//			}    		
        } else {
    		frmPantalla.idLimpiarMedico.value = "Limpiar";			        	
            activaDemograficosMedico(true);                    
            frmPantalla.hdnkMedico.value = "0";
            frmPantalla.txtCodigoMedico.value = "";
            frmPantalla.txtApellidoPaternoMedico.value = "";
            frmPantalla.txtApellidoMaternoMedico.value = "";
            frmPantalla.txtNombreMedico.value = "";
            frmPantalla.txtCorreoElectronicoMedico.value = "";
            frmPantalla.idGuardarNuevoMedico.disabled = false;            
        }
    }

    function actualizarFechaEntrega() {
        var frmPantalla = window.document.frmDatosOrdenFundacion;
        var kAdmision = frmPantalla.txtAdmision.value;
        var sFechaEntregaNew = frmPantalla.txtFechaEntrega.value
        if ((frmPantalla.txtAdmision.value =! "") && (frmPantalla.txtFechaEntrega.value =! "")) {
            frmPantalla.txtAdmision.value = kAdmision;
            frmPantalla.txtFechaEntrega.value = sFechaEntregaNew;
			if (confirm("Estas seguro de actualizar la fecha compromiso de la orden?")) {
		        frmPantalla.txtAdmision.value = kAdmision;
		        frmPantalla.txtFechaEntrega.value = sFechaEntregaNew;
				DatosOrden.actualizarFechaCompromiso(kAdmision,
													 frmPantalla.idUsuario.value,
													 sFechaEntregaNew,
													 actualizarFechaCompromiso_CallBack);    	    			
			}    		
        }
        frmPantalla.txtAdmision.value = kAdmision;
        frmPantalla.txtFechaEntrega.value = sFechaEntregaNew;
    }
    
    function actualizarFechaCompromiso_CallBack(data) {
    	alert(data);
    }
    
    function activaDemograficosMedico(bolType) {
        var frmPantalla = window.document.frmDatosOrdenFundacion;
        if (bolType) {
	        frmPantalla.txtCodigoMedico.disabled = false;
			document.getElementById('txtCodigoMedico').className = 'text';
	        frmPantalla.txtApellidoPaternoMedico.disabled = false;
			document.getElementById('txtApellidoPaternoMedico').className = 'text';
	        frmPantalla.txtApellidoMaternoMedico.disabled = false;
			document.getElementById('txtApellidoMaternoMedico').className = 'text';
	        frmPantalla.txtNombreMedico.disabled = false;
			document.getElementById('txtNombreMedico').className = 'text';
	   		frmPantalla.txtCorreoElectronicoMedico.disabled = false;		
			document.getElementById('txtCorreoElectronicoMedico').className = 'text';
        } else {
	        frmPantalla.txtCodigoMedico.disabled = true;
			document.getElementById('txtCodigoMedico').className = 'textflat';
	        frmPantalla.txtApellidoPaternoMedico.disabled = true;
			document.getElementById('txtApellidoPaternoMedico').className = 'textflat';
	        frmPantalla.txtApellidoMaternoMedico.disabled = true;
			document.getElementById('txtApellidoMaternoMedico').className = 'textflat';
	        frmPantalla.txtNombreMedico.disabled = true;
			document.getElementById('txtNombreMedico').className = 'textflat';        	
	   		frmPantalla.txtCorreoElectronicoMedico.disabled = true;		
			document.getElementById('txtCorreoElectronicoMedico').className = 'textflat';
        }
    }
    
    function cambiarMedicoOrden_CallBack(data) 
    {
   		var frmPantalla = window.document.frmDatosOrdenFundacion;   		
		frmPantalla.idLimpiarMedico.value = "Cambiar Medico";	
   		frmPantalla.hdnkMedico.value = data.kmedico;
   		frmPantalla.txtCodigoMedico.value = data.cmedico;		
   		frmPantalla.txtApellidoPaternoMedico.value = data.sappaterno;		
   		frmPantalla.txtApellidoMaternoMedico.value = data.sapmaterno;		
   		frmPantalla.txtNombreMedico.value = data.snombre;		
   		frmPantalla.txtCorreoElectronicoMedico.value = data.scorreoelectro;		
   	    adminDIV("MedicoBusqueda","hidden","none");
   	 	codeDIVHTML("MedicoBusqueda","");
   	 	codeDIVHTML("gridbusquedaDireccion","");
   	    adminDIV("gridbusquedaDireccion","hidden","none");
        frmPantalla.idGuardarNuevoMedico.disabled = true;
        activaDemograficosMedico(false);
    }
    
    function medicoAceptado_CallBack(data) 
    {
   		var frmPantalla = window.document.frmDatosOrdenFundacion;   		
   		frmPantalla.hdnkMedico.value = data.kmedico;
   		frmPantalla.txtCodigoMedico.value = data.cmedico;		
   		frmPantalla.txtApellidoPaternoMedico.value = data.sappaterno;		
   		frmPantalla.txtApellidoMaternoMedico.value = data.sapmaterno;		
   		frmPantalla.txtNombreMedico.value = data.snombre;		
   		frmPantalla.txtCorreoElectronicoMedico.value = data.scorreoelectro;		
   	    adminDIV("MedicoBusqueda","hidden","none");
   	 	codeDIVHTML("MedicoBusqueda","");
   	 	codeDIVHTML("gridbusquedaDireccion","");
   	    adminDIV("gridbusquedaDireccion","hidden","none");
        frmPantalla.idGuardarNuevoMedico.disabled = true;
        activaDemograficosMedico(false);
//		frmPantalla.idGuardarNuevaOrden.focus();		
    }

    function imprimirEtiquetaMuestra(uMuestra) {
   		var frmPantalla = window.document.frmDatosOrdenFundacion;
   		if (frmPantalla.txtAdmision.value > 0) {
   			if ((document.getElementById('IdSucursalActual').value == 10) || (document.getElementById('IdSucursalActual').value == 12)) {   			
//   			if (frmPantalla.IdSucursalActual.value == 12) {
//	   			DatosExamen.imprimeEtiquetasExamenesGabinetes(frmPantalla.txtAdmision.value,imprimirEtiquetas_CallBack);
			} else {
	   			DatosExamen.imprimeEtiquetaMuestra(frmPantalla.txtAdmision.value,uMuestra,imprimirEtiquetas_CallBack);
			}
   		} else {
   			alert('Es necesario seleccionar una orden o guardar una orden');
   		}
    }
    
    
    function imprimirEtiquetas() {
//   		var frmPantalla = window.document.frmDatosOrdenFundacion;
//   		if (frmPantalla.txtAdmision.value > 0) {
   		if (document.getElementById('txtAdmision').value > 0) {
   			if ((document.getElementById('IdSucursalActual').value == 10) || (document.getElementById('IdSucursalActual').value == 12)) {
//   			if ((frmPantalla.IdSucursalActual.value == 12) || (frmPantalla.IdSucursalActual.value == 10)) {
//   	   		DatosExamen.imprimeEtiquetasExamenesGabinetes(frmPantalla.txtAdmision.value,imprimirEtiquetas_CallBack);
   			} else {
//   	   			DatosExamen.imprimeEtiquetasExamenes(frmPantalla.txtAdmision.value,imprimirEtiquetas_CallBack);
   	   			DatosExamen.imprimeEtiquetasExamenes(document.getElementById('txtAdmision').value,imprimirEtiquetas_CallBack);
   			}
   		} else {
   			alert('Es necesario seleccionar una orden o guardar una orden');
   		}
    }
    
    function imprimirEtiquetas_CallBack(data) {
    	if (data[0] != "") {
    		window.document.frmDatosOrdenFundacion.hdlHelp.value = data[0];    		
    		pausecomp();
    	}
    	if (data[1] != "") {
    		window.document.frmDatosOrdenFundacion.hdlHelp.value = data[1];    		
    		setTimeout("pausecomp();",2000); 
    	}
    }
    
    function pausecomp() 
    {
    	sliga = window.document.frmDatosOrdenFundacion.ligaReporteZebra.value;
    	sURL  = window.document.frmDatosOrdenFundacion.hdlHelp.value;
    	surl = sliga+'?strEtiquetaZPL='+sURL;
    	snombre = "ImprimeEtiquetas";
    	abrirVentana(surl,snombre);
    } 
    
	function ocultarOrdenes() {
		var objDIV = document.getElementById("gridGridOrdenes");
		if ((objDIV.style.visibility == "hidden") && (objDIV.style.display == "none")) {
		    adminDIV("gridGridOrdenes","visible","inline");			
		} else {
		    adminDIV("gridGridOrdenes","hidden","none");			
		}		
	}
    	
	function ocultarCotizaciones() {
		var objDIV = document.getElementById("gridGridCotizaciones");
		if ((objDIV.style.visibility == "hidden") && (objDIV.style.display == "none")) {
		    adminDIV("gridGridCotizaciones","visible","inline");			
		} else {
		    adminDIV("gridGridCotizaciones","hidden","none");			
		}		
	}

	function abrirProcedimiento() {
   		var frmPantalla = window.document.frmDatosOrdenFundacion;
		snombre = "Procedimiento";
		strRuta = "http://www.olab.com.mx/Procedimientos/" + TypeObjeto(frmPantalla.selConvenios) + ".pdf";
		abrirVentanaOrden(strRuta,snombre);	   			     			    
	}
	
	function activarBuscadorConvenios() {
	    adminDIV("txtBuscarConvenio","visible","inline");					
	}

	function desactivarBuscadorConvenios() {
	    adminDIV("txtBuscarConvenio","visible","inline");					
		//	    adminDIV("txtBuscarConvenio","hidden","none");					
	}
	
	function visualizarResultado(kAdmision,sPassword) {

		strRuta = ("http://201.150.42.46:8081/ResultadosOlab/jsp/resultado/ServicioResultadosOlabECE.jsp?" +

				   "kOrden=" + kAdmision +
				   "&sPassword=" + sPassword);
		showPopWin(strRuta, 800, 500, "Resultados via Internet");			
	}
	
	
	function visualizarOrden(liga, nombre,intAdmision) {
		alert('¿Se dieron indicaciones previas al paciente?');
		url = liga+'?kAdmision=' + intAdmision + '&eventSubmit_doRecibo=action';	
		showPopWin(url, 800, 500, nombre);			
	}
	
	function visualizarOrdenFOP186(intAdmision) {
		alert('¿Se dieron indicaciones previas al paciente?');
		showPopWin('http://173.203.12.186:8080/web2labportal/jsp/ResporteOrdenFOP.jsp?kOrdenSucursal='+intAdmision, 800, 500, 'Factura');			
	}
	
	
	function visualizarOrdenFOP(intAdmision) {
		alert('¿Se dieron indicaciones previas al paciente?');
		DatosReportesAjax.createFileFOPOrden(intAdmision,visualizarOrdenFOP_CallBack)
	}
	
	function visualizarOrdenFOP_CallBack(data) {
		showPopWin(data, 800, 500, 'Factura');			
	}
	
	function visualizarFactura(strRuta) {
		showPopWin(strRuta, 800, 500, "Factura PDF");			
	}
	
	function crearFactura(kOrdenSucursal) {
   		var frmPantalla = window.document.frmDatosOrdenFundacion;
		showPopWin(frmPantalla.hdenRutaCreaFactura.value + "?kOrdenSucursal=" + kOrdenSucursal, 800, 400, "Crear Factura");
	}
	
	function activardivmedico(objRadio) {
   		var frmPantalla = window.document.frmDatosOrdenFundacion;
		frmPantalla.idLimpiarMedico.value = "Limpiar";			        	
	    limpiaMedico();		    
		if (objRadio.value == 2 || objRadio.value == 3) {
			frmPantalla.txtApellidoPaternoMedico.value = "A QUIEN CORRESPONDA";
			frmPantalla.txtCodigoMedico.value = "1";
			frmPantalla.hdnkMedico.value = 5228;
		    adminDIV("RegistroMedico","hidden","none");					
		} else {
		    adminDIV("RegistroMedico","visible","inline");		
		}
	}
		
	function modificarCorreoElectronicoMedico() {
   		var frmPantalla = window.document.frmDatosOrdenFundacion;
    	if (frmPantalla.hdnkMedico.value > 0) {
    		strCorreoElectronico = trim(frmPantalla.txtCorreoElectronicoMedico.value);
			if (strCorreoElectronico == "" || strCorreoElectronico.length < 4) {
		   		frmPantalla.txtCorreoElectronicoMedico.disabled = false;		
				document.getElementById('txtCorreoElectronicoMedico').className = 'text';
        		alert('Escr&iacute;be el correo y despu&eacute;s oprime de nuevo "Cambiar Correo Electronico", por favor');    				
			} else {
    			DatosMedico.actualizaCorreoElectronicoMedico(frmPantalla.hdnkMedico.value,strCorreoElectronico,actualizaCorreoElectronicoMedico_CallBack);
    		}
    	}		
	}
	
	function actualizaCorreoElectronicoMedico_CallBack(data) {
   		var frmPantalla = window.document.frmDatosOrdenFundacion;
		alert(data);
   		frmPantalla.txtCorreoElectronicoMedico.disabled = true;		
		document.getElementById('txtCorreoElectronicoMedico').className = 'textflat';
	}
	
	function onClickRecepcion(TypoRecepcion) {
		if (TypoRecepcion == 1) {
			limpiaPantalla();
		    StopTheClock();
		 	codeDIVHTML("divProcesoTickets","");
		    adminDIV("divProcesoTickets","hidden","none");										
		    adminDIV("botonesSeccion","hidden","none");			
		} else {
			limpiaPantalla();
			eliminarElemento("buttonNuevaOrden");
			eliminarElemento("radRecepcion2");
			eliminarElemento("labRecepcion2");
		    adminDIV("botonesSeccion","visible","inline");	    
		    adminDIV("datosdemograficospaciente","hidden","none");			
			InitializeTimer();
		}
	}
	
	function nuevosTickets() {
		Tickets.buscarTicketNuevosHTML(document.getElementById("IdSucursalActual").value,TypeObjeto(document.getElementById("selCubiculo")),document.getElementById("idUsuario").value,nuevosTickets_CallBack);
	}
	
	function nuevosTickets_CallBack(data) { 		
	 	codeDIVHTML("divProcesoTickets","");
	    adminDIV("divProcesoTickets","hidden","none");							
	    adminDIV("divProcesoTickets","visible","inline");			
	 	codeDIVHTML("divProcesoTickets",data);
	}
	
	function llamarPaciente(kticketsucursal) {
		StopTheClock();
	 	codeDIVHTML("divProcesoTickets","");
	    adminDIV("divProcesoTickets","hidden","none");							
		Tickets.llamarTicket(kticketsucursal,TypeObjeto(document.getElementById("selCubiculo")),document.getElementById("idUsuario").value,llamarPaciente_CallBack);
	}
	
	function llamarPaciente_CallBack(data) {
	    adminDIV("datosdemograficospaciente","visible","inline");	    
	    adminDIV("divProcesoTickets","visible","inline");			
	 	codeDIVHTML("divProcesoTickets",data);
	}
	
	function atendidoPaciente() {
		Tickets.atendidoTicket(document.getElementById("hdlTicketSucursal").value,document.getElementById("txtAdmision").value,0,atendidoPaciente_CallBack);
	}

	function atendidoPaciente_CallBack(data) {
		
	}
	
	function cerrarTicketSucursal(kticketsucursal) {
		limpiaPantalla();
		if(confirm("¿CONTINUAS atendiendo al mismo Turno?")) {

		} else {
/*			StopTheClock();
		 	codeDIVHTML("divProcesoTickets","");
		    adminDIV("divProcesoTickets","hidden","none");						*/	
			Tickets.cerrarTicket(kticketsucursal,cerrarTicket_CallBack);
		 	codeDIVHTML("divProcesoTickets","");
		    adminDIV("divProcesoTickets","hidden","none");							
		    adminDIV("datosdemograficospaciente","hidden","none");			
//			setTimeout ("nuevosTickets();", 4000); 		
			StartTheTimer();
		}		
	}

	function cerrarTicketAnteriores(kTicket) {
		limpiaPantalla();
		Tickets.cerrarTicketAnteriores(kTicket,cerrarTicket_CallBack);
	 	codeDIVHTML("divProcesoTickets","");
	    adminDIV("divProcesoTickets","hidden","none");							
	    adminDIV("datosdemograficospaciente","hidden","none");			
		StartTheTimer();
	}
		
	function cerrarTicket_CallBack() {
//	 	codeDIVHTML("divProcesoTickets","");
//	    adminDIV("divProcesoTickets","hidden","none");							
	}
	
	/************************** Timer ************************************/   

    var secs;
    var timerID = null;
    var timerRunning = false;
    var delay = 1000;

    function InitializeTimer() {
       secs = 2;
       StopTheClock();
       StartTheTimer();
    }

	function StopTheClock() {
       if(timerRunning) {
           clearTimeout(timerID);    	   
       }
       timerRunning = false;
	}

	function StartTheTimer() {
		if (secs==0) {
			StopTheClock();
			nuevosTickets();
			InitializeTimer();
		} else {
			self.status = secs;
			secs = (secs - 1);
			timerRunning = true;
			timerID = self.setTimeout("StartTheTimer()", delay);
		}
	}


		