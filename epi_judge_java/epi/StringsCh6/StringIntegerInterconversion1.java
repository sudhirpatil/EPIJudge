package epi.StringsCh6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion1 {

  public static String intToString(int x) {
    // devide by 10 * n to get left part & remainder
    // append ramainder to sb
    // negative case handling

    StringBuilder sb = new StringBuilder();
    boolean isNegative = false;
    if(x < 0 ) {
      isNegative = true;
      x *= -1;
    }

    int devisor = 10;
    while(x>0){
      sb.append((char)((x%devisor) +'0'));
      x = x/devisor;
    }
    if(isNegative) sb.append("-");
    return sb.reverse().toString();
  }

  public static int stringToInt(String s) {
    int intValue = s.substring(s.charAt(0) == '-'? 1 : 0).chars().
            reduce(0 , (sum, c) -> sum * 10 + (c - '0'));
    return s.charAt(0) == '-' ? intValue * -1 : intValue;
  }

  public static int stringToIntnonfunctional(String s) {
    boolean isNegative = false;
    int number =0;
    for(char ch : s.toCharArray()){
      if(ch == '-') {
        isNegative = true;
        continue;
      }

      number = number * 10 + (ch - '0');
    }
    return isNegative? -1* number: number;
  }

  public static String intToStringOld(int x) {
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

  public static int stringToIntOld(String s) {

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
