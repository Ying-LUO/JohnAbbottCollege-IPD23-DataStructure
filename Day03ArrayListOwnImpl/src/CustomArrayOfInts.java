public class CustomArrayOfInts {

    private int[] data; // only grows by doubling size, never shrinks
    private int size; // how many items do you really have

    public CustomArrayOfInts(){
        this.data = new int[1];
        this.size = 0;
    }

    public int size() { return size; }
    public int[] data() { return data; }

    public void add(int value) {
        if (size == data.length){
            int[] newArray = new int[2 * size];
            copyToNewArray(data, newArray);
            data = newArray;
        }
        insertValueAtIndex(value, size);
    }

    private void copyToNewArray(int[] data, int[] newArray){
        for(int i=0; i<data.length; i++) newArray[i] = data[i];
    }

    public void deleteByIndex(int index) {
        if(index >= size){
            throw new ArrayIndexOutOfBoundsException("index out of bounds when delete");
        }else if(index < size-1) {
            leftCopy(index, size);
        }
        data[size-1]=0;
        size--;
    }

    private void leftCopy(int index, int size){
        for(int i=index; i<size-1; i++){
            data[i] = data[i+1];
        }
    }

    public boolean deleteByValue(int value) {
        for(int i=0; i< size; i++){
            if(data[i] == value) {
                deleteByIndex(i);
                return true;
            }
        }
        return false;
    } // delete first value matching, true if value found, false otherwise

    private void rightCopy(int index, int size){
        for(int i=size; i> index; i--){
            data[i] = data[i-1];
        }
    }

    public void insertValueAtIndex(int value, int index) {
        if(index < data.length ){
            if(index < size) {
                rightCopy(index, size);
            }else if (index > size)
            {
                throw new ArrayIndexOutOfBoundsException("No Sparse Array");
            }
        }else{
            throw new ArrayIndexOutOfBoundsException("index out of bounds when insert");
        }
        data[index] = value;
        size++;
    }

    public void clear() { size = 0; data = null;}

    public int get(int index) {
        if(index >= size){
            throw new ArrayIndexOutOfBoundsException("index out of bounds when get");
        }else{
            return data[index];
        }
    } // may throw IndexOutOfBoundsException

    public int[] getSlice(int startIdx, int length) {
        if(length > 0){
            if(startIdx + length - 1 < size){
                int[] sliceArray = new int[length];
                int count=0;
                for(int i=startIdx; i< startIdx+length; i++){
                    sliceArray[count] = data[i];
                    count++;
                }
                return sliceArray;
            }else{
                throw new ArrayIndexOutOfBoundsException("index out of bounds when get slice");
            }
        }else {
            throw new ArrayIndexOutOfBoundsException("slice length must larger than 0");
        }
    } // may throw IndexOutOfBoundsException

    @Override
    public String toString() {
        String str="";
        for (int i = 0; i < data.length; i++) {
            str += String.format("%s%d%s", i == 0 ? "[" : ", ", data[i], i == data.length-1 ? "]" : "");
        }
        return str;
    } // returns String similar to: [3, 5, 6, -23]

}
