/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day07binarytree;

import java.util.Arrays;

/**
 *
 * @author ewuzhou
 */
public class Day07BinaryTree{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        BinaryTreeOfInts tree = new BinaryTreeOfInts();
        tree.put(5);
        tree.put(2);
        tree.put(3);
        tree.put(7);
        tree.put(9);
        tree.put(4);
        
        int sum = tree.getSumOfAllValues();
        
        System.out.println("Sum of all values are: " + sum);
        
        int[] valsInOrder = tree.getValuesInOrder();
        System.out.println(Arrays.toString(valsInOrder));
        
        for(int i : tree){
            System.out.println("value: " + i);
        }
    }
    
}
