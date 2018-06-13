function init() 
{
	DWRUtil.useLoadingMessage();
	 	
}

function monitoreoBitacora(){
	InitializeTimer();
}

function MuestraBitacora() {
	MonitoreoBitacora.mostrarBitacora(document.getElementById("IdSucursalActual").value,mostrarBitacora_CallBack);
}
	
function mostrarBitacora_CallBack(data) { 												
	 codeDIVHTML("gridmostrarBitacora",data);
}
	
/************************** Timer ************************************/   

    var secs;
    var timerID = null;
    var timerRunning = false;
    var delay = 1000;

function InitializeTimer() {
     secs = 3;
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
		MuestraBitacora();
		InitializeTimer();
	} else {
		self.status = secs;
		secs = (secs - 1);
		timerRunning = true;
		timerID = self.setTimeout("StartTheTimer()", delay);
	}
}