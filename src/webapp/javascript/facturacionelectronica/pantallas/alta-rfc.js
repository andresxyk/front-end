var datosfiscalesBean = new DatosFiscalesBean();

function altaDatosFiscales(srfc,sdireccion,sdelegacion,sestado,srazonsocial,scolonia,scodigopostal) {		
	datosfiscalesBean.kDatosFiscales=0;
	datosfiscalesBean.strRFC=srfc;
	datosfiscalesBean.strRazonSocial=srazonsocial;
	datosfiscalesBean.strDireccion=sdireccion;
	datosfiscalesBean.cCodigoPostal=0;
	datosfiscalesBean.sPais="MEXICO";
	datosfiscalesBean.cPostal=scodigopostal;
	datosfiscalesBean.strEstado=sestado;
	datosfiscalesBean.strCiudad=sestado;
	datosfiscalesBean.strDelegacionMunicipio=sdelegacion;
	datosfiscalesBean.strColonia=scolonia;
	DatosFiscales.crearDatosFiscalesFacturacionElectronicaInternet(datosfiscalesBean,1,asignarDatoFiscal_CallBack);	
}

function asignarDatoFiscal_CallBack(data) {	
	document.getElementById('hdnDatosFiscales').value = data.kDatosFiscales;
	if (data.unuevodatofiscal == 1) {
		alert("Se crearon los Datos Fiscales....");
	} else {
		alert("El RFC ya existia en nuestra Base de Datos....");
	}
	var liga = "confirmar-informacion.jsp?kordensucursalfinal=" + document.getElementById('txtAdmision').value + "&emailfinal=" + document.getElementById('txtCorreoElectronico').value + "&passwordfinal=" + document.getElementById('txtPassword').value + "&datofiscalfinal=" + data.kDatosFiscales;     	  
	window.location.href =liga;	
}

function DatosFiscalesBean() {
	kDatosFiscales=null,
	strRFC=null,
	strRazonSocial=null,
	strDireccion=null,
	cCodigoPostal=null,
	sPais=null,
	cPostal=null,
	strEstado=null,
	strCiudad=null,
	strDelegacionMunicipio=null,
	strColonia=null,
	blike=null
}

