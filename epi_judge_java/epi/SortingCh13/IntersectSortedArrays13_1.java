package epi.SortingCh13;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays13_1 {
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    int i=0,j=0;
    List<Integer> list = new ArrayList<>();
    while (i<A.size() && j<B.size()){
      int aVal = A.get(i), bVal = B.get(j);
      if(i>0 && aVal == A.get(i-1)){
        i++;
        continue;
      }
      if(j>0 && bVal == B.get(j-1)){
        j++;
        continue;
      }

      if(aVal == bVal){
        list.add(aVal);
        i++; j++;
      }else if(aVal < bVal){
        i++;
      }else {
        j++;
      }
    }
    return list;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
