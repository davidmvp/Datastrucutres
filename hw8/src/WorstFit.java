
import java.util.ArrayList;
import java.util.Arrays;


/** Worst Fit Allocator using MaxHeap Structure. */
public class WorstFit extends BaseAllocator implements Allocator  {
    
    /** The heap to store empty blocks. */
    private MaxHeap<Block> emptyBlks;
    /** The sorter variable. */
    
    /** The current allocation request id. */
    private int id;
    
    /** Whether or not the last allocation needed to defrag or not. */
    private boolean lastDefrag; 
    /** Number of defrags, number of failed allocations, 
     * total failed allocations size, total size of defragmented blocks.
     */
    private int defrags, failedAllocations, totalFailSize, totalDefragSize;
    /** Total allocation times (excludes defrag time), 
     * total bucket sort time, total quick sort time. */
    private long totalAllocateTime, bucketSortTime, quickSortTime;
    
    /** Constructor with initial empty block with size parameter.
     * @param size The initial empty block size
     */
    public WorstFit(int size) {
        this.emptyBlks = new MaxHeap<Block>();
        this.id = 1;
        this.emptyBlks.insert(new Block(size));
    }    

    @Override
    public boolean allocate(int size) {
        long start, end;
        Block addTo = this.emptyBlks.getMax();
        if (addTo == null) {
            this.lastDefrag = false;
            this.id++;
            this.failedAllocations++;
            this.totalFailSize += size;
            return false;
        }
        if (addTo.getBlkSize() < size) {
            this.defragment();
            this.lastDefrag = true;
            if (addTo.getBlkSize() < size) {
                this.id++;
                this.failedAllocations++;
                this.totalFailSize += size;
                return false;
            }
        } else {
            this.lastDefrag = false;
        }
        start = System.currentTimeMillis();    
        addTo = this.emptyBlks.deleteMax();
        this.emptyBlks.insert(this.allocate(addTo, size, this.id++));   
        end = System.currentTimeMillis();
        this.totalAllocateTime += end - start;
        
        return true;
    }

    @Override
    public boolean deallocate(int id) {
        Block toRemove = this.deallocate("" + id);
        if (toRemove != null && id > 0) {
            this.emptyBlks.insert(toRemove);
            return true;
        }
        return false;
    }

    @Override
    public void defragment() {
        long start, end;
        this.totalDefragSize += this.emptyBlks.getSize();
        this.defrags++;        
        
        Comparable<Block>[] toSort = this.emptyBlks.toArray();
        Block[] sort = new Block[this.emptyBlks.getSize()];
        int j;
        for (int i = 1; i < toSort.length; i++) {
            j = i - 1;
            if (toSort[i] != null) {
                sort[j] = (Block) toSort[i];
                //System.out.print(sort[j] + " ");
            }
        } 
         Sorter srt = new Sorter();
        start = System.currentTimeMillis();
        srt.bucketSort(sort);
        end = System.currentTimeMillis();
        this.bucketSortTime += end - start;
        
        
        start = System.currentTimeMillis();
        srt.QuickSort(sort);
        end = System.currentTimeMillis();
        this.quickSortTime += end - start;
        Block prev, curr = null;
        ArrayList<Block> temp = new ArrayList<Block>();                
        
        // Recombine blocks from sorted list
        for (int i = 0; i < sort.length; i++) {
            prev = curr;
            if (sort[i] != null) {
                curr = sort[i];
                if (prev != null) {
                    if (prev.getEndAddr() == curr.getStrAddr()) {
                        prev.setBlkSize(prev.getBlkSize() + curr.getBlkSize());
                        curr = prev;
                        //System.out.println(temp);
                    } else {
                        temp.add(curr);
                        //System.out.println("added " + curr);
                        
                    }
                } else {
                    temp.add(curr);
                    //System.out.println("added " + curr);
                }
            }
        } //System.out.println(temp);
        Block[] sorted = new Block[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            sorted[i] = temp.get(i);
        }
        this.emptyBlks = new MaxHeap<Block>(sorted);
        
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
        return this.totalAllocateTime / this.id;
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
        return this.id;
    }
    
    /** Returns a string of allocated & deallocated blocks.
     * @return The string
     */
    public String toStringAll() {
        return this.toString() + "\n" + this.emptyBlks.toString();
    }
   
 
    
    /** Main method to test Worst Fit.
     * @param args Arguments
     */
    public static void main(String[] args) {
        WorstFit wf = new WorstFit(100);
        for (int i = 0; i < 11; i++) {
            System.out.println(wf.allocate(10));
            
        }
        System.out.println(wf.deallocate(2));
        System.out.println(wf.deallocate(8));
        System.out.println(wf.deallocate(7));
        System.out.println(wf.toStringAll());
        wf.defragment();
        System.out.println(wf.toStringAll());
        
        
        
    }


}