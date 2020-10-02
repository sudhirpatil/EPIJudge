package epi.BinaryTreeCh09;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced09_1 {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  /*
  Start calculating height from lower leaf levels, compare left & right height at each levels
  key is to 2 values in return height & isBalanced and return immidiately if tree is not balanced at each stage

  one way is indicating height value as -ve (as height cannot go below 0)
  or Return object with 2 values height & isBalanced

  Create a Object when 2 or more values to be returned,
  e.g height and isBalanced status for this

  How should i have done this faster?
  returning 2 values is still possible, should have thought through more & implemented
  missed returning object/tuple for 2 different values
   */

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    /*in order traversal, return 0 on null
    if return -ve them immidiately return
    if left & right height compare > 1 return -ve
    or else return Max(left, right) + 1
     */
    return balanceCheck(tree) <0 ? false : true;
  }

  public static int balanceCheck(BinaryTreeNode<Integer> tree){
    if(tree == null) return 0;
    int left = balanceCheck(tree.left);
    if(left < 0) return left;

    int rt = balanceCheck(tree.right);
    if(rt < 0) return rt;

    return Math.abs(left - rt) > 1 ? -1 : Math.max(left, rt)+1;
  }




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

  public static boolean isBalanced1(BinaryTreeNode<Integer> tree) {
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
