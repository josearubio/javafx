/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.entity;

/**
 *
 * @author JoseAntonio
 */
public class Odd {
 
    private String match_id;
    private String odd_1;
    private String odd_x;
    private String odd_2;
    private String odd_bookmakers;

    public Odd(){
        odd_bookmakers = "bet365";
        odd_1 = "1.9";
        odd_x = "3";
        odd_2 = "2.1";
    }
    
    public Odd(String match_id,String odd_1, String odd_x, String odd_2, String odd_bookmakers) {
        this.match_id = match_id;
        this.odd_1 = odd_1;
        this.odd_x = odd_x;
        this.odd_2 = odd_2;
        this.odd_bookmakers = odd_bookmakers;
    }

    public String getOdd_bookmakers() {
        return odd_bookmakers;
    }

    public void setOdd_bookmakers(String bookie) {
        this.odd_bookmakers = bookie;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getOdd_1() {
        return odd_1;
    }

    public void setOdd_1(String odd_1) {
        this.odd_1 = odd_1;
    }

    public String getOdd_x() {
        return odd_x;
    }

    public void setOdd_x(String odd_x) {
        this.odd_x = odd_x;
    }

    public String getOdd_2() {
        return odd_2;
    }

    public void setOdd_2(String odd_2) {
        this.odd_2 = odd_2;
    }
}
