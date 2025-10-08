package Day_06;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class readWrite {
  ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  Lock readLock = readWriteLock.readLock();
  Lock writeLock = readWriteLock.writeLock();
  List<Integer> list = new ArrayList<>();

  public void add(int value) {
    writeLock.lock();
    try {
      list.add(value);
      System.out.println("value added - " + value);
    } finally {
      writeLock.unlock();
    }
  }

  public int get(int index) {
    readLock.lock();
    try {
      return list.get(index);
    } finally {
      readLock.unlock();
    }
  }

  public static void main(String[] args) {
    readWrite rw = new readWrite();
    rw.add(1);
    rw.add(2);
    rw.add(3);
    System.out.println("reading the value at the index 2" + rw.get(2));

  }

}
