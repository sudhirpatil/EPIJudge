package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortAlmostSortedArray {

  public static List<Integer>
  sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
    // min priority queue with size k
    // add k elements from iterator
    // while queue empty, get min add to list, add nex item from iterator to heap

    PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
    for(int i=0; i< k; i++){
      if(sequence.hasNext()) minHeap.offer(sequence.next());
    }
    List<Integer> output = new ArrayList<>();
    while (!minHeap.isEmpty()){
      output.add(minHeap.poll());
      if(sequence.hasNext()) minHeap.offer(sequence.next());
    }
    return output;
  }

  @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
  public static List<Integer>
  sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
    return sortApproximatelySortedData(sequence.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortAlmostSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
