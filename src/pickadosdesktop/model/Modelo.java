/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.model;

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
    private final long msRefresco;
    private static final long DELAY = 3000;
    private  Gson gson = new Gson();
    
        public Modelo(IDAO clientDAO, long msRefresco) {
	matches = new SimpleObjectProperty<>(FXCollections.observableArrayList());
        
        this.clientDAO = clientDAO;
	odds = new SimpleObjectProperty<>(FXCollections.observableArrayList());
        
        for(Match match: this.clientDAO.getMatches()) {
            matches.get().add(match);
        }
        
        TimerTask delayedTask = new TimerTask() {
	    @Override
	    public void run() {
                Platform.runLater(new Runnable() {
                @Override 
                public void run(){
                try {
			URL url = new URL("https://apifootball.com/api/?action=get_events&from=2016-10-30&to=2016-11-01&league_id=62&APIkey=a5dfecb261f17d6f3644e14059d5220bb043042c983b19102d3e74af46ead2fd");
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String response = "";
			while (null != (response = br.readLine())) {
                                Type matchType = new TypeToken<Collection<Match>>() {}.getType();
                                List<Match> matchesRetrieved = gson.fromJson(response, matchType);
                                matches.get().addAll(matchesRetrieved);
			}
                        
		} catch (Exception ex) {
			ex.printStackTrace();
		}
                }});
	    }
	};
	Timer timer = new Timer(true);
	timer.schedule(delayedTask, DELAY);

	this.msRefresco = msRefresco;
        }
        
        public ObjectProperty<ObservableList<Match>> matchesProperty() {
            return matches;
        }
        
        public IDAO getDAOClient(){
            return clientDAO;
        }
}
