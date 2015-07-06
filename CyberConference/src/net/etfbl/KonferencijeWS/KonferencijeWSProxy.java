package net.etfbl.KonferencijeWS;

public class KonferencijeWSProxy implements net.etfbl.KonferencijeWS.KonferencijeWS {
  private String _endpoint = null;
  private net.etfbl.KonferencijeWS.KonferencijeWS konferencijeWS = null;
  
  public KonferencijeWSProxy() {
    _initKonferencijeWSProxy();
  }
  
  public KonferencijeWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initKonferencijeWSProxy();
  }
  
  private void _initKonferencijeWSProxy() {
    try {
      konferencijeWS = (new net.etfbl.KonferencijeWS.KonferencijeWSServiceLocator()).getKonferencijeWS();
      if (konferencijeWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)konferencijeWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)konferencijeWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (konferencijeWS != null)
      ((javax.xml.rpc.Stub)konferencijeWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public net.etfbl.KonferencijeWS.KonferencijeWS getKonferencijeWS() {
    if (konferencijeWS == null)
      _initKonferencijeWSProxy();
    return konferencijeWS;
  }
  
  public java.lang.String konferencijeZaTekuciMjesec() throws java.rmi.RemoteException{
    if (konferencijeWS == null)
      _initKonferencijeWSProxy();
    return konferencijeWS.konferencijeZaTekuciMjesec();
  }
  
  
}