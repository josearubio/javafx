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
import pickadosdesktop.entity.Match;

/**
 *
 * @author JoseAntonio
 */
public class ApiFootballServices {
    
    public List<Match> getMatches(){
        Gson gson = new Gson();
        List<Match> matchesRetrieved = new ArrayList<>();
                        try {
			URL url = new URL("https://apifootball.com/api/?action=get_events&from=2017-06-27&to=2017-06-27&APIkey=a5dfecb261f17d6f3644e14059d5220bb043042c983b19102d3e74af46ead2fd");
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String response = "";
			while (null != (response = br.readLine())) {
                                Type matchType = new TypeToken<Collection<Match>>() {}.getType();
                                matchesRetrieved = gson.fromJson(response, matchType);
			}
                        
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        return matchesRetrieved;
    }
    
}
