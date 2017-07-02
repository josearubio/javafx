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
    public void should_return_list_of_matchs() throws IOException {
         List <Match> matchList = new ArrayList<Match>();
         Match m1 = new Match("1", "F.C. Barcelona", "R. Madrid", State.UNSTARTED, 0);
         matchList.add(m1);
         
         when(apiParserMock.parseListOfMatches(Mockito.anyString())).thenReturn(matchList);
         apiServices = new ApiFootballServicesStub("", "", apiParserMock);
         
         List <Match> actualList = apiServices.getMatches("", "");
         
         Assert.assertEquals(matchList, actualList);
         verify(apiParserMock).parseListOfMatches(Mockito.anyString());
    }
}
