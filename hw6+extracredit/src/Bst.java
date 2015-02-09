import java.util.Collection;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * @author davidmvp23
 *
 * @param <T>
 */
public class Bst<T extends Comparable<? super T>> implements OrderedSet<T> {

    /**
     * Node for bst tree.
     * @author davidmvp23
     *
     */
    private class BNode {
        /**
         * the data that node holds.
         */
        private T data;
        /**
         * left child.
         */
        private BNode left;
        /**
         * right child.
         */
        private BNode right;
        /**
         * insert value into the node.
         * @param val val is he dat we want to insert in.
         * 
         */
       
        public BNode(T val) {
            this.data = val;
        }
        /**
         * check if it is the leaf.
         * @return true of false
         */
        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }
    }
    /**
     * the root of the tree.
     */
    private BNode root;
    /**
     * the size of the tree.
     */
    private int size;
    /**
     * Constructor for bst.
     * Its running time is O(1).
     */
    public Bst() {
        this.root = null;
        this.size = 0;
    }
    /**
     * The Iterator method.
     * Its running time is O(n), because we need to
     * create a collection of all the roots using inOrder traverse.
     * @return return the new iterator.
     */
    @Override
    public Iterator<T> iterator() {
        BstIterator myIter = new BstIterator();
        return myIter;
    }
    /**
     * Size of the tree.
     * The runtime for size is O(1).
     * @return the size.
     */
    public int size() {
      
        return this.size;
    }

    /**
     * Find if the tree is empty.
     * The runtime for isEmpty O(1).
     * @return return true if it is empty.
     */
    public boolean isEmpty() {
        
        return this.size() == 0;
    }
    /**
     * find the root data.
     * The runtime for root is O(1).
     * @return the data.
     */
    public T root() {
        if (this.root == null) {
            return null;
        } else {
            return this.root.data;
        }
    }
    /**
     * Find if the tree contains the data.
     * @param val the data.
     * The run time time for contains is O(n), because the tree is not balanced.
     * @return return true if it exists
     */
    public boolean contains(T val) {
        return this.contains(val, this.root) != null;
    }
    /**
     * recursively go through the tree.
     * The running time is O(n).
     * @param val value of the data.
     * @param curr current root.
     * @return the root.
     */
    public BNode contains(T val, BNode curr) {
        if (curr == null) {
            return null;
        }
        if (curr.data.compareTo(val) == 0) {
            return curr;
        }
        if (curr.data.compareTo(val) > 0) {
            return this.contains(val, curr.left);
        }
        return this.contains(val, curr.right);
    }
    /**
     * add method.
     * The big O running time for add is O(n), on average its log(n).
     * @param val we want to insert.
     * @return return true if it is inserted.
     */
    public boolean add(T val) {
        if (this.contains(val)) {
            return false;
        }
     
        
        this.root = this.add(val, this.root);
       
        this.size++;
        return true;
    }
    /**
     * helper method for add, so we can do it recursively.
     * The run time is O(n).
     * @param val data.
     * @param curr current node.
     * @return the node.
     */
    private BNode add(T val, BNode curr) {
        if (curr == null) {
            return new BNode(val);
        }
        if (curr.data.compareTo(val) == 0) {
            return curr;
        }
        if (curr.data.compareTo(val) > 0) {
            curr.left = this.add(val, curr.left);
        } else {  // duplicates will go to right
            curr.right = this.add(val, curr.right);
        }
        return curr;
    }
   /**
    * Remove method.
    * The big o running time for remove is O(n), the average is O(log n).
    * @param val the data we want to  remove.
    * @return true if it has been removed.
    */
    public boolean remove(T val) {
        if (this.contains(val)) {
            this.root = this.remove(val, this.root);
            return true;
        }
        return false;
    }
    /**
     * help method for remove.
     * The big o running time for remove is O(n)
     * @param val the value we want to delete.
     * @param curr current root.
     * @return return the node thats being delete.
     */
    public BNode remove(T val, BNode curr) {
        if (curr == null) {
           
            return curr;
        }
        int diff = curr.data.compareTo(val);
       
        if (diff > 0) {
            
            curr.left = this.remove(val, curr.left);
        } else if (diff < 0) {
           
            curr.right = this.remove(val, curr.right);
        } else { 
            if (curr.isLeaf()) {
                if (curr == this.root) {
                    this.root = null;
                    this.size--;
                }
               
                curr = null;
               
              
                this.size--; 
            } else {  

                BNode change;
                if (curr.right != null) {  
                    change = findMin(curr.right);  
                    curr.data = change.data;
                    curr.right = this.remove(change.data, curr.right);
                } else {  
                    curr = curr.left;
                    this.size--;  
                }
            }
        }
        return curr;
    }
    
/**
 * find the minimum value.
 * The big O running time for findmin is O(n).
 * @param curr current noed.
 * @return the minmum node.
 */
    private BNode findMin(BNode curr) {
        if (curr == null) {
            return curr;
        }
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    /**
 * This method is to print out the tree in order.
 * The big O running time for toString is O(n).
 * @return return the string.
 */
    public String toString() {
        return inOrder().toString();
    }

/**
 * Bst Iterator class can iterate through a bst tree.
 * @author davidmvp23
 *
 */
    private class BstIterator implements Iterator<T> {
        /**
         * Collection of all the roots in the tree.
         */
        Collection<T> collection;
     
        /**
         * Iterator that iterates through the collection.
         */
        
        Iterator<T> it;
       /**
         * constructor for bstIterator.
         */
        private T temp;
       
        /**
         * Constructor for the iterator.
        /* The big O running time for the constructor is O(n),
         * because the inOrder method costs O(n).
         */
         
        
        public BstIterator() {
           
            this.collection = inOrder(Bst.this.root);
            this.it = this.collection.iterator();
            
           
        }
        
        /**The big O running time for hasNext is O(1).
         * @return true it still has a next element.
         */
        @Override
        public boolean hasNext() {
            if (this.it.hasNext()) {
                return true;
            }
            return false;
       
        }
        /**The big O running time for next is O(1).
         * @return the data it has.
         */
        @Override
        public T next() {
            if (this.it.hasNext()) {
                this.temp = this.it.next();
                return this.temp;
                
            }
           
            return null;
        }
        
        /**The big O running time for remove is O(1).
         * Remove the next element in the collection.
         */
        @Override
        public void remove() {
            this.it.remove();
            Bst.this.remove(this.temp);
        }
        
       
       
    }
    
    /** Inorder traversal.
     * The big O running time for inOrder is O(n).
     * because we need to traverse every element in the tree.
     * @return return the tree in order.
     */
    public Iterable<T> inOrder() {
        return this.inOrder(this.root);
    }
   
    /**
     * Put every root in the collection and in order.
     * The big O running time for O(n).
     * @param curr the current node.
     * @return return all the roots in order.
     */
    private Collection<T> inOrder(BNode curr) {
        
        LinkedList<T> iter = new LinkedList<T>();
        if (curr == null) {
            return iter;
        }
      
        iter.addAll(this.inOrder(curr.left));
        iter.addLast(curr.data);
      
        iter.addAll(this.inOrder(curr.right));
        return iter;
    
    }

    /**
     * The big O running time for union is O(n).
     * @param that the other set we use to create the union.
     * @return return the union set.
     */
    @Override
    public Set<T> union(final Set<T> that) {
        Bst<T> temp = new Bst<T>();
        Bst<T> tempThat =  (Bst<T>) that;
        Iterator<T> itr = this.iterator();
        Iterator<T> itr2 = tempThat.iterator();
        int diff;

       

        T info, info2;
        info = itr.next();
        info2 = itr2.next();
        while (info != null && info2 != null) {
            diff = info.compareTo(info2);
            if (diff <= 0) {
                temp.add(info);
                info = itr.next();
                if (diff == 0) {
                    info2 = itr2.next();
                }
            } else if (diff > 0) {
                temp.add(info2);
                info2 = itr2.next();
            }
        }
        // add in rest of remaining list
        while (info != null) {
            temp.add(info);
            info = itr.next();
        }
        while (info2 != null) {
            temp.add(info2);
            info2 = itr2.next();
        }
        return temp;
    }
     
        
    
    
    
    /**
     * The big O running time for intersect is O(n).
     * @param that the other set we use to create the intersect.
     * @return return the intersect set.
     */
    @Override
    public Set<T> intersect(Set<T> that) {
        Bst<T> temp = new Bst<T>();
        Bst<T> tempThat = (Bst<T>) that;
        Iterator<T> itr = this.iterator();
        Iterator<T> itr2 = tempThat.iterator();
        int diff;

        T info, info2;
        info = itr.next();
        info2 = itr2.next();
        while (info != null && info2 != null) {
            diff = info.compareTo(info2);
            if (diff < 0) {
                info = itr.next();
            } else if (diff > 0) {
                info2 = itr2.next();
            } else {
                temp.add(info);
                info = itr.next();
                info2 = itr2.next();
            }
        }
        return temp;
    }

    @Override
    public OrderedSet<T> subset(T start, T end) {
       
        return null;
    }

}
