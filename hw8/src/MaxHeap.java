import java.util.ArrayList;

/** Generic Max Binary Heap implementation.
 * (based off of Weiss' source code)
 * @param <T> The base type
 */
public class MaxHeap<T extends Comparable<? super T>> {
    
    /** The initial default size of a heap. */
    private static final int DEFAULT_SIZE = 100;
    /** The array to hold the heap. */
    private T[] heapArray;
    /** The number of elements in the heap. */
    private int size;

    /** Default constructor. */
    public MaxHeap() {
        this(DEFAULT_SIZE);
    }
    
    /** Constructor with initial max size. 
     * @param initMax the initial max size
     */
    public MaxHeap(int initMax) {
        this.size = 0;
        this.heapArray = (T[]) new Comparable[initMax + 1];
    }
    
    /** Constructor that builds a heap from an array of items.
     * @param items the array of items
     */
    public MaxHeap(T[] items) {
        this.size = 0;
        this.heapArray = (T[]) new Comparable[(items.length + 2)];
        int i = 1;
        for (T item : items) {
            this.heapArray[i++] = item;
            if (item != null) {
                this.size++;
            }
        }
        this.buildHeap();
        
    }
    
    /** Inserts an item into the heap (maintains heap property).
     * @param val the item to insert
     */
    public void insert(T val) {
        if (this.size == this.heapArray.length - 1) {
            this.doubleArray();
        }        
        this.size++;
        int curr = this.size;
        for (this.heapArray[curr] = val; curr > 1 && val.compareTo(
                this.heapArray[curr / 2]) > 0; curr /= 2) {
            this.heapArray[curr] = this.heapArray[curr / 2];
        }
        this.heapArray[curr] = val;        
    }
    
    /** Doubles the max size of the array. */
    private void doubleArray() {
        T[] temp = this.heapArray;
        int newSize = this.heapArray.length * 2;
        this.heapArray = (T[]) new Comparable[newSize + 1];
        for (int i = 0; i < temp.length; i++) {
            this.heapArray[i] = temp[i];
        }        
    }
    
    /** Returns the largest item in the heap.
     * @return the largest item
     */
    public T getMax() {
        if (this.isEmpty()) {
            return null;
        }
        return this.heapArray[1];
    }
    
    /** Deletes and return the largest item in the heap.
     * @return the removed largest item
     */
    public T deleteMax() {
        if (this.isEmpty()) {
            return null;
        }
        T maxItem = this.getMax();
        this.heapArray[1] = this.heapArray[this.size--];
        this.percolateDown(1);
        return maxItem;
    }
    
    /** Builds a max heap by percolating down
     * i.e. ensuring heap property in all subtrees.
     */
    private void buildHeap() {
        for (int i = this.size / 2; i > 0; i--) {
            this.percolateDown(i);
        }
    }
    
    /** Whether or not the heap is logically empty.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    /** Returns the number of items in the heap.
     * @return the number of items
     */
    public int getSize() {
        return this.size;
    }
    
    /** Percolates down to maintain heap property in whole tree.
     * @param curr The root of the subtree to percolate down from 
     */
    private void percolateDown(int curr) {
        int child;
        T temp = this.heapArray[curr];
        int count;
        for (count = 0; curr * 2 <= this.size; curr = child) {
            child = curr * 2;
            if (child != this.size
                    && this.heapArray[child + 1].compareTo(
                            this.heapArray[child]) > 0) {
                child++;
            }
            if (this.heapArray[child].compareTo(temp) > 0) {
                this.heapArray[curr] = this.heapArray[child];
                count++;
            }
        }
        if (count > 0) {
            this.heapArray[curr] = temp;
        }
    }
    
    /** Returns a string of all the items in the array.
     *  @return The string
     */
    public String toString() {
        String s;
        s = "[";
        for (int i = 1; i <= this.size; i++) {
            s = s + this.heapArray[i];
            if (i != this.size) {
                s += ", ";
            }
        }
        return s + "]";
    }
    
    /** Returns an array of the heap.
     * @return The array
     */
    public T[] toArray() {
        ArrayList<T> temp = new ArrayList<T>();
        return temp.toArray(this.heapArray);
    }
}