package mx.com.web2lab.reportes;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BarCodeImage  implements Serializable{
	public static final int BARCODE_2OF7 = 0;
	public static final int BARCODE_3OF9 = 1;
	public static final int BARCODE_Bookland = 2;
	public static final int BARCODE_Codabar = 3;
	public static final int BARCODE_Code128 = 4;
	public static final int BARCODE_Code128A = 5;
	public static final int BARCODE_Code128B = 6;
	public static final int BARCODE_Code128C = 7;
	public static final int BARCODE_Code39 = 8;
	public static final int BARCODE_EAN128 = 9;
	public static final int BARCODE_EAN13 = 10;
	public static final int BARCODE_GlobalTradeItemNumber = 11;
	public static final int BARCODE_Int2of5 = 12;
	public static final int BARCODE_Monarch = 13;
	public static final int BARCODE_NW7 = 14;
	public static final int BARCODE_PDF417 = 15;
	public static final int BARCODE_SCC14ShippingCode = 16;
	public static final int BARCODE_ShipmentIdentificationNumber = 17;
	public static final int BARCODE_SSCC18 = 18;
	public static final int BARCODE_Std2of5 = 19;
	public static final int BARCODE_UCC128 = 20;
	public static final int BARCODE_UPCA = 21;
	public static final int BARCODE_USD3 = 22;
	public static final int BARCODE_USD4 = 23;
	public static final int BARCODE_USPS = 24;
	
	//** log de la aplicacion */
	private static Log iObjLog = LogFactory.getLog(BarCodeImage.class);
	
	/** Constructor privado default */
	private BarCodeImage(){
	}
	
	/**
	 * M&eacute;todo encargado de generar una imagen con el c&oacute;digo de barras 
	 * del tipo indicado en el primer argumento y con el texto indicado
	 * en el segundo argumento. 
	 * @param type
	 * @param data
	 * @return
	 */
	public synchronized static java.awt.Image getImage(int type, String data) {
		iObjLog.debug("getImage - ENTRANDO :" + data);
		java.awt.Image img = null;
		try{
			img = (java.awt.Image)
			(it.businesslogic.ireport.barcode.BcImage.getBarcodeImage(type, data, false, false));
		}catch(Exception e){
			iObjLog.error("getImage:ERROR", e);
			throw new RuntimeException(e.getMessage());
		}
		iObjLog.debug("getImage - SALIENDO :" + data);
		return img;
	}
	
	/**
	 * M&eacute;todo encargado de generar una imagen con el c&oacute;digo de barras 
	 * del tipo indicado en el primer argumento y con el texto indicado
	 * en el segundo argumento, se manda en el tercer argumentotambien 
	 * una bandera para imprimir el texto enviado en el segundo argumento 
	 * y una bandera para incluir o no el checksum como parte del c&oacute;digo 
	 * de barras. 
	 * @param type
	 * @param data
	 * @param showText
	 * @param checkSum
	 * @return
	 */
	public synchronized static java.awt.Image getImage(int type, String data, boolean showText, boolean checkSum) {
		iObjLog.debug("getImage - ENTRANDO :" + data);
		java.awt.Image img = null;
		try{
			img = (java.awt.Image)
			(it.businesslogic.ireport.barcode.BcImage.getBarcodeImage(type, data, showText, checkSum));
		}catch(Exception e){
			iObjLog.error("getImage:ERROR", e);
			throw new RuntimeException(e.getMessage());
		}
		iObjLog.debug("getImage - SALIENDO :" + data);
		return img;
	}

}
