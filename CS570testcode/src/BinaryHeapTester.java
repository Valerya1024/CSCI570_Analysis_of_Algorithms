import java.util.Random;

public class BinaryHeapTester {
   public static void main(String[] args) {

      BinaryHeap bin = new BinaryHeap(true);
      bin.printHeap();
      bin.deleteMinOrMax();
      bin.insert(10);
      bin.insert(3);
      bin.insert(5);
      bin.insert(1);
      bin.insert(19);
      bin.insert(15);
      bin.insert(2);
      bin.printHeap();


      int[] arr = new int[7];
      arr[0] = 10;
      arr[1] = 7;
      arr[2] = 0;
      arr[3] = 4;
      arr[4] = 19;
      arr[5] = 13;
      arr[6] = 21;
      BinaryHeap bin1 = new BinaryHeap(true, arr);
      bin1.printHeap();
      bin1.heapSort();
      bin1.printHeap();
      BinaryHeap bin2 = new BinaryHeap(false, arr);
      bin2.printHeap();
      bin2.heapSort();
      bin2.printHeap();
      bin1.merge(bin);
      bin1.printHeap();
      bin1.heapSort();
      bin1.printHeap();

      int n = 20;
      arr = new int[n];
      Random r = new Random();
      for (int i = 0; i< n; i++) {
         arr[i] = r.nextInt(100);
      }
      BinaryHeap bin3 = new BinaryHeap(true, arr);
      bin3.printHeap();
      bin3.heapSort();
      bin3.printHeap();

      bin3.merge(bin1);
      bin3.printHeap();
      bin3.heapSort();
      bin3.printHeap();

      n = 40;
      arr = new int[n];
      for (int i = 0; i< n; i++) {
         arr[i] = r.nextInt(100);
      }
      BinaryHeap bin4 = new BinaryHeap(true, arr);
      bin4.printHeap();
      bin4.heapSort();
      bin4.printHeap();

      bin4.merge(bin3);
      bin4.printHeap();
      bin4.heapSort();
      bin4.printHeap();
   }

}
