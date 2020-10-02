package epi.StackQueue8;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class IsValidParenthesization8_3 {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  // Pay more attention in each statement, think how it will be used e.g while creating hasmap should have used closing brace
  // Attention on each condition, comparing not using map content to compare was silly
  // handling edge cases, like what if only opening braces are passed
  // handle failure cases, check if stack is empty
  // Should have thought about retrieval of mapping opening brace and put in map instead of set
  // Case when queue is empty when closing braces are present, handled but wrong logic

  public static boolean isWellFormed(String s) {
    Map<Character, Character> closing = new HashMap<>();
    closing.put('}', '{');
    closing.put(')', '(');
    closing.put(']', '[');

    Deque<Character> queue = new ArrayDeque<Character>();
    for (Character ch : s.toCharArray()) {
      if (closing.containsKey(ch)) {
        if (queue.isEmpty())
          return false;
        else if(!queue.peek().equals(closing.get(ch))) {
          break;
        } else {
          queue.poll();
        }
      } else {
        queue.push(ch);
      }
    }
    return queue.isEmpty()
            ;
  }

  public static boolean isWellFormed2(String s) {
    // map with opening & closing brace
    // stack to push opening braces
    // On closing brace pop stack & check if it matches
    Map<Character, Character> map = new HashMap<Character, Character>(){{
      put(')', '(');
      put(']', '[');
      put('}', '{');
    }};
    Deque<Character> stack = new ArrayDeque<>();

    for(char ch: s.toCharArray()){
      if(ch == '(' || ch == '[' || ch == '{') stack.push(ch);
      else if(map.containsKey(ch) && !stack.isEmpty() && stack.pop() == map.get(ch)) {
        System.out.println("mapped");
      }
      else return false;
    }

    return stack.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
