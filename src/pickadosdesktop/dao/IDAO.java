/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.dao;

import java.util.List;
import pickadosdesktop.model.Match;
import pickadosdesktop.model.Odd;

/**
 *
 * @author JoseAntonio
 */
public interface IDAO {
    void persistOdd(Odd odd);
    void resetOddList();
    List<Match> getMatches();
}
