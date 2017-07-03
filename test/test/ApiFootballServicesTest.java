/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import pickadosdesktop.entity.Match;
import pickadosdesktop.entity.State;
import pickadosdesktop.exceptions.WrongRequestException;
import pickadosdesktop.exceptions.ParsingResponseException;
import pickadosdesktop.model.OddRow;
import pickadosdesktop.service.ApiFootballServicesStub;
import pickadosdesktop.service.ApiParser;

/**
 *
 * @author Norman
 */
public class ApiFootballServicesTest {

    private ApiParser apiParserMock;
    private ApiFootballServicesStub apiServices;

    public ApiFootballServicesTest() {
    }

    @Before
    public void setUp() {
        apiParserMock = mock(ApiParser.class);
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void should_return_list_of_matchs() throws IOException, ParsingResponseException {
        List<Match> matchList = new ArrayList<Match>();
        Match m1 = new Match("1", "F.C. Barcelona", "R. Madrid", State.UNSTARTED, 0, "");
        matchList.add(m1);

        try {
            when(apiParserMock.parseListOfMatches(Mockito.anyString())).thenReturn(matchList);
            apiServices = new ApiFootballServicesStub("", "", apiParserMock);
            apiServices.setResponse("");
            List<Match> actualList = apiServices.getMatches("", "");

            Assert.assertEquals(matchList, actualList);
            verify(apiParserMock).parseListOfMatches(Mockito.anyString());
        } catch (Exception ex) {
            Assert.fail("It should not throw any exception");
        }

    }

    @Test
    public void should_throw_wrong_request_exception() {

        try {
            when(apiParserMock.parseListOfMatches(Mockito.anyString())).thenThrow(WrongRequestException.class);
            apiServices = new ApiFootballServicesStub("", "", apiParserMock);
            apiServices.setResponse("Exception");
            apiServices.getMatches("", "");
            verify(apiParserMock).parseListOfMatches(Mockito.anyString());
        } catch (WrongRequestException ex) {
        } catch(ParsingResponseException ex) {
             Assert.fail("It should throws WrongRequestException");
        }
    }
    
    @Test
    public void should_throw_parsing_response_exception() {

        try {
            when(apiParserMock.parseListOfMatches(Mockito.anyString())).thenThrow(ParsingResponseException.class);
            apiServices = new ApiFootballServicesStub("", "", apiParserMock);
            apiServices.setResponse("");
            apiServices.getMatches("", "");
            verify(apiParserMock).parseListOfMatches(Mockito.anyString());
        } catch (WrongRequestException ex) {
            Assert.fail("It should throws ParsingResponseException");
        } catch(ParsingResponseException ex) {
             
        }
    }
    
    @Test
    public void should_return_list_of_live_matchs() throws IOException, ParsingResponseException {
        List<Match> matchList = new ArrayList<Match>();
        Match m1 = new Match("1", "F.C. Barcelona", "R. Madrid", State.UNSTARTED, 0, "");
        Match m2 = new Match("2", "F.C. Barcelona", "Atlético de Madrid", State.UNSTARTED, 0, "");
        matchList.add(m1);
        matchList.add(m2);

        try {
            when(apiParserMock.parseListOfMatches(Mockito.anyString())).thenReturn(matchList);
            apiServices = new ApiFootballServicesStub("", "", apiParserMock);
            apiServices.setResponse("");
            List<Match> actualList = apiServices.getLiveMatchs("", "");

            Assert.assertEquals(matchList, actualList);
            verify(apiParserMock).parseListOfMatches(Mockito.anyString());
        } catch (Exception ex) {
            Assert.fail("It should not throw any exception");
        }
    }
    
    @Test
    public void should_throw_wrong_request_exception_when_retrieving_live_matchs() {

        try {
            when(apiParserMock.parseListOfMatches(Mockito.anyString())).thenThrow(WrongRequestException.class);
            apiServices = new ApiFootballServicesStub("", "", apiParserMock);
            apiServices.setResponse("Exception");
            apiServices.getLiveMatchs("", "");
            verify(apiParserMock).parseListOfMatches(Mockito.anyString());
        } catch (WrongRequestException ex) {
        } catch(ParsingResponseException ex) {
             Assert.fail("It should throws WrongRequestException");
        }
    }
    
    @Test
    public void should_throw_parsing_response_exception_when_retrieving_live_matchs() {
        try {
            when(apiParserMock.parseListOfMatches(Mockito.anyString())).thenThrow(ParsingResponseException.class);
            apiServices = new ApiFootballServicesStub("", "", apiParserMock);
            apiServices.setResponse("");
            List<Match> actualList = apiServices.getLiveMatchs("", "");
            verify(apiParserMock).parseListOfMatches(Mockito.anyString());
        } catch (WrongRequestException ex) {
            Assert.fail("It should throws ParsingResponseException");
        } catch(ParsingResponseException ex) {
             
        }
    }
    
    @Test
    public void should_return_list_of_odds() throws IOException, ParsingResponseException {
        List<OddRow> oddRows = new ArrayList<OddRow>();
        OddRow o1 =  new OddRow("bet365", "1.71", "2.95", "1.35", "3.03", "2.25");
        OddRow o2 =  new OddRow("betfair", "1.75", "3.00", "1.38", "3.10", "2.40");
        oddRows.add(o1);
        oddRows.add(o2);

        try {
            when(apiParserMock.parseListOfOdds(Mockito.anyString())).thenReturn(oddRows);
            apiServices = new ApiFootballServicesStub("", "", apiParserMock);
            apiServices.setResponse("");
            List<OddRow> actualList = apiServices.getOddsFromMatch("", "", "");

            Assert.assertEquals(oddRows, actualList);
            verify(apiParserMock).parseListOfOdds(Mockito.anyString());
        } catch (Exception ex) {
            Assert.fail("It should not throw any exception");
        }
    }
    
    @Test
    public void should_throw_wrong_request_exception_when_retrieving_odds_from_match() {
        try {
            when(apiParserMock.parseListOfOdds(Mockito.anyString())).thenThrow(WrongRequestException.class);
            apiServices = new ApiFootballServicesStub("", "", apiParserMock);
            apiServices.setResponse("Exception");
            apiServices.getOddsFromMatch("", "", "");
            verify(apiParserMock).parseListOfMatches(Mockito.anyString());
        } catch (WrongRequestException ex) {
        } catch(ParsingResponseException ex) {
             Assert.fail("It should throws WrongRequestException");
        }
    }
    
    @Test
    public void should_throw_parsing_response_exception_when_retrieving_odds_from_match() {
        
        try {
            when(apiParserMock.parseListOfOdds(Mockito.anyString())).thenThrow(ParsingResponseException.class);
            apiServices = new ApiFootballServicesStub("", "", apiParserMock);
            apiServices.setResponse("");
            apiServices.getOddsFromMatch("","", "");
            verify(apiParserMock).parseListOfMatches(Mockito.anyString());
        } catch (WrongRequestException ex) {
            Assert.fail("It should throws ParsingResponseException");
        } catch(ParsingResponseException ex) {
             
        }
    }
}