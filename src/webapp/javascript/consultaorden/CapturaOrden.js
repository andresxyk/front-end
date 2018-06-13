
	function limpiaPantalla() {
        var frmPantalla = window.document.frmConsultaOrden;		
        frmPantalla.txtCodigoPaciente.value = "0";
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
        frmPantalla.txtAdmision.value = ""    ;
        frmPantalla.txtOrden.value = "";
        frmPantalla.txtFechaEntrega.value = "";
        frmPantalla.txtCorreoElectronico.value = "";		
	    document.getElementById('idTextEnvioInternet').innerHTML = "Resultados, Factura y Expediente Clinico Internet";
	    document.getElementById('idTextEnvioInternet').style.color  = "black";
	    document.getElementById('idTextCorreoElectronico').innerHTML = "Correo Electronico";
	    document.getElementById('idTextCorreoElectronico').style.color  = "black";	    
        frmPantalla.chkEntregaResultados.check;
        frmPantalla.txtTotalPagar.value = "";
        frmPantalla.txtAcuenta.value = "";
        frmPantalla.txtAdeudo.value = "" ;
        frmPantalla.txtObservaciones.value = "" ;
   		frmPantalla.hdnkMedico.value = "0";		
   		codigoBean(frmPantalla.txtCodigoMedico,false,"0");		
   		codigoBean(frmPantalla.txtApellidoPaternoMedico,false,"");		
   		codigoBean(frmPantalla.txtApellidoMaternoMedico,false,"");		
   		codigoBean(frmPantalla.txtNombreMedico,false,"");		
        frmPantalla.radSexo[0].checked = false;
        frmPantalla.radSexo[1].checked = false;
//		window.document.frmConsultaOrden.txtDineroRecibido.value = "";
//		window.document.frmConsultaOrden.txtCambio.value = "";
        frmPantalla.txtEdadDescompuesta.value = "";
        frmPantalla.hdnkPaciente.value = "";
        frmPantalla.txtBuscarConvenio.value = "";
		document.getElementById('txtCodigoPaciente').className = 'text';
        frmPantalla.txtCodigoPaciente.disabled = false;
        frmPantalla.txtCodigoPaciente.disabled = false;
		document.getElementById('txtCodigoPaciente').className = 'text';
		frmPantalla.chkEntregaResultados.disabled = true;	
		frmPantalla.chkEntregaResultados.checked = 0;	               
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
		document.getElementById('txtAcuenta').style.color="black";			            		
		onChangeTipoOperacion();
	}

    function visualizaCuestionario(strLiga, nombre){
        var frmPantalla = window.document.frmConsultaOrden;
    	if (frmPantalla.hdnkPaciente.value != "" && frmPantalla.hdnkPaciente.value > 0) {
	    	var liga = "";    	
			liga = strLiga + "?kPaciente=" + frmPantalla.hdnkPaciente.value;
			window.location.href =liga;
	    	return true;    		
    	} else {
    		return false;
    	}
    }
    
    function visualizarResultado(kAdmision) {
//		window.location.href = "http://www.web2-lab.com/Files/Interpretacion/Examen.doc";    	
//    	window.location.href = "http://www.web2-lab.com/Files/Interpretacion/" + kAdmision + ".pdf";    	
    	window.location.href = "http://201.148.87.157:8080/Empresas/ABB/Resultados/" + kAdmision + ".pdf";    	
    }
    
    function viewDiag(strLiga, nombre,KOrdenFundacion){
        var frmPantalla = window.document.frmConsultaOrden;
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
		usuarioautoriza = window.document.frmConsultaOrden.usuarioautoriza;
		passwordautoriza = window.document.frmConsultaOrden.passwordautoriza;
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
        var frmPantalla = window.document.frmConsultaOrden;
	    DWRUtil.useLoadingMessage();
	    adminDIV("cotizaExamenes","hidden","none");
	    adminDIV("pagoOrden","hidden","none");
	    adminDIV("RegistraUsuario","hidden","none");
	    adminDIV("RegistraPago","hidden","none");
	    adminDIV("gridbusquedaDireccion","hidden","none");	
	    adminDIV("cotizaExamenesSeccion","visible","inline");	    
	    adminDIV("Medico","hidden","none");	
	    adminDIV("buscarExamenesACotizar","hidden","none");			
	    frmPantalla.txtApellidoPaterno.focus();
		onChangeTipoOperacion();
	}
	
	function actualizarEntregaResultado() {
        var frmPantalla = window.document.frmConsultaOrden;
		if (confirm("Estas seguro de la entrega de resultados de la factura: " + frmPantalla.txtOrden.value + "?")) {
    		DatosOrden.registraResultados(frmPantalla.txtAdmision.value,frmPantalla.idUsuario.value,actualizarEntregaResultado_CallBack);
		}
	}
	
	function actualizarEntregaResultado_CallBack(){
		window.document.frmConsultaOrden.chkEntregaResultados.disabled = true;	
		alert('Resultado Entregado');
	}
		
	     
    function altaMedico() {
        var frmPantalla = window.document.frmConsultaOrden;
    	htmlOb=document.getElementById("MedicoBusqueda");
    	if (frmPantalla.hdnkMedico.value == 0) {
    		strApellidoPaterno = trim(frmPantalla.txtApellidoPaternoMedico.value);
    		strApellidoMaterno = trim(frmPantalla.txtApellidoMaternoMedico.value);
    		strNombre = trim(frmPantalla.txtNombreMedico.value);
    		if (strApellidoPaterno == "" || strApellidoPaterno.length < 4) {
        		alert('Es necesario que capture por lo menos el Apellido Paterno del Medico');
    		} else {
		        activaDemograficosMedico(false);
    			DatosMedico.altaMedicoBasico(strNombre,strApellidoPaterno,strApellidoMaterno,"",<medicoAceptado_CallBack);    	    			
    		}
    	} else {
    		alert('Para guardar una medico no debio seleccionar ningun medico');
    	}
    }

    function limpiaMedico() {
        var frmPantalla = window.document.frmConsultaOrden;
        frmPantalla.hdnkMedico.value = "0";
        frmPantalla.txtCodigoMedico.value = "";
        frmPantalla.txtApellidoPaternoMedico.value = "";
        frmPantalla.txtApellidoMaternoMedico.value = "";
        frmPantalla.txtNombreMedico.value = "";
        activaDemograficosMedico(true);
    }

    function activaDemograficosMedico(bolType) {
        var frmPantalla = window.document.frmConsultaOrden;
        if (bolType) {
	        frmPantalla.txtCodigoMedico.disabled = false;
			document.getElementById('txtCodigoMedico').className = 'text';
	        frmPantalla.txtApellidoPaternoMedico.disabled = false;
			document.getElementById('txtApellidoPaternoMedico').className = 'text';
	        frmPantalla.txtApellidoMaternoMedico.disabled = false;
			document.getElementById('txtApellidoMaternoMedico').className = 'text';
	        frmPantalla.txtNombreMedico.disabled = false;
			document.getElementById('txtNombreMedico').className = 'text';
        } else {
	        frmPantalla.txtCodigoMedico.disabled = true;
			document.getElementById('txtCodigoMedico').className = 'textflat';
	        frmPantalla.txtApellidoPaternoMedico.disabled = true;
			document.getElementById('txtApellidoPaternoMedico').className = 'textflat';
	        frmPantalla.txtApellidoMaternoMedico.disabled = true;
			document.getElementById('txtApellidoMaternoMedico').className = 'textflat';
	        frmPantalla.txtNombreMedico.disabled = true;
			document.getElementById('txtNombreMedico').className = 'textflat';        	
        }
    }
    
    function medicoAceptado_CallBack(data) 
    {
    	activaDemograficosMedico(false);
   		var frmPantalla = window.document.frmConsultaOrden;   		
   		frmPantalla.hdnkMedico.value = data.kmedico;
   		frmPantalla.txtCodigoMedico.value = data.cmedico;		
   		frmPantalla.txtApellidoPaternoMedico.value = data.sappaterno;		
   		frmPantalla.txtApellidoMaternoMedico.value = data.sapmaterno;		
   		frmPantalla.txtNombreMedico.value = data.snombre;		
   	    adminDIV("MedicoBusqueda","hidden","none");
   	 	codeDIVHTML("MedicoBusqueda","");
   	 	codeDIVHTML("gridbusquedaDireccion","");
   	    adminDIV("gridbusquedaDireccion","hidden","none");
        activaDemograficosMedico(false);
    }
    
    
    function imprimirEtiquetas() {
   		var frmPantalla = window.document.frmConsultaOrden;
   		if (frmPantalla.txtAdmision.value > 0) {
   			DatosExamen.imprimeEtiquetasExamenes(frmPantalla.txtAdmision.value,imprimirEtiquetas_CallBack);
   		} else {
   			alert('Es necesario seleccionar una orden o guardar una orden');
   		}
    }
    
    function imprimirEtiquetas_CallBack(data) {
    	if (data[0] != "") {
    		window.document.frmConsultaOrden.hdlHelp.value = data[0];    		
    		pausecomp();
    	}
    	if (data[1] != "") {
    		window.document.frmConsultaOrden.hdlHelp.value = data[1];    		
    		setTimeout("pausecomp();",2000); 
    	}
    }
    
    function pausecomp() 
    {
    	sliga = window.document.frmConsultaOrden.ligaReporteZebra.value;
    	sURL  = window.document.frmConsultaOrden.hdlHelp.value;
    	surl = sliga+'?strEtiquetaZPL='+sURL;
    	snombre = "ImprimeEtiquetas";
    	abrirVentana(surl,snombre);
    } 

    