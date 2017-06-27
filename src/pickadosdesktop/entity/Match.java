
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.entity;

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
    private int match_live;
    private List<Odd> odds;

    public Match(String id, String home_team, String away_team, State state, int match_live) {
        this.id = id;
        this.match_hometeam_name = home_team;
        this.match_awayteam_name = away_team;
        this.state = state;
        this.match_live = match_live;
    }

    public String getMatch_hometeam_name() {
        return match_hometeam_name;
    }

    public void setMatch_hometeam_name(String match_hometeam_name) {
        this.match_hometeam_name = match_hometeam_name;
    }

    public String getMatch_awayteam_name() {
        return match_awayteam_name;
    }

    public void setMatch_awayteam_name(String match_awayteam_name) {
        this.match_awayteam_name = match_awayteam_name;
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String toString() {
        return "" + match_hometeam_name + " - " + match_awayteam_name + "";
    }
}
