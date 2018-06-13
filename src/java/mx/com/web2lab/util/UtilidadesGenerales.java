package mx.com.web2lab.util;

public class UtilidadesGenerales {

	
	public static String completaCadena(String cadena, char caracter, int longitud, String posicion){
		String cadenaFinal = null;
		String aux = "";
		if(cadena != null){
			if(cadena.length() > longitud){
				cadenaFinal = cadena.substring(0,longitud);
				
			}else{
				for(int i = 0; i < (longitud - cadena.length()); i++){
					aux += caracter;
				}
				cadenaFinal = posicion.equals("R") ? cadena + aux : aux + cadena;
			}
		}
		return cadenaFinal;
	}
}
