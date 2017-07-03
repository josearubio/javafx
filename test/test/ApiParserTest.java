/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pickadosdesktop.entity.Match;
import pickadosdesktop.entity.State;
import pickadosdesktop.exceptions.ParsingResponseException;
import pickadosdesktop.model.OddRow;
import pickadosdesktop.service.ApiParser;

/**
 *
 * @author Norman
 */
public class ApiParserTest {

    private ApiParser apiParser;

    public ApiParserTest() {
    }

    @Before
    public void setUp() {
        apiParser = new ApiParser();
    }

    @Test
    public void should_parse_list_of_matchs() {
        String response = "["
                + "{'match_id':'173354','match_date':'2017-07-03','match_hometeam_name':'Bulgaria U19','match_hometeam_score':'0','match_awayteam_name':'England U19','match_awayteam_score':'2','match_live':'1'},"
                + "{'match_id':'173355','match_date':'2017-07-03','match_hometeam_name':'Germany U19','match_hometeam_score':'1','match_awayteam_name':'Netherlands U19','match_awayteam_score':'4','match_live':'1'}"
                + "]";
        List<Match> expectedMatchs = new ArrayList<Match>();
        Match m1 = new Match("173354", "Bulgaria U19", "England U19", State.UNSTARTED, 1, "2017-07-03");
        m1.setHome_score("0");
        m1.setAway_score("2");
        Match m2 = new Match("173355", "Germany U19", "Netherlands U19", State.UNSTARTED, 1, "2017-07-03");
        m2.setHome_score("1");
        m2.setAway_score("4");

        expectedMatchs.add(m1);
        expectedMatchs.add(m2);
        try {
            List<Match> actualMatchs = apiParser.parseListOfMatches(response);
            Assert.assertEquals(expectedMatchs.get(0).toString(), actualMatchs.get(0).toString());
        } catch (ParsingResponseException ex) {
            Assert.fail("It should not throw any exception");
        }
    }

    @Test
    public void should_parse_list_of_odds() {
        String response = "["
                + "{'match_id':'189792','odd_bookmakers':'10Bet','odd_1':'4.65','odd_x':'4.10','odd_2':'1.51','o+2.5':'2.25','u+2.5':'3.10'},"
                + "{'match_id':'189792','odd_bookmakers':'188BET','odd_1':'3.25','odd_x':'4.10','odd_2':'1.75','o+2.5':'1.65','u+2.5':'2.02'}"
                + "]";
        try {
            List<OddRow> actualMatchs = apiParser.parseListOfOdds(response);
            Assert.assertEquals(actualMatchs.size(), 2);
        } catch (ParsingResponseException ex) {
            Assert.fail("It should not throw any exception");
        }
    }
}
