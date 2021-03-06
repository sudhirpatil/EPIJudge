package epi.StackQueue8;
import epi.BinaryTree;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.stream.Collectors;

public class TreeLevelOrder8_6 {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    // 2 queue's one for current level another next level
    // Iterate first queue & add children to 2nd queue
    // after first queue empty, move second -> first and second -> empty
    // Cycle continues till 2nd queue is empty
    List<List<Integer>> oList = new ArrayList<>();
    Deque<BinaryTreeNode<Integer>> firstQ = new ArrayDeque<>(), secondQ = new ArrayDeque<>();

    int lIndex = 0;
    if(tree != null) {
      firstQ.offer(tree);
      oList.add(new ArrayList<>());
    }

    while (!firstQ.isEmpty()){
      BinaryTreeNode<Integer> node = firstQ.poll();
      oList.get(lIndex).add(node.data);
      if(node.left != null) secondQ.offer(node.left);
      if(node.right != null) secondQ.offer(node.right);

      if(firstQ.isEmpty() && !secondQ.isEmpty()){
        firstQ = secondQ;
        secondQ = new ArrayDeque<>();
        lIndex++;
        oList.add(new ArrayList<>());
      }
    }

    return oList;
  }

  public static List<List<Integer>>
  binaryTreeDepthOrderOld2(BinaryTreeNode<Integer> tree) {
    List<List<Integer>> allLevles = new ArrayList<>();
    ArrayDeque<BinaryTreeNode<Integer>> deque = new ArrayDeque<>();
    List<BinaryTreeNode> levelNodes = new LinkedList<BinaryTreeNode>();
    levelNodes.add(tree);

    while (!levelNodes.isEmpty()){
      // Get ints at level as list
      List<Integer> oneLevelList = levelNodes.stream().
              filter(node -> node != null).
              map(node -> (Integer)node.data).
              collect(Collectors.toList());
      allLevles.add(oneLevelList);

      // Get nodes in next level
      levelNodes = levelNodes.stream().
              filter(node -> node != null).
              map(node -> Arrays.asList(node.left, node.right)).
              flatMap(List::stream).
              filter(child -> child != null).
              collect(Collectors.toList());
    }

    return allLevles;
  }

  public static List<List<Integer>>
  binaryTreeDepthOrderOld(BinaryTreeNode<Integer> tree) {
    List<List<Integer>> allLevles = new ArrayList<>();
    ArrayDeque<BinaryTreeNode<Integer>> deque = new ArrayDeque<>();
    ArrayDeque<BinaryTreeNode<Integer>> deque2 = new ArrayDeque<>();
    if(tree != null) deque.offer(tree);
    List<Integer> currList = new ArrayList<>();
    while(!deque.isEmpty()){
      BinaryTreeNode<Integer> node = deque.poll();
      currList.add(node.data);
//      System.out.println("node val: "+node.data);
      if(node.left != null) deque2.offer(node.left);
      if(node.right != null) deque2.offer(node.right);

      if(deque.isEmpty()){
        if(!currList.isEmpty()){
          allLevles.add(currList);
          currList = new ArrayList<>();
        }

        deque = deque2;
        deque2 = new ArrayDeque<>();
      }
    }

    return allLevles;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
