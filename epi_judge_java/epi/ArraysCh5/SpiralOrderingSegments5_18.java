package epi.ArraysCh5;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrderingSegments5_18 {
  @EpiTest(testDataFile = "spiral_ordering_segments.tsv")

  /*
  Stuck at rounding up
  Check what should be return value, wasted time in sys out
  Be fast and dedicated time in developing, concentrated as if doing in test

  Logic got it right without any changes
   */

  /* KEY
    Represent shift in 2D array, to move in matrix boundary
    mark visited cell with 0
   */
  public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    List<Integer> list = new ArrayList<>();
    // define 2d array for boundary additions
    // for loop for n * n
    // set visited to 0
    // if nextX nextY multiple conditions then move
    int[][] shifts = {{0,1}, {1, 0}, {0, -1}, {-1, 0}};
    int x = 0 , y =0;
    int size = squareMatrix.size();
    int dimIndex = 0;
    for(int i= 0; i < size * size; i++){
      list.add(squareMatrix.get(x).get(y));
      int nextX = x + shifts[dimIndex][0], nextY = y + shifts[dimIndex][1];
      if(nextX>=size || nextY >= size || nextX <0 || nextY < 0
      || squareMatrix.get(nextX).get(nextY) == 0
       ){
        dimIndex = (dimIndex + 1) % 4;
        nextX = x + shifts[dimIndex][0];
        nextY = y + shifts[dimIndex][1];
      }
      squareMatrix.get(x).set(y, 0);
      x = nextX;
      y = nextY;
    }

    return  list;
  }

  public static List<Integer> matrixInSpiralOrderOld(List<List<Integer>> squareMatrix) {
    // loop for each spiral layer rowCount / 2
    // loop for going through spiral
    List<Integer> spiralList = new ArrayList<>();
    int rowCount = squareMatrix.size();
    if(rowCount > 0 ) {
      int columnCount = squareMatrix.get(0).size();
      System.out.println("\nrowCount :"+rowCount+" , Spirals : "+(int)Math.ceil((double)rowCount / 2));
      for (int i = 0; i < (int)Math.ceil((double)rowCount / 2); i++) {
        int row = i, column = i;
        int startCol = i, startRow = row;
        // horizontal top
        for (; column < columnCount - startCol; column++) spiralList.add(squareMatrix.get(row).get(column));
        // vertical last
        for (++row, --column; row < rowCount - startRow; row++) spiralList.add(squareMatrix.get(row).get(column));
        // horizontal down
        for (--row, --column; column >= startCol; column--) spiralList.add(squareMatrix.get(row).get(column));
        // vertical first
        for (--row, ++column; row > startRow; row--) spiralList.add(squareMatrix.get(row).get(column));
      }
    }

    System.out.println(spiralList);
    return spiralList;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrderingSegments.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
