import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CustomHashMapStringString_JunitTest {

    @Test
    public void constructor_AssertSucceed(){
        CustomHashMapStringString customHashmap = new CustomHashMapStringString();
        assertEquals(customHashmap.getSize(), 0);
    }

    @Test
    public void toString_AssertSucceed(){
        CustomHashMapStringString customHashmap = new CustomHashMapStringString();
        customHashmap.putValue("testKey", "testValue");
        customHashmap.putValue("newKey", "newValue");
        assertTrue(customHashmap.toString().equals("[ Key newKey => Val newValue ][ Key testKey => Val testValue ]"));
    }

    @Test
    public void getAllKeys_AssertSucceed(){
        CustomHashMapStringString customHashmap = new CustomHashMapStringString();
        customHashmap.putValue("testKey", "testValue");
        customHashmap.putValue("newKey", "newValue");
        customHashmap.putValue("againKey", "againValue");
        customHashmap.putValue("tripKey", "tripValue");
        customHashmap.putValue("tripKey", "changeValue");
        customHashmap.putValue("anotherKey", "anotherValue");
        String[] sortedKeys = customHashmap.getAllKeys();
        Arrays.sort(sortedKeys);
        assertArrayEquals(sortedKeys, new String[]{"againKey", "anotherKey", "newKey", "testKey", "tripKey"});
    }

    @Test
    public void getValue_AssertExceptionThrown(){
        CustomHashMapStringString customHashmap = new CustomHashMapStringString();
        customHashmap.putValue("testKey", "testValue");
        assertEquals(customHashmap.getSize(), 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customHashmap.getValue("notkey");});
    }

    @Test
    public void addNewValue_AssertSucceed(){
        CustomHashMapStringString customHashmap = new CustomHashMapStringString();
        customHashmap.putValue("testKey", "testValue");
        assertEquals(customHashmap.getSize(), 1);
        assertTrue(customHashmap.getValue("testKey").equals("testValue"));
        customHashmap.putValue("newKey", "newValue");
        assertEquals(customHashmap.getSize(), 2);
        assertTrue(customHashmap.getValue("newKey").equals("newValue"));
        customHashmap.putValue("againKey", "againValue");
        assertEquals(customHashmap.getSize(), 3);
        assertTrue(customHashmap.getValue("againKey").equals("againValue"));
        customHashmap.putValue("tripKey", "tripValue");
        assertEquals(customHashmap.getSize(), 4);
        assertTrue(customHashmap.getValue("tripKey").equals("tripValue"));
        customHashmap.putValue("tripKey", "changeValue");
        assertEquals(customHashmap.getSize(), 4);
        assertTrue(customHashmap.getValue("tripKey").equals("changeValue"));
        customHashmap.putValue("anotherKey", "anotherValue");
        assertEquals(customHashmap.getSize(), 5);
        assertTrue(customHashmap.getValue("anotherKey").equals("anotherValue"));
        assertTrue(customHashmap.getValue("againKey").equals("againValue"));
    }

    @Test
    public void deleteByKeyExistedAtLast_AssertSucceed(){
        CustomHashMapStringString customHashmap = new CustomHashMapStringString();
        customHashmap.putValue("testKey", "testValue");
        customHashmap.putValue("newKey", "newValue");
        customHashmap.putValue("againKey", "againValue");
        customHashmap.putValue("tripKey", "tripValue");
        customHashmap.putValue("tripKey", "changeValue");
        customHashmap.putValue("anotherKey", "anotherValue");
        assertTrue(customHashmap.deleteByKey("anotherKey"));
        assertEquals(customHashmap.getSize(), 4);
        assertTrue(customHashmap.getValue("againKey").equals("againValue"));
    }

    @Test
    public void deleteByKeyExistedInTheMiddle_AssertSucceed(){
        CustomHashMapStringString customHashmap = new CustomHashMapStringString();
        customHashmap.putValue("testKey", "testValue");
        customHashmap.putValue("newKey", "newValue");
        customHashmap.putValue("againKey", "againValue");
        customHashmap.putValue("tripKey", "tripValue");
        customHashmap.putValue("tripKey", "changeValue");
        customHashmap.putValue("anotherKey", "anotherValue");
        assertTrue(customHashmap.deleteByKey("againKey"));
        assertEquals(customHashmap.getSize(), 4);
        assertTrue(customHashmap.getValue("anotherKey").equals("anotherValue"));
    }

    @Test
    public void deleteByKeyNotExisted_AssertExceptionThrown(){
        CustomHashMapStringString customHashmap = new CustomHashMapStringString();
        customHashmap.putValue("testKey", "testValue");
        customHashmap.putValue("newKey", "newValue");
        customHashmap.putValue("againKey", "againValue");
        customHashmap.putValue("tripKey", "tripValue");
        customHashmap.putValue("tripKey", "changeValue");
        customHashmap.putValue("anotherKey", "anotherValue");
        assertEquals(customHashmap.getSize(), 5);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customHashmap.deleteByKey("notkey");});
    }

}