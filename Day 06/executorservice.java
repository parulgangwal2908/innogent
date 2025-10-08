package Day_06;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

class countLines implements Callable<Integer> {
  File file;

  countLines(File file) {
    this.file = file;
  }

  @Override
  public Integer call() throws Exception {
    int lines = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      while (br.readLine() != null) {
        lines++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("the number of lines in the file " + file.getName() + " - " + lines);
    return lines;
  }

}

public class executorservice {
  public static void main(String[] args) {
    File dir = new File("C:\\Program Files");
    File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
    if (files == null || files.length == 0) {
      System.out.println("no files found");
      return;
    }
    ExecutorService es = Executors.newFixedThreadPool(3);
    List<Future<Integer>> total = new ArrayList<>();

    for (File file : files) {
      countLines cl = new countLines(file);
      Future<Integer> future = es.submit(cl);
      total.add(future);

    }
    int grandTotal = 0;
    for (Future<Integer> f : total) {
      try {
        grandTotal += f.get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
      System.out.println("the total number of lines in all the text files - " + grandTotal);

    }
    es.shutdown();

  }
}
