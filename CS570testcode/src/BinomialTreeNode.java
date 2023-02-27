import java.util.ArrayList;

public class BinomialTreeNode<T> {
   private T node;
   private BinomialTreeNode<T> parent;
   private ArrayList<BinomialTreeNode<T>> children;

   public BinomialTreeNode(T n) {
      node = n;
      children = new ArrayList<>();
      parent = null;
   }

   public void addChild(BinomialTreeNode<T> a) {
      children.add(a);
   }

   public void setParent(BinomialTreeNode<T> p) {
      parent = p;
   }

   /**
    * caution: no defensive copy.
    */
   public ArrayList<BinomialTreeNode<T>> getChildren() {
      return children;
   }

   public T getValue() {
      return node;
   }
}

