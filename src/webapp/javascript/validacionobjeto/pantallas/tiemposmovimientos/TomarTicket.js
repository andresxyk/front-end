function crearTicket(sNemonicoOpcion){	
	document.getElementById("txtEleccion").focus();
	Tickets.nuevoTicketZPL(document.getElementById("IdSucursalActual").value,sNemonicoOpcion,imprimirTicket_CallBack);	
	document.getElementById("txtEleccion").focus();
}

function imprimirTicket_CallBack(data) 
{
	sliga = document.getElementById("ligaReporteZebra").value;
	surl = sliga+'?strEtiquetaZPL='+data;
	snombre = "ImprimeEtiquetas";
	document.getElementById("txtEleccion").focus();
	abrirVentana(surl,snombre);
} 

function elegirOpcion() {
	var key=window.event.keyCode;
	if((key==13) || (key==49) || (key==50) || (key==51) || (key==52)){
		var opcion = parseInt(eval(document.getElementById("txtEleccion").value));
		if(opcion <= 4){
			if(opcion!=0) {
				var sNemonico = "";
				if (key==49) {
					sNemonico = "RE";
				} else if (key==50) {
					sNemonico = "CO";
				} else if (key==51) {
					sNemonico = "CI";
				} else if (key==52) {
					sNemonico = "EN";
				}
				crearTicket(sNemonico);
				document.getElementById("txtEleccion").focus();
			}
			else{
				alert('La opción que ha elegido no se encuentra en el menu favor de rectificar');
				document.getElementById("txtEleccion").focus();
			}
		}else{
			alert('La opción que ha elegido no se encuentra en el menu favor de rectificar');
			document.getElementById("txtEleccion").focus();
		}
	}
	document.getElementById("txtEleccion").focus();
}

