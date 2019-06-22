package epi.StringsCh6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion1 {

  public static String intToString(int x) {
    String sign = "";
    if(x < 0){
      sign = "-";
      x *= -1;
    }

    StringBuilder sb = new StringBuilder();
    while (x > 0 ){
      int rem = x % 10;
      sb.append(rem);
      x = (x-rem)/10;
    }
    if(sign.equals("-")){
      sb.append(sign);
    }

    return sb.reverse().toString();
  }

  public static int stringToInt(String s) {

    int sign = 1, intVal = 0;
    for(int i=0; i < s.length(); i++){
      char ch = s.charAt(i);
      if(i==0 && ch == '-'){
        sign = -1;
      }else {
        Character.isDigit(ch);
        int digit = ch - '0';
        intVal = intVal * 10 + digit;
      }
    }

    return intVal * sign;
  }

  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (!intToString(x).equals(s)) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion1.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
