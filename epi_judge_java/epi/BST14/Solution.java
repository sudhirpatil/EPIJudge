package epi.BST14;

import java.util.*;

public class Solution {
    public static void main(String[] args){
        int[][] ar = {{1,3},{2,6},{8,10},{15,18}};
        Arrays.sort(ar, (a,b) -> {
            return Integer.compare(a[0], b[0]);
        });
        System.out.println(Arrays.deepToString(merge(ar)));
    }

    public static int[][] merge(int[][] intervals) {
        int size = intervals.length;
        int[][] arr = new int[size][2];

        int[] curr = new int[2];
        int j=0;
        for(int i =0; i< size;i++){
            if(i==0){
                curr = intervals[i];
            }else{
                int[] el = intervals[i];
                if(curr[1] >= el[0]){
                    curr[1] = el[1];
                }else{
                    arr[j++] = curr;
                    curr = el;
                }
            }
        }
        arr[j++] = curr;
        return arr;
    }
}
