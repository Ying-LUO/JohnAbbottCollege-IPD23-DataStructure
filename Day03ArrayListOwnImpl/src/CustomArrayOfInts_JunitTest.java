import org.junit.jupiter.api.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayOfInts_JunitTest {

    @Test
    public void constructor_AssertSucceed(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        assertArrayEquals(customArray.data(), new int[]{ 0 });
        assertEquals(customArray.size(), 0);
        assertEquals(customArray.data().length, 1);
    }

    @Test
    public void clear_AssertSucceed_DataPointToNull(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        customArray.add(9);
        customArray.add(10);
        assertArrayEquals(customArray.data(), new int[]{ 9, 10 });
        assertEquals(customArray.size(), 2);
        assertEquals(customArray.data().length, 2);
        customArray.clear();
        assertArrayEquals(customArray.data(), null);
        assertEquals(customArray.size(), 0);
    }

    @Test
    public void toString_AssertSucceed(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        customArray.add(5);
        assertTrue(customArray.toString().equals("[5]"));
        customArray.add(5);
        assertTrue(customArray.toString().equals("[5, 5]"));
        customArray.add(5);
        assertTrue(customArray.toString().equals("[5, 5, 5, 0]"));
        customArray.add(5);
        assertTrue(customArray.toString().equals("[5, 5, 5, 5]"));
        customArray.add(5);
        assertTrue(customArray.toString().equals("[5, 5, 5, 5, 5, 0, 0, 0]"));
        customArray.add(5);
        assertTrue(customArray.toString().equals("[5, 5, 5, 5, 5, 5, 0, 0]"));
    }

    @Test
    public void addNewValueInitially_AssertSucceed(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        customArray.add(5);
        assertArrayEquals(customArray.data(), new int[]{ 5 });
        assertEquals(customArray.size(), 1);
        assertEquals(customArray.data().length, 1);
    }

    @Test
    public void addNewValueWhenDoubleSize_AssertSucceed(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        customArray.add(5);
        customArray.add(5);
        assertArrayEquals(customArray.data(), new int[]{ 5, 5 });
        assertEquals(customArray.size(), 2);
        assertEquals(customArray.data().length, 2);
    }

    @Test
    public void addNewValueWhenSizeEnough_AssertSucceed(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        customArray.add(5);
        customArray.add(5);
        customArray.add(5);
        assertArrayEquals(customArray.data(), new int[]{ 5, 5 , 5, 0});
        assertEquals(customArray.size(), 3);
        assertEquals(customArray.data().length, 4);
    }

    @Test
    public void addMoreValue_AssertSucceed(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        customArray.add(5);
        customArray.add(5);
        customArray.add(5);
        customArray.add(5);
        customArray.add(5);
        customArray.add(5);
        assertArrayEquals(customArray.data(), new int[]{ 5, 5, 5, 5, 5 , 5, 0, 0});
        assertEquals(customArray.size(), 6);
        assertEquals(customArray.data().length, 8);
    }

    @Test
    public void insertAtIndexWhenInitial_AssertSucceed(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        customArray.insertValueAtIndex(5, 0);
        assertArrayEquals(customArray.data(), new int[]{ 5 });
        assertEquals(customArray.size(), 1);
        assertEquals(customArray.data().length, 1);
    }

    @Test
    public void insertAtIndexOverSize_AssertExceptionThrown(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            customArray.insertValueAtIndex(5, 1); });  //positive
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            customArray.insertValueAtIndex(5, -1); });  //negative

    }

    @Test
    public void getOverSize_AssertExceptionThrown(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            customArray.get(3);});  //positive
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            customArray.get(-3);});  //negative
    }

    @Test
    public void insertAtIndexOverSizeBoundary_AssertExceptionThrown() {
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        customArray.insertValueAtIndex(5, 0);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            customArray.insertValueAtIndex(9, 1);  }); // insert when array length is not enough(array length 1 size 1 index 0, try to insert at index 1)
        customArray.add(5);
        customArray.add(5);
        customArray.add(5);
        customArray.add(5);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            customArray.insertValueAtIndex(9, 6); }); // insert sparse array, array length 8, size 5, index 0-4, try to insert at index 6, index 5 will be sparse
        customArray.insertValueAtIndex(9, 5); // insert into last index succeed
    }

    @Test
    public void insertAtIndexInSize_AssertSucceed(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        customArray.insertValueAtIndex(5, 0);  // insert at the beginning
        customArray.add(5);
        customArray.add(5);
        assertEquals(customArray.get(0), 5);  // test get method
        assertEquals(customArray.get(1), 5);
        assertEquals(customArray.get(2), 5);
        assertEquals(customArray.size(), 3);
        customArray.insertValueAtIndex(9, 3);  // insert at the last index
        assertArrayEquals(customArray.data(), new int[]{ 5, 5 , 5 , 9});
        assertEquals(customArray.get(3), 9);
        assertEquals(customArray.size(), 4);
        assertEquals(customArray.data().length, 4);
        customArray.add(5);
        assertArrayEquals(customArray.data(), new int[]{ 5, 5 , 5 , 9, 5, 0, 0, 0});
        customArray.insertValueAtIndex(9, 2);  // insert in the middle of index
        assertArrayEquals(customArray.data(), new int[]{ 5, 5 , 9 , 5, 9, 5, 0, 0});
    }

    @Test
    public void getSliceInSize_AssertSucceed(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        customArray.add(9);
        customArray.add(8);
        customArray.add(7);
        assertArrayEquals(customArray.getSlice(0,2), new int[]{9, 8});
        assertArrayEquals(customArray.getSlice(0,3), new int[]{9, 8, 7});
        assertArrayEquals(customArray.getSlice(1,2), new int[]{8, 7});
        assertArrayEquals(customArray.getSlice(1,1), new int[]{8});
        assertArrayEquals(customArray.getSlice(2,1), new int[]{7});
    }

    @Test
    public void getSliceOverSize_AssertExceptionThrown(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        customArray.add(9);
        customArray.add(8);
        customArray.add(7);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            customArray.getSlice(2,2); });// array length 4, size 3, index 0-2, startIndex + length > size
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            customArray.getSlice(3,1); });// startIndex > size
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            customArray.getSlice(4,1); });// startIndex > length
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            customArray.getSlice(1,0); }); // length < 0
    }

    @Test
    public void deleteByIndexOverSize_AssertExceptionThrown(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            customArray.deleteByIndex(0); }); // initial deletion

        customArray.add(9);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            customArray.deleteByIndex(1);  });// minimum deletion

        customArray.add(8);
        customArray.add(7);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            customArray.deleteByIndex(3);  }); // last index deletion

        customArray.add(8);
        customArray.add(7);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            customArray.deleteByIndex(7); }); // in middle deletion

    }

    @Test
    public void deleteByValue_AssertFalse(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        assertFalse(customArray.deleteByValue(0));
        customArray.add(9);
        assertFalse(customArray.deleteByValue(0));
        customArray.add(8);
        customArray.add(7);
        assertFalse(customArray.deleteByValue(0));
    }

    @Test
    public void deleteInSize_AssertSucceed(){
        CustomArrayOfInts customArray = new CustomArrayOfInts();
        customArray.add(9);
        customArray.deleteByIndex(0);
        assertEquals(customArray.size(), 0);
        assertEquals(customArray.data().length, 1);
        customArray.add(8);
        customArray.add(7);
        assertEquals(customArray.size(), 2);
        assertEquals(customArray.data().length, 2);   // before deletion
        customArray.deleteByIndex(0);  // delete the first element, move right element to left
        assertArrayEquals(customArray.data(), new int[]{7, 0});
        assertEquals(customArray.size(), 1);
        assertEquals(customArray.data().length, 2);   // after deletion
        customArray.add(8);
        customArray.add(7);  // add duplicate element
        assertTrue(customArray.deleteByValue(7));  // delete the first found equal-value element
        assertArrayEquals(customArray.data(), new int[]{8, 7, 0, 0});
        customArray.insertValueAtIndex(0, 1);
        customArray.insertValueAtIndex(1, 3);
        assertArrayEquals(customArray.data(), new int[]{8, 0, 7, 1});
        assertTrue(customArray.deleteByValue(1));  // delete the last element
        assertArrayEquals(customArray.data(), new int[]{8, 0, 7, 0});

    }


}