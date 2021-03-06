package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	Model model = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboCorsi;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private TextField txtMatricolaStudente;

    @FXML
    private Button btnCercaStudente;

    @FXML
    private TextField txtNomeStudente;

    @FXML
    private TextField txtCognomeStudente;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	this.txtOutput.clear();
    	
    	String corso = this.comboCorsi.getSelectionModel().getSelectedItem();
    	
    	int matricola = -1;
    	
    	try {
			matricola = Integer.parseInt(this.txtMatricolaStudente.getText().trim());
			
		} catch (NumberFormatException e) {
			this.txtOutput.appendText("Errore: inserisci un numero di matricola valido.\n");
			e.printStackTrace();
		}
    	
    	Studente s = this.model.getStudente(matricola);
    	
    	if(s == null) {
    		this.txtOutput.appendText("Studente non trovato.\n");
    		return;
    	}
    	
    	this.txtNomeStudente.setText(s.getNome());
		this.txtCognomeStudente.setText(s.getCognome());
    	
    	if(corso == null || corso.equals("")) {
    		List<Corso> corsi = this.model.getCorsiStudente(s);
        		
       		for(Corso c : corsi)
           		this.txtOutput.appendText(c.toString() + "\n");
       		
    	} else {
    		if(this.model.isIscrittoAlCorso(corso, s))
    			this.txtOutput.appendText("Studente già iscritto a questo corso.");
    		else
    			this.txtOutput.appendText("Studente NON iscritto a questo corso.");
    	}
    	
    	
    }

    @FXML
    void doCercaIscrittiCorsi(ActionEvent event) {
    	this.txtOutput.clear();
    	
    	String corso = this.comboCorsi.getSelectionModel().getSelectedItem();
    	
    	if(corso == null || corso.equals("")) {
    		this.txtOutput.appendText("Errore: seleziona un corso.\n");
    		return;
    	}
    	
    	List<Studente> studentiIscritti = this.model.getStudentiIscrittiAlCorso(corso);
    	
    	for(Studente s : studentiIscritti)
    		this.txtOutput.appendText(s.toString() + "\n");
    }

    @FXML
    void doCercaStudente(ActionEvent event) {
    	int matricola = -1;
    	
    	try {
			matricola = Integer.parseInt(this.txtMatricolaStudente.getText().trim());
			
		} catch (NumberFormatException e) {
			this.txtOutput.appendText("Errore: inserisci un numero di matricola valido.\n");
			e.printStackTrace();
		}
    	
    	Studente s = this.model.getStudente(matricola);
    	
    	if(s == null)
    		this.txtOutput.appendText("Studente non trovato.\n");
    	
    	else {
    		this.txtNomeStudente.setText(s.getNome());
    		this.txtCognomeStudente.setText(s.getCognome());
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	this.txtOutput.clear();
    	
    	String corso = this.comboCorsi.getSelectionModel().getSelectedItem();
    	
    	if(corso == null || corso.equals("")) {
    		this.txtOutput.appendText("Errore: seleziona un corso.\n");
    		return;
    	}
    	
    	int matricola = -1;
   
    	try {
			matricola = Integer.parseInt(this.txtMatricolaStudente.getText().trim());
			
		} catch (NumberFormatException e) {
			this.txtOutput.appendText("Errore: inserisci un numero di matricola valido.\n");
			e.printStackTrace();
		}
    	
    	Studente s = this.model.getStudente(matricola);
    	
    	if(s == null) {
    		this.txtOutput.appendText("Errore: inserisci uno studente esistente.\n");
    		return;
    	}
    	this.txtNomeStudente.setText(s.getNome());
		this.txtCognomeStudente.setText(s.getCognome());
		
		if(this.model.isIscrittoAlCorso(corso, s))
			this.txtOutput.appendText("Studente già iscritto a questo corso.");
		else {
			boolean result = this.model.iscriviStudenteAlCorso(s, corso);
			if(result)
				this.txtOutput.appendText("Studente iscritto correttamente al corso.\n");
		}
    }

    @FXML
    void doReset(ActionEvent event) {
    	this.comboCorsi.getSelectionModel().clearSelection();
    	this.txtMatricolaStudente.clear();
    	this.txtNomeStudente.clear();
    	this.txtCognomeStudente.clear();
    	this.txtOutput.clear();
    }

    @FXML
    void initialize() {
        assert comboCorsi != null : "fx:id=\"comboCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricolaStudente != null : "fx:id=\"txtMatricolaStudente\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaStudente != null : "fx:id=\"btnCercaStudente\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNomeStudente != null : "fx:id=\"txtNomeStudente\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognomeStudente != null : "fx:id=\"txtCognomeStudente\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        
        this.txtOutput.setStyle("-fx-font-family: monospace");
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.addItemsToComboCorsi();
    }
    
    public void addItemsToComboCorsi() {
    	this.comboCorsi.getItems().add("");
    
    	for(String nome : this.model.getNomiCorsi())
    		this.comboCorsi.getItems().add(nome);
    }
}
