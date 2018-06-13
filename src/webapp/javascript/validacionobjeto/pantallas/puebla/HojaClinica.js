var pacienteBean = new PacienteBean();
var cuestionariopantallaBean = new CuestionarioPantallaBean();

	function init() 
	{
	    DWRUtil.useLoadingMessage();
	}

/************************************** FUNCIONES PACIENTE *********************************************/	
	function buscarPacienteECE(kPaciente) {		
		pacienteBean.kpacientefundacion=kPaciente;
		pacienteBean.snombre="";
		pacienteBean.sappaterno="";
		pacienteBean.sapmaterno="";
		pacienteBean.cconvenio = 0;
		DatosPaciente.buscarPaciente(pacienteBean,buscarPaciente_CallBack);
	}	

    function buscarPaciente_CallBack(data)
	 {
		var frmPantalla = window.document.frmPantallaPuebla;

		document.getElementById('txtCodigoPaciente').className = 'textflat';
		frmPantalla.txtCodigoPaciente.disabled = true;
		frmPantalla.txtCodigoPaciente.value = data.kpacientefundacion;

		document.getElementById('txtApellidoPaterno').className = 'textflat';
		frmPantalla.txtApellidoPaterno.disabled = true;
		frmPantalla.txtApellidoPaterno.value = data.sappaterno;

		document.getElementById('txtApellidoMaterno').className = 'textflat';
		frmPantalla.txtApellidoMaterno.disabled = true;
		frmPantalla.txtApellidoMaterno.value = data.sapmaterno;		

		document.getElementById('txtNombre').className = 'textflat';
		frmPantalla.txtNombre.disabled = true;
		frmPantalla.txtNombre.value = data.snombre;

		frmPantalla.txtFechaNacimiento.value = data.snacimiento;

		document.getElementById('txtCodigoPostal').className = 'textflat';
		frmPantalla.txtCodigoPostal.disabled = true;
		frmPantalla.txtCodigoPostal.value = data.scodigopostal;

		document.getElementById('txtColonia').className = 'textflat';
		frmPantalla.txtColonia.disabled = true;
		frmPantalla.txtColonia.value = data.scolonia;

		document.getElementById('txtDelegacionMunicipio').className = 'textflat';
		frmPantalla.txtDelegacionMunicipio.disabled = true;
		frmPantalla.txtDelegacionMunicipio.value =  data.sdelegmuni;
		
		document.getElementById('txtCalle').className = 'textflat';
		frmPantalla.txtCalle.disabled = true;
		frmPantalla.txtCalle.value = data.sdireccion;

		document.getElementById('txtEstado').className = 'textflat';
		frmPantalla.txtEstado.disabled = true;
		frmPantalla.txtEstado.value = data.sciudad;
		
		document.getElementById('txtTelefono').className = 'textflat';
		frmPantalla.txtTelefono.disabled = true;
		frmPantalla.txtTelefono.value = data.stelefono;		

		obtenerEdadfrm(window.document.frmPantallaPuebla);				
		frmPantalla.txtFechaNacimiento.disabled = true;

		buscarCuestionario();
	}	 	 
	
	function viewPacienteCaptura(strLiga,KPaciente){
    	var liga = "";    	
		liga = strLiga + "?kPacientePuebla=" + KPaciente;
		window.location.href =liga;
    	return true;    		
    }
/************************************** FUNCIONES PACIENTE *********************************************/	
/************************************** FUNCIONES CUESTIONARIO *********************************************/	

	function actualizaCuestionario() {
		if (parseInt(window.document.frmPantallaPuebla.txtCodigoPaciente.value) > 0) {
			loadCuestionarioBean();
			Puebla.persistirCuestionario(cuestionariopantallaBean,showCuestionarioPantalla_CallBack)			
		}	
	}
	
	function buscarCuestionario() {
		if (parseInt(window.document.frmPantallaPuebla.txtCodigoPaciente.value) > 0) {
			loadCuestionarioBean();
			Puebla.buscarCuestionario(cuestionariopantallaBean,showCuestionarioPantalla_CallBack)			
		}	
	}
	
	function imprimirCuestionario() {
		if ((parseInt(window.document.frmPantallaPuebla.txtCodigoPaciente.value) > 0) && (parseInt(window.document.frmPantallaPuebla.kcuestionariopacientepuebla.value) > 0)) {
			loadCuestionarioBean();
			Puebla.imprimirCuestionario(cuestionariopantallaBean,imprimirCuestionario_CallBack)			
		}	
	}

	
	function showCuestionarioPantalla_CallBack(data) {
		loadCuestionarioScreen(data);
		if (data.smensajeoperacion != "") {
			alert(data.smensajeoperacion);
		}
	}
	
	function imprimirCuestionario_CallBack(data) {
		abrirVentana(data.smensajeoperacion,'HojaClinica');
	}
	
	function loadCuestionarioBean() {
		var frmPantalla = window.document.frmPantallaPuebla;
				cuestionariopantallaBean.kcuestionariopacientepuebla=frmPantalla.kcuestionariopacientepuebla.value;
				cuestionariopantallaBean.kPaciente=frmPantalla.txtCodigoPaciente.value;	
				cuestionariopantallaBean.uedadprimeramestruacion=frmPantalla.txtEdadPrimMens.value;	
				cuestionariopantallaBean.uhijos=frmPantalla.txtHijos.value;	
				cuestionariopantallaBean.uabortos=frmPantalla.txtAbortos.value;			
				if (TypeObjeto(frmPantalla.radEmbarazo[1])) {
					cuestionariopantallaBean.bolembarazada = false;
				} else {
					cuestionariopantallaBean.bolembarazada = true;
				}			
				cuestionariopantallaBean.sembarazada=frmPantalla.txtEmbarazada.value;	
				if (TypeObjeto(frmPantalla.radMastografiaAnterior[0])) {
					cuestionariopantallaBean.bolmastografiaanterior = false;
				} else {
					cuestionariopantallaBean.bolmastografiaanterior = true;
				}			
				cuestionariopantallaBean.smastografiacuando=frmPantalla.txtCuandoMasto.value;	
				if (TypeObjeto(frmPantalla.radCancerMama[1])) {
					cuestionariopantallaBean.bolatenidocancer = false;
				} else {
					cuestionariopantallaBean.bolatenidocancer = true;
				}			
				cuestionariopantallaBean.scancercuando=frmPantalla.txtCuandoCancer.value;	
				cuestionariopantallaBean.sultimamestruacion=frmPantalla.txtUltimaMens.value;	
				cuestionariopantallaBean.smadre=frmPantalla.txtMadre.value;	
				cuestionariopantallaBean.sedadmadre=frmPantalla.txtEdadMadre.value;	
				cuestionariopantallaBean.stias=frmPantalla.txtTias.value;	
				cuestionariopantallaBean.sedadtias=frmPantalla.txtEdadTias.value;
				cuestionariopantallaBean.sabuelas=frmPantalla.txtAbuelas.value;	
				cuestionariopantallaBean.sedadabuelas=frmPantalla.txtEdadAbuelas.value;
				if (TypeObjeto(frmPantalla.radAnticonceptivo[1])) {
					cuestionariopantallaBean.bolanticonceptivo = false;
				} else {
					cuestionariopantallaBean.bolanticonceptivo = true;
				}			
				cuestionariopantallaBean.scualesanticonceptivo=frmPantalla.txtCualesAnti.value;	
				cuestionariopantallaBean.stiempoanticonceptivo=frmPantalla.txtTiempoAnti.value;
				if (TypeObjeto(frmPantalla.radCirugia[1])) {
					cuestionariopantallaBean.bolcirugia = false;
				} else {
					cuestionariopantallaBean.bolcirugia = true;
				}			
				cuestionariopantallaBean.scirugiatipo=frmPantalla.txtTipoCirugia.value;	
				cuestionariopantallaBean.scirugiacuando=frmPantalla.txtCuandoCirugia.value;
				cuestionariopantallaBean.scirugiadonde=frmPantalla.txtDondeCirugia.value;
				if (TypeObjeto(frmPantalla.radSenaMama[1])) {
					cuestionariopantallaBean.bolsenamama = false;
				} else {
					cuestionariopantallaBean.bolsenamama = true;
				}			
				cuestionariopantallaBean.ssenadonde=frmPantalla.txtDondeSena.value;	
				cuestionariopantallaBean.ssenatipo=frmPantalla.txtTipoSena.value;	
				cuestionariopantallaBean.smotivoestudio=frmPantalla.txtMotivoEstudio.value;	
				if (TypeObjeto(frmPantalla.radLesion[1])) {
					cuestionariopantallaBean.bollesion = false;
				} else {
					cuestionariopantallaBean.bollesion = true;
				}			
				cuestionariopantallaBean.slesionubicacion=frmPantalla.txtUbicacionLesion.value;	
				if (TypeObjeto(frmPantalla.radFuma[1])) {
					cuestionariopantallaBean.bolfuma = false;
				} else {
					cuestionariopantallaBean.bolfuma = true;
				}			
				cuestionariopantallaBean.sfumadesde=frmPantalla.txtDesdeCuandoFuma.value;	
				cuestionariopantallaBean.spadecimiento=frmPantalla.txtPadecimiento.value;
	}

	function loadCuestionarioScreen(data) {
		var frmPantalla = window.document.frmPantallaPuebla;
			frmPantalla.kcuestionariopacientepuebla.value = data.kcuestionariopacientepuebla;
			frmPantalla.txtEdadPrimMens.value = data.uedadprimeramestruacion;	
			frmPantalla.txtHijos.value = data.uhijos;	
			frmPantalla.txtAbortos.value = data.uabortos;			
			if (data.bolembarazada) {
		        frmPantalla.radEmbarazo[1].checked = false;
		        frmPantalla.radEmbarazo[0].checked = true;
			} else {
		        frmPantalla.radEmbarazo[1].checked = true;
		        frmPantalla.radEmbarazo[0].checked = false;
			}						
			frmPantalla.txtEmbarazada.value = data.sembarazada;	
			if (data.bolmastografiaanterior) {
		        frmPantalla.radMastografiaAnterior[1].checked = false;
		        frmPantalla.radMastografiaAnterior[0].checked = true;
			} else {
		        frmPantalla.radMastografiaAnterior[1].checked = true;
		        frmPantalla.radMastografiaAnterior[0].checked = false;
			}			
			frmPantalla.txtCuandoMasto.value = data.smastografiacuando;	
			if (data.bolatenidocancer) {
		        frmPantalla.radCancerMama[1].checked = false;
		        frmPantalla.radCancerMama[0].checked = true;
			} else {
		        frmPantalla.radCancerMama[1].checked = true;
		        frmPantalla.radCancerMama[0].checked = false;
			}			
			frmPantalla.txtCuandoCancer.value = data.scancercuando;	
			frmPantalla.txtUltimaMens.value = data.sultimamestruacion;	
			frmPantalla.txtMadre.value = data.smadre;	
			frmPantalla.txtEdadMadre.value = data.sedadmadre;	
			frmPantalla.txtTias.value = data.stias;	
			frmPantalla.txtEdadTias.value = data.sedadtias;
			frmPantalla.txtAbuelas.value = data.sabuelas;	
			frmPantalla.txtEdadAbuelas.value = data.sedadabuelas;
			if (data.bolanticonceptivo) {
		        frmPantalla.radAnticonceptivo[1].checked = false;
		        frmPantalla.radAnticonceptivo[0].checked = true;
			} else {
		        frmPantalla.radAnticonceptivo[1].checked = true;
		        frmPantalla.radAnticonceptivo[0].checked = false;
			}			
			frmPantalla.txtCualesAnti.value = data.scualesanticonceptivo;	
			frmPantalla.txtTiempoAnti.value = data.stiempoanticonceptivo;
			if (data.bolcirugia) {
		        frmPantalla.radCirugia[1].checked = false;
		        frmPantalla.radCirugia[0].checked = true;
			} else {
		        frmPantalla.radCirugia[1].checked = true;
		        frmPantalla.radCirugia[0].checked = false;
			}			
			frmPantalla.txtTipoCirugia.value = data.scirugiatipo;	
			frmPantalla.txtCuandoCirugia.value = data.scirugiacuando;
			frmPantalla.txtDondeCirugia.value = data.scirugiadonde;
			if (data.bolsenamama) {
		        frmPantalla.radSenaMama[1].checked = false;
		        frmPantalla.radSenaMama[0].checked = true;
			} else {
		        frmPantalla.radSenaMama[1].checked = true;
		        frmPantalla.radSenaMama[0].checked = false;
			}			
			frmPantalla.txtDondeSena.value = data.ssenadonde;	
			frmPantalla.txtTipoSena.value = data.ssenatipo;	
			frmPantalla.txtMotivoEstudio.value = data.smotivoestudio;	
			if (data.bollesion) {
		        frmPantalla.radLesion[1].checked = false;
		        frmPantalla.radLesion[0].checked = true;
			} else {
		        frmPantalla.radLesion[1].checked = true;
		        frmPantalla.radLesion[0].checked = false;
			}			
			frmPantalla.txtUbicacionLesion.value = data.slesionubicacion;	
			if (data.bolfuma) {
		        frmPantalla.radFuma[1].checked = false;
		        frmPantalla.radFuma[0].checked = true;
			} else {
		        frmPantalla.radFuma[1].checked = true;
		        frmPantalla.radFuma[0].checked = false;
			}			
			frmPantalla.txtDesdeCuandoFuma.value = data.sfumadesde;	
			frmPantalla.txtPadecimiento.value = data.spadecimiento;
	}
	
	
/************************************** FUNCIONES CUESTIONARIO *********************************************/	
	

/************************************** BEANS *********************************************/	
	function PacienteBean() {	
		kpacientefundacion=null,
		snombre=null,
		sappaterno=null,
		sapmaterno=null,
		dnacimiento=null,
		snacimiento=null,
		sdireccion=null,
		scolonia=null,
		sdelegmuni=null,
		ccodigopostal=null,
		scodigopostal=null,
		stelefono=null,
		scelular=null,
		scorreoelectronico=null,
		sciudad=null,
		bregistroactivo=null,
		cusuario=null,
		csexo=null,
		cconvenio=null,
		svalorexpediente=null,
		uopcionenviocorreo=null,
		scelular=null
	}	
	
	function CuestionarioPantallaBean() {
		kcuestionariopacientepuebla=null,
		kPaciente=null,	
		uedadprimeramestruacion=null,	
		uhijos=null,	
		uabortos=null,	
		bolembarazada=null,	
		sembarazada=null,	
		bolmastografiaanterior=null,	
		smastografiacuando=null,	
		bolatenidocancer=null,	
		scancercuando=null,	
		sultimamestruacion=null,	
		smadre=null,	
		sedadmadre=null,	
		stias=null,	
		sedadtias=null,
		sabuelas=null,	
		sedadabuelas=null,
		bolanticonceptivo=null,	
		scualesanticonceptivo=null,	
		stiempoanticonceptivo=null,
		bolcirugia=null,	
		scirugiatipo=null,	
		scirugiacuando=null,
		scirugiadonde=null,
		bolsenamama=null,	
		ssenadonde=null,	
		ssenatipo=null,	
		smotivoestudio=null,	
		bollesion=null,	
		slesionubicacion=null,	
		bolfuma=null,	
		sfumadesde=null,	
		spadecimiento=null,		
		smensajeoperacion=null
	}
	