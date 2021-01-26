package midtermprioritystackgeneric;

import java.lang.reflect.Array;

class TestMe<T> {
    // Example: how to create and return a generic array
    public T[] makeArray(Class<T> type, T val0, T val1, T val2) {
        T[] array = (T[]) Array.newInstance(type, 3);
        array[0] = val0;
        array[1] = val1;
        array[2] = val2;
        return array;
    }
}

public class MidtermPriorityStackGeneric {

    public static void main(String[] args) {        
        // you can put code here if you like but it WILL NOT BE GRADED
        
        PriorityStack<String> instance = new PriorityStack<>();
        instance.push("Jerry");
        instance.push("Terry");
        instance.push("Larry");
        instance.push("Barry");
        Object [] result = instance.toArrayReversed(String.class);
        Object [] expected = {"Jerry", "Terry", "Larry", "Barry" };
        //Assert.assertArrayEquals(expected, result);
        
        /*
        PriorityStack<String> instance = new PriorityStack<>();
        instance.push("Jerry", false);
        instance.push("Terry");
        instance.push("Larry", true);
        instance.push("Barry");
        instance.push("Eva", true);
        instance.push("Martha");
        instance.push("Ruth");
        instance.reorderByPriority();
        System.out.println("Expected: [Eva:P,Larry:P,Ruth:N,Martha:N,Barry:N,Terry:N,Jerry:N]");
        System.out.println("Result  : " + instance.toString());
        */
        /*
        PriorityStack<String> instance = new PriorityStack<>();
        instance.push("Jerry");
        instance.push("Terry");
        instance.push("Barry");        
        instance.push("Larry", true);
        String res1 = instance.popPriority();
        System.out.println("Expected Larry, got " + res1);
        /*
        /*
        PriorityStack<String> instance = new PriorityStack<>();
        instance.push("Jerry");
        instance.push("Terry");
        instance.push("Larry", true);
        instance.push("Barry");
        int size = instance.getSize();
        */
        // assertEquals("[Barry:N,Larry:P,Terry:N,Jerry:N]",instance.toString());      
        
        /* // code demonstrating returning an array of a specific generic type
        TestMe<String> testMe = new TestMe<>();
        String [] result = testMe.makeArray(String.class, "Jerry", "Barry", "Larry");
        for (String s : result) {
            System.out.println("Value: " + s);
        } */
    }
    
}
