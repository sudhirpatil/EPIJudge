package epi.HashCh12;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries12_05 {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")

  // Object with last 2 occurence, diff with 2 occurence, word
  // Hash map of Obj key is word
  // ref for min distance obj
  // iterate all words update map if distance is less than prev global min update it

  // OR just map of word => last occured index & every word compare and update global min
  public static int findNearestRepetition(List<String> paragraph) {
    /*
    hashmap with word , index position.
    new word update minDistance & update map
     */
    int min =Integer.MAX_VALUE;
    Map<String, Integer> map = new HashMap<>();
    for(int i =0; i< paragraph.size();i++){
      String str = paragraph.get(i);
      if(map.containsKey(str)){
        min = Math.min(min, i - map.get(str));
      }
      map.put(str, i);
    }
    return min == Integer.MAX_VALUE ? -1 : min;
  }




  public static int findNearestRepetition1(List<String> paragraph) {
    Map<String, Integer> map = new HashMap<>();
    int gDist = Integer.MAX_VALUE;

    for(int i=0; i< paragraph.size(); i++){
      String str = paragraph.get(i);
      if(map.containsKey(str))
        gDist = Math.min(gDist, i - map.get(str));
      map.put(str, i);
    }
    return gDist == Integer.MAX_VALUE ? -1 : gDist;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
