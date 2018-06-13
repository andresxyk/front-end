/******************** General ********************************/
   function init() 
   {
      	var frmPantalla = window.document.frmFacturaCancelar;
	    DWRUtil.useLoadingMessage();
		frmPantalla.radInformacionCopiar[0].checked = false;
		frmPantalla.radInformacionCopiar[1].checked = true;				    
   }
/********************* Negocio *******************************/
   function CancelacionFacturacion()
   {
       	var frmPantalla = window.document.frmFacturaCancelar;
       	facturaCancelar = TypeObjeto(frmPantalla.txtIdFactura);        	
		if (confirm("Estas seguro de cancelar la factura A-" + facturaCancelar + "?")) {
			MantenimientoOrdenFacturacion.cancelarFactura(facturaCancelar,frmPantalla.idUsuario.value,TypeObjeto(frmPantalla.selInformacionCopiar),CancelacionFacturacion_CallBack)
		}    			   
   }
   
   function CancelacionFacturacion_CallBack(data)
   {
	   alert(data);
   }
