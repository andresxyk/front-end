package mx.com.web2lab.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CalculaFechas  implements Serializable{
	/** constructor default */
	public CalculaFechas(){
	}
	/**log de la aplicación */
	private static Log iObjLog = LogFactory.getLog(CalculaFechas.class);
	
	/*
	 * Obtiene la edad
	 * @param objFechaNac
	 * @return edad
	 * @throws Exception
	 *
	public int getEdadAnios(Date objFechaNac, Date objFechaBase) 
	throws Exception{
		Calendar calNac = new GregorianCalendar();
		//long objEdadMs = System.currentTimeMillis() - objFechaNac.getTime();
		long objEdadMs = objFechaBase.getTime() - objFechaNac.getTime();
		calNac.setTime(new Date(objEdadMs));
		//se restan 1970 por caracterisiticas de los milisegundos del date
		return  calNac.get(Calendar.YEAR)-1970;		
	}*/
	
	/*
	 * obtiene la edad en meses
	 * @param objFechaNac
	 * @return edad en meses
	 * @throws Exception
	 *
	public int getEdadMeses(Date objFechaNac, Date objFechaBase) 
	throws Exception{
		Calendar calNac = new GregorianCalendar();
		//long objEdadMs = System.currentTimeMillis() - objFechaNac.getTime();
		long objEdadMs = objFechaBase.getTime() - objFechaNac.getTime();
		calNac.setTime(new Date(objEdadMs));
		return calNac.get(Calendar.MONTH);
	}*/

	/*
	 * obtiene la edad en dias
	 * @param ldteNacimiento
	 * @return edad en dias
	 * @throws Exception
	 *
	public int getEdadDias(Date objFechaNac, Date objFechaBase) 
	throws Exception {
		Calendar calNac = new GregorianCalendar();
		//long objEdadMs = System.currentTimeMillis() - objFechaNac.getTime();
		long objEdadMs = objFechaBase.getTime() - objFechaNac.getTime();
		calNac.setTime(new Date(objEdadMs));
		return calNac.get(Calendar.DAY_OF_MONTH);
	}*/
	
	/**
	 * Obtiene la edad
	 * @param objFechaNac con el formato yyyy-mm-dd
	 * @return edad
	 * @throws Exception*/
	 
	public int getEdadAnios(Date objFechaNac, Date objFechaBase){
			//day, month, year) {
		
		String strFechaNac = this.getFechaNumerica(objFechaNac);
		String strFechaBase = this.getFechaNumerica(objFechaBase);
		String[] arrFecha = strFechaNac.split(" ")[0].split("-");
		int year = Integer.parseInt(arrFecha[0]);
		int month = Integer.parseInt(arrFecha[1]);
//		int day = Integer.parseInt(arrFecha[2]);
		
		String[] arrFechaBase = strFechaBase.split(" ")[0].split("-");
		int thisYear = Integer.parseInt(arrFechaBase[0]);
		int thisMonth = Integer.parseInt(arrFechaBase[1]);
//		int thisDay = Integer.parseInt(arrFechaBase[2]);
		
		int yearsold = thisYear - year;
		int monthsold = 0; 
//		int daysold = 0; 
//		String cadena = "";

	    if (thisMonth >= month) monthsold = thisMonth - month;
	    else { yearsold--; monthsold = thisMonth + 12 - month; }

//	    if (thisDay >= day)daysold = thisDay - day;
//	    else {
	        if (monthsold > 0) monthsold--;
	        else { yearsold--; monthsold += 11; }
//	        daysold = thisDay + 31 - day;
//	    }

	    return yearsold;
	}
	
	/**
	 * obtiene la edad en meses
	 * @param objFechaNac con el formato yyyy-mm-dd
	 * @return edad en meses
	 * @throws Exception
	 */
	
	
	public int getEdadMeses(Date objFechaNac, Date objFechaBase) {
		
		String strFechaNac = this.getFechaNumerica(objFechaNac);
		String strFechaBase = this.getFechaNumerica(objFechaBase);
		String[] arrFecha = strFechaNac.split(" ")[0].split("-");
		int year = Integer.parseInt(arrFecha[0]);
		int month = Integer.parseInt(arrFecha[1]);
//		int day = Integer.parseInt(arrFecha[2]);
		
		String[] arrFechaBase = strFechaBase.split(" ")[0].split("-");
		int thisYear = Integer.parseInt(arrFechaBase[0]);
		int thisMonth = Integer.parseInt(arrFechaBase[1]);
//		int thisDay = Integer.parseInt(arrFechaBase[2]);
	    
		int yearsold = thisYear - year;
		int monthsold = 0; 
//		int daysold = 0; 
//		String cadena = "";
		
	    if (thisMonth >= month) monthsold = thisMonth - month;
	    else { yearsold--; monthsold = thisMonth + 12 - month; }

//	    if (thisDay >= day)daysold = thisDay - day;
//	    else {
	        if (monthsold > 0) monthsold--;
	        else { yearsold--; monthsold += 11; }
//	        daysold = thisDay + 31 - day;
//	    }
	    return monthsold;
	}
	
	
	
	/**
	 * obtiene la edad en dias
	 * @param ldteNacimiento con el formato yyyy-mm-dd
	 * @return edad en dias
	 * @throws Exception
	 */
	
	public int getEdadDias(Date objFechaNac, Date objFechaBase){
		String strFechaNac = this.getFechaNumerica(objFechaNac);
		String strFechaBase = this.getFechaNumerica(objFechaBase);
		String[] arrFecha = strFechaNac.split(" ")[0].split("-");
		int year = Integer.parseInt(arrFecha[0]);
		int month = Integer.parseInt(arrFecha[1]);
		int day = Integer.parseInt(arrFecha[2]);
		
		String[] arrFechaBase = strFechaBase.split(" ")[0].split("-");
		int thisYear = Integer.parseInt(arrFechaBase[0]);
		int thisMonth = Integer.parseInt(arrFechaBase[1]);
		int thisDay = Integer.parseInt(arrFechaBase[2]);
		
	    int yearsold = thisYear - year;
		int monthsold = 0; 
		int daysold = 0; 
//		String cadena = "";

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
	
	/**
	 * Obtiene la edad
	 * @param objDteNacimiento con el formato yyyy-mm-dd
	 * @return edad
	 * @throws Exception
	 */
	public int getEdadAnios(Date objDteNacimiento) 
	throws Exception{
		String strFechaNac = objDteNacimiento.toString();
		String strFechaBase = this.getFechaNumerica(new Date());
		String[] arrFecha = strFechaNac.split(" ")[0].split("-");
		int year = Integer.parseInt(arrFecha[0]);
		int month = Integer.parseInt(arrFecha[1]);
//		int day = Integer.parseInt(arrFecha[2]);
		
		String[] arrFechaBase = strFechaBase.split(" ")[0].split("-");
		int thisYear = Integer.parseInt(arrFechaBase[0]);
		int thisMonth = Integer.parseInt(arrFechaBase[1]);
//		int thisDay = Integer.parseInt(arrFechaBase[2]);
		
		int yearsold = thisYear - year;
		int monthsold = 0; 
//		int daysold = 0; 
//		String cadena = "";

	    if (thisMonth >= month) monthsold = thisMonth - month;
	    else { yearsold--; monthsold = thisMonth + 12 - month; }

//	    if (thisDay >= day)daysold = thisDay - day;
//	    else {
	        if (monthsold > 0) monthsold--;
	        else { yearsold--; monthsold += 11; }
//	        daysold = thisDay + 31 - day;
//	    }
	    return yearsold;
	}
	
	/**
	 * obtiene la edad en meses
	 * @param objDteNacimiento con el formato yyyy-mm-dd
	 * @return edad en meses
	 * @throws Exception
	 */
	public int getEdadMeses(Date objDteNacimiento) 
	throws Exception{
		String strFechaNac = objDteNacimiento.toString();
		String strFechaBase = this.getFechaNumerica(new Date());
		String[] arrFecha = strFechaNac.split(" ")[0].split("-");
		int year = Integer.parseInt(arrFecha[0]);
		int month = Integer.parseInt(arrFecha[1]);
//		int day = Integer.parseInt(arrFecha[2]);
		
		String[] arrFechaBase = strFechaBase.split(" ")[0].split("-");
		int thisYear = Integer.parseInt(arrFechaBase[0]);
		int thisMonth = Integer.parseInt(arrFechaBase[1]);
//		int thisDay = Integer.parseInt(arrFechaBase[2]);
	    
		int yearsold = thisYear - year;
		int monthsold = 0; 
//		int daysold = 0; 
//		String cadena = "";
		
	    if (thisMonth >= month) monthsold = thisMonth - month;
	    else { yearsold--; monthsold = thisMonth + 12 - month; }

//	    if (thisDay >= day)daysold = thisDay - day;
//	    else {
	        if (monthsold > 0) monthsold--;
	        else { yearsold--; monthsold += 11; }
//	        daysold = thisDay + 31 - day;
//	    }

	    return monthsold;
	}
	
	/**
	 * obtiene la edad en dias
	 * @param ldteNacimiento con el formato yyyy-mm-dd
	 * @return edad en dias
	 * @throws Exception
	 */
	public int getEdadDias(Date objDteNacimiento) 
	throws Exception {
		String strFechaNac = objDteNacimiento.toString();
		String strFechaBase = this.getFechaNumerica(new Date());
		String[] arrFecha = strFechaNac.split(" ")[0].split("-");
		int year = Integer.parseInt(arrFecha[0]);
		int month = Integer.parseInt(arrFecha[1]);
		int day = Integer.parseInt(arrFecha[2]);
		
		String[] arrFechaBase = strFechaBase.split(" ")[0].split("-");
		int thisYear = Integer.parseInt(arrFechaBase[0]);
		int thisMonth = Integer.parseInt(arrFechaBase[1]);
		int thisDay = Integer.parseInt(arrFechaBase[2]);
		
	    int yearsold = thisYear - year;
		int monthsold = 0; 
		int daysold = 0; 
//		String cadena = "";

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
	
	/**
	 * Este metodo regresa la fecha de hoy en el formato yyyy'-'mm'-'dd
	 * 19-nov-2010
	 * @param Fecha a formatear.
	 * @return strFechaValida
	 */
	public String getFechaNumerica(Date aObjFecha){
		SimpleDateFormat objFormato = new SimpleDateFormat("yyyy'-'MM'-'dd");
		String strFecha = objFormato.format(aObjFecha);
	 	iObjLog.debug(" > strFecha: "+strFecha);
		return strFecha;
	}

}
