package epi.StackQueue8;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class StackWithMax8_1 {

  Deque<List<Integer>> queue = new ArrayDeque<>();

  public void enqueue(Integer x) {
    Integer max;
    if(queue.isEmpty()){
      max = x;
    }else {
      max = Math.max(x, queue.peek().get(1));
    }

    queue.offer(Arrays.asList(x, max));
    return;
  }

  public Integer dequeue() {
    return queue.poll().get(0);
  }

  public Integer max() {
    return queue.peek().get(1);
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
      StackWithMax8_1 q = new StackWithMax8_1();

      for (QueueOp op : ops) {
        switch (op.op) {
        case "QueueWithMax":
          q = new StackWithMax8_1();
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
