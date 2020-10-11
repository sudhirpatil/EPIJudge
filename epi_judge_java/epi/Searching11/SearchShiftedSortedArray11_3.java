package epi.Searching11;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchShiftedSortedArray11_3 {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

  /*
  Struggled with end condition <= or <
  start index after the split mid or mid +1 and end mid or mid-1?

   */
  public static int searchSmallest(List<Integer> A) {
    /*
    point of circle next element is smaller than current
    binary search if start value > end Value, redo same for sub array
      both start & end set to mid to include in next sub array as it can be starting point also
    Boundary/index out of bound : missed cas of mid +1
      for index+1/-1 always check if it is going out of bounds
     */
    int start = 0, end = A.size()-1;
    while (start < end && A.get(start) >= A.get(end)){
      int mid = start + (end -start)/2;
      if(A.get(mid)> A.get(mid+1))
        return mid+1;
      else if(A.get(start) >= A.get(mid)){
        end = mid;
      }else {
        start = mid;
      }
    }
    return start;
  }


  public static int searchSmallest1(List<Integer> A) {
    int left = 0, right = A.size()-1;

    while(left<right){
      int mid = left + ((right - left)/2);
      if(A.get(mid) > A.get(right)){
        left = mid + 1;
      }else{
        right = mid;
      }
    }
    return left;
  }

  public static int searchSmallestOptimal(List<Integer> A) {
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
