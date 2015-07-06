/**
 * KonferencijeWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.etfbl.KonferencijeWS;

public class KonferencijeWSServiceLocator extends org.apache.axis.client.Service implements net.etfbl.KonferencijeWS.KonferencijeWSService {

    public KonferencijeWSServiceLocator() {
    }


    public KonferencijeWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public KonferencijeWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for KonferencijeWS
    private java.lang.String KonferencijeWS_address = "http://localhost:8085/CyberConferenceKonfWS/services/KonferencijeWS";

    public java.lang.String getKonferencijeWSAddress() {
        return KonferencijeWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String KonferencijeWSWSDDServiceName = "KonferencijeWS";

    public java.lang.String getKonferencijeWSWSDDServiceName() {
        return KonferencijeWSWSDDServiceName;
    }

    public void setKonferencijeWSWSDDServiceName(java.lang.String name) {
        KonferencijeWSWSDDServiceName = name;
    }

    public net.etfbl.KonferencijeWS.KonferencijeWS getKonferencijeWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(KonferencijeWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getKonferencijeWS(endpoint);
    }

    public net.etfbl.KonferencijeWS.KonferencijeWS getKonferencijeWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            net.etfbl.KonferencijeWS.KonferencijeWSSoapBindingStub _stub = new net.etfbl.KonferencijeWS.KonferencijeWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getKonferencijeWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setKonferencijeWSEndpointAddress(java.lang.String address) {
        KonferencijeWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (net.etfbl.KonferencijeWS.KonferencijeWS.class.isAssignableFrom(serviceEndpointInterface)) {
                net.etfbl.KonferencijeWS.KonferencijeWSSoapBindingStub _stub = new net.etfbl.KonferencijeWS.KonferencijeWSSoapBindingStub(new java.net.URL(KonferencijeWS_address), this);
                _stub.setPortName(getKonferencijeWSWSDDServiceName());
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
        if ("KonferencijeWS".equals(inputPortName)) {
            return getKonferencijeWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://KonferencijeWS.etfbl.net", "KonferencijeWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://KonferencijeWS.etfbl.net", "KonferencijeWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("KonferencijeWS".equals(portName)) {
            setKonferencijeWSEndpointAddress(address);
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
