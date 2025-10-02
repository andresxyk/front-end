
// var hostServerApache = "http://10.3.0.8:9085"; 
// var hostServerWebApp = "http://10.3.0.8:8192"; 
 
var hostServerApache = "http://10.3.0.8:9085"; 
 var hostServerWebApp = "http://10.3.0.8:8192";

	function init() {
		//DWRUtil.useLoadingMessage();
		if (parseInt(window.document.frmPagoFactura.txtkFactura.value) > 0) {
			CuentasxCobrarMayoreo.getPagoFactura(window.document.frmPagoFactura.txtkFactura.value,initFactura_CallBack);					
		} else {
		    adminDIV("gridRegistroPago","hidden","none");			
		    adminDIV("gridBuscarFactura","visible","inline");	     
			document.getElementById("txtFolioFactura").focus(); 		    
		}
	}
	
	function nuevoFactura() {
		var frmPantalla = window.document.frmPagoFactura;
		frmPantalla.btnRegPago.disabled = false;
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
    	
			if(data.msaldo === 0)
				frmPantalla.btnRegPago.disabled = true;
				
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
    			try{		
    			document.getElementById('txtFechaDeposito').value = "";				
    			document.getElementById('txtImportePago').value = "";				
    			document.getElementById("txtImportePago").focus(); 		
				}catch (error){
					console.log(error);
				}		
        }							
    }

	function reimprimirFacturaPDF() {
		var frmPantalla = window.document.frmPagoFactura;
		var selectedMarca = document.getElementById("selMarca").value;
		snombre = "FacturaOrden";
		if(selectedMarca==1){


			strRuta = hostServerApache+"/FacturasElectronicas_Olab/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";


			abrirVentanaOrden(strRuta,snombre);	   			     			    
		    return true;
       	} else if(selectedMarca==4){


       		strRuta = hostServerApache+"/FacturasElectronicas_Azteca/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";


       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;

       	}else if(selectedMarca==5 || selectedMarca==15){

       		strRuta = hostServerApache+"/FacturasElectronicas_Swisslab/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";


       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==7){

       		strRuta = hostServerApache+"/FacturasElectronicas_Jenner/Prado/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==8){

       		strRuta = hostServerApache+"/FacturasElectronicas_Jenner/Lean/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==19){

       		strRuta = hostServerApache+"/FacturasElectronicas_FamilyLabs/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==20){

       		strRuta = hostServerApache+"/FacturasElectronicas_Exakta/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==21){

       		strRuta = hostServerApache+"/FacturasElectronicas_AsesoresSur/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==16){

       		strRuta = hostServerApache+"/FacturasElectronicas_Moreira/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==22){

       		strRuta = hostServerApache+"/FacturasElectronicas_Polab/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==25){

       		strRuta = hostServerApache+"/FacturasElectronicas_BiomedicaReferencia/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==26){

       		strRuta = hostServerApache+"/FacturasElectronicas_Promedic/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if (selectedMarca==9){
       		strRuta = hostServerApache+"/FacturasElectronicas_SwissHospital/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
//       		var kfactura = frmPantalla.txtkFactura.value;
//       		CuentasxCobrarMayoreo.getMarcaKfactura(kfactura,marcaKfactura2_CallBack);
       	}	
	}
	
	function marcaKfactura2_CallBack(data){
		var marca =data;
		var frmPantalla = window.document.frmPagoFactura;
		snombre = "FacturaOrden";
		if(marca==1){

			strRuta = hostServerApache+"/FacturasElectronicas_Olab/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";


       	} else if(marca==4){

       		strRuta = hostServerApache+"/FacturasElectronicas_Azteca/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";

       	}else if(marca==5 || marca==15){

       		strRuta = hostServerApache+"/FacturasElectronicas_Swisslab/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";

       	}else if(marca==7){

       		strRuta = hostServerApache+"/FacturasElectronicas_Jenner/Prado/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf"; 

       	}else if(marca==19){

       		strRuta = hostServerApache+"/FacturasElectronicas_FamilyLabs/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf"; 

       	}else if(marca==20){

       		strRuta = hostServerApache+"/FacturasElectronicas_Exakta/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf"; 

       	}else if(marca==21){

       		strRuta = hostServerApache+"/FacturasElectronicas_AsesoresSur/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf"; 

       	}else if(marca==16){

       		strRuta = hostServerApache+"/FacturasElectronicas_Moreira/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf"; 

       	}else if(marca==22){

       		strRuta = hostServerApache+"/FacturasElectronicas_Polab/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf"; 

       	}else if(marca==25){

       		strRuta = hostServerApache+"/FacturasElectronicas_BiomedicaReferencia/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf"; 

       	}else if(marca==26){

       		strRuta = hostServerApache+"/FacturasElectronicas_Promedic/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf"; 

       	}else if(marca==9){

       		strRuta = hostServerApache+"/FacturasElectronicas_SwissHospital/XMLTMP/PDF/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".pdf";

       	}
		abrirVentanaOrden(strRuta,snombre);	   			     			    
	    return true; 
	}

	function reimprimirFacturaXML() {
		var frmPantalla = window.document.frmPagoFactura;
		var selectedMarca = document.getElementById("selMarca").value;
		snombre = "FacturaOrden";
		
		if(selectedMarca==1){

			strRuta = hostServerApache+"/FacturasElectronicas_Olab/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

			abrirVentanaOrden(strRuta,snombre);	   			     			    
		    return true;
       	} else if(selectedMarca==4){

       		strRuta = hostServerApache+"/FacturasElectronicas_Azteca/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;

       	}else if(selectedMarca==5 || selectedMarca==15){

       		strRuta = hostServerApache+"/FacturasElectronicas_Swisslab/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==7){

       		strRuta = hostServerApache+"/FacturasElectronicas_Jenner/Prado/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
       	}else if(selectedMarca==8){

       		strRuta = hostServerApache+"/FacturasElectronicas_Jenner/Lean/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true; 
       	}else if(selectedMarca==19){

       		strRuta = hostServerApache+"/FacturasElectronicas_FamilyLabs/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true; 
       	}else if(selectedMarca==20){

       		strRuta = hostServerApache+"/FacturasElectronicas_Exakta/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true; 
       	}else if(selectedMarca==21){

       		strRuta = hostServerApache+"/FacturasElectronicas_AsesoresSur/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true; 
       	}else if(selectedMarca==16){

       		strRuta = hostServerApache+"/FacturasElectronicas_Moreira/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true; 
       	}else if(selectedMarca==22){

       		strRuta = hostServerApache+"/FacturasElectronicas_Polab/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true; 
       	}else if(selectedMarca==25){

       		strRuta = hostServerApache+"/FacturasElectronicas_BiomedicaReferencia/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true; 
       	}else if(selectedMarca==26){

       		strRuta = hostServerApache+"/FacturasElectronicas_Promedic/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true; 
       	}else if (selectedMarca==9){
       		strRuta = hostServerApache+"/FacturasElectronicas_SwissHospital/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       		abrirVentanaOrden(strRuta,snombre);	   			     			    
    	    return true;
//       		var kfactura = frmPantalla.txtkFactura.value;
//       		CuentasxCobrarMayoreo.getMarcaKfactura(kfactura,marcaKfactura_CallBack);
       	}		
		
	}
	
	function marcaKfactura_CallBack(data){
		var marca =data;
		var frmPantalla = window.document.frmPagoFactura;
		snombre = "FacturaOrden";
		if(marca==1){

			strRuta = hostServerApache+"/FacturasElectronicas_Olab/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       	} else if(marca==4){

       		strRuta = hostServerApache+"/FacturasElectronicas_Azteca/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       	}else if(marca==5 || marca==15){

       		strRuta = hostServerApache+"/FacturasElectronicas_Swisslab/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

       	}else if(marca==7){

       		strRuta = hostServerApache+"/FacturasElectronicas_Jenner/Prado/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml"; 

       	}else if(marca==19){

       		strRuta = hostServerApache+"/FacturasElectronicas_FamilyLabs/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml"; 

       	}else if(marca==20){

       		strRuta = hostServerApache+"/FacturasElectronicas_Exakta/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml"; 

       	}else if(marca==21){

       		strRuta = hostServerApache+"/FacturasElectronicas_AsesoresSur/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml"; 

       	}else if(marca==16){

       		strRuta = hostServerApache+"/FacturasElectronicas_Moreira/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml"; 

       	}else if(marca==22){

       		strRuta = hostServerApache+"/FacturasElectronicas_Polab/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml"; 

       	}else if(marca==25){

       		strRuta = hostServerApache+"/FacturasElectronicas_BiomedicaReferencia/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml"; 

       	}else if(marca==26){

       		strRuta = hostServerApache+"/FacturasElectronicas_Promedic/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml"; 

       	}else if(marca==9){

       		strRuta = hostServerApache+"/FacturasElectronicas_SwissHospital/XML/FacturacionElectronica_" + frmPantalla.txtNumeroFactura.value + ".xml";

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
       	}else if(selectedMarca==10){
       		marca='SERIE B'; 
       	} else if(selectedMarca==15){
       		marca='LIACSA'; 
       	} else if(selectedMarca==19){
       		marca='FAMILYLABS NORTE'; 
       	} else if(selectedMarca==20){
       		marca='EXAKTA'; 
       	} else if(selectedMarca==21){
       		marca='ASESORES DEL SUR'; 
       	} else if(selectedMarca==16){
       		marca='MOREIRA'; 
       	} else if(selectedMarca==22){
       		marca='POLAB'; 
       	} else if(selectedMarca==25){
       		marca='BIOMEDICA DE REFERENCIA'; 
       	} else if(selectedMarca==26){ 
       		marca='PROMEDIC'; 
       	} else if(selectedMarca==9){
       		marca='SWISS HOSPITAL'; 
       	} 
       	var tipoVM = frmPantalla.idVMRegistro.value;
       	var pagos ;
       	if(tipoVM == 1){
       		pagos= true;
       	}else{
       		pagos= false;
       	}
       	 
       	if (confirm("Estas seguro de buscar el folio " + window.document.frmPagoFactura.txtFolioFactura.value + " de la marca "+marca+" ?")) {
			CuentasxCobrarMayoreo.buscaFacturaFolio(window.document.frmPagoFactura.txtFolioFactura.value,frmPantalla.selMarca.value,pagos,initFactura_CallBack);
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
		
		var checkboxSustitucion = document.getElementById("chkAgregarSustitucion").checked;
		var txtfoliosustitucion = document.getElementById("txtUfoliofacturaSustitucion").value;
		var txtUuidSustitucion = document.getElementById("hdenUuidSustitucion").value;
		
		var txtConfirmacionOpcionales = "";
		if(txtRfcBanco != ""){
			txtConfirmacionOpcionales += "Rfc del Banco: "+txtRfcBanco+"\n";
		}
		if(txtNomBanco != ""){
			txtConfirmacionOpcionales += "Nombre del Banco: "+txtNomBanco+"\n";
		}
		if(txtNomCuentaClabe != ""){
			txtConfirmacionOpcionales += "Numero de Cuenta/Clabe: "+txtNomCuentaClabe+"\n";
		}
		if(txtNomOperacion != ""){
			txtConfirmacionOpcionales += "Numero de Operacion: "+txtNomOperacion+"\n";
		}
		 
		if (pago != null) {
			if (( parseInt(pago) > 0) && ( parseInt(saldo) >=  parseInt(pago))) {
				if((sFechaPago!=null) && (sFechaPago!='') && (parseInt(cFormaPago)!=0)){
					if (confirm("Estas seguro de registrar el pago por $" + pago + " para la factura " + strfactura + "?")) {
						if(checkboxOpcional){
							if(confirm("Estas seguro de agregar los siguientes campos opcionales?\n"+txtConfirmacionOpcionales)){
								frmPantalla.btnRegPago.disabled = true;
							CuentasxCobrarMayoreo.pagoFactura(intFactura,anticipo,pago,saldo,cTipoPago,idUsuario,sFechaPagoCompleta,1,
									checkbox,parseInt(cFormaPago),selectedMarca,txtRfcBanco,txtNomBanco,txtNomCuentaClabe,txtNomOperacion,
									checkboxSustitucion,txtfoliosustitucion,txtUuidSustitucion,pagoFactura_CallBack);
									pagos = true;
									CuentasxCobrarMayoreo.buscaFacturaFolio(window.document.frmPagoFactura.txtFolioFactura.value,frmPantalla.selMarca.value,pagos,initFactura_CallBack);
									adminDIV("gridEstadoFactura","visible","inline");
							}
						}else{
							frmPantalla.btnRegPago.disabled = true;
							CuentasxCobrarMayoreo.pagoFactura(intFactura,anticipo,pago,saldo,cTipoPago,idUsuario,sFechaPagoCompleta,1,
									checkbox,parseInt(cFormaPago),selectedMarca,"","","","",
									checkboxSustitucion,txtfoliosustitucion,txtUuidSustitucion,pagoFactura_CallBack);
									pagos = true;
									CuentasxCobrarMayoreo.buscaFacturaFolio(window.document.frmPagoFactura.txtFolioFactura.value,frmPantalla.selMarca.value,pagos,initFactura_CallBack);
									adminDIV("gridEstadoFactura","visible","inline");
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
	
	function showCamposSustitucion(){
		if (document.getElementById("chkAgregarSustitucion").checked){	
			adminDIV("divCamposSustitucion","visible","inline");
			document.getElementById("txtUfoliofacturaSustitucion").focus();
		}else{
			adminDIV("divCamposSustitucion","hidden","none");
			document.getElementById("chkAgregarSustitucion").checked = false;
			document.getElementById("txtUfoliofacturaSustitucion").value="";
		}
	}
	
	function showCheckSustitucion(){
		if (document.getElementById("chkCrearComplento").checked){	
			adminDIV("divCheckSustitucion","visible","inline");
			adminDIV("divCamposSustitucion","hidden","none");
			document.getElementById("txtUfoliofacturaSustitucion").value="";
			document.getElementById("chkAgregarSustitucion").checked = false;
		}else{
			adminDIV("divCheckSustitucion","hidden","none");
			adminDIV("divCamposSustitucion","hidden","none");
			document.getElementById("txtUfoliofacturaSustitucion").value="";
			document.getElementById("chkAgregarSustitucion").checked = false;
		}
	}
	
	var ventana_secundaria = null;     
	   function showSubModalSustitucion() {
		   if (ventana_secundaria != null){
			   ventana_secundaria.close();
		   }
		   
		   var ufoliofactura = document.getElementById("txtFolioFactura").value; 
		   var selectedMarca = document.getElementById("selMarca").value; 
		   ventana_secundaria = window.open('/web2labportal/servlet/template/web2lab,sustitucion,SustitucionFactura.vm?ufoliofactura='+ufoliofactura+'&cmarca='+selectedMarca+'&tipoFactura=3&cconvenio=0',"SustitucionFactura","width=900,height=300,top=200,left=200,menubar=no,scrollbars=yes");
		   //('/web2labportal/servlet/template/web2lab,sustitucion,SustitucionFactura.vm?ufoliofactura='+ufoliofactura+'&cmarca='+selectedMarca, 950, 300, "Sustitucion");
	   }
	      
	   function agregarValoresSustitucion(folio,kfactura,uuid){
		   document.getElementById("txtUfoliofacturaSustitucion").value=folio;
		   document.getElementById("hdenkfacturaSustitucion").value=kfactura;
		   document.getElementById("hdenUuidSustitucion").value=uuid;
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

		
		if(data.smensaje!="Exito en el registro del Pago"){
			alert(data.smensaje);
		}else{
			alert(data.smensaje);
			var checkbox =	document.getElementById("chkCrearComplento").checked;
			document.getElementById('txtFechaDeposito').value = "";
			document.getElementById('txtImportePago').value = "";
			CuentasxCobrarMayoreo.getPagoFactura(window.document.frmPagoFactura.txtkFactura.value,initFactura_CallBack);	
			if((data.smensaje=="Exito en el registro del Pago") && (checkbox)){
				window.open(hostServerWebApp+"/facturas/complemento-pagos/"+data.keypago, "_blank");
				CuentasxCobrarMayoreo.getPagoFactura(window.document.frmPagoFactura.txtkFactura.value,initFactura_CallBack);
			}

		}
	}	
	
	
	/*** Versi&oacute;n 25 de Marzo 2013*/ 
	function reversarPago() {
		var frmPantalla = window.document.frmPagoFactura;
		var intFactura = frmPantalla.txtkFactura.value;
		var idUsuario = frmPantalla.idUsuario.value; 
		var strfactura = frmPantalla.strNumeroFactura.value;
		
		if (confirm("Estas seguro de reversar el ultimo pago registrado para la factura " + strfactura + "?")) {
			CuentasxCobrarMayoreo.reversarPago(intFactura,idUsuario,reversarPago_CallBack);			
		}
				
	}
	
	function reversarPagoSeleccionado() {
			var frmPantalla = window.document.frmPagoFactura;
			var intFactura = frmPantalla.txtkFactura.value;
			var idUsuario = frmPantalla.idUsuario.value; 
			var strfactura = frmPantalla.strNumeroFactura.value;
			var chkBox = frmPantalla.chkBoxFactura.value;
			const checkboxes = frmPantalla.chkBoxFactura;
			const checkArray = [];

			if (checkboxes instanceof NodeList || checkboxes instanceof HTMLCollection || Array.isArray(checkboxes)) {
			  for (let i = 0; i < checkboxes.length; i++) {
			    checkArray.push(checkboxes[i]);
			  }
			} else if (checkboxes) {
			  checkArray.push(checkboxes);
			}
		
			const valoresSeleccionados = Array.from(checkArray)
			  .filter(checkbox => checkbox.checked)
			  .map(checkbox => checkbox.value);
			const kfactura = [];
			const kpagofactura = [];

			valoresSeleccionados.forEach(item => {
			  const [factura, pagofactura] = item.split('-');
			  kfactura.push(factura);
			  kpagofactura.push(pagofactura);
			});

			const strKfactura = kfactura.join(',');
			const strKpagofactura = kpagofactura.join(',');
			
			if (confirm("Estas seguro de reversar el ultimo pago registrado para las facturas " + strKpagofactura + "?")) {
				CuentasxCobrarMayoreo.reversarPagoSeleccionado(strKfactura,idUsuario, strKpagofactura,reversarPago_CallBack);			
			}
					
		}
	
	/*** Versi&oacute;n 25 de Marzo 2013*/
	function reversarPago_CallBack(data) {
		alert(data);
		nuevoFactura();
	}
	
	/*** Versi&oacute;n 25 de Marzo 2013*/
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
			alert('Debes buscar una factura v&aacute;lida');
		}
	} 
	
	function actualizarEstadoFactura_CallBack(data) {
		alert(data);
		nuevoFactura();
		adminDIV("gridEstadoFactura","hidden","none");
	}
	
	function visualizarFactura(strRuta) {
		CuentasxCobrarMayoreo.findCfdiPdf(strRuta,file_CallBack);
		  			     			    
	}
	
	function file_CallBack(data)
	{
		snombre = "Factura";
		abrirVentanaOrden(data,snombre);	 
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