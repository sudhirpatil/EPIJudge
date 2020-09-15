package epi.StringsCh6;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;
public class PhoneNumberMnemonic6_7 {
  static String[] digitCharMap = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

  /*
  Few problems are very elegantly and easily solved by recursion : find out what kind of patterns we should use recursion?
    When number of for loops are not known or too big, like in this case phone number size not known
    Wherever we need to use stack, which cases to use recursion instead of stack?
  While using recursion, Have clarity on what should be termination point? what is return value? return values from top level functions

  How to calculate O(n) for recursions?
  How to get number of permutations in this case, where each index can represent 4 possible values?
   */

  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
  public static List<String> phoneMnemonic(String phoneNumber) {
    List<String> lst = new LinkedList<>();
    phoneRecursive(phoneNumber, "", lst);
    return lst;
  }

  public static void phoneRecursive(String phoneNumber, String phoneStrin, List<String> lst){
    if(phoneNumber.isEmpty()) {
      lst.add(phoneStrin);
      return ;
    }

    for(char ch : digitCharMap[(int)(phoneNumber.charAt(0) - '0')].toCharArray()){
      phoneRecursive(phoneNumber.substring(1), phoneStrin + ch, lst);
    }
    return ;
  }



















  public static List<String> phoneMnemonic1(String phoneNumber) {
    List<String> list = new ArrayList<>();
    return phoneMnemonicRecurse(phoneNumber, "");
  }

  public static List<String> phoneMnemonicRecurse(String phoneNumber, String currString){
    List<String> lst = new ArrayList<>();
    for(char currChar: digitCharMap[(int)(phoneNumber.charAt(0) - '0')].toCharArray()){
      if(phoneNumber.length() == 1) {
        lst.add(currString+currChar);
      }else {
        lst.addAll(phoneMnemonicRecurse(phoneNumber.substring(1), currString + currChar));
      }
    }
    return lst;
  }
















//  // Key is to creating new method for recursion that takes extra variable sort of accumulator for previous results
//  // finding out end of recursion condition e.g phoneNumber == ""
//  // collecting all previous results of recursion call
//  public static List<String> getPhoneMnemonics(String phoneNumber, String mnemonic){
//    List<String> results = new LinkedList<>();
//    // return condition on recursion
//    if(phoneNumber.equals("")){
//      results.add(mnemonic);
//      return results;
//    }
//
//    // call method recursively and add to return list
//    char[] mapChars = digitCharMap[phoneNumber.charAt(0) - '0'].toCharArray();
//    for(char ch : mapChars){
//      results.addAll(getPhoneMnemonics(phoneNumber.substring(1), mnemonic+ch));
//    }
//
//    return  results;
//  }

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
