
	/**
	 * Funcion para el calculo del RFC.
	 *
	 * @param aStrApePat. Apellido paterno.
	 * @param aStrApeMat. Apellido materno.
	 * @param aStrNombre. Nombre.
	 * @param aStrFechaNac. Fecha de nacimiento.
	 * @param aStrSeparador. Separador de la caden ade fecha. ejem: "-" o "/";
	 */
	function calcularRFC(aStrApePat, aStrApeMat, aStrNombre, aStrFechaNac, aStrSeparador) {		
		var strFecha = new String(aStrFechaNac);
	 	var arrFecha = strFecha.split(aStrSeparador);		
 		var strDia   = arrFecha[0];
	 	var strMes   = obtenerStrNumMesEsp(arrFecha[1]);
 		var strTmpAnio   = arrFecha[2];
 		var strAnio  = strTmpAnio.substring(2,4);
		if(aStrApeMat == null || trim(aStrApeMat) == ""){			
			var strApeMat = new String("X");		
		}else{
			var strApeMat = new String(aStrApeMat);		
		}
 		var strApePat = new String(aStrApePat); 		
		var strNombre = new String(aStrNombre);

		var str1 = obtenerCharsApePat(strApePat); 
		var str2 = strApeMat.substring(0,1);
		var str3 = strNombre.substring(0,1);

		var strRFC = str1 + str2 + str3 + strAnio + strMes + strDia;
		
		return strRFC;
	}
	
	/**
	 * Funcion que obtiene el numero del mes cuando este esta abreviado
	 * en espanol, ejemplo: Febrero = Feb, Agosto = Ago.
	 *
	 * @param aStrMes. Abreviatura del mes. ejem: "Enero" = "Ene".
	 */
	function obtenerStrNumMesEsp(aStrMes) {
		var strMes = "";
	
		if( aStrMes == "ENE" ) { strMes = "01"; }
		if( aStrMes == "FEB" ) { strMes = "02"; }
		if( aStrMes == "MAR" ) { strMes = "03"; }
		if( aStrMes == "ABR" ) { strMes = "04"; }
		if( aStrMes == "MAY" ) { strMes = "05"; }
		if( aStrMes == "JUN" ) { strMes = "06"; }
		if( aStrMes == "JUL" ) { strMes = "07"; }
		if( aStrMes == "AGO" ) { strMes = "08"; }
		if( aStrMes == "SEP" ) { strMes = "09"; }
		if( aStrMes == "OCT" ) { strMes = "10"; }									
		if( aStrMes == "NOV" ) { strMes = "11"; }									
		if( aStrMes == "DIC" ) { strMes = "12"; }		
	
		return strMes;								
	}
	
	/**
	 * funcio que obtiene los caracteres del apellido paterno
	 * solocitados en el RFC, es decir la primera letra y la priera vocal
	 * del apellido paterno.
	 */
	function obtenerCharsApePat(aStrApePat) {
		var strApePat = new String(aStrApePat);
		var strPrimeraLetra = strApePat.substring(0,1); 
		var intLongitud = strApePat.length;
		var strTmp = strApePat.substring(1,intLongitud);
		var strValido = "AEIOUaeiou";
		var strPrimeraVocal = "";
		var strCaracter;
		var bolEncontrado = false;
		
		for(var i=0; i< strTmp.length; i++) {
			strCaracter = "" + strTmp.substring(i, i+1);
			if(strValido.indexOf(strCaracter) != "-1") {
				if(bolEncontrado == false) {
					strPrimeraVocal = strCaracter;
					bolEncontrado = true;
				}
			}
		}	
		
		return strPrimeraLetra + strPrimeraVocal;
	}
	
	