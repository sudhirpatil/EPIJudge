package epi.StackQueue8;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
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
