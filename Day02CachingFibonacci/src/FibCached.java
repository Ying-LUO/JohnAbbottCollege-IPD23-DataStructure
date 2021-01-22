import java.util.ArrayList;

public class FibCached {

    //Constructor
    public FibCached() {
        fibsCached.add(0L); // #0
        fibsCached.add(1L); // #1
    }

    private ArrayList<Long> fibsCached = new ArrayList<>(); // NOT a HashMap !
    private int fibsCompCount = 2;
    private int fibsInCache = 0;
    // in a correct caching implementation fibsCompCount will end up the same as fibsCached.size();

    public long getNthFib(int n) { return computeNthFib(n); }

    // You can find implementation online, recursive or non-recursive.
    // For 100% solution you should use values in fibsCached as a starting point
    // instead of always starting from the first two values of 0, 1.
    private long computeNthFib(int n) {
        long fib = 0;
        if(n<0){
            System.out.println("Please input valid positive number");
        }else if(n==0 || n==1){
            fib =n;
        }else{
            try {
                fib = fibsCached.get(n-1)+fibsCached.get(n-2);
                fibsInCache++;
                System.out.println("Get from cached");
            }catch(IndexOutOfBoundsException ex){
                    fib = computeNthFib(n-1) + computeNthFib( n-2);
                    fibsCached.add(fib);
                    fibsCompCount++;
                    System.out.println("Get by compute");
            }
        }
        return fib;
    }

    // You are allowed to add another private method for fibonacci generation
    // if you want to use recursive approach. I recommend non-recursive though.

    // How many fibonacci numbers has your code computed as opposed to returned cached?
    // Use this in your testing to make sure your caching actually works properly.
    public int getCountOfFibsComputed() {
        return fibsCompCount;
    }

    public int getCountOfFibsInCache() {
        return fibsInCache;
    }

    @Override
    public String toString() {
        ArrayList<String> stringFibs = new ArrayList<String>();
        for (long fib : fibsCached)
        {
            stringFibs.add(fib+"");
        }
        return String.join(";", stringFibs);
    } // returns all cached Fib values, comma-space-separated on a single line

    private ArrayList<Long> getFibsCached(){
        return fibsCached;
    }

}
