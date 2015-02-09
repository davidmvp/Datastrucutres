// Version 1.1 11/13/2012
/* CHANGE LOG:
 * Removed generic T from Allocator
 * Removed generic T from Block
 * Removed Construct method (not necessary for this project)
 */

/* Version 1.3 11/13/2012
 * CHANGE LOG:
 * Removed MaxMemLength()
 * Removed isEmpty()
 */

/* Version 1.4 11/15/2012 */

public interface Allocator {
    /**
     * Find out how many blocks are in the allocator.
     * 
     * @return the number
     */
    public int numSize();
    
     /**
     * Add an item to the set, no duplicates allowed.
     * 
     * @param size
     *            the size of the block
     * @return true if added, false otherwise (was duplicate)
     */
    public boolean allocate(int size);

    /**
     * Remove an item from the set.
     * 
     * @param id
     *            the ID of the block to remove
     * @return true if removed, false if not found
     */
    public boolean deallocate(int id);

    /**
     * Joins Blocks adjacent to curr all together.
     */
    public void defragment();
    
    /** Reports the number of defragmentations.
     * @return The number of defragmentations
     */
    public int getDefrags();
    
    /** Reports the number of failed allocations.
     * @return The number of failed allocations.
     */
    public int getFailedAllocations();
    
    /** Reports the average failed size. 
     * @return The average failed size
     */
    public int getAvgFailedSize();
    
    /** Reports the average allocation time.
     * @return The average allocation time
     */
    public float getAvgAllocationTime();
    
    /** Reports the average quick sort time.
     * @return The average quick sort time.
     */
    public float getAvgQuickSortTime();
    
    /** Reports the average bucket sort time.
     * @return The average bucket sort time
     */
    public float getAvgBucketSortTime();

    /** Reports whether or not the last allocation defragmented.
     * @return True if defraged, false otherwise.
     */
    public boolean lastDefrag();
    
    /** Reports the id of the last block that was allocated or deallocated (0 if deallocated).
     * @return The ID
     */
    public int getLastID();
    
    /** Reports the start address of the last block that was allocated/deallocated.
     * @return The address
     */
    public int getLastAddr();
    
    /** Reports the size of the last block that was allocated/deallocated.
     * @return The size
     */
    public int getLastSize();
    /*
     * removed construct method
     */

}

/*
 * toString() should return a string of sizes of
 * allocated blocks in order of memory address
 * e.g.: [41, 29, 33, 55]
 * 
 * public String toString();
 */
 
