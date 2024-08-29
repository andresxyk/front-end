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
       	var selectedMarca = document.getElementById("selMarca").value; 
       	var marca='';
       	if(selectedMarca==1){
       		marca='OLAB';
       	} else if(selectedMarca==4){
       		marca='AZTECA';
       	}else if(selectedMarca==5){
       		marca='SWISSLAB'; 
       	}else if(selectedMarca==7){
       		marca='JENNER PRADO'; 
       	}else if(selectedMarca==8){
       		marca='JENNER LEAN'; 
       	}else if(selectedMarca==19){
       		marca='FAMILYLABS NORTE'; 
       	}else if(selectedMarca==20){
       		marca='EXAKTA'; 
       	}else if(selectedMarca==21){
       		marca='ASESORES DEL SUR'; 
       	}else if(selectedMarca==16){
       		marca='MOREIRA'; 
       	}else if(selectedMarca==22){
       		marca='POLAB'; 
       	}else if(selectedMarca==25){
       		marca='BIOMEDICA DE REFERENCIA'; 
       	}else if(selectedMarca==26){
       		marca='PROMEDIC'; 
       	}
       	 
		if (confirm("Estas seguro de cancelar la factura A-" + facturaCancelar + " de la marca "+marca+" ?")) {
			MantenimientoOrdenFacturacion.cancelarFactura(facturaCancelar,frmPantalla.idUsuario.value,TypeObjeto(frmPantalla.selInformacionCopiar),frmPantalla.selMarca.value,CancelacionFacturacion_CallBack)
		}    			   
   }
   
   function CancelacionFacturacion_CallBack(data)
   {
	   alert(data);
   }
