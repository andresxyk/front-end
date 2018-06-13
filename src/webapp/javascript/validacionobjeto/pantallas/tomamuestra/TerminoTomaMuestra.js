   
   function terminoMuestra() {
//		var key=window.event.keyCode;		
//		if(key==13){
			var strUsuario = trimStr(document.getElementById('txtCodigoEmpleado').value);			
			if (strUsuario.length > 1) {			
				TomaMuestraAjax.terminoTomaMuestra(document.getElementById('intkOrdenSucursal').value,
									               document.getElementById('txtCodigoEmpleado').value,
									               document.getElementById('hdlTipoExamen').value,terminoTomaMuestra);
			} else {
		    	alert("Debe indicar un usuario");
			}
//	    }	   
   }
   
   function terminoTomaMuestra(data) {

   }
   
   function init() 
   {
	    DWRUtil.useLoadingMessage();
   }

   