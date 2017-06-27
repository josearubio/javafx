/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import pickadosdesktop.entity.Match;
import pickadosdesktop.entity.Odd;
import pickadosdesktop.entity.State;
import pickadosdesktop.utilities.DBManager;

/**
 *
 * @author JoseAntonio
 */
public class LocalDAO implements IDAO{
    private List<Odd> oddList = new ArrayList<>();
    private List<Match> matchList = new ArrayList<>();
    //private Connection conn = DBManager.getConnection(); 
    
    public LocalDAO () {
        Match match1 = new Match("1","Elche", "Hércules", State.UNSTARTED,0);
        Match match2 = new Match("2","Albacete", "Oviedo", State.UNSTARTED,0);
        Match match3 = new Match("3","Valencia", "Eibar", State.UNSTARTED,0);
        matchList.add(match1);
        matchList.add(match2);
        matchList.add(match3);
    }
    
    public List<Match> getMatches() {
        return matchList;
    }
    public void persistOdd(Odd odd){
        oddList.add(odd);
    }
    
    public void resetOddList(){
        oddList.removeAll(oddList);
    }
}
