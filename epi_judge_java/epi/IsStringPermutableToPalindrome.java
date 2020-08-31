package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {
    /*
    Get each char count in hashmap
    iterate to find out if more than one char count is odd, then it's not palindrome
     */
    Map<Character , Integer> map = new HashMap<>();
    for(char ch : s.toCharArray()){
      map.merge(ch, 1, Integer::sum);
    }

    int oddCount = 0;
    for(int count: map.values()){
      if(count %2 > 0){
        if(oddCount == 0)
          oddCount++;
        else
          return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
