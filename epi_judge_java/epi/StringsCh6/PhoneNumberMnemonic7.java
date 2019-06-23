package epi.StringsCh6;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;
public class PhoneNumberMnemonic7 {
  static String[] digitCharMap = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
  public static List<String> phoneMnemonic(String phoneNumber) {
    return getPhoneMnemonics(phoneNumber, "");
  }

  // Key is to creating new method for recursion that takes extra variable sort of accumulator for previous results
  // finding out end of recursion condition e.g phoneNumber == ""
  // collecting all previous results of recursion call
  public static List<String> getPhoneMnemonics(String phoneNumber, String mnemonic){
    List<String> results = new LinkedList<>();
    // return condition on recursion
    if(phoneNumber.equals("")){
      results.add(mnemonic);
      return results;
    }

    // call method recursively and add to return list
    char[] mapChars = digitCharMap[phoneNumber.charAt(0) - '0'].toCharArray();
    for(char ch : mapChars){
      results.addAll(getPhoneMnemonics(phoneNumber.substring(1), mnemonic+ch));
    }

    return  results;
  }

  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PhoneNumberMnemonic7.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
