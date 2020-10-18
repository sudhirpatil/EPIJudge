package epi.BST14;

import epi.BstNode;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class KLargestValuesInBst14_3 {
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")

  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    return recFindKLargestInBst(tree, k, new ArrayList<Integer>());
  }

  public static List<Integer> recFindKLargestInBst(BstNode<Integer> tree, int k, List<Integer> list){
    if(tree != null && list.size() < k){
      list = recFindKLargestInBst(tree.right, k, list);
      if(list.size() == k)
        return list;
      list.add(tree.data);
      list = recFindKLargestInBst(tree.left, k, list);
    }

    return list;
  }
  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
