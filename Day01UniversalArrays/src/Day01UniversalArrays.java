import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Day01UniversalArrays {

    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
	// write your code here
        input2DArray();

        System.out.println();
        System.out.println();
        int[] result = findDuplicates(new int[]{7,1,2, 0,3,4,5}, new int[]{8,7,1,3, 0, 5,7,3,5});
        for(int i=0; i< result.length; i++){
                System.out.printf("%s%d",i==0? "" : ",", result[i]);
        }

        int[][] a1 = new int[][]{new int[]{5,7,8,2,1}, new int[]{1,3,0,5,7, 9}};
        int[][] a2 = new int[][]{new int[]{6,9,3}, new int[]{8,9,0,1,4,5}, new int[]{3, 6}};
        System.out.println();
        int[] result2d = findDuplicates(a1, a2);
        for(int i=0; i< result2d.length; i++){
            System.out.printf("%s%d",i==0? "" : ",", result2d[i]);
        }

        System.out.println();
        result2d = removeDuplicates(findDuplicates(a1, a2));
        for(int i=0; i< result2d.length; i++){
                System.out.printf("%s%d",i==0? "" : ",", result2d[i]);
        }
    }

    private static void input2DArray(){

        boolean flag = true;

        while(flag){
            try{
                System.out.println("Please input the number of rows of 2D Array");
                int row = console.nextInt();

                if(row > 0) {
                    int[][] array2d = new int[row][];
                    for (int i = 0; i < row; i++) {
                        System.out.printf("%nRow %d: ",(i+1));
                        array2d[i] = inputArray();
                    }
                    flag = false;
                    printArray(array2d);
                }
            }catch(InputMismatchException ex){
                System.out.println("Please input valid integer number");
                flag = false;
            }
        }
    }

    private static int[] inputArray(){

        boolean flag = true;

        while(flag){
            try{
                System.out.print("\nPlease input the length of Array: ");
                int length = console.nextInt();

                if(length > 0) {
                    int[] array = new int[length];
                    for (int i = 0; i < array.length; i++) {
                        System.out.printf("Enter value of element%d: ",(i + 1));
                        array[i] = console.nextInt();
                    }
                    flag = false;
                    return array;
                }
            }catch(InputMismatchException ex){
                System.out.println("Please input valid integer number");
                flag = false;
            }
        }
        return new int[0];
    }

    private static void printArray(int[] data, int[] digits) {
        String format = "";
        for (int i = 0; i < data.length; i++) {
            format = "%" + digits[i] + "d";
            System.out.printf("%s"+format, i == 0 ? "" : ",", data[i]);
        }
    }

    private static int maxColumn(int[][] data2d){
        int maxColumn = data2d[0].length;
        for(int i=1; i< data2d.length; i++) {
            if (data2d[i].length > maxColumn) {
                maxColumn = data2d[i].length;
            }
        }
        return maxColumn;
    }

    private static void printArray(int[][] data2d) {
        int max = maxColumn(data2d);
        int[] maxDigit = new int[max];
        for(int i =0; i< max; i++){
            int[] column = columnArray(data2d, i);
            maxDigit[i] = maxDigits(column);
            System.out.printf("%n column %d max digit: %d", i,maxDigit[i]);
        }
        System.out.println();
        for(int i =0; i< data2d.length; i++){
            System.out.println();
            printArray(data2d[i], maxDigit);
        }
    }

    private static int maxDigits(int[] data){
        int max = String.valueOf(data[0]).length();
        System.out.println("initial max: " + max);
        for (int i = 1; i < data.length; i++) {
            int digit = String.valueOf(data[i]).length();
            System.out.println(" digit: " + digit);
            if( digit > max){
                max = digit;
            }
        }
        return max;
    }

    private static int[] columnArray(int[][] data2d, int column){
        int[] columnArray = new int[maxColumn(data2d)];
        for(int i=0; i<data2d.length; i++){
            for(int j=0; j<maxColumn(data2d); j++){
                if(data2d[i].length>column){
                    columnArray[j] = data2d[i][column];
                }else{
                    columnArray[j] = 0;
                }
            }
        }
        return columnArray;
    }

    private static int[] findDuplicates(int[] a1, int[]a2) {

        int[] find = new int[Math.min(a1.length, a2.length)];
        int count=0;
        if(a1.length >= a2.length){
            for(int i=0; i< a2.length; i++){
                for(int j=0; j< a1.length; j++){
                    if(a2[i]==a1[j]){
                        find[count]=a2[i];
                        count++;
                        break;
                    }
                }
            }
        }else{
            for(int i=0; i< a1.length; i++){
                for(int j=0; j< a2.length; j++){
                    if(a1[i]==a2[j]){
                        find[count]=a1[i];
                        count++;
                        break;
                    }
                }
            }
        }

        int[] result = new int[count];
        for(int i=0; i<count; i++){
            result[i] = find[i];
        }
        return result;
    }

    private static int[] findDuplicates(int[][] a1, int[][] a2){

        int[][][] find = new int[a1.length][a2.length][0];
        for(int i=0; i<a1.length; i++){
            for(int j=0; j<a2.length; j++){
                find[i][j] = removeDuplicates(findDuplicates(a1[i],a2[j]));
            }
        }

        int[] set = new int[100];
        int count=0;
        for(int i =0; i< find.length; i++){
            for(int j =0; j< find[i].length; j++){
                for(int k =0; k< find[i][j].length; k++){
                    set[count] = find[i][j][k];
                    count++;
                }
            }
        }
        int[] result = new int[count];
        for(int i=0; i<count; i++){
            result[i] = set[i];
        }
        return result;
    }

    public static int[] removeDuplicates(int[] data) {

        Arrays.sort(data);
        int[] temp = new int[data.length];
        int[] result = new int[0];
        if(data.length !=0){
            temp[0]= data[0];
            int count=1;
            int j=1;
            for(int i=0; i< data.length-1; i++){
                if(j< data.length){
                    if(data[i]!=data[j]){
                        temp[count]=data[j];
                        count++;
                    }
                    j++;
                }
            }

            result = new int[count];
            for(int i=0; i< result.length; i++){
                result[i] = temp[i];
            }
        }
        return result;
    }

}
