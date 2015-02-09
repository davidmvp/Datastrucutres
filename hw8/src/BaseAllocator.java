/* VERSION 1.4 11/15/2012 (John)
 *  Changed Deallocate to take in String instead of int
 *  so there's no conflict with the Deallocate in Allocator.java
 */

import java.util.*;

/** Base Allocator class to handle all allocations.
 */
public class BaseAllocator  {
    
    /** The ArrayList to hold all allocated blocks. */
    private ArrayList<Block> array;
    private Block lastBlock;
    
    /** Default constructor. */
    public BaseAllocator() {
        this.array = new ArrayList<Block>();
    }
    
    /** Reports the number of allocated blocks.
     * @return The number of allocated blocks.s
     */
    public int numSize() {
        return this.array.size();
    }

    /** This allocate is not a direct implementation of
     * allocate in the Allocator.java interface.
     * This is used in the closeFit, bestFit, worstFit
     * classes to split the chosen block into the 
     * allocated and empty blocks.
     * @param addTo The empty block to add to (determined by above classes)
     * @param size The size of the block to allocate
     * @param id The allocation request id
     * @return Block the empty block truncated after allocating
     */
    public Block allocate(Block addTo, int size, int id) {
        //System.out.println("size should be " + size);
        int start = addTo.getStrAddr();
        //System.out.println("start" + start);
        int newStarting = addTo.getStrAddr() + size;
        //System.out.println("newstart" + newStarting);
        int oldSize = addTo.getBlkSize();
        //System.out.println("old size" + oldSize);
        this.lastBlock = new Block(start, size, id);
        this.array.add(this.lastBlock);
        
        return new Block(newStarting, oldSize - size);
    }
    
    /** This deallocate is not a direct implementation of
     * deallocate in the Allocator.java interface.
     * This is used in the closeFit, bestFit, worstFit
     * classes to get the deallocated block or return
     * null if it is not found.
     * @param id The memory request id
     * @return The block if found, otherwise null
     */
    public Block deallocate(String id) {
        Iterator<Block> iter = this.array.listIterator();
        int idInt = Integer.parseInt(id);
        Block temp;
        while (iter.hasNext()) {
            temp = iter.next();
            if (temp.getBlkID() == idInt) {
                this.lastBlock = temp;
                temp.setBlkID(0);
                this.array.remove(temp);
                return temp;
            }
        }
        return null;
    }
   
    public int getLastAddr() {
        return this.lastBlock.getStrAddr();
    }
    
    public int getLastSize() {
        return this.lastBlock.getBlkSize();
    }
      
    @Override
    public String toString() {
        return this.array.toString();
    }
    
    /** Tester method to print out the array of allocated blocks.
     */
    public void print() {
        System.out.println(this.array.toString());
    }

    
}
   

  
  