package DP;

import java.util.Arrays;

public class BurstBallons {

   int[] numsDP;
   int[][] table;
   int n;

   public BurstBallons(int[] nums){
      n = nums.length;
      numsDP = new int[n+2];
      table = new int[n+2][n+2];
      numsDP[0] = numsDP[n+1] = 1;
      for (int i = 0; i<n; i++){
         numsDP[i+1] = nums[i];
      }
      for (int i = 0; i<n+2; i++) {
         for (int j = 0; j<n+2; j++){
            table[i][j] = -1; // not visited
         }
      }
   }

   int maxCoin(int i, int j){
      if (table[i][j] == -1){
         if (j-i==1){
            table[i][j] = 0;
         } else {
            for (int k=i+1; k<j; k++){
               int max = maxCoin(i,k) + maxCoin(k,j) + numsDP[i]* numsDP[k]* numsDP[j];
               if (max > table[i][j]){
                  table[i][j] = max;
               }
            }
         }
      }
      return table[i][j];
   }

   void getTable(){
      for (int i =0; i<n+2; i++){
         System.out.println(Arrays.toString(table[i]));
      }
   }

   public static void main(String[] args){
      int[] nums = {1,5};
      BurstBallons bb = new BurstBallons(nums);
      System.out.println(bb.maxCoin(0,nums.length+1));
      bb.getTable();
   }
}
