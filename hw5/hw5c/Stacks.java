/**
 * Stacks class implements Stack.
 * @author davidmvp23
 *
 * @param <T>
 */
public class Stacks<T> implements Stack<T> {
    /**
     * The head of the stack.
     */
    private Node<T> head;
    /**
     * set the szie to 0.
     */
    private int size = 0;
    /**
     * Constructor for stacks class.
     */
    public Stacks() {
        this.head = null;
    }

    @Override
    public void push(T data) {
        if (this.head == null) {
            Node<T> h = new Node<T>();
            h.data = data;
            h.next = null;
            this.head = h;
            this.size++;
            return;
        }
        Node<T> n = new Node<T>();
        n.data = data;
        n.next = this.head;
        this.head = n;
        this.size++;
        return;

    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new StackEmptyException();
        }
        T temp;
        temp = this.head.data;
        this.head = this.head.next;
        this.size--;
        return temp;
    }

    @Override
    public T top() {
        if (isEmpty()) {
            throw new StackEmptyException();
        }
        return this.head.data;
    }

    @Override
    public int size() {

        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }

        return false;
    }
    @Override
    public String toString() {
        Node<T> s = this.head;
        String str = "Top ->";
        while (s != null) {
            str = str + " " + s.data;
            s = s.next;
        }
        str = str + ".";
        return str;
    }

}


