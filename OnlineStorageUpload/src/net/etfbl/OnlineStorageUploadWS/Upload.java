package net.etfbl.OnlineStorageUploadWS;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
 
//Service Endpoint Interface

@WebService
@SOAPBinding(style = Style.RPC)
public interface Upload {
 
	//download a File from server

	@WebMethod 
	public String upload(byte[] uploadedFile);
 
	
}