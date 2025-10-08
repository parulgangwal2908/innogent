package Day_06;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class deadlock {
  Lock Resource1 = new ReentrantLock();
  Lock Resource2 = new ReentrantLock();

  public void method1() {
    try {
      Resource1.lock();
      System.out.println("method 1 holding the resource 1");
      Thread.sleep(100);

      Resource2.lock();
      System.out.println("method 1 is holding the resource 2");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      Resource2.unlock();
      Resource1.unlock();
    }

  }

  public void method2() {
    try {
      Resource1.lock();
      System.out.println("method 2 holding the resource 1");
      Thread.sleep(100);

      Resource2.lock();
      System.out.println("method 2 is holding the resource 2");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      Resource2.unlock();
      Resource1.unlock();
    }

  }

  public static void main(String[] args) {
    deadlock dl = new deadlock();

    Thread t1 = new Thread(() -> dl.method1());
    Thread t2 = new Thread(() -> dl.method2());

    t1.start();
    t2.start();

  }

}
