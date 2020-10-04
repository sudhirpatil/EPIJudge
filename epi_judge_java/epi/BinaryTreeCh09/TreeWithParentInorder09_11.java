package epi.BinaryTreeCh09;

import epi.BinaryTree;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class TreeWithParentInorder09_11 {
  /*
  Detect if traversal is up from right child

  If down move node.parent = prev, move left, add to list, move right, if right null move to parent up
  If up move to parent and is moved to parent from left node, add to list, move right if not null or up
  If move to parent is from right, again move up to parent.
   */
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    BinaryTree<Integer> curr = tree, prev = null;
    List<Integer> list = new ArrayList<>();
    while(curr!=null){
      if(curr.parent == prev){
        prev = curr;
        if(curr.left != null){
          curr = curr.left;
        }else {
          list.add(curr.data);
          if(curr.right != null){
            curr = curr.right;
          }else{
            curr = curr.parent;
          }
        }
      }else if(curr.left == prev){
        list.add(curr.data);
        prev = curr;
        if(curr.right != null){
          curr = curr.right;
        } else {
          curr = curr.parent;
        }
      }else{
        prev = curr;
        curr = curr.parent;
      }
    }
    return list;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
