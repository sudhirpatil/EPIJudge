package epi.StackQueue8;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

// Not working
public class QueueWithMax8_1 {
  Deque<Integer> queue = new ArrayDeque<>();
  public void enqueue(Integer x) {
    queue.push(x);
    return;
  }
  public Integer dequeue() {
    return queue.poll();
  }
  public Integer max() {
    Integer max = Integer.MIN_VALUE;
    for(Iterator<Integer> iter = queue.iterator();iter.hasNext();){
      max = Math.max(max, iter.next());
    }
    return max;
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "queue_with_max.tsv")
  public static void queueTest(List<QueueOp> ops) throws TestFailure {
    try {
      QueueWithMax8_1 q = new QueueWithMax8_1();

      for (QueueOp op : ops) {
        switch (op.op) {
        case "QueueWithMax":
          q = new QueueWithMax8_1();
          break;
        case "enqueue":
          q.enqueue(op.arg);
          break;
        case "dequeue":
          int result = q.dequeue();
          if (result != op.arg) {
            throw new TestFailure("Dequeue: expected " +
                                  String.valueOf(op.arg) + ", got " +
                                  String.valueOf(result));
          }
          break;
        case "max":
          int s = q.max();
          if (s != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
          }
          break;
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "QueueWithMax.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
