/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.service;

import java.io.IOException;

/**
 *
 * @author Norman
 */
public class ApiFootballServicesStub extends ApiFootballServices {
    
    private String response;

    public void setResponse(String response) {
        this.response = response;
    }
    
    public ApiFootballServicesStub(String apiUrl, String apiKey, ApiParser apiParser) {
        super(apiUrl, apiKey, apiParser);
    }
    
    @Override
    public String makeRequest(String requestUrl) throws IOException{
        if(response.equals("Exception")) {
            throw new IOException();
        }
        return response;
    }
    
    
}
