package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  /*
  recursive method takes node & height
  returns height on node null
   */
  public static int checkIfBalanced1(BinaryTreeNode<Integer> tree){
    if(tree == null)
      return 0;

    int lHeight = checkIfBalanced1(tree.left);
    int rHeight = checkIfBalanced1(tree.right);
    if(lHeight == -1 || rHeight == -1){
      return -1;
    }else if(Math.abs(lHeight - rHeight) > 1){
      return -1;
    }else {
      return Math.max(lHeight, rHeight) + 1;
    }
  }

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    int height = checkIfBalanced1(tree);
    if(height == -1)
      return false;
    else
      return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
