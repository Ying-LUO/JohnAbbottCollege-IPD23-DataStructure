/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day07binarytreekeyval;

/**
 *
 * @author ewuzhou
 */
public class Day07BinaryTreeKeyVal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BinaryTreeStringInt tree = new BinaryTreeStringInt();
        tree.put("Jerry", 33);
        tree.put("Eva", 22);
        tree.put("Adam", 11);
        tree.put("Maria", 43);
        tree.put("Terry", 3);
        tree.put("Peter", 67);
        tree.printAllKeyValPairs();
    }
    
}
