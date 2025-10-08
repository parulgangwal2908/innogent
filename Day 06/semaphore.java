package Day_06;

import java.util.concurrent.Semaphore;

class car extends Thread {
  Semaphore s;
  String name;

  public car(Semaphore s, String name) {
    this.s = s;
    this.name = name;

  }

  @Override
  public void run() {
    try {
      System.out.println(name);
      s.acquire();
      System.out.println(name + " parked");

      Thread.sleep(2000);
      System.out.println(name + " leaving the parking");
      s.release();
    } catch (InterruptedException e) {
      e.printStackTrace();

    }
  }
}

public class semaphore {
  public static void main(String[] args) {
    Semaphore s = new Semaphore(2);
    car c1 = new car(s, "car 1");
    car c2 = new car(s, "car 2");
    car c3 = new car(s, "car 3");

    c1.start();
    c2.start();
    c3.start();

  }

}
