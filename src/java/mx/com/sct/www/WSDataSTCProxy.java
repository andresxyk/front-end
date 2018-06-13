package mx.com.sct.www;

public class WSDataSTCProxy implements WSDataSTC_PortType {
  private String _endpoint = null;
  private WSDataSTC_PortType wSDataSTC_PortType = null;
  
  public WSDataSTCProxy() {
    _initWSDataSTCProxy();
  }
  
  public WSDataSTCProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSDataSTCProxy();
  }
  
  private void _initWSDataSTCProxy() {
    try {
      wSDataSTC_PortType = (new WSDataSTC_ServiceLocator()).getWSDataSTCPort();
      if (wSDataSTC_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSDataSTC_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSDataSTC_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSDataSTC_PortType != null)
      ((javax.xml.rpc.Stub)wSDataSTC_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public WSDataSTC_PortType getWSDataSTC_PortType() {
    if (wSDataSTC_PortType == null)
      _initWSDataSTCProxy();
    return wSDataSTC_PortType;
  }
  
  public Derechohabiente[] getDatos(java.lang.String expediente, java.lang.String tokengrant) throws java.rmi.RemoteException{
    if (wSDataSTC_PortType == null)
      _initWSDataSTCProxy();
    return wSDataSTC_PortType.getDatos(expediente, tokengrant);
  }
  
  public PasesLG[] getPase(java.lang.String numeropase, java.lang.String tokengrant) throws java.rmi.RemoteException{
    if (wSDataSTC_PortType == null)
      _initWSDataSTCProxy();
    return wSDataSTC_PortType.getPase(numeropase, tokengrant);
  }
  
  public java.lang.String updateEstudios(java.lang.String idestudio, java.lang.String nombreestudio, java.lang.String indicacionesestudio, java.lang.String precioestudio, java.lang.String tipoestudio, java.lang.String tokengrant) throws java.rmi.RemoteException{
    if (wSDataSTC_PortType == null)
      _initWSDataSTCProxy();
    return wSDataSTC_PortType.updateEstudios(idestudio, nombreestudio, indicacionesestudio, precioestudio, tipoestudio, tokengrant);
  }
  
  public java.lang.String addInterpretacion(java.lang.String numeropase, java.lang.String idestudio, java.lang.String ferealizacion, java.lang.String interpretacion, java.lang.String unidadlaboratorio, double costoestudio, java.lang.String entregado, java.lang.String rutadetalle, java.lang.String tokengrant) throws java.rmi.RemoteException{
    if (wSDataSTC_PortType == null)
      _initWSDataSTCProxy();
    return wSDataSTC_PortType.addInterpretacion(numeropase, idestudio, ferealizacion, interpretacion, unidadlaboratorio, costoestudio, entregado, rutadetalle, tokengrant);
  }
  
  
}