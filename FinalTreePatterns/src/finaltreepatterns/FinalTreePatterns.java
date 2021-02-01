/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finaltreepatterns;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FinalTreePatterns {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            TreeStringIntSet treeSet = new TreeStringIntSet();
            treeSet.add("Test", 0);
            treeSet.add("Test", 1);
            treeSet.add("Test", 2);
            treeSet.add("Test", 3);
            treeSet.add("Test", 4);
            treeSet.add("Test", 5);
            treeSet.add("Another", 5);
            treeSet.add("Another", 6);
            treeSet.add("New", 6);
            treeSet.add("New", 7);
            
            for(Pair pair : treeSet){
                 System.out.println(pair.toString());
            } 
            
            System.out.println(treeSet.getValuesByKey("Test"));
            System.out.println(treeSet.getValuesByKey("Another"));
            System.out.println(treeSet.getValuesByKey("New"));
            System.out.println(treeSet.containsKey("Test"));
            
            System.out.println(String.join("; ", treeSet.getAllKeys()));
            
            System.out.println(String.join("; ", treeSet.getKeysContainingValue(5)));
            
            treeSet.add("New", 6);
 
        } catch (DuplicateValueException ex) {
            System.out.println("exception: " + ex.getMessage());
        }
        
    }
    
}
