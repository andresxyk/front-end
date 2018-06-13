
 function buscarEtiqueta(liga, nombre){
  	var frmPantalla = window.document.frmScotianbank;
	var key=window.event.keyCode;
	
	if(key==13){
		repScotianbank(liga, nombre)
		document.getElementById("txtNumOrden").focus();
		}
 }

  function repScotianbank(liga, nombre){
		 var txtconscutivo= document.frmScotianbank.txtNumOrden.value;
		 var url =liga +'?kordensucursal='+txtconscutivo +'&eventSubmit_doDatos=action';
		 abrirVentana(url,nombre);
		 return  true;	
  }
