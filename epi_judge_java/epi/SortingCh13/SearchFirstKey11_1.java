package epi.Searching11;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class SearchFirstKey11_1 {
  @EpiTest(testDataFile = "search_first_key.tsv")

  /*
  Name variable appropriately, to avoid confusion
  re evaluate every statement , don't copy paste, think critically
   */
  public static int searchFirstOfK(List<Integer> A, int k) {
    // binary search, get mid
    // if mid valud < k , start = mid + 1
    // if mid == k, note it, end = mid -1
    // if mid > k, end = mid -1

    int start=0, end = A.size() -1, mid = 0, index = -1;
    while (start<=end){
      mid = start + (end - start)/2;
      int midValue = A.get(mid);
      if( midValue< k){
        start = mid +1;
      }else if(midValue == k) {
        index = mid;
        end = mid - 1;
      }else{
        end = mid -1 ;
      }
    }
    return index;
  }

  public static int searchFirstOfKLib(List<Integer> A, int k) {
    int index = Collections.binarySearch(A, k);
    if(index >=0 ){
      int start=0, end = index;
      int newIndex;
      while (start >= end){
        newIndex = Collections.binarySearch(A.subList(start, end ), k);
        if(newIndex < 0) break;
        index = newIndex;
        start = 0;
        end = newIndex-1;

      }
    }
    return index < 0 ? -1 : index;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
