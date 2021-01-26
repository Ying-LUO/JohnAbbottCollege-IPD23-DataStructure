import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListArray_JunitTest {


    @Test
    public void constructor_AssertSucceed(){
        LinkedListArray<String> linkedListArray = new LinkedListArray<String>();
        assertEquals(linkedListArray.getSize(), 0);
    }

    @Test
    public void addNewValueInitially_AssertSucceed(){
        LinkedListArray<String> linkedListArray = new LinkedListArray<String>();
        linkedListArray.add("1str");
        assertEquals(linkedListArray.getSize(), 1);
        assertTrue(linkedListArray.getValue(0).equals("1str"));
    }

    @Test
    public void addNewValueAfter_AssertSucceed(){
        LinkedListArray<String> linkedListArray = new LinkedListArray<String>();
        linkedListArray.add("1str");
        linkedListArray.add("2str");
        assertEquals(linkedListArray.getSize(), 2);
        assertTrue(linkedListArray.getValue(0).equals("1str"));
        assertTrue(linkedListArray.getValue(1).equals("2str"));
    }

    @Test
    public void getValueByIndex_AssertExceptionThrown(){
        LinkedListArray<String> linkedListArray = new LinkedListArray<String>();
        linkedListArray.add("1str");
        linkedListArray.add("2str");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedListArray.getValue(2);});
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedListArray.getValue(-2);});
    }

    @Test
    public void insertAtIndex_AssertExceptionThrown(){
        LinkedListArray<String> linkedListArray = new LinkedListArray<String>();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedListArray.insertValueAtIndex(1, "1str");});
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedListArray.insertValueAtIndex(-1, "1str");});
        linkedListArray.insertValueAtIndex(0, "1str");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedListArray.insertValueAtIndex(2, "2str");});
    }

    @Test
    public void insertAtIndex_AssertSucceed(){
        LinkedListArray<String> linkedListArray = new LinkedListArray<String>();
        linkedListArray.insertValueAtIndex(0, "1str");  // insert initial
        linkedListArray.insertValueAtIndex(1, "2str");  // insert at the end
        linkedListArray.insertValueAtIndex(2, "3str");
        assertEquals(linkedListArray.getSize(), 3);
        assertArrayEquals(linkedListArray.toArray(String), new String[]{ "1str", "2str", "3str"});
        linkedListArray.insertValueAtIndex(2, "4str"); // insert in the middle
        assertEquals(linkedListArray.getSize(), 4);
        assertArrayEquals(linkedListArray.toArray(String), new String[]{ "1str", "2str", "4str", "3str"});
        linkedListArray.insertValueAtIndex(0, "0str"); // insert at the beginning
        assertEquals(linkedListArray.getSize(), 5);
        assertArrayEquals(linkedListArray.toArray(String), new String[]{ "0str", "1str", "2str", "4str", "3str"});
    }

    @Test
    public void toString_AssertSucceed(){
        LinkedListArray<String> linkedListArray = new LinkedListArray<String>();
        linkedListArray.add("1str");
        assertTrue(linkedListArray.toString().equals("[1str]"));
        linkedListArray.add("2str");
        assertTrue(linkedListArray.toString().equals("[1str, 2str]"));
        linkedListArray.add("3str");
        assertTrue(linkedListArray.toString().equals("[1str, 2str, 3str]"));
        linkedListArray.add("4str");
        assertTrue(linkedListArray.toString().equals("[1str, 2str, 3str, 4str]"));
        linkedListArray.add("5str");
        assertTrue(linkedListArray.toString().equals("[1str, 2str, 3str, 4str, 5str]"));
        linkedListArray.add("6str");
        assertTrue(linkedListArray.toString().equals("[1str, 2str, 3str, 4str, 5str, 6str]"));
    }

    @Test
    public void deleteByIndexInSize_AssertSucceed(){
        LinkedListArray<String> linkedListArray = new LinkedListArray<String>();
        linkedListArray.add("1str");
        linkedListArray.add("2str");
        assertEquals(linkedListArray.getSize(), 2);
        assertTrue(linkedListArray.toString().equals("[1str, 2str]"));
        linkedListArray.deleteByIndex(0);   // delete the first one
        assertEquals(linkedListArray.getSize(), 1);
        assertTrue(linkedListArray.toString().equals("[2str]"));
        linkedListArray.deleteByIndex(0);   // delete the last one
        assertEquals(linkedListArray.getSize(), 0);
        linkedListArray.add("3str");
        linkedListArray.add("4str");
        linkedListArray.add("5str");
        assertTrue(linkedListArray.toString().equals("[3str, 4str, 5str]"));
        linkedListArray.deleteByIndex(1);   // delete the middle one
        assertTrue(linkedListArray.toString().equals("[3str, 5str]"));
    }

    @Test
    public void deleteByIndexOverSize_AssertExceptionThrown(){
        LinkedListArray<String> linkedListArray = new LinkedListArray<String>();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedListArray.deleteByIndex(2);});    // positive index
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedListArray.deleteByIndex(0);});   // initial index
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedListArray.deleteByIndex(-1);});   // negative index
        linkedListArray.add("1str");
        assertEquals(linkedListArray.getSize(), 1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedListArray.deleteByIndex(2);});    // positive index
        linkedListArray.deleteByIndex(0);
        assertEquals(linkedListArray.getSize(), 0);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedListArray.deleteByIndex(-1);});   // negative index
    }

    @Test
    public void deleteByValue_AssertSucceed(){
        LinkedListArray<String> linkedListArray = new LinkedListArray<String>();
        linkedListArray.add("1str");
        linkedListArray.add("2str");
        assertEquals(linkedListArray.getSize(), 2);
        assertTrue(linkedListArray.toString().equals("[1str, 2str]"));
        assertTrue(linkedListArray.deleteByValue("1str"));   // delete the first one
        assertEquals(linkedListArray.getSize(), 1);
        assertTrue(linkedListArray.toString().equals("[2str]"));
        assertTrue(linkedListArray.deleteByValue("2str"));   // delete the last one
        assertEquals(linkedListArray.getSize(), 0);
        linkedListArray.add("3str");
        linkedListArray.add("4str");
        linkedListArray.add("5str");
        assertTrue(linkedListArray.toString().equals("[3str, 4str, 5str]"));
        assertTrue(linkedListArray.deleteByValue("4str"));   // delete the middle one
        assertTrue(linkedListArray.toString().equals("[3str, 5str]"));
        assertTrue(linkedListArray.deleteByValue("5str"));   // delete the last one
        assertTrue(linkedListArray.toString().equals("[3str]"));
        linkedListArray.add("6str");
        linkedListArray.add("7str");
        assertFalse(linkedListArray.deleteByValue("5str"));   // delete the wrong string
        assertTrue(linkedListArray.toString().equals("[3str, 6str, 7str]"));
        assertFalse(linkedListArray.deleteByValue(""));   // delete the empty string
        assertTrue(linkedListArray.toString().equals("[3str, 6str, 7str]"));
        assertFalse(linkedListArray.deleteByValue(null));   // delete the null string
        assertTrue(linkedListArray.toString().equals("[3str, 6str, 7str]"));
        assertTrue(linkedListArray.getValue(2).equals("7str"));
    }

}