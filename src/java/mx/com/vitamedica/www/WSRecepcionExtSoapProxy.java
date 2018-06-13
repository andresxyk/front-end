package mx.com.vitamedica.www;

public class WSRecepcionExtSoapProxy implements mx.com.vitamedica.www.WSRecepcionExtSoap {
  private String _endpoint = null;
  private mx.com.vitamedica.www.WSRecepcionExtSoap wSRecepcionExtSoap = null;
  
  public WSRecepcionExtSoapProxy() {
    _initWSRecepcionExtSoapProxy();
  }
  
  public WSRecepcionExtSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSRecepcionExtSoapProxy();
  }
  
  private void _initWSRecepcionExtSoapProxy() {
    try {
      wSRecepcionExtSoap = (new mx.com.vitamedica.www.WSRecepcionExtLocator()).getWSRecepcionExtSoap();
      if (wSRecepcionExtSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSRecepcionExtSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSRecepcionExtSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSRecepcionExtSoap != null)
      ((javax.xml.rpc.Stub)wSRecepcionExtSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public mx.com.vitamedica.www.WSRecepcionExtSoap getWSRecepcionExtSoap() {
    if (wSRecepcionExtSoap == null)
      _initWSRecepcionExtSoapProxy();
    return wSRecepcionExtSoap;
  }
  
  public java.lang.String buscaOrdenLaboratorio(java.lang.String tipo, java.lang.String elegibilidad) throws java.rmi.RemoteException{
    if (wSRecepcionExtSoap == null)
      _initWSRecepcionExtSoapProxy();
    return wSRecepcionExtSoap.buscaOrdenLaboratorio(tipo, elegibilidad);
  }
  
  public java.lang.String buscaServiciosPorRealizar(java.lang.String tipo, java.lang.String elegibilidad) throws java.rmi.RemoteException{
    if (wSRecepcionExtSoap == null)
      _initWSRecepcionExtSoapProxy();
    return wSRecepcionExtSoap.buscaServiciosPorRealizar(tipo, elegibilidad);
  }
  
  public java.lang.String validaServicioPorRealizar(java.lang.String tipo, java.lang.String proveedorConsulta, java.lang.String grupoID, java.lang.String proveedorLaboratorio, java.lang.String elegibilidad, java.lang.String ICD, java.lang.String CPT, java.lang.String fechaConsulta, java.lang.String preautorizacion, int unidades) throws java.rmi.RemoteException{
    if (wSRecepcionExtSoap == null)
      _initWSRecepcionExtSoapProxy();
    return wSRecepcionExtSoap.validaServicioPorRealizar(tipo, proveedorConsulta, grupoID, proveedorLaboratorio, elegibilidad, ICD, CPT, fechaConsulta, preautorizacion, unidades);
  }
  
  public java.lang.String generaReclamacion(java.lang.String tipo, java.lang.String proveedorConsulta, java.lang.String grupoID, java.lang.String proveedorLaboratorio, java.lang.String elegibilidad, java.lang.String ICD, java.lang.String CPT, java.lang.String fechaConsulta, java.lang.String preautorizacion, int unidades) throws java.rmi.RemoteException{
    if (wSRecepcionExtSoap == null)
      _initWSRecepcionExtSoapProxy();
    return wSRecepcionExtSoap.generaReclamacion(tipo, proveedorConsulta, grupoID, proveedorLaboratorio, elegibilidad, ICD, CPT, fechaConsulta, preautorizacion, unidades);
  }
  
  public java.lang.String cancelaServicios(java.lang.String tipo, java.lang.String proveedorLaboratorio, java.lang.String elegibilidad, java.lang.String CPT, java.lang.String reclamacion) throws java.rmi.RemoteException{
    if (wSRecepcionExtSoap == null)
      _initWSRecepcionExtSoapProxy();
    return wSRecepcionExtSoap.cancelaServicios(tipo, proveedorLaboratorio, elegibilidad, CPT, reclamacion);
  }
  
  public java.lang.String buscaDatosUsuario(java.lang.String tipo, java.lang.String grupoID, java.lang.String nomina_poliza, java.lang.String certificado, int beneficiario) throws java.rmi.RemoteException{
    if (wSRecepcionExtSoap == null)
      _initWSRecepcionExtSoapProxy();
    return wSRecepcionExtSoap.buscaDatosUsuario(tipo, grupoID, nomina_poliza, certificado, beneficiario);
  }
  
  public java.lang.String limpiaServiciosPorRealizar(java.lang.String elegibilidad) throws java.rmi.RemoteException{
    if (wSRecepcionExtSoap == null)
      _initWSRecepcionExtSoapProxy();
    return wSRecepcionExtSoap.limpiaServiciosPorRealizar(elegibilidad);
  }
  
  public java.lang.String registraServiciosProveedor(java.lang.String reclamacion, java.lang.String cveProveedor, java.lang.String cveSucursal) throws java.rmi.RemoteException{
    if (wSRecepcionExtSoap == null)
      _initWSRecepcionExtSoapProxy();
    return wSRecepcionExtSoap.registraServiciosProveedor(reclamacion, cveProveedor, cveSucursal);
  }
  
  
}