var pacienteBean = new PacienteBean();
var buscarordenesviajeBean = new BuscarOrdenesViajeBean();
/******************** General ********************************/

   function init() 
   {
	    DWRUtil.useLoadingMessage();
	 	limpiaDIV();	   
	 	limpiaText();
   }

   function limpiaPantalla() {
	 	limpiaDIV();	   
	 	limpiaText();
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
   }
   
 /**  
   function limpiaText() {
  		var frmPantalla = window.document.frmConsultaOrdenes;   		
		frmPantalla.txtApellidoPaterno.value = "";
		frmPantalla.txtApellidoMaterno.value = "";
		frmPantalla.txtNombre.value = "";		 	   
		frmPantalla.txtViaje.value = "";  
		frmPantalla.txtAdmision.value = "";
   }

   **/
   function limpiaText() {
		var frmPantalla = window.document.frmConsultaOrdenes;   		
		frmPantalla.txtApellidoPaterno.value = "";
		frmPantalla.txtApellidoMaterno.value = "";
		frmPantalla.txtNombre.value = "";		 	   
		frmPantalla.txtViaje.value = "";  
		frmPantalla.txtAdmision.value = "";
		frmPantalla.txtDeFecha.value="";
		frmPantalla.txtAFecha.value="";
		valorCombo(frmPantalla.selConvenios,0);
		valorCombo(frmPantalla.selSucursales,0);
		valorCombo(frmPantalla.selActualiza,0);
		valorCombo(frmPantalla.selGrupo,0);
		
 }

/************************** Busqueda por Paciente ************************************/   
   
   function consultaPacienteGrid(frmPantalla) {		
		txtApellidoPaterno = frmPantalla.txtApellidoPaterno;
		txtApellidoMaterno = frmPantalla.txtApellidoMaterno;
		txtNombre = frmPantalla.txtNombre;		 
		if (txtApellidoMaterno.value.length < 4  && txtApellidoPaterno.value.length < 4  && txtNombre.value.length < 4) {
			 limpiaDIV();
		} else {
			LoadBasicPaciente(false);
			DatosPaciente.consultaPacienteGridNewFacturacion(pacienteBean,consultaPacienteGrid_CallBack);
		}
	}	
			
    function consultaPacienteGrid_CallBack(data)
    {
	   	loadDIVBusqueda(data);
	}	 

	function LoadBasicPaciente(bolCode) {
		var frmPantalla = window.document.frmConsultaOrdenes;
//		if (bolCode == true) {
//			pacienteBean.kpacientefundacion=frmPantalla.txtCodigoPaciente.value;
//		}
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

/************************** Busqueda por Viaje ************************************/   
	   

    function buscarOrdenes() {		
 		var frmPantalla = window.document.frmConsultaOrdenes;
 		if (frmPantalla.txtViaje.value != "") {
 			LoadBeanBuscarOrdenesViajeBean();
 			ViajeFacturacion.buscarViajeOrdenesNewIndividual(buscarordenesviajeBean,consultaConveniosGrid_CallBack);
			frmPantalla.txtViaje.value = "";		
			frmPantalla.txtViaje.focus();			
 		} else if (frmPantalla.txtAdmision.value != "") {
 			LoadBeanBuscarOrdenesViajeBean();
 			ViajeFacturacion.buscarViajeOrdenes(buscarordenesviajeBean,consultaConveniosGrid_CallBack);
			frmPantalla.txtAdmision.value = "";			
			frmPantalla.txtAdmision.focus();			
 		} else if (frmPantalla.txtFactura.value != "") {
 			LoadBeanBuscarOrdenesViajeBean();
 			ViajeFacturacion.buscarViajeOrdenes(buscarordenesviajeBean,consultaConveniosGrid_CallBack);
			frmPantalla.txtFactura.value = "";
			frmPantalla.txtFactura.focus();			
 		} else {
 			if (((frmPantalla.txtDeFecha.value != "") && (frmPantalla.txtAFecha.value != "")) && ((TypeObjeto(frmPantalla.selConvenios) != 0) || (TypeObjeto(frmPantalla.selSucursales) != 0))) {
	 			LoadBeanBuscarOrdenesViajeBean();
	 			ViajeFacturacion.buscarViajeOrdenes(buscarordenesviajeBean,consultaConveniosGrid_CallBack);
 			} else {
 				alert('Seleccione un rango de fechas y un convenio o sucursal');
 			}
 		}
	}	

    
    function buscarOrdenesPaciente(kpaciente) {		
 		var frmPantalla = window.document.frmConsultaOrdenes;
 				LoadBeanBuscarOrdenesViajeBean();
	 			ViajeFacturacion.buscarViajeOrdenesNewPaciente(buscarordenesviajeBean,kpaciente,consultaConveniosGrid_CallBack);
 	}
    
    
    function LoadBeanBuscarOrdenesViajeBean() {
 		var frmPantalla = window.document.frmConsultaOrdenes;
		    	buscarordenesviajeBean.kviaje = frmPantalla.txtViaje.value;
		    	buscarordenesviajeBean.kadmision = frmPantalla.txtAdmision.value;
		    	buscarordenesviajeBean.ufoliofacturaempresas = frmPantalla.txtFactura.value;
		    	buscarordenesviajeBean.cconvenio = TypeObjeto(frmPantalla.selConvenios);
		    	buscarordenesviajeBean.csucursal = TypeObjeto(frmPantalla.selSucursales);
		    	buscarordenesviajeBean.intcestadoregistro = TypeObjeto(frmPantalla.selActualiza);
		    	buscarordenesviajeBean.intbloque = TypeObjeto(frmPantalla.selGrupo);
		    	buscarordenesviajeBean.strfechainicial = frmPantalla.txtDeFecha.value;
		    	buscarordenesviajeBean.strfechafinal = frmPantalla.txtAFecha.value;
		    	buscarordenesviajeBean.cestadoregistroconsulta = TypeObjeto(frmPantalla.selEstadoRegistroConsulta);
    }    
    
    function BuscarOrdenesViajeBean() {
		kviaje=null,
		kadmision=null,	
		ufoliofacturaempresas=null,
		cconvenio=null,
		csucursal=null,
		intcestadoregistro=null,
		intbloque=null,
		strorderby=null,
		sordeneshtml=null,
		strfechainicial=null,
		strfechafinal=null,
		cestadoregistroconsulta= null
    }    
        
    function buscarOrdenesTeclado() {
		if (window.event && window.event.keyCode == 13) {
			buscarOrdenes();
		}    	
    }
    
    function cleanTextViaje() {
 		window.document.frmConsultaOrdenes.txtViaje.value = "";
    }

    function cleanTextAdmision() {
 		window.document.frmConsultaOrdenes.txtAdmision.value = "";
    }

    function cleanTextFactura() {
 		window.document.frmConsultaOrdenes.txtFactura.value = "";
    }
    
    function ordenAceptada(kOrdenSucursal,uOrden) {
	   	sliga = window.document.frmConsultaOrdenes.ligaDatosOrden.value;
		surl = sliga+'?kOrdenSucursal='+kOrdenSucursal+'&uOrden='+uOrden;
		snombre = "PagoOrden";
		abrirVentanaOrden(surl,snombre);	   
    }   
	
    function consultaConveniosGrid_CallBack(data)
    {
	    adminDIV("Busqueda","visible","inline");
	 	codeDIVHTML("Busqueda",data.sordeneshtml);
	 	codeDIVHTML("OrdenesBusqueda","");
	    adminDIV("OrdenesBusqueda","hidden","none");	 
	}	 

/************************** Incidencias Viajes *****************************************/
    
    function seleccionaAllSelect() {
    	var elementos = document.getElementsByName("cboEstadoOrdenViaje");    	 
    	for (x=0;x<elementos.length;x++) {
    		compareSelectvalue(elementos[x],37);
		}
    }
    
    function actualizarBatch() {
    	if (document.getElementById("idAccionesEjecutar").value == "0:0") {
        	alert("!!! No existen cambios para realizar !!!");
    	} else {
        	alert(document.getElementById("idAccionesEjecutar").value);
			ViajeFacturacion.actualizarOrdenesBatch(document.getElementById("idAccionesEjecutar").value,document.getElementById("idBloquesEjecutar").value,CambiarOrden_CallBack);
    	}
    }

    function activarBatch() {
    	var objradActualizaBloque = document.getElementById("radActualizaBloque");
    	var objidActualizarBatch = document.getElementById("idActualizarBatch");
    	document.getElementById("idAccionesEjecutar").value = "0:0";
    	document.getElementById("idBloquesEjecutar").value = "0:0";
		if(!TypeObjeto(objradActualizaBloque)){    	
			objidActualizarBatch.disabled = true;
		} else {
			objidActualizarBatch.disabled = false;
		}
    }
    
    function CambiarEstadoOrden(kOrdenSucursal,cmbEstadoOrdenLocal) {
    	var objradActualizaBloque = document.getElementById("radActualizaBloque")
		if(!TypeObjeto(objradActualizaBloque)){    	
	    	buscarordenesviajeBean.kviaje = 0;
	    	buscarordenesviajeBean.kadmision = kOrdenSucursal;
	    	buscarordenesviajeBean.ufoliofacturaempresas = 0;
	    	buscarordenesviajeBean.cconvenio = 0;
	    	buscarordenesviajeBean.csucursal = 0;
	    	buscarordenesviajeBean.intcestadoregistro = TypeObjeto(cmbEstadoOrdenLocal);
	    	buscarordenesviajeBean.intbloque = 0;
	    	buscarordenesviajeBean.strfechainicial = "";
	    	buscarordenesviajeBean.strfechafinal = "";
	    	buscarordenesviajeBean.cestadoregistroconsulta = 0;
			ViajeFacturacion.buscarViajeOrdenesNewIndividual(buscarordenesviajeBean,CambiarOrden_CallBack);
		} else {
			document.getElementById("idAccionesEjecutar").value = document.getElementById("idAccionesEjecutar").value + "," + kOrdenSucursal + ":" + TypeObjeto(cmbEstadoOrdenLocal);
		}
    }
    
    function CambiarGrupoFacturacion(kOrdenSucursal,cmbGrupoFacturacionLocal) {
    	var objradActualizaBloque = document.getElementById("radActualizaBloque")
		if(!TypeObjeto(objradActualizaBloque)){    	
	    	buscarordenesviajeBean.kviaje = 0;
	    	buscarordenesviajeBean.kadmision = kOrdenSucursal;
	    	buscarordenesviajeBean.ufoliofacturaempresas = 0;
	    	buscarordenesviajeBean.cconvenio = 0;
	    	buscarordenesviajeBean.csucursal = 0;
	    	buscarordenesviajeBean.intcestadoregistro = 0;
	    	buscarordenesviajeBean.intbloque = TypeObjeto(cmbGrupoFacturacionLocal);
	    	buscarordenesviajeBean.strfechainicial = "";
	    	buscarordenesviajeBean.strfechafinal = "";
	    	buscarordenesviajeBean.cestadoregistroconsulta = 0;
			ViajeFacturacion.buscarViajeOrdenesNewIndividual(buscarordenesviajeBean,CambiarOrden_CallBack);
		} else {
			document.getElementById("idBloquesEjecutar").value = document.getElementById("idBloquesEjecutar").value + "," + kOrdenSucursal + ":" + TypeObjeto(cmbGrupoFacturacionLocal);
		}
    }
    
    function CambiarOrden_CallBack(data) {
    	alert('Exito en la modificacion');
    }
    
    function levantarIncidenciaOrden(kOrdenSucursal,cmbIncidencia) {
 		var frmPantalla = window.document.frmConsultaOrdenes;
 		var intIncidencia = TypeObjeto(cmbIncidencia);
 		var intUsuario = frmPantalla.idUsuario.value;
 		if (intIncidencia > 0) {
 			ViajeFacturacion.levantarIncidenciaOrden(kOrdenSucursal,intIncidencia,intUsuario,levantarIncidencia_CallBack); 		
 		}
    }    
    
    function levantarIncidenciaViaje(kViaje) {
 		var frmPantalla = window.document.frmConsultaOrdenes;
 		var intIncidencia = TypeObjeto(frmPantalla.cboIncidenciaViaje);
 		var intUsuario = frmPantalla.idUsuario.value;
 		if (intIncidencia > 0) {
 			ViajeFacturacion.levantarIncidenciaViaje(kViaje,intIncidencia,intUsuario,levantarIncidencia_CallBack); 		
 		}
    }  
    
    function levantarIncidencia_CallBack(data) {
    	alert("La incidencia fue levantada con el número: " + data);
    }
    
    
    
 /*************************** Cancelacion de Facturas *********************************************
    
    function CancelacionFacturacion(uFolioFactura)
    {
        var frmPantalla = window.document.frmConsultaOrdenes;        	
 		if (confirm("Estas seguro de cancelar la factura A-" + uFolioFactura + "?")) {
 			MantenimientoOrdenFacturacion.cancelarFactura(uFolioFactura,frmPantalla.idUsuario.value,1,CancelacionFacturacion_CallBack)
 		}    			   
    }
    
    function CancelacionFacturacion_CallBack(data)
    {
 	   alert(data);
    }
   
*/    
    