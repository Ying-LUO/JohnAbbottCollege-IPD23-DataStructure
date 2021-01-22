import java.util.InputMismatchException;
import java.util.Scanner;

public class Day02ArraySearch {

    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {

        int[][] data2D = {
                {1, 3, 6},
                {7, 1, 2, 3},
                {8, 3, 2, 1, 0, 7},
                {1, 7, 1, 9},
        };
        boolean flag=true;
        try{
            while(flag){
                System.out.println("Please enter the row & col:");
                int row = console.nextInt();
                int col = console.nextInt();

                System.out.printf("Value of element at (row%d,col%d) is %d %n", row, col, getIfExists(data2D, row, col));
                System.out.printf("Sum of surrounding elements at (row%d,col%d) is %d %n", row, col, sumOfCross(data2D, row, col));
                System.out.printf("Smallest Sum of element is (row%d,col%d)", smallestSum(data2D)[0], smallestSum(data2D)[1]);
                print2D(data2D);
                System.out.println();
                print2D(createSumsArray(data2D));
                flag=false;
            }
        }catch(InputMismatchException ex){
            System.out.println("Exception: Please input valid number");
            flag=false;
        }
    }

    private static int getIfExists(int[][] data, int row, int col) {
        //assume data is not jagged array
        try{
            return data[row][col];
        }catch (IndexOutOfBoundsException ex){
            return 0;
        }
    }

    private static int sumOfCross(int[][] data, int row, int col) {
        //assume data is not jagged array
        return getIfExists(data,row,col)+ getIfExists(data,row-1,col) + getIfExists(data,row+1,col)
                + getIfExists(data, row, col-1) + getIfExists(data,row,col+1);
    }

    private static void print2D(int[][] data) {
        for(int row=0; row< data.length; row++){
            System.out.println();
            for(int col=0; col < data[row].length; col++){
                System.out.printf("%3s%2d", "",  data[row][col]);
            }
        }
    }

    private static int[][] duplicateEmptyArray2D(int[][] orig2d){
        int[][] result = new int[orig2d.length][];
        for(int row=0; row< orig2d.length; row++){
            int rowLength = orig2d[row].length;
            result[row] = new int[rowLength];
        }
        return result;
    }

    private static int[] smallestSum(int[][] data){
        int minRow=0; int minCol=0;
        int minSum=sumOfCross(data, 0, 0);
        for(int row=0; row<data.length; row++){
            for(int col=0; col<data[row].length; col++){
                if(sumOfCross(data, row, col)<minSum){
                    minSum = sumOfCross(data, row, col);
                    minRow = row;
                    minCol = col;
                }
            }
        }
        int[] minCoordinate = new int[2];
        minCoordinate[0]=minRow;
        minCoordinate[1]=minCol;
        return minCoordinate;
    }

    private static int[][] createSumsArray(int[][] data){
        int[][] data2Dsums= duplicateEmptyArray2D(data);
        for(int row=0; row<data.length; row++){
            for(int col=0; col<data[row].length; col++){
                data2Dsums[row][col] = sumOfCross(data, row, col);
            }
        }
        return data2Dsums;
    }

}
