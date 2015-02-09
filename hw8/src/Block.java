/* Definition of Memory block. This structure is private to the usage
   in this file.Hence, the scope of this class is only this file.
   NOTE : THE BLOCKS ARE ADDRESS AS WELL SIZE ORDERED. AND, WE USE
          THE SAME LIST TO KEEP "USED" AND "UN-USED" BLOCKS. */

// Version 1.1 11/13/2012
/* CHANGE LOG:
 * Removed generic T from Block
 * Fixed type mismatch errors in set and get methods
 * Used this(...) constructor to streamline code
 * Added additional constructor with implicit end address
 */

// Version 1.3 11/13/2012
/* CHANGE LOG:
 * Changed blkID (ID) to int instead of String
 * Starting address for default constructor changed to 0
 * change name of variable from blkNum to blkID
 * change endAddr to exclusive
 * removed blkState
 */

/* Version 1.4 11/14/2012 (john)
 * CHANGE LOG:
 * Made Block comparable; added compareTo(Block that) and equals(Block that) methods
 * - Compares by size of the other Block, NOT by id
 */

/**
 * Block Class that defines a Block.
 * 
 */
public class Block implements Comparable<Block> {
    /** Start of Block (inclusive). */
    private int strAddr;
    /** End of Block (exclusive). */
    private int endAddr;
    /** Size of Block. */
    private int blkSize;
    /** ID of the Block. Invalid if blkState = UNUSED. */
    private int blkID;

    /**
     * Constructor for unallocated block with only size.
     * 
     * @param s
     *            size of block
     */
    public Block(int s) {
        this(0, s);
    }

    /**
     * Constructor for unallocated block with only start address and size.
     * 
     * @param strAddr
     *            the starting address
     * @param blkSize
     *            the block size
     */
    public Block(int strAddr, int blkSize) {
        this(strAddr, blkSize, 0);
        this.endAddr = strAddr + blkSize;
    }

    /**
     * Constructor for allocating blocks.
     * 
     * @param strAddr
     *            integer starting address
     * @param blkSize
     *            integer block size
     * @param blkID
     *            integer block id
     */
    public Block(int strAddr, int blkSize, int blkID) {
        this(strAddr, strAddr + blkSize, blkSize, blkID);
    }

    /**
     * Constructor with all parameters.
     * 
     * @param endAddr
     *            integer ending address
     * @param strAddr
     *            integer starting address
     * @param blkSize
     *            integer block size
     * @param blkID
     *            integer block id
     */
    public Block(int strAddr, int endAddr, int blkSize, int blkID) {
        this.strAddr = strAddr;
        this.endAddr = endAddr;
        this.blkSize = blkSize;
        this.blkID = blkID;
    }

    /** End of Constructor. */

    /**
     * Method to set starting address.
     * 
     * @param strAddr
     *            integer starting address
     */
    public void setStrAddr(int strAddr) {
        this.strAddr = strAddr;
    }

    /**
     * Method to set the ending address of the block.
     * 
     * @param endAddr
     *            integer ending address
     */
    public void setEndAddr(int endAddr) {
        this.endAddr = endAddr;
        this.blkSize = endAddr - this.strAddr;
    }

    /**
     * Method to set the block sixe.
     * 
     * @param blkSize
     *            integer block size
     */
    public void setBlkSize(int blkSize) {
        this.blkSize = blkSize;
        this.endAddr = this.strAddr + blkSize;
    }

    /**
     * Method to set the Block Id.
     * 
     * @param blkID
     *            integer of the block id
     */
    public void setBlkID(int blkID) {
        this.blkID = blkID;
    }

    /**
     * Method to get the starting address of a block.
     * 
     * @return integer value of the starting address
     */
    public int getStrAddr() {
        return this.strAddr;
    }

    /**
     * Method to get the ending address of a block.
     * 
     * @return integer of the ending address of a block
     */
    public int getEndAddr() {
        return this.endAddr;
    }

    /**
     * Method to get the block size of a block.
     * 
     * @return integer of the block size
     */

    public int getBlkSize() {
        return this.blkSize;
    }

    /**
     * Method to get the block id of a block.
     * 
     * @return integer block id of a block
     */
    public int getBlkID() {
        return this.blkID;
    }

    @Override
    public int compareTo(Block that) {
        return this.blkSize - that.getBlkSize();
    }

    /**
     * Method to see if two block are equal.
     * 
     * @param that
     *            the block that is being compared
     * @return boolean true if two block are equal by compareTo
     */
    public boolean equals(Block that) {
        return this.compareTo(that) == 0;
    }

    /**
     * Method to put the block into string format.
     * 
     * @return the block in string format
     */
    public String toString() {
        return "(" + this.blkID + ") " + this.blkSize + " @ " + this.strAddr
                + ">>" + this.endAddr;
    }
}/** End of Block. */