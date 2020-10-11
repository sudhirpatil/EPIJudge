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
    /*
    like quick sort separate large & small array based on pivot
    pick larger or smaller arr based on pivot index & kth largest
     */
    if(A.size() == 0)
      return  0;

    k--;
    int first = 0, last = A.size()-1;
    int pValue = 0;
    while (first <= last){
      // create sub arr
      //get pivot & swap to last
      int pIndex = first + (last-first)/2;
      pValue = A.get(pIndex);
      Collections.swap(A,pIndex, last);
      int tempLast = last -1;
      //loop & create sub array
      for(int i = first; i <= last-1 && i<= tempLast;){
        if(A.get(i) < pValue){
          Collections.swap(A, i, tempLast--);
        }else
          i++;
      }
      // swap back first element of right half with last elem
      pIndex = tempLast+1;
      Collections.swap(A, pIndex, last);
      //
      //select sub array or return
      if(pIndex == k){
        break;
      }else if(pIndex > k){
        last = pIndex -1;
      }else {
        first = pIndex + 1;
      }
    }
    return pValue;
  }

















  public static int findKthLargest2(int k, List<Integer> A) {
    /*
    Like quick sort partition in to 2 arrays larger & smaller
    if pivot = k , return the element, if not then choose higher or lower part & repeat
     */
    k--;
    int start = 0, end = A.size() -1;
    int pValue = A.get(end/2);
    while (start<=end){
      int pivotInd = start + (end-start)/2;
      pValue = A.get(pivotInd);

      Collections.swap(A, pivotInd, end);
      int endTmp=end -1;
      for(int i= start; i<=end -1 && i<= endTmp; ){
        if(A.get(i) < pValue){
          Collections.swap(A, i, endTmp--);
        }else
          i++;
      }
      pivotInd = endTmp +1;
      Collections.swap(A, pivotInd, end);

      if (pivotInd == k){
        break;
      }else if(pivotInd > k){
        end = pivotInd -1;
      }else {
        start = pivotInd + 1;
      }
    }

    return  pValue;
  }

  public static int findKthLargest1(int k, List<Integer> A) {
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
