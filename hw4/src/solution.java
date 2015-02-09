import java.util.Iterator;
import java.util.ConcurrentModificationException;

/** OrderedSetList is a linkedlist implementation of OrderedSet.
 *  In this solution, subset provides a view (not a new set), but
 *  it does not accommodate changes to the endpoints, only in the middle.
 *  It uses a clever trick by keeping a pointer to the most recently added
 *  node so that adding elements already in order to the list can be done
 *  in O(N) time total instead of O(N^2).  This is used by union and intersect.
 *
 * @author Violet
 *
 * @param <T>
 */
public class OrderedSetList<T extends Comparable<? super T>> implements
        OrderedSet<T> {

    // curr node plays a trick to make add() more efficient.
    // especially when you add values to the set in ascending order.
    /**member variable nodes.
     */
    private Node<T> head, curr, tail;
    /* to ensure the iterator is up-to-date(no modification
    during using iterator*/
    /**member variable state.
     */
    private int state;

    /**constructor.
     * @param phead
     *            initial head node
     * @param ptail
     *            initial tail node
     * @param pstate
     *            initial state
     */
    //O(1)
    public OrderedSetList(final Node<T> phead, final Node<T> ptail,
            final int pstate) {
        this.head = phead;
        this.tail = ptail;
        this.curr = null;
        this.state = pstate;
    }

    /**default constructor, set all nodes to null.
     */
    //O(1)
    public OrderedSetList() {
        this.head = null;
        this.tail = null;
        this.curr = null;
        this.state = 0;
    }

    /**Make an iterator for the list.
     * @return a new LinkedSetIterator()
     */
    //O(1)
    public final Iterator<T> iterator() {
        OrderedSetIterator myIter = new OrderedSetIterator();
        return myIter;
    }

    /**Get the size of the Set.
     * @return the size
     */
    //O(N)
    public final int size() {
        int count = 0;
        Node<T> temp = this.head;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    /**See if the set is empty.
     * @return true if it's empty, false otherwise
     */
    //O(1)
    public final boolean isEmpty() {
        return this.head == null;
    }

    /**Adds a new item to the list in order, without duplicating items.
     *
     * @param o
     *            is the object to be added
     * @return true if the object could be added, false otherwise
     */
    //O(N)
    public final boolean add(final T o) {
        Node<T> prev = null;

        // curr == null when it's initial or it's reach the tail of the list.
        // if the object < current element or reaching the tail of the list,
        // start from the head
        // or start from the current node.
        if (this.curr == null || this.curr.data.compareTo(o) > 0) {
            this.curr = this.head;
        }
        // find where in list new item belongs
        while (this.curr != null && this.curr.data.compareTo(o) < 0) {
            prev = this.curr;
            this.curr = this.curr.next;
        }

        // if the value not already in the set, add between prev and curr
        if (this.curr == null || this.curr.data.compareTo(o) > 0) {
            Node<T> n = new Node<T>(o, this.curr);
            // add the object to the end of the linked-list
            if (this.curr == null) {
                this.tail = n;
            }
            this.curr = n;
            if (prev == null) {
                this.head = n;
            } else {
                prev.next = n;
            }
            this.state++;
            return true;
        }

        return false; // must have been duplicate
    }

    /**Remove an item from the set.
     * @param o
     *            the item to remove
     * @return true if removed, false if not found
     */
    //O(N)
    public final boolean remove(final T o) {
        Node<T> temp = this.head, prev = null;
        while (temp != null && !temp.data.equals(o)) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) { // not in list
            return false;
        }
        if (temp == this.tail) { // if the last element is deleted
            this.tail = prev;
        }
        // remove curr
        if (prev == null) { // curr is at head
            this.head = temp.next;
        } else {
            prev.next = temp.next;
        }
        this.state++;
        return true;
    }

    /**Search for item in the set.
     * @param o
     *            the item to search for
     * @return true if found, false otherwise
     */
    //O(N)
    public final boolean contains(final T o) {
        // uses iterator
        for (T item : this) {
            if (item.equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**Create the union of two sets, no duplicates.
     *
     * @param that
     *            the other set to union with this
     * @return a new set which contains all the elements in this and that
     */
    //O(N+M)
    public final Set<T> union(final Set<T> that) {
        OrderedSetList<T> temp = new OrderedSetList<T>();
        OrderedSetList<T> tempThat = (OrderedSetList<T>) that;
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
     * Create the intersection of two sets, which is everthing that appears in
     * both sets.
     *
     * @param that
     *            the other set to union with this
     * @return a new set which is this intersect that
     */
    //O(N+M)
    public final Set<T> intersect(final Set<T> that) {
        OrderedSetList<T> temp = new OrderedSetList<T>();
        OrderedSetList<T> tempThat = (OrderedSetList<T>) that;
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

    /**
     * Return a subset of this set with elements in the range [start, end). This
     * does not return a copy of the set, but rather a "view" into it
     *
     * @param start
     *            the inclusive low value of the range
     * @param end
     *            the exclusive high value of the range
     * @return the subset itself
     */
    //O(N)
    public final OrderedSet<T> subset(final T start, final T end) {
        Node<T> subHead = null, subTail = null, temp = null, prev = null;
        OrderedSetList<T> sublist = new OrderedSetList<T>();
        temp = this.head;
        while (temp != null) {
            if (temp.data.compareTo(start) >= 0) {
                subHead = temp;
                break;
            }
            prev = temp;
            temp = temp.next;
        }
        while (temp != null) {
            if (temp.data.compareTo(end) >= 0) {
                subTail = prev;
                break;
            }
            prev = temp;
            temp = temp.next;
        }

        if (subHead != null && subTail != null) {
            sublist = new OrderedSetList<T>(subHead, subTail, this.state);
        }
        return sublist;

    }

    /** convert an orderedset to a string.
     * @return a string representation of the set elements, ordered by value
     */
    //O(N)
    public final String toString() {
        String s = "{";
        for (T item : this) {
            // uses iterator
            s += item + " ";
        }
        s += "}";
        return s;
    }


    /**nested Node class.
     */
    private static class Node<T> {
        // instance variables
        /**member variable data.
         */
        private T data;

        /**member variable pointer.
         */
        private Node<T> next;

        /**constructor.
         * @param d  data
         * @param n  pointer
         */
        //O(1)
        public Node(final T d, final Node<T> n) {
            this.data = d;
            this.next = n;
        }
    } // end Node class


    /** Inner LinkedSetIterator class.
     */
    private class OrderedSetIterator implements Iterator<T> { // instance
                                                                // variables
        /**member variable nodes.
         */
        private Node<T> icurr, nextnode, prev;

        /**member variable flag, record whether iterator can do remove.
         */
        private boolean oktoRemove;

        /**member variable state, to control version of origin set and iterator.
         */
        private int mystate;

        /**constructor.
         */
        //O(1)
        public OrderedSetIterator() {
            this.nextnode = OrderedSetList.this.head;
            this.icurr = null;
            this.prev = null;
            this.oktoRemove = false;
            this.mystate = OrderedSetList.this.state;
        }

        /**Determines if the list has a next item.
         *
         * @return true if there are items, false otherwise
         */
        //O(1)
        public boolean hasNext() {
            if (this.mystate != OrderedSetList.this.state) {
                System.out.println("This iterator is invalid.");
                throw new ConcurrentModificationException();
            }
            return (this.nextnode != null
                    && this.icurr != OrderedSetList.this.tail);
        }

        /**Gets the next item in the list.
         *
         * @return the next item
         */
        //O(1)
        public T next() {
            if (this.mystate != OrderedSetList.this.state) {
                System.out.println("This iterator is invalid.");
                throw new ConcurrentModificationException();
            }
            if (this.hasNext()) {
                this.prev = this.icurr;
                this.icurr = this.nextnode;
                this.nextnode = this.nextnode.next;
                this.oktoRemove = true;
                return this.icurr.data;
            }
            return null;
        }

        /**Removes the last item in the list that was returned.
         */
        //O(1)
        public void remove() {
            if (this.mystate != OrderedSetList.this.state
                    || OrderedSetList.this.head == null || !this.oktoRemove) {
                System.out.println("Error: Cannot remove now");
                throw new ConcurrentModificationException();
            }
            if (this.prev == null) {
                OrderedSetList.this.head = OrderedSetList.this.head.next;
            } else {
                // remove curr
                if (this.icurr == OrderedSetList.this.tail) {
                    OrderedSetList.this.tail = this.prev;
                }
                this.prev.next = this.nextnode;
            }
            this.oktoRemove = false;
        }
    } // LinkedSetIterator

}
public class solution {

}
