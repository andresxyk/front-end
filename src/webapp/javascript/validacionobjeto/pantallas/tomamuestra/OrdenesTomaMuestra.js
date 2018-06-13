
/******************** General ********************************/

   function init() 
   {
	    DWRUtil.useLoadingMessage();
   }
   
/************************** Busqueda de Ordenes ************************************/   
   
   function consultaOrdenGrid() 
   {		
 		var frmPantalla = window.document.frmOrdenesTomaMuestra;   		
 		TomaMuestraAjax.showOrdenesTomaMuestra(document.getElementById('IdSucursalActual').value,
 											   TypeObjeto(frmPantalla.selSalaToma),
 											   document.getElementById('txtkOrdenSucursalAnterior').value,consultaOrdenGrid_CallBack);
   }	

   function consultaOrdenGrid_CallBack(data)
   {
	    adminDIV("OrdenesBusqueda","visible","inline");
	 	codeDIVHTML("OrdenesBusqueda",data);
   }	 
   
   function registrarPago(kOrdenSucursal,uOrden) {
	   	sliga = window.document.frmOrdenesTomaMuestra.ligaDatosOrden.value;
		surl = sliga+'?kOrdenSucursal='+kOrdenSucursal+'&uOrden='+uOrden;
		snombre = "PagoOrden";
		abrirVentanaOrden(surl,snombre);	   
   }
   
   function ejecutaConsulta() {
       StopTheClock();
       consultaOrdenGrid();	   
   }
   
/************************** Timer ************************************/   

   var secs;
   var timerID = null;
   var timerRunning = false;
   var delay = 1000;

   function InitializeTimer()
   {
	   
	   var frmPantalla = window.document.frmOrdenesTomaMuestra;   		
       secs = frmPantalla.selTimer[frmPantalla.selTimer.selectedIndex].value;
       StopTheClock();
       StartTheTimer();
   }

   function StopTheClock()
   {
	   var frmPantalla = window.document.frmOrdenesTomaMuestra;   		
	   frmPantalla.idIniciaTimer.disabled=false;		
	   frmPantalla.idStopTimer.disabled=true;		
	   frmPantalla.idExecute.disabled=false;		
       if(timerRunning) {
           clearTimeout(timerID);    	   
       }
       timerRunning = false;
   }

   function StartTheTimer()
   {
	   var frmPantalla = window.document.frmOrdenesTomaMuestra;   		
	   frmPantalla.idIniciaTimer.disabled=true;		
	   frmPantalla.idStopTimer.disabled=false;		
	   frmPantalla.idExecute.disabled=false;		
       if (secs==0)
       {
           StopTheClock();
           consultaOrdenGrid();
           InitializeTimer();
       }
       else
       {
           self.status = secs;
           secs = (secs - 1);
           timerRunning = true;
           timerID = self.setTimeout("StartTheTimer()", delay);
       }
   }

   function confirmarTomaPaciente(kOrdenSucursal) {
	   var name=prompt("Cual es tu numero de Nomina?","");
	   if (name!=null && name!="") {
		   document.getElementById('intkOrdenSucursal').value = kOrdenSucursal;
		   document.getElementById('intcExamen').value = 0;
		   document.getElementById('txtCodigoEmpleado').value = name;
		   document.getElementById('hdlTipoExamen').value = TypeObjeto(document.getElementById('selSalaToma'));
		   executaZebra();
	   }	   
   }
   
   
   /*
   function confirmarToma(kOrdenSucursal) {
		var frmPantalla = window.document.frmOrdenesTomaMuestra;
		showPopWin(frmPantalla.hdenRutaTomaMuestra.value + "?kOrdenSucursal=" + kOrdenSucursal + "&cExamen=0&bolTipoExamen=" + TypeObjeto(frmPantalla.selSalaToma), 800, 400, "Confirmar Toma Muestra");	
   }
   */

   function confirmarToma(kOrdenSucursal) {
	   var name=prompt("Cual es tu numero de Nomina?","");
	   if (name!=null && name!="") {
		   document.getElementById('intkOrdenSucursal').value = kOrdenSucursal;
		   document.getElementById('intcExamen').value = 0;
		   document.getElementById('txtCodigoEmpleado').value = name;
		   document.getElementById('hdlTipoExamen').value = TypeObjeto(document.getElementById('selSalaToma'));
		   executaZebra();
	   }	   
   }

   /*
   function confirmarTomaExamen(kOrdenSucursal,cExamen) {
		var frmPantalla = window.document.frmOrdenesTomaMuestra;
		showPopWin(frmPantalla.hdenRutaTomaMuestra.value + "?kOrdenSucursal=" + kOrdenSucursal + "&cExamen=" + cExamen + "&bolTipoExamen=" + TypeObjeto(frmPantalla.selSalaToma), 800, 400, "Confirmar Toma Muestra Examen");	
   } 
   */  

   function confirmarTomaExamen(kOrdenSucursal,cExamen) {
	   var name=prompt("Cual es tu numero de Nomina?","");
	   if (name!=null && name!="") {
		   document.getElementById('intkOrdenSucursal').value = kOrdenSucursal;
		   document.getElementById('intcExamen').value = cExamen;
		   document.getElementById('txtCodigoEmpleado').value = name;
		   document.getElementById('hdlTipoExamen').value = TypeObjeto(document.getElementById('selSalaToma'));
		   executaZebra();
	   }	   
   }
   
/*
   function terminoToma(kOrdenSucursal) {
		var frmPantalla = window.document.frmOrdenesTomaMuestra;
		showPopWin(frmPantalla.hdenRutaTerminoMuestra.value + "?kOrdenSucursal=" + kOrdenSucursal + "&bolTipoExamen=" + TypeObjeto(frmPantalla.selSalaToma), 800, 400, "Termino Toma Muestra");	
   }
 */
   
   function terminoToma(kOrdenSucursal) {
	   var name=prompt("Cual es tu numero de Nomina?","");
	   if (name!=null && name!="") {
		   document.getElementById('intkOrdenSucursal').value = kOrdenSucursal;
		   document.getElementById('txtCodigoEmpleado').value = name;
		   document.getElementById('hdlTipoExamen').value = TypeObjeto(document.getElementById('selSalaToma'));
		   terminoMuestra();
	   }	   
   }
   
   /*
   function nuevaFechaTomaExamen(kOrdenSucursal,cExamen) {
		var frmPantalla = window.document.frmOrdenesTomaMuestra;
		showPopWin(frmPantalla.hdenRutaNuevaFechaTomaMuestra.value + "?kOrdenSucursal=" + kOrdenSucursal + "&cExamen=" + cExamen, 800, 400, "Nueva Fecha Toma Muestra Examen");	
   }
   */
      
   function tomaMuestraPendiente(kOrdenExamenSucursal,objcheck) {
	    if (objcheck.checked) {
			if(confirm("¿Otro dia se tomara la muestra?")) {
				TomaMuestraAjax.tomaMuestraPendiente(kOrdenExamenSucursal,1,tomaMuestraPendiente_CallBack);
			} else {
				objcheck.checked = false;				
			}
		} else { 
			if(confirm("¿Hoy se va a tomar la muestra?")) {
				TomaMuestraAjax.tomarHoyMuestraPendiente(kOrdenExamenSucursal,1,tomaMuestraPendiente_CallBack);
			} else {
				objcheck.checked = true;
			}   			
		}
	}
   
   function tomaMuestraPendiente_CallBack(data) {
	   alert(data);   	
   }
   
   function imprimeEtiquetas(kOrdenSucursal) {
	  	DatosExamen.imprimeEtiquetasExamenes(kOrdenSucursal,imprimirEtiquetas_CallBack);
   }   
   
   function imprimirEtiquetaMuestra(kOrdenSucursal,uMuestra) {
		DatosExamen.imprimeEtiquetaMuestra(kOrdenSucursal,uMuestra,imprimirEtiquetas_CallBack);
   }   
  
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
	   
  function pausecomp() {
	   	sliga = document.getElementById('ligaReporteZebra').value;
	   	sURL  = document.getElementById('hdlHelp').value;
	   	surl = sliga+'?strEtiquetaZPL='+sURL;
	   	snombre = "ImprimeEtiquetas";
	   	abrirVentana(surl,snombre);
   } 
  
   