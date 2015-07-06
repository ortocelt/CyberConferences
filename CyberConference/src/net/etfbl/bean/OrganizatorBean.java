package net.etfbl.bean;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.etfbl.dao.KonferencijaDao;
import net.etfbl.dao.KorisnikDao;
import net.etfbl.dto.Konferencija;
import net.etfbl.dto.Korisnik;

@ManagedBean
@SessionScoped
public class OrganizatorBean {

	private Konferencija novaKonferencija;
	private ArrayList<Korisnik> sviRecenzenti = new ArrayList<Korisnik>();
	private String unosNoveKonferencijeMsg = "";
	private ArrayList<String> noviRecezenti = new ArrayList<String>();
	private Date tempPocetak;
	private Date tempKraj;
	private Date tempRok;
	
	@PostConstruct
	public void init() {
		sviRecenzenti = KorisnikDao.selectSviRecenzenti();
	}
	
	public String getUnosNoveKonferencijeMsg() {
		return unosNoveKonferencijeMsg;
	}

	public void setUnosNoveKonferencijeMsg(String unosNoveKonferencijeMsg) {
		this.unosNoveKonferencijeMsg = unosNoveKonferencijeMsg;
	}

	public ArrayList<String> getNoviRecezenti() {
		return noviRecezenti;
	}

	public void setNoviRecezenti(ArrayList<String> noviRecezenti) {
		this.noviRecezenti = noviRecezenti;
	}

	public Date getTempPocetak() {
		return tempPocetak;
	}

	public void setTempPocetak(Date tempPocetak) {
		this.tempPocetak = tempPocetak;
	}

	public Date getTempKraj() {
		return tempKraj;
	}

	public void setTempKraj(Date tempKraj) {
		this.tempKraj = tempKraj;
	}

	public Date getTempRok() {
		return tempRok;
	}

	public void setTempRok(Date tempRok) {
		this.tempRok = tempRok;
	}

	public void unosNoveKonferencije() {
		unosNoveKonferencijeMsg = KonferencijaDao.unosNoveKonferencije(
				novaKonferencija, noviRecezenti);
	}

	public ArrayList<Korisnik> getSviRecenzenti() {
		return sviRecenzenti;
	}

	public void setSviRecenzenti(ArrayList<Korisnik> sviRecenzenti) {
		this.sviRecenzenti = sviRecenzenti;
	}

	public Konferencija getNovaKonferencija() {
		return novaKonferencija;
	}

	public void setNovaKonferencija(Konferencija novaKonferencija) {
		this.novaKonferencija = novaKonferencija;
	}

	public String navigationDodavanjeKonferencije() {
		return "/organizator/organizatorDodavanjeKonferencije.xhtml?faces-redirect=true";
	}

	public String navigationIzmjenaKonferencije() {
		return "/organizator/organizatorPregledKonferencija.xhtml?faces-redirect=true";
	}

	public String navigationIzvjestajStranica() {
		return "organizator/organizatorIzvjestaj.xhtml";
	}

	public String navigationOrganizatorPocetna() {
		return "/organziator/organizatorPocetna.xhtml";
	}

}
