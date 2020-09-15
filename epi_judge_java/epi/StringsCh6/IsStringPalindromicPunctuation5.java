package epi.StringsCh6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromicPunctuation5 {
  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

  public static boolean isPalindrome(String s) {
    int start = 0, end = s.length() -1;
    char[] sArr = s.toLowerCase().toCharArray();
    while(start < end){
      if(!Character.isLetterOrDigit(sArr[start])) {
        start++;
        continue;
      }
      if(!Character.isLetterOrDigit(sArr[end])) {
        end--;
        continue;
      }
      if(sArr[start] != sArr[end]) break;
      start++;
      end--;
    }
    return start == end;
  }




  public static boolean isPalindrome1(String s) {
    boolean isPalindrome = true;
    int fwdIndex = 0, backIndex = s.length()-1;
    while(fwdIndex < backIndex){
      char fwdChar = s.charAt(fwdIndex);
      char backChar = s.charAt(backIndex);
      if(!Character.isLetterOrDigit(fwdChar)){
        fwdIndex++;
        continue;
      }else if(!Character.isLetterOrDigit(backChar)){
        backIndex++;
        continue;
      }

      if(fwdChar != backChar){
        isPalindrome = false;
        break;
      }

    }

    return isPalindrome;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation5.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
