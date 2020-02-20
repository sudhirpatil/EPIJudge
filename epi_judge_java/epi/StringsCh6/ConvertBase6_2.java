package epi.StringsCh6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.Collections;

public class ConvertBase6_2 {
  @EpiTest(testDataFile = "convert_base.tsv")
/*
Missed character cases A, F, blunder fixed one case but not another : revise through solution, recheck boundary conditions
Missed converting to char from int :
  Very careful when character conversions happen, use different variable instead of doint all online so that type conversion errors visible
Fixing bug takes more time than, revise & fix

Have mental model of whole program
 */
  public static String convertBase(String numAsString, int b1, int b2) {
    // convert to base10 int
    // start from leftmost char, num * b1 + current num
    // convert to base b2
    // devide by b2 add remainder to buffer, num /= b2, loop till num =0
    // handle -ve case?

    if(b1 == 0 || b2 == 0) return null;

    boolean isNeg = numAsString.charAt(0) == '-';
    if(numAsString.equals("97D3AD06")){
      System.out.println("found ");
    }
    int number = numAsString.substring(isNeg ? 1 : 0).
            chars().
            reduce(0, (num, c) -> (num *b1)+
                    (Character.isDigit(c) ?(c - '0'):(c- 'A'+10)));

    StringBuffer sb = new StringBuffer();
    do{
      int rem = number % b2;
      sb.append((char) (rem>=10 ? 'A' + (rem -10) : '0' + rem));
      number = number / b2;
    }while (number > 0);
    if(isNeg) sb.append("-");
    return sb.reverse().toString();
  }



























//  public static String convertBaseOld(String numAsString, int b1, int b2) {
//    // Not using correct variables, Need to be more alert while coding variable names
//    // Missing edge case of A, keep in mind about all cases and have plan to implement it
//    // wrongly using ^ operator it's xor not power
//    // character and integer conversions after math operations
//
//    // Convert to 10 base
//    int b1Pow = 0;
//    int base10Val = 0;
//    String signIndicator = "";
//    int firstIndex = 0;
//    if(numAsString.charAt(0) == '-'){
//      signIndicator = "-";
//      firstIndex = 1;
//    }
//    for(int i = numAsString.length(); i>firstIndex;i--){
//      char ch = numAsString.charAt(i-1);
//      int val;
//      if(Character.isDigit(ch)) {
//        val = ch - '0';
//      }else{// if(Character.isLetter(ch)){
//        val = ch - 'A' + 10;
//      }
//      base10Val += val * Math.pow(b1, b1Pow);
//      b1Pow++;
//    }
//    System.out.println("base10 value :"+base10Val);
//
//    // 10 base to b2 base
//    int b2Pow = 0 ;
//    while (base10Val / Math.pow(b2, b2Pow+1) >= 1){
//      b2Pow++;
//    }
//
//    StringBuilder sb = new StringBuilder();
//    sb.append(signIndicator);
//    while (b2Pow >= 0){
//      int valb2 = (int) (base10Val/ Math.pow(b2,b2Pow));
//      if(valb2 >=10){
//        sb.append((char) ('A'+(valb2-10)));
//      }else {
//        sb.append(valb2);
//      }
//      base10Val = base10Val % (int) Math.pow(b2, b2Pow);
//      b2Pow--;
//    }
//    System.out.println("base b2 value:"+ sb.toString());
//    return sb.toString();
//  }
//
//  public static String convertBaseNew(String numAsString, int b1, int b2) {
//    boolean isNegative = numAsString.startsWith("-");
//
//    int intVal = numAsString.substring(isNegative? 1 : 0).
//            chars().
//            reduce(0, // initialize accumulator with 0
//                    // x is accumulator carries sum from previous iterator, c is character
//                    (x, c) -> x * b1 +
//                    (Character.isDigit(c) ? c - '0' : c - 'A' + 10));
//    // get digit in string
//    // multiply previous accumulated value with base i.e b1, add current digit
//    // this will be passed as accumulator value for next character in iteration
//
//    System.out.println(intVal);
//
//    return (isNegative ? "-" : "") +
//            (intVal == 0 ? "0" : convertBaseFromInt(intVal, b2));
//  }
//
//  public static String convertBaseFromInt(int intVal, int b2){
//    return intVal == 0 ?
//            "" : convertBaseFromInt(intVal/ b2, b2) +
//                    (char) ((intVal % b2) > 10 ? 'A' + (intVal % 10) - 10 : '0' + (intVal % 10));
//
//    // value % b2 ==> right most character/digit, value / b2  is passed again to function to get left side characters
//  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase2.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
//    convertBase("A1", 16, 7);
  }
}
