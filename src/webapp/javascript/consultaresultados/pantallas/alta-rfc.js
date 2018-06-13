var datosfiscalesBean = new DatosFiscalesBean();

function altaDatosFiscales() {
	if ((document.getElementById('widgetu7841_input').value.length > 0) &&
		(document.getElementById('widgetu7846_input').value.length > 0) && 
		(document.getElementById('widgetu7987_input').value.length > 0) && 
		(document.getElementById('widgetu8008_input').value.length > 0) && 
		(document.getElementById('widgetu8003_input').value.length > 0) && 
		(document.getElementById('widgetu7997_input').value.length > 0) &&  
		(document.getElementById('widgetu7992_input').value.length > 0)) {
		datosfiscalesBean.kDatosFiscales=0;
		datosfiscalesBean.strRFC=document.getElementById('widgetu7841_input').value;
		datosfiscalesBean.strRazonSocial=document.getElementById('widgetu7846_input').value;
		datosfiscalesBean.strDireccion=document.getElementById('widgetu7987_input').value;
		datosfiscalesBean.cCodigoPostal=0;
		datosfiscalesBean.sPais="MEXICO";
		datosfiscalesBean.cPostal=document.getElementById('widgetu8008_input').value;
		datosfiscalesBean.strEstado=document.getElementById('widgetu8003_input').value;
		datosfiscalesBean.strCiudad=document.getElementById('widgetu8003_input').value;
		datosfiscalesBean.strDelegacionMunicipio=document.getElementById('widgetu7997_input').value;
		datosfiscalesBean.strColonia=document.getElementById('widgetu7992_input').value;
		DatosFiscales.crearDatosFiscalesFacturacionElectronicaInternet(datosfiscalesBean,1,asignarDatoFiscal_CallBack);	
		true;
	} else {
		alert('Es necesario llenar todos los campos para la facturacion....');
		false;
	}
}

function asignarDatoFiscal_CallBack(data) {	
	document.getElementById('hdnDatosFiscales').value = data.kDatosFiscales;
	if (data.unuevodatofiscal == 1) {
		alert('Se crearon los Datos Fiscales....');
	} else {
		alert('El RFC ya existia en nuestra Base de Datos....');
	}
	var liga = "confirmar-informacion.jsp?kordensucursalfinal=" + document.getElementById('txtAdmision').value + "&emailfinal=" + document.getElementById('txtCorreoElectronico').value + "&passwordfinal=" + document.getElementById('txtPassword').value + "&datofiscalfinal=" + document.getElementById('hdnDatosFiscales').value;     	  
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

