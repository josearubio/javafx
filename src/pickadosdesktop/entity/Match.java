
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author JoseAntonio
 */
public class Match {
    @SerializedName("match_id") 
    private String id; 
    @SerializedName("match_hometeam_name") 
    private String home_team;
    @SerializedName("match_awayteam_name") 
    private String away_team;
    private State state;
    @SerializedName("match_hometeam_score") 
    private String home_score;
    @SerializedName("match_awayteam_score") 
    private String away_score;
    private int match_live;
    private List<Odd> odds;

    public Match(String id, String home_team, String away_team, State state, int match_live) {
        this.id = id;
        this.home_team = home_team;
        this.away_team = away_team;
        this.state = state;
        this.match_live = match_live;
        this.home_score = "";
        this.away_score = "";
    }

    public String getMatch_hometeam_name() {
        return home_team;
    }

    public void setMatch_hometeam_name(String match_hometeam_name) {
        this.home_team = match_hometeam_name;
    }

    public String getMatch_awayteam_name() {
        return away_team;
    }

    public void setMatch_awayteam_name(String match_awayteam_name) {
        this.away_team = match_awayteam_name;
    }

    public int getMatch_live() {
        return match_live;
    }

    public void setMatch_live(int match_live) {
        this.match_live = match_live;
    }

    public String getId() {
        return id;
    }

    public String getHome_team() {
        return home_team;
    }

    public String getAway_team() {
        return away_team;
    }

    public State getState() {
        return state;
    }

    public String getHome_score() {
        return home_score;
    }

    public String getHome_away() {
        return away_score;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setHome_score(String home_score) {
        this.home_score = home_score;
    }

    public void setHome_away(String home_away) {
        this.away_score = home_away;
    }

    public List<Odd> getOdds() {
        return odds;
    }

    public void setOdds(List<Odd> odds) {
        this.odds = odds;
    }

    public void onFocus() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String toString() {
        return "" + home_team + " " + home_score + " - " + " " + away_score + " " + away_team + "";
    }
}
