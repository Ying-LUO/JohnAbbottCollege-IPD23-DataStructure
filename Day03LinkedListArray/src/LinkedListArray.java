public class LinkedListArray<T> {

    private class Container {
        Container next;
        T value;
    }

    private Container start;
    private int size;

    public LinkedListArray(){
        this.start = new Container();
        this.size = 0;
    }

    public void add(T value) {
        insertValueAtIndex(size, value);  // add any element in the end of list, e.g. initially 0
    }

    public T getValue(int index) {
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException("Index out of Bound");
        }else{
            return getContainer(index).value;
        }
    }

    private Container getContainer(int index){
        Container temp = new Container();
        temp = start;
        if (index != 0) {
            for (int i = 1; i <= index; i++) {
                temp = temp.next;
            }
        }
        return temp;
    }

    public void insertValueAtIndex(int index, T value) {
        Container newCont = new Container();
        newCont.value = value;

        if(index == size && size !=0){   // insert at the end
            newCont.next = null;
            getContainer(index-1).next = newCont;
            size++;
            return;
        }
        if(index == 0 && size == 0) {  // initial add
            newCont.next = null;
            start = newCont;
            size++;
            return;
        }
        if(index == 0 && size != 0){  // insert at the beginning
            newCont.next = start;
            start = newCont;
            size++;
            return;
        }
        if(index < size && index > 0){  // insert in the middle
            newCont.next = getContainer(index);
            getContainer(index-1).next = newCont;
            size++;
        }else{
            throw new IndexOutOfBoundsException("Cannot find element at index when insert: " + index);
        }
    }

    public void deleteByIndex(int index) {
        if(index == 0 && size ==1){   //delete the only one
            start = null;
            size--;
        }else if(index == 0 && size >1){   //delete the first one
            start = getContainer(1);
            size--;
        }else if(index == size -1 && size > 1){  //delete the last one
            getContainer(index-1).next = null;
            size--;
        }else if(index > 0 && index < size-1){   // delete the middle one
            getContainer(index-1).next = getContainer(index+1);
            size--;
        }else{
            throw new IndexOutOfBoundsException("Cannot find element at index when delete: " + index);
        }
    }

    public boolean deleteByValue(T value) {
        for(int i=0; i<size; i++){
            if(getValue(i).equals(value)){
                deleteByIndex(i);
                return true;
            }
        }
        return false;
    } // delete first value found

    public int getSize() {
        return size;
    }


    @Override
    public String toString() {
        String str="";
        for (int i = 0; i < size; i++) {
            str += String.format("%s%s%s", i == 0 ? "[" : ", ", getContainer(i).value.toString(), i == size-1 ? "]" : "");
        }
        return str;
    } // comma-separated values list similar to: [5,8,11]

    public T[] toArray(T[] templateType) {
        T[] str= (T[])java.lang.reflect.Array.newInstance(templateType.getClass().getComponentType(), size);
        for (int i = 0; i < size; i++) {
            str[i] = getContainer(i).value;
        }
        return str;
    } // could be used for Unit testing

}
