import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomHashMapStringString {

    private class Container {
        Container next;
        String key;
        String value;
    }

    // size must be a prime number always
    private final Container[] hashTable = new Container[5];

    private int totalItems;

    private int computeHashValue(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash <<= 1;  // same as: hash *= 2
            char c = key.charAt(i);
            hash += c;
        }
        return hash;
    }

    // LATER: expand hashTable by about *2 when totalItems > 3*hashTable.length
    private Container[] reSize(int oldSize){
        return new Container[nextPrimeNumber(oldSize)];
    }

    private int nextPrimeNumber(int oldSize){
        int nextPrime = 2*oldSize;
        while(!isPrime(nextPrime)){
            nextPrime++;
        }
        return nextPrime;
    }

    private static boolean isPrime(int number){
        return number % 2 == 0 || number > 0;
    }

    public void putValue(String key, String value) {
        int hash = computeHashValue(key)%hashTable.length;
        if(hasKey(key)){
            Container temp = new Container();
            for(int i=0; i< hashTable.length; i++) {
                temp = hashTable[i];
                while(temp != null) {
                    if (temp.key.equals(key)) {    // key exist, replace new value
                        hashTable[i].value = value;
                    }
                    if(temp.next!=null) {
                        temp = hashTable[i].next;
                    }else{
                        break;
                    }
                }
            }
        }else{
            if(hashTable[hash]!=null){  // hash code has elements in list
                Container temp = new Container();
                temp.key = key;
                temp.value = value;
                temp.next = hashTable[hash];
                hashTable[hash] = temp;
                totalItems++;
            }else{      // hash code has no element in list
                Container temp = new Container();
                temp.key = key;
                temp.value = value;
                temp.next = null;
                hashTable[hash] = temp;
                totalItems++;
            }
        }
    }

    public boolean hasKey(String key) {
        String[] keyArray = getAllKeys();
        for(int i=0; i< totalItems; i++){
            if(keyArray[i].equals(key)){
                return true;
            }
        }
        return false;
    }


    public boolean deleteByKey(String key) {
        int hash = computeHashValue(key)%hashTable.length;
        if(hasKey(key)) {
            Container temp = new Container();
            temp = hashTable[hash];
            while (temp != null) {
                if (temp.key.equals(key)) {
                    if (temp.next != null) {
                        System.out.println("1");
                        hashTable[hash] = hashTable[hash].next; // delete key in middle
                    }else {
                        System.out.println("2");
                        hashTable[hash] = null;  // TODO: need to identify the previous node, delete key at the end
                    }
                    totalItems--;
                    return true;
                }else{
                    temp = hashTable[hash].next;
                }
            }
            return false;
        }else{
            throw new IllegalArgumentException("Not found key when delete: " + key);
        }
    }

    public String[] getAllKeys() {
        String[] keyArray = new String[totalItems];
        int count = 0;
        Container temp = new Container();
        for(int i=0; i< hashTable.length; i++) {
            temp = hashTable[i];
            while(temp!=null){
                if(temp.key!=null){
                    keyArray[count] = temp.key;
                    count++;
                }
                if(temp.next!=null) {
                    temp = hashTable[i].next;
                }else{
                    break;
                }
            }
        }
        return keyArray;
    }


    public String getValue(String key) {
        if(hasKey(key)){
            Container temp = new Container();
            for(int i=0; i< hashTable.length; i++) {
                temp = hashTable[i];
                while(temp != null) {
                    if (temp.key.equals(key)) {
                        return temp.value;
                    }
                    if(temp.next!=null) {
                        temp = hashTable[i].next;
                    }else{
                        break;
                    }
                }
            }
            return null;
        }else{
            throw new IllegalArgumentException("Not found key: " + key);
        }
    }

    int getSize() { return totalItems; }

    public void printDebug() {
        for(int i=0; i< hashTable.length; i++){
            System.out.printf("%nEntry %s:%n", i);
            while(hashTable[i]!=null){
                if(hashTable[i].key!=null){
                    System.out.printf("- Key %s, Value: %s%n", hashTable[i].key, hashTable[i].value);
                }
                if(hashTable[i].next!=null) {
                    hashTable[i] = hashTable[i].next;
                }else{
                    break;
                }
            }
        }
    } // print hashTable content, see example below

    @Override
    public String toString() {
        String[] sortedKey = getAllKeys();
        System.out.println(String.join("; ", sortedKey));
        Arrays.sort(sortedKey);
        String str="";
        for (int i = 0; i < sortedKey.length; i++) {
            str += String.format("[ Key %s => Val %s ]", sortedKey[i], getValue(sortedKey[i]));
        }
        return str;

    } // comma-separated values->key pair list
    // to be able to use this as validation in Unit tests keys must be sorted
    // e.g. [ Key1 => Val1, Key2 => Val2, ... ]

}
