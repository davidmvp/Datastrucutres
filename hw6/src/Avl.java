
/**
 * This is to collect all the elements in a tree.
 */
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;






public class Avl <T extends Comparable<? super T>> implements OrderedSet<T> {
    private class ANode {
        private T data;
        private ANode left;
        private ANode right;
        private int height = 0;
        public ANode(T val) {
            data = val;
            
        }
        public boolean isLeaf() {
            return left == null && right == null;
        }
    }
    
    private ANode root;
    private int size;
    private int height = 0;
    
    public Avl() {
        this.root = null;
        this.size = 0;
       
    }
    @Override
    public Iterator<T> iterator() {
        return null;
       // AvlIterator myIter = new AvlIterator();
       // return myIter;
       
    }

    @Override
    public int size() {
        
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
        return true;
        }
        else 
            return false;
    }

    @Override
    public boolean add(T val) {
        
        root = add(val,root);
        size++;
        return false;
    }
    public int height(ANode a) {
    if (a == null) {
        return 0;
    }
    return a.height;
    }
    private int max(int h,int h1) {
        if (h > h1) {
            return h;
        }
        return h1;
    }
    private ANode rightRotate(ANode curr) {
        System.out.println("h232a");
        ANode temp = curr.left;
        curr.left = temp.right;
        temp.right = curr;
        curr.height = max(height(curr.left), height(curr.right));
        temp.height = max(height(temp.left), height(temp.right));
        return temp;
    }
    private ANode leftRotate(ANode curr) {
        System.out.println("h2323a");
        ANode temp = curr.right;
        curr.right = temp.left;
        temp.left = curr;
        curr.height = max(height(curr.left), height(curr.right));
        temp.height = max(height(temp.left), height(temp.right));
        return temp;
    }
    private ANode leftRightRotate(ANode curr) {
        System.out.println("h111a");
        curr.left = leftRotate(curr.left);
        return rightRotate(curr);
    }
    private ANode rightLeftRotate(ANode curr) {
        System.out.println("ha22");
        curr.right = rightRotate(curr.right);
        return leftRotate(curr);
    }
    private ANode add(T val, ANode curr) {
        System.out.println("gg");
        if (curr == null) { // leaf, make new node
            System.out.println("ha");
           curr = newNode(val);
           
        }
        else if (curr.data.compareTo(val) == 0) {
            return null;
        }
        else if (curr.data.compareTo(val) > 0) {
            System.out.println("hs12sa");
            curr.left = add(val, curr.left);
            System.out.println("hssa");
            if (height(curr.left)-height(curr.right) == 2) {
                if (curr.left.data.compareTo(val) > 0) {
                    curr = rightRotate(curr);
                }
                else {
                    curr = leftRightRotate(curr);
                }
            
        }
        }
       
        else {  // duplicates will go to right
            System.out.println("hssa");
            curr.right = add(val, curr.right); 
         
            System.out.println("intersting");
            if (height(curr.right)-height(curr.left) == 2) {
                if (curr.right.data.compareTo(val) < 0) {
                    curr = leftRotate(curr);
                }
                else {
                    curr = rightLeftRotate(curr);
                }
            
        }
        }
        System.out.println("left height" + height(curr.left) + "right height" + height(curr.right));
        curr.height = max(height(curr.left),height(curr.right))+1;
        System.out.println("Curr.data  " + curr.data + " curr height " + curr.height);
        return curr;
    }
    
    private ANode newNode(T val) {
        ANode a = new ANode(val);
        a.left = null ;
                a.right = null;
        a.height = 0;
        System.out.println("dada" + val);
        return a;
    }
public void print() { System.out.println("height" + root.height);
    System.out.println(root.data);
   // System.out.println(root.right.data);
    System.out.println(root.left.data);
    System.out.println(root.right.data);
}
    @Override
    public boolean remove(T o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(T o) {
        // TODO Auto-generated method stub
        return false;
    }
    public Iterable<T> inOrder() {
        return inOrder(root);
    }

    // this can no longer be static because BNode is not static
    private Collection<T> inOrder(ANode curr) {
        LinkedList<T> iter = new LinkedList<T>();
        if (curr == null)
            return iter;
        iter.addAll(inOrder(curr.left));
        
        iter.addLast(curr.data);
       
        iter.addAll(inOrder(curr.right));
        return iter;
        
    }

    @Override
    public Set<T> union(Set<T> that) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<T> intersect(Set<T> that) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OrderedSet<T> subset(T start, T end) {
        // TODO Auto-generated method stub
        return null;
    }

}
