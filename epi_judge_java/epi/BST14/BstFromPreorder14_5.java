package epi.BST14;

import epi.BstNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BstFromPreorder14_5 {
  @EpiTest(testDataFile = "bst_from_preorder.tsv")

  public static BstNode<Integer>
  rebuildBSTFromPreorder(List<Integer> preorderSequence) {

    return rebuildBST(preorderSequence, 0, preorderSequence.size()-1);
  }

  public  static BstNode<Integer> rebuildBST(List<Integer> preorder , int start, int end){
    if(start>end)
      return null;
    else if(start == end)
      return new BstNode<Integer>(preorder.get(start));

    BstNode<Integer> node = new BstNode<>(preorder.get(start));
    int mid = start+1;
    while (mid <= end && preorder.get(mid) < preorder.get(start)){
      mid++;
    }
    node.left = rebuildBST(preorder, start+1, mid -1);
    node.right = rebuildBST(preorder, mid, end);
    return node;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromPreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}