package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> listCorsiAll() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
			}
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	
		return corsi;
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> listStudentiIscrittiAlCorso(String corso) {

		final String sql = "SELECT * FROM corso c, studente s, iscrizione i WHERE c.nome = ? AND i.matricola = s.matricola AND i.codins = c.codins";
		
		List<Studente> studentiIscritti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("s.matricola");
				String cognome = rs.getString("s.cognome");
				String nome = rs.getString("s.nome");
				String CDS = rs.getString("s.CDS");

				studentiIscritti.add(new Studente(matricola, cognome, nome, CDS));
			}
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	
		return studentiIscritti;
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

	public List<Corso> listCorsiByStudente(int matricola) {
		
		final String sql = "SELECT * FROM corso c, studente s, iscrizione i WHERE s.matricola = ? AND i.matricola = s.matricola AND i.codins = c.codins";
		
		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("c.codins");
				int numeroCrediti = rs.getInt("c.crediti");
				String nome = rs.getString("c.nome");
				int periodoDidattico = rs.getInt("c.pd");

				corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
			}
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	
		return corsi;
	}
}
