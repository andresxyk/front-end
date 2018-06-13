function init() 
{
	DWRUtil.useLoadingMessage();
	 	
}

function monitoreoTurnos(){
	InitializeTimer();
}

/*
function onClickRecepcion(TypoRecepcion) {
	if (TypoRecepcion == 1) {
		limpiaPantalla();
		    StopTheClock();
		 	codeDIVHTML("divProcesoTickets","");
		    adminDIV("divProcesoTickets","hidden","none");										
		    adminDIV("botonesSeccion","hidden","none");			
		} else {
			limpiaPantalla();
			eliminarElemento("buttonNuevaOrden");
			eliminarElemento("radRecepcion2");
			eliminarElemento("labRecepcion2");
		    adminDIV("botonesSeccion","visible","inline");	    
		    adminDIV("datosdemograficospaciente","hidden","none");			
			InitializeTimer();
		}
	}*/
	
function nuevosTickets() {
	Tickets.buscarTicketNuevosHTML(document.getElementById("IdSucursalActual").value,'Z',document.getElementById("idUsuario").value,nuevosTickets_CallBack);
}
	
function nuevosTickets_CallBack(data) { 									
	 adminDIV("divProcesoTickets","visible","inline");			
	 codeDIVHTML("divProcesoTickets",data);
}
	
/************************** Timer ************************************/   

    var secs;
    var timerID = null;
    var timerRunning = false;
    var delay = 1000;

function InitializeTimer() {
     secs = 2;
     StopTheClock();
     StartTheTimer();
}

function StopTheClock() {
     if(timerRunning) {
         clearTimeout(timerID);    	   
     }
     timerRunning = false;
}

function StartTheTimer() {
	if (secs==0) {
		StopTheClock();
		nuevosTickets();
		InitializeTimer();
	} else {
		self.status = secs;
		secs = (secs - 1);
		timerRunning = true;
		timerID = self.setTimeout("StartTheTimer()", delay);
	}
}