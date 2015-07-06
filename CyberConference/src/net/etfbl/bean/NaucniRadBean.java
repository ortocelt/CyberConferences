package net.etfbl.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import javax.xml.rpc.ServiceException;

import org.apache.axis.utils.IOUtils;

import net.etfbl.OnlineStorageUploadWS.UploadWS;
import net.etfbl.OnlineStorageUploadWS.UploadWSServiceLocator;
import net.etfbl.OnlineStorageWS.OnlineStorageWS;
import net.etfbl.OnlineStorageWS.OnlineStorageWSServiceLocator;
import net.etfbl.dao.KorisnikDao;
import net.etfbl.dao.NaucniRadDao;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.NaucniRad;

@ManagedBean
@SessionScoped
public class NaucniRadBean {

	private ArrayList<KorisnickiRadovi> korisnickiRadovi;
	private NaucniRad noviNaucniRad = new NaucniRad();
	private ArrayList<String> noviAutori = new ArrayList<String>();
	private ArrayList<String> izmjenaAutora = new ArrayList<String>();
	private ArrayList<Korisnik> sviKandidati = new ArrayList<Korisnik>();
	private String unosNovogRadaMsg = "";
	private String izmjenaRadaMsg = "";
	private NaucniRad izmjenjenRad;
	private Part file;

	public class KorisnickiRadovi {
		private int id;
		private String ime;
		private String prezime;
		private String naziv;
		private String abstrakt;
		private String konferencija;

		// public KorisnickiRadovi() {
		// System.out.println("Konstrukcija");
		// sviKandidati = KorisnikDao.sviKandidati();
		// }
		// @PostConstruct
		// public void init() {
		// System.out.println("Usao u post construct");
		// punjenjeKandidata();
		// System.out.println("Izlazi iz postconstructa");
		// }

		public void punjenjeKandidata() {
			sviKandidati = KorisnikDao.sviKandidati();
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getIme() {
			return ime;
		}

		public void setIme(String ime) {
			this.ime = ime;
		}

		public String getPrezime() {
			return prezime;
		}

		public void setPrezime(String prezime) {
			this.prezime = prezime;
		}

		public String getNaziv() {
			return naziv;
		}

		public void setNaziv(String naziv) {
			this.naziv = naziv;
		}

		public String getAbstrakt() {
			return abstrakt;
		}

		public void setAbstrakt(String abstrakt) {
			this.abstrakt = abstrakt;
		}

		public String getKonferencija() {
			return konferencija;
		}

		public void setKonferencija(String konferencija) {
			this.konferencija = konferencija;
		}

	}

	public void unosIzmjeneRada() {
		izmjenaRadaMsg = NaucniRadDao.unosIzmjeneRada(izmjenjenRad,
				izmjenaAutora);
	}

	public void unosNovogRada() {
		unosNovogRadaMsg = NaucniRadDao
				.unosNovogRada(noviNaucniRad, noviAutori);
	}

	public ArrayList<KorisnickiRadovi> getKorisnickiRadovi() {
		return korisnickiRadovi;
	}

	public void setKorisnickiRadovi(ArrayList<KorisnickiRadovi> korisnickiRadovi) {
		this.korisnickiRadovi = korisnickiRadovi;
	}

	public NaucniRad getNoviNaucniRad() {
		return noviNaucniRad;
	}

	public void setNoviNaucniRad(NaucniRad noviNaucniRad) {
		this.noviNaucniRad = noviNaucniRad;
	}

	public ArrayList<String> getNoviAutori() {
		return noviAutori;
	}

	public void setNoviAutori(ArrayList<String> noviAutori) {
		this.noviAutori = noviAutori;
	}

	public ArrayList<Korisnik> getSviKandidati() {
		return sviKandidati;
	}

	public void setSviKandidati(ArrayList<Korisnik> svikandidati) {
		this.sviKandidati = svikandidati;
	}

	public String getUnosNovogRadaMsg() {
		return unosNovogRadaMsg;
	}

	public void setUnosNovogRadaMsg(String unosNovogRadaMsg) {
		this.unosNovogRadaMsg = unosNovogRadaMsg;
	}

	public String izmjenaRada(int radId) {
		izmjenjenRad = NaucniRadDao.izmjenaRada(radId);
		
		return "/kandidat/kandidatIzmjenaRada.xhtml";
	}

	public NaucniRad getIzmjenjenRad() {
		return izmjenjenRad;
	}

	public void setIzmjenjenRad(NaucniRad izmjenjenRad) {
		this.izmjenjenRad = izmjenjenRad;
	}

	public ArrayList<String> getIzmjenaAutora() {
		return izmjenaAutora;
	}

	public void setIzmjenaAutora(ArrayList<String> izmjenaAutora) {
		this.izmjenaAutora = izmjenaAutora;
	}

	public String getIzmjenaRadaMsg() {
		return izmjenaRadaMsg;
	}

	public void setIzmjenaRadaMsg(String izmjenaRadaMsg) {
		this.izmjenaRadaMsg = izmjenaRadaMsg;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
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

	public static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(b);
		o.writeObject(obj);
		return b.toByteArray();
	}
	
	public void upload() {
		try {
			InputStream is = file.getInputStream();
//			byte[] fileForUpload = NaucniRadBean.serialize(is);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int nRead;
			byte[] data = new byte[16384];

			while ((nRead = is.read(data, 0, data.length)) != -1) {
			  buffer.write(data, 0, nRead);
			}

			buffer.flush();

			byte[] fileForUpload = buffer.toByteArray();
//			byte[] fileForUpload = IOUtils.toByteArray(is);
		
			UploadWSServiceLocator locator = new UploadWSServiceLocator();
			UploadWS service = locator.getUploadWS();
			service.upload(fileForUpload);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void download() {
		try {
			OnlineStorageWSServiceLocator locator = new OnlineStorageWSServiceLocator();
			OnlineStorageWS service = locator.getOnlineStorageWS();

			// URL url = new URL("http://localhost:9899/ws/file?wsdl");
			// QName qname = new QName("http://ws.download.ibm.com/",
			// "FileServerImplService");
			// Service service = Service.create(url, qname);
			// FileServer fileServer = service.getPort(FileServer.class);
			// DataHandler dh = service.downloadFile("projektni.pdf");
			byte[] in = service.downloadFile("projektni.pdf");
			InputStream is = new ByteArrayInputStream(in);
			/* Location for downloading and storing in client’s machine */
			DataHandler dataHandler = new DataHandler(
					new InputStreamDataSource(is));
			FileOutputStream outputStream = new FileOutputStream("C:/test.pdf");
			dataHandler.writeTo(outputStream);
			outputStream.flush();
			outputStream.close();
			is.close();
			System.out.println(" Download Successful!");

		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
