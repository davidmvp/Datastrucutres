import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

/** Set of Junit tests for our BST implementation.
 */
public class BSTTest {
    SplayTree<Integer> bst;
    int[] iray = {17, 23, 8, 20, 4, 15, 25, 0, 30, 15, 6, 12, 20};

    @Before
    public void setupBST() {
        bst = new SplayTree<Integer>();
    }

    @Test
    public void testEmptyBST() {
        assertEquals("[]", bst.toString());
        assertEquals(0, bst.size());
        assertEquals(true, bst.isEmpty());
    }

    @Test
    public void testInsert() {
        bst.add(13);
        assertEquals("[13]", bst.toString());
        bst.add(23);
        assertEquals("[13, 23]", bst.toString());
        bst.add(8);
        assertEquals("[8, 13, 23]", bst.toString());
        assertEquals(3, bst.size());
        assertEquals(false, bst.isEmpty());
        //assertEquals(new Integer(13), bst.root());
    }

    @Test
    public void testInsertInorder() {
        for (int i=0; i < iray.length; i++)
            bst.add(iray[i]);
        assertEquals(iray.length-2, bst.size());
        assertEquals("[0, 4, 6, 8, 12, 15, 17, 20, 23, 25, 30]", bst.toString());
    }

    @Test
    public void testContains() {
        for (int i=0; i < iray.length; i++)
            bst.add(iray[i]);
        for (int i=0; i < iray.length; i++)
            assertEquals(true, bst.contains(iray[i]));
        System.out.println(bst.size());
        System.out.println(iray.length);
   assertEquals(iray.length-2, bst.size());
      assertEquals(false, bst.contains(-1));
      assertEquals(false, bst.contains(7));
       assertEquals(false, bst.contains(18));
        assertEquals(false, bst.contains(35));
        System.out.println(bst.toString());
        assertEquals("[0, 4, 6, 8, 12, 15, 17, 20, 23, 25, 30]", bst.toString());
    }

    @Test
    public void testDeleteTrue() {

        for (int i=0; i < iray.length; i++)
            bst.add(iray[i]);
        int size = bst.size();
        System.out.println("size" + bst.size());
        assertEquals("[0, 4, 6, 8, 12, 15, 17, 20, 23, 25, 30]", bst.toString());
        // delete leaf
        bst.remove(6);
        System.out.println("checksize!" + bst.size());
        assertEquals("[0, 4, 8, 12, 15, 17, 20, 23, 25, 30]", bst.toString());
        size--;
        System.out.println("size " + size);
        System.out.println("bstsize " + bst.size());
        assertEquals(size, bst.size());
        // delete node with only left child
        bst.remove(4);
        assertEquals("[0, 8, 12, 15, 17, 20, 23, 25, 30]", bst.toString());
        size--;
        assertEquals(size, bst.size());
        // delete node with only right child
        bst.remove(25);
       assertEquals("[0, 8, 12, 15, 17, 20, 23, 30]", bst.toString());
       size--;
       assertEquals(size, bst.size());
        // delete node with two children
        bst.remove(8);
        assertEquals("[0, 12, 15, 17, 20, 23, 30]", bst.toString());
        size--;
      assertEquals(size, bst.size());
        // delete duplicate
        bst.remove(20);
        assertEquals("[0, 12, 15, 17, 23, 30]", bst.toString());
        size--;
        assertEquals(size, bst.size());
        // delete root
        bst.remove(17);
        assertEquals("[0, 12, 15, 23, 30]", bst.toString());
       // assertEquals(new Integer(20), bst.root());
        size--;
       assertEquals(size, bst.size());
    }

    @Test
    public void testDeleteFalse() {
        bst.add(15);
        bst.add(5);
        bst.add(25);
        bst.add(10);
        bst.add(20);
        bst.add(30);
        bst.add(30);
        assertEquals("[5, 10, 15, 20, 25, 30]", bst.inOrder().toString());
        assertEquals(6, bst.size());
       bst.remove(0);
       bst.remove(12);
       bst.remove(35);
      assertEquals("[5, 10, 15, 20, 25, 30]", bst.inOrder().toString());
        assertEquals(6, bst.size());
    }
}