package epi.BST14;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeABst14_01 {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    if(tree == null)
      return true;

    if(tree.left != null && tree.left.data > tree.data )
      return false;
    if(tree.right != null && tree.right.data <= tree.data)
      return false;

    return isBinaryTreeBST(tree.left) && isBinaryTreeBST(tree.right);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
