import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;


public class OrderedSetTest {
    
    OrderedSet<Integer> set;
    OrderedSet<Integer> set2;
   
   
    @Before
    public void setupSet() {
        set = new OrderedSetList<Integer>();
        set2 = new OrderedSetList<Integer>();
    }
    @Test
    public void testEmptySet() {
        assertEquals("{}", set.toString());
        assertEquals(0, set.size());
        assertEquals(true, set.isEmpty());
      
    }
    @Test
    public void testSize() {
        assertEquals(true, set.add(1));
        assertEquals(1, set.size());
       
      
    }
    @Test
    public void testisEmpty() {
        assertEquals(true, set.isEmpty());
        assertEquals(true, set.add(1));
        assertEquals(false, set.isEmpty());
       
      
    }
    @Test
    public void testAdd() {
        assertEquals(true, set.add(1));
        assertEquals(false, set.add(1));
        assertEquals(true, set.add(3));
        assertEquals(true, set.add(13));
        assertEquals("{1, 3, 13}", set.toString());
    }
    
    @Test
    public void testRemove() {
        assertEquals("{}", set.toString());
        assertEquals(false, set.remove(3));
        assertEquals(true, set.add(1));
        assertEquals(true, set.remove(1));
        assertEquals(false, set.remove(1));
       
    }
    @Test
    public void testContains() {
        assertEquals(false, set.contains(1));
        assertEquals(true, set.add(1));
        assertEquals(true, set.add(13));
        assertEquals(false, set.contains(12));
        assertEquals(true, set.contains(13));
        
       
    }
    @Test
    public void testUnion() {
        assertEquals(true, set.add(1));
        assertEquals(true, set.add(4));
        assertEquals(true, set.add(12));
        assertEquals(true, set.add(13));
        assertEquals(true, set2.add(1));
        assertEquals(true, set2.add(12));
        assertEquals(true, set2.add(7));
        assertEquals("{1, 4, 7, 12, 13}",set.union(set2).toString());
       
        }
    @Test
    public void testIntersect() {
        assertEquals(true, set.add(1));
        assertEquals(true, set.add(4));
        assertEquals(true, set.add(12));
        assertEquals(true, set.add(13));
        assertEquals(true, set2.add(1));
        assertEquals(true, set2.add(12));
        assertEquals(true, set2.add(7));
        assertEquals("{1, 12}",set.intersect(set2).toString());
       
        }
    @Test
    public void testSubset() {
        for (int i = 0; i<100;i++) {
            assertEquals(true, set.add(i));
        }
        assertEquals("{2, 3, 4}",set.subset(2,5).toString());
        assertEquals("{23, 24, 25, 26}",set.subset(23,27).toString());
        }
    
    @Test
    public void testtoString() {
        assertEquals(true,set.add(1));
        assertEquals("{1}",set.toString());
    }
    @Test
    public void testHasNext() {
       
       
        assertEquals(true, set.add(1));
        Iterator<Integer> it = set.iterator();
        assertEquals(true,it.hasNext());
        
        }
    @Test
    public void testNext() {
        for (int i = 0; i<5;i++) {
            assertEquals(true, set.add(i));
        }
       
        Iterator<Integer> it = set.iterator();
        int counter = 0;
        while (it.hasNext() == true) {
            int s = it.next();
            assertEquals(counter,s);
            counter++;
        }
        
        }
    
    @Test
    public void testIteratorRemove() {
        for (int i = 0; i<5;i++) {
            assertEquals(true, set.add(i));
       }
        Iterator<Integer> it = set.iterator();
      
        it.next();
        
        it.remove();
      
         assertEquals("{1, 2, 3, 4}", set.toString());
       
        }
}
