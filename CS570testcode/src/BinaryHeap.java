import java.util.Arrays;

public class BinaryHeap {
   private int[] arr;
   private int size;
   private boolean minHeap;
   private static final int initLen = 20;
   private static final double factor = 1.2;

   /**
    * Creates empty binary heap
    */
   public BinaryHeap(boolean min) {
      arr = new int[initLen+1];
      size = 0;
      minHeap = min;
   }

   /**
    * Builds binary heap with known sequence
    * @param initArr
    */
   public BinaryHeap(boolean min, int[] initArr) {
      minHeap = min;
      if (initArr.length >= initLen) {
         arr = new int[(int) Math.round((initArr.length+1)*factor)];
      } else {
         arr = new int[initLen+1];
      }
      for (int i=0; i<initArr.length; i++) {
         arr[i+1] = initArr[i];
      }
      size = initArr.length;
      heapify();
   }

   public void printHeap() {
      System.out.println(toString());
   }

   public int[] toArray() {
      int[] newArr = new int[size];
      for (int i = 0; i < size; i++) {
         newArr[i] = arr[i+1];
      }
      return newArr;
   }

   public String toString() {
      return Arrays.toString(toArray());
   }

   public int findMinOrMax() {
      return arr[1];
   }

   public int getSize() {
      return size;
   }

   public void insert(int num) {
      size++;
      if (size==arr.length) {
         extendArr();
      }
      arr[size] = num;
      swapWithParent(size);
   }

   public int deleteMinOrMax() {
      if (size<=0) {
         System.out.println("Heap is empty");
         return 0;
      }
      int min = arr[1];
      swap(1,size);
      size--;
      swapWithChildren(1);
      return min;
   }

   public void heapSort() {
      int len = size;
      while (size > 0) {
         deleteMinOrMax();
      }
      for (int i = 0; i<len/2; i++) {
         swap(i+1, len-i);
      }
      size = len;
   }

   public void merge(BinaryHeap b2) {
      if (size + b2.getSize() > arr.length-1) {
         extendArr(size + b2.getSize());
      }
      int[] b2arr = b2.toArray();
      for (int i = 0; i < b2arr.length; i++) {
         arr[size+1+i] = b2arr[i];
      }
      size += b2arr.length;
      heapify();
   }

   public int decreaseKey() {
      return 0;
   }

   public int increaseKey() {
      return 0;
   }

   private void heapify() {
      for (int i=size/2; i > 0; i--) {
         swapWithChildren(i);
      }
   }

   private void swapWithParent(int key) {
      if (key <= 1) {
         return;
      }
      if (minHeap) {
         if (arr[key/2] > arr[key]) {
            swap(key/2, key);
            swapWithParent(key/2);
         }
      } else {
         if (arr[key/2] < arr[key]) {
            swap(key/2, key);
            swapWithParent(key/2);
         }
      }
   }

   private void swapWithChildren(int key) {
      if (key > size/2) {
         return;
      }
      if (minHeap) {
         if (2*key+1 > size) {
            if (arr[2*key] < arr[key]) {
               swap(2*key, key);
               swapWithChildren(2*key);
            }
         } else if (arr[2*key] < arr[key] || arr[2*key+1] < arr[key]) {
            if (arr[2*key] < arr[2*key+1]) {
               swap(2*key, key);
               swapWithChildren(2*key);
            } else {
               swap(2*key+1, key);
               swapWithChildren(2*key+1);
            }
         }
      } else {
         if (2*key+1 > size) {
            if (arr[2*key] > arr[key]) {
               swap(2*key, key);
               swapWithChildren(2*key);
            }
         } else if (arr[2*key] > arr[key] || arr[2*key+1] > arr[key]) {
            if (arr[2*key] > arr[2*key+1]) {
               swap(2*key, key);
               swapWithChildren(2*key);
            } else {
               swap(2*key+1, key);
               swapWithChildren(2*key+1);
            }
         }
      }
   }

   /**
    * Pre: 1 <= key1 <= size, 1 <= key2 <= size
    * @param key1
    * @param key2
    */
   private void swap(int key1, int key2) {
      int tmp = arr[key1];
      arr[key1] = arr[key2];
      arr[key2] = tmp;
   }

   private void extendArr() {
      int[] newArr = new int[(int) (Math.round(arr.length*factor))];
      for (int i=0; i<arr.length; i++) {
         newArr[i] = arr[i];
      }
      arr = newArr;
   }

   private void extendArr(int len) {
      int[] newArr = new int[(int) (Math.round((len+1)*factor))];
      for (int i=0; i<=size; i++) {
         newArr[i] = arr[i];
      }
      arr = newArr;
   }

}
