package epi.heapsCh10;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SortedArraysMerge10_1 {
  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

  /*
  Missed duplicate numbers : think about edge cases and possible values, write down sample cases
  wrong index sizes & comparisons : Careful when using size of Array & using indexes to accces, as total size < last index
  Took long time to come up with soln

  Think about creating metadata object when metadata reference needed in an item, like in this case index of list for the item
  Use iterator when we need to track/keep reference of index of current item
   */
  public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
    List<Integer> list = new ArrayList<>();
    PriorityQueue<Index> pQue = new PriorityQueue<>(sortedArrays.size(), (i1, i2) -> {
      return Integer.compare(i1.data, i2.data);
    });

    for(int i=0; i< sortedArrays.size(); i++){
      pQue.add(new Index(sortedArrays.get(i).get(0), i, 0));
    }

    while (!pQue.isEmpty()){
      Index ind = pQue.poll();
      list.add(ind.data);
      if(ind.li+1 < sortedArrays.get(ind.index).size()){
        pQue.add(new Index(sortedArrays.get(ind.index).get(ind.li+1), ind.index, ind.li + 1));
      }
    }
    return list;
  }

  public static class Index {
    Integer data;
    int index;
    int li;

    public Index(Integer d, int i, int li){
      this.data = d;
      this.index = i;
      this.li = li;
    }
  }






  public static List<Integer> mergeSortedArrays1(List<List<Integer>> sortedArrays) {
    List<Integer> output = new ArrayList<>();
    // Object with value & index of list in sortedArray
    // Store this object in heap, index to identify list & put next element in list to heap
    // Convert sublist to iterator to keep track of current item.
    class Element{
      int index;
      int value;
      public Element(int i, int v){
        index = i;
        value = v;
      }
    }

    List<Iterator<Integer>> iterators = new ArrayList<>();
    for(List<Integer> list: sortedArrays) iterators.add(list.iterator());

    PriorityQueue<Element> minHeap = new PriorityQueue<>(sortedArrays.size(), (e1, e2) -> e1.value - e2.value);
    // add one item from each list to heap
    int index = 0;
    for(Iterator<Integer> iterator : iterators){
      if(iterator.hasNext()) minHeap.offer(new Element(index++, iterator.next()));
    }

    while (!minHeap.isEmpty()){
      Element elm = minHeap.poll();
      Iterator<Integer> iterator = iterators.get(elm.index);
      if(iterator.hasNext()) minHeap.offer(new Element(elm.index, iterator.next()));
      output.add(elm.value);
    }
    return output;
  }

  public static List<Integer> mergeSortedArraysNonOptimal(List<List<Integer>> sortedArrays) {
    int listCount = sortedArrays.size();
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(listCount);
    List<Integer> output = new ArrayList<>();
    // Get an element from each of List and insert to heap
    // when heap.size >= num lists, poll and add to output list
    // iterating to all list finished, move remaining elements from heap -> list
    // How to handle duplicates?
    // one iteration in big list , fill heap with one element from each
    // store min element & add to output list,
    //    iterate through big list if current > min add to heap & move to next list
    //    if element == min, go through list & add elements to output list
    // Maintain index for each list in the biglist

    Map<Integer, Integer> indexes = new HashMap<>();
    // minHeap with one element from each list & update map with current index for each list
    for(int i = 0; i < listCount; i++) {
      if (!sortedArrays.get(i).isEmpty()) {
        minHeap.offer(sortedArrays.get(i).get(0));
        indexes.put(i, 0);
      }
    }

    // Iterate till index map has value, make sure remove index from map when current Index == list size
    while (!indexes.isEmpty() && !minHeap.isEmpty()){
      int min = minHeap.poll();
      output.add(min);
      // go through each sublist, if duplicates i.e same value as min, put them in to output & update index
      for(int j=0; j < listCount; j++){
        List<Integer> subList = sortedArrays.get(j);
        // make sure map contains index, so that end not reached for that sublist, eliminate finished lists for better performance
        // index for sublist should b smaller than list size
        if(indexes.containsKey(j) && indexes.get(j) < subList.size()-1 ) {
          int currIndex = indexes.get(j);
          do {
            currIndex++;
            int current = subList.get(currIndex);
            // update map with curr index
            indexes.put(j, currIndex);
            if (current <= min) {
              // go deep in the sublist, till current > min
              output.add(current);
            } else {
              // add current to heap and move to next sublist
              minHeap.offer(current);
              break;
            }
          }while (currIndex < subList.size() -1);

          // all elements in sublist already iterated, remove sublist from index map
          if(indexes.get(j) == subList.size() - 1){
            indexes.remove(j);
          }
        }
      }
    }

    while (!minHeap.isEmpty()){
      output.add(minHeap.poll());
    }

    return output;
  }

  public static List<Integer>
  mergeSortedArraysOld(List<List<Integer>> sortedArrays) {
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
