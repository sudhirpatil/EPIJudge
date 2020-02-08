package epi.ListCh7;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                       int finish) {
    if(L == null || L.next == null) return L;
    ListNode<Integer> origStartNode = L;
    ListNode<Integer> prevNode = null;
    for(int i =1; i>1 && i<= start; i++) {
      prevNode = L;
      L = L.next;
      if(L == null) return origStartNode;
    }

    ListNode<Integer> reverse = reverse(L, finish - start);
    System.out.println(reverse);
    if(prevNode == null) return reverse;
    else {
      prevNode.next = reverse;
      return origStartNode;
    }
  }

  public static ListNode<Integer> reverse(ListNode<Integer> L, int end){
    if(L.next == null || end == 0){
      return L;
    }
    ListNode<Integer> reverseNode = reverse(L.next, end-1);
    ListNode<Integer> endNext = reverseNode.next;
    reverseNode.next = L;
    L.next = endNext;
    return reverseNode;
  }

  public static ListNode<Integer> reverseSublistFlawed(ListNode<Integer> L, int start,
                                                 int finish) {
    // iterate to start node
    // iterate & start reversing till finish, preserve start node
    // point start->next finish->next
    // handle start =1
    ListNode<Integer> list = L;
    int i=1;
    for(; i<start; i++) {
      list = list.next;
    }
    ListNode<Integer> beforeStart = list;
    list = list.next;
    ListNode<Integer> startNode = list;
    list = list.next;
    for(i=i+2;i<=finish;i++){
      ListNode<Integer> nextNode = list.next;
      list.next = startNode;
      startNode.next = nextNode;
      list = nextNode;
    }
    beforeStart.next = list;
    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
