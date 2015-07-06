package net.etfbl.dto;

import java.sql.Timestamp;

public class Korisnik {

	private int id;
	private String ime;
	private String prezime;
	private String username;
	private String password;
	private String email;
	private int privilegija_id;
	private Timestamp datum_rodjenja;
	private String biografija;

	public Korisnik(int id, String ime, String prezime, String username,
			String password, String email, int privilegija_id,
			Timestamp datum_rodjenja, String biografija) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
		this.email = email;
		this.privilegija_id = privilegija_id;
		this.datum_rodjenja = datum_rodjenja;
		this.biografija = biografija;
	}

	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPrivilegija_id() {
		return privilegija_id;
	}

	public void setPrivilegija_id(int privilegija_id) {
		this.privilegija_id = privilegija_id;
	}

	public Timestamp getDatum_rodjenja() {
		return datum_rodjenja;
	}

	public void setDatum_rodjenja(Timestamp datum_rodjenja) {
		this.datum_rodjenja = datum_rodjenja;
	}

	public String getBiografija() {
		return biografija;
	}

	public void setBiografija(String biografija) {
		this.biografija = biografija;
	}
	
	public String imePrezimeToString() {
		return getIme() + " " + getPrezime();
	}
	
	

}
