/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import pickadosdesktop.dao.IDAO;
import pickadosdesktop.dao.LocalDAO;
import pickadosdesktop.model.Match;
import pickadosdesktop.model.Modelo;

/**
 * FXML Controller class
 *
 * @author JoseAntonio
 */
public class DashboardController implements Initializable {
    @FXML
    private ListView<Match> comingEventsList;
    
    Modelo modelo;
    
    IDAO clientDAO;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        clientDAO = new LocalDAO();
        modelo = new Modelo(clientDAO, 0);
       // comingEventsList.itemsProperty().bind(modelo.matchesProperty());
        initializeMatches();
    }    
    
    public void initializeMatches(){
        List<Match> matchList = clientDAO.getMatches();
        for(Match match : matchList){
            comingEventsList.getItems().add(match);
        }
        
    	modelo.matchesProperty().get().addListener(new ListChangeListener<Match>() {
	    @Override
	    public void onChanged(ListChangeListener.Change<? extends Match> c) {
		while (c.next()) {
		    if (c.wasAdded()) { // sólo miramos las nuevas alarmas, de momento no borramos ni modificamos
			for (Match match : c.getAddedSubList()) {
                            comingEventsList.getItems().add(match);
			}
			
		    }
		}
		
	    }
	});
	
	comingEventsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Match>() {
	    @Override
	    public void changed(ObservableValue<? extends Match> observable, Match oldValue, Match newValue) {
		if (newValue != null) {
		    newValue.onFocus();
		}
	    }
	});
    }
    
}
