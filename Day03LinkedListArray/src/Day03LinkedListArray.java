import java.util.Random;

public class Day03LinkedListArray {

    public static void main(String[] args) {
	// write your code here

        Random rand = new Random();
        LinkedListArrayOfStrings LinkedListArray = new LinkedListArrayOfStrings();

        for(int i=0; i<15; i++){
            LinkedListArray.add(rand.nextInt(500)+"str");
        }

        System.out.println(LinkedListArray.toString());
        String[] array = LinkedListArray.toArray();
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%s%s%s", i == 0 ? "[" : ", ", array[i], i == array.length-1 ? "]" : "");
        }
    }
}
