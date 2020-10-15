package epi.HashCh12;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class SmallestSubarrayCoveringSet12_06 {

  // Represent subarray by starting and ending indices, inclusive.
  private static class Subarray {
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph,
                                                         Set<String> keywords) {
    /*
    LinkedHashMap as insertion LRU
    on each insert if map size == set size, get array index of first element in the map
    if diff first & current is > existing diff then update
     */

    Subarray arr = new Subarray(0, 0);
    LinkedHashMap<String , Integer> map = new LinkedHashMap<>(keywords.size());
    for(int i=0; i< paragraph.size(); i++){
      String str = paragraph.get(i);
      if(keywords.contains(str)){
        map.remove(str);
        map.put(str, i);
        if(keywords.size() == map.size() &&
                ((arr.start == 0 && arr.end == 0) ||
                        arr.end - arr.start > i - getFirst(map))){
          arr.start = getFirst(map);
          arr.end = i;
        }
      }
    }
    return arr;
  }

  public static int getFirst(LinkedHashMap<String, Integer> map){
    return map.entrySet().iterator().next().getValue();
  }

  @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
  public static int findSmallestSubarrayCoveringSetWrapper(
      TimedExecutor executor, List<String> paragraph, Set<String> keywords)
      throws Exception {
    Set<String> copy = new HashSet<>(keywords);

    Subarray result = executor.run(
        () -> findSmallestSubarrayCoveringSet(paragraph, keywords));

    if (result.start < 0 || result.start >= paragraph.size() ||
        result.end < 0 || result.end >= paragraph.size() ||
        result.start > result.end)
      throw new TestFailure("Index out of range");

    for (int i = result.start; i <= result.end; i++) {
      copy.remove(paragraph.get(i));
    }

    if (!copy.isEmpty()) {
      throw new TestFailure("Not all keywords are in the range");
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestSubarrayCoveringSet.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
