/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz1rollingfifo;

/**
 *
 * @author YingLUO
 */
public class FIFOFullException extends Exception{
    
    public FIFOFullException(String errorMessage) {
        super(errorMessage);
    }
    
}
