package epi.StackQueue8;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.nio.charset.IllegalCharsetNameException;
import java.util.*;
import java.util.function.ToIntBiFunction;

public class EvaluateRpn8_2 {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  /**
   * Not analysing the input case completely, missed scenario of more than 2 numbers coming in sequence
   * Forgot break in switch case
   * Was using queue instead of Stack
   * Missed sequencing when data popped from stack
   */

  // using hashmap with function as value for operation
  public static int eval(String expression) {
    Deque<Integer> numbersStack = new ArrayDeque<>();

    // Map with function to operate on numbers
    // Split string & iterate, check with hashmap & apply method or add to numbers
    Map<String, ToIntBiFunction<Integer, Integer>> map = new HashMap<String, ToIntBiFunction<Integer, Integer>>() {
      {
        put("+", (y,x) -> x + y);
        put("-", (y,x) -> x - y);
        put("*", (y,x) -> x * y);
        put("/", (y,x) -> x / y);
      }
    };

    for(String subStr : expression.split(",")){
      if(map.containsKey(subStr)){
        numbersStack.push(map.get(subStr).applyAsInt(numbersStack.pop(), numbersStack.pop()));
      }else {
        numbersStack.push(Integer.valueOf(subStr));
      }
    }
    return numbersStack.pop();
  }

  public static int evalUsingCase(String expression) {
    // split string by ,
    // 2 queues, number queue & operator queue, insert in to these
    // keeping popping 2 from numbers and one from operator, insert result into number queue
    Deque<Integer> numbers = new ArrayDeque<>();

    for(String subStr : expression.toLowerCase().split(",")){
      switch (subStr){
        case "+":
        case "-":
        case "*":
        case "/":
          if(numbers.size() < 2) return 0;
          Integer second = numbers.pop(), first = numbers.pop(), result = null;
          switch (subStr){
            case "+":
              result = first + second;
              break;
            case "-":
              result = first - second;
              break;
            case "*":
              result = first * second;
              break;
            case "/":
              if(second == 0) return 0;
              result = (int) (first / second);
              break;
          }
          numbers.push(result);
          break;
        default: numbers.push(Integer.valueOf(subStr));
      }
    }

    return numbers.pop();
  }


  public static int evalOld(String expression) {
    System.out.println(expression);
    String[] elements = expression.split(",");
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    for(String element : elements){
      if(isInteger(element)){
        stack.push(Integer.parseInt(element));
      }else{
        int num2 = stack.pop();
        int num1 = stack.pop();
        int result =0;
        switch (element){
          case "+" :
            result = num1 + num2;
            break;
          case "-" :
            result = num1 - num2;
            break;
          case "*":
            result = num1 * num2;
            break;
          case "/":
            result = num1 / num2;
            break;
        }

        stack.push(result);
      }
    }
    return stack.pop();
  }

  public static boolean isInteger(String str){
    try{
      Integer.parseInt(str);
      return true;
    }catch (Exception e){
      return false;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
