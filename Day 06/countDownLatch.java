package Day_06;

import java.util.concurrent.CountDownLatch;

class Services extends Thread {
  String ServiceName;
  CountDownLatch cl;

  Services(String ServiceName, CountDownLatch cl) {
    this.ServiceName = ServiceName;
    this.cl = cl;
  }

  @Override
  public void run() {
    try {
      System.out.println(ServiceName + "started");
      Thread.sleep(2000);
      System.out.println(ServiceName + "completed");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      cl.countDown();
    }
  }
}

public class countDownLatch {
  public static void main(String[] args) throws InterruptedException {
    CountDownLatch cl = new CountDownLatch(3);
    Services s1 = new Services("loading configuration", cl);
    Services s2 = new Services("connecting to the database", cl);
    Services s3 = new Services("setup", cl);

    s1.start();
    s2.start();
    s3.start();

    try {
      cl.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("all services are completed");
  }

}
