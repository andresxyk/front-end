var pacienteBean = new PacienteBean();
var convenioBean = new ConvenioBean();

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
 		var frmPantalla = window.document.frmConsultaPacientesEmpresa;
	    frmPantalla.txtFechaInicial.value = "";
	    frmPantalla.txtFechaTermino.value = "";
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
  		var frmPantalla = window.document.frmConsultaPacientesEmpresa;   		
		frmPantalla.idBuscarOrdenesPaciente.disabled = bolDisable;		 
   }
   
   function limpiaText() {
  		var frmPantalla = window.document.frmConsultaPacientesEmpresa;   		
        frmPantalla.txtCodigoPaciente.value = "0";
		frmPantalla.txtApellidoPaterno.value = "";
		frmPantalla.txtApellidoMaterno.value = "";
		frmPantalla.txtNombre.value = "";		 	   
		frmPantalla.txtNombreConPro.value = "";  
		frmPantalla.txtCodigoConPro.value = "0";
   }

   function disableText(bolDisable) {
 		var frmPantalla = window.document.frmConsultaPacientesEmpresa;   		
        frmPantalla.txtCodigoPaciente.disabled = bolDisable;
		frmPantalla.txtApellidoPaterno.disabled = bolDisable;
		frmPantalla.txtApellidoMaterno.disabled = bolDisable;
		frmPantalla.txtNombre.disabled = bolDisable;		 	   
  }
   
   function ordenAceptada(kOrdenSucursal,uOrden) {
	   	sliga = window.document.frmConsultaPacientesEmpresa.ligaDatosOrden.value;
		surl = sliga+'?kOrdenSucursal='+kOrdenSucursal+'&uOrden='+uOrden;
		snombre = "PagoOrden";
		abrirVentanaOrden(surl,snombre);	   
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
				DatosPaciente.consultaPacienteConvenioGrid(pacienteBean,consultaPacienteGrid_CallBack);
			}
		}
	}	
			
    function consultaPacienteGrid_CallBack(data)
    {
	   	loadDIVBusqueda(data);
	}	 

	function LoadBasicPaciente(bolCode) {
		var frmPantalla = window.document.frmConsultaPacientesEmpresa;
		if (bolCode == true) {
			pacienteBean.kpacientefundacion=frmPantalla.txtCodigoPaciente.value;
		}
		pacienteBean.snombre=frmPantalla.txtNombre.value;
		pacienteBean.sappaterno=frmPantalla.txtApellidoPaterno.value;
		pacienteBean.sapmaterno=frmPantalla.txtApellidoMaterno.value;
		pacienteBean.cconvenio=frmPantalla.cConvenio.value;
	}
    
	function registroAceptado(registro){
        var frmPantalla = window.document.frmConsultaPacientesEmpresa;
	 	frmPantalla.txtCodigoPaciente.value = registro;
	 	buscarPaciente();		 	
		limpiaDIV();
	}	 
	
    function buscarPaciente() {		
 		var frmPantalla = window.document.frmConsultaPacientesEmpresa;
		if (frmPantalla.txtCodigoPaciente.value != "" && frmPantalla.txtCodigoPaciente.value > 0) {
			LoadBasicPaciente(true);						
			DatosPaciente.buscarPaciente(pacienteBean,buscarPaciente_CallBack);
		} else {
			alert('Ingrese un codigo de Paciente, por favor');
		}
	}	
			
	function buscarPaciente_CallBack(data)
	{
		var frmPantalla = window.document.frmConsultaPacientesEmpresa;
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
		var frmPantalla = window.document.frmConsultaPacientesEmpresa;
		convenioBean.kconvenio=frmPantalla.txtCodigoConPro.value;
		convenioBean.cconvenio=frmPantalla.txtCodigoConPro.value;
		convenioBean.sconvenio=frmPantalla.txtNombreConPro.value;
	}
	
	function convenioAceptado(registro){
        var frmPantalla = window.document.frmConsultaPacientesEmpresa;
        frmPantalla.txtCodigoConPro.value = registro;
        buscarConvenio();		 	
		limpiaDIV();
	}	 
	
    function buscarConvenio() {		
 		var frmPantalla = window.document.frmConsultaPacientesEmpresa;
		if (frmPantalla.txtCodigoConPro.value != "" && frmPantalla.txtCodigoConPro.value > 0) {
			LoadBasicConvenio();
			DatosCliente.buscarConvenio(convenioBean,buscarConvenio_CallBack);
		} else {
			alert('Ingrese un codigo de Convenio, por favor');
		}
	}	
			
	function buscarConvenio_CallBack(data)
	{
		var frmPantalla = window.document.frmConsultaPacientesEmpresa;
		frmPantalla.txtCodigoConPro.value = data.kconvenio;
		frmPantalla.txtCodigoConPro.value = data.cconvenio;
		frmPantalla.txtNombreConPro.value = data.sconvenio;		
	 	disableBotones(true);
		limpiaDIV();
	 	disableText(true);
	}	 	 

    function buscarOrdenesConvenio(cConvenio) {		
 		var frmPantalla = window.document.frmConsultaPacientesEmpresa;
 		if ((frmPantalla.txtFechaInicial.value != "") && (frmPantalla.txtFechaTermino.value != "")) {
			convenioBean.kconvenio=cConvenio;
			convenioBean.cconvenio=cConvenio;		
			DatosCliente.buscarOrdenesConvenioECE(convenioBean,
												  frmPantalla.txtFechaInicial.value,
												  frmPantalla.txtFechaTermino.value,0,
												  consultaConveniosGrid_CallBack);
 		} else {
 			alert('Es necesario seleccionar un periodo para la busqueda');
 		}
	}	

    function buscarOrdenesSucursal(cSucursal) {		
 		var frmPantalla = window.document.frmConsultaPacientesEmpresa;
 		if ((frmPantalla.txtFechaInicial.value != "") && (frmPantalla.txtFechaTermino.value != "")) {
			DatosCliente.buscarOrdenesSucursalECE(cSucursal,
												  frmPantalla.txtFechaInicial.value,
												  frmPantalla.txtFechaTermino.value,
												  frmPantalla.txtCodigoPaciente.value,0,
												  consultaConveniosGrid_CallBack);
 		} else {
 			alert('Es necesario seleccionar un periodo para la busqueda');
 		}
	}	
        
	function visualizarResultado(kAdmision,sPassword) {
		strRuta = ("http://173.203.12.185:8081/ResultadosOlab/jsp/resultado/ServicioResultadosOlabECE.jsp?" +
				   "kOrden=" + kAdmision +
				   "&sPassword=" + sPassword);
		showPopWin(strRuta, 800, 500, "Resultados via Internet");			
	}

	function visualizarResultadoSinImagenes(kAdmision,sPassword) {
		strRuta = ("http://173.203.12.185:8081/ResultadosOlab/jsp/resultado/ServicioResultadosOlabProcesos.jsp?" +
						"kOrdenSucursal=" + kAdmision +
						"&uFormato=0" +
						"&uRepo=1" +
						"&uip='125'" );
		showPopWin(strRuta, 800, 500, "Resultados via Internet");			
	}
    
    
    function buscarOrdenesConvenioPaciente(cConvenio) {		
 		var frmPantalla = window.document.frmConsultaPacientesEmpresa;
 		if ((frmPantalla.txtFechaInicial.value != "") && (frmPantalla.txtFechaTermino.value != "")) {
			convenioBean.kconvenio=cConvenio;
			convenioBean.cconvenio=cConvenio;		
			DatosCliente.buscarOrdenesConvenioECE(convenioBean,
												  frmPantalla.txtFechaInicial.value,
												  frmPantalla.txtFechaTermino.value,
												  frmPantalla.txtCodigoPaciente.value,
												  consultaConveniosGrid_CallBack);
 		} else {
 			alert('Es necesario seleccionar un periodo para la busqueda');
 		}
	}	

    function showECEPaciente(kPaciente,cConvenio) {
		snombre = "ECE";
		abrirVentanaOrden("web2lab,ap,DatosOrden.vm?kPacienteECE=" + kPaciente +"&cConvenioECE=" + cConvenio,snombre);	   			     			    
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