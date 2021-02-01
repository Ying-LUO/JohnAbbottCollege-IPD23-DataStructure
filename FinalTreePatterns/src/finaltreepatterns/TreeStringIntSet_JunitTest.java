package finaltreepatterns;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TreeStringIntSet_JunitTest {

    @Test
    public void addNewPair_AssertSucceed() throws DuplicateValueException {
        TreeStringIntSet treeSet = new TreeStringIntSet();
        treeSet.add("Test", 0);
        treeSet.add("Test", 1);
        treeSet.add("Test", 2);
        treeSet.add("Test", 3);
        treeSet.add("Test", 4);
        treeSet.add("Test", 5);
        treeSet.add("Another", 5);
        treeSet.add("Another", 6);
        treeSet.add("New", 6);
        treeSet.add("New", 7);

        // getValuesByKey
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5}, treeSet.getValuesByKey("Test").stream().mapToInt(Integer::intValue).toArray());
        assertArrayEquals(new int[]{5, 6}, treeSet.getValuesByKey("Another").stream().mapToInt(Integer::intValue).toArray());
        assertArrayEquals(new int[]{6, 7}, treeSet.getValuesByKey("New").stream().mapToInt(Integer::intValue).toArray());
        // Empty value list return if key not exist
        assertArrayEquals(new int[]{}, treeSet.getValuesByKey("NotExist").stream().mapToInt(Integer::intValue).toArray());

        // getKeysContainingValue
        assertArrayEquals(new String[]{"Test", "Another"}, treeSet.getKeysContainingValue(5).toArray());
        assertArrayEquals(new String[]{"New", "Another"}, treeSet.getKeysContainingValue(6).toArray());
        // Empty key list return if value not exist
        assertArrayEquals(new String[]{}, treeSet.getKeysContainingValue(10).toArray());

    }

    @Test
    public void addNewPair_AssertExceptionThrow() throws DuplicateValueException {
        TreeStringIntSet treeSet = new TreeStringIntSet();
        treeSet.add("Test", 0);
        // throws DuplicateValueException if this key already contains such value
        Assertions.assertThrows(DuplicateValueException.class, () -> {
            treeSet.add("Test", 0);});
    }

    @Test
    public void getAllKeys_AssertSucceed() throws DuplicateValueException {
        TreeStringIntSet treeSet = new TreeStringIntSet();
        treeSet.add("Test", 0);
        treeSet.add("Another", 1);
        treeSet.add("New", 2);
        // containsKey
        assertTrue(treeSet.containsKey("Test"));
        assertTrue(treeSet.containsKey("Another"));
        assertTrue(treeSet.containsKey("New"));
        // key not exist
        assertFalse(treeSet.containsKey("notExist"));
        // getAllKeys
        assertArrayEquals(new String[]{"Test", "New", "Another"}, treeSet.getAllKeys().toArray());
    }

    @Test
    public void iterator_AssertSucceed() throws DuplicateValueException {
        TreeStringIntSet treeSet = new TreeStringIntSet();
        treeSet.add("Test", 1);
        treeSet.add("Another", 1);
        treeSet.add("New", 1);
        for(Pair pair : treeSet) assertTrue(pair.value.equals(1));
    }

}