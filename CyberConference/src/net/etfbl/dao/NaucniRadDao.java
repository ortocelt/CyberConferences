package net.etfbl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.etfbl.bean.NaucniRadBean;
import net.etfbl.bean.NaucniRadBean.KorisnickiRadovi;
import net.etfbl.dto.Korisnik;
import net.etfbl.dto.NaucniRad;

public class NaucniRadDao {

	private static final String insertNoviRad = "insert into cyconf.naucni_rad (konferencija_id, naziv,abstrakt,kljucne_rijeci) values (?,?,?,?)";
	private static final String insertNoviKorisnickiRadovi = "insert into cyconf.korisnicki_radovi (korisnik, naucni_rad) values (?,?)";
	// private static final String sviKorisnickiRadovi =
	// "select naucni_rad.id, naucni_rad.naziv, naucni_rad.abstrakt, konferencija.naziv as konferencija from naucni_rad natural join korisnicki_radovi inner join konferencija on konferencija_id = konferencija.id where korisnik = ?";
	private static final String sviKorisnickiRadovi = "select naucni_rad.id, naucni_rad.naziv, naucni_rad.abstrakt, korisnicki_radovi.korisnik from naucni_rad left join korisnicki_radovi on naucni_rad.id = korisnicki_radovi.naucni_rad where korisnik = ? ";
	private static final String konferencijaZaRad = "select konferencija.naziv from naucni_rad left join konferencija on konferencija_id = konferencija.id where naucni_rad.id = ?";
	private static final String selectRad = "select * from naucni_rad where id = ?";
	private static final String izmjenaRada = "update naucni_rad set konferencija_id = ? , naziv = ?, abstrakt = ?, kljucne_rijeci = ? where id = ?";
	private static final String izmjenaAutoraRada = "update korisnicki_radovi set korisnik = ? where naucni_rad = ?";

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

	public static String unosNovogRada(NaucniRad noviNaucniRad,
			ArrayList<String> noviAutori) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");

			PreparedStatement st = conn.prepareStatement(insertNoviRad,
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, noviNaucniRad.getKonferencija_id());
			st.setString(2, noviNaucniRad.getNaziv());
			st.setString(3, noviNaucniRad.getAbstrakt());
			st.setString(4, noviNaucniRad.getKljucne_rijeci());
			int i = st.executeUpdate();
			if (i != 0) {
				ResultSet keys = st.getGeneratedKeys();
				keys.next();
				int lastId = keys.getInt(1);
				ArrayList<String> autori = noviAutori;
				for (String k : autori) {
					PreparedStatement st1 = conn
							.prepareStatement(insertNoviKorisnickiRadovi);
					st1.setInt(1, Integer.parseInt(k));
					st1.setInt(2, lastId);
					st1.executeUpdate();
				}
				return "Rad je uspjesno dodat";
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Doslo je do greske";
	}

	public static ArrayList<KorisnickiRadovi> sviKorisnikoviRadovi(int loginId) {
		ArrayList<KorisnickiRadovi> korisnickiRadovi = new ArrayList<KorisnickiRadovi>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn;
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st = conn.prepareStatement(sviKorisnickiRadovi);
			st.setInt(1, loginId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				NaucniRadBean naucniRad = new NaucniRadBean();
				KorisnickiRadovi radovi = naucniRad.new KorisnickiRadovi();
				radovi.setId(rs.getInt("id"));
				radovi.setNaziv(rs.getString("naziv"));
				radovi.setAbstrakt(rs.getString("abstrakt"));
				radovi.setKonferencija(konferencijaZaRad(radovi.getId()));
				korisnickiRadovi.add(radovi);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return korisnickiRadovi;
	}

	public static String konferencijaZaRad(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn;
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st = conn.prepareStatement(konferencijaZaRad);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String naziv = rs.getString("naziv");
				return naziv;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static NaucniRad izmjenaRada(int radId) {
		NaucniRad naucniRad = new NaucniRad();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn;
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st = conn.prepareStatement(selectRad);
			st.setInt(1, radId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				naucniRad.setId(radId);
				naucniRad.setNaziv(rs.getString("naziv"));
				naucniRad.setAbstrakt(rs.getString("abstrakt"));
				naucniRad.setKljucne_rijeci(rs.getString("kljucne_rijeci"));
				naucniRad.setKonferencija_id(rs.getInt("konferencija_id"));
				return naucniRad;
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return naucniRad;
	}

	public static String unosIzmjeneRada(NaucniRad izmjenjenRad,
			ArrayList<String> izmjenaAutora) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");

			PreparedStatement st = conn.prepareStatement(izmjenaRada);
			st.setInt(1, izmjenjenRad.getKonferencija_id());
			st.setString(2, izmjenjenRad.getNaziv());
			st.setString(3, izmjenjenRad.getAbstrakt());
			st.setString(4, izmjenjenRad.getKljucne_rijeci());
			st.setInt(5, izmjenjenRad.getId());
			int i = st.executeUpdate();
			if (i != 0) {
				ArrayList<String> autori = izmjenaAutora;
				for (String k : autori) {
					PreparedStatement st1 = conn
							.prepareStatement(izmjenaAutoraRada);
					st1.setInt(1, Integer.parseInt(k));
					st1.setInt(2, izmjenjenRad.getId());
					st1.executeUpdate();
				}
				return "Rad je uspjesno dodat";
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Doslo je do greske";
	}

}
