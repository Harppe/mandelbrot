import java.util.*;
/**
 * Pseudo-random generator
 * 
 * coded this without a guide via ideas from this youtube video
 * https://www.youtube.com/watch?v=ovJcsL7vyrk
 * 
 * 
 * @author mark w
 * @version delta blue 42
 */
public class RNG
{
    // instance variables - replace the example below with your own
    private static double START_CONDITION = 0.1;
    private static final double GROWTH_RATE = 4;
    private static final int ITERS = 10000;
    public ArrayList<Integer> numbers;
    private int currentInt = 100;
    public static void main() {
        System.out.print("\f");
        double time = System.currentTimeMillis();
        double startSeed = ((time%100)/100);
        System.out.println("Start Seed: " + startSeed);
        RNG rng = new RNG(startSeed);
        LinkedList nList = rng.cloneTrunc(rng.numbers,100,10);
        System.out.println(nList);
    }

    public LinkedList cloneTrunc(List l, int start, int size) {
        LinkedList copy = new LinkedList();
        for (int i = start; i<start+size;i++) {
            copy.add(l.get(i));
        }
        return copy;
    }

    public int getRandomInt() {
        int rInt = numbers.get(currentInt);
        currentInt++;
        return rInt;
    }

    boolean truncateSelectStr(String str, int cutOff) {
        if (str.length()>cutOff) {
            return true;
        }
        return false;
    }

    /**
     * Constructor for objects of class Main
     */
    public RNG(double seed)
    {
        int buckets[] = new int[10];
        int buckets2[] = new int[10];
        numbers = new ArrayList<Integer>(ITERS);
        START_CONDITION = seed;
        for (int i = 0; i<ITERS; i++) {
            double temp = mandelbrot(i);
            String tempStr = ""+temp;
            if (tempStr!=null&&tempStr.length()>0) {
                for (int x =16;x>6;x--) {
                    if (truncateSelectStr(tempStr,x)) {
                        tempStr = tempStr.substring(x-1,x);
                        break;
                    }
                }
                if (!(tempStr.contains(".")||tempStr.contains("-")||tempStr.contains("E"))) {
                    int tempInt = Integer.parseInt(tempStr);
                    numbers.add(tempInt);
                    buckets[tempInt]++;
                }
            }
        }
        for (int i = 0; i<buckets.length; i++) {
            System.out.println(i+" : " + ((double)buckets[i])/ITERS);
        }
    }

    /**
     * finds x{n} = x*r*(1-x)
     */
    public double mandelbrot(int iters)
    {
        double temp = START_CONDITION;
        for (int i = 0; i<iters; i++) {
            temp = GROWTH_RATE*temp*(1-temp);
        }
        return temp;
    }
}
