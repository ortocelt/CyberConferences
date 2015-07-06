package net.etfbl.OnlineStorageUploadWS;

public class UploadWSProxy implements net.etfbl.OnlineStorageUploadWS.UploadWS {
  private String _endpoint = null;
  private net.etfbl.OnlineStorageUploadWS.UploadWS uploadWS = null;
  
  public UploadWSProxy() {
    _initUploadWSProxy();
  }
  
  public UploadWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initUploadWSProxy();
  }
  
  private void _initUploadWSProxy() {
    try {
      uploadWS = (new net.etfbl.OnlineStorageUploadWS.UploadWSServiceLocator()).getUploadWS();
      if (uploadWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)uploadWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)uploadWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (uploadWS != null)
      ((javax.xml.rpc.Stub)uploadWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public net.etfbl.OnlineStorageUploadWS.UploadWS getUploadWS() {
    if (uploadWS == null)
      _initUploadWSProxy();
    return uploadWS;
  }
  
  public java.lang.String upload(byte[] uploadedFile) throws java.rmi.RemoteException{
    if (uploadWS == null)
      _initUploadWSProxy();
    return uploadWS.upload(uploadedFile);
  }
  
  
}