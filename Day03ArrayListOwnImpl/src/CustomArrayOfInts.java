public class CustomArrayOfInts {

    private int[] data; // only grows by doubling size, never shrinks
    private int size; // how many items do you really have

    public CustomArrayOfInts(){
        this.data = new int[1];
        this.size = 0;
    }

    public int size() { return size; }
    public void add(int value) {

    }
    public void deleteByIndex(int index) {

    }
    public boolean deleteByValue(int value) {

    } // delete first value matching, true if value found, false otherwise
    public void insertValueAtIndex(int value, int index) {

    }
    public void clear() { size = 0; }
    public int get(int index) {

    } // may throw IndexOutOfBoundsException
    public int[] getSlice(int startIdx, int length) {

    } // may throw IndexOutOfBoundsException

    @Override
    public String toString() {
        String str="";
        for (int i = 0; i < data.length; i++) {
            str = String.format("%s%d%s", i == 0 ? "[" : ",", data[i], i == data.length-1 ? "]" : "");
        }
        return str;
    } // returns String similar to: [3, 5, 6, -23]

}
