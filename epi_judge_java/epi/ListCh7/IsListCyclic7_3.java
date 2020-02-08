package epi.ListCh7;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class IsListCyclic7_3 {

  // to many mistakes in getting if else conditions correct
  // pay attention to all the conditions == or !=

  public static ListNode<Integer> hasCycle(ListNode<Integer> head) {
    // find out if it has cycle, by using 2 iterators, 1 with 1 & 2nd 2 steps
    // find out cycle length
    // 2 iterator, 1 with 1 step & 2nd with cycle length step
    ListNode<Integer> list1 = head, list2 = head;
    while (list1 != null && list2 != null ) {
      list1 = list1.next;
      if(list2.next == null) break;
      list2 = list2.next.next;
      if(list1 == list2) break;
    }

    if(list1 == null || list2 == null || list2.next == null || list1 != list2) return null;

    int cycleLength=1;
    list2= list2.next;
    for(; list2 != list1; cycleLength++) list2= list2.next;

    ListNode<Integer> listCycle = head;
    for(int j=1; j<= cycleLength;j++) listCycle = listCycle.next;
    while (head != listCycle) {
      head = head.next;
      listCycle = listCycle.next;
    }
    return listCycle;
  }

  /**
   * Lot of mistakes in comparisons in while loop & if conditions. How to fix it?
   * flawed logic on how to get meeting point, not sure why i put -1 & < in comparison
   */
  @EpiTest(testDataFile = "is_list_cyclic.tsv")
  public static void HasCycleWrapper(TimedExecutor executor,
                                     ListNode<Integer> head, int cycleIdx)
      throws Exception {
    int cycleLength = 0;
    if (cycleIdx != -1) {
      if (head == null) {
        throw new RuntimeException("Can't cycle empty list");
      }
      ListNode<Integer> cycleStart = null, cursor = head;
      while (cursor.next != null) {
        if (cursor.data == cycleIdx) {
          cycleStart = cursor;
        }
        cursor = cursor.next;
        if (cycleStart != null) {
          cycleLength++;
        }
      }
      if (cursor.data == cycleIdx) {
        cycleStart = cursor;
      }
      if (cycleStart == null) {
        throw new RuntimeException("Can't find a cycle start");
      }
      cursor.next = cycleStart;
      cycleLength++;
    }

    ListNode<Integer> result = executor.run(() -> hasCycle(head));

    if (cycleIdx == -1) {
      if (result != null) {
        throw new TestFailure("Found a non-existing cycle");
      }
    } else {
      if (result == null) {
        throw new TestFailure("Existing cycle was not found");
      }

      ListNode<Integer> cursor = result;
      do {
        cursor = cursor.next;
        cycleLength--;
        if (cursor == null || cycleLength < 0) {
          throw new TestFailure(
              "Returned node does not belong to the cycle or is not the closest node to the head");
        }
      } while (cursor != result);

      if (cycleLength != 0) {
        throw new TestFailure(
            "Returned node does not belong to the cycle or is not the closest node to the head");
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListCyclic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
