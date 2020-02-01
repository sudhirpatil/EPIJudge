package epi.ArraysCh5;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }
  /*
  Took long time understand question correctly
  Initially ignored the different cases
  Long time decide on correct and optimal logic, correct & optimal did not flash till multiple iterations
  * Silly mistakes in loops, variables count logic etc

  Write down overall broad logic so that it is clear while writing code
  Be clear about every variable and loop logic, do not write until logic is clear in head
  When copy pasted look at the variables and logic
  Always review
  Give dedicated fully focused effort, be very fast

   */

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    //one loop to move smaller to starting part of array
    // another loop to move larger to end part

    Color pivot = A.get(pivotIndex);
    int size = A.size();

    int smaller =0;
    for(int i=0; i< size; i++){
      if(A.get(i).ordinal() == 0){
        Collections.swap(A, smaller, i);
        smaller++;
      }
    }

    int larger = size-1;
    for(int j=size-1; j>=smaller;j--){
      if(A.get(j).ordinal() == 2){
        Collections.swap(A, larger, j);
        larger--;
      }
    }
  }


  public static void dutchFlagPartitionOld(int pivotIndex, List<Color> A) {
    Color pivot = A.get(pivotIndex);
    int size = A.size()-1;

    System.out.println("\n "+pivot+" size:"+size);
    System.out.println("Original :"+A);
    int count =0;
    for(int i=size; i > count ;i--){
      if(A.get(i).compareTo(Color.RED) == 0){
        while (A.get(count).compareTo(Color.RED)== 0 && i>count) count++;
        replace(A, count, i);
        count++;
      }
    }
    System.out.println("First : "+A);
    if (pivot.compareTo(Color.RED) == 0) return;

    int blueCount=0;
    for(int j= count; j< size-blueCount; j++){

      if(A.get(j).compareTo(Color.BLUE) == 0){
        while (A.get(size - blueCount).compareTo(Color.BLUE)== 0 & j < size -blueCount) blueCount++;

        replace(A, j, size-blueCount);
        blueCount++;
      }
    }
    System.out.println("Second : "+A);


  }

  public  static void  replace(List<Color> A, int i, int j){
    Color temp = A.get(i);
    A.set(i, A.get(j));
    A.set(j, temp);
  }

  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx) throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
