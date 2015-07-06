package net.etfbl.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class Konferencija implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String naziv;
	private Timestamp pocetakKonferencije;
	private Timestamp krajKonferencije;
	private Timestamp rokZaPrijavu;
	private String mjesto;

	public String getMjesto() {
		return mjesto;
	}

	public void setMjesto(String mjesto) {
		this.mjesto = mjesto;
	}

	public Konferencija() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Konferencija(int id, String naziv, Timestamp pocetakKonferencije,
			Timestamp krajKonferencije, Timestamp rokZaPrijavu, String mjesto) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.pocetakKonferencije = pocetakKonferencije;
		this.krajKonferencije = krajKonferencije;
		this.rokZaPrijavu = rokZaPrijavu;
		this.mjesto = mjesto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Timestamp getPocetakKonferencije() {
		return pocetakKonferencije;
	}

	public void setPocetakKonferencije(Timestamp pocetakKonferencije) {
		this.pocetakKonferencije = pocetakKonferencije;
	}

	public Timestamp getKrajKonferencije() {
		return krajKonferencije;
	}

	public void setKrajKonferencije(Timestamp krajKonferencije) {
		this.krajKonferencije = krajKonferencije;
	}

	public Timestamp getRokZaPrijavu() {
		return rokZaPrijavu;
	}

	public void setRokZaPrijavu(Timestamp rokZaPrijavu) {
		this.rokZaPrijavu = rokZaPrijavu;
	}

}
