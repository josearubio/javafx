/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import pickadosdesktop.entity.Match;
import pickadosdesktop.entity.Odd;
import pickadosdesktop.exceptions.ParsingResponseException;
import pickadosdesktop.model.OddRow;

/**
 *
 * @author Norman
 */
public class ApiParser {

    private final static Logger logger = Logger.getLogger(ApiParser.class);

    public List<Match> parseListOfMatches(String response) throws ParsingResponseException{
        Gson gson = new Gson();
        List<Match> parsedMatchs = new ArrayList<>();

        try {
            Type matchType = new TypeToken<Collection<Match>>() {
            }.getType();
            parsedMatchs = gson.fromJson(response, matchType);
            logger.info("Matchs parsed succesfully");
        } catch (Exception ex) {
            logger.error("Error while trying to parsing matchs. It was produced by: " + ex.getMessage());
            throw new ParsingResponseException("Error while trying to parsing matchs.");
        }
        return parsedMatchs;
    }

    public List<OddRow> parseListOfOdds(String response) throws ParsingResponseException{
        Gson gson = new Gson();
        List<Odd> odds = new ArrayList<>();
        List<OddRow> parsedRows = new ArrayList<>();
        try {
            Type matchType = new TypeToken<Collection<Odd>>() {
            }.getType();
            odds = gson.fromJson(response, matchType);
            parsedRows = odds.stream()
                    .map(odd -> new OddRow(odd))
                    .collect(Collectors.toList());
            logger.info("Odds parsed succesfully");
        } catch (Exception ex) {
            logger.error("Error while trying to parsing odds. It was produced by: " + ex.getMessage());
             throw new ParsingResponseException("Error while trying to parsing odds.");
        }
        return parsedRows;
    }
}
