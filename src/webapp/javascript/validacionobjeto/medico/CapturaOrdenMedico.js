 var medicoBean = new MedicoBean();
 
 var hostServerWebApp = "http://10.3.0.8:8192";
// var hostServerWebApp = "http://10.20.26.6:8192";

 
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
 
 function reasignacionOrdenes(){
	 var frmPantalla = window.document.frmMedicos;
	 var idUser = document.getElementById("idUsuario").value;
	 var url = hostServerWebApp+"/medicos/reasignacion-ordenes/"+idUser;
	 window.open(url, "_blank"); 
	 
 }
 
 function loadBeanMedico(uoperacion) {
		var frmPantalla = window.document.frmMedicos;
		
		if(document.getElementById("hdnCMedico").value == 0){
			medicoBean.kmedico = 0;
		}else{
			medicoBean.kmedico = document.getElementById("hdnCMedico").value;
		}
		
//		if (document.getElementById("txtCodigoMedico").value == "") {
//			medicoBean.cmedico = 0;
//		} else {
//			medicoBean.cmedico = document.getElementById("txtCodigoMedico").value;
//		}
		medicoBean.cmedico = document.getElementById("txtCodigoMedico").value;
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
		medicoBean.stelefono = document.getElementById("txtTelefono").value;
		medicoBean.sdireccion = document.getElementById("txtCalle").value;
		medicoBean.ctipoDireccion = TypeObjeto(document.getElementById("selReferenciaDireccion"));	
		medicoBean.kcodigopostal = document.getElementById("hdnCodigoPostal").value;
		
		var estadoregistro = 0;
		if(document.getElementById("selEstatusDireccion").value == 0){
			estadoregistro = 3;
		}else{
			estadoregistro = 11;
		}
		
		medicoBean.cestadoregistro = estadoregistro;
		
		medicoBean.userid = document.getElementById("idUsuario").value;
		medicoBean.susuarioweb = document.getElementById("txtUsuarioWeb").value;
		
		var marcasventa = '0';
		if(frmPantalla.checkOlab.checked == true){
			marcasventa = marcasventa+',1';
		}
		if(frmPantalla.checkAzteca.checked == true){
			marcasventa = marcasventa+',4';
		}
		if(frmPantalla.checkSwisslab.checked == true){
			marcasventa = marcasventa+',5';
		}
		if(frmPantalla.checkJenner.checked == true){
			marcasventa = marcasventa+',7';
		}
		if(frmPantalla.checkLiacsa.checked == true){
			marcasventa = marcasventa+',15';
		}
		if(frmPantalla.checkFamilyLabsNorte.checked == true){
			marcasventa = marcasventa+',19';
		}
		if(frmPantalla.checkExakta.checked == true){
			marcasventa = marcasventa+',20';
		}
		if(frmPantalla.checkAsesoresSur.checked == true){
			marcasventa = marcasventa+',21';
		}
		if(frmPantalla.checkMoreira.checked == true){
			marcasventa = marcasventa+',16';
		}
		if(frmPantalla.checkPolab.checked == true){
			marcasventa = marcasventa+',22';
		}
		if(frmPantalla.checkBiomedicaReferencia.checked == true){
			marcasventa = marcasventa+',25';
		}
		if(frmPantalla.checkPromedic.checked == true){
			marcasventa = marcasventa+',26';
		}
		if(marcasventa != '0'){
			marcasventa = marcasventa+',0';
		}
		
		medicoBean.smarcasventa = marcasventa;
		medicoBean.bsustentable = frmPantalla.checkSustentable.checked;
		
		
 }
 
 function VerificaModificacion()
 {
	if (document.getElementById("hdnActualizacion").value == 0) { 
		document.getElementById("hdnActualizacion").value = 1;  
//		if (document.getElementById("txtCodigoMedico").value == "") {
//		 	if (validaAltaMedico()) {
//				if (confirm("Desea dar de alta los datos del Medico?")) {	
//					document.getElementById("hdnEstadoMedico").value = 24;
//				 	loadBeanMedico(1);
//				 	DatosMedico.actualizaMedico(medicoBean,actualizaMedico_CallBack);	 	
//				}
//			}
//		} else {
		 	if (validaFullMedico()) {
				if (confirm("Estas seguro de actualizar los datos del Medico?")) {	 					
				 	loadBeanMedico(2);
				 	DatosMedico.actualizaMedico(medicoBean,actualizaMedico_CallBack);	 	
				}
			}
//		}		
		document.getElementById("hdnActualizacion").value = 0;
	}
 } 	 
 
 function VerificaAltaMedico() 
 {
	 if (document.getElementById("hdnActualizacion").value == 0) { 
		 document.getElementById("hdnActualizacion").value = 1;  
		 if (validaAltaMedico()) {
			if (confirm("Desea dar de alta los datos del Medico?")) {	
				document.getElementById("hdnEstadoMedico").value = 24;
			 	loadBeanMedico(1);
			 	DatosMedico.altaMedico(medicoBean,altaMedico_CallBack);	 	
			}
		 }
		 document.getElementById("hdnActualizacion").value = 0;
	 }
 }
  
 function verificaAlta() {		
	if (window.document.frmMedicos.hdnActualizacion.value == 0) { 
		window.document.frmMedicos.hdnActualizacion.value = 1; 
	 	if (validaAltaMedico()) {
			if (confirm("Desea dar de alta los datos del M�dico?")) {	 	
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
 
 function altaMedico_CallBack(data) {
	 if(data.utipooperacion==3){
		 alert("Ya existe un Medico con la clave " + data.cmedico );
	 }else{
		 alert("Registro alta del Medico " + data.cmedico + " " + data.sappaterno + " " + data.snombre);
		 medicoAceptado(data.kmedico);		 
	 }
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