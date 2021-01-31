/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day07binarytree;

import java.util.Iterator;

/**
 *
 * @author ewuzhou
 */                                    // implement diff interface
public class BinaryTreeOfInts implements Iterable<Integer>{
                                            // implement diff interface
    class SimpleBinaryTreeIterator implements Iterator<Integer>{
        
        private int[] valuesArray;
        private int currIndex; // current position in the array
        
        SimpleBinaryTreeIterator(){
            this.valuesArray = getValuesInOrder();
        }
        
        @Override
        public boolean hasNext(){
                return currIndex < valuesArray.length;
        }
        
        @Override
        public Integer next(){
            return valuesArray[currIndex++];
        }
        
    }
    
    
    @Override
    public Iterator<Integer> iterator() {
        return new SimpleBinaryTreeIterator();
    }
    
    private class NodeOfInt {
        int value; // could also be key,value pair
        NodeOfInt left, right;
    }
    
    NodeOfInt root;
    private int nodesCount;
	
	// throws exception if put attempts to insert value that already exists (a duplicate)
    public void put(int value) throws IllegalArgumentException { 
        NodeOfInt newNode = new NodeOfInt();
        newNode.value = value;
        if(root == null){
            root = newNode;
            nodesCount++;
            return;
        }
        //recursive or while loop
        NodeOfInt currNode = root;
        while(true){
            if(currNode.value == value){
                throw new IllegalArgumentException("Duplicates are not allowed");
            }
            if( value < currNode.value){ // go left branch
                if(currNode.left ==  null){
                    // found the spot - put it here
                    currNode.left = newNode;
                    nodesCount++;
                    return;
                }else{  // follow to the left
                    currNode = currNode.left;
                    // the loop will continue to another iteration
                }
            }else{  // go right branch
                if(currNode.right ==  null){
                    // found the spot - put it here
                    currNode.right = newNode;
                    nodesCount++;
                    return;
                }else{  // follow to the right
                    currNode = currNode.right;
                    // the loop will continue to another iteration
                }
            }
        }
        
    }

	// uses recursion to compute the sum of all values in the entire tree
	public int getSumOfAllValues() { 
            return getSumOfThisAndSubNodes(root);
        }
	// private helper recursive method to implement the above method
	private int getSumOfThisAndSubNodes(NodeOfInt node) { 
            if(node == null) return 0;
             int result = node.value;
             result += getSumOfThisAndSubNodes(node.left);
             result += getSumOfThisAndSubNodes(node.right);
             return result;
        }
	
	// uses recursion to collect all values from largest to smallest
	public int [] getValuesInOrder() { // from largest to smallest
            resultArray = new int[nodesCount];
            resultIndex = 0;
            collectValuesInOrder(root);  // execute the recursive method to refresh array with value
            return resultArray;
	}
	// private helper recursive method to implement the above method
	private void collectValuesInOrder(NodeOfInt node) {
            if(node == null) return;
            collectValuesInOrder(node.right);
            resultArray[resultIndex++] = node.value;
            collectValuesInOrder(node.left);
    }
	// data structures used to make collecting values in order easier
    private int[] resultArray;
    private int resultIndex;
    
    
}
