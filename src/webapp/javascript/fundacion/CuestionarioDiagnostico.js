var cuestionarioBean = new BPacienteCuestionarioBean();


function buscarCuestionario() {
	var frmPantalla = window.document.frmCuestionario;
	cuestionarioBean.bpacientefundacion=frmPantalla.hdnkPaciente.value;
	DatosCuestionario.consultaCuestionario(cuestionarioBean,buscarCuestionario_CallBack);	
}

function buscarCuestionario_CallBack(data) {
	var intCuetionario = data.kpacientecuestionario;
	if (intCuetionario  == 0) {
		alert('No existe Cuestionario para esta Paciente');				
	} else {
		pintaPantalla(data);
		alert('Cuestionario Encontrado ' + data.kpacientecuestionario);		
	}
}

function actualizarCuestionario() {
	llenaBean();
	DatosCuestionario.guardaCuestionario(cuestionarioBean,actualizarCuestionario_CallBack);	
}

function actualizarCuestionario_CallBack(data) {
	var frmPantalla = window.document.frmCuestionario;
	frmPantalla.kPacienteCuestionario.value=data.kpacientecuestionario;
	alert('Cuestionario Actualizado');
}

function pintaPantalla(data) {
	var frmPantalla = window.document.frmCuestionario;	
	frmPantalla.kPacienteCuestionario.value=data.kpacientecuestionario;	
	if (data.balergias) {
		frmPantalla.radAlergias[0].checked=true;
	} else {
		frmPantalla.radAlergias[1].checked=true;
	}
	frmPantalla.txtTabaquismoDia.value=data.smedicamento;
	frmPantalla.selOcupacionPaciente.selectedIndex = data.cocupacionpaciente;
	frmPantalla.selOcupacionFamiliar.selectedIndex = data.cocupacionfamiliar;
	frmPantalla.selEstadoCivil.selectedIndex = data.cestadocivil;
	frmPantalla.selEscolaridad.selectedIndex = data.cescolaridad;	
	frmPantalla.txtTabaquismoDia.value=data.utabaquismodia;
	frmPantalla.txtTabaquismoTiempo.value=data.utabaquismotiempo;
	frmPantalla.txtDiabetesTiempo.value=data.udiabetestiempo;
	frmPantalla.txtMenarca.value=data.umenarca;
	frmPantalla.txtDiasRitmo1.value=data.udiasritmo1;
	frmPantalla.txtDiasRitmo2.value=data.udiasritmo2;
	frmPantalla.txtFechaUltimaRegla.value=data.dfechaUltimaRegla;
	frmPantalla.txtVidaSexualActiva.value=data.uvidaSexualActiva;
	frmPantalla.txtNumeroParejasSexuales.value=data.unumeroParejasSexuales;
	frmPantalla.txtGestaciones.value=data.ugestaciones;
	frmPantalla.txtPartosNormales.value=data.upartosNormales;
	frmPantalla.txtCesareas.value=data.ucesareas;
	frmPantalla.txtAbortos.value=data.uabortos;
	frmPantalla.txtPrimerEmbarazo.value=data.uprimerEmbarazo;
	frmPantalla.txtClimaterio.value=data.uclimaterio;
	frmPantalla.txtOtroAnticonceptivo.value=data.sotroAnticonceptivo;
	frmPantalla.txtFechaUltimoPapanicolau.value=data.dfechaUltimoPapanicolau;
	frmPantalla.txtFechaVPH.value=data.dfechaVph;
	frmPantalla.txtAnteceHereFami.value=data.santeceHereFami;
	frmPantalla.txtFechaHisterectomia.value=data.dfechaHisterectomia;
	frmPantalla.txtFechaOforectomia.value=data.dfechaOforectomia;
	frmPantalla.txtMedicamento.value = data.smedicamento;
	frmPantalla.txtMotivoOforectomia.value=data.smotivoOforectomia;
	frmPantalla.txtFechaEQX.value=data.dfechaEqx;
	frmPantalla.txtFechaElectroCoagulacion.value=data.dfechaElectroCoagulacion;
	frmPantalla.txtFechaCrioterapia.value=data.dfechaCrioterapia;
	frmPantalla.txtMotivoCrioterapia.value=data.smotivoCrioterapia;
	frmPantalla.txtSintomatologiaActual.value=data.ssintomatologiaActual;
//	frmPantalla.txtVulvaOtros.value=data.svulvaOtros;
//	frmPantalla.txtDescripcionZonasAcetopositivas.value=data.szonasAcetopositivas;
//	frmPantalla.txtTamaPolipo.value=data.utamanoPeriorificiador;
//	frmPantalla.txtQuisteNaboth.value=data.squisteNaboth;
//	frmPantalla.txtTamaEctropion.value=data.utamanoEctropion;
	frmPantalla.selMotivoHisterectomia.selectedIndex=data.cmotivoHisterectomia;
	frmPantalla.selHTA.selectedIndex=data.cHTA;
//	frmPantalla.selEctropion.selectedIndex=data.cectropion;
//	frmPantalla.selSecrecion.selectedIndex=data.csecrecion;
//	frmPantalla.selDIU.selectedIndex=data.cdiu;
//	frmPantalla.selCervix.selectedIndex=data.ccervix;
	frmPantalla.selLugarPapanicolau.selectedIndex=data.clugarPapanicolau;
	frmPantalla.selResultadoPapanicolau.selectedIndex=data.cresultadoPapanicolau;
	frmPantalla.selHTA.selectedIndex=data.chta;
	frmPantalla.selTratamiento.selectedIndex=data.ctratamientodiabet;
//	frmPantalla.selTiposBiopsia.selectedIndex=data.ctiposBiopsia;
//	frmPantalla.selPolipo.selectedIndex=data.cpolipo;
	frmPantalla.selMotivoEstudio.selectedIndex=(data.cmotivoEstudio);
	frmPantalla.selMotivoElectroCoagulacion.selectedIndex=(data.cmotivoElectroCoagulacion);
	frmPantalla.selMotivoEQX.selectedIndex=data.cmotivoEqx;
	frmPantalla.chkAnteceCervico.checked=data.banteceCervico;
	frmPantalla.chkAnteceMama.checked=data.banteceMama;
	frmPantalla.chkAntiRitmo.checked=data.bantiRitmo;
	frmPantalla.chkAntiLocal.checked=data.bantiLocal;
	frmPantalla.chkAntiParenteral.checked=data.bantiParenteral;
	frmPantalla.chkAntiOral.checked=data.bantiOral;
	frmPantalla.chkAntiVasectomia.checked=data.bantiVasectomia;
	frmPantalla.chkAntiParche.checked=data.bantiParche;
	frmPantalla.chkAntiDIU.checked=data.bantiDiu;
	frmPantalla.chkAntiCoito.checked=data.bantiCoito;
	frmPantalla.chkAntiSalpingoclasia.checked=data.bantiSalpingoclasia;
	frmPantalla.chkAntiBarrera.checked=data.bantiBarrera;
	frmPantalla.chkAntiNada.checked=data.bantiNada;
	frmPantalla.chkAntiImplante.checked=data.bantiImplante;
	frmPantalla.chkAntiOtro.checked=data.bantiOtro;
	frmPantalla.chkOforectomiaDerecho.checked=data.boforectomiaDerecho;
	frmPantalla.chkOforectomiaIzuierdo.checked=data.boforectomiaIzuierdo;
	frmPantalla.chkOforectomiaNo.checked=data.boforectomiaNo;
//	frmPantalla.chkProcesoErosivo.checked=data.bprocesoErosivo;
//	frmPantalla.chkMEI.checked=data.bmei;
//	frmPantalla.chkMEIPO.checked=data.bmeipo;
//	frmPantalla.chkQuisteNaboth.checked=data.bquisteNaboth;
//	frmPantalla.chkCervixCentral.checked=data.bcervixCentral;
//	frmPantalla.chkCervixDerecho.checked=data.bcervixDerecho;
//	frmPantalla.chkCervixIzquierdo.checked=data.bcervixIzquierdo;
//	frmPantalla.chkCervixAnterior.checked=data.bcervixAnterior;
//	frmPantalla.chkCervixPosterior.checked=data.bcervixPosterior;
//	frmPantalla.chkSecresionFluida.checked=data.bsecresionFluida;
//	frmPantalla.chkSecresionEspesa.checked=data.bsecresionEspesa;
//	frmPantalla.chkSecresionSemiespesa.checked=data.bsecresionSemiespesa;
//	frmPantalla.chkSecresionGrumosa.checked=data.bsecresionGrumosa;
//	frmPantalla.chkSecresionEspumosa.checked=data.bsecresionEspumosa;
//	frmPantalla.chkSecresionEscasa.checked=data.bsecresionEscasa;
//	frmPantalla.chkSecresionModerada.checked=data.bsecresionModerada;
//	frmPantalla.chkSecresionAbundante.checked=data.bsecresionAbundante;
//	frmPantalla.chksecresionMixta.checked=data.bsecresionMixta;
	if (data.blactancia) {
		frmPantalla.radLactancia[0].checked=true;
	} else {
		frmPantalla.radLactancia[1].checked=true;
	}
	if (data.bvph) {
		frmPantalla.radVPH[0].checked=true;
	} else {
		frmPantalla.radVPH[1].checked=true;
	}
//	if (data.bunionEscamoclumnar) {
//		frmPantalla.[0].checked=true;
//	} else {
//		frmPantalla.[1].checked=true;
//	}
	if (data.bcrioterapia) {
		frmPantalla.radCrioterapia[0].checked=true;
	} else {
		frmPantalla.radCrioterapia[1].checked=true;
	}
	if (data.belectroCoagulacion) {
		frmPantalla.radElectroCoagulacion[0].checked=true;
	} else {
		frmPantalla.radElectroCoagulacion[1].checked=true;
	}
	if (data.beqx) {
		frmPantalla.radEQX[0].checked=true;
	} else {
		frmPantalla.radEQX[1].checked=true;
	}
	if (data.bdiabetes) {
		frmPantalla.radDiabetes[0].checked=true;
	} else {
		frmPantalla.radDiabetes[1].checked=true;
	}
	if (data.btabaquismo) {
		frmPantalla.radTabaquismo[0].checked=true;
	} else {
		frmPantalla.radTabaquismo[1].checked=true;
	}
}


function llenaBean() {
	var frmPantalla = window.document.frmCuestionario;
	cuestionarioBean.balergias=TypeObjeto(frmPantalla.radAlergias[0]);
	cuestionarioBean.smedicamento=TypeObjeto(frmPantalla.txtMedicamento);
	cuestionarioBean.cocupacionpaciente=TypeObjeto(frmPantalla.selOcupacionPaciente);
	cuestionarioBean.cocupacionfamiliar=TypeObjeto(frmPantalla.selOcupacionFamiliar);
	cuestionarioBean.cestadocivil=TypeObjeto(frmPantalla.selEstadoCivil);
	cuestionarioBean.cescolaridad=TypeObjeto(frmPantalla.selEscolaridad);
	cuestionarioBean.btabaquismo=TypeObjeto(frmPantalla.radTabaquismo[0]);     
	cuestionarioBean.utabaquismodia=TypeObjeto(frmPantalla.txtTabaquismoDia);   
	cuestionarioBean.utabaquismotiempo=TypeObjeto(frmPantalla.txtTabaquismoTiempo);    
	cuestionarioBean.bdiabetes=TypeObjeto(frmPantalla.radDiabetes[0]);    
	cuestionarioBean.udiabetestiempo=TypeObjeto(frmPantalla.txtDiabetesTiempo);   
	cuestionarioBean.umenarca=TypeObjeto(frmPantalla.txtMenarca);   
	cuestionarioBean.udiasritmo1=TypeObjeto(frmPantalla.txtDiasRitmo1);   
	cuestionarioBean.udiasritmo2=TypeObjeto(frmPantalla.txtDiasRitmo2);    
	cuestionarioBean.dfechaUltimaRegla=TypeObjeto(frmPantalla.txtFechaUltimaRegla);    
	cuestionarioBean.uvidaSexualActiva=TypeObjeto(frmPantalla.txtVidaSexualActiva);   
	cuestionarioBean.unumeroParejasSexuales=TypeObjeto(frmPantalla.txtNumeroParejasSexuales);    
	cuestionarioBean.ugestaciones=TypeObjeto(frmPantalla.txtGestaciones);    
	cuestionarioBean.upartosNormales=TypeObjeto(frmPantalla.txtPartosNormales);    
	cuestionarioBean.ucesareas=TypeObjeto(frmPantalla.txtCesareas);    
	cuestionarioBean.uabortos=TypeObjeto(frmPantalla.txtAbortos);   
	cuestionarioBean.uprimerEmbarazo=TypeObjeto(frmPantalla.txtPrimerEmbarazo);   
	cuestionarioBean.blactancia=TypeObjeto(frmPantalla.radLactancia[0]);    
	cuestionarioBean.uclimaterio=TypeObjeto(frmPantalla.txtClimaterio);    
	cuestionarioBean.bantiRitmo=TypeObjeto(frmPantalla.chkAntiRitmo);    
	cuestionarioBean.bantiLocal=TypeObjeto(frmPantalla.chkAntiLocal);      
	cuestionarioBean.bantiParenteral=TypeObjeto(frmPantalla.chkAntiParenteral);   
	cuestionarioBean.bantiOral=TypeObjeto(frmPantalla.chkAntiOral);   
	cuestionarioBean.bantiVasectomia=TypeObjeto(frmPantalla.chkAntiVasectomia);     
	cuestionarioBean.bantiParche=TypeObjeto(frmPantalla.chkAntiParche);    
	cuestionarioBean.bantiDiu=TypeObjeto(frmPantalla.chkAntiDIU);    
	cuestionarioBean.bantiCoito=TypeObjeto(frmPantalla.chkAntiCoito);    
	cuestionarioBean.bantiSalpingoclasia=TypeObjeto(frmPantalla.chkAntiSalpingoclasia);    
	cuestionarioBean.bantiBarrera=TypeObjeto(frmPantalla.chkAntiBarrera);    
	cuestionarioBean.bantiNada=TypeObjeto(frmPantalla.chkAntiNada);    
	cuestionarioBean.bantiImplante=TypeObjeto(frmPantalla.chkAntiImplante);    
	cuestionarioBean.bantiOtro=TypeObjeto(frmPantalla.chkAntiOtro);
	cuestionarioBean.sotroAnticonceptivo=TypeObjeto(frmPantalla.txtOtroAnticonceptivo);    
	cuestionarioBean.dfechaUltimoPapanicolau=TypeObjeto(frmPantalla.txtFechaUltimoPapanicolau);    
	cuestionarioBean.bvph=TypeObjeto(frmPantalla.radVPH[0]);     
	cuestionarioBean.dfechaVph=TypeObjeto(frmPantalla.txtFechaVPH);    
	cuestionarioBean.banteceCervico=TypeObjeto(frmPantalla.chkAnteceCervico);    
	cuestionarioBean.banteceMama=TypeObjeto(frmPantalla.chkAnteceMama);
	cuestionarioBean.santeceHereFami=TypeObjeto(frmPantalla.txtAnteceHereFami);	
	cuestionarioBean.cHTA=TypeObjeto(frmPantalla.selHTA);
	cuestionarioBean.dfechaHisterectomia=TypeObjeto(frmPantalla.txtFechaHisterectomia);
	cuestionarioBean.cmotivoHisterectomia=TypeObjeto(frmPantalla.selMotivoHisterectomia);    
	cuestionarioBean.boforectomiaDerecho=TypeObjeto(frmPantalla.chkOforectomiaDerecho);    
	cuestionarioBean.boforectomiaIzuierdo=TypeObjeto(frmPantalla.chkOforectomiaIzuierdo);    
	cuestionarioBean.boforectomiaNo=TypeObjeto(frmPantalla.chkOforectomiaNo);    
	cuestionarioBean.dfechaOforectomia=TypeObjeto(frmPantalla.txtFechaOforectomia);
	cuestionarioBean.smotivoOforectomia=TypeObjeto(frmPantalla.txtMotivoOforectomia);    
	cuestionarioBean.beqx=TypeObjeto(frmPantalla.radEQX[0]);   
	cuestionarioBean.dfechaEqx=TypeObjeto(frmPantalla.txtFechaEQX);
	cuestionarioBean.cmotivoEqx=TypeObjeto(frmPantalla.selMotivoEQX);  
	cuestionarioBean.belectroCoagulacion=TypeObjeto(frmPantalla.radElectroCoagulacion[0]);     
	cuestionarioBean.dfechaElectroCoagulacion=TypeObjeto(frmPantalla.txtFechaElectroCoagulacion);
	cuestionarioBean.cmotivoElectroCoagulacion=TypeObjeto(frmPantalla.selMotivoElectroCoagulacion);    
	cuestionarioBean.bcrioterapia=TypeObjeto(frmPantalla.radCrioterapia[0]);    
	cuestionarioBean.dfechaCrioterapia=TypeObjeto(frmPantalla.txtFechaCrioterapia);
	cuestionarioBean.smotivoCrioterapia=TypeObjeto(frmPantalla.txtMotivoCrioterapia);
	cuestionarioBean.cmotivoEstudio=TypeObjeto(frmPantalla.selMotivoEstudio);
	cuestionarioBean.ssintomatologiaActual=TypeObjeto(frmPantalla.txtSintomatologiaActual);    
//	cuestionarioBean.bcervixCentral=TypeObjeto(frmPantalla.chkCervixCentral);    
//	cuestionarioBean.bcervixDerecho=TypeObjeto(frmPantalla.chkCervixDerecho);    
//	cuestionarioBean.bcervixIzquierdo=TypeObjeto(frmPantalla.chkCervixIzquierdo);    
//	cuestionarioBean.bcervixAnterior=TypeObjeto(frmPantalla.chkCervixAnterior);     
//	cuestionarioBean.bcervixPosterior=TypeObjeto(frmPantalla.chkCervixPosterior);    
//	cuestionarioBean.bsecresionFluida=TypeObjeto(frmPantalla.chkSecresionFluida);     
//	cuestionarioBean.bsecresionEspesa=TypeObjeto(frmPantalla.chkSecresionEspesa);    
//	cuestionarioBean.bsecresionSemiespesa=TypeObjeto(frmPantalla.chkSecresionSemiespesa);    
//	cuestionarioBean.bsecresionGrumosa=TypeObjeto(frmPantalla.chkSecresionGrumosa);    
//	cuestionarioBean.bsecresionEspumosa=TypeObjeto(frmPantalla.chkSecresionEspumosa);    
//	cuestionarioBean.bsecresionEscasa=TypeObjeto(frmPantalla.chkSecresionEscasa);    
//	cuestionarioBean.bsecresionModerada=TypeObjeto(frmPantalla.chkSecresionModerada);    
//	cuestionarioBean.bsecresionAbundante=TypeObjeto(frmPantalla.chkSecresionAbundante);    
//	cuestionarioBean.bsecresionMixta=TypeObjeto(frmPantalla.chksecresionMixta);
//	cuestionarioBean.svulvaOtros=TypeObjeto(frmPantalla.txtVulvaOtros);    
//	cuestionarioBean.bunionEscamoclumnar=TypeObjeto(frmPantalla.[0]);
//	cuestionarioBean.szonasAcetopositivas=TypeObjeto(frmPantalla.txtDescripcionZonasAcetopositivas);    
//	cuestionarioBean.utamanoPeriorificiador=TypeObjeto(frmPantalla.txtTamaPolipo);    
//	cuestionarioBean.bprocesoErosivo=TypeObjeto(frmPantalla.chkProcesoErosivo);    
//	cuestionarioBean.bmei=TypeObjeto(frmPantalla.chkMEI);    
//	cuestionarioBean.bmeipo=TypeObjeto(frmPantalla.chkMEIPO);    
//	cuestionarioBean.bquisteNaboth=TypeObjeto(frmPantalla.chkQuisteNaboth);    
//	cuestionarioBean.squisteNaboth=TypeObjeto(frmPantalla.txtQuisteNaboth);    
//	cuestionarioBean.cectropion=TypeObjeto(frmPantalla.selEctropion);    
//	cuestionarioBean.csecrecion=TypeObjeto(frmPantalla.selSecrecion);    
//	cuestionarioBean.cdiu=TypeObjeto(frmPantalla.selDIU);    
//	cuestionarioBean.ccervix=TypeObjeto(frmPantalla.selCervix);    
	cuestionarioBean.clugarPapanicolau=TypeObjeto(frmPantalla.selLugarPapanicolau);    
	cuestionarioBean.cresultadoPapanicolau=TypeObjeto(frmPantalla.selResultadoPapanicolau);    
	cuestionarioBean.chta=TypeObjeto(frmPantalla.selHTA);    
	cuestionarioBean.ctratamientodiabet=TypeObjeto(frmPantalla.selTratamiento);    
//	cuestionarioBean.ctiposBiopsia=TypeObjeto(frmPantalla.selTiposBiopsia);    
//	cuestionarioBean.cpolipo=TypeObjeto(frmPantalla.selPolipo);	
//	cuestionarioBean.utamanoEctropion=TypeObjeto(frmPantalla.txtTamaEctropion);		
	cuestionarioBean.idUsuario=frmPantalla.idUsuario.value;
	cuestionarioBean.bpacientefundacion=frmPantalla.hdnkPaciente.value;
	cuestionarioBean.kpacientecuestionario=frmPantalla.kPacienteCuestionario.value;
}

function actualizaCuestionario(){
		TypeObjeto(frmPantalla.radTabaquismo[0]); 
		TypeObjeto(frmPantalla.txtTabaquismoDia);
		TypeObjeto(frmPantalla.txtTabaquismoTiempo);
		TypeObjeto(frmPantalla.radDiabetes[0]); 
		TypeObjeto(frmPantalla.txtDiabetesTiempo);
		TypeObjeto(frmPantalla.selTratamiento);
		TypeObjeto(frmPantalla.txtMenarca);
		TypeObjeto(frmPantalla.txtDiasRitmo1);
		TypeObjeto(frmPantalla.txtDiasRitmo2);
		TypeObjeto(frmPantalla.txtFechaUltimaRegla);
		TypeObjeto(frmPantalla.txtVidaSexualActiva);
		TypeObjeto(frmPantalla.txtNumeroParejasSexuales);
		TypeObjeto(frmPantalla.txtGestaciones);
		TypeObjeto(frmPantalla.txtPartosNormales);
		TypeObjeto(frmPantalla.txtCesareas);
		TypeObjeto(frmPantalla.txtAbortos);
		TypeObjeto(frmPantalla.txtPrimerEmbarazo);
		TypeObjeto(frmPantalla.radLactancia[0]); 
		TypeObjeto(frmPantalla.txtClimaterio);
		TypeObjeto(frmPantalla.chkAntiRitmo);
		TypeObjeto(frmPantalla.chkAntiLocal);          
		TypeObjeto(frmPantalla.chkAntiParenteral);   
		TypeObjeto(frmPantalla.chkAntiOral);    	  
		TypeObjeto(frmPantalla.chkAntiVasectomia); 
		TypeObjeto(frmPantalla.chkAntiParche);
		TypeObjeto(frmPantalla.chkAntiDIU);  
		TypeObjeto(frmPantalla.chkAntiCoito);
		TypeObjeto(frmPantalla.chkAntiSalpingoclasia); 
		TypeObjeto(frmPantalla.chkAntiBarrera); 
		TypeObjeto(frmPantalla.chkAntiNada);    
		TypeObjeto(frmPantalla.chkAntiImplante);
		TypeObjeto(frmPantalla.chkAntiOtro);
		TypeObjeto(frmPantalla.txtOtroAnticonceptivo);
		TypeObjeto(frmPantalla.txtFechaUltimoPapanicolau);
		TypeObjeto(frmPantalla.selLugarPapanicolau);
		TypeObjeto(frmPantalla.selResultadoPapanicolau);
		TypeObjeto(frmPantalla.radVPH[0]); 
		TypeObjeto(frmPantalla.txtFechaVPH);
		TypeObjeto(frmPantalla.chkAnteceCervico);
		TypeObjeto(frmPantalla.chkAnteceMama);
		TypeObjeto(frmPantalla.txtAnteceHereFami);
		TypeObjeto(frmPantalla.selHTA);
		TypeObjeto(frmPantalla.txtFechaHisterectomia);
		TypeObjeto(frmPantalla.selMotivoHisterectomia);
		TypeObjeto(frmPantalla.chkOforectomiaDerecho);
		TypeObjeto(frmPantalla.chkOforectomiaIzuierdo);
		TypeObjeto(frmPantalla.chkOforectomiaNo);
		TypeObjeto(frmPantalla.txtFechaOforectomia);
		TypeObjeto(frmPantalla.txtMotivoOforectomia);
		TypeObjeto(frmPantalla.radEQX[0]); 
		TypeObjeto(frmPantalla.txtFechaEQX);
		TypeObjeto(frmPantalla.selMotivoCono);
		TypeObjeto(frmPantalla.radElectroCoagulacion[0]); 
		TypeObjeto(frmPantalla.txtFechaElectroCoagulacion);
		TypeObjeto(frmPantalla.selMotivoElectroCoagulacion);
		TypeObjeto(frmPantalla.radCrioterapia[0]); 
		TypeObjeto(frmPantalla.txtFechaCrioterapia);
		TypeObjeto(frmPantalla.txtMotivoCrioterapia);
		TypeObjeto(frmPantalla.selMotivoEstudio);
		TypeObjeto(frmPantalla.txtSintomatologiaActual);
//		TypeObjeto(frmPantalla.selCervix);
//		TypeObjeto(frmPantalla.chkCervixCentral); 
//		TypeObjeto(frmPantalla.chkCervixDerecho); 
//		TypeObjeto(frmPantalla.chkCervixIzquierdo);
//		TypeObjeto(frmPantalla.chkCervixAnterior); 
//		TypeObjeto(frmPantalla.chkCervixPosterior); 
//		TypeObjeto(frmPantalla.selSecrecion);
//		TypeObjeto(frmPantalla.chkSecresionFluida);   
//		TypeObjeto(frmPantalla.chkSecresionEspesa);
//		TypeObjeto(frmPantalla.chkSecresionSemiespesa);
//		TypeObjeto(frmPantalla.chkSecresionGrumosa);
//		TypeObjeto(frmPantalla.chkSecresionEspumosa);
//		TypeObjeto(frmPantalla.chkSecresionEscasa);
//		TypeObjeto(frmPantalla.chkSecresionModerada); 
//		TypeObjeto(frmPantalla.chkSecresionAbundante);
//		TypeObjeto(frmPantalla.chksecresionMixta);
//		TypeObjeto(frmPantalla.selDIU);
//		TypeObjeto(frmPantalla.txtVulvaOtros);
//		TypeObjeto(frmPantalla.selTiposBiopsia);
//		TypeObjeto(frmPantalla.[0]);
//		TypeObjeto(frmPantalla.txtDescripcionZonasAcetopositivas);
//		TypeObjeto(frmPantalla.selEctropion);
//		TypeObjeto(frmPantalla.txtTamaEctropion);		
//		TypeObjeto(frmPantalla.selPolipo);
//		TypeObjeto(frmPantalla.txtTamaPolipo);
//		TypeObjeto(frmPantalla.chkProcesoErosivo);
//		TypeObjeto(frmPantalla.chkMEI);
//		TypeObjeto(frmPantalla.chkMEIPO);
//		TypeObjeto(frmPantalla.chkQuisteNaboth);
//		TypeObjeto(frmPantalla.txtQuisteNaboth);
}


/************* Funciones Genericas ********************/
	function TypeObjeto(objComponente) {
		var strReturn = "";
		if (objComponente.type == "select-one") {
			strReturn = objComponente[objComponente.selectedIndex].value;
		} else if (objComponente.type == "text") {			
			strReturn = objComponente.value;
		} else if (objComponente.type == "checkbox") {
			strReturn = objComponente.checked;
		} else if (objComponente.type == "radio") {
			if (objComponente.checked) {
				strReturn = true;			
			} else {
				strReturn = false;			
			}
		}
		return strReturn;
	} 
/************* End Funciones Genericas ********************/

/************* Funciones Cuestionario ********************/
	function valorInicio(objComponente) {
		if (objComponente.type == "select-one") {
			objComponente.selectedIndex = 0;
		} else if (objComponente.type == "text") {			
			objComponente.value = "0";
		} else if (objComponente.type == "checkbox") {

		} else if (objComponente.type == "radio") {
			objComponente.checked = true;
		}
	} 
	
	function initCuestionario() {
		var frmPantalla = window.document.frmCuestionario;		
		valorInicio(frmPantalla.radTabaquismo[1]); 
		valorInicio(frmPantalla.txtTabaquismoDia);
		valorInicio(frmPantalla.txtTabaquismoTiempo);
		valorInicio(frmPantalla.radDiabetes[1]); 
		valorInicio(frmPantalla.txtDiabetesTiempo);
		valorInicio(frmPantalla.selTratamiento);
		valorInicio(frmPantalla.txtMenarca);
		frmPantalla.txtDiasRitmo1.value = 30;
		frmPantalla.txtDiasRitmo2.value = 4;
		valorInicio(frmPantalla.txtVidaSexualActiva);
		frmPantalla.txtNumeroParejasSexuales.value = 1;
		valorInicio(frmPantalla.txtGestaciones);
		valorInicio(frmPantalla.txtPartosNormales);
		valorInicio(frmPantalla.txtCesareas);
		valorInicio(frmPantalla.txtAbortos);
		valorInicio(frmPantalla.txtPrimerEmbarazo);
		valorInicio(frmPantalla.radLactancia[1]); 
		valorInicio(frmPantalla.txtClimaterio);
		valorInicio(frmPantalla.selLugarPapanicolau);
		valorInicio(frmPantalla.selResultadoPapanicolau);
		valorInicio(frmPantalla.radVPH[1]); 
		valorInicio(frmPantalla.selHTA);
		valorInicio(frmPantalla.selMotivoHisterectomia);
		valorInicio(frmPantalla.radEQX[1]); 
		valorInicio(frmPantalla.selMotivoEQX);
		valorInicio(frmPantalla.radElectroCoagulacion[1]); 
		valorInicio(frmPantalla.selMotivoElectroCoagulacion);
		valorInicio(frmPantalla.radCrioterapia[1]); 
		valorInicio(frmPantalla.selMotivoEstudio);
//		valorInicio(frmPantalla.selCervix);
//		valorInicio(frmPantalla.selSecrecion);
//		valorInicio(frmPantalla.selDIU);
//		valorInicio(frmPantalla.selTiposBiopsia);
//		valorInicio(frmPantalla.[1]);
//		valorInicio(frmPantalla.selEctropion);
//		valorInicio(frmPantalla.txtTamaEctropion);		
//		valorInicio(frmPantalla.selPolipo);
//		valorInicio(frmPantalla.txtTamaPolipo);
	}

/************* End Funciones Cuestionario ********************/
	
/************* Funciones Diagnostico ********************/
	
	function initDiagnostico() {
        var frmPantalla = window.document.frmDiagnostico;
	        valorInicio(frmPantalla.selMucosaAtrofica);
	        valorInicio(frmPantalla.chkColpoSano);
	        valorInicio(frmPantalla.chkColpoLEIBG);
	        valorInicio(frmPantalla.chkColpoLEIAG);
	        valorInicio(frmPantalla.chkColpoCancer);
	        valorInicio(frmPantalla.chkColpoBacteriana);
	        valorInicio(frmPantalla.chkColpoMicotica);
	        valorInicio(frmPantalla.chkColpoParasitaria);
	        valorInicio(frmPantalla.chkColpoMiomaedocervical);
	        valorInicio(frmPantalla.chkColpoCondilomatosis);
	        valorInicio(frmPantalla.chkVulvaBerruga);
	        valorInicio(frmPantalla.chkVulvaBartho);
	        valorInicio(frmPantalla.chkVulvaPapilomatosis);
	        valorInicio(frmPantalla.chkVulvaMolusco);
	        valorInicio(frmPantalla.chkVulvaNevos);
	        valorInicio(frmPantalla.chkVulvaQuiste);
	        valorInicio(frmPantalla.chkVulvaVPH);
	        valorInicio(frmPantalla.chkVulvaVulvitis);
	        valorInicio(frmPantalla.chkVulvaPerianal);
	        valorInicio(frmPantalla.chkVulvaVaginal);
	        valorInicio(frmPantalla.chkVulvaVulvar);
	        valorInicio(frmPantalla.chkPapaLEIBG);
	        valorInicio(frmPantalla.chkPapaLEIAG);
	        valorInicio(frmPantalla.chkPapaCarciInSitu);
	        valorInicio(frmPantalla.chkPapaCarciMicroinvasor);
	        valorInicio(frmPantalla.chkPapaCarcinomaInvasor);
	        valorInicio(frmPantalla.chkPapaAdenoInSitu);
	        valorInicio(frmPantalla.chkPapaAdenoInvasor);
	        valorInicio(frmPantalla.chkPapaCarciEpidermoide);
	        valorInicio(frmPantalla.chkPapaASCUS);
	        valorInicio(frmPantalla.chkPapaASCH);
	        valorInicio(frmPantalla.chkPapaCandida);
	        valorInicio(frmPantalla.chkPapaTrichomona);
	        valorInicio(frmPantalla.chkPapaActinomyces);
	        valorInicio(frmPantalla.chkPapaVaginosis);
	        valorInicio(frmPantalla.chkCervixLEIBG);
	        valorInicio(frmPantalla.chkCervixLEIAG);
	        valorInicio(frmPantalla.chkCervixCarciInSitu);
	        valorInicio(frmPantalla.chkCervixCarciMicroinvasor);
	        valorInicio(frmPantalla.chkCervixCarciInvasor);
	        valorInicio(frmPantalla.chkCervixAdenoInSitu);
	        valorInicio(frmPantalla.chkCervixAdenoInvasor);
	        valorInicio(frmPantalla.chkCervixCarciEpidermoide);
	        valorInicio(frmPantalla.chkCervixCondiloma);
	        valorInicio(frmPantalla.chkCervixPolipo);
	        valorInicio(frmPantalla.chkCervixAguda);
	        valorInicio(frmPantalla.chkCervixCronica);
	        valorInicio(frmPantalla.chkConoLEIBGsin);
	        valorInicio(frmPantalla.chkConoLEIAGsin);
	        valorInicio(frmPantalla.chkConoLEIBGcon);
	        valorInicio(frmPantalla.chkConoLEIAGcon);
	        valorInicio(frmPantalla.chkConoCarcinoma);
	        valorInicio(frmPantalla.chkConoAdenosin);
	        valorInicio(frmPantalla.chkConoAdenocon);
	        valorInicio(frmPantalla.chkConoAdenoInvasor);
	        valorInicio(frmPantalla.chkConoAguda);
	        valorInicio(frmPantalla.chkConoCronica);
	        valorInicio(frmPantalla.chkBlandosCondiloma);
	        valorInicio(frmPantalla.chkBlandosNivu);
	        valorInicio(frmPantalla.chkBlandosMolusco);
	        valorInicio(frmPantalla.chkBlandosBerruga);
	        valorInicio(frmPantalla.chkBlandosNevo);
	        valorInicio(frmPantalla.chkVaginaCondiloma);
	        valorInicio(frmPantalla.chkVaginaPolipo);
	        valorInicio(frmPantalla.chkVaginaNiva);
	        valorInicio(frmPantalla.chkVaginaProceso);
	}

/************* End Funciones Diagnostico ********************/
	
	
	
	function BPacienteCuestionarioBean() {	
		kpacientecuestionario=null,    
	    balergias=null,
	    smedicamento=null,
	    cocupacionpaciente=null,
	    cocupacionfamiliar=null,
	    cestadocivil=null,
	    cescolaridad=null,		
		btabaquismo=null,    
		utabaquismodia=null,    
		utabaquismotiempo=null,    
		bdiabetes=null,    
		udiabetestiempo=null,    
		umenarca=null,    		
		udiasritmo1=null,    
		udiasritmo2=null,    
		dfechaUltimaRegla=null,    
		uvidaSexualActiva=null,    
		unumeroParejasSexuales=null,    
		ugestaciones=null,    
		upartosNormales=null,    
		ucesareas=null,    
		uabortos=null,    
		uprimerEmbarazo=null,    
		blactancia=null,    
		uclimaterio=null,    
		bantiRitmo=null,    
		bantiLocal=null,    
		bantiParenteral=null,    
		bantiOral=null,    
		bantiVasectomia=null,    
		bantiParche=null,    
		bantiDiu=null,    
		bantiCoito=null,    
		bantiSalpingoclasia=null,    
		bantiBarrera=null,    
		bantiNada=null,    
		bantiImplante=null,    
		bantiOtro=null,
		sotroAnticonceptivo=null,    
		dfechaUltimoPapanicolau=null,    
		bvph=null,    
		dfechaVph=null,    
		banteceCervico=null,    
		banteceMama=null,
		santeceHereFami=null,    
		cHTA=null;
		dfechaHisterectomia=null,
		cmotivoHisterectomia=null,    
		boforectomiaDerecho=null,    
		boforectomiaIzuierdo=null,    
		boforectomiaNo=null,    
		dfechaOforectomia=null,
		smotivoOforectomia=null,    
		beqx=null,    
		dfechaEqx=null,
		cmotivoEqx=null,    
		belectroCoagulacion=null,    
		dfechaElectroCoagulacion=null,
		cmotivoElectroCoagulacion=null,    
		bcrioterapia=null,    
		dfechaCrioterapia=null,
		smotivoCrioterapia=null,
		cmotivoEstudio=null,
		ssintomatologiaActual=null,    
		bcervixCentral=null,    
		bcervixDerecho=null,    
		bcervixIzquierdo=null,    
		bcervixAnterior=null,    
		bcervixPosterior=null,    
		bsecresionFluida=null,    
		bsecresionEspesa=null,    
		bsecresionSemiespesa=null,    
		bsecresionGrumosa=null,    
		bsecresionEspumosa=null,    
		bsecresionEscasa=null,    
		bsecresionModerada=null,    
		bsecresionAbundante=null,    
		bsecresionMixta=null,
		svulvaOtros=null,    
		bunionEscamoclumnar=null,
		szonasAcetopositivas=null,    
		utamanoPeriorificiador=null,    
		bprocesoErosivo=null,    
		bmei=null,    
		bmeipo=null,    
		bquisteNaboth=null,    
		squisteNaboth=null,    
		cectropion=null,    
		csecrecion=null,    
		cdiu=null,    
		ccervix=null,    
		clugarPapanicolau=null,    
		bpacientefundacion=null,    
		cresultadoPapanicolau=null,    
		ctratamientodiabet=null,    
		ctiposBiopsia=null,    
		cpolipo=null,
		utamanoEctropion=null,
	    idUsuario=null
	}	