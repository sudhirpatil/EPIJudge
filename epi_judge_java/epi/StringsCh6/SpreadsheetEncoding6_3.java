package epi.StringsCh6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SpreadsheetEncoding6_3 {
  @EpiTest(testDataFile = "spreadsheet_encoding.tsv")

  public static int ssDecodeColID(final String col) {
    /*
    iterate char array, get int value of char +1
    value= (value * 26) + (char value + 1)
     */
    int num = 0;
    for(char ch : col.toCharArray()){
      num = (num *26) + ((int) (ch - 'A' + 1));
    }
    return num;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpreadsheetEncoding.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
