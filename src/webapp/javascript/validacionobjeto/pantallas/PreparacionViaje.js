    function init() {
        DWRUtil.useLoadingMessage();
    }
    
	function validaOrden(){
		var txtvalidar = new String('');
		var txtNoViaje = new String('');
        txtValidar = window.document.frmPrepViajeFacturacion.txtNumOrden.value;
		var txtUnidad = window.document.frmPrepViajeFacturacion.txtUnidadH.value;
		var txtLiberar = window.document.frmPrepViajeFacturacion.hdnLiberar.value;
		var txtIdUsuario = window.document.frmPrepViajeFacturacion.hdnIdUsuario.value;
		txtValidar = trim(txtValidar);		
		if ((txtValidar.length > 3) && (txtValidar.length < 11)) {
	        FacturacionAjax.validaOrdenViaje(txtValidar ,txtUnidad,txtLiberar,txtIdUsuario,validaOrden_CallBack);
		} else {
			alert('La orden debe ser mayor a 3 y menor a 10 d&iacute;gitos!!!');
		}	
     }

    function validaOrden_CallBack(data)
    {
		var txtValidado = window.document.frmPrepViajeFacturacion.txtNumOrden.value;
		if (data[0] == "1") {
			alert(data[1]);		
			if(confirm("¿Quiere sacar la orden del viaje?")) {
				window.document.frmPrepViajeFacturacion.hdnLiberar.value = "1";
				validaOrden();
			}
		} else if (data[0] == "2") {
			agregarOrdenValidada(data[1]);
		} else {
			alert(data[1]);		
		}
		window.document.frmPrepViajeFacturacion.txtNumOrden.value = "";
		window.document.frmPrepViajeFacturacion.hdnLiberar.value = "0";
		document.getElementById('txtNumOrden').focus();
//       window.event.keyCode = 0;
    }

	function sinViajeOrden(){
		var txtUnidad = window.document.frmPrepViajeFacturacion.txtUnidadH.value;
		var txtIdUsuario = window.document.frmPrepViajeFacturacion.hdnIdUsuario.value;
		var txtOrdenar = TypeObjeto(window.document.frmPrepViajeFacturacion.selOrdenar);
		FacturacionAjax.sinViajeOrden(txtUnidad,txtIdUsuario,txtOrdenar,sinViajeOrden_CallBack);
     }

	function enViajeOrdenNoFac(){
		var txtUnidad = window.document.frmPrepViajeFacturacion.txtUnidadH.value;
		var txtIdUsuario = window.document.frmPrepViajeFacturacion.hdnIdUsuario.value;
		var txtOrdenar = TypeObjeto(window.document.frmPrepViajeFacturacion.selOrdenar);
	    FacturacionAjax.enViajeOrdenNoFac(txtUnidad,txtIdUsuario,txtOrdenar,sinViajeOrden_CallBack);
     }
	
	function ActualViaje(){
		var txtUnidad = window.document.frmPrepViajeFacturacion.txtUnidadH.value;
		var txtIdUsuario = window.document.frmPrepViajeFacturacion.hdnIdUsuario.value;
	    FacturacionAjax.getOrdenesNewViaje(txtUnidad,txtIdUsuario,sinViajeOrden_CallBack);
     }
	
	function cerrarViaje() {
		var txtUnidad = window.document.frmPrepViajeFacturacion.txtUnidadH.value;
		var txtIdUsuario = window.document.frmPrepViajeFacturacion.hdnIdUsuario.value;
	    FacturacionAjax.getOrdenesNewViaje(txtUnidad,txtIdUsuario,sinViajeOrden_CallBack);
		if (confirm("Estas seguro de cerrar el viaje actual?")) {
		    FacturacionAjax.cerrarViaje(txtUnidad,txtIdUsuario,cerrarViaje_CallBack);
		}	    
	}

	function reimprimirEtiquetaViaje() {
 		var frmPantalla = window.document.frmPrepViajeFacturacion;   		
		var txtUnidad = frmPantalla.txtUnidadH.value;
		var txtIdUsuario = frmPantalla.hdnIdUsuario.value;
		var kViaje = frmPantalla.cboViajes[frmPantalla.cboViajes.selectedIndex].value;
		if (kViaje > 0) {
			buscarViajeSelect();
			if (confirm("Estas seguro de reimprimir la etiqueta del viaje " + kViaje + "?")) {
			    FacturacionAjax.reimprimirEtiquetasViaje(txtUnidad,kViaje,cerrarViaje_CallBack);
			}	    
		} else {
			alert('Seleccione un viaje!!!!!!');
		}
	}
	
	
    function cerrarViaje_CallBack(data) 
    {
    	sliga = window.document.frmPrepViajeFacturacion.ligaReporteZebra.value;
    	surl = sliga+'?strEtiquetaZPL='+data;
    	snombre = "ImprimeEtiquetas";
    	abrirVentana(surl,snombre);
    } 
		
	function sinViajeOrden_CallBack(data){
	    adminDIV("OrdenesBusqueda","visible","inline");
	 	codeDIVHTML("OrdenesBusqueda",data);		
	}
    
	 function buscarViaje()
	 {
		if (window.event && window.event.keyCode == 13) {
	 		var frmPantalla = window.document.frmPrepViajeFacturacion;   		
			window.event.keyCode = 0;
			var txtUnidad = frmPantalla.txtUnidadH.value;
			var txtIdUsuario = frmPantalla.hdnIdUsuario.value;
			var kViaje = frmPantalla.txtViajeBuscar.value;			
		    FacturacionAjax.buscarViaje(txtUnidad,txtIdUsuario,kViaje,sinViajeOrden_CallBack);
		}
	 }
	
	 function buscarViajeSelect()
	 {
 		var frmPantalla = window.document.frmPrepViajeFacturacion;   		
		var txtUnidad = frmPantalla.txtUnidadH.value;
		var txtIdUsuario = frmPantalla.hdnIdUsuario.value;
		var kViaje = frmPantalla.cboViajes[frmPantalla.cboViajes.selectedIndex].value;
	    FacturacionAjax.buscarViaje(txtUnidad,txtIdUsuario,kViaje,sinViajeOrden_CallBack);
	 }
	 	 
	 function agregaOrden()
	 {
		if (window.event && window.event.keyCode == 13) {			
		    adminDIV("OrdenesBusqueda","hidden","none");
		 	codeDIVHTML("OrdenesBusqueda","");			
			window.event.keyCode = 0;
			validaOrden();
		}
	 }
	 
	 function agregarOrdenValidada(kAdmision) {
    	 var cboDestino = eval("window.document.frmPrepViajeFacturacion.cboMuestras");
    	 var txtOrigen = window.document.frmPrepViajeFacturacion.txtNumOrden.value;
         cboDestino.add(new Option('' ,'', true, true));
         var j = (cboDestino.length - 1);	    
         var actual;
		 var z = 0;
		 if (j==0) {
	            cboDestino.options[0]= new Option(kAdmision ,txtOrigen , true, true);
         } else {
             for(var i=j; i != 0; i--) {
            	 z = (i - 1);
                 actual =  cboDestino.options[z].text;
				 valor  =  cboDestino.options[z].value;
                 cboDestino.options[i]= new Option(actual ,valor , true, true);						
 	        }	    
         	cboDestino.options[0]= new Option(kAdmision ,txtOrigen , true, true);
         }
	 }
	 
     function registrarPago(kOrdenSucursal,uOrden) {
		 sliga = window.document.frmPrepViajeFacturacion.ligaDatosOrden.value;
		 surl = sliga+'?kOrdenSucursal='+kOrdenSucursal+'&uOrden='+uOrden;
		 snombre = "DetalleOrden";
		 abrirVentanaOrden(surl,snombre);	   
     }
	 