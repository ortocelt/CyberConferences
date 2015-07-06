package net.etfbl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.dto.Konferencija;

public class KonferencijaDao {

	private static final String sveAktivneKonferencije = "SELECT * FROM cyconf.konferencija where rok > curdate()";
	
	public static ArrayList<Konferencija> aktivneKonferencije = new ArrayList<Konferencija>();
	
	public static ArrayList<Konferencija> aktivneKonferencijeZaPrijavu() {
		ArrayList<Konferencija> konferencije = new ArrayList<Konferencija>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyconf", "root", "root");
		PreparedStatement st = conn.prepareStatement(sveAktivneKonferencije);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()){
			Konferencija k = new Konferencija();
			k.setId(rs.getInt("id"));
			k.setNaziv(rs.getString("naziv"));
			k.setRokZaPrijavu(rs.getTimestamp("rok"));
			k.setPocetakKonferencije(rs.getTimestamp("od"));
			k.setKrajKonferencije(rs.getTimestamp("do"));
			k.setMjesto(rs.getString("mjesto"));
			konferencije.add(k);
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return konferencije;
		
	}

	public static String unosNoveKonferencije(Konferencija novaKonferencija,
			ArrayList<String> noviRecezenti) {
		return null;
	}
}
