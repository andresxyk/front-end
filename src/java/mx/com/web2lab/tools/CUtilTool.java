package mx.com.web2lab.tools;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

import mx.com.web2lab.util.CalculaFechas;
import mx.com.web2lab.util.Formatos;
import mx.com.web2lab.backend.hbm.ConfiguracionProperties;
import mx.com.web2lab.backend.interfaz.MCatalogosTool;
import mx.com.web2lab.backend.util.cacheestatus.EstadosEntidad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.services.pull.ApplicationTool;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.pool.Recyclable;

public class CUtilTool implements Recyclable, ApplicationTool, Serializable{
	
	//metodos necesarios para la implementacion de un tool
	
	/**log de la aplicaci&oacute;n */
	private static Log iObjLog = LogFactory.getLog(CUtilTool.class);	
	/** The object containing request specific data */
	private RunData iObjDatos;
	
	/** For Dispose managing */
	private boolean iBolDisposed;
	
	/** Conctructor Default*/
	public CUtilTool(){
		
	}
	/**
	* Comment
	* @param data comment
	*/
	public void init(Object aObjDatos){
	   this.iObjDatos = (RunData)aObjDatos;
	}
	/**
	* nulls out the issue and user objects
	*/
	public void refresh(){
	   // do not need since it is a request tool
	}
	/**
	* Recycles the object for a new client. Recycle methods with
	* parameters must be added to implementing object and they will be
	* automatically called by pool implementations when the object is
	* taken from the pool for a new client. The parameters must
	* correspond to the parameters of the constructors of the object.
	* For new objects, constructors can call their corresponding recycle
	* methods whenever applicable.
	* The recycle methods must call their super.
	*/
	public void recycle(){
	   iBolDisposed = false;
	}
	/**
	* Disposes the object after use. The method is called
	* when the object is returned to its pool.
	* The dispose method must call its super.
	*/
	public void dispose(){
		iObjDatos = null;
		iBolDisposed = true;
	}
	/**
	* Checks whether the recyclable has been disposed.
	* @return true, if the recyclable is disposed.
	*/
	public boolean isDisposed(){
		   return iBolDisposed;
	}

	/* ****** metodos de funcionalidad del tool  ******* */
	
	/**
	 * Metodo que formatea las cantidades de moneda al formato
	 *  $12,123,123.89
	 * @param aStrCantidad
	 * @return
	 */
	public String getCantidadFormato(String aStrCantidad){
		String strFormato = null;
		double objDouble = 0;
		try{
			if(aStrCantidad != null && !aStrCantidad.trim().equals("")){
				objDouble = Double.parseDouble(aStrCantidad);
			}else{
				objDouble = Double.parseDouble("0");
			}
			strFormato = new DecimalFormat("$###,###,###,###,##0.00").format(objDouble);
		}catch(NumberFormatException aObjException){
			return ("El valor asignado a la funcion de formateo no es valido");
		}
		return strFormato;
	}
	
	
	/**
	 * Metodo que regresa la fecha actual en el formato
	 * @return
	 * @throws Exception
	 */
	public String getFechaActual()throws Exception{
		String strFecha = new Formatos().getFecha(new Date());
		return strFecha;
	}

	/**
	 * Metodo que regresa la fecha actual en el formato
	 * @return
	 * @throws Exception
	 */
	public String getVersion()throws Exception{
		String strReturn = "";
		if (ConfiguracionProperties.bolProduccion) {
			strReturn = "PRODUCCION";
		} else {
			strReturn = "DESARROLLO";
		}
		return strReturn;
	}
	
	
	/**
	 * Metodo que regresa la fecha actual en el formato
	 * @return
	 * @throws Exception
	 */
	public String getFechaActualCompleta()throws Exception{
		String strFecha = new Formatos().getFechaCompleta(new Date());
		return strFecha;
	}

	/**
	 * Metodo que convierte una fecha al formato estandard 
	 * @return
	 * @throws Exception
	 */
	public String getFechaCompleta(Date aObjFecha)throws Exception{
		String strFecha = new Formatos().getFechaCompleta(aObjFecha);
		return strFecha;
	}
	
	/**
	 * Metodo que convierte una fecha al formato estandard
	 * @return
	 * @throws Exception
	 */
	public String getFecha(Date aObjFecha)throws Exception{
		String strFecha = new Formatos().getFecha(aObjFecha);
		return strFecha;
	}
	
	/**
	 * Metodo que formatea un numero para no regresar
	 * decimales 
	 * @param aStrCantidad
	 * @return
	 */
	public String getCantSinDec(String aStrCantidad){
		String strFormato = null;
		double objDouble = 0;
		try{
			if(aStrCantidad != null && !aStrCantidad.trim().equals("")){
				objDouble = Double.parseDouble(aStrCantidad);
			}else{
				objDouble = Double.parseDouble("0");
			}
			strFormato = new DecimalFormat("#00").format(objDouble);
		}catch(NumberFormatException aObjException){
			return ("El valor asignado a la funcion de formateo no es valido");
		}
		return strFormato;
	}
	
	/**
	 * Metodo que formatea un numero para no regresar
	 * decimales.
	 *  
	 * @param aStrCantidad
	 * @return
	 */
	public String getCant2Dec(String aStrCantidad){
		String strFormato = null;
		double objDouble = 0;
		try{
			if(aStrCantidad != null && !aStrCantidad.trim().equals("")){
				objDouble = Double.parseDouble(aStrCantidad);
			}else{
				objDouble = Double.parseDouble("0");
			}
			strFormato = new DecimalFormat("#00.00").format(objDouble);
		}catch(NumberFormatException aObjException){
			return ("El valor asignado a la funcion de formateo no es valido");
		}
		return strFormato;
	}
		
	
	/**
	 * Metodo que realiza el calculo de la edad en Años comparando la
	 * fecha de nacimiento contra la fecha de captura.
	 * 
	 * @param aObjFechaNac. Fecha de nacimiento.
	 * @param aObjFechaCap. Fecha de captura.
	 * @return Edad en Años.
	 * @throws Exception
	 */
	public int getEdadAnios(Date aObjFechaNac, Date aObjFechaCap) 
	throws Exception {
		CalculaFechas objFechas = new CalculaFechas();
		return objFechas.getEdadAnios(aObjFechaNac, aObjFechaCap);
	}
	
	/**
	 * Metodo que realiza el calculo de la edad en MESES comparando la
	 * fecha de nacimiento contra la fecha de captura.
	 * 
	 * @param aObjFechaNac. Fecha de nacimiento.
	 * @param aObjFechaCap. Fecha de captura.
	 * @return Edad en meses.
	 * @throws Exception
	 */
	public int getEdadMeses(Date aObjFechaNac, Date aObjFechaCap) 
	throws Exception {
		CalculaFechas objFechas = new CalculaFechas();
		return objFechas.getEdadMeses(aObjFechaNac, aObjFechaCap);
	}

	/**
	 * Metodo que realiza el calculo de la edad en DIAS comparando la
	 * fecha de nacimiento contra la fecha de captura.
	 * 
	 * @param aObjFechaNac. Fecha de nacimiento.
	 * @param aObjFechaCap. Fecha de captura.
	 * @return Edad en dias.
	 * @throws Exception
	 */
	public int getEdadDias(Date aObjFechaNac, Date aObjFechaCap) 
	throws Exception {
		CalculaFechas objFechas = new CalculaFechas();
		return objFechas.getEdadDias(aObjFechaNac, aObjFechaCap);
	}

	public static boolean getIndexOf(String contenedor, String contenido) 
	{
		boolean retorno = true;
		
		if(contenedor.indexOf(contenido) == -1)
		{
			retorno = false;
		}
		return retorno;
	}
}
