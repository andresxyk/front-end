	function cancelarExamen(examencancelar) {		
        var frmPantalla = window.document.frmDatosOrdenFundacion;
		var motivo = "";            
		if (confirm("Estas seguro de cancelar el examen " + examencancelar.value + "?")) {
			motivo = prompt("Cual es el motivo de la cancelacion?", "");
			if (motivo != "") {
				frmPantalla.txtExamenesACotizar.value = "";		
	    		DatosExamen.cancelarExamen(frmPantalla.txtAdmision.value,
	    										    examencancelar.value,
	    	                      frmPantalla.hdnExamenesCotizados.value,
	    	                                                      motivo,
	    	                                 frmPantalla.idUsuario.value,
	    	                               actualizarOrdenExamen_CallBack);
	    	} else {
	    		alert("Se requiere un motivo de la cancelacion, no puedo proceder con la cancelacion!!!!");
	    	}
    	} 
	}	

	function eliminarExamen(exameneliminar) {			 		
        var frmPantalla = window.document.frmDatosOrdenFundacion;
	    intSucursal = frmPantalla.IdSucursalActual.value;
	    if( intSucursal == 0 ) {
    		alert("La opcion del nivel socieconomico es un dato obligatorio.");
    		return false;
		} else {
			frmPantalla.txtExamenesACotizar.value = "";		
    		DatosExamen.eliminarExamen(exameneliminar.value,
    	    frmPantalla.hdnVolumenesExamenesCotizados.value,
    		         frmPantalla.hdnExamenesCotizados.value,
    		        							intSucursal,
    		           TypeObjeto(frmPantalla.selConvenios),
    		                           RefreshGrid_CallBack);
    	}	 	
	}	

 	function persistentesExamenes() {		
        var frmPantalla = window.document.frmDatosOrdenFundacion;
        var intRol = frmPantalla.idRol.value;
        var boolGerente = false;
        if (intRol == 330) {
        	boolGerente = true;
        } else {
        	boolGerente = false;
        }
		DatosExamen.persistentesExamenes(frmPantalla.txtAdmision.value,
		                        frmPantalla.hdnExamenesCotizados.value,
		                                                   boolGerente,
		                                          RefreshGrid_CallBack);
	}	
		 
 	function actualizarExamen() {		
        var frmPantalla = window.document.frmDatosOrdenFundacion;
	    intSucursal = frmPantalla.IdSucursalActual.value;
	    if( intSucursal == 0 ) {
    		alert("La opcion del nivel socieconomico es un dato obligatorio.");
    		return false;
		} else {
			frmPantalla.txtExamenesACotizar.value = "";		
    		DatosExamen.eliminarExamen(0,frmPantalla.hdnExamenesCotizados.value,
    														intSucursal,
    		                                               RefreshGrid_CallBack);
    	}	 	
	}	
	
	function newExamen() {		
		var key=window.event.keyCode;				
		if(key==13){
			var frmPantalla = window.document.frmDatosOrdenFundacion;
			var strProductos = frmPantalla.txtExamenesACotizar.value;	
			frmPantalla.txtExamenesACotizar.value = "";		
		    intSucursal = frmPantalla.IdSucursalActual.value;
			if (esNumeroValido(strProductos)) {		    
//				if (frmPantalla.idGuardarNuevaOrden.disabled==false) {							
//	        		DatosExamen.newExamen(strProductos,frmPantalla.hdnVolumenesExamenesCotizados.value,frmPantalla.hdnExamenesCotizados.value,intSucursal,TypeObjeto(frmPantalla.selConvenios),RefreshGrid_CallBack);
//	        	} else {
	    			//if (confirm("Estas seguro de cotizar los examenes o perfiles " + strProductos + "?")) {						        		
	    			//	DatosExamen.newExamenOrden(strProductos,frmPantalla.hdnExamenesCotizados.value,intSucursal,frmPantalla.txtAdmision.value,frmPantalla.idUsuario.value,actualizarOrdenExamen_CallBack);
	    	        //} 
//	        	} 
			} else {
				alert("Para consultar debe tener m�nimo 5 letras o ser un numero, si requiere b�squedas menores utilice el bot�n")
			}
		}
	}	

	function newProductoGrid(intProducto) {		
		var frmPantalla = window.document.frmDatosOrdenFundacion;
		frmPantalla.txtExamenesACotizar.value = "";		
	    intSucursal = frmPantalla.IdSucursalActual.value;
//			if (frmPantalla.idGuardarNuevaOrden.disabled==false) {							
//        		DatosExamen.newExamen(intProducto,frmPantalla.hdnVolumenesExamenesCotizados.value,frmPantalla.hdnExamenesCotizados.value,intSucursal,TypeObjeto(frmPantalla.selConvenios),RefreshGrid_CallBack);
//			} else if (frmPantalla.idGuardarNuevaCotizacion.disabled==false) {
//        		DatosExamen.newExamen(intProducto,frmPantalla.hdnVolumenesExamenesCotizados.value,frmPantalla.hdnExamenesCotizados.value,intSucursal,TypeObjeto(frmPantalla.selConvenios),RefreshGrid_CallBack);
//        	} else {
//    			//if (confirm("Estas seguro de cotizar los examenes o perfiles " + intProducto + "?")) {						        		
//    			//	DatosExamen.newExamenOrden(intProducto,frmPantalla.hdnExamenesCotizados.value,intSucursal,frmPantalla.txtAdmision.value,frmPantalla.idUsuario.value,actualizarOrdenExamen_CallBack);
//    			//	frmPantalla.txtExamenesACotizar.focus();;
//    	        //} 
//        	} 
	}		
	
	function informacionExamen(intProducto) {		
		DatosExamen.informacionExamenes(intProducto,informacionExamen_CallBack);
	}		
	
    function informacionExamenes() {
		DatosExamen.informacionExamenes(window.document.frmDatosOrdenFundacion.hdnExamenesCotizados.value,informacionExamen_CallBack);
    }
	
	function informacionExamen_CallBack(data) {
	    adminDIV("indicacionesExamen","visible","inline");
	 	codeDIVHTML("indicacionesExamen",data);
	}

	function RefreshGrid_CallBack(data)
    {
        var frmPantalla = window.document.frmDatosOrdenFundacion;
		codeDIVHTML("Grilla",data[0]);
		frmPantalla.hdnExamenesCotizados.value = data[1]; 
//		frmPantalla.hdnVolumenesExamenesCotizados.value = data[2]; 
		frmPantalla.txtExamenesACotizar.value = ""; 
	 	codeDIVHTML("buscarExamenesACotizar","");
	    adminDIV("buscarExamenesACotizar","hidden","none");			
	 	codeDIVHTML("indicacionesExamen","");
	    adminDIV("indicacionesExamen","hidden","none");		    
//	    frmPantalla.idGuardarNuevaOrden.disabled = false;
	    if (data[2] != null) {
		    if (data[2] != "") {
		    	alert(data[2]);
		    }
	    }
	 }

	function consultaProductosGridBoton() {		
        var frmPantalla = window.document.frmDatosOrdenFundacion;
        var strExamenBuscar = frmPantalla.txtExamenesACotizar.value;
        if ((strExamenBuscar != "") && (strExamenBuscar.length > 1) ) {
			DatosExamen.consultaExamenesGrid(frmPantalla.txtExamenesACotizar.value,frmPantalla.IdSucursalActual.value,TypeObjeto(frmPantalla.selConvenios),consultaExamenesGrid_CallBack);
			frmPantalla.txtExamenesACotizar.focus();;			
		} else {
		 	codeDIVHTML("buscarExamenesACotizar","");
		    adminDIV("buscarExamenesACotizar","hidden","none");		
			codeDIVHTML("gridbusquedaDireccion","");
			adminDIV("gridbusquedaDireccion","hidden","none");
		 	codeDIVHTML("gridbusquedaPacientes","");
		    adminDIV("gridbusquedaPacientes","hidden","none");		    
		 	codeDIVHTML("indicacionesExamen","");
		    adminDIV("indicacionesExamen","hidden","none");		    
//			frmPantalla.txtExamenesACotizar.focus();;
		}
	}	
    
	function consultaExamenesGrid() {		
        var frmPantalla = window.document.frmDatosOrdenFundacion;
        var strExamenBuscar = frmPantalla.txtExamenesACotizar.value;
	 	codeDIVHTML("indicacionesExamen","");
	    adminDIV("indicacionesExamen","hidden","none");		            
        if ((strExamenBuscar != "") && (strExamenBuscar.length > 4) ) {
			DatosExamen.consultaExamenesGrid(frmPantalla.txtExamenesACotizar.value,frmPantalla.IdSucursalActual.value,TypeObjeto(frmPantalla.selConvenios),consultaExamenesGrid_CallBack);
			frmPantalla.txtExamenesACotizar.focus();;			
		} else {
		 	codeDIVHTML("buscarExamenesACotizar","");
		    adminDIV("buscarExamenesACotizar","hidden","none");		
			codeDIVHTML("gridbusquedaDireccion","");
			adminDIV("gridbusquedaDireccion","hidden","none");
		 	codeDIVHTML("gridbusquedaPacientes","");
		    adminDIV("gridbusquedaPacientes","hidden","none");		    
//			frmPantalla.txtExamenesACotizar.focus();;
		}
	}	
			
    function consultaExamenesGrid_CallBack(data)
    {
	    adminDIV("buscarExamenesACotizar","visible","inline");
	 	codeDIVHTML("buscarExamenesACotizar",data);
	 	codeDIVHTML("indicacionesExamen","");
	    adminDIV("indicacionesExamen","hidden","none");		    
	}	 
    
    function tomaMuestraPendiente(kOrdenExamenSucursal,objcheck) {
	    if (objcheck.checked) {
			if(confirm("�Otro dia se tomara la muestra?")) {
				TomaMuestraAjax.tomaMuestraPendiente(kOrdenExamenSucursal,1,tomaMuestraPendiente_CallBack);
			} else {
				objcheck.checked = false;				
			}
		} else { 
			if(confirm("�Hoy se va a tomar la muestra?")) {
				TomaMuestraAjax.tomarHoyMuestraPendiente(kOrdenExamenSucursal,1,tomaMuestraPendiente_CallBack);
			} else {
				objcheck.checked = true;
			}   			
		}
	}

//    function tomaMuestraPendiente(kOrdenExamenSucursal) {
//       	if(confirm("�Es una muestra pendiente?")) {
//       		TomaMuestraAjax.tomaMuestraPendiente(kOrdenExamenSucursal,1,tomaMuestraPendiente_CallBack);
//       	} else {
//       	   	if(confirm("�Se va a tomar hoy la muestra?")) {
//       	   		TomaMuestraAjax.tomarHoyMuestraPendiente(kOrdenExamenSucursal,1,tomaMuestraPendiente_CallBack);
//       	   	}    	
//       	}    	
//       }
    
    function tomaMuestraPendiente_CallBack(data) {
    	
    }
    