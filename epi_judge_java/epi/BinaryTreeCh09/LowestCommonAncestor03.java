package epi.BinaryTreeCh09;

import epi.BinaryTreeNode;
import epi.test_framework.*;

public class LowestCommonAncestor03 {
  /*
  object/tuple with match count and common ancestor node for return
  on any one match of node & tree increase count by 1 & return
  if on any return left/right count is 2 just return the status object no need to proceed with other part of recursion
  because of recursion only at the level of common ancestor both left count & right count will be 1 i.e 1+1 = 2 ?
   */
  public static class Status{
    int count;
    BinaryTreeNode<Integer> node;
    public Status(int c, BinaryTreeNode<Integer> n){
      this.count = c;
      this.node = n;
    }
  }

  public static Status findLCA(BinaryTreeNode<Integer> tree,
                        BinaryTreeNode<Integer> node0,
                        BinaryTreeNode<Integer> node1) {
    if(tree == null)
      return new Status(0, null);

    Status lStatus = findLCA(tree.left, node0, node1);
    if(lStatus.count == 2)
      return lStatus;
    Status rStatus = findLCA(tree.right, node0, node1);
    if(rStatus.count == 2)
      return rStatus;

    int count = lStatus.count + rStatus.count + ((node0 == tree) ? 1 : 0 ) + ((node1 == tree)?1:0);
    // count is 2 only at recursion point where both status is stored in stack is 1 and i.e common ancestor
    return new Status(count , (count == 2)? tree: null);
  }

  public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
    return findLCA(tree, node0, node1).node;
  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> LCA(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
