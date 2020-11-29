package epi.StackQueue8;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CircularQueue8_7 {

  public static class Queue {
    // ds array, head, tail pointer
    // rotate if head/tail > size
    // check size tail+1% != head
    // resize? copy from first to last in new array starting from index 0
    Integer[] circular;
    int head =-1, tail =-1;
    public Queue(int capacity) { circular = new Integer[capacity];}

    public void resize(){
      Collections.rotate(Arrays.asList(circular),  -head); // inplace rotation of array elements
      int queSize = size();
      circular = Arrays.copyOf(circular, queSize * 2);
      head = 0;
      tail = queSize-1;
    }

    public void resizeOld(){
      Integer[] tempArr = new Integer[circular.length + circular.length];
      int tempHead = head, tempTail = tail, newTail = -1;
      while (tempHead != tempTail){
        tempArr[++newTail] = circular[tempHead];
        tempHead = (tempHead + 1) % circular.length;
      }
      if(tempHead != -1 && tempHead == tempTail){
        tempArr[++newTail] = circular[tempHead];
        head = 0;
        tail = newTail;
        circular = tempArr;
      }
    }

    public void enqueue(Integer x) {
      int index = (tail + 1)%  circular.length;
      if(index == head){
        resize();
        index = (tail + 1)%  circular.length;
      }

      circular[index] = x;
      tail = index;
      if(head == -1) head = 0;
      return;
    }

    public Integer dequeue() {
      int data = 0;
      if(head != -1){
        data = circular[head];
        if(head != tail) {
          head = (head + 1) % circular.length;
        }else{
          head = -1;
          tail = -1;
        }
      }
      return data;
    }

    public int size() {
      if(head == -1) return 0;
//      else return (tail+1+ circular.length -head) % circular.length;
      else if (tail >= head) return tail - head + 1;
      else return circular.length - head + tail + 1;
    }

    @Override
    public String toString() {
      return "head:"+head+" , tail:"+tail+" array:"+circular;
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
