package net.etfbl.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.etfbl.dao.KonferencijaDao;
import net.etfbl.dao.KorisnikDao;
import net.etfbl.dto.Konferencija;
import net.etfbl.dto.Korisnik;

@ManagedBean
@SessionScoped
public class KonferencijaBean {

	private List<Konferencija> aktivneKonferencije = new ArrayList<Konferencija>();
	private ArrayList<Korisnik> sviKandidati = new ArrayList<Korisnik>();
	private int konferencijaId;

	public List<Konferencija> getAktivneKonferencije() {
		return aktivneKonferencije;
	}

	public void setAktivneKonferencije(List<Konferencija> aktivneKonferencije) {
		this.aktivneKonferencije = aktivneKonferencije;
	}
	
	public void sveAktivneKonferencije() {
		aktivneKonferencije = KonferencijaDao.aktivneKonferencijeZaPrijavu();
	}

	public int getKonferencijaId() {
		return konferencijaId;
	}

	public void setKonferencijaId(int konferencijaId) {
		this.konferencijaId = konferencijaId;
	}
	
	public ArrayList<Korisnik> getSviKandidati() {
		return sviKandidati;
	}

	public void setSviKandidati(ArrayList<Korisnik> sviKandidati) {
		this.sviKandidati = sviKandidati;
	}
	
	public void punjenjeKandidata() {
		sviKandidati = KorisnikDao.sviKandidati();
	}

	@PostConstruct
	public void init() {
		sveAktivneKonferencije();
		punjenjeKandidata();
		
	}

}
