package epi.heapsCh10;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SortIncreasingDecreasingArray2 {
  @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")

  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
    // Find out increase & decrease point, store them in class & collection
    // Treat each k items as different array, Use priority queue to find min among sub arrays
    List<Integer> sorted = new ArrayList<>();

    class ListMeta{
      int startIndex;
      int endIndex;
      boolean isIncreasing;
      int size;//?
    }
    List<ListMeta> listMeta = new ArrayList<>();



    return null;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortIncreasingDecreasingArray2.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
