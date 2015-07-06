package net.etfbl.dto;

public class NaucniRad {
	private int id;
	private int konferencija_id;
	private String abstrakt;
	private String kljucne_rijeci;
	private boolean odobren;
	private String naziv;
	private boolean upload;

	public NaucniRad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NaucniRad(int id, int konferencija_id, String abstrakt,
			String kljucne_rijeci, boolean odobren) {
		super();
		this.id = id;
		this.konferencija_id = konferencija_id;
		this.abstrakt = abstrakt;
		this.kljucne_rijeci = kljucne_rijeci;
		this.odobren = odobren;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbstrakt() {
		return abstrakt;
	}

	public void setAbstrakt(String abstrakt) {
		this.abstrakt = abstrakt;
	}

	public String getKljucne_rijeci() {
		return kljucne_rijeci;
	}

	public void setKljucne_rijeci(String kljucne_rijeci) {
		this.kljucne_rijeci = kljucne_rijeci;
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}

	public int getKonferencija_id() {
		return konferencija_id;
	}

	public void setKonferencija_id(int konferencija_id) {
		this.konferencija_id = konferencija_id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public boolean isUpload() {
		return upload;
	}

	public void setUpload(boolean upload) {
		this.upload = upload;
	}

}
