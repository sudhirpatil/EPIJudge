package epi.StringsCh6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
public class ReplaceAndRemove4 {

  public static int replaceAndRemove(int size, char[] s) {
    // delete char b's, keep shifting characters to left
    // for converting a to 2 d's, start from end of array, pick characters from size - del count and copy it end of array, also copy 2 elements if char is a
    // in the end, shift from last char in the operation to starting index

    int delIndex = 0;
    for(int i =0; i< size; i++){
      if(s[i] != 'b'){
        s[delIndex] = s[i];
        delIndex++;
      }
    }

    //TODO:: count a's in above loop & use delIndex + aSize instead of s.length to avoid las loop for shifting
    int replaceIndex = s.length -1;
    for(int j= delIndex -1; j>=0; j--){
      if(s[j] == 'a'){
        s[replaceIndex] = 'd';
        replaceIndex--;
        s[replaceIndex] = 'd';
      }else {
        s[replaceIndex] = s[j];
      }
      replaceIndex--;
    }

    int leftIndex=0;
    for(int k= replaceIndex+1; k<s.length;k++, leftIndex++){
      s[leftIndex] = s[k];
    }

    return leftIndex;
  }

  @EpiTest(testDataFile = "replace_and_remove.tsv")
  public static List<String>
  replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
      throws Exception {
    char[] sCopy = new char[s.size()];
    for (int i = 0; i < size; ++i) {
      if (!s.get(i).isEmpty()) {
        sCopy[i] = s.get(i).charAt(0);
      }
    }

    Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

    List<String> result = new ArrayList<>();
    for (int i = 0; i < resSize; ++i) {
      result.add(Character.toString(sCopy[i]));
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReplaceAndRemove4.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
