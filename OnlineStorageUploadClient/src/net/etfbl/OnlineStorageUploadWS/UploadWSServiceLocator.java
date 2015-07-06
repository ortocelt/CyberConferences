/**
 * UploadWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.etfbl.OnlineStorageUploadWS;

public class UploadWSServiceLocator extends org.apache.axis.client.Service implements net.etfbl.OnlineStorageUploadWS.UploadWSService {

    public UploadWSServiceLocator() {
    }


    public UploadWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UploadWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UploadWS
    private java.lang.String UploadWS_address = "http://localhost:8085/OnlineStorageUpload/services/UploadWS";

    public java.lang.String getUploadWSAddress() {
        return UploadWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UploadWSWSDDServiceName = "UploadWS";

    public java.lang.String getUploadWSWSDDServiceName() {
        return UploadWSWSDDServiceName;
    }

    public void setUploadWSWSDDServiceName(java.lang.String name) {
        UploadWSWSDDServiceName = name;
    }

    public net.etfbl.OnlineStorageUploadWS.UploadWS getUploadWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UploadWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUploadWS(endpoint);
    }

    public net.etfbl.OnlineStorageUploadWS.UploadWS getUploadWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            net.etfbl.OnlineStorageUploadWS.UploadWSSoapBindingStub _stub = new net.etfbl.OnlineStorageUploadWS.UploadWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getUploadWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUploadWSEndpointAddress(java.lang.String address) {
        UploadWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (net.etfbl.OnlineStorageUploadWS.UploadWS.class.isAssignableFrom(serviceEndpointInterface)) {
                net.etfbl.OnlineStorageUploadWS.UploadWSSoapBindingStub _stub = new net.etfbl.OnlineStorageUploadWS.UploadWSSoapBindingStub(new java.net.URL(UploadWS_address), this);
                _stub.setPortName(getUploadWSWSDDServiceName());
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
        if ("UploadWS".equals(inputPortName)) {
            return getUploadWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://OnlineStorageUploadWS.etfbl.net", "UploadWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://OnlineStorageUploadWS.etfbl.net", "UploadWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UploadWS".equals(portName)) {
            setUploadWSEndpointAddress(address);
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
