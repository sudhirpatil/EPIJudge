package epi.BinaryTreeCh09;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeSymmetric02 {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean checkSymmetric(BinaryTreeNode<Integer> sub1, BinaryTreeNode<Integer> sub2){
    if(sub1 == null && sub2 == null)
      return true;
    else if(sub1 == null || sub2 == null)
      return false;
    else if(sub1.data == sub2.data) {
      boolean left = checkSymmetric(sub1.left, sub2.right);
      if (!left)
        return false;
      boolean right = checkSymmetric(sub1.right, sub2.left);
      if (!right)
        return false;

      return true;
    }else{
      return false;
    }

  }

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {

    return checkSymmetric(tree, tree);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
