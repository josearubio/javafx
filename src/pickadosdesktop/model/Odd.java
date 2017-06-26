/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.model;

/**
 *
 * @author JoseAntonio
 */
public class Odd {
 
    private String match_id;
    private String type;
    private double value;
    private String bookie;

    public Odd(){
        match_id = type = bookie = "";
        value=0.0;
    }
    
    public Odd(String match_id,String type, double value, String bookie) {
        this.match_id = match_id;
        this.type = type;
        this.value = value;
        this.bookie = bookie;
    }

    public String getBookie() {
        return bookie;
    }

    public void setBookie(String bookie) {
        this.bookie = bookie;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    
}
