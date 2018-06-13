var facturaelectronicaBean = new FacturaElectronicaBean();

function init() {
//	DWRUtil.useLoadingMessage();
    adminDIV("gridprogressbar","hidden","none");        
}

function editarrfc() {
	var liga = document.getElementById('hdnEditarRFC').value;
	window.location.href =liga;
}

function guardarFactura() {
	if (document.getElementById('hdnProcesandoFactura').value == 0) {
		document.getElementById('hdnProcesandoFactura').value = "1";
		document.getElementById("btnaceptar").disabled = true;	
		document.getElementById("btn_aceptar_font").innerHTML="PROCESANDO...espere...";	
		if ((document.getElementById('hdnDatosFiscales').value > 0) && (document.getElementById('hdnDatosFiscales').value != "") && (document.getElementById('txtAdmision').value >0) && (document.getElementById('txtAdmision').value != "")) {
			if (trim(document.getElementById('txtCorreoElectronico').value) != "") {
				if (document.getElementById('txtCorreoElectronico').value.length > 3) {
					LoadFacturaElectronica();
					OrdenFacturarAjax.crearOrdenFacturarElectronica(facturaelectronicaBean,document.getElementById('txtAdmision').value,
																	document.getElementById('IdSucursalActual').value,
																	1,
																	false,
																	document.getElementById('txtCorreoElectronico').value,generarFactura_CallBack);
				} else {
					alert('Es necesario ingresar el e-mail del paciente');
				}
			} else {
				alert('Existe un error en el correo electronico, agreguelo de nuevo por favor.');
			}
		} else {
			alert('Existe un error en la seleccion de los Datos Demograficos y/o Consecutivo, busquelos de nuevo por favor.');
		}
	} else {
		alert('Solicite su factura una sola vez....espera la respuesta del sistema');
	}
}

function guardarreFactura() {
	if (document.getElementById('hdnProcesandoFactura').value == 0) {
		document.getElementById('hdnProcesandoFactura').value = "1";
		document.getElementById("btnaceptar").disabled = true;	
		document.getElementById("btn_aceptar_font").innerHTML="PROCESANDO...espere...";	
		if ((document.getElementById('hdnDatosFiscales').value > 0) && (document.getElementById('hdnDatosFiscales').value != "") && (document.getElementById('txtAdmision').value >0) && (document.getElementById('txtAdmision').value != "")) {
			if (trim(document.getElementById('txtCorreoElectronico').value) != "") {
				if (document.getElementById('txtCorreoElectronico').value.length > 3) {
					LoadFacturaElectronica();
					OrdenFacturarAjax.crearOrdenFacturarElectronica(facturaelectronicaBean,document.getElementById('txtAdmision').value,
																	document.getElementById('IdSucursalActual').value,
																	1,
																	true,
																	document.getElementById('txtCorreoElectronico').value,generarFactura_CallBack);
				} else {
					alert('Es necesario ingresar el e-mail del paciente');
				}
			} else {
				alert('Existe un error en el correo electronico, agreguelo de nuevo por favor.');
			}
		} else {
			alert('Existe un error en la seleccion de los Datos Demograficos y/o Consecutivo, busquelos de nuevo por favor.');
		}
	} else {
		alert('Solicite su factura una sola vez....espera la respuesta del sistema');
	}
}


function generarFactura_CallBack(data) {
	var liga = "facturacion-exitosa.jsp?urlFactura=" + data + "&cmarca=" + document.getElementById('cMarca').value;
	window.location.href =liga;
}
		 
function LoadFacturaElectronica() {
	facturaelectronicaBean.sserie="A";
	facturaelectronicaBean.sfolio="1";
	facturaelectronicaBean.fecha="2010-12-31 10:55:31";
	facturaelectronicaBean.cconvenio=0;
	facturaelectronicaBean.nnumeroaprobacion="422843";
	facturaelectronicaBean.sanoaprobacion="2010";
	facturaelectronicaBean.sformapago="pago en una sola exhibicion"; 
	facturaelectronicaBean.mtotal= document.getElementById('dblTotalPagar').value;
	facturaelectronicaBean.miva=( document.getElementById('dblTotalPagar').value - (document.getElementById('dblTotalPagar').value / 1.16));
	facturaelectronicaBean.mdescuento=(((0))/1.16);
	facturaelectronicaBean.stipocomprobante="ingreso";
	facturaelectronicaBean.sncertificado="00001000000202233501";
//				facturaelectronicaBean.sncertificado="00001000000102101701";
	//variables del emisorOlab	
//	alert(document.getElementById('cMarca').value);
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
	facturaelectronicaBean.hDatosFiscal=document.getElementById('hdnDatosFiscales').value;
	facturaelectronicaBean.srazonsocialreceptor=document.getElementById('strRazonSocial').value;
	facturaelectronicaBean.srfcreceptor=document.getElementById('strRFC').value;
	facturaelectronicaBean.scallereceptor=document.getElementById('strCalle').value;
	facturaelectronicaBean.snexteriorreceptor="";
	facturaelectronicaBean.sninteriorreceptor="";
	facturaelectronicaBean.scoloniareceptor=document.getElementById('strColonia').value;
	facturaelectronicaBean.sciudadreceptor=document.getElementById('strCiudad').value;
	facturaelectronicaBean.smunicipioreceptor=document.getElementById('strMunicipio').value;
	facturaelectronicaBean.sestadoreceptor=document.getElementById('strEstado').value;
	facturaelectronicaBean.scodigopostalreceptor=document.getElementById('strCodigoPostal').value;
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
	cmarca=null;
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
	ccliente=null 	
}
				     		 
		 		 