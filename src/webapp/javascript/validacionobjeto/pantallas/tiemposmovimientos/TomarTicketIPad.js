function crearTicket(sNemonicoOpcion){	
	Tickets.nuevoTicketZPL(document.getElementById("IdSucursalActual").value,sNemonicoOpcion,imprimirTicket_CallBack);	
}

function imprimirTicket_CallBack(data) {
	
} 