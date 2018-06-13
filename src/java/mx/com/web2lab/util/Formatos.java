package mx.com.web2lab.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;

public class Formatos  implements Serializable{
	/** constructor default */
	public Formatos(){
	}
	/**log de la aplicación */
	private static Log iObjLog = LogFactory.getLog(Formatos.class);
	/**
	 * Este metodo recibe un string con el formato dd-mm-yyyy hh:mm:ss 
	 * y regresa un objeto Date correspondiente al string proporcionado  
	 * @param strFecha Fecha a formatear.
	 * @return Date
	 */
	public Date getObjFecha(String strFecha) throws Exception{
		iObjLog.debug("Formateando la fecha: " + strFecha);
		Calendar objCal = null;
		Date objDate = null;
		boolean isAnioBisiesto = false;
		try{
			String[] arrFecha = strFecha.split(" ")[0].split("-");
			String[] arrHora = strFecha.split(" ")[1].split(":");
			int dia = Integer.parseInt(arrFecha[0]);
			int mes = Integer.parseInt(arrFecha[1])-1;
			int anio = Integer.parseInt(arrFecha[2]);
			int hora = Integer.parseInt(arrHora[0]);
			int min = Integer.parseInt(arrHora[1]);
			int seg = Integer.parseInt(arrHora[2]);
			if(mes<0 || mes>11){
				iObjLog.debug("No es un mes apropiado...");
				return null;
			}
			if (((anio % 4)==0) && ((anio % 100)!=0) || ((anio % 400)==0)) {
				isAnioBisiesto = true;
			}
			if((mes==0 || mes==2 || mes==6 || mes==7 || mes==9 || mes==11) && (dia<1 || dia>31)){
				iObjLog.debug("No es un dia valido ...");
				return null;
			}
			if((mes==1 || mes==3 || mes==5 || mes==8 || mes==10 || mes==12) && (dia<1 || dia>30)){
				iObjLog.debug("No es un dia valido ...");
				return null;
			}
			if ((mes==1 && isAnioBisiesto && dia > 29) || (mes==1 && !isAnioBisiesto && dia > 28)){
				iObjLog.debug("No es un dia valido para el mes de febrero...");
				return null;
			}
			objCal = new GregorianCalendar();
			objCal.set(anio,mes,dia,hora,min,seg);
			objDate = objCal.getTime();
			objCal = null;
		}
		catch(Exception aError){
			throw aError;
		}
		return objDate;
	}
	
	
	/**
	 * Este metodo regresa la fecha de hoy en el formato dd'-'MMM'-'yyyy
	 * 19-NOV-2010
	 * @param aObjFecha Fecha a formatear.
	 * @return strFechaValida
	 */
	public String getFecha() throws Exception{
		String strFecha = (new SimpleDateFormat("dd-MMM-yyyy",this.getLocale())).format(new Date());
	 	strFecha = strFecha.toUpperCase();
	 	iObjLog.debug(" > strFecha: " + strFecha);
		return strFecha;
	}
	
	/**
	 * Este metodo regresa la fecha de hoy en el formato dd'-'MMM'-'yyyy
	 * 19-NOV-2010
	 * @param fecha Fecha a formatear.
	 * @return 
	 */
	public String getFecha(Date fecha) throws Exception{
		String strFecha = "";
		if(fecha!=null) {
			strFecha = (new SimpleDateFormat("dd-MMM-yyyy",this.getLocale())).format(fecha);
		 	strFecha = strFecha.toUpperCase();
		}
	 	iObjLog.debug(" > strFecha: " + strFecha);
		return strFecha;
	}

	/**
	 * Este metodo regresa la fecha de hoy en el formato dd'-'MMM'-'yyyy
	 * 19-NOV-2010
	 * @param fecha Fecha a formatear.
	 * @return 
	 */
	public String getFechaCompleta(Date fecha) throws Exception{
		String strFecha = "";
		if(fecha!=null) {
			strFecha = (new SimpleDateFormat("dd-MMMM-yyyy",this.getLocale())).format(fecha);
		 	strFecha = strFecha.toUpperCase();
		}
	 	iObjLog.debug(" > strFecha: " + strFecha);
		return strFecha;
	}
	
	
	/**
	 * Este metodo regresa la fecha de hoy en el formato dd'-'MMM'-'yyyy
	 * 19-NOV-2010, flagHoraMin = 1 horas y minutos 
	 * @param aObjFecha Fecha a formatear.
	 * @return strFechaValida
	 */
	public String getFecha(Date aObjFecha, int flagHoraMin ) throws Exception{
		String strFmt = (flagHoraMin == 1?"dd-MMM-yyyy HH:mm":"dd-MMM-yyyy");
		String strFecha = new SimpleDateFormat(strFmt,this.getLocale()).format(aObjFecha).toUpperCase();
	 	iObjLog.debug(" > strFecha: " + strFecha);
		return strFecha;
	}
	/**
	 * Este metodo regresa la fecha de hoy en el formato
	 * indicado por aStrFormato 
	 * 19-NOV-2010
	 * @param aObjFecha Fecha a formatear.
	 * @return strFechaValida
	 */
	public String getFecha(Date aObjFecha, String aStrFormato) throws Exception{
		if (aStrFormato==null || aStrFormato.trim().equals("")) return this.getFecha(aObjFecha);
		//SimpleDateFormat objFormato = new SimpleDateFormat(aStrFormato,this.getLoc());
		String strFecha = new SimpleDateFormat(aStrFormato,this.getLocale()).format(aObjFecha).toUpperCase();
	 	iObjLog.debug(" > strFecha: "+strFecha);
	 	strFecha = sustituyeMesCorto(strFecha).toUpperCase();
	 	return strFecha;
	}
	/**
	 * Este metodo regresa la fecha enviada en  
	 * aObjFecha en la forma "EEEEE, d ' de ' MMM ' del ' yyyy" 
	 * "Lunes, 19 de Noviembre del 2010"
	 * @param aObjFecha Fecha a formatear.
	 * @return strFechaValida
	 */
	public String getFechaLarga(Date aObjFecha) throws Exception{
		String strFecha = new SimpleDateFormat("EEE, d ' de ' MMM ' del ' yyyy", this.getLocale()).format(aObjFecha).toUpperCase();
	 	iObjLog.debug(" > strFecha: " + strFecha);
		return strFecha;
	}
	
	/**
	 * Este metodo regresa la fecha de hoy en la forma dd-MM-yyyy
	 * 19-11-2010
	 * @param aObjFecha Fecha a formatear.
	 * @return strFechaValida
	 */
	public String getFechaNumeros(Date aObjFecha){
		String strFecha = new SimpleDateFormat("dd-MM-yyyy",this.getLocale()).format(aObjFecha);
	 	iObjLog.debug("getFechaNumeros:strFecha: "+strFecha);
		return strFecha;
	}
	
	/**
	 * Este metodo regresa la fecha de hoy en la forma yyyy-MM-dd
	 * 19-11-2010
	 * @param aObjFecha Fecha a formatear.
	 * @return strFechaValida
	 */
	public String getFechaNumeros_yyymmddd(Date aObjFecha){
		String strFecha = new SimpleDateFormat("yyyy-MM-dd",this.getLocale()).format(aObjFecha);
	 	iObjLog.debug("getFechaNumeros_yyymmddd:strFecha: "+strFecha);
		return strFecha;
	}
	
	/**
	 * Este metodo regresa la fecha de hoy en la forma dd-MM-yyyy
	 * 19-11-2010
	 * @param aObjFecha Fecha a formatear.
	 * @return strFechaValida
	 */
	public String getFechaNumeros(Date aObjFecha, String aStrFormato){
		if (aStrFormato==null || aStrFormato.trim().equals("")) return getFechaNumeros(aObjFecha);
		String strFecha = new SimpleDateFormat(aStrFormato,this.getLocale()).format(aObjFecha);
	 	iObjLog.debug(" > strFecha: "+strFecha);
		return strFecha;
	}
	/**
	 * Este metodo regresa la fecha enviada en  
	 * aObjFecha en la forma "EEE, d ' de ' MMM ' del ' yyyy" 
	 * algo como "Lunes, 17 de Junio del 2004"
	 * @param aObjFecha Fecha a formatear.
	 * @return strFechaValida
	 */
	public String getFechaLetras(Date aObjFecha) throws Exception{
		String strFormato = "EEE, d ' de ' MMM ' del ' yyyy";
		String strFecha = new SimpleDateFormat(strFormato,this.getLocale()).format(aObjFecha).toUpperCase();
	 	iObjLog.debug(" > strFecha: "+strFecha);
		return strFecha;
	}
	/**
	 * Este metodo regresa la fecha enviada en  
	 * aObjFecha en la forma "EEE, d 'de' MMM 'del' yyyy" 
	 * algo como "Lunes, 17 de Junio del 2004"
	 * @param aObjFecha Fecha a formatear.
	 * @return strFechaValida
	 */
	public String getFechaLetras(Date aObjFecha, String aStrFormato) throws Exception{
		if (aStrFormato==null || aStrFormato.trim().equals("")) return getFechaLetras(aObjFecha);
		//SimpleDateFormat objFormato = new SimpleDateFormat(aStrFormato);
		String strFecha = new SimpleDateFormat(aStrFormato,this.getLocale()).format(aObjFecha).toUpperCase();
	 	iObjLog.debug(" > strFecha: "+strFecha);
	 	//strFecha = sustituyeDia(strFecha).toUpperCase();
	 	//strFecha = sustituyeMesCorto(strFecha).toUpperCase();
		return strFecha;
	}
	/**
	 * Este metodo obtiene la hora y los minutos
	 * @param aObjFecha Fecha a formatear.
	 * @return String
	 */
	public String getHoraMin(Date aObjFecha){
		return new SimpleDateFormat("HH:mm",this.getLocale()).format(aObjFecha);
	}
	
	/**
	 * Este metodo recibe un string con el formato dd-MMM-yyyy (10-OCT-2004)
	 * o con el formato dd-MMM-yyyy hh:mm:ss (10-OCT-2004 13:55:20) 
	 * y regresa un objeto Date correspondiente del string proporcionado. 
	 * La parte de la hora, minuto y segundo es opcional  
	 * @param strFecha (String) Fecha a formatear.
	 * @return java.util.Date
	 */
	public String getFechaSinHora(String strFecha) throws Exception{
		iObjLog.debug("Fomateando la fecha: " + strFecha);
		String[] arrFechaHora = null; 
		String[] arrFecha = null;
		arrFechaHora = strFecha.split(" ");
		if (arrFechaHora.length<0){
			arrFecha = arrFechaHora[0].split("-");
		}
		else{
			arrFecha = strFecha.split("-");
		}
		if(arrFecha.length<3){
			iObjLog.debug("La fecha no es valida ...");
			return null;
		}
		
		String dia = arrFecha[0];
		String mes = sustituyeNumeroMesBD(arrFecha[1].toUpperCase());
		String anio = arrFecha[2];
		return dia+"-"+mes+"-"+anio;
	}
	
	/**
	 * Este metodo recibe un string con el formato dd-MMM-yyyy hh:mm:ss 
	 * (10-OCT-2004 13:55:20) y regresa un objeto Date correspondiente 
	 * del string proporcionado. 
	 * La parte de la hora, minuto y segundo es opcional  
	 * @param strFecha (String) Fecha a formatear.
	 * @return java.util.Date
	 */
	public String getFechaBD(String strFecha, String control) throws Exception{
		iObjLog.debug("getFechaBD: " + strFecha);
		if(strFecha==null || strFecha.equals(""))return "";
		String strRetFecha = "";
		String[] arrFechaHora = null; 
		String[] arrFecha = null;
		arrFechaHora = strFecha.split(" ");
		if (arrFechaHora.length<0){
			arrFecha = arrFechaHora[0].split("-");
		}
		else{
			arrFecha = strFecha.split("-");
		}
		if(arrFecha.length<3){
			iObjLog.debug("La fecha no es valida ...");
			return null;
		}
		
		String dia = arrFecha[0];
		String mes = sustituyeNumeroMesBD(arrFecha[1].toUpperCase());
		String anio = arrFecha[2];
		strRetFecha = dia+"-"+mes+"-"+anio;
		if(control!=null && !control.equals("")){
			if(control.toUpperCase().equals("I")){
				strRetFecha += " 00:00:00";
			}else if(control.toUpperCase().equals("F")){
				strRetFecha += " 23:59:59";
			}
		}
		return strRetFecha;
	}
	
	/**
	 * Este metodo recibe un string con el formato dd-MMM-yyyy hh:mm:ss 
	 * (10-OCT-2004 13:55:20) y regresa un objeto Date correspondiente 
	 * del string proporcionado sin horas:minutos:segundos, es decir 10/10/2007
	 * La parte de la hora, minuto y segundo es opcional  
	 * @param strFecha (String) Fecha a formatear.
	 * @return java.util.Date
	 */
	public String getFechaSN(String strFecha, String control) throws Exception{
		iObjLog.debug("getFechaSN: " + strFecha);
		if(strFecha==null || strFecha.equals(""))return "";
		String strRetFecha = "";
		String[] arrFechaHora = null; 
		String[] arrFecha = null;
		arrFechaHora = strFecha.split(" ");
		if (arrFechaHora.length<0){
			arrFecha = arrFechaHora[0].split("-");
		}
		else{
			arrFecha = strFecha.split("-");
		}
		if(arrFecha.length<3){
			iObjLog.debug("La fecha no es valida ...");
			return null;
		}
		
		String dia = arrFecha[0];
		String mes = sustituyeNumeroMesBD(arrFecha[1].toUpperCase());
		String anio = arrFecha[2];
		strRetFecha = dia+"/"+mes+"/"+anio;
		iObjLog.debug("FECHA SN*****"+ strRetFecha);
		return strRetFecha;
	}
	
	/**
	 * Este metodo recibe un string con el formato dd-MMM-yyyy (10-OCT-2004)
	 * y regresa un String con la fecha numerica correspondiente (10-10-2004).  
	 * @param strFecha (String) Fecha a formatear.
	 * @return Date Fecha correspondiente al string proporcionado 
	 */
	public String getFechaBDSinHora(String strFecha) throws Exception{
		iObjLog.debug("getFechaBDSinHora: " + strFecha);
		String strRetFecha = "";
		if(strFecha==null || strFecha.equals(""))return "";
		String[] arrFechaHora = null; 
		String[] arrFecha = null;
		arrFechaHora = strFecha.split(" ");
		if (arrFechaHora.length<0){
			arrFecha = arrFechaHora[0].split("-");
		}
		else{
			arrFecha = strFecha.split("-");
		}
		if(arrFecha.length<3){
			iObjLog.debug("La fecha no es valida ...");
			return null;
		}
		
		String dia = arrFecha[0];
		String mes = sustituyeNumeroMesBD(arrFecha[1].toUpperCase());
		String anio = arrFecha[2];
		strRetFecha = dia+"-"+mes+"-"+anio;
		iObjLog.debug("getFechaBDSinHora:Regresa:"+strRetFecha);
		return strRetFecha;
	}
	
	/**
	 * Este metodo regresa la cantidad mencionada en 
	 * aObjNumero con letras 
	 * P.ej: 256.68 (dos cientos cincuenta y seis (68/100) )
	 * @param aObjNumero - Numero a formatear
	 * @return retStr - String con el numero formateado
	 */
	public String getStringNumeros(BigDecimal aObjNumero){
		String retStr = "";
		return retStr;
	}
	/**
	 * Este metodo regresa la cantidad mencionada en 
	 * aObjNumero con letras y con el formato de moneda
	 * P.ej: 256.68 (Son dos cientos cincuenta y seis pesos 68/100 M.N.)
	 * @param aObjNumero 
	 * @return strFechaValida
	 */
	public String getStringMoneda(BigDecimal aObjNumero){
		String retStr = "";
		return retStr;
	}
	
	/**
	 * 
	 * @param aObjNum
	 * @return
	 * @throws NumberFormatException
	 */
	public String formateaNumero(String aStrCantidad)
	throws NumberFormatException {
		String strFormato = null;
		double objDouble = 0;
		try{
			if(aStrCantidad != null && !aStrCantidad.trim().equals("")){
				objDouble = Double.parseDouble(aStrCantidad);
			}else{
				objDouble = Double.parseDouble("0");
			}
			strFormato = new DecimalFormat("###,###,###,###,##0.00").format(objDouble);
		}catch(NumberFormatException aObjException){
			return ("El valor asignado a la funcion de formateo no es valido");
		}
		return strFormato;
	}

	
	public static String formateaNumero2Digitos(String aStrCantidad)
	throws NumberFormatException {
		String strFormato = null;
		double objDouble = 0;
		try{
			if(aStrCantidad != null && !aStrCantidad.trim().equals("")){
				objDouble = Double.parseDouble(aStrCantidad);
			}else{
				objDouble = Double.parseDouble("00");
			}
			strFormato = new DecimalFormat("00").format(objDouble);
		}catch(NumberFormatException aObjException){
			return ("El valor asignado a la funcion de formateo no es valido");
		}
		return strFormato;
	}
		
	/**
	 * Método que regresa una cadena que representa el objeto 
	 * que se pasa, dicho objeto puede ser una subclase de Number
	 * o un String, el formato es de miles separado por coma  
	 * @param aObjNum Object
	 * @return
	 * @throws Exception
	 */
	public String formateaNumero(Object aObjNum)
	throws Exception {
		Number objNumber = null;
		String strFmt = "###,###,###,###,##0.00";
		String strRes = "0.00";
		try{
			if(aObjNum==null){
				iObjLog.debug("Formatos.formateaNumero:ObjetonVieneNull: " + aObjNum);
				return strRes;
			}
			else if ((aObjNum instanceof Number)){
				objNumber = (Number)aObjNum;
				iObjLog.debug("Formatos.formateaNumero(Object-Number): " + aObjNum);
				strRes = new DecimalFormat(strFmt).format(objNumber.doubleValue());
			}
			else if ((aObjNum instanceof String)){
					String strNum = (String)aObjNum;
					iObjLog.debug("Formatos.formateaNumero(Object-String): " + aObjNum);
					if (!(esNumero(strNum))) return strRes;
					Double objDbl = new Double(strNum);
					strRes = new DecimalFormat(strFmt).format(objDbl.doubleValue());
			}
			else{
				iObjLog.debug("----->>>>> Formatos.formateaNumero(Object)La clase del objeto fue: " + aObjNum.getClass().toString());
				return strRes;
			}
		}
		catch(Exception aError){
			iObjLog.error("----->>>>> Formatos.formateaNumero(arg0) Error al formatear el String: " + aObjNum + " " + aError.toString());
			throw aError;
		}
		iObjLog.debug("Formatos:formateaNumero:Objeto|Regresa:" + aObjNum +"|"+ strRes);
		return strRes;
	}
	
	/**
	 * Método que regresa una cadena que representa el objeto 
	 * que se pasa, dicho objeto puede ser una subclase de Number
	 * o un String, el formato es solo con dos decimales sin separador 
	 * de miles.   
	 * @param aObjNum Object
	 * @return
	 * @throws Exception
	 */
	public String formateaNumero2Dec(Object aObjNum)
	throws Exception {
		Number objNumber = null;
		String strFmt = "##0.00";
		String strRes = "0.00";
		try{
			if(aObjNum==null){
				iObjLog.debug("formateaNumero2Dec:ObjetonVieneNull: " + aObjNum);
				return strRes;
			}
			else if ((aObjNum instanceof Number)){
				objNumber = (Number)aObjNum;
				iObjLog.debug("formateaNumero2Dec-(Object-Number): " + aObjNum);
				strRes = new DecimalFormat(strFmt).format(objNumber.doubleValue());
			}
			else if ((aObjNum instanceof String)){
					String strNum = (String)aObjNum;
					iObjLog.debug("formateaNumero2Dec-(Object-String): " + aObjNum);
					if (!(esNumero(strNum))) return strRes;
					Double objDbl = new Double(strNum);
					strRes = new DecimalFormat(strFmt).format(objDbl.doubleValue());
			}
			else{
				iObjLog.debug("----->>>>> formateaNumero2Dec(Object)La clase del objeto fue: " + aObjNum.getClass());
				return strRes;
			}
		}
		catch(Exception aError){
			iObjLog.error("----->>>>> formateaNumero2Dec(arg0) Error al formatear el String: " + aObjNum + " " + aError.toString());
			throw aError;
		}
		iObjLog.debug("Formatos:formateaNumero:Objeto|Regresa:" + aObjNum +"|"+ strRes);
		return strRes;
	}
	
	/**
	 * 
	 * @param aObjNum
	 * @param aStrFmt
	 * @return
	 * @throws Exception
	 */
	public String formateaNumero(BigDecimal aObjNum, String aStrFmt)
	throws Exception {
		String strRes = "";
		try{
			if(aStrFmt==null||aStrFmt.trim().equals(""))return formateaNumero(aObjNum);
			strRes = new DecimalFormat(aStrFmt).format(aObjNum.longValue());
		}
		catch(Exception aError){
			iObjLog.debug("----->>>>> Formatos.formateaNumero(arg0,arg1) Error al formatear: " + aObjNum);
		}
		return strRes;
	}
	
	
	/**
	 * Metodo que formatea una clave de muestra a 10 digitos.
	 * @param lngMuestra (Numero de muestra)
	 * @return String cadena con el numero formateado.
	 * @throws Exception
	 */
	public String fromateaMuestra(long lngMuestra) throws Exception{
		try {
			//El formato de 10 digitos es de hecho mayor que el valor maximo de Long
			return new DecimalFormat("##########").format(lngMuestra);
		}
		catch(Exception aError){
			iObjLog.error("formateaMuestra Al dar formato a:"+lngMuestra+" excepcion:"+aError.toString());
			return Long.toString(lngMuestra);
		}
	}
	
	/**
	 * Metodo que formatea una fecha de tipo  1978-11-16 02:00:05.0
	 * para mostrarla en 16-NOV-1978
	 * @param aStrFecAFor
	 * @return
	 */
	public String formateaFechaDB(String aStrFecAFor){
	    if( aStrFecAFor.length() < 12 ){
	        return aStrFecAFor;
	    }
		Map objMeses = new HashMap();
		objMeses.put("01", "Ene");
		objMeses.put("02", "Feb");
		objMeses.put("03", "Mar");
		objMeses.put("04", "Abr");
		objMeses.put("05", "May");
		objMeses.put("06", "Jun");
		objMeses.put("07", "Jul");
		objMeses.put("08", "Ago");
		objMeses.put("09", "Sep");
		objMeses.put("10", "Oct");
		objMeses.put("11", "Nov");
		objMeses.put("12", "Dic");
		String strFecFormat = null;
		//1978-11-16 02:00:05.0
		String strAnio = aStrFecAFor.substring(0,4);
		String strMes = objMeses.get(aStrFecAFor.substring(5,7))+"";
		String strDia = aStrFecAFor.substring(8,10);
		strFecFormat = strDia+"-"+strMes+"-"+strAnio;
		return strFecFormat;
	}
	
	/**
	 * Método que sustituye un string (searchString) 
	 * por otro string (replaceString), dentro de 
	 * una tercera cadena (evaluateString) y regresa 
	 * el resultado de la operación, la sustitución es 
	 * no es sensible a mayúsculas o minúsculas 
	 * @param searchString
	 * @param replaceString
	 * @param evaluateString
	 * @return String 
	 */
	public static String replaceString(String evaluateString, String searchString, String replaceString)
	throws Exception {
		String str = "";
		try{
			RE objRE = new RE("([" + searchString + "])",RE.MATCH_CASEINDEPENDENT);
			str = objRE.subst(evaluateString, replaceString, RE.REPLACE_ALL);
		}
		catch(Exception aError){
			iObjLog.debug("Ocurrio un error al reemplazar el string: ", aError);
			throw aError;
		}
		return str;
	}
	
	/**
	 * Metodo que sustituye los meses en ingles por su equivalente en español
	 * (JAN - ENERO ...) 
	 * @param aStrFecha
	 * @return
	 * @throws Exception
	 */
	public String sustituyeMesLargo(String aStrFecha) 
	throws Exception{
		String strFecha = aStrFecha;
	 	strFecha = replaceString(strFecha,"JAN","ENERO");
	 	strFecha = replaceString(strFecha,"FEB","FEBRERO");
	 	strFecha = replaceString(strFecha,"MAR","MARZO");
	 	strFecha = replaceString(strFecha,"APR","ABRIL");
	 	strFecha = replaceString(strFecha,"MAY","MAYO");
	 	strFecha = replaceString(strFecha,"JUN","JUNIO");
	 	strFecha = replaceString(strFecha,"JUL","JULIO");
	 	strFecha = replaceString(strFecha,"AUG","AGOSTO");
	 	strFecha = replaceString(strFecha,"SEP","SEPTIEMBRE");
	 	strFecha = replaceString(strFecha,"OCT","OCTUBRE");
	 	strFecha = replaceString(strFecha,"NOV","NOVIEMBRE");
	 	strFecha = replaceString(strFecha,"DEC","DICIEMBRE");
	 	return strFecha;
	}
	/**
	 * Metodo que sustituye los meses en ingles por su equivalente en español
	 * (JAN - ENE ...) 
	 * @param aStrFecha
	 * @return
	 * @throws Exception
	 */
	public String sustituyeMesCorto(String aStrFecha)
	throws Exception{
		String strFecha = aStrFecha;
	 	strFecha = replaceString(strFecha,"JAN","ENE");
	 	strFecha = replaceString(strFecha,"FEB","FEB");
	 	strFecha = replaceString(strFecha,"MAR","MAR");
	 	strFecha = replaceString(strFecha,"APR","ABR");
	 	strFecha = replaceString(strFecha,"MAY","MAY");
	 	strFecha = replaceString(strFecha,"JUN","JUN");
	 	strFecha = replaceString(strFecha,"JUL","JUL");
	 	strFecha = replaceString(strFecha,"AUG","AGO");
	 	strFecha = replaceString(strFecha,"SEP","SEP");
	 	strFecha = replaceString(strFecha,"OCT","OCT");
	 	strFecha = replaceString(strFecha,"NOV","NOV");
	 	strFecha = replaceString(strFecha,"DEC","DIC");
	 	return strFecha;
	}
	/**
	 * Metodo que sustituye los meses en español  por su equivalente en ingles
	 * (ENE - JAN ...) 
	 * @param aStrFecha
	 * @return
	 * @throws Exception
	 */
	public String sustituyeMesCortoIng(String aStrFecha)
	throws Exception{
		String strFecha = aStrFecha;
	 	if(aStrFecha.equals("ENE")) return "JAN";
	 	if(aStrFecha.equals("FEB")) return "FEC";
	 	if(aStrFecha.equals("MAR")) return "MAR";
	 	if(aStrFecha.equals("ABR")) return "APR";
	 	if(aStrFecha.equals("MAY")) return "MAY";
	 	if(aStrFecha.equals("JUN")) return "JUN";
	 	if(aStrFecha.equals("JUL")) return "JUL";
	 	if(aStrFecha.equals("AGO")) return "AUG";
	 	if(aStrFecha.equals("SEP")) return "SEP";
	 	if(aStrFecha.equals("OCT")) return "OCT";
	 	if(aStrFecha.equals("NOV")) return "NOV";
	 	if(aStrFecha.equals("DIC")) return "DEC";
	 	return strFecha;
	}
	/**
	 * Metodo que sustituye el numero de mes en ingles por su equivalente en español
	 * (01 - ENE, 02 - FEB,  ...) 
	 * @param aStrFecha
	 * @return
	 * @throws Exception
	 * @deprecated
	 */
	public String sustituyeNumeroMes(Date aObjFecha)
	throws Exception{
		String strFecha = new SimpleDateFormat("MM").format(aObjFecha);
	 	strFecha = replaceString(strFecha,"01","ENE");
	 	strFecha = replaceString(strFecha,"02","FEB");
	 	strFecha = replaceString(strFecha,"03","MAR");
	 	strFecha = replaceString(strFecha,"04","ABR");
	 	strFecha = replaceString(strFecha,"05","MAY");
	 	strFecha = replaceString(strFecha,"06","JUN");
	 	strFecha = replaceString(strFecha,"07","JUL");
	 	strFecha = replaceString(strFecha,"08","AGO");
	 	strFecha = replaceString(strFecha,"09","SEP");
	 	strFecha = replaceString(strFecha,"10","OCT");
	 	strFecha = replaceString(strFecha,"11","NOV");
	 	strFecha = replaceString(strFecha,"12","DIC");
	 	return strFecha;
	}
	/**
	 * Metodo que sustituye el numero de mes en ingles por su equivalente en español
	 * (ENE - 01, FEB - 02,  ...) 
	 * @param aObjFecha - Date
	 * @return String 
	 * @throws Exception
	 */
	public String sustituyeNumeroMes(String aStrFecha)
	throws Exception{
		String strFecha = aStrFecha.trim();
		if(strFecha.equalsIgnoreCase("ENE"))strFecha = "01";
		if(strFecha.equalsIgnoreCase("FEB"))strFecha = "02";
		if(strFecha.equalsIgnoreCase("MAR"))strFecha = "03";
		if(strFecha.equalsIgnoreCase("ABR"))strFecha = "04";
		if(strFecha.equalsIgnoreCase("MAY"))strFecha = "05";
		if(strFecha.equalsIgnoreCase("JUN"))strFecha = "06";
		if(strFecha.equalsIgnoreCase("JUL"))strFecha = "07";
		if(strFecha.equalsIgnoreCase("AGO"))strFecha = "08";
		if(strFecha.equalsIgnoreCase("SEP"))strFecha = "09";
		if(strFecha.equalsIgnoreCase("OCT"))strFecha = "10";
		if(strFecha.equalsIgnoreCase("NOV"))strFecha = "11";
		if(strFecha.equalsIgnoreCase("DIC"))strFecha = "12";
	 	return strFecha;
	}
	/**
	 * Metodo que sustituye el dia en ingles por su equivalente en español
	 * (SUN - DOMINGO, MON - LUNES,  ...) 
	 * @param aStrFecha
	 * @return
	 * @throws Exception
	 */
	public String sustituyeDia(String aStrFecha)
	throws Exception{
		String strFecha = aStrFecha;
	 	strFecha = replaceString(strFecha,"SUN","DOMINGO");
	 	strFecha = replaceString(strFecha,"MON","LUNES");
	 	strFecha = replaceString(strFecha,"TUE","MARTES");
	 	strFecha = replaceString(strFecha,"THU","MIERCOLES");
	 	strFecha = replaceString(strFecha,"WED","JUEVES");
	 	strFecha = replaceString(strFecha,"FRI","VIERNES");
	 	strFecha = replaceString(strFecha,"SAT","SABADO");
	 	return strFecha;
	}
	
	/**
	 * Metodo que obtiene la localidad (Locale) para crear los formatos de fecha
	 * si no se encuentra para idioma español se crea uno con el default del jvm 
	 * @return Locale 
	 */
	public Locale getLocale() {
		Locale objLoc = new Locale("es");
		iObjLog.debug("creando Locale 'es': " + objLoc);
		if (objLoc == null){
			objLoc = Locale.getDefault();
			iObjLog.debug("no encontro 'es', default del jvm: " + objLoc);
		}
		return objLoc; 
	}
	
	/**
	 * Método que evalua que una cadena contenga un valor 
	 * numérico, la cadena siempre debe contener la parte entera, 
	 * la parte decimal es opcional 
	 * @param evaluateString
	 * @return boolean 
	 */
	public boolean esNumero(String strNumero) 
	throws Exception {
		boolean retValue = false; 
		try{
			if (strNumero==null || strNumero.trim().equals("")){
				iObjLog.debug("Formatos:esNumero:la cadena es null o vacia se regresa false : " + strNumero);
				return retValue;
			}
			retValue = (!Double.isNaN(Double.parseDouble(strNumero)));
			//retValue = new RE("^[+-]?\\d+(\\.\\d+)?$").match(strNumero);
		}
		catch(Exception aError){
			iObjLog.error("Formatos:esNumero:Ocurrio un error al validar la cadena a numero : " + strNumero + " " + aError.toString());
			return false;
			//throw aError;
		}
		return retValue; 
	}
	
	/**
	 * Este metodo recibe un string con el formato dd-mm-yyyy (10-OCT-2004)
	 * o con el formato dd-MMM-yyyy hh:mm:ss (10-OCT-2004 13:55:20) 
	 * y regresa un objeto Date correspondiente del string proporcionado. 
	 * La parte de la hora, minuto y segundo es opcional  
	 * @param strFecha (String) Fecha a formatear.
	 * @return java.util.Date
	 */
	public Date getFecha(String strFecha) throws Exception{
		iObjLog.debug("getFecha:Fomateando la fecha: " + strFecha);
		String[] arrFechaHora = null; 
		String[] arrFecha = null;
		String[] arrHora = "00:00:00".split(":");
		String fecha = "";
		Date objDate = null; 
		arrFechaHora = strFecha.split(" ");
		iObjLog.debug("NUMEROSPLIT:ARRAEGLO|"+arrFechaHora.length+"|"+arrFechaHora);
		if (arrFechaHora!=null && arrFechaHora.length>0){
			//LA FECHA PUEDE O NO VENIR CON HORA
			arrFecha = arrFechaHora[0].split("-");
			if (arrFechaHora.length>1){
				//LA FECHA TRAE HORA
				arrHora = arrFechaHora[1].split(":");
			}else{
				//SOLO TRAE LA FECHA
				arrFecha = strFecha.split("-");
			}
			if(arrFecha.length<3){
				System.out.println("La fecha no es valida ...");
				return null;
			}
			String dia = arrFecha[0];
			String mes = sustituyeNumeroMesJava(arrFecha[1]);
			String anio = arrFecha[2];
			iObjLog.debug("Formatos.getFecha(Date)dia|mes:anio:"+dia+"|"+mes+"|"+anio);
			String hora = "00";
			String mins = "00";
			String segs = "00";
			if (arrHora!=null && arrHora.length>0){
				if (arrHora.length==1){
					hora = arrHora[0];
				}
				else if(arrHora.length==2){
					hora = arrHora[0];
					mins = arrHora[1];
				}
				else if(arrHora.length==3){
					hora = arrHora[0];
					mins = arrHora[1];
					segs = arrHora[2];
				}
			}
			fecha = dia+"-"+mes+"-"+anio+" "+hora+":"+mins+":"+segs;
			iObjLog.debug("DATOSDELAFECHA:"+fecha);
			objDate = getObjFechaJava(fecha);
		}		
		iObjLog.debug("getFecha:DATERETORNADA:|"+objDate);
		return objDate;
	}
	
	/**
	 * Este metodo recibe un string con el formato dd-mm-yyyy hh:mm:ss 
	 * y regresa un objeto Date correspondiente al string proporcionado  
	 * @param strFecha Fecha a formatear.
	 * @return Date
	 */
	private Date getObjFechaJava(String strFecha) throws Exception{
		iObjLog.debug("getObjFechaJava:Formateando la fecha: " + strFecha);
		Calendar objCal = null;
		Date objDate = null;
		boolean isAnioBisiesto = false;
		try{
			String[] arrFecha = strFecha.split(" ")[0].split("-");
			String[] arrHora = strFecha.split(" ")[1].split(":");
			int dia = Integer.parseInt(arrFecha[0]);
			int mes = Integer.parseInt(arrFecha[1]);
			int anio = Integer.parseInt(arrFecha[2]);
			int hora = Integer.parseInt(arrHora[0]);
			int min = Integer.parseInt(arrHora[1]);
			int seg = Integer.parseInt(arrHora[2]);
			if(mes<0 || mes>11){
				iObjLog.debug("No es un mes apropiado...");
				return null;
			}
			if (((anio % 4)==0) && ((anio % 100)!=0) || ((anio % 400)==0)) {
				isAnioBisiesto = true;
			}
			iObjLog.debug("DatosFecha:Bisiesto|Anio|mes|Dia|hora|min|seg:"+isAnioBisiesto+"|"+anio+"|"+mes+"|"+dia+"|"+hora+"|"+min+"|"+seg);
			if((mes==0 || mes==2 || mes==6 || mes==7 || mes==9 || mes==11) && (dia<1 || dia>31)){
				iObjLog.debug("No es un dia valido:mes|Dia" + mes + "|"+dia);
				return null;
			}
			if((mes==1 || mes==3 || mes==5 || mes==8 || mes==10 || mes==12) && (dia<1 || dia>30)){
				iObjLog.debug("No es un dia valido:mes|Dia:" + mes + "|"+dia);
				return null;
			}
			if ((mes==1 && isAnioBisiesto && dia>29) || (mes==1 && !isAnioBisiesto && dia>28)){
				iObjLog.debug("No es un dia valido para el mes de febrero:mes|Dia:" + mes + "|"+dia);
				return null;
			}
			objCal = new GregorianCalendar();
			objCal.set(anio,mes,dia,hora,min,seg);
			objDate = objCal.getTime();
			objCal = null;
		}
		catch(Exception aError){
			throw aError;
		}
		iObjLog.debug("getObjFechaJava:Regresa:" +objDate);
		return objDate;
	}
	
	/**
	 * Metodo que sustituye el numero de mes en ingles por su equivalente en español
	 * (ENE - 01, FEB - 02,  ...) 
	 * @param aObjFecha - Date
	 * @return String 
	 * @throws Exception
	 */
	public  String sustituyeNumeroMesBD(String aStrFecha)
	throws Exception{		
		iObjLog.debug("SUSTITUIYENUM:|"+aStrFecha+"|");
		String strFechaMayus = aStrFecha.trim().toUpperCase();
		iObjLog.debug("SUSTITUIYENUMMAYUS:|"+strFechaMayus+"|");
		String strFecha = "";
		if(strFechaMayus.equals("ENE"))strFecha="01";
		if(strFechaMayus.equals("FEB"))strFecha="02";
		if(strFechaMayus.equals("MAR"))strFecha="03";
		if(strFechaMayus.equals("ABR"))strFecha="04";
		if(strFechaMayus.equals("MAY"))strFecha="05";
		if(strFechaMayus.equals("JUN"))strFecha="06";
		if(strFechaMayus.equals("JUL"))strFecha="07";
		if(strFechaMayus.equals("AGO"))strFecha="08";
		if(strFechaMayus.equals("SEP"))strFecha="09";
		if(strFechaMayus.equals("OCT"))strFecha="10";
		if(strFechaMayus.equals("NOV"))strFecha="11";
		if(strFechaMayus.equals("DIC"))strFecha="12";
		iObjLog.debug("SUSTITUIYENUMRET:|"+strFecha+"|");
		return strFecha;
	}	

	/**
	 * Metodo que sustituye el numero de mes en ingles por su equivalente en español
	 * (ENE - 01, FEB - 02,  ...) 
	 * @param aObjFecha - Date
	 * @return String 
	 * @throws Exception
	 */
	public  String sustituyeNumeroMesJava(String aStrFecha)
	throws Exception{		
		String strFecha = aStrFecha.trim();
		if(strFecha.equalsIgnoreCase("ENE"))strFecha = "00";
		if(strFecha.equalsIgnoreCase("FEB"))strFecha = "01";
		if(strFecha.equalsIgnoreCase("MAR"))strFecha = "02";
		if(strFecha.equalsIgnoreCase("ABR"))strFecha = "03";
		if(strFecha.equalsIgnoreCase("MAY"))strFecha = "04";
		if(strFecha.equalsIgnoreCase("JUN"))strFecha = "05";
		if(strFecha.equalsIgnoreCase("JUL"))strFecha = "06";
		if(strFecha.equalsIgnoreCase("AGO"))strFecha = "07";
		if(strFecha.equalsIgnoreCase("SEP"))strFecha = "08";
		if(strFecha.equalsIgnoreCase("OCT"))strFecha = "09";
		if(strFecha.equalsIgnoreCase("NOV"))strFecha = "10";
		if(strFecha.equalsIgnoreCase("DIC"))strFecha = "11";
	 	return strFecha;
	}	
	
    public static double redondeo2decimales(double nD) {
		return Math.round(nD*Math.pow(10,2))/Math.pow(10,2);      	
    }
    
    public static boolean isNumeric(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }    
}
