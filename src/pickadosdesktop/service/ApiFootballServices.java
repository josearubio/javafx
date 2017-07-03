/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import pickadosdesktop.entity.Match;
import pickadosdesktop.exceptions.ParsingResponseException;
import pickadosdesktop.exceptions.WrongRequestException;
import pickadosdesktop.model.OddRow;

/**
 *
 * @author JoseAntonio
 */
public class ApiFootballServices {

    public ApiFootballServices(String apiUrl, String apiKey, ApiParser apiParser) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
        this.apiParser = apiParser;
    }
    
    private String apiUrl;
    private String apiKey;
    private final static Logger logger = Logger.getLogger(ApiFootballServices.class);
    private ApiParser apiParser;


    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public void setApiParser(ApiParser apiParser) {
        this.apiParser = apiParser;
    }

    public List<Match> getMatches(String fromDate, String toDate) throws WrongRequestException, ParsingResponseException{
        List<Match> matchesRetrieved = new ArrayList<>();
        String requestURL = apiUrl+ "get_events&from=" + fromDate + "&to=" + toDate + "&APIkey=" + apiKey;
        try {
            String response =  makeRequest(requestURL);
            matchesRetrieved = apiParser.parseListOfMatches(response);
            logger.info("Events for today were loaded successfully");
        } catch (IOException ex) {
            logger.error("Error while trying to make a request to: " + requestURL + ". It was produced by: "+ex.getMessage());
            throw new WrongRequestException("Error while trying to make a request");
        } catch(ParsingResponseException ex) {
            logger.error("Error while trying to load events for today. It was produced by: "+ex.getMessage());
            throw new ParsingResponseException(ex.getMessage());
        }
        return matchesRetrieved;
    }

    public List<Match> getLiveMatchs(String fromDate, String toDate) throws WrongRequestException, ParsingResponseException{
        List<Match> matchesRetrieved = new ArrayList<>();
        String requestURL = apiUrl+ "get_events&from=" + fromDate + "&to=" + toDate + "&match_live=1&APIkey="+ apiKey;
        
        try {
            String response = makeRequest(requestURL);
            matchesRetrieved = apiParser.parseListOfMatches(response);
            logger.info("Live matchs were loaded successfully");
        }catch (IOException ex) {
            logger.error("Error while trying to make a request to: " + requestURL + ". It was produced by: "+ex.getMessage());
            throw new WrongRequestException("Error while trying to make a request");
        } catch(ParsingResponseException ex) {
            logger.error("Error while trying to load live matchs. It was produced by: "+ex.getMessage());
            throw new ParsingResponseException(ex.getMessage());
        } 
        return matchesRetrieved;
    }

    public List<OddRow> getOddsFromMatch(String matchId, String fromDate, String toDate) throws WrongRequestException, ParsingResponseException {
        List<OddRow> rows = new ArrayList<>();
        String requestURL = apiUrl+"get_odds&from=" + fromDate + "&to=" + toDate + "&APIkey="+apiKey+"&match_id="+matchId;
        
        try {
            String response = makeRequest(requestURL);
            rows = apiParser.parseListOfOdds(response);
            logger.info("Succesfully loaded odds for match: " + matchId);

        } catch (IOException ex) {
            logger.error("Error while trying to make a request to: " + requestURL + ". It was produced by: "+ex.getMessage());
            throw new WrongRequestException("Error while trying to make a request");
        } catch(ParsingResponseException ex) {
            throw new ParsingResponseException(ex.getMessage());
        }
        return rows;
    }
    
    public String makeRequest(String requestUrl) throws MalformedURLException, IOException {
        String response = "";
            
        URL url = new URL(requestUrl);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        response = br.lines()
                .map(line -> line)
                .reduce("", (a, b) -> a + b);
         
        return response;
    }

}
