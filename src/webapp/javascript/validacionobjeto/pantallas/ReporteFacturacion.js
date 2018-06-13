function init() 
   {
	    DWRUtil.useLoadingMessage();
   }

function repFacturacionSelect(liga, nombre){
	var fecha1 = document.reporte.txtDeFecha.value;
	var fecha2 = document.reporte.txtAFecha.value;
	var opc = document.reporte.selReporte[document.reporte.selReporte.selectedIndex].value;
	var convenio = document.reporte.selConvenios[document.reporte.selConvenios.selectedIndex].value;
	
	var url='';
	
	if (fecha1=='' || fecha2==''){
		alert('Es necesario capturar las Fechas...');
		return false;
	}
	
	if(opc==5){
		alert('Entra a opc = 5  '+fecha1 +'   '+fecha2);
		showPopWin("/web2labportal/jsp/ReporteFacturasSerieB.jsp?fecha1="+fecha1+"&fecha2="+fecha2, 300, 300, "Previo");
		return  true;
	}else{	
		url = liga+'?TipoReporte='+opc+'&txtDeFecha='+fecha1+'&txtAFecha='+fecha2+'&cconvenio='+convenio+'&eventSubmit_doImprimir=action';
		abrirVentana(url,nombre);
		return  true;
	}
}

function buscarConvenioRapido(objConvenio,strBuscar) {
    var frmPantalla = window.document.facturacion;		
		if ((strBuscar.length == 9) && (validaSoloNumeros(strBuscar))) {
				var strPromocion = strBuscar.substring(0,4);
				
				if (strPromocion.substring(0,1) == "0") {
					buscaTextoSelect(objConvenio,strPromocion.substring(1));
				} else {
					buscaTextoSelect(objConvenio,strPromocion);
				}
		} else {
			buscaTextoSelect(objConvenio,strBuscar);
			displayCargaDatos();
		}
	}

function limpiaPantalla()
{
	   var frmPantalla = window.document.reporte;
	   valorCombo(frmPantalla.selConvenios,0);
	   frmPantalla.txtBuscarConvenio.value="";
	   document.getElementById("txtBuscarConvenio").focus();
	   frmPantalla.txtAFecha.value="";
	   frmPantalla.txtDeFecha.value="";
}

