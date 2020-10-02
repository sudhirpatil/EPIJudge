package epi.StackQueue8;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CircularQueue8_7_1 {

  public static class Queue {
    int[] arr;
    int first=0, last=-1,cap=0, size=0;
    public Queue(int capacity) {
      arr = new int[capacity];
      cap = capacity;
    }
    public void enqueue(Integer x) {
      if(size == cap){
        resize();

      }
      last = (last+1)%cap;
      size++;
      arr[last] = x;
      return;
    }
    /*
    Collections.rotate(Arrays.asList()) : to more back the head 0, Arrays.asList modifies original array
    Copy subarrays using Arrays.copy
     */
    public void resize(){
      int newCap = cap * 2, f=first;
      int[] newArr = new int[newCap];
      for(int i=0; i<size;i++){
        newArr[i] = arr[f];
        f = (f+1)%cap;
      }
      first = 0;
      last = size-1;
      cap = newCap;
      arr = newArr;
    }

    public Integer dequeue() {
      if(size>0){
        int i = arr[first];
        first = (first+1) %cap;
        size--;
        return i;
      }
      return 0;
    }
    public int size() {
      return size;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      int f = first;
      for(int i=0; i<size;i++){
        sb.append(arr[f]);
        if(i!=size-1)
          sb.append(',');
        f = (f+1)%cap;
      }
      return sb.toString();
    }
  }

  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTest(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
