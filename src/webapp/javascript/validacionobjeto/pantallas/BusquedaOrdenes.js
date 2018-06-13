var pacienteBean = new PacienteBean();
var convenioBean = new ConvenioBean();
var medicoBean = new MedicoBean();

/******************** General ********************************/

   function init() 
   {
	    DWRUtil.useLoadingMessage();
	 	limpiaDIV();	   
	 	limpiaText();
	 	disableBotones(true);
	 	disableText(false);
   }

   function limpiaPantalla() {
	 	limpiaDIV();	   
	 	limpiaText();
	 	disableBotones(true);
	 	disableText(false);
   }
   
   function limpiaDIV(){
	 	codeDIVHTML("Busqueda","");
	    adminDIV("Busqueda","hidden","none");
	 	codeDIVHTML("OrdenesBusqueda","");
	    adminDIV("OrdenesBusqueda","hidden","none");	 
   }
 
   function loadDIVBusqueda(data) {
	    adminDIV("Busqueda","visible","inline");
	 	codeDIVHTML("Busqueda",data);
	 	codeDIVHTML("OrdenesBusqueda","");
	    adminDIV("OrdenesBusqueda","hidden","none");	 
	 	disableBotones(true);
   }
   
   function disableBotones(bolDisable){
  		var frmPantalla = window.document.frmConsultaOrdenes;   		
		frmPantalla.idBuscarOrdenesPaciente.disabled = bolDisable;		 
		frmPantalla.idBuscarOrdenesMedico.disabled = bolDisable;		 
		frmPantalla.idBuscarOrdenesConvenio.disabled = bolDisable;		 	   
   }
   
   function limpiaText() {
  		var frmPantalla = window.document.frmConsultaOrdenes;   		
		frmPantalla.txtApellidoPaternoMedico.value = "";
		frmPantalla.txtApellidoMaternoMedico.value = "";
		frmPantalla.txtNombreMedico.value = "";		 
		frmPantalla.txtCodigoMedico.value = "0";
		frmPantalla.hdnkMedico.value = "0";
        frmPantalla.txtCodigoPaciente.value = "0";
		frmPantalla.txtApellidoPaterno.value = "";
		frmPantalla.txtApellidoMaterno.value = "";
		frmPantalla.txtNombre.value = "";		 	   
		frmPantalla.txtNombreConPro.value = "";  
		frmPantalla.txtCodigoConPro.value = "0";
   }

   function disableText(bolDisable) {
 		var frmPantalla = window.document.frmConsultaOrdenes;   		
		frmPantalla.txtApellidoPaternoMedico.disabled = bolDisable;
		frmPantalla.txtApellidoMaternoMedico.disabled = bolDisable;
		frmPantalla.txtNombreMedico.disabled = bolDisable;		 
		frmPantalla.txtCodigoMedico.disabled = bolDisable;
        frmPantalla.txtCodigoPaciente.disabled = bolDisable;
		frmPantalla.txtApellidoPaterno.disabled = bolDisable;
		frmPantalla.txtApellidoMaterno.disabled = bolDisable;
		frmPantalla.txtNombre.disabled = bolDisable;		 	   
		frmPantalla.txtNombreConPro.disabled = bolDisable;
		frmPantalla.txtCodigoConPro.disabled = bolDisable;
  }
   
/************************** Busqueda por Medico ************************************/   
   
   function consultaMedicoGrid(frmPantalla) 
   {		
		txtApellidoPaterno = frmPantalla.txtApellidoPaternoMedico;
		txtApellidoMaterno = frmPantalla.txtApellidoMaternoMedico;
		txtNombre = frmPantalla.txtNombreMedico;		 
		txtCodigoMedico = frmPantalla.txtCodigoMedico;
		if (txtApellidoMaterno.value == "" && txtApellidoPaterno.value == "" && txtNombre.value == "" && txtCodigoMedico.value==0 ) {
			 limpiaDIV();
		} else {
		 	DatosMedico.consultaMedicosGrid(txtCodigoMedico.value,
		 									txtApellidoPaterno.value,
									        txtApellidoMaterno.value,
									        txtNombre.value,
									        consultaMedicoGrid_CallBack);
		}
   }	

   function consultaMedicoGrid_CallBack(data)
   {
	   	loadDIVBusqueda(data);
   }	 

	function medicoAceptado(cMedico) 
	{
		DatosMedico.buscarMedico(cMedico,medicoAceptado_CallBack);
	}

    function medicoAceptado_CallBack(data) 
    {
   		var frmPantalla = window.document.frmConsultaOrdenes;   		
   		frmPantalla.hdnkMedico.value = data.kmedico;
   		frmPantalla.txtCodigoMedico.value = data.cmedico;		
   		frmPantalla.txtApellidoPaternoMedico.value = data.sappaterno;		
   		frmPantalla.txtApellidoMaternoMedico.value = data.sapmaterno;		
   		frmPantalla.txtNombreMedico.value = data.snombre;		
	 	disableBotones(true);
   		limpiaDIV();
	 	disableText(true);
		frmPantalla.idBuscarOrdenesMedico.disabled = false;		 
    }   

    function buscarOrdenesMedico() {		
 		var frmPantalla = window.document.frmConsultaOrdenes;
		LoadBasicMedico();
		DatosMedico.buscarMedicosOrdenes(medicoBean,
										 frmPantalla.txtDeFecha.value,
										 frmPantalla.txtAFecha.value,
										 consultaConveniosGrid_CallBack);
	}	

	function LoadBasicMedico() {
		var frmPantalla = window.document.frmConsultaOrdenes;
		medicoBean.kmedico=frmPantalla.hdnkMedico.value;
		medicoBean.cmedico=frmPantalla.hdnkMedico.value;
	}
    
    function MedicoBean() {	
    	kmedico=null, 
    	cmedico=null,
    	snombre=null,
    	sappaterno=null,
    	sapmaterno=null,
    	snacimiento=null,
    	srfc=null,
    	sdireccion=null,
    	scolonia=null,
    	sdelegmuni=null,
    	sciudad=null,
        czona=null,
        casentamiento=null,
    	scodigopostal=null,
    	kcodigopostal=null,
    	stelefono=null,
    	usexo=null,
    	scorreoelectro=null,
    	cespecialidad=null,
    	utipooperacion=null
     }    
/************************** Busqueda por Paciente ************************************/   
   
   function consultaPacienteGrid(frmPantalla) {		
        if (frmPantalla.txtCodigoPaciente.value < 1) {
			txtApellidoPaterno = frmPantalla.txtApellidoPaterno;
			txtApellidoMaterno = frmPantalla.txtApellidoMaterno;
			txtNombre = frmPantalla.txtNombre;		 
			if (txtApellidoMaterno.value.length < 4  && txtApellidoPaterno.value.length < 4  && txtNombre.value.length < 4) {
				 limpiaDIV();
			} else {
				LoadBasicPaciente(false);
				DatosPaciente.consultaPacienteGrid(pacienteBean,consultaPacienteGrid_CallBack);
			}
		}
	}	
			
    function consultaPacienteGrid_CallBack(data)
    {
	   	loadDIVBusqueda(data);
	}	 

	function LoadBasicPaciente(bolCode) {
		var frmPantalla = window.document.frmConsultaOrdenes;
		if (bolCode == true) {
			pacienteBean.kpacientefundacion=frmPantalla.txtCodigoPaciente.value;
		}
		pacienteBean.snombre=frmPantalla.txtNombre.value;
		pacienteBean.sappaterno=frmPantalla.txtApellidoPaterno.value;
		pacienteBean.sapmaterno=frmPantalla.txtApellidoMaterno.value;
		pacienteBean.cconvenio = 0;		
	}
    
	function registroAceptado(registro){
        var frmPantalla = window.document.frmConsultaOrdenes;
	 	frmPantalla.txtCodigoPaciente.value = registro;
	 	buscarPaciente();		 	
		limpiaDIV();
	}	 
	
    function buscarPaciente() {		
 		var frmPantalla = window.document.frmConsultaOrdenes;
		if (frmPantalla.txtCodigoPaciente.value != "" && frmPantalla.txtCodigoPaciente.value > 0) {
			LoadBasicPaciente(true);						
			DatosPaciente.buscarPaciente(pacienteBean,buscarPaciente_CallBack);
		} else {
			alert('Ingrese un codigo de Paciente, por favor');
		}
	}	
			
	function buscarPaciente_CallBack(data)
	{
		var frmPantalla = window.document.frmConsultaOrdenes;
		frmPantalla.txtCodigoPaciente.value = data.kpacientefundacion;
		frmPantalla.txtApellidoPaterno.value = data.sappaterno;
		frmPantalla.txtApellidoMaterno.value = data.sapmaterno;		
		frmPantalla.txtNombre.value = data.snombre;		
	 	disableBotones(true);
		limpiaDIV();
	 	disableText(true);
		frmPantalla.idBuscarOrdenesPaciente.disabled = false;		 
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

/************************** Busqueda por Convenio ************************************/   
	   
   function consultaConvenioGrid(frmPantalla) {		
		if (frmPantalla.txtNombreConPro.value.length < 5  && frmPantalla.txtCodigoConPro.value==0 ) {
			 limpiaDIV();
		} else {
			LoadBasicConvenio();
			DatosCliente.buscarConvenios(convenioBean,consultaConveniosGrid_CallBack);
		}
	}	
			
    function consultaConveniosGrid_CallBack(data)
    {
	   	loadDIVBusqueda(data);
	}	 

	function LoadBasicConvenio() {
		var frmPantalla = window.document.frmConsultaOrdenes;
		convenioBean.kconvenio=frmPantalla.txtCodigoConPro.value;
		convenioBean.cconvenio=frmPantalla.txtCodigoConPro.value;
		convenioBean.sconvenio=frmPantalla.txtNombreConPro.value;
	}
	
	function convenioAceptado(registro){
        var frmPantalla = window.document.frmConsultaOrdenes;
        frmPantalla.txtCodigoConPro.value = registro;
        buscarConvenio();		 	
		limpiaDIV();
	}	 
	
    function buscarConvenio() {		
 		var frmPantalla = window.document.frmConsultaOrdenes;
		if (frmPantalla.txtCodigoConPro.value != "" && frmPantalla.txtCodigoConPro.value > 0) {
			LoadBasicConvenio();
			DatosCliente.buscarConvenio(convenioBean,buscarConvenio_CallBack);
		} else {
			alert('Ingrese un codigo de Convenio, por favor');
		}
	}	
			
	function buscarConvenio_CallBack(data)
	{
		var frmPantalla = window.document.frmConsultaOrdenes;
		frmPantalla.txtCodigoConPro.value = data.kconvenio;
		frmPantalla.txtCodigoConPro.value = data.cconvenio;
		frmPantalla.txtNombreConPro.value = data.sconvenio;		
	 	disableBotones(true);
		limpiaDIV();
	 	disableText(true);
		frmPantalla.idBuscarOrdenesConvenio.disabled = false;		 
	}	 	 

    function buscarOrdenesConvenio() {		
 		var frmPantalla = window.document.frmConsultaOrdenes;
		LoadBasicConvenio();
		DatosCliente.buscarConveniosOrdenes(convenioBean,
											frmPantalla.txtDeFecha.value,
											frmPantalla.txtAFecha.value,
											consultaConveniosGrid_CallBack);
	}	

    
    
	function ConvenioBean() {
	    kconvenio=null,
	    cconvenio=null,
	    sconvenio=null,
	    dregistro=null,
	    sInicioVigencia=null,
	    sTerminoVigencia=null,
	    ctipoconvenio=null,
	    stipoconvenio=null,
	    ccliente=null,
	    scliente=null,
	    cvigencia=null,
	    clistacorporativa=null
	}
	
    function ordenAceptada(kOrdenSucursal,uOrden) {
	   	sliga = window.document.frmConsultaOrdenes.ligaDatosOrden.value;
		surl = sliga+'?kOrdenSucursal='+kOrdenSucursal+'&uOrden='+uOrden;
		snombre = "PagoOrden";
		abrirVentanaOrden(surl,snombre);	   
    }   
	
	