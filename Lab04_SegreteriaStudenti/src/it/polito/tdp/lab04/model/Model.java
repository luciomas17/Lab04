package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		corsoDAO = new CorsoDAO();
		studenteDAO = new StudenteDAO();
	}

	public List<String> getNomiCorsi() {
		List<String> nomiCorsi = new ArrayList<String>();
		
		for(Corso c : corsoDAO.listCorsiAll())
			nomiCorsi.add(c.getNome());
		
		Collections.sort(nomiCorsi);
		
		return nomiCorsi;
	}

	public Studente getStudente(int matricola) {
		
		return studenteDAO.findStudenteFromMatricola(matricola);
	}

	public List<Studente> getStudentiIscrittiAlCorso(String corso) {
		
		return corsoDAO.listStudentiIscrittiAlCorso(corso);
	}

	public List<Corso> getCorsiStudente(Studente s) {

		return corsoDAO.listCorsiByStudente(s.getMatricola());
	}

}
