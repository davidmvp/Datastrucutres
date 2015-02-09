import java.util.Collection;

import java.util.Iterator;
import java.util.LinkedList;




/**
 * Splay tree implementation.
 * @author davidmvp23
 *
 * @param <T> any type.
 */
public class SplayTree  
    <T extends Comparable<? super T>> implements OrderedSet<T> {
    /**
     * Splay Node class.
     * @author davidmvp23
     *
     */
    private class SNode {
        /**
         * Data node contains.
         */
        T data;
        /**
         * left child.
         */
        SNode left;  
        /**
         * right child.
         */
        SNode right;       
        /**
         * Insert the data into the Node.
         * @param theKey data we want to insert.
         */
        SNode(T theKey) {
            this.data = theKey;
            this.left = null;
            this.right = null;
        }

       
    }
    /**
     * The root.
     */
    private SNode root;
    /**
     * The size.
     */
    private int size;
    /**
     * Create a header for splay.
     */
    private  SNode header = new SNode(null); 
    /**
     * Constructor of the splaytree.
     */
    public SplayTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Add methood.
     * @param key The data we want to insert.
     * @return Return true if it is successful.
     */
    public boolean add(T key) {
        SNode n;
        int c;
        if (this.root == null) {
            this.root = new SNode(key);
            this.size++;
            return true;
        }
        splay(key);
        c = key.compareTo(this.root.data);
        if (c == 0) {
           
            return false;
        }
        n = new SNode(key);
        if (c < 0) {
            n.left = this.root.left;
            n.right = this.root;
            this.root.left = null;
            this.root = n;
            this.size++;
            return true;
        } else {
            n.right = this.root.right;
            n.left = this.root;
            this.root.right = null;
            this.root = n;
            this.size++;
            return true;
        }
    
    }

    /**
     * Remove from the tree.
     * @param key the data we want to remove..
     * @return true if it is successful.
     */
    public boolean remove(T key) {
        if (contains(key)) {
            
        
            SNode x;
            this.splay(key);
        
        // Now delete the root
            if (this.root.left == null) {
                this.root = this.root.right;
                this.size--;
                return true;
            } else {
                x = this.root.right;
                this.root = this.root.left;
                splay(key);
                this.root.right = x;
                this.size--;
                return true;
            }
        } else {
            return false;
        }
        
    }

    /**
     * Find the smallest item in the tree.
     * @return return the smallest data.
     */
    public T findMin() {
        SNode x = this.root;
        if (this.root == null) {
            return null;
        }
        while (x.left != null) {
            x = x.left;
        }
        splay(x.data);
        return x.data;
    }

    /**
     * Find the largest item in the tree.
     * @return return the largest data.
     */
    public T findMax() {
        SNode x = this.root;
        if (this.root == null) {
            return null;
        }
        while (x.right != null) {
            x = x.right;
        }
        splay(x.data);
        return x.data;
    }

    /**
     * Find an item in the tree.
     * @param key the data we want to find in the tree.
     * @return return its data.
     */
    public T find(T key) {
        if (this.root == null) {
            return null;
        }
        splay(key);
        if (this.root.data.compareTo(key) != 0) {
            return null;
        }
        return this.root.data;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }


    
    
    
    /**
     * Do the splay method.
     * @param key the data we want to splay.
     */
    private void splay(T key) {
        SNode l, r, t, y;
        l = this.header;
        r = this.header;
        t = this.root;
        this.header.left = null;
        this.header.right = null;
        for (;;) {
            if (key.compareTo(t.data) < 0) {
                if (t.left == null) {
                    break;
                }
                if (key.compareTo(t.left.data) < 0) {
                    y = t.left;                            
                    t.left = y.right;
                    y.right = t;
                    t = y;
                    if (t.left == null) {
                        break;
                    }
                }
                r.left = t;                               
                r = t;
                t = t.left;
            } else if (key.compareTo(t.data) > 0) {
                if (t.right == null) {
                    break;
                }
                if (key.compareTo(t.right.data) > 0) {
                    y = t.right;                          
                    t.right = y.left;
                    y.left = t;
                    t = y;
                    if (t.right == null) {
                        break;
                    }
                }
                l.right = t;                             
                l = t;
                t = t.right;
            } else {
                break;
            }
        }
        l.right = t.left;                               
        r.left = t.right;
        t.left = this.header.right;
        t.right = this.header.left;
        this.root = t;
        this.header = new SNode(null);
    }
    /**
     * Do an inorder traverse.
     * @return the collection.
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
    /**
     * Print out the tree in order.
     * @return return the string.
     */
    public String toString() {
        return this.inOrder().toString();
    }
    @Override
    public Iterator<T> iterator() {
        
        SplayIterator myIter = new SplayIterator();
        return myIter;
    }
    /**
     * SplayIterator class.
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
        /**
         * Constructor.
         */
        public SplayIterator() {
            
            this.collection = SplayTree.this.inOrder(SplayTree.this.root);
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
            SplayTree.this.remove(this.temp);
        }
        
       
       
    }
    @Override
    public int size() {
        return this.size;
        
    }

   


    @Override
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
    public Set<T> union(final Set<T> that) {
        SplayTree<T> temp = new SplayTree<T>();
        SplayTree<T> tempThat =  (SplayTree<T>) that;
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
        SplayTree<T> temp = new SplayTree<T>();
        SplayTree<T> tempThat = (SplayTree<T>) that;
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