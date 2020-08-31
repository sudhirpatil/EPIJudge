package epi.HashCh12;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsAnonymousLetterConstructible12_02 {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    Map<Character, Integer> map = new HashMap<>();
    for(char ch : letterText.toCharArray()){
      map.merge(ch, 1, Integer::sum);
    }

    for(char c: magazineText.toCharArray()){
      if(map.containsKey(c)){
        map.merge(c,-1,Integer::sum);
        if(map.get(c) == 0) {
          map.remove(c);
          if (map.isEmpty())
            break;
        }
      }
    }
    return map.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
