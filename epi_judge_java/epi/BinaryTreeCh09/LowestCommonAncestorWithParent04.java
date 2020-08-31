package epi.BinaryTreeCh09;

import epi.BinaryTree;
import epi.test_framework.*;

public class LowestCommonAncestorWithParent04 {

  public  static int getHeight(BinaryTree<Integer> node) {
    int height = 0;
    while(node.parent != null){
      height++;
      node = node.parent;
    }
    return height;
  }

  public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    int height0 = getHeight(node0);
    int height1 = getHeight(node1);

    int diff = Math.abs(height0-height1);
    if(height0 < height1){
      BinaryTree<Integer> temp = node0;
      node0 = node1;
      node1 = temp;
    }

    while(diff!=0){
      diff--;
      node0 = node0.parent;
    }

    while(!node0.equals(node1)){
      node0 = node0.parent;
      node1 = node1.parent;
    }
    return node0;
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> LCA(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
