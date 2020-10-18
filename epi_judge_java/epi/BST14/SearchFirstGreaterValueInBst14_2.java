package epi.BST14;
import epi.BstNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SearchFirstGreaterValueInBst14_2 {

  public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
                                                       Integer k) {
    if(tree == null)
      return null;

    BstNode<Integer> sub = null;
    while(tree != null){
      if(tree.data > k){
        sub = tree;
        tree = tree.left;
      }else {
        tree = tree.right;
      }
    }
    return tree;
  }

  public static BstNode<Integer> findFirstGreaterThanK1(BstNode<Integer> tree,
                                                       Integer k) {
    if(tree == null)
      return null;

    BstNode<Integer> node = findFirstGreaterThanK(tree.left, k);
    if(node !=null)
      return node;
    if(tree.data > k)
      return tree;
    node = findFirstGreaterThanK(tree.right, k);
    return node;
  }

  @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
  public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
                                                 Integer k) {
    BstNode<Integer> result = findFirstGreaterThanK(tree, k);
    return result != null ? result.data : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
