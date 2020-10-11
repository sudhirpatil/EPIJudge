package epi.Searching11;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.List;

public class SearchEntryEqualToIndex11_2 {
/*
Difficulty in if condition logic for index <> value[index] :
  how to think clearly for if conditions very frequent mistake?
    write down with sample numbers, wherever confusion.
 */
  public static int searchEntryEqualToItsIndex(List<Integer> A) {
    int start = 0, end = A.size() -1;
    while (start <= end){
      int mid = start + (end-start)/2;
      if(mid == A.get(mid))
        return mid;
      else if(mid < A.get(mid)){
        end = mid -1;
      }else
        start = mid +1;
    }
    return -1;
  }

  @EpiTest(testDataFile = "search_entry_equal_to_index.tsv")
  public static void searchEntryEqualToItsIndexWrapper(TimedExecutor executor,
                                                       List<Integer> A)
      throws Exception {
    int result = executor.run(() -> searchEntryEqualToItsIndex(A));

    if (result != -1) {
      if (A.get(result) != result) {
        throw new TestFailure("Entry does not equal to its index");
      }
    } else {
      for (int i = 0; i < A.size(); ++i) {
        if (A.get(i) == i) {
          throw new TestFailure("There are entries which equal to its index");
        }
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchEntryEqualToIndex.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
