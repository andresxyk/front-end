	function repEstadoCuentaPuntos(liga, nombre){
		var frmPantalla = window.document.frmMedicos;
		if (frmPantalla.txtCodigoMedico.value != "") {
			if (frmPantalla.txtDeFecha.value != "" && frmPantalla.txtAFecha.value != "") {
				url = liga+'?cMedico='+frmPantalla.txtCodigoMedico.value+'&txtDeFecha=' + frmPantalla.txtDeFecha.value + '&txtAFecha=' + frmPantalla.txtAFecha.value + '&eventSubmit_doEstadocuentapuntosmedico=action';
				abrirVentana(url,nombre);
			} else {
				alert("Seleccione un Periodo por favor");
			}
		} else {
			alert("Seleccione una Clave de M�dico por favor");
		}
	}


	function permisoActualizacion() {
		var frmPantalla = window.document.frmMedicos;
		var intGrupo = frmPantalla.IdSucursalActual.value;
		if (intGrupo == 104) {
		} else {
			eliminarElemento('idGuardarNuevaOrden');
			eliminarElemento('idUpdateMedico');
			eliminarElemento('idEnviarSMS');
		}		
	}

	function init() 
	{
		var frmPantalla = window.document.frmMedicos;
		codigoBean(frmPantalla.txtCodigoMedico,false,"");		
		DWRUtil.useLoadingMessage();
	    adminDIV("MedicoBusqueda","hidden","none");
	 	codeDIVHTML("MedicoBusqueda","");
	 	codeDIVHTML("gridbusquedaDireccion","");
	    adminDIV("gridbusquedaDireccion","hidden","none");	    
		permisoActualizacion();
	}
		
	function limpiaMedico() {
		var frmPantalla = window.document.frmMedicos;
		codigoBean(frmPantalla.txtCodigoMedico,false,"");		
		frmPantalla.txtCodigoMedico.value = "";
		frmPantalla.txtApellidoPaternoMedico.value = "";
		frmPantalla.txtApellidoMaternoMedico.value = "";		
		frmPantalla.txtNombreMedico.value = "";		
		frmPantalla.txtFechaNacimiento.value = "";
		frmPantalla.txtRfc.value = "";
		frmPantalla.selZona.selectedIndex = 0;		
		frmPantalla.selEspecialidad.selectedIndex = 0;
		frmPantalla.txtCorreoElectronico.value = "";
		frmPantalla.radSexo[1].checked = false;
		frmPantalla.radSexo[0].checked = false;			
		frmPantalla.selFormaPago.selectedIndex = 0;				
		frmPantalla.txtHorarioVisita.value = "";
		frmPantalla.txtCURP.value = "";
		frmPantalla.selTipoMedico.selectedIndex = 0;
		
		frmPantalla.selEstatusDireccion.selectedIndex = 0;
		frmPantalla.txtTelefono.value = "";
		frmPantalla.selReferenciaDireccion.selectedIndex = 0;
		frmPantalla.txtCalle.value = "";
		frmPantalla.txtColonia.value = "";
		frmPantalla.txtDelegacionMunicipio.value = "";
		frmPantalla.selEstado.selectedIndex = 1;
		frmPantalla.txtCodigoPostal.value = "";
		
		frmPantalla.txtDiasVisita.value = "";
		frmPantalla.checkOlab.checked = false;
		frmPantalla.checkAzteca.checked = false;
		frmPantalla.checkSwisslab.checked = false;
		frmPantalla.checkJenner.checked = false;
		frmPantalla.checkLiacsa.checked = false;
		frmPantalla.checkFamilyLabsNorte.checked = false;
		frmPantalla.checkAsesoresSur.checked = false;
		frmPantalla.checkExakta.checked = false;
		
		frmPantalla.hdnCodigoPostal.value = "0";
		
		frmPantalla.hdnEstadoMedico.value = 0;
		frmPantalla.hdnActualizacion.value = 0;
		frmPantalla.hdnCMedico.value =0;
		
	    adminDIV("MedicoBusqueda","hidden","none");
	 	codeDIVHTML("MedicoBusqueda","");
	 	codeDIVHTML("gridbusquedaDireccion","");
	    adminDIV("gridbusquedaDireccion","hidden","none");	    	    
		adminDIV("divGridDirecciones","hidden","none");
	 	codeDIVHTML("divGridDirecciones","");	
		adminDIV("divGridTelefonos","hidden","none");
	 	codeDIVHTML("divGridTelefonos","");		    
	    codigoBean(frmPantalla.txtCodigoMedico,false,"");		
		frmPantalla.txtEdadDescompuesta.value = "";	
		permisoActualizacion();
	}
	
	function validaFullMedico() {
		var frmPantalla = window.document.frmMedicos;
		var bolReturn = true;
		var strField = "";
		txtCodigo = frmPantalla.txtCodigoMedico;
		txtApellidoPaterno = frmPantalla.txtApellidoPaternoMedico;
		txtApellidoMaterno = frmPantalla.txtApellidoMaternoMedico;
		txtNombre = frmPantalla.txtNombreMedico;
		txtFechaNacimiento = frmPantalla.txtFechaNacimiento;
		txtCorreoElectronico = frmPantalla.txtCorreoElectronico;			
		bolSexoFemenino =  TypeObjeto(frmPantalla.radSexo[0]);
		bolSexoMasculino =  TypeObjeto(frmPantalla.radSexo[1]);
		cZona = TypeObjeto(frmPantalla.selZona);
		
		tipodireccion = TypeObjeto(frmPantalla.selReferenciaDireccion);
		direccion = frmPantalla.hdnCodigoPostal.value;
		
		var EspecialidadArray = new Array();
		EspecialidadArray = loopSelected("selEspecialidad");		
		if( !validaVacios(txtCodigo.value) ) {
	    	return false;
	    }
		else if( txtCodigo.value == "0") {
			return false;
		}
		else if( !validaVacios(txtApellidoPaterno.value) ) {
			strField = "Apellido Paterno";
			bolReturn = false;
	    }
	    else if( !validaTextos(txtApellidoPaterno.value) ) {
			strField = "Apellido Paterno";
			bolReturn = false;
	    }
	    else if( !validaVacios(txtNombre.value) ) {
			strField = "Nombre";
			bolReturn = false;
	    }
	    else if( !validaTextos(txtNombre.value) ) {
			strField = "Nombre";
			bolReturn = false;
	    }	    
	    else if( cZona == 0 ) {
			alert("No seleccionaste ninguna Zona");			
			return false;
	    }
	    else if( EspecialidadArray[0] == 0 ) {
			alert("No seleccionaste ninguna Especialidad");			
			return false;
	    }
	    else if( !validaVacios(txtFechaNacimiento.value) ) {
			strField = "Fecha de Nacimiento";
			bolReturn = false;
	    } 
		else if(tipodireccion == 0){
	    	alert("No seleccionaste ningun Tipo de Direccion");			
			return false;
	    }
	    else if(direccion=="0"){
	    	alert("Falta infomacion en el apartado de Direccion");			
			return false;
	    }
	    else if (bolSexoFemenino == false) {
	    	if (bolSexoMasculino == false) {
				strField = "Sexo del Medico";
				bolReturn = false;
	    	}
	    }	
		
		if (bolReturn == false) {
			alert("Existe un error o falta  " + strField);			
			return false;
		}	    
		return true;	
	}	 	

	function validaAltaMedico() {
		var frmPantalla = window.document.frmMedicos;
		var bolReturn = true;
		var strField = "";
		txtApellidoPaterno = frmPantalla.txtApellidoPaternoMedico;
		txtApellidoMaterno = frmPantalla.txtApellidoMaternoMedico;
		txtNombre = frmPantalla.txtNombreMedico;
		txtFechaNacimiento = frmPantalla.txtFechaNacimiento;		
		bolSexoFemenino =  TypeObjeto(frmPantalla.radSexo[0]);
		bolSexoMasculino =  TypeObjeto(frmPantalla.radSexo[1]);
		cZona = TypeObjeto(frmPantalla.selZona);
		tipodireccion = TypeObjeto(frmPantalla.selReferenciaDireccion);
		direccion = frmPantalla.hdnCodigoPostal.value;
		
		var EspecialidadArray = new Array();
		EspecialidadArray = loopSelected("selEspecialidad");		
		if( !validaVacios(txtApellidoPaterno.value) ) {
			strField = "Apellido Paterno";
			bolReturn = false;
	    }
	    else if( !validaTextos(txtApellidoPaterno.value) ) {
			strField = "Apellido Paterno";
			bolReturn = false;
	    }
//	    else if( !validaVacios(txtApellidoMaterno.value) ) {
//			strField = "Apellido Materno";
//			bolReturn = false;
//	    }
//	    else if( !validaTextos(txtApellidoMaterno.value) ) {
//			strField = "Apellido Materno";
//			bolReturn = false;
//	    }	 	       	    
	    else if( !validaVacios(txtNombre.value) ) {
			strField = "Nombre";
			bolReturn = false;
	    }
	    else if( !validaTextos(txtNombre.value) ) {
			strField = "Nombre";
			bolReturn = false;
	    }	    
	    else if( cZona == -1 ) {
			alert("No seleccionaste ninguna Zona");			
			return false;
	    }
	    else if( EspecialidadArray[0] == 0 ) {
			alert("No seleccionaste ninguna Especialidad");			
			return false;
	    }
	    else if( !validaVacios(txtFechaNacimiento.value) ) {
			strField = "Fecha de Nacimiento";
			bolReturn = false;
	    }
		else if(tipodireccion == 0){
	    	alert("No seleccionaste ningun Tipo de Direccion");			
			return false;
	    }
	    else if(direccion=="0"){
	    	alert("Falta infomacion en el apartado de Direccion");			
			return false;
	    }
	    else if (bolSexoFemenino == false) {
	    	if (bolSexoMasculino == false) {
				strField = "Sexo del Medico";
				bolReturn = false;
	    	}
	    }
				
		if (bolReturn == false) {
			alert('Existe un error o falta  ' + strField);			
			return false;
		}
		
		
		
		return true;	
	}	 	
		
		
	function medicosxZona(frmPantalla) {
		var frmPantalla = window.document.frmMedicos;
		var cZona = TypeObjeto(frmPantalla.selZona);  
		if (confirm("Quieres realizar la busqueda por esta zona?")) {
		 	DatosMedico.buscarMedicoZona(cZona,consultaMedicoGrid_CallBack);	 
		} 
	}
	
//	function changeEstadoMedico() {
//		var frmPantalla = window.document.frmMedicos;
//		if (frmPantalla.hdnEstadoMedico.value == 24) {	
//			/********ACTIVO**********/
//			if (confirm("Quieres desactivar al medico?")) {
//				frmPantalla.hdnEstadoMedico.value = 25;
//				VerificaModificacion();
//			} else if(confirm("Quieres indicar como finado al medico?")) {
//				frmPantalla.hdnEstadoMedico.value = 26;
//				VerificaModificacion();
//			}
//		} else if (frmPantalla.hdnEstadoMedico.value == 25) {
//			/********INACTIVO**********/
//			if (confirm("Quieres activar al medico?")) {
//				frmPantalla.hdnEstadoMedico.value = 24;
//				VerificaModificacion();
//			}			
//		} 
//	}
	
	function medicosxEspecialidad(frmPantalla) {
		var frmPantalla = window.document.frmMedicos;
		var cEspecialidad = TypeObjeto(frmPantalla.selEspecialidad);  
		if (confirm("Quieres realizar la busqueda por esta especialidad?")) {
		 	DatosMedico.buscarMedicoEspecialidad(cEspecialidad,consultaMedicoGrid_CallBack);	 
		} 
	}

	 function medicoAceptado_CallBack(data) 
	 {
			var frmPantalla = window.document.frmMedicos;
			
			frmPantalla.hdnCMedico.value = data.kmedico;
			codigoBean(frmPantalla.txtCodigoMedico,true,data.cmedico);		
			frmPantalla.txtApellidoPaternoMedico.value = data.sappaterno;
			frmPantalla.txtApellidoMaternoMedico.value = data.sapmaterno;		
			frmPantalla.txtNombreMedico.value = data.snombre;		
			frmPantalla.txtFechaNacimiento.value = data.snacimiento;
			frmPantalla.selZona.selectedIndex = (data.czona + 1);		
			valorCombo(frmPantalla.selEspecialidad,data.cespecialidad);			
			frmPantalla.txtRfc.value = data.srfc;		
			frmPantalla.txtCorreoElectronico.value = data.scorreoelectro;
			if (data.usexo == 1) {
				frmPantalla.radSexo[1].checked = true;
				frmPantalla.radSexo[0].checked = false;			
			} else {
				frmPantalla.radSexo[1].checked = false;
				frmPantalla.radSexo[0].checked = true;			
			}
			obtenerEdadfrm(window.document.frmMedicos);						
			valorCombo(frmPantalla.selFormaPago,data.cformapagomedico);			
			frmPantalla.txtHorarioVisita.value = data.shorariovisita;
			frmPantalla.txtCURP.value = data.scurp;
			frmPantalla.selTipoMedico.selectedIndex = (data.ucategoriamedico);		
			frmPantalla.hdnEstadoMedico.value = data.uestadomedico;
			frmPantalla.selReferenciaDireccion.selectedIndex = (data.ctipoDireccion);
			frmPantalla.txtTelefono.value = data.stelefono;
			frmPantalla.txtCalle.value = data.sdireccion;
			frmPantalla.hdnCodigoPostal.value = data.kcodigopostal;
			 			
						
			if(data.cestadoregistro==3){
				frmPantalla.selEstatusDireccion.selectedIndex = 0;
			}else{
				frmPantalla.selEstatusDireccion.selectedIndex = 1;
			}
			
			frmPantalla.txtColonia.value = data.scolonia;
			frmPantalla.txtDelegacionMunicipio.value = data.sdelegmuni;
			
			if(data.sciudad!=""){
				frmPantalla.selEstado.selectedIndex = compareSelect(frmPantalla.selEstado,data.sciudad);
			}	
			frmPantalla.txtCodigoPostal.value = data.scodigopostal;
			frmPantalla.txtUsuarioWeb.value = data.susuarioweb;
			
			frmPantalla.checkOlab.checked = data.marcaolab;
			frmPantalla.checkAzteca.checked = data.marcaazteca;
			frmPantalla.checkSwisslab.checked = data.marcaswisslab;
			frmPantalla.checkJenner.checked = data.marcajenner;
			frmPantalla.checkLiacsa.checked = data.marcaliacsa;
			frmPantalla.checkFamilyLabsNorte.checked = data.marcafamilylabsnorte;
			frmPantalla.checkAsesoresSur.checked = data.marcaasesoressur;
			frmPantalla.checkExakta.checked = data.marcaexakta;
			
			
			
//			adminDIV("divGridDirecciones","visible","inline");
//		 	codeDIVHTML("divGridDirecciones",data.sgriddirecciones);	
//			adminDIV("divGridTelefonos","visible","inline");
//		 	codeDIVHTML("divGridTelefonos",data.sgridtelefonos);	
			permisoActualizacion();
	 }
	

	function OnlyReaderDatosDemograficosMedico() {
		var frmPantalla = window.document.frmMedicos;
	    adminDIV("tableModificacionMedico","hidden","none");
		
		document.getElementById('txtCodigoMedico').className = 'textflat';
		document.getElementById('txtApellidoPaternoMedico').className = 'textflat';
		document.getElementById('txtApellidoMaternoMedico').className = 'textflat';		
		document.getElementById('txtNombreMedico').className = 'textflat';		
		document.getElementById('txtFechaNacimiento').className = 'textflat';
		document.getElementById('txtRfc').className = 'textflat';
		document.getElementById('selZona').className = 'textflat';		
	    document.getElementById('selZona').style.backgroundColor  = "white";
	    document.getElementById('selZona').style.color = "black";
		document.getElementById('selEspecialidad').className = 'textflat';
	    document.getElementById('selEspecialidad').style.backgroundColor  = "white";
	    document.getElementById('selEspecialidad').style.color = "black";
		document.getElementById('txtCorreoElectronico').className = 'textflat';
		document.getElementById('txtHorarioVisita').className = 'textflat';
		document.getElementById('txtCURP').className = 'textflat';
		document.getElementById('selTipoMedico').className = 'textflat';				
	    document.getElementById('selTipoMedico').style.backgroundColor  = "white";
	    document.getElementById('selTipoMedico').style.color = "black";
		document.getElementById('selReferenciaDireccion').className = 'textflat';				
	    document.getElementById('selReferenciaDireccion').style.backgroundColor  = "white";
	    document.getElementById('selReferenciaDireccion').style.color = "black";
		codeDIVHTML("gridbusquedaDireccion","");
		adminDIV("gridbusquedaDireccion","hidden","none");	    		
	    
		frmPantalla.txtCodigoMedico.disabled = true;
		frmPantalla.txtApellidoPaternoMedico.disabled = true;
		frmPantalla.txtApellidoMaternoMedico.disabled = true;		
		frmPantalla.txtNombreMedico.disabled = true;		
		frmPantalla.txtFechaNacimiento.disabled = true;
		frmPantalla.txtRfc.disabled = true;
		frmPantalla.selZona.disabled = true;		
		frmPantalla.selEspecialidad.disabled = true;
		frmPantalla.txtCorreoElectronico.disabled = true;
		frmPantalla.radSexo[1].disabled = true;
		frmPantalla.radSexo[0].disabled = true;			
		frmPantalla.selFormaPago.disabled = true;
		frmPantalla.txtHorarioVisita.disabled = true;
		frmPantalla.txtCURP.disabled = true;
		frmPantalla.selTipoMedico.disabled = true;				
		frmPantalla.selReferenciaDireccion.disabled = true;
		eliminarElemento('imgFechaNacimiento');		
		eliminarElemento('btnLimpiar');		
	} 
	
	function mantenimientoDirecciones() {
		var frmPantalla = window.document.frmMedicos;
		showPopWin(frmPantalla.hdenligaRutaMantenimientoDirecciones.value, 900, 300, "Mantenimiento Direcciones");
	}
	
	function mantenimientoTelefonos() {
		var frmPantalla = window.document.frmMedicos;
		showPopWin(frmPantalla.hdenligaRutaMantenimientoTelefonos.value, 900, 300, "Mantenimiento Telefonos");
	}
	 
	function mantenimientoEstadoRegistro() {
		
	}
	