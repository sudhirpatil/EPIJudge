package epi.StackQueue8;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SunsetView8_5 {
  public static List<Integer>
  examineBuildingsWithSunset(Iterator<Integer> sequence) {
    Deque<List<Integer>> stack = new ArrayDeque<>();
    int i = 0;
    while (sequence.hasNext()){
      Integer next = sequence.next();
      if(!stack.isEmpty() && stack.peek().get(1) < next) {
        while (!stack.isEmpty() && stack.peek().get(1) < next) {
          stack.pop();
        }
      }
      stack.push(Arrays.asList(i++, next));
    }

    List<Integer> outList = new ArrayList<>();
    Iterator<List<Integer>> iterator = stack.iterator();
    while(iterator.hasNext()) outList.add(iterator.next().get(0));
    return outList;
  }

  @EpiTest(testDataFile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
    return examineBuildingsWithSunset(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SunsetView.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
