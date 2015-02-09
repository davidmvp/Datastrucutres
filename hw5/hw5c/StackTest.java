import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 * This the class to test stack using Junit4 test.
 * @author davidmvp23
 *
 */

public class StackTest {
    /**
     * Create a new stack.
     */
    Stacks<String> stack = new Stacks<String>();
    /**
     * Test method for stack.
     */
    @Test public void testStack() {
        this.stack.push("2");
        this.stack.push("4");
        this.stack.push("11");
        assertEquals("Error!", "11", this.stack.top());
        assertEquals("Error!", "11", this.stack.pop());
        assertEquals("Error!", "4", this.stack.top());
        assertEquals("Error!", "4", this.stack.pop());
        assertEquals("Error!", "Top -> 2.", this.stack.toString());
        assertEquals("Error!", "2", this.stack.pop());
        assertEquals("Error!", 0, this.stack.size());
        assertEquals("Error!", true, this.stack.isEmpty());

    }



}
