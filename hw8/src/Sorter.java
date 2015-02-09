import java.util.LinkedList;
/**
 * The sorter class.
 * @author davidmvp23
 *
 */
public class Sorter {
    /**
     * Buckets.
     */
    LinkedList<Block>[] list;
    /**
     * Constructor.
     */
    public Sorter() {
        this.list = new LinkedList[10];
        for (int i = 0; i < 10; i++) {
            this.list[i] = new LinkedList<Block>();
        }
    }
    /**
     * toSort Blcok.
     */
    private Block[] toSort;
    /**
     * The range of buckets.
     */
    private int rangeOfBuckets = 0;
    
    /**
     * Bubble sort used for sort each bucket.
     * @param arr the array.
     * @return the sorted array.
     */
    public static Block[] bubbleSort(LinkedList<Block> arr) {
        boolean swapped = true;
        int j = 0;
        int tmp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < arr.size() - j; i++) {
                if (arr.get(i).getStrAddr() > arr.get(i + 1).getStrAddr()) {
                    tmp = arr.get(i + 1).getStrAddr();
                    arr.get(i + 1).setStrAddr(arr.get(i + 1).getStrAddr());
                    arr.get(i + 1).setStrAddr(tmp);
                    swapped = true;
                }
            }                
        }
        Block[] newArray = new Block[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            newArray[i] = arr.get(i);
        }
        return newArray;
    }
    /**
     * bucket sort.
     * @param arr get the array.
     * @return return sorted array.
     */
    public  Block[] bucketSort(Block[] arr) {
        int largestAddress = 0;
        System.out.println("llook at me ");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].toString());
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getStrAddr() > largestAddress) {
                largestAddress = arr[i].getStrAddr();
            }
        }
        
        this.rangeOfBuckets = largestAddress / 10 + 1;
        for (int i = 0; i < arr.length; i++) {
            insert(arr[i], arr[i].getStrAddr());
        }
        
         
        Block[] b = new Block[arr.length];
        int counter = 0;
        for (int i = 0; i < this.list.length; i++) {
            for (int j = 0; j < this.list[i].size(); j++) {
                   
                b[counter] = bubbleSort(this.list[i])[j];
                counter++;
            }
            
        }
        return b;
         
    }
    /**
     * insert for bucksort.
     * @param x the block
     * @param size the size
     */
    public void insert(Block x, int size) {
    
        this.list[hash(size)].add(x);
      
    }
    
    /**
     * The hash function.
     * @param x the size
     * @return the hashValue.
     */
    public int hash(int x) {
        int hashValue = (x - 1) / this.rangeOfBuckets;
        return hashValue;
    }

   
    /**
     * QuickSort.
     * @param array the array we want to sort.
     */
    public void QuickSort(Block[] array) {
        this.toSort = array;
        quickSort();
    }

    /**
     * Sorts the array managed by this sorter.
     */
    public void quickSort() {
        this.quickSort(0, this.toSort.length - 1);
    }
    /**
     * helper for quicksort.
     * @param low low
     * @param high high
     */
    public void quickSort(int low, int high) {
        if (low >= high) {
            return;
        }
        int p = partition(low, high);
        this.quickSort(low, p);
        this.quickSort(p + 1, high);
    }
    /**
     * partition.
     * @param low low.
     * @param high high.
     * @return int
     */
    private int partition(int low, int high) {
        // First element
        int pivot = this.toSort[low].getStrAddr();

        // Middle element
        // int middle = (low + high) / 2;
        // int pivot = a[middle];

        int i = low - 1;
        int j = high + 1;
        while (i < j) {
            i++;
            while (this.toSort[i].getStrAddr() < pivot) {
                i++;
            }
            j--;
            while (this.toSort[j].getStrAddr() > pivot) {
                j--;
            }
            if (i < j) {
                swap(i, j);
            }
        }
        return j;
    }
    /**
     * return array after quicksort.
     * @return array.
     */
    public Block[] returnArray() {
        return this.toSort;
    }

    /**
     * Swaps two entries of the array.
     * 
     * @param i the first position to swap
     * @param j the second position to swap
     */
    private void swap(int i, int j) {
        Block temp = new Block(0);
        temp = this.toSort[i];
        this.toSort[i] = this.toSort[j];
        this.toSort[j] = temp;
    }

    
    
}