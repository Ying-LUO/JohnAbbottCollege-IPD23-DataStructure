package day07binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTree_JunitTest {

    @Test
    public void genericTreeIntStr_assertSucceed() {
        BinaryTree<Integer,String> tree = new BinaryTree<>();
        tree.put(1, "Test");
        assertTrue(tree.getValueByKey(1).equals("Test"));
        tree.put(1, "AnotherTest");  // replace value in the same key
        tree.put(2, "NewTest");
        assertTrue(tree.getValueByKey(1).equals("AnotherTest"));
        assertTrue(tree.getValueByKey(2).equals("NewTest"));
    }

    @Test
    public void genericTreeStrInt_assertSucceed() {
        BinaryTree<String,Integer> tree = new BinaryTree<>();
        tree.put("Test", 1231223);
        assertTrue(tree.getValueByKey("Test").equals(1231223));
        tree.put("Test", 789789);
        tree.put("NewTest", 1231223);
        assertTrue(tree.getValueByKey("Test").equals(789789));
        assertTrue(tree.getValueByKey("NewTest").equals(1231223));
    }

    @Test
    public void genericTreeStrStr_assertSucceed() {
        BinaryTree<String,String> tree = new BinaryTree<>();
        tree.put("Test", "James");
        assertTrue(tree.getValueByKey("Test").equals("James"));
        tree.put("Test", "Peter");
        tree.put("NewTest", "Lawrence");
        assertTrue(tree.getValueByKey("Test").equals("Peter"));
        assertTrue(tree.getValueByKey("NewTest").equals("Lawrence"));
    }

    @Test
    public void genericTreeIntInt_assertSucceed() {
        BinaryTree<Integer,Integer> tree = new BinaryTree<>();
        tree.put(1, 14355);
        assertTrue(tree.getValueByKey(1).equals(14355));
        tree.put(1, 100);
        tree.put(-9, -9);
        assertTrue(tree.getValueByKey(1).equals(100));
        assertTrue(tree.getValueByKey(-9).equals(-9));
    }

    @Test
    public void genericTreeIntObject_assertSucceed() {
        BinaryTree<Integer, Person> tree = new BinaryTree<>();
        Person test = new Person("Test", "Person");
        Person another = new Person("Another", "Person");
        Person newUser = new Person("NewUser", "Person");
        tree.put(1, test);
        assertTrue(tree.getValueByKey(1).equals(test));
        tree.put(1, another);
        tree.put(-9, newUser);
        assertTrue(tree.getValueByKey(1).equals(another));
        assertTrue(tree.getValueByKey(-9).equals(newUser));
    }

}