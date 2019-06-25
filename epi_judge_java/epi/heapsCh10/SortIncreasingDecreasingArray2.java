package epi.heapsCh10;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SortIncreasingDecreasingArray2 {
  @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")

  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> inputList) {
    List<Integer> sorted = new ArrayList<>();

    // Find out increase & decrease point, store them in class & collection
    class SubArrayMetadata{
      int startIndex;
      int endIndex;
      boolean isIncreasing;
    }

    List<SubArrayMetadata> metaList = new LinkedList<>();
    int index =0;
    while (index<inputList.size()){
      SubArrayMetadata metadata = new SubArrayMetadata();
      if(index+1==inputList.size() || inputList.get(index) < inputList.get(index+1)){
        metadata.isIncreasing = true;
        metadata.startIndex = index;

        while (index+1<=inputList.size() && inputList.get(index) < inputList.get(index+1)){
          index++;
        }
        metadata.endIndex = index;
      }else if(index+1< inputList.size() && inputList.get(index) > inputList.get(index+1)){
        metadata.isIncreasing = true;
        metadata.startIndex = index;

        while (index+1<=inputList.size() && inputList.get(index) > inputList.get(index+1)){
          index++;
        }
        metadata.endIndex = index;
      }
      metaList.add(metadata);
    }

    // Find out increase & decrease point, store them in class & collection
    class Entry{
      int value;
      int metaIndex;

      public Entry(int value, int metaIndex) {
        this.value = value;
        this.metaIndex = metaIndex;
      }
    }
    // Using priority queue and metadalist create sorted array
    PriorityQueue<Entry> pQueue = new PriorityQueue<>(metaList.size(), (e1, e2) -> Integer.compare(e1.value, e2.value));
    for(int i=0;i<metaList.size(); i++){
      SubArrayMetadata metadata = metaList.get(i);
      int arrayIndex;
      if(metadata.isIncreasing){
        arrayIndex = metadata.startIndex;
        metadata.startIndex = arrayIndex+1;
      }else {
        arrayIndex = metadata.endIndex;
        metadata.endIndex = arrayIndex--;
      }
      pQueue.add(new Entry(inputList.get(arrayIndex), i));
      metaList.add(i, metadata);
    }

    while (!pQueue.isEmpty()){
      Entry entry = pQueue.poll();
      sorted.add(entry.value);

      SubArrayMetadata metadata = metaList.get(entry.metaIndex);
      if(metadata.startIndex <metadata.endIndex){
        int arrayIndex;
        if(metadata.isIncreasing){
          arrayIndex = metadata.startIndex;
          metadata.startIndex = arrayIndex+1;
        }else {
          arrayIndex = metadata.endIndex;
          metadata.endIndex = arrayIndex--;
        }
        pQueue.add(new Entry(inputList.get(arrayIndex), entry.metaIndex));
        metaList.add(entry.metaIndex, metadata);
      }
    }

    return sorted;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortIncreasingDecreasingArray2.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
