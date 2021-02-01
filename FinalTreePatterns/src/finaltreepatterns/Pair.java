/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finaltreepatterns;

public class Pair<K, V> {
    public K key;
    public V value;
    
    Pair(K key, V value){
            this.key = key;
            this.value = value;
        }

    Pair left;
    Pair right;
    
    @Override
    public String toString() {
        return String.format("(%s=>%s)", key.toString(), value.toString());
    }
    
}
