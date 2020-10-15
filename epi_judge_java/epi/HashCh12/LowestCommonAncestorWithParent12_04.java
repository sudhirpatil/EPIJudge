package epi.HashCh12;

import epi.BinaryTree;
import epi.test_framework.*;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorWithParent12_04 {

  public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    Set<Integer> s0 = new HashSet<>(), s1 = new HashSet<>();
    while (node0!=null || node1!=null){
      if(node0!=null){
        if(s1.contains(node0.data))
          return node0;
        s0.add(node0.data);
        node0 = node0.parent;
      }

      if(node1 != null){
        if(s0.contains(node1.data))
          return node1;
        s1.add(node1.data);
        node1 = node1.parent;
      }
    }
    return null;
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
