package epi.StringsCh6;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
public class ReplaceAndRemove6_4 {

  /*
  Careful about the array sizes
  Revise program once done
  keep overall flow  in your mental model

  read the question correctly: what are inputs, what is exactly expected ; missed size is of number of characters not the array size
  array index starts with 0 & so max length + 1 = size : confused with size/count with index in array
  Careful about ++/-- in array index : better do it outside index
  prefer for loop over while, as variable initiation/increment etc more clear
   */
  public static int replaceAndRemove(int size, char[] s) {
    int pos = 0, aCount = 0;
    for(int i = 0; i< size ; i++){

      if(s[i] != 'b'){
        if(s[i] == 'a') aCount++;

        s[pos] = s[i];
        pos++;
      }}

      int finalSize = pos + aCount - 1;
      for(int j=pos-1; j>=0; j--, finalSize--){
        if(s[j] == 'a') {
          s[finalSize] = 'd';
          finalSize--;
          s[finalSize] = 'd';
        }else{
          s[finalSize] = s[j];
        }
      }
    return pos + aCount;
  }








  public static int replaceAndRemove2(int size, char[] s) {
    /*
    iterate, 2 indexes one original another after delete, if b don't copy other chars copy to new pos
    Start from original size, copy to new size index or add with 2 chars to new pos, iteration decreasing
     */
    int pos = 0, aCount =0;
    for(int i=0; i<size; i++){
      if(s[i] != 'b'){
        if(s[i] == 'a') aCount++;
        s[pos] = s[i];
        pos++;
      }
    }

    for(int j= pos-1, newSize = pos + aCount -1; j >=0 ; j--, newSize--){
      if(s[j] == 'a'){
        s[newSize] = 'd';
        newSize--;
        s[newSize] = 'd';
      }else{
        s[newSize] = s[j];
      }
    }
    return pos + aCount;
  }


  public static int replaceAndRemove1(int size, char[] s) {
    // iterate remove b's, move other chars to new place, also count a's
    // start from the from new size, move new size -> nSize + aCount
    int aCount = 0, bCount = 0;
    for(int i =0; i< size;i++){
      if(s[i] == 'b') bCount++;
      else {
        if(s[i] == 'a') aCount++;
        if(bCount > 0) s[i-bCount] = s[i];
      }
    }

    int currSize = size - bCount;
    int newSize = currSize + aCount-1;
    for(int j = currSize -1; j >= 0; j--){
      if(s[j] == 'a') {
        s[newSize] = 'd';
        newSize--;
        s[newSize] = 'd';
        newSize--;
      }else{
        s[newSize] = s[j];
        newSize--;
      }
    }
    return currSize + aCount;
  }


//  public static int replaceAndRemoveOld(int size, char[] s) {
//    // delete char b's, keep shifting characters to left
//    // for converting a to 2 d's, start from end of array, pick characters from size - del count and copy it end of array, also copy 2 elements if char is a
//    // in the end, shift from last char in the operation to starting index
//
//    int delIndex = 0;
//    for(int i =0; i< size; i++){
//      if(s[i] != 'b'){
//        s[delIndex] = s[i];
//        delIndex++;
//      }
//    }
//
//    //TODO:: count a's in above loop & use delIndex + aSize instead of s.length to avoid las loop for shifting
//    int replaceIndex = s.length -1;
//    for(int j= delIndex -1; j>=0; j--){
//      if(s[j] == 'a'){
//        s[replaceIndex] = 'd';
//        replaceIndex--;
//        s[replaceIndex] = 'd';
//      }else {
//        s[replaceIndex] = s[j];
//      }
//      replaceIndex--;
//    }
//
//    int leftIndex=0;
//    for(int k= replaceIndex+1; k<s.length;k++, leftIndex++){
//      s[leftIndex] = s[k];
//    }
//
//    return leftIndex;
//  }

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
