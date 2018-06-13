/**
 * WSDataSTC_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.sct.www;

public class WSDataSTC_ServiceLocator extends org.apache.axis.client.Service implements WSDataSTC_Service {

    public WSDataSTC_ServiceLocator() {
    }


    public WSDataSTC_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSDataSTC_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSDataSTCPort
    private java.lang.String WSDataSTCPort_address = "http://metro-sisem.df.gob.mx:8080/ws_stc/WSDataSTC";

    public java.lang.String getWSDataSTCPortAddress() {
        return WSDataSTCPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSDataSTCPortWSDDServiceName = "WSDataSTCPort";

    public java.lang.String getWSDataSTCPortWSDDServiceName() {
        return WSDataSTCPortWSDDServiceName;
    }

    public void setWSDataSTCPortWSDDServiceName(java.lang.String name) {
        WSDataSTCPortWSDDServiceName = name;
    }

    public WSDataSTC_PortType getWSDataSTCPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSDataSTCPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSDataSTCPort(endpoint);
    }

    public WSDataSTC_PortType getWSDataSTCPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            WSDataSTCPortBindingStub _stub = new WSDataSTCPortBindingStub(portAddress, this);
            _stub.setPortName(getWSDataSTCPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSDataSTCPortEndpointAddress(java.lang.String address) {
        WSDataSTCPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (WSDataSTC_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                WSDataSTCPortBindingStub _stub = new WSDataSTCPortBindingStub(new java.net.URL(WSDataSTCPort_address), this);
                _stub.setPortName(getWSDataSTCPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WSDataSTCPort".equals(inputPortName)) {
            return getWSDataSTCPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://wsystem/", "WSDataSTC");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://wsystem/", "WSDataSTCPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSDataSTCPort".equals(portName)) {
            setWSDataSTCPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
