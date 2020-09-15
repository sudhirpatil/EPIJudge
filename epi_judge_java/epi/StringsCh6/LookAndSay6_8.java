package epi.StringsCh6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class LookAndSay6_8 {
  @EpiTest(testDataFile = "look_and_say.tsv")

  public static String lookAndSay(int n) {
    /*
    one loop for n
    loop for each char in prev string (list chars)
      currChar, cuurCount, add to currList when char != currChar
      edge cases start Char & end Char
    Complexity:
      number of iterations n
      for each iteration max chars is 2 pow n
      Total complexity n * 2 pow n
     */
    List<Integer> str = new ArrayList<>();
    str.add(1);
    for(int i =1;i<n;i++){
      int cur = str.get(0), count = 1;
      List<Integer> newStr = new ArrayList<>();
      for(int j=1; j< str.size();j++){
        int newInt = str.get(j);
        if(newInt != cur){
          newStr.add(count);
          newStr.add(cur);
          cur = newInt;
          count = 1;
        }else{
          count++;
        }
      }

      newStr.add(count);
      newStr.add(cur);
      str = newStr;
    }

    StringBuffer sb = new StringBuffer();
    for(int i =0; i< str.size(); i++){
      sb.append((char)('0'+str.get(i)));
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
