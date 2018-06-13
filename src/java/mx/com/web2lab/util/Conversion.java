package mx.com.web2lab.util;


public class Conversion
{

    public Conversion()
    {
    }

    public String operacion(String word1)
    {
        String unidades[] = {
            "", "UN", "DOS", "TRES", "CUATRO", "CINCO", "SEIS", "SIETE", "OCHO", "NUEVE"
        };
        String un[] = {
            "", "ONCE", "DOCE", "TRECE", "CATORCE", "QUINCE", "DIECISEIS", "DIECISIETE", "DIECIOCHO", "DIECINUEVE"
        };
        String decenas[] = {
            "", "DIEZ", "VEINTI", "TREINTA", "CUARENTA", "CINCUENTA", "SESENTA", "SETENTA", "OCHENTA", "NOVENTA"
        };
        String centenas[] = {
            "", "CIENTO", "DOSCIENTOS", "TRESCIENTOS", "CUATROCIENTOS", "QUINIENTOS", "SEISCIENTOS", "SETECIENTOS", "OCHOCIENTOS", "NOVECIENTOS"
        };
        if(word1.equals("100"))
            return "CIEN ";
        int uno = Integer.valueOf(word1.substring(0, 1)).intValue();
        int dos = Integer.valueOf(word1.substring(1, 2)).intValue();
        int tres = Integer.valueOf(word1.substring(2, 3)).intValue();
        word1 = "";
        word1 = centenas[uno] + " ";
        if(dos == 1 && tres != 0)
        {
            word1 = word1 + un[tres] + " ";
            return word1;
        }
        if(dos == 2 && tres == 0)
            return word1 = word1 + "VEINTE ";
        word1 = word1 + decenas[dos];
        if(dos != 0 && dos != 2 && tres != 0)
            word1 = word1 + " Y ";
        word1 = word1 + unidades[tres] + " ";
        return word1;
    }

    public String convertirALetra(String word)
    {
        String cantidad[] = {
            "", "MIL", "MILLONES", "MIL"
        };
        int itMin = 0;
        int itMax = 3;
        String stTmp = " ";
        String stTotal = "";
        String stResiduo = "00";
        int longitud = word.length();
        int itTmp = word.indexOf(".");
        if(itTmp != -1)
        {
            stResiduo = word.substring(itTmp, longitud);
            if(stResiduo.length() == 1)
                stResiduo = "00";
            else
            if(stResiduo.length() == 2)
                stResiduo = stResiduo.substring(1, 2) + "0";
            else
            if(stResiduo.length() > 2)
                stResiduo = stResiduo.substring(1, 3);
            word = word.substring(0, itTmp);
        }
        longitud = word.length() % 3;
        if(longitud == 1)
            word = "00" + word;
        if(longitud == 2)
            word = "0" + word;
        longitud = word.length();
        if(longitud == 9 && word.charAt(longitud - 7) == '1' && word.charAt(longitud - 8) == '0' && word.charAt(longitud - 9) == '0')
            cantidad[2] = "MILLON";
        longitud = word.length() / 3;
        if(longitud > 1)
        {
            itMax = longitud * 3;
            itMin = itMax - 3;
        }
        for(int i = 0; i < longitud; i++)
        {
            stTmp = word.substring(itMin, itMax);
            if(!stTmp.equals("000"))
                stTotal = operacion(stTmp) + cantidad[i] + " " + stTotal;
            if(i + 1 < longitud)
            {
                itMin -= 3;
                itMax -= 3;
            }
        }

        return stTotal + " PESOS " + stResiduo + "/100 M.N.";
    }
}
