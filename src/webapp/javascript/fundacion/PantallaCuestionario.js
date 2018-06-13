		function init() 
		{
		    DWRUtil.useLoadingMessage();
		    adminDIV("divCuestionario","hidden","none");
		    initCuestionario();		    
		}
		
		function buscarPaciente() {		
            var frmPantalla = window.document.frmCuestionario;
			txtCodigoPaciente = frmPantalla.txtCodigoPaciente;
			if (txtCodigoPaciente.value != "" && txtCodigoPaciente.value > 0) {
				hdnkPaciente = frmPantalla.hdnkPaciente;		
				txtApellidoPaterno = frmPantalla.txtApellidoPaterno;
				txtApellidoMaterno = frmPantalla.txtApellidoMaterno;
				txtNombre = frmPantalla.txtNombre;		 
		     	DatosPaciente.buscarPaciente(txtCodigoPaciente.value,
										  txtApellidoPaterno.value,
										  txtApellidoMaterno.value,
										  txtNombre.value,
										  buscarPaciente_CallBack);
	        } else {
				alert('Ingrese un codigo de Paciente, por favor');
			}
		 }	
		
	     function intibuscarPaciente_CallBack(data)
	     {
            var frmPantalla = window.document.frmCuestionario;
			txtCodigoPaciente = frmPantalla.txtCodigoPaciente;
			hdnkPaciente = frmPantalla.hdnkPaciente;		
			txtApellidoPaterno = frmPantalla.txtApellidoPaterno;
			txtApellidoMaterno = frmPantalla.txtApellidoMaterno;
			txtNombre = frmPantalla.txtNombre;
			txtFechaNacimiento = frmPantalla.txtFechaNacimiento;
			txtTelefono = frmPantalla.txtTelefono;
		 	hdnkPaciente.value = data.kpacientefundacion;
            disableTextBox(txtCodigoPaciente,'txtCodigoPaciente',"    " + data.kpacientefundacion,17);
            disableTextBox(txtApellidoPaterno,'txtApellidoPaterno',"    " + data.sappaterno,17);
            disableTextBox(txtApellidoMaterno,'txtApellidoMaterno',"    " + data.sapmaterno,17);
            disableTextBox(txtNombre,'txtNombre',"    " + data.snombre,17);
            disableTextBox(txtTelefono,'txtTelefono',"    " + data.stelefono,17);
            disableTextBox(txtFechaNacimiento,'txtFechaNacimiento',"    " + data.snacimiento,17);
			obtenerEdadfrm(window.document.frmCuestionario);
		    adminDIV("divCuestionario","visible","inline");		
	     	DatosOrden.consultaOrdenesCuestionarioGrid(data.kpacientefundacion,consultaOrdenesGrid_CallBack); 
		    buscarCuestionario();    
		 }	 
		
			
	     function buscarPaciente_CallBack(data)
	     {
            var frmPantalla = window.document.frmCuestionario;
			txtCodigoPaciente = frmPantalla.txtCodigoPaciente;
			hdnkPaciente = frmPantalla.hdnkPaciente;		
			txtApellidoPaterno = frmPantalla.txtApellidoPaterno;
			txtApellidoMaterno = frmPantalla.txtApellidoMaterno;
			txtNombre = frmPantalla.txtNombre;
			txtFechaNacimiento = frmPantalla.txtFechaNacimiento;
			txtTelefono = frmPantalla.txtTelefono;
		 	hdnkPaciente.value = data.kpacientefundacion;
            disableTextBox(txtCodigoPaciente,'txtCodigoPaciente',"    " + data.kpacientefundacion,17);
            disableTextBox(txtApellidoPaterno,'txtApellidoPaterno',"    " + data.sappaterno,17);
            disableTextBox(txtApellidoMaterno,'txtApellidoMaterno',"    " + data.sapmaterno,17);
            disableTextBox(txtNombre,'txtNombre',"    " + data.snombre,17);
            disableTextBox(txtTelefono,'txtTelefono',"    " + data.stelefono,17);
            disableTextBox(txtFechaNacimiento,'txtFechaNacimiento',"    " + data.snacimiento,17);
			obtenerEdadfrm(window.document.frmCuestionario);
		    adminDIV("divCuestionario","visible","inline");		    
	     	DatosOrden.consultaOrdenesCuestionarioGrid(data.kpacientefundacion,consultaOrdenesGrid_CallBack); 
		 }	 

		 function consultaOrdenesGrid_CallBack(data) {
			    adminDIV("gridbusquedaPacientes","visible","inline");
			 	codeDIVHTML("gridbusquedaPacientes",data);		 
		 }	     
	     
		 function consultaPacienteGrid() {		
            var frmPantalla = window.document.frmCuestionario;
            if (frmPantalla.hdnkPaciente.value < 1) {
				txtApellidoPaterno = frmPantalla.txtApellidoPaterno;
				txtApellidoMaterno = frmPantalla.txtApellidoMaterno;
				txtNombre = frmPantalla.txtNombre;		 
				if (txtApellidoMaterno.value == "" && txtApellidoPaterno.value == "" && txtNombre.value == "") {
				 	codeDIVHTML("gridbusquedaPacientes","");
				    adminDIV("gridbusquedaPacientes","hidden","none");
				} else {
				 	DatosPaciente.consultaPacienteGrid(txtApellidoPaterno.value,
											        txtApellidoMaterno.value,
											        txtNombre.value,
											         consultaPacienteGrid_CallBack);
				}
			}
		 }	
			
	     function consultaPacienteGrid_CallBack(data)
	     {
		    adminDIV("gridbusquedaPacientes","visible","inline");
		 	codeDIVHTML("gridbusquedaPacientes",data);
		 }	 
		 
		 function registroAceptado(registro){
            var frmPantalla = window.document.frmCuestionario;
		 	frmPantalla.txtCodigoPaciente.value = registro;
		 	frmPantalla.hdnkPaciente.value = registro;		 	
		 	buscarPaciente();		 	
		 	codeDIVHTML("gridbusquedaPacientes","");
		    adminDIV("gridbusquedaPacientes","hidden","none");
		 }
