package epi.ArraysCh5;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrderingSegments5_18 {
  @EpiTest(testDataFile = "spiral_ordering_segments.tsv")

  /*
  Rounding up  : Match.ceil((double) a/b)

  How to cycle, externalize adding,sub using 2d array (0,1)(0,-1) etc.
  Mark visited index 0, to know when to turn cycle & enter inner circle

  last increment in loop i++, increases
  missed creating row from cy index
   */

  /* KEY
    Represent shift in 2D array, to move in matrix boundary
    mark visited cell with 0
   */
  public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    List<Integer> list = new ArrayList<>();
    int n = squareMatrix.size();
    int[][] dims= {{0,1}, {1,0},{0,-1},{-1,0}};
    int x=0, y=0,dim =0;
    for(int i=0;i<n*n; i++){
      list.add(squareMatrix.get(x).get(y));
      squareMatrix.get(x).set(y, 0);
      int nextX= x + dims[dim][0], nextY = y + dims[dim][1];
      //need to change deems?
      if(nextX<0 || nextX>=n || nextY <0 || nextY >=n || squareMatrix.get(nextX).get(nextY) ==0){
        dim = (dim + 1) % 4;
        nextX= x + dims[dim][0];
        nextY = y + dims[dim][1];
      }

        x = nextX;
        y = nextY;
    }
    return list;
  }


  public static List<Integer> matrixInSpiralOrder2(List<List<Integer>> squareMatrix) {
    List<Integer> list = new ArrayList();
    int n = squareMatrix.size()-1, row = 0, col= 0;
    System.out.println("n:"+n);
    for(int cy=0; cy<Math.ceil((double)(n+1)/2);cy++){
      System.out.println("n:"+n);
      // horizontal fwd
      for(col=cy;col<=n-cy;col++)
        list.add(squareMatrix.get(row).get(col));
      col--;
      for(row=cy+1;row<=n-cy;row++)
        list.add(squareMatrix.get(row).get(col));
      row--;
      for(col=col-1;col>=cy;col--)
        list.add(squareMatrix.get(row).get(col));
      col++;
      for(row=row-1;row>=cy+1;row--)
        list.add(squareMatrix.get(row).get(col));
      row++;
    }
    return list;
  }

  public static List<Integer> matrixInSpiralOrder1(List<List<Integer>> squareMatrix) {
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
