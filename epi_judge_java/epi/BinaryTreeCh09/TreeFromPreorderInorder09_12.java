package epi.BinaryTreeCh09;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeFromPreorderInorder09_12 {
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")
/*
Always be careful while passing list or array size, ** pass size-1 Always **
All tree related problems first think solving by using Recursion

Pick item from preorder as this is root, get index of item in inorder list to find out left & right sub tree items
  use recursion to get left & right sub tree by 2 calls left & right
Think about root and divide inorder into 2 sub arrays start,index-1 & index+1,end
split pre order list also, so that can start from first index item in preorder
 */
  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
    /*
    params : node, map(inorder), instart/end, prestart/end
     */
    Map<Integer, Integer> map = new HashMap<>();
    int j =0;
    for(Integer i : inorder)
      map.put(i, j++);
    return getTree(preorder, inorder, 0,inorder.size()-1,0,inorder.size()-1, map);
  }

  public static BinaryTreeNode<Integer> getTree(List<Integer> preorder, List<Integer> inorder, int iStart, int iEnd,
                                                int pStart, int pEnd, Map<Integer, Integer> map){
    if(pStart>pEnd || iStart > iEnd) {
      return null;
    }

    if(pStart >= inorder.size())
      System.out.println("test");

    int item = preorder.get(pStart);
    int iIndex = map.get(item);
    int nextP = pStart + (iIndex - iStart);

    return new BinaryTreeNode<Integer>(item,
            getTree(preorder, inorder, iStart,iIndex-1,pStart+1,nextP, map),
            getTree(preorder, inorder, iIndex+1,iEnd,nextP+1,pEnd, map));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
