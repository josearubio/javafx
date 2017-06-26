/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.service;

import java.util.Iterator;
import org.json.*;
import pickadosdesktop.model.Odd;

/**
 *
 * @author JoseAntonio
 */
public class ParseJSON {
    public void parseOdds(String xml){
        //xml="{{"match_id":"148356","odd_bookmakers":"10Bet","odd_date":"2017-02-07 07:41:36","odd_1":"1.77" (...)
        JSONArray oddsArray = new JSONArray(xml);
        String bookie, type, value, match_id;
        bookie = type = value = match_id = "";
        Odd odd = new Odd();
        for (int i=0;i<oddsArray.length();i++)
        {   JSONObject oddGroup = oddsArray.getJSONObject(i);
            
            match_id = oddGroup.getString("match_id");
            bookie = oddGroup.getString("odd_bookmakers");
            
            Iterator<?> keys = oddGroup.keys();
            
            //Skip match_id and bookie 
            if(keys.hasNext()) keys.next();
            if(keys.hasNext()) keys.next();
            
            while( keys.hasNext() ) {
                String key = (String)keys.next();
                if ( oddGroup.get(key) instanceof JSONObject ) {
                    odd.setMatch_id(match_id);
                    odd.setBookie(bookie);
                    odd.setType(key);
                    odd.setValue(Double.parseDouble(oddGroup.getString(key)));
                    
                    //TODO: SAVE ODD USING LOCALDAO
                }
            }
        }
    }
}
