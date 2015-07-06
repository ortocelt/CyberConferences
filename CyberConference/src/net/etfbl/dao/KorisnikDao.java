package net.etfbl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.bean.NaucniRadBean;
import net.etfbl.bean.NaucniRadBean.KorisnickiRadovi;
import net.etfbl.dto.Korisnik;

public class KorisnikDao {

	private static String login = "select * from korisnik where username = ? and password = md5(?)";
	private static String registracija = "insert into korisnik (privilegija_id,ime,prezime,username,password,email,biografija,datum_rodjenja) values(?,?,?,?,md5(?),?,?,?)";
	private static String provjeraKorisnikaUsername = "select * from korisnik where username = ?";
	private static String sviKandidati = "select * from korisnik where privilegija_id = 3";
	private static String pretragaAutoraPoImenuIPrezimenu = "select * from korisnik where ime = ? or prezime = ?";
	private static String dodavanjeKontakta = "insert into kontakti (korisnik, kontakt) values (?,?)";
	private static String provjeraListeKontakata = "select * from kontakti where korisnik = ? and kontakt = ?";
	private static String listaKontakata = "select korisnik.id, korisnik.ime, korisnik.prezime from cyconf.kontakti join cyconf.korisnik on korisnik.id = kontakti.kontakt where cyconf.kontakti.korisnik = ?";
	private static String slanjePoruke = "insert into cyconf.poruka (salje, prima, tekst, datum, procitana) values (?,?,?,now(),0)";
	private static String brojNeprocitanihPoruka = "select count(prima) as broj from poruka where prima = ? and procitana = 0";
	private static String imeIPrezimePoId = "select ime, prezime from korisnik where id = ?";
	private static String selectKorisnikById = "select * from korisnik where id = ?";
	private static String selectSviRecenzenti = "select * from korisnik where privilegija_id = 2";

	public KorisnikDao() {
		super();
	}

	public static Korisnik selectKorisnikById(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st = conn.prepareStatement(selectKorisnikById);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Korisnik k = new Korisnik();
				k.setId(rs.getInt("id"));
				k.setIme(rs.getString("ime"));
				k.setPrezime(rs.getString("prezime"));
				return k;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static boolean provjeraListeKontakata(int korisnik, int kontakt) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st = conn
					.prepareStatement(provjeraListeKontakata);
			st.setInt(1, korisnik);
			st.setInt(2, kontakt);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<Korisnik> sviKandidati() {
		ArrayList<Korisnik> korisnikSvi = new ArrayList<Korisnik>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st = conn.prepareStatement(sviKandidati);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Korisnik korisnik = new Korisnik();
				korisnik.setId(rs.getInt("id"));
				korisnik.setIme(rs.getString("ime"));
				korisnik.setPrezime(rs.getString("prezime"));
				korisnik.setUsername(rs.getString("username"));
				korisnik.setBiografija(rs.getString("biografija"));
				korisnik.setDatum_rodjenja(rs.getTimestamp("datum_rodjenja"));
				korisnik.setPrivilegija_id(3);
				korisnikSvi.add(korisnik);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return korisnikSvi;
	}

	public static Korisnik login(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st = conn.prepareStatement(login);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Korisnik korisnik = new Korisnik();
				korisnik.setId(rs.getInt("id"));
				korisnik.setIme(rs.getString("ime"));
				korisnik.setPrezime(rs.getString("prezime"));
				korisnik.setUsername(rs.getString("username"));
				korisnik.setBiografija(rs.getString("biografija"));
				korisnik.setDatum_rodjenja(rs.getTimestamp("datum_rodjenja"));
				korisnik.setPrivilegija_id(rs.getInt("privilegija_id"));
				return korisnik;
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<KorisnickiRadovi> pretragaRadova(
			String unosPretrage, String tipPretrage) {
		ArrayList<KorisnickiRadovi> korisnickiRadovi = new ArrayList<KorisnickiRadovi>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st = null;
			if (tipPretrage.equals("autor")) {
				st = conn
						.prepareStatement("select "
								+ "cyconf.korisnik.ime,"
								+ "cyconf.korisnik.prezime, "
								+ "cyconf.naucni_rad.naziv, "
								+ "cyconf.naucni_rad.abstrakt, "
								+ "cyconf.naucni_rad.id "
								+ "from cyconf.naucni_rad "
								+ "left join cyconf.korisnicki_radovi "
								+ "inner join cyconf.korisnik "
								+ "on cyconf.korisnicki_radovi.korisnik = cyconf.korisnik.id "
								+ "on cyconf.naucni_rad.id = cyconf.korisnicki_radovi.naucni_rad "
								+ "where cyconf.korisnik.ime like '%"
								+ unosPretrage + "%'"
								+ "or cyconf.korisnik.prezime like '%"
								+ unosPretrage + "%'");
			} else {
				st = conn
						.prepareStatement("select cyconf.korisnik.ime,"
								+ "cyconf.korisnik.prezime, "
								+ "cyconf.naucni_rad.naziv, "
								+ "cyconf.naucni_rad.abstrakt, "
								+ "cyconf.naucni_rad.id "
								+ "from cyconf.naucni_rad "
								+ "left join cyconf.korisnicki_radovi "
								+ "inner join cyconf.korisnik "
								+ "on cyconf.korisnicki_radovi.korisnik = cyconf.korisnik.id "
								+ "on cyconf.naucni_rad.id = cyconf.korisnicki_radovi.naucni_rad "
								+ "where cyconf.naucni_rad.kljucne_rijeci like '%"
								+ unosPretrage + "%'");
			}
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				NaucniRadBean naucniRad = new NaucniRadBean();
				KorisnickiRadovi radovi = naucniRad.new KorisnickiRadovi();
				radovi.setId(rs.getInt("id"));
				radovi.setIme(rs.getString("ime"));
				radovi.setPrezime(rs.getString("prezime"));
				radovi.setNaziv(rs.getString("naziv"));
				radovi.setAbstrakt(rs.getString("abstrakt"));
				korisnickiRadovi.add(radovi);

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return korisnickiRadovi;

	}

	public static String registracija(Korisnik k) {
		if (provjeraKorisnikUsername(k.getUsername())) {

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/cyconf", "root", "root");
				PreparedStatement st = conn.prepareStatement(registracija);
				st.setInt(1, k.getPrivilegija_id());
				st.setString(2, k.getIme());
				st.setString(3, k.getPrezime());
				st.setString(4, k.getUsername());
				st.setString(5, k.getPassword());
				st.setString(6, k.getEmail());
				st.setString(7, k.getBiografija());
				st.setTimestamp(8, k.getDatum_rodjenja());
				st.executeUpdate();
				return "Korisik je uspjesno registrovan";

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "Korisnicko ime vec postoji";

	}

	public static boolean provjeraKorisnikUsername(String username) {
		boolean postoji = true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st = conn
					.prepareStatement(provjeraKorisnikaUsername);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return postoji = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return postoji;

	}

	public static ArrayList<Korisnik> pretragaAutoraPoImenuIPrezimenu(
			String pretragaAutoraIme, String pretragaAutoraPrezime) {
		ArrayList<Korisnik> rezultat = new ArrayList<Korisnik>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st = conn
					.prepareStatement(pretragaAutoraPoImenuIPrezimenu);
			st.setString(1, pretragaAutoraIme);
			st.setString(2, pretragaAutoraPrezime);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Korisnik korisnik = new Korisnik();
				korisnik.setId(rs.getInt("id"));
				korisnik.setIme(rs.getString("ime"));
				korisnik.setPrezime(rs.getString("prezime"));
				rezultat.add(korisnik);
				return rezultat;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rezultat;
	}

	public static String dodajUKontakte(int korisnikId, int kontaktId) {
		if (provjeraListeKontakata(korisnikId, kontaktId)) {
			return "Kontakt vec postoji";
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st;
			st = conn.prepareStatement(dodavanjeKontakta);
			st.setInt(1, korisnikId);
			st.setInt(2, kontaktId);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return "Kontakt kreiran";
	}

	public static ArrayList<Korisnik> listaKontakata(int loginId) {
		ArrayList<Korisnik> rezultat = new ArrayList<Korisnik>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st = conn.prepareStatement(listaKontakata);
			st.setInt(1, loginId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Korisnik korisnik = new Korisnik();
				korisnik.setId(rs.getInt("id"));
				korisnik.setIme(rs.getString("ime"));
				korisnik.setPrezime(rs.getString("prezime"));
				rezultat.add(korisnik);
			}
			return rezultat;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rezultat;
	}

	public static String slanjeNovePoruke(int loginId, int kontakt,
			String sadrzajNovePoruke) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st;
			st = conn.prepareStatement(slanjePoruke);
			st.setInt(1, loginId);
			st.setInt(2, kontakt);
			st.setString(3, sadrzajNovePoruke);
			int i = st.executeUpdate();
			if (i != 0)
				return "Poruka uspjesno poslata";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Doslo je do greske";
	}

	public static String neprocitanePoruke(int loginId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st;
			st = conn.prepareStatement(brojNeprocitanihPoruka);
			st.setInt(1, loginId);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return rs.getString("broj").toString();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static String provjeraImenaIPrezimenaPoIdu(int posiljalacId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st;
			st = conn.prepareStatement(imeIPrezimePoId);
			st.setInt(1, posiljalacId);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String ime = rs.getString("ime");
				String prezime = rs.getString("prezime");
				return ime + " " + prezime;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Doslo je do greske";
	}

	public static ArrayList<Korisnik> selectSviRecenzenti() {
		ArrayList<Korisnik> korisnik = new ArrayList<Korisnik>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st = conn.prepareStatement(selectSviRecenzenti);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Korisnik k = new Korisnik();
				k.setId(rs.getInt("id"));
				k.setIme(rs.getString("ime"));
				k.setPrezime(rs.getString("prezime"));
				korisnik.add(k);
			}
			return korisnik;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}