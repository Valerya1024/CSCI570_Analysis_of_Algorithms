package DP;

import java.util.Arrays;

public class MarbleGame {

   private int n;
   private int[] value;
   private int[] prefixsum;
   private int[][] B;
   private int[][] T;
   private boolean[][] visited;

   public MarbleGame(int[] value) {
      n = value.length;
      this.value = value.clone();
      prefixsum = new int[n+1];
      int sum = 0;
      for (int i = 1; i<=n; i++) {
         sum += value[i-1];
         prefixsum[i] = sum;
      }
      B = new int[n][n];
      T = new int[n][n];
      visited = new boolean[n][n];
      for (int i = 0; i<n; i++){
         visited[i][i] = true;
      }
   }

   public int diffScore(){
      maxScore(0, n-1);
      return T[0][n-1] - B[0][n-1];
   }

   private void maxScore(int i, int j){
      if ((n-i-j)%2 == 0){// op turn
         if (!visited[i+1][j]) {
            maxScore(i+1, j);
         }
         int scoreI = B[i+1][j] + sum(i+1,j);//take i
         if (!visited[i][j-1]) {
            maxScore(i, j-1);
         }
         int scoreJ = B[i+1][j] + sum(i,j-1);//take j
         if (scoreJ > scoreI) {
            B[i][j] = scoreJ;
            T[i][j] = T[i][j-1];
         } else {
            B[i][j] = scoreI;
            T[i][j] = T[i+1][j];
         }
      } else {
         if (!visited[i+1][j]) {
            maxScore(i+1, j);
         }
         int scoreI = T[i+1][j] + sum(i+1,j);//take i
         if (!visited[i][j-1]) {
            maxScore(i, j-1);
         }
         int scoreJ = T[i+1][j] + sum(i,j-1);//take j
         if (scoreJ > scoreI) {
            T[i][j] = scoreJ;
            B[i][j] = B[i][j-1];
         } else {
            T[i][j] = scoreI;
            B[i][j] = B[i + 1][j];
         }
      }
      visited[i][j] = true;
   }

   public void getTable1(){
      for (int i = 0; i < n; i++) {
         for (int j = 0; j<n; j++){
            System.out.print(B[i][j]);
         }
         System.out.println();
      }
   }


   public void getTable2(){
      for (int i = 0; i < n; i++) {
         for (int j = 0; j<n; j++){
            System.out.print(T[i][j]);
         }
         System.out.println();
      }
   }

   public int sum(int i, int j) {
      return prefixsum[j+1] - prefixsum[i];
   }

   public static void main(String[] args) {
      int[] arr = {1,8,9,8,1,2,1,3};
      MarbleGame marbleGame = new MarbleGame(arr);
      System.out.println(marbleGame.diffScore());
      System.out.println(Arrays.toString(marbleGame.prefixsum));
      marbleGame.getTable1();
      marbleGame.getTable2();
   }
}
/** "both players play optimally" means both player are maximizing their score by the end of the game.\\
 a. Let B($i$,$j$) be the max score Bruin can get  when the subproblem is the $i$th to the $j$th marbles, while T($i$,$j$) is the max score Tommy can get. If it's Tommy's turn, Tommy will maximize his score by choosing between the max score of removing the $i$th marble (T($i+1$,$j$) + sum of $i+1$th to $j$th elements) and max score of removing the $j$th marble (T($i+1$,$j$) + sum of $i+1$th to $j$th elements). If Tommy choose to remove the $i$th element, Bruin's max score B($i$,$j$) is B($i+1$,$j$), else B($i$,$j$) is B($i$,$j-1$). If it's Bruin's turn, Bruin also maximizes his score in a similar way.\\
 b. If $n-i-j$ is even (Bruin's turn): B($i$,$j$) = max(B($i+1$,$j$) + sum($i+1$,$j$), B($i$,$j-1$) + sum($i$,$j-1$)). If Bruins removes $i$th marble, T($i$,$j$) = T($i+1$,$j$) else T($i$,$j$) = T($i$,$j-1$)\\
 If $n-i-j$ is odd (Tommy's turn): T($i$,$j$) = max(T($i+1$,$j$) + sum($i+1$,$j$), T($i$,$j-1$) + sum($i$,$j-1$)). If Tommy removes $i$th marble, B($i$,$j$) = B($i+1$,$j$) else B($i$,$j$) = B($i$,$j-1$)\\
 c. Algorithm (memoization, calculate sum of elements with prefix sum array):\\
 \begin{lstlisting}[language = Java]
 int[] value = { ... }; //input

 int n = value.length;
 prefixsum = new int[n+1];
 int sum = 0;
 for (int i = 1; i<=n; i++) {
 sum += value[i-1];
 prefixsum[i] = sum;
 }
 boolean[][] visited = new boolean[n][n];
 for (int i = 0; i<n; i++){
 visited[i][i] = true;//Base case: only one marble left
 }

 int sum(int i, int j) {
 return prefixsum[j+1] - prefixsum[i];
 }

 void maxScore(int i, int j){
 if ((n-i-j)%2 == 0){//Bruin's turn
 if (!visited[i+1][j]) {
 maxScore(i+1, j);
 }
 int scoreI = B[i+1][j] + sum(i+1,j);//take i
 if (!visited[i][j-1]) {
 maxScore(i, j-1);
 }
 int scoreJ = B[i+1][j] + sum(i,j-1);//take j
 if (scoreJ > scoreI) {
 B[i][j] = scoreJ;
 T[i][j] = T[i][j-1];
 } else {
 B[i][j] = scoreI;
 T[i][j] = T[i+1][j];
 }
 } else {
 if (!visited[i+1][j]) {
 maxScore(i+1, j);
 }
 int scoreI = T[i+1][j] + sum(i+1,j);//take i
 if (!visited[i][j-1]) {
 maxScore(i, j-1);
 }
 int scoreJ = T[i+1][j] + sum(i,j-1);//take j
 if (scoreJ > scoreI) {
 T[i][j] = scoreJ;
 B[i][j] = B[i][j-1];
 } else {
 T[i][j] = scoreI;
 B[i][j] = B[i + 1][j];
 }
 }
 visited[i][j] = true;
 }

 maxScore(0, n-1);
 int diffscore = T[0][n-1] - B[0][n-1];
 \end{lstlisting}
 d. Base case: when $i == j$ (only one marble), both player get 0 score. The final answer can be found at T[0][$n$-1] - B[0][$n$-1].\\
 e. The time complexity is $O(n^{2})$.\\

 */