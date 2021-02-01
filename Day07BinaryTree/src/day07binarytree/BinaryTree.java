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
 */
public class BinaryTree<K extends Comparable, V> implements Iterable<Pair<K, V>>{

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new BinaryTreeIterator();
    }

    private class BinaryTreeIterator implements Iterator<Pair<K, V>> {
        
        private Pair[] arrayOfPairs;
        private int currIndex;

        public BinaryTreeIterator() {
            this.arrayOfPairs = getValuesInOrder();
        }

        @Override
        public boolean hasNext() {
            return currIndex < arrayOfPairs.length;
        }

        @Override
        public Pair<K, V> next() {
            return arrayOfPairs[currIndex++];
        }
    }
    
    private Pair root;
    private int pairsCount;
    
    public void put(K key, V value) throws IllegalArgumentException {
        
        if(root == null){
            root = new Pair(key, value);
            pairsCount++;
            return;
        }
        //recursive or while loop
        Pair currPair = root;
        while(true){
            if(currPair.key.equals(key)){
                currPair.value = value;
                return;
            }
            if( key.compareTo(currPair.key) < 0){ // go left branch
                if(currPair.left ==  null){
                    // found the spot - put it here
                    currPair.left = new Pair(key, value);
                    pairsCount++;
                    return;
                }else{  // follow to the left
                    currPair = currPair.left;
                    // the loop will continue to another iteration
                }
            }else{  // go right branch
                if(currPair.right ==  null){
                    // found the spot - put it here
                    currPair.right = new Pair(key, value);
                    pairsCount++;
                    return;
                }else{  // follow to the right
                    currPair = currPair.right;
                    // the loop will continue to another iteration
                }
            }
        }
    }

    private Pair[] arrayOfPairs;
    private int resultIndex;
    
    private Pair[] getValuesInOrder() {
        arrayOfPairs = new Pair[pairsCount];
        resultIndex = 0;
        collectValuesInOrder(root);  // execute the recursive method to refresh array with value
        return arrayOfPairs;
    }
        
    private void collectValuesInOrder(Pair pair) {
        if(pair == null) return;
        collectValuesInOrder(pair.right);
        arrayOfPairs[resultIndex++] = pair;
        collectValuesInOrder(pair.left);
    }
    
    public V getValueByKey(K key) throws RuntimeException{
        
        // logically pairsCount == arrayOfPairs.length
        for(int i=0; i< getValuesInOrder().length; i++){
            if(arrayOfPairs[i].key.equals(key)){
                return (V)arrayOfPairs[i].value;
            }
        }
        throw new RuntimeException("Key not found");
    }
    
}
