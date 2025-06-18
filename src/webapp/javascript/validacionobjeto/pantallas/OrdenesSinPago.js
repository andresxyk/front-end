
/******************** General ********************************/

   function init() {
	    DWRUtil.useLoadingMessage();
		adminDIV("divConfirmacionTicket","hidden","none");
   }
   
/************************** Busqueda de Ordenes ************************************/   
   
   function consultaOrdenGrid() {		
 		var frmPantalla = window.document.frmOrdenesSinPago;   		
 		DatosPago.showOrdenesSinPago(frmPantalla.IdSucursalActual.value,consultaOrdenGrid_CallBack);
   }	

   function consultaOrdenGrid_CallBack(data) {
	    adminDIV("OrdenesBusqueda","visible","inline");
	 	codeDIVHTML("OrdenesBusqueda",data);
   }	 
   
   function registrarPago(kOrdenSucursal,uOrden) {
	   	sliga = window.document.frmOrdenesSinPago.ligaDatosOrden.value;
		surl = sliga+'?kOrdenSucursal='+kOrdenSucursal+'&uOrden='+uOrden;
		snombre = "PagoOrden";
		abrirVentanaOrden(surl,snombre);	   
 		DatosPago.setInicioTicket(document.getElementById("IdSucursalActual").value,
               														 kOrdenSucursal,
				    					 document.getElementById("idUsuario").value,
										                                        "B",
										                                        "B",
										                   setInicioTicket_CallBack);
   }
   
   function setInicioTicket_CallBack(data) {
	   document.getElementById("kticketcaja").value = data;
	   document.getElementById("idConfirmacionTicket").value = "Termino Atenci&oacute;n Ticket: " + data;
	   if ((document.getElementById("IdSucursalActual").value == 10) || (document.getElementById("IdSucursalActual").value == 12)) {
		   adminDIV("divConfirmacionTicket","visible","inline");
		   adminDIV("divTimer","hidden","none");
		   adminDIV("OrdenesBusqueda","hidden","none");
		   codeDIVHTML("OrdenesBusqueda","");	   
		   StopTheClock();
	   }
   }
   
   function AtendidoTicket() {
	   DatosPago.setTerminoAtencionTicket(document.getElementById("kticketcaja").value,setTerminoAtencionTicket_CallBack);	   
	   InitializeTimer();
	   adminDIV("divTimer","visible","inline");
	   adminDIV("divConfirmacionTicket","hidden","none");
	   document.getElementById("kticketcaja").value = 0;
   }
   
   function setTerminoAtencionTicket_CallBack(data) {
	   
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

   function InitializeTimer() {	   
	   var frmPantalla = window.document.frmOrdenesSinPago;   		
       secs = frmPantalla.selTimer[frmPantalla.selTimer.selectedIndex].value;
       StopTheClock();
       StartTheTimer();
   }

   function StopTheClock() {
	   var frmPantalla = window.document.frmOrdenesSinPago;   		
	   frmPantalla.idIniciaTimer.disabled=false;		
	   frmPantalla.idStopTimer.disabled=true;		
	   frmPantalla.idExecute.disabled=false;		
       if(timerRunning) {
           clearTimeout(timerID);    	   
       }
       timerRunning = false;
   }

   function StartTheTimer() {
	   var frmPantalla = window.document.frmOrdenesSinPago;   		
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

