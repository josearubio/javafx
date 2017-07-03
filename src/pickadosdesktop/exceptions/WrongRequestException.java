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
public class WrongRequestException extends Exception {
      public WrongRequestException() {}
      public WrongRequestException(String message)
      {
         super(message);
      }
}
