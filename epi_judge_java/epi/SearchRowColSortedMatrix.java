package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchRowColSortedMatrix {
  @EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")

  public static boolean matrixSearch(List<List<Integer>> A, int x) {
    /*
    compare 0,n-1 with x, if x>elem row++ , if x<elem col--
     */
    int rSize = A.size()-1, cSize = A.get(0).size()-1;
    int row = 0, col = cSize;
    while (row>=0 && col>=0 && row <= rSize && col <= cSize){
      int val = A.get(row).get(col);
      if(x == val)
        return true;
      else if(x > val){
        row++;
      }else{
        col--;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchRowColSortedMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
