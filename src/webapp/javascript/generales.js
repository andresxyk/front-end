/**
adminDIV* constantes para validaciones
*/
var letras  = "abcdefghijklmn?opqrstuvwxyzABCDEFGHIJKLMN?OPQRSTUVWXYZ ";
var digito = "0123456789";
var dPD = ".";
var space = " \t\n\r";
var ok = "yes";

function loopSelected(strNameSelect)
{
  var txtSelectedValuesObj = document.getElementById('txtSelectedValues');
  var selectedArray = new Array();
  var selObj = document.getElementById(strNameSelect);
  var i;
  var count = 0;
  for (i=0; i<selObj.options.length; i++) {
    if (selObj.options[i].selected) {
      selectedArray[count] = selObj.options[i].value;
      count++;
    }
  }
  return selectedArray;
}

function setValidarTextos(strTextBox,uTypeValidacion) {
	var bolReturn = true;
	var strRed = "#F4A460";
	var strBlanco = "white"
	if (document.getElementById(strTextBox) != null) {	
		if (uTypeValidacion == 1) {
		    if( !validaVacios(document.getElementById(strTextBox).value) ) {
		    	document.getElementById(strTextBox).style.backgroundColor = strRed;
		    	bolReturn = false;	    
		    } else {
		    	document.getElementById(strTextBox).style.backgroundColor = strBlanco;
		    	bolReturn = true;	    
		    } 
		} else {
		    if( !validaTextos(document.getElementById(strTextBox).value) ) {
		    	document.getElementById(strTextBox).style.backgroundColor = strRed;
		    	bolReturn = false;	    
		    } else {
		    	document.getElementById(strTextBox).style.backgroundColor = strBlanco;
		    	bolReturn = true;	    
		    } 
		    if( !validaVacios(document.getElementById(strTextBox).value) ) {
		    	document.getElementById(strTextBox).style.backgroundColor  = strRed;
		    	bolReturn = false;	    
		    } else {
		    	document.getElementById(strTextBox).style.backgroundColor  = strBlanco;
		    	bolReturn = true;	    
		    } 
		}
		return bolReturn;
	} else {
		return false;
	}
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

function eliminarElemento(id){
	imagen = document.getElementById(id);
	if (!imagen){
//		alert("El elemento selecionado no existe");
	} else {
		padre = imagen.parentNode;
		padre.removeChild(imagen);
	}
}	

function valorCombo(lstCombo,Id) {
  for (var i=0;i<lstCombo.length;i++) {
    if(lstCombo.options[i].value==Id) {
    	lstCombo.options[i].selected=true;
    } else {
    	lstCombo.options[i].selected=false;
    }
  }
}
/************* End Funciones Genericas ********************/

function codigoBean(txtTextBox,bolType,strdata) {
	txtTextBox.disabled = bolType;
	txtTextBox.value = strdata;		
	if (bolType == false) {
		document.getElementById(txtTextBox.id).className = 'text';
		document.getElementById(txtTextBox.id).style.fontSize = 11;			
	} else {
		document.getElementById(txtTextBox.id).className = 'textflat';			
		document.getElementById(txtTextBox.id).style.fontSize = 22;			
	}
}

function compareSelect(objSelect,strCompare) {
	for(var x=0; x <= document.getElementById(objSelect.id).length; x = x+1) { 
		if (objSelect[x].text == strCompare) {
			return x;
		}
	};
}

function buscaTextoSelect(objSelect,strCompare) {
	var strText = "";
	var intPosicion = -1;
	for(var x=0; x <= document.getElementById(objSelect.id).length; x = x+1) { 
		strText = objSelect[x].text;
		intPosicion = strText.lastIndexOf(strCompare);
		if (intPosicion != -1) {
			objSelect.options[x].selected=true;
			return true;
		}
	};
	return false;
}

function compareSelectvalue(objSelect,intCompare) {
	for(var x=0; x < document.getElementById(objSelect.id).length; x = x+1) { 
		if (objSelect[x].value == intCompare) {
			objSelect.options[x].selected=true;
			return true;
		}
	};
}

function adminDIV(strDIV,strFuncion,strDisplay){    	
	var objDIV = document.getElementById(strDIV);
	objDIV.style.visibility = strFuncion;
	objDIV.style.display = strDisplay;
}

function codeDIVHTML(strDIV,strCode){
	htmlOb=document.getElementById(strDIV);
	htmlOb.innerHTML=strCode;    	
}

/**
 * funcion para cambiar a mayusculas lo 
 * que se haya tecleado
 */
function soloNumerosCP(liga){				
	var key=window.event.keyCode;//codigo de tecla.				
	if(key==13){
    	return obtenerCodigoPostal(liga);
	}else if(!numero()){
    	alert("Solo se permite ingresar numeros");
    	return false;
    }
}
  
/**
 * funcion que permite solo valores numericos y ciertos caracteres
 * especiales como - y espacio en blanco.
 */
function validaTelefono( txtValor ) {
	var strValido = "1234567890-";
	var ok = "si";
	var temp;
	for (var i=0; i< txtValor.length; i++) {
		temp = "" + txtValor.substring(i, i+1);
		if (strValido.indexOf(temp) == "-1") ok = "no";
	}
	if (ok == "no") {
		return false;
	} else { 
		return true;
	}
}

/**
 * funcion que valida textos sin permitir caracteres como numeros
 * y otros caracteres especiales tales como: *+!#$%&/()=???+*{};:<>
 */
function validaTextos( txtValor ) {
	var strValido = "1234567890*+!#$%&/()=???+*{};:<>-@";
	var ok = "si";
	var temp;
	for (var i=0; i< txtValor.length; i++) {
		temp = "" + txtValor.substring(i, i+1);
		if (strValido.indexOf(temp) != "-1") ok = "no";
	}
	if (ok == "no") {
		return false;
	} else { 
		return true;
	}
}

/**
* funcion que valida textos sin permitir caracteres como numeros
* y otros caracteres especiales tales como: *+!#$%&/()=???+*{};:<>
*/
function validaTextosNuevos( txtValor ) {
	var strValido = "|";
	var ok = "si";
	var temp;
	for (var i=0; i< txtValor.length; i++) {
		temp = "" + txtValor.substring(i, i+1);
		if (strValido.indexOf(temp) != "-1") ok = "no";
	}
	if (ok == "no") {
		return false;
	} else { 
		return true;
	}
}


/**
* funcion para cambiar a mayusculas lo 
* que se haya tecleado
*/
function mayuscula(){
    var key=window.event.keyCode;//codigo de tecla.
    if (key < 123 && key > 96 ){ 
        window.event.keyCode=window.event.keyCode-32;
    }
    else{
         //alert("Key:"+key);
         if(key == 241){    //C?digo de ? 241
                window.event.keyCode=209;//C?digo de ? 209
         }	    	
    }
}


/**
 * funcion que valida que los campos de texto considerados como
 * obligatorios no queden vacios al momento de enviar la peticion.
 */
function validaVacios( txtValor ) {
	if( trim(txtValor) == null || trim(txtValor) == "" ) {
		return false;
	} else { 
		return true;
	}			
}

/**
 * funcion que invoca la funcionalidad de calcular la edad
 * en anos, meses y dias de CalculaEdad.js
 */
function obtenerEdadfrm(frmPantalla) {		
 	txtFechaNac = frmPantalla.txtFechaNacimiento.value;
	if( txtFechaNac != null && txtFechaNac != "" ) {
		txtFechaNac = txtFechaNac.toUpperCase();
	 	intEdadAnios = calcularAniosFecha(txtFechaNac, "-");
	 	if(intEdadAnios < 0){
	 		alert("Fecha de nacimiento incorrecta");
	 		frmPantalla.txtFechaNac.value = "";
	 		frmPantalla.txtEdadDescompuesta.value = "";
	 		return false;
	 	}
	 	intEdadMeses = calcularMesesFecha(txtFechaNac, "-");
 		intEdadDias  = calcularDiasFecha(txtFechaNac, "-"); 		 	
 		frmPantalla.txtEdadDescompuesta.value = intEdadAnios + "-" + intEdadMeses + "-" + intEdadDias;
 	}
}


/**
 * funcion que invoca valida si la fecha es mayor al dia de hoy
 */
function validafechafrm(txtObject) {		
 	txtFechaNac = txtObject.value;
	if( txtFechaNac != null && txtFechaNac != "" ) {
		txtFechaNac = txtFechaNac.toUpperCase();
	 	intEdadAnios = calcularAniosFecha(txtFechaNac, "-");
	 	if(intEdadAnios < 0){
	 		alert("Fecha no valida");
	 		txtObject.value = "";
	 		return false;
	 	}
 	}
}


/**
* funcion que se encarga de activar o desactivar un textbox
*/
function disableTextBox(txtBox,strname,strValue,intFont) {
		if (intFont > 0) {
			document.getElementById(strname).style.fontSize=intFont;
			document.getElementById(strname).style.color="black";
		}
		document.getElementById(strname).className = 'textflat';
	 	txtBox.value = strValue;
}


/**
* funcion para cambiar a mayusculas lo 
* que se haya tecleado e ignorando caracteres especiales.
*/
function mayusculaIgnorandoCaracteres(){
    var key=window.event.keyCode;//codigo de tecla.
    if (key < 123 && key > 96 ){ 
        window.event.keyCode=window.event.keyCode-32;
    }
    else{
         //alert("Key:"+key);
         if(key == 241){    //C?digo de ? 241
                window.event.keyCode=209;//C?digo de ? 209
         }	    	
    }
    
    var keyend = window.event.keyCode; //codigo de tecla.

    if ( ((keyend < 65) || (keyend > 90)) && (keyend != 32) && (keyend != 209) && (!(key<58 && key>47))) {
    	alert("No es una tecla valida");
    	window.event.keyCode = 10;
    } 
}


/**
* funcion para cambiar a minusculas lo 
* que se haya tecleado e ignorando caracteres especiales.
*/
function minusculasIgnorandoCaracteres(){
    var key=window.event.keyCode;//codigo de tecla.
    if (key < 123 && key > 96 ){ 
        window.event.keyCode=window.event.keyCode+32;
    }
    else{
         //alert("Key:"+key);
         if(key == 241){    //C?digo de ? 241
                window.event.keyCode=209;//C?digo de ? 209
         }	    	
    }
    
    var keyend = window.event.keyCode; //codigo de tecla.

    if ( ((keyend < 65) || (keyend > 90)) && (keyend != 32) && (keyend != 209) && (!(key<58 && key>47))) {
    	alert("No es una tecla valida");
    	window.event.keyCode = 10;
    } 
}

/**
* funcion para permitir capturar solo numeros y letras  
* del teclado 
*/
function validaRFC(frmPantalla){
    var key=window.event.keyCode;
    var txtSRFC = frmPantalla.txtRFC;
    var txtSRFCConfirmar = frmPantalla.txtConfirmarRFC;
    var cboCTIPOPERSONA = frmPantalla.selTipoPersona;
    var lengthRFC = txtSRFC.value.length;
    var lengthRFCConfirmar = txtSRFCConfirmar.value.length;
	if (cboCTIPOPERSONA.value == 1) {
		if ((lengthRFC > 13) && (lengthRFCConfirmar >13)) {
			window.event.keyCode = 0;
			alert('Para personas Fisicas solo son 13 caracteres');
			return false;			
		}    
	} else if (cboCTIPOPERSONA.value == 2) {   	
		if ((lengthRFC > 12) && (lengthRFCConfirmar >12)) {
			window.event.keyCode = 0;
			alert('Para personas Fisicas solo son 12 caracteres');
			return false;			
		}    
	}   	

    
    
    if (cboCTIPOPERSONA.value == 0) {
		alert("Selecciona el Tipo de Empresa que estas dando de alta !!!");
		window.event.keyCode = 0;
		txtSRFC.value = "";
		return false;
    } else {
    	if (cboCTIPOPERSONA.value == 1) {
	    	var iniLetras = -1;
	    	var endLetras = 3;
	    	var iniAno = 4;
	    	var endAno = 5;
	    	var iniMes = 6;
	    	var endMes = 7;
	    	var iniDia = 8;
	    	var endDia = 9;
    	} else if (cboCTIPOPERSONA.value == 2) {   	
	    	var iniLetras = -1;
	    	var endLetras = 2;
	    	var iniAno = 3;
	    	var endAno = 4;
	    	var iniMes = 5;
	    	var endMes = 6;
	    	var iniDia = 7;
	    	var endDia = 8;    
    	}   	
	    if (key < 123 && key > 96 ){ 
	    	window.event.keyCode=window.event.keyCode-32;
	    } else{
	         //alert("Key:"+key);
	        if(key == 241){    //C?digo de ? 241
	        	window.event.keyCode=209;//C?digo de ? 209
	        }	    	
	    }	
	    if (!((key<58 && key>47) || (key<91 && key>64) || (key<123 && key>93))) { 
			if(key!=13){
				window.event.keyCode = 0;
				return false;
			}
	    }	
	    if (lengthRFC > iniLetras && lengthRFC <= endLetras) {
			if (!((key<91 && key>64) || (key<123 && key>93))) {
		    	alert("Ãšnicamente se aceptan letras");
				window.event.keyCode = 0;
				return false;
			}
	    } else if ( (lengthRFC >= iniAno) && (lengthRFC <= endAno)) {
			if (!(key<58 && key>47)) {
		    	alert("Ãšnicamente se aceptan nÃºmeros");
				window.event.keyCode = 0;
				return false;
			}
	    } else if ( (lengthRFC >= iniMes) && (lengthRFC <= endMes)) {
			if (!(key<58 && key>47)) {
		    	alert("Ãšnicamente se aceptan nÃºmeros");
				window.event.keyCode = 0;
				return false;
			} else if (lengthRFC == iniMes) {
				if(!(key<50 && key>47)) {
					alert("Ãšnicamente se aceptan 0 o 1");
					window.event.keyCode = 0;
					return false;
	        	}
			} else if (lengthRFC == endMes) {
				if ((txtSRFC.value.substr(iniMes,1) == "1") && !(key<51 && key>47)){                                       
					alert("Ãšnicamente se aceptan 0, 1 o 2");
					window.event.keyCode = 0;
					return false;					
				} else if (!(key<58 && key>48) && (txtSRFC.value.substr(iniMes,1) == "0")){
					alert("Ãšnicamente se aceptan 1 al 9");
					window.event.keyCode = 0;
					return false;
				}
	    	}
	    } else if ( (lengthRFC >= iniDia) && (lengthRFC <= endDia)) {
			if (!(key<58 && key>47)) {
		        alert("Ãšnicamente se aceptan nÃºmeros");
				window.event.keyCode = 0;
				return false;
			} else if (lengthRFC == iniDia) {
				if(!(key<52 && key>47)) {
			        alert("Ãšnicamente se aceptan 0, 1, 2 o 3");
					window.event.keyCode = 0;
					return false;
	            }
			} else if (lengthRFC == endDia) {
				if ((txtSRFC.value.substr(iniDia,1) == "3")){
					if (!(key<50 && key>47)) {
						alert("Ãšnicamente se aceptan 0 o 1");
						window.event.keyCode = 0;
						return false;				
					}	
				} else if ((txtSRFC.value.substr(iniDia,1) == "1" || txtSRFC.value.substr(iniDia,1) == "2") && key == 48) {
				} else if (!(key<58 && key>48)){
					alert("Ãšnicamente se aceptan 1 al 9");
					window.event.keyCode = 0;
					return false;
				}
	        }
	    }
	    return true;
	}
}





/**
* funcion para permitir capturar solo numeros 
* del teclado 
*/
function numero(){
	var key=window.event.keyCode;
	if (!(key<58 && key>47)){ 
		if(key!=13){
			window.event.keyCode = 0;
			return false;
		}
	}
	return true;
}

/**
* funcion para permitir capturar solo numeros de montos
* del teclado 
*/
function montos(){
	var key=window.event.keyCode;
	if (!(key<58 && key>47)){ 
		if((key!=46)){
			if(key!=13){
				window.event.keyCode = 0;
				return false;
			}
		}
	}
	return true;
}


function porcentaje(txtPorcentaje,strName){
	if ((txtPorcentaje.value >= 0) && (txtPorcentaje.value <=100)){ 
		return true;
	} else {
		txtPorcentaje.value = "";
		alert("El " + strName + " debe ser numerico y en un rango entre 0 y 100")
		return false;
	}
}


/**
* cambia a mausculas el valor de un campo
*/
function cambiaMay(forma, campo){
	obj_elem = eval('window.document.' + forma + '.' + campo);
	var cambiaMayusculas = obj_elem.value;
	obj_elem.value = cambiaMayusculas.toUpperCase();
}

//abre una nueva ventana con las carateristicas dadas
function abrirVentana(strLiga, nombre){
	var props="toolbar=0,location=0,status=0,menubar=0,scrollbars=1,resizable=1,width=900,height=600,dependent=Yes,alwaysRaised=Yes";
	ventanaNormal(strLiga, nombre, props);
//	return false;
}

//abre una nueva ventana con las carateristicas dadas
function abrirVentanaOrden(strLiga, nombre){
	var props="toolbar=0,location=0,status=0,menubar=0,scrollbars=1,resizable=1,width=1200,height=800,dependent=Yes,alwaysRaised=Yes";
	ventanaNormal(strLiga, nombre, props);
	return false;
}


//valida que los valores de orden/admision sean numericos
function validaNumero(aObj, aMsg){
   	if (!esNumeroValido(aObj.value)){
   		if (aObj.value!=""){
			alert("Ingrese un numero v?lido (" + aMsg + ")");
			aObj.value='';
		}
		aObj.value='';
		aObj.focus;
		return false;
	} 
	return true;
}

/** funcion que valida si el string que se manda 
* es un numero valido. El numero puede contener 
* signo y decimales  
* @param strNumero string que contiene el numero a validar
* @return boolean  
*/
function esNumeroValido(strNumero) {
   return /^[-+]?\d+(\.\d+)?$/.test(strNumero);
}

/** funcion que valida si el string que se manda 
* es un numero entero valido y sin signo 
* @param strNumero string que contiene el numero a validar
* @return boolean  
*/
function esNumeroEntero(strNumero){
	return /^\d+$/.test(strNumero);
}

/** funcion muestra/oculta una seccion de html
* @param nombre: el id del elemento que se desea desplegar
* @param patron: el patron que presenta el id (nombre) del tag
* @param tagName: tipo de tag que se muestra/oculta, 'TABLE', 'SPAN', 'DIV' 
* @return void 
*/
function desplegar(nombre, patron, tagName){
	var regPatron = new RegExp(patron,"i");
	var arrElem = document.getElementsByTagName(tagName);
	for(var i=0;i<arrElem.length;i++){
		if(arrElem[i].id.search(regPatron)!=-1){
			if (arrElem[i].id == nombre){
        		arrElem[i].style.display = 'block';
			}
			else {
				arrElem[i].style.display='none';
			}
		}
	}
}

/** funcion muestra/oculta una seccion de html
* @param nombre: el id de la seccion que se desea mostrar/ocultar 
* @param patron: el patron que presenta el id (nombre) del tag
* @param tagName: tipo de tag que se muestra/oculta, 'TABLE', 'SPAN', 'DIV' 
* @return void 
*/
function despliega(nombre, patron, tagName){
	var actual; 
	var regPatron = new RegExp(patron,"i");
	var arrElem = document.getElementsByTagName(tagName);
	for(var i=0;i<arrElem.length;i++){
		if(arrElem[i].id.search(regPatron)!=-1){
			if (arrElem[i].id == nombre){
        		actual = (arrElem[i].style.display == 'block') ? 'none' : 'block';
        		arrElem[i].style.display = actual;
			}
			else {
				arrElem[i].style.display='none';
			}
		}
	}
}

/** funcion para eliminar los espacios en blanco 
* a la derecha y a la izquierda de una cadena
* @param strValue String al que desamos quitar los espacios
* @return String 
*/
function trim(strValue) {
   var temp = strValue;
   var obj = /^(\s*)([\W\w]*)(\b\s*$)/;
   if (obj.test(temp)) { temp = temp.replace(obj, '$2'); }
   var obj = / +/g;
   temp = temp.replace(obj, " ");
   if (temp == " ") { temp = ""; }
   return temp;
}

/**
* Abre una ventana en forma modal
* @param pagina: URL de la p?gina que se requiere 
* @param nombre: el nombre que se desea dar a la pagina que se abre
* @param props: las propiedades de la p?gina que se abre (ancho, alto, centrado, etc) 
* @return void 
*/
function ventanaModal(pagina, nombre, props) {
	var opciones=props; 
	if (opciones==""){
		opciones = "dialogHeight: 437px; dialogWidth: 700px; dialogTop: 0px; dialogLeft: 0px; edge: Raised; center: Yes; resizable: Yes; status: No;";
	}
	window.showModalDialog(pagina,nombre,opciones);
}

/**
* Abre una ventana en forma Normal
* @param pagina: URL de la p?gina que se requiere 
* @param nombre: el nombre que se desea dar a la pagina que se abre
* @param props: las propiedades de la p?gina que se abre (ancho, alto, centrado, etc) 
* @return void 
*/
function ventanaNormal(pagina, nombre, props) {
	var opciones=props; 
	if (opciones==""){
		opciones = "toolbar=No,location=No,status=No,menubar=No,scrollbars=No,resizable=Yes,width=600,height=450,dependent=Yes,alwaysRaised=Yes";
	}
	var ventana = window.open(pagina,nombre,opciones);
	//Por si esta atras, traerla al frente.
	ventana.focus();
	return ventana;
}

/**
* Funcion que regresa el valor del radio que ha 
* sido seleccionado dentro del arreglo con el  
* nombre chkName 
* @param frm: el objeto asociado a la forma en que se 
* busca el arreglo de radios
* @param chkName: Nombre del arreglo de radios que se busca 
* @return String 
*/
function selectedRadio(frm, radioName){
	strvalue='';
	for(var i=0;i<frm.elements.length;i++){
		if(frm.elements[i].type=="radio" && frm.elements[i].name==radioName){
			if (frm.elements[i].checked==true){
				strvalue = frm.elements[i].value;
				break;
			}
		}
	}
	return strvalue;
}

/**
* Funcion que regresa un String con los valores de los checkbox
* que estan seleccionados, separados por pipes (|) y que 
* corresponden al nombre chkName
* @param frm: el objeto asociado a la forma en que se 
* busca el arreglo de checkbox's
* @param chkName: Nombre del arreglo de checkbox's que se busca 
* @return String 
*/
function selectedCheck(frm, chkName){
	var strvalue='';
	var ctrlType = 'checkbox';
	for(var i=0;i<frm.elements.length;i++){
		if(frm.elements[i].type==ctrlType && frm.elements[i].name==chkName){
			if (frm.elements[i].checked==true){
				if(strvalue==""){
					strvalue = frm.elements[i].value;  
				}
				else {
					strvalue += "|" + frm.elements[i].value;  
				}
			}
		}
	}
	return strvalue;
}

function selectedCheckToken(frm, chkName, token){
	var strvalue='';
	var ctrlType = 'checkbox';
	for(var i=0;i<frm.elements.length;i++){
		if(frm.elements[i].type==ctrlType && frm.elements[i].name==chkName){
			if (frm.elements[i].checked==true){
				if(strvalue==""){
					strvalue = frm.elements[i].value;  
				}else{
					strvalue += token + frm.elements[i].value;  
				}
			}
		}
	}
	return strvalue;
}

function roundOff(value, precision){
	var value = "" + value //convert value to string		
    var precision = parseInt(precision);        
    var whole = "" + Math.round(value * Math.pow(10, precision));        
    var decPoint = whole.length - precision;        
    var result = "";
    if(decPoint >= 0 || decPoint == -1 ){            
    	result = whole.substring(0, decPoint);
        result += ".";
        result += whole.substring(decPoint, whole.length);
    }else{
        result = whole;
    }
    return result;
}

/**
* Funci?n que redondea un n?mero decimal (Num) 
* a ciertos n?mero de decimales (Places)
**/
function roundit(Num, Places) {
	if (Places > 0) {
  		if ((Num.toString().length - Num.toString().lastIndexOf('.')) > (Places + 1)) {
	     	var Rounder = Math.pow(10, Places);
    	 	return Math.round(Num * Rounder) / Rounder;
  		}
  		else return Num;
	}
	else return Math.round(Num);
}

/**
* funcion que se utiliza para comparar dos fechas, 
* estas fecha se reciben como string con formato 
* dd-mmm-yyyy (mes abreviado en espa?ol), regresa:
*  1 - si fecha1 es mayor que fecha2
*  0 - si fecha1 es igual que fecha2
* -1 - si fecha1 es nenor que fecha2
*/
function comparaFecha(sfecha1, sfecha2){
   return comparaDate(getFechaJs(sfecha1),getFechaJs(sfecha2));
}

/**
* funcion que se utiliza para comparar dos fechas, 
* estas fecha se reciben como Date de js, regresa:
*  1 - si fecha1 es mayor que fecha2
*  0 - si fecha1 es igual que fecha2
* -1 - si fecha1 es nenor que fecha2
*/
function comparaDate(dfecha1, dfecha2){
   if(dfecha1>dfecha2){
      return 1;
   }else if(dfecha1==dfecha2){
      return 0;
   }else if(dfecha1<dfecha2){
      return -1;
   }
}

/**
* funcion que regresa un objeto Date de javascript 
* creado con un string que representa una fecha 
* en el formato dd-mmm-yyyy (mes abreviado en espa?ol)
*/
function getFechaJs(fecha){
   var arrFecha = fecha.split(/-/);
   //alert('Fecha split|'+arrFecha+'|');
   //var dia = parseInt(arrFecha[0]);
   var dia = arrFecha[0];
   var mes = getNumMesJs(arrFecha[1]);
   var anio = parseInt(arrFecha[2]);
   var objDate = new Date();
   objDate.setFullYear(anio);
   objDate.setMonth(mes);
   objDate.setDate(dia);
   return objDate;
}

/**
 * Esta funcion es utilizada para formatear un objeto Date
 * y entregar el siguiente formato: 01-ENE-2005
 */
function formatDate(fecha){
	var sdate = '';
	if(fecha){
		var nDia = fecha.getDate();
		var nMes = fecha.getMonth();
		var nAnio = fecha.getFullYear();
		if((nDia+'').length==1)nDia = '0'+nDia;
		if((nMes+'').length==1)nMes = '0'+nMes;
		sdate = nDia+'-'+getNombreMesJs(nMes)+'-'+nAnio;
	}
	return sdate;
}

/**
 * Esta funcion es utilizada para formatear un objeto Date
 * y entregar el siguiente formato: 01-ENE-2005 17:55
 */
function formatDateHHMM(fecha){
	var sdate = '';
	if(fecha){
		var nDia = fecha.getDate();
		var nMes = fecha.getMonth();
		var nAnio = fecha.getFullYear();
		var nHou = fecha.getHours();
		var nMin = fecha.getMinutes();
		if((nDia+'').length==1)nDia = '0'+nDia;
		if((nMes+'').length==1)nMes = '0'+nMes;
		if((nHou+'').length==1)nHou='0'+nHou;
		if((nMin+'').length==1)nMin='0'+nMin;
		sdate = nDia+'-'+getNombreMesJs(nMes)+'-'+nAnio + ' ' + nHou + ':' +nMin;
	}
	return sdate;
}

/**
 * Funcion utilizada para calcular si 
 * el anio pasado es bisiesto
 */
function isAnioBisiesto(nAnio){
	if ((nAnio%100 == 0) && (nAnio%400==0)) return true;
	else if ((nAnio%4)==0) return true;
	return false;
}

/**
* funcion que regresa el numero de mes correspondiente 
* al mes abreviado en espa?ol 
*/
function getNumMes(mes){
   var intMes = 0;
   mes = mes.toUpperCase();
   if( mes == "ENE" ) { intMes = 1; }
   if( mes == "FEB" ) { intMes = 2; }
   if( mes == "MAR" ) { intMes = 3; }
   if( mes == "ABR" ) { intMes = 4; }
   if( mes == "MAY" ) { intMes = 5; }
   if( mes == "JUN" ) { intMes = 6; }
   if( mes == "JUL" ) { intMes = 7; }
   if( mes == "AGO" ) { intMes = 8; }
   if( mes == "SEP" ) { intMes = 9; }
   if( mes == "OCT" ) { intMes = 10; }									
   if( mes == "NOV" ) { intMes = 11; }									
   if( mes == "DIC" ) { intMes = 12; }		
   return intMes;								
}

/**
* funcion que regresa el numero de mes correspondiente 
* al mes abreviado en espa?ol 
*/
function getNumMesJs(mes){
   var intMes = 0;
   mes = mes.toUpperCase();
   if( mes == "ENE" ) { intMes = 0; }
   if( mes == "FEB" ) { intMes = 1; }
   if( mes == "MAR" ) { intMes = 2; }
   if( mes == "ABR" ) { intMes = 3; }
   if( mes == "MAY" ) { intMes = 4; }
   if( mes == "JUN" ) { intMes = 5; }
   if( mes == "JUL" ) { intMes = 6; }
   if( mes == "AGO" ) { intMes = 7; }
   if( mes == "SEP" ) { intMes = 8; }
   if( mes == "OCT" ) { intMes = 9; }									
   if( mes == "NOV" ) { intMes = 10; }									
   if( mes == "DIC" ) { intMes = 11; }		
   return intMes;								
}

/**
* funcion que regresa el numero de mes correspondiente 
* al mes abreviado en espa?ol 
*/
function getNumMesBD(mes){
   var intMes = 0;
   mes = mes.toUpperCase();
   if( mes == "ENE" ) { intMes = 1; }
   if( mes == "FEB" ) { intMes = 2; }
   if( mes == "MAR" ) { intMes = 3; }
   if( mes == "ABR" ) { intMes = 4; }
   if( mes == "MAY" ) { intMes = 5; }
   if( mes == "JUN" ) { intMes = 6; }
   if( mes == "JUL" ) { intMes = 7; }
   if( mes == "AGO" ) { intMes = 8; }
   if( mes == "SEP" ) { intMes = 9; }
   if( mes == "OCT" ) { intMes = 10; }									
   if( mes == "NOV" ) { intMes = 11; }									
   if( mes == "DIC" ) { intMes = 12; }		
   return intMes;								
}

/**
* funcion que regresa el numero de mes 
* de javascript correspondiente 
* al mes abreviado en espa?ol 
*/
function getNombreMesJs(intMes){
   var mes = '';
   mes = mes.toUpperCase();
   if(intMes==0){mes="ENE"}
   if(intMes==1){mes="FEB"}
   if(intMes==2){mes="MAR"}
   if(intMes==3){mes="ABR"}
   if(intMes==4){mes="MAY"}
   if(intMes==5){mes="JUN"}
   if(intMes==6){mes="JUL"}
   if(intMes==7){mes="AGO"}
   if(intMes==8){mes="SEP"}
   if(intMes==9){mes="OCT"}									
   if(intMes==10){mes="NOV"}									
   if(intMes==11){mes="DIC"}		
   return mes;								
}

/**
*Metodo que recibe un elemento de la forma y lo formatea 
* a $111,111.00 redondeando si son mas decimales 
*/
function formatCurrency(objCampo){
	strValue = objCampo.value;
	strValue = strValue.toString().replace(/\$|\,/g,'');
	dblValue = parseFloat(strValue);
	objCampo.value = dblValue;
	blnSign = (dblValue == (dblValue = Math.abs(dblValue)));
	dblValue = Math.floor(dblValue*100+0.50000000001);
	intCents = dblValue%100;
	strCents = intCents.toString();
	dblValue = Math.floor(dblValue/100).toString();
	if(intCents<10)
	strCents = "0" + strCents;
	for (var i = 0; i < Math.floor((dblValue.length-(1+i))/3); i++)
		dblValue = dblValue.substring(0,dblValue.length-(4*i+3))+','+
		dblValue.substring(dblValue.length-(4*i+3));
	objCampo.value = (((blnSign)?'':'-') + '$' + dblValue + '.' + strCents);
	
}

/**
*Metodo que recibe un elemento de la forma y lo formatea 
* a $111,111.00 redondeando si son mas decimales 
*/
function formatDouble(objCampo){
	strValue = objCampo.value;
	strValue = strValue.toString().replace(/\$|\,/g,'');
	dblValue = parseFloat(strValue);
	objCampo.value = dblValue;
	blnSign = (dblValue == (dblValue = Math.abs(dblValue)));
	dblValue = Math.floor(dblValue*100+0.50000000001);
	intCents = dblValue%100;
	strCents = intCents.toString();
	dblValue = Math.floor(dblValue/100).toString();
	if(intCents<10)
	strCents = "0" + strCents;
	for (var i = 0; i < Math.floor((dblValue.length-(1+i))/3); i++)
		dblValue = dblValue.substring(0,dblValue.length-(4*i+3))+
		dblValue.substring(dblValue.length-(4*i+3));
	objCampo.value = (((blnSign)?'':'-') + dblValue + '.' + strCents);
	
}



function isCampoVacio(Element){
    a = Element.value;
    if (a != null && a.length > 0 ){
        return true;
    }
    else{
      alert("El campo no puede estar vacio");
      Element.focus();
      return false;
    }
  }
  
function isEmpty(s){
	return ((s == null) || (s.length == 0))
}

  function isDigit(s){
    return ((s >= "0") && (s <= "9"))
  }

function isNumerico(Element){
	if(!isCampoVacio(Element))return false;
	if(isFloat(Element))return true;
	if(isInteger(Element))return true;
	alert("Es necesario que escribas la cantidad numerica correctamente (ej. 2345.89)!");
	Element.value="";
		Element.focus();
		return false;
	}

function isFloat(Element){
    var i, salida = true;
    var seenDecimalPoint = false;
    s = Element.value;
    if (s == dPD) salida = false;
    if (s.length <= 0) salida = false;
    for (i = 0; i < s.length; i++){
        var c = s.charAt(i);
        if ((c == dPD) && !seenDecimalPoint) seenDecimalPoint = true;
        else if (!isDigit(c)) salida = false;
    }
	return salida;
}

function isInteger(Element){
	var i;
	s = Element.value;
	for (i = 0; i < s.length; i++){
		var c = s.charAt(i);
		if (!isDigit(c)){
			return false;
			break;
		}
	}
	return true;
}
  
  	/**
* metodo utilizado para eliminar 
* los options de un select
*/	 
 function clearOptions(objSelect){
 	/*
 	var len = objSelect.length;
 	if(len>0){
		for(var i=0; i < len; i++){
			objSelect.remove(objSelect.options[0]);
	 	}
 	}
 	*/
 	objSelect.length = 0;
 }
 
 /**
 * funcion para cambiar a mayusculas  
 * el codigo tecleado por el usuario 
 */
function soloNumeros(){		
    var key=window.event.keyCode;//codigo de tecla.
    if (key < 123 && key > 96){ 
        alert("Solo se permite ingresar numeros.");
        return false;
    }
    return true;
}

/**
* Funcion que valida el formato de fecha 
* que ingresa el usuario, la fecha esperada  
* debe estar en el formato dd-mm-yyyy ?  
* dd-mmm-yyyy ? dd/mm/yyyy ? dd/mmm/yyyy  
*/
function validaFormatoFecha(fecha){
	var strFecha = "";
	var meses = ["ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC"];
	if(fecha=="")return strFecha;
	if(fecha!="")fecha=fecha.toUpperCase();
	var re = /\b(0?[1-9]|[12][0-9]|3[01])[\-\/](0?[1-9]|1[0-2]|ENE|FEB|MAR|ABR|MAY|JUN|JUL|AGO|SEP|OCT|NOV|DIC)[\-\/]((18|19|20|21|22)\d{2})/i;
	var arrFecha = re.exec(fecha);
	if(arrFecha){
		var dia = arrFecha[1];
		if(isNaN(arrFecha[2])){
			var mes = arrFecha[2];
			for(var i=0;i<meses.length;i++){
				if(meses[i]==arrFecha[2]){
					var nMes = meses[i];
				}
			}
		}else{
			var mes = meses[arrFecha[2]-1];
			var nMes = arrFecha[2]-1;
		}
		var anio = arrFecha[3];
		if (dia == 31 && (nMes == 3 || nMes == 5 || nMes == 8 || nMes == 10)) {
			alert("Dia Incorrecto, el mes solo puede tener 30 dias");
			strFecha = "";
		}else if (dia > 29 && nMes == 1) {
			alert("Dia Incorrecto, el mes solo puede tener 28/29 dias");
			strFecha = "";
		}else if (nMes == 1 && dia == 29 && !isAnioBisiesto(anio)) {
			alert("Dia Incorrecto, no es aÃ±o bisiesto, el mes solo puede tener 28 dias");
			strFecha = "";
		}else{
			strFecha = dia +"-"+ mes +"-"+ anio;
		}
	}else{
		alert("Fecha incorrecta (Debe ingresar un formato dd-mm-yyyy o dd/mm/yyyy)...");
	}
	return strFecha;
}

/**
* Esta funcion esta encargada de agregar un
* guion para completar el correcto 
* formato de la fecha 
*/
function agregaDiag(campo){		
	var longitud = campo.value.length;			
	if(longitud == 2){
		campo.value += '-';		 					
	}if(longitud == 5){    					
		campo.value += '-';
	}    	
}

/************************************************
Funcion que remueve todas las comas en una cadena dada
*************************************************/
function removeCommas(strValue){
	var objRegExp = /,/g;
  	return strValue.replace(objRegExp,'');
}

/************************************************
Funcion que inserta una comas cada 3 numeros en una 
cadena dada  que sea numerica para formato #,#00.00 
NOTA: Solo funciona con dos decimales o menos  
*************************************************/
function addCommas(strValue){
  	var objRegExp  = new RegExp('(-?[0-9]+)([0-9]{3})');
    while(objRegExp.test(strValue)) {
       strValue = strValue.replace(objRegExp, '$1,$2');
    }
  	return strValue;
}

/**
* Esta funcion valida que la cadena este en un formato de 
* 24 horas (1:35, 16:25, etc.)
*/
function validaHH24(shora){
	var reHH24 = /^(([0-1]?[0-9]|[2][0-3])(:[0-5]?[0-9])?)$/;
	return reHH24.test(shora);
}

/**
* Esta funciÃƒÂ³n sustitutye
* los ASCII/ANSI por los hexadecimales correctos
* y las devuelve correctamente.
* x = cadena con acento(s) a corregir
*/
function corrigeAcentos(x) 
{
	// version 040623
	// Spanish - EspaÃƒÂ±ol
	// Portuguese - PortuguÃƒÂ©s - PortuguÃƒÂªs
	// Italian - Italiano
	// French - FrancÃƒÂ©s - FranÃƒÂ§ais
	// Also accepts and converts single and double quotation marks, square and angle brackets
	// and miscelaneous symbols.
	// Also accepts and converts html entities for all the above.
//	if (navigator.appVersion.toLowerCase().indexOf("windows") != -1) {return x}
	x = x.replace(/Ã‚Â¡/g,"\xA1");	x = x.replace(/&iexcl;/g,"\xA1")
	x = x.replace(/Ã‚Â¿/g,"\xBF");	x = x.replace(/&iquest;/g,"\xBF")
	x = x.replace(/Ãƒâ‚¬/g,"\xC0");	x = x.replace(/&Agrave;/g,"\xC0")
	x = x.replace(/ÃƒÂ /g,"\xE0");	x = x.replace(/&agrave;/g,"\xE0")
	x = x.replace(/Ãƒ?/g,"\xC1");	x = x.replace(/&Aacute;/g,"\xC1")
	x = x.replace(/ÃƒÂ¡/g,"\xE1");	x = x.replace(/&aacute;/g,"\xE1")
	x = x.replace(/Ãƒâ€š/g,"\xC2");	x = x.replace(/&Acirc;/g,"\xC2")
	x = x.replace(/ÃƒÂ¢/g,"\xE2");	x = x.replace(/&acirc;/g,"\xE2")
	x = x.replace(/ÃƒÆ’/g,"\xC3");	x = x.replace(/&Atilde;/g,"\xC3")
	x = x.replace(/ÃƒÂ£/g,"\xE3");	x = x.replace(/&atilde;/g,"\xE3")
	x = x.replace(/Ãƒâ€ž/g,"\xC4");	x = x.replace(/&Auml;/g,"\xC4")
	x = x.replace(/ÃƒÂ¤/g,"\xE4");	x = x.replace(/&auml;/g,"\xE4")
	x = x.replace(/Ãƒâ€¦/g,"\xC5");	x = x.replace(/&Aring;/g,"\xC5")
	x = x.replace(/ÃƒÂ¥/g,"\xE5");	x = x.replace(/&aring;/g,"\xE5")
	x = x.replace(/Ãƒâ€ /g,"\xC6");	x = x.replace(/&AElig;/g,"\xC6")
	x = x.replace(/ÃƒÂ¦/g,"\xE6");	x = x.replace(/&aelig;/g,"\xE6")
	x = x.replace(/Ãƒâ€¡/g,"\xC7");	x = x.replace(/&Ccedil;/g,"\xC7")
	x = x.replace(/ÃƒÂ§/g,"\xE7");	x = x.replace(/&ccedil;/g,"\xE7")
	x = x.replace(/ÃƒË†/g,"\xC8");	x = x.replace(/&Egrave;/g,"\xC8")
	x = x.replace(/ÃƒÂ¨/g,"\xE8");	x = x.replace(/&egrave;/g,"\xE8")
	x = x.replace(/Ãƒâ€°/g,"\xC9");	x = x.replace(/&Eacute;/g,"\xC9")
	x = x.replace(/ÃƒÂ©/g,"\xE9");	x = x.replace(/&eacute;/g,"\xE9")
	x = x.replace(/ÃƒÅ /g,"\xCA");	x = x.replace(/&Ecirc;/g,"\xCA")
	x = x.replace(/ÃƒÂª/g,"\xEA");	x = x.replace(/&ecirc;/g,"\xEA")
	x = x.replace(/Ãƒâ€¹/g,"\xCB");	x = x.replace(/&Euml;/g,"\xCB")
	x = x.replace(/ÃƒÂ«/g,"\xEB");	x = x.replace(/&euml;/g,"\xEB")
	x = x.replace(/ÃƒÅ’/g,"\xCC");	x = x.replace(/&Igrave;/g,"\xCC")
	x = x.replace(/ÃƒÂ¬/g,"\xEC");	x = x.replace(/&igrave;/g,"\xEC")
	x = x.replace(/Ãƒ?/g,"\xCD");	x = x.replace(/&Iacute;/g,"\xCD")
	x = x.replace(/ÃƒÂ­/g,"\xED");	x = x.replace(/&iacute;/g,"\xED")
	x = x.replace(/ÃƒÅ½/g,"\xCE");	x = x.replace(/&Icirc;/g,"\xCE")
	x = x.replace(/ÃƒÂ®/g,"\xEE");	x = x.replace(/&icirc;/g,"\xEE")
	x = x.replace(/Ãƒ?/g,"\xCF");	x = x.replace(/&Iuml;/g,"\xCF")
	x = x.replace(/ÃƒÂ¯/g,"\xEF");	x = x.replace(/&iuml;/g,"\xEF")
	x = x.replace(/Ãƒâ€˜/g,"\xD1");	x = x.replace(/&Ntilde;/g,"\xD1")
	x = x.replace(/ÃƒÂ±/g,"\xF1");	x = x.replace(/&ntilde;/g,"\xF1")
	x = x.replace(/Ãƒâ€™/g,"\xD2");	x = x.replace(/&Ograve;/g,"\xD2")
	x = x.replace(/ÃƒÂ²/g,"\xF2");	x = x.replace(/&ograve;/g,"\xF2")
	x = x.replace(/Ãƒâ€œ/g,"\xD3");	x = x.replace(/&Oacute;/g,"\xD3")
	x = x.replace(/ÃƒÂ³/g,"\xF3");	x = x.replace(/&oacute;/g,"\xF3")
	x = x.replace(/Ãƒâ€�/g,"\xD4");	x = x.replace(/&Ocirc;/g,"\xD4")
	x = x.replace(/ÃƒÂ´/g,"\xF4");	x = x.replace(/&ocirc;/g,"\xF4")
	x = x.replace(/Ãƒâ€¢/g,"\xD5");	x = x.replace(/&Otilde;/g,"\xD5")
	x = x.replace(/ÃƒÂµ/g,"\xF5");	x = x.replace(/&otilde;/g,"\xF5")
	x = x.replace(/Ãƒâ€“/g,"\xD6");	x = x.replace(/&Ouml;/g,"\xD6")
	x = x.replace(/ÃƒÂ¶/g,"\xF6");	x = x.replace(/&ouml;/g,"\xF6")
	x = x.replace(/ÃƒËœ/g,"\xD8");	x = x.replace(/&Oslash;/g,"\xD8")
	x = x.replace(/ÃƒÂ¸/g,"\xF8");	x = x.replace(/&oslash;/g,"\xF8")
	x = x.replace(/Ãƒâ„¢/g,"\xD9");	x = x.replace(/&Ugrave;/g,"\xD9")
	x = x.replace(/ÃƒÂ¹/g,"\xF9");	x = x.replace(/&ugrave;/g,"\xF9")
	x = x.replace(/ÃƒÅ¡/g,"\xDA");	x = x.replace(/&Uacute;/g,"\xDA")
	x = x.replace(/ÃƒÂº/g,"\xFA");	x = x.replace(/&uacute;/g,"\xFA")
	x = x.replace(/Ãƒâ€º/g,"\xDB");	x = x.replace(/&Ucirc;/g,"\xDB")
	x = x.replace(/ÃƒÂ»/g,"\xFB");	x = x.replace(/&ucirc;/g,"\xFB")
	x = x.replace(/ÃƒÅ“/g,"\xDC");	x = x.replace(/&Uuml;/g,"\xDC")
	x = x.replace(/ÃƒÂ¼/g,"\xFC");	x = x.replace(/&uuml;/g,"\xFC")
	
	x = x.replace(/\"/g,"\x22")
	x = x.replace(/\'/g,"\x27")
	x = x.replace(/\</g,"\x3C")
	x = x.replace(/\>/g,"\x3E")
	x = x.replace(/\[/g,"\x5B")
	x = x.replace(/\]/g,"\x5D")

	x = x.replace(/Ã‚Â¢/g,"\xA2");	x = x.replace(/&cent;/g,"\xA2") 
	x = x.replace(/Ã‚Â£/g,"\xA3");	x = x.replace(/&pound;/g,"\xA3")
	x = x.replace(/Ã¢â€šÂ¬/g,"\u20AC");	x = x.replace(/&euro;/g,"\u20AC") 
	x = x.replace(/Ã‚Â©/g,"\xA9");	x = x.replace(/&copy;/g,"\xA9") 
	x = x.replace(/Ã‚Â®/g,"\xAE");	x = x.replace(/&reg;/g,"\xAE") 
	x = x.replace(/Ã‚Âª/g,"\xAA");	x = x.replace(/&ordf;/g,"\xAA") 
	x = x.replace(/Ã‚Âº/g,"\xBA");	x = x.replace(/&ordm;/g,"\xBA") 
	x = x.replace(/Ã‚Â°/g,"\xB0");	x = x.replace(/&deg;/g,"\xB0") 
	x = x.replace(/Ã‚Â±/g,"\xB1");	x = x.replace(/&plusmn;/g,"\xB1")
	x = x.replace(/Ãƒâ€”/g,"\xD7");	x = x.replace(/&times;/g,"\xD7") 
	
	return x
}

/**
 * Funcion para poner un div flotante
 * Recibe cuatro parametros: 
 * id = el id del div 
 * sx = la pocision en el eje x
 * sy = la pocision en el eje y
 * tm = tiempo de delay para el timeout
 */
function _floatDiv(id, sx, sy, tm){
	var ns = (navigator.appName.indexOf('Netscape') != -1);
	var d = document; 
	var px = document.layers ? '' : 'px'; 
	var el = d.getElementById ? d.getElementById(id) : d.all ? d.all[id] : d.layers[id];
	window[id + '_obj'] = el;
	if(d.layers) el.style = el;
	el.cx = el.sx = sx; 
	el.cy = el.sy = sy;
	el.sP = function(x,y){
		this.style.left = x + px;
		this.style.top = y + px;
	};
	el.flt = function(){
		var pX, pY;
		var dE = d.documentElement;
		var dB = d.body;
		pX = (this.sx >= 0) ? 0 : ns ? innerWidth : dE && dE.clientWidth ? dE.clientWidth : dB.clientWidth;
		pY = ns ? pageYOffset : dE && dE.scrollTop ? dE.scrollTop : dB.scrollTop;
		if(this.sy<0) pY += ns ? innerHeight : dE && dE.clientHeight ? dE.clientHeight : dB.clientHeight;
		this.cx += (pX + this.sx - this.cx)/8;
		this.cy += (pY + this.sy - this.cy)/8;
		this.sP(this.cx, this.cy);
		setTimeout(this.id + '_obj.flt()', tm);
	}
	return el;
}

/**
 * Funcion utilizada para asignar una funcion 
 * al evento onLoad del objeto Window
 */
function callOnLoad(fn){
	if (window.addEventListener) window.addEventListener("load", fn, false);
	else if (window.attachEvent) window.attachEvent("onload", fn);
	else window.onload = fn;
}

function loadJSFile(fileName) {
	var head = document.getElementsByTagName("head")[0];
	var script = document.createElement("script");
	script.setAttribute("type","text/javascript");
	script.setAttribute("src",fileName);
	head.appendChild(script);
}

function validaSoloNumeros( txtValor ) {
	var strValido = "1234567890.";
	var ok = "si";
		var temp;
	for (var i=0; i< txtValor.length; i++) {
		temp = "" + txtValor.substring(i, i+1);
		if (strValido.indexOf(temp) == "-1") ok = "no";
	}
	if (ok == "no") {
		return false;
	} else { 
		return true;
	}
} 	

/**
 * Esta funcion se agrega al objeto Date de Javascript
 * para realizar la operacion de sumar cierto numero de dias 
 * al objeto de fecha tratado
 */
Date.prototype.addDays = function(numDays){
	var nDays = new Number(numDays)
	this.setDate(this.getDate()+nDays);
}

/**
 * Esta funcion se agrega al objeto Date de Javascript
 * para verificar si el anio de la fecha tratada 
 * es bisiesto
 */
Date.prototype.isLeapYear = function(){
	var nYear = this.getFullYear();
	if ((nYear%100 == 0) && (nYear%400==0)) return true;
	else if ((nYear%4)==0) return true;
	return false;
}

/**
 * Esta funcion se agrega al objeto Array de 
 * javascript para buscar un elemento dentro 
 * del arreglo 
 */
Array.prototype.contains = function (elem){
	for (var i = 0; i < this.length; i++){
		if (this[i] == elem)return true;
	}
	return false;
} 
 

/**
 * Esta funcion se utiliza para completar una cadena 
 * dada en el argumento 1, hasta cierta longitud 
 * indicada en el argumento 2 y con un caracter determinado 
 * en el argumento 3, se puede acompletar por la derecha (R) 
 * o por la izquierda (L) segun el argumento 4. Por default 
 * se acompleta con ceros y la izquierda. 
 */
function pad(str,_length, padstr, side) {
    var ret = '' + str;
    if(!side)side = 'L';
    if(!padstr)padstr='0';
    while (ret.length < _length)
    	if(side=='L')ret = padstr + ret;
        else ret = ret + padstr;
    return ret;
}

function asignarValoresConvenio() {
		
		if(!self.opener.closed){
		    frmpadre = self.opener.document.forms(0);
		    if ( frmpadre.name == "frmDatosDemograficos")
			{
		    refrescarExamenesCotizados  = eval("window.document.frmConvExamen.refrescarExamenesCotizados");
		    refrescarPerfilesCotizados  = eval("window.document.frmConvExamen.refrescarPerfilesCotizados");
		    
		    window.document.BConsultasCotizacionAction.hdnLimpia.value='';
		    frmpadre.hdnIdCodPost.value = hdnIdCodigo.value;
		    frmpadre.txtCodPost.value = hdnCodigo.value;
		    frmpadre.txtDelMun.value  = hdnDelmun.value;
		    frmpadre.txtEstado.value  = hdnEstado.value;
	        frmpadre.txtColonia.value = hdnColonia.value;
	        frmpadre.txtCiudad.value  = hdnCiudad.value;       
		    frmpadre.hdnDelMun.value  = hdnDelmun.value;
		    frmpadre.hdnEstado.value  = hdnEstado.value;
	        frmpadre.hdnColonia.value = hdnColonia.value;
	        frmpadre.hdnCiudad.value  = hdnCiudad.value;        
			window.close();
		     }
			else if( frmpadre.name == "frmUnidades" ){
		    hdnIdCodigo  = eval("window.document.frmBuscarCodigoPostal.hdnIdCodigoPostal"+ indice);
		    hdnCodigo  = eval("window.document.frmBuscarCodigoPostal.hdnCodigoPostal"+ indice);
		    window.document.frmBuscarCodigoPostal.hdnLimpia.value='';
		    frmpadre.hdnIdCodPost.value = hdnIdCodigo.value;
		    frmpadre.ccodigopostal.value = hdnCodigo.value;
		    window.close();
	    	}

			
		}else{
			alert("?La ventana principal se ha cerrado!");
			window.close();
		}
	}

	function valEmail(valor){
	    re=/^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,3})$/
	    if(!re.exec(valor))    {
	        return false;
	    }else{
	        return true;
	    }
	}
	
	function isMail(Cadena) {   
		  
	    Punto = Cadena.substring(Cadena.lastIndexOf('.') + 1, Cadena.length);            // Cadena del .com   
	    Dominio = Cadena.substring(Cadena.lastIndexOf('@') + 1, Cadena.lastIndexOf('.'));    // Dominio @lala.com   
	    Usuario = Cadena.substring(0, Cadena.lastIndexOf('@'));                  // Cadena lalala@   
	    Reserv = "@â�„Âº\"\'+*{}\\<>?Â¿[]Ã¡Ã©Ã­Ã³Ãº#Â·Â¡!^*;,:";                      // Letras Reservadas   
	       
	    // AÃ±adida por El Codigo para poder emitir un alert en funcion de si email valido o no   
	    valido = true;   
	       
	    // verifica qie el Usuario no tenga un caracter especial   
	    for (var Cont=0; Cont<Usuario.length; Cont++) {   
	        X = Usuario.substring(Cont,Cont+1);   
	        if (Reserv.indexOf(X)!=-1)   
	                    valido = false;   
	    }   
	  
	    // verifica qie el Punto no tenga un caracter especial   
	    for (var Cont=0; Cont<Punto.length; Cont++) {   
	        X=Punto.substring(Cont,Cont+1);   
	        if (Reserv.indexOf(X)!=-1)   
	            valido = false;   
	    }   
	                           
	    // verifica qie el Dominio no tenga un caracter especial   
	    for (var Cont=0; Cont<Dominio.length; Cont++) {   
	        X=Dominio.substring(Cont,Cont+1);   
	        if (Reserv.indexOf(X)!=-1)   
	            valido = false;   
	        }   
	  
	    // Verifica la sintaxis bÃ¡sica.....   
	    if (Punto.length<2 || Dominio <1 || Cadena.lastIndexOf('.')<0 || Cadena.lastIndexOf('@')<0 || Usuario<1) {   
	        valido = false;   
	    }   
	       
	    // AÃ±adido por El CÃ³digo para que emita un alert de aviso indicando si email vÃ¡lido o no   
	    if (valido) {   
//	        alert('Email valido.');   
	        return true;    //cambiar por return true para hacer el submit del formulario en caso de validacion correcta   
	    } else {   
	        alert('Email no valido.');   
	        return false;   
	    }   
	}
	
	/**
	* funcion para permitir capturar solo numeros y letras  
	* del teclado 
	*/
	function validaRFCInterpretacion(frmPantalla){
	    var key=window.event.keyCode;
	    var txtSRFC = frmPantalla.txtRFC;
	    //var txtSRFCConfirmar = frmPantalla.txtConfirmarRFC;
	    var cboCTIPOPERSONA = frmPantalla.selTipoPersona;
	    var lengthRFC = txtSRFC.value.length;
	    //var lengthRFCConfirmar = txtSRFCConfirmar.value.length;
		if (cboCTIPOPERSONA.value == 1) {
			if (lengthRFC > 13) {
				window.event.keyCode = 0;
				alert('Para personas Fisicas solo son 13 caracteres');
				return false;			
			}    
		} else if (cboCTIPOPERSONA.value == 2) {   	
			if (lengthRFC > 12) {
				window.event.keyCode = 0;
				alert('Para personas Fisicas solo son 12 caracteres');
				return false;			
			}    
		}   	
	    if (cboCTIPOPERSONA.value == 0) {
			alert("Selecciona el Tipo de Persona Moral o Fisica !!!");
			window.event.keyCode = 0;
			txtSRFC.value = "";
			return false;
	    } else {
	    	if (cboCTIPOPERSONA.value == 1) {
		    	var iniLetras = -1;
		    	var endLetras = 3;
		    	var iniAno = 4;
		    	var endAno = 5;
		    	var iniMes = 6;
		    	var endMes = 7;
		    	var iniDia = 8;
		    	var endDia = 9;
	    	} else if (cboCTIPOPERSONA.value == 2) {   	
		    	var iniLetras = -1;
		    	var endLetras = 2;
		    	var iniAno = 3;
		    	var endAno = 4;
		    	var iniMes = 5;
		    	var endMes = 6;
		    	var iniDia = 7;
		    	var endDia = 8;    
	    	}   	
		    if (key < 123 && key > 96 ){ 
		    	window.event.keyCode=window.event.keyCode-32;
		    } else{
		         //alert("Key:"+key);
		        if(key == 241){    //C?digo de ? 241
		        	window.event.keyCode=209;//C?digo de ? 209
		        }	    	
		    }	
		    if (!((key<58 && key>47) || (key<91 && key>64) || (key<123 && key>93))) { 
				if(key!=13){
					window.event.keyCode = 0;
					return false;
				}
		    }	
		    if (lengthRFC > iniLetras && lengthRFC <= endLetras) {
				if (!((key<91 && key>64) || (key<123 && key>93))) {
			    	alert("Ãšnicamente se aceptan letras");
					window.event.keyCode = 0;
					return false;
				}
		    } else if ( (lengthRFC >= iniAno) && (lengthRFC <= endAno)) {
				if (!(key<58 && key>47)) {
			    	alert("Ãšnicamente se aceptan nÃºmeros");
					window.event.keyCode = 0;
					return false;
				}
		    } else if ( (lengthRFC >= iniMes) && (lengthRFC <= endMes)) {
				if (!(key<58 && key>47)) {
			    	alert("Ãšnicamente se aceptan nÃºmeros");
					window.event.keyCode = 0;
					return false;
				} else if (lengthRFC == iniMes) {
					if(!(key<50 && key>47)) {
						alert("Ãšnicamente se aceptan 0 o 1");
						window.event.keyCode = 0;
						return false;
		        	}
				} else if (lengthRFC == endMes) {
					if ((txtSRFC.value.substr(iniMes,1) == "1") && !(key<51 && key>47)){                                       
						alert("Ãšnicamente se aceptan 0, 1 o 2");
						window.event.keyCode = 0;
						return false;					
					} else if (!(key<58 && key>48) && (txtSRFC.value.substr(iniMes,1) == "0")){
						alert("Ãšnicamente se aceptan 1 al 9");
						window.event.keyCode = 0;
						return false;
					}
		    	}
		    } else if ( (lengthRFC >= iniDia) && (lengthRFC <= endDia)) {
				if (!(key<58 && key>47)) {
			        alert("Ãšnicamente se aceptan nÃºmeros");
					window.event.keyCode = 0;
					return false;
				} else if (lengthRFC == iniDia) {
					if(!(key<52 && key>47)) {
				        alert("Ãšnicamente se aceptan 0, 1, 2 o 3");
						window.event.keyCode = 0;
						return false;
		            }
				} else if (lengthRFC == endDia) {
					if ((txtSRFC.value.substr(iniDia,1) == "3")){
						if (!(key<50 && key>47)) {
							alert("&Uacute;nicamente se aceptan 0 o 1");
							window.event.keyCode = 0;
							return false;				
						}	
					} else if ((txtSRFC.value.substr(iniDia,1) == "1" || txtSRFC.value.substr(iniDia,1) == "2") && key == 48) {
					} else if (!(key<58 && key>48)){
						alert("&Uacute;nicamente se aceptan 1 al 9");
						window.event.keyCode = 0;
						return false;
					}
		        }
		    }
		    return true;
		}
	}

	function validaCurp(curpStr){

		if(curpStr.match(/^([a-z]{4})([0-9]{6})([a-z]{6})([0-9]{2})$/i))
		{
			//alert('curp vÃ¡lida!');
			return true;
		}else
		{
			alert('Debe ingresar un CURP correcto!');
			frmPantalla.txtCurp.value="";
			return false;
		}
		 
	 } 

	
	function ltrim(str) {
	  return str.replace(/^\s+/g, '');
	}

	function rtrim(str) {
	  return str.replace(/\s+$/g, '');
	}
	
	function trimStr(str) {
	  return str.replace(/^\s+|\s+$/g, '');
	}


