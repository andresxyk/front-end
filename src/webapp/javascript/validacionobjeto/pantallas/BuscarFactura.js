
/******************** General ********************************/

   function init() 
   {
	    DWRUtil.useLoadingMessage();
   }
   
/********************* Negocio *******************************/
   
   function buscarFactura() {	
		var frmPantalla = window.document.frmBusqudaFactura;
		var key=window.event.keyCode;
		
		//if(key==13){
		    if (frmPantalla.txtBuscarFactura.value != "" && frmPantalla.txtBuscarFactura.value > 0)  {
				adminDIV("gridbusquedaFacturas","visible","inline");
		    	BusquedaFacturas.buscarFacturas(frmPantalla.txtBuscarFactura.value,buscarFactura_CallBack);
			}else{
				alert('Debe ingresar un folio de factura adecuado');
			} 
		 //}
	}
   
   function buscarComplemento() {	
		var frmPantalla = window.document.frmBusqudaComplemento; 
		var key=window.event.keyCode;
		
		//if(key==13){
		    if (frmPantalla.txtBuscarComplemento.value != "" && frmPantalla.txtBuscarComplemento.value > 0)  {
				adminDIV("gridbusquedaComplemento","visible","inline");
		    	BusquedaFacturas.buscarComplemento(frmPantalla.txtBuscarComplemento.value,buscarComplemento_CallBack);
			}else{
				alert('Debe ingresar un folio de complemento de pago adecuado');
			} 
		 //}
	}
   
  function buscarFactura_CallBack(data){
	  adminDIV("gridbusquedaFacturas","visible","inline");
	  codeDIVHTML("gridbusquedaFacturas",data);
	  
  }
  
  function buscarComplemento_CallBack(data){
	  adminDIV("gridbusquedaComplemento","visible","inline");
	  codeDIVHTML("gridbusquedaComplemento",data);
	  
  }
  
  function generacionPrevioFactura(kfactura)
  {
      	if (kfactura !='') {
				showPopWin("/web2labportal/jsp/soporteFactura.jsp?kfactura="+kfactura, 300, 300, "Previo");
		} else {
			alert('Debe seleccionar al menos un bloque y tipo de Reporte que requiere');
		}   			   
  }
  
  
  function limpiar(){
		var frmPantalla = window.document.frmBusqudaFactura;
		frmPantalla.txtBuscarFactura.value="";
		adminDIV("gridbusquedaFacturas","hidden","none");
		frmPantalla.txtBuscarFactura.focus();
		
	  }
  
  function limpiarComplemento(){
		var frmPantalla = window.document.frmBusqudaComplemento;
		frmPantalla.txtBuscarComplemento.value="";
		adminDIV("gridbusquedaComplemento","hidden","none");
		frmPantalla.txtBuscarComplemento.focus();
		
	  }
  
  function visualizarFactura(strRuta) {
	  BusquedaFacturas.findCfdiPdf(strRuta,file_CallBack);
	}
  
  function file_CallBack(data)
	{
		snombre = "Factura";
		abrirVentanaOrden(data,snombre);	 
	}	