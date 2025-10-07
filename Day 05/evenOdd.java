package threads;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

class Print {
  public synchronized void printOdd(AtomicInteger counter, int max) {
    while (counter.get() < max) {
      if (counter.get() % 2 == 1) {
        System.out.println("Odd -" + counter.get());
        counter.incrementAndGet();
        notify();
      } else {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

  }

  public synchronized void printEven(AtomicInteger counter, int max) {
    while (counter.get() < max) {
      if (counter.get() % 2 == 0) {
        System.out.println("Even -" + counter.get());
        counter.incrementAndGet();
        notify();
      } else {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

  }

}

public class evenOdd {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the maximum number: ");
    int max = sc.nextInt();
    sc.close();
    AtomicInteger counter = new AtomicInteger(1);
    Print print = new Print();

    Thread oddThread = new Thread(() -> print.printOdd(counter, max));
    Thread evenThread = new Thread(() -> print.printEven(counter, max));

    oddThread.start();
    evenThread.start();

  }

}
