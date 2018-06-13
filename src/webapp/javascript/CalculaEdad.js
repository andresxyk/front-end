/**
 * Funcion    encargad de calcular la edad en base a la
 * fecha de nacimiento, la respuesta es una cadena como la
 * siguiente "46 Anios  4 Meses  85 Dias"
 *
 * @param. txtFecha. Fecha de nacimiento. ejem: 16-Ago-1975
 * @param. txtSeparador. Separador de los anios, meses y dias. Ejem: "-" o "/".
 */
function calcularEdadFecha(txtFecha, txtSeparador) {
	strFecha = new String(txtFecha);
 	arrFecha = strFecha.split(txtSeparadorFecha);		
 	intDia   = arrFecha[0] * 1;
 	intMes   = obtenerNumMesEsp(arrFecha[1]) * 1;
 	intAnio  = arrFecha[2] * 1;
 	intEdadAnios = calcularAnios(intDia, intMes, intAnio);
 	
 	return intEdadAnios;
}

/**
 * Funcion encargad de calcular la edad en base a la
 * fecha de nacimiento, la respuesta es una cadena como la
 * siguiente "46 Anios  4 Meses  85 Dias"
 */
function calcularEdad(day, month, year) {
	var today = new Date();
	var thisYear = y2k(today.getYear());
	var thisMonth = today.getMonth()+1;
	var thisDay = today.getDate();

    var yearsold = thisYear - year, monthsold = 0, daysold = 0, string = '';

    if (thisMonth >= month) monthsold = thisMonth - month;
    else { yearsold--; monthsold = thisMonth + 12 - month; }

    if (thisDay >= day)daysold = thisDay - day;
    else {
        if (monthsold > 0) monthsold--;
        else { yearsold--; monthsold += 11; }
        daysold = thisDay + 31 - day;
    }

    if (yearsold < 0) return '';

    if ((yearsold == 0) && (monthsold == 0) && (daysold == 0))
        return '';

    if (yearsold > 0) {
        string = yearsold + ' A&ntilde;o';
        if (yearsold > 1) string += 's';
        string += ' ';
    }

    if (monthsold > 0) {
        string += monthsold + ' Mes';
        if (monthsold > 1) string += 'es';
        string += ' ';
    }

    if (daysold > 0) {
        string += daysold + ' D&iacute;a';
        if (daysold > 1) string += 's';
        string += ' ';
    }

    return '<p> Edad:  '+ string;
}

/**
 * Funcion encargada de calcular la edad en anios en base a la
 * fecha de nacimiento.
 *
 * @param. txtFecha. Fecha de nacimiento. ejem: 16-Ago-1975
 * @param. txtSeparador. Separador de los anios, meses y dias. Ejem: "-" o "/".
 */
function calcularAniosFecha(txtFecha, txtSeparador) {
	strFecha = new String(txtFecha);
 	arrFecha = strFecha.split(txtSeparador);		
 	intDia   = arrFecha[0] * 1;
 	intMes   = obtenerNumMesEsp(arrFecha[1]) * 1;
 	intAnio  = arrFecha[2] * 1;
 	intEdadAnios = calcularAnios(intDia, intMes, intAnio);
 	
 	return intEdadAnios;
}

/**
 * Funcion encargada de calcular la edad en anios en base al anio,
 * mes y dia de nacimiento, es decir, si la fecha de nacimiento es
 * 16-Ago-1975, los parametros recibidos son dia = 16, mes = 8
 * y ano = 1975. Con base en estos datos se calcula el numero de 
 * anios.
 *
 * @param day. Dia de nacimiento. ejem: 16
 * @param month. Mes de nacimiento. ejem: 8
 * @param year. Ano de nacimiento. ejem: 1975
 */
function calcularAnios(day, month, year) {
	var today = new Date();
	var thisYear = y2k(today.getYear());
	var thisMonth = today.getMonth()+1;
	var thisDay = today.getDate();
	var yearsold = thisYear - year, monthsold = 0, daysold = 0, string = '';

    if (thisMonth >= month) monthsold = thisMonth - month;
    else { yearsold--; monthsold = thisMonth + 12 - month; }

    if (thisDay >= day)daysold = thisDay - day;
    else {
        if (monthsold > 0) monthsold--;
        else { yearsold--; monthsold += 11; }
        daysold = thisDay + 31 - day;
    }

    return yearsold;
}

/**
 * Funcion encargada de calcular los meses de la edad actual
 * en base a la fecha de nacimiento, es decir, si se tiene una
 * edad de 26 anos 4 meses, la respuesta es el numero de
 * meses actuales calculado ejem: "4"
 *
 * @param. txtFecha. Fecha de nacimiento. ejem: 16-Ago-1975
 * @param. txtSeparador. Separador de los anios, meses y dias. Ejem: "-" o "/".
 */
function calcularMesesFecha(txtFecha, txtSeparador) {
	strFecha = new String(txtFecha);
 	arrFecha = strFecha.split(txtSeparador);		
 	intDia   = arrFecha[0] * 1;
 	intMes   = obtenerNumMesEsp(arrFecha[1]) * 1;
 	intAnio  = arrFecha[2] * 1;
 	intEdadMeses = calcularMeses(intDia, intMes, intAnio);
 	return intEdadMeses;
}

/**
 * Funcion encargada de calcular los meses de la edad actual
 * en base a la fecha de nacimiento, es decir, si se tiene una
 * edad de 26 anos 4 meses, la respuesta es el numero de
 * meses actuales calculado ejem: "4"
 *
 * @param day. Dia de nacimiento. ejem: 16
 * @param month. Mes de nacimiento. ejem: 8
 * @param year. Anio de nacimiento. ejem: 1975
 */
function calcularMeses(day, month, year) {
	var today = new Date();
	var thisYear = y2k(today.getYear());
	var thisMonth = today.getMonth() + 1;
	var thisDay = today.getDate();

    var yearsold = thisYear - year, monthsold = 0, daysold = 0, string = '';

    if (thisMonth >= month) monthsold = thisMonth - month;
    else { yearsold--; monthsold = thisMonth + 12 - month; }

    if (thisDay >= day)daysold = thisDay - day;
    else {
        if (monthsold > 0) monthsold--;
        else { yearsold--; monthsold += 11; }
        daysold = thisDay + 31 - day;
    }

    return monthsold;
}

/**
 * Funcion encargada de calcular los dias de la edad actual
 * en base a la fecha de nacimiento, es decir, si se tiene una
 * edad de 26 anos 4 meses 85 dias, la respuesta es el numero de
 * dias actuales calculado ejem: "85".
 *
 * @param. txtFecha. Fecha de nacimiento. ejem: 16-Ago-1975
 * @param. txtSeparador. Separador de los anios, meses y dias. Ejem: "-" o "/".
 */
function calcularDiasFecha(txtFecha, txtSeparador) {
	strFecha = new String(txtFecha);
 	arrFecha = strFecha.split(txtSeparador);		
 	intDia   = arrFecha[0] * 1;
 	intMes   = obtenerNumMesEsp(arrFecha[1]) * 1;
 	intAnio  = arrFecha[2] * 1;
 	intEdadDias  = calcularDias(intDia, intMes, intAnio); 	
 	return intEdadDias;
}

/**
 * Funcion encargada de calcular los dias de la edad actual
 * en base a la fecha de nacimiento, es decir, si se tiene una
 * edad de 26 anos 4 meses 85 dias, la respuesta es el numero de
 * dias actuales calculado ejem: "85".
 *
 * @param day. Dia de nacimiento. ejem: 16
 * @param month. Mes de nacimiento. ejem: 8
 * @param year. Anio de nacimiento. ejem: 1975
 */
function calcularDias(day, month, year) {
	var today = new Date();
	var thisYear = y2k(today.getYear());
	var thisMonth = today.getMonth()+1;
	var thisDay = today.getDate();

    var yearsold = thisYear - year, monthsold = 0, daysold = 0, string = '';

    if (thisMonth >= month) monthsold = thisMonth - month;
    else { yearsold--; monthsold = thisMonth + 12 - month; }

    if (thisDay >= day)daysold = thisDay - day;
    else {
        if (monthsold > 0) monthsold--;
        else { yearsold--; monthsold += 11; }
        daysold = thisDay + 31 - day;
    }
 
    return daysold;
}

function y2k( number ) { 
	return (number < 1000) ? number + 1900 : number; 
}

/**
 * Funcion que obtiene el numero del mes cuando este esta abreviado
 * en espanol, ejemplo: Febrero = Feb, Agosto = Ago.
 *
 * @param mes. Abreviatura del mes. ejem: "Enero" = "Ene".
 */
function obtenerNumMesEsp(mes1) {
	var intMes = 0;
	var mes = mes1.toUpperCase();
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
 * Funcion que calcula el n?mero de a?os a partir
 * del n?mero de d?as vivido
 * @param dias
**/
function diasanio(days)
{
	var anios;
	var meses;
	var dias;
	anios = Math.floor((days/365),0);
	//alert("A:"+anios);
	meses = Math.floor(((days%365)/30.34),0);
	//alert("M:"+meses);
	dias = Math.floor(((days%365)%30.34),0);
	//alert("D:"+days);
	var edad = new Array(3);
	edad[0]=anios;
	edad[1]=meses;
	edad[2]=dias;
	//edad[3]=days;
	return edad;
}

function getUdias(anios, meses, dias){
	anios  = anios ? anios : 0;
	anios = parseInt(anios) * (365);
	meses = meses ? meses : 0;
	meses = parseInt(meses) * (30.34);
	dias = dias ? parseInt(dias) : 0;
	var numero = new Number(anios + meses + dias);
	return numero.toFixed(0);
}


/**
 * Funcion que calcula el n?mero de a?os a partir
 * de la fecha de nacimiento
 * @param txtFechaNac -- Fecha de nacimiento
 * @param edad -- Arreglo que contiene a?os meses y d?as vividos
**/
function obtenEdad(txtFechaNac) {
		var edad = new Array(3);
	 	if( txtFechaNac != null && txtFechaNac != "" ) {
			txtFechaNac = txtFechaNac.toUpperCase();
			intEdadAnios = calcularAniosFecha(txtFechaNac, "-");
		 	if(intEdadAnios < 0){
		 		alert("Fecha de nacimiento incorrecta");
		 		return edad;
		 	}
			
		 	intEdadMeses = calcularMesesFecha(txtFechaNac, "-");
	 		intEdadDias  = calcularDiasFecha(txtFechaNac, "-");
		 	edad[0] = intEdadAnios;
		 	edad[1] = intEdadMeses;
		 	edad[2] = intEdadDias;
	 		
	 		return edad;
	 	}
	}