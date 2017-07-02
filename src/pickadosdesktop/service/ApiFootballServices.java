/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.service;

import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import pickadosdesktop.entity.Match;
import pickadosdesktop.entity.Odd;
import pickadosdesktop.model.OddRow;

/**
 *
 * @author JoseAntonio
 */
public class ApiFootballServices {
    
    private final static String apiUrl = Utils.getProperty("api");
    private final static String apiKey = Utils.getProperty("apiKey");
    private final static Logger logger = Logger.getLogger(ApiFootballServices.class);

    public List<Match> getMatches() {
        Gson gson = new Gson();
        List<Match> matchesRetrieved = new ArrayList<>();
        String currentDate = Utils.getFormattedCurrentDate();
        
        try {
            URL url = new URL(apiUrl+ "get_events&from=" + currentDate + "&to=" + currentDate + "&APIkey=" + apiKey);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String response = "";
            while (null != (response = br.readLine())) {
                Type matchType = new TypeToken<Collection<Match>>() {
                }.getType();
                matchesRetrieved = gson.fromJson(response, matchType);
            }
            
            logger.info("Events for today were loaded successfully");
        } catch (Exception ex) {
            logger.error("Error while trying to load events for today. It was produced by: "+ex.getMessage());
        }
        return matchesRetrieved;
    }

    public List<Match> getLiveMatches() {
        Gson gson = new Gson();
        List<Match> matchesRetrieved = new ArrayList<>();
        String currentDate = Utils.getFormattedCurrentDate();
        
        try {
            
            URL url = new URL(apiUrl+ "get_events&from=" + currentDate + "&to=" + currentDate + "&match_live=1&APIkey="+ apiKey);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String response = "";
            while (null != (response = br.readLine())) {
                Type matchType = new TypeToken<Collection<Match>>() {
                }.getType();
                matchesRetrieved = gson.fromJson(response, matchType);
            }
            logger.info("Live matchs were loaded successfully");
        } catch (Exception ex) {
            logger.error("Error while trying to load live matchs. It was produced by: "+ex.getMessage());
        }
        return matchesRetrieved;
    }

    public List<OddRow> getOddsFromMatch(String matchId) {
        Gson gson = new Gson();
        List<OddRow> rows = new ArrayList<>();
        List<Odd> odds = new ArrayList<>();
        String currentDate = Utils.getFormattedCurrentDate();
        try {
            URL url = new URL(apiUrl+"get_odds&from=" + currentDate + "&to=" + currentDate + "&APIkey="+apiKey+"&match_id="+matchId);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String response = "";
            while (null != (response = br.readLine())) {
                Type matchType = new TypeToken<Collection<Odd>>() {
                }.getType();
                odds = gson.fromJson(response, matchType);
            }
            
            for(Odd odd: odds) {
                rows.add(new OddRow(odd));
            }
            logger.info("Succesfully loaded odds for match: " + matchId);

        } catch (Exception ex) {
            logger.error("Error while trying to load odd for match: " + matchId + ". It was produced by: "+ex.getMessage());
        }
        return rows;
    }

}
