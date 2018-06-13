var pacienteBean = new PacienteBean();


/***************************************** INICIO FUNCIONES DE VALIDACIONES *****************************************************************/

	function viewPacienteCaptura(strLiga,KPaciente){
    	var liga = "";    	
		liga = strLiga + "?kPaciente=" + KPaciente;
		window.location.href =liga;
    	return true;    		
    }

/***************************************** TERMINA FUNCIONES DE VALIDACIONES *****************************************************************/
	    			     
     function buscarPaciente() {		
 		var frmPantalla = window.document.frmConsultaOrden;
		if (frmPantalla.txtCodigoPaciente.value != "" && frmPantalla.txtCodigoPaciente.value > 0) {
			LoadBasicPaciente(true);						
			DatosPaciente.buscarPaciente(pacienteBean,buscarPaciente_CallBack);
		} else {
			alert('Ingrese un codigo de Paciente, por favor');
		}
	}	

     
	function buscarPaciente_CallBack(data)
	{
		var frmPantalla = window.document.frmConsultaOrden;
		frmPantalla.txtCodigoPaciente.value = data.kpacientefundacion;
		frmPantalla.hdnkPaciente.value = data.kpacientefundacion;
		frmPantalla.txtApellidoPaterno.value = data.sappaterno;		
		frmPantalla.txtApellidoMaterno.value = data.sapmaterno;		
		frmPantalla.txtNombre.value = data.snombre;		
		frmPantalla.txtCorreoElectronico.value = data.scorreoelectronico;
		frmPantalla.txtFechaNacimiento.value = data.snacimiento;
		frmPantalla.hdnCodigoPostal.value = data.ccodigopostal;
		frmPantalla.txtCodigoPostal.value = data.scodigopostal;
		frmPantalla.txtColonia.value = data.scolonia;
		frmPantalla.txtDelegacionMunicipio.value =  data.sdelegmuni;
		frmPantalla.txtEstado.value = data.sciudad;
		frmPantalla.txtCalle.value = data.sdireccion;
		//Datos Fiscales para la Factura
		frmPantalla.txtCodigoPostalFiscal.value = data.scodigopostal;
		frmPantalla.txtColoniaFiscal.value = data.scolonia;
		frmPantalla.txtDelegacionMunicipioFiscal.value =  data.sdelegmuni;
		frmPantalla.txtEstadoFiscal.value = data.sciudad;
		frmPantalla.txtCalleFiscal.value = data.sdireccion;
		//*************************************
		frmPantalla.txtTelefono.value = data.stelefono;			
		if (data.csexo == 0) {
			frmPantalla.radSexo[0].checked=true;
		} else if (data.csexo == 1)  {
			frmPantalla.radSexo[1].checked=true;			
		}		
		obtenerEdadfrm(window.document.frmConsultaOrden);
		adminDIV("cotizaExamenes","visible","inline");
		adminDIV("cotizaExamenesSeccion","visible","inline");
		codeDIVHTML("gridbusquedaDireccion","");
		adminDIV("gridbusquedaDireccion","hidden","none");
	 	codeDIVHTML("gridbusquedaPacientes","");
	    adminDIV("gridbusquedaPacientes","hidden","none");
		DatosOrden.consultaOrdenesGrid(data.kpacientefundacion,0,consultaOrdenesGrid_CallBack); 
	}	 	 
	    
	    
	function registroAceptado(registro){
        var frmPantalla = window.document.frmConsultaOrden;
	 	frmPantalla.txtCodigoPaciente.value = registro;
	 	frmPantalla.hdnkPaciente.value = registro;		 	
	 	buscarPaciente();		 	
	 	codeDIVHTML("gridbusquedaPacientes","");
	    adminDIV("gridbusquedaPacientes","hidden","none");
		codeDIVHTML("gridbusquedaDireccion","");
		adminDIV("gridbusquedaDireccion","hidden","none");
	}	 
		 
	function showDatosPaciente(bolSelect) {
	    var frmPantalla = window.document.frmConsultaOrden;
		frmPantalla.txtApellidoPaterno.disabled = bolSelect;
		frmPantalla.txtApellidoMaterno.disabled = bolSelect;
		frmPantalla.txtNombre.disabled = bolSelect;
		frmPantalla.txtFechaNacimiento.disabled = bolSelect;
		frmPantalla.txtTelefono.disabled = bolSelect;
		frmPantalla.txtCalle.disabled = bolSelect;
		frmPantalla.txtColonia.disabled = bolSelect;
		frmPantalla.txtDelegacionMunicipio.disabled = bolSelect;
		frmPantalla.txtCodigoPostal.disabled = bolSelect;
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
		csexo=null,
		cconvenio=null
	}	

	