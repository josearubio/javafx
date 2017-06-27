/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.model;

import pickadosdesktop.entity.Odd;
import pickadosdesktop.entity.Match;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pickadosdesktop.dao.IDAO;
import pickadosdesktop.service.ApiFootballServices;

/**
 *
 * @author JoseAntonio
 */
public class Modelo {
    private final ObjectProperty<ObservableList<Match>> matches;
    private final IDAO clientDAO;
    private final ApiFootballServices apiFootballServices;
    private final ObservableList<OddRow> oddRows;
    private static final long DELAY = 3000;
    
        public Modelo(IDAO clientDAO, long msRefresco) {
	matches = new SimpleObjectProperty<>(FXCollections.observableArrayList());
        apiFootballServices = new ApiFootballServices();
        oddRows = FXCollections.observableArrayList();
        this.clientDAO = clientDAO;
        
        for(Match match: this.clientDAO.getMatches()) {
            matches.get().add(match);
        }
        
        TimerTask delayedTask = new TimerTask() {
	    @Override
	    public void run() {
                Platform.runLater(new Runnable() {
                @Override 
                public void run(){
                    List<Match> matchesRetrieved = apiFootballServices.getLiveMatches();
                    matches.get().addAll(matchesRetrieved);
                }});
	    }
	};
	Timer timer = new Timer(true);
	timer.schedule(delayedTask, DELAY);
        }
        
        public ObservableList<OddRow> oddRowsProperty() {
            return oddRows;
        }
            
        public ObjectProperty<ObservableList<Match>> matchesProperty() {
            return matches;
        }
        
        public IDAO getDAOClient(){
            return clientDAO;
        }
}
