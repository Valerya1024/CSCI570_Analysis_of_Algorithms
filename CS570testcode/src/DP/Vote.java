package DP;

import java.util.Arrays;

public class Vote {
   public static String winner(String[] votes){
      int n = votes.length;
      quicksort(votes, 0, n-1);
      if (n%2==1){
         String candidate = votes[n/2];
         int first = bisectLeft(candidate, votes);
         if (votes[n/2 + first]==candidate){
            return candidate;
         } else {
            return null;
         }
      } else {
         String candidate1 = votes[n/2-1];
         String candidate2 = votes[n/2];
         if (candidate1==candidate2){
            int first = bisectLeft(candidate1, votes);
            if (votes[n/2 + first]==candidate1){
               return candidate1;
            } else {
               return null;
            }
         } else {
            if (votes[0] == candidate1) {
               if (votes[n-1] == candidate2) {
                  return null;
               } else {
                  return candidate1;
               }
            } else if (votes[n-1] == candidate2) {
               return candidate2;
            }
         }
      }
      return null;
   }

   static int bisectLeft(String i, String[] arr) {
      int highIdx = arr.length;
      int lowIdx = 0;
      int mid;
      while (lowIdx < highIdx) {
         mid = (lowIdx + highIdx) / 2;
         if (arr[mid].compareTo(i) < 0) {
            lowIdx = mid + 1;
         } else {
            highIdx = mid;
         }
      }
      return lowIdx;
   }

   static void swap(String[] arr, int i, int j)
   {
      String temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
   }

   static int partition(String[] arr, int low, int high)
   {
      String pivot = arr[high];
      int i = (low - 1);
      for (int j = low; j <= high - 1; j++) {
         if (arr[j].compareTo(pivot) < 0) {
            i++;
            swap(arr, i, j);
         }
      }
      swap(arr, i + 1, high);
      return (i + 1);
   }

   static void quicksort(String[] arr, int low, int high)
   {
      if (low < high) {
         int pi = partition(arr, low, high);
         quicksort(arr, low, pi - 1);
         quicksort(arr, pi + 1, high);
      }
   }

   public static void main(String[] args){
      String[] votes = {"A","B","A","A","B","B"};
      quicksort(votes, 0, votes.length-1);
      System.out.println(Arrays.toString(votes));
      System.out.println(winner(votes));
   }
}
