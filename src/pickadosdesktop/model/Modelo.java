/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.model;

import pickadosdesktop.entity.Odd;
import pickadosdesktop.entity.Match;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pickadosdesktop.dao.IDAO;
import pickadosdesktop.service.ApiFootballServices;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 *
 * @author JoseAntonio
 */
public class Modelo {
    private final ObjectProperty<ObservableList<Odd>> odds;
    private final ObjectProperty<ObservableList<Match>> matches;

    private final IDAO clientDAO;
    private final ApiFootballServices apiFootballServices;
    private final ObservableList<OddRow> oddRows;
    private final long msRefresco;
    private static final long DELAY = 3000;
    
        public Modelo(IDAO clientDAO, long msRefresco) {
	matches = new SimpleObjectProperty<>(FXCollections.observableArrayList());
        apiFootballServices = new ApiFootballServices();
        oddRows = FXCollections.observableArrayList();
        this.clientDAO = clientDAO;
	odds = new SimpleObjectProperty<>(FXCollections.observableArrayList());
        
        for(Match match: this.clientDAO.getMatches()) {
            matches.get().add(match);
        }
        
        //INITIALIZE ODD TABLE DATA
        oddRows.add(new OddRow("bet365","2","2","2","3","1.5"));
        
        TimerTask delayedTask = new TimerTask() {
	    @Override
	    public void run() {
                Platform.runLater(new Runnable() {
                @Override 
                public void run(){
                    List<Match> matchesRetrieved = apiFootballServices.getMatches();
                    matches.get().addAll(matchesRetrieved);
                }});
	    }
	};
	Timer timer = new Timer(true);
	timer.schedule(delayedTask, DELAY);

	this.msRefresco = msRefresco;
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
