package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente findStudenteFromMatricola(int matricola) {
		
		final String sql = "SELECT * FROM studente WHERE matricola = ?";
		
		Studente s = null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");
					
				s = new Studente(matricola, cognome, nome, CDS);
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		return s;
	}

}
