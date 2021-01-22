import java.util.Scanner;

public class Day02CachingFibonacci {

    static Scanner console = new Scanner(System.in);
    public static void main(String[] args) {
	// write your code here
        FibCached fib = new FibCached();
        System.out.println("Input a number of fibs:");
        int n = console.nextInt();
        System.out.printf("%n%dth of Fibs is %d, Counts of Compute: %d, Counts in Cache: %d", n, fib.getNthFib(n), fib.getCountOfFibsComputed(), fib.getCountOfFibsInCache());
        System.out.printf("%nFibs in Cache: %s", fib.toString());

        System.out.println("\nInput a number of fibs in Cache:");
        n = console.nextInt();
        System.out.printf("%n%dth of Fibs is %d, Counts of Compute: %d, Counts in Cache: %d", n, fib.getNthFib(n), fib.getCountOfFibsComputed(), fib.getCountOfFibsInCache());
        System.out.printf("%nFibs in Cache: %s", fib.toString());
    }

}
