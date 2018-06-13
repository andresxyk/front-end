function mostrarTurnos(){
	StopTheClock();
	Tickets.buscarTicketLlamados(document.getElementById("IdSucursalActual").value,mostrarTurnos_CallBack);	
}

function mostrarTurnos_CallBack(data){
	if(data != ""){
		for(var i=0;i<data.length;i++){
			
			var strTurnoOriginal =  document.getElementById('lblturno').innerHTML;
			var strTurnoAnterior = strTurnoOriginal.substring(strTurnoOriginal.indexOf("TURNO")+6, strTurnoOriginal.length);			
			document.getElementById('lblanterior').innerHTML = 'TURNO ANTERIOR - ' + strTurnoAnterior  + " " + document.getElementById('lblcubiculo').innerHTML;
			document.getElementById('lblturno').innerHTML = 'TURNO '+ data[i].snemonicoconsecutivo + "-" + rellenar(data[i].uconsecutivoticketsucursal + " ");
			document.getElementById('lblcubiculo').innerHTML = 'MODULO \"'+data[i].smodulo + '\"';
			document.embeds[0].play();
			setTimeout ("delayShowScreenTurno();", 5000); 		
			atendiendoPaciente(data[i].kticketsucursal);
		}		
	}
	InitializeTimer();
}

function delayShowScreenTurno() {
}

function atendiendoPaciente(kticketsucursal) {
	Tickets.atendiendoTicket(kticketsucursal,atendiendoPaciente_CallBack);
}

function atendiendoPaciente_CallBack(data) {

}

function rellenar(strTurno){
	var strReturn = "";
	cadcero='';
	for(i=0;i<(4-strTurno.length);i++){
		cadcero+='0';
	}
	strReturn=cadcero+strTurno;
	return strReturn;
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
		mostrarTurnos();
		InitializeTimer();
	} else {
		self.status = secs;
		secs = (secs - 1);
		timerRunning = true;
		timerID = self.setTimeout("StartTheTimer()", delay);
	}
}		