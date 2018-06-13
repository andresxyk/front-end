 var regaloBean = new CRegalo();


	function init(intActividad) {
		DWRUtil.useLoadingMessage();
	}
	
	function altaRegalos_CallBack(data){
		alert("Se dio de alta con el numero" + data.cregalo);
	}
	
	function altaRegalos() {
		var frmPantalla = window.document.frmRegalosMedico;
		if (confirm("Estas seguro de registrar el regalo " + frmPantalla.txtNombreRegalo.value + " con un valor en puntos de  " + frmPantalla.txtTotalPuntos.value + "?")) {		
			loadBeanRegalo();
			MedicosPuntos.altRegaloMedicos(regaloBean,altaRegalos_CallBack)
		}
	}

	 function loadBeanRegalo() {
		var frmPantalla = window.document.frmRegalosMedico;
		regaloBean.sregalo = frmPantalla.txtNombreRegalo.value;		
		regaloBean.uvalorpuntos = frmPantalla.txtTotalPuntos.value;		
		regaloBean.ucategoriamedico = frmPantalla.txtClasificacionMedico.value;		
	 }
	
	 function CRegalo() {	
	    cregalo=null, 
	    sregalo=null, 
	    uvalorpuntos=null, 
	    ucategoriamedico=null 
	}	

	