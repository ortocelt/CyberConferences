package net.etfbl.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.etfbl.bean.NaucniRadBean.KorisnickiRadovi;
import net.etfbl.dao.KorisnikDao;
import net.etfbl.dao.NaucniRadDao;
import net.etfbl.dao.PorukaDao;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.Poruka;

@ManagedBean
@SessionScoped
public class KorisnikBean {

	private boolean loggedIn = false;
	private int loginId;
	private int loginPrivilegija;
	private String loginUsername;
	private String loginPassword;
	private String registerUsername;
	private String registerPassword;
	private String porukaLogin = "";
	private String porukaRegister = "";
	private Korisnik noviKorisnik = new Korisnik();
	private Date tempDate;
	private String kandidatPocetna = "/kandidat/kandidatPocetna.xhtml?faces-redirect=true";
	private String kandidatNoviRad = "/kandidat/kandidatNoviRad.xhtml?faces-redirect=true";
	private String korisnickiRadoviNavigation = "/kandidat/kandidatKorisnickiRadovi.xhtml?faces-redirect=true";
	private ArrayList<KorisnickiRadovi> sviKorisnikoviRadovi = new ArrayList<KorisnickiRadovi>();
	private String dodavanjeKontaktaMsg = "";
	private String slanjePorukeMsg = "";
	private ArrayList<Korisnik> listaKontakata = new ArrayList<Korisnik>();
	private String sadrzajNovePoruke = "";
	private int primalacId;
	private ArrayList<Poruka> korisnikovePoruke = new ArrayList<Poruka>();
	private String brojNeprocitanihPoruka;
	private String imePrezimePoID;
	private Poruka procitanaPoruka;
	private int korisnikPorukaPosiljalacId;
	
	public String slanjeOdgovora() {
		primalacId = korisnikPorukaPosiljalacId;
		return napisiPoruku(primalacId);
	}
	
	public String procitajPoruku(int porukaId, int posiljalacId) {
		setKorisnikPorukaPosiljalacId(posiljalacId);
		imePrezimePoID = KorisnikDao.provjeraImenaIPrezimenaPoIdu(posiljalacId);
		procitanaPorukaAkcija(porukaId);
		return "/kandidat/kandidatSadrzajPoruke.xhtml?faces-redirect=true";
	}
	
	public void procitanaPorukaAkcija(int porukaId) {
		procitanaPoruka = PorukaDao.procitanaPoruka(porukaId);
		if (procitanaPoruka != null) {
			PorukaDao.setProcitana(porukaId);
		}
	}
	
	public String neprocitanePoruke() {
		return "Imate "  + brojNeprocitanihPoruka + " neprocitanih poruka";
	}
	
	public String pregledPoruka() {
		korisnikovePoruke = PorukaDao.korisnikovePoruke(loginId);
		return "/kandidat/kandidatPorukeStranica.xhtml?faces-redirect=true";
	}
	
	public String napisiPoruku(int kontaktId) {
		setPrimalacId(kontaktId);
		return "/kandidat/kandidatPorukaSlanje.xhtml?faces-redirect=true";
	}
	
	public void slanjeNovePoruke() {
		slanjePorukeMsg = KorisnikDao.slanjeNovePoruke(loginId, primalacId, getSadrzajNovePoruke());
		setSadrzajNovePoruke("");
	}
	
	public String kontaktiInit() {
		listaKontakata = KorisnikDao.listaKontakata(loginId);
		return "/kandidat/kandidatListaKontakata.xhtml?faces-redirect=true";
	}
	
	public void dodajUKontakte(int id) {
		dodavanjeKontaktaMsg = KorisnikDao.dodajUKontakte(loginId, id);
		PorukaDao.automatskaPorukaZaDodaniKontakt(loginId,id);
	}	
	
	public String korisnickiRadovi() {
		sviKorisnikoviRadovi = NaucniRadDao.sviKorisnikoviRadovi(loginId);
		return "/kandidat/kandidatKorisnickiRadovi.xhtm?faces-redirect=truel";
	}

	public String login() {
		Korisnik korisnik = new Korisnik();
		korisnik = KorisnikDao.login(loginUsername, loginPassword);
		if (korisnik != null) {
			loggedIn = true;
			loginId = korisnik.getId();
			setLoginPrivilegija(korisnik.getPrivilegija_id());
			if(korisnik.getPrivilegija_id() == 1) {
				return "/organizator/organizatorPocetna.xhtml?faces-redirect=true";
			}
			else if (korisnik.getPrivilegija_id() == 2) {
				return "/recezent/recezentPocetna.xhtml?faces-redirect=true";
			}
			else if (korisnik.getPrivilegija_id() == 3) {
				brojNeprocitanihPoruka = KorisnikDao.neprocitanePoruke(loginId);
				return "/kandidat/kandidatPocetna.xhtml?faces-redirect=true";
			}
		} else {
			porukaLogin = "Neispravan username ili password!";
		}
		return "";
			
	}
	
	public String logout() {
		setLoggedIn(false);
		return "/index.xhtml?faces-redirect=true";
	}
	
	public void registracija() {
		Korisnik k = new Korisnik();
		k.setIme(noviKorisnik.getIme());
		k.setPrezime(noviKorisnik.getPrezime());
		k.setUsername(noviKorisnik.getUsername());
		k.setPassword(noviKorisnik.getPassword());
		k.setBiografija(noviKorisnik.getBiografija());
		k.setEmail(noviKorisnik.getEmail());		
		k.setDatum_rodjenja(new Timestamp(getTempDate().getTime()));
		k.setPrivilegija_id(noviKorisnik.getPrivilegija_id());
		porukaRegister = KorisnikDao.registracija(k);
	}
	
	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getRegisterUsername() {
		return registerUsername;
	}

	public void setRegisterUsername(String registerUsername) {
		this.registerUsername = registerUsername;
	}

	public String getRegisterPassword() {
		return registerPassword;
	}

	public void setRegisterPassword(String registerPassword) {
		this.registerPassword = registerPassword;
	}

	public String getPorukaLogin() {
		return porukaLogin;
	}

	public void setPorukaLogin(String porukaLogin) {
		this.porukaLogin = porukaLogin;
	}

	public Korisnik getNoviKorisnik() {
		return noviKorisnik;
	}

	public void setNoviKorisnik(Korisnik noviKorisnik) {
		this.noviKorisnik = noviKorisnik;
	}

	public Date getTempDate() {
		return tempDate;
	}

	public void setTempDate(Date tempDate) {
		this.tempDate = tempDate;
	}

	public String getPorukaRegister() {
		return porukaRegister;
	}

	public void setPorukaRegister(String porukaRegister) {
		this.porukaRegister = porukaRegister;
	}

	public String getKandidatPocetna() {
		return kandidatPocetna;
	}

	public void setKandidatPocetna(String kandidatPocetna) {
		this.kandidatPocetna = kandidatPocetna;
	}

	public String getKandidatNoviRad() {
		return kandidatNoviRad;
	}

	public void setKandidatNoviRad(String kandidatNoviRad) {
		this.kandidatNoviRad = kandidatNoviRad;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public int getPrimalacId() {
		return primalacId;
	}

	public void setPrimalacId(int primalacId) {
		this.primalacId = primalacId;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public int getLoginPrivilegija() {
		return loginPrivilegija;
	}

	public void setLoginPrivilegija(int loginPrivilegija) {
		this.loginPrivilegija = loginPrivilegija;
	}

	public ArrayList<Korisnik> getListaKontakata() {
		return listaKontakata;
	}


	public void setListaKontakata(ArrayList<Korisnik> listaKontakata) {
		this.listaKontakata = listaKontakata;
	}


	public String getKorisnickiRadoviNavigation() {
		return korisnickiRadoviNavigation;
	}

	public void setKorisnickiRadoviNavigation(String korisnickiRadoviNavigation) {
		this.korisnickiRadoviNavigation = korisnickiRadoviNavigation;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public ArrayList<KorisnickiRadovi> getSviKorisnikoviRadovi() {
		return sviKorisnikoviRadovi;
	}

	public void setSviKorisnikoviRadovi(ArrayList<KorisnickiRadovi> sviKorisnikoviRadovi) {
		this.sviKorisnikoviRadovi = sviKorisnikoviRadovi;
	}


	public String getDodavanjeKontaktaMsg() {
		return dodavanjeKontaktaMsg;
	}


	public void setDodavanjeKontaktaMsg(String dodavanjeKontaktaMsg) {
		this.dodavanjeKontaktaMsg = dodavanjeKontaktaMsg;
	}

	public String getSadrzajNovePoruke() {
		return sadrzajNovePoruke;
	}

	public void setSadrzajNovePoruke(String sadrzajNovePoruke) {
		this.sadrzajNovePoruke = sadrzajNovePoruke;
	}

	public String getSlanjePorukeMsg() {
		return slanjePorukeMsg;
	}

	public void setSlanjePorukeMsg(String slanjePorukeMsg) {
		this.slanjePorukeMsg = slanjePorukeMsg;
	}

	public ArrayList<Poruka> getKorisnikovePoruke() {
		return korisnikovePoruke;
	}

	public void setKorisnikovePoruke(ArrayList<Poruka> korisnikovePoruke) {
		this.korisnikovePoruke = korisnikovePoruke;
	}

	public String getBrojNeprocitanihPoruka() {
		return brojNeprocitanihPoruka;
	}

	public void setBrojNeprocitanihPoruka(String brojNeprocitanihPoruka) {
		this.brojNeprocitanihPoruka = brojNeprocitanihPoruka;
	}

	public String getImePrezimePoID() {
		return imePrezimePoID;
	}

	public void setImePrezimePoID(String imePrezimePoID) {
		this.imePrezimePoID = imePrezimePoID;
	}

	public Poruka getProcitanaPoruka() {
		return procitanaPoruka;
	}

	public void setProcitanaPoruka(Poruka procitanaPoruka) {
		this.procitanaPoruka = procitanaPoruka;
	}

	public int getKorisnikPorukaPosiljalacId() {
		return korisnikPorukaPosiljalacId;
	}

	public void setKorisnikPorukaPosiljalacId(int korisnikPorukaPosiljalacId) {
		this.korisnikPorukaPosiljalacId = korisnikPorukaPosiljalacId;
	}

}
