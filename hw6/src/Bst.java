import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;


public class Bst <T extends Comparable<? super T>> implements OrderedSet<T>{

    
    private class BNode {
        private T data;
        private BNode left;
        private BNode right;
        
        public BNode(T val) {
            data = val;
        }
        public boolean isLeaf() {
            return left == null && right == null;
        }
    }
    
    private BNode root;
    private int size;
    
    public Bst() {
        this.root = null;
        this.size = 0;
    }
    @Override
    public Iterator<T> iterator() {
        BstIterator myIter = new BstIterator();
        return myIter;
    }

    public int size() {
      
        return size;
    }

    @Override
    public boolean isEmpty() {
        
        return size() == 0;
    }
    
    public T root() {
        if (root == null) {
            return null;
        }
        else
            return root.data;
    }
    
    public boolean contains(T val) {
        return contains(val, root) != null;
    }
    
    public BNode contains(T val, BNode curr) {
        if (curr == null)
            return null;
        if (curr.data.compareTo(val) == 0)
            return curr;
        if (curr.data.compareTo(val) > 0)
            return contains(val, curr.left);
        return contains(val, curr.right);
    }
    @Override
    public boolean add(T val) {
        root = add(val,root);
        size++;
        return false;
    }
    private BNode add(T val, BNode curr) {
        if (curr == null)  // leaf, make new node
            return new BNode(val);
        if (curr.data.compareTo(val) == 0) {
            return null;
        }
        if (curr.data.compareTo(val) > 0) {
            curr.left = add(val, curr.left);
        }
       
        else {  // duplicates will go to right
            curr.right = add(val, curr.right); 
        }
        return curr;
    }
    @Override
    public boolean remove(T val) {
        root = remove(val,root);
        return false;
    }

    public BNode remove(T val, BNode curr) {
        if (curr == null) 
            return curr;
        int diff = curr.data.compareTo(val);
        if (diff > 0)
            curr.left = remove(val, curr.left);
        else if (diff < 0)
            curr.right = remove(val, curr.right);
        else { // delete curr
            // case 1: curr is a leaf
            if (curr.isLeaf()) {
                curr = null;
                size--;  // only decrement when you actually remove a node
            }
            else {  // has kids

                /* this method was not updating nodes properly, now fixed
                 * but is it still only O(log N), 
                 * or is it O(log^2 N) because we allow duplicates?
                 * no, because duplicates are inserted to the right
                 * but if this is rebalanced (ie, used as AVL) could be 
                 * O(log^2 N) if all values in the tree are the same
                 * would need to keep and use parent pointers to avoid that
                 */

                BNode change;
                if (curr.right != null) {  // has 1 or 2 children
                    change = findMin(curr.right);  // smallest in right subtree
                    curr.data = change.data;
                    curr.right = remove(change.data, curr.right);
                }
                else {  // only one left child, replace with it
                    curr = curr.left;
                    size--;  // because node is actually deleted here
                }
            }
        }
        return curr;
    }
    
private BNode findMin(BNode curr) {
    if (curr == null)
        return curr;
    while (curr.left != null)
        curr = curr.left;
    return curr;
}

/** Search from curr (as root of subtree) and find maximum value
    @return the max
*/
private BNode findMax(BNode curr) {
    if (curr == null)
        return curr;
    while (curr.right != null)
        curr = curr.right;
    return curr;
}

public String toString() {
    return inOrder().toString();
}


private class BstIterator implements Iterator<T> {
    
    Collection<T> collection = inOrder(root);
    Iterator<T> it = collection.iterator();
    public BstIterator() {
   
    }
    
    @Override
    public boolean hasNext() {
       if (it.hasNext() == true)
           return true;
       else 
           return false;
       
        
    }

    @Override
    public T next() {
   
    return it.next();
    }

    @Override
    public void remove() {
        it.remove();
        
    }
    
}
/** Inorder traversal
 */
public Iterable<T> inOrder() {
    return inOrder(root);
}

// this can no longer be static because BNode is not static
private Collection<T> inOrder(BNode curr) {
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
        Bst<T> temp = new Bst<T>();
        Bst<T> temp1 = (Bst<T>) that;
        Iterator<T> it = this.iterator();
        Iterator<T> it1 = that.iterator();
     
       while (it.hasNext() == true) {
          
           temp.add(it.next());
       }
       
       while (it1.hasNext() == true) {
       
       temp.add(it1.next());}
        
      
       
        return temp;
    }

    @Override
    public Set<T> intersect(Set<T> that) {
        Bst<T> temp = new Bst<T>();
        Bst<T> temp1 = (Bst<T>) that;
        Iterator<T> it = this.iterator();
        while (it.hasNext() == true) {
            T temp2 = it.next();
            if (that.contains(temp2))
            temp.add(temp2);
        }
        
        
        return temp;
    }

    @Override
    public OrderedSet<T> subset(T start, T end) {
       
        return null;
    }

}
