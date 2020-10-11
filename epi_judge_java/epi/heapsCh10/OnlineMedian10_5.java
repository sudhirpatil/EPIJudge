package epi.heapsCh10;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class OnlineMedian10_5 {
  /*
  Heaps can be used sorting, specially when we require middle elements, by splitting maxHeap for smaller numbers &
    minHeap for larger numbers

  logic of maxHeap & minHeap was in mind but could not figure out solution,
        :write cases & try it on paper, walk through one complete cycle for all elements
        Distracted, not focussed on solving problem : Use pomodoro diligently
   */
  public static List<Double> onlineMedian(Iterator<Integer> sequence) {
    List<Double> list = new ArrayList<>();
    PriorityQueue<Double> smallMaxH = new PriorityQueue<>((x,y) -> Double.compare(y,x)),
      largeMinH = new PriorityQueue<>();

    while (sequence.hasNext()){
      Double item = (double) sequence.next();
      smallMaxH.add(item);
      largeMinH.add(smallMaxH.poll());
      if(smallMaxH.size() < largeMinH.size()){
        smallMaxH.add(largeMinH.poll());
      }
      list.add(smallMaxH.size() == largeMinH.size() ? ((0.5) *(smallMaxH.peek() + largeMinH.peek())): smallMaxH.peek());
    }
    return list;
  }














  public static List<Double> onlineMedian1(Iterator<Integer> sequence) {
    PriorityQueue<Integer> smallMaxH = new PriorityQueue<>((x,y) -> Integer.compare(y,x)),
            largeMinH = new PriorityQueue<>();
    List<Double> list = new ArrayList<>();
    while (sequence.hasNext()){
      int item = sequence.next();
      if(smallMaxH.isEmpty() && largeMinH.isEmpty()) {
        smallMaxH.add(item);
        list.add((double)item);
      }
      else{
        if(smallMaxH.peek() >= item) {
          smallMaxH.add(item);
        } else{
          largeMinH.add(item);
        }

        if(Math.abs(smallMaxH.size() - largeMinH.size()) >1){
          if(smallMaxH.size() > largeMinH.size()){
            largeMinH.add(smallMaxH.poll());
          }else {
            smallMaxH.add(largeMinH.poll());
          }
        }

        if(smallMaxH.size() == largeMinH.size()){
          list.add(((double)smallMaxH.peek() + largeMinH.peek())/2);
        }else if(smallMaxH.size() > largeMinH.size()){
          list.add((double)smallMaxH.peek());
        }else{
          list.add((double)largeMinH.peek());
        }
      }
    }
    return list;
  }
  @EpiTest(testDataFile = "online_median.tsv")
  public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
    return onlineMedian(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "OnlineMedian.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
