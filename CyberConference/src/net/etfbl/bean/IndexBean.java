package net.etfbl.bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.rpc.ServiceException;

import net.etfbl.KonferencijeWS.KonferencijeWS;
import net.etfbl.KonferencijeWS.KonferencijeWSServiceLocator;
import net.etfbl.bean.NaucniRadBean.KorisnickiRadovi;
import net.etfbl.dao.KonferencijaDao;
import net.etfbl.dao.KorisnikDao;
import net.etfbl.dao.NaucniRadDao;
import net.etfbl.dto.Konferencija;
import net.etfbl.dto.Korisnik;
import net.etfbl.rss.Feed;
import net.etfbl.rss.FeedMessage;
import net.etfbl.rss.RssFeedParser;

@ManagedBean
@ViewScoped
public class IndexBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<FeedMessage> rssPoruke;
	private List<Konferencija> aktivneKonferencije;
	private String aktuelneKonferencijeService;
	private String tipPretrage;
	private String unosPretrage;
	private ArrayList<KorisnickiRadovi> rezultatPretrage;
	private boolean imaRezulat = true;
	private String pretragaAutoraIme;
	private String pretragaAutoraPrezime;
	private ArrayList<Korisnik> rezultatPretrageAutora;

	public List<Konferencija> getAktivneKonferencije() {
		return aktivneKonferencije;
	}

	public void setAktivneKonferencije(List<Konferencija> aktivneKonferencije) {
		this.aktivneKonferencije = aktivneKonferencije;
	}

	/**
	 * INIT pjesmu na page load
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@PostConstruct
	public void init() {
		try {
			aktivneKonferencije = KonferencijaDao
					.aktivneKonferencijeZaPrijavu();
			try {
				KonferencijeWSServiceLocator locator = new KonferencijeWSServiceLocator();
				KonferencijeWS service = locator.getKonferencijeWS();
				aktuelneKonferencijeService = service
						.konferencijeZaTekuciMjesec();
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RssFeedParser parser = new RssFeedParser(
					"http://aceee.org/conferences-events/feed");
			Feed feed = parser.readFeed();
			rssPoruke = feed.getMessages();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void pretraga() {
		System.out.println(unosPretrage);
		System.out.println(tipPretrage);
		setRezultatPretrage(NaucniRadDao.pretragaRadova(unosPretrage,
				tipPretrage));
		if (rezultatPretrage.isEmpty()) {
			imaRezulat = false;
		} else {
			imaRezulat = true;
		}
	}
	
	public void pretragaAutora() {
		rezultatPretrageAutora = KorisnikDao.pretragaAutoraPoImenuIPrezimenu(pretragaAutoraIme, pretragaAutoraPrezime);
	}

	public String getAktuelneKonferencijeService() {
		return aktuelneKonferencijeService;
	}

	public void setAktuelneKonferencijeService(
			String aktuelneKonferencijeService) {
		this.aktuelneKonferencijeService = aktuelneKonferencijeService;
	}

	public String getTipPretrage() {
		return tipPretrage;
	}

	public void setTipPretrage(String tipPretrage) {
		this.tipPretrage = tipPretrage;
	}

	public String getUnosPretrage() {
		return unosPretrage;
	}

	public void setUnosPretrage(String unosPretrage) {
		this.unosPretrage = unosPretrage;
	}

	public List<FeedMessage> getRssPoruke() {
		return rssPoruke;
	}

	public void setRssPoruke(List<FeedMessage> rssPoruke) {
		this.rssPoruke = rssPoruke;
	}

	public ArrayList<KorisnickiRadovi> getRezultatPretrage() {
		return rezultatPretrage;
	}

	public void setRezultatPretrage(ArrayList<KorisnickiRadovi> korisnickiRadovi) {
		this.rezultatPretrage = korisnickiRadovi;
	}

	public boolean isImaRezulat() {
		return imaRezulat;
	}

	public void setImaRezulat(boolean imaRezulat) {
		this.imaRezulat = imaRezulat;
	}
	
	public String getLoginPath() {
		return "/gost/login.xhtml?faces-redirect=true";
	}
	
	public String getRegisterPath() {
		return "/gost/register.xhtml?faces-redirect=true";
	}

	public String getPretragaAutoraIme() {
		return pretragaAutoraIme;
	}

	public void setPretragaAutoraIme(String pretragaAutoraIme) {
		this.pretragaAutoraIme = pretragaAutoraIme;
	}

	public String getPretragaAutoraPrezime() {
		return pretragaAutoraPrezime;
	}

	public void setPretragaAutoraPrezime(String pretragaAutoraPrezime) {
		this.pretragaAutoraPrezime = pretragaAutoraPrezime;
	}

	public ArrayList<Korisnik> getRezultatPretrageAutora() {
		return rezultatPretrageAutora;
	}

	public void setRezultatPretrageAutora(ArrayList<Korisnik> rezultatPretrageAutora) {
		this.rezultatPretrageAutora = rezultatPretrageAutora;
	}
}
