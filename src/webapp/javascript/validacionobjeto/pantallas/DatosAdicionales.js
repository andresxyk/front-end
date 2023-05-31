var pacienteBean = new PacienteBean();


/********************* Negocio *******************************/
   
   function buscarPaciente() {	
		var frmPantalla = window.document.frmDatosAdicionales;
		var key=window.event.keyCode;
		
		if(key==13){
		    if (document.frmDatosAdicionales.radTipo[0].checked) {
		    	adminDIV("gridPaciente","hidden","none");
		    	adminDIV("gridbusquedaPacientes","hidden","none");
				if (frmPantalla.txtBusqueda.value != "" && frmPantalla.txtBusqueda.value > 0) {
					 //DatosOrden.getOrdenbyAdmision(frmPantalla.txtBusqueda.value,getkPaciente_CallBack);
					 DatosOrden.getOrden(frmPantalla.txtBusqueda.value,consultaOrden_CallBack);
				} else {
					alert('Ingrese un codigo de orden, v�lido por favor');
					frmPantalla.txtBusqueda.value="";
				}
		    }
		    if (document.frmDatosAdicionales.radTipo[1].checked) {
		    	adminDIV("gridPaciente","hidden","none");
		    	adminDIV("gridbusquedaPacientes","hidden","none");
				if (frmPantalla.txtBusqueda.value != "" && frmPantalla.txtBusqueda.value > 0) {
					pacienteBean.kpacientefundacion=frmPantalla.txtBusqueda.value;
					DatosPaciente.buscarPaciente(pacienteBean,buscarPaciente_CallBack);
				} else {
					alert('Ingrese un codigo de Paciente, por favor');
					frmPantalla.txtBusqueda.value="";
				}
		    }
		}
	}
   
   function consultaOrden_CallBack(data){
	   if(data!=null){
		   DatosAdicionales.ordenGrid(data,pintarOrdenGrid_CallBack);
	   }else{
		   alert('No existen datos para esa busqueda, verifique el c�digo');
	   }
   }
   
   function ocultarOrdenes() {
		var objDIV = document.getElementById("gridGridOrdenes");
		if ((objDIV.style.visibility == "hidden") && (objDIV.style.display == "none")) {
		    adminDIV("gridGridOrdenes","visible","inline");			
		} else {
		    adminDIV("gridGridOrdenes","hidden","none");			
		}		
	}
   
   function pintarOrdenGrid_CallBack(data){
	   if(data!=null){
	   adminDIV("gridbusquedaPacientes","visible","inline");
	   codeDIVHTML("gridbusquedaPacientes",data);
   	  }else{
   		alert('No existen datos para esa busqueda, verifique el c�digo');
   	  }
   }
   
  /* function buscarPaciente_CallBack(data)
	{
		var frmPantalla = window.document.frmDatosAdicionales;
		if(data!=null){
			adminDIV("gridPaciente","visible","inline");
			frmPantalla.txtApellidoPaterno.value = data.sappaterno;		
			frmPantalla.txtApellidoMaterno.value = data.sapmaterno;		
			frmPantalla.txtNombre.value = data.snombre;	
			DatosOrden.consultaOrdenesGrid(data.kpacientefundacion,0,consultaOrdenesGrid_CallBack);
		}else{
			alert('No existen datos,verifique la busqueda');
			frmPantalla.txtBusqueda.value="";
		}
	}*/	
   
   function buscarPaciente_CallBack(data)
	{
		var frmPantalla = window.document.frmDatosAdicionales;
		if(data!=null){
			DatosAdicionales.pacienteGrid(data,pintarOrdenGrid_CallBack);
		}else{
			alert('No existen datos,verifique la busqueda');
			frmPantalla.txtBusqueda.value="";
		}
	}
	

   function consultaOrdenesGrid_CallBack(data) {
	   if(data!=null){
	    adminDIV("gridbusquedaPacientes","visible","inline");
	 	codeDIVHTML("gridbusquedaPacientes",data[0]);	
	   }else{
		   alert('No existen datos para esa busqueda, verifique el c�digo');  
	   }
   }
   
   function limpiar(){
	   var frmPantalla = window.document.frmDatosAdicionales;
	   frmPantalla.txtBusqueda.value="";
	   document.frmDatosAdicionales.radTipo[0].checked=true;
	   adminDIV("gridPaciente","hidden","none");
   	   adminDIV("gridbusquedaPacientes","hidden","none");
	   document.getElementById("txtBusqueda").focus();
   }
   
   function mostrarFactura(kOrdenSucursal) {
  		var frmPantalla = window.document.frmDatosAdicionales;
  		//alert(kOrdenSucursal);
		showPopWin(frmPantalla.hdenRutaDatoAdicional.value + "?kOrdenSucursal=" + kOrdenSucursal, 900, 800, "AltaAdicional");
	}
   
   function mensaje(kPaciente){
	   alert('Para esta busqueda debe elegir una orden del paciente'+kPaciente)
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
