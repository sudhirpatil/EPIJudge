package epi.Searching11;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class KthLargestInArray11_8 {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(A, 1) returns 3, findKthLargest(A, 2) returns 2,
  // findKthLargest(A, 3) returns 1, and findKthLargest(A, 4) returns -1.

  // select 1st element as pivot , divide array to >pivot & <pivot
  // while smaller element than pivot in first part & loop while greater element than pivot in 2nd part starting from end
  // wrap this under bigger loop
  // if index of pivot is < k , call function again list (pivot + 1, end)
  // if index of pivot == k return pivot
  // else call function again list (0, pivot-1)

  /*
  index of kth largest is size() - k
  Loop till pIndex == k or start < end
    get pindex where start-pindex < pivot < pindex + 1 ,end
    get pindex, change start & end based on pindex <=> k

    - did not think about kth largest index is size -k : write down sample case, think critically, go through the question again
    - lot of mistakes in comparisons, pindex <> k, start <=end : ??
    - mistakes in comparing indexes with values
    - Stuck at logic implementation : don't club everything in on single loop, writing function helps moving logic,
      replace pivot value with end, reduces work by not moving all the elements to index -1
   */
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {
    if(k> A.size()) return -1;

    int pIndex = 0;
    int start = 0, end = A.size()-1;
    k = A.size() -k;
    while (start <= end){
      pIndex = getPivotIndex(A, start, end);
      if(pIndex== k) break;
      else if(pIndex > k) end = pIndex -1;
      else start = pIndex+1;
    }
    return A.get(pIndex);
  }

  /*
  Pivot move to the end, reduces complexity in managing indexes
  move start -> end, replace A[pIndex], A[i] if A[i] < pivot, [start-pindex] are all smaller than pivot
  move end/pivot value to correct index position
   */
  public static int getPivotIndex(List<Integer>A, int start, int end){
    int pIndex = start;
    int pivot = A.get(pIndex);
    Collections.swap(A, pIndex, end);
    while (start<end){
      if(A.get(start)<pivot){
        Collections.swap(A, pIndex, start);
        pIndex++;
      }
      start++;
    }
    Collections.swap(A, pIndex, end);
    return pIndex;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
