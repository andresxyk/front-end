var objExamenConfiguracionBean = new ExamenConfiguracionBean();

function consultaExamenesGrid() {		
    var frmPantalla = window.document.frmConfiguracionExamen;
    if ((frmPantalla.txtClaveExamen.value.length > 3) || (frmPantalla.txtNombreExamen.value.length > 4) || (TypeObjeto(frmPantalla.cboDepartamento) > 0)) {
		LoadBasicDatosFiscales(frmPantalla);
		DatosFiscales.showDatosFiscales(objExamenConfiguracionBean,consultaDatosExamenGrid_CallBack);        	
	} else {
	 	codeDIVHTML("gridbusquedaDatosFiscales","");
	    adminDIV("gridbusquedaDatosFiscales","hidden","none");	 
	}
}	

function consultaDatosExamenGrid_CallBack(data) {
	
}

function LoadBasicDatosFiscales() {
    var frmPantalla = window.document.frmConfiguracionExamen;
    if (frmPantalla.txtClaveExamen.value.length > 3) {
    	objExamenConfiguracionBean.cExamen=frmPantalla.txtClaveExamen.value;
		objExamenConfiguracionBean.sExamen="";
		objExamenConfiguracionBean.cDepartamento=0;    	
    } else if (frmPantalla.txtNombreExamen.value.length > 4) {
    	objExamenConfiguracionBean.cExamen=0;
		objExamenConfiguracionBean.sExamen=frmPantalla.txtNombreExamen.value;
		objExamenConfiguracionBean.cDepartamento=0;    	
    } else if (TypeObjeto(frmPantalla.cboDepartamento) > 0) {
    	objExamenConfiguracionBean.cExamen=0;
		objExamenConfiguracionBean.sExamen="";
		objExamenConfiguracionBean.cDepartamento=TypeObjeto(frmPantalla.cboDepartamento);    	
    }
}

function ExamenConfiguracionBean() {	
	cExamen=null,		
	sExamen=null,
	sNemonico=null,
	cGenero=null,
	sGenero=null,
	cDepartamento=null,
	sDepartamento=null,
	cIndicacionesPaciente=null,
	sIndicacionesPaciente=null,	
	ctipomuestra=null,	
	stipomuestra=null,	
	cinsumo=null,
	sinsumo=null,	
	cindicaciontomador=null,
	sindicaciontomador=null,
	ctemperaturamuestra=null,
	stemperaturamuestra=null,
	cmotivorechazo=null,	
	smotivorechazo=null,
	ctiempoproceso=null,
	stiempoproceso=null
}	