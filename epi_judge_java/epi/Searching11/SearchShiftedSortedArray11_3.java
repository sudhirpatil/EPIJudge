package epi.Searching11;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchShiftedSortedArray11_3 {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

  public static int searchSmallest(List<Integer> A) {
    int pIndex = 0;
    if(A.get(0) > A.get(A.size()-1)){
      int start = 0, end = A.size()-1;
      // find the max element
      while (start<=end){
        pIndex = (end-start)/2 + start;
        int pivot = A.get(pIndex);
        // pivot > start, start= pIndex , check if value of pindex + 1 < pindes
        // if pivot < start, end = pindex -1
        if(pivot>= A.get(start)){
          if(A.get(pIndex+1) < pivot) {
            pIndex++;
            break;
          }
          start = pIndex + 1;
        }else {
          end = pIndex -1;
        }
      }
    }
    return pIndex;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
