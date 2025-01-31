
	function init() {
		DWRUtil.useLoadingMessage();
		
		BusquedaFacturas.buscarFacturaSustitucion(window.document.frmSustitucion.ufoliofactura.value,
				window.document.frmSustitucion.cmarca.value,
				window.document.frmSustitucion.tipofactura.value,
				window.document.frmSustitucion.cconvenio.value,
				buscarFacturaSustitucion_CallBack); 
		
	}
	
	function buscarFacturaSustitucion_CallBack(data){
		
		document.getElementById("divFacturasSustitucion").innerHTML=""; 
		var htmlOb=document.getElementById("divFacturasSustitucion");
		htmlOb.innerHTML=data; 
	   }
	         
	function agregarDatosSustitucion(folio,kfactura,uuid){
		
		window.opener.agregarValoresSustitucion(folio,kfactura,uuid);
		window.opener.focus();
		window.close();
	}
