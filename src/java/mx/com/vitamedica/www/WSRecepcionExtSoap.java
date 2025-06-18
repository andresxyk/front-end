/**
 * WSRecepcionExtSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.vitamedica.www;

public interface WSRecepcionExtSoap extends java.rmi.Remote {

    /**
     * B&uacute;squeda de Ordenes de laboratorio
     */
    public java.lang.String buscaOrdenLaboratorio(java.lang.String tipo, java.lang.String elegibilidad) throws java.rmi.RemoteException;

    /**
     * Busca servicios de Laboratorio por realizar
     */
    public java.lang.String buscaServiciosPorRealizar(java.lang.String tipo, java.lang.String elegibilidad) throws java.rmi.RemoteException;

    /**
     * Valida sevicio de laboratorio por realizar
     */
    public java.lang.String validaServicioPorRealizar(java.lang.String tipo, java.lang.String proveedorConsulta, java.lang.String grupoID, java.lang.String proveedorLaboratorio, java.lang.String elegibilidad, java.lang.String ICD, java.lang.String CPT, java.lang.String fechaConsulta, java.lang.String preautorizacion, int unidades) throws java.rmi.RemoteException;

    /**
     * Obtiene el n&uacute;mero de la reclamaci&oacute;n que se genera para el pago
     * (salud) o guarda CPT (GMn)
     */
    public java.lang.String generaReclamacion(java.lang.String tipo, java.lang.String proveedorConsulta, java.lang.String grupoID, java.lang.String proveedorLaboratorio, java.lang.String elegibilidad, java.lang.String ICD, java.lang.String CPT, java.lang.String fechaConsulta, java.lang.String preautorizacion, int unidades) throws java.rmi.RemoteException;

    /**
     * Genera la cancelaci&oacute;n total o parcial de los servicios
     */
    public java.lang.String cancelaServicios(java.lang.String tipo, java.lang.String proveedorLaboratorio, java.lang.String elegibilidad, java.lang.String CPT, java.lang.String reclamacion) throws java.rmi.RemoteException;

    /**
     * Busqueda de  elegibilidades por usuario
     */
    public java.lang.String buscaDatosUsuario(java.lang.String tipo, java.lang.String grupoID, java.lang.String nomina_poliza, java.lang.String certificado, int beneficiario) throws java.rmi.RemoteException;

    /**
     * Limpiar la validacin de los servicios por realizar
     */
    public java.lang.String limpiaServiciosPorRealizar(java.lang.String elegibilidad) throws java.rmi.RemoteException;

    /**
     * Inserta el registro de servicios realizados por sucursal
     */
    public java.lang.String registraServiciosProveedor(java.lang.String reclamacion, java.lang.String cveProveedor, java.lang.String cveSucursal) throws java.rmi.RemoteException;
}
