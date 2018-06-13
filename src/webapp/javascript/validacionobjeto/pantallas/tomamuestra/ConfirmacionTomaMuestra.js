   function imprimirEtiquetas_CallBack(data) {
   	if (data[0] != "") {
   		document.getElementById('hdlHelp').value = data[0];    		
   		pausecomp();
   	}
   	if (data[1] != "") {
   		document.getElementById('hdlHelp').value = data[1];    		
   		setTimeout("pausecomp();",2000); 
   	}
   }
   
   function pausecomp() 
   {
   	sliga = document.getElementById('ligaReporteZebra').value;
   	sURL  = document.getElementById('hdlHelp').value;
   	surl = sliga+'?strEtiquetaZPL='+sURL;
   	snombre = "ImprimeEtiquetas";
   	abrirVentana(surl,snombre);
   } 

   
   function executaZebra() {
//		var key=window.event.keyCode;		
//		if(key==13){
			var strUsuario = trimStr(document.getElementById('txtCodigoEmpleado').value);			
			if (strUsuario.length > 1) {
				TomaMuestraAjax.inicioTomaMuestra(document.getElementById('intkOrdenSucursal').value,
												  document.getElementById('intcExamen').value,
												  document.getElementById('txtCodigoEmpleado').value,
												  document.getElementById('hdlTipoExamen').value,inicioTomaMuestra_CallBack);
			} else {
		    	alert("Debe indicar un usuario");
			}
//	    }	   
   }
            
   function inicioTomaMuestra_CallBack(data) {
	   if (data[0] == "") {
		   alert(data[1]);
	   } else {
		   imprimirEtiquetas_CallBack(data);
	   }
   }
   
   function init() 
   {
	    DWRUtil.useLoadingMessage();
   }

