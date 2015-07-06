package net.etfbl.dto;

import java.sql.Timestamp;

public class Poruka {

	public Poruka() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int id;
	private int salje;
	private int prima;
	private String tekst;
	private Timestamp datum;
	private boolean procitana;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalje() {
		return salje;
	}

	public void setSalje(int salje) {
		this.salje = salje;
	}

	public int getPrima() {
		return prima;
	}

	public void setPrima(int prima) {
		this.prima = prima;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Timestamp getDatum() {
		return datum;
	}

	public void setDatum(Timestamp datum) {
		this.datum = datum;
	}

	public boolean isProcitana() {
		return procitana;
	}

	public void setProcitana(boolean procitana) {
		this.procitana = procitana;
	}

}
