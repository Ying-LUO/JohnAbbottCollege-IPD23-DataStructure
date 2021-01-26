/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz1rollingfifo;

/**
 *
 * @author ewuzhou
 */
public class Quiz1RollingFIFO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RollingPriorityFIFO fifo = new RollingPriorityFIFO(5);
        try {
            fifo.enqueue("test1", false);
            fifo.enqueue("test2", true);
            System.out.println(String.join("; ", fifo.toArrayOnlyPriority()));
        } catch (FIFOFullException e) {
            e.printStackTrace();
        }
    }
    
}
