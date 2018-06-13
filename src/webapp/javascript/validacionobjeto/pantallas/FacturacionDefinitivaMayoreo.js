/******************** General ********************************/

   function init() 
   {
	    DWRUtil.useLoadingMessage();
   }

/********************* Negocio *******************************/
   
   function GeneracionFacturaConvenio()
   {
       	var frmPantalla = window.document.frmFacturacionMayoreo;
       	clavenew = TypeObjeto(frmPantalla.selConvenios); 
		if (confirm("Estas seguro de generar la facturacion del Convenio " + clavenew + "?")) {
            LoadFacturaElectronica();
			FacturacionMayoreoAjax.crearFacturaConvenio(facturaelectronicaBean,frmPantalla.IdUnidadActual.value,frmPantalla.idUsuario.value,false,clavenew,GeneracionFacturaConvenio_CallBack)
		}    			   
   }
   
   function GeneracionFacturaConvenio_CallBack(data)
   {
	   alert(data);
   }
   
 function LoadFacturaElectronica() {
		facturaelectronicaBean.sserie="A";
		facturaelectronicaBean.sfolio="1";
		facturaelectronicaBean.fecha="2010-12-31 10:55:31";
		facturaelectronicaBean.cconvenio=309;
		facturaelectronicaBean.nnumeroaprobacion="422843";
		facturaelectronicaBean.sanoaprobacion="2010";
		facturaelectronicaBean.sformapago="pago en una sola exhibicion"; 
		facturaelectronicaBean.mtotal= frmPantalla.txtTotalPagar.value;
		facturaelectronicaBean.miva=( frmPantalla.txtTotalPagar.value - (frmPantalla.txtTotalPagar.value / 1.16));
		facturaelectronicaBean.mdescuento=(((frmPantalla.txtDescuentoPaciente.value + frmPantalla.txtDescuentoEmpresa.value))/1.16);
		facturaelectronicaBean.stipocomprobante="ingreso";
		facturaelectronicaBean.sncertificado="00001000000202233501";
//		facturaelectronicaBean.sncertificado="00001000000102101701";
		//variables del emisorOlab	
		if (document.getElementById('cMarca').value == 1) {
			facturaelectronicaBean.srazonsocialemisor="ESTUDIOS CLINICOS DR TJ ORIARD SA DE CV";
			facturaelectronicaBean.srfcemisor="ECD741021QA5";
			facturaelectronicaBean.scalleemisor="AV. REVOLUCION No.56";
			facturaelectronicaBean.snexterioremisor="";
			facturaelectronicaBean.sninterioremisor="";
			facturaelectronicaBean.scoloniaemisor="ESCANDON";
			facturaelectronicaBean.sciudademisor="MEXICO D.F.";
			facturaelectronicaBean.smunicipioemisor="MIGUEL HIDALGO";
			facturaelectronicaBean.sestadoemisor="MEXICO D.F.";
			facturaelectronicaBean.scodigopostalemisor="11800";
			facturaelectronicaBean.spaisemisor="MEXICO";
			facturaelectronicaBean.cmarca = 1;
		} else {
			facturaelectronicaBean.srazonsocialemisor="LABORATORIO QUIMICO CLINICO AZTECA S.A.P.I. DE C.V.";
			facturaelectronicaBean.srfcemisor="LQC920131M20";
			facturaelectronicaBean.scalleemisor="SIMON BOLIVAR 15";
			facturaelectronicaBean.snexterioremisor="";
			facturaelectronicaBean.sninterioremisor="";
			facturaelectronicaBean.scoloniaemisor="LOS REYES ACAQUILPAN CENTRO";
			facturaelectronicaBean.sciudademisor="ESTADO DE MEXICO";
			facturaelectronicaBean.smunicipioemisor="LA PAZ";
			facturaelectronicaBean.sestadoemisor="MEXICO D.F.";
			facturaelectronicaBean.scodigopostalemisor="56400";
			facturaelectronicaBean.spaisemisor="MEXICO";				
			facturaelectronicaBean.cmarca = 4;
		}
		//variables del emisorSucursal
		facturaelectronicaBean.scallesuc="AV. VASCO DE QUIROGA NO.3380 P.B.";
		facturaelectronicaBean.snexteriorsuc="";
		facturaelectronicaBean.sninteriorsuc="";
		facturaelectronicaBean.scoloniasuc="LOMAS DE SANTA FE";
		facturaelectronicaBean.sciudadsuc="MEXICO D.F.";
		facturaelectronicaBean.smunicipiosuc="CUAJIMALPA";
		facturaelectronicaBean.sestadosuc="DISTRITO FEDERAL";
		facturaelectronicaBean.scodigopostalsuc="05300";
		facturaelectronicaBean.spaissuc="MEXICO";				
		//Datos del receptor
		facturaelectronicaBean.hDatosFiscal=frmPantalla.hdnkDatoFiscal.value;
		facturaelectronicaBean.srazonsocialreceptor=frmPantalla.txtRazonSocialFiscal.value;
		facturaelectronicaBean.srfcreceptor=frmPantalla.txtRFC.value;
		facturaelectronicaBean.scallereceptor=frmPantalla.txtCalleFiscal.value;
		facturaelectronicaBean.snexteriorreceptor="";
		facturaelectronicaBean.sninteriorreceptor="";
		facturaelectronicaBean.scoloniareceptor=frmPantalla.txtColoniaFiscal.value;
		facturaelectronicaBean.sciudadreceptor=frmPantalla.txtEstadoFiscal.value;
		facturaelectronicaBean.smunicipioreceptor=frmPantalla.txtDelegacionMunicipioFiscal.value;
		facturaelectronicaBean.sestadoreceptor=frmPantalla.txtEstadoFiscal.value;
		facturaelectronicaBean.scodigopostalreceptor=frmPantalla.txtCodigoPostalFiscal.value;
		facturaelectronicaBean.spaisreceptor="MEXICO";
		//impuesto
		facturaelectronicaBean.sdescripcion="";
		facturaelectronicaBean.ssellodigital="";
		facturaelectronicaBean.scadenaoriginal="";
		facturaelectronicaBean.ccliente=94; 	
	}

	function FacturaElectronicaBean() {
		sserie=null,
		sfolio=null,
		fecha=null,
		cconvenio=null,
		nnumeroaprobacion=null,
		sanoaprobacion=null,
		sformapago=null, 
		msubtotal=null,
		msubtotaluniemp=null,
		mdescuento=null,
		mtotal=null,
		stipocomprobante=null,
		sncertificado=null,
		cert=null,
	    //variables del emisorOlab	
		srazonsocialemisor=null,
		srfcemisor=null,
		scalleemisor=null,
		snexterioremisor=null,
		sninterioremisor=null,
		scoloniaemisor=null,
		sciudademisor=null,
		smunicipioemisor=null,
		sestadoemisor=null,
		scodigopostalemisor=null,
		spaisemisor=null,
		//variables del emisorSucursal
		scallesuc=null,
		snexteriorsuc=null,
		sninteriorsuc=null,
		scoloniasuc=null,
		sciudadsuc=null,
		smunicipiosuc=null,
		sestadosuc=null,
		scodigopostalsuc=null,
		spaissuc=null,
		//Datos del receptor
		hDatosFiscal=null,
		srazonsocialreceptor=null,
		srfcreceptor=null,
		scallereceptor=null,
		snexteriorreceptor=null,
		sninteriorreceptor=null,
		scoloniareceptor=null,
		sciudadreceptor=null,
		smunicipioreceptor=null,
		sestadoreceptor=null,
		scodigopostalreceptor=null,
		spaisreceptor=null,
		//impuesto
		miva=null,
		sdescripcion=null,
		ssellodigital=null,
		lstexamenes=null,
		scadenaoriginal=null,
		ccliente=null,
		cmarca=null
	}
   
	var datosfiscalesBean = new DatosFiscalesBean();


	function buscarDatoFiscalConvenio() {
		frmPantalla = window.document.frmFacturacionMayoreo;
		datosfiscalesBean.kDatosFiscales=0;
		datosfiscalesBean.strRFC="";
		datosfiscalesBean.strRazonSocial="";
		datosfiscalesBean.cConvenio = TypeObjeto(frmPantalla.selConvenios);
		DatosFiscales.getOneDatoFiscal(datosfiscalesBean,asignarDatoFiscal_CallBack);	
	}
	
	function asignarDatoFiscal_CallBack(data) {	
		if (data == null) {
		    adminDIV("DireccionFiscal","hidden","none");	
		    alert('NO EXISTEN DATOS FISCALES PARA ESTE CONVENIO, COMUNICATE CON TI POR FAVOR!!!');
		} else {
			frmPantalla = window.document.frmFacturacionMayoreo;
			frmPantalla.hdnkDatoFiscal.value = data.kDatosFiscales;
			frmPantalla.txtRFC.value = data.strRFC;
			frmPantalla.txtRazonSocialFiscal.value = data.strRazonSocial;
			frmPantalla.txtCalleFiscal.value = data.strDireccion;
			frmPantalla.hdnCodigoPostalFiscal.value = data.cCodigoPostal;
//			frmPantalla.txtRazonSocialFiscal.value = data.sPais;
			frmPantalla.txtCodigoPostalFiscal.value = data.cPostal;
			frmPantalla.txtEstadoFiscal.value = data.strEstado;
//			frmPantalla.txtRazonSocialFiscal.value = data.strCiudad;
			frmPantalla.txtDelegacionMunicipioFiscal.value = data.strDelegacionMunicipio;
			frmPantalla.txtColoniaFiscal.value = data.strColonia;
			document.getElementById('txtRFC').className = 'textflat';
			document.getElementById('txtRazonSocialFiscal').className = 'textflat';
			document.getElementById('txtCalleFiscal').className = 'textflat';
			document.getElementById('txtCodigoPostalFiscal').className = 'textflat';
			document.getElementById('txtEstadoFiscal').className = 'textflat';
			document.getElementById('txtDelegacionMunicipioFiscal').className = 'textflat';
			document.getElementById('txtColoniaFiscal').className = 'textflat';
			funreadonly(true);
		    adminDIV("DireccionFiscal","visible","inline");
		}
	}

	function funreadonly(bolType) {
		document.getElementById('txtRFC').readonly = bolType;
		document.getElementById('txtRazonSocialFiscal').readonly = bolType;
		document.getElementById('txtCalleFiscal').readonly = bolType;
		document.getElementById('txtCodigoPostalFiscal').readonly = bolType;
		document.getElementById('txtEstadoFiscal').readonly = bolType;
		document.getElementById('txtDelegacionMunicipioFiscal').readonly = bolType;
		document.getElementById('txtColoniaFiscal').readonly = bolType;
	}

	function DatosFiscalesBean() {
		kDatosFiscales=null,
		strRFC=null,
		strRazonSocial=null,
		strDireccion=null,
		cCodigoPostal=null,
		sPais=null,
		cPostal=null,
		cConvenio=null,
		strEstado=null,
		strCiudad=null,
		strDelegacionMunicipio=null,
		strColonia=null
	}

