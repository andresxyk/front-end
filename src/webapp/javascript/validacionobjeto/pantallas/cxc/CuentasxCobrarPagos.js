
	function init() {
		DWRUtil.useLoadingMessage();
		if (parseInt(window.document.frmPagoFactura.txtkFactura.value) > 0) {
			CuentasxCobrarMayoreo.getPagoFactura(window.document.frmPagoFactura.txtkFactura.value,initFactura_CallBack);					
		} else {
		    adminDIV("gridRegistroPago","hidden","none");			
		    adminDIV("gridBuscarFactura","visible","inline");	    
			document.getElementById("txtFolioFactura").focus(); 		    
		}
	}
	
	function nuevoFactura() {
	    adminDIV("gridRegistroPago","hidden","none");			
	    adminDIV("gridBuscarFactura","visible","inline");	    			
	    adminDIV("gridPagos","hidden","none");			
	    document.getElementById('txtFolioFactura').value = "";	    
		document.getElementById("txtFolioFactura").focus();
		adminDIV("gridEstadoFactura","hidden","none");
	}

    function initFactura_CallBack(data) {
    		
    	var frmPantalla = window.document.frmPagoFactura;							
        adminDIV("gridBuscarFactura","hidden","none");							
    							
    		document.getElementById('txtTotalFactura').className = 'textflat';					
    		frmPantalla.txtTotalFactura.disabled = true;					
    		frmPantalla.txtTotalFactura.value = "$" + data.mtotalfactura;					
    		frmPantalla.hidTotalFactura.value = data.mtotalfactura;					
        							
    		document.getElementById('txtAnticipoFactura').className = 'textflat';					
    		frmPantalla.txtAnticipoFactura.disabled = true;					
    		frmPantalla.txtAnticipoFactura.value = "$" + data.manticipo;					
    		frmPantalla.hidAnticipoFactura.value = data.manticipo;					
        							
        	document.getElementById('txtSaldoFactura').className = 'textflat';						
    		frmPantalla.txtSaldoFactura.disabled = true;					
    		frmPantalla.txtSaldoFactura.value = "$" + data.msaldo;					
    		frmPantalla.hidSaldoFactura.value = data.msaldo;					
    							
    		document.getElementById('txtNumeroFactura').className = 'textflat';					
    		frmPantalla.txtNumeroFactura.disabled = true;					
    		frmPantalla.txtNumeroFactura.value = data.sformatofactura;					
    		frmPantalla.strNumeroFactura.value = data.sformatofactura;					
    							
    		frmPantalla.txtkFactura.value = data.kfactura;					
    		adminDIV("gridPagos","visible","inline");	    				
    		codeDIVHTML("gridPagos",data.sgridpagos);					
    		adminDIV("gridEstadoFactura","hidden","none");					
    		if(data.smensaje!='CANCELADA') {					
    			adminDIV("gridEstadoFactura","visible","inline");				
    			if (data.msaldo <= 0) {				
    				document.getElementById('txtImportePago').className = 'textflat';			
    				frmPantalla.txtImportePago.disabled = true;			
    				frmPantalla.txtImportePago.value = "";			
    							
    				document.getElementById('txtFechaDeposito').className = 'textflat';			
    				frmPantalla.txtFechaDeposito.disabled = true;			
    				frmPantalla.txtFechaDeposito.value = "";			
    							
    				adminDIV("gridRegistroPago","hidden","none");			
    			} else {				
    				adminDIV("gridRegistroPago","visible","inline");			
    			}				
    							
    			document.getElementById('txtFechaDeposito').value = "";				
    			document.getElementById('txtImportePago').value = "";				
    			document.getElementById("txtImportePago").focus(); 				
        }							
    }

	function reimprimirFacturaPDF() {
		var frmPantalla = window.document.frmPagoFactura;
		snombre = "FacturaOrden";
		strRuta = "http://192.237.150.70:9085/FacturasElectronicas_Olab/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";
	    abrirVentanaOrden(strRuta,snombre);	   			     			    
	    return true;
	}

	function reimprimirFacturaXML() {
		var frmPantalla = window.document.frmPagoFactura;
		snombre = "FacturaOrden";
		strRuta = "http://192.237.150.70:9085/FacturasElectronicas_Olab/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";
	    abrirVentanaOrden(strRuta,snombre);	   			     			    
	    return true;
	}
	
	function buscarFolio() {
		var frmPantalla = window.document.frmPagoFactura;
		var selectedMarca = document.getElementById("selMarca").value; 
       	var marca='';
       	if(selectedMarca==1){
       		marca='OLAB';
       	} else if(selectedMarca==4){
       		marca='AZTECA';
       	}else if(selectedMarca==5){
       		marca='SWISSLAB'; 
       	}
       	if (confirm("Estas seguro de buscar el folio" + window.document.frmPagoFactura.txtFolioFactura.value + " de la marca "+marca+" ?")) {
			CuentasxCobrarMayoreo.buscaFacturaFolio(window.document.frmPagoFactura.txtFolioFactura.value,frmPantalla.selMarca.value,initFactura_CallBack);
			adminDIV("gridEstadoFactura","visible","inline");
       	}
    }
    
	function buscarFolioKey() {
		var key=window.event.keyCode;		
		if(key==13){
			buscarFolio();
		}	   
	}	
	
	function pagoFactura() {
		var frmPantalla = window.document.frmPagoFactura;
		var pago = frmPantalla.txtImportePago.value;
		var saldo = frmPantalla.hidSaldoFactura.value;
		var anticipo = frmPantalla.hidAnticipoFactura.value;
		var strfactura = frmPantalla.strNumeroFactura.value;
		var intFactura = frmPantalla.txtkFactura.value;
		var sFechaPago = frmPantalla.txtFechaDeposito.value;
		var idUsuario = frmPantalla.idUsuario.value; 
		var cTipoPago = TypeObjeto(frmPantalla.selTipoPago);
		if (pago != null) {
			if (( parseInt(pago) > 0) && ( parseInt(saldo) >=  parseInt(pago))) {
				if (confirm("Estas seguro de registrar el pago por $" + pago + " para la factura " + strfactura + "?")) {
					CuentasxCobrarMayoreo.pagoFactura(intFactura,anticipo,pago,saldo,cTipoPago,idUsuario,sFechaPago,1,pagoFactura_CallBack);			
				}
			} else {
				alert("El pago debe ser mayor a $0 y menor a $" + frmPantalla.hidSaldoFactura.value);				
				frmPantalla.txtImportePago.value = frmPantalla.hidSaldoFactura.value;
			}
		} else {
			alert("El pago es nulo");
			frmPantalla.txtImportePago.value = frmPantalla.hidSaldoFactura.value;
		}
	}

	function validapagoFactura() {
		var frmPantalla = window.document.frmPagoFactura;
		var pago = frmPantalla.txtImportePago.value;
		var saldo = frmPantalla.hidSaldoFactura.value;
		if ((pago != null) && (parseInt(pago) > 0)) {
			if (( parseInt(pago) > 0) && ( parseInt(saldo) >=  parseInt(pago))) {

			} else {
				alert("El pago debe ser mayor a $0 y menor a $" + frmPantalla.hidSaldoFactura.value);				
				frmPantalla.txtImportePago.value = frmPantalla.hidSaldoFactura.value;
			}
		} else {
			if (parseInt(pago) > 0) {
				alert("El pago es nulo");
				frmPantalla.txtImportePago.value = frmPantalla.hidSaldoFactura.value;
			}
		}
	}	
	
	function pagoFactura_CallBack(data) {
		alert(data.smensaje);
		document.getElementById('txtFechaDeposito').value = "";
		document.getElementById('txtImportePago').value = "";
		CuentasxCobrarMayoreo.getPagoFactura(window.document.frmPagoFactura.txtkFactura.value,initFactura_CallBack);					
	}	
	
	/*** Versi�n 25 de Marzo 2013*/
	function reversarPago() {
		var frmPantalla = window.document.frmPagoFactura;
		var intFactura = frmPantalla.txtkFactura.value;
		var idUsuario = frmPantalla.idUsuario.value; 
		var strfactura = frmPantalla.strNumeroFactura.value;
		
		if (confirm("Estas seguro de reversar el ultimo pago registrado para la factura " + strfactura + "?")) {
			CuentasxCobrarMayoreo.reversarPago(intFactura,idUsuario,reversarPago_CallBack);			
		}
				
	}
	
	/*** Versi�n 25 de Marzo 2013*/
	function reversarPago_CallBack(data) {
		alert(data);
		nuevoFactura();
	}
	
	/*** Versi�n 25 de Marzo 2013*/
	function actualizaFactura(opcion){
		var frmPantalla = window.document.frmPagoFactura;
		var intFactura = frmPantalla.txtkFactura.value;
		var idUsuario = frmPantalla.idUsuario.value; 
		var strfactura = frmPantalla.strNumeroFactura.value;
		
		if((opcion>0) &&(intFactura>0)){
			if (confirm("Estas seguro de cambiar el estado de la factura " + strfactura + "?")) {
				CuentasxCobrarMayoreo.actualizarFactura(intFactura,idUsuario,opcion,actualizarEstadoFactura_CallBack);			
			}
		}else{
			alert('Debes buscar una factura v�lida');
		}
	}
	
	function actualizarEstadoFactura_CallBack(data) {
		alert(data);
		nuevoFactura();
		adminDIV("gridEstadoFactura","hidden","none");
	}
