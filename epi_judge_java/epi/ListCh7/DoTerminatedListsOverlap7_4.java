package epi.ListCh7;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class DoTerminatedListsOverlap7_4 {
  /*
  Meeting point is at same distance from the end, so for larger list if we move to diff spaces
  then length of both is same
   */
  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    ListNode<Integer> l0Head= l0, l1Head = l1;
    int len1=0, len2 =0;
    while(l0 != null){
      len1++;
    }
    while (l1 != null){
      len2++;
    }
    l0 = l0Head; l1 = l1Head;
    if(len1 > len2){
      for(int i = 0; i<len1-len2;i++)
        l0 = l0.next;
    }else if(len1 < len2){
      for(int i = 0; i<len2-len1;i++)
        l1 = l1.next;
    }

    while (l0 != null && l1 != null && l0 != l1){
      l0 = l0.next;
      l1 = l1.next;
    }
    return (l0 == l1)? l0 : null;
  }

  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
