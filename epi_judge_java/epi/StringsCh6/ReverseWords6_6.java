package epi.StringsCh6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class ReverseWords6_6 {
/*
  ReverseWords6_6
Missed edge cases : first char space & last word as last char is not space
  last word & case when last char is not space: end = i not i-1
 */
  public static void reverseWords(char[] input) {
    int len = input.length - 1;
    reverse(input, 0 , len);

    for(int i=0, start=0, end=0; i<= len; i++){
      if((input[i] == ' ' &&  i>0 && input[i-1] != ' ') || (i == len)){
        end = i -1;
        if(i == len && input[i] != ' ') end = i;
        reverse(input, start, end);
        start = i+1;
      }else if(input[i] == ' '){
        start = i + 1;
      }
    }
  }

  public static  void reverse(char[] input, int start, int end){
    for(;start<end;start++, end--){
      char temp = input[end];
      input[end] = input[start];
      input[start] = temp;
    }
  }


















  public static void reverseWords2(char[] input) {
    // reverse whole string
    // reverse each word in string
    // edge cases starting with space, multiple spaces, last word no space
    int len = input.length - 1;
    reverseChars(input, 0, len);

    //reverse each word
    for(int i=0, start = 0, end = 0; i<=len; i++){
      if((input[i] == ' ' && i >0 && input[i-1] != ' ') || i == len){
        end = i - 1;
        if(i==len && input[i] != ' ') end = i;
        reverseChars(input, start, end);
        start = i + 1;
      }
      if(input[i] == ' ') start = i + 1;
    }
  }

  public static void reverseChars(char[] input, int start, int end){
    for(; start< end; start++, end--){
      char temp = input[end];
      input[end] = input[start];
      input[start] = temp;
    }
  }




    public static void reverseWords1(char[] input) {
    /*
    Good :
    took len-1 avoided confusions with index & len

    Bad:
    missed edge cases : reversing last word with ' ' logic and first char empty space
    end index assignment for last word, it should be i not i-1

     */
    int length = input.length-1;
//    while(input[length] == ' ')
//      length--;

    for(int i=0, end = length;i< end; i++, end--){
      char temp = input[end];
      input[end] = input[i];
      input[i] = temp;
    }

    int start = 0;
    for(int i =0; i<= length ; i++){
      if((input[i] == ' ' && (i>0 && input[i-1] != ' ')) || (i == length)){
        int end = i -1;
        if(i== length && input[i] != ' ') end = i;

        for(int j= start; j< end; j++, end--){
          char temp = input[end];
          input[end] = input[j];
          input[j] = temp;
        }
      }
      if(input[i] == ' ') start = i + 1;
    }

      return;
  }

  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
