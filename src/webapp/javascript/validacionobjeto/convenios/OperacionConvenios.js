
 function operacionConvenios(){
	 var frmPantalla = window.document.frmOpConvenios;
	 var idUser = document.getElementById("idUsuario").value;	 
	 DatosCliente.getMarcasUser(idUser,getMarcasUser_CallBack);
	 
	 
	 
 } 
 
 function getMarcasUser_CallBack(data) {
	 var frmPantalla = window.document.frmOpConvenios;
	 var idUser = document.getElementById("idUsuario").value;
//	 var url = "http://10.20.26.6:8021/webInfodiamex/homeGdaInit/"+idUser+"/"+data;
	 var url = "http://10.20.20.12:8021/webInfodiamex/homeGda/"+idUser;
	 window.open(url, "_blank"); 
 }