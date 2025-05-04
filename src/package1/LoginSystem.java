package package1;
import java.util.*;


        
class OddNumberPrinter implements Runnable {
    private final MultithreadingSimulation obj;

    public OddNumberPrinter(MultithreadingSimulation obj) {
        this.obj = obj;
    }

    public void run() {
        obj.generateOddNumbers();
    }
}

class EvenNumberPrinter implements Runnable {
    private final MultithreadingSimulation obj;

    public EvenNumberPrinter(MultithreadingSimulation obj) {
        this.obj = obj;
    }

    public void run() {
        obj.generateEvenNumbers();
    }
}

public class MultithreadingSimulation {
    public static final int N = 10;
    private int count = 1;

    public void generateEvenNumbers() {
        synchronized (this) {
            while (count < N) {
                while (count % 2 == 1) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Even Number thread : " + count);
                count++;
                notify();
            }
        }
    }

    public void generateOddNumbers() {
        synchronized (this) {
            while (count < N) {
                while (count % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Odd Number thread : " + count);
                count++;
                notify();
            }
        }
    }

    public static void main(String[] args) {
        MultithreadingSimulation sharedObject = new MultithreadingSimulation();

        Thread evenThread = new Thread(new EvenNumberPrinter(sharedObject));
        Thread oddThread = new Thread(new OddNumberPrinter(sharedObject));

        evenThread.start();
        oddThread.start();
    }
}

