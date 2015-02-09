import java.util.ArrayList;

import java.util.Iterator;
import java.util.Arrays;


/**
 * Close Fit class (hash table).
 * @author davidmvp23
 *
 */
public class closeFit extends BaseAllocator implements Allocator {
    /**
     * Make a hash table.
     */
    private Hashtable<Block> hashT;
    /**
     * keep track of the ID.
     */
    private int Id;
    /**
     * Check defrag.
     */
    private boolean lastDefrag; 
    /**
     * number of defrag, fails, total fail size and defragsize.
     */
    private int defrags = 0, failedAllocations, totalFailSize, totalDefragSize;
    /**
     * allocation time and also for bucketsort and quicksort.
     */
    private long totalAllocateTime, bucketSortTime, quickSortTime;
    /**
     * total size.
     */
    private int totalSize;
    /**
     * constructor for close fit.
     * @param size the size.
     */
    public closeFit(int size) {
        this.totalSize = size;
        this.hashT = new Hashtable<Block>(size);
        Block t = new Block(size);
        t.setBlkID(0);
        t.setEndAddr(size);
        this.hashT.insert(t, size);
        this.Id = 1;
    }


    /**
     * Allocate the a block with that size.
     * @param size size we want to allocate.
     * @return true if there is enough size;
     */
    public boolean allocate(int size) {
        long start, end, start1 = 0, end1 = 0 , hashTime = 0;
        if (this.totalSize < size) {
            this.lastDefrag = false;
            this.Id++;
            this.failedAllocations++;
            this.totalFailSize += size;
            return false;
        }
        if (this.hashT.checkAvailSpace() == false) {
            this.lastDefrag = false;
            this.Id++;
            this.failedAllocations++;
            this.totalFailSize += size;
            return false;
        }
        Block b = this.hashT.remove(size);
       
       
        if (b == null) {
            this.defragment();
            this.lastDefrag = true;
            start1 = System.nanoTime(); 
            b = this.hashT.remove(size);
            end1 = System.nanoTime();
            if (b == null) {
                this.Id++;
                this.failedAllocations++;
                this.totalFailSize += size;
                this.defrags--;
                return false;
            }
        } else {
            hashTime = end1 - start1;
            this.lastDefrag = false;
        }
        start = System.nanoTime(); 
           
        Block temp = this.allocate(b, size, this.Id);
           
        int newSize = temp.getBlkSize();
           
        this.hashT.insert(temp, newSize);
         
        this.Id++;
        end = System.nanoTime();
        this.totalAllocateTime += end - start + hashTime;
           
        return true;
       
       
     
    }


    @Override
    public boolean deallocate(int id) {
        String i = "" + id;
        Block addTo = this.deallocate(i);
        
       
        if (addTo == null) {
            return false;
        }
        int s = addTo.getBlkSize();
        this.hashT.insert(addTo, s);
        return true;
    }


    @Override
    public void defragment() {
        long start, end;
        this.totalDefragSize += this.hashT.getSize();
        this.defrags++;  
        ArrayList<Block> array = this.hashT.defrag();
        this.hashT.makeEmpty();

        Block[] sorted = new Block[array.size()];
        Block[] sorted2 = new Block[array.size()];
        for (int i = 0; i < array.size(); i++) {
            sorted[i] = array.get(i);
            sorted2[i] = array.get(i);
        }
        

        Sorter sort = new Sorter();
        start = System.nanoTime();
        
        sort.bucketSort(sorted);
        
        
        end = System.nanoTime();
        this.bucketSortTime += end - start;
        
     
        start = System.nanoTime();
        sort.QuickSort(sorted2);
        end = System.nanoTime();
        this.quickSortTime += end - start;
        sort.QuickSort(sorted);
        array = new ArrayList<Block>(Arrays.asList(sort.returnArray()));
        Block prev, curr = null;
        ArrayList<Block> temp = new ArrayList<Block>();                
              
        
        // Recombine blocks from sorted list
        for (int i = 0; i < sorted.length; i++) {
            prev = curr;
            if (sorted[i] != null) {
                curr = sorted[i];
                if (prev != null) {
                    if (prev.getEndAddr() == curr.getStrAddr()) {
                        prev.setBlkSize(prev.getBlkSize() + curr.getBlkSize());
                        curr = prev;
                        
                    } else {
                        temp.add(curr);
                       
                        
                    }
                } else {
                    temp.add(curr);
                   
                }
            }
        } 
      
        
        
        // Recombine blocks from sorted list
       
       
        
      
        Iterator<Block> itr = temp.iterator(); 
        Hashtable<Block> hashT1 = new Hashtable<Block>(this.totalSize);
        
        while (itr.hasNext()) {
            Block addTo = itr.next();
            int s = addTo.getBlkSize();
            
            hashT1.insert(addTo, s);
            
        }
        this.hashT = hashT1;
        
    }       
    
    @Override
    public int getDefrags() {
        return this.defrags;
    }

    @Override
    public int getFailedAllocations() {
        return this.failedAllocations;
    }

    @Override
    public int getAvgFailedSize() {
        return this.totalFailSize / this.failedAllocations;
    }

    @Override
    public float getAvgAllocationTime() {
        return this.totalAllocateTime / this.Id;
    }

    @Override
    public float getAvgQuickSortTime() {
        return this.quickSortTime / this.totalDefragSize;
    }
    
    @Override
    public float getAvgBucketSortTime() {
        return this.bucketSortTime / this.totalDefragSize;
    }
    
    @Override
    public boolean lastDefrag() {
        return this.lastDefrag;
    }
    
    @Override
    public int getLastID() {
        return this.Id;
    }
    /**
     * toString both the allocated blocks and unallocated blocks.
     * @return the string.
     */
    public String toStringAll() {
        return this.toString() + "\n" + this.hashT.toString();
    }
}



