     function envioCorreoPromocion()
     {
  	    var frmPantalla = window.document.frmEnvioCorreoMarketing;
	    var cPromocion = frmPantalla.selPromocion[frmPantalla.selPromocion.selectedIndex].value;
        var cSucursal = frmPantalla.selSucursal[frmPantalla.selSucursal.selectedIndex].value;
		CorreoPromociones.envioPromocionEmail(cPromocion,cSucursal,envioCorreoPromocion_CallBack); 
     }

     function envioCorreoPromocion_CallBack(data) {		
    	 alert(data);
     }	
			
