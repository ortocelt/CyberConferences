package net.etfbl.OnlineStorageWS;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

//Service Implementation Bean

@MTOM
@WebService(endpointInterface = "net.etfbl.OnlineStorageDownload.OnlineStorage")
public class OnlineStorageWS implements OnlineStorage {

	@Override
	public byte[] downloadFile(String fileName) {

		// Location of File in Web service

		FileDataSource dataSource = new FileDataSource("c:/test/" + fileName);
		DataHandler fileDataHandler = new DataHandler(dataSource);
		byte[] byteArray = toBytes(fileDataHandler);
		return byteArray;
	}
	
	private static byte[] toBytes(DataHandler handler)  {
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    try {
			handler.writeTo(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return output.toByteArray();
	}
}