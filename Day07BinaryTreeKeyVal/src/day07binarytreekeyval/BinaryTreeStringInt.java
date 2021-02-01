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
public class BinaryTreeStringInt {
    
    private class Node {
	Node(String key, int value){
            this.key = key;
            this.value = value;
        }
        String key;
        int value;
        Node left, right;
    }

	Node root;
        private int nodesCount;
	
	// throws exception if put attempts to insert a key that already exists (a duplicate)
	// values may be duplicates but keys may not
    void put(String key, int value) throws IllegalArgumentException {
        
        if(root == null){
            root = new Node(key, value);
            nodesCount++;
            return;
        }
        //recursive or while loop
        Node currNode = root;
        while(true){
            if(currNode.key.equals(key)){
                currNode.value = value;
                return;
            }
            if( key.compareTo(currNode.key) < 0){ // go left branch
                if(currNode.left ==  null){
                    // found the spot - put it here
                    currNode.left = new Node(key, value);
                    nodesCount++;
                    return;
                }else{  // follow to the left
                    currNode = currNode.left;
                    // the loop will continue to another iteration
                }
            }else{  // go right branch
                if(currNode.right ==  null){
                    // found the spot - put it here
                    currNode.right = new Node(key, value);
                    nodesCount++;
                    return;
                }else{  // follow to the right
                    currNode = currNode.right;
                    // the loop will continue to another iteration
                }
            }
        }
    }
	
	// print out all key-value pairs (one per line) from the smallest key to the largest
	void printAllKeyValPairs() { 
            printNodeAndAllBelow(root);
        }
        
        private void printNodeAndAllBelow(Node node){
            
            if(node == null) return;
            printNodeAndAllBelow(node.left);  // must change to node, not root!!!!!
            System.out.printf("%s ==> %d%n", node.key, node.value);
            printNodeAndAllBelow(node.right);
        }
            
}
