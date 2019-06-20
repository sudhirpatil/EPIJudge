package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MaxSumSubarray {
  @EpiTest(testDataFile = "max_sum_subarray.tsv")

  public static int findMaximumSubarray(List<Integer> A) {
    //Key :: Find Min Array Sum and minus Running Sum to find max Sum subarray
    // for each i
    // get running sum
    // get maxSubArrSum
    // update minSubArrSum
    int runningSum =0, maxSubArrSum =0, minSubArrSum =0;
    for (int element: A) {
      runningSum += element;
      if(runningSum <= minSubArrSum) minSubArrSum = runningSum;
      if(maxSubArrSum < (runningSum - minSubArrSum)) maxSubArrSum = (runningSum - minSubArrSum);
    }
    return maxSubArrSum;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxSumSubarray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
