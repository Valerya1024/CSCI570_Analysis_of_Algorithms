public class HW2P1 {
   private long cost;
   private final int charge = 4;

   public HW2P1() {
      cost = 0;
   }

   private boolean function0(long x){
      cost++;
      if (x == 1) {
         return true;
      }
      if (x == 0 || (x % (long)2 != 0)) {
         return false;
      }
      if (x > 1) {
         return function0(x / (long)2);
      }
      return false;
   }

   private void function1(long x){
      if (function0(x)){
         for (long i = 0; i < x ; i++){
            cost++;
            //System.out.println(i);
         }
      } else {
         //cost++;
         //System.out.println(x);
      }
      //System.out.println(cost);
   }

   public void function2(long n){
      for (long i = 1; i <= n; i++) {
         function1(i);
      }
      System.out.println(cost);
   }

   public static void main(String[] args) {
      HW2P1 p1 = new HW2P1();
      double k = 30;
      p1.function2((long) Math.pow(2,k));
      System.out.println((long) Math.pow(2,k)* p1.charge - p1.cost);

   }
}
