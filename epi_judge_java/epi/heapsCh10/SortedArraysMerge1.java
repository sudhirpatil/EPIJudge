package epi.heapsCh10;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge1 {




  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {
    List<Integer> mergedList = new ArrayList<>();
    // metadata class for value with pointer to list & array index
    class ValueMetadata {
      int value;
      int listIndex;
      int arrayIndex;

      public ValueMetadata(int value, int listIndex, int arrayIndex) {
        this.value = value;
        this.listIndex = listIndex;
        this.arrayIndex = arrayIndex;
      }
    }

    PriorityQueue<ValueMetadata> pQueue = new PriorityQueue<>(sortedArrays.size(), (i1, i2) -> Integer.compare(i1.value, i2.value)); //Comparator.comparing(Person::getName)

    // initialize pqueue add first element from each array
    for(int i=0; i< sortedArrays.size(); i++){
      pQueue.add(new ValueMetadata(sortedArrays.get(i).get(0), i, 0));
    }

    // exatract min element & loop till all arrays size reached
    while (!pQueue.isEmpty()){
      ValueMetadata valueMeta = pQueue.poll();
      mergedList.add(valueMeta.value);
      List<Integer> list = sortedArrays.get(valueMeta.listIndex);
      int arrayIndex = valueMeta.arrayIndex+1;
      if(arrayIndex < list.size()){
        pQueue.add(new ValueMetadata(list.get(arrayIndex), valueMeta.listIndex, arrayIndex));
      }
    }

    return mergedList;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge1.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
