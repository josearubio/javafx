/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.exceptions;

/**
 *
 * @author Norman
 */
public class ParsingResponseException extends Exception{
    public ParsingResponseException(){}
    public ParsingResponseException(String message) {
        super(message);
    }
}
