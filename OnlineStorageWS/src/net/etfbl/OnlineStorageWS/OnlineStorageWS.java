package net.etfbl.OnlineStorageWS;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

//Service Implementation Bean

@MTOM
@WebService(endpointInterface = "net.etfbl.OnlineStorageWS.OnlineStorage")
public class OnlineStorageWS implements OnlineStorage {

	@Override
	public DataHandler downloadFile(String fileName) {

		// Location of File in Web service

		FileDataSource dataSource = new FileDataSource("c:/test/" + fileName);
		DataHandler fileDataHandler = new DataHandler(dataSource);
		return fileDataHandler;
	}
}