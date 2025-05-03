package package1;
import java.util.*;
public class MultithreadingSimulation {

    private static final Object lock = new Object();
    private static int number = 1;

    static class Printer extends Thread {
        private boolean isOdd;

        Printer(boolean isOdd) {
            this.isOdd = isOdd;
        }

        public void run() {
            while (number <= 100) {
                synchronized (lock) {
                    if ((number % 2 == 0) == !isOdd) {
                        System.out.println(number++);
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Printer(true).start();  
        new Printer(false).start(); 
    }
}
