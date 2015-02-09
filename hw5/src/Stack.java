
/** Interface for an unbounded generic stack.
    @param <T> the base type of the objects in the stack.
 */

public interface Stack<T> {

    /** Add an element to the top of the stack.
        @param data the element to add
    */
    public void push(T data);

    /** Remove the top element from the stack.
        @throws StackEmptyException
        @return the element that was removed
    */
    public T pop();

    /** Peek at the top element of the stack.
        @throws StackEmptyException
        @return the top
    */
    public T top();

    /** Find out how many elements are in the stack.
        @return the size
    */
    public int size();

    /** Find out if the stack is empty.
        @return true if empty, false otherwise
    */
    public boolean isEmpty();

    /** Create a description of the stack contents, clearly marking the top 
        as in:  TOP -> 5 2 6 4.
        @return the description
    */
        public String toString();

}