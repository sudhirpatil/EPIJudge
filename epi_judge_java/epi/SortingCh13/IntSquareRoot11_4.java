package epi.Searching11;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntSquareRoot11_4 {
  @EpiTest(testDataFile = "int_square_root.tsv")

  /*
  Be conscious of type boundary specially when multiplying & adding
   */
  public static int squareRoot(int k) {
    int start = 0 , end = k, maxNum = 0, mid=0;
    long square;
    while (start <= end){
      mid = start + (end - start)/2;
      square = (long) mid * mid;
      if(square < k){
        maxNum = mid;
        start = mid + 1;
      }else if(square == k){
        maxNum = mid;
        break;
      }else {
        end = mid -1;
      }
    }
    return maxNum;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
