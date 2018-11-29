
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
        cargarHora();
    							
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
		var selectedMarca = document.getElementById("selMarca").value;
		snombre = "FacturaOrden";
		if(selectedMarca==1){
			strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Olab/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";
			abrirVentanaOrden(strRuta,snombre);	   			     			    
		    return true;
       	} else if(selectedMarca==4){
       		strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Azteca/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";
       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==5){
       		strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Swisslab/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";
       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==7){
       		strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Jenner/Prado/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";
       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==8){
       		strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Jenner/Lean/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";
       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if (selectedMarca==9){
       		var kfactura = frmPantalla.txtkFactura.value;
       		CuentasxCobrarMayoreo.getMarcaKfactura(kfactura,marcaKfactura2_CallBack);
       	}	
	}
	
	function marcaKfactura2_CallBack(data){
		var marca =data;
		var frmPantalla = window.document.frmPagoFactura;
		snombre = "FacturaOrden";
		if(marca==1){
			strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Olab/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";
       	} else if(marca==4){
       		strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Azteca/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";
       	}else if(marca==5){
       		strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Swisslab/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";
       	}else if(marca==7){
       		strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Jenner/Prado/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf"; 
       	}
		abrirVentanaOrden(strRuta,snombre);	   			     			    
	    return true; 
	}

	function reimprimirFacturaXML() {
		var frmPantalla = window.document.frmPagoFactura;
		var selectedMarca = document.getElementById("selMarca").value;
		snombre = "FacturaOrden";
		
		if(selectedMarca==1){
			strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Olab/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";
			abrirVentanaOrden(strRuta,snombre);	   			     			    
		    return true;
       	} else if(selectedMarca==4){
       		strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Azteca/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";
       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==5){
       		strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Swisslab/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";
       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==7){
       		strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Jenner/Prado/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";
       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==8){
       		strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Jenner/Lean/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";
       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true; 
       	}else if (selectedMarca==9){
       		var kfactura = frmPantalla.txtkFactura.value;
       		CuentasxCobrarMayoreo.getMarcaKfactura(kfactura,marcaKfactura_CallBack);
       	}		
		
	}
	
	function marcaKfactura_CallBack(data){
		var marca =data;
		var frmPantalla = window.document.frmPagoFactura;
		snombre = "FacturaOrden";
		if(marca==1){
			strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Olab/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";
       	} else if(marca==4){
       		strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Azteca/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";
       	}else if(marca==5){
       		strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Swisslab/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";
       	}else if(marca==7){
       		strRuta = "http://201.150.42.46:9085/FacturasElectronicas_Jenner/Prado/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml"; 
       	}
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
       	}else if(selectedMarca==7){
       		marca='JENNER PRADO'; 
       	}else if(selectedMarca==8){
       		marca='JENNER LEAN'; 
       	}else if(selectedMarca==9){
       		marca='SERIE B'; 
       	}
       	if (confirm("Estas seguro de buscar el folio " + window.document.frmPagoFactura.txtFolioFactura.value + " de la marca "+marca+" ?")) {
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
		var shora = frmPantalla.selhora.value;
		var sminutos = frmPantalla.selminutos.value;
		var ssegundos = frmPantalla.selsegundos.value;
		var cFormaPago = frmPantalla.selFormaPago.value;
		var checkbox =	document.getElementById("chkCrearComplento").checked;
		var selectedMarca = document.getElementById("selMarca").value;
		
		var sFechaPagoCompleta =sFechaPago+" "+shora+":"+sminutos+":"+ssegundos;
		
		var checkboxOpcional = document.getElementById("chkAgregarOpcionales").checked;
		var txtRfcBanco = document.getElementById("txtRfcBanco").value;
		var txtNomBanco = document.getElementById("txtNombreBanco").value;
		var txtNomCuentaClabe= document.getElementById("txtNumCuentaClabe").value;
		var txtNomOperacion= document.getElementById("txtNumOperacion").value;
		
		if (pago != null) {
			if (( parseInt(pago) > 0) && ( parseInt(saldo) >=  parseInt(pago))) {
				if((sFechaPago!=null) && (sFechaPago!='') && (parseInt(cFormaPago)!=0)){
					if (confirm("Estas seguro de registrar el pago por $" + pago + " para la factura " + strfactura + "?")) {
						if(checkboxOpcional){
							CuentasxCobrarMayoreo.pagoFactura(intFactura,anticipo,pago,saldo,cTipoPago,idUsuario,sFechaPagoCompleta,1,
									checkbox,parseInt(cFormaPago),selectedMarca,txtRfcBanco,txtNomBanco,txtNomCuentaClabe,txtNomOperacion,pagoFactura_CallBack);
						}else{
							CuentasxCobrarMayoreo.pagoFactura(intFactura,anticipo,pago,saldo,cTipoPago,idUsuario,sFechaPagoCompleta,1,
									checkbox,parseInt(cFormaPago),selectedMarca,"","","","",pagoFactura_CallBack);
						}
					}
				}else{
					if(parseInt(cFormaPago)==0){
						alert("Tienes que seleccionar una Forma de Pago");
					}
					if(sFechaPago==null || sFechaPago==''){
						alert("Tienes que ingresar una fecha correcta");						
					}
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
	
	function showCamposOpcionales(){
		if (document.getElementById("chkAgregarOpcionales").checked){	
			adminDIV("divCamposOpcionales","visible","inline");
			document.getElementById("txtUfoliofactura").focus();
		}else{
			adminDIV("divCamposOpcionales","hidden","none");
			document.getElementById("chkAgregarOpcionales").checked = false;
			document.getElementById("txtRfcBanco").value="";
			document.getElementById("txtNombreBanco").value="";
			document.getElementById("txtNumCuentaClabe").value="";
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
		var checkbox =	document.getElementById("chkCrearComplento").checked;
		document.getElementById('txtFechaDeposito').value = "";
		document.getElementById('txtImportePago').value = "";
		CuentasxCobrarMayoreo.getPagoFactura(window.document.frmPagoFactura.txtkFactura.value,initFactura_CallBack);	
		if((data.smensaje=="Exito en el registro del Pago") && (checkbox)){
			window.open("http://201.150.42.46:8192/facturas/complemento-pagos/"+data.keypago, "_blank");
		}
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
	
	function visualizarFactura(strRuta) {
		snombre = "Factura";
		abrirVentanaOrden(strRuta,snombre);	   			     			    
	}
	
	function cargarHora(){
		document.getElementById("selhora").innerHTML="";
		document.getElementById("selminutos").innerHTML="";
		document.getElementById("selsegundos").innerHTML="";

		var selecthora = document.getElementById('selhora');
		for (var i = 0; i<=23; i++){
		    var opt = document.createElement('option');
		    opt.value = i;
		    if(i<10){
		    	opt.innerHTML = '0'+i+' hrs';
		    }else{
		    	if (i==12) {
		    		opt.selected=true;
		    		opt.innerHTML = i+' hrs';
		    	}else{
		    		opt.innerHTML = i+' hrs';
		    	}	
		    }		    
		    selecthora.appendChild(opt);
		}		

		var selectmin = document.getElementById('selminutos');
		for (var i = 0; i<=59; i++){
		    var opt = document.createElement('option');
		    opt.value = i;
		    if(i<10){
		    	opt.innerHTML = '0'+i+' min';
		    }else{		    	
	    		opt.innerHTML = i+' min';		    		
		    }		    
		    selectmin.appendChild(opt);
		}
		var selectseg = document.getElementById('selsegundos');
		for (var i = 0; i<=59; i++){
		    var opt = document.createElement('option');
		    opt.value = i;
		    if(i<10){
		    	opt.innerHTML = '0'+i+' seg';
		    }else{
		    	opt.innerHTML = i+' seg';
		    }		    
		    selectseg.appendChild(opt);
		}
	}
