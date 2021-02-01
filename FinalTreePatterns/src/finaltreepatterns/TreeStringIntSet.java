/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finaltreepatterns;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class TreeStringIntSet implements Iterable<Pair<String,Integer>> , TreeEventObserverInt{

    public static final String FILE_NAME = "events.txt";

    @Override
    public Iterator<Pair<String, Integer>> iterator() {
        return new TreeStringIntSetIterator();
    }

    @Override
    public void event(String key, int value, String operation) {

        try {
            FileWriter fw = new FileWriter(FILE_NAME, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Event: " + operation + ", nodeKey=" + key + ", value=" + value);
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            System.out.println("Failed to write into File: " + e.getMessage());
        }
    }

    private class TreeStringIntSetIterator implements Iterator<Pair<String, Integer>> {
        
        private Pair[] arrayOfPairs;
        private int currIndex;

        public TreeStringIntSetIterator() {
            this.arrayOfPairs = getValuesInOrder();
        }

        @Override
        public boolean hasNext() {
            return currIndex < arrayOfPairs.length;
        }

        @Override
        public Pair<String, Integer> next() {
            return arrayOfPairs[currIndex++];
        }
    }
    
    class Node {
        
        String key; // keys are unique
        // HashSet is like ArrayList except it does not hold duplicates
        HashSet<Integer> valuesSet = new HashSet<>(); // unique only
        
        Node(){
            this.key = "";
            this.valuesSet = new HashSet<>();
        }
        
        Node left, right;
    }

    private Node root;
    private int nodesCount;
    
    // throws DuplicateValueException if this key already contains such value
    public void add(String key, int value) throws DuplicateValueException{
        
        if(root == null){
            root = new Node();
            root.key = key;
            root.valuesSet.add(value);
            nodesCount++;
            this.event(key,value,"Add");
            return;
        }
        //recursive or while loop
        Node currNode = root;
        while(true){
            if(currNode.key.equals(key)){
                if(currNode.valuesSet.contains(value)){
                    this.event(key,value,"Add-Failed");
                    throw new DuplicateValueException("This key already contains such value");
                }else{
                    currNode.valuesSet.add(value);
                    nodesCount++;
                    this.event(key,value,"Add");
                    return;
                }
            }
            if( key.compareTo(currNode.key) < 0){ // go left branch
                if(currNode.left ==  null){
                    // found the spot - put it here
                    currNode.left = new Node();
                    currNode.left.key = key;
                    currNode.left.valuesSet.add(value);
                    nodesCount++;
                    this.event(key,value,"Add");
                    return;
                }else{  // follow to the left
                    currNode = currNode.left;
                    // the loop will continue to another iteration
                }
            }else{  // go right branch
                if(currNode.right ==  null){
                    // found the spot - put it here
                    currNode.right = new Node();
                    currNode.right.key = key;
                    currNode.right.valuesSet.add(value);
                    nodesCount++;
                    this.event(key,value,"Add");
                    return;
                }else{  // follow to the right
                    currNode = currNode.right;
                    // the loop will continue to another iteration
                }
            }
        }
        
    }
    
    private Pair[] arrayOfPairs;
    private int resultIndex;
    private List<Integer> valueList;
    
    private Pair[] getValuesInOrder() {
        arrayOfPairs = new Pair[nodesCount];
        resultIndex = 0;
        collectValuesInOrder(root);  // execute the recursive method to refresh array with value
        return arrayOfPairs;
    }
        
    private void collectValuesInOrder(Node node) {
        if(node == null) return;
        collectValuesInOrder(node.right);
        for(int value : node.valuesSet){
            arrayOfPairs[resultIndex++] = new Pair(node.key, value);
        }
        collectValuesInOrder(node.left);
    }

    public boolean containsKey(String key) {
        return getAllKeys().contains(key);
    }

    public List<Integer> getValuesByKey(String key) {
        valueList = new ArrayList<Integer>();
        getValuesByKey(root, key);
        return valueList;
    } // return empty list if key not found
    
    private void getValuesByKey(Node node, String key) {
        if(node == null) return;
        getValuesByKey(node.right, key);
        if(node.key.equals(key)){
            valueList = new ArrayList<Integer>(node.valuesSet);
        }
        getValuesByKey(node.left, key);
    }

    public List<String> getKeysContainingValue(int value) {
        List<String> keyList = new ArrayList<String>();
        for(int i=0; i< getValuesInOrder().length; i++){
            if(arrayOfPairs[i].value.equals(value)){
                keyList.add(arrayOfPairs[i].key.toString());
            }
        }
        return keyList;
    }

    public List<String> getAllKeys() {
    
        List<String> keyList = new ArrayList<String>();
        for(int i=0; i< getValuesInOrder().length; i++){
            if(!keyList.contains(arrayOfPairs[i].key.toString())){
                keyList.add(arrayOfPairs[i].key.toString());
            }
        }
        return keyList;
    }
    
}
