/**
 * WSDataSTC_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.sct.www;

public interface WSDataSTC_PortType extends java.rmi.Remote {
    public Derechohabiente[] getDatos(java.lang.String expediente, java.lang.String tokengrant) throws java.rmi.RemoteException;
    public PasesLG[] getPase(java.lang.String numeropase, java.lang.String tokengrant) throws java.rmi.RemoteException;
    public java.lang.String updateEstudios(java.lang.String idestudio, java.lang.String nombreestudio, java.lang.String indicacionesestudio, java.lang.String precioestudio, java.lang.String tipoestudio, java.lang.String tokengrant) throws java.rmi.RemoteException;
    public java.lang.String addInterpretacion(java.lang.String numeropase, java.lang.String idestudio, java.lang.String ferealizacion, java.lang.String interpretacion, java.lang.String unidadlaboratorio, double costoestudio, java.lang.String entregado, java.lang.String rutadetalle, java.lang.String tokengrant) throws java.rmi.RemoteException;
}
