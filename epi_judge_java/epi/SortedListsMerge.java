package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    ListNode<Integer> list= null, head;
    if((L1 != null && L2== null) || (L1 != null && L2!= null && L1.data <= L2.data)){
      list = L1;
      L1 = L1.next;
    }else if(L2 != null){
      list = L2;
      L2 = L2.next;
    }
    head = list;

    while(L1 != null && L2 != null){
      if(L1.data <= L2.data){
        list.next = L1;
        L1 = L1.next;
      }else {
        list.next = L2;
        L2 = L2.next;
      }
      list = list.next;
      list.next = null;
    }

    while (L1 != null){
      list.next = L1;
      L1 = L1.next;
      list = list.next;
    }

    while (L2 != null){
      list.next = L2;
      L2 = L2.next;
      list= list.next;
    }

    return head;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
