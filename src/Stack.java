import java.util.Iterator;

/**
 * Stack implemented using a singly linked, double ended linked list
 */
public class Stack<E> implements Iterable<E>
{
    /**
     * Singly linked, double ended linked list
     */
    private LinkedList<E> list;

    /**
     * Initialise new Stack
     */
    Stack()
    {
        list = new LinkedList<>();
    }

    /**
     * Initialise stack with values
     * @param values
     */
    Stack(E ... values)
    {
        list = new LinkedList<>();
        for(E e : values)
        {
            push(e);
        }
    }

    /**
     * @return An iterator for this stack's contents
     */
    public Iterator<E> iterator()
    {
        return list.iterator();
    }

    /**
     * Insert a value at the front of the list. Equivalent to a stack push
     * @param value value being inserted
     */
    public void push(E value)
    {
        list.insertFirst(value);
    }

    /**
     * Removes value at front of the list. Equivalent to a stack pop
     * @return value being removed
     */
    public E pop()
    {
        return list.removeFirst();
    }

    /**
     * Returns the value at the front of the stack without removing it.
     * @return Value at front of the list
     */
    public E top()
    {
        return list.peakFirst();
    }

    /**
     * @return True if stack is empty.
     */
    public boolean isEmpty()
    {
        return list.isEmpty();
    }
}
