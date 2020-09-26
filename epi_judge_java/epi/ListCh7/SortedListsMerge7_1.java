package epi.ListCh7;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortedListsMerge7_1 {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  /*
  Use Dummy head node, then no need of special case of selecting head node, logic for head node is same as other nodes
  For remaining nodes after 1st loop just point current.next to Remaining, need not have to copy like array list.
   */
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    ListNode<Integer> head = new ListNode<>(0, null);
    ListNode<Integer> current = head;

    while(L1!=null && L2!=null){
      if(L1.data <= L2.data){

        current.next = L1;
        L1 = L1.next;
      }else{
        current.next = L2;
        L2 = L2.next;
      }
      current = current.next;
    }

    current.next = L1 == null ? L2 : L1;
    return head.next;
  }














  public static ListNode<Integer> mergeTwoSortedLists1(ListNode<Integer> L1,
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
