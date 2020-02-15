package epi.StackQueue8;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class QueueWithMax8_9 {

  /**
   * Could not think of solution from maintaining max 
   * Stuck with limitations of api offer, poll, push, pop . Learn other api also helpful in some cases
   */

  Deque<Integer> queue = new ArrayDeque<>();
  // to keep max element with count
  Deque<Integer> maxQue = new ArrayDeque<>();

  public void enqueue(Integer x) {
    while (!maxQue.isEmpty() && maxQue.peekLast().compareTo(x) <= 0){
      maxQue.removeLast();
    }
    maxQue.addLast(x);
    queue.addLast(x);
    return;
  }

  public Integer dequeue() {
    Integer element = queue.removeFirst();
    if(element == maxQue.peekFirst()) maxQue.removeFirst();
    return element;
  }

  public Integer max() {
    return maxQue.peekFirst();
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
      QueueWithMax8_9 q = new QueueWithMax8_9();

      for (QueueOp op : ops) {
        switch (op.op) {
        case "QueueWithMax":
          q = new QueueWithMax8_9();
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
