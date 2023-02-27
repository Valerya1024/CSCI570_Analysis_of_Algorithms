package DP;

public class MarbleGame3 {
   public static void main(String[] args) {
      int[] marbles = {1, 9, 8,9,1,2,1,3};
      int result = maxScoreDifference(marbles);
      System.out.println("the difference is: " + result);
   }
   public static int maxScoreDifference(int[] m) {
      int n = m.length;
      int[][] dp = new int[n][n];
      int[][] sum = new int[n][n];
      if(n <= 1) {
         return 0;
      }
      for (int i = 0; i < n; i++) {
         dp[i][i] = 0;
         sum[i][i] = m[i];
         for (int j = i+1; j < n; j++) {
            sum[i][j] = sum[i][j-1] + m[j];
         }
      }
      for (int len = 2; len <= n; len++) {
         for (int i = 0; i <= n-len; i++) {
            int j = i + len - 1;
            dp[i][j] = Math.max(Math.abs(dp[i][j-1] - sum[i][j-1]),
                  Math.abs(dp[i+1][j] - sum[i+1][j]));
         }
      }
      dp[0][n-1] = Math.max(Math.abs(dp[0][n-2] - sum[0][n-2]),
            Math.abs(dp[1][n-1] - sum[1][n-1]));
      return dp[0][n-1];
   }

}
