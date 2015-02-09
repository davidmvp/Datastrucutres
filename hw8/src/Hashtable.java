
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * This is the has table.
 * @author davidmvp23
 *
 * @param <T>
 */
public class Hashtable<T> {
    /**
     * load facot is 0.75.
     */
    private static double loadFactor = 0.75;
    /**
     * List of buckets.
     */
    private LinkedList<T> [ ] list; 
    /**
     * current table size.
     */
    private int currentSize;
    /**
     * Keep track of number of rehashes.
     */
    private int numRehash;
    /**
     * make arraylist to keep all the blocks.
     */
    private ArrayList<T> array = new ArrayList<T>();
    /**
     * Find the ranges of each buckets.
     */
    private int rangeOfBuckets;
    /**
     * Construct the hash table.
     * 
     */
    
    public Hashtable() {
         
    }
    /**
     * Another constructor to create the hash table. 
     * @param size size of the table.
     */
    public Hashtable(int size) {
        this.numRehash = 0;
        this.currentSize = 0;
        this.list = new LinkedList[10];
        this.rangeOfBuckets = size / 10 + 1;
        
        for (int i = 0; i < this.list.length; i++) {
            this.list[ i ] = new LinkedList<T>();
        }
      
    }

    
    /**
     * Get the size of the table.
     * @return the size.
     */
    public int getSize() {
        return this.currentSize;
    }
    /**
     * Insert element into the buckets.
     * @param x the block.
     * @param size ofthe block.
     */
    public void insert(T x, int size) {
    
        if ((this.currentSize + 1) > this.list.length * loadFactor) {
            
            rehash();
        }
    
        this.list[myhash(size)].add(x);
        this.currentSize++;
               
          
        
    }
    /**
     * Check if there is any available space.
     * @return true if there is.
     */
    public boolean checkAvailSpace() {
        for (int i = 0; i < this.list.length; i++) {
            if (this.list[i] != null) {
                return true;
            }
        }
        return false;
    }
    /**
     * Remove from the hash table.
     * @param x the item to remove.
     * @return the block.
     */
    public T remove(int x) {
       
        
        int n = myhash(x) % 10;
        int temp = n;
          
             
        for (int j = n; j < 9;j++) {
            for (int i = 0; i < this.numRehash + 1; i++) {
                temp = j + i * 10 ;
                if (this.list[temp + 1].isEmpty() == false) {
                               
                    T removeBlock =  this.list[temp + 1].getFirst();
                    this.list[temp + 1].remove(removeBlock);
                    this.currentSize--;
                                  
                    return removeBlock;
                }
            }
                        
        }
                    
                  
                
        return null;
     
    }   
    /**
    * Make the hash table logically empty.
    */
    public void makeEmpty() {
        
        for (int i = 0; i < this.list.length; i++) {
            this.list[ i ].clear();
        }
        this.currentSize = 0;    
        
    }

    /**
     * rehash, make twice as many buckets.
     */
    private void rehash() {
        this.numRehash++;
        List<T> [ ]  oldLists = this.list;
        int oldlength = this.list.length;
            // Create new double-sized, empty table
        this.list = new LinkedList[  2 * this.list.length  ];
        
        for (int i = 0; i < oldlength; i++) {
            this.list[i] = (LinkedList<T>) oldLists[i];
        }
           
        
        for (int s = oldlength; s < this.list.length; s++) {
            
            this.list[s] = new LinkedList<T>();
        }
    }
    /**
     * my hash function.
     * @param s the size
     * @return return the hash number.
     */
    private int myhash(int s) {
     
        int hashValue = (s - 1) / this.rangeOfBuckets + this.numRehash * 10;
     

        return hashValue;
    }
    
   
    /**
     * return the list of blocks.
     * @return arrraylist of all the blocks.
     */
    public ArrayList<T> defrag() {
        Iterator<T> itr;
        for (int i = 0; i < this.list.length; i++) {
            
            itr = this.list[i].iterator();
            while (itr.hasNext()) {
                this.array.add(itr.next());
            }
        }
        return this.array;
        
    }
    /**
     * toString.
     * @return return the string.
     */
    public String toString() {
        ArrayList<T> arr = new ArrayList<T>();
      
        Iterator<T> itr;
        for (int i = 0; i < this.list.length; i++) {
            
            itr = this.list[i].iterator();
            while (itr.hasNext()) {
                arr.add(itr.next());
            }
        }
        return arr.toString();
    }
   
    
   

}


