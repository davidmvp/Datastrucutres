import java.util.Collection;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * @author davidmvp23
 *
 * @param <T>
 */
public class Splay <T extends Comparable<? super T>> implements OrderedSet<T> {

    /**
     * Node for splay tree.
     * @author davidmvp23
     *
     */
    private class SNode {
        /**
         * the data that node holds.
         */
        private T data;
        /**
         * left child.
         */
        private SNode left;
        /**
         * right child.
         */
        private SNode right;
        /**
         * insert value into the node.
         * @param val val is he dat we want to insert in.
         * 
         */
       
        public SNode(T val) {
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
    private SNode root;
    /**
     * the size of the tree.
     */
    private int size;
    public Splay() {
        this.root = null;
        this.size = 0;
    }
    @Override
    public Iterator<T> iterator() {
        SplayIterator myIter = new SplayIterator();
        return myIter;
    }
    /**
     * Size of the tree.
     * @return the size.
     */
    public int size() {
      
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        
        return this.size() == 0;
    }
    /**
     * find the root data.
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
     * @return return true if it exists
     */
    public boolean contains(T val) {
        return this.contains(val, this.root) != null;
    }
    /**
     * recursively go through the tree.
     * @param val value of the data.
     * @param curr current root.
     * @return the root.
     */
    public SNode contains(T val, SNode curr) {
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
    @Override
    public boolean add(T val) {
        if (this.contains(val)) {
            return false;
        }
     
        
        this.root = this.add(val,this.root);
       
        this.size++;
        splay(val);
        return true;
    }
    /**
     * helper method for add, so we can do it recursively.
     * @param val data.
     * @param curr current node.
     * @return the node.
     */
    private SNode add(T val, SNode curr) {
        if (curr == null) {
            return new SNode(val);
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
    @Override
    public boolean remove(T val) {
        if (this.contains(val)) {
        this.root = this.remove(val, this.root);
        
        return true;
        }
        return false;
    }
    /**
     * help method for remove.
     * @param val the value we want to delete.
     * @param curr current root.
     * @return return the node thats being delete.
     */
    public SNode remove(T val, SNode curr) {
        if (curr == null) {
           
            return curr;
        }
        int diff = curr.data.compareTo(val);
       
        if (diff > 0) {
            
            curr.left = this.remove(val, curr.left);
        } else if (diff < 0) {
           
            curr.right = this.remove(val, curr.right);
        } else { // delete curr
            // case 1: curr is a leaf
                
            
            if (curr.isLeaf()) {
                if (curr == root) {
                    root = null;
                    size--;
                }
               
                curr = null;
               
              //  System.out.println(curr.data);
                this.size--;  // only decrement when you actually remove a node
            } else {  // has kids

                /* this method was not updating nodes properly, now fixed
                 * but is it still only O(log N), 
                 * or is it O(log^2 N) because we allow duplicates?
                 * no, because duplicates are inserted to the right
                 * but if this is rebalanced (ie, used as AVL) could be 
                 * O(log^2 N) if all values in the tree are the same
                 * would need to keep and use parent pointers to avoid that
                 */

                SNode change;
                if (curr.right != null) {  // has 1 or 2 children
                    change = findMin(curr.right);  // smallest in right subtree
                    curr.data = change.data;
                    curr.right = this.remove(change.data, curr.right);
                } else {  // only one left child, replace with it
                    curr = curr.left;
                    this.size--;  // because node is actually deleted here
                }
            }
        }
        return curr;
    }
    
    SNode header = null;
    
    public void splay(T val, SNode curr) {
        SNode leftTreeMax, rightTreeMin;

        header.left = header.right = null;
        leftTreeMax = rightTreeMin = header;

        nullNode.element = x;   // Guarantee a match

        for( ; ; )
            if( x.compareTo( t.element ) < 0 )
            {
                if( x.compareTo( t.left.element ) < 0 )
                    t = rotateWithLeftChild( t );
                if( t.left == nullNode )
                    break;
                // Link Right
                rightTreeMin.left = t;
                rightTreeMin = t;
                t = t.left;
            }
            else if( x.compareTo( t.element ) > 0 )
            {
                if( x.compareTo( t.right.element ) > 0 )
                    t = rotateWithRightChild( t );
                if( t.right == nullNode )
                    break;
                // Link Left
                leftTreeMax.right = t;
                leftTreeMax = t;
                t = t.right;
            }
            else
                break;

        leftTreeMax.right = t.left;
        rightTreeMin.left = t.right;
        t.left = header.right;
        t.right = header.left;
        return t;
    }

    }
/**
 * find the minimum value.
 * @param curr current noed.
 * @return the minmum node.
 */
    private SNode findMin(SNode curr) {
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
    private class SplayIterator implements Iterator<T> {
    
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
        public SplayIterator() {
            collection = inOrder(root);
            it = collection.iterator();
            
           
        }
        
       
        @Override
        public boolean hasNext() {
            if (it.hasNext())
                return true;
            return false;
       
        }

        @Override
        public T next() {
            if (it.hasNext() == false) {
                return null;
            }
            temp = it.next();
            return temp;
        }

        @Override
        public void remove() {
            it.remove();
            Splay.this.remove(temp);
        }
        
       
       
    }
    
    /** Inorder traversal.
     * @return return the tree in order.
     */
    public Iterable<T> inOrder() {
        return this.inOrder(this.root);
    }
   
    /**
     * Put every data inorder.
     * @param curr the current node.
     * @return return all the roots in order.
     */
    private Collection<T> inOrder(SNode curr) {
        
        LinkedList<T> iter = new LinkedList<T>();
        if (curr == null) {
            return iter;
        }
      
        iter.addAll(this.inOrder(curr.left));
        iter.addLast(curr.data);
      
        iter.addAll(this.inOrder(curr.right));
        return iter;
    
    }


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
