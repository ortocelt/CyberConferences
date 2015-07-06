package net.etfbl.OnlineStorageWS;

public class OnlineStorageWSProxy implements net.etfbl.OnlineStorageWS.OnlineStorageWS {
  private String _endpoint = null;
  private net.etfbl.OnlineStorageWS.OnlineStorageWS onlineStorageWS = null;
  
  public OnlineStorageWSProxy() {
    _initOnlineStorageWSProxy();
  }
  
  public OnlineStorageWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initOnlineStorageWSProxy();
  }
  
  private void _initOnlineStorageWSProxy() {
    try {
      onlineStorageWS = (new net.etfbl.OnlineStorageWS.OnlineStorageWSServiceLocator()).getOnlineStorageWS();
      if (onlineStorageWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)onlineStorageWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)onlineStorageWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (onlineStorageWS != null)
      ((javax.xml.rpc.Stub)onlineStorageWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public net.etfbl.OnlineStorageWS.OnlineStorageWS getOnlineStorageWS() {
    if (onlineStorageWS == null)
      _initOnlineStorageWSProxy();
    return onlineStorageWS;
  }
  
  public byte[] downloadFile(java.lang.String fileName) throws java.rmi.RemoteException{
    if (onlineStorageWS == null)
      _initOnlineStorageWSProxy();
    return onlineStorageWS.downloadFile(fileName);
  }
  
  
}