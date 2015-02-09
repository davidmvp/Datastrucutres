

import java.util.Collection;

import java.util.Iterator;
import java.util.LinkedList;









/**
 * This is the avl tree.
 * @author davidmvp23
 *
 * @param <T> any type
 */
public class Avl<T extends Comparable<? super T>>
    implements OrderedSet<T> {
   /**
    * Node for AVL tree.
    * @author davidmvp23
    *
    */
    private class ANode {
        /**
         * it holds data.
         */
        private T data;
        /**
         * left child.
         */
        private ANode left;
        /**
         * right child.
         */
        private ANode right;
        /**
         * height of the node.
         */
        private int height = 0;
        /**
         * 
         * @param val The data.
         */
        public ANode(T val) {
            this.data = val;
        }
        
        
    }
    /**
     * Root of the avl tree.
     */
    private ANode root;
    /**
     * size of the tree.
     */
    private int size;
    
    /**
     * Constructor for bst.
     * Its running time is O(1).
     */
    public Avl() {
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
        
        AvlIterator myIter = new AvlIterator();
        return myIter;
       
    }
    /**
     * Size of the tree.
     * The runtime for size is O(1).
     * @return the size.
     */
    @Override
    public int size() {
        
        return this.size;
    }
    /**
     * Find if the tree is empty.
     * The runtime for isEmpty O(1).
     * @return return true if it is empty.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    /** find the root data.
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
     * add method.
     * The big O running time for add is O(log n), because the tree is balanced.
     * @param val we want to insert.
     * @return return true if it is inserted.
     */
    @Override
    public boolean add(T val) {
        if (this.contains(val)) {
            return false;
        }
     
        
        this.root = this.add(val, this.root);
       
        this.size++;
        return true;
    }
    /**
     * Check the height the tree.
     * The big O running time for height is O(1).
     * @param a The node.
     * @return return the height of the tree.
     */
    public int height(ANode a) {
        if (a == null) {
            return 0;
        }
        return a.height;
    }
    /**
     * Find the max height out of the two.
     * The big O running time for mas is O(1).
     * @param h first height.
     * @param h1 second height.
     * @return return the max height. 
     */
    private int max(int h, int h1) {
        if (h > h1) {
            return h;
        }
        return h1;
    }
    /**
     * Do the rightRotate.
     * The big O running time for this is O(1).
     * @param curr the node we want to rotate.
     * @return return the new tree.
     */
    private ANode rightRotate(ANode curr) {
       
        ANode temp = curr.left;
        curr.left = temp.right;
        temp.right = curr;
        curr.height = this.max(this.height(curr.left), 
                this.height(curr.right)) + 1;
        temp.height = this.max(this.height(temp.left),
                this.height(temp.right)) + 1;
        return temp;
    }
    /**
     * Do the leftRotate.
     * The big O running time for this is O(1).
     * @param curr the current root.
     * @return the current node.
     */
    private ANode leftRotate(ANode curr) {
        
        ANode temp = curr.right;
        curr.right = temp.left;
        temp.left = curr;
        curr.height = this.max(this.height(curr.left),
                this.height(curr.right)) + 1;
        temp.height = this.max(this.height(temp.left),
                this.height(temp.right)) + 1;
        return temp;
    }
    /**
     * Do the leftRightRotate.
     * The big O running time for this is O(1).
     * @param curr the current node.
     * @return return the current node.
     */
    private ANode leftRightRotate(ANode curr) {
       
        curr.left = this.leftRotate(curr.left);
        return this.rightRotate(curr);
    }
    /**
     * Do the rightleft Rotate.
     * The big O running time for this is O(1).
     * @param curr the current node.
     * @return return the current node.
     */
    private ANode rightLeftRotate(ANode curr) {
       
        curr.right = this.rightRotate(curr.right);
        return this.leftRotate(curr);
    }
    /**
     * Add helper function.
     * The big O running time for this is O(log n).
     * @param val the value we want to add.
     * @param curr the current node.
     * @return return the current node.
     */
    private ANode add(T val, ANode curr) {
        
        if (curr == null) { // leaf, make new node
           
            curr = newNode(val);
           
        } else if (curr.data.compareTo(val) > 0) {
            
            curr.left = this.add(val, curr.left);
          
            if (this.height(curr.left) - this.height(curr.right) == 2) {
                if (curr.left.data.compareTo(val) > 0) {
                    curr = this.rightRotate(curr);
                } else {
                    curr = this.leftRightRotate(curr);
                }
            
            }
        } else {  // duplicates will go to right
          
            curr.right = this.add(val, curr.right); 
         
           
            if (this.height(curr.right) - this.height(curr.left) == 2) {
                if (curr.right.data.compareTo(val) < 0) {
                    curr = this.leftRotate(curr);
                } else {
                    curr = this.rightLeftRotate(curr);
                }
            
            }
        }
     
        curr.height = this.max(this.height(curr.left),
                this.height(curr.right)) + 1;
    
        return curr;
    }
    /**
     * Createa a new node.
     * The big O running time for this is O(1).
     * @param val the value we want to insert.
     * @return return the new root.
     */
    private ANode newNode(T val) {
        ANode a = new ANode(val);
        a.left = null;
        a.right = null;
        a.height = 0;
       
        return a;
    }
    /**
     * Remove method.
     * The big o running time for remove is O(log n), 
     * because the tree is balanced.
     * @param val the data we want to  remove.
     * @return true if it has been removed.
     */
    @Override
    public boolean remove(T val) {
        if (this.contains(val)) {
            this.root = this.remove(val, this.root);
            this.size--;
            return true;
        }
       
           
        return false;
    }
    /**
     * Remove helper method.
     * The big o running time for remove is O(log n).
     * @param val the value we want to delete.
     * @param curr the current node we are on.
     * @return return the new root.
     */
    public ANode remove(T val, ANode curr) {
        if (curr == null) {
            return curr;
        }
        int diff = curr.data.compareTo(val);
        if (diff == 0) {
            if (curr.right == null) {
                curr = curr.left;
            } else {
                ANode n = curr.right;
                while (n.left != null) {
                    n = n.left;
                }
                curr.data = n.data;
                curr.right = this.remove(curr.data, curr.right);
                curr.height = this.max(this.height(curr.left),
                        this.height(curr.right)) + 1;
            }
            return curr;
        } else if (diff > 0) {
            curr.left = this.remove(val, curr.left);
        } else {
            curr.right = this.remove(val, curr.right);
            
        } 
        curr.height = this.max(this.height(curr.left), this.height(curr.right));
        
        if (curr.left != null) {
            curr.left = rotate(curr.left);
        }
        if (curr.right != null) {
            curr.right = rotate(curr.right);
        }
        
        return rotate(curr);
    }
    /**
     * Rotate method.
     * The big o running time for remove is O(1). 
     * @param curr the current node.
     * @return return the new tree.
     */
    private ANode rotate(ANode curr) {
        if (this.height(curr.left) - this.height(curr.right) == 2) {
            if (this.height(curr.left.left) >= this.height(curr.left.right)) {
                curr = this.rightRotate(curr);
            } else {
                curr = this.leftRightRotate(curr);
            }
        }
        if (this.height(curr.right) - this.height(curr.left) == 2) {
            if (this.height(curr.right.right) >= this.height(curr.right.left)) {
                curr = this.leftRotate(curr);
            } else {
                curr = this.rightLeftRotate(curr);
            }
        }
        return curr;
    }
    /**
     * The AvlIterator class.
     * @author davidmvp23
     *
     */
    private class AvlIterator implements Iterator<T> {
        /**
         * Collection of all the roots in the tree.
         */
        Collection<T> collection;
     
        /**
         * Iterator that iterates through the collection.
         */
        
        Iterator<T> it;
       /**
         * temporary data.
         */
        private T temp;
        /**
         * Constructor for the iterator.
        /* The big O running time for the constructor is O(n),
         * because the inOrder method costs O(n).
         */
        public AvlIterator() {
            this.collection = inOrder(Avl.this.root);
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
            Avl.this.remove(this.temp);
        }
        
        
    }
    
    /**
    * Find if the tree contains the data.
    * @param val the data.
    * The run time time for contains is O(log n), 
    * because the tree is not balanced.
    * @return return true if it exists
    */
    @Override
    public boolean contains(T val) {
        return this.contains(val, this.root) != null;
        
    }
    /**
     * recursively go through the tree.
     * The running time is O(log n).
     * @param val value of the data.
     * @param curr current root.
     * @return the root.
     */
    public ANode contains(T val, ANode curr) {
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
    /** Inorder traversal.
     * The big O running time for inOrder is O(n).
     * because we need to traverse every element in the tree.
     * @return return the tree in order.
     */
    public Iterable<T> inOrder() {
        return this.inOrder(this.root);
    }
    
    /**
     * Check if the tree is balanced.
     * The big O running time for inOrder is O(n).
     */
    public void isBalanced() {
        
        this.isBalanced(this.root);
      
        return;
    }
    /**
     * Check each root and see their balance factor.
     * A tree is balanced if its balance factor is -1,0 or 1.
     * @param curr return the current root.
     */
    public void isBalanced(ANode curr) {
        if (curr == null) {
            return;
        }
        this.isBalanced(curr.left);
        if (curr.left == null && curr.right == null) {
            
            System.out.println("The balance factor is 0");
        } else if (curr.left == null) {
           
            System.out.println("The balance factor is "  
                    + (-curr.right.height));
            
        } else if (curr.right == null) {
            
            System.out.println("The balance factor is " + (curr.left.height));
           
            
        } else if (curr.left != null && curr.right != null) {
            int bf = curr.left.height - curr.right.height;
            System.out.println("The balance factor is " + bf);
           
        }
        this.isBalanced(curr.right);
        
    }
    /**
     * Put every root in the collection and in order.
     * The big O running time for O(n).
     * @param curr the current node.
     * @return return all the roots in order.
     */
    private Collection<T> inOrder(ANode curr) {
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
     * This method is to print out the tree in order.
     * The big O running time for toString is O(n).
     * @return return the string.
     */
    public String toString() {
        return this.inOrder().toString();
        
    }
    /**
    * The big O running time for union is O(n).
    * @param that the other set we use to create the union.
    * @return return the union set.
    */
    @Override
    public Set<T> union(Set<T> that) {
        Avl<T> temp = new Avl<T>();
        Avl<T> tempThat =  (Avl<T>) that;
        Iterator<T> itr = this.iterator();
        Iterator<T> itr2 = tempThat.iterator();
        int diff;

       

        T info, info2;
        System.out.println(itr.hasNext());
        info = itr.next();
        System.out.println(itr2.hasNext());
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
        Avl<T> temp = new Avl<T>();
        Avl<T> tempThat = (Avl<T>) that;
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
        // TODO Auto-generated method stub
        return null;
    }

}
