package day07binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTree_JunitTest {

    @Test
    public void genericTreeIntStr_assertSucceed() {
        BinaryTree<Integer,String> tree = new BinaryTree<>();
        tree.put(1, "Test");
        assertTrue(tree.getValueByKey(1).equals("Test"));
    }

}