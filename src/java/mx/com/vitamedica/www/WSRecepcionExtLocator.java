/**
 * WSRecepcionExtLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.vitamedica.www;

public class WSRecepcionExtLocator extends org.apache.axis.client.Service implements mx.com.vitamedica.www.WSRecepcionExt {

    public WSRecepcionExtLocator() {
    }


    public WSRecepcionExtLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSRecepcionExtLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSRecepcionExtSoap
    private java.lang.String WSRecepcionExtSoap_address = "http://paralelo.redvitamedica.com.mx/laboratorios/Services/wsRecepcionext.asmx";

    public java.lang.String getWSRecepcionExtSoapAddress() {
        return WSRecepcionExtSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSRecepcionExtSoapWSDDServiceName = "WSRecepcionExtSoap";

    public java.lang.String getWSRecepcionExtSoapWSDDServiceName() {
        return WSRecepcionExtSoapWSDDServiceName;
    }

    public void setWSRecepcionExtSoapWSDDServiceName(java.lang.String name) {
        WSRecepcionExtSoapWSDDServiceName = name;
    }

    public mx.com.vitamedica.www.WSRecepcionExtSoap getWSRecepcionExtSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSRecepcionExtSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSRecepcionExtSoap(endpoint);
    }

    public mx.com.vitamedica.www.WSRecepcionExtSoap getWSRecepcionExtSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            mx.com.vitamedica.www.WSRecepcionExtSoapStub _stub = new mx.com.vitamedica.www.WSRecepcionExtSoapStub(portAddress, this);
            _stub.setPortName(getWSRecepcionExtSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSRecepcionExtSoapEndpointAddress(java.lang.String address) {
        WSRecepcionExtSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (mx.com.vitamedica.www.WSRecepcionExtSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                mx.com.vitamedica.www.WSRecepcionExtSoapStub _stub = new mx.com.vitamedica.www.WSRecepcionExtSoapStub(new java.net.URL(WSRecepcionExtSoap_address), this);
                _stub.setPortName(getWSRecepcionExtSoapWSDDServiceName());
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
        if ("WSRecepcionExtSoap".equals(inputPortName)) {
            return getWSRecepcionExtSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.vitamedica.com.mx/", "WSRecepcionExt");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.vitamedica.com.mx/", "WSRecepcionExtSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSRecepcionExtSoap".equals(portName)) {
            setWSRecepcionExtSoapEndpointAddress(address);
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
