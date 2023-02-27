package DP;

import java.lang.constant.Constable;

public class SQRT {
   public static Integer sqrt(int N) {
      if (N == 0) {
         return 0;
      }
      int right = 1;
      while (right*right < N){ //find upper limit
         right = right*2;
      }
      int left = right/2; //lower limit
      while (right >= left){//binary search
         int mid = (left+right)/2;
         if (mid*mid > N){
            right = mid - 1;
         } else if (mid*mid < N) {
            left = mid + 1;
         } else {
            return mid;
         }
      }
      return null;
   }

   public static void main(String[] args){
      System.out.println(sqrt(5));
      System.out.println(sqrt(9));
      System.out.println(sqrt(81));
      System.out.println(sqrt(1024));
   }
}
