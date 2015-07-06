package net.etfbl.dto;

public class Kontakti {
	private int id;
	private int korisnik;
	private int kontakt;
	public Kontakti() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Kontakti(int id, int korisnik, int kontakt) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.kontakt = kontakt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(int korisnik) {
		this.korisnik = korisnik;
	}
	public int getKontakt() {
		return kontakt;
	}
	public void setKontakt(int kontakt) {
		this.kontakt = kontakt;
	}
	
	

}
