var datosfiscalesBean = new DatosFiscalesBean();


function consultaDatosFiscalesGrid(frmPantalla) {		
    if (frmPantalla.txtRFC.value==frmPantalla.txtConfirmarRFC.value) {
		LoadBasicDatosFiscales(frmPantalla);
	    adminDIV("DireccionFiscal","visible","inline");	
		DatosFiscales.showDatosFiscales(datosfiscalesBean,consultaDatosFiscalesGrid_CallBack);        	
	} else {
		alert('No es igual el RFC')
	 	codeDIVHTML("gridbusquedaDatosFiscales","");
	    adminDIV("gridbusquedaDatosFiscales","hidden","none");	 
	}
}	
		
function consultaDatosFiscalesGrid_CallBack(data)
{
    adminDIV("gridbusquedaDatosFiscales","visible","inline");
 	codeDIVHTML("gridbusquedaDatosFiscales",data);
}	 

function LoadBasicDatosFiscales(frmPantalla) {	
	datosfiscalesBean.kDatosFiscales=0;
	datosfiscalesBean.strRFC=frmPantalla.txtRFC.value;
	datosfiscalesBean.strRazonSocial="";
	datosfiscalesBean.cCodigoPostal=0;
	datosfiscalesBean.blike=false;
}

function asignarDatoFiscal(kDatosFiscal) {
	datosfiscalesBean.kDatosFiscales=kDatosFiscal;
	datosfiscalesBean.strRFC="";
	datosfiscalesBean.strRazonSocial="";
	DatosFiscales.getOneDatoFiscal(datosfiscalesBean,asignarDatoFiscal_CallBack);	
}

function comparaRFC() {
   frmPantalla = window.document.frmConsultaOrden;
   if (frmPantalla.txtRFC.value==frmPantalla.txtConfirmarRFC.value) {
	   document.getElementById('idTextConfirmacionrfc').innerHTML = "RFC IGUALES";
	   document.getElementById('idTextConfirmacionrfc').style.color = "black";
	   frmPantalla.idBuscarDatosFiscales.disabled = false;
   } else {
	   document.getElementById('idTextConfirmacionrfc').innerHTML = "NO SON IGUALES LOS RFC";
	   document.getElementById('idTextConfirmacionrfc').style.color  = "red";
	   frmPantalla.idBuscarDatosFiscales.disabled = true;
	   adminDIV("DireccionFiscal","hidden","none");
	   adminDIV("gridbusquedaDatosFiscales","visible","inline");
	   codeDIVHTML("gridbusquedaDatosFiscales","");
   }	
}

function cleanDatosFiscales() {
	frmPantalla = window.document.frmConsultaOrden;
	if (frmPantalla.idLimpiarDatosFiscales.value == "Limpiar Datos Fiscales") {
		funreadonly(false);
		codigoBean(frmPantalla.txtRFC,false,"");		
		codigoBean(frmPantalla.txtConfirmarRFC,false,"");		
		codigoBean(frmPantalla.txtRazonSocialFiscal,false,"");		
		codigoBean(frmPantalla.txtCalleFiscal,false,"");		
		codigoBean(frmPantalla.txtCodigoPostalFiscal,false,"");		
		codigoBean(frmPantalla.txtEstadoFiscal,false,"");		
		codigoBean(frmPantalla.txtDelegacionMunicipioFiscal,false,"");		
		codigoBean(frmPantalla.txtColoniaFiscal,false,"");		
		frmPantalla.hdnkDatoFiscal.value = 0;
		frmPantalla.hdnCodigoPostalFiscal.value = 0;
		frmPantalla.idLimpiarDatosFiscales.value = "Guardar Datos Fiscales";			        		
	    adminDIV("DireccionFiscal","hidden","none");
	    adminDIV("gridbusquedaDatosFiscales","visible","inline");
	 	codeDIVHTML("gridbusquedaDatosFiscales","");
	} else {
		if ((frmPantalla.txtRFC.value.length > 0) && (frmPantalla.txtRazonSocialFiscal.value.length > 0) && (frmPantalla.txtCalleFiscal.value.length > 0) && (frmPantalla.txtCodigoPostalFiscal.value.length > 0) && (frmPantalla.txtEstadoFiscal.value.length > 0) && (frmPantalla.txtDelegacionMunicipioFiscal.value.length > 0) &&  (frmPantalla.txtColoniaFiscal.value.length > 0)) {
			datosfiscalesBean.kDatosFiscales=0;
			datosfiscalesBean.strRFC=frmPantalla.txtRFC.value;
			datosfiscalesBean.strRazonSocial=frmPantalla.txtRazonSocialFiscal.value;
			datosfiscalesBean.strDireccion=frmPantalla.txtCalleFiscal.value;
			datosfiscalesBean.cCodigoPostal=0;
			datosfiscalesBean.sPais="MEXICO";
			datosfiscalesBean.cPostal=frmPantalla.txtCodigoPostalFiscal.value;
			datosfiscalesBean.strEstado=frmPantalla.txtEstadoFiscal.value;
			datosfiscalesBean.strCiudad="";
			datosfiscalesBean.strDelegacionMunicipio=frmPantalla.txtDelegacionMunicipioFiscal.value;
			datosfiscalesBean.strColonia=frmPantalla.txtColoniaFiscal.value;
			DatosFiscales.crearDatosFiscales(datosfiscalesBean,1,asignarDatoFiscal_CallBack);	
			true;
		} else {
			alert('Es necesario llenar todos los campos para la facturacion....');
			false;
		}
	}
}

function asignarDatoFiscal_CallBack(data) {	
	frmPantalla = window.document.frmConsultaOrden;
	frmPantalla.hdnkDatoFiscal.value = data.kDatosFiscales;
	frmPantalla.txtRFC.value = data.strRFC;
	frmPantalla.txtConfirmarRFC.value = data.strRFC;
	frmPantalla.txtRazonSocialFiscal.value = data.strRazonSocial;
	frmPantalla.txtCalleFiscal.value = data.strDireccion;
	frmPantalla.hdnCodigoPostalFiscal.value = data.cCodigoPostal;
//	frmPantalla.txtRazonSocialFiscal.value = data.sPais;
	frmPantalla.txtCodigoPostalFiscal.value = data.cPostal;
	frmPantalla.txtEstadoFiscal.value = data.strEstado;
//	frmPantalla.txtRazonSocialFiscal.value = data.strCiudad;
	frmPantalla.txtDelegacionMunicipioFiscal.value = data.strDelegacionMunicipio;
	frmPantalla.txtColoniaFiscal.value = data.strColonia;
	document.getElementById('txtRFC').className = 'textflat';
	document.getElementById('txtConfirmarRFC').className = 'textflat';
	document.getElementById('txtRazonSocialFiscal').className = 'textflat';
	document.getElementById('txtCalleFiscal').className = 'textflat';
	document.getElementById('txtCodigoPostalFiscal').className = 'textflat';
	document.getElementById('txtEstadoFiscal').className = 'textflat';
	document.getElementById('txtDelegacionMunicipioFiscal').className = 'textflat';
	document.getElementById('txtColoniaFiscal').className = 'textflat';
	funreadonly(true);
	frmPantalla.idLimpiarDatosFiscales.value = "Limpiar Datos Fiscales";			        		
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
	strEstado=null,
	strCiudad=null,
	strDelegacionMunicipio=null,
	strColonia=null,
	blike=null
}

