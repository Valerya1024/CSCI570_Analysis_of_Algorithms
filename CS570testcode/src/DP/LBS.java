package DP;

import java.util.Arrays;

public class LBS {

   int n;
   int[] height;
   int[] LIS;
   int[] LDS;

   LBS(int[] value) {
      height = value.clone();
      n = value.length;
      LIS = new int[n];
      LDS = new int[n];
      LIS[0] = 1;
      LDS[n-1] = 1;
   }

   int lis(int i) {
      if (LIS[i]==0) {
         LIS[i] = 1;
         int j = i;
         while (j>0) {
            j--;
            if (height[j] < height[i]) {
               int maxlen = lis(j) + 1;
               if (maxlen > LIS[i]) {
                  LIS[i] = maxlen;
               }
            }
         }
      }
      return LIS[i];
   }

   int lds(int i) {
      if (LDS[i]==0) {
         LDS[i] = 1;
         int j = i+1;
         while (j<n) {
            if (height[j] < height[i]) {
               int maxlen = lds(j) + 1;
               if (maxlen > LDS[i]) {
                  LDS[i] = maxlen;
               }
            }
            j++;
         }
      }
      return LDS[i];
   }

   int lbs() {
      int maxlen = 0;
      for (int i = 0; i<n; i++){
         int len = lds(i) + lis(i) - 1;
         if (len > maxlen) {
            maxlen = len;
         }
      }
      return maxlen;
   }

   public static void main(String[] args){
      int[] arr = {1,2,4,3,5,6,2,0,1,1};
      LBS lbs = new LBS(arr);
      System.out.println(lbs.n-lbs.lbs());
      System.out.println(Arrays.toString(lbs.LIS));
      System.out.println(Arrays.toString(lbs.LDS));
   }
}
