import java.util.Random;

public class Day03ArrayListOwnImpl {

    public static void main(String[] args) {
	// write your code here
        Random rand = new Random();
        CustomArrayOfInts customArray = new CustomArrayOfInts();

        for(int i=0; i<15; i++){
            customArray.add(rand.nextInt(500));
        }

        System.out.println(customArray.toString());

    }
}
