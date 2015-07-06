package net.etfbl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.dto.Korisnik;
import net.etfbl.dto.Poruka;

public class PorukaDao {

	private static String updateProcitana = "update poruka set procitana = 1 where id = ?";
	private static String korisnikovePoruke = "select * from poruka where prima = ?";
	private static String procitanaPoruka = "select * from poruka where id = ?";
	private static String insertAutomatskaPorukaZaNoviKontakt = "insert into poruka (salje,prima,tekst,datum,procitana) values (?,?,?,now(),0)";

	public PorukaDao() {
		super();
	}

	public static Poruka procitanaPoruka(int porukaId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st;
			st = conn.prepareStatement(procitanaPoruka);
			st.setInt(1, porukaId);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Poruka poruka = new Poruka();
				poruka.setId(rs.getInt("id"));
				poruka.setPrima(rs.getInt("prima"));
				poruka.setSalje(rs.getInt("salje"));
				poruka.setTekst(rs.getString("tekst"));
				poruka.setDatum(rs.getTimestamp("datum"));
				return poruka;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void setProcitana(int porukaId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st = conn.prepareStatement(updateProcitana);
			st.setInt(1, porukaId);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<Poruka> korisnikovePoruke(int loginId) {
		ArrayList<Poruka> poruke = new ArrayList<Poruka>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st;
			st = conn.prepareStatement(korisnikovePoruke);
			st.setInt(1, loginId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Poruka poruka = new Poruka();
				poruka.setId(rs.getInt("id"));
				poruka.setPrima(rs.getInt("prima"));
				poruka.setSalje(rs.getInt("salje"));
				poruka.setTekst(rs.getString("tekst"));
				poruka.setDatum(rs.getTimestamp("datum"));
				poruke.add(poruka);
			}
			return poruke;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return poruke;
	}

	public static void automatskaPorukaZaDodaniKontakt(int salje, int prima) {
		Korisnik pos = KorisnikDao.selectKorisnikById(salje);
		Korisnik pri = KorisnikDao.selectKorisnikById(prima);
		kreiranjeAutomatskePoruke(
				pos.getId(), 
				pri.getId(),
				"Korisnik " + pos.getIme() + " " + pos.getPrezime()	+ " vas je dodao za kontakta."
		);
	}

	public static void kreiranjeAutomatskePoruke(int salje, int prima,
			String sadrzaj) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cyconf", "root", "root");
			PreparedStatement st;
			st = conn.prepareStatement(insertAutomatskaPorukaZaNoviKontakt);
			st.setInt(1, salje);
			st.setInt(2, prima);
			st.setString(3, sadrzaj);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
