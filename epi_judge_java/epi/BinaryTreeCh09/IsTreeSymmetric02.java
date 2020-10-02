package epi.BinaryTreeCh09;
import epi.BinaryTree;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class IsTreeSymmetric02 {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  /*
  Never thought about recursion method with left & right sub trees, instead used complicated lists as param
  Logic of checking null, check only current node for null, child nodes will anyway get checked in sub methods
      mixed null checking for both current node & child node, that made logic complicated
   */

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree){
    return tree == null || isSymetricChek(tree.left, tree.right);
  }

  public static boolean isSymetricChek(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right){
    if(left== null && right == null)
      return true;
    else if(left != null && right !=null) {
      return left.data == right.data &&
              isSymetricChek(left.left, right.right) &&
              isSymetricChek(left.right, right.left);
    }
    return false;
  }

  public static boolean isSymmetric4(BinaryTreeNode<Integer> tree){
    if(tree == null || (tree.left==null && tree.right ==null)){
      return true;
    }else if(tree.left== null || tree.right == null){
      return false;
    }else {
      return isSymetricChek4(tree.left, tree.right);
    }
  }

  public static boolean isSymetricChek4(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right){
    if(left.data != right.data)
      return false;

    if(left.left !=null && right.right !=null)
      return isSymetricChek4(left.left, right.right);
    else if(!(left.left == null && right.right == null))
      return false;

    if(left.right !=null && right.left !=null)
      return isSymetricChek4(left.right, right.left);
    else if(!(left.right == null && right.left == null))
      return false;

    return true;
  }

  public static boolean isSymmetric3(BinaryTreeNode<Integer> tree) {
    if(tree == null || (tree.left == null && tree.right == null))
      return true;

    if(tree.left != null && tree.right != null && tree.left.data == tree.right.data)
      return checkIfSymetric(Arrays.asList(tree.left), Arrays.asList(tree.right));
    else
      return false;
  }

  public static boolean checkIfSymetric(List<BinaryTreeNode<Integer>> left, List<BinaryTreeNode<Integer>> right){
    List<BinaryTreeNode<Integer>> nL = new LinkedList<>(), nR = new LinkedList<>();
    int size = left.size();
    for(int l=0, r= size-1;l<size && r>=  0; l++,r--){
      BinaryTreeNode<Integer> lNode= left.get(l), rNode = right.get(r);
      if(lNode.left != null && rNode.right != null && lNode.left.data == rNode.right.data){
        nL.add(lNode.left);
        nR.add(rNode.right);
      }else if(lNode.left != lNode.right){
        return false;
      }

      if(lNode.right != null && rNode.left != null && lNode.right.data == rNode.left.data){
        nL.add(lNode.right);
        nR.add(rNode.left);
      }else if(lNode.right != lNode.left){
        return false;
      }
    }

    if(nL.size() == 0)
      return true;
    else {
      Collections.reverse(nR);
      return checkIfSymetric(nL, nR);
    }
  }
















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

  public static boolean isSymmetric2(BinaryTreeNode<Integer> tree) {

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
