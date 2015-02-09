

    /** Exception class for empty Deques.
     */
public class StackEmptyException extends RuntimeException {

        /**
         *
         */
    private static final long serialVersionUID = 1L;

        /** Create a default exception object.
         */
    public StackEmptyException() {
            super("ERROR: Stack is empty, invalid operation");
    }

        /** Create a specific exception object.
            @param err the error message
         */
    public StackEmptyException(String err) {
            super(err);
    }
}

