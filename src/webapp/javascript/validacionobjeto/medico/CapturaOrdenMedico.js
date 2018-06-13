 var medicoBean = new MedicoBean();

 
 function showSubModalRegistraRegalo(frmPantalla) {
		showPopWin(frmPantalla.hdenRutaRegaloAlta.value, 800, 400, "Captura Regalo");
 }
 
 function consultaMedicoGrid(frmPantalla) 
 {		
	txtApellidoPaterno = frmPantalla.txtApellidoPaternoMedico;
	txtApellidoMaterno = frmPantalla.txtApellidoMaternoMedico;
	txtNombre = frmPantalla.txtNombreMedico;		 
	txtCodigoMedico = frmPantalla.txtCodigoMedico;
	if (txtApellidoMaterno.value == "" && txtApellidoPaterno.value == "" && txtNombre.value == "" && (txtCodigoMedico.value==0 || txtCodigoMedico.value=="" ) ) {
	 	codeDIVHTML("MedicoBusqueda","");
	    adminDIV("MedicoBusqueda","hidden","none");
	 	codeDIVHTML("gridbusquedaDireccion","");
	    adminDIV("gridbusquedaDireccion","hidden","none");
	} else {
		if (txtApellidoMaterno.value.length > 4 || txtApellidoPaterno.value.length > 4 || txtNombre.value.length > 4 || txtCodigoMedico.value > 0) {
		 	DatosMedico.consultaMedicosGrid(txtCodigoMedico.value,
		 									txtApellidoPaterno.value,
									        txtApellidoMaterno.value,
									        txtNombre.value,
									        consultaMedicoGrid_CallBack);
		}
	}
 }	

 function consultaMedicoGrid_CallBack(data)
 {
    adminDIV("MedicoBusqueda","visible","inline");
 	codeDIVHTML("MedicoBusqueda",data);
 	codeDIVHTML("gridbusquedaDireccion","");
    adminDIV("gridbusquedaDireccion","hidden","none");
 }	 

 function medicoAceptado(cMedico) 
 {
	 	DatosMedico.buscarMedico(cMedico,medicoAceptado_CallBack);
 }

 function medicoAceptadoClave(cMedicoClave) 
 {
	 	DatosMedico.buscarMedicoClave(cMedicoClave,medicoAceptado_CallBack);
 }
 
 function medicoECEClave(cMedicoClave) 
 {
	 	DatosMedico.buscarMedicoClave(cMedicoClave,medicoAceptado_CallBack);
	 	OnlyReaderDatosDemograficosMedico();
 }
 
 function loadBeanMedico(uoperacion) {
		var frmPantalla = window.document.frmMedicos;
		if (document.getElementById("txtCodigoMedico").value == "") {
			medicoBean.cmedico = 0;
		} else {
			medicoBean.cmedico = document.getElementById("txtCodigoMedico").value;
		}
		medicoBean.sappaterno = document.getElementById("txtApellidoPaternoMedico").value;
		medicoBean.sapmaterno = document.getElementById("txtApellidoMaternoMedico").value;		
		medicoBean.snombre = document.getElementById("txtNombreMedico").value;		
		medicoBean.snacimiento = document.getElementById("txtFechaNacimiento").value;
		medicoBean.srfc = document.getElementById("txtRfc").value;
		medicoBean.czona = TypeObjeto(document.getElementById("selZona"));		
		var EspecialidadArray = new Array();
		EspecialidadArray = loopSelected("selEspecialidad");		
		medicoBean.cespecialidad = EspecialidadArray[0];

		medicoBean.scorreoelectro = document.getElementById("txtCorreoElectronico").value;
	 	medicoBean.utipooperacion = uoperacion;
	 	medicoBean.uestadomedico=document.getElementById("hdnEstadoMedico").value;	 		 	
		medicoBean.cformapagomedico = TypeObjeto(frmPantalla.selFormaPago);	 	
	 	medicoBean.shorariovisita = document.getElementById("txtHorarioVisita").value;
	 	medicoBean.scurp = document.getElementById("txtCURP").value;
	 	medicoBean.ucategoriamedico = TypeObjeto(frmPantalla.selTipoMedico);	 	
		if (frmPantalla.radSexo[1].checked == true) {
			medicoBean.usexo = 1;			
		} else {
			medicoBean.usexo = 0;			
		}
 }
 
 function VerificaModificacion()
 {
	if (document.getElementById("hdnActualizacion").value == 0) { 
		document.getElementById("hdnActualizacion").value = 1; 
		if (document.getElementById("txtCodigoMedico").value == "") {
		 	if (validaAltaMedico()) {
				if (confirm("Desea dar de alta los datos del Médico?")) {	
					document.getElementById("hdnEstadoMedico").value = 24;
				 	loadBeanMedico(1);
				 	DatosMedico.actualizaMedico(medicoBean,actualizaMedico_CallBack);	 	
				}
			}
		} else {
		 	if (validaFullMedico()) {
				if (confirm("Estas seguro de actualizar los datos del Médico?")) {	 					
				 	loadBeanMedico(2);
				 	DatosMedico.actualizaMedico(medicoBean,actualizaMedico_CallBack);	 	
				}
			}
		}		
		document.getElementById("hdnActualizacion").value = 0;
	}
 } 	 
  
 function verificaAlta() {		
	if (window.document.frmMedicos.hdnActualizacion.value == 0) { 
		window.document.frmMedicos.hdnActualizacion.value = 1; 
	 	if (validaAltaMedico()) {
			if (confirm("Desea dar de alta los datos del Médico?")) {	 	
			 	loadBeanMedico(1);
			 	DatosMedico.actualizaMedico(medicoBean,actualizaMedico_CallBack);	 	
			}
		}
		window.document.frmMedicos.hdnActualizacion.value = 0; 
	}
 }	
 
 function actualizaMedico_CallBack(data) {
	 alert("Registro actualizado del Medico " + data.cmedico + " " + data.sappaterno + " " + data.snombre);
	 medicoAceptado(data.kmedico);
 }

 function MedicoBean() {	
	kmedico=null, 
	cmedico=null,
	snombre=null,
	sappaterno=null,
	sapmaterno=null,
	snacimiento=null,
	srfc=null,
	sdireccion=null,
	scolonia=null,
	sdelegmuni=null,
	sciudad=null,
    czona=null,
    casentamiento=null,
	scodigopostal=null,
	kcodigopostal=null,
	stelefono=null,
	usexo=null,
	scorreoelectro=null,
	cespecialidad=null,
	utipooperacion=null,
    cformapagomedico=null,
    shorariovisita=null,
    scurp=null,
    ucategoriamedico=null,
    sestadomedico=null,    
    uestadomedico=null,
    sgriddirecciones= null,
    sgridtelefonos=null
 }