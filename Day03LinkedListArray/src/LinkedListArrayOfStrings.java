public class LinkedListArrayOfStrings {

    private class Container {
        Container next;
        String value;
    }
    private Container start;
    private int size;

    public void add(String value) {}
    public String get(int index) {}
    public void insertValueAtIndex(int index, String value) {}
    public void deleteByIndex(int index) { }
    public boolean deleteByValue(String value) {} // delete first value found
    public int getSize() { }

    @Override
    public String toString() { } // comma-separated values list similar to: [5,8,11]

    public String[] toArray() { } // could be used for Unit testing

}
