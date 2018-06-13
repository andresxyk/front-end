   
   function executaCambio() {
		var key=window.event.keyCode;		
		if(key==13){
			var strUsuario = trimStr(document.getElementById('txtCodigoEmpleado').value);			
			if (strUsuario.length > 1) {			
				TomaMuestraAjax.cambioFechaTomaMuestra(document.getElementById('intkOrdenSucursal').value,
												  	   document.getElementById('intcExamen').value,
												       document.getElementById('txtCodigoEmpleado').value,
												       document.getElementById('txtNuevaFechaToma').value,cambioFechaTomaMuestra_CallBack);
			} else {
		    	alert("Debe indicar un usuario");
			}
	    }	   
   }
            
   function cambioFechaTomaMuestra_CallBack(data) {

   }
   
   function init() 
   {
	    DWRUtil.useLoadingMessage();
   }

