package DP;

public class CutRod {
   public static int maxMoney(int N, int[] P) {
      int[] table = new int[N+1];
      for (int len = 1; len <= N; len++) {
         for (int cutlen = 1; cutlen <= len; cutlen++) {
            int money = P[cutlen - 1] + table[len - cutlen];
            if (money > table[len]) {
               table[len] = money;
            }
         }
      }
      return table[N];
   }

   public static void main(String[] args) {
      int N = 7;
      int[] P = { 1, 1, 2, 3, 5, 5, 5 };
      System.out.println(maxMoney(N, P));
   }
}
