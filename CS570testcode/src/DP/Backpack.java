package DP;

import java.util.ArrayList;
import java.util.Arrays;

public class Backpack {

   private int[] size;
   private int[] value;
   private int capacity;
   private int[] table;

   public Backpack(int W, int[] value, int[] size){
      capacity = W;
      this.value = value.clone();
      this.size = size.clone();
      table = new int[W+1];
      for (int i = 1; i<=W; i++){
         table[i] = -1;
      }
   }

   public int maxValue(int W) {
      if (table[W]==-1){
         table[W] = 0;
         for (int i = 0; i<value.length;i++) {
            if (W-size[i]>=0){
               int val = maxValue(W-size[i]) + value[i];
               if (val > table[W]){
                  table[W] = val;
               }
            }
         }
      }
      return table[W];
   }

   public static void main(String[] args){
      int[] value = {1,3,5,7,9};
      int[] size = { 2,3,4,6,7};

      int W = 10;
      Backpack bp = new Backpack(W, value,size);
      System.out.println(bp.maxValue(W));
      System.out.println(Arrays.toString(bp.table));
   }

}

