
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.model;

import java.util.List;

/**
 *
 * @author JoseAntonio
 */
public class Match {
    private String id;
    private String match_hometeam_name;
    private String match_awayteam_name;
    private State state;
    private int home_score;
    private int home_away;
    private List<Odd> odds;

    public Match(String id, String home_team, String away_team, State state) {
        this.id = id;
        this.match_hometeam_name = home_team;
        this.match_awayteam_name = away_team;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public String getHome_team() {
        return match_hometeam_name;
    }

    public String getAway_team() {
        return match_awayteam_name;
    }

    public State getState() {
        return state;
    }

    public int getHome_score() {
        return home_score;
    }

    public int getHome_away() {
        return home_away;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHome_team(String home_team) {
        this.match_hometeam_name = home_team;
    }

    public void setAway_team(String away_team) {
        this.match_awayteam_name = away_team;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setHome_score(int home_score) {
        this.home_score = home_score;
    }

    public void setHome_away(int home_away) {
        this.home_away = home_away;
    }

    public List<Odd> getOdds() {
        return odds;
    }

    public void setOdds(List<Odd> odds) {
        this.odds = odds;
    }

    public void onFocus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String toString() {
        return "" + match_hometeam_name + " - " + match_awayteam_name + "";
    }
}
