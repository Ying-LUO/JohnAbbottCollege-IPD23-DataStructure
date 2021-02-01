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
        
        BinaryTree<Integer,String> genericTree = new BinaryTree<>();
        genericTree.put(33, "Jerry");
        genericTree.put(22, "Eva");
        genericTree.put(11, "Adam");
        genericTree.put(43, "Maria");
        genericTree.put(3, "Terry");
        genericTree.put(11, "Test");
        genericTree.put(67, "Peter");
        
        for (Pair<Integer,String> pair : genericTree) { // only works if Iterable is implemented
            System.out.printf("%d => %s\n", pair.key, pair.value);
        }
        
        System.out.printf("Get Value by Key%n%d => %s\n", 67, genericTree.getValueByKey(67));
        
    }
    
}
