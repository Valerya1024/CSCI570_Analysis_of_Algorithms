package DP;

public class MarbleGame2 {

   private int[] value;
   private int n;
   private int[] prefixsum;
   private int[][] D;
   private boolean[][] visited;

   public MarbleGame2(int[] value) {
      this.value = value.clone();
      n = value.length;
      prefixsum = new int[n+1];
      int sum = 0;
      for (int i = 1; i<=n; i++) {
         sum += value[i-1];
         prefixsum[i] = sum;
      }
      D = new int[n][n];
      visited = new boolean[n][n];
      for (int i = 0; i<n; i++){
         visited[i][i] = true;
      }
   }

   public int sum(int i, int j) {
      return prefixsum[j+1] - prefixsum[i];
   }

   public int diffScore() {
      for (int l = 1; l < n; l++) {
         for (int i = 0; i < n-l; i++){ //Diagonal
            int j = i + l;
            if ((n-i-j)%2==0) { //Bruin's turn, minimize Diff
               int scoreI = D[i+1][j] - sum(i+1,j);
               int scoreJ = D[i][j-1] - sum(i,j-1);
               D[i][j] = Math.min(scoreI, scoreJ);
            } else { //Tommy's turn, maximize Diff
               int scoreI = D[i+1][j] + sum(i+1,j);
               int scoreJ = D[i][j-1] + sum(i,j-1);
               D[i][j] = Math.max(scoreI, scoreJ);
            }
         }
      }
      return D[0][n-1];
   }

   public void getTable(){
      for (int i = 0; i < n; i++) {
         for (int j = 0; j<n; j++){
            System.out.print(D[i][j]+" ");
         }
         System.out.println();
      }
   }

   public static void main(String[] args) {
      int[] arr = {1, 9, 8,9,1,2,1,3};
      MarbleGame2 marbleGame = new MarbleGame2(arr);
      System.out.println(marbleGame.diffScore());
      marbleGame.getTable();
   }
}
