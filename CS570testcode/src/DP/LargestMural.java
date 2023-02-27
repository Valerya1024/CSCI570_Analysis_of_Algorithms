package DP;

public class LargestMural {
   public static int largest(int[] heights, int left, int right) {
      if (left==right) {
         return heights[left];
      }
      if (left>right){
         return 0;
      }
      int minIdx = left;
      for (int i=left+1; i<=right; i++) {
         if (heights[i]<heights[minIdx]) {
            minIdx = i;
         }
      }
      return Math.max(Math.max(largest(heights, minIdx+1, right), largest(heights, left, minIdx-1)), heights[minIdx]*(right-left+1));
   }

   public static void main(String[] args){
      int[] arr = {1, 3, 4, 5, 2, 7, 3};
      System.out.println(largest(arr, 0, arr.length-1));
   }
}
