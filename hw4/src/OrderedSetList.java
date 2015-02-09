
import java.util.Iterator;
/**
 *
 *
 *
 * @param <T>
 */

public class OrderedSetList<T extends Comparable<? super T>> implements
    OrderedSet<T> {
/**
 *Each Node has T type data and link.
 *
 * @param <T>
 */

    private static class Node<T> {
/**
 * contains data of the node.
 */
        private T data;
/**
* link connects each node in the list.
*/
        private Node<T> link;
/**
 Insert the value in to the node.
 @param value is the data value
 */
        public Node(T value) {
            this.data = value;
            this.link = null;
        }
        /**
         * Set the data and link for the node null.
         */
        public Node() {
            this.data = null;
            this.link = null;
        }
    }
/**
     * Create a head node.
     */
    private Node<T> head;
     /**
     * Set size to 0.
     */
    private int size = 0;
    /**
     * Dummy value for the head node.
     */
    private T d;
    /**
     * Upper bound for this set.
     */
    private T upperbound;
    /**
     * Lower bound for this set.
     */
    private T lowerbound;
    /**
     * Constructor for ordered Set list.
     */
    public OrderedSetList() {

        this.head = new Node<T>(this.d);
    }


    @Override
    public Iterator<T> iterator() {
        return new SetIterator();
    }
    /**
     *SetIterator class.
     *
     */
    private class SetIterator implements Iterator<T> {
        /**
         * Current Node is the node we are on.
         */
        private Node<T> current;
        /**
         * We need to save the previous node so we can go back.
         */
        private Node<T> previous;

        /**
         * SetIterator constructor.
         */
        public SetIterator() {
            this.current = OrderedSetList.this.head;
            this.previous = null;
        }

        @Override
       /**
        * hasNext will check if it still has a next element.
        * Big O running time is O(1).
        */
        public boolean hasNext() {
            if (this.current.link == null) {
                return false;
            }
            return true;
        }

        @Override
        /**
         * Big O running time should be O(1).
         * Next method weill return the data of the next node.
         */
        public T next() {
            if (OrderedSetList.this.head.link == null) {
                return null;
            }
            T temp = null;
            this.previous = this.current;
            if (this.hasNext()) {
                temp = this.current.link.data;
            }
            this.current = this.current.link;
            return temp;
        }

        @Override
        /**
         * Remove the current node.
         * Big O running time is O(1).
        **/
        public void remove() {
            this.previous.link = this.current.link;
            OrderedSetList.this.size--;


        }
    }

    @Override
    /**
     * Return the size of the set.
     * Big O running time for this is O(1).
     */
    public int size() {
        int s = 0;
        Node<T> temp = this.head.link;

        while (temp != null) {
            if (temp.data.compareTo(this.lowerbound) <= 1
                    && temp.data.compareTo(this.upperbound) <= 1) {
                s++;
            }
            temp = temp.link;
        }
        this.size = s;

        return this.size;
    }


    @Override
    /**Big O running time for this is O(1).
     * Check if the set is empty, if it is return true.
     * Othersise, return false.
     **/
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }
    @Override
    /**
     * Big O running time for add method is O(n).
     * Because we need to go through the entire linkedlist.
     */
    public boolean add(T o) {
        Node<T> curr = this.head;
        Node<T> temporary = null;
        Node<T> temp = new Node<T>(o);
        if (this.head.link == null) {
            this.head.link = temp;
            temp.link = null;
            this.size++;
            this.upperbound = temp.data;
            this.lowerbound = temp.data;
            return true;
        }

        while (curr.link != null) {
            if (curr.link.data.compareTo(o) == 0) {
                return false;
            } else if (curr.link.data.compareTo(o) < 0) {
                if (curr.link.link == null) {
                    curr.link.link = temp;
                    temp.link = null;
                    this.size++;
                    this.lowerbound = this.head.link.data;
                    this.upperbound = temp.data;
                    return true;
                }  else {
                    curr = curr.link;
                }
            }  else if (curr.link.data.compareTo(o) > 0) {
                temporary = curr.link;
                curr.link = temp;
                temp.link = temporary;
                this.size++;
                this.lowerbound = this.head.link.data;
                return true;
            }
        }
        return false;
    }

    @Override
    /**
     * Big O running time for remove is O(N).
     * Becuase we need to first find the element and then delete it.
     */
    public boolean remove(T o) {
        Node<T> curr = this.head;
        if (curr.link == null) {
            return false;
        }
        while (curr.link != null) {
            if (curr.link.data.compareTo(o) == 0) {
                if (curr.link.link == null) {
                    this.upperbound = curr.link.data;
                    curr.link = null;
                    this.size--;
                    this.lowerbound = this.head.link.data;
                    return true;
                } else {
                    if (this.head.link.data.compareTo(o) == 0
                            && this.head.link.link != null) {
                        this.lowerbound = this.head.link.link.data;
                    }
                    curr.link = curr.link.link;
                    this.size--;
                    return true;
                }
            } else if (curr.link.data.compareTo(o) > 0) {
                return false;
            } else if (curr.link.data.compareTo(o) < 0) {
                curr = curr.link;
            }
        }
        return false;
    }

    @Override
    /**
     * Big O running time for contains method is O(n).
     * We need to go through every element in the list.
     */
    public boolean contains(T o) {
        Iterator<T> iter = this.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    /**
     * Big O running time for union method is O(n).
     * We first need to add all the elements in this set to temp.
     * We then add all elements in that set to temp.
     * The running time for this 2n.
     */
    public Set union(Set that) {
        OrderedSetList<T> temp = new OrderedSetList<T>();
        OrderedSetList<T> arraythat = new OrderedSetList<T>();
        arraythat =  (OrderedSetList<T>) that;
        Node<T> t = this.head.link;
        Node<T> t1 = arraythat.head.link;
        if (t == null) {
            return that;
        }
        if (t1 == null) {
            return this;
        }
        while (t != null) {
            temp.add(t.data);
            t = t.link;
        }
        while (t1 != null) {
            temp.add(t1.data);
            t1 = t1.link;
        }
        this.lowerbound = temp.head.link.data;
        Node<T> n = temp.head.link;
        while (n != null) {
            if (n.link == null) {
                this.upperbound = n.data;
            }
            n = n.link;
        }
        return temp;
    }

    @Override
    /**
     * Big O running time is O(N).
     * We add elements that both that and this set has to temp.
     * Then we need to set the upper bound and lower bound.
     */
    public Set intersect(Set that) {
        OrderedSetList<T> temp = new OrderedSetList<T>();
        OrderedSetList<T> arraythat = new OrderedSetList<T>();
        Node<T> t = this.head.link;
        while (t != null) {
            if (that.contains(t.data)) {
                temp.add(t.data);
            }
            t = t.link;
        }
        if (temp.head.link == null) {
            return temp;
        }

        this.lowerbound = temp.head.link.data;
        Node<T> n = temp.head;
        while (n.link != null) {
            n = n.link;
            if (n.link == null) {
                this.upperbound = n.data;
            }
        }

        return temp;
    }
    @Override
    /**
     * This subset method will reutn a subset that superset contains with
     * in the bound.
     * @param start
     * @param end
     * @return
     */
    public OrderedSet subset(Comparable start, Comparable end) {
        T u = null;
        T l = null;
        l = this.lowerbound;
        u = this.upperbound;
        OrderedSetList<T> temp = new OrderedSetList<T>();
        Node<T> t = this.head.link;
        Node<T> tempHead = new Node();
        temp.head = this.head;
        while (t != null) {
            if (((t.data.compareTo((T) start) == 0))
                    && (t.data.compareTo((T) end) < 0)) {
                temp.size++;
                if (t.data.compareTo(l) > 0) {
                    l = t.data;

                }
                if (t.data.compareTo(u) < 0) {
                    u = t.data;

                }
            } else if ((t.data.compareTo((T) start) > 0)
                    && (t.data.compareTo((T) end) < 0)) {
                temp.size++;
                if (t.data.compareTo(l) > 0) {
                    l = t.data;

                }
                if (t.data.compareTo(u) < 0) {
                    u = t.data;
                }
            }
            t = t.link;
        }
        temp.upperbound = l;
        temp.lowerbound = u;
        return temp;
    }

    @Override
    /**
     * This method will return a string that
     * is readable to users.
     */
    public String toString() {
        String s = "{";
        Node<T> n = this.head.link;
        while (n != null) {
            if (n.data.compareTo(this.lowerbound) >= 0
                    && n.data.compareTo(this.upperbound) <= 0) {
                s = s + n.data;
                if (n.link != null
                        && n.link.data.compareTo(this.upperbound) <= 0) {
                    s = s + ", ";
                }
            }
            n = n.link;
        }
        s = s + "}";
        return s;
    }

    @Override
    public void print() {

    }


}
