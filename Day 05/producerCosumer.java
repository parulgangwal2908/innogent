package threads;

import java.util.ArrayList;
import java.util.Scanner;

class Bucket {
  int size;
  ArrayList<Integer> list;

  public Bucket(int size) {
    this.size = size;
    this.list = new ArrayList<>();
  }

  public synchronized void produce(int item) {
    while (list.size() == size) {
      try {
        System.out.println("Bucket full, producer waiting...");
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    list.add(item);
    System.out.println("Produced - " + item);
    notifyAll();
  }

  public synchronized void consume() {
    while (list.isEmpty()) {
      try {
        System.out.println("Bucket empty, consumer waiting...");
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    int item = list.remove(0);
    System.out.println("Consumed - " + item);
    notifyAll();
  }
}

class Producer extends Thread {
  Bucket bucket;
  int iterations;

  public Producer(Bucket bucket, int iterations) {
    this.bucket = bucket;
    this.iterations = iterations;
  }

  @Override
  public void run() {
    for (int i = 0; i < iterations; i++) {
      bucket.produce(i);
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Producer finished producing " + iterations + " items.");
  }
}

class Consumer extends Thread {
  Bucket bucket;
  int iterations;

  public Consumer(Bucket bucket, int iterations) {
    this.bucket = bucket;
    this.iterations = iterations;
  }

  @Override
  public void run() {
    for (int i = 0; i < iterations; i++) {
      bucket.consume();
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Consumer finished consuming " + iterations + " items.");
  }
}

public class producerConsumer {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter the size of the bucket: ");
    int size = sc.nextInt();

    System.out.print("Enter the number of iterations: ");
    int iterations = sc.nextInt();
    sc.close();

    Bucket bucket = new Bucket(size);

    Thread producer = new Producer(bucket, iterations);
    Thread consumer = new Consumer(bucket, iterations);

    producer.start();
    consumer.start();
  }
}
