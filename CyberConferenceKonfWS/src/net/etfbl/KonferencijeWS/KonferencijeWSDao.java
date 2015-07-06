package net.etfbl.KonferencijeWS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class KonferencijeWSDao {
	
	private static final String konferencijeZaTekuciMjesec = "SELECT * FROM cyconf.konferencija where month(rok) = month(curdate())";
	
	public static String konferencijeZaTekuciMjesec(){
		
		String konferencije = new String();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyconf", "root", "root");
		PreparedStatement st = conn.prepareStatement(konferencijeZaTekuciMjesec);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()){
			konferencije += (rs.getString("naziv") + " Rok za prijavu: " +  rs.getTimestamp("rok") + "\n");
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

}
