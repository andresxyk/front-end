package mx.com.web2lab.util;

import java.io.Serializable;
import java.text.DecimalFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author it
 * Esta clase convierte un numero decimal en su representacion textual.
 * Por ejemplo  856 escribe ocho cientos, cincuenta y seis.
 * 1024 mil veinticuatro.
 */

public class ConvierteNumeros  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public long cantidad;
	public long decimal;
	public String letrasDecimales;
	public String numDecimales;
	public String cadenaFinal;
	
	private static Log iObjLog = LogFactory.getLog(ConvierteNumeros.class);
	
	public static void main(String args[]){
		ConvierteNumeros nel = new ConvierteNumeros(600568);
		//             Debugger.debug("ConvierteNumeros.java",166,""+ nel.cadenaFinal);
		nel.convierte(600568);
		System.out.println("convierte (600568): " + nel.convierte(600568) );
	}

	//Constructor de la clase... SRC
	public ConvierteNumeros(long x){
		cantidad = x;
		cadenaFinal = convierte(cantidad);
	}
	
	//Constructor de la clase... SRC
	public ConvierteNumeros(String x){
		iObjLog.debug("String x = " + x.toString());
		String strValue = new DecimalFormat("#00.00").format(x);
		//TODO:Cath Exception
		numDecimales = new Long(strValue).toString().split("([.])")[1];//<<< Configuracion regional España causa ERROR aqui por la coma ! (pmimenza)
		cantidad = new Long(numDecimales).longValue();
		letrasDecimales = convierte(cantidad);
		cantidad = new Long(x).longValue();
		cadenaFinal = convierte(cantidad);
	}

	//Constructor de la clase... SRC
	public ConvierteNumeros(Double x){
		iObjLog.debug("String x = " + x.toString()); 
		String strValue =	new DecimalFormat("#00.00").format(x);
		iObjLog.debug("doble formateado: " + strValue);
		numDecimales = strValue.split("([.])")[1];
		cantidad = new Long(numDecimales).longValue();
		letrasDecimales = convierte(cantidad);
		cantidad = x.longValue();
		cadenaFinal = convierte(cantidad);
	}

	//Constructor de la clase... SRC
	public ConvierteNumeros(Long x){
		iObjLog.debug("String x = " + x.toString());
		String strValue = new DecimalFormat("#00.00").format(x);
		System.out.println("long formateado: " + strValue);
		numDecimales = strValue.split("([.])")[1];
		cantidad = new Long(numDecimales).longValue();
		letrasDecimales = convierte(cantidad);
		cantidad = x.longValue();
		cadenaFinal = convierte(cantidad);
	}

	private String unidades [] ={
			"",
			"uno",
			"dos",
			"tres",
			"cuatro",
			"cinco",
			"seis",
			"siete",
			"ocho",
			"nueve"
	};
	private String decenas[] = {
			"",
			"dieci",
			"veinti",
			"treinta",
			"cuarenta",
			"cincuenta",
			"sesenta",
			"setenta",
			"ochenta",
			"noventa"
	};
	
	/** 
	 Regresa una cadena que representa un numero del 0 al 99.
	 Esta funcion es utilizada por otra que se encarga de "desglosar"
	 el resto de los numeros.
	 @param n un numero entero decimal del 0 al 99
	 */
	protected String del0al99( int n ){
		String letras = null;
		switch( n ){
		case 0 : letras = "cero"; break;
		case 10: letras = "diez"; break;
		case 11: letras = "once"; break;
		case 12: letras = "doce"; break;
		case 13: letras = "trece"; break;
		case 14: letras = "catorce"; break;
		case 15: letras = "quince"; break;
		case 20: letras = "veinte"; break;
		default:
			letras = decenas[ n / 10 ];
		if( n < 30 ){
			letras += unidades[ n % 10 ];
			
		}else{
			if( n % 10 != 0 ){
				letras += " y "+ unidades[ n % 10 ];
			}
		}
		break;
		
		}
		return letras;
	}
	
	/**
	 Convierte un numero  representado por una cadena en su represantacion en letras.
	 Funciona recursivamente llamandose a si misma y concatenando el resultado.
	 por ejemplo para decir 19845 primero calcula como se dicen los primeros dos:
	 "diecinueve", le concatena el "mil" , y despues calcula como se dice 845, "ocho" + "cientos", y al
	 ultimo calcula como se dice 45 "cuarenta y cinco", todo esto se concatena el regresar de la recursividad
	 y donde se mando el mensaje recibe "diecinueve mill ocho cientos cuarenta y cinco".
	 <br>  Aun no maneja numeros negativos pero debe de ser bastante sencillo: concatenar la palabra menos
	 @param esteNumero , un cadena que representa un numero. Ejemplo: "10000", y regresa "diez mil"
	 */
	public String convierte(long n){
		return convierte(String.valueOf(n));
	}
	
	public String convierte(String esteNumero) {
		// Este metodo trabaja deacuerdo a la longitud del string que recibe:
		// si su longitud es 2 el numero es menor  100
		// si la longitud es 3 es un numero menor que 1000, etc etc.
		int longitud = esteNumero.length();
		int numero = Integer.parseInt( esteNumero );
		// casos dificiles...
		switch( numero ){
		case    100 : return "cien";
		case    1000: return "mil";
		case 1000000: return "un millon";
		}
		// trabajamos de acuerdo a la longitud de la cadena que recibimos
		switch( longitud ){
		case 1:
		case 2: return ( esteNumero.equals("00") ) ?
				"" :
					del0al99(Integer.parseInt(esteNumero));
		/*
		 if( ( esteNumero.equals("00") )){
		 return "";
		 }else{
		 return ( del0al99( Integer.parseInt(esteNumero)));  
		 }*/
		// 0 al 999
		case 3:
			if( esteNumero.equals("000")){
				return "";
			}
			String tipo = null ;
			// casos dificiles, por que no se puede decir "cinco cientos", o "uno cientos"
			switch( esteNumero.charAt(0) ){ 
			case '1': tipo = "ciento "; break;
			case '5': tipo = "quinientos "; break;
			case '7': tipo = "setecientos "; break;
			case '9': tipo = "novecientos ";break;
			}
			if( tipo == null ){
				if( numero / 100 == 0 ){
					tipo = "";
				}else{
					tipo =  unidades[numero / 100 ]  + "cientos ";
				}
			}
			return ( tipo + convierte(esteNumero.substring(1,longitud)));
			// 0 al 999999
		case 4:
			
			if( esteNumero.charAt(0) == '1' ){
				return( "mil, " + convierte( esteNumero.substring(1,4))); 
			}
		case 5:
		case 6:
			if( numero / 1000  == 0 ){
				return  convierte( esteNumero.substring( longitud - 3 , longitud ));
			} 
			return (
					convierte( esteNumero.substring( 0, longitud-3 ) ) + " mil, " +
					convierte( esteNumero.substring( longitud-3 , longitud ) ) 
			);
			// 1000000 al 9999999
		case 7: 
			String inicio = null;
			if( esteNumero.charAt(0) == '1' ){
				inicio = "un millon ";
			}else{
				inicio = unidades[numero / 1000000] + " millones, ";
			}
			return ( inicio + convierte( esteNumero.substring( 1, longitud ) ) );
		}
		return "Aun sin implementar";
	}
	
	public String getNumLetras(){
		return cadenaFinal;
	}
	
	public String getStrNumDec(){
		return numDecimales;
	}

	public String getStrLetrasDec(){
		return letrasDecimales;
	}
}
