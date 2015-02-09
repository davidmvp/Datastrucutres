import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class closeFitTest {
    

        closeFit cf ;
        
        @Before
        public void setUpcloseFit() {
            this.cf = new closeFit(100);
        }

        @Test
        public void testNumSize() {
            assertEquals(0, cf.numSize());
        }

        @Test
        public void testAllocate() {
            assertEquals(true, cf.allocate(10));
            assertEquals(false, cf.allocate(30));
        }

        @Test
        public void testDeallocate() {
            cf.allocate(5);
            cf.allocate(10);
            assertEquals(true, cf.deallocate(1));
            assertEquals(false, cf.deallocate(1));
            assertEquals(false, cf.deallocate(4));
        }

        @Test
        public void testDefragment() {
            cf.allocate(4);
            cf.allocate(4);
            cf.allocate(4);
            cf.deallocate(1);
            cf.deallocate(2);
            cf.deallocate(3);
            cf.defragment();
            assertEquals("[]\n[(0) 100 @ 0>>100]", cf.toStringAll());
            
        }


    }

