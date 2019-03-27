package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model {

	public List<String> getNomiCorsi() {
		CorsoDAO dao = new CorsoDAO();
		List<String> nomiCorsi = new ArrayList<String>();
		
		for(Corso c : dao.getTuttiICorsi())
			nomiCorsi.add(c.getNome());
		
		Collections.sort(nomiCorsi);
		
		return nomiCorsi;
	}

}
