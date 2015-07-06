package net.etfbl.OnlineStorageUploadWS;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

@MTOM
@WebService(endpointInterface = "net.etfbl.OnlineStorageUpload.Upload")
public class UploadWS implements Upload {

	@Override
	public String upload(byte[] uploadedFile) {
		try {
			// byte[] in = service.downloadFile("projektni.pdf");
			InputStream is = new ByteArrayInputStream(uploadedFile);
			/* Location for downloading and storing in client’s machine */
			DataHandler dataHandler = new DataHandler(
					new InputStreamDataSource(is));
			FileOutputStream outputStream = new FileOutputStream(
					"C:/uploaded/test.pdf");
			dataHandler.writeTo(outputStream);
			outputStream.flush();
			outputStream.close();
			is.close();
			return "Fajl je uspjesno uploadovan";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Doslo je do greske";
	}

	public class InputStreamDataSource implements DataSource {
		private InputStream inputStream;

		public InputStreamDataSource(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		@Override
		public InputStream getInputStream() throws IOException {
			return inputStream;
		}

		@Override
		public OutputStream getOutputStream() throws IOException {
			throw new UnsupportedOperationException("Not implemented");
		}

		@Override
		public String getContentType() {
			return "*/*";
		}

		@Override
		public String getName() {
			return "InputStreamDataSource";
		}
	}
}
